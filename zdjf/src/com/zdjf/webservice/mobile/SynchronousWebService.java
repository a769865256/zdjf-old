package com.zdjf.webservice.mobile;

import com.zdjf.components.events.AuditEvent;
import com.zdjf.components.events.FundEvent;
import com.zdjf.components.events.UserBenefitsEvent;
import com.zdjf.components.mobile.ServiceChargeUtil;
import com.zdjf.components.mobile.SinaResultUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.dao.product.IProductBuyDao;
import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.CoinStreamVo;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.domain.fund.Coupon;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.GiftVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.fund.Withdraw;
import com.zdjf.domain.fund.WithdrawVo;
import com.zdjf.domain.notify.NotifyLog;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductBuyVo;
import com.zdjf.domain.product.ProductIncome;
import com.zdjf.domain.product.ProductIncomeVo;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.service.front.IFrontService;
import com.zdjf.service.fund.ICoinService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.fund.IWithdrawService;
import com.zdjf.service.notify.INotifyService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductDynamicService;
import com.zdjf.service.product.IProductIncomeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CommonUtils;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.PhoneFormatCheckUtils;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.util.SignUtil;
import com.zdjf.util.StrUtils;
import com.zdjf.util.Tools;
import com.zdjf.util.UrlConstant;
import com.zdjf.webservice.express.SmsReturn;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sina")
@PropertySource(value= "classpath:project.properties")
public class SynchronousWebService {
	Log log = LogFactory.getLog(this.getClass());
	private IUserService userService;

	private IUserFundStatService userFundStatService;

	private IWithdrawService  withdrawService;

	private IUserGiftService userGiftService;

	private IGiftService giftService;

	private ICoinService coinService;

	private ICoinStreamService coinStreamService;

	private IFundsService fundsService;

	private IProductBuyService productBuyService;

	private IProductService productService;

	private IProductDynamicService productDynamicService;

	private ICouponService couponService;

	private IUserCouponService userCouponService;

	private IProductIncomeService productIncomeService;

	private IProductBuyIncomeService productBuyIncomeService;

	private ILenderService lenderService;

	private INotifyService notifyService;

	private ApplicationContext applicationContext;

	private IFrontService frontService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor; 

	@Autowired
	private IUserBankService userBankService;

	private Map tradeHashMap = new HashMap();

	@Value("${activity2.start.date}")
	private String newYearActivityStartDate;

	@Value("${activity2.end.date}")
	private String newYearActivityEndDate;

	@Value("${gift2.name}")
	private String newYearActivityGiftName;

	//女神节活动
	@Value("${activity4.start.date}")
	private String goddessActivityStartDate;

	@Value("${activity4.end.date}")
	private String getGoddessActivityEndDate;

	@Value("${gift4.name.second}")
	private String secGoddessGiftName;

	//愚人节活动参数
	@Value("${activity5.start.date}")
	private String foolsActivityStartDate;

	@Value("${activity5.end.date}")
	private String foolsActivityEndDate;

	@Value("${gift5.name.first}")
	private String foolsGift;


	/**
	 * 充值返回（新浪收银台发起） 2018-1-26 add
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deposit",method=RequestMethod.GET)
	public String setDeposit(HttpServletRequest request, Model model){

		//获取请求参数
		String uid = request.getParameter("uid");
		String trade_no = request.getParameter("out_trade_no");
		String reg_source = request.getParameter("reg_source");
		String ip = RequestUtils.getIp(request);

		//查询充值记录
		Funds fun = new Funds();
		fun.setTrade_no(trade_no);
		fun.setUser_id(Long.valueOf(uid));
		List<FundsVo> funs = fundsService.selectForList(fun);
		if (funs == null || funs.size() == 0 || funs.size() > 1) {
			model.addAttribute("resStatus",0);//失败
			model.addAttribute("resMsg","系统异常，请稍后再试！");
			if("4".equals(reg_source)||"3".equals(reg_source)||"2".equals(reg_source)){//手机端
				return "redirect:/static/zdjf_app/page/recharge/r_fail.html?reg_source=" + reg_source;
			}else{
				return "front/charge_back";
			}
		}
		FundsVo oneFun = funs.get(0);
		model.addAttribute("amt",RoofNumberUtils.formatAmt(oneFun.getAmount()));//充值金额
		//调用银行存管接口查询充值结果
		String result = SinaUtil.queryHostDeposit(Long.valueOf(uid), trade_no,ip);
		if (!StrUtils.emptyJudge(result)) {
			model.addAttribute("resStatus",0);//失败
			model.addAttribute("resMsg","网络异常，请稍后再试！");
			if("4".equals(reg_source)||"3".equals(reg_source)||"2".equals(reg_source)){//手机端
				return "redirect:/static/zdjf_app/page/recharge/r_fail.html?reg_source=" + reg_source;
			}else{
				return "front/charge_back";
			}
		}
		Map resultMap = JsonUtil.jsonToMap(result);
		Object strCode = resultMap.get("response_code");
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			model.addAttribute("resStatus",0);//失败
			model.addAttribute("resMsg",resultMap.get("response_msg").toString());
			if("4".equals(reg_source)||"3".equals(reg_source)||"2".equals(reg_source)){//手机端
				return "redirect:/static/zdjf_app/page/recharge/r_fail.html?reg_source=" + reg_source;
			}else{
				return "front/charge_back";
			}
		}
		Map dataMap =  (Map) resultMap.get("data");
		Object list =  dataMap.get("deposit_list");
		String content = list.toString();
		if(content.indexOf("SUCCESS")>0){//充值成功
			//更新用户统计信息
			UserFundStat userFund = new UserFundStat();
			userFund.setUser_id(Long.valueOf(uid));
			UserFundStat voF = userFundStatService.selectForObjectById(userFund);
			Double balance = voF.getBalance();
			Double fAmount = oneFun.getAmount();
			if (oneFun.getStatus() != 1) {
				balance += fAmount;
				voF.setBalance(balance);
				voF.setRecharged(voF.getRecharged()+fAmount);
				//更新充值记录
				oneFun.setStatus(1);
				fundsService.updateFundsById(oneFun);
				userFundStatService.updateUserFundStatById(voF);
			}
			model.addAttribute("user_balance",RoofNumberUtils.formatAmt(balance));//网页充值成功页面显示充值后余额
			model.addAttribute("trade_time",oneFun.getCreate_time());
			model.addAttribute("resStatus",1);//成功
			model.addAttribute("resMsg","账户充值成功");
			if("4".equals(reg_source)||"3".equals(reg_source)||"2".equals(reg_source)){//手机端
				return "redirect:/static/zdjf_app/page/recharge/r_ok.html?reg_source=" + reg_source;
			}else{
				return "front/charge_back";
			}
		}
		if(content.indexOf("FAILED")>0){//充值失败
			//更新充值信息
			oneFun.setStatus(5);
			fundsService.updateFundsById(oneFun);
			model.addAttribute("trade_time",oneFun.getCreate_time());
			model.addAttribute("resStatus",0);//失败
			if("4".equals(reg_source)||"3".equals(reg_source)||"2".equals(reg_source)){//手机端
				return "redirect:/static/zdjf_app/page/recharge/r_fail.html?reg_source=" + reg_source;
			}else{
				return "front/charge_back";
			}
		}
		if(content.indexOf("PROCESSING")>0){//充值处理中
			//更新充值信息
			oneFun.setStatus(3);//银行处理中
			fundsService.updateFundsById(oneFun);
			model.addAttribute("trade_time",oneFun.getCreate_time());
			model.addAttribute("resStatus",2);//银行处理中
			model.addAttribute("resMsg","银行处理中");
			if("4".equals(reg_source)||"3".equals(reg_source)||"2".equals(reg_source)){//手机端
				return "redirect:/static/zdjf_app/page/recharge/r_fail.html?reg_source=" + reg_source;
			}else{
				return "front/charge_back";
			}
		}
		return "";
	}

	//	//充值成功
	//	@RequestMapping(value="/deposit",method=RequestMethod.GET)
	//	public String setDeposit(HttpServletRequest request,
	//            HttpServletResponse response,Model model){
	//
	//		SmsReturn sr = new SmsReturn();
	//		String uid = request.getParameter("uid");
	//		String trade_no = request.getParameter("out_trade_no");
	//		String amount = request.getParameter("amount");
	//		String ip = request.getRemoteAddr();
	//		Funds fun = new Funds();
	//		fun.setTrade_no(trade_no);
	//		fun.setStatus(3);//待审核
	//		if(uid!=null && !"".equals(uid)){
	//			fun.setUser_id(Long.valueOf(uid));;
	//		}
	//		List<FundsVo> listFun = fundsService.selectForList(fun);
	//		if(listFun==null || listFun.size()==0){
	//			//2018-1-15 add 确定该笔充值已成功 start
	//			Funds fun2 = new Funds();
	//			fun2.setTrade_no(trade_no);
	//			fun2.setStatus(1);//充值成功
	//			if(uid!=null && !"".equals(uid)){
	//				fun2.setUser_id(Long.valueOf(uid));;
	//			}
	//			List<FundsVo> listFun2 = fundsService.selectForList(fun2);
	//			if (listFun2 != null && listFun2.size()>0) {
	//				UserFundStat ufs_query = new UserFundStat();
	//				ufs_query.setUser_id(Long.valueOf(uid));
	//				UserFundStat ufs = userFundStatService.selectForObjectById(ufs_query);
	//				model.addAttribute("amt",listFun2.get(0).getAmount());//2018-1-15 add 网页充值成功页面显示充值金额
	//				model.addAttribute("user_balance",ufs.getBalance());//2018-1-15 add 网页充值成功页面显示充值后余额
	//				model.addAttribute("trade_time",listFun2.get(0).getCreate_time());
	//				return "front/cz_success";
	//			}
	//			//2018-1-15 add 确定该笔充值已成功 end
	//			return "";
	//		}else{
	//			FundsVo funds = (FundsVo)listFun.get(0);
	//			uid = funds.getUser_id().toString();
	//		}
	//
	//		User us = new User();
	//		us.setId(Long.parseLong(uid));
	//
	//		User user = userService.load(us);
	//
	//		String result = SinaUtil.queryHostDeposit(user.getId(), trade_no,ip);
	//
	//		System.out.println(result);
	//
	//		Map resultMap = JsonUtil.jsonToMap(result);
	//		Object strCode = resultMap.get("response_code");
	//
	//		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
	//
	//
	//			return resultMap.get("response_msg").toString();
	//		}
	//
	//		Funds f = new Funds();
	//		f.setTrade_no(trade_no);
	//		f.setUser_id(Long.valueOf(uid));
	//		f.setStatus(3);
	//
	//		List fundsList = fundsService.selectForList(f);
	//		if(fundsList!=null && fundsList.size()>0){
	//			Funds fund = (Funds)fundsList.get(0);
	//			fund.setStatus(1);
	//			//更新用户统计信息
	//			UserFundStat userFund = new UserFundStat();
	//			userFund.setUser_id(user.getId());
	//			UserFundStat voF = userFundStatService.selectForObjectById(userFund);
	//			Double balance = voF.getBalance();
	//			Double fAmount = fund.getAmount();
	//			model.addAttribute("amt",fAmount);//2018-1-10 add 网页充值成功页面显示充值金额
	//			balance += fAmount;
	//			voF.setBalance(balance);
	//			model.addAttribute("user_balance",balance);//2018-1-10 add 网页充值成功页面显示充值后余额
	//			model.addAttribute("trade_time",((Funds) fundsList.get(0)).getCreate_time());
	//			voF.setRecharged(voF.getRecharged()+fAmount);
	//			//保存
	//			fundsService.updateFundsById(fund);
	//			userFundStatService.updateUserFundStatById(voF);
	//
	//
	//			Map dataMap =  (Map) resultMap.get("data");
	//			Object list =  dataMap.get("deposit_list");
	//
	//			String content = list.toString();
	//
	//			if(content.indexOf("SUCCESS")>0){
	//
	//			}
	//
	//
	//			String domainName = request.getServerName();
	//			if(domainName.equals(UrlConstant.MOBILE_URL)){
	//
	//			}else{
	////				return "front/success";
	//				return "front/cz_success";//2018-1-10 add 账户充值成功页面
	//			}
	//		}
	//
	//
	//		return "front/mywealth/charge";
	//	}



	//提现成功
	@RequestMapping(value="/asy/recharge",method=RequestMethod.POST)
	public int asyRecharge(HttpServletRequest request,
			HttpServletResponse response,Model model){

		String trade_no = request.getParameter("trade_no");
		String stat = request.getParameter("stat");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(trade_no + stat+
				Constants.API_KEY);

		if( null == sign || sign.isEmpty()){
			return 101;
		}

		if(!sign.equalsIgnoreCase(md5Sign)){


			return 102;
		}

		if(stat.equalsIgnoreCase("0"))
			return 102;

		return 101;
	}

	//提现成功  异步
	@RequestMapping(value="/asy/withdraw",method=RequestMethod.POST)
	public String asyWithdraw(HttpServletRequest request,
			HttpServletResponse response,Model model){

		String trade_no = request.getParameter("trade_no");
		String stat = request.getParameter("stat");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(trade_no + stat+
				Constants.API_KEY);

		if( null == sign || sign.isEmpty()){
			return "101";
		}

		if(!sign.equalsIgnoreCase(md5Sign)){


			return "102";
		}

		if(stat.equalsIgnoreCase("0"))
			return "102";

		//提现
		Withdraw withdraw = new Withdraw();
		withdraw.setPp_serial_no(trade_no);
		withdraw.setStatus(2);
		List<WithdrawVo> list = withdrawService.selectForList(withdraw);

		if(list.size() == 1){
			WithdrawVo vo = list.get(0);
			vo.setStatus(3);
			withdrawService.updateWithdrawById(vo);
			User us = new User();
			us.setId(vo.getUser_id());
			User user = userService.load(us);

			/*Funds fun = new Funds();
			fun.setTrade_no(trade_no);
			fun.setOperate_type(21);
			fun.setStatus(3);
			List<FundsVo> listFun = fundsService.selectForList(fun);*/



			Map<String,Object> map=new HashMap<String, Object>();

			//Double cny = StrUtils.convToDouble(amount);

			//更新用户统计账户信息
			UserFundStat userFund = new UserFundStat();
			userFund.setUser_id(user.getId());
			UserFundStat voF = userFundStatService.selectForObjectById(userFund);
			Double balance = voF.getBalance() - vo.getMoney();
			voF.setBalance(balance);

			Double withdrawed = voF.getWithdrawed() + vo.getMoney();
			voF.setWithdrawed(withdrawed);
			userFundStatService.updateUserFundStatById(voF);



			vo.setStatus(1);
			withdrawService.updateWithdrawById(vo);

			Funds fund = new Funds();
			fund.setTrade_no(trade_no);
			fund.setOperate_type(21);
			fund.setStatus(3);
			List<FundsVo> listFund = fundsService.selectForList(fund);
			if(listFund.size()==1)
			{
				FundsVo funds = listFund.get(0);
				funds.setStatus(1);
				funds.setRelation_id(withdraw.getId());
				funds.setBalance(balance);
				fundsService.updateFundsById(funds);
			}
			return "100";
		}

		return "102";
	}



	@RequestMapping(value="/jump",method=RequestMethod.GET)
	public String jump(HttpServletRequest request,
			HttpServletResponse response,Model model){
		String reg_source=RequestUtils.getReqString(request, "reg_source");
		String type=RequestUtils.getReqString(request, "type");
		String uid = RequestUtils.getReqString(request, "uid");
		model.addAttribute("reg_source", reg_source);
		if ("1".equals(reg_source)) {//pc端
			if(type.equals("back")){
				return "front/mywealth/back_paypwd_ok";
			}else if(type.equals("change")){
				return "front/mywealth/mod_paypwd_ok";
			}else if("bindCard".equals(type)){
				User usr = new User();
				usr.setId(Long.valueOf(uid));
				User user = userService.selectForObject(usr);
				//获取用户原状态
				int userStatus = user.getStatus();
				if (1 == user.getReal_name_auth() && user.getStatus() < 2) { //已实名的老用户
					//绑定银行卡正经值奖励
					applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
							userFundStatService,coinStreamService));
				} else if (1 != user.getReal_name_auth() && user.getStatus() < 2) {
					//实名认证正经值奖励
					applicationContext.publishEvent(new AuditEvent(this,user));
					//绑定银行卡正经值奖励
					applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
							userFundStatService,coinStreamService));
				}
				user.setReal_name_auth(1);
				user.setIdcard_auth_time(new Date());
				user.setStatus(3);
				userService.updateForObjectByPhone(user);
				String res = SinaUtil.queryBankCard(user.getId().toString());
				if (!StrUtils.emptyJudge(res) || !(res.startsWith("{") || res.startsWith("["))) {
					log.info("上海银行查询绑卡接口网络异常");
				}
				Map restMap = JsonUtil.jsonToMap(res);
				Object resCode = restMap.get("response_code");
				if(resCode == null || !resCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
					log.info("上海银行查询绑卡接口异常:"+restMap.get("response_msg"));
				}else{//成功
					Map dataMap = (Map)restMap.get("data");
					String cardList = (String) dataMap.get("card_list");
					if (StrUtils.emptyJudge(cardList)) {//绑过卡
						String[] cardInfo = cardList.split("\\^");
						UserBank ub = new UserBank();
						ub.setStatus(1);
						ub.setUser_id(user.getId());
						List<UserBankVo> ubs = userBankService.selectForList(ub);
						if (ubs == null || ubs.size()== 0) {
							ub.setCard_id(cardInfo[0]);
							ub.setType(1);
							ub.setBank_alias(cardInfo[1]);
							ub.setBank_no(cardInfo[2]);
							ub.setCard_user_name(cardInfo[3]);
							ub.setCard_attribute(cardInfo[5]);
							ub.setCreate_time(new Date());
							userBankService.save(ub);
						}
					}
				}
				if (4 == userStatus) {//解绑之后再绑卡
					return "redirect:/toSetBankCard.action";
				} else {//初次开通银行存管
					return "front/psw_success";
				}
			}

		} else {
			if(type.equals("back")){
				return "front/m/back_ok";
			}else if(type.equals("change")){
				return "front/m/change_ok";
			}else if(type.equals("forget")){
				return "front/m/forget_ok";
			}else if(type.equals("bindCard")){
				User usr = new User();
				usr.setId(Long.valueOf(uid));
				User user = userService.selectForObject(usr);
				int userStatus = user.getStatus();
				if (1 == user.getReal_name_auth() && user.getStatus() < 2) { //已实名的老用户
					//绑定银行卡正经值奖励
					applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
							userFundStatService,coinStreamService));
				} else if (1 != user.getReal_name_auth() && user.getStatus() < 2) {
					//实名认证正经值奖励
					applicationContext.publishEvent(new AuditEvent(this,user));
					//绑定银行卡正经值奖励
					applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
							userFundStatService,coinStreamService));
				}
				user.setReal_name_auth(1);
				user.setStatus(3);
				userService.updateForObjectByPhone(user);
				String res = SinaUtil.queryBankCard(user.getId().toString());
				if (!StrUtils.emptyJudge(res) || !(res.startsWith("{") || res.startsWith("["))) {
					log.info("上海银行查询绑卡接口网络异常");
				}
				Map restMap = JsonUtil.jsonToMap(res);
				Object resCode = restMap.get("response_code");
				if(resCode == null || !resCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
					log.info("上海银行查询绑卡接口异常:"+restMap.get("response_msg"));
				}else{//成功
					Map dataMap = (Map)restMap.get("data");
					String cardList = (String) dataMap.get("card_list");
					if (StrUtils.emptyJudge(cardList)) {//绑过卡
						String[] cardInfo = cardList.split("\\^");
						UserBank ub = new UserBank();
						ub.setStatus(1);
						ub.setUser_id(user.getId());
						List<UserBankVo> ubs = userBankService.selectForList(ub);
						if (ubs == null || ubs.size()== 0) {
							ub.setCard_id(cardInfo[0]);
							ub.setType(1);
							ub.setBank_alias(cardInfo[1]);
							ub.setBank_no(cardInfo[2]);
							ub.setCard_attribute(cardInfo[5]);
							ub.setCard_user_name(cardInfo[3]);
							ub.setCreate_time(new Date());
							userBankService.save(ub);
						}
					}
				}
				if (4 == userStatus) {//解绑之后再绑卡
					return "front/m/bind_ok";
				} else {//初次开通银行存管
					return "front/m/de_ok";
				}
			}
		}
		return "";
	}


	//提现成功
	@RequestMapping(value="/withdraw",method=RequestMethod.GET)
	public String setWithdraw(HttpServletRequest request,
			HttpServletResponse response,Model model){

		String phone = request.getParameter("phone");
		String trade_no = request.getParameter("out_trade_no");
		String reg_source=request.getParameter("reg_source");
		//String reg_source = request.getParameter("reg_source");
		User user = userService.selectForObjectByPhone(phone);

		String result = SinaUtil.queryHostWithdraw(user.getId(), trade_no, user.getReg_ip());

		Map resultMap = JsonUtil.jsonToMap(result);

		Object strCode =  resultMap.get("response_code");

		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){

			return "";
		}

		Map dataMap =  (Map) resultMap.get("data");

		String trade_list = (String) dataMap.get("withdraw_list");
		System.out.println(trade_list);
		if(trade_list.indexOf("SUCCESS")>0 || trade_list.indexOf("PROCESSING")>0){

			Withdraw withdraw = new Withdraw();
			withdraw.setPp_serial_no(trade_no);
			withdraw.setStatus(2);
			List<WithdrawVo> list = withdrawService.selectForList(withdraw);

			if(list.size() == 1){
				WithdrawVo vo = list.get(0);
				vo.setStatus(3);
				withdrawService.updateWithdrawById(vo);

				Funds fun = new Funds();
				fun.setTrade_no(trade_no);
				fun.setOperate_type(21);
				fun.setStatus(3);
				List<FundsVo> listFun = fundsService.selectForList(fun);
				if(listFun==null || listFun.size()!=1)
				{
					return "已完成";
				}

				String amount = request.getParameter("amount");
				String fee_amt = request.getParameter("fee_amt");


				Map<String,Object> map=new HashMap<String, Object>();

				Double cny = StrUtils.convToDouble(amount);

				//更新用户统计账户信息
				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(user.getId());
				UserFundStat voF = userFundStatService.selectForObjectById(userFund);
				Double balance = voF.getBalance() - cny;
				voF.setBalance(balance);

				Double withdrawed = voF.getWithdrawed() + cny;
				voF.setWithdrawed(withdrawed);
				userFundStatService.updateUserFundStatById(voF);


				Withdraw withdraw1 = new Withdraw();
				withdraw1.setPp_serial_no(trade_no);

				List<WithdrawVo> list1 = withdrawService.selectForList(withdraw1);

				if(list1.size() == 1){
					vo.setStatus(1);
					withdrawService.updateWithdrawById(vo);
				}

				//提现 异步
				/*Withdraw withdraw = new Withdraw();
					withdraw.setUser_id(user.getId());
					withdraw.setMoney(cny);
					withdraw.setReal_name(user.getReal_name());
					withdraw.setStatus(1);
					withdraw.setPp_serial_no(trade_no);
					withdraw.setFee_amt(fee_amt);
					withdraw.setCreate_time(new Date());
					if(reg_source!=null && !"".equals(reg_source)){
						withdraw.setReq_source(Integer.parseInt(reg_source));
					}else{
						withdraw.setReq_source(0);
					}

					withdrawService.save(withdraw);*/


				Funds fund = new Funds();
				fund.setTrade_no(trade_no);
				fund.setOperate_type(21);
				fund.setStatus(3);
				List<FundsVo> listFund = fundsService.selectForList(fund);
				if(listFund.size()==1)
				{
					FundsVo funds = listFund.get(0);
					funds.setStatus(1);
					funds.setRelation_id(withdraw.getId());
					funds.setBalance(balance);
					fundsService.updateFundsById(funds);
					map.put("withdraw_time", funds.getCreate_time());//取现时间
					map.put("account_time", DateUtil.addDate(funds.getCreate_time(),1));//到账时间
				}



				map.put("returnMsg", "交易成功");
				map.put("amount", amount);
				map.put("balance", balance);
				map.put("reg_source", reg_source);

				model.addAllAttributes(CommonUtils.packageResult(true, map));
				boolean isMoblie = PhoneFormatCheckUtils.JudgeIsMoblie(request); 

				if(reg_source.equalsIgnoreCase("4")){
//
//					response.setContentType("text/html;charset=UTF-8");
//					//response.setHeader("refresh","3;url=/responseFile/index.jsp");
//					String url = "3;url=" + UrlConstant.API_H5_WITHDRAW_URL;
//					response.setHeader("refresh",url);
					return "redirect:/static/zdjf_app/page/recharge/w_ok.html";
				}

				if(isMoblie){
					return "front/m/withdraw/w_ok";					
				}else{
					return "front/tx_success";
				}
			}


		}

		if(reg_source.equalsIgnoreCase("4")){

			response.setContentType("text/html;charset=UTF-8");
			//response.setHeader("refresh","3;url=/responseFile/index.jsp");
			String url = "3;url=" + UrlConstant.API_H5_WITHDRAW_URL;
			response.setHeader("refresh",url);
			return "";
		}
		return "";
	}


	//支付密码
	@RequestMapping(value="/passwd",method=RequestMethod.GET)
	public String setPayPassword(HttpServletRequest request,
			HttpServletResponse response,Model model){

		String phone = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		User user = userService.selectForObjectByPhone(phone);

		if(user == null){
			log.info("设置支付密码同步回调异常：phone:" + phone + "reg_source:" + reg_source);
			return "";
		}

		String result = SinaUtil.isSetPayPassword(user.getId(), user.getReg_ip());

		log.info("设置支付密码同步回调结果：" + result);

		if(!StrUtils.emptyJudge(result)){
			log.info("查询设置支付密码接口网络异常");
			return "";
		}
		Map resultMap = JsonUtil.jsonToMap(result);
		Object strCode = resultMap.get("response_code");

		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			log.info("查询设置支付密码接口异常：" + strCode!=null?strCode.toString():"");
			return "";
		}

		Map dataMap =  (Map) resultMap.get("data");
		Object is =  dataMap.get("is_set_paypass");

		if(is.toString().equalsIgnoreCase("Y") && user.getStatus() < 3){//设置成功
			user.setStatus(3);
			userService.updateForObjectByPhone(user);
		}

		Map map=new HashMap();
		map.put("reg_source", reg_source);
		model.addAllAttributes(CommonUtils.packageResult(true, map));
		model.addAttribute("reg_source", reg_source);

		if(reg_source.equalsIgnoreCase("4")){

			response.setContentType("text/html;charset=UTF-8");
			//response.setHeader("refresh","3;url=/responseFile/index.jsp");
			String url = "3;url=" + UrlConstant.API_H5_PASSWORD_URL;
			response.setHeader("refresh",url);
			return "";
		}

		//app返回
		if(!"1".equals(reg_source)){
			return "front/m/de_ok";
		}else{
			return "front/psw_success";
		}
	}

	void fullScale(Product product,String trade_no){
		ProductBuy buy = new ProductBuy();
		buy.setProduct_id(product.getId());

		List<ProductBuyVo> buys = productBuyService.selectForList(buy);



		Collections.sort(buys,new Comparator<ProductBuyVo>(){
			public int compare(ProductBuyVo arg0, ProductBuyVo arg1) {
				return arg0.getId().compareTo(arg1.getId());
			}
		});

		int end = buys.size()>3?3:buys.size();

		for(int i = 0;i<end;i++){

			int value = 0;
			if(i == 0)
				value = 5;
			else if(i == 1)
				value = 3;
			else if(i == 2)
				value = 1;

			applicationContext.publishEvent(new FundEvent(this,buys.get(i).getUser_id(),14,value*1.0,
					userFundStatService,coinStreamService,trade_no));
		}


		//收尾											  
		applicationContext.publishEvent(new FundEvent(this,buys.get(buys.size()-1).getUser_id(),14,8*1.0,
				userFundStatService,coinStreamService,trade_no));

		Collections.sort(buys,new Comparator<ProductBuyVo>(){
			public int compare(ProductBuyVo arg0, ProductBuyVo arg1) {
				return arg1.getAmount().compareTo(arg0.getAmount());
			}
		});

		for(int i = 0;i<2;i++){
			Double value = 0.0;
			if(i == 0)
				value = buys.get(i).getAmount()*0.0007;
			else if(i == 1)
				value = buys.get(i).getAmount()*0.0003;

			applicationContext.publishEvent(new FundEvent(this,buys.get(i).getUser_id(),14,value*1.0,
					userFundStatService,coinStreamService,trade_no));
		}

	}

	void AppDownload(Long uid,UserFundStat voF,String trade_no){
		ProductBuy productBuy = new ProductBuy();
		productBuy.setUser_id(uid);
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);

		for(int i = 0;i<listBuy.size();i++){
			ProductBuy buyVo = listBuy.get(i);

			if((buyVo.getReq_source() == 2)&&(buyVo.getStatus() ==1) || (buyVo.getReq_source() == 3)&&(buyVo.getStatus() ==1) ||
					(buyVo.getReq_source() == 2)&&(buyVo.getStatus() ==2) || (buyVo.getReq_source() == 3)&&(buyVo.getStatus() ==2)){
				return ;
			}
		}
		Coin coin = new Coin();

		coin.setCoin_source(8);
		coin.setCoin_name("下载APP");
		coin.setStatus(1);


		List<CoinVo> listCo = coinService.selectForList(coin);
		CoinVo vo = listCo.get(0);
		CoinStream coinStream = new CoinStream();

		coinStream.setUser_id(uid);
		coinStream.setAction(1);
		coinStream.setType(46);//2018-1-17 edit 46
		coinStream.setCreate_time(new Date());
		coinStream.setNum(vo.getCoin_num());
		coinStream.setStatus(1);
		coinStream.setRemark(trade_no);
		coinStream.setStream_id((long)-coin.getCoin_source());
		coinStream.setAmount(vo.getCoin_num()*1.0);
		coinStream.setBalance(voF.getCoin_balance() + vo.getCoin_num());
		coinStreamService.save(coinStream);

		Double coin_balance = voF.getCoin_balance() + vo.getCoin_num();
		Double coin_total = voF.getCoin_total() + vo.getCoin_num();

		voF.setCoin_balance(coin_balance);
		voF.setCoin_total(coin_total);

		userFundStatService.updateUserFundStatById(voF);
	}


	void reCase(Long uid,UserFundStat voF,String trade_no){
		ProductBuy productBuy = new ProductBuy();
		productBuy.setUser_id(uid);
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);

		int times = 0;
		for(int i = 0;i<listBuy.size();i++){
			ProductBuy buyVo = listBuy.get(i);

			if(buyVo.getStatus() == 2){
				times++;
			}

			if((times == 1)&&(buyVo.getStatus() == 1)){
				times++;
			}
		}
		Coin coin = new Coin();
		if(times == 1){
			coin.setCoin_source(6);
			coin.setCoin_name("首次复投");
			coin.setStatus(1);
		}else{
			return ;
		}

		List<CoinVo> listCo = coinService.selectForList(coin);
		CoinVo vo = listCo.get(0);
		CoinStream coinStream = new CoinStream();

		coinStream.setUser_id(uid);
		coinStream.setAction(1);
		coinStream.setType(11);
		coinStream.setCreate_time(new Date());
		coinStream.setNum(vo.getCoin_num());
		coinStream.setStatus(1);
		coinStream.setRemark(trade_no);

		coinStream.setAmount(vo.getCoin_num()*1.0);
		coinStreamService.save(coinStream);

		Double coin_balance = voF.getCoin_balance() + vo.getCoin_num();
		Double coin_total = voF.getCoin_total() + vo.getCoin_num();

		voF.setCoin_balance(coin_balance);
		voF.setCoin_total(coin_total);

		userFundStatService.updateUserFundStatById(voF);
	}


	boolean singleInvestment(Long uid){
		ProductBuy productBuy = new ProductBuy();
		productBuy.setUser_id(uid);
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
		boolean result = true;

		for(int i = 0;i<listBuy.size();i++){
			if(listBuy.get(i).getAmount().compareTo(1000.0)>=0&&listBuy.get(i).getStatus()==1){
				result = false;
				continue ;
			}

		}


		return result ;
	}

	boolean firstCast(Long uid,UserFundStat voF,String trade_no){

		ProductBuy productBuy = new ProductBuy();
		productBuy.setUser_id(uid);
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
		boolean result = true;
		//判断是否首次投标
		for(int i = 0;i<listBuy.size();i++){
			ProductBuy buyVo = listBuy.get(i);
			switch(buyVo.getStatus())
			{
			case 1:
				result = false;
				break;
			case 0:
				result = false;
				break;
			case 2:
				result = false;
				break;
			case -1:
				result = false;
				break;
			case -6:
				result = false;
				break;
			case 7:
				result = false;
				break;
			}

			if(!result)
				break;
		}
		if(result){


			Coin coin = new Coin();
			coin.setCoin_source(5);
			coin.setCoin_name("首次投资");
			coin.setStatus(1);

			List<CoinVo> listCo = coinService.selectForList(coin);
			CoinVo vo = listCo.get(0);
			CoinStream coinStream = new CoinStream();

			coinStream.setUser_id(uid);
			coinStream.setAction(1);
			coinStream.setType(11);
			coinStream.setCreate_time(new Date());
			coinStream.setNum(vo.getCoin_num());
			coinStream.setStatus(1);
			coinStream.setRemark(trade_no);

			coinStream.setAmount(vo.getCoin_num()*1.0);
			coinStream.setBalance(voF.getCoin_balance() + vo.getCoin_num());
			coinStreamService.save(coinStream);

			Double coin_balance = voF.getCoin_balance() + vo.getCoin_num();
			Double coin_total = voF.getCoin_total() + vo.getCoin_num();

			voF.setCoin_balance(coin_balance);
			voF.setCoin_total(coin_total);


			userFundStatService.updateUserFundStatById(voF);

		}

		return result;
	}

	void coinInvest(String phone,Double amount,String trade_no){
		User user = userService.selectForObjectByPhone(phone);
		if(user == null)
			return ;
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);

		Coin coin = new Coin();
		coin.setCoin_source(7);
		coin.setCoin_name("邀请好友");
		coin.setStatus(1);

		List<CoinVo> listCo = coinService.selectForList(coin);
		CoinVo vo = listCo.get(0);
		CoinStream coinStream = new CoinStream();

		coinStream.setUser_id(user.getId());
		coinStream.setAction(1);
		coinStream.setType(12);
		coinStream.setCreate_time(new Date());
		coinStream.setStatus(1);
		coinStream.setRemark(trade_no);
		coinStream.setStream_id((long)-coin.getCoin_source());
		Double invite_amount = amount*vo.getInvite_ratio()*0.01;
		coinStream.setAmount(invite_amount);
		coinStream.setBalance(voF.getCoin_balance() + invite_amount);
		coinStreamService.save(coinStream);

		Double coin_balance = voF.getCoin_balance() + invite_amount;
		Double coin_total = voF.getCoin_total() + invite_amount;

		voF.setCoin_balance(coin_balance);
		voF.setCoin_total(coin_total);

		userFundStatService.updateUserFundStatById(voF);
	}



	void  getTick(String debt_code) throws InterruptedException{
		//资金流动明细  锁定金额
		Funds fund = new Funds();
		fund.setRemark(debt_code);
		fund.setOperate_type(22);
		fund.setStatus(1);
		List<FundsVo> listFunds = fundsService.selectForList(fund);

		for(int i = 0;i<listFunds.size();i++){
			FundsVo fVo = listFunds.get(i);

			Thread.sleep(1);
			if(fVo.getTrade_no() != null){
				String result = SinaUtil.queryHostTrade(fVo.getUser_id(), fVo.getTrade_no(), "", "");

				//返回值
				Map resultMap = JsonUtil.jsonToMap(result);

				Object strCode =  resultMap.get("response_code");

				if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){

					continue ;
				}

				Map dataMap =  (Map) resultMap.get("data");

				String trade_list = (String) dataMap.get("trade_list");

				if(trade_list.indexOf("WAIT_PAY")>0){
					//fundAmount += fVo.getAmount();
				}else if(trade_list.indexOf("TRADE_CLOSED")>0){
					fVo.setStatus(5);
					fundsService.updateFundsById(fVo);

					/*if(status == 9){*/  //付款成功的异常情况
					UserFundStat userFund = new UserFundStat();
					userFund.setUser_id(fVo.getUser_id());
					UserFundStat voF = userFundStatService.selectForObjectById(userFund);
					/*ProductBuy buy = new ProductBuy();
						buy.setId(fVo.getRelation_id());*/
					//根据关联ID  找到buy
					ProductBuy buy = productBuyService.selectForObjectById(fVo.getRelation_id());
					//userFundStatService.selectForList(voF)
					if(buy.getUser_coupon_id() != null){//加息券回退
						UserCoupon userCoupon = new UserCoupon();
						userCoupon.setId(buy.getUser_coupon_id());
						userCoupon.setStatus(2);
						List<UserCouponVo> listCoupon  = userCouponService.selectForList(userCoupon);
						UserCouponVo userVo = listCoupon.get(0);
						userVo.setStatus(1);
						userCouponService.updateUserCouponByCouponId(userVo);
					}

					if(buy.getUser_gift_id() != null){//红包ID
						UserGift userCoupon = new UserGift();
						userCoupon.setId(buy.getUser_gift_id());
						userCoupon.setStatus(2);
						List<UserGiftVo> listCoupon  = userGiftService.selectForList(userCoupon);
						UserGiftVo userVo = listCoupon.get(0);
						userVo.setStatus(1);
						userGiftService.updateUserGiftByGiftId(userVo);

						//代收  20-12-04
						voF.setPend_income(voF.getPend_income() - buy.getIncomed() - buy.getAmount());
						//累计收益
						voF.setIncomed(voF.getIncomed() - buy.getIncomed() - userVo.getAmount());
					}

					if(buy.getCoin() != null){//正经值

						CoinStream stream = new CoinStream();
						stream.setRelation_id(buy.getId());
						//stream.setAmount(buy.getCoin());
						List<CoinStreamVo> listSt = coinStreamService.selectForList(stream);
						for(int j = 0;j<listSt.size();j++){
							CoinStreamVo cVo = listSt.get(0);
							cVo.setStatus(-1);
							coinStreamService.updateCoinStreamById(cVo);

							voF.setCoin_cost(voF.getCoin_cost() - buy.getCoin());
							voF.setCoin_balance(voF.getCoin_balance() + buy.getCoin());
						}


						//代收  20-12-04
						voF.setPend_income(voF.getPend_income() - buy.getIncomed() - buy.getAmount());
						//累计收益
						voF.setIncomed(voF.getIncomed() - buy.getIncomed() - buy.getCoin());
					}

					voF.setBalance(voF.getBalance() + buy.getAct_pay_money());
					voF.setInvest_sum(voF.getInvest_sum() - buy.getAmount());
					voF.setInvest_frequency(voF.getInvest_frequency() - 1);

					userFundStatService.updateUserFundStatById(voF);
					//}

				}else if(trade_list.indexOf("TRADE_FINISHED")>0){
					//成功





				}else if(trade_list.indexOf("TRADE_FAILED")>0){

				}
			}

		}
	}


	void yearUser(User user,Double amount,UserFundStat voF){

		//白银年终奖
		if(amount.compareTo(1000.0)>=0){
			UserGift gift = new UserGift();
			gift.setUser_id(user.getId());
			gift.setRelation_id(56L);
			List<UserGiftVo> listGift = userGiftService.selectForList(gift);

			if(listGift.size() >1){

			}else{
				Gift gift0 = new Gift();

				gift0.setGift_name("白银年终奖");
				gift0.setGift_source(56);
				gift0.setStatus(1);

				//红包 流程
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						gift0,giftService,userGiftService));


				//加息券
				Coupon coupon = new Coupon();
				coupon.setCoupon_source(51);
				coupon.setStatus(1);
				coupon.setCoupon_name("白银年终奖");
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						coupon,couponService,userCouponService));

			}
		}

		//黄金年终奖   所有已投
		Double invest_sum = voF.getInvest_sum() + amount;
		if(invest_sum.compareTo(0.0)>0){

			UserGift gift = new UserGift();
			gift.setUser_id(user.getId());
			gift.setRelation_id(57L);
			List<UserGiftVo> listGift = userGiftService.selectForList(gift);

			if(listGift.size() >1){

			}else{
				Gift gift0 = new Gift();

				gift0.setGift_name("黄金年终奖");
				gift0.setGift_source(57);
				gift0.setStatus(1);

				//红包 流程
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						gift0,giftService,userGiftService));

			}

		}

		// 10万
		if(invest_sum.compareTo(100000.0)>0){

			UserGift gift = new UserGift();
			gift.setUser_id(user.getId());
			gift.setRelation_id(58L);
			List<UserGiftVo> listGift = userGiftService.selectForList(gift);

			if(listGift.size() >1){

			}else{
				Gift gift0 = new Gift();

				gift0.setGift_name("黄金年终奖");
				gift0.setGift_source(58);
				gift0.setStatus(1);

				//红包 流程
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						gift0,giftService,userGiftService));

			}

		}


		// 30万
		if(invest_sum.compareTo(300000.0)>0){

			UserGift gift = new UserGift();
			gift.setUser_id(user.getId());
			gift.setRelation_id(59L);
			List<UserGiftVo> listGift = userGiftService.selectForList(gift);

			if(listGift.size() >1){

			}else{
				Gift gift0 = new Gift();

				gift0.setGift_name("黄金年终奖");
				gift0.setGift_source(59);
				gift0.setStatus(1);

				//红包 流程
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						gift0,giftService,userGiftService));

			}

		}

	}



	public void getProductBuy(User user ,Double amount,String debt_code,Double preferential,
			Long user_coupon_id,Long user_gift_id,String trade_no,String req_source,String coin_num)throws Exception{


		//好友返利
		log.info("投资订单号记录："+trade_no);
		Product pro = new Product();
		pro.setDebt_code(debt_code);
		List<ProductVo> productList = productService.selectForList(pro);
		if(productList.size() != 1){
			System.out.println("存在多个标");
			tradeHashMap.remove(trade_no);
			return ;
		}
		Product product = productList.get(0);
		//付息模板
		ProductIncome productIncome= new ProductIncome();
		productIncome.setProduct_id(product.getId());

		List<ProductIncomeVo> listVo = productIncomeService.selectForList(productIncome);

		if(listVo.size()<1){
			log.info("付息模板丢失："+product.getProduct_name() + "ID：" + product.getId() + "Debt_code" + product.getDebt_code());
			return ;
		}

		Double sale_money = product.getSale_money() + amount;
		Double balance = product.getBalance() - amount;//剩余金额
		if(balance.compareTo(0.0)<0 ||sale_money.compareTo(product.getMoney())>0){
			log.info("超标*********投资订单号记录："+trade_no + "金额：" + amount);
			tradeHashMap.remove(trade_no);
			return ;
		}

		product.setBalance(balance);
		product.setSale_money(sale_money);





		if(user.getInviter_phone() != null&&!user.getInviter_phone().isEmpty())
			coinInvest(user.getInviter_phone(),amount,trade_no);

		//资金统计
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		//下载APP
		if ("2".equals(req_source) || "3".equals(req_source)) {//2018-1-17 edit
			AppDownload(user.getId(), voF, trade_no);
		}



		int buyer_count = product.getBuyer_count() + 1;
		product.setBuyer_count(buyer_count);
		log.info("项目："+product.getId()+"+++余额："+product.getBalance());
		productService.updateProductById(product);

		Double income = 0.0;






		//买标
		ProductBuy buy1 = new ProductBuy();
		buy1.setTrade_no(trade_no);
		buy1.setStatus(9);
		List<ProductBuyVo> listBuy = productBuyService.selectForList(buy1);
		if(listBuy.size() != 1){
			tradeHashMap.remove(trade_no);
			return ;
		}
		//buy.setUser_id(user.getId());


		ProductBuy buy = listBuy.get(0);

		Date date = buy.getCreate_time();

		if(date == null){
			date = new Date();
		}
		buy.setAmount(amount);
		buy.setPhone(user.getUser_name());
		Double act_pay = amount - preferential;
		buy.setAct_pay_money(act_pay);//preferential 是红包钱
		//buy.setReconciliation_id(trade_no);  流水号  int  我们是
		buy.setBid_status(1);
		Double coupon_interest = 0.0;
		if(user_coupon_id!=null&&user_coupon_id != 0){
			buy.setUser_coupon_id(user_coupon_id);
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setId(user_coupon_id);
			userCoupon.setStatus(1);
			List<UserCouponVo> listCoupon  = userCouponService.selectForList(userCoupon);

			if(listCoupon.size() == 1){
				UserCoupon coupon = listCoupon.get(0);
				if(coupon.getStatus() == 1){
					coupon.setUse_time(date);
					coupon.setStatus(2);
					coupon_interest = coupon.getInterest();
					userCouponService.updateUserCouponByCouponId(coupon);
				}

			}
		}
		//红包使用
		if(user_gift_id!=null&&user_gift_id != 0){
			buy.setUser_gift_id(user_gift_id);
			UserGift gift = new UserGift();
			gift.setId(user_gift_id);
			gift.setStatus(1);
			List<UserGiftVo> listGift = userGiftService.selectForList(gift);

			if(listGift.size() == 1){
				UserGift userGift = listGift.get(0);
				userGift.setStatus(2);
				Double giftAmount = userGift.getAmount();
				if(preferential.compareTo(giftAmount) != 0)
				{
					userGift.setRemark("不一致");
				}
				userGiftService.updateUserGiftByGiftId(userGift);
			}
		}
		buy.setUser_id(user.getId());
		buy.setProduct_interest(product.getIncome() + coupon_interest);
		//buy.setCreate_time(date);//去掉时间
		buy.setProduct_id(product.getId());
		buy.setProduct_name(product.getProduct_code());
		buy.setType(1);
		buy.setPay_time(new Date());
		buy.setClose_time(product.getWill_end_date());
		buy.setReturn_date(product.getWill_end_date());//回款日期
		int income_days = 0;
		Date start_dates = date;
		if(product.getIncome_method() == 2)//T+1
		{
			start_dates = DateUtil.addDay(date, 1);	
		}

		String strDate = DateUtil.formatDate(product.getWill_end_date());
		income_days = DateUtil.incomeDays(product.getWill_end_date(), start_dates);
		Date yearEnd = DateUtil.getDateTime(2018, 3, 1,0,0,0);
		if(amount.compareTo(3000.0)>=0&&date.getTime()<yearEnd.getTime()){//2018-1-12
			Date shuang = DateUtil.getDateTime(2018, 2, 12,0,0,0);
			if(shuang.getTime()<date.getTime())
				userGift(income_days,user.getId());//发红包  3000 2018-1-10日
		}

		//时间  2018年1月6日   2018年1月31日
		Date yearBegin = DateUtil.getDateTime(2018, 1, 6,0,0,0);

		if(yearBegin.getTime()<date.getTime()&&date.getTime()<yearEnd.getTime()){
			yearUser(user,amount, voF);//黄金年终奖
		}


		//int come_dayss =DateUtil.differentDays(start_dates,product.getWill_end_date());
		buy.setStart_date(start_dates);
		buy.setWill_end_date(product.getWill_end_date());

		buy.setWill_income_days(income_days);
		buy.setStatus(1);
		buy.setBid_status(1);
		buy.setEnd_date(product.getWill_end_date());

		income = amount*(product.getIncome() + coupon_interest)*0.01* income_days/360;
		//操持两位小数
		income = SinaResultUtil.doubleToTwo(income);
		buy.setWill_income(income);
		//待收益  20-12-04
		voF.setPend_income(voF.getPend_income() + income );
		//待回款
		voF.setPend_return(voF.getPend_return()  + amount);
		//累计收益
		//voF.setIncomed(voF.getIncomed() + income );//+ preferential  去掉 2017-12-7日  


		buy.setReq_source(Integer.parseInt(req_source));

		if(user.getNew_hand() == 1){
			//渠道红包  2017-12-29
			if(user.getInvite_source()!=null && !user.getInvite_source().isEmpty()){
				if(user.getInvite_source().equalsIgnoreCase("501") || 
						user.getInvite_source().equalsIgnoreCase("502")||
						user.getInvite_source().equalsIgnoreCase("503")||
						user.getInvite_source().equalsIgnoreCase("504")||
						user.getInvite_source().equalsIgnoreCase("505")||
						user.getInvite_source().equalsIgnoreCase("506")){
					Gift gift = new Gift();
					if(user.getInvite_source().equalsIgnoreCase("504")){
						gift.setGift_name("车主复投红包");
						gift.setGift_source(55);
						gift.setStatus(1);
					}

					if(user.getInvite_source().equalsIgnoreCase("501")||
							user.getInvite_source().equalsIgnoreCase("502")||
							user.getInvite_source().equalsIgnoreCase("503")||
							user.getInvite_source().equalsIgnoreCase("505")||
							user.getInvite_source().equalsIgnoreCase("506")){
						gift.setGift_name("复投红包");
						gift.setGift_source(55);
						gift.setStatus(1);
					}


					//红包 流程
					applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
							gift,giftService,userGiftService));
				}
			}
		}

		if((amount >=1000.0)&&firstCast(user.getId(),voF,trade_no)){
			buy.setFirst_buy_flag(1);//首次投资
			user.setNew_hand(2);

			userService.updateForObjectByPhone(user);

		}

		if(user.getNew_hand() != 2){
			ProductBuy productBuy1 = new ProductBuy();
			productBuy1.setUser_id(user.getId());
			List<ProductBuyVo> listBuy2 = productBuyService.selectForList(productBuy1);
			int size = 0;
			for(int z = 0;z<listBuy2.size();z++){
				ProductBuyVo vo1 = listBuy2.get(z);

				if(size==2)
					break;
				if(vo1.getStatus() == 1 || vo1.getStatus() ==2){
					size++;
				}
			}

			if(size == 1){
				user.setNew_hand(2);
				userService.updateForObjectByPhone(user);
			}

		}

		if(amount.compareTo(1000.0)>=0&&singleInvestment(user.getId())){
			//2017-12-06
			Gift gift = new Gift();

			gift.setGift_name("新手首投红包");
			gift.setGift_source(13);
			gift.setStatus(1);

			//红包 流程
			applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
					gift,giftService,userGiftService));


			//加息券
			Coupon coupon = new Coupon();
			coupon.setCoupon_source(12);
			coupon.setStatus(1);
			coupon.setCoupon_name("新手首投加息券");
			applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
					coupon,couponService,userCouponService));
		}



		if((user.getNew_hand() == 2)&&(amount >=1000)&&(voF.getInvest_frequency()<30))
			reCase(user.getId(),voF,trade_no);//复投




		productBuyService.updateProductBuyById(buy);

		if(balance.compareTo(0.0)==0||product.getSale_money().compareTo(product.getMoney())==0){//给奖励{
			product.setStatus(7);
			fullScale(product,trade_no);
			product.setFill_time(new  Date());
			product.setOrder_no(99);
		}
		log.info("项目："+product.getId()+"+++余额："+product.getBalance());
		productService.updateProductById(product);

		if(!coin_num.equalsIgnoreCase("0.0")&&!coin_num.isEmpty()&&coin_num.equalsIgnoreCase(preferential.toString())){
			buy.setCoin(preferential);///正经值个数  需要抵换

			CoinStream stream = new CoinStream();
			stream.setAction(2);
			stream.setRelation_id(buy.getId());
			stream.setUser_id(user.getId());
			stream.setAmount(preferential);
			stream.setStatus(1);
			stream.setCreate_time(date);
			stream.setType(21);
			stream.setRemark(trade_no);//正经值兑换——保存单号
			coinStreamService.save(stream);

			Double coin_balance = voF.getCoin_balance() - preferential;
			Double coin_cost = voF.getCoin_cost() + preferential;

			voF.setCoin_balance(coin_balance);
			voF.setCoin_cost(coin_cost);
		}

		//资金流动明细
		Funds funds = new Funds();
		funds.setTrade_no(trade_no);
		funds.setRelation_id(product.getId());
		funds.setOperate_type(22);
		funds.setStatus(3);

		List<FundsVo> listFunds = fundsService.selectForList(funds);
		//可用余额
		Double balance1 = voF.getBalance() - act_pay;
		if(listFunds.size() == 1){//改变购买金额
			FundsVo funVo = listFunds.get(0);
			funVo.setStatus(1);
			funVo.setRelation_id(buy.getId());
			funVo.setBalance(SinaResultUtil.queryBalance(user));
			fundsService.updateFundsById(funVo);
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

				//int come_days =DateUtil.differentDays(start_dates,vo.getEnd_date());
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

			//productIncomeService.updateProductIncomeById(vo);
		}



		Double investBefore =  voF.getInvest_sum();//;
		voF.setBalance(SinaResultUtil.queryBalance(user));
		Double  invest_sum = voF.getInvest_sum() + amount;
		voF.setInvest_sum(invest_sum);
		int invest_frequency = voF.getInvest_frequency() + 1;
		voF.setInvest_frequency(invest_frequency);
		voF.setUpdate_time(new Date());
		userFundStatService.updateUserFundStatById(voF);

		if(invest_sum.compareTo(10000.0)>=0&&investBefore<10000){
			//  2017-12-06
			Gift gift = new Gift();

			gift.setGift_name("新手复投红包");
			gift.setGift_source(23);
			gift.setStatus(1);

			//红包 流程
			applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
					gift,giftService,userGiftService));

		}

		//新春开门红包活动 2018-2-23 add
		//当前系统时间
		Date curDate = new Date();
		//当前系统时间yyyy-MM-dd 毫秒值
		long currDateLong = DateUtil.convert(DateUtil.formatDate(curDate,"yyyy-MM-dd"),2).getTime();
		//在活动时间范围内，发放红包
		if (currDateLong >= DateUtil.convert(newYearActivityStartDate,0).getTime()
				&& currDateLong <= DateUtil.convert(newYearActivityEndDate,0).getTime()) {

			//判断用户是否拿到过奖励
			UserGift ugft = new UserGift();
			ugft.setUser_id(user.getId());
			ugft.setGift_name(newYearActivityGiftName);
			List<UserGiftVo> ugfts = userGiftService.selectForList(ugft);
			if (ugfts.size()==0) {
				//红包 流程
				Gift nygift = new Gift();
				nygift.setGift_name(newYearActivityGiftName);
				nygift.setStatus(1);
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						nygift,giftService,userGiftService));
			}
		}

		//女神节活动 2018-3-5 add
		//在活动时间范围内，发放红包
		if (currDateLong >= DateUtil.convert(goddessActivityStartDate,0).getTime()
				&& currDateLong <= DateUtil.convert(getGoddessActivityEndDate,0).getTime()) {

			if (amount >= 5000) {
				//红包 流程
				Gift nygift = new Gift();
				nygift.setGift_name(secGoddessGiftName);
				nygift.setStatus(1);
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						nygift,giftService,userGiftService));
			}
		}

		//愚人节活动 2018-3-22 add
		//在活动时间范围内，发放红包
		if (currDateLong >= DateUtil.convert(foolsActivityStartDate,0).getTime()
				&& currDateLong <= DateUtil.convert(foolsActivityEndDate,0).getTime()) {

			if (amount >= 2000) {
				//红包 流程
				Gift nygift = new Gift();
				nygift.setGift_name(foolsGift);
				nygift.setStatus(1);
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						nygift,giftService,userGiftService));
			}
		}
		tradeHashMap.remove(trade_no);
		frontService.reloadIndexCache();
		log.info("投资订单号记录结束："+trade_no);
	}


	void getProductDynamic(String debt_code,String product_code,int index){
		/*ProductDynamic productD = new ProductDynamic();
		productD.setDebt_code(debt_code);
		productD.setProduct_code(product_code);
		productD.setNum(index);

		List<ProductDynamicVo> listDy = productDynamicService.selectForList(productD);
		ProductDynamic dynamic = null;
		if(listDy.size() == 1){
			dynamic = listDy.get(0);

		}else if(listDy.size() == 0){
			dynamic = new ProductDynamic();
		}

		List<ProductDynamicVo> listDy = productDynamicService.selectForList(productD);
		ProductDynamic dynamic = null;
		if(listDy.size() == 1){
			dynamic = listDy.get(0);


		}else if(listDy.size() == 0){
			dynamic = new ProductDynamic();
			//保存统计
			dynamic.setDebt_code(debt_code);
			//dynamic.setProduct_id(product.getId());
			dynamic.setProduct_code(product.getProduct_code());
			dynamic.setLoan_id(product.getLoan_id());
			dynamic.setLender_id(product.getLender_id());
			dynamic.setIssure_time(product.getIssue_time());
			dynamic.setNum(i+1);
			dynamic.setPay_day(vo.getEnd_date());
			dynamic.setTo_pay_amount(product.getMoney());
			dynamic.setTo_pay_income(income);
			dynamic.setAmount(amount);
			dynamic.setNow_payed_amount(product.getMoney());
			dynamic.setNow_to_pay_income(income);
			dynamic.setDay_to_pay_amount(amount);
			dynamic.setDay_to_pay_income(income);

			productDynamicService.save(dynamic);
		}*/

	}

	void userGift(int income_days,Long user_id){

		Gift gift = new Gift();
		gift.setGift_name("年末红包");
		gift.setGift_source(104);
		List<GiftVo> listG = giftService.selectForList(gift);
		//int total = listG.size() - 1;
		for(int i = 0;i<listG.size();i++){
			GiftVo vo = listG.get(i);
			if(income_days >= vo.getMax_days()){
				UserGift userGift = new UserGift();

				Date date = new Date();
				userGift.setGift_id(vo.getId());
				userGift.setGift_name(vo.getGift_name());
				userGift.setUser_id(user_id);
				userGift.setAmount(vo.getMoney());
				userGift.setCreate_time(date);
				userGift.setRemark(vo.getGift_name());

				if(vo.getDate_type() == 2){
					userGift.setStart_date(date);
					Calendar calendar = new GregorianCalendar(); 
					calendar.setTime(date); 
					calendar.add(Calendar.DATE, vo.getGift_days()-1);//把日期往后增加一年.整数往后推,负数往前移动
					userGift.setEnd_date(calendar.getTime());
				}else if(vo.getDate_type() == 1){
					//比较一下时间  那个比较晚  就用那个时间
					userGift.setStart_date((date.getTime()>vo.getStart_date().getTime())?date:vo.getStart_date());
					userGift.setEnd_date(vo.getEnd_date());
				}

				userGift.setStatus(1);
				userGift.setMax_days(vo.getMax_days());
				userGift.setMax_amount(vo.getMax_amount());
				userGift.setUse_type(vo.getUse_type());

				userGiftService.save(userGift);

				return ;
			}


		}

	}


	private IProductBuyDao productBuyDao;
	@Autowired(required=true)
	public void setProductBuyDao(
			@Qualifier("productBuyDao")IProductBuyDao productBuyDao) {
		this.productBuyDao = productBuyDao;
	}


	//代收完成
	//@Transactional(rollbackFor = { Exception.class })
	@RequestMapping(value="/collect/trade",method=RequestMethod.GET)
	public  String setCollectTrade(HttpServletRequest request,
			HttpServletResponse response,Model model) throws NumberFormatException, Exception{

		SmsReturn sr = new SmsReturn();

		String uid = request.getParameter("uid");

		String amount = request.getParameter("amount");
		String debt_code = request.getParameter("debt");
		String user_coupon_id = request.getParameter("coupon");
		String user_gift_id = request.getParameter("gift_id");
		String gift_money = request.getParameter("gift");
		String req_source = request.getParameter("req");
		String coin = request.getParameter("coin");
		String trade_no = request.getParameter("trade");
		Funds funds = new Funds();
		funds.setTrade_no(trade_no);
		funds.setStatus(1);
		if(tradeHashMap!=null && tradeHashMap.get(trade_no)!=null){
			return "";
		}else{
			tradeHashMap.put(trade_no, trade_no);
		}
		List<FundsVo> listFunds = fundsService.selectForList(funds);
		if(listFunds.size()>0){
			tradeHashMap.remove(trade_no);
			//2018-03-16 Andrew add 对于新浪页面点立即返回所做处理
			Product prodt = new Product();
			prodt.setDebt_code(debt_code);
			List<ProductVo> productList = productService.selectForList(prodt);
			prodt=productList.get(0);
			ProductBuy prdtBuy=new ProductBuy();
			prdtBuy.setProduct_id(prodt.getId());
			prdtBuy.setUser_id(Long.valueOf(uid));
			List<ProductBuyVo> prodtBuyList=(List<ProductBuyVo>) productBuyDao.selectForList("selectProductBuyNew",prdtBuy);
			prdtBuy=prodtBuyList.get(0);
			Map mp=new HashMap();
			mp.put("pay_time", prdtBuy.getPay_time());
			mp.put("return_date", prdtBuy.getReturn_date());
			mp.put("start_date", prdtBuy.getStart_date());
			mp.put("buy_amount",prdtBuy.getAmount());
			model.addAllAttributes(CommonUtils.packageResult(true, mp));
			return "front/tz_success";
		}
		getTick(debt_code);
		User us = new User();
		us.setId(Long.parseLong(uid));
		User user = userService.load(us);

		if(gift_money == null || gift_money.isEmpty())
			gift_money = "0.0";

		if(user_coupon_id == null || user_coupon_id.isEmpty())
			user_coupon_id = "0";

		if(user_gift_id == null || user_gift_id.isEmpty())
			user_gift_id = "0";

		if(coin == null || coin.isEmpty())
			coin = "0";

		String result = SinaUtil.queryHostTrade(user.getId(), trade_no, "", "");
		/*String tr = fVo.getTrade_no();
		String re = SinaUtil.queryPayResult(trade_no);*/
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);

		Object strCode =  resultMap.get("response_code");

		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){

			return "";
		}

		Map dataMap =  (Map) resultMap.get("data");

		String trade_list = (String) dataMap.get("trade_list");
		System.out.println(trade_list);
		Map map=new HashMap();
		map.put("req_source", req_source);
		if(trade_list.indexOf("TRADE_FINISHED")>0){

			ProductBuy buy = new ProductBuy();
			buy.setTrade_no(trade_no);
			buy.setStatus(9);
			List<ProductBuyVo> list =  productBuyService.selectForList(buy);
			if(list.size()==1){//同步
				ProductBuyVo vo = list.get(0);
				vo.setStatus(10);
				productBuyService.updateProductBuy(vo);


				//购买标的内容
				getProductBuy(user,RoofNumberUtils.StringtoDouble(amount),debt_code,RoofNumberUtils.StringtoDouble(gift_money),
						Long.parseLong(user_coupon_id),Long.parseLong(user_gift_id),trade_no,req_source,coin);


				ServiceChargeUtil.getInstance().addLatest(user.getUser_name(), amount,"投资");
				sr.setContent("投资成功");
				sr.setStatus(100);
				sr.setReturnCase(true);

				Product pro = new Product();
				pro.setDebt_code(debt_code);
				List<ProductVo> productList = productService.selectForList(pro);
				pro=productList.get(0);
				ProductBuy productBuy=new ProductBuy();
				productBuy.setProduct_id(pro.getId());
				productBuy.setUser_id(user.getId());
				List<ProductBuyVo> productBuyList=(List<ProductBuyVo>) productBuyDao.selectForList("selectProductBuyNew",productBuy);
				productBuy=productBuyList.get(0);
				map.put("pay_time", productBuy.getPay_time());
				map.put("return_date", productBuy.getReturn_date());
				map.put("start_date", productBuy.getStart_date());
				map.put("product_id",productBuy.getProduct_id());
				map.put("buy_id", productBuy.getId());
				map.put("buy_amount",productBuy.getAmount());//2018-1-15 add 用于tz_success.jsp页面显示投资本金
				model.addAllAttributes(CommonUtils.packageResult(true, map));
				if(req_source.equalsIgnoreCase("4")){//微信端
					response.setContentType("text/html;charset=UTF-8");
					//response.setHeader("refresh","3;url=/responseFile/index.jsp");
					String url = "3;url=" + UrlConstant.API_H5_COLLECT_TRADE_URL;
					response.setHeader("refresh",url);

					return UrlConstant.API_H5_COLLECT_TRADE_URL;
				}else if(req_source.equalsIgnoreCase("2")||req_source.equalsIgnoreCase("3")){//app
					return "front/m/in_ok";
				}else {//web端
					return "front/tz_success";//2018-1-15 edit pc投资返回页面
				}

			}

			return "";

		}else{
			if(req_source.equalsIgnoreCase("4")){

				response.setContentType("text/html;charset=UTF-8");
				//response.setHeader("refresh","3;url=/responseFile/index.jsp");
				String url = "3;url=" + UrlConstant.API_H5_COLLECT_TRADE_URL;
				response.setHeader("refresh",url);
				return UrlConstant.API_H5_COLLECT_TRADE_URL;
			}else{
				return "front/m/in_ok";
			}
		}


	}


	@RequestMapping(value="/asy/collect/trade",method=RequestMethod.POST)
	public String asyCollectTrade(HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception{

		String trade_no = request.getParameter("trade_no");
		String stat = request.getParameter("stat");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(trade_no + stat+
				Constants.API_KEY);

		/*if( null == sign || sign.isEmpty()){
			return "101";
		}

		if(!sign.equalsIgnoreCase(md5Sign)){


			return "102";
		}*/

		if(stat.equalsIgnoreCase("0")){
			return "101";
		}
		if(tradeHashMap!=null && tradeHashMap.get(trade_no)!=null){
			return "";
		}else{
			tradeHashMap.put(trade_no, trade_no);
		}
		//买标
		return tradeFinish(trade_no);
	}

	private String tradeFinish(String trade_no)throws Exception{
		ProductBuy buy = new ProductBuy();

		//		String result = SinaUtil.queryHostTrade(buy.getUser_id(), trade_no, "", "");
		/*String tr = fVo.getTrade_no();
		String re = SinaUtil.queryPayResult(trade_no);*/
		//返回值
		Map hashMap = new HashMap();
		hashMap.put("trade_no", "%"+trade_no+"%");
		NotifyLog nl = (NotifyLog)notifyService.selectNotifyLogByTradeNo(hashMap);
		if(nl!=null){

		}else{
			tradeHashMap.remove(trade_no);
			return "102";
		}
		//		Object strCode =  resultMap.get("response_code");
		//		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
		//			tradeHashMap.remove(trade_no);
		//			return "";
		//		}
		//		Map dataMap =  (Map) resultMap.get("data");
		//		
		//		String trade_list = (String) dataMap.get("trade_list");
		//		
		//		if(trade_list.indexOf("TRADE_FINISHED")==-1){
		//			tradeHashMap.remove(trade_no);
		//			return "102";
		//		}
		buy.setTrade_no(trade_no);
		buy.setStatus(9);
		List<ProductBuyVo> list =  productBuyService.selectForList(buy);
		if(list!=null && list.size()==1){
			ProductBuyVo vo = list.get(0);
			User us = new User();
			us.setId(vo.getUser_id());
			User user = userService.load(us);
			if(vo.getCoin() == null)
				vo.setCoin(0.0);
			getProductBuy(user,vo.getAmount(),vo.getProduct_name(),vo.getAmount() - vo.getAct_pay_money(),
					vo.getUser_coupon_id(),vo.getUser_gift_id(),trade_no,vo.getReq_source()+"",vo.getCoin().toString());
			ServiceChargeUtil.getInstance().addLatest(user.getUser_name(), vo.getAmount().toString(),"投资");
			return "100";
		}
		tradeHashMap.remove(trade_no);
		return "101";
	}

	@RequestMapping(value="/repayment",method=RequestMethod.POST)
	public int repayment(HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception{

		String trade_no = request.getParameter("trade_no");
		String stat = request.getParameter("stat");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(trade_no + stat+
				Constants.API_KEY);

		if( null == sign || sign.isEmpty()){
			return 101;
		}

		if(!sign.equalsIgnoreCase(md5Sign)){


			return 102;
		}

		if(stat.equalsIgnoreCase("0")){
			return 101;
		}
		/*//买标
		ProductBuy buy = new ProductBuy();
		buy.setTrade_no(trade_no);
		List<ProductBuyVo> list =  productBuyService.selectForList(buy);
		if(list.size()==1){
			ProductBuyVo vo = list.get(0);
			User us = new User();
			us.setId(vo.getUser_id());
			User user = userService.load(us);
			getProductBuy(user,vo.getAmount(),vo.getProduct_name(),vo.getAmount() - vo.getAct_pay_money(),
					vo.getUser_coupon_id(),vo.getUser_gift_id(),trade_no,vo.getReq_source()+"",vo.getCoin().toString());
			ServiceChargeUtil.getInstance().addLatest(user.getUser_name(), vo.getAmount().toString(),"投资");
			return 100;
		}*/
		return 101;
	}

	@RequestMapping(value="/batch",method=RequestMethod.POST)
	public int batch(HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception{

		String trade_no = request.getParameter("trade_no");
		String stat = request.getParameter("stat");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(trade_no + stat+
				Constants.API_KEY);

		if( null == sign || sign.isEmpty()){
			return 101;
		}

		if(!sign.equalsIgnoreCase(md5Sign)){


			return 102;
		}

		if(stat.equalsIgnoreCase("0")){
			return 101;
		}
		/*//买标
		ProductBuy buy = new ProductBuy();
		buy.setTrade_no(trade_no);
		List<ProductBuyVo> list =  productBuyService.selectForList(buy);
		if(list.size()==1){
			ProductBuyVo vo = list.get(0);
			User us = new User();
			us.setId(vo.getUser_id());
			User user = userService.load(us);
			getProductBuy(user,vo.getAmount(),vo.getProduct_name(),vo.getAmount() - vo.getAct_pay_money(),
					vo.getUser_coupon_id(),vo.getUser_gift_id(),trade_no,vo.getReq_source()+"",vo.getCoin().toString());
			ServiceChargeUtil.getInstance().addLatest(user.getUser_name(), vo.getAmount().toString(),"投资");
			return 100;
		}*/
		return 101;
	}

	@RequestMapping(value="/return/{serviceName}", method=RequestMethod.POST)
	public void notify(@PathVariable String serviceName,HttpServletRequest request,HttpServletResponse response){
		long start = System.currentTimeMillis();
		System.out.println("serviceName==="+serviceName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		NotifyLog notifyLog = new NotifyLog();
		notifyLog.setService_name(serviceName);
		try {
			String sign=request.getParameter("sign");
			String sign_type = request.getParameter("sign_type");//
			String like_result = Tools.createLinkString(Tools.getParameterMap(request,true), false);//获取异步回调参数
			String _input_charset=request.getParameter("_input_charset");
			String UUID = Tools.getUUID();//日志跟踪号
			String signKey = "";
			log .info("------------------------------------------------------");
			log .info("["+UUID+"]"+"异步回调验签参数："+like_result.toString());
			log.info("["+UUID+"]"+"异步验签sign："+sign);

			if ("RSA".equalsIgnoreCase(sign_type.toString())) {
				//验签公钥
				signKey = Tools.getKey("rsa_sign_public.pem");
			}
			try {
				//对异步参数做验签
				if (SignUtil.Check_sign(like_result.toString(),sign,sign_type,signKey,_input_charset )) 

				{
					log .info("["+UUID+"]"+"异步验签成功验签成功，并返回success");
					response.setContentType("text/html;charset=UTF-8");
					/*
					 * 验签成功后做数据落地，数据落地成功后返回success告知sina收到通知，以免sina重复通知，浪费双方资源
					 */
					/*
					 * 返回success后，根据产品需求以及落地相关数据信息做相应业务处理
					 */
					String outer_trade_no = "";
					if(like_result.indexOf("SUCCESS")!=-1 || like_result.indexOf("TRADE_FINISHED")!=-1){
						notifyLog.setStatus("success");
						if(serviceName.equals("create_hosting_collect_trade")){
							if(like_result!=null && like_result.indexOf("&")!=-1){
								outer_trade_no = request.getParameter("outer_trade_no");
								System.out.println("++++++++++++++++++++++"+outer_trade_no);
							}
						}
					}else{
						notifyLog.setStatus("error");
					}

					notifyLog.setReturn_content(like_result.toString());
					notifyLog.setNotify_time(sdf.format(new Date()));
					saveNofifyLog(notifyLog);
					log .info("异步调用投资记录：create_hosting_collect_trade==="+outer_trade_no);
					final String otn=outer_trade_no;
					taskExecutor.execute(new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								log .info("异步调用投资记录开始：create_hosting_collect_trade==="+otn);
								tradeFinish(otn);
								log .info("异步调用投资记录结束：create_hosting_collect_trade==="+otn);
							}catch (Exception e){
								e.printStackTrace();
							}
						}
					}));
					long end = System.currentTimeMillis();
					System.out.println("++++++++++++++++++++++"+(end-start));
					responseWriteString(response,"success");

				} else {
					notifyLog.setReturn_content("sign error!");
					notifyLog.setStatus("error");
					notifyLog.setNotify_time(sdf.format(new Date()));
					saveNofifyLog(notifyLog);
					responseWriteString(response,"error");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				notifyLog.setReturn_content("请求异常！");
				notifyLog.setStatus("error");
				notifyLog.setNotify_time(sdf.format(new Date()));
				saveNofifyLog(notifyLog);
				responseWriteString(response,"error");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("非法请求！！！");
		}

	}

	private void responseWriteString(HttpServletResponse resp, String result) {
		PrintWriter writer = null;
		try {
			writer = resp.getWriter();
			writer.write(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			log.info("返回第三方支付结果异常", e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	public void saveNofifyLog(NotifyLog notifyLog){
		notifyService.save(notifyLog);
	}

	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}

	@Autowired(required = true)
	public void setWithdrawService(IWithdrawService withdrawService) {
		this.withdrawService = withdrawService;
	}

	@Autowired(required = true)
	public void setFundsService(IFundsService fundsService) {
		this.fundsService = fundsService;
	}

	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}

	@Autowired(required = true)
	public void setGiftService(IGiftService giftService) {
		this.giftService = giftService;
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
	public void setProductDynamicService(IProductDynamicService productDynamicService) {
		this.productDynamicService = productDynamicService;
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
	public void setCouponService(ICouponService couponService) {
		this.couponService = couponService;
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
	public void setNotifyService(INotifyService notifyService) {
		this.notifyService = notifyService;
	}

	@Autowired(required = true)
	public void setFrontService(IFrontService frontService) {
		this.frontService = frontService;
	}

}
