package com.zdjf.web.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
import com.zdjf.domain.file.File;
import com.zdjf.domain.file.Guarantee;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.domain.user.Loaner;
import com.zdjf.domain.user.LoanerVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.file.IFileService;
import com.zdjf.service.file.IGuaranteeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.ILoanerService;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.StrUtils;





@Controller
@RequestMapping("/web/product")
public class ProductController {
	
	private IProductService productService;
	
	private IFileService fileService;
	
	private IGuaranteeService guaranteeService;
	
	private IDataFieldService dataFieldService;
	private ILenderService lenderService;
	private ILoanerService loanerService;
	
	//获取成品的
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String getBankPage(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		String user_name = BrowseUtil.getSession(request,"sysuser");
		
		Product product = new Product();
		
		
		Page page = PageUtils.createPage(request);
		page = productService.page(page, product);
		
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		return "sys/product/list";
	}
	
	@Transactional(rollbackFor = { Exception.class })  
	@RequestMapping(value="/toDetail")
	public String toDetail(HttpServletRequest request,
            HttpServletResponse response, Model model) throws Exception{
		DataField dataField=new DataField();
		dataField.setData_field_id("ensure_type");
		List<DataFieldVo> ensure_type=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("mortgage_type");
		List<DataFieldVo> mortgage_type=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("income_method");
		List<DataFieldVo> income_method=dataFieldService.selectForList(dataField);
		
		dataField.setData_field_id("return_method");
		List<DataFieldVo> return_method=dataFieldService.selectForList(dataField);
		List<LenderVo> lender_list=new ArrayList<LenderVo>();
		List<LoanerVo> loaner_list=new ArrayList<LoanerVo>();
		Lender lender=new Lender();
		lender_list=lenderService.selectForList(lender);
		Loaner lo=new Loaner();
		loaner_list=loanerService.selectForList(lo);
		Long product_id=IdkeySub(ddy.util.DDYUtil.genId());
		model.addAttribute("ensure_type",ensure_type);
		model.addAttribute("mortgage_type",mortgage_type);
		model.addAttribute("income_method",income_method);
		model.addAttribute("return_method",return_method);
		model.addAttribute("lender_list",lender_list);
		model.addAttribute("loaner_list",loaner_list);
		model.addAttribute("product_id",product_id);
		System.out.println(product_id);
		return "sys/product/detail";
	}
	 public static Long IdkeySub(Long id) {
	        String str = id.toString();
	        if(str.length() <= 16) {
	            return id;
	        }
	        return convToLong(str.substring(0, 16));
	    }
	 public static String getReqString(HttpServletRequest request, String key) {
	        // 入参异常判断
	        if (request == null || !emptyJudge(key)) {
	            return "";
	        }
	        // 取得请求中的值
	        String val = request.getParameter(key);
	        // 对值进行异常判断
	        if (!emptyJudge(val)) {
	            return "";
	        }
	        return val;
	    }
	 public static boolean emptyJudge(String str) {
	        if (null != str) {
	            str = str.trim();
	        }
	        return !(null == str || str.equals(""));
	    }
	 public static long convToLong(String str) {
	        // 最终结果
	        long res = 0l;
	        // 判断非空
	        if (null==str||""==str) {
	            return res;
	        }
	        // 转换类型
	        try {
	            res = Long.parseLong(str);
	        } catch (Exception e) {
	            res = 0l;
	        }
	        return res;
	    }
	 public static Date convToDate(String str) {
	        Date date = null;
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            date = sdf.parse(str);
	        } catch (ParseException e) {

	        }
	        return date;
	    }
	 private void setProduct(Product product, HttpServletRequest req) {
			// 产品ID
			product.setProduct_id(convToLong(getReqString(req, "productId")));
			// 产品编号
			product.setProduct_code(getReqString(req, "productCode"));
			// 产品名称
			product.setProduct_name(getReqString(req, "productName"));
			// 债权编号
			product.setDebt_code(getReqString(req, "debtCode"));
			// 是否分期
			product.setIs_serial(convToInt(getReqString(req, "isSerial")));
			// 分期编号
			product.setSerial_no(getReqString(req, "serialNo"));
			// 是否新手项目于
			product.setIs_fresh(convToInt(getReqString(req, "isFresh")));
			// 担保方式
			product.setEnsure_type(convToInt(getReqString(req, "ensureType")));
			// 抵押方式
			product.setMortgage_type(convToInt(getReqString(req, "mortgageType")));
			// 借款起始日期
			product.setStart_date(convToDate(getReqString(req, "startDate")));
			// 借款起始日期
			product.setEnd_date(convToDate(getReqString(req, "endDate")));
			// 债券总额(合同总金额)
			product.setDebt_money(convToDouble(getReqString(req, "debtMoney")));
			// 本次转让金额(项目金额)
			product.setMoney(convToDouble(getReqString(req, "money")));
			// 剩余金额
			product.setBalance(convToDouble(getReqString(req, "balance")));
			// 起投金额
			product.setPay_min(convToDouble(getReqString(req, "payMin")));
			// 递增金额
			product.setPay_add(convToDouble(getReqString(req, "payAdd")));
			// 投资上限
			product.setPay_max(convToDouble(getReqString(req, "payMax")));
			// 年化收益(%)
			product.setIncome(convToDouble(getReqString(req, "income")));
			// 平台贴息
			product.setPlatform_interest(convToDouble(getReqString(req, "platformInterest")));
			// 实际年化收益=年化收益+平台贴息
			product.setIncome(product.getIncome() + product.getPlatform_interest());
			// 线下年化利率(%)
			product.setIncome_offline(convToDouble(getReqString(req, "incomeOffline")));
			// 计息方式: 1.T+0 2.T+1
			product.setIncome_method(convToInt(getReqString(req, "incomeMethod")));
			// 还本付息方式: 1.按日计息，到期一次性还本付息 2.按日计息，按月付息，到期还本
			product.setReturn_method(convToInt(getReqString(req, "returnMethod")));
			// 项目状态
			product.setStatus(convToInt(getReqString(req, "status")));
			// 是否在线
			product.setOnline(convToInt(getReqString(req, "online")));
			// 备注
			product.setRemark(getReqString(req, "remark"));
			// 产品封面图片
			product.setPhoto(getReqString(req, "photo"));
		}
	 public static double convToDouble(String str) {
	        // 最终结果
	        double res = 0.0;
	        // 判断非空
	        if (!emptyJudge(str)) {
	            return res;
	        }
	        // 转换类型
	        try {
	            res = Double.parseDouble(str);
	        } catch (Exception e) {
	            res = 0.0;
	        }
	        return res;
	    }
	 public static int convToInt(String str) {
	        // 最终结果
	        int res = 0;
	        // 判断非空
	        if (!emptyJudge(str)) {
	            return res;
	        }
	        // 转换类型
	        try {
	            res = Integer.parseInt(str);
	        } catch (Exception e) {
	            res = 0;
	        }
	        return res;
	    }
	 
	@Transactional(rollbackFor = { Exception.class }) 
	@RequestMapping(value="/add",method=RequestMethod.POST)//添加管理用户
	public  @ResponseBody Map<String,Object> addProduct(HttpServletRequest request) throws Exception{
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		String product_code = request.getParameter("product_code");
		String status = request.getParameter("status");
		String product_name = request.getParameter("product_name");
		String debt_code = request.getParameter("debt_code");
		String is_serial = request.getParameter("is_serial");
		String serial_no = request.getParameter("serial_no");
		String is_fresh = request.getParameter("is_fresh");
		String ensure_type = request.getParameter("ensure_type");
		String mortgage_type = request.getParameter("mortgage_type");
		String start_date = request.getParameter("start_date");
		String will_end_date = request.getParameter("will_end_date");
		String end_date = request.getParameter("end_date");
		String debt_money = request.getParameter("debt_money");
		String money = request.getParameter("money");
		String sale_money = request.getParameter("sale_money");
		String balance = request.getParameter("balance");
		String buyer_count = request.getParameter("buyer_count");
		String pay_min = request.getParameter("pay_min");
		String pay_add = request.getParameter("pay_add");
		String pay_max = request.getParameter("pay_max");
		String income = request.getParameter("income");
		String income_offline = request.getParameter("income_offline");
		
		
		String income_method = request.getParameter("income_method");
		String return_method = request.getParameter("return_method");
		String online = request.getParameter("online");
		String remark = request.getParameter("remark");
		String create_id = request.getParameter("create_id");
		String create_time = request.getParameter("create_time");
		String audit_id = request.getParameter("audit_id");
		String audit_time = request.getParameter("audit_time");
		String issue_time = request.getParameter("issue_time");
		String full_time = request.getParameter("full_time");
		String order_no = request.getParameter("order_no");
		String photo = request.getParameter("photo");
		String will_issue_time = request.getParameter("will_issue_time");
		String will_show_time = request.getParameter("will_show_time");
		String act_show_time = request.getParameter("act_show_time");
		String advance_status = request.getParameter("advance_status");
		String advance_date = request.getParameter("advance_date");
		String is_send_msg = request.getParameter("is_send_msg");
		String platform_interest = request.getParameter("platform_interest");
		String product_type = request.getParameter("product_type");
		String return_days = request.getParameter("return_days");
		
		
		Product product = new Product();
//		product.setProduct_code(product_code);
//		product.setStatus(Integer.parseInt(status));
//		product.setProduct_name(product_name);
//		product.setDebt_code(debt_code);
//		product.setIs_serial(Integer.parseInt(is_serial));
//		product.setSerial_no(serial_no);
//		product.setIs_fresh(Integer.parseInt(is_fresh));
//		product.setEnsure_type(Integer.parseInt(ensure_type));
//		product.setMortgage_type(Integer.parseInt(mortgage_type));
//		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		ParsePosition pos = new ParsePosition(0);
//		
//		//product.setStart_date(formatter.parse(start_date, pos));
//		product.setStart_date(new Date());
//		pos = new ParsePosition(0);
//		//product.setWill_end_date(formatter.parse(will_end_date, pos));
//		product.setWill_end_date(new Date());
//		
//		pos = new ParsePosition(0);
//		
//		//product.setEnd_date(formatter.parse(end_date, pos));
//		product.setEnd_date(new Date());
//		
//		product.setDebt_money(Double.valueOf(debt_money));
//		product.setMoney(Double.valueOf(money));
//		product.setSale_money(Double.valueOf(sale_money));
//		product.setBalance(Double.valueOf(balance));
//		product.setBuyer_count(Integer.parseInt(buyer_count));
//		product.setPay_min(Double.valueOf(pay_min));
//		product.setPay_add(Double.valueOf(pay_add));
//		product.setPay_max(Double.valueOf(pay_max));
//		product.setIncome(Double.valueOf(income));
//		product.setIncome_offline(Double.valueOf(income_offline));
//		product.setIncome_method(Integer.parseInt(income_method));
//		product.setReturn_method(Integer.parseInt(return_method));
//		product.setOnline(Integer.parseInt(online));
//		product.setRemark(remark);
//		product.setCreate_id(Long.parseLong(create_id));
//		product.setCreate_time(new Date());
//		product.setAudit_id(Long.parseLong(audit_id));
//		pos = new ParsePosition(0);
//		//product.setAudit_time(formatter.parse(audit_time,pos));
//		product.setAudit_time(new Date());
//		pos = new ParsePosition(0);
//		//product.setIssue_time(formatter.parse(issue_time,pos));
//		product.setIssue_time(new Date());
//		pos = new ParsePosition(0);
//		//product.setFull_time(formatter.parse(full_time,pos));
//		product.setFull_time(new Date());
//		product.setOrder_no(Integer.parseInt(order_no));
//		product.setPhoto(photo);
//		pos = new ParsePosition(0);
//		//product.setWill_issue_time(formatter.parse(will_issue_time,pos));
//		product.setWill_issue_time(new Date());
//		pos = new ParsePosition(0);
//		//product.setWill_show_time(formatter.parse(will_show_time,pos));
//		product.setWill_show_time(new  Date ());
//		pos = new ParsePosition(0);
//		//product.setAct_show_time(formatter.parse(act_show_time,pos));
//		product.setAct_show_time(new Date());
//		product.setAdvance_status(Integer.parseInt(advance_status));
//		pos = new ParsePosition(0);
//		//product.setAdvance_date(formatter.parse(advance_date,pos));
//		product.setAdvance_date(new Date());
//		product.setIs_send_msg(Integer.parseInt(is_send_msg));
//		product.setPlatform_interest(Double.parseDouble(platform_interest));
//		product.setProduct_type(Integer.parseInt(product_type));
//		product.setReturn_days(Integer.parseInt(return_days));
		setProduct(product, request);
		if (product != null) {
			
			productService.save(product);
			Long proId =  product.getId();
			
			File proFile = new File();
			proFile.setFrom_id(proId);
			proFile.setFile_type(1);
			proFile.setFile_height(100);
			proFile.setFile_num(1);
			proFile.setFile_width(200);
			proFile.setCreate_time(new Date());
			fileService.save(proFile);
			
			
			Guarantee gua = new Guarantee();
			
			gua.setFrom_id(proId);
			gua.setGuarantee_num(1);
			gua.setCreate_time(new Date());
			guaranteeService.save(gua);
			
			
			resultMap.put("status", "1");
			return resultMap;
		} else {
			resultMap.put("status", "0");
			return resultMap;
		}
	}
	
	
	@RequestMapping(value = "/list_v2", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public @ResponseBody String list2(HttpServletRequest request) {
		Integer orderType = StrUtils.convToInt(
				request.getParameter("orderType"),
				2);
		Integer searchType = StrUtils.convToInt(
				request.getParameter("searchType"),1);
		Integer productType = StrUtils.convToInt(
				request.getParameter("productType"),
				0);
		Integer reqSource = StrUtils.convToInt(
				request.getParameter("reqSource"),
				1);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("pagename", "selectProductForIndex");
		map.put("total", "selectProductForIndexCount");
		map.put("orderType", orderType);
		map.put("searchType", searchType);
		map.put("productType", productType);
		map.put("reqSource", reqSource);
		Page page = PageUtils.createPage(request);
		page = productService.page(page, map);
//		webProductBo.list(productRsp, reqSource, searchType, productType,
//				orderType, currPage, pageSize);
		Map<String, Object> map1 =new HashMap<String, Object>();
		map1.put("total", page.getTotal());
		map1.put("currentPage", page.getCurrentPage());
		map1.put("records", page.getDataList());
		map1.put("respCode", 0);
		return JsonUtil.objectTojson(map1);
	}
	
	@Autowired(required = true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	
	@Autowired(required = true)
	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}
	
	@Autowired(required = true)
	public void setGuaranteeService(IGuaranteeService guaranteeService) {
		this.guaranteeService = guaranteeService;
	}
	
	@Autowired(required = true)
	public void setDataFieldService(IDataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}

	@Autowired(required = true)
	public void setLenderService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}

	@Autowired(required = true)
	public void setLoanerService(ILoanerService loanerService) {
		this.loanerService = loanerService;
	}

}
