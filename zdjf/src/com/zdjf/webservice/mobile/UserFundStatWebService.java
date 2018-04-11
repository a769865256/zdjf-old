package com.zdjf.webservice.mobile;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.ServiceChargeUtil;
import com.zdjf.components.mobile.SinaResultUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.components.mobile.ValuetoString;
import com.zdjf.domain.fund.Bank;
import com.zdjf.domain.fund.BankVo;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.CoinStreamVo;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.fund.Withdraw;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.domain.user.WithdrawCoupons;
import com.zdjf.domain.user.WithdrawCouponsVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.fund.IBankService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.fund.IWithdrawService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.service.user.IWithdrawCouponsService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.webservice.express.SmsReturn;

@Controller
@RequestMapping("/m/userFundStat")
public class UserFundStatWebService {

	private IUserFundStatService userFundStatService;

	private IUserService userService;

	private IFundsService fundsService;

	private IUserBankService userBankService;

	private IWithdrawService  withdrawService;

	private IWithdrawCouponsService  withdrawCouponsService;

	private IBankService bankService;

	private IUserGiftService userGiftService;

	private ICoinStreamService coinStreamService;

	//获取用户交易明细
	@RequestMapping(value="/transaction/details",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getTransactionDetails(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String operate_type = request.getParameter("operate_type");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);



		if( null==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");

			return sr;
		}

		if(!sign.equalsIgnoreCase(md5Sign)){

			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");

		}

		User user = userService.selectForObjectByPhone(user_name);
		if(user != null){

			Funds funds = new Funds();
			funds.setUser_id(user.getId());
			//funds.setStatus(1);
			if(operate_type != null&&!operate_type.isEmpty()
					&&!operate_type.equalsIgnoreCase("0"))
				funds.setOperate_type(Integer.parseInt(operate_type));

			Page page = PageUtils.createPage(request);
			page = fundsService.page(page, funds);


			int currentPage = page.getCurrentPage().intValue();
			int limit = page.getLimit().intValue();

			int total = page.getTotal().intValue();
			int start = limit * (currentPage - 1);
			int end = total >= limit*currentPage?limit*currentPage:total;


			List<FundsVo> list = fundsService.selectForList(funds);

			List<Map<String,String>> listS = new ArrayList<Map<String,String>>();
			for(int i = start;i<end;i++){
				FundsVo vo = (FundsVo)list.get(i);
				Funds ns = new Funds();
				BeanUtils.copyProperties(vo,ns);

				Map<String,String> map = ValuetoString.eachProperties(ns);

				listS.add(map);
			}

			page.setDataList(listS);

			sr.setReturnCase(true);
			sr.setStatus(100);
			sr.setContent(page);
		}else{
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("no");
		}



		return sr;
	}

	//获取用户资金统计详情
	@RequestMapping(value="/freeze/details",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getDetails(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);

		/*if( null==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");

			return sr;
		}

		if(!sign.equalsIgnoreCase(md5Sign)){

			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");

		}*/

		User user = userService.selectForObjectByPhone(user_name);
		if(user != null){

			UserFundStat userFund = new UserFundStat();
			userFund.setUser_id(user.getId());

			Funds funds = new Funds();
			funds.setUser_id(user.getId());
			funds.setStatus(1);
			funds.setOperate_type(33);

			//UserFundStat voF = userFundStatService.selectForObjectById(userFund);
			List<FundsVo>list = fundsService.selectForList(funds);

			sr.setReturnCase(true);
			sr.setStatus(100);
			sr.setContent(list);
		}else{
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("no");
		}



		return sr;
	}

	//获取用户充值
	@RequestMapping(value="/recharge",method=RequestMethod.POST)
	public @ResponseBody SmsReturn setRecharge(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String amount = request.getParameter("amount");
		String pay_type = request.getParameter("pay_type");
		String sign = request.getParameter("sign");
		String summary = request.getParameter("summary");
		String recharge_type=RequestUtils.getReqString(request, "recharge_type");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + amount +
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
		Double cny = RoofNumberUtils.StringtoDouble(amount);

		UserBank userBank = new UserBank();
		if(user == null){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("用户不存在");

			return sr;
		}
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
		UserBankVo vo = list.get(0);
		String card_id =  vo.getCard_id();
		String pay_method = null;

		if(pay_type.equalsIgnoreCase("1"))
			pay_method = "online_bank^" + amount + "^BANKPAY,DEBIT," + vo.getCard_attribute(); //收银台
		else{

			//测试写TESTBANK
			//TESTBANK  只有pc  支持  普通卡
			//pay_method = "online_bank^" + amount + "^" +vo.getBank_alias()+",DEBIT," + vo.getCard_attribute();
			//			pay_method = "online_bank^" + amount + "^" + "TESTBANK" +",DEBIT," + vo.getCard_attribute();
			pay_method = "online_bank^" + amount + "^BANKPAY,DEBIT," + vo.getCard_attribute(); //收银台
		}
		if(!recharge_type.equals("1")){
			pay_method = "binding_pay^" + amount + "^" + card_id;
		}

		String trade_no = UniqueUtil.getTradeNo();
		String result = SinaUtil.createHostDeposit(user.getId(), trade_no, summary,
				cny, pay_method, user.getReg_ip(),"",reg_source);
		System.out.println(result);


		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);

		Object strCode =  resultMap.get("response_code");

		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){



			sr.setContent(resultMap.get("response_msg"));
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}

		if(pay_type.equalsIgnoreCase("2")){
			Map dataMap =  (Map) resultMap.get("data");

			Object html =  dataMap.get("html");

			String strHtml = html.toString();

			sr.setContent("只有PC端");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}

		


		//更新用户统计账户信息
		/*UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		Double balance = voF.getBalance();
		balance += cny;
		voF.setBalance(balance);
		userFundStatService.updateUserFundStatById(userFund);*/


		Funds funds = new Funds();
		if(!recharge_type.equals("1")){
			String ticket = (String)resultMap.get("ticket");
			funds.setTicket(ticket);
		}
		funds.setAmount(cny);
		funds.setBalance(SinaResultUtil.queryBalance(user));
		funds.setAction(1);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setRemark(result);
		funds.setOperate_type(11);
		funds.setStatus(3);
		funds.setTrade_no(trade_no);
		funds.setSummary(summary);
		fundsService.save(funds);

		sr.setReturnCase(true);
		sr.setStatus(100);
		if(!recharge_type.equals("1")){
			sr.setContent("充值请求已经提交需要短信确认");
		}else{
			Map dataMap =  (Map) resultMap.get("data");

			Object redirect_url =  dataMap.get("redirect_url");
			String strHtml = redirect_url.toString();
			sr.setContent(strHtml);
		}
		

		return sr;
	}

	//获取充值验证
	@RequestMapping(value="/recharge/advance",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getBankAdvance(HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {

		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("valid_code");
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

		Funds funds = new Funds();
		funds.setUser_id(user.getId());
		funds.setStatus(3);
		funds.setOperate_type(11);

		List<FundsVo> listFund = fundsService.selectForList(funds);


		FundsVo fundsVo = listFund.get(0);
		String trade_no = UniqueUtil.getTradeNo();
		String result = SinaUtil.advanceHostPay(user.getId(),trade_no,
				fundsVo.getTicket(), reg_source,user.getReg_ip());
		System.out.println(result);
		Map resultMap = JsonUtil.jsonToMap(result);
		Object strCode =  resultMap.get("response_code");

		if(strCode != null &&strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){

			//流程标识充值成功
			fundsVo.setStatus(1);
			fundsVo.setTrade_no(trade_no);

			//更新用户统计信息
			UserFundStat userFund = new UserFundStat();
			userFund.setUser_id(user.getId());
			UserFundStat voF = userFundStatService.selectForObjectById(userFund);
			Double amount = fundsVo.getAmount();
			Double balance = voF.getBalance() + amount;

			//balance += amount;
			Double rechange = voF.getRecharged() + amount;

			voF.setBalance(balance);
			voF.setRecharged(rechange);

			fundsVo.setBalance(balance);
			//保存
			fundsService.updateFundsById(fundsVo);
			userFundStatService.updateUserFundStatById(voF);

			sr.setContent(resultMap.get("response_msg").toString());
			sr.setReturnCase(true);
			sr.setStatus(100);
			ServiceChargeUtil.getInstance().addLatest(user_name, amount.toString(),"充值");
			//break;
		}else{
			sr.setContent(resultMap.get("response_msg").toString());
			sr.setReturnCase(false);
			sr.setStatus(102);
			System.out.println(resultMap.get("response_msg").toString());
		}

		//}

		return sr;
	}

	Double getFee(User user,Double pend){
		Double fee = 0.0;

		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);


		Double freeQuota = voF.getInvest_sum() - voF.getRecharged() - voF.getWithdrawed();
		if(freeQuota<0){
			freeQuota = 0.0;
		}
		if(freeQuota>=pend){//在免费金额
			if(pend>=Constants.WITHDRAW_FREE){

			}else{
				fee = 1.0;
			}

		}else{

			Double temp = pend - freeQuota;
			Double freeTemp = temp * 0.002 + 1;

			if(freeQuota<Constants.WITHDRAW_FREE){
				fee = 1.0;
			}

			fee +=freeTemp;
		}

		Double withdraw = voF.getWithdrawed() + pend;
		voF.setWithdrawed(withdraw);
		voF.setBalance(voF.getBalance() - pend);

		userFundStatService.updateUserFundStatById(voF);

		BigDecimal b = new BigDecimal(fee); 
		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
		return f1;
	}

	//获取用户提现
	@RequestMapping(value="/withdraw",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostWithdraw(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String amount = request.getParameter("amount");
		String payto_type = request.getParameter("payto_type");
		String sign = request.getParameter("sign");
		String summary = request.getParameter("summary");

		String withdraw_id = request.getParameter("withdraw_id");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + amount +
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

		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		Double cny = RoofNumberUtils.StringtoDouble(amount);
		if(voF.getBalance()<cny){
			sr.setContent("提现金额 小于剩余提现金额");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}


		if(amount == null || amount.isEmpty() || cny.equals(0.0))
		{
			sr.setContent("核对提现金额");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}

		Double balance = SinaResultUtil.queryBalance(user);
		if(SinaResultUtil.queryBalance(user)<cny){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("可用余额小于提现余额");

			return sr;
		}

		voF.setBalance(balance);

		userFundStatService.updateUserFundStatById(voF);
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



		UserBankVo vo = list.get(0);
		String card_id =  vo.getCard_id();

		String trade_no = UniqueUtil.getTradeNo();
		Double user_fee = 0.0;//提现手续费暂时不算
		/*boolean fee = false;
		if(withdraw_id != null && !withdraw_id.isEmpty()){

			WithdrawCoupons withdrawCoupons = new WithdrawCoupons();
			withdrawCoupons.setUser_id(user.getId());
			withdrawCoupons.setId(Long.parseLong(withdraw_id));

			List<WithdrawCouponsVo> listCoupon = withdrawCouponsService.selectForList(withdrawCoupons);

			WithdrawCoupons coupons = listCoupon.get(0);
			coupons.setAction(2);
			coupons.setType(-1);

			withdrawCouponsService.updateWithdrawCouponsById(coupons);
			fee = true;
		}
		if(!fee){
			user_fee = getFee(user,cny);//计算手续费
		}*/
		String cashdesk_addr_category = "MOBILE";

		String result = SinaUtil.createHostWithdraw(user.getId(), trade_no, summary, cny, card_id,
				user_fee, payto_type, cashdesk_addr_category, user.getReg_ip(),user_name,reg_source);
		System.out.println(result);


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

		Object redirect_url =  dataMap.get("redirect_url");
		String strHtml = redirect_url.toString();


		Funds funds = new Funds();
		//funds.setRelation_id(withdraw.getId());
		funds.setAmount(cny);
		funds.setBalance(balance);
		funds.setAction(2);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setOperate_type(21);
		funds.setStatus(3);
		funds.setTrade_no(trade_no);
		if(withdraw_id != null && !withdraw_id.isEmpty()){
			funds.setTicket(withdraw_id);
		}
		funds.setSummary("提现");
		fundsService.save(funds);

		//提现异步
		Withdraw withdraw = new Withdraw();
		withdraw.setUser_id(user.getId());
		withdraw.setMoney(cny);
		withdraw.setReal_name(user.getReal_name());
		withdraw.setStatus(2);
		withdraw.setPp_serial_no(trade_no);
		withdraw.setFee_amt(user_fee.toString());
		withdraw.setCreate_time(new Date());
		withdraw.setReq_source(Integer.parseInt(reg_source));
		withdrawService.save(withdraw);


		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(strHtml);

		return sr;
	}

	@RequestMapping(value="/user/withdraw",method=RequestMethod.GET)
	public @ResponseBody SmsReturn userWithdraw(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
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

		WithdrawCoupons withdrawCoupons = new WithdrawCoupons();
		withdrawCoupons.setUser_id(user.getId());
		withdrawCoupons.setAction(1);

		List<WithdrawCouponsVo> listWithdraw = withdrawCouponsService.selectForList(withdrawCoupons);


		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setMapContent(listWithdraw);
		return sr;
	}

	//获取用户充值——之前
	@RequestMapping(value="/pre/recharge",method=RequestMethod.POST)
	public @ResponseBody SmsReturn preRechange(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");

		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
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
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);

		List<UserBankVo> listUserBank = userBankService.selectForList(userBank);

		UserBank bankUser = listUserBank.get(0);

		Bank bank = new Bank();
		bank.setNum(bankUser.getBank_alias());

		List<BankVo> listBank = bankService.selectForList(bank);

		if(listBank.size() !=1){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无快捷支付");

			return sr;
		}

		BankVo bankVo = listBank.get(0);

		//System.out.println(result);
		Map<String,String> map = new HashMap<String,String>();
		map.put("bankno", bankUser.getBank_no());//银行卡号
		map.put("alias", bankUser.getBank_alias());//银行英文缩写
		map.put("single_max", Double.toString(bankVo.getSingle_max()));//单笔最大
		map.put("day_max", Double.toString(bankVo.getDay_max()));//当日最大
		map.put("balance", SinaResultUtil.queryBalance(user).toString());//可用余额
		map.put("other_phone", user.getUser_name());//手机预留号码

		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(map);

		return sr;
	}

	//获取用户提现——手续费
	@RequestMapping(value="/withdraw/amount",method=RequestMethod.POST)
	public @ResponseBody SmsReturn couponWithdraw(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String amount = request.getParameter("amount");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");

		Double user_fee = getFee(user,RoofNumberUtils.StringtoDouble(amount));//计算手续费

		WithdrawCoupons withdraw = new WithdrawCoupons();
		withdraw.setUser_id(user.getId());
		withdraw.setType(1);

		List<WithdrawCouponsVo> listVo = withdrawCouponsService.selectForList(withdraw);

		String prompt = "充值未投资的资金提现将收取其提现金额2‰的手续费+1元/笔";
		Map<String,String> map = new HashMap<String,String>();
		if(user_fee>0.0){
			map.put("user_fee",user_fee.toString());//银行卡号
			map.put("withdraw_prompt", prompt);
		}else{
			map.put("user_fee","0.0");//银行卡号
			map.put("withdraw_prompt", "");
		}

		if(listVo.size()>0)
			map.put("withdraw_id", listVo.get(0).getId() + "");//银行英文缩写
		else
			map.put("withdraw_id", "");//银行英文缩写



		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(map);
		return sr;
	}

	//获取用户提现——之前
	@RequestMapping(value="/pre/withdraw",method=RequestMethod.POST)
	public @ResponseBody SmsReturn preWithdraw(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		//String amount = request.getParameter("amount");
		String reg_source = request.getParameter("reg_source");

		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
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
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> listUserBank = userBankService.selectForList(userBank);

		UserBank bankUser = listUserBank.get(0);

		Bank bank = new Bank();
		bank.setNum(bankUser.getBank_alias());

		List<BankVo> listBank = bankService.selectForList(bank);

		BankVo bankVo = listBank.get(0);

		//Double user_fee = getFee(user,RoofNumberUtils.StringtoDouble(amount));//计算手续费
		//System.out.println(result);
		Map<String,String> map = new HashMap<String,String>();
		map.put("bankno", bankUser.getBank_no());//银行卡号
		map.put("alias", bankUser.getBank_alias());//银行英文缩写
		map.put("alias_name", bankVo.getName());//银行中文
		if(bankVo.getSingle_max() == null){
			map.put("single_max", "10000.0");//单笔最大
		}else{
			map.put("single_max", Double.toString(bankVo.getSingle_max()));//单笔最大
		}
		map.put("day_max", Double.toString(bankVo.getDay_max()));//当日最大
		//map.put("withdraw_id", user_fee.toString());//手续费
		map.put("balance", SinaResultUtil.queryBalance(user).toString());//可用余额
		WithdrawCoupons withdraw = new WithdrawCoupons();
		withdraw.setUser_id(user.getId());
		withdraw.setType(1);

		List<WithdrawCouponsVo> listVo = withdrawCouponsService.selectForList(withdraw);

		if(listVo.size()>0)
			map.put("withdraw_id", listVo.get(0).getId() + "");//银行英文缩写
		else
			map.put("withdraw_id",  "");//银行英文缩写

		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(map);

		return sr;
	}


	//用户冻结余额
	@RequestMapping(value="/balance/freeze",method=RequestMethod.POST)
	public @ResponseBody SmsReturn setBalanceFreeze(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		String trade_no = request.getParameter("trade_no");
		String amount = request.getParameter("amount");
		String summary = request.getParameter("summary");
		//String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + trade_no + amount +
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

		User user = userService.selectForObjectByPhone(user_name);


		Double cny = RoofNumberUtils.StringtoDouble(amount);
		trade_no = UniqueUtil.getTradeNo();
		//调用支付接口
		String result = SinaUtil.balanceFreeze(user.getId(), trade_no,
				summary,cny,user.getReg_ip());


		System.out.println(result);


		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);

		String strCode = (String) resultMap.get("response_code");

		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}


		//统计资金
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);

		Double balance = voF.getBalance();
		balance -= cny;
		Double locked_money = voF.getLocked_money();
		locked_money +=cny;

		voF.setBalance(balance);
		voF.setLocked_money(locked_money);
		userFundStatService.updateUserFundStatById(voF);
		//资金明细
		Funds funds = new Funds();
		funds.setAmount(cny);
		funds.setBalance(balance);
		funds.setAction(2);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setRemark(result);
		funds.setOperate_type(33);
		funds.setStatus(1);
		funds.setTrade_no(trade_no);

		fundsService.save(funds);


		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent("冻结成功");

		return sr;
	}

	//用户解冻余额
	@RequestMapping(value="/balance/unfreeze",method=RequestMethod.POST)
	public @ResponseBody SmsReturn setBalanceUnfreeze(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		String trade_no = request.getParameter("trade_no");
		String amount = request.getParameter("amount");
		String summary = request.getParameter("summary");
		String out_freeze_no = request.getParameter("out_freeze_no");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + trade_no + amount +
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

		User user = userService.selectForObjectByPhone(user_name);


		Double cny = RoofNumberUtils.StringtoDouble(amount);
		trade_no = UniqueUtil.getTradeNo();
		//调用支付接口
		String result = SinaUtil.balanceUnfreeze(user.getId(), out_freeze_no,trade_no,
				summary,cny,user.getReg_ip());


		System.out.println(result);


		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);

		String strCode = (String) resultMap.get("response_code");

		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}


		//统计资金
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);

		Double balance = voF.getBalance();
		balance += cny;
		Double locked_money = voF.getLocked_money();
		locked_money -=cny;

		voF.setBalance(balance);
		voF.setLocked_money(locked_money);
		userFundStatService.updateUserFundStatById(voF);
		//资金明细
		Funds funds = new Funds();
		funds.setTrade_no(out_freeze_no);

		List<FundsVo> fund = fundsService.selectForList(funds);

		if(fund.size() == 1){
			FundsVo vo = fund.get(0);
			vo.setTicket(trade_no);
			vo.setStatus(3);

			fundsService.updateFundsById((Funds)vo);
		}


		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent("解冻成功");

		return sr;
	}

	//用户余额查询
	@SuppressWarnings("unused")
	@RequestMapping(value="/balance/query",method=RequestMethod.POST)
	public @ResponseBody SmsReturn queryBalance(HttpServletRequest request,
			HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
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

		User user = userService.selectForObjectByPhone(user_name);

		//Double ban = SinaResultUtil.queryBalance(user);
		//调用支付接口
		String result = SinaUtil.queryBalance(user.getId(), reg_ip,"");


		System.out.println(result);


		if(result == null || result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);

		String strCode = (String) resultMap.get("response_code");

		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}


		Map dataMap =  (Map) resultMap.get("data");

		String balance = (String) dataMap.get("balance");
		String vailable_balance = (String) dataMap.get("available_balance");


		//统计资金
		UserFundStat userFund = new UserFundStat();
		if(user == null){
			sr.setContent("没有此用户");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		if(voF == null){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}
		Double q_vailable_balance = RoofNumberUtils.StringtoDouble(vailable_balance);


		if(q_vailable_balance != voF.getBalance()){
			voF.setBalance(RoofNumberUtils.StringtoDouble(vailable_balance));
			voF.setUpdate_time(new Date());
			voF.setLocked_money(RoofNumberUtils.StringtoDouble(balance)-RoofNumberUtils.StringtoDouble(vailable_balance));

			//更新统计信息
			userFundStatService.updateUserFundStatById(voF);
		}

		//voF.setBalance(RoofNumberUtils.StringtoDouble(vailable_balance));
		Map<String,String> map = ValuetoString.eachProperties(voF);

		UserGift userGift = new UserGift();
		userGift.setUser_id(user.getId());
		userGift.setStatus(2);

		List<UserGiftVo> listGift = userGiftService.selectForList(userGift);

		Double gift_totle = 0.0;

		for(int i = 0;i<listGift.size();i++){
			gift_totle += listGift.get(i).getAmount();
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

		Double pend_amount = voF.getPend_income() + voF.getPend_return();
		map.put("pend_amount", SinaResultUtil.doubleToTwoToString(pend_amount));//待收金额
		int date = DateUtil.incomeDays(new Date(), user.getCreate_time());
		map.put("time_apart", Integer.toString(date));
		Double de = voF.getLocked_money();
		Double total_assets = voF.getBalance() + voF.getPend_income() + voF.getPend_return() /*+ voF.getLocked_money()*/;
		map.put("total_assets", SinaResultUtil.doubleToTwoToString(total_assets));//总额
		//Double total_income = voF.getPend_income() + voF.getIncomed() + voF.getCoin_cost() + gift_totle;

		Double total_coupon =  coin_total + gift_totle + voF.getIncomed() + voF.getPend_income();

		Double total_income = coin_total + gift_totle;
		map.put("total_coupon", SinaResultUtil.doubleToTwoToString(total_income));//累计优惠金额
		map.put("total_income", SinaResultUtil.doubleToTwoToString(total_coupon));//累计收益金额
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setMapContent(map);

		return sr;
	}

	//用户充值详情
	@RequestMapping(value="/recharge/query/details",method=RequestMethod.POST)
	public @ResponseBody SmsReturn queryRechargeDetail(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String page_size = request.getParameter("page_size");
		String page_no = request.getParameter("page_no");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + start_time + end_time +
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

		User user = userService.selectForObjectByPhone(user_name);
		start_time +="000000";
		end_time +="235959";

		//调用支付接口
		String result = SinaUtil.queryHostDeposit(user.getId(),start_time,end_time,Integer.parseInt(page_size),
				Integer.parseInt(page_no),user.getReg_ip());


		System.out.println(result);


		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);

		String strCode = (String) resultMap.get("response_code");

		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}


		Map dataMap =  (Map) resultMap.get("data");

		String detail_list = (String) dataMap.get("detail_list");

		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(detail_list);

		return sr;
	}



	//用户余额查询
	@RequestMapping(value="/balance/query/details",method=RequestMethod.POST)
	public @ResponseBody SmsReturn queryBalanceDetail(HttpServletRequest request,
			HttpServletResponse response){

		SmsReturn sr = new SmsReturn();

		String user_name = request.getParameter("phone");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String page_size = request.getParameter("page_size");
		String page_no = request.getParameter("page_no");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + start_time + end_time +
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

		User user = userService.selectForObjectByPhone(user_name);


		//调用支付接口
		String result = SinaUtil.queryAccountDetails(user.getId(),start_time,end_time,Integer.parseInt(page_size),
				Integer.parseInt(page_no),user.getReg_ip());


		System.out.println(result);


		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);

		String strCode = (String) resultMap.get("response_code");

		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}


		Map dataMap =  (Map) resultMap.get("data");

		String detail_list = (String) dataMap.get("detail_list");

		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(detail_list);

		return sr;
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
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}

	@Autowired(required = true)
	public void setWithdrawService(IWithdrawService withdrawService) {
		this.withdrawService = withdrawService;
	}

	@Autowired(required = true)
	public void setWithdrawCouponService(IWithdrawCouponsService withdrawCouponsService) {
		this.withdrawCouponsService = withdrawCouponsService;
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
	public void setBankService(IBankService bankService) {
		this.bankService = bankService;
	}

	@Autowired(required = true)
	public void setCoinStreamService(ICoinStreamService coinStreamService) {
		this.coinStreamService = coinStreamService;
	}

}
