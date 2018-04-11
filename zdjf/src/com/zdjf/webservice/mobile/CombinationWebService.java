package com.zdjf.webservice.mobile;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bcloud.msg.http.HttpSender;
import com.zdjf.components.mobile.InvestmentOrder;
import com.zdjf.components.mobile.ServiceChargeUtil;
import com.zdjf.components.mobile.SinaResultUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.dao.product.IProductBuyIncomeDao;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.CoinStreamVo;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.notice.News;
import com.zdjf.domain.notice.NewsVo;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductBuyIncomeVo;
import com.zdjf.domain.product.ProductBuyVo;
import com.zdjf.domain.product.ProductIncome;
import com.zdjf.domain.product.ProductIncomeVo;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.domain.user.WithdrawCoupons;
import com.zdjf.domain.user.WithdrawCouponsVo;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.notice.INewsService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductIncomeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.service.user.IWithdrawCouponsService;
import com.zdjf.timejob.DynamicProductJob;
import com.zdjf.timejob.FundsReturnJob;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.webservice.express.SmsReturn;


class  CalendarNode{
	String total;//总和
	String amount;//本金
	String income;//利息
	String name;//
	String dates;//时间
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	
	
}

@RestController
@RequestMapping("/m/combination")
public class CombinationWebService {
	
	Log log = LogFactory.getLog(this.getClass());
	
	private IUserService userService;
	
	private IFundsService fundsService;
	
	private IUserGiftService userGiftService;
	
	private IUserCouponService userCouponService;
	
	private IProductBuyService productBuyService;
	
	private IProductService productService;
	
	private ICoinStreamService coinStreamService;
	
	private IUserFundStatService userFundStatService;
	
	private INewsService newsService;
	
	private IWithdrawCouponsService withdrawCouponsService;
	
	private IProductIncomeService productIncomeService;
	
	private IProductBuyIncomeDao productBuyIncomeDao;
	
	private IProductBuyIncomeService productBuyIncomeService;
	
	
	
	int getGiftCoupon(User user){
		
		UserGift gift = new UserGift();
		gift.setUser_id(user.getId());
		gift.setStatus(1);
		
		List<UserGiftVo> listGift = userGiftService.selectForList(gift);//红包
		
		UserCoupon coupon = new UserCoupon();
		coupon.setUser_id(user.getId());
		coupon.setStatus(1);
		
		List<UserCouponVo> listCoupon = userCouponService.selectForList(coupon);//加息券
		
		WithdrawCoupons withdrawCoupons = new WithdrawCoupons();
		withdrawCoupons.setUser_id(user.getId());
		withdrawCoupons.setAction(1);
		
		List<WithdrawCouponsVo> listWithdraw = withdrawCouponsService.selectForList(withdrawCoupons);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("gift", listGift);
		
		return listGift.size() + listCoupon.size() + listWithdraw.size();
	}
	
	//
	@RequestMapping(value="/one/key",method=RequestMethod.GET)
	public @ResponseBody SmsReturn oneKeyReturn(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		SmsReturn sr = new SmsReturn();
		
		DynamicProductJob dynamicPro = new DynamicProductJob();
		try {
			dynamicPro.dynamicProduct();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("DynamicProductJob" + "error" );
		}
		
		FundsReturnJob fundsJob = new FundsReturnJob();
		try {
			fundsJob.fundsReturn();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sr;
	}
	
	@RequestMapping(value="/one/income",method=RequestMethod.POST)
	public @ResponseBody SmsReturn oneIncomeReturn(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		SmsReturn sr = new SmsReturn();
		
		String product_id = request.getParameter("product_id");
		
		Product pro = new  Product();
		pro.setId(Long.parseLong(product_id));
		
		List<ProductVo> proList = productService.selectForList(pro);
		
		if(proList.size() != 1){
			
			sr.setContent("产品不存在或者有问题");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		Product product = proList.get(0);
		
		//付息模板
		ProductIncome productIncome= new ProductIncome();
		productIncome.setProduct_id(product.getId());
		
		
		List<ProductIncomeVo> listVo = productIncomeService.selectForList(productIncome);
		
		if(listVo.size()<1){
			log.info("付息模板丢失："+product.getProduct_name() + "ID：" + product.getId() + "Debt_code" + product.getDebt_code());


			sr.setContent("付息模板丢失");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		int income_days = 0;
		Double income = 0.0;
		
		ProductBuy buy1 = new ProductBuy();
		buy1.setProduct_id(product.getId());
		buy1.setStatus(1);
		List<ProductBuyVo> listBuy = productBuyService.selectForList(buy1);
		
		String strDate = DateUtil.formatDate(new Date());
		for(int j = 0;j<listBuy.size();j++){
			
			ProductBuy buy = listBuy.get(j);
			User us = new User();
			us.setId(buy.getUser_id());
			
			User user = userService.load(us);
			
			
			//
			Date date = buy.getCreate_time();
			if(date == null){
				date = buy.getPay_time();
			}
			Date start_dates = date;
			if(product.getIncome_method() == 2)//T+1
			{
				start_dates = DateUtil.addDay(date, 1);	
			}
			
			Double amount = buy.getAmount();
			
			Long user_coupon_id = buy.getUser_coupon_id();
			
			Double coupon_interest = 0.0;
			if(user_coupon_id!=null&&user_coupon_id != 0){
				buy.setUser_coupon_id(user_coupon_id);
				UserCoupon userCoupon = new UserCoupon();
				userCoupon.setId(user_coupon_id);
				userCoupon.setStatus(2);
				List<UserCouponVo> listCoupon  = userCouponService.selectForList(userCoupon);
				
				if(listCoupon.size() == 1){
					UserCoupon coupon = listCoupon.get(0);
					if(coupon.getStatus() == 2){
						
						coupon_interest = coupon.getInterest();
						
					}
					
				}
			}
			
			//购买收益
			
			if(product.getReturn_method() == 2){
				
				
				for(int i = 0;i<listVo.size();i++){
					ProductIncomeVo vo = listVo.get(i);
					//vo 参数保存值  
					Integer voDays = vo.getDays();
					if(voDays == null || voDays ==  0 ){
						vo.setDays(DateUtil.incomeDays(vo.getEnd_date(),vo.getStart_date()));
					}
					
					Double voAmount = vo.getAmount();
					if(voAmount == null ||voAmount == 0.0){
						vo.setAmount(product.getMoney());
					}
					
					Double voIncome = vo.getIncome();
					if(voIncome == null || voIncome == 0.0){
						vo.setIncome(income);
					}else{
						vo.setIncome(voIncome + income);
					}
					
					if(i != 0){
						start_dates = vo.getStart_date();
					}
					income_days = DateUtil.incomeDays(vo.getEnd_date(), start_dates);
					
					ProductBuyIncome productBuyIncome = new ProductBuyIncome();
					productBuyIncome.setBuy_id(buy.getId());
					productBuyIncome.setProduct_id(product.getId());
					productBuyIncome.setStart_date(start_dates);
					productBuyIncome.setDays(income_days);
					productBuyIncome.setEnd_date(vo.getEnd_date());
					
			
					if( i == listVo.size() - 1){
						productBuyIncome.setIs_return_amount(1);
						productBuyIncome.setAmount(amount);
					}else{
						productBuyIncome.setIs_return_amount(2);
						
					}
					productBuyIncome.setNum(i+1);
					productBuyIncome.setRemark(product.getIncome() + coupon_interest + "% = 年化率：" + product.getIncome() + "%加息券利率：" + coupon_interest +"%");
					productBuyIncome.setInterest(product.getIncome() + coupon_interest);
					income = amount*(product.getIncome() + coupon_interest)*income_days*0.01/360;
					//操持两位小数
					income = SinaResultUtil.doubleToTwo(income);
					productBuyIncome.setIncome(income);
					
					Double voDailly = vo.getIncome_dailly();
					if(voDailly == null || voDailly == 0.0){
						vo.setIncome_dailly(income);
					}else{
						vo.setIncome(voDailly + income);
					}
					productBuyIncome.setPay_date(vo.getEnd_date());
					productBuyIncome.setStatus(-1);
					productBuyIncome.setRemark(productBuyIncome.getRemark() + "#######由于付息模板丢失,重新生成时间为" + strDate);
					productBuyIncome.setUser_id(user.getId());
					productBuyIncome.setCreate_time(new Date());
					productBuyIncomeService.save(productBuyIncome);
					
					
					//更新一下    项目的资金
					
					//productIncomeService.updateProductIncomeById(vo);
					
				}
			}else if(product.getReturn_method() == 1){
				
				ProductBuyIncome productBuyIncome = new ProductBuyIncome();
				productBuyIncome.setBuy_id(buy.getId());
				productBuyIncome.setProduct_id(product.getId());
				productBuyIncome.setStart_date(start_dates);
				productBuyIncome.setDays(income_days);
				productBuyIncome.setNum(1);
				productBuyIncome.setUser_id(user.getId());
				productBuyIncome.setIs_return_amount(1);
				productBuyIncome.setAmount(amount);
				productBuyIncome.setRemark(product.getIncome() + coupon_interest + "% = 年化率：" + product.getIncome() + "%加息券利率：" + coupon_interest +"%");
				productBuyIncome.setInterest(product.getIncome() + coupon_interest);
				income = amount*(product.getIncome() + coupon_interest)*income_days*0.01/360;
				//操持两位小数
				income = SinaResultUtil.doubleToTwo(income);
				productBuyIncome.setIncome(income);
				productBuyIncome.setPay_date(product.getWill_end_date());
				productBuyIncome.setStatus(-1);
				productBuyIncome.setCreate_time(new Date());
				productBuyIncomeService.save(productBuyIncome);//保存错误
				
				//getProductDynamic(debt_code,product.getProduct_code(),1);
				ProductIncomeVo vo = listVo.get(0);
				Double voAmount = vo.getAmount();
				if(voAmount == null ||voAmount == 0.0){
					vo.setAmount(product.getMoney());
				}
				
				Double voIncome = vo.getIncome();
				if(voIncome == null || voIncome == 0.0){
					vo.setIncome(income);
				}else{
					vo.setIncome(voIncome + income);
				}
				
				Double voDailly = vo.getIncome_dailly();
				if(voDailly == null || voDailly == 0.0){
					vo.setIncome_dailly(income);
				}else{
					vo.setIncome(voDailly + income);
				}
				
			}
			
		}
		
		
		sr.setContent("重新生成完成");
		sr.setReturnCase(false);
		sr.setStatus(101);
		return sr;
	}
	
	//短信接口
	@RequestMapping(value="/proofsms",method=RequestMethod.GET)
	public @ResponseBody SmsReturn ProofSmsTest(HttpServletRequest request,
            HttpServletResponse response) throws ParseException, InterruptedException {
		SmsReturn sr = new SmsReturn();
		
		
		String uri = "http://send.18sms.com/msg/HttpBatchSendSM";//应用地址
		String account = "63j24m";//示远账号
		String pswd = "83y0bE6o";//示远密码
		String mobiles = "13735196677";//手机号码，多个号码使用","分割
		String content = "王先生，经过平台信用测试。已经欠费-2500000.00元";//短信内容，注意内容中的逗号请使用中文状态下的逗号
		boolean needstatus = true;//是否需要状态报告，需要true，不需要false
		String product = "";//产品ID(不用填写)
		String extno = "";//扩展码(不用填写)
		try {
			String returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
			System.out.println(returnString);
			//TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			//TODO 处理异常
			e.printStackTrace();
		}
		
		return sr;
	}
	
	
	
	//校对数据
	@RequestMapping(value="/proofread",method=RequestMethod.GET)
	public @ResponseBody SmsReturn Proofread(HttpServletRequest request,
            HttpServletResponse response) throws ParseException, InterruptedException {
		SmsReturn sr = new SmsReturn();
		Date date = new Date();
		String strDate = DateUtil.formatDate(DateUtil.addDate(date,-1));//加一天的值
		//2017-12-07
		Date begin = DateUtil.addDate(date, -87);
		String strBegin = DateUtil.formatDate(begin);
		
		Map<Long,String> mapO = new HashMap<Long,String>();
		while(!strBegin.equalsIgnoreCase(strDate)){
			//返回用户    
		    Map<String,Object> map = new HashMap<String,Object>();
			map.put("page_name","selectProductBuyIncomeEnd");
			map.put("end_dates",strBegin);
			
		    //今天要回款buyId
		    List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);
		    
		    for(int i = 0;i<listBuyI.size();i++){
				ProductBuyIncome buyIncomeV = listBuyI.get(i);
				if(buyIncomeV.getStatus() == 1){
					mapO.put(buyIncomeV.getUser_id(), buyIncomeV.getUser_id().toString());
				}
				
			}
		    
		    begin = DateUtil.addDate(begin, 1);
			strBegin = DateUtil.formatDate(begin);
		}
		//项目
	    Set<Long> set = mapO.keySet();
	    List<Long> valueList = new ArrayList<Long>(set);
	    
	    for(int i = 0;i<valueList.size();i++){
	    	
	    	Long uid = valueList.get(i);
	    	
	    	//uid = 84205L;
	    	Map<String,Object> mapUser = new HashMap<String,Object>();
	    	mapUser.put("page_name","selectProductBuyIncomeEnd");
	    	mapUser.put("user_id",uid);
	    	mapUser.put("status", -1);
	    	
	    	//未回款的金额
	    	List<ProductBuyIncome> listBuy = productBuyIncomeService.selectForList(mapUser);
	 	   
	    	Double tempIncome = 0.0;
	    	Double tempAmount = 0.0;
	 	    for(int j = 0;j<listBuy.size();j++){
	 			ProductBuyIncome incomeV = listBuy.get(j);
	 			if(incomeV.getIs_return_amount() == 1){
	 				tempAmount +=incomeV.getAmount();
	 			}
	 			
	 			tempIncome +=incomeV.getIncome();
	 	    }
	 	    
	 	    UserFundStat userFundStat = new UserFundStat();
			userFundStat.setUser_id(uid);
			
			UserFundStat stat = userFundStatService.selectForObjectById(userFundStat);
			
			stat.setPend_return(SinaResultUtil.doubleToTwo(tempAmount));
			stat.setPend_income(SinaResultUtil.doubleToTwo(tempIncome));
			//stat.setIncomed(SinaResultUtil.doubleToTwo(tempIncome));
			
			userFundStatService.updateUserFundStatById(stat);
			log.info("id : " + uid + "********pengAmount:" + SinaResultUtil.doubleToTwoToString(tempAmount)+ "****tempIncome" + SinaResultUtil.doubleToTwoToString(tempIncome));
			Thread.sleep(100);
			
	    }
		
		return sr;
	}
	
	
	
	//我的页面
	@RequestMapping(value="/mypage",method=RequestMethod.GET)
	public @ResponseBody SmsReturn myPage(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		List<FundsVo> userFunds = fundsService.selectFundListNoRes(user.getId());
		for (FundsVo one : userFunds) {
			//调用新浪接口，查询充值/提现结果
			String res = "";
			if (11 == one.getOperate_type()) {
				res = SinaUtil.queryHostDeposit(one.getUser_id(),one.getTrade_no(),RequestUtils.getIp(request));
			}
			if (21 == one.getOperate_type()) {
				res = SinaUtil.queryHostWithdraw(one.getUser_id(),one.getTrade_no(),RequestUtils.getIp(request));
			}
			if (StrUtils.emptyJudge(res) && res.indexOf("APPLY_SUCCESS")>0 && res.indexOf("^SUCCESS")>0) {//明确成功
				one.setStatus(1);
			}
			if (StrUtils.emptyJudge(res) && res.indexOf("APPLY_SUCCESS")>0 && res.indexOf("^FAILED")>0) {//明确失败
				one.setStatus(5);
			}
			fundsService.updateFundsById(one);
		}
		SmsReturn sr = new SmsReturn();
		if(user == null){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			//model.addAttribute("sr", sr);
			return sr;
		}else{
			
			if(!sign.equalsIgnoreCase(md5Sign)){
				sr.setContent("无效数据");
				sr.setReturnCase(true);
				sr.setStatus(100);
				
				return sr;
			}
			
			CoinStream userGift = new CoinStream();
			userGift.setUser_id(user.getId());
			
			UserFundStat userFundStat = new UserFundStat();
			userFundStat.setUser_id(user.getId());
			
			UserFundStat stat = userFundStatService.selectForObjectById(userFundStat);
			
			ProductBuy buy = new ProductBuy();
			buy.setUser_id(user.getId());
			
			List<ProductBuyVo> listBuy = productBuyService.selectForList(buy);
			int investment = listBuy.size();
			Map<String,String> myPage = new HashMap<String,String>();
			String calendar = "";
			
			String strDate = DateUtil.formatDate(new Date());
			//返回用户    
		    Map<String,Object> map = new HashMap<String,Object>();
			map.put("page_name","selectProductBuyIncomeEnd");
			map.put("end_dates",strDate);
			map.put("user_id", user.getId());
		    
		    List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);
		    if(listBuyI.size()>0){
		    	calendar = "今日有回款";
		    }
			
			News news = new News();
			news.setUser_id(user.getId());
			news.setIs_read(1);
			List<NewsVo> listNews = newsService.selectForList(news);
			UserGift userGift1 = new UserGift();
			userGift1.setUser_id(user.getId());
			userGift1.setStatus(2);
			
			List<UserGiftVo> listGift = userGiftService.selectForList(userGift1);
			
			Double gift_totle = 0.0;
			
			for(int i = 0;i<listGift.size();i++){
				UserGiftVo vo = listGift.get(i);
				if(vo == null){
					continue;
				}
				if(vo.getAmount() == null){
					vo.setAmount(0.0);
				}
				gift_totle += vo.getAmount();
			}
			
			CoinStream stream = new CoinStream();
			stream.setUser_id(user.getId());
			//stream.setStatus(1);
			stream.setType(21);
			List<CoinStreamVo> listVo = coinStreamService.selectForList(stream);
			Double coin_total = 0.0;
			for(int i = 0;i<listVo.size();i++){
				if(listVo.get(i).getAmount() != null)
					coin_total += listVo.get(i).getAmount();
			}
			Double incomed = stat.getIncomed() + gift_totle + coin_total + stat.getPend_income();
			myPage.put("incomed", SinaResultUtil.doubleToTwoToString(incomed));//累计收益
			Double pend_amount = stat.getPend_income() + stat.getPend_return();
			myPage.put("pend_amount", SinaResultUtil.doubleToTwoToString(pend_amount));//待收金额
			myPage.put("pend_income", SinaResultUtil.doubleToTwoToString(stat.getPend_income()));//待收益
			
			myPage.put("balance", SinaResultUtil.doubleToTwoToString(stat.getBalance()));//账户余额
			myPage.put("investment", investment + "");//我的投资
			myPage.put("calendar",calendar);//回款日历
			myPage.put("giftCoupon",getGiftCoupon(user) + "");//红包卡券
			
			Double total_assets = stat.getPend_return() + stat.getBalance() + stat.getPend_income() /*+ stat.getLocked_money()*/;
			myPage.put("total_assets", SinaResultUtil.doubleToTwoToString(total_assets));//总额
			myPage.put("invitfriends","获好友投资额2‰正经值");//邀请好友
			myPage.put("assessmen","进取型");//风险评估
			myPage.put("news", listNews.size() + "");//消息条数
			myPage.put("hf_balance",stat.getHf_balance() + "");//付汇余额
			
			
			//System.out.println(user_name + myPage);
			
			sr.setContent(myPage);
			sr.setReturnCase(true);
			sr.setStatus(100);
		}
		
		
		
		
		return sr;
	}
	
	//优惠券
	@RequestMapping(value="/coupons",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getCoupons(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(user == null){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
			
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
		}
		
		
		UserGift gift = new UserGift();
		gift.setUser_id(user.getId());
		gift.setStatus(1);
		
		List<UserGiftVo> listGift = userGiftService.selectForList(gift);//红包
		
		UserCoupon coupon = new UserCoupon();
		coupon.setUser_id(user.getId());
		coupon.setStatus(1);
		
		List<UserCouponVo> listCoupon = userCouponService.selectForList(coupon);//加息券
		
		CoinStream coinStream = new CoinStream();
		
		coinStream.setUser_id(user.getId());
		coinStream.setAction(1);
		
		List<CoinStreamVo> listStream = coinStreamService.selectForList(coinStream);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("coin", listStream);//正经值
		map.put("gift", listGift);//红包
		map.put("coupon", listCoupon);//加息券
		
		
		
		sr.setContent(map);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return null;
	}
	
	//交易详情
	@RequestMapping(value="/transaction/details",method=RequestMethod.GET)
	public @ResponseBody SmsReturn transactionDetails(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String operate_type = request.getParameter("operate_type");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(user == null){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
			
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
		}
		
		Funds funds = new Funds();
		funds.setUser_id(user.getId());
		
		if(operate_type != null && !operate_type.isEmpty()){
			funds.setOperate_type(Integer.parseInt(operate_type));
		}
		
		List<FundsVo> listFund = fundsService.selectForList(funds);
		
		
		sr.setMapContent(listFund);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	//回款日历
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/back/calendar",method=RequestMethod.POST)
	public @ResponseBody SmsReturn backCalendar(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
	
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		
		String time_month = request.getParameter("time_month");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		SmsReturn sr = new SmsReturn();
		//截取月份
		
		/*String[] time_months = time_month.split("-");
		
		if(reg_source.equalsIgnoreCase("2")){
			if(Integer.parseInt(time_months[1])>0&&Integer.parseInt(time_months[1])<10){
				time_month = time_months[0] + "-0" + time_months[1];
			}
		}*/
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = sdf.parse(time_month);
		
		Date monthBegin = DateUtil.monthBegin(date);
		
		Date monthEnd = DateUtil.monthEnd(date);
		
		String start = DateUtil.formatDateTimeS(monthBegin);  
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user.getId());
		map.put("returnDate",time_month);
		
		
		Map hashMap = new HashMap();
		hashMap.put("beginDate", monthBegin);
		hashMap.put("endDate", monthEnd);
		hashMap.put("user_id", user.getId());
		List userMonthBuyList = productBuyService.selectUserMonthBuyList(hashMap); //获取本月的回款日历
		List userMonthIncomeList = productIncomeService.selectUserMonthIncomeList(hashMap); //获取本月的利息回款日历
		
		List<ProductBuyIncomeVo> list = (List<ProductBuyIncomeVo>)productBuyIncomeDao.selectForList("selectPayReturn",map);
		
		Map<String,Object>  mapH = new HashMap<String,Object>();
		
		Map<String,Object>  mapO = new HashMap<String,Object>();
		
		List<Object> listOb = new ArrayList<Object>();
		Double month_amount = 0.0;//应收本
		Double month_income = 0.0;//应收息
		Double month_total = 0.0;//应收总
		
		Double month_un_amount = 0.0;//待收本
		Double month_un_income = 0.0;//待收息
		Double month_un_total = 0.0;//待收总
		for(int i = 0;i<list.size();i++){
			ProductBuyIncome buyIncome = list.get(i);
			
			if(buyIncome.getStatus() == 1 || buyIncome.getStatus() == -1){
				
				//if(buyIncome.getStatus() == 1){
				if(buyIncome.getAmount() != null){
					if(buyIncome.getIs_return_amount() == 1){
						month_total += buyIncome.getAmount();
						month_amount += buyIncome.getAmount();
					}
					
				}
				
				month_income += buyIncome.getIncome();
				month_total += buyIncome.getIncome();
				//}
				
				
				if(buyIncome.getStatus() == -1){
					if(buyIncome.getAmount() != null){
						if(buyIncome.getIs_return_amount() == 1){
							month_un_total += buyIncome.getAmount();
							month_un_amount += buyIncome.getAmount();
						}
						
					}
					
					month_un_income += buyIncome.getIncome();
					month_un_total += buyIncome.getIncome();
				}
				
				String strDay = DateUtil.toShortString(buyIncome.getPay_date());
				
				List<ProductBuyIncome> listTemp = (List<ProductBuyIncome>) mapO.get(strDay);
				
				if(listTemp == null){
					List<ProductBuyIncome> listBuy = new ArrayList<ProductBuyIncome>();
					listBuy.add(buyIncome);
					mapO.put(strDay, listBuy);
				}else{
					listTemp.add(buyIncome);
					mapO.put(strDay, listTemp);
				}
			}
			
		}
		
		// 
		for (Map.Entry<String, Object> entry : mapO.entrySet()) {  
			  
			Map<String,Object> month = new HashMap<String,Object>();
		  //  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		    month.put("month", entry.getKey());
		    
		    List<ProductBuyIncome> listBuy = (List<ProductBuyIncome>) entry.getValue();
		    
		    List<CalendarNode> listNode = new ArrayList<CalendarNode>();
		    int status = 0;
		    for(int i = 0;i<listBuy.size();i++){
		    	ProductBuyIncome buyIn = listBuy.get(i);
		    	
		    	Product product = new Product();
		    	
		    	product.setId(buyIn.getProduct_id());
		    	
		    	List<ProductVo> listVo = productService.selectForList(product);
		    	if(listVo.size() == 0)
		    		continue ;
		    	status = buyIn.getStatus();
		    	CalendarNode node = new CalendarNode();
		    	Double total = 0.0;
		    	if(buyIn.getIs_return_amount() == 1){
		    		total += buyIn.getAmount();
		    		node.setAmount(total + "");
		    	}
		    	node.setName(listVo.get(0).getProduct_code());
		   
		    	
		    	node.setDates(DateUtil.formatDate(buyIn.getCreate_time()) + "  购买");
		    	node.setIncome(buyIn.getIncome() + "");
		    	total +=buyIn.getIncome();
		    	node.setTotal(total + "");
		    	listNode.add(node);	
		    }
		    month.put("month_size", listBuy.size() + "");
		    month.put("month_list", listNode);
		    if(status == -1){
		    	 month.put("month_stat", "待回款");
		    }else if(status == 1){
		    	month.put("month_stat", "已回款");
		    }
		    
		    listOb.add(month);
		}  
		
		
		mapH.put("dataList", listOb);
		mapH.put("month_amount", month_amount + "");
		mapH.put("month_income", month_income + "");
		mapH.put("month_total", month_total + "");
		
		
		mapH.put("month_un_amount", month_un_amount + "");
		mapH.put("month_un_income", month_un_income + "");
		mapH.put("month_un_total", month_un_total + "");
		sr.setMapContent(mapH);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	//未支付的订单
	@RequestMapping(value="/roll",method=RequestMethod.POST)
	public @ResponseBody SmsReturn roll(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String type = request.getParameter("type");
		String sign = request.getParameter("sign");
		String amount = request.getParameter("amount");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		
		
		List<InvestmentOrder> list = ServiceChargeUtil.getInstance().addLatest(user_name, amount,type);
		
		
		sr.setMapContent(list);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	//获取滚动列表
	@RequestMapping(value="/roll/details",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getRoll(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		
		
		List<InvestmentOrder> list = ServiceChargeUtil.getInstance().getList();
		
		
		sr.setMapContent(list);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	//风险测评
	@RequestMapping(value="/risk",method=RequestMethod.POST)
	public @ResponseBody SmsReturn risk(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String risk = request.getParameter("risk");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + risk +
				Constants.API_KEY);
		SmsReturn sr = new SmsReturn();
		
		
		String[] list = risk.split(",");
		
		
		int a = 0;
		int b = 0;
		int c = 0;
		for(int i = 0;i<7;i++){
			String ch = list[i];
			if(ch.equalsIgnoreCase("A")){
				a++;
			}else if(ch.equalsIgnoreCase("B")){
				b++;
			}else if(ch.equalsIgnoreCase("C")){
				c++;
			}
		}
		
		String strText = "";
		
		if(c>=4){
			strText = "你的投资态度相对谨小慎微，厌恶风险，对投资回报要求不高，相对合理的投资年华收益率为9%。";
		}else if(b>=4){
			strText = "你的投资态度相对四平八稳，追求稳健的投资回报，同时对风险具有一定的承受能力，相对合理的投资年华收益11%。";
		}else if(c>=4){
			strText = "您是投资的冒险家，追求较高的投资收益同时对风险的承受力较强，相对合理的投资年华收益率13%。";
		}
		
		
		sr.setContent(strText);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	//未支付的订单
	@RequestMapping(value="/queryWithdraw",method=RequestMethod.POST)
	public @ResponseBody SmsReturn queryWithdraw(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String out_trade_no = request.getParameter("out_trade_no");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		/*String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);*/
		
		
		SmsReturn sr = new SmsReturn();
		/*if(user == null){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
			
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}*/
		String re = SinaUtil.queryHostDeposit(user.getId(), out_trade_no, user.getReg_ip());
		ProductBuy productBuy = new ProductBuy();
		productBuy.setStatus(-1);
		String result = SinaUtil.queryHostWithdraw(user.getId(), out_trade_no, user.getReg_ip());
		
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			//return "";
			sr.setMapContent("失败");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		Map dataMap =  (Map) resultMap.get("data");
		
		String trade_list = (String) dataMap.get("withdraw_list");
		System.out.println(trade_list);
		if(trade_list.indexOf("SUCCESS")>0){
			List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
			
			sr.setMapContent(listBuy);
			sr.setReturnCase(true);
			sr.setStatus(100);
		}else{
			List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
			
			sr.setMapContent("失败");
			sr.setReturnCase(false);
			sr.setStatus(102);
		}
		
		
		return sr;
	}
	
	
	//未支付的订单
	@RequestMapping(value="/unpay",method=RequestMethod.GET)
	public @ResponseBody SmsReturn unpay(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(user == null){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
			
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		ProductBuy productBuy = new ProductBuy();
		productBuy.setStatus(-1);
		
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
		
		sr.setMapContent(listBuy);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	
	@Autowired(required = true)
	public void setFundsService(IFundsService fundsService) {
		this.fundsService = fundsService;
	}
	
	@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}
	
	@Autowired(required = true)
	public void setWithdrawCouponsService(IWithdrawCouponsService withdrawCouponsService) {
		this.withdrawCouponsService = withdrawCouponsService;
	}
	
	@Autowired(required = true)
	public void setUserCouponsService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}
	
	@Autowired(required = true)
	public void setCoinStreamService(ICoinStreamService coinStreamService) {
		this.coinStreamService = coinStreamService;
	}
	
	@Autowired(required = true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	
	@Autowired(required = true)
	public void setProductIncomeService(IProductIncomeService productIncomeService) {
		this.productIncomeService = productIncomeService;
	}
	
	@Autowired(required = true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	
	@Autowired(required = true)
	public  void setProductBuyIncomeDao(
			@Qualifier("productBuyIncomeDao")IProductBuyIncomeDao productBuyIncomeDao) {
		this.productBuyIncomeDao = productBuyIncomeDao;
	}
	
	@Autowired(required = true)
	public void setProductBuyIncomeService(IProductBuyIncomeService productBuyIncomeService) {
		this.productBuyIncomeService = productBuyIncomeService;
	}
}
