package com.zdjf.web.user;

import com.zdjf.components.events.AuditEvent;
import com.zdjf.components.events.FundEvent;
import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.dao.product.IProductBuyDao;
import com.zdjf.dao.user.IUserCouponDao;
import com.zdjf.dao.user.IUserGiftDao;
import com.zdjf.domain.activity.Sign;
import com.zdjf.domain.fund.CoinGoods;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.CoinStreamVo;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.fund.UserFundStatVo;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.activity.ISignService;
import com.zdjf.service.fund.IBankService;
import com.zdjf.service.fund.ICoinGoodsService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductIncomeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CityMapUtil;
import com.zdjf.util.DateUtil;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.PhoneFormatCheckUtils;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.StringUtil;
import com.zdjf.util.UrlConstant;
import com.zdjf.webservice.express.JsonReturn;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class Mycontroller {

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
	private ICoinGoodsService coinGoodsSerice;
	@Autowired
	private ICoinStreamService coinStreamService;
	@Autowired
	private ISignService signService;
	
	
	/**
	 * 实名认证
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toAudit")
	public String toAudit(HttpServletRequest request,
			HttpServletResponse response){
		String domainName = request.getServerName();
		String user_name = BrowseUtil.getCookie(request,response);
		User user = userService.selectForObjectByPhone(user_name);
		if(user.getStatus()!=2 && user.getStatus()!=3){
			List bankList = bankService.selectForList(null);
			Object[] province = CityMapUtil.getProvince();
			request.setAttribute("bankList", bankList);
			request.setAttribute("province", province);
		}
		request.setAttribute("user", user);
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否来自手机端 手机端返回手机页面
			return "front/m_index/index";
		}else{
			return "front/user/real_name";
		}
	}

	/**
	 * 至开通银行存管页面（实名认证+绑定银行卡）2018-1-18 add
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "toNewAudit", method = RequestMethod.GET)
	public String toNewAudit(HttpServletRequest request,
						  HttpServletResponse response,Model model){
		String ip = RequestUtils.getIp(request);
		//获取登录用户
		String user_name = BrowseUtil.getCookie(request,response);
		User user = userService.selectForObjectByPhone(user_name);
		//查询用户是否绑过卡
		if (StrUtils.emptyJudge(user.getReal_name()) && StrUtils.emptyJudge(user.getIdcard_no()) && user.getStatus() < 2) {
			String res = SinaUtil.queryBankCard(user.getId().toString());
			if (!StrUtils.emptyJudge(res) || !(res.startsWith("{") || res.startsWith("["))) {
				model.addAttribute("errMsg", "网络异常,请稍后再试！");
				return "front/user/new_real_name";
			}
			Map restMap = JsonUtil.jsonToMap(res);
			Object resCode = restMap.get("response_code");
			if(resCode == null || !resCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
				String response_msg = (String) restMap.get("response_msg");
				model.addAttribute("errMsg", response_msg);
				return "front/user/new_real_name";
			}else{//成功
				if (res.indexOf("card_list")>0) {//绑过卡
					//判断用户原状态
					if(1 != user.getReal_name_auth()) {
						//实名认证正经值奖励
						applicationContext.publishEvent(new AuditEvent(this,user));
						//绑定银行卡正经值奖励
						applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
								userFundStatService,coinStreamService));
					}
					if (1 == user.getReal_name_auth() && user.getStatus() < 2) {
						//绑定银行卡正经值奖励
						applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
								userFundStatService,coinStreamService));
					}
					Map dataMap = (Map)restMap.get("data");
					String cardList = (String) dataMap.get("card_list");
					if (StrUtils.emptyJudge(cardList)) {//绑过卡
						String[] cardInfo = cardList.split("\\^");
						user.setReal_name_auth(1);
						user.setIdcard_auth_time(new Date());
						user.setStatus(2);
						userService.update(user);
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
						model.addAttribute("user", user);
						return "front/user/new_real_name";
					}
				}
			}
		}
		//判断用户是否完成实名+绑卡，完成则跳转到支付密码设置
		if (user.getStatus() == 2) {
			return setPayPwdRedirect(request,response,ip,user);
		}
		if (user.getStatus() == 4) {//若解绑银行卡，需要再绑定
			return "redirect:/toAddBankCard.action";
		}
		model.addAttribute("user", user);
		return "front/user/new_real_name";
	}

	/**
	 * 实名认证
	 * @param request
	 * @param response
	 * @return
	 */
		@RequestMapping(value="/audit",method=RequestMethod.POST)
		public  String auditUser(HttpServletRequest request,
				HttpServletResponse response) {
			String idcard_no = request.getParameter("id_card");
			String real_name = request.getParameter("real_name");
//			String ip = "127.0.0.1";
			String ip = request.getRemoteAddr();
			
			if(idcard_no==null || real_name==null || "".equals(idcard_no) || "".equals(real_name)){
				request.setAttribute("error", "姓名与身份证号不能为空");
				return "front/user/real_name";
			}
			try{

				String user_name = BrowseUtil.getCookie(request, response);
				User user = userService.selectForObjectByPhone(user_name);
				request.setAttribute("user", user);
				String result = SinaUtil.setRealName(user.getId(),real_name,idcard_no,ip);
				if(result.trim().isEmpty()){
					request.setAttribute("error", "请求异常请重新提交");
					return "front/user/real_name";
				}
				
				Map resultMap = JsonUtil.jsonToMap(result);
				
				String strCode = (String) resultMap.get("response_code");
				if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
					String response_msg = (String) resultMap.get("response_msg");
					request.setAttribute("error", response_msg);
					return "front/user/real_name";
				}
				user.setIdcard_no(idcard_no);
				user.setReal_name(real_name);
				//调用新浪支付接口  实名认证进行
				user.setReal_name_auth(1);
				user.setIdcard_auth_time(new Date());
				userService.update(user);
				
				applicationContext.publishEvent(new AuditEvent(this,user));
				return "redirect:toAudit.action";
//				model.addAllAttributes(CommonUtils.packageResult(true, "审核通过成功!"));
			}catch (Exception e){
				e.printStackTrace();
			}
			
			return "";

		}
		
	@RequestMapping(value="/getCity",method=RequestMethod.POST)
	public @ResponseBody String getCity(HttpServletRequest request,
		HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String province = request.getParameter("province");
//			String province = "浙江";
		String[] citys = CityMapUtil.getCity(province);
		JsonUtil ju = new JsonUtil();
		return ju.collectionToJson(citys);
	}

	/**
	 * 发起实名认证+绑定银行卡
	 * @param request
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/realNameAndBindBankCard",method=RequestMethod.POST)
	@ResponseBody
	public JsonReturn realNameAndBindBankCard(HttpServletRequest request,HttpServletResponse response,
											  @RequestParam Map<String,Object> param){
		//构建返回对象
		JsonReturn jr = new JsonReturn();

		//获取请求参数
		String userRealName = (String) param.get("userRealName");
		String userIdCard = (String) param.get("userIdCard");
		String bankAccount = (String) param.get("bank_accounts");
		String province = (String) param.get("province");
		String city = (String) param.get("city");
		String bankCard = (String) param.get("bankCard");
		String phone = (String) param.get("phone");
		String ip = RequestUtils.getIp(request);

		//获取登录用户
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		//查询用户绑卡信息
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> list = userBankService.selectForList(userBank);
		if(list.size()>0){
			jr.setStatus(101);
			jr.setContent("您已绑定银行卡");
			return jr;
		}
		//调用上海银行存管接口
		String request_no = "zdjfu" + new Date().getTime() + StringUtil.getRandom();
		String result = SinaUtil.bindBankCard(user.getId(),request_no,bankAccount,bankCard,"C",
				phone,province,city,ip,user.getReal_name_auth(),userRealName,userIdCard);
		Map resultMap = JsonUtil.jsonToMap(result);
		String strCode = (String) resultMap.get("response_code");

		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			String response_msg = (String) resultMap.get("response_msg");
			jr.setStatus(101);
			jr.setContent(response_msg);
			return jr;
		}else{//成功
			String ticket = (String) resultMap.get("ticket");

			//判断老用户是否实名认证过
			if (user.getReal_name_auth() != 1) {
				user.setReal_name(userRealName);
				user.setIdcard_no(userIdCard);
				user.setSex(StrUtils.idcardToSex(userIdCard));
			}
			user.setStatus(1);
			userService.update(user);

			UserBank addUb = new UserBank();
			addUb.setUser_id(user.getId());
			addUb.setBank_alias(bankAccount);
			addUb.setBank_no(bankCard);
			addUb.setPhone(phone);
			addUb.setCreate_time(new Date());
			addUb.setStatus(2);//处理中
			addUb.setType(1);//快捷卡
			addUb.setTicket(ticket);
			addUb.setProvince(province);
			addUb.setCity(city);
			addUb.setCard_attribute("C");
			addUb.setRequest_no(request_no);
			//保存银行
			userBankService.save(addUb);

			jr.setStatus(100);
			jr.setContent(ticket);
			return jr;
		}
	}

	/**
	 * 跳转到新浪设置支付密码页面
	 * @param request
	 * @param response
	 * @param ip
	 * @param user
	 * @return
	 */
	public String setPayPwdRedirect(HttpServletRequest request,HttpServletResponse response,String ip, User user) {

		String domainName = request.getServerName();
		int type =0;
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否手机端
			type = 1;
		}

		String res = SinaUtil.setPayPassword(user.getId(),type,ip,user.getUser_name(),"1");
		JSONObject jsonObject = JSONObject.fromObject(res);
		if(jsonObject.get("response_code").toString().equals("APPLY_SUCCESS")){
			String redirect_urlJsonStr = jsonObject.get("data").toString();
			if(redirect_urlJsonStr==null || "null".equals(redirect_urlJsonStr) || "".equals(redirect_urlJsonStr)){
				return redirect_urlJsonStr;
			}
			JSONObject jsonObject1 = JSONObject.fromObject(redirect_urlJsonStr);

			return "redirect:"+jsonObject1.get("redirect_url");
		}else {
			String response_code = jsonObject.get("response_code").toString();
			if(("PAYPWD_HAS_SET".equals(response_code) || "REQUEST_BANK_FORM_FAIL".equals(response_code)) && 2 == user.getStatus()){
				user.setStatus(3);
				userService.update(user);
			}
			String response_msg = jsonObject.get("response_msg").toString();
			request.setAttribute("response_msg", response_msg);
			return "front/pw_error";
		}
	}
	/**
	 * 实名认证+绑定银行卡推进
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/realNameAndBankCardAdvance",method=RequestMethod.POST)
	public String realNameAndBankCardAdvance(@RequestParam Map<String,Object> param, HttpServletRequest request,
									  HttpServletResponse response,Model model){
		//获取请求参数
		String ticket = (String) param.get("ticket");
		String valid_code = (String) param.get("valid_code");
		String ip = RequestUtils.getIp(request);

		//获取登录用户信息
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		List bankList = bankService.selectForList(null);
		Object[] provinces = CityMapUtil.getProvince();
		model.addAttribute("bankList", bankList);
		model.addAttribute("province", provinces);
		model.addAttribute("user", user);
		model.addAttribute("ticket", ticket);
		if (!StrUtils.emptyJudge(valid_code) || !StrUtils.emptyJudge(ticket)) {
			model.addAttribute("errMsg", "请先获取短信验证码");
			return "front/user/new_real_name";
		}

		//查询用户预绑卡信息
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setTicket(ticket);
		List<UserBankVo> list = userBankService.selectForList(userBank);

		String result = SinaUtil.bindBankCardAdvance(ticket,valid_code,ip);
		Map resultMap = JsonUtil.jsonToMap(result);

		String strCode = (String) resultMap.get("response_code");
		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			String response_msg = (String) resultMap.get("response_msg");
			model.addAttribute("errMsg", response_msg);
			return "front/user/new_real_name";
		}else{//成功
			//判断老用户是否实名认证过
			if (user.getReal_name_auth() != 1) {
				user.setReal_name_auth(1);//已实名
				user.setIdcard_auth_time(new Date());
				//实名认证正经值奖励
				applicationContext.publishEvent(new AuditEvent(this,user));
				//绑定银行卡正经值奖励
				applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
						userFundStatService,coinStreamService));
			} else {//已实名
				//绑定银行卡正经值奖励
				applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
						userFundStatService,coinStreamService));
			}
			//更新用户信息
			user.setStatus(2);
			userService.update(user);

			String card_id = (String) resultMap.get("card_id");
			userBank.setCard_id(card_id);
			userBank.setId(list.get(0).getId());
			userBank.setStatus(1);
			//更新用户绑定银行卡信息
			userBankService.updateUserBankById(userBank);

			return setPayPwdRedirect(request,response,ip,user);
		}
	}

	/**
	 * @description 开通银行存管，绑卡跳至上海银行页面操作
	 * @author Andrew
	 * @date_time 2018-03-14 09:44
	 * @param request
	 * @param response
	 * @param model
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/openBankDeposit" ,method=RequestMethod.POST)
	public String openBankDeposit(HttpServletRequest request,
											 HttpServletResponse response,Model model){
		//获取请求参数
		String idcard_no = request.getParameter("idcard_no");
		String real_name = request.getParameter("real_name");
		String ip = RequestUtils.getIp(request);

		//获取登录用户信息
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		model.addAttribute("user", user);

		//若用户绑过卡未设置支付密码
		if (2 == user.getStatus()) {
			return setPayPwdRedirect(request,response,ip,user);//跳转到设置支付密码
		}

		String result = SinaUtil.bindBankCard(user.getId(),real_name,idcard_no,"1");

		if (!StrUtils.emptyJudge(result) || !(result.startsWith("{") || result.startsWith("["))) {
			model.addAttribute("errMsg", "网络异常,请稍后再试！");
			return "front/user/new_real_name";
		}
		Map resultMap = JsonUtil.jsonToMap(result);

		Object strCode = resultMap.get("response_code");
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			Object response_msg = resultMap.get("response_msg");
			model.addAttribute("errMsg", response_msg);
			return "front/user/new_real_name";
		}else{//成功
			Map dataMap = (Map)resultMap.get("data");
			user.setReal_name(real_name);
			user.setIdcard_no(idcard_no);
			user.setSex(StrUtils.idcardToSex(idcard_no));
			user.setBirthday(StrUtils.idCardNoToBirthDay(idcard_no).substring(4,8));
			user.setCreate_time(new Date());
			userService.update(user);
			return "redirect:"+dataMap.get("redirect_url");
		}
	}

	@RequestMapping(value="/bindBankCard",method=RequestMethod.POST)
	public @ResponseBody JsonReturn bindBankCard(HttpServletRequest request,
			HttpServletResponse response){
		String acceptHeader = request.getHeader("X-Requested-With"); 
		if (acceptHeader!=null && !"".equals(acceptHeader) ) {			
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String bank_accounts = request.getParameter("bank_accounts");
			String phone = request.getParameter("phone");
			String bankCard = request.getParameter("bankCard");
			String ip = request.getRemoteAddr();
			String real_name = RequestUtils.getReqString(request,"real_name");
			String idcard_no = RequestUtils.getReqString(request,"idcard_no");
//			String ip = "127.0.0.1";
			JsonReturn jr = new JsonReturn();
			if(PhoneFormatCheckUtils.isChinaPhoneLegal(phone)==false){
				jr.setStatus(101);
				jr.setContent("手机号码错误！");
				return jr;
			}
			if(bankCard==null || "".equals(bankCard) || bank_accounts==null || "".equals(bank_accounts) 
					|| province==null || "".equals(province) || city==null || city.equals("")){
				jr.setStatus(102);
				jr.setContent("银行账号信息不能为空！");
				return jr;
			}
			String user_name = BrowseUtil.getCookie(request, response);
			User user = userService.selectForObjectByPhone(user_name);
			String request_no = "zdjfu" + new Date().getTime() + StringUtil.getRandom();
			String result = SinaUtil.bindBankCard(user.getId(),request_no,bank_accounts,bankCard,"C",phone,province,city,ip,user.getReal_name_auth(),real_name,idcard_no);
			Map resultMap = JsonUtil.jsonToMap(result);
			
			String strCode = (String) resultMap.get("response_code");
			if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
				String response_msg = (String) resultMap.get("response_msg");
				jr.setStatus(103);
				jr.setContent(response_msg);
				return jr;
			}else{
				String response_msg = (String) resultMap.get("ticket");
				jr.setStatus(100);
				jr.setContent(response_msg);
				return jr;
			}
		}
		
		return null;
	}
	
	@RequestMapping(value="/bindBankCardAdvance",method=RequestMethod.POST)
	public String bindBankCardAdvance(HttpServletRequest request,
			HttpServletResponse response){
		String ticket = request.getParameter("ticket");
		String valid_code = request.getParameter("valid_code");
		String phone = request.getParameter("phone");
		String bankCard = request.getParameter("bankCard");
		String bank_accounts = request.getParameter("bank_accounts");
		String ip = request.getRemoteAddr(); 
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		
		if(ticket==null || "".equals(ticket)){
			request.setAttribute("error", "请求异常请重新提交");
			return "front/user/real_name";
		}

		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		
		List<UserBankVo> list = userBankService.selectForList(userBank);
		List bankList = bankService.selectForList(null);
		Object[] provinces = CityMapUtil.getProvince();
		request.setAttribute("bankList", bankList);
		request.setAttribute("province", provinces);
		request.setAttribute("user", user);
		if(list.size()>0){
			request.setAttribute("error", "银行卡已经存在");
			return "front/user/real_name";
		}
		String result = SinaUtil.bindBankCardAdvance(ticket,valid_code,ip);
		Map resultMap = JsonUtil.jsonToMap(result);
		
		String strCode = (String) resultMap.get("response_code");
		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			String response_msg = (String) resultMap.get("response_msg");
			request.setAttribute("error", response_msg);
			request.setAttribute("ticket", ticket);
			return "front/user/real_name";
		}else{//绑定银行卡成功
			UserFundStat userFundStat=new UserFundStat();
			userFundStat.setUser_id(user.getId());
			userFundStat=userFundStatService.selectForObjectById(userFundStat);  //用户资金统计信息
//			userFundStat.setCoin_balance(userFundStat.getCoin_balance()+15);
//			userFundStatService.updateUserFundStatById(userFundStat);
			//绑定银行卡正经值奖励 2018-1-17 edit
			//正经值
			applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
					userFundStatService,coinStreamService));

			user.setStatus(2);
			userService.update(user);
			String card_id = (String) resultMap.get("card_id");
			
			userBank.setCard_id(card_id);
			userBank.setUser_id(user.getId());
			userBank.setBank_alias(bank_accounts);
			userBank.setBank_no(bankCard);
			userBank.setPhone(phone);
			userBank.setCreate_time(new Date());
			userBank.setStatus(1);
			userBank.setType(1);//快捷卡
			userBank.setTicket(ticket);
			userBank.setProvince(province);
			userBank.setCity(city);
			userBank.setCard_attribute("C");
			userBank.setRequest_no(user.getPay_account());//
			//保存银行
			userBankService.save(userBank);
		}
		return "redirect:toAudit.action";
	}
	
	
	/**
	 * 到设置密码页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/pay_password")
	public String pay_password(HttpServletRequest request,
							   HttpServletResponse response){
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		String ip = request.getRemoteAddr();
		String domainName = request.getServerName();
		int type =0;
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否手机端
			type = 1;
		}

		String result = SinaUtil.setPayPassword(user.getId(),type,ip,user.getUser_name(),"1");
		JSONObject jsonObject = JSONObject.fromObject(result);
		if(jsonObject.get("response_code").toString().equals("APPLY_SUCCESS")){
			String redirect_urlJsonStr = jsonObject.get("data").toString();
			if(redirect_urlJsonStr==null || "null".equals(redirect_urlJsonStr) || "".equals(redirect_urlJsonStr)){
				return redirect_urlJsonStr;
			}
			JSONObject jsonObject1 = JSONObject.fromObject(redirect_urlJsonStr);

			return "redirect:"+jsonObject1.get("redirect_url");
		}else {
			String response_code = jsonObject.get("response_code").toString();
			if(("PAYPWD_HAS_SET".equals(response_code) || "REQUEST_BANK_FORM_FAIL".equals(response_code)) && 2 == user.getStatus()){
				user.setStatus(3);
				userService.update(user);
			}
			String response_msg = jsonObject.get("response_msg").toString();
			request.setAttribute("response_msg", response_msg);
			return "front/pw_error";
		}
	}

	/**
	 * 到修改支付密码页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/modPayPassword", method = RequestMethod.GET)
	public String modPayPassword(HttpServletRequest request,
							   HttpServletResponse response,Model model){
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		String ip = request.getRemoteAddr();
		String domainName = request.getServerName();
		int type =0;
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否手机端
			type = 1;
		}

		String result = SinaUtil.modifyPayPassword(user.getId(),type,ip,"1","change");
		if(result == null || result.trim().isEmpty()){
			model.addAttribute("response_msg", "网络异常,请稍后再试！");
			return "front/mywealth/myaccount";
		}
		JSONObject jsonObject = JSONObject.fromObject(result);
		if(jsonObject.get("response_code").toString().equals("APPLY_SUCCESS")){
			String redirect_urlJsonStr = jsonObject.get("data").toString();
			if(redirect_urlJsonStr==null || "null".equals(redirect_urlJsonStr) || "".equals(redirect_urlJsonStr)){
				return redirect_urlJsonStr;
			}
			JSONObject jsonObject1 = JSONObject.fromObject(redirect_urlJsonStr);

			return "redirect:"+jsonObject1.get("redirect_url");
		}else {
			String response_msg = jsonObject.get("response_msg").toString();
			model.addAttribute("response_msg", response_msg);
			return "front/mywealth/myaccount";
		}
	}

	/**
	 * 到找回支付密码页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/backPayPassword", method = RequestMethod.GET)
	public String backPayPassword(HttpServletRequest request,
								 HttpServletResponse response,Model model){
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		String ip = request.getRemoteAddr();
		String domainName = request.getServerName();
		int type =0;
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否手机端
			type = 1;
		}

		String result = SinaUtil.findPayPassword(user.getId(),type,"1","back");
		if(result == null || result.trim().isEmpty()){
			model.addAttribute("response_msg", "网络异常,请稍后再试！");
			return "front/mywealth/myaccount";
		}
		JSONObject jsonObject = JSONObject.fromObject(result);
		if(jsonObject.get("response_code").toString().equals("APPLY_SUCCESS")){
			String redirect_urlJsonStr = jsonObject.get("data").toString();
			if(redirect_urlJsonStr==null || "null".equals(redirect_urlJsonStr) || "".equals(redirect_urlJsonStr)){
				return redirect_urlJsonStr;
			}
			JSONObject jsonObject1 = JSONObject.fromObject(redirect_urlJsonStr);

			return "redirect:"+jsonObject1.get("redirect_url");
		}else {
			String response_msg = jsonObject.get("response_msg").toString();
			model.addAttribute("response_msg", response_msg);
			return "front/mywealth/myaccount";
		}
	}

	/**
	 * 通过金额获取用户加息券与红包
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getUserCoupon",method=RequestMethod.POST)
	public @ResponseBody String getUserCoupon(HttpServletRequest request,
		HttpServletResponse response){
		String acceptHeader = request.getHeader("X-Requested-With"); 
		String amount = request.getParameter("amount");
		String product_id = request.getParameter("product_id");
		if (acceptHeader!=null && !"".equals(acceptHeader) ) {
			String user_name = BrowseUtil.getCookie(request, response);
			User user = userService.selectForObjectByPhone(user_name);
			Product product=productService.selectForObjectById(Long.valueOf(product_id)); 
			long endDate = product.getEnd_date().getTime();
			long nowDate = new Date().getTime();
			Long day = (endDate-nowDate)/(1000*60*60*24);
			if(day>0){	
				if(Double.valueOf(amount)>product.getBalance()){
					amount = product.getBalance().toString();
				}
				Map hashMap = new HashMap();
				hashMap.put("min_days", day);
				hashMap.put("user_id", user.getId());
				hashMap.put("status", 1);
				hashMap.put("end_date", DateUtil.addDay(new Date(), -1));
				List userCouponList = couponService.getUserCouponList(hashMap); //查询用户加息券
				hashMap.put("amount", amount);
				List userGiftList = userGiftService.getUserGiftList(hashMap);
				
				hashMap = new HashMap();
				hashMap.put("userCouponList", userCouponList);
				hashMap.put("userGiftList", userGiftList);
				String str = JsonUtil.objectTojson(hashMap);
				return str;
			}
		}
		return null;
	}
	
	/**
	 * 我的财富
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mywealth")
	public String mywealth(HttpServletRequest request,
			HttpServletResponse response){
		String user_name=BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		/**
		 * 2018-1-12 ADD 获取登录用户签到次数
		 */
		//当日签到记录
		Sign sg = new Sign();
		sg.setUser_id(user.getId());
		sg.setCreate_date(DateUtil.formatDate(new Date()));
		sg.setStatus(1);
		List<Sign> list = signService.selectForList(sg);
		//当日已签到次数
		int signUsedNum = list.size();
		if (signUsedNum == 0 || user.getSign_num() > 0) {//当日未签到，有一次基础签到机会
			request.setAttribute("isSignTimes", "1");//1 表示有签到机会
		} else if (user.getSign_num() == 0) {//当日已签到
			request.setAttribute("isSignTimes", "0");//0 表示无签到机会
		}
		if(user.getId()==88888888l){
			String type = request.getParameter("type");
			if(type==null || "".equals(type)){
				type = "101";
			}
			String ip = "117.149.16.71";
			String result = SinaUtil.queryBalance(user.getId(),ip,type);
			Map resultMap = JsonUtil.jsonToMap(result);
			if(resultMap.get("response_code").equals("APPLY_SUCCESS")){				
				Map dataMap =  (Map) resultMap.get("data");
				Object balance =  dataMap.get("balance");
				request.setAttribute("gnh_balance", balance+"");
				request.setAttribute("type", type);
			}
		}else{
			String ip = request.getRemoteAddr();
			userFundStatService.updateUserBalance(user.getId(),ip);
		}
		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user.getId());
		userFundStat=userFundStatService.selectForObjectById(userFundStat);  //用户资金统计信息
		if(null==userFundStat){
			userFundStat=new UserFundStat();
			userFundStat.setUser_id(user.getId());
			userFundStat.setBalance(0.00);
			userFundStat.setCoin_balance(0.00);
			userFundStat.setCoin_cost(0.00);
			userFundStat.setCoin_invest(0.00);
			userFundStat.setCoin_total(0.00);
			userFundStat.setIncomed(0.00);
			userFundStat.setInvest_frequency(0);
			userFundStat.setInvest_sum(0.00);
			userFundStat.setLocked_money(0.00);
			userFundStat.setPend_income(0.00);
			userFundStat.setPend_return(0.00);
			userFundStat.setPend_withdraw(0.00);
			userFundStat.setRecharged(0.00);
			userFundStat.setReturned(0.00);
			userFundStat.setWithdraw_coupons(0);
			userFundStat.setWithdrawed(0.00);
			userFundStatService.save(userFundStat);
		}
		int giftCount= (int) userGiftService.selectGiftCountByUid(user.getId());    //可用红包数
		Double giftUsedNum = userGiftService.getgiftUsedNum(user.getId()); //红包使用金额
		int couponCount= (int) userCouponService.selectCouponCountByUid(user.getId());//可用加息券
		List userBuyList = productBuyService.selectUserBuyList(user.getId()); // 获取收益
		//2018-03-30 Andrew mod 回款日历增加从2017的数据开始显示
//		Date beginDate = DateUtil.addDate(new Date(), -90);
		Date beginDate = DateUtil.yearBegin(DateUtil.parseDate("2017-01-01"));
		Date endDate = DateUtil.addDate(new Date(), 360);
		Map hashMap = new HashMap();
		hashMap.put("beginDate", beginDate);
		hashMap.put("endDate", endDate);
		hashMap.put("user_id", user.getId());
		List userMonthIncomeList = productIncomeService.selectUserMonthIncomeList(hashMap); //获取利息回款日历
		
		beginDate = DateUtil.monthBegin(new Date());
		endDate = DateUtil.addDate(DateUtil.monthEnd(new Date()),1);
		
		List todayIncomeList = new ArrayList();
		List productIds = new ArrayList();
		List userThisMonthIncomeList = productIncomeService.selectUserMonthIncomeList(hashMap); //获取当月利息回款日历
		request.setAttribute("userThisMonthIncomeList", userThisMonthIncomeList);
		if(userThisMonthIncomeList!=null && userThisMonthIncomeList.size()>0){
			for(int i=0;i<userThisMonthIncomeList.size();i++){
				ProductBuyIncome pb = (ProductBuyIncome)userThisMonthIncomeList.get(i);
				if(DateUtil.formatDate(pb.getEnd_date()).equals(DateUtil.formatDate(new Date()))){
					todayIncomeList.add(pb);
				}
				productIds.add(pb.getProduct_id());
			}
		}
		Map proHashMap = new HashMap();
		proHashMap.put("productIds", productIds);
		if(productIds!=null && productIds.size()>0){			
			List productList = productService.getProductList(proHashMap);
			proHashMap = new HashMap();
			if(productList!=null && productList.size()>0){
				for(int i=0;i<productList.size();i++){
					Product p = (Product)productList.get(i);
					proHashMap.put(p.getId(), p);
				}
			}
			request.setAttribute("proHashMap", proHashMap);
		}
		request.setAttribute("todayIncomeList", todayIncomeList);
		beginDate = DateUtil.yearBegin(new Date());
		endDate = DateUtil.yearEnd(new Date());
		hashMap.put("beginDate", beginDate);
		hashMap.put("endDate", endDate);
		List userMonthBuyList = productIncomeService.selectUserBuyIncomeByYear(hashMap); //获取本年的回款日历
		String yearDate = "";
		Double maxAmount = 5.0;
		Double allAmount = 0.0;
		if(userMonthBuyList==null || userMonthBuyList.size()==0){
			yearDate = "0,0,0,0,0,0,0,0,0,0,0,0";
		}else{
			Map yearHashMap = new HashMap();
			yearHashMap.put(DateUtil.year(beginDate)+"-01", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-02", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-03", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-04", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-05", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-06", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-07", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-08", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-09", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-10", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-11", "0");
			yearHashMap.put(DateUtil.year(beginDate)+"-12", "0");
			for(int i=0;i<userMonthBuyList.size();i++){
				Map cmap = (Map)userMonthBuyList.get(i);
				yearHashMap.put(cmap.get("date"), cmap.get("incomed"));
				
				if(cmap.get("incomed")!=null){
					allAmount += Double.valueOf(cmap.get("incomed")+"");			
				}
				if(cmap.get("incomed")!=null && Double.valueOf(cmap.get("incomed")+"")>maxAmount){
					maxAmount = Double.valueOf(cmap.get("incomed")+"");
				}
			}
			for(int i=1;i<13;i++){
				if(i<10){
					yearDate += yearHashMap.get(DateUtil.year(beginDate)+"-0"+i)+",";
				}else{
					yearDate += yearHashMap.get(DateUtil.year(beginDate)+"-"+i)+","; 
				}
			}
		}
		request.setAttribute("yearDate", yearDate);
		request.setAttribute("maxAmount", maxAmount);
		request.setAttribute("allAmount", allAmount);
		Map hkhashMap = new HashMap();
		String monthStr = DateUtil.formatDateYm(new Date());
		Double monthAmout = 0.0;
		String today = DateUtil.formatDate(new Date());
		Double todayAmout = 0.0;
		Integer todaycount =0;
//		Map todayhashMap = new HashMap();
//		if(userMonthBuyList!=null && userMonthBuyList.size()>0){
//			for(int i=0;i<userMonthBuyList.size();i++){
//				ProductBuy pb = (ProductBuy)userMonthBuyList.get(i);
//				String hkStatus = "待回款";
//				if(pb.getStatus()==2){
//					hkStatus = "已回款";
//				}
//				if(pb.getStatus()==1 && DateUtil.formatDateYm(pb.getEnd_date()).equals(monthStr)){
//					monthAmout = monthAmout + pb.getAmount() + pb.getIncomed();
//				}
//				if(DateUtil.formatDate(pb.getEnd_date()).equals(today)){
//					todayAmout = todayAmout + pb.getAmount() + pb.getIncomed();
//					todaycount = todaycount+1;
////					todayhashMap.put(key, value);
//				}
//				hkhashMap.put(DateUtil.formatDate(pb.getEnd_date()), hkStatus);
//			}
//		}
		if(userMonthIncomeList!=null && userMonthIncomeList.size()>0){
			for(int i=0;i<userMonthIncomeList.size();i++){
				ProductBuyIncome pb = (ProductBuyIncome)userMonthIncomeList.get(i);
				String hkStatus = "待回款";
				if(pb.getStatus()==1){
					hkStatus = "已回款";
				}
				if(pb.getStatus()==-1 && DateUtil.formatDateYm(pb.getEnd_date()).equals(monthStr) && pb.getIs_return_amount()==2){
					monthAmout = monthAmout + pb.getIncome();
				}else if(pb.getStatus()==-1 && DateUtil.formatDateYm(pb.getEnd_date()).equals(monthStr) && pb.getIs_return_amount()==1){
					monthAmout = monthAmout + pb.getIncome() + pb.getAmount();
				}
				hkhashMap.put(DateUtil.formatDate(pb.getEnd_date()), hkStatus);
			}
		}
		//2017-12-29 add 修正累计收益金额不正确
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
		Double totalIncome = userFundStat.getIncomed() + gift_totle + coin_total + userFundStat.getPend_income();
		
		request.setAttribute("totalIncome",totalIncome);
		request.setAttribute("userFundStat", userFundStat);
		request.setAttribute("giftCount", giftCount);
		request.setAttribute("user", user);
		request.setAttribute("couponCount", couponCount);
		request.setAttribute("userBuyList", userBuyList);
		request.setAttribute("couponCount", couponCount);
		request.setAttribute("hkhashMap", hkhashMap);
		request.setAttribute("monthAmout", monthAmout);
		request.setAttribute("monthStr", monthStr);
		request.setAttribute("showType", "mywealth");
		request.setAttribute("giftUsedNum", gift_totle + coin_total);
		request.setAttribute("today", today);
		request.setAttribute("todayAmout", todayAmout);
		request.setAttribute("todaycount", todaycount);
		return "front/mywealth/wealth";
	}

	/**
	 * @description 按日期查询当月用户收益信息 提供给pc端
	 * @author Andrew
	 * @date_time 2018-03-28 14:10
	 * @param
	 * @return com.zdjf.webservice.express.JsonReturn
	 */
	@ResponseBody
	@RequestMapping(value="/queryUserIncomeByDate",method=RequestMethod.POST)
	public JsonReturn queryUserIncomeByDate(HttpServletRequest request, HttpServletResponse response) {
		//构造返回对象
		JsonReturn jsonRtn = new JsonReturn();
		//获取参数
		String qryDateStr = request.getParameter("qryDate");//日期格式：yyyy-MM-dd
		String currPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("limit");
		if (!StrUtils.emptyJudge(qryDateStr)) {
			jsonRtn.setContent("查询日期不能为空");
			jsonRtn.setStatus(101);
			return jsonRtn;
		}
		Date qryDate = DateUtil.parseDateTimes(qryDateStr,"yyyy-MM-dd");
		//获取登录用户信息
		String userName = BrowseUtil.getCookie(request, response);
		if (!StrUtils.emptyJudge(userName)) {
			jsonRtn.setContent("用户未登录");
			jsonRtn.setStatus(102);
			return jsonRtn;
		}
		//构建返回对象
		Map<String, Object> dataMap = new HashMap<>();
		User user = userService.selectForObjectByPhone(userName);
		//按日期查询用户当月应收
		Double totalIncome = productIncomeService.queryUserIncomeByMonth(qryDate,null,user.getId());
		dataMap.put("totalIncome",RoofNumberUtils.formatAmt(totalIncome==null?0.0:totalIncome));
		//按日期查询用户当月待收
		Double willIncome = productIncomeService.queryUserIncomeByMonth(qryDate,"-1",user.getId());
		dataMap.put("willIncome",RoofNumberUtils.formatAmt(willIncome==null?0.0:willIncome));
		//按日期分页查询当日回款
		Page p = new Page();
		Long pageNum = Long.valueOf(currPage == null ? "1" : currPage);
		p.setCurrentPage(pageNum);
		if (Long.valueOf(pageNum) > 1) {
			p.setStart((pageNum-1)*Long.valueOf(pageSize));
		}

		p.setLimit(Long.parseLong(pageSize));
		Map<String, Object> para = new HashMap<>();
		para.put("pagename","queryUserIncomeList");
		para.put("total","queryUserIncomeListCount");
		para.put("userId",user.getId());
		para.put("qryDate",qryDate);
		para.put("status",null);
		para.put("ordType",1);//排序类型 1：按购买日期正序 2：按还款日期正序
		//分页查询用户待回款记录
		Page refundListPage = productIncomeService.page(p,para);
		dataMap.put("pageCount",refundListPage.getPageCount());//总页数
		dataMap.put("currentPage",refundListPage.getCurrentPage());//当前页
		List<Map<String, Object>> refundpage = (List<Map<String, Object>>)refundListPage.getDataList();
		dataMap.put("refundList",refundpage);
		//查询用户当日所有回款记录
		List<Map<String, Object>> refundList = productIncomeService.queryUserIncomeList(qryDate,"",user.getId(),"1");
		//用户当日回款总额
		double totalAmt = 0.0;
		if(refundList != null && refundList.size() > 0) {
			for (Map<String, Object> data : refundList) {
				totalAmt +=  Double.parseDouble(data.get("totalAmt").toString());
			}
			dataMap.put("totalAmt", RoofNumberUtils.formatAmt(totalAmt));
			dataMap.put("totalNum",refundList.size());
		} else {
			dataMap.put("totalAmt",0.0);
			dataMap.put("totalNum",0);
		}

		jsonRtn.setStatus(100);
		jsonRtn.setContent(dataMap);
		return jsonRtn;
	}

	/**
	 * @description 查询用户待回款明细
	 * @author Andrew
	 * @date_time 2018-03-28 16:57
	 * @param request
	 * @param response
	 * @return com.zdjf.webservice.express.JsonReturn
	 */
	@ResponseBody
	@RequestMapping(value="/queryUserWillRefundList",method=RequestMethod.POST)
	public JsonReturn queryUserWillRefundList(HttpServletRequest request, HttpServletResponse response) {
		//构造返回对象
		JsonReturn jsonRtn = new JsonReturn();

		//获取参数
		String currPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("limit");

		//获取登录用户信息
		String userName = BrowseUtil.getCookie(request, response);
		if (!StrUtils.emptyJudge(userName)) {
			jsonRtn.setContent("用户未登录");
			jsonRtn.setStatus(102);
			return jsonRtn;
		}
		User user = userService.selectForObjectByPhone(userName);

		Page p = new Page();
		Long pageNum = Long.valueOf(currPage == null ? "1" : currPage);
		p.setCurrentPage(pageNum);
		if (Long.valueOf(pageNum) > 1) {
			p.setStart((pageNum-1)*Long.valueOf(pageSize));
		}

		p.setLimit(Long.parseLong(pageSize));

		Map<String, Object> para = new HashMap<>();
		para.put("pagename","queryUserIncomeList");
		para.put("total","queryUserIncomeListCount");
		para.put("userId",user.getId());
		para.put("status",-1);
		para.put("ordType",2);//排序类型 1：按购买日期正序 2：按还款日期正序
		//分页查询用户待回款记录
		Page willRefundListPage = productIncomeService.page(p,para);
		jsonRtn.setStatus(100);
		jsonRtn.setContent(willRefundListPage);
		return jsonRtn;
	}

	/**
	 * 通过json获取用户某一天的回款项目
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getUserDayIncome",method=RequestMethod.POST)
	public @ResponseBody String getUserDayIncome(HttpServletRequest request,
		HttpServletResponse response){
		String dateStr = request.getParameter("dateStr");
		String user_name=BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		
		Date beginDate =new Date();
		Date endDate = DateUtil.addDate(new Date(), 1);
		Map hashMap = new HashMap();
		hashMap.put("beginDate", DateUtil.formatDate(beginDate));
		hashMap.put("endDate", DateUtil.formatDate(endDate));
		hashMap.put("user_id", user.getId());
		List userDayIncomeList = productIncomeService.selectUserMonthIncomeList(hashMap); //获取利息回款日历
		List productIds = new ArrayList();
		if(userDayIncomeList!=null && userDayIncomeList.size()>0){
			for(int i=0;i<userDayIncomeList.size();i++){
				ProductBuyIncome pb = (ProductBuyIncome)userDayIncomeList.get(i);
				
				productIds.add(pb.getProduct_id());
			}
		}
		Map proHashMap = new HashMap();
		proHashMap.put("productIds", productIds);
		List productList = productService.getProductList(proHashMap);
		proHashMap = new HashMap();
		if(productList!=null && productList.size()>0){
			for(int i=0;i<productList.size();i++){
				Product p = (Product)productList.get(i);
				proHashMap.put(p.getId(), p.getProduct_name());
			}
		}
		JSONArray jsonArray = new JSONArray();  
		if(userDayIncomeList!=null && userDayIncomeList.size()>0){
			for(int i=0;i<userDayIncomeList.size();i++){
				ProductBuyIncome pb = (ProductBuyIncome)userDayIncomeList.get(i);
				JSONObject dataelem1=new JSONObject();  
				if(pb.getIs_return_amount()==1){
					dataelem1.put("amount", pb.getAmount());					
				}else{
					dataelem1.put("amount", 0);	
				}
				dataelem1.put("income", pb.getIncome());
				dataelem1.put("product_name", proHashMap.get(pb.getId()));
				jsonArray.add(dataelem1);
			}
		}
		return jsonArray.toString();
	}
	
	/**
	 * 我的资金明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/myFunds")
	public String myFunds(HttpServletRequest request,
			HttpServletResponse response){
		String beginDateStr = request.getParameter("beginDate");
		String endDateStr = request.getParameter("endDate");
		String dateType = request.getParameter("dateType");
		String type = request.getParameter("type");//类型
		String page = request.getParameter("page");//当前页
		Date beginDate = null;
		Date endDate = null;
		if(dateType!=null && !"".equals(dateType)){
			if(dateType.equals("1")){
				beginDate = DateUtil.addDate(new Date(), -7);
			}else if(dateType.equals("2")){
				beginDate = DateUtil.addDate(new Date(), 0, -1, 0);
			}else if(dateType.equals("3")){
				beginDate = DateUtil.addDate(new Date(), 0, -3, 0);
			}else if(dateType.equals("4")){
				beginDate = DateUtil.addDate(new Date(), 0, -6, 0);
			}
			endDate = new Date();
		}else {
			if(beginDateStr!=null && !"".equals(beginDateStr)){
				beginDate = DateUtil.parseDate(beginDateStr);
			}
			if(endDateStr!=null && !"".equals(endDateStr)){
				endDate = DateUtil.addDate(DateUtil.parseDate(endDateStr), 1);
			}
		}
		Long start = 0L;
		Page p = new Page();
		if(page!=null && !"".equals(page)){
			start = p.getLimit() * (Long.valueOf(page)-1);
		}
		p.setStart(start);
		Map hashMap = new HashMap();
		if(beginDate!=null){
			hashMap.put("beginDate", DateUtil.formatDate(beginDate));
		}
		if(endDate!=null){
			endDate = DateUtil.addDate(endDate, 1);
			hashMap.put("endDate", DateUtil.formatDate(endDate));
		}
		if(type!=null && !"".equals(type)){
			hashMap.put("operate_type", type);
		}
		String user_name=BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		//2018-1-25 从新浪同步充值,提现结果
		//查询待审核的记录 操作类型：11充值,21提现
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

		hashMap.put("user_id", user.getId());
		Page myFundsList = fundsService.getMyFundsList(p,hashMap);
		String showPage = myFundsList.showPage(request, "myFunds.action"); //获取分页
		request.setAttribute("myFundsList", myFundsList.getDataList());
		request.setAttribute("showPage", showPage);
		request.setAttribute("beginDate", beginDateStr);
		request.setAttribute("endDate", endDateStr);
		request.setAttribute("dateType", dateType);
		request.setAttribute("type", type);
		request.setAttribute("showType", "mycapital"); //left.jsp需要展示的css定义
		request.setAttribute("totalRecord",myFundsList.getTotal());
		return "front/mywealth/mycapital";
	}
	
	/**
	 * 我的理财
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/myProductBuy")
	public String myProductBuy(HttpServletRequest request,
			HttpServletResponse response){
		String beginDateStr = request.getParameter("beginDate");
		String endDateStr = request.getParameter("endDate");
		String dateType = request.getParameter("dateType");
		String status = request.getParameter("status");//类型
		String page = request.getParameter("page");//当前页
		String showTypeStr =request.getParameter("showTypeStr");
		Date beginDate = null;
		Date endDate = null;
		if(dateType!=null && !"".equals(dateType)){
			if(dateType.equals("1")){
				beginDate = DateUtil.addDate(new Date(), -7);
			}else if(dateType.equals("2")){
				beginDate = DateUtil.addDate(new Date(), 0, -1, 0);
			}else if(dateType.equals("3")){
				beginDate = DateUtil.addDate(new Date(), 0, -3, 0);
			}
			endDate = DateUtil.addDate(new Date(), 1);
		}else {
			if(beginDateStr!=null && !"".equals(beginDateStr)){
				beginDate = DateUtil.parseDate(beginDateStr);
			}
			if(endDateStr!=null && !"".equals(endDateStr)){
				endDate = DateUtil.addDate(DateUtil.parseDate(endDateStr), 1);
			}
		}
		Long start = 0L;
		Page p = new Page();
		if(page!=null && !"".equals(page)){
			start = p.getLimit() * (Long.valueOf(page)-1);
		}
		p.setStart(start);
		Map hashMap = new HashMap();
		if(beginDate!=null){
			hashMap.put("beginDate", DateUtil.formatDate(beginDate));
		}
		if(endDate!=null){
			hashMap.put("endDate", DateUtil.formatDate(endDate));
		}
		if(status!=null && !"".equals(status)){
			hashMap.put("status", status);
		}
		String user_name=BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		hashMap.put("user_id", user.getId());
		hashMap.put("pagename", "selectMyProductBuyList");  //查询分页的sql
		hashMap.put("total", "selectMyProductBuyListCount");     //查询总条数的sql
		Page myProductBuyList = productBuyService.page(p,hashMap);
		String showPage = myProductBuyList.showPage(request, "myProductBuy.action"); //获取分页
		request.setAttribute("beginDate", beginDateStr);
		request.setAttribute("endDate", endDateStr);
		request.setAttribute("dateType", dateType);
		request.setAttribute("status", status);
		request.setAttribute("showType", "myconduct");
		request.setAttribute("showPage", showPage);
		request.setAttribute("showTypeStr", showTypeStr);
		request.setAttribute("myProductBuyList", myProductBuyList.getDataList());
		request.setAttribute("totalRecord",myProductBuyList.getTotal());
		return "front/mywealth/myconduct";
	}
	
	private IUserGiftDao userGiftDao;
	private IUserCouponDao userCouponDao;
	/**
	 * 我的优惠券
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/myCou")
	public String myCou(HttpServletRequest request,
			HttpServletResponse response,Model model){
		String type=RequestUtils.getReqString(request, "type");
		String discount_type=RequestUtils.getReqString(request, "discount_type");
		String goods_type=RequestUtils.getReqString(request, "goods_type");
		String dis_type=RequestUtils.getReqString(request, "dis_type");
		
		String user_name=BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		Page page1=PageUtils.createPage(request);
		page1.setLimit(6l);
		
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("user_id", user.getId());
		request.setAttribute("gift_num", userGiftDao.selectForObject("selectGiftCountByUid", map).toString());
		request.setAttribute("coupon_num", userCouponDao.selectForObject("selectCouponCountByUid",map).toString());
		
		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user.getId());
		userFundStat=userFundStatService.selectForObjectById(userFundStat);
		request.setAttribute("coin", userFundStat.getCoin_balance().toString());
		if(!StrUtils.emptyJudge(discount_type)){
			discount_type="1";
		}
		if(!StrUtils.emptyJudge(type)||"1".equals(type)){
			map.put("pagename", "selectGiftPageByMap");
			map.put("total", "selectGiftPageCnByMap");
			map.put("status", discount_type);
			page1=userGiftDao.page(page1, map);
		}else if(StrUtils.emptyJudge(type)&&"2".equals(type)){
			map.put("pagename", "selectCouponPageByMap");
			map.put("total", "selectCouponPageCnByMap");
			map.put("status", discount_type);
			page1=userCouponDao.page(page1, map);
		}
		request.setAttribute("myDiscountList", page1.getDataList()); 
		request.setAttribute("showType", "mydiscount"); 
		request.setAttribute("discount_type", discount_type);
		request.setAttribute("type", type);
		CoinGoods coinGoods=new CoinGoods();
		if(!StrUtils.emptyJudge(goods_type)||"1".equals(goods_type)){
			coinGoods.setGoods_type(1);
		}else if(StrUtils.emptyJudge(goods_type)&&"2".equals(goods_type)){
			coinGoods.setGoods_type(2);
		}
		Page page2=PageUtils.createPage("goods",request);
		page2.setLimit(6l);
		map.put("pagename", "selectExchangePage");
		map.put("total", "selectExchangeCount");
		map.put("goods_type", coinGoods.getGoods_type());
		page2=coinGoodsSerice.page(page2, map);
		
		request.setAttribute("myGoodsList", page2.getDataList());
		request.setAttribute("goods_type", goods_type);
		request.setAttribute("dis_type", dis_type);
		model.addAttribute("page1",page1);
		model.addAttribute("page2",page2);
		model.addAllAttributes(PageUtils.createPagePar( page1));
		model.addAllAttributes(PageUtils.createPagePar("goods",page2));
		
		return "front/mywealth/mydiscount";
	}
	/**
	 * 我的正经值明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/myCoinList")
	@ResponseBody
	public JsonReturn myCoinList(HttpServletRequest request,
								 HttpServletResponse response) {
		JsonReturn jr = new JsonReturn();
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		CoinStream coinStream = new CoinStream();
		coinStream.setUser_id(user.getId());
		coinStream.setStatus(1);
		List<Map<String, Object>> list = coinStreamService.selectUserCoinForList(coinStream);
		for (Map map : list) {
			if ("1".equals(map.get("action").toString())) {
				map.put("amount","+"+map.get("amount"));
			}
			if ("2".equals(map.get("action").toString())) {
				map.put("amount","-"+map.get("amount"));
			}
			if (map.get("balance")==null) {
				map.put("balance","");
			}
		}
		jr.setContent(list);
		jr.setStatus(100);
		return jr;
	}

	/**
	 * 至账户设置页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toSetAccount", method = RequestMethod.GET)
	public String toSetAccount (HttpServletRequest request, HttpServletResponse response,
									Model model) {
		request.setAttribute("showType","myaccount");
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		model.addAttribute("user",user);
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> userBanks = userBankService.selectForList(userBank);
		/*if ((userBanks == null || userBanks.size() == 0) && user.getStatus() >= 2 && user.getStatus() < 4) {//用户绑过卡，则从上海银行同步卡信息
			String res = SinaUtil.queryBankCard(user.getId().toString());
			if (!StrUtils.emptyJudge(res) || !(res.startsWith("{") || res.startsWith("["))) {
				log.info("上海银行查询绑卡接口网络异常");
			}
			Map restMap = JsonUtil.jsonToMap(res);
			Object resCode = restMap.get("response_code");
			if(resCode == null || !resCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
				log.info("上海银行查询绑卡接口异常:"+restMap.get("response_msg"));
			}else if (res.indexOf("card_list") > 0){//成功
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
						userBanks=userBankService.selectForList(userBank);
					}
				}
			}
		}*/
		model.addAttribute("userBanks",userBanks);

		return "front/mywealth/myaccount";
	}

	/**
	 * 绑定银行卡设置
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toSetBankCard", method = RequestMethod.GET)
	public String toSetBankCard (HttpServletRequest request, HttpServletResponse response,
								Model model) {
		request.setAttribute("showType","myaccount");
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		model.addAttribute("user",user);
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> userBanks = userBankService.selectForList(userBank);
		model.addAttribute("userBanks",userBanks);

		return "front/mywealth/setbank";
	}

	/**
	 * 至解绑银行卡页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toUnboundBankCard", method = RequestMethod.GET)
	public String toUnboundBankCard (HttpServletRequest request, HttpServletResponse response,
								 Model model) {
		request.setAttribute("showType","myaccount");
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		model.addAttribute("user",user);
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> userBanks = userBankService.selectForList(userBank);
		model.addAttribute("userBanks",userBanks);

		return "front/mywealth/unbound_bankcard";
	}

	/**
	 * 发起银行卡解绑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "sendUnboundBankCard", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn sendUnboundBankCard (HttpServletRequest request, HttpServletResponse response) {
		JsonReturn jr = new JsonReturn();
		String phone = request.getParameter("phone");
		String user_name = BrowseUtil.getCookie(request, response);
		String req_ip = RequestUtils.getIp(request);
		User user = userService.selectForObjectByPhone(user_name);
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> ubs = userBankService.selectForList(userBank);
		if (ubs == null || ubs.size()==0) {
			jr.setContent("您的银行卡已解绑");
			jr.setStatus(101);
			return jr;
		}
		UserBankVo ubv = ubs.get(0);
//		//判断预留手机号是否正确
//		if (!phone.equals(ubv.getPhone())) {
//			jr.setStatus(101);
//			jr.setContent("预留手机号码有误！");
//			return jr;
//		}
		//判断用户余额是否为0
		UserFundStat userFundStat = new UserFundStat();
		userFundStat.setUser_id(user.getId());
		List<UserFundStatVo> list = userFundStatService.selectForList(userFundStat);
		UserFundStatVo one = list.get(0);
		if (one.getBalance() != 0.0) {//可用余额不为0
			jr.setStatus(102);
			jr.setContent(one.getBalance());
			return jr;
		}else {//可用余额为0，进行解绑
			String resData = SinaUtil.unbindBankCard(user.getId(),ubv.getCard_id(),req_ip);
			if(resData == null || resData.trim().isEmpty()){
				jr.setContent("网络异常，请稍后再试！");
				jr.setStatus(103);
				return jr;
			}

			Map resultMap = JsonUtil.jsonToMap(resData);
			String strCode = (String) resultMap.get("response_code");
			if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
				jr.setContent(resultMap.get("response_msg"));
				jr.setStatus(104);
				return jr;
			}
			String ticket = (String) resultMap.get("ticket");
			jr.setContent(ticket);
			jr.setStatus(100);
			return jr;
		}
	}
	/**
	 * 银行卡解绑推进
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "unboundBankCardAdvance", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn unboundBankCardAdvance (HttpServletRequest request, HttpServletResponse response) {
		JsonReturn jr = new JsonReturn();
		String ticket = request.getParameter("ticket");
		String valid_code = request.getParameter("valid_code");
		String user_name = BrowseUtil.getCookie(request, response);
		String req_ip = RequestUtils.getIp(request);
		User user = userService.selectForObjectByPhone(user_name);
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);
		List<UserBankVo> list = userBankService.selectForList(userBank);
		if (list == null || list.size()==0) {
			jr.setContent("您的银行卡已解绑");
			jr.setStatus(101);
			return jr;
		}
		if (!StrUtils.emptyJudge(ticket)) {
			jr.setContent("请获取验证码");
			jr.setStatus(102);
			return jr;
		}
		UserBankVo ubv = list.get(0);
		String resData = SinaUtil.unbindBankCardAdvance(user.getId(),ticket,valid_code,req_ip);
		if(resData == null || resData.trim().isEmpty()){
			jr.setContent("网络异常，请稍后再试！");
			jr.setStatus(103);
			return jr;
		}

		Map resultMap = JsonUtil.jsonToMap(resData);
		String strCode = (String) resultMap.get("response_code");
		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			jr.setContent(resultMap.get("response_msg"));
			jr.setStatus(104);
			return jr;
		} else {
			ubv.setStatus(4);//解绑
			ubv.setUnbind_ticket(ticket);
			ubv.setUpdate_time(new Date());
			userBankService.updateUserBankById(ubv);
			user.setStatus(4);//有支付密码，已解绑
			userService.update(user);
			jr.setStatus(100);
			return jr;
		}
	}

	/**
	 * 至绑定银行卡页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toAddBankCard", method = RequestMethod.GET)
	public String toAddBankCard (HttpServletRequest request, HttpServletResponse response,
									 Model model) {
		request.setAttribute("showType","myaccount");
		//获取登录用户
		String user_name = BrowseUtil.getCookie(request,response);
		User user = userService.selectForObjectByPhone(user_name);
//		//银行列表
//		List bankList = bankService.selectForList(null);
//		//省市列表
//		Object[] province = CityMapUtil.getProvince();
//		model.addAttribute("bankList", bankList);
//		model.addAttribute("province", province);
//		model.addAttribute("user", user);
		String result = SinaUtil.bindBankCard(user.getId(),user.getReal_name(),user.getIdcard_no(),"1");

		if (!StrUtils.emptyJudge(result) || !(result.startsWith("{") || result.startsWith("["))) {
			model.addAttribute("errMsg", "网络异常,请稍后再试！");
			return "front/user/new_real_name";
		}
		Map resultMap = JsonUtil.jsonToMap(result);

		Object strCode = resultMap.get("response_code");
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			Object response_msg = resultMap.get("response_msg");
			model.addAttribute("errMsg", response_msg);
			return "front/user/new_real_name";
		}else{//成功
			Map dataMap = (Map)resultMap.get("data");
			return "redirect:"+dataMap.get("redirect_url");
		}
	}

	/**
	 * 发起银行卡绑定
	 * @param request
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "sendAddBankCard", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn sendAddBankCard (HttpServletRequest request, HttpServletResponse response,
								 @RequestParam Map<String,Object> param) {
		//获取请求参数
		String bankAccount = (String) param.get("bank_accounts");
		String province = (String) param.get("province");
		String city = (String) param.get("city");
		String bankCard = (String) param.get("bankCard");
		String phone = (String) param.get("phone");
		String ip = RequestUtils.getIp(request);

		//获取登录用户
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		//调用上海银行存管接口
		String request_no = "zdjfu" + new Date().getTime() + StringUtil.getRandom();
		String result = SinaUtil.bindBankCard(user.getId(),request_no,bankAccount,bankCard,"C",
				phone,province,city,ip,user.getReal_name_auth(),user.getReal_name(),user.getIdcard_no());
		//构建返回对象
		JsonReturn jr = new JsonReturn();
		if(result == null || result.trim().isEmpty()){
			jr.setContent("网络异常，请稍后再试！");
			jr.setStatus(103);
			return jr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);
		String strCode = (String) resultMap.get("response_code");
		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			String response_msg = (String) resultMap.get("response_msg");
			jr.setStatus(103);
			jr.setContent(response_msg);
			return jr;
		}else{
			String ticket = (String) resultMap.get("ticket");
			UserBank userBank = new UserBank();
			userBank.setUser_id(user.getId());
			userBank.setBank_alias(bankAccount);
			userBank.setBank_no(bankCard);
			userBank.setPhone(phone);
			userBank.setCreate_time(new Date());
			userBank.setStatus(2);
			userBank.setType(1);//快捷卡
			userBank.setTicket(ticket);
			userBank.setProvince(province);
			userBank.setCity(city);
			userBank.setCard_attribute("C");
			userBank.setRequest_no(request_no);
			//保存银行
			userBankService.save(userBank);
			jr.setStatus(100);
			jr.setContent(ticket);
			return jr;
		}
	}

	/**
	 * 添加银行卡推进
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addBankCardAdvance", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn addBankCardAdvance(@RequestParam Map<String,Object> param, HttpServletRequest request,
									 HttpServletResponse response){
		//获取请求参数
		String ticket = (String) param.get("ticket");
		String valid_code = (String) param.get("valid_code");
		String phone = (String) param.get("phone");
		String bankCard = (String) param.get("bankCard");
		String bank_accounts = (String) param.get("bank_accounts");
		String ip = RequestUtils.getIp(request);
		String province = (String) param.get("province");
		String city = (String) param.get("city");

		//获取登录用户信息
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);

		String result = SinaUtil.bindBankCardAdvance(ticket,valid_code,ip);
		//构建返回对象
		JsonReturn jr = new JsonReturn();
		if(result == null || result.trim().isEmpty()){
			jr.setContent("网络异常，请稍后再试！");
			jr.setStatus(101);
			return jr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);
		String strCode = (String) resultMap.get("response_code");
		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			String response_msg = (String) resultMap.get("response_msg");
			jr.setContent(response_msg);
			jr.setStatus(102);
			return jr;
		}else{//成功
			//更新用户信息
			user.setStatus(3);
			userService.update(user);

			String card_id = (String) resultMap.get("card_id");
			//更新用户银行卡信息
			UserBank ub = new UserBank();
			ub.setUser_id(user.getId());
			ub.setType(1);
			ub.setTicket(ticket);
			List<UserBankVo> list = userBankService.selectForList(ub);
			UserBank userBank = list.get(0);
			userBank.setCard_id(card_id);
			userBank.setStatus(1);
			userBankService.updateUserBankById(userBank);
			jr.setStatus(100);
			return jr;
		}
	}
	/**
	 * 账户设置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/myAccount")
	public String myAccount(HttpServletRequest request,
			HttpServletResponse response){
		request.setAttribute("showType", "myaccount");
		return "front/mywealth/myaccount";
	}
	
	/**
	 * 邀请有礼
	 * @param request
	 * @param response
	 * @return
	 */
	private IProductBuyDao productBuyDao;
	@RequestMapping(value="/myInvitation")
	public String myInvitation(HttpServletRequest request,
			HttpServletResponse response,Model model){
		String user_name=BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		String invite_code = user.getInvite_code();
		if(invite_code == null){
			invite_code = user.getId() + "";
			user.setInvite_code(invite_code);
			userService.updateForObjectByPhone(user);
		}
		user=new User();
		user.setInviter_phone(user_name);
		List<Map<String,Object>> sumList=(List<Map<String, Object>>) productBuyDao.selectForList("selectInviterPhoneSum", user);
		if(null!=sumList&&sumList.size()>0){
			int invited_num=sumList.size();
			double invited_amount=0.0;
			for(int i=0;i<sumList.size();i++){
				Map<String,Object> sumMap=sumList.get(i);
				if(sumMap.containsKey("total_pay")){
					invited_amount+=StrUtils.convToDouble(sumMap.get("total_pay").toString());
				}
			}
			model.addAttribute("invited_num", invited_num);
			model.addAttribute("invited_amount", invited_amount);
		}
		model.addAttribute("invite_code",invite_code);
		Page page=PageUtils.createPage(request);
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("pagename", "selectInviterPhone");
		map.put("total", "selectInviterPhoneCn");
		map.put("inviter_phone", user_name);
		page=productBuyService.page(page, map);
		String showPage=page.showPage(request, "myInvitation.action");
		model.addAttribute("detail", page);
		request.setAttribute("showPage", showPage);
		request.setAttribute("showType", "myinvitation");
		return "front/mywealth/myinvitation";
	}
	
	/**
	 * 消息中心
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mynews")
	public String mynews(HttpServletRequest request,
			HttpServletResponse response){
		request.setAttribute("showType", "mynews");
		return "front/mywealth/mynews";
	}


	@Autowired(required = true)
	public void setUserGiftDao(
			@Qualifier("userGiftDao")IUserGiftDao userGiftDao) {
		this.userGiftDao = userGiftDao;
	}
	@Autowired(required = true)
	public void setUserCouponDao(
			@Qualifier("userCouponDao")IUserCouponDao userCouponDao) {
		this.userCouponDao = userCouponDao;
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
	@Autowired(required=true)
	public void setFundsService(IFundsService fundsService) {
		this.fundsService = fundsService;
	}
	@Autowired(required=true)
	public void setCoinGoodsSerice(ICoinGoodsService coinGoodsSerice) {
		this.coinGoodsSerice = coinGoodsSerice;
	}

	

	@Autowired(required=true)
	public void setProductBuyDao(
			@Qualifier("productBuyDao")IProductBuyDao productBuyDao) {
		this.productBuyDao = productBuyDao;
	}
	
}
