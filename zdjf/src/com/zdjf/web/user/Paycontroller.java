package com.zdjf.web.user;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.domain.fund.Bank;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.fund.Withdraw;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductIncome;
import com.zdjf.domain.product.ProductIncomeVo;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserGift;
import com.zdjf.service.fund.FundsService;
import com.zdjf.service.fund.IBankService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.fund.IWithdrawService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductIncomeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.UrlConstant;
import com.zdjf.webservice.express.JsonReturn;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pay")
public class Paycontroller {

	Log log = LogFactory.getLog(this.getClass());

	private IUserService userService;
	private IBankService bankService;
	private ApplicationContext applicationContext;
	private IUserBankService userBankService;
	private IProductService productService;
	private ICouponService couponService;
	private IUserGiftService userGiftService;
	private IUserFundStatService userFundStatService;
	private IUserCouponService userCouponService;
	private IProductBuyService productBuyService;
	private IProductIncomeService productIncomeService;
	private IFundsService fundsService;
	private IWithdrawService  withdrawService;
	
	
	/**
	 * 到充值页面 /提现页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/charge")
	public String toCharge(HttpServletRequest request,
			HttpServletResponse response,Model model){
		String type = request.getParameter("type");
		String user_name=BrowseUtil.getCookie(request, response);
		User user=userService.selectForObjectByPhone(user_name);
		String ip = request.getRemoteAddr();
		userFundStatService.updateUserBalance(user.getId(),ip);
		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user.getId());
		userFundStat=userFundStatService.selectForObjectById(userFundStat);
		UserBank userBank=new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setStatus(1);
		userBank=(UserBank) userBankService.selectUserBankById(userBank);
		if(user.getReal_name_auth() != 1 || userBank==null){
			return "redirect:/toNewAudit.action";
		}
		Bank bank=new Bank();
		bank.setNum(userBank.getBank_alias());
		bank=bankService.selectForObjectById(bank);
		request.setAttribute("userFundStat", userFundStat);
		request.setAttribute("userBank", userBank);
		request.setAttribute("bank", bank);
		request.setAttribute("showType", "mywealth");
		if(type!=null && type.equals("1")){
			return "front/mywealth/withdraw";
		}else
		return "front/mywealth/new_charge";
	}
	
	/**
	 * 快捷充值
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toRecharge",method=RequestMethod.POST)
	public @ResponseBody JsonReturn setRecharge(HttpServletRequest request,
			HttpServletResponse response){

		JsonReturn sr = new JsonReturn();

		String user_name = BrowseUtil.getCookie(request, response);
		String amount = request.getParameter("amount");
		String summary = "账户充值";

		//调用支付接口

		User user = userService.selectForObjectByPhone(user_name);
		Double cny = RoofNumberUtils.StringtoDouble(amount);

		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);

		List<UserBankVo> list = userBankService.selectForList(userBank);

		if(list==null || list.size() == 0){
			sr.setContent("需要绑定快捷支付");
			sr.setStatus(101);

			return sr;
		}
		UserBankVo vo = list.get(0);
		String card_id =  vo.getCard_id();
		String pay_method = null;

		pay_method = "online_bank^" + amount + "^BANKPAY,DEBIT," + vo.getCard_attribute(); //收银台
		
		String trade_no = UniqueUtil.getTradeNo();
		String result = SinaUtil.createHostDeposit(user.getId(), trade_no, summary,
				cny, pay_method, user.getReg_ip(),"","");
		System.out.println(result);

		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);

		if(resultMap ==null || resultMap.get("response_code") == null || !resultMap.get("response_code").toString().equalsIgnoreCase("APPLY_SUCCESS")){

			sr.setContent("短信发送失败");
			sr.setStatus(101);
			return sr;
		}
		
		Map dataMap =  (Map) resultMap.get("data");
		Object html =  dataMap.get("redirect_url");
		String strHtml = html.toString();
		sr.setContent(strHtml);
		sr.setStatus(100);
		
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


		return sr;
	}

	/**
	 * 到新浪账户充值页面（绑卡充值+网银充值）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toBankPay", method = RequestMethod.POST)
	public String toBankPay(HttpServletRequest request, HttpServletResponse response, Model model){

		String user_name = BrowseUtil.getCookie(request, response);
		String amount = request.getParameter("amount");
		String account_identity=RequestUtils.getReqString(request, "account_identity");
		model.addAttribute("amt",amount);
		String summary = "账户充值";

		User user = userService.selectForObjectByPhone(user_name);
		Double cny = RoofNumberUtils.StringtoDouble(amount);

		//查询用户绑卡信息
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> list = userBankService.selectForList(userBank);
		UserBankVo vo = list.get(0);
		String pay_method = "online_bank^" + amount + "^BANKPAY,DEBIT," + vo.getCard_attribute(); //收银台

		String trade_no = UniqueUtil.getTradeNo();//生成交易流水号（唯一）
		//调用支付接口
		String result = SinaUtil.createHostDeposit(user.getId(), trade_no, summary,
				cny, pay_method, user.getReg_ip(),account_identity,"");
		if (!StrUtils.emptyJudge(result)) {
			model.addAttribute("resStatus",0);//失败
			model.addAttribute("resMsg","网络异常，请稍后再试！");
			return "front/charge_back";
		}
		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);

		if(!resultMap.get("response_code").toString().equalsIgnoreCase("APPLY_SUCCESS")){
			model.addAttribute("resStatus",0);//失败
			model.addAttribute("resMsg",resultMap.get("response_msg").toString());
			return "front/charge_back";
		}
		Map dataMap =  (Map) resultMap.get("data");
		Object html =  dataMap.get("redirect_url");
		if (html != null ) {
			//用户统计账户信息
			UserFundStat userFund = new UserFundStat();
			userFund.setUser_id(user.getId());
			UserFundStat voF = userFundStatService.selectForObjectById(userFund);
			Double balance = voF.getBalance();
			balance += cny;

			Funds funds = new Funds();
			funds.setAmount(cny);
			funds.setBalance(balance);
			funds.setAction(1);
			funds.setCreate_time(new Date());
			funds.setUser_id(user.getId());
			funds.setOperate_type(11);//充值
			funds.setStatus(3);//待审核
			funds.setTrade_no(trade_no);
			funds.setRemark(result);
			funds.setSummary(summary);
			fundsService.save(funds);
			//跳转到银行存管收银台
			return "redirect:"+html.toString();
		} else {
			model.addAttribute("resStatus",0);//失败
			model.addAttribute("resMsg","系统出现异常,请稍后再试！");
			return "front/charge_back";
		}
	}
	/**
	 * 网银充值
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/wyRecharge",method=RequestMethod.POST)
	public String wyRecharge(HttpServletRequest request,
			HttpServletResponse response){

		JsonReturn sr = new JsonReturn();

		String user_name = BrowseUtil.getCookie(request, response);
		String amount = request.getParameter("wy_amount");
		String pay_type = request.getParameter("pay_type");
		String bank_alias = request.getParameter("bank_alias"); //银行类型
		String account_identity  = request.getParameter("account_identity");
		String summary = "网银充值";

		//调用支付接口

		User user = userService.selectForObjectByPhone(user_name);
		Double cny = RoofNumberUtils.StringtoDouble(amount);

		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);

		List<UserBankVo> list = userBankService.selectForList(userBank);

		UserBankVo vo = list.get(0);
		String pay_method = null;
		//TESTBANK  只有pc  支持  普通卡
//		pay_method = "online_bank^" + amount + "^" +bank_alias+",DEBIT," + vo.getCard_attribute(); //银行卡
		pay_method = "online_bank^" + amount + "^BANKPAY,DEBIT," + vo.getCard_attribute(); //收银台
		//测试写TESTBANK
//		pay_method = "online_bank^" + amount + "^" + "TESTBANK" +",DEBIT," + vo.getCard_attribute();

		String trade_no = UniqueUtil.getTradeNo();
		String result = SinaUtil.createHostDeposit(user.getId(), trade_no, summary,
				cny, pay_method, user.getReg_ip(),account_identity,"");
		System.out.println(result);

		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);

		if(resultMap ==null || resultMap.get("response_code") == null || !resultMap.get("response_code").toString().equalsIgnoreCase("APPLY_SUCCESS")){

			return "";
		}
		Map dataMap =  (Map) resultMap.get("data");
		Object html =  dataMap.get("redirect_url");
		String strHtml = html.toString();
//		String redirect_url = (String)resultMap.get("redirect_url");

		//更新用户统计账户信息
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		Double balance = voF.getBalance();
		balance += cny;
		voF.setBalance(balance);
		userFundStatService.updateUserFundStatById(userFund);

		Funds funds = new Funds();

		funds.setAmount(cny);
		funds.setBalance(balance);
		funds.setAction(1);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setOperate_type(11);
		funds.setStatus(3);
		funds.setTrade_no(trade_no);
		funds.setSummary(summary);
		fundsService.save(funds);
//		request.setAttribute("strHtml", strHtml);
//		return "front/mywealth/wyPay";
		return "redirect:"+strHtml;
	}
	
	//获取充值验证
	@RequestMapping(value="/recharge/advance",method=RequestMethod.POST)
	public @ResponseBody JsonReturn getBankAdvance(HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {

		JsonReturn sr = new JsonReturn();
		String user_name = BrowseUtil.getCookie(request, response);
		String reg_source = request.getParameter("valid_code");
		String ip = request.getRemoteAddr();
		String fundsId = request.getParameter("fundsId");

		User user = userService.selectForObjectByPhone(user_name);

		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);

		List<UserBankVo> list = userBankService.selectForList(userBank);

		if(list.size() == 0){
			sr.setContent("需要绑定快捷支付");
			sr.setStatus(101);

			return sr;
		}

		Funds funds = fundsService.selectForObjectById(Long.valueOf(fundsId));

		if(funds!=null && funds.getStatus()==3){
			String result = SinaUtil.advanceHostPay(user.getId(),funds.getTrade_no(),
					funds.getTicket(), reg_source,ip);
			Map resultMap = JsonUtil.jsonToMap(result);
			if(resultMap!=null && resultMap.get("response_code")!=null){
				Object strCode =  resultMap.get("response_code");

				if(strCode != null &&strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){

					//流程标识充值成功
					funds.setStatus(1);
					//更新用户统计信息
					UserFundStat userFund = new UserFundStat();
					userFund.setUser_id(user.getId());
					UserFundStat voF = userFundStatService.selectForObjectById(userFund);
					Double balance = voF.getBalance();
					Double amount = funds.getAmount();
					balance += amount;
					voF.setBalance(balance);
					voF.setRecharged(voF.getRecharged()+amount);
					//保存
					fundsService.updateFundsById(funds);
					userFundStatService.updateUserFundStatById(voF);

					sr.setContent("充值确认成功");
					sr.setStatus(100);

				}else{
					sr.setContent("充值失败");
					sr.setStatus(101);
				}
			}else{
				sr.setContent("充值失败");
				sr.setStatus(101);
			}
		}else{
			sr.setContent("充值失败");
			sr.setStatus(101);
		}

		return sr;
	}
		
	/**
	 * 用户提现
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/withdraw",method=RequestMethod.POST)
	public @ResponseBody JsonReturn hostWithdraw(HttpServletRequest request,
			HttpServletResponse response){
		String domainName = request.getServerName();
		JsonReturn sr = new JsonReturn();
		String user_name = BrowseUtil.getCookie(request, response);
		String amount = request.getParameter("amount");

		if(amount==null || amount.equals("")){
			sr.setContent("金额有误");
			sr.setStatus(101);

			return sr;
		}

		User user = userService.selectForObjectByPhone(user_name);
		Double cny = RoofNumberUtils.StringtoDouble(amount);

		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> list = userBankService.selectForList(userBank);

		if(list.size() == 0){
			sr.setContent("需要绑定快捷支付");
			sr.setStatus(101);
			return sr;
		}
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		if(voF.getBalance()<cny){
			sr.setContent("提现金额不能大于可用余额");
			sr.setStatus(101);
			return sr;
		}

		UserBankVo vo = list.get(0);
		String card_id =  vo.getCard_id();

		String trade_no = UniqueUtil.getTradeNo();
		Double user_fee = 0.0;
		String cashdesk_addr_category = ""; //
		String summary = "PC提现";
		String reg_source = "1" ;
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否来自手机端 手机端返回手机页面
			cashdesk_addr_category = "MOBILE";
			summary = "H5提现";
			reg_source = "2";
		}
		String payto_type = "GENERAL";

		String result = SinaUtil.createHostWithdraw(user.getId(), trade_no, summary, cny, card_id,
				user_fee, payto_type, cashdesk_addr_category, user.getReg_ip(),user_name,reg_source);
		System.out.println(result);
		if (!StrUtils.emptyJudge(result)) {//result为空
			sr.setContent("网络异常，请稍后再试");
			sr.setStatus(101);
			return sr;
		}

		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);

		Object strCode =  resultMap.get("response_code");

		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent(resultMap.get("response_msg").toString());
			sr.setStatus(101);
			return sr;
		}

		Map dataMap =  (Map) resultMap.get("data");

		Object redirect_url =  dataMap.get("redirect_url");

		String strHtml = redirect_url.toString();



		//更新用户统计账户信息
		Double balance = voF.getBalance();
		balance = balance - cny;
//			voF.setBalance(balance);
//			userFundStatService.updateUserFundStatById(userFund);

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

		Funds funds = new Funds();
		String ticket = "";
		if(resultMap.get("ticket")!=null){
			ticket =  resultMap.get("ticket") +"";
		}
		funds.setAmount(cny);
		funds.setBalance(balance);
		funds.setAction(2);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setRemark(result);
		funds.setOperate_type(21);
		funds.setStatus(3);
		funds.setTrade_no(trade_no);
		funds.setTicket(ticket);
		funds.setSummary(summary);
		fundsService.save(funds);



		sr.setStatus(100);
		sr.setContent(strHtml);

		return sr;
	}

	@RequestMapping(value = "/orderInfoCheck", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn orderInfoCheck (HttpServletRequest request, HttpServletResponse response) {
		JsonReturn jr = new JsonReturn();
		String couponId = request.getParameter("couponId");
		String giftId = request.getParameter("giftId");
		String amount = request.getParameter("amount");
		String productId = request.getParameter("productId");
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		Product product=productService.selectForObjectById(Long.valueOf(productId));
		if(user.getNew_hand()==2 && product.getIs_fresh()==1){
			jr.setStatus(101);
			jr.setContent("新手标，您无法购买。");
		}
		Long day = (product.getEnd_date().getTime()-new Date().getTime())/(1000*60*60*24); //剩余天数
		if(couponId!=null && !"".equals(couponId)){
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setId(Long.valueOf(couponId));
			userCoupon.setStatus(1);

			UserCoupon uc = userCouponService.selectForObjectByCouponId(userCoupon);
			if(uc==null){
				jr.setStatus(101);
				jr.setContent("您的加息券不存在");
			}else if(uc.getMin_days()>day){
				jr.setStatus(101);
				jr.setContent("您的加息券不符使用条");
			}
		}
		if(giftId!=null && !"".equals(giftId)){
			UserGift userGift = new UserGift();
			userGift.setId(Long.valueOf(giftId));
			UserGift ug = userGiftService.selectForObjectByGiftId(userGift);
			if(ug.getMax_days()>day){
				jr.setStatus(101);
				jr.setContent("您的红包不符合使用条件");
			}
			if(ug.getMax_amount()>Double.valueOf(amount)){
				jr.setStatus(101);
				jr.setContent("您的红包不符合使用条件");
			}
		}
		return jr;
	}
	/**
	 * 到订单确认页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/buy",method=RequestMethod.POST)
	public String buy(HttpServletRequest request,
			HttpServletResponse response){
		String coinState = request.getParameter("coinState");
		String couponId = request.getParameter("couponId");
		String giftId = request.getParameter("giftId");
		String amount = request.getParameter("amount");
		String productId = request.getParameter("productId");
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		Product product=productService.selectForObjectById(Long.valueOf(productId));
		if(product==null){
			return "redirect:/product/list.action";
		}
		if(user.getNew_hand()==2 && product.getIs_fresh()==1){
			return "redirect:/product/detail/"+product.getId()+".action?error=新手标，您无法购买。";
		}
		if(amount==null || "".equals(amount) || Double.valueOf(amount)>product.getBalance()){
			
			return "redirect:/product/detail/"+product.getId()+".action?error=购买金额有误";
		}else if(product.getBalance()>Double.valueOf(amount) && Double.valueOf(amount)<100.0){
			return "redirect:/product/detail/"+product.getId()+".action?error=必须100元起投";
		}
		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user.getId());
		userFundStat=userFundStatService.selectForObjectById(userFundStat);  //用户资金统计信息
//		if(userFundStat.getBalance()<Double.valueOf(amount)){
//			return "redirect:product/detail/"+product.getId()+".action?error=余额不足";
//		}
		Long day = (product.getEnd_date().getTime()-new Date().getTime())/(1000*60*60*24); //剩余天数
		if(couponId!=null && !"".equals(couponId)){			
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setId(Long.valueOf(couponId));
			userCoupon.setStatus(1);
			
			UserCoupon uc = userCouponService.selectForObjectByCouponId(userCoupon);
			if(uc==null){
				return "redirect:/product/detail/"+product.getId()+".action?error=您的加息券不存在";
			}else if(uc.getMin_days()>day){
				return "redirect:/product/detail/"+product.getId()+".action?error=您的加息券不符使用条件";
			}
			request.setAttribute("uc", uc);
		}
		if(giftId!=null && !"".equals(giftId)){
			UserGift userGift = new UserGift();
			userGift.setId(Long.valueOf(giftId));
			UserGift ug = userGiftService.selectForObjectByGiftId(userGift);
			if(ug.getMax_days()>day){
				return "redirect:/product/detail/"+product.getId()+".action?error=您的红包不符使用条件";
			}
			if(ug.getMax_amount()>Double.valueOf(amount)){
				return "redirect:/product/detail/"+product.getId()+".action?error=您的红包不符使用条件";
			}
			request.setAttribute("ug", ug);
		}
		ProductIncome productIncome=new ProductIncome();
		productIncome.setProduct_id(product.getId());
		List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome); //获取产品利息
		request.setAttribute("userFundStat", userFundStat);
		request.setAttribute("product", product);
		request.setAttribute("productIncomeList", productIncomeList);
		request.setAttribute("amount", amount);
		request.setAttribute("coinState",coinState);//是否使用正经值  0否1是
		return "front/mywealth/order";
	}
	
	/**
	 * 标的代收
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/topay",method=RequestMethod.POST)
	public String topay(HttpServletRequest request,
			HttpServletResponse response){
		String coinState = request.getParameter("coinState");
		String couponId = request.getParameter("userCouponId");
		String giftId = request.getParameter("userGiftId");
		String amount = request.getParameter("amount");
		String productId = request.getParameter("productId");
		String useCon = request.getParameter("interest");
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		Product product=productService.selectForObjectById(Long.valueOf(productId));
		if(product==null){
			return "redirect:/product/list.action";
		}
		if(amount==null || "".equals(amount) || Double.valueOf(amount)>product.getBalance()){
			
			return "redirect:/product/detail/"+product.getId()+".action?error=购买金额有误";
		}else if(product.getBalance()>Double.valueOf(amount) && Double.valueOf(amount)<100.0){
			return "redirect:/product/detail/"+product.getId()+".action?error=必须100元起投";
		}
		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user.getId());
		userFundStat=userFundStatService.selectForObjectById(userFundStat);  //用户资金统计信息
//		
		Long day = (product.getEnd_date().getTime()-new Date().getTime())/(1000*60*60*24); //剩余天数
		Double giftAmount = 0.0;
		UserCoupon uc = null; // 用户加息券
		if(couponId!=null && !"".equals(couponId)){			
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setId(Long.valueOf(couponId));
			userCoupon.setStatus(1);
			
			 uc = userCouponService.selectForObjectByCouponId(userCoupon);
			if(uc==null){
				return "redirect:/product/detail/"+product.getId()+".action?error=您的加息券不存在";
			}else if(uc.getMin_days()>day){
				return "redirect:/product/detail/"+product.getId()+".action?error=您的加息券不符使用条件";
			}
			request.setAttribute("uc", uc);
		}
		UserGift userGift =  null;
		if(giftId!=null && !"".equals(giftId)){
			userGift = new UserGift();
			userGift.setId(Long.valueOf(giftId));
			userGift = userGiftService.selectForObjectByGiftId(userGift);
			if(userGift==null || userGift.getMax_days()>day){
				return "redirect:/product/detail/"+product.getId()+".action?error=您的红包不符使用条件";
			}
			if(userGift==null || userGift.getMax_amount()>Double.valueOf(amount)){
				return "redirect:/product/detail/"+product.getId()+".action?error=您的红包不符使用条件";
			}
			giftAmount = userGift.getAmount();
			request.setAttribute("ug", userGift);
		}
		if(giftAmount>0){//使用了红包优惠抵用，则不能使用正经值
			if(userFundStat.getBalance()<Double.valueOf(amount)-giftAmount){
				ProductIncome productIncome=new ProductIncome();
				productIncome.setProduct_id(product.getId());
				List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome); //获取产品利息
				request.setAttribute("coinState",coinState);
				request.setAttribute("userFundStat", userFundStat);
				request.setAttribute("product", product);
				request.setAttribute("productIncomeList", productIncomeList);
				request.setAttribute("amount", amount);
				request.setAttribute("error", "余额不足");
				return "front/mywealth/order";
			}
		}else if(useCon!=null && !"".equals(useCon) && "1".equals(coinState)){//使用了正经值抵用
				giftAmount= Double.valueOf(useCon);
				if (userFundStat.getBalance()<Double.valueOf(amount)-Double.valueOf(useCon)) {
					ProductIncome productIncome=new ProductIncome();
					productIncome.setProduct_id(product.getId());
					List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome); //获取产品利息
					request.setAttribute("coinState",coinState);
					request.setAttribute("userFundStat", userFundStat);
					request.setAttribute("product", product);
					request.setAttribute("productIncomeList", productIncomeList);
					request.setAttribute("amount", amount);
					request.setAttribute("error", "余额不足");
					return "front/mywealth/order";
				}

		} else if(userFundStat.getBalance()<Double.valueOf(amount)){//没有使用优惠抵现
			ProductIncome productIncome=new ProductIncome();
			productIncome.setProduct_id(product.getId());
			List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome); //获取产品利息
			request.setAttribute("coinState",coinState);
			request.setAttribute("userFundStat", userFundStat);
			request.setAttribute("product", product);
			request.setAttribute("productIncomeList", productIncomeList);
			request.setAttribute("amount", amount);
			request.setAttribute("error", "余额不足");
			return "front/mywealth/order";
		}
		String cashdesk_addr_category  = "";
		String ip = request.getRemoteAddr(); 
		String domainName = request.getServerName();
		if(domainName.equals(UrlConstant.MOBILE_URL)){
			cashdesk_addr_category = "MOBILE";
		}
		String out_trade_no = UniqueUtil.getTradeNo();
		String pay_method = "online_bank^" + amount + "^SINAPAY,DEBIT,C";
		String giftMoney = "0.0";
		if(giftAmount>0){
			giftMoney = giftAmount.toString();
		}
		
		//资金流动明细
		Funds fund = new Funds();
		fund.setRelation_id(product.getId());
		fund.setOperate_type(22);
		fund.setStatus(3);
		List<FundsVo> listFunds = fundsService.selectForList(fund);
		Double fundAmount = 0.0;

		for(int i = 0;i<listFunds.size();i++){
			//fundAmount +=
			FundsVo fVo = listFunds.get(i);
			if(fVo.getTrade_no() != null){
				String result = SinaUtil.queryHostTrade(fVo.getUser_id(), fVo.getTrade_no(), "", "");
				/*String tr = fVo.getTrade_no();
				String re = SinaUtil.queryPayResult(trade_no);*/
				//返回值
				Map resultMap = JsonUtil.jsonToMap(result);

				Object strCode =  resultMap.get("response_code");

				if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){

					continue ;
				}

				Map dataMap =  (Map) resultMap.get("data");

				String trade_list = (String) dataMap.get("trade_list");

				if(trade_list.indexOf("WAIT_PAY")>0){
					fundAmount += fVo.getAmount();
				}else if(trade_list.indexOf("TRADE_CLOSED")>0){
					fVo.setStatus(5);
					fundsService.updateFundsById(fVo);
				}
			}

		}

		if(fundAmount>product.getBalance()-Double.valueOf(amount)){
			ProductIncome productIncome=new ProductIncome();
			productIncome.setProduct_id(product.getId());
			List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome); //获取产品利息
			request.setAttribute("coinState",coinState);
			request.setAttribute("userFundStat", userFundStat);
			request.setAttribute("product", product);
			request.setAttribute("productIncomeList", productIncomeList);
			request.setAttribute("amount", amount);
			request.setAttribute("error", "标剩余可买金额为"+(product.getBalance()-fundAmount)+"元");
			return "front/mywealth/order";
		}
		//2017/12/27 add 当正经值优惠抵现启用时传入实际金额，否则传入0.0
		String activeCoin = "1".equals(coinState)?useCon:"0.0";
		String result = SinaUtil.createHostCollectTrade(user.getId(), out_trade_no, "1001", "车贷投标", Double.valueOf(amount), product.getDebt_code(), pay_method,
				giftMoney, cashdesk_addr_category, ip, activeCoin, giftId, "1", couponId);
		
		Map resultMap = JsonUtil.jsonToMap(result);

		Object strCode =  resultMap.get("response_code");

		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){

			ProductIncome productIncome=new ProductIncome();
			productIncome.setProduct_id(product.getId());
			List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome); //获取产品利息
			request.setAttribute("coinState",coinState);
			request.setAttribute("userFundStat", userFundStat);
			request.setAttribute("product", product);
			request.setAttribute("productIncomeList", productIncomeList);
			request.setAttribute("amount", amount);
			request.setAttribute("error", "支付提交失败");
			log.info("投资支付提交失败:" + resultMap.get("response_msg"));
			return "front/mywealth/order";
		}

		Map dataMap =  (Map) resultMap.get("data");

		Object redirect_url =  dataMap.get("redirect_url");

		String strHtml = redirect_url.toString();
		
		ProductBuy productBuy = new ProductBuy();
		productBuy.setUser_id(user.getId());
		productBuy.setTrade_no(out_trade_no);
		productBuy.setProduct_id(product.getId());
		productBuy.setProduct_name(product.getDebt_code());
		productBuy.setAmount(Double.valueOf(amount));
		productBuy.setReq_source(1);
		productBuy.setCreate_time(new Date());
		if(userGift!=null && userGift.getAmount()>0){
			productBuy.setAct_pay_money(Double.valueOf(amount) - userGift.getAmount());
			productBuy.setUser_gift_id(userGift.getId());
		}else if(useCon!=null && !"".equals(useCon) && "1".equals(coinState)){//开启正经值抵现
			productBuy.setAct_pay_money(Double.valueOf(amount) - Double.parseDouble(useCon));
			productBuy.setCoin(Double.parseDouble(useCon));
		}else {
			productBuy.setAct_pay_money(Double.valueOf(amount));
		}
		if(uc!=null){
			productBuy.setUser_coupon_id(uc.getCoupon_id());			
		}
		productBuy.setStatus(9);
		productBuyService.save(productBuy);
		
		Funds funds = new Funds();
		//String ticket = (String) resultMap.get("ticket");
		
		funds.setAmount(Double.valueOf(amount));
		funds.setBalance(userFundStat.getBalance()-Double.valueOf(amount));
		funds.setRelation_id(product.getId());
		funds.setAction(2);
		funds.setCreate_time(new Date());
		funds.setUser_id(user.getId());
		funds.setRemark(result);
		funds.setOperate_type(22);
		funds.setStatus(3);
		funds.setTrade_no(out_trade_no);
		funds.setTicket(strHtml);
		funds.setSummary("");
		fundsService.save(funds);
		
		System.out.println(result);
		return "redirect:"+strHtml;
	}

	/**
	 * 手动更新用户余额
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/updateBalance",method=RequestMethod.GET)
	public void updateBalance(HttpServletRequest request,
			HttpServletResponse response){
		List fundsList = fundsService.getAmountFundsList();
		if(fundsList!=null && fundsList.size()>0){
			for(int i=0;i<fundsList.size();i++){
				Funds funds = (Funds)fundsList.get(i);
				String result = SinaUtil.queryBalance(funds.getUser_id(),"127.0.0.1","");
				Map resultMap = JsonUtil.jsonToMap(result);

				Map dataMap =  (Map) resultMap.get("data");
				String balance =  dataMap.get("balance")+"" ;
				
				UserFundStat userFundStat = new UserFundStat();
				userFundStat.setUser_id(funds.getUser_id());
				userFundStat.setBalance(Double.valueOf(balance));
				System.out.println("user_id="+funds.getUser_id()+"==b="+balance);
				userFundStatService.updateUserBalance(userFundStat);
			}
		}
	}
	
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setBankService(IBankService bankService) {
		this.bankService = bankService;
	}
	
	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Autowired(required = true)
	public void setUserBankService(IUserBankService userBankService) {
		this.userBankService = userBankService;
	}
	@Autowired(required=true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	@Autowired(required=true)
	public void setCouponService(ICouponService couponService) {
		this.couponService = couponService;
	}
	@Autowired(required=true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}
	@Autowired(required=true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	@Autowired(required=true)
	public void setUserCouponService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}
	@Autowired(required=true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	@Autowired(required=true)
	public void setProductIncomeService(IProductIncomeService productIncomeService) {
		this.productIncomeService = productIncomeService;
	}
	@Autowired(required = true)
	public void setFundsService(FundsService fundsService) {
		this.fundsService = fundsService;
	}
	@Autowired(required = true)
	public void setWithdrawService(IWithdrawService withdrawService) {
		this.withdrawService = withdrawService;
	}
}
