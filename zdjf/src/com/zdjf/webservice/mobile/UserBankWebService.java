package com.zdjf.webservice.mobile;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.events.AuditEvent;
import com.zdjf.components.events.FundEvent;
import com.zdjf.components.events.UserBenefitsEvent;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.fund.Bank;
import com.zdjf.domain.fund.BankVo;
import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.fund.IBankService;
import com.zdjf.service.fund.ICoinService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.StringUtil;
import com.zdjf.webservice.express.SmsReturn;


@Controller
@RequestMapping("/m/userBank")
public class UserBankWebService {

	private IUserBankService userBankService;

	private IBankService bankService;

	private IUserService userService;

	private ICoinService coinService;

	private ICoinStreamService coinStreamService;

	private IUserFundStatService userFundStatService;

	private ApplicationContext applicationContext;

	private IGiftService giftService;

	private IUserGiftService userGiftService;

	//获取预留
	@RequestMapping(value="/other/phone",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getOtherPhone(HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {

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

		//验证一下  是否存在快捷支付
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);

		List<UserBankVo> list = userBankService.selectForList(userBank);


		if(list.size()==0){
			sr.setContent("不存在快捷支付");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		if(list.size()>1){
			sr.setContent("存在多个快捷支付");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		UserBankVo vo = list.get(0);

		sr.setMapContent(vo.getPhone());
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}


	//用户解绑银行卡
	@RequestMapping(value="/unbind",method=RequestMethod.POST)
	public @ResponseBody SmsReturn  unbindBankCard(HttpServletRequest request,
			HttpServletResponse response) {
		SmsReturn sr = new SmsReturn();


		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String phone = request.getParameter("other_phone");
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

		//if(user.getPhone())

		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());

		UserFundStat voF = userFundStatService.selectForObjectById(userFund);

		if(voF.getBalance()>0){
			sr.setContent("可用余额不为，不能解绑");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);

		List<UserBankVo> list = userBankService.selectForList(userBank);

		if(list.size()==0){
			sr.setContent("用户没有快捷支付卡");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		UserBankVo vo = list.get(0);

		//		if(!vo.getPhone().equalsIgnoreCase(phone)){
		//			sr.setContent("预留手机号不一致");
		//			sr.setReturnCase(false);
		//			sr.setStatus(101);
		//
		//			return sr;
		//		}

		String card_id = vo.getCard_id();

		String result = SinaUtil.unbindBankCard(user.getId(),card_id,user.getReg_ip());
		System.out.println(result);


		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);

		Object strCode =  resultMap.get("response_code");

		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		String ticket = (String) resultMap.get("ticket");


		vo.setUpdate_time(new Date());
		vo.setUnbind_ticket(ticket);
		userBankService.updateUserBankById(vo);

		user.setPay_account(null);

		user.setUpdate_time(new Date());
		userService.update(user);


		sr.setContent("解绑验证码已发送至绑卡预留手机号，请查收");
		sr.setReturnCase(true);
		sr.setStatus(100);

		return sr;
	}


	//获取解绑验证
	@RequestMapping(value="/unbind/advance",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getBindAdvance(HttpServletRequest request,
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
		userBank.setStatus(1);
		userBank.setType(1);

		List<UserBankVo> list = userBankService.selectForList(userBank);


		UserBankVo vo = list.get(0);

		String ticket = vo.getUnbind_ticket();
		String result = SinaUtil.unbindBankCardAdvance(user.getId(),ticket, reg_source, reg_ip);
		System.out.println(result);

		vo.getRemark();

		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);
		String strCode = (String) resultMap.get("response_code");

		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent(resultMap.get("response_mgs"));
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		//vo.setType(2);//2018-2-1  改变状态 4 type 不变
		vo.setStatus(4);
		vo.setUpdate_time(new Date());
		userBankService.updateUserBankById((UserBank)vo);

		user.setPay_account(null);
		user.setStatus(4);
		userService.updateForObjectByPhone(user);


		sr.setContent("解绑及确认成功");
		sr.setReturnCase(true);
		sr.setStatus(100);

		return sr;
	}

	//用户绑定银行卡
	@RequestMapping(value="/bind",method=RequestMethod.POST)
	public @ResponseBody SmsReturn  bindBankCard(HttpServletRequest request,
			HttpServletResponse response) {

		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		String card_attribute = RequestUtils.getReqString(request,"card_attribute");
		String bank_no = RequestUtils.getReqString(request,"bank_no");
		String bank_alias = RequestUtils.getReqString(request,"bank_alias");
		String phone = RequestUtils.getReqString(request,"other_phone");
		String province = RequestUtils.getReqString(request,"province");
		String city = RequestUtils.getReqString(request,"city");
		String sign = RequestUtils.getReqString(request,"sign");
		String real_name=RequestUtils.getReqString(request, "real_name");
		String idcard_no=RequestUtils.getReqString(request, "idcard_no");
		String ip=RequestUtils.getReqString(request, "ip");
		String md5Sign = Md5Util.md5to32(user_name  +ip+
				Constants.API_KEY);
		String reg_source=RequestUtils.getReqString(request, "reg_source");
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
		if(user_name.isEmpty()||null==user){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}

		String bankinfo=SinaUtil.queryBankCard(user.getId().toString());

		if(bankinfo == null || bankinfo.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Map bankMap = JsonUtil.jsonToMap(bankinfo);
		String bankCode = (String) bankMap.get("response_code");
		if(bankCode == null || !bankCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent(bankMap.get("response_msg"));
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}
		StrUtils.removeNullValue(bankMap);
		if(bankMap.containsKey("data")&&null!=bankMap.get("data")&&((Map)bankMap.get("data")).containsKey("card_list")){
			String card_list=((Map)bankMap.get("data")).get("card_list").toString();
			String[] cardInfo=card_list.split("\\^");

			UserBank userBank = new UserBank();
			userBank.setUser_id(user.getId());
			//userBank.setType(1);
			userBank.setStatus(1);
			userBank.setType(1);
			List<UserBankVo> list = userBankService.selectForList(userBank);

			userBank.setCard_id(cardInfo[0]);
			userBank.setType(1);
			userBank.setBank_alias(cardInfo[1]);
			userBank.setBank_no(cardInfo[2]);
			userBank.setCard_user_name(cardInfo[3]);
			userBank.setCard_attribute(cardInfo[5]);
			userBank.setCreate_time(new Date());

			if(user.getReal_name_auth()==0){
				user.setReal_name_auth(1);
				userService.update(user);
				//实名认证正经值奖励
				applicationContext.publishEvent(new AuditEvent(this,user));
			}
			if(list.size()>0){
				if(user.getStatus()<3){
					user.setStatus(2);
					String isPw=SinaUtil.isSetPayPassword(user.getId(), ip);
					if(isPw.trim().isEmpty()){
						sr.setContent("新浪接口问题");
						sr.setReturnCase(false);
						sr.setStatus(101);
						return sr;
					}
					Map isPwMap = JsonUtil.jsonToMap(isPw);
					String isPwCode = (String) isPwMap.get("response_code");
					if(isPwCode == null || !isPwCode.equalsIgnoreCase("APPLY_SUCCESS")){
						sr.setContent((String) isPwMap.get("response_msg"));
						sr.setReturnCase(false);
						sr.setStatus(101);
						return sr;
					}else{
						Map pwData=(Map) isPwMap.get("data");
						if(pwData.containsKey("is_set_paypass")){
							String flag=pwData.get("is_set_paypass").toString();
							if("Y".equalsIgnoreCase(flag)){
								user.setStatus(3);
								userService.updateForObjectByPhone(user);
								sr.setContent("用户已设置支付密码");
								sr.setReturnCase(false);
								sr.setStatus(101);
								return sr;
							}
						}else{
							sr.setContent("新浪接口问题");
							sr.setReturnCase(false);
							sr.setStatus(101);
							return sr;
						}
					}
					userService.updateForObjectByPhone(user);
					userBankService.updateUserBankById(userBank);
					String pwResult=SinaUtil.setPayPassword(user.getId(), 1,ip, user_name, reg_source);
					if(pwResult.trim().isEmpty()){
						sr.setContent("新浪接口问题");
						sr.setReturnCase(false);
						sr.setStatus(101);
						return sr;
					}

					Map pwMap = JsonUtil.jsonToMap(pwResult);
					String pwCode = (String) pwMap.get("response_code");
					if(pwCode == null || !pwCode.equalsIgnoreCase("APPLY_SUCCESS")){
						sr.setContent((String) pwMap.get("response_msg"));
						sr.setReturnCase(false);
						sr.setStatus(101);
						return sr;
					}
					Map dataMap =  (Map) pwMap.get("data");
					String redirect_url = (String) dataMap.get("redirect_url");
					sr.setContent(redirect_url);
					sr.setReturnCase(true);
					sr.setStatus(100);
					return sr;
				}else if(user.getStatus()==3){
					userBankService.updateUserBankById(userBank);
					sr.setContent("用户已绑卡且设置支付密码");
					sr.setReturnCase(false);
					sr.setStatus(101);
					return sr;
				}else if(user.getStatus()==4){
					real_name=user.getReal_name();
					idcard_no=user.getIdcard_no();
				}
			}else{
				userBankService.save(userBank);
				applicationContext.publishEvent(new FundEvent(this,user.getId(),45,15*1.0,
						userFundStatService,coinStreamService));
				String pwResult=SinaUtil.setPayPassword(user.getId(), 1,ip, user_name, reg_source);
				if(pwResult.trim().isEmpty()){
					sr.setContent("新浪接口问题");
					sr.setReturnCase(false);
					sr.setStatus(101);
					return sr;
				}

				Map pwMap = JsonUtil.jsonToMap(pwResult);
				String pwCode = (String) pwMap.get("response_code");
				if(pwCode == null || !pwCode.equalsIgnoreCase("APPLY_SUCCESS")){
					sr.setContent((String) pwMap.get("response_msg"));
					sr.setReturnCase(false);
					sr.setStatus(101);
					return sr;
				}
				Map dataMap =  (Map) pwMap.get("data");
				String redirect_url = (String) dataMap.get("redirect_url");
				sr.setContent(redirect_url);
				sr.setReturnCase(true);
				sr.setStatus(100);
				return sr;
			}
		}else{
			if(user.getStatus()==4){
				idcard_no=user.getIdcard_no();
				real_name=user.getReal_name();
			}else if(user.getStatus()==3){
				user.setStatus(1);
				userService.updateForObjectByPhone(user);
			}
		}

		//验证一下  是否存在快捷支付

		Date date = new Date();

		//唯一标识   设定
		String request_no = "zdjfu" + date.getTime() + StringUtil.getRandom();
		String result="";
		//		if("".equals(real_name)&&"".equals(idcard_no)){
		//			result = SinaUtil.bindBankCard(user.getId(),request_no,bank_alias,
		//					bank_no,card_attribute,phone,province,city,user.getReg_ip());
		//		}else{
		//			result = SinaUtil.bindBankCard(user.getId(),request_no,bank_alias,
		//					bank_no,card_attribute,phone,province,city,user.getReg_ip(),user.getReal_name_auth(),real_name,idcard_no);
		//2018-3-13
		result = SinaUtil.bindBankCard(user.getId(),real_name,idcard_no,reg_source);
		//		}
		System.out.println(result);

		if(result == null || result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		String remark = user.getRemark();
		/*if(remark == null || remark.isEmpty())
			user.setRemark("bank:" + result);
		else{

			user.setRemark(remark + ";bank:" + result);
		}*/


		Map resultMap = JsonUtil.jsonToMap(result);
		String strCode = (String) resultMap.get("response_code");
		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent(resultMap.get("response_msg"));
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}


		user.setReal_name_auth(0);
		user.setReal_name(real_name);
		user.setIdcard_no(idcard_no);
		user.setSex(StrUtils.idcardToSex(idcard_no));
		user.setBirthday(StrUtils.idCardNoToBirthDay(idcard_no).substring(4,8));
		user.setIdcard_auth_time(new Date());

		if(!"".equals(reg_source)||null!=reg_source){
			sr.setContent(((Map)resultMap.get("data")).get("redirect_url"));
		}else{
			String ticket = (String) resultMap.get("ticket");
			user.setPay_passwd(ticket);
			user.setPay_account(request_no);
			sr.setContent("发送验证码成功");
		}

		userService.updateForObjectByPhone(user);
		sr.setReturnCase(true);
		sr.setStatus(100);

		return sr;
	}

	//获取快捷支付
	@RequestMapping(value="/quick/payment",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getQuickPayment(HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {

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

		if(user == null){
			sr.setContent("获取不到此用户");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		//验证一下  是否存在快捷支付
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		userBank.setStatus(1);//状态:成功 2018-1-29 add
		List<UserBankVo> list = userBankService.selectForList(userBank);


		if(list.size()==0){
			sr.setContent("不存在快捷支付");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		if(list.size()>1){
			sr.setContent("存在多个快捷支付");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		UserBankVo vo = list.get(0);

		sr.setMapContent(vo);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}


	//获取短信验证
	@Transactional(rollbackFor = { Exception.class }) 
	@RequestMapping(value="/bank/advance",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getBankAdvance(HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {

		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		String valid_code = request.getParameter("valid_code");
		String reg_source =request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");

		//银行卡内容
		String card_attribute = request.getParameter("card_attribute");
		String bank_no = request.getParameter("bank_no");
		String bank_alias = request.getParameter("bank_alias");
		String phone = request.getParameter("other_phone");
		String province = request.getParameter("province");
		String city = request.getParameter("city");

		String md5Sign = Md5Util.md5to32(user_name + valid_code + reg_ip +
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
		String ticket = user.getPay_passwd();
		//
		String result = SinaUtil.bindBankCardAdvance(ticket, valid_code, reg_ip);
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
			sr.setContent(resultMap.get("response_msg"));
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		String card_id = (String) resultMap.get("card_id");
		user.setUpdate_time(new Date());
		result=SinaUtil.isSetPayPassword(user.getId(), user.getReg_ip());
		System.out.println("isplaypassword :" + result);

		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		resultMap = JsonUtil.jsonToMap(result);
		strCode = (String) resultMap.get("response_code");
		if(strCode == null ){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}else{
			Map<String, Object> data=(Map<String, Object>) resultMap.get("data");
			strCode=data.get("is_set_paypass").toString();
			if(strCode.equalsIgnoreCase("N")){
				result = SinaUtil.setPayPassword(user.getId(),1,user.getReg_ip(),user_name,reg_source);
				System.out.println("playpassword :" + result);


				if(result.trim().isEmpty()){
					sr.setContent("新浪接口问题");
					sr.setReturnCase(false);
					sr.setStatus(101);

					return sr;
				}

				resultMap = JsonUtil.jsonToMap(result);
				strCode = (String) resultMap.get("response_code");

				if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
					sr.setContent("此用户没有通过审核");
					sr.setReturnCase(false);
					sr.setStatus(101);

					return sr;
				}

				Map dataMap =  (Map) resultMap.get("data");
				String redirect_url = (String) dataMap.get("redirect_url");

				//支付密码用md5
				user.setPay_passwd(redirect_url);
				user.setStatus(2);
				sr.setContent(redirect_url);

				String name = "绑银行卡";
				//获取正经值
				//applicationContext.publishEvent(new FundEvent(this,user,name,coinStreamService,coinService));
				Coin coin = new Coin();
				coin.setCoin_source(4);
				coin.setCoin_name(name);
				coin.setStatus(1);
				List<CoinVo> listCo = coinService.selectForList(coin);

				for(CoinVo vo : listCo){
					//注册
					applicationContext.publishEvent(new FundEvent(this,user.getId(),45,vo.getCoin_num()*1.0,
							userFundStatService,coinStreamService));
				}

				if(user.getReal_name_auth() !=1){
					//正经值
					Coin coin1 = new Coin();
					coin1.setCoin_source(2);
					coin1.setCoin_name("实名认证");

					/*applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
							coin,coinService,coinStreamService,1));*/
					List<CoinVo> listCo1 = coinService.selectForList(coin);

					for(CoinVo vo : listCo1){
						//实名认证
						applicationContext.publishEvent(new FundEvent(this,user.getId(),23,vo.getCoin_num()*1.0,
								userFundStatService,coinStreamService));
						/*CoinStream coinStream = new CoinStream();

						coinStream.setUser_id(user.getId());
						coinStream.setAction(1);
						//coinStream.setType(1);
						coinStream.setCreate_time(new Date());
						coinStream.setNum(vo.getCoin_num());
						coinStream.setStatus(1);


						coinStreamService.save(coinStream);*/
					}
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
								gift.setGift_name("车主首投红包");
								gift.setGift_source(55);
								gift.setStatus(1);
							}

							if(user.getInvite_source().equalsIgnoreCase("501")||
									user.getInvite_source().equalsIgnoreCase("502")||
									user.getInvite_source().equalsIgnoreCase("503")||
									user.getInvite_source().equalsIgnoreCase("505")||
									user.getInvite_source().equalsIgnoreCase("506")){
								gift.setGift_name("首投红包");
								gift.setGift_source(55);
								gift.setStatus(1);
							}


							//红包 流程
							applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
									gift,giftService,userGiftService));
						}
					}
				}

			}else if(strCode.equalsIgnoreCase("Y")){
				user.setStatus(3);
				user.setReal_name_auth(1);

				sr.setContent("支付密码已经设置");
			}else{
				sr.setContent("新浪接口问题");
				sr.setReturnCase(false);
				sr.setStatus(101);

				return sr;
			}
		}

		//支付密码

		userService.updateForObjectByPhone(user);
		UserBank userBank = new UserBank();

		userBank.setCard_id(card_id);
		userBank.setUser_id(user.getId());
		userBank.setBank_alias(bank_alias);
		userBank.setBank_no(bank_no);
		userBank.setPhone(phone);
		userBank.setCreate_time(new Date());
		userBank.setStatus(1);
		userBank.setType(1);//快捷卡
		userBank.setTicket(ticket);
		userBank.setProvince(province);
		userBank.setCity(city);
		userBank.setCard_attribute(card_attribute);
		userBank.setRequest_no(user.getPay_account());//
		//保存银行
		userBankService.save(userBank);

		//sr.setContent(redirect_url);

		sr.setReturnCase(true);
		sr.setStatus(100);

		return sr;
	}

	//获取可用银行的信息
	@RequestMapping(value="/bank/list",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getBankList(HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {

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
		Bank bank = new Bank();
		List<BankVo> list = bankService.selectForList(bank);
		sr.setMapContent(list);
		sr.setReturnCase(true);
		sr.setStatus(100);

		return sr;
	}

	//获取用户绑定卡
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getBankPage(HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {

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



		UserBank userBank = new UserBank();
		if(!user_name.isEmpty()){
			User user = userService.selectForObjectByPhone(user_name);
			if(user != null){
				userBank.setUser_id(user.getId());
			}
		}


		Page page = PageUtils.createPage(request);
		page = userBankService.page(page, userBank);

		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));

		sr.setContent(page);
		sr.setReturnCase(true);
		sr.setStatus(100);

		return sr;
	}

	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}


	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}


	@Autowired(required = true)
	public void setUserBankService(IUserBankService userBankService) {
		this.userBankService = userBankService;
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
	public void setBankService(IBankService bankService) {
		this.bankService = bankService;
	}

	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Autowired(required = true)
	public void setGiftService(IGiftService giftService) {
		this.giftService = giftService;
	}

	@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}

}
