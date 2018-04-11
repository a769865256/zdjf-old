package com.zdjf.web.user;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.webservice.express.SmsReturn;

public class UserFundStatController {
	
private IUserFundStatService userFundStatService;
	
	private IUserService userService;
	
	private IFundsService fundsService;
	
	private IUserBankService userBankService;
	
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
		String amount = request.getParameter("amount");
		String pay_type = request.getParameter("pay_type");
		String summary = request.getParameter("summary");
		/*String md5Sign = Md5Util.md5to32(user_name + trade_no + amount +
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
		
		//调用支付接口
		
		User user = userService.selectForObjectByPhone(user_name);
		Double cny = RoofNumberUtils.StringtoDouble(amount);
		
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		
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
		pay_type = "1";
		if(pay_type.equalsIgnoreCase("1"))
			pay_method = "binding_pay^" + amount + "^" + card_id;
		else{
			
			//测试写TESTBANK
			//TESTBANK  只有pc  支持  普通卡
//			pay_method = "online_bank^" + amount + "^" +vo.getBank_alias()+",DEBIT," + vo.getCard_attribute();
			pay_method = "online_bank^" + amount + "^SINAPAY,DEBIT," + vo.getCard_attribute(); //收银台
//			pay_method = "online_bank^" + amount + "^" + "TESTBANK" +",DEBIT," + vo.getCard_attribute();
		}
		
		String trade_no = UniqueUtil.getTradeNo();
		String result = SinaUtil.createHostDeposit(user.getId(), trade_no, summary,
				cny, pay_method, user.getReg_ip(),"","");
		System.out.println(result);
		
		
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
		
			
			sr.setContent("充值不成功");
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
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		Double balance = voF.getBalance();
		balance += cny;
		voF.setBalance(balance);
		userFundStatService.updateUserFundStatById(userFund);
		
		
		Funds funds = new Funds();
		String ticket = (String) resultMap.get("ticket");
		
		funds.setAmount(cny);
		funds.setBalance(balance);
		funds.setAction(1);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setRemark(result);
		funds.setOperate_type(11);
		funds.setStatus(3);
		funds.setTrade_no(trade_no);
		funds.setTicket(ticket);
		funds.setSummary(summary);
		fundsService.save(funds);
		
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent("充值成功");
		
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
		
		//for(FundsVo fundsVo : listFund){
		for(int i = 0;i<listFund.size();i++){
			//遍历
			FundsVo fundsVo = listFund.get(i);
			String result = SinaUtil.advanceHostPay(user.getId(),fundsVo.getTrade_no(),
					fundsVo.getTicket(), reg_source,user.getReg_ip());
			System.out.println(result);
			Map resultMap = JsonUtil.jsonToMap(result);
			Object strCode =  resultMap.get("response_code");
			
			if(strCode != null &&strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
				
				//流程标识充值成功
				fundsVo.setStatus(1);
				//更新用户统计信息
				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(user.getId());
				UserFundStat voF = userFundStatService.selectForObjectById(userFund);
				Double balance = voF.getBalance();
				Double amount = fundsVo.getAmount();
				balance += amount;
				voF.setBalance(balance);
				//保存
				fundsService.updateFundsById(fundsVo);
				userFundStatService.updateUserFundStatById(userFund);
				
				sr.setContent("充值确认成功");
				sr.setReturnCase(true);
				sr.setStatus(100);
				
				break;
			}
		
		}
		
		return sr;
	}
	
	//获取用户提现
	@RequestMapping(value="/withdraw",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostWithdraw(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String trade_no = request.getParameter("trade_no");
		String amount = request.getParameter("amount");
		//String pay_type = request.getParameter("pay_type");
		String payto_type = request.getParameter("payto_type");
		String cashdesk_addr_category = request.getParameter("cashdesk_addr_category");
		String sign = request.getParameter("sign");
		String summary = request.getParameter("summary");
		String reg_source=request.getParameter("reg_source");
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
		
		//调用支付接口
		
		User user = userService.selectForObjectByPhone(user_name);
		Double cny = RoofNumberUtils.StringtoDouble(amount);
		
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		
		List<UserBankVo> list = userBankService.selectForList(userBank);
		
		if(list.size() == 0){
			sr.setContent("需要绑定快捷支付");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		UserBankVo vo = list.get(0);
		String card_id =  vo.getCard_id();
		
		
		Double user_fee = 1.0;
		String result = SinaUtil.createHostWithdraw(user.getId(), trade_no, summary, cny, card_id,
				user_fee, payto_type, cashdesk_addr_category, user.getReg_ip(),user_name,reg_source);

		System.out.println(result);
		
		
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
		
			
			sr.setContent("提现不成功");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		Map dataMap =  (Map) resultMap.get("data");
		
		Object redirect_url =  dataMap.get("redirect_url");
		
		String strHtml = redirect_url.toString();
		
		
		
		//更新用户统计账户信息
		/*UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		Double balance = voF.getBalance();
		balance += cny;
		voF.setBalance(balance);
		userFundStatService.updateUserFundStatById(userFund);
		
		
		Funds funds = new Funds();
		String ticket = (String) resultMap.get("ticket");
		
		funds.setAmount(cny);
		funds.setBalance(balance);
		funds.setAction(1);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setRemark(result);
		funds.setOperate_type(11);
		funds.setStatus(3);
		funds.setTrade_no(trade_no);
		funds.setTicket(ticket);
		funds.setSummary(summary);
		fundsService.save(funds);*/
		
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(strHtml);
		
		return sr;
	}
	
	//获取用户提现
	@RequestMapping(value="/collect/trade",method=RequestMethod.POST)
	public @ResponseBody SmsReturn hostCollectTrade(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String trade_no = request.getParameter("trade_no");
		String amount = request.getParameter("amount");
		String out_trade_code = request.getParameter("out_trade_code");
		String payto_type = request.getParameter("payto_type");
		String cashdesk_addr_category = request.getParameter("cashdesk_addr_category");
		String sign = request.getParameter("sign");
		String summary = request.getParameter("summary");
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
		
		//调用支付接口
		
		User user = userService.selectForObjectByPhone(user_name);
		Double cny = RoofNumberUtils.StringtoDouble(amount);
		
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		
		List<UserBankVo> list = userBankService.selectForList(userBank);
		
		if(list.size() == 0){
			sr.setContent("需要绑定快捷支付");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		UserBankVo vo = list.get(0);
		String card_id =  vo.getCard_id();
		
		
		Double user_fee = 1.0;
		String result = "";/*SinaUtil.createHostCollectTrade(user.getId(), trade_no, out_trade_code,
				summary, amount, goods_id, pay_method, gift_money, cashdesk_addr_category, ip);*/
				/*(user.getId(), trade_no, summary, cny, card_id,
				user_fee, payto_type, cashdesk_addr_category, user.getReg_ip());*/
		System.out.println(result);
		
		
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
		
			
			sr.setContent("提现不成功");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		Map dataMap =  (Map) resultMap.get("data");
		
		Object redirect_url =  dataMap.get("redirect_url");
		
		String strHtml = redirect_url.toString();
		
		
		
		//更新用户统计账户信息
		/*UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		Double balance = voF.getBalance();
		balance += cny;
		voF.setBalance(balance);
		userFundStatService.updateUserFundStatById(userFund);
		
		
		Funds funds = new Funds();
		String ticket = (String) resultMap.get("ticket");
		
		funds.setAmount(cny);
		funds.setBalance(balance);
		funds.setAction(1);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setRemark(result);
		funds.setOperate_type(11);
		funds.setStatus(3);
		funds.setTrade_no(trade_no);
		funds.setTicket(ticket);
		funds.setSummary(summary);
		fundsService.save(funds);*/
		
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(strHtml);
		
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
	@RequestMapping(value="/balance/query",method=RequestMethod.POST)
	public @ResponseBody SmsReturn queryBalance(HttpServletRequest request,
            HttpServletResponse response){
		
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
		
		
		/*//调用支付接口
		String result = SinaUtil.queryBalance(user.getId(), reg_ip);
		

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
		
		String balance = (String) dataMap.get("balance");
		String vailable_balance = (String) dataMap.get("available_balance");*/
		
		
		//统计资金
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		/*Double q_vailable_balance = RoofNumberUtils.StringtoDouble(vailable_balance);
		
		
		if(q_vailable_balance != voF.getBalance()){
			voF.setBalance(RoofNumberUtils.StringtoDouble(vailable_balance));
			voF.setUpdate_time(new Date());
			voF.setLocked_money(RoofNumberUtils.StringtoDouble(balance)-RoofNumberUtils.StringtoDouble(vailable_balance));
			
			//更新统计信息
			userFundStatService.updateUserFundStatById(voF);
		}*/
		
		//voF.setBalance(RoofNumberUtils.StringtoDouble(vailable_balance));
		
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(voF);
		
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

}
