package com.zdjf.web.admin.product;

import java.text.DateFormat;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.dao.file.IFileDao;
import com.zdjf.dao.product.IProductDao;
import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
import com.zdjf.domain.file.File;
import com.zdjf.domain.file.FileVo;
import com.zdjf.domain.file.GuaranteeVo;
import com.zdjf.domain.notice.News;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductIncome;
import com.zdjf.domain.product.ProductIncomeVo;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.domain.user.Loaner;
import com.zdjf.domain.user.LoanerVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.file.IFileService;
import com.zdjf.service.file.IGuaranteeService;
import com.zdjf.service.front.IFrontService;
import com.zdjf.service.notice.INewsService;
import com.zdjf.service.notice.NewsService;
import com.zdjf.service.product.IProductIncomeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.ILoanerService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.ProductConstants;
import com.zdjf.util.ProductConstants.FileType;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.webservice.express.SmsReturn;

@Controller
@RequestMapping("/sys/product")
public class SysProductController {

	private IProductService productService;
	private IProductDao productDao;

	private IFileService fileService;
	private IFileDao fileDao;


	private IDataFieldService dataFieldService;

	private ILoanerService loanerService;

	private ILenderService lenderService;
	private IProductIncomeService productIncomeService;
	private IFrontService frontService;
	//获取成品的
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String getBankPage(HttpServletRequest req,
			HttpServletResponse response, Model model) throws ParseException {
		Map<String, Object> params=new HashMap<String, Object>();
		//搜索参数
		params.put(ProductConstants.REQ_PARAM_SEARCH_PARAM, RequestUtils.getReqString(
				req, ProductConstants.REQ_PARAM_SEARCH_PARAM, true));
		// 取得请求中的查询类型参数
		params.put(ProductConstants.REQ_PARAM_SEARCH_TYPE, StrUtils.convToInt(
				RequestUtils.getReqString(req, ProductConstants.REQ_PARAM_SEARCH_TYPE)));
		//理财状态
		params.put(ProductConstants.REQ_PARAM_PRODUCT_STATUS,
				RequestUtils.getReqString(req, ProductConstants.REQ_PARAM_PRODUCT_STATUS));
		// 取得请求中的到期时间
		params.put(ProductConstants.REQ_PARAM_START_DATE,
				RequestUtils.getReqString(req, Constants.REQ_PARAM_START_DATE));
		// 取得请求中的到期时间
		params.put(ProductConstants.REQ_PARAM_END_DATE,
				RequestUtils.getReqString(req, Constants.REQ_PARAM_END_DATE));
		// 取得请求中的发布时间
		params.put(ProductConstants.REQ_PARAM_ISSUE_START_DATE,
				RequestUtils.getReqString(req,
						ProductConstants.REQ_PARAM_ISSUE_START_DATE));
		// 取得请求中的发布时间
		params.put(ProductConstants.REQ_PARAM_ISSUE_END_DATE,
				RequestUtils.getReqString(req,
						ProductConstants.REQ_PARAM_ISSUE_END_DATE));
		//转让金额
		params.put(ProductConstants.REQ_PARAM_MONEY_MIN,
				RequestUtils.getReqString(req,
						ProductConstants.REQ_PARAM_MONEY_MIN));
		//转让金额
		params.put(ProductConstants.REQ_PARAM_MONEY_MAX,
				RequestUtils.getReqString(req,
						ProductConstants.REQ_PARAM_MONEY_MAX));
		//收益天数
		params.put(ProductConstants.REQ_PARAM_INCOME_DAYS_MIN,
				RequestUtils.getReqString(req,
						ProductConstants.REQ_PARAM_INCOME_DAYS_MIN));
		//收益天数
		params.put(ProductConstants.REQ_PARAM_INCOME_DAYS_MAX,
				RequestUtils.getReqString(req,
						ProductConstants.REQ_PARAM_INCOME_DAYS_MAX));
		params.put("pagename", "selectProductForPage");
		params.put("total", "selectProductForPageCn");

		List<LenderVo> lenderList=new ArrayList<LenderVo>();
		Lender lender=new Lender();
		lenderList=lenderService.selectForList(lender);
		Map<String , Object> lenderMap=new HashMap<String, Object>();
		for(int i=0;i<lenderList.size();i++){
			lenderMap.put(lenderList.get(i).getId().toString(), lenderList.get(i));
		}

		List<DataFieldVo> product_status=new ArrayList<DataFieldVo>();
		Map<String , Object> productStatusMap = new HashMap<String, Object>();
		DataField dataField=new DataField();
		dataField.setData_field_id("product_status");
		product_status=dataFieldService.selectForList(dataField);
		for(int m=0;m<product_status.size();m++){
			productStatusMap.put(product_status.get(m).getData_field_value(), product_status.get(m));
		}

		Map<String, Object> map=new HashMap<String, Object>();
		map.put("params", params);
		Page page = PageUtils.createPage(req);
		page = productService.page(page, params);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String,Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		for(int j=0;j<dataList.size();j++){
			Map<String, Object> paramsMap=dataList.get(j);
			String lender_id=paramsMap.get("lender_id").toString();
			Lender lender1=(Lender) lenderMap.get(lender_id);
			paramsMap.put("lender_name", (lender1.getLender_type()==1?"个人":"企业")+"-"+lender1.getName());
			String status=paramsMap.get("status").toString();
			DataFieldVo dataFieldVo=(DataFieldVo)productStatusMap.get(status);
			paramsMap.put("create_id", dataFieldVo.getData_field_name());
			Date start_date=sdf.parse(paramsMap.get("start_date").toString());
			Date end_time=sdf.parse(paramsMap.get("end_date").toString());
			Date issue_time=new Date();
			if(paramsMap.containsKey("issue_time")){
				issue_time=sdf.parse(paramsMap.get("issue_time").toString());
			}
			int income_method=0;
			if(paramsMap.containsKey("income_method")){
				income_method=StrUtils.convToInt(paramsMap.get("income_method").toString());
			}

			paramsMap.put("income_day", StrUtils.getIncomeDays(Integer.parseInt(status), start_date, end_time, issue_time, income_method));
		}
		model.addAttribute("detail", map);
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));

		//		return "sys/product/list";
		return "sysNew/product/list";
	}

	@RequestMapping(value="/toDetail")
	public String toDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception{
		String id=RequestUtils.getReqString(request, "product_id");
		String update_type=RequestUtils.getReqString(request, "update_type");
		Long product_id= 0L;
		if(StrUtils.emptyJudge(id)){
			product_id=Long.valueOf(id);
			Product product=productService.selectForObjectById(product_id);
			model.addAttribute("detail", product);
			File file=new File();
			file.setFrom_id(product_id);
			file.setFile_type(1);
			List<File> infoList=(List<File>) fileDao.selectForList("selectFile", file);
			file.setFile_type(2);
			List<File> suggestList=(List<File>) fileDao.selectForList("selectFile", file);
			file.setFile_type(3);
			List<File> contranctList=(List<File>) fileDao.selectForList("selectFile", file);
			file.setFile_type(4);
			List<File> otherList=(List<File>) fileDao.selectForList("selectFile", file);

			ProductIncome productIncome=new ProductIncome();
			productIncome.setProduct_id(product_id);
			List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome);
			model.addAttribute("infoList", infoList);
			model.addAttribute("suggestList", suggestList);
			model.addAttribute("contranctList", contranctList);
			model.addAttribute("otherList", otherList);
			model.addAttribute("productIncomeList", productIncomeList);
		}else{
			product_id=(Long) productDao.selectForObject("getMaxId");
		}
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
		StrUtils.IdkeySub(ddy.util.DDYUtil.genId());
		model.addAttribute("ensure_type",ensure_type);
		model.addAttribute("mortgage_type",mortgage_type);
		model.addAttribute("income_method",income_method);
		model.addAttribute("return_method",return_method);
		model.addAttribute("lender_list",lender_list);
		model.addAttribute("loaner_list",loaner_list);
		model.addAttribute("product_id",product_id);
		model.addAttribute("update_type",update_type);
		//		return "sys/product/detail";
		return "sysNew/product/detail";
	}
	private void setModel(HttpServletRequest req, Model model) throws Exception {
		Map map=new HashMap();
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
		model.addAttribute("ensure_type",ensure_type);
		model.addAttribute("mortgage_type",mortgage_type);
		model.addAttribute("income_method",income_method);
		model.addAttribute("return_method",return_method);
		model.addAttribute("lender_list",lender_list);
		model.addAttribute("loaner_list",loaner_list);
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		map.put("product_code",RequestUtils.getReqString(req, "productCode") );
		map.put("product_name", RequestUtils.getReqString(req, "productName"));
		map.put("debt_code", RequestUtils.getReqString(req, "debtCode"));
		map.put("is_serial", RequestUtils.getReqString(req, "isSerial"));
		map.put("serial_no", RequestUtils.getReqString(req, "serialNo"));
		map.put("is_fresh", RequestUtils.getReqString(req, "isFresh"));
		map.put("ensure_type", RequestUtils.getReqString(req, "ensureType"));
		map.put("mortgage_type", RequestUtils.getReqString(req, "mortgageType"));
		map.put("start_date", StrUtils.convToDate(RequestUtils.getReqString(req, "startDate")));
		map.put("end_date", StrUtils.convToDate(RequestUtils.getReqString(req, "endDate")));
		map.put("debt_money", RequestUtils.getReqString(req, "debtMoney"));
		map.put("money", RequestUtils.getReqString(req, "money"));
		map.put("pay_min", RequestUtils.getReqString(req, "payMin"));
		map.put("pay_add", RequestUtils.getReqString(req, "payAdd"));
		map.put("pay_max", RequestUtils.getReqString(req, "payMax"));
		map.put("income", RequestUtils.getReqString(req, "income"));
		map.put("platform_interest", RequestUtils.getReqString(req, "platformInterest"));
		map.put("income_offline", RequestUtils.getReqString(req, "incomeOffline"));
		map.put("income_method", RequestUtils.getReqString(req, "incomeMethod"));
		map.put("return_method", RequestUtils.getReqString(req, "returnMethod"));
		map.put("online", RequestUtils.getReqString(req, "online"));
		map.put("remark", RequestUtils.getReqString(req, "remark"));
		map.put("photo", RequestUtils.getReqString(req, "photo"));
		map.put("protect_mode", RequestUtils.getReqString(req, "protectMode"));
		map.put("product_desc", RequestUtils.getReqString(req, "productDesc"));
		map.put("lend_use", RequestUtils.getReqString(req, "lendUse"));
		map.put("protect_msg", RequestUtils.getReqString(req, "protectMsg"));
		map.put("lender_id", RequestUtils.getReqString(req, "lendId"));
		map.put("loan_id", RequestUtils.getReqString(req, "loanId"));
		map.put("info_urls", RequestUtils.getReqString(req, "infoUrls"));
		map.put("suggest_urls", RequestUtils.getReqString(req, "suggestUrls"));
		map.put("contract_urls", RequestUtils.getReqString(req, "contractUrls"));
		map.put("other_urls", RequestUtils.getReqString(req, "otherUrls"));
		map.put("status", 1);
		model.addAttribute("detail", map);
		
		String[] start_date = RequestUtils.getReqArray(req, "start_date");
		String[] end_date = RequestUtils.getReqArray(req, "end_date");
		String[] pay_date = RequestUtils.getReqArray(req, "pay_date");
		
		List productIncomeList=new ArrayList();
		if(StrUtils.emptyJudge(start_date[0])&&StrUtils.emptyJudge(end_date[0])&&StrUtils.emptyJudge(pay_date[0])){
			for (int i=0;i< start_date.length;i++) {
				ProductIncome productIncome=new ProductIncome();
				productIncome.setStart_date(df.parse(start_date[i]));
				productIncome.setEnd_date(df.parse(end_date[i]));
				productIncome.setPay_date(df.parse(pay_date[i]));
				productIncome.setStatus(1);
				productIncome.setCreate_time(df.parse(df.format(new Date())));
				productIncomeList.add(productIncome);
			}
		}
		model.addAttribute("productIncomeList", productIncomeList);
	}
	private void setProduct(Product product, HttpServletRequest req) {
		// 产品编号
		product.setProduct_code(RequestUtils.getReqString(req, "productCode"));
		// 产品名称
		product.setProduct_name(RequestUtils.getReqString(req, "productName"));
		// 债权编号
		product.setDebt_code(RequestUtils.getReqString(req, "debtCode"));
		// 是否分期
		product.setIs_serial(StrUtils.convToInt(RequestUtils.getReqString(req, "isSerial")));
		// 分期编号
		product.setSerial_no(RequestUtils.getReqString(req, "serialNo"));
		// 是否新手项目于
		product.setIs_fresh(StrUtils.convToInt(RequestUtils.getReqString(req, "isFresh")));
		// 担保方式
		product.setEnsure_type(StrUtils.convToInt(RequestUtils.getReqString(req, "ensureType")));
		// 抵押方式
		product.setMortgage_type(StrUtils.convToInt(RequestUtils.getReqString(req, "mortgageType")));
		// 借款起始日期
		product.setStart_date(StrUtils.convToDate(RequestUtils.getReqString(req, "startDate")));
		// 借款起始日期
		product.setWill_end_date(StrUtils.convToDate(RequestUtils.getReqString(req, "endDate")));
		product.setEnd_date(StrUtils.convToDate(RequestUtils.getReqString(req, "endDate")));
		// 债券总额(合同总金额)
		product.setDebt_money(StrUtils.convToDouble(RequestUtils.getReqString(req, "debtMoney")));
		// 本次转让金额(项目金额)
		product.setMoney(StrUtils.convToDouble(RequestUtils.getReqString(req, "money")));
		// 剩余金额
		// 起投金额
		product.setPay_min(StrUtils.convToDouble(RequestUtils.getReqString(req, "payMin")));
		// 递增金额
		product.setPay_add(StrUtils.convToDouble(RequestUtils.getReqString(req, "payAdd")));
		// 投资上限
		product.setPay_max(StrUtils.convToDouble(RequestUtils.getReqString(req, "payMax")));
		// 年化收益(%)
		product.setIncome(StrUtils.convToDouble(RequestUtils.getReqString(req, "income")));
		// 平台贴息
		product.setPlatform_interest(StrUtils.convToDouble(RequestUtils.getReqString(req, "platformInterest")));
		// 实际年化收益=年化收益+平台贴息
		product.setIncome(product.getIncome() + product.getPlatform_interest());
		// 线下年化利率(%)
		product.setIncome_offline(StrUtils.convToDouble(RequestUtils.getReqString(req, "incomeOffline")));
		// 计息方式: 1.T+0 2.T+1
		product.setIncome_method(StrUtils.convToInt(RequestUtils.getReqString(req, "incomeMethod")));
		// 还本付息方式: 1.按日计息，到期一次性还本付息 2.按日计息，按月付息，到期还本
		product.setReturn_method(StrUtils.convToInt(RequestUtils.getReqString(req, "returnMethod")));
		// 项目状态
		// 是否在线
		product.setOnline(StrUtils.convToInt(RequestUtils.getReqString(req, "online")));
		// 备注
		product.setRemark(RequestUtils.getReqString(req, "remark"));
		// 产品封面图片
		product.setPhoto(RequestUtils.getReqString(req, "photo"));
		//
		product.setProtect_mode(RequestUtils.getReqString(req, "protectMode"));
		//product_desc
		product.setProduct_desc(RequestUtils.getReqString(req, "productDesc"));
		//lend_use
		product.setLend_use(RequestUtils.getReqString(req, "lendUse"));
		//protect_msg
		product.setProtect_msg(RequestUtils.getReqString(req, "protectMsg"));
		String lender=StrUtils.emptyJudge(RequestUtils.getReqString(req, "lendId"))?RequestUtils.getReqString(req, "lendId"):"0";
		String loan=StrUtils.emptyJudge(RequestUtils.getReqString(req, "loanId"))?RequestUtils.getReqString(req, "loanId"):"0";
		product.setLender_id(Long.parseLong(lender));
		product.setLoan_id(Long.parseLong(loan));;
		product.setCreate_time(new Date());
	}

	@RequestMapping(value="/delete",method=RequestMethod.POST)//添加管理用户
	public @ResponseBody String deleteProduct(HttpServletRequest req) throws Exception{
		String product_id=RequestUtils.getReqString(req, "product_id");
		Product product=new Product();
		product.setId(Long.valueOf(product_id));
		productService.delete(product);
		File file=new File();
		file.setFrom_id(Long.valueOf(product_id));
		fileService.delete(file);
		return "success";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)//添加管理用户
	public String addProduct(HttpServletRequest req,Model model) {
		SmsReturn sms=new SmsReturn();
		try {
			String update_type=RequestUtils.getReqString(req, "update_type");
			Product product1 =productService.selectForObjectById(StrUtils.convToLong(RequestUtils.getReqString(req, "productId")));
			if(("1".equals(update_type)||"2".equals(update_type))&&null!=product1&&StrUtils.emptyJudge(product1.getDebt_code())){
				File file=new File();
				file.setFrom_id(product1.getId());
				fileService.delete(file);
				ProductIncome productIncome=new ProductIncome();
				productIncome.setProduct_id(product1.getId());
				productIncomeService.delete(productIncome);
				System.out.println("清除图片和付息模板："+product1.getId());
			}
			Product product=new Product();
			setProduct(product, req);
			setModel(req,model);
			Long product_id=0L;
			
			if(!StrUtils.emptyJudge(update_type)||"3".equals(update_type)){
				String debt_code=RequestUtils.getReqString(req, "debtCode");
				Product product2=new Product();
				product2.setDebt_code(debt_code);
				List<ProductVo> productList=productService.selectForList(product2);
				if(null!=productList&&productList.size()>0){
					model.addAttribute("errorMsg", "债券编号重复");
					return "sysNew/product/detail";
				}
				product.setStatus(1);
				product.setBalance(StrUtils.convToDouble(RequestUtils.getReqString(req, "money")));
				product.setProduct_type(1);
				product_id=StrUtils.convToLong(productService.save(product).toString());
			}else if(null!=update_type&&("1".equals(update_type)||"2".equals(update_type))){
				product.setStatus(product1.getStatus());
				product.setBalance(product1.getBalance());
				product.setId(StrUtils.convToLong(RequestUtils.getReqString(req, "productId")));
				productService.updateProductById(product);
				product_id=product.getId();
			}
			System.out.println("....................项目id:"+product_id);
			String[] start_date = RequestUtils.getReqArray(req, "start_date");
			String[] end_date = RequestUtils.getReqArray(req, "end_date");
			String[] pay_date = RequestUtils.getReqArray(req, "pay_date");
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			if(StrUtils.emptyJudge(start_date[0])&&StrUtils.emptyJudge(end_date[0])&&StrUtils.emptyJudge(pay_date[0])){
				for (int i=0;i< start_date.length;i++) {
					ProductIncome productIncome=new ProductIncome();
					productIncome.setProduct_id(product_id);
					productIncome.setStart_date(df.parse(start_date[i]));
					productIncome.setEnd_date(df.parse(end_date[i]));
					productIncome.setPay_date(df.parse(pay_date[i]));
					productIncome.setStatus(1);
					productIncome.setCreate_time(df.parse(df.format(new Date())));
					System.out.println("....................付息模板id:"+productIncomeService.save(productIncome).toString());
				}
			}
			List<FileVo> files = new ArrayList<FileVo>();
			// 产品相关URL集合
			String info = RequestUtils.getReqString(req, "infoUrls");
			String[] infos=info.split("<p><img");
			if(StrUtils.emptyStrArray(infos)&&!"".equals(info)) {
				for (int i=1;i< infos.length;i++) {
					String url=infos[i].substring(infos[i].indexOf("src=\"")+5,(infos[i].indexOf("\" ")>(infos[i].indexOf("src=\"")+5))?infos[i].indexOf("\" "):infos[i].indexOf("\"/"));
					System.out.println("....................产品id:"+fileService.save(getProductFile(product_id, url, i+1,"0","0", FileType.INFO)).toString());
				}
			}

			// 律师意见URL集合
			String suggest = RequestUtils.getReqString(req, "suggestUrls");
			String[] suggests = suggest.split("<p><img");
			if(StrUtils.emptyStrArray(suggests)&&!"".equals(suggests)) {
				for (int i=1;i< suggests.length;i++) {
					String url=suggests[i].substring(suggests[i].indexOf("src=\"")+5,(suggests[i].indexOf("\" ")>(suggests[i].indexOf("src=\"")+5))?suggests[i].indexOf("\" "):suggests[i].indexOf("\"/"));
					System.out.println(".................... 律师意见id:"+fileService.save(getProductFile(product_id, url, i+1,"0","0", FileType.SUGGEST)).toString());
				}
			}

			// 合同文件URL集合
			String contract = RequestUtils.getReqString(req, "contractUrls");
			String[] contracts = contract.split("<p><img");
			if(StrUtils.emptyStrArray(contracts)&&!"".equals(contracts)) {
				for (int i=1;i< contracts.length;i++) {
					String url=contracts[i].substring(contracts[i].indexOf("src=\"")+5,(contracts[i].indexOf("\" ")>(contracts[i].indexOf("src=\"")+5))?contracts[i].indexOf("\" "):contracts[i].indexOf("\"/"));
					System.out.println(".................... 合同文件id:"+fileService.save(getProductFile(product_id, url,i+1,"0","0", FileType.CONTRACT)).toString());
				}
			}

			// 其他图片URL集合
			String other = RequestUtils.getReqString(req, "otherUrls");
			String[] others = other.split("<p><img");
			if(StrUtils.emptyStrArray(others)&&!"".equals(others)) {
				for (int i=1;i< others.length;i++) {
					String url=others[i].substring(others[i].indexOf("src=\"")+5,(others[i].indexOf("\" ")>(others[i].indexOf("src=\"")+5))?others[i].indexOf("\" "):others[i].indexOf("\"/"));
					System.out.println(".................... 其他文件id:"+fileService.save(getProductFile(product_id, url, i+1,"0", "0", FileType.OTHER)).toString());
				}
			}
			return "redirect:/sys/product/page.action";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "保存或更新异常");
			return "sysNew/product/detail";
		}
	}

	@RequestMapping(value = "/audit", method = RequestMethod.GET)
	public @ResponseBody String audit(HttpServletRequest request,HttpServletResponse response, Model model){
		Long pro_id=StrUtils.convToLong(RequestUtils.getReqString(request, "product_id"));
		Product pro=productService.selectForObjectById(pro_id);
		String index=RequestUtils.getReqString(request, "index");
		String remark=RequestUtils.getReqString(request, "remark");
		Map<String,Object> map=new HashMap<String, Object>();
		if(index.equals("0")){
			pro.setStatus(1);
			pro.setRemark(remark);
			productService.updateProductById(pro);
			map.put("status", "fail");
			return JsonUtil.objectTojson(map);
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String return_type=(pro.getReturn_method()==1)?"REPAY_CAPITAL_WITH_INTEREST":"SCHEDULED_INTEREST_PAYMENTS_DUE";
		String bid_product_type="";
		if(pro.getMortgage_type()==1){
			bid_product_type="HOUSING_LOAN";
		}else if(pro.getMortgage_type()==2){
			bid_product_type="CAR_LOAN";
		}else if(pro.getMortgage_type()==3){
			bid_product_type="OTHER";
		}
		String protocol_type="";
		if(pro.getProduct_type()==1){
			protocol_type="ASSIGNMENT_DEBT";
		}
		long day=(pro.getEnd_date().getTime()-pro.getStart_date().getTime())/((24*60*60*1000));

		String start_date=(df.format(pro.getStart_date())).replace("-", "")+"000000";
		String end_date=(df.format(pro.getEnd_date())).replace("-", "")+"000000";
		df=new SimpleDateFormat("yyyyMMddHHmmss");
		String product_name=pro.getProduct_name()+df.format(new Date());
		Lender lender=lenderService.selectForObjectById(pro.getLender_id());
		String lender_info=lender.getUser_id()+"~"+"UID"+"~"+pro.getDebt_money()+"~"+"买车"+"~"+lender.getPhone();
		String result=SinaUtil.ItemEntry(pro.getDebt_code(),product_name, pro.getMoney(), pro.getIncome(), Long.toString(day),
				return_type, protocol_type, bid_product_type, "", "", 
				pro.getPay_add(), pro.getPay_max(), pro.getPay_min(), "", "", 
				start_date, end_date, "银行担保", "", lender_info);
		map=JsonUtil.jsonToMap(result);
		System.out.println(result);
		if(map.containsKey("response_code")){
			if(!"".equals(map.get("response_code"))&&null!=map.get("response_code")&&map.get("response_code").equals("APPLY_SUCCESS")){
				map.put("status", 1);
				pro.setStatus(3);
				productService.updateProductById(pro);
				frontService.reloadIndexCache();
			}else{
				map.put("status", 2);
				pro.setStatus(1);
				pro.setRemark(remark);
				productService.updateProductById(pro);
			}
		}
		return JsonUtil.objectTojson(map);
	}

	@RequestMapping("/issue")
	public @ResponseBody SmsReturn issue(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception{
		Long pro_id=StrUtils.convToLong(RequestUtils.getReqString(request, "productId"));
		String auditType=RequestUtils.getReqString(request, "auditType");
		String willIssueTime=RequestUtils.getReqString(request, "willIssueTime");
		String willShowTime=RequestUtils.getReqString(request, "willShowTime");
		String isSendMsg=RequestUtils.getReqString(request, "isSendMsg");
		Product pro=productService.selectForObjectById(pro_id);
		//		String result=SinaUtil.ItemQurty(pro.getDebt_code());
		//		Map map=JsonUtil.jsonToMap(result);
		Map map=new HashMap();
		Map data=new HashMap();
		data.put("bid_status", "VALID");
		map.put("data", data);
		SmsReturn sr=new SmsReturn();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(map.containsKey("data")&&((Map)map.get("data")).containsKey("bid_status")){
			String bid_status=(String) ((Map)map.get("data")).get("bid_status");
			if(bid_status.equals("VALID")){
				if("1".equals(auditType)){
					pro.setStatus(4);
					pro.setOnline(1);
					pro.setIssue_time(new Date());
					productService.updateProductById(pro);
				}else if("2".equals(auditType)){
					pro.setStatus(21);
					pro.setWill_issue_time(df.parse(willIssueTime));
					pro.setWill_show_time(df.parse(willShowTime));
					if("on".endsWith(isSendMsg)){
						pro.setIs_send_msg(1);
					}
					productService.updateProductById(pro);
				}
				frontService.reloadIndexCache();
				sr.setReturnCase(true);
				sr.setStatus(100);
			}else if(bid_status.equals("REJECT")){
				sr.setReturnCase(false);
				sr.setStatus(100);
				pro.setStatus(1);
				productService.updateProductById(pro);
				sr.setContent(pro.getDebt_code()+"已经"+map.get("response_msg")+"!状态:"+bid_status+" 请修改标的号和标的名称");
			}
		}else{
			sr.setReturnCase(false);
			sr.setContent(map.get("response_msg"));
			sr.setStatus(101);
		}

		return sr;
	}

	@RequestMapping(value = "/orderList", method = RequestMethod.GET)
	public String orderList(HttpServletRequest request,HttpServletResponse response, Model model) {
		Page page = PageUtils.createPage(request);
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("pagename", "orderList");
		map.put("total", "orderListCn");
		//		map.put("product_code", request.getAttribute("product_code"));
		//		map.put("product_name", request.getAttribute("product_name"));
		page = productService.page(page, map);
		List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		DataField dataField=new DataField();
		dataField.setData_field_id("product_status");
		List<DataFieldVo> productStatusList=dataFieldService.selectForList(dataField);
		Map<String, Object> product_status_map=new HashMap<String, Object>();
		for(int i=0;i<productStatusList.size();i++){
			product_status_map.put(productStatusList.get(i).getData_field_value(), productStatusList.get(i));
		}
		for(int j=0;j<dataList.size();j++){
			Map<String,Object> dataMap=dataList.get(j);
			int status= (int)dataMap.get("status");
			DataFieldVo productStatus=(DataFieldVo) product_status_map.get(status+"");
			dataMap.put("status_text", productStatus.getData_field_name());
		}
		model.addAttribute("page",page);
		return "sysNew/product/orderlist";
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
	@RequestMapping("/submit")
	public @ResponseBody String toSubmit(HttpServletRequest request,HttpServletResponse response, Model model){
		String product_id=RequestUtils.getReqString(request, "product_id");
		Product product=productService.selectForObjectById(StrUtils.convToLong(product_id));
		product.setStatus(11);
		productService.updateProductById(product);
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("status", "success");
		return JsonUtil.objectTojson(map);
	}

	@RequestMapping("/toAudit")
	public String toAudit(HttpServletRequest request,HttpServletResponse response, Model model){
		String product_id=RequestUtils.getReqString(request, "product_id");
		//		Product product=productService.selectForObjectById(StrUtils.convToLong(product_id));
		//		product.setStatus(2);
		//		productService.updateProductById(product);
		model.addAttribute("product_id",product_id);
		return "sysNew/product/audit";
	}

	@RequestMapping("/orderSave")
	public String orderSave(HttpServletRequest request,HttpServletResponse response){
		String product_id=RequestUtils.getReqString(request, "product_id");
		String order_no=RequestUtils.getReqString(request, "order_no");
		String[] idList=product_id.split(",");
		String[] noList=order_no.split(",");
		for(int i=0;i<idList.length;i++){
			Product product=new Product();
			product.setId(StrUtils.convToLong(idList[i]));
			product.setOrder_no(StrUtils.convToInt(noList[i]));
			productService.updateProductById(product);
		}
		frontService.reloadIndexCache();
		return "redirect:/sys/product/orderList.action";
	}

	@RequestMapping("/statistic")
	public String statistic(HttpServletRequest req,HttpServletResponse response,Model model){
		Page page=PageUtils.createPage(req);
		Map<String, Object> params = new HashMap<String, Object>();
		// 取得请求中的到期时间
		//		params.put(ProductConstants.REQ_PARAM_START_DATE,
		//				RequestUtils.getReqString(req, Constants.REQ_PARAM_START_DATE));
		//		// 取得请求中的到期时间
		//		params.put(ProductConstants.REQ_PARAM_END_DATE,
		//				RequestUtils.getReqString(req, Constants.REQ_PARAM_END_DATE));
		//		// 取得请求中的发布时间
		//		params.put(ProductConstants.REQ_PARAM_ISSUE_START_DATE,
		//				RequestUtils.getReqString(req,
		//						ProductConstants.REQ_PARAM_ISSUE_START_DATE));
		//		// 取得请求中的发布时间
		//		params.put(ProductConstants.REQ_PARAM_ISSUE_END_DATE,
		//				RequestUtils.getReqString(req,
		//						ProductConstants.REQ_PARAM_ISSUE_END_DATE));
		//		params.put(ProductConstants.REQ_PARAM_SEARCH_TYPE, StrUtils.convToInt(
		//				RequestUtils.getReqString(req, ProductConstants.REQ_PARAM_SEARCH_TYPE)));
		//		params.put(ProductConstants.REQ_PARAM_SEARCH_PARAM, RequestUtils.getReqString(
		//				req, ProductConstants.REQ_PARAM_SEARCH_PARAM, true));
		//		params.put(ProductConstants.REQ_PARAM_NAME, RequestUtils.getReqString(
		//				req, ProductConstants.REQ_PARAM_NAME, true));
		//		// 取得请求中的状态
		//		params.put(ProductConstants.REQ_PARAM_PRODUCT_STATUS,
		//				RequestUtils.getReqString(req, ProductConstants.REQ_PARAM_PRODUCT_STATUS));
		//		params.put(ProductConstants.SQL_PARAM_LIMIT, StrUtils.convToInt(
		//				RequestUtils.getReqString(req, ProductConstants.SQL_PARAM_LIMIT)));
		//		params.put(ProductConstants.REQ_PARAM_MONEY_MIN,
		//				RequestUtils.getReqString(req,
		//						ProductConstants.REQ_PARAM_MONEY_MIN));
		//		params.put(ProductConstants.REQ_PARAM_MONEY_MAX,
		//				RequestUtils.getReqString(req,
		//						ProductConstants.REQ_PARAM_MONEY_MAX));
		//
		//		// 取得请求中的支付时间
		//		params.put("payStartDate",
		//				RequestUtils.getReqString(req, "payStartDate"));
		//		// 取得请求中的支付时间
		//		params.put("payEndDate",
		//				RequestUtils.getReqString(req, "payEndDate"));
		params.put("pagename", "statisticPage");
		params.put("total", "statisticCn");
		page=productService.page(page, params);

		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");

		List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> dataMap=dataList.get(i);
			int lend_id=((int) dataMap.get("lender_id"));
			Long lender_id=Long.valueOf(lend_id);
			Long id=Long.valueOf( dataMap.get("id").toString());
			Lender lender=new Lender();
			lender=lenderService.selectForObjectById(lender_id);
			params.put("id", id);
			dataMap.put("name", (lender.getLender_type()==1?"个人":"企业")+lender.getName());
			double payed_total_incomed=StrUtils.convToDouble(productDao.selectForObject("payed_total_incomed", params));
			double payed_total_income_offline=StrUtils.convToDouble(productDao.selectForObject("payed_total_income_offline", params));
			double total_income_offline=StrUtils.convToDouble(productDao.selectForObject("total_income_offline", params));
			double total_incomed=StrUtils.convToDouble(productDao.selectForObject("total_incomed", params));
			double total_coin=StrUtils.convToDouble(productDao.selectForObject("total_coin", params));
			double total_gift_amount=StrUtils.convToDouble(productDao.selectForObject("total_gift_amount", params));
			double total_coupon_amount=StrUtils.convToDouble(productDao.selectForObject("total_coupon_amount", params));
			double payed_total_coupon_amount=StrUtils.convToDouble(productDao.selectForObject("payed_total_coupon_amount", params));
			double profit = total_income_offline - total_incomed;
			double payedProfit = payed_total_income_offline - payed_total_incomed;
			double netProfit = profit - (total_coin + total_gift_amount);
			double payedNetProfit = payedProfit - (total_coin + total_gift_amount);
			if(dataMap.containsKey("start_date")&&dataMap.containsKey("end_date")){
				Date start_date;
				try {
					start_date = df.parse(dataMap.get("start_date").toString());
					Date end_date=df.parse(dataMap.get("end_date").toString());
					String income_total_days=new Integer(DateUtil.daysBetween(start_date, end_date)).toString()+"天";
					dataMap.put("income_total_days", income_total_days);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dataMap.put("profit", profit);
			dataMap.put("payedProfit", payedProfit);
			dataMap.put("netProfit", netProfit);
			dataMap.put("payedNetProfit", payedNetProfit);
			dataMap.put("payed_total_incomed", payed_total_incomed);
			dataMap.put("payed_total_income_offline", payed_total_income_offline);
			dataMap.put("total_income_offline", total_income_offline);
			dataMap.put("total_incomed", total_incomed);
			dataMap.put("total_coin", total_coin);
			dataMap.put("total_gift_amount", total_gift_amount);
			dataMap.put("total_coupon_amount", total_coupon_amount);
			dataMap.put("payed_total_coupon_amount", payed_total_coupon_amount);
		}
		model.addAttribute("page",page);
		return "sysNew/product/statistic";
	}
	@RequestMapping("update")
	public String update(HttpServletRequest req,HttpServletResponse response,Model model){

		return "";
	}

	@RequestMapping("/complete")
	public @ResponseBody String complete(HttpServletRequest req,HttpServletResponse response,Model model){
		String product_id=RequestUtils.getReqString(req, "product_id");
		Product product=productService.selectForObjectById(StrUtils.convToLong(product_id));
		product.setMoney(product.getSale_money());
		product.setStatus(7);
		productService.updateProductById(product);
		return "success";
	}












	public File getProductFile(Long productId, String fileUrl,int fileNum,String fileW,String fileH, int fileType) {
		File file = new File();
		file.setFrom_id(productId);//产品ID
		file.setFile_url(fileUrl);//产品地址
		file.setFile_name(StrUtils.getFileNameByUrl(fileUrl));//产品名称
		file.setFile_type(fileType);
		file.setFile_num(fileNum);
		file.setFile_width(Integer.parseInt(fileW));
		file.setFile_height(Integer.parseInt(fileH));
		file.setCreate_time(new Date());
		file.setFrom_table("zd_product");
		return file;
	}

	private GuaranteeVo getProductGuarantee(Long id, String guarantee_name, String guarantee_desc, int guarantee_num) {
		GuaranteeVo guaranteeVo=new GuaranteeVo();
		guaranteeVo.setFrom_id(id);
		guaranteeVo.setGuarantee_name(guarantee_name);
		guaranteeVo.setGuarantee_desc(guarantee_desc);
		guaranteeVo.setGuarantee_num(guarantee_num);
		guaranteeVo.setCreate_time(new Date());
		return guaranteeVo;
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
	public void setDataFieldService(IDataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}
	@Autowired(required = true)
	public void setLoanerService(ILoanerService loanerService) {
		this.loanerService = loanerService;
	}
	@Autowired(required = true)
	public void setLenderService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}
	@Autowired(required = true)
	public void setProductIncomeService(IProductIncomeService productIncomeService) {
		this.productIncomeService = productIncomeService;
	}
	@Autowired(required = true)
	public void setFrontService(IFrontService frontService) {
		this.frontService = frontService;
	}
	@Autowired(required = true)
	public void setProductDao(
			@Qualifier("productDao")IProductDao productDao) {
		this.productDao = productDao;
	}
	@Autowired(required = true)
	public void setFileDao(
			@Qualifier("fileDao")IFileDao fileDao) {
		this.fileDao = fileDao;
	}

}
