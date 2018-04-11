package com.zdjf.webservice.mobile;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.components.mobile.ServiceChargeUtil;
import com.zdjf.components.mobile.SinaResultUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.components.mobile.ValuetoString;
import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductBuyVo;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.reconciliation.Reconciliation;
import com.zdjf.domain.sms.Sms;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.domain.user.UserVo;
import com.zdjf.service.fund.ICoinService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductIncomeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.reconciliation.IReconciliationService;
import com.zdjf.service.sms.ISmsService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.webservice.express.SmsReturn;


class InvestmentNode{
	
	Double amount;//面额
	
	String strAmount;//字符串
	
	String invest_dates;//投资天数
	
	String invest_amount;//投资金额
	
	String type;//1 红包   2加息券 3正经值
	
	String name;//名字

	String node_id; //对应ID  红包 与 加息券  
	
	String dates;//时间期限
	
	String use_type;//用户类型
	
	String overdue;//过期

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStrAmount() {
		return strAmount;
	}

	public void setStrAmount(String strAmount) {
		this.strAmount = strAmount;
	}

	public String getInvest_dates() {
		return invest_dates;
	}

	public void setInvest_dates(String invest_dates) {
		this.invest_dates = invest_dates;
	}

	public String getInvest_amount() {
		return invest_amount;
	}

	public void setInvest_amount(String invest_amount) {
		this.invest_amount = invest_amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNode_id() {
		return node_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getUse_type() {
		return use_type;
	}

	public void setUse_type(String use_type) {
		this.use_type = use_type;
	}

	public String getOverdue() {
		return overdue;
	}

	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}

	
}


@Controller
@RequestMapping("/m/investment")
public class UserInvestmentWebService {
	
	private IUserFundStatService userFundStatService;
	
	private IUserService userService;
	
	private IFundsService fundsService;
	
	private IUserBankService userBankService;
	
	private IProductBuyService productBuyService;
	
	private IProductService productService;
	
	private IUserGiftService userGiftService;
	
	private IReconciliationService   reconciliationService;
	
	private IProductIncomeService productIncomeService;
	
	private IProductBuyIncomeService productBuyIncomeService;
	
	private ApplicationContext applicationContext;
	
	private IUserCouponService userCouponService;
	
	private ICoinService coinService;
	
	private ICoinStreamService coinStreamService;
	
	private ILenderService lenderService;
	
	private ISmsService smservice;
	
	Log log = LogFactory.getLog(this.getClass());
	
	//免密定向
	@RequestMapping(value="/withhold/authority",method=RequestMethod.GET)
	public @ResponseBody SmsReturn hostWithholdAuthority(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String sign = request.getParameter("sign");
		String reg_ip = request.getParameter("ip");
		
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if( null == sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
			
			return sr;
		}
		
		//调用支付接口
		
		User user = userService.selectForObjectByPhone(user_name);
		
		
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> list = userBankService.selectForList(userBank);
		
		if(list.size() == 0){
			sr.setContent("需要绑定快捷支付");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}

		
		
		
		String result = SinaUtil.withholdAuthority(user.getId());
		System.out.println(result);
	
		
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			sr.setContent("免密设置不成功");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		Map dataMap =  (Map) resultMap.get("data");
		
		String redirect_url = (String) dataMap.get("redirect_url");
		
		
		
		//更新用户统计账户信息

		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setMapContent(redirect_url);
		
		return sr;
	}
	
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getProducts(HttpServletRequest request,
            HttpServletResponse response)throws Exception{
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if( null ==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		User user = userService.selectForObjectByPhone(user_name);
		
		ProductBuy buy = new ProductBuy();
		buy.setUser_id(user.getId());
		
		List<ProductBuyVo> listBuy = productBuyService.selectForList(buy);
		List<Product> list = new ArrayList<Product>();
		for(int i = 0;i<listBuy.size();i++){
			
			Product product = productService.selectForObjectById(listBuy.get(i).getProduct_id());
			
			list.add(product);
		}
		
		
		sr.setMapContent(list);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	@RequestMapping(value="/token",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getToken(HttpServletRequest request,
            HttpServletResponse response)throws Exception{
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if( null ==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		User user = userService.selectForObjectByPhone(user_name);
		
		BrowseUtil.createSessionPhone(request, user.getUser_name());
		
		String token = BrowseUtil.createSessionToken(request);
		
		
		sr.setContent(token);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	@RequestMapping(value="/project/details",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getProjectDetails(HttpServletRequest request,
            HttpServletResponse response)throws Exception{
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String project_id = request.getParameter("project_id");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if( null ==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		User user = userService.selectForObjectByPhone(user_name);
		
		Product product = productService.selectForObjectById(Long.parseLong(project_id));
		
		ProductBuy allBuy = new ProductBuy();
		allBuy.setProduct_id(Long.parseLong(project_id));
		List<ProductBuyVo> allList = productBuyService.selectForList(allBuy);
		
		
		
		ProductBuy productBuy = new ProductBuy();
		productBuy.setUser_id(user.getId());
		List<ProductBuyVo> buys = productBuyService.selectForList(productBuy);
		if(buys.size() == 0){
			sr.setContent("");
			sr.setReturnCase(true);
			sr.setStatus(100);
			return sr;
		}
		ProductBuyVo buy = buys.get(0);
		
		Map<String,String> map = ValuetoString.eachProperties(product);
		map.put("myStart", DateUtil.formatDate(buy.getStart_date()));//我的计息开始
		map.put("allBuy", allList.size() + "");//一共多少投资人
		
		sr.setContent(map);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	@RequestMapping(value="/cost/gift",method=RequestMethod.POST)
	public @ResponseBody SmsReturn costUserGift(HttpServletRequest request,
            HttpServletResponse response)throws Exception{
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String user_gift_id = request.getParameter("user_gift_id");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if( null ==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		UserGift gift = new UserGift();
		gift.setId(Long.parseLong(user_gift_id));
		
		List<UserGiftVo> listGift = userGiftService.selectForList(gift);
		String gift_money = "";
		if(listGift.size() == 1){
			UserGift userGift = listGift.get(0);
			gift_money = userGift.getAmount().toString();
			
			userGiftService.updateUserGiftByGiftId(userGift);
		}
		
		
		sr.setContent(gift_money);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	
	//用正经值
	@RequestMapping(value="/cost/coin",method=RequestMethod.POST)
	public @ResponseBody SmsReturn costCoin(HttpServletRequest request,
            HttpServletResponse response)throws Exception{
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String amount = request.getParameter("amount");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if( null ==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		Coin coin = new Coin();
		coin.setCoin_name("消费抵扣");
		
		List<CoinVo> listCoin = coinService.selectForList(coin);
		Coin coinVo = listCoin.get(0);
		//抵扣最大值
		Double max_cost = coinVo.getDeduction_ratio()*RoofNumberUtils.StringtoDouble(amount)*0.01;
		
		User user = userService.selectForObjectByPhone(user_name);
		
		UserFundStat fundStat = new UserFundStat();
		fundStat.setUser_id(user.getId());
		
		UserFundStat userFundStat = userFundStatService.selectForObjectById(fundStat);
		
		Double coin_balance = userFundStat.getCoin_balance();
		Double temp = max_cost > coin_balance?coin_balance:max_cost;
		
		sr.setContent(temp.toString());
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	
	//投资前
	@RequestMapping(value="/pro/collect/trade",method=RequestMethod.POST)
	public @ResponseBody SmsReturn proCollectTrade(HttpServletRequest request,
            HttpServletResponse response)throws Exception{
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String amount = request.getParameter("amount");
		String sign = request.getParameter("sign");
		String product_id = request.getParameter("product_id");
		String income_days = request.getParameter("income_days");
		String reg_source = request.getParameter("reg_source");
		
		String md5Sign = Md5Util.md5to32(user_name + income_days + amount +
				Constants.API_KEY);
		
		if( null == sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
			
			return sr;
		}
		
		if(amount == null || amount.trim().isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("请查看输入金额");
			
			return sr;
		}
		
		if(income_days == null || Integer.parseInt(income_days)<=0){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("请查看输入投资天数");
			
			return sr;
		}
		
		if(product_id == null){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("product_id不能空");
			
			return sr;
		}
		Product pro = new Product();
		pro.setId(Long.parseLong(product_id));
		
		
		List<ProductVo> productList = productService.selectForList(pro);
		
		if(productList.size() != 1){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("存在多个标");
			
			return sr;
		}
		
		ProductVo product = productList.get(0);
		
		System.out.println(user_name + "用户信息");
		User user = userService.selectForObjectByPhone(user_name);
		
		if(user == null){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("没有此用户");
			
			return sr;
		}
		
		
		List<InvestmentNode> useGift = new ArrayList<InvestmentNode>();
		
		List<InvestmentNode> unGift = new ArrayList<InvestmentNode>();//不能用的
		
		
		//可用红包
		UserGift gift = new UserGift();
		gift.setUser_id(user.getId());
		gift.setStatus(1);
		List<UserGiftVo> listGift = userGiftService.selectForList(gift);
		
		for(int i = 0;i<listGift.size();i++){
			UserGift userGift = listGift.get(i);
			Date date = new Date();
			Date endDate = DateUtil.dateEnd(userGift.getEnd_date());
			if(endDate.getTime()<date.getTime()){
				userGift.setStatus(3);
				userGiftService.updateUserGiftByGiftId(userGift);
			}
			
			InvestmentNode node = new InvestmentNode();
			node.setAmount(userGift.getAmount());
			node.setStrAmount(userGift.getAmount().toString());
			node.setInvest_dates(userGift.getMax_days() + "");
			node.setInvest_amount(userGift.getMax_amount() + "");
			node.setNode_id(userGift.getId().toString());
			node.setType("1");
			node.setName(userGift.getGift_name());
			node.setUse_type(userGift.getUse_type() + "");
			node.setDates(DateUtil.formatDate(userGift.getStart_date()) + " 至 " + 
					DateUtil.formatDate(userGift.getEnd_date()));
			
			
			
			if(DateUtil.dateSub(userGift.getEnd_date(),date)<4){
				node.setOverdue("即将过期");
			}
			if(userGift.getMax_amount() == null){
				userGift.setMax_amount(100.0);
			}
			
			if(userGift.getMax_days() == 0){
				userGift.setMax_days(10);
			}
			//log.info("usergift id " + userGift.getId());
			if(RoofNumberUtils.StringtoDouble(amount)>=userGift.getMax_amount()&&
					userGift.getMax_days()<=Integer.parseInt(income_days)&&
					userGift.getStart_date().getTime()<=date.getTime()&&
							DateUtil.addDate(userGift.getEnd_date(),1).getTime()>=date.getTime()){
				if(product.getIs_fresh() == 1){
					if(node.getUse_type().equalsIgnoreCase("1") ||node.getUse_type().equalsIgnoreCase("2"))
						useGift.add(node);
				}else if(product.getIs_fresh() == -1){
					if(node.getUse_type().equalsIgnoreCase("1") ||node.getUse_type().equalsIgnoreCase("3"))
						useGift.add(node);
				}else{
					unGift.add(node);
				}
					
			}else{
				unGift.add(node);
			}
			
		}
		//正经值
		Coin coin = new Coin();
		coin.setCoin_name("消费抵扣");
		
		List<CoinVo> listCoin = coinService.selectForList(coin);
		Coin coinVo = listCoin.get(0);
		//抵扣最大值
		Double max_cost = coinVo.getDeduction_ratio()*RoofNumberUtils.StringtoDouble(amount)*0.01;
		
		UserFundStat fundStat = new UserFundStat();
		fundStat.setUser_id(user.getId());
		
		UserFundStat userFundStat = userFundStatService.selectForObjectById(fundStat);
		
		Double coin_balance = userFundStat.getCoin_balance();
		Double temp = max_cost > coin_balance?coin_balance:max_cost;
		
		InvestmentNode nodeCoin = new InvestmentNode();
		nodeCoin.setAmount(temp);
		nodeCoin.setStrAmount(temp.toString());
		nodeCoin.setType("3");
		nodeCoin.setName("正经值");
		nodeCoin.setDates(coin_balance.toString());
		nodeCoin.setNode_id("-1");
		useGift.add(nodeCoin);
		
		if(useGift.size()>1){//比较最优化的
			Collections.sort(useGift,new Comparator<InvestmentNode>(){
	            public int compare(InvestmentNode arg0, InvestmentNode arg1) {
	                return -arg0.getAmount().compareTo(arg1.getAmount());
	            }
		    });
		}
		
		//加息券
		UserCoupon coupon = new UserCoupon();
		coupon.setUser_id(user.getId());
		coupon.setStatus(1);
		List<UserCouponVo> listCoupon = userCouponService.selectForList(coupon);
		
		List<InvestmentNode> useCoupon = new ArrayList<InvestmentNode>();
		
		List<InvestmentNode> unCoupon = new ArrayList<InvestmentNode>();//不能用的
		
		for(int i = 0;i<listCoupon.size();i++){
			UserCoupon userGift = listCoupon.get(i);
			InvestmentNode node = new InvestmentNode();
			node.setAmount(userGift.getInterest());
			node.setStrAmount(userGift.getInterest().toString());
			node.setInvest_dates(userGift.getMin_days() + "");
			node.setInvest_amount(userGift.getMin_amount() + "");
			node.setNode_id(userGift.getId().toString());
			node.setType("2");
			node.setName(userGift.getCoupon_name());
			
			node.setUse_type(userGift.getUse_type() + "");
			node.setDates(DateUtil.formatDate(userGift.getStart_date()) + " 至 " + 
					DateUtil.formatDate(userGift.getEnd_date()));
			
			Date date = new Date();
			 
			if(DateUtil.dateSub(userGift.getEnd_date(),date)<2){
				node.setOverdue("即将过期");
			}
			
			if(userGift.getMin_amount() == null ){
				userGift.setMin_amount(10.0);
			}
			
			if(RoofNumberUtils.StringtoDouble(amount)>=userGift.getMin_amount()&&
					userGift.getMin_days()<=Integer.parseInt(income_days)&&
					userGift.getStart_date().getTime()<=date.getTime()&&
					DateUtil.addDate(userGift.getEnd_date(),1).getTime()>=date.getTime()){
				//useCoupon.add(node);
				if(product.getIs_fresh() == 1){
					if(node.getUse_type().equalsIgnoreCase("1") ||node.getUse_type().equalsIgnoreCase("2"))
						useCoupon.add(node);
				}else if(product.getIs_fresh() == -1){
					if(node.getUse_type().equalsIgnoreCase("1") ||node.getUse_type().equalsIgnoreCase("3"))
						useCoupon.add(node);
				}else{
					unCoupon.add(node);
				}
			}else{
				unCoupon.add(node);
			}
			
		}
		
		if(useCoupon.size()>1){//比较最优化的
			Collections.sort(useCoupon,new Comparator<InvestmentNode>(){
	            public int compare(InvestmentNode arg0, InvestmentNode arg1) {
	                return -arg0.getAmount().compareTo(arg1.getAmount());
	            }
		    });
		}
		
		
		
		List<InvestmentNode> recommend = new ArrayList<InvestmentNode>();//推荐
		
		if(useCoupon.size()>0)
			recommend.add(useCoupon.get(0));
		
		if(useGift.size()>0)
			recommend.add(useGift.get(0));
		
		useGift.addAll(useCoupon);
		unGift.addAll(unCoupon);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("use", useGift);//可用的
		map.put("unUser", unGift);//不可用
		map.put("recommend", recommend); //推荐
		
		
		
		
		
		sr.setContent(map);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	
	//获取用户投标1001=投标
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value="/collect/trade",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostCollectTrade(HttpServletRequest request,
            HttpServletResponse response)throws Exception{
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String amount = request.getParameter("amount");
		String sign = request.getParameter("sign");
		String summary = request.getParameter("summary");
		String goods_id = request.getParameter("goods_id");
		String user_coupon_id = request.getParameter("user_coupon_id");
		String user_gift_id = request.getParameter("user_gift_id");
		String gift_money = request.getParameter("gift_money");
		String reg_source = request.getParameter("reg_source");
		String coin = request.getParameter("coin");
		String trade_no = request.getParameter("trade_no");
		
		
		
		String md5Sign = Md5Util.md5to32(user_name + goods_id + amount +
				Constants.API_KEY);
		
		if( null == sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
			
			return sr;
		}
		
		//是否  重复订单
		String phone = BrowseUtil.getSessionPhone(request);
		
		if(!phone.equalsIgnoreCase(user_name)){
			System.out.println("phone  不一致");
			//BrowseUtil.createSessionPhone(request, user_name);
		}
		
		String token = BrowseUtil.getSessionToken(request);
		
		if(!token.equalsIgnoreCase(trade_no)){
			System.out.println("token  不一致");
		}
		
		ProductBuy buy = new ProductBuy();
		buy.setTrade_no(trade_no);
		List<ProductBuyVo> listBuy = productBuyService.selectForList(buy);
		if(listBuy.size() >0){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("不能重复购买");
			
			return sr;
		}
		
		
		Double cny = RoofNumberUtils.StringtoDouble(amount);
		//Long product_id = Long.parseLong(goods_id);
		Product pro = new Product();
		pro.setDebt_code(goods_id);
		
		
		List<ProductVo> productList = productService.selectForList(pro);
		
		if(productList.size() != 1){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("存在多个标");
			
			return sr;
		}
		
		ProductVo product = productList.get(0);
		
		//调用支付接口
		User user = userService.selectForObjectByPhone(user_name);
		if((user.getNew_hand()==2)&&(product.getIs_fresh()==1)){
			sr.setReturnCase(false);
			sr.setStatus(102);
			sr.setContent("新手标不能投");
			
			return sr;
		}
		
		//资金流动明细  锁定金额
		/*Funds fund = new Funds();
		fund.setRelation_id(product.getId());
		fund.setOperate_type(22);
		fund.setStatus(3);
		List<FundsVo> listFunds = fundsService.selectForList(fund);*/
		Double fundAmount = SinaResultUtil.invested(product.getId(), 3, fundsService);
		
		
		
		if(product.getBalance()<cny + fundAmount){
			if(fundAmount>0.0){
				sr.setReturnCase(false);
				sr.setStatus(101);
				sr.setContent("该项目部分金额被锁定，请稍后查看");
				return sr;
			}else{
				sr.setReturnCase(false);
				sr.setStatus(101);
				sr.setContent("投入金额已经超过剩余金额");
				return sr;
			}
			
		}
		
		if(product.getBalance()== 0.0){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("已经满标");
			
			return sr;
		}
		
		
		
		
		
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> list = userBankService.selectForList(userBank);
		
		if(list.size() == 0){
			sr.setContent("需要绑定快捷支付");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		String out_trade_code = "1001";
		String cashdesk_addr_category = "MOBILE";
		String pay_method = "";
	
		
		
		
		String type = "1";
		if(type.equalsIgnoreCase("1"))
			pay_method = "online_bank^" + amount + "^BANKPAY,DEBIT,C";
		else if(type.equalsIgnoreCase("2"))
			pay_method = "balance^" + amount + "^BANK";
		
		if(gift_money == null || gift_money.isEmpty())
			gift_money = "0.0";
		
		if(user_coupon_id == null || user_coupon_id.isEmpty())
			user_coupon_id = "0";
		
		if(user_gift_id == null || user_gift_id.isEmpty())
			user_gift_id = "0";
		
		if(coin == null || coin.isEmpty())
			coin = "0";
		
		String result = SinaUtil.createHostCollectTrade(user.getId(),trade_no, out_trade_code, summary, cny,
				goods_id, pay_method, gift_money, cashdesk_addr_category, user.getReg_ip(),
				coin,user_gift_id,reg_source,user_coupon_id);
		System.out.println(result);
		
		
		//异步提款
		ProductBuy productBuy = new ProductBuy();
		productBuy.setUser_id(user.getId());
		productBuy.setTrade_no(trade_no);
		productBuy.setProduct_id(product.getId());
		productBuy.setProduct_name(goods_id);
		productBuy.setAmount(cny);
		productBuy.setCreate_time(new Date());
		productBuy.setReq_source(Integer.parseInt(reg_source));
		productBuy.setAct_pay_money(cny - Double.parseDouble(gift_money));
		productBuy.setCoin(Double.parseDouble(coin));
		productBuy.setUser_coupon_id(Long.parseLong(user_coupon_id));
		productBuy.setStatus(9);
		productBuy.setUser_gift_id(Long.parseLong(user_gift_id));
		productBuyService.save(productBuy);
		
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			sr.setContent(resultMap.get("response_msg"));
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		Map dataMap =  (Map) resultMap.get("data");
		
		String redirect_url = (String) dataMap.get("redirect_url");
		
		/*getProductBuy(user,cny,Long.parseLong(goods_id),RoofNumberUtils.StringtoDouble(gift_money),
				Long.parseLong(user_coupon_id),Long.parseLong(user_gift_id),trade_no,reg_source);*/
		
		//更新用户统计账户信息
		/*UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		Double balance = voF.getBalance();
		balance += cny;
		voF.setBalance(balance);
		userFundStatService.updateUserFundStatById(userFund);*/
		
		
		Funds funds = new Funds();
		//String ticket = (String) resultMap.get("ticket");
		
		funds.setAmount(cny);
		//funds.setBalance(balance);
		funds.setRelation_id(product.getId());
		funds.setAction(2);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		String strText = "";
		/*if(!user_coupon_id.equalsIgnoreCase("0")){
			strText = "user_coupon_id : " + user_coupon_id;
			funds.setFunds_id(Long.parseLong(user_coupon_id));
		}
			
		
		if(!user_gift_id.equalsIgnoreCase("0")){//红包ID
			strText += "user_gift_id : " + user_gift_id;
			funds.setUid(Long.parseLong(user_gift_id));
		}*/
		//funds.setRemark(投标);
		funds.setOperate_type(22);
		funds.setStatus(3);
		funds.setTrade_no(trade_no);
		funds.setTicket(redirect_url);
		funds.setSummary(summary);
		funds.setBalance(RoofNumberUtils.StringtoDouble(gift_money));
		
		//funds.setUid(user_gift_id);//投资
		fundsService.save(funds);
		
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(redirect_url);
		
		return sr;
	}
	
	//获取用户投标1002=投标  借款人  还款
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value="/host/collect/trade",method=RequestMethod.POST)
	public @ResponseBody SmsReturn gethostCollectTrade(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String strDate = DateUtil.formatDate(new Date());
		//返回用户    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductBuyIncomeEnd");
		map.put("end_dates",strDate);
		
	    
	    List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);
		
		
		
		Double cny = 0.0;
		for(int i = 0;i<listBuyI.size();i++){
			ProductBuyIncome buyIncomeV = listBuyI.get(i);
			if(buyIncomeV.getIs_return_amount()  == 1){
				if(buyIncomeV.getAmount() != null)
					cny += buyIncomeV.getAmount();
			}
			
			cny += buyIncomeV.getIncome();
			
		}
		
		
		String out_trade_code = "1002";
		
		String cashdesk_addr_category = "MOBILE";
		String pay_method = "";
		
		/*String type = "1";//免密功能
		if(type.equalsIgnoreCase("1"))
			pay_method = "online_bank^" + amount + "^SINAPAY,DEBIT,C";
		else if(type.equalsIgnoreCase("2"))
			pay_method = "balance^" + amount + "^BANK";*/
		
		
		Map<Long,Double> mapO = new HashMap<Long,Double>();
		for(int i = 0;i<listBuyI.size();i++){
			ProductBuyIncome buyIncomeV = listBuyI.get(i);
			if(buyIncomeV.getStatus() == -1){
				
				/*Long  old =   buyIncomeV.getOld_buy_id();
				int num = 0;
				if(old == null){
					
				}else{
					num = buyIncomeV.getNum();
				}*/
				int num = buyIncomeV.getNum();
				
				buyIncomeV.setNum(++num);
				Double am = 0.0;
				Long product_id = buyIncomeV.getProduct_id();
				
				
				if(buyIncomeV.getIs_return_amount()  == 1){
					if(buyIncomeV.getAmount() != null){
						cny += buyIncomeV.getAmount();
						am +=buyIncomeV.getAmount();
					}
						
				}
				//
				cny += buyIncomeV.getIncome();
				am +=buyIncomeV.getIncome();
				
				Double ob = mapO.get(product_id);
				if(ob == null){
					mapO.put(product_id, am);
					ServiceChargeUtil.getInstance().addProductId(product_id);
				}else{
					mapO.put(product_id,am + ob);
				}
			}
			
		}
		//还款
		Map<Long,Double> mapH = new HashMap<Long,Double>();
		  Iterator<Map.Entry<Long, Double>> it = mapO.entrySet().iterator();
			while (it.hasNext()) {
		     
			 
			Map.Entry<Long, Double> entry = it.next();
			  Long in = entry.getKey();
			  Product pro = new Product();
				pro.setId(in);
				List<ProductVo> productList = productService.selectForList(pro);
				
				if(productList.size() != 1){
					sr.setReturnCase(false);
					sr.setStatus(101);
					sr.setContent("存在多个标");
					
					return sr;
				}
				
				ProductVo product = productList.get(0);
				
				Double str = mapO.get(in);//得到每个key多对用value的值
				
				
				cny = SinaResultUtil.doubleToTwo(str);
				String amount=cny.toString();
				String type = "2";//免密功能
				if(type.equalsIgnoreCase("1"))
					pay_method = "online_bank^" + amount + "^BANKPAY,DEBIT,C";
				else if(type.equalsIgnoreCase("2"))
					pay_method = "balance^" + amount + "^BANK";
				Lender len = new Lender();
				len.setId(product.getLender_id());
				List<LenderVo> listVo = lenderService.selectForList(len);
				Lender lender = listVo.get(0);
				
				Double ob = mapH.get(lender.getUser_id());
				if(ob == null){
					mapH.put(lender.getUser_id(), str);
				}else{
					mapH.put(lender.getUser_id(),str + ob);
				}
			
				String trade_no = UniqueUtil.getTradeNo();
				String gift_money="0.0";
				
				String summary = "还钱" + product.getDebt_code();
				String result = SinaUtil.createHostCollectTrade(lender.getUser_id(),trade_no, out_trade_code, summary, cny,
						product.getDebt_code(), pay_method, gift_money, cashdesk_addr_category, "117.149.16.71","","","","");
				System.out.println("trade_no:" + trade_no + ":"+ result);
				
				
				Map resultMap = JsonUtil.jsonToMap(result);
				
				Object strCode =  resultMap.get("response_code");
				
				if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
					
					continue ;
				}
				
				Map dataMap =  (Map) resultMap.get("data");
				
				String redirect_url = (String) dataMap.get("");
			           
              //Double str = mapO.get(in);//得到每个key多对用value的值
              System.out.println(in + "     " + str);
              
            //资金统计
      		UserFundStat userFund = new UserFundStat();
      		userFund.setUser_id(lender.getUser_id());
      		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
      		//voF.setPend_return(voF.getPend_return() - cny);
      		voF.setBalance(voF.getBalance() - cny);
      		userFundStatService.updateUserFundStatById(voF);
		}
		
		
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent("");
		
		return sr;
	}

	
	
	
	//获取用户放款——按照项目放款
	@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value="/pay/trade/product",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostPayTradeProductId(HttpServletRequest request,
            HttpServletResponse response) throws UnknownHostException{
		
		SmsReturn sr = new SmsReturn();
		
		String product_id = request.getParameter("product_id");
		
		
		String strDate = DateUtil.formatDate(new Date());
		 Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductBuyIncomeEnd");
		map.put("end_dates",strDate);
			
		    
		List<Long> list = ServiceChargeUtil.getInstance().getProduct();
		if(product_id !=null&&!product_id.isEmpty()){
			map.put("product_id",product_id);
		}else{
			
		}
		
		//for(int i = 0;i<list.size();i++){
			//map.put("product_id",list.get(i));
			List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);
			
			Double cny = 0.0;
			String trade_list = "";
			String summary="批量还钱";
			for(int j = 0;j<listBuyI.size();j++){
				
				if(j != 0){
					trade_list += "$";
				}
				ProductBuyIncome vo = listBuyI.get(j);
				
				if(vo.getStatus() == 1){
					
					String trade_no = UniqueUtil.getTradeNo();
					Long id = vo.getUser_id();
					String uid = "UID";
					String bank = "BANK";
					Double repay_profit = 0.0;//利息
					
					
					Double repay_principal = 0.0;//本金
					//资金流动明细
					Reconciliation reconciliation = new  Reconciliation();
					if(vo.getIs_return_amount()  == 1){
						if(vo.getAmount() != null){
							repay_principal = vo.getAmount();
							//am +=buyIncomeV.getAmount();
							reconciliation.setHf_amt(vo.getAmount());
						}
							
					}
					repay_profit = vo.getIncome();
					
					
					
					reconciliation.setOrder_date(DateUtil.formatDate(new Date()));
					reconciliation.setCreate_time(new Date());
					reconciliation.setTrans_amt(repay_profit.toString());
					//reconciliation.setInvest_cust_id(buy_trade_no);
					reconciliation.setMer_cust_id(trade_no);
					
					reconciliation.setTrans_type("REPAYMENT");
					//reconciliationService.save(reconciliation);
				
					
					Double amount1 = repay_profit + repay_principal;
					
					int repay_period = 1;
					
					Product pro = new Product();
					pro.setId(vo.getProduct_id());
					List<ProductVo> productList = productService.selectForList(pro);
					
					if(productList.size() != 1){
						sr.setReturnCase(false);
						sr.setStatus(101);
						sr.setContent("存在多个标");
						
						return sr;
					}
					
					ProductVo product = productList.get(0);
					
					String list_extend_param = "repay_principal^" + repay_principal.toString() +
							"|repay_profit^" + repay_profit.toString()+ "|repay_period^" + repay_period;
					System.out.println(id.toString() );
					trade_list += trade_no + "~" + id.toString() + "~UID~BANK~" 
								 + amount1.toString() + "~~" + summary + "~"+ list_extend_param+ "~~" + product.getDebt_code()+"~";
				
					
					
					/*vo.setStatus(1);
					
					productBuyIncomeService.updateProductBuyIncomeById(vo);*/
				
				}
			}
				
				String trade_no = UniqueUtil.getTradeNo();
				String sip  = InetAddress.getLocalHost().getHostAddress();
				String result = SinaUtil.createBatchHostPayTrade(trade_no, trade_list, sip);
				/*String result = SinaUtil.createSingleHostPayTrade(user.getId(), trade_no, out_trade_code, 
						summary, cny, goods_id, user.getReg_ip(),cny,repay_period,repay_profit,extend_param);*/
			
				System.out.println("product_id" + product_id + "trade_no:" + trade_no + ":"+ result );
				
				
				//返回值
				Map resultMap = JsonUtil.jsonToMap(result);
				
				Object strCode =  resultMap.get("response_code");
				
				if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
					
					System.out.println("失败");
				}
				
				System.out.println("成功 product_id"  + product_id);
				//productService.save(product);
				
			//}
		
		
		
		return sr;
	}
	
	//把多余的钱
	@RequestMapping(value="/pay/trade/refund",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostPayTradeRefund(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String product_id = request.getParameter("product_id");
		/*Double an = 200000.00;
		String result2 = SinaUtil.createHostRefund(131102L, "zdjfu15129849025145445356", "zdjfu1512984902514544535", "投资失败", an,"117.149.16.71");*/
		ProductBuy productBuy = new ProductBuy();
		productBuy.setProduct_id(Long.parseLong(product_id));
		productBuy.setStatus(9);
		
		
		Double wait = 0.0;
		Double close = 0.0;
		Double fish = 0.0;
		Double fail = 0.0;
		
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
		
		for(int i = 0;i<listBuy.size();i++){
			ProductBuy buy = listBuy.get(i);
			Long buy_user_id = buy.getUser_id();
			String buy_trade_no = buy.getTrade_no();
			String result = SinaUtil.queryHostTrade(buy_user_id, buy_trade_no, "", "");
			
			//返回值
			Map resultMap = JsonUtil.jsonToMap(result);
			
			Object strCode =  resultMap.get("response_code");
			
			if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
				
				continue ;
			}
			
			Map dataMap =  (Map) resultMap.get("data");
			
			String trade_list = (String) dataMap.get("trade_list");
			
			if(trade_list.indexOf("WAIT_PAY")>0){
				wait += buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_CLOSED")>0){
				close+=buy.getAmount();
				
				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_FINISHED")>0){
				//成功
				fish +=buy.getAmount();
				
				
				String trade_no = UniqueUtil.getTradeNo();
				//资金流动明细
				/*Reconciliation reconciliation = new  Reconciliation();
				reconciliation.setOrder_date(DateUtil.formatDate(new Date()));
				reconciliation.setCreate_time(new Date());
				reconciliation.setTrans_amt(buy.getAmount().toString());
				reconciliation.setInvest_cust_id(buy_trade_no);
				reconciliation.setMer_cust_id(trade_no);
				reconciliation.setHf_amt(buy.getAct_pay_money());
				reconciliation.setTrans_type("refund");
				reconciliationService.save(reconciliation);*/
				if(buy.getAmount()>2900){
					Long uid = buy.getUser_id();
					String result1 = SinaUtil.createHostRefund(uid, trade_no, buy_trade_no, "投资失败", buy.getAmount(),"117.149.16.71");
					System.out.println(result1 + "trade_no:  " + buy_trade_no + " finished :" + buy.getAmount() +  "uid:"  + uid);
				}
				
				/*Long uid = buy.getUser_id();
				
				System.out.println("trade_no:  " + buy_trade_no + " finished :" + buy.getAmount() +  "uid:"  + uid);*/
				
			}else if(trade_list.indexOf("TRADE_FAILED")>0){
				fail +=buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " fail :");
			}
		}
		return sr;
	}
	
	//获取用户放款——出借人  
	@RequestMapping(value="/pay/trade/buy",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostPayTradeBuy(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String product_id = request.getParameter("product_id");
		
		User user = userService.selectForObjectByPhone(user_name);
		ProductBuy productBuy = new ProductBuy();
		productBuy.setProduct_id(Long.parseLong(product_id));
		productBuy.setUser_id(user.getId());
		
		
		Double wait = 0.0;
		Double close = 0.0;
		Double fish = 0.0;
		Double fail = 0.0;
		
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
		
		for(int i = 0;i<listBuy.size();i++){
			ProductBuy buy = listBuy.get(i);
			Long buy_user_id = buy.getUser_id();
			String buy_trade_no = buy.getTrade_no();
			String result = SinaUtil.queryHostTrade(buy_user_id, buy_trade_no, "", "");
			
			//返回值
			Map resultMap = JsonUtil.jsonToMap(result);
			
			Object strCode =  resultMap.get("response_code");
			
			if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
				
				continue ;
			}
			
			Map dataMap =  (Map) resultMap.get("data");
			
			String trade_list = (String) dataMap.get("trade_list");
			
			if(trade_list.indexOf("WAIT_PAY")>0){
				wait += buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_CLOSED")>0){
				close+=buy.getAmount();
				
			
				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_FINISHED")>0){
				//成功
				fish +=buy.getAmount();
		
				//System.out.println("trade_no:  " + buy_trade_no + " fail :");
			}else if(trade_list.indexOf("TRADE_FAILED")>0){
				fail +=buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " fail :");
			}
		}
		
		return sr;
	}
	
	
	//获取用户放款——出借人  
	@RequestMapping(value="/pay/trade",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostPayTrade(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String amount = request.getParameter("amount");
		String sign = request.getParameter("sign");
		String summary = request.getParameter("summary");
		String goods_id = request.getParameter("goods_id");
		String md5Sign = Md5Util.md5to32(user_name + goods_id + amount +
				Constants.API_KEY);
		
		/*if( null == sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
			
			return sr;
		}*/
		
		//调用支付接口
		User user = userService.selectForObjectByPhone(user_name);
		Double cny = RoofNumberUtils.StringtoDouble(amount);
		
		
		Product pro = new Product();
		pro.setDebt_code(goods_id);
		
		
		List<ProductVo> productList = productService.selectForList(pro);
		
		if(productList.size() != 1){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("存在多个标");
			
			return sr;
		}
		
		ProductVo product = productList.get(0);
		
		/*if(product.getSale_money().compareTo(cny) != 0){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("没有满标，请把余额投完");
			
			return sr;
		}*/
		
		Lender len = new Lender();
		len.setId(product.getLender_id());
		List<LenderVo> listVo = lenderService.selectForList(len);
		Lender lender = listVo.get(0);
		//lenderService.getLender_id();
		
		ProductBuy productBuy = new ProductBuy();
		productBuy.setProduct_id(product.getId());
		productBuy.setStatus(1);;
		
		Double wait = 0.0;
		Double close = 0.0;
		Double fish = 0.0;
		Double fail = 0.0;
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
		for(int i = 0;i<listBuy.size();i++){
			ProductBuy buy = listBuy.get(i);
			Long buy_user_id = buy.getUser_id();
			String buy_trade_no = buy.getTrade_no();
			String result = SinaUtil.queryHostTrade(buy_user_id, buy_trade_no, "", "");
			
			//返回值
			Map resultMap = JsonUtil.jsonToMap(result);
			
			Object strCode =  resultMap.get("response_code");
			
			if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
				
				continue ;
			}
			
			Map dataMap =  (Map) resultMap.get("data");
			
			String trade_list = (String) dataMap.get("trade_list");
			
			if(trade_list.indexOf("WAIT_PAY")>0){
				wait += buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_CLOSED")>0){
				close+=buy.getAmount();
				
			
				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_FINISHED")>0){
				//成功
				fish +=buy.getAmount();
		
				System.out.println("trade_no:  " + buy_trade_no + " succ :");
			}else if(trade_list.indexOf("TRADE_FAILED")>0){
				fail +=buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " fail :");
			}
		}
		
		

		String out_trade_code = "2001";
		String trade_no = UniqueUtil.getTradeNo();
		
		Long uid = lender.getUser_id();
		Double amout = SinaResultUtil.doubleToTwo(cny);
		String result = SinaUtil.createSingleHostPayTrade(uid, trade_no, out_trade_code, 
				summary, amout, goods_id, user.getReg_ip(),0.0,0,0.0,"");
	
		System.out.println("trade_no:" + trade_no + ":"+ result);
		
		
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			sr.setContent(resultMap.get("response_msg"));
			sr.setReturnCase(false);
			sr.setStatus(102);
			return sr;
		}
		
		if(product.getStatus() == 7){
			product.setStatus(5);
			//product.setlo
			//product.setc
			UserFundStat userFund = new UserFundStat();
			userFund.setUser_id(uid);
			UserFundStat voF = userFundStatService.selectForObjectById(userFund);
			voF.setBalance(voF.getBalance() + cny);
			userFundStatService.updateUserFundStatById(voF);
		}
		productService.updateProductById(product);
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setMapContent("放款成功");
		
		return sr;
	}
	
	//获取用户放款——还款投资人
	@RequestMapping(value="/single/pay/trade/one",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostSinglePayTradeOne(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String amount = request.getParameter("amount");
		String summary = request.getParameter("summary");
		String goods_id = request.getParameter("goods_id");
		String repay_period = request.getParameter("repay_period");//期数
		String repay_profit = request.getParameter("repay_profit");//利息
		/*String md5Sign = Md5Util.md5to32(user_name + goods_id + amount +
				Constants.API_KEY);*/
		
		/*if( null == sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
			
			return sr;
		}*/
		
		Product pro = new Product();
		pro.setDebt_code(goods_id);
		List<ProductVo> productList = productService.selectForList(pro);
		
		if(productList.size() != 1){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("存在多个标");
			
			return sr;
		}
		
		ProductVo product = productList.get(0);
		
		/*if(product.getSale_money().compareTo(cny) != 0){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("没有满标，请把余额投完");
			
			return sr;
		}*/
		
		/*Lender len = new Lender();
		len.setId(product.getLender_id());
		List<LenderVo> listVo = lenderService.selectForList(len);
		Lender lender = listVo.get(0);*/
		
		ProductBuy buy = new ProductBuy();
		buy.setProduct_id(product.getId());
		
		List<ProductBuyVo> listBuy = productBuyService.selectForList(buy);
		
		ProductBuyVo productBuy = listBuy.get(0);
		/*cny = 0.0;
		for(int i = 0;i<listBuy.size();i++){
			ProductBuyVo productBuy = listBuy.get(i);
			
			cny +=productBuy.getAmount() + productBuy.getWill_income();
			
		}*/
		
		//调用支付接口
		User user = userService.selectForObjectByPhone(user_name);
		Double cny = RoofNumberUtils.StringtoDouble(amount);
		


		String out_trade_code = "2002";
		String trade_no = UniqueUtil.getTradeNo();
		
		//Double repay_profit = 5.0;//利息
		
		Double repay_principal = cny - RoofNumberUtils.StringtoDouble(repay_profit);//本金cny-repay_profit
		
		//int repay_period = 1;
		
		
		
		String extend_param = "repay_principal^" + repay_principal.toString() + "|repay_profit^" + 
				repay_profit.toString()+ "|repay_period^" + repay_period;
		
		
		String result = SinaUtil.createSingleHostPayTrade(user.getId(), trade_no, out_trade_code, 
				summary, cny, goods_id, user.getReg_ip(),cny,
				Integer.parseInt(repay_period),RoofNumberUtils.StringtoDouble(repay_profit),extend_param);
	
		System.out.println("trade_no:" + trade_no + ":"+ result);
		
		
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			sr.setContent("返钱不成功");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		/*Map dataMap =  (Map) resultMap.get("data");
		
		String redirect_url = (String) dataMap.get("redirect_url");*/
		
		/*if(pay_type.equalsIgnoreCase("2")){
			Map dataMap =  (Map) resultMap.get("data");
			
			Object html =  dataMap.get("html");
			
			String strHtml = html.toString();
			
			sr.setContent("只有PC端");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}*/
		
		
		//更新用户统计账户信息
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		Double balance = voF.getBalance();
		balance += cny;
		voF.setBalance(SinaResultUtil.queryBalance(user));
		userFundStatService.updateUserFundStatById(voF);
		
		
		Funds funds = new Funds();
		//String ticket = (String) resultMap.get("ticket");
		
		funds.setAmount(cny);
		funds.setBalance(balance);
		funds.setAction(1);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setRemark(result);
		funds.setOperate_type(11);
		funds.setStatus(3);
		funds.setTrade_no(trade_no);
		//funds.setTicket(ticket);
		funds.setSummary(summary+"single");
		fundsService.save(funds);
		
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setMapContent("返钱成功");
		
		return sr;
	}
	
	//获取用户放款——还款投资人
	@RequestMapping(value="/single/pay/trade",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostSinglePayTrade(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
	
		String strDate = DateUtil.formatDate(new Date());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductBuyIncomeEnd");
		map.put("end_dates",strDate);
		//map.put("end_dates","2017-12-02");
	    
	    List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);
		
		for(int i = 0;i<listBuyI.size();i++){
			ProductBuyIncome buyIncomeV = listBuyI.get(i);
			String out_trade_code = "2002";
			String trade_no = UniqueUtil.getTradeNo();
			
			if(buyIncomeV.getStatus() == -1){
				
				Double cny=0.0;
				Double repay_principal=0.0;
				if(buyIncomeV.getIs_return_amount()  == 1){
					if(buyIncomeV.getAmount() != null){
						cny += buyIncomeV.getAmount();
						repay_principal = buyIncomeV.getAmount();
					}	
				}
				//
				
				Product pro = new Product();
				pro.setId(buyIncomeV.getProduct_id());
				List<ProductVo> productList = productService.selectForList(pro);
				
				if(productList.size() != 1){
					sr.setReturnCase(false);
					sr.setStatus(101);
					sr.setContent("存在多个标");
					
					return sr;
				}
				
				ProductVo product = productList.get(0);
				cny += buyIncomeV.getIncome();
				String repay_profit = buyIncomeV.getIncome().toString();
				
				int repay_period= 1;
				String extend_param = "repay_principal^" + repay_principal.toString() + "|repay_profit^" + 
						repay_profit.toString()+ "|repay_period^" + repay_period;
				
				
				String result = SinaUtil.createSingleHostPayTrade(buyIncomeV.getUser_id(), trade_no, out_trade_code, 
						"回款", cny, product.getDebt_code(), "117.149.16.71",cny,
						repay_period,RoofNumberUtils.StringtoDouble(repay_profit),extend_param);
			
				System.out.println("trade_no:" + trade_no + ":"+ result);
				
				
				//返回值
				Map resultMap = JsonUtil.jsonToMap(result);
				
				Object strCode =  resultMap.get("response_code");
				
				if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
					
					sr.setContent("返钱不成功");
					sr.setReturnCase(false);
					sr.setStatus(101);
					return sr;
				}
				
				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(buyIncomeV.getUser_id());
				UserFundStat voF = userFundStatService.selectForObjectById(userFund);
				
				voF.setBalance(voF.getBalance()+cny);
				
			}
			
		}
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setMapContent("返钱成功");
		
		return sr;
	}
	
	
	//获取用户放款——还款投资人——批量
	@RequestMapping(value="/batch/pay",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostBatch(HttpServletRequest request,
            HttpServletResponse response) throws UnknownHostException{
		
		SmsReturn sr = new SmsReturn();
		
		String strDate = DateUtil.formatDate(new Date());
		//返回用户    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductBuyIncomeEnd");
		map.put("end_dates",strDate);
	    
	    List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);
		for(int i = 0;i<listBuyI.size();i++){
			ProductBuyIncome buyIncomeV = listBuyI.get(i);
			if(buyIncomeV.getStatus() == -1/*&&buyIncomeV.getProduct_id()==Long.parseLong(product_id)*/){
				
				Double am = 0.0;
				//Long product_id1= buyIncomeV.getProduct_id();
				
				Double cny=0.0;
				if(buyIncomeV.getIs_return_amount()  == 1){
					if(buyIncomeV.getAmount() != null){
						cny += buyIncomeV.getAmount();
						//am +=buyIncomeV.getAmount();
						
					}
						
				}
				//
				
				Double income  = buyIncomeV.getIncome();
				cny += income;
				//am +=buyIncomeV.getIncome();
				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(buyIncomeV.getUser_id());
				
				User user = new User();
				user.setId(buyIncomeV.getUser_id());
				User user1 = userService.load(user);
				
				
				UserFundStat voF = userFundStatService.selectForObjectById(userFund);
				//Double ba = SinaResultUtil.queryBalance(user1);
				//voF.setBalance(ba);
				voF.setBalance(voF.getBalance()+cny);
				voF.setPend_return(voF.getPend_return() - cny);
				userFundStatService.updateUserFundStatById(voF);
				
				
				Sms sms = new Sms();
				sms.setSms_type(Integer.valueOf(3));
				sms.setPhone(user1.getUser_name());
				sms.setSource_ip("117.149.16.71");
		
				sms.setStatus(0);
				sms.setContent("amount:" + cny + "income : " + income);
				
				sms.setProduct("正道");
				sms.setAmount("");
				sms.setIncome(cny.toString());
				String res = smservice.sendSms(sms);
				
				if(res!=null && "0".equals(res)){					
					buyIncomeV.setStatus(1);
					productBuyIncomeService.updateProductBuyIncomeById(buyIncomeV);
					System.out.println("ok ___id : "  +  user1.getId() + "name : " + user1.getReal_name());
				}else{
					System.out.println("no ___id : "  +  user1.getId() + "name : " + user1.getReal_name());
				}
				
			}
			
		}
		
		return sr;
	}
	
	//获取用户放款——还款投资人——批量
		@RequestMapping(value="/batch/pay/trade/sle",method=RequestMethod.POST)
		public @ResponseBody SmsReturn hostBatchPayTradeSle(HttpServletRequest request,
	            HttpServletResponse response) throws UnknownHostException{
			
			SmsReturn sr = new SmsReturn();
			
			String user_id = request.getParameter("user_id");
			String amount = request.getParameter("amount");
			String buy_trade_no = request.getParameter("buy_trade_no");
			
			
			
			
			String trade_no = UniqueUtil.getTradeNo();
			//资金流动明细
			Reconciliation reconciliation = new  Reconciliation();
			reconciliation.setOrder_date(DateUtil.formatDate(new Date()));
			reconciliation.setCreate_time(new Date());
			reconciliation.setTrans_amt(amount);
			reconciliation.setInvest_cust_id(buy_trade_no);
			reconciliation.setMer_cust_id(trade_no);
			//reconciliation.setHf_amt(buy.getAct_pay_money());
			reconciliation.setTrans_type("REFUND");
			reconciliationService.save(reconciliation);//放款流水
			
			Long uid = Long.parseLong(user_id);
			Double cny = RoofNumberUtils.StringtoDouble(amount);
			String result1 = SinaUtil.createHostRefund(uid, trade_no, buy_trade_no, "投资失败", cny,"117.149.16.71");
			System.out.println(result1 + "trade_no:  " + buy_trade_no + " finished :" + amount +  "uid:"  + uid);
			UserFundStat userFund = new UserFundStat();
			userFund.setUser_id(uid);
			UserFundStat voF = userFundStatService.selectForObjectById(userFund);
			
			User us = new User();
			us.setId(uid);

			User  user = userService.load(us);
			voF.setPend_return(voF.getPend_return() - cny);
			voF.setBalance(SinaResultUtil.queryBalance(user));
			userFundStatService.updateUserFundStatById(voF);
			return sr;
		}
	
	//获取用户放款——还款投资人——批量
	@RequestMapping(value="/batch/pay/trade",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostBatchPayTrade(HttpServletRequest request,
            HttpServletResponse response) throws UnknownHostException{
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String amount = request.getParameter("amount");
		String sign = request.getParameter("sign");
		String summary = request.getParameter("summary");
		String product_id = request.getParameter("product_id");
	
		
		
		String strDate = DateUtil.formatDate(new Date());
		//返回用户    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductBuyIncomeEnd");
		map.put("end_dates",strDate);
		if(product_id != null&&!product_id.isEmpty())
			map.put("product_id",product_id);
		
		
	    
	    List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);
		
		Double cny = 0.0;
		String trade_list = "";
		summary="批量还钱";
		for(int i = 0;i<listBuyI.size();i++){
			
			if(i != 0){
				trade_list += "$";
			}
			ProductBuyIncome vo = listBuyI.get(i);
			
			if(vo.getStatus() == -1){
				
				String trade_no = UniqueUtil.getTradeNo();
				Long id = vo.getUser_id();
				String uid = "UID";
				String bank = "BANK";
				Double repay_profit = 0.0;//利息
				
				
				Double repay_principal = 0.0;//本金
				if(vo.getIs_return_amount()  == 1){
					if(vo.getAmount() != null){
						repay_principal = vo.getAmount();
						
					}
						
				}
				repay_profit = vo.getIncome();
			
				
				Double amount1 = repay_profit + repay_principal;
				
				int repay_period = 1;
				
				Product pro = new Product();
				pro.setId(vo.getProduct_id());
				List<ProductVo> productList = productService.selectForList(pro);
				
				if(productList.size() != 1){
					sr.setReturnCase(false);
					sr.setStatus(101);
					sr.setContent("存在多个标");
					
					return sr;
				}
				
				ProductVo product = productList.get(0);
				
				String list_extend_param = "repay_principal^" + repay_principal.toString() +
						"|repay_profit^" + repay_profit.toString()+ "|repay_period^" + repay_period;
				System.out.println(id.toString() );
				trade_list += trade_no + "~" + id.toString() + "~UID~BANK~" 
							 + amount1.toString() + "~~" + summary + "~"+ list_extend_param+ "~~" + product.getDebt_code()+"~";
			
			
			}
		}
		
		//还款
		
		//调用支付接口
		
		String trade_no = UniqueUtil.getTradeNo();
		String sip  = InetAddress.getLocalHost().getHostAddress();
		String result = SinaUtil.createBatchHostPayTrade(trade_no, trade_list, sip);
		
	
		System.out.println("trade_no:" + trade_no + ":"+ result);
		
		
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			sr.setContent("返钱不成功");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		
		for(int i = 0;i<listBuyI.size();i++){
			ProductBuyIncome buyIncomeV = listBuyI.get(i);
			if(buyIncomeV.getStatus() == -1/*&&buyIncomeV.getProduct_id()==Long.parseLong(product_id)*/){
				
				
				
				
				Double am = 0.0;
				//Long product_id1= buyIncomeV.getProduct_id();
				
				cny=0.0;
				if(buyIncomeV.getIs_return_amount()  == 1){
					if(buyIncomeV.getAmount() != null){
						cny += buyIncomeV.getAmount();
						//am +=buyIncomeV.getAmount();
						
					}
						
				}
				//
				cny += buyIncomeV.getIncome();
				//am +=buyIncomeV.getIncome();
				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(buyIncomeV.getUser_id());
				UserFundStat voF = userFundStatService.selectForObjectById(userFund);
				voF.setPend_return(voF.getPend_return() - cny);
				voF.setBalance(voF.getBalance()+cny);
				userFundStatService.updateUserFundStatById(voF);
				
				
				Reconciliation reconciliation = new  Reconciliation();
					
					reconciliation.setCreate_time(new Date());
					reconciliation.setTrans_amt(cny.toString());
					reconciliation.setInvest_cust_id(buyIncomeV.getUser_id().toString());
					reconciliation.setMer_cust_id(buyIncomeV.getProduct_id().toString());
					reconciliation.setTrans_type("LOANS");
					reconciliationService.save(reconciliation);
				
			}
			
		}
		
	
		
		//更新用户统计账户信息
	
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setMapContent("返钱成功");
		
		return sr;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	
	@Autowired(required = true)
	public void setFundsService(IFundsService fundsService) {
		this.fundsService = fundsService;
	}
	
	@Autowired(required = true)
	public void setUserBankService(IUserBankService userBankService) {
		this.userBankService = userBankService;
	}
	
	@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}
	
	@Autowired(required = true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	
	@Autowired(required = true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	
	@Autowired(required = true)
	public void setProductIncomeService(IProductIncomeService productIncomeService) {
		this.productIncomeService = productIncomeService;
	}
	
	@Autowired(required = true)
	public void setProductBuyIncomeService(IProductBuyIncomeService productBuyIncomeService) {
		this.productBuyIncomeService = productBuyIncomeService;
	}
	
	@Autowired(required = true)
	public void setUserCouponService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}
	
	@Autowired(required = true)
	public void setCoinService(ICoinService coinService) {
		this.coinService = coinService;
	}
	
	@Autowired(required = true)
	public void setCoinStreamService(ICoinStreamService coinStreamService) {
		this.coinStreamService = coinStreamService;
	}
	
	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Autowired(required = true)
	public void setLenderService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}
	
	@Autowired(required = true)
	public void setReconciliationService(IReconciliationService reconciliationService) {
		this.reconciliationService = reconciliationService;
	}
	
	@Autowired(required = true)
	public void setSmsService(ISmsService smservice) {
		this.smservice = smservice;
	}

}
