package com.zdjf.webservice.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zdjf.components.events.FundEvent;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.user.IUserBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zdjf.components.events.AuditEvent;
import com.zdjf.components.events.RegisterEvent;
import com.zdjf.components.mobile.SinaResultUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.sms.Sms;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.sms.ISmsService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.IDCardUtil;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.PhoneFormatCheckUtils;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.webservice.express.SmsReturn;

@RestController
@RequestMapping("/m/user")
public class UserWebService {
	
	private IUserService userService;
	
	private ISmsService smservice;
	
	private ApplicationContext applicationContext;
	
	private IUserFundStatService userFundStatService;

	@Autowired
	private ICoinStreamService coinStreamService;

	@Autowired
	private IUserBankService userBankService;
	
	
	//用户是否设置查询密码
	@RequestMapping(value="/is/paypasswd",method=RequestMethod.POST)
	public @ResponseBody SmsReturn  userOpenAccount(HttpServletRequest request,
            HttpServletResponse response) {
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
		if(user.getStatus() == 3){
			sr.setContent("密码已经设置过");
			sr.setReturnCase(true);
			sr.setStatus(100);
			
			return sr;
		}
		String result = SinaUtil.isSetPayPassword(user.getId(), reg_ip);

		System.out.println(result);
		
		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		Map resultMap = JsonUtil.jsonToMap(result);
		Object strCode = resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent("此用户绑定银行卡没有");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		Map dataMap =  (Map) resultMap.get("data");
		Object is =  dataMap.get("is_set_paypass");
		
		if(is.toString().equalsIgnoreCase("Y"))
		{
			
			user.setStatus(3);
			userService.updateForObjectByPhone(user);
			sr.setContent("设置成功");
			sr.setReturnCase(true);
			sr.setStatus(100);
		}else{
			sr.setContent("设置不成功");
			sr.setReturnCase(false);
			sr.setStatus(10);
		}
		
		return sr;
	}
	//找回认证手机
	@RequestMapping(value="/verify/mobile",method=RequestMethod.POST)//添加管理用户
	public SmsReturn findVerifyMobile(HttpServletRequest request) {
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		
		if(user == null){
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("没有此用户");
			return sr;
		}
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		/*if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("无效数据");
			return sr;
		}*/
		
		
		String result = SinaUtil.findVerifyMobile(user.getId(),1,reg_source);
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
		
		Map dataMap =  (Map) resultMap.get("data");
		
		String redirect_url = (String) dataMap.get("redirect_url");
		
		sr.setStatus(100);
		sr.setReturnCase(true);
		sr.setMapContent(redirect_url);
		
		return sr;
	}
	
	//找回支付密码
	@RequestMapping(value="/find/paypasswd",method=RequestMethod.POST)//添加管理用户
	public SmsReturn findPayPasswd(HttpServletRequest request) {
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		
		if(user == null){
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("没有此用户");
			return sr;
		}
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("无效数据");
			return sr;
		}
		
		/*if(SinaResultUtil.isPassword(user)){
			user.setStatus(3);
			userService.updateForObjectByPhone(user);
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("已经设置过交易密码");
			return sr;
		}*/
		
		String result = SinaUtil.findPayPassword(user.getId(),1,reg_source,"forget");
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
		
		Map dataMap =  (Map) resultMap.get("data");
		
		String redirect_url = (String) dataMap.get("redirect_url");
		
		//支付密码用md5
		user.setPay_passwd(redirect_url);
		
		userService.updateForObjectByPhone(user);
		
		sr.setStatus(100);
		sr.setReturnCase(true);
		sr.setMapContent(redirect_url);
		//sr.setContent(redirect_url);
		
		return sr;
	}
	
	//修改支付密码
	@RequestMapping(value="/modify/paypasswd",method=RequestMethod.POST)//添加管理用户
	public SmsReturn modifyPayPasswd(HttpServletRequest request) {
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		
		if(user == null){
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("没有此用户");
			return sr;
		}
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("无效数据");
			return sr;
		}
		
		
		
		String result = SinaUtil.modifyPayPassword(user.getId(),1,user.getReg_ip(),reg_source,"change");
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
		
		Map dataMap =  (Map) resultMap.get("data");
		
		String redirect_url = (String) dataMap.get("redirect_url");
		
		//支付密码用md5
		user.setPay_passwd(redirect_url);
		
		userService.updateForObjectByPhone(user);
		
		sr.setStatus(100);
		sr.setReturnCase(true);
		sr.setMapContent(redirect_url);
		//sr.setContent(redirect_url);
		
		return sr;
	}
	
	//支付密码
	@RequestMapping(value="/paypasswd",method=RequestMethod.POST)//添加管理用户
	public SmsReturn userPayPasswd(HttpServletRequest request) {
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		
		if(user == null){
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("没有此用户");
			return sr;
		}
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("无效数据");
			return sr;
		}
		
		if(SinaResultUtil.isPassword(user)){
			user.setStatus(3);
			userService.updateForObjectByPhone(user);
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("已经设置过交易密码");
			return sr;
		}
		
		String result = SinaUtil.setPayPassword(user.getId(),1,user.getReg_ip(),user_name,reg_source);
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
			sr.setContent(resultMap.get("response_msg"));
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		Map dataMap =  (Map) resultMap.get("data");
		
		String redirect_url = (String) dataMap.get("redirect_url");
		
		//支付密码用md5
		user.setPay_passwd(redirect_url);
		
		userService.updateForObjectByPhone(user);
		
		sr.setStatus(100);
		sr.setReturnCase(true);
		sr.setMapContent(redirect_url);
		//sr.setContent(redirect_url);
		
		return sr;
	}
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST)//添加管理用户
	public SmsReturn registerUser(HttpServletRequest request,HttpServletResponse response) {
		
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		User old = userService.selectForObjectByPhone(user_name);
		if(old == null){
			String passwd = request.getParameter("passwd");
			String inviter_phone = request.getParameter("inviter_phone");
			String verif = request.getParameter("verif");
			String reg_ip = request.getParameter("ip");
			String reg_source = request.getParameter("reg_source");
			String invite_code = request.getParameter("invite_code");
			String sign = request.getParameter("sign");
			String invite_source = request.getParameter("invite_source");
			String md5Sign = Md5Util.md5to32(user_name + verif + reg_ip +
					Constants.API_KEY);
			
			Sms sms = smservice.getCodeByPhone(user_name);  //手机号码获取验证码
			if(sms == null || sms.getCode() == null){
				sr.setStatus(101);
				sr.setContent("今天没有获取到手机验证码");
				sr.setReturnCase(false);
				return sr;
			}
			System.out.println(String.format("n-%s,v-%s,s-%s,r-%s",user_name,verif,sms.getCode(),reg_source));
			if(!sign.equalsIgnoreCase(md5Sign)){
			
				sr.setStatus(101);
				sr.setContent("无效数据");
				sr.setReturnCase(false);
			    return sr;
			}
			if(sms == null || sms.getCode() == null){
				sr.setStatus(101);
				sr.setContent("验证码无效");
				sr.setReturnCase(false);
				
				return sr;
			}
				if(sms!=null && sms.getCode().equalsIgnoreCase(verif)){
					User user = new User();
					
					if(!isPassword(passwd)){
						sr.setStatus(101);
						sr.setContent("密码只能是数字与字符");
						sr.setReturnCase(false);
						
						return sr;
					}
					
					user.setUser_name(user_name);
					String md5 =  Md5Util.md5to32(passwd);
					user.setPasswd(md5);
					if(!inviter_phone.isEmpty())
						user.setInviter_phone(inviter_phone);
					/*if(!invite_code.isEmpty()){
						User other = new User();
						if(isInteger(invite_code)){
							other.setId(Long.parseLong(invite_code));
						}else{
							other.setInvite_code(invite_code);
						}
						
						User invite = userService.selectForObject(other);
						user.setInviter_phone(invite.getUser_name());
					}*/
						
					if(invite_source != null&&!invite_source.isEmpty())
						user.setInvite_source(invite_source);
					user.setCreate_time(new Date());
					user.setCreate_time(new Date());
					user.setReg_source(Integer.parseInt(reg_source));
					user.setReg_ip(reg_ip);
					user.setLast_login_time( new Date());
					user.setPhone(user_name);
					user.setNew_hand(1);
					user.setUser_type(1);
					userService.save(user); //to sun user
					
					
					applicationContext.publishEvent(new RegisterEvent(this,user));
					
					sr.setStatus(100);
					sr.setReturnCase(true);
					sr.setContent("注册成功");
					return sr;
				}else{
					
					sr.setStatus(101);
					sr.setContent("验证码无效");
					sr.setReturnCase(false);
				}
			
		}else{
			sr.setStatus(101);
			sr.setContent("用户已存在");
			sr.setReturnCase(false);
		}
		
		
		return sr;
	}
	
	private boolean isPassword(String str){
		
		return Pattern.matches("^[A-Za-z0-9]+$",str);
	}
	
	private  boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
	}
	
	
	@RequestMapping(value="/invite/friends",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getInviteFriends(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		User friend = new User();
		friend.setInviter_phone(user_name);
		List<UserVo> listVo = userService.selectForList(friend);//邀请好友
		List<Object>  map = new ArrayList<Object>();
		for(int i = 0;i<listVo.size();i++){
			Map<String,String> vo = new HashMap<String,String>();
			User user = listVo.get(i);
			vo.put("register_time", DateUtil.formatDate(user.getCreate_time()));//注册时间
			vo.put("user_name", user.getUser_name());//用户名
			
			UserFundStat fund = new UserFundStat();
			fund.setUser_id(user.getId());
			UserFundStat fundStat =  userFundStatService.selectForObjectById(fund);
			Double coin = fundStat.getInvest_sum()*0.002;
			java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");  
			
			vo.put("coin", df.format(coin));//正经值贡献
			
			
			map.add(vo);
		}
		
		sr.setStatus(100);
		sr.setReturnCase(true);
		Map dataMap=new HashMap();
		dataMap.put("dataList", map);
		dataMap.put("total", listVo.size());
		sr.setContent(dataMap);
		return sr;
		}
	
	//获取邀请码
	@RequestMapping(value="/invite/code",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getInviteCode(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		//boolean phone = PhoneFormatCheckUtils.isChinaPhoneLegal(user_name);
		if(sign.equalsIgnoreCase(md5Sign)){
			
			User user = userService.selectForObjectByPhone(user_name);
			if(user != null){
				String invite_code = user.getInvite_code();
				if(invite_code == null){
					invite_code = user.getId() + "";
					user.setInvite_code(invite_code);
					userService.updateForObjectByPhone(user);
				}
				sr.setReturnCase(true);
				sr.setStatus(100);
				sr.setContent(invite_code);
			}else{
				sr.setReturnCase(false);
				sr.setStatus(101);
				sr.setContent("no");
			}
		}else{
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		}
		
	
		return sr;
	}
	

	//检验手机号
	@RequestMapping(value="/check",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getCheckPhone(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		boolean phone = PhoneFormatCheckUtils.isChinaPhoneLegal(user_name);
		if(sign.equalsIgnoreCase(md5Sign)){
			if(phone){
				sr.setReturnCase(true);
				sr.setStatus(100);
				sr.setContent("ok");
			}else{
				sr.setReturnCase(false);
				sr.setStatus(101);
				sr.setContent("no");
			}
		}else{
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		}
		
	
		return sr;
	}
	
	//获取用户详情
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getDetails(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		User user = userService.selectForObjectByPhone(user_name);
		
		if( null==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(sign.equalsIgnoreCase(md5Sign)){
			if(user != null){
				//查询用户是否绑过卡
				if (StrUtils.emptyJudge(user.getReal_name()) && StrUtils.emptyJudge(user.getIdcard_no()) && user.getStatus() < 2) {
					String res = SinaUtil.queryBankCard(user.getId().toString());
					if (StrUtils.emptyJudge(res) && (res.startsWith("{") || res.startsWith("["))) {
						Map restMap = JsonUtil.jsonToMap(res);
						Object resCode = restMap.get("response_code");
						if(resCode != null && resCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
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
								}
							}
						}
					}
				}

				sr.setReturnCase(true);
				sr.setStatus(100);
				sr.setContent(user);
			}else{
				sr.setReturnCase(false);
				sr.setStatus(101);
				sr.setContent("no");
			}
		}else{
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		}
		
	
		return sr;
	}
	
	//修改密码
	@RequestMapping(value="/passwd",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getUserModifyPassword(HttpServletRequest request,
            HttpServletResponse response){
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		String passwd = request.getParameter("passwd");
		
		User user = userService.selectForObjectByPhone(user_name);
		String verif = request.getParameter("verif");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + passwd + reg_ip +
				Constants.API_KEY);
		
		Sms sms = smservice.getCodeByPhone(user_name);  //手机号码获取验证码
		if(sms==null || sms.getCode() == null){
			sr.setStatus(101);
			sr.setContent("今天没有获取到手机验证码");
			sr.setReturnCase(false);
			return sr;
		}
		System.out.println("strVerif:" +sms.getCode() + "verif:" + verif );
		if(!sms.getCode().equalsIgnoreCase(verif)){
			sr.setStatus(101);
			sr.setContent("验证码不相等，请重新 获取");
			sr.setReturnCase(false);
			return sr;
		}
	
		if(sign.equalsIgnoreCase(md5Sign)){
			if(user == null){
				sr.setContent("用户不存在");
				sr.setReturnCase(false);
				sr.setStatus(101);
			}else{
				String md5 =  Md5Util.md5to32(passwd);
				user.setPasswd(md5);
				userService.update(user);// 更新用户信息接口
				
				sr.setContent("密码修改成功");
				sr.setReturnCase(true);
				sr.setStatus(100);
			}
			
		}else{
			sr.setContent("无数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
		}
		return  sr;
	}
	
	//登录
	@RequestMapping(value="/loginout",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getUserLoginOut(HttpServletRequest request,
            HttpServletResponse response){
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		
		
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		User user = userService.selectForObjectByPhone(user_name);
		
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
		}
		
		if(user != null){
			
			try {
				request.logout();
				
				//cookie.setValue("");
				
				sr.setReturnCase(true);
				sr.setStatus(100);
				sr.setContent("loginOut");
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("no");
		}
		
		return sr;
	}
	
	//登录
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getUserLogin(HttpServletRequest request,
            HttpServletResponse response){
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		
		
		User user = userService.selectForObjectByPhone(user_name);
		
		String passwd = request.getParameter("passwd");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String reg_source=RequestUtils.getReqString(request, "reg_source");
		String is_new=RequestUtils.getReqString(request, "is_new");
		System.out.println("登录开始");
		if((null==reg_source||"".equals(reg_source))&&(null==is_new||"".equals(is_new))){
			sr.setContent("当前系统维护暂时无法登陆，敬请谅解。请前往PC或微信投资");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		String md5Sign = Md5Util.md5to32(user_name + passwd + reg_ip +
				Constants.API_KEY);
		if(sign.equalsIgnoreCase(md5Sign)){
			String md5 =  Md5Util.md5to32(passwd);
			if(user == null){
				sr.setContent("用户不存在");
				sr.setReturnCase(false);
				sr.setStatus(101);
			}else{
				
				if(!user.getPasswd().equalsIgnoreCase(md5)){
					sr.setContent("密码不正确");
					sr.setReturnCase(false);
					sr.setStatus(101);
				}else{
					String login_ip = request.getParameter("login_ip");
					user.setLogin_ip(login_ip);
					user.setUpdate_time(new Date());
					userService.update(user);
					
					Cookie cookie = new Cookie("user_name", user_name);
					response.addCookie(cookie);
					
					
					
					sr.setContent("ok");
					sr.setReturnCase(true);
					sr.setStatus(100);
				}
			}
		}else{
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
		}
		
		
		return sr;
	}
	
	//用户审核
	@RequestMapping(value="/audit/update",method=RequestMethod.GET)
	public @ResponseBody SmsReturn auditUserUpdate(HttpServletRequest request,
            HttpServletResponse response) {
		
		
		SmsReturn sr = new SmsReturn();
		User user = new User();
		List<UserVo> list = userService.selectForList(user);
		
		for(int i = 0;i<list.size();i++){
			User users = list.get(i);
			if(users != null){
				
				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(users.getId());
				UserFundStat vo = userFundStatService.selectForObjectById(userFund);
				 
				if(vo.getBalance()<0){
						
					Double balance = SinaResultUtil.queryBalance(users);
					vo.setBalance(balance);
					//UserFundStat voF = userFundStatService.selectForObjectById(userFund);
					//voF.setBalance(balance);
					
					userFundStatService.updateUserFundStatById(vo);
				}
				
			}
		}
		return sr;
	}
	
	//用户审核
	@RequestMapping(value="/audit/sms",method=RequestMethod.GET)
	public @ResponseBody SmsReturn auditSMS(HttpServletRequest request,
            HttpServletResponse response) {
	
			SmsReturn sr = new SmsReturn();
			Sms sms = new Sms();
			sms.setSms_type(Integer.valueOf(3));
			sms.setPhone("18645876660");
			sms.setSource_ip("117.149.16.71");
			sms.setStatus(2);
			sms.setStatus(0);
			sms.setContent("saf");
			sms.setCode("123456");
			/*if(type.equals("1")||type.equals("2")){
				sms.setCode(StringUtil.getRandom());
			}*/
			sms.setProduct("正道");
			sms.setAmount("");
			sms.setIncome("2018");
			String res = smservice.sendSms(sms);
			
			if(res!=null && "0".equals(res)){					
				sr.setStatus(100);
				sr.setReturnCase(true);
				
			}else{
				sr.setStatus(101);
				sr.setReturnCase(false);
			}
				sr.setContent(res);
		return sr;
	}
	//用户审核
	@RequestMapping(value="/audit",method=RequestMethod.POST)
	public @ResponseBody SmsReturn auditUser(HttpServletRequest request,
            HttpServletResponse response) {
		
		
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		String idcard_no = request.getParameter("idcard_no").trim();
		String real_name = request.getParameter("real_name").trim();
		
		if(real_name.isEmpty()||idcard_no.isEmpty()){
			
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("输入的内容不能为空");
			
			return sr;
		}
		
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + idcard_no + real_name +
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
			
		
		if(!user_name.isEmpty()){
			User user = userService.selectForObjectByPhone(user_name);
			
			if(user == null){
				sr.setContent("没有此用户");
				sr.setReturnCase(false);
				sr.setStatus(101);
				
				return sr;
			}
			
			String result = SinaUtil.setRealName(user.getId(),real_name,idcard_no,user.getReg_ip());
			//System.out.println(result);
			
			System.out.println(result + idcard_no  + "======="+ real_name);
			
			
			if(result.trim().isEmpty()){
				sr.setContent("新浪接口问题");
				sr.setReturnCase(false);
				sr.setStatus(101);
				
				return sr;
			}
			
			Map resultMap = JsonUtil.jsonToMap(result);
			
			Object strCode =  resultMap.get("response_code");
			
			if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
				
				Object strMap =  resultMap.get("response_msg");
				sr.setContent(strMap.toString());
				sr.setReturnCase(false);
				sr.setStatus(101);
				
				return sr;
			}
			
			
			
			user.setIdcard_no(idcard_no);
			user.setReal_name(real_name);
			//调用新浪支付接口  实名认证进行
			
			
			user.setReal_name_auth(1);
			user.setIdcard_auth_time(new Date());
			
			user.setSex(StrUtils.idcardToSex(idcard_no));
			
			user.setBirthday(IDCardUtil.getMonthByIdCard(idcard_no) + "" + IDCardUtil.getDateByIdCard(idcard_no));
			userService.updateForObjectByPhone(user);
			
			applicationContext.publishEvent(new AuditEvent(this,user));
			
			//如果审核通过才能  用户资金统计
			
			sr.setContent("实名认证成功");
			sr.setReturnCase(true);
			sr.setStatus(100);
		}
			
		return sr;
	}
	
	@RequestMapping(value="/realNameInfo")
	public @ResponseBody SmsReturn getRealNameInfo(HttpServletRequest request,
											  HttpServletResponse response){
		SmsReturn sr = new SmsReturn();
		String user_name= RequestUtils.getReqString(request,"user_name");
		String reg_source = RequestUtils.getReqString(request,"reg_source");
		String reg_ip = RequestUtils.getReqString(request,"ip");
		String sign = RequestUtils.getReqString(request,"sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		User user = userService.selectForObjectByPhone(user_name);

		if( null==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			return sr;
		}

		if(sign.equalsIgnoreCase(md5Sign)){
			if(user != null){
				sr.setReturnCase(true);
				sr.setStatus(100);
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("real_name_auth",user.getReal_name_auth());
				if(user.getReal_name_auth()==1){
					map.put("real_name",user.getReal_name());
					map.put("idcard_no",user.getIdcard_no());
				}
				sr.setContent(map);
			}else{
				sr.setReturnCase(false);
				sr.setStatus(101);
				sr.setContent("no");
			}
		}else{
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		}
		return sr;
	}
	
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setSmsService(ISmsService smservice) {
		this.smservice = smservice;
	}
	
	
	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}

}
