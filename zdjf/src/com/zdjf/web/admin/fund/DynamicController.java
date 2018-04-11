package com.zdjf.web.admin.fund;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductBuyIncomeVo;
import com.zdjf.domain.product.ProductDynamic;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.domain.user.Loaner;
import com.zdjf.domain.user.LoanerVo;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.front.IFrontService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductDynamicService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.ILoanerService;
import com.zdjf.service.user.IUserService;
import com.zdjf.service.user.UserService;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.webservice.express.SmsReturn;

@Controller
@RequestMapping("/sys/dynamic")
public class DynamicController {
	private IProductDynamicService productDynamicService;
	@Autowired
	private ILenderService lenderService;
	@Autowired
	private ILoanerService loanerService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IProductBuyIncomeService pbiService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IFrontService frontService;
	@RequestMapping("/toList")
	public String toList(HttpServletRequest req, Model model){
		ProductDynamic pd=new ProductDynamic();
		Page page=PageUtils.createPage(req);
		Map map=new HashMap();
		map.put("pagename", "selectProductDynamicPage");
		map.put("total", "selectProductDynamicCount");
		page=productDynamicService.page(page, map);
		List<Map> dataList=(List<Map>) page.getDataList();
		for(int i=0;i<dataList.size();i++){
			Map dataMap=dataList.get(i);
			if(dataMap.containsKey("lender_id")){
				Long lender_id=StrUtils.convToLong(dataMap.get("lender_id").toString());
				Lender lender=lenderService.selectForObjectById(lender_id);
				dataMap.put("lender_name", lender.getName());
			}
		}
		model.addAttribute("page",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "/sysNew/finance/dynamic";
	}
	@RequestMapping("/toPage")
	public String toPage(HttpServletRequest req, Model model) throws ParseException{
		Map<String,Object> map=new HashMap<String,Object>();

		String lenderName=RequestUtils.getReqString(req, "lenderName");
		String loanName=RequestUtils.getReqString(req, "loanName");
		String payStartTime=RequestUtils.getReqString(req, "payStartTime");
		String payEndTime=RequestUtils.getReqString(req, "payEndTime");
		String productCode=RequestUtils.getReqString(req, "productCode");

		if(StrUtils.emptyJudge(lenderName)){
			Lender lender=new Lender();
			lender.setName(lenderName);
			List<LenderVo> lenderList=lenderService.selectForList(lender);
			if(lenderList.size()>0){
				map.put("lender_id", lenderList.get(0).getId());
			}
			model.addAttribute("lenderName",lenderName);
		}

		if(StrUtils.emptyJudge(loanName)){
			Loaner loaner=new Loaner();
			loaner.setName(loanName);
			List<LoanerVo> loanList=loanerService.selectForList(loaner);
			if(loanList.size()>0){
				map.put("loan_id", loanList.get(0).getId());
			}
			model.addAttribute("loanName",loanName);
		}

		if(StrUtils.emptyJudge(payStartTime)){
			map.put("payStartTime", payStartTime);
			model.addAttribute("payStartTime",payStartTime);
		}
		if(StrUtils.emptyJudge(payEndTime)){
			map.put("payEndTime", payEndTime);
			model.addAttribute("payEndTime",payEndTime);
		}
		if(StrUtils.emptyJudge(productCode)){
			map.put("productCode", "%"+productCode+"%");
			model.addAttribute("productCode",productCode);
		}

		ProductDynamic pd=new ProductDynamic();
		Page page=PageUtils.createPage(req);
		page.setLimit(20l);
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");

		page=productDynamicService.page(page, pd);
		map.put("page_name", "selectProductDynamicMap");
		List<Map> productDynamicList=new ArrayList<Map>();
		if(StrUtils.emptyJudge(payStartTime)||StrUtils.emptyJudge(payEndTime)){
			productDynamicList.addAll(productDynamicService.selectForMap(map));
		}else{
			map.put("startTime", df.format(new Date()));
			productDynamicList.addAll(productDynamicService.selectForMap(map));
			map.remove("startTime");
			map.put("endTime", df.format(new Date()));
			productDynamicList.addAll(productDynamicService.selectForMap(map));
		}

		int limit=page.getLimit().intValue();
		int currentPage=page.getCurrentPage().intValue();
		List<Map> dataList=new ArrayList<Map>();
		int max=limit*(currentPage-1)+20;
		if(max>productDynamicList.size()){
			max=productDynamicList.size();
		}
		for(int i=limit*(currentPage-1);i<max;i++){
			dataList.add(productDynamicList.get(i));
		}
		page.setTotal(new Long((long)productDynamicList.size()));
		String paydate ="";
		String lender_name="";
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, Object> mapdate = new HashMap<String, Object>();//存放还款日期次数
		Map<String, Map<String,Object>> mapname = new HashMap<String, Map<String,Object>>();//存放还款日期中的姓名出现的次数
		Map lender_balance=new HashMap();
		for(int i=0;i<dataList.size();i++){
			Map dataMap=dataList.get(i);
			if(dataMap.containsKey("lender_id")){
				Long lender_id=StrUtils.convToLong(dataMap.get("lender_id").toString());
				Lender lender=lenderService.selectForObjectById(lender_id);
				User user=userService.selectForObjectByPhone(lender.getPhone());
				dataMap.put("lender_name", lender.getName());
				if(lender_balance.containsKey(lender.getName())){
					dataMap.put("lender_balance", lender_balance.get(lender.getName()));
				}else{
					String result=SinaUtil.queryBalance(user.getId(), user.getReg_ip(), "");
					Map resultMap = JsonUtil.jsonToMap(result);
					Object strCode =  resultMap.get("response_code");
					if(strCode != null && strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
						System.out.println(user.getId() + result + user.getReg_ip());
						Map datamap =  (Map) resultMap.get("data");
						String vailable_balance = (String) datamap.get("available_balance");
						dataMap.put("lender_balance", vailable_balance);
						lender_balance.put(lender.getName(), vailable_balance);
					}
				}
			}
			if(dataMap.containsKey("loan_id")){
				Long loan_id=StrUtils.convToLong(dataMap.get("loan_id").toString());
				Loaner loan=loanerService.selectForObjectById(loan_id);
				dataMap.put("loan_name", loan.getName());
			}
			if(dataMap.containsKey("product_id")){
				Long product_id=StrUtils.convToLong(dataMap.get("product_id").toString());
				Map pbiMap=new HashMap();
				pbiMap.put("page_name", "selectPbiMap");
				pbiMap.put("product_id", product_id);
				pbiMap.put("pay_date", dataMap.get("pay_day").toString());
				List<ProductBuyIncome> pbiList=pbiService.selectForList(pbiMap);
				pbiMap.put("status", 1);
				List<ProductBuyIncome> pbiList2=pbiService.selectForList(pbiMap);
				double amount=0.0;
				double payed_amount=0.0;
				double product_amount=0.0;
				double income=0.0;
				double payed_income=0.0;
				for(int j=0;j<pbiList.size();j++){
					income+=pbiList.get(j).getIncome();
					if(pbiList.get(0).getIs_return_amount()==1){
						amount+=pbiList.get(j).getAmount();
					}
				}
				for(int m=0;m<pbiList2.size();m++){
					payed_income+=pbiList2.get(m).getIncome();
					if(pbiList.get(0).getIs_return_amount()==1){
						payed_amount+=pbiList2.get(m).getAmount();
					}
				}
				DecimalFormat f = new DecimalFormat("#0.00");
				product_amount=productService.selectForObjectById(product_id).getMoney();
				dataMap.put("amount", product_amount);
				dataMap.put("now_to_pay_income", f.format(income));
				dataMap.put("now_to_pay_amount", amount);
				dataMap.put("now_payed_income", f.format(payed_income));
				dataMap.put("now_payed_amount", payed_amount);
			}
		}
		for(int i=0;i<dataList.size();i++){
			Map dataMap=dataList.get(i);
			paydate=dataMap.get("pay_day").toString();
			lender_name=dataMap.get("lender_name").toString();
			if(!mapdate.containsKey(paydate)){
				mapdate.put(paydate, 1);
				maps.put(paydate+lender_name,1);
			}else {
				mapdate.put(paydate,(Integer)mapdate.get(paydate)+1);
				if(maps.get(paydate+lender_name)==null){//判断出借人次数
					maps.put(paydate+lender_name,1);
				}else{
					maps.put(paydate+lender_name,(Integer)maps.get(paydate+lender_name)+1);
				}
			}
			mapname.put(paydate, maps);
		}

		for(int i=0;i<dataList.size();i++){
			Map dataMap=dataList.get(i);
			paydate=dataMap.get("pay_day").toString();
			int j=(int) mapdate.get(paydate);
			if(j>1){
				if( !mapdate.containsKey(paydate+"dayamount")&&dataMap.containsKey("now_to_pay_amount")){
					mapdate.put(paydate+"dayamount",dataMap.get("now_to_pay_amount"));
				}else{
					if(dataMap.containsKey("now_to_pay_amount")){
						mapdate.put(paydate+"dayamount",StrUtils.convToDouble(dataMap.get("now_to_pay_amount").toString())+StrUtils.convToDouble(mapdate.get(paydate+"dayamount").toString()));
					}
				}
				if( !mapdate.containsKey(paydate+"dayicome")&&dataMap.containsKey("now_to_pay_income")){
					mapdate.put(paydate+"dayicome",dataMap.get("now_to_pay_income"));
				}else{
					if(dataMap.containsKey("now_to_pay_income")){
						mapdate.put(paydate+"dayicome",StrUtils.convToDouble(dataMap.get("now_to_pay_income").toString())+StrUtils.convToDouble(mapdate.get(paydate+"dayicome").toString()));
					}
				}
				if(! mapdate.containsKey(paydate+"dayamounted")&&dataMap.containsKey("now_payed_amount")){
					mapdate.put(paydate+"dayamounted",dataMap.get("now_payed_amount"));
				}else{
					if(dataMap.containsKey("now_payed_amount")){
						mapdate.put(paydate+"dayamounted",StrUtils.convToDouble(dataMap.get("now_payed_amount").toString())+StrUtils.convToDouble(mapdate.get(paydate+"dayamounted").toString()));
					}
				}
				if( !mapdate.containsKey(paydate+"dayicomed")&&dataMap.containsKey("now_payed_income")){
					mapdate.put(paydate+"dayicomed",dataMap.get("now_payed_income"));
				}else{
					if(dataMap.containsKey("now_payed_income")){
						mapdate.put(paydate+"dayicomed",StrUtils.convToDouble(dataMap.get("now_payed_income").toString())+StrUtils.convToDouble(mapdate.get(paydate+"dayicomed").toString()));
					}
				}

			}else{
				mapdate.put(paydate+"dayamount",dataMap.get("now_to_pay_amount"));
				mapdate.put(paydate+"dayicome",dataMap.get("now_to_pay_income"));
				mapdate.put(paydate+"dayamounted",dataMap.get("now_payed_amount"));
				mapdate.put(paydate+"dayicomed",dataMap.get("now_payed_income"));
			}
		}

		for(int i=0;i<dataList.size();i++){
			Map dataMap=dataList.get(i);
			paydate=dataMap.get("pay_day").toString();
			int j=(int) mapdate.get(paydate);
			lender_name=dataMap.get("lender_name").toString();
			int m=(int)mapname.get(paydate).get(paydate+lender_name);
			if(j>1){
				if(mapdate.get(paydate+"status") ==null){
					dataMap.put("rowSpanStatus", 1);
					dataMap.put("rowSpan", j);
					dataMap.put("day_to_pay_amount", mapdate.get(paydate+"dayamount"));
					dataMap.put("day_to_pay_income", mapdate.get(paydate+"dayicome"));
					dataMap.put("day_payed_amount", mapdate.get(paydate+"dayamounted"));
					dataMap.put("day_payed_income", mapdate.get(paydate+"dayicomed"));
					mapdate.put(paydate+"status",1);
					if(m>=1){
						if(mapname.get(paydate).get(paydate+lender_name+"status") ==null){
							dataMap.put("rowSpanStatuss", 1);
							dataMap.put("rowSpans", m);
							mapname.get(paydate).put(paydate+lender_name+"status",1);
						}else{
							dataMap.put("rowSpanStatuss", 2);
						}
					}
				}else{
					dataMap.put("rowSpanStatus", 2);
					if(m>=1){
						if(mapname.get(paydate).get(paydate+lender_name+"status") ==null){
							dataMap.put("rowSpanStatuss", 1);
							dataMap.put("rowSpans", m);
							mapname.get(paydate).put(paydate+lender_name+"status",1);
						}else{
							dataMap.put("rowSpanStatuss", 2);
						}
					}
				}
			}
		}

		page.setDataList(dataList);
		model.addAttribute("page",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "/sysNew/finance/repayment";
	}
	
	@RequestMapping("/lock")
	public @ResponseBody SmsReturn fundsLock(HttpServletRequest req){
		String lender_id=RequestUtils.getReqString(req, "lender_id");
		Boolean flag=RequestUtils.getReqString(req, "flag").equals("true");
		frontService.reloadFundsCache(flag, lender_id);
		SmsReturn sr=new SmsReturn();
		sr.setContent("设置成功 lender_id:"+lender_id+"\n锁状态："+flag);
		sr.setStatus(100);
		return sr;
	}
	
	@Autowired(required=true)
	public void setProductDynamicService(IProductDynamicService productDynamicService) {
		this.productDynamicService = productDynamicService;
	}

}
