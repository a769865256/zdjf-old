package com.zdjf.web.admin.fund;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.Recharge;
import com.zdjf.domain.fund.Withdraw;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductDynamic;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.Loaner;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IRechargeService;
import com.zdjf.service.fund.IWithdrawService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductDynamicService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.ILoanerService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.DateUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;

@Controller
@RequestMapping("/sys/finance")
public class FinanceController {
	private IProductBuyService productBuyService;
	private IUserService userService;
	private IUserCouponService userCouponService;
	private IUserGiftService userGiftService;
	private IFundsService fundsService;
	private IProductBuyIncomeService productBuyIncomeService;
	private IProductService productService;
	private ILoanerService loanerService;
	private ILenderService lenderService;
	private IDataFieldService dataFieldService;
	private IWithdrawService withdrawService;
	private IRechargeService rechargeService;
	private IProductDynamicService productDynamicService;

	@RequestMapping("/purchase/toList")
	public String purchase(HttpServletRequest req, Model model){
		String searchType=RequestUtils.getReqString(req, "searchType");
		String searchParam=RequestUtils.getReqString(req, "searchParam");
		String productName=RequestUtils.getReqString(req, "productName");
		String createStartTime=RequestUtils.getReqString(req, "createStartTime");
		String createEndTime=RequestUtils.getReqString(req, "createEndTime");
		String payStartTime=RequestUtils.getReqString(req, "payStartTime");
		String payEndTime=RequestUtils.getReqString(req, "payEndTime");
		String regSource=RequestUtils.getReqString(req, "regSource");
		String status=RequestUtils.getReqString(req, "status");

		Page page=PageUtils.createPage(req);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pagename", "selectPurchasePage");
		map.put("total", "selectPurchaseCount");
		map.put("type", "1");
		map.put("createStartTime", createStartTime);
		map.put("createEndTime", createEndTime);
		map.put("payStartTime", payStartTime);
		map.put("payEndTime", payEndTime);
		map.put("regSource", regSource);
		map.put("status", status);
		map.put("productName", "%"+productName+"%");

		model.addAttribute("searchType", searchType);
		model.addAttribute("searchParam", searchParam);
		model.addAttribute("createStartTime", createStartTime);
		model.addAttribute("createEndTime", createEndTime);
		model.addAttribute("payStartTime", payStartTime);
		model.addAttribute("payEndTime", payEndTime);
		model.addAttribute("regSource", regSource);
		model.addAttribute("status", status);
		model.addAttribute("productName", productName);

		if(searchType.equals("1")){
			User user=new User();
			user.setUser_name(searchParam);
			//			List<UserVo> userList=userService.selectForList(user);
			//			map.put("user_id", userList.get(0).getId());
			user=userService.selectForObjectByPhone(searchParam);
			map.put("user_id", user.getId());
		}else if(searchType.equals("2")){
			User user=new User();
			user.setReal_name(searchParam);
			List<UserVo> userList=userService.selectForList(user);
			map.put("user_id", userList.get(0).getId());
		}else if(searchType.equals("3")){
			Product product=new Product();
			product.setDebt_code(searchParam);
			List<ProductVo> productList=productService.selectForList(product);
			map.put("product_id", productList.get(0).getId());
		}


		page=productBuyService.page(page, map);
		List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> dataMap=dataList.get(i);
			String phone="";
			String user_coupon_id="";
			String user_gift_id="";
			User user=new User();
			UserCoupon userCoupon=new UserCoupon();
			UserGift userGift=new UserGift();
			if(dataMap.containsKey("user_id")){//姓名
				phone=(String) dataMap.get("phone");
				user=userService.selectForObjectByPhone(phone);
				if(null!=user){
					dataMap.put("real_name",user.getReal_name());
				}
			}
			if(dataMap.containsKey("user_coupon_id")){//加息券
				user_coupon_id= dataMap.get("user_coupon_id").toString();
				userCoupon.setId(Long.valueOf(user_coupon_id));
				userCoupon=userCouponService.selectForObjectByCouponId(userCoupon);
				dataMap.put("couponInterest",userCoupon.getInterest());
			}
			if(dataMap.containsKey("user_gift_id")){//红包
				user_gift_id= dataMap.get("user_gift_id").toString();
				userGift.setId(Long.valueOf(user_gift_id));
				userGift=userGiftService.selectForObjectByGiftId(userGift);
				dataMap.put("giftAmount",userGift.getAmount());
			}
		}

		DataField dataField=new DataField();
		dataField.setData_field_id("fina_sale_status");
		List<DataFieldVo> fina_sale_status=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("recharge_source");
		List<DataFieldVo> recharge_source=dataFieldService.selectForList(dataField);

		model.addAttribute("detail", page);
		model.addAttribute("fina_sale_status", fina_sale_status);
		model.addAttribute("recharge_source", recharge_source);
		return "sysNew/finance/purchase";
	}

	@RequestMapping("/cash/toList")
	public String cash(HttpServletRequest req, Model model){
		Page change=PageUtils.createPage("change",req);
		Page recharge=PageUtils.createPage("recharge",req);
		Page withdraw=PageUtils.createPage("withdraw",req);
		String user_name=RequestUtils.getReqString(req, "user_name");
		String real_name=RequestUtils.getReqString(req, "real_name");
		model.addAttribute("user_name",user_name);
		model.addAttribute("real_name",real_name);
		User user=new User();
		if(StrUtils.emptyJudge(real_name)||StrUtils.emptyJudge(user_name)){
			if(StrUtils.emptyJudge(real_name)){
				user.setReal_name(real_name);
				model.addAttribute("real_name",real_name);
			}
			if(StrUtils.emptyJudge(user_name)){
				user.setUser_name(user_name);
				model.addAttribute("user_name",user_name);
			}
			user=userService.selectForObject(user);
		}

		Map<String, Object>map=new HashMap<String, Object>();
		map.put("pagename", "selectCashList");
		map.put("total", "selectCashListCn");
		if(null!=user){
			map.put("user_id",user.getId());
		}
		change=fundsService.page(change, map);//变动
		List<Map<String,Object>> changeList=(List<Map<String, Object>>) change.getDataList();
		for(int i=0;i<changeList.size();i++){
			Map<String,Object> changeMap=changeList.get(i);
			if(changeMap.containsKey("user_id")){
				String user_id=changeMap.get("user_id").toString();
				user=new User();
				user.setId(Long.valueOf(user_id));
				user=userService.selectForObject(user);
				changeMap.put("real_name", user.getReal_name());
				changeMap.put("phone", user.getPhone());
			}

		}

		DataField dataField=new DataField();
		dataField.setData_field_id("recharge_status");
		List<DataFieldVo>recharge_status=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("withdraw_status");
		List<DataFieldVo>withdraw_status=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("funds_vary_status");
		List<DataFieldVo>funds_vary_status=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("withdraw_fee_obj");
		List<DataFieldVo>withdraw_fee_obj=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("funds_operate_type");
		List<DataFieldVo>funds_operate_type=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("recharge_source");
		List<DataFieldVo>recharge_source=dataFieldService.selectForList(dataField);
		model.addAttribute("recharge_status", recharge_status);
		model.addAttribute("withdraw_status", withdraw_status);
		model.addAttribute("funds_vary_status", funds_vary_status);
		model.addAttribute("withdraw_fee_obj", withdraw_fee_obj);
		model.addAttribute("funds_operate_type", funds_operate_type);
		model.addAttribute("recharge_source", recharge_source);


		Recharge r=new Recharge();
		recharge=rechargeService.page(recharge, r);//充值
		List<Map<String,Object>> rechargeList=(List<Map<String, Object>>) recharge.getDataList();
		for(int i=0;i<rechargeList.size();i++){
			Map<String,Object> rechargeMap=rechargeList.get(i);
			if(rechargeMap.containsKey("user_id")){
				String user_id=rechargeMap.get("user_id").toString();
				user=new User();
				user.setId(Long.valueOf(user_id));
				user=userService.selectForObject(user);
				rechargeMap.put("phone", user.getPhone());
			}
		}

		map.put("pagename", "selectWithdrawList");
		map.put("total", "selectWithdrawListCount");
		withdraw=withdrawService.page(withdraw, map);//提现
		List<Map<String,Object>> withdrawList=(List<Map<String, Object>>) withdraw.getDataList();
		for(int i=0;i<withdrawList.size();i++){
			Map<String,Object> withdrawMap=withdrawList.get(i);
			if(withdrawMap.containsKey("user_id")){
				String user_id=withdrawMap.get("user_id").toString();
				user=new User();
				user.setId(Long.valueOf(user_id));
				user=userService.selectForObject(user);
				withdrawMap.put("phone", user.getPhone());
			}

		}

		model.addAttribute("change",change);
		model.addAllAttributes(PageUtils.createPagePar("change", change));
		model.addAttribute("recharge",recharge);
		model.addAllAttributes(PageUtils.createPagePar("recharge", recharge));
		model.addAttribute("withdraw",withdraw);
		model.addAllAttributes(PageUtils.createPagePar("withdraw", withdraw));
		return "sysNew/finance/cash";
	}
	@RequestMapping("/interest/toList")
	public String interest(HttpServletRequest req, Model model){
		String userName=RequestUtils.getReqString(req, "userName");
		String payStartDate=RequestUtils.getReqString(req, "payStartDate");
		String payEndDate=RequestUtils.getReqString(req, "payEndDate");
		String productName=RequestUtils.getReqString(req, "productName");
		String status=RequestUtils.getReqString(req, "status");
		String isReturnAmount=RequestUtils.getReqString(req, "isReturnAmount");

		Page page=PageUtils.createPage(req);
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("pagename", "selectInterestPage");
		map.put("total", "selectInterestCount");
		if(StrUtils.emptyJudge(userName)){
			User user=userService.selectForObjectByPhone(userName);
			model.addAttribute("userName", userName);
			map.put("user_id", user.getId());
		}
		if(StrUtils.emptyJudge(payStartDate)){
			model.addAttribute("payStartDate", payStartDate);
			map.put("payStartDate", payStartDate);
		}
		if(StrUtils.emptyJudge(payEndDate)){
			model.addAttribute("payEndDate", payEndDate);
			map.put("payEndDate", payEndDate);
		}
		if(StrUtils.emptyJudge(productName)){
			model.addAttribute("productName", productName);
			Map map1=new HashMap();
			map1.put("page_name", "fuzzyProduct");
			map1.put("product_code", "%"+productName+"%");
			List<ProductVo> productList=productService.selectForList(map1);
			String product_id="(";
			for(int i=0;i<productList.size();i++){
				product_id+=productList.get(i).getId()+",";
			}
			product_id=product_id.substring(0,product_id.length()-1);
			product_id+=")";
			map.put("product_id", productName);
		}
		if(StrUtils.emptyJudge(status)){
			model.addAttribute("status", status);
			map.put("status", status);
		}
		if(StrUtils.emptyJudge(isReturnAmount)){
			model.addAttribute("isReturnAmount", isReturnAmount);
			map.put("isReturnAmount", isReturnAmount);
		}
		
		page=productBuyIncomeService.page(page, map);
		List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> dataMap=dataList.get(i);
			if(dataMap.containsKey("user_id")){
				String user_id=dataMap.get("user_id").toString();
				User user=new User();
				user.setId(Long.valueOf(user_id));
				user=userService.selectForObject(user);
				dataMap.put("phone", user.getPhone());
				dataMap.put("real_name", user.getReal_name());
			}
			if(dataMap.containsKey("product_id")){
				String product_id=dataMap.get("product_id").toString();
				Product pro=productService.selectForObjectById(Long.valueOf(product_id));
				dataMap.put("product_code", pro.getProduct_code());
				Loaner loaner=loanerService.selectForObjectById(pro.getLoan_id());
				dataMap.put("loan_name", loaner.getName());
			}
		}
		model.addAttribute("page", page);
		return "sysNew/finance/interest";
	}
	@RequestMapping("/repayment/toList")
	public String repayment(HttpServletRequest req, Model model){
		Page page=PageUtils.createPage(req);
		ProductDynamic productDynamic=new ProductDynamic();
		page=productDynamicService.page(page, productDynamic);
		List<Map<String, Object>>dataList=(List<Map<String, Object>>) page.getDataList();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> dataMap=dataList.get(i);
			if(dataMap.containsKey("loan_id")){
				Long loan_id=Long.valueOf(dataMap.get("loan_id").toString());
				Loaner loaner=loanerService.selectForObjectById(loan_id);
				dataMap.put("loaner_name", (loaner.getLoaner_type()==1?"个人":"企业")+loaner.getName());
			}
			if(dataMap.containsKey("pay_day")){
				try {
					String pay_day=dataMap.get("pay_day").toString();
					Date payDay=df.parse(pay_day);
					int expire_day=DateUtil.between(payDay, new Date());
					dataMap.put("expire_day", expire_day);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sysNew/finance/repayment";
	}
	@RequestMapping("/repaymentInterest/toList")
	public String repaymentInterest(HttpServletRequest req, Model model){
		Page page=PageUtils.createPage(req);
		ProductDynamic productDynamic=new ProductDynamic();
		page=productDynamicService.page(page, productDynamic);
		List<Map<String, Object>>dataList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> dataMap=dataList.get(i);
			if(dataMap.containsKey("loan_id")){
				Long loan_id=Long.valueOf(dataMap.get("loan_id").toString());
				Loaner loaner=loanerService.selectForObjectById(loan_id);
				dataMap.put("loaner_name", loaner.getName());
			}
			if(dataMap.containsKey("lender_id")){
				Long lender_id=Long.valueOf(dataMap.get("lender_id").toString());
				Lender lender=lenderService.selectForObjectById(lender_id);
				dataMap.put("lender_name", lender.getName());
			}
		}
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sysNew/finance/repaymentInterest";
	}
	@Autowired(required=true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	@Autowired(required=true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Autowired(required=true)
	public void setUserCouponService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}
	@Autowired(required=true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}
	@Autowired(required=true)
	public void setFundsService(IFundsService fundsService) {
		this.fundsService = fundsService;
	}
	@Autowired(required=true)
	public void setProductBuyIncomeService(IProductBuyIncomeService productBuyIncomeService) {
		this.productBuyIncomeService = productBuyIncomeService;
	}
	@Autowired(required=true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	@Autowired(required=true)
	public void setLoanerService(ILoanerService loanerService) {
		this.loanerService = loanerService;
	}
	@Autowired(required=true)
	public void setDataFieldService(IDataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}
	@Autowired(required=true)
	public void setWithdrawService(IWithdrawService withdrawService) {
		this.withdrawService = withdrawService;
	}
	@Autowired(required=true)
	public void setRechargeService(IRechargeService rechargeService) {
		this.rechargeService = rechargeService;
	}

}
