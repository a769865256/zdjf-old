package com.zdjf.web.front;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zdjf.webservice.express.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.events.RegisterEvent;
import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.sms.Sms;
import com.zdjf.domain.user.User;
import com.zdjf.service.sms.ISmsService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.Md5Util;
import com.zdjf.util.PhoneFormatCheckUtils;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.UrlConstant;
import com.zdjf.webservice.express.SmsReturn;

@Controller
@RequestMapping("/")
public class FrontUserController {

	private IUserService userService;
	private ISmsService smservice;
	private ApplicationContext applicationContext;

	@SuppressWarnings("unused")
	@RequestMapping(value="login",method=RequestMethod.POST)
	public  String getUserLogin(HttpServletRequest request,
			HttpServletResponse response){
		String user_name = request.getParameter("user_name");
		String url = request.getParameter("url");
		request.setAttribute("url", url);
		if(PhoneFormatCheckUtils.isChinaPhoneLegal(user_name)==false){
			request.setAttribute("errorUser", "用户名有误！");
			return "front/login";
		}
		String passwd = request.getParameter("passwd");
		if(null==passwd||""==passwd){
			request.setAttribute("errorPasswd", "密码不能为空");
			return "front/login";
		}
		User user = userService.selectForObjectByPhone(user_name);

		if(null==user){
			request.setAttribute("errorUser", "用户不存在");
			return "front/login";
		}

		//获取浏览器访问访问服务器时传递过来的cookie数组
		Cookie[] cookies = request.getCookies();
		String md5 =  Md5Util.md5to32(passwd);

		if(!user.getPasswd().equalsIgnoreCase(md5)){
			request.setAttribute("errorPasswd", "密码错误");
			return "front/login";
		}else{
			String login_ip = RequestUtils.getIp(request);
			user.setLogin_ip(login_ip);
			user.setUpdate_time(new Date());
			user.setLast_login_time(new Date());
			userService.update(user);
			boolean result = false;
			if (cookies!=null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (cookie.getName().equals("user_name")) {
						cookie.setValue(user_name);
						result = true;
						break;
					}
				}
			}
			if(!result){
				//用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
				Cookie cookie = new Cookie("user_name", user_name);//创建一个cookie，cookie的名字是lastAccessTime
				//将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
				response.addCookie(cookie);
			}
			//登录用户存入session
			request.getSession().setAttribute("CURRENT_LOGED_IN_USER", user_name);
		}
		if(url!=null && !"".equals(url) && !"null".equals(url)){
			return "redirect:"+url;
		}
		return "redirect:/index.action";
	}
	
	@RequestMapping(value="m/login",method=RequestMethod.POST)
	public  String mUserLogin(HttpServletRequest request,
			HttpServletResponse response,Model model){
		String user_name = request.getParameter("user_name");
		if(PhoneFormatCheckUtils.isChinaPhoneLegal(user_name)==false){
			request.setAttribute("errorMsg", "用户名有误！");
			return "front/m/login/login";
		}
		String passwd = request.getParameter("passwd");
		if(null==passwd||""==passwd){
			request.setAttribute("errorMsg", "密码不能为空");
			return "front/m/login/login";
		}
		User user = userService.selectForObjectByPhone(user_name);

		if(null==user){
			request.setAttribute("errorMsg", "用户不存在");
			return "front/m/login/login";
		}

		//获取浏览器访问访问服务器时传递过来的cookie数组
		Cookie[] cookies = request.getCookies();
		String md5 =  Md5Util.md5to32(passwd);
		if(!user.getPasswd().equalsIgnoreCase(md5)){
			request.setAttribute("errorMsg", "密码错误");
			return "front/login";
		}else{
			String login_ip = RequestUtils.getIp(request);
			user.setLogin_ip(login_ip);
			user.setUpdate_time(new Date());
			user.setLast_login_time(new Date());
			userService.update(user);
			boolean result = false;
			if (cookies!=null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					if (cookie.getName().equals("user_name")) {
						cookie.setValue(user_name);
						result = true;
						break;
					}
				}
			}
			if(!result){
				//用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
				Cookie cookie = new Cookie("user_name", user_name);//创建一个cookie，cookie的名字是lastAccessTime
				//将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
				response.addCookie(cookie);
			}

		}
		return "redirect:/index.action";
	}

	@RequestMapping(value="register",method=RequestMethod.POST)//添加用户
	public String registerUser(HttpServletRequest request,HttpServletResponse response) {
		String user_name = request.getParameter("user_name");
		String passwd = request.getParameter("passwd");
		String passwd2 = request.getParameter("passwd2");
		String inviter_phone = request.getParameter("inviter_phone");
		String verif = request.getParameter("verif");
		String type = request.getParameter("type");
		String invite_source=RequestUtils.getReqString(request, "invite_source");
		if(PhoneFormatCheckUtils.isChinaPhoneLegal(user_name)==false){
			request.setAttribute("errorPhone", "手机号码有误");
			return "front/register";
		}
		if(!StrUtils.emptyJudge(verif)){
			request.setAttribute("errorCode", "验证码不能为空");
			return "front/register";
		}
		if(!StrUtils.emptyJudge(passwd)){
			request.setAttribute("errorPasswd", "密码不能为空");
			return "front/register";
		}
		if(!StrUtils.emptyJudge(passwd2)){
			request.setAttribute("errorPasswd2", "请再次输入密码");
			return "front/register";
		}
		if(!passwd.equals(passwd2)){
			request.setAttribute("errorPasswd2", "两次输入密码不一致");
			return "front/register";
		}


		User old = userService.selectForObjectByPhone(user_name);
		boolean result = false;
		Cookie[] cookies=request.getCookies();
		if (cookies!=null) {
			//out.write("您上次访问的时间是：");
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("user_name")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}

		if(old == null){
			String reg_ip = RequestUtils.getIp(request);
			Sms sms = smservice.getCodeByPhone(user_name);  //手机号码获取验证码
			if(!sms.getCode().equalsIgnoreCase("")){
				if(sms.getCode().equalsIgnoreCase(verif)){
					User user = new User();

					user.setUser_name(user_name);
					String md5 =  Md5Util.md5to32(passwd);
					user.setPasswd(md5);
					user.setInviter_phone(inviter_phone);
					user.setCreate_time(new Date());
					user.setReg_source(1);
					user.setReg_ip(reg_ip);
					user.setLast_login_time( new Date());
					user.setNew_hand(1);
					user.setUser_type(1);
					user.setPhone(user_name);
					user.setInvite_source(invite_source);
					if(type!=null && !"".equals(type)){
						user.setInvite_source(type);
					}
					userService.save(user); //to sun user
					if(!result){
						//用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
						Cookie cookie = new Cookie("user_name", user_name);//创建一个cookie，cookie的名字是lastAccessTime
						//将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
						cookie.setMaxAge(30*60);//过期时间半小时
						response.addCookie(cookie);
						request.getSession().setAttribute("CURRENT_LOGED_IN_USER", user_name);//2018-1-15 add
					}
					applicationContext.publishEvent(new RegisterEvent(this,user));
					return "redirect:/toRegisterSuccess.action";//2018-3-23 edit
				}else{
					request.setAttribute("errorCode", "验证码错误");
					return "front/register";
				}

			}else{
				request.setAttribute("errorCode", "验证码发送失败");
				return "front/register";
			}
		}else{
			request.setAttribute("errorPhone", "用户已存在");
			return "front/register";
		}
	}

	/**
	 * @description 跳至注册成功页面
	 * @author Andrew
	 * @date_time 2018-03-23 16:06
	 * @param
	 * @return java.lang.String
	 */
	@RequestMapping(value="/toRegisterSuccess", method = RequestMethod.GET)
	public String toRegisterSuccess(){
		return "front/regist_success";
	}
	
	@RequestMapping(value="/phoneVerify")
	public @ResponseBody SmsReturn phoneVerify(HttpServletRequest request,
			HttpServletResponse response){		
		String acceptHeader = request.getHeader("X-Requested-With"); 
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		String ip = request.getParameter("ip");
		String questType = request.getParameter("questType");
		SmsReturn sr = new  SmsReturn();
		sr.setStatus(101);
		if (acceptHeader!=null && !"".equals(acceptHeader) ) {
			if(phone!=null && type!=null && PhoneFormatCheckUtils.isChinaPhoneLegal(phone)){
				User user = userService.selectForObjectByPhone(phone);
				if("register".equals(questType)){
					if(user!=null){
						sr.setContent("此号码已被注册！");
					}else{
						sr.setStatus(100);
						sr.setContent(Md5Util.md5to32(phone+type+ip+Constants.API_KEY));
					}					
				}else if("forget".equals(questType)){
					if(user!=null){
						sr.setStatus(100);
						sr.setContent(Md5Util.md5to32(phone+type+ip+Constants.API_KEY));
					}else{
						sr.setContent("手机号码有误！");
						
					}
				}
			}else{
				sr.setContent("手机号码有误！");
			}
		} else {
			sr.setContent("请求错误！");
		}
		return sr;
	}
	
	@RequestMapping(value="m/register",method=RequestMethod.POST)//添加用户
	public String mRegisterUser(HttpServletRequest request,HttpServletResponse response) {
		String user_name = request.getParameter("user_name");
		String passwd = request.getParameter("passwd");
		String inviter_phone = request.getParameter("inviter_phone");
		String verif = request.getParameter("verif");
		if(PhoneFormatCheckUtils.isChinaPhoneLegal(user_name)==false){
			request.setAttribute("errorMsg", "手机号码有误");
			return "front/m/register";
		}
		if(!StrUtils.emptyJudge(verif)){
			request.setAttribute("errorMsg", "验证码不能为空");
			return "front/m/register";
		}
		if(!StrUtils.emptyJudge(passwd)){
			request.setAttribute("errorMsg", "密码不能为空");
			return "front/m/register";
		}
//		if(!StrUtils.emptyJudge(passwd2)){
//			request.setAttribute("errorPasswd2", "请再次输入密码");
//			return "front/register";
//		}
//		if(!passwd.equals(passwd2)){
//			request.setAttribute("errorPasswd2", "两次输入密码不一致");
//			return "front/register";
//		}


		User old = userService.selectForObjectByPhone(user_name);
		boolean result = false;
		Cookie[] cookies=request.getCookies();
		if (cookies!=null) {
			//out.write("您上次访问的时间是：");
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("user_name")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}

		if(old == null){
			String reg_ip = RequestUtils.getIp(request);
			Sms sms = smservice.getCodeByPhone(user_name);  //手机号码获取验证码
			if(!sms.getCode().equalsIgnoreCase("")){
				if(sms.getCode().equalsIgnoreCase(verif)){
					User user = new User();

					user.setUser_name(user_name);
					String md5 =  Md5Util.md5to32(passwd);
					user.setPasswd(md5);
					user.setInviter_phone(inviter_phone);
					//user.setInvite_source(invite_source);
					user.setCreate_time(new Date());
					user.setReg_source(1);
					user.setReg_ip(reg_ip);
					user.setLast_login_time( new Date());
					user.setPhone(user_name);
					userService.save(user); //to sun user
					if(!result){
						//用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
						Cookie cookie = new Cookie("user_name", user_name);//创建一个cookie，cookie的名字是lastAccessTime
						//将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
						cookie.setMaxAge(30*60);//过期时间半小时
						response.addCookie(cookie);
						
					}
					applicationContext.publishEvent(new RegisterEvent(this,user));
					return "redirect:/index.action";
				}else{
					request.setAttribute("errorMsg", "验证码错误");
					return "front/m/register";
				}

			}else{
				request.setAttribute("errorMsg", "验证码发送失败");
				return "front/m/register";
			}
		}else{
			request.setAttribute("errorPhone", "用户已存在");
			return "front/register";
		}
	}
	
	
	
	/*  此方法禁止再启用（陈师回批注）
	@RequestMapping(value="/send")
	public @ResponseBody SmsReturn sendSms(HttpServletRequest request,
			HttpServletResponse response){

		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		String ip = request.getParameter("ip");

		SmsReturn sr = new SmsReturn();
		if(phone!=null && type!=null && PhoneFormatCheckUtils.isChinaPhoneLegal(phone)){

			Sms sms = new Sms();
			sms.setSms_type(Integer.valueOf(type));
			sms.setPhone(phone);
			sms.setSource_ip(ip);
			sms.setStatus(2);
			sms.setStatus(0);
			if(type.equals("1")||type.equals("2")){
				sms.setCode(StringUtil.getRandom());
			}
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

		}else{
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("请求失败");
		}

		return sr;

	}*/

	/**
	 * 忘记密码第二步
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/forget",method=RequestMethod.POST)
	public String getUserModifyPassword(HttpServletRequest request,
			HttpServletResponse response){

		String type = request.getParameter("type");
		String ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String user_name = request.getParameter("user_name");
		String passwd = request.getParameter("passwd");
		String passwd2 = request.getParameter("passwd2");
		if(Md5Util.md5to32(user_name+type+ip+Constants.API_KEY).equals(sign)){
			if(!StrUtils.emptyJudge(passwd)){
				request.setAttribute("error", "密码不能为空");
				return "front/back";
			}
			if(!StrUtils.emptyJudge(passwd2)){
				request.setAttribute("error", "密码不能为空");
				return "front/back";
			}
			if(!passwd.equals(passwd2)){
				request.setAttribute("error", "两次输入密码不一致");
				return "front/back";
			}
			User user = userService.selectForObjectByPhone(user_name);
			String md5 =  Md5Util.md5to32(passwd);
			user.setPasswd(md5);
			userService.update(user);// 更新用户信息接口
			return "redirect:/success.action?type=1";
		}else{
			return "redirect:toBack.action";
		}
	}
	
	@RequestMapping(value="/m/forget",method=RequestMethod.POST)
	public String mUserModifyPassword(HttpServletRequest request,
			HttpServletResponse response){

		String type = request.getParameter("type");
		String ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String user_name = request.getParameter("user_name");
		String passwd = request.getParameter("passwd");
		if(Md5Util.md5to32(user_name+type+ip+Constants.API_KEY).equals(sign)){
			if(!StrUtils.emptyJudge(passwd)){
				request.setAttribute("error", "密码不能为空");
				return "front/back";
			}
			User user = userService.selectForObjectByPhone(user_name);
			String md5 =  Md5Util.md5to32(passwd);
			user.setPasswd(md5);
			userService.update(user);// 更新用户信息接口
			return "redirect:/success.action?type=1";
		}else{
			return "redirect:toBack.action";
		}
	}
	
	@RequestMapping(value="/success",method=RequestMethod.GET)
	public String success(HttpServletRequest request,HttpServletResponse response){
		String type = request.getParameter("type");
		
		request.setAttribute("type", type);
		return "front/success";
	}

	/**
	 * 忘记密码第一步
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/verifySms", method = RequestMethod.POST)
	public String verify(HttpServletRequest request, HttpServletResponse response){
		String user_name = request.getParameter("user_name");
		String sms_verify = request.getParameter("verify");
		String type = request.getParameter("type");
		String ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		if(user_name!=null && type!=null && sign!=null && PhoneFormatCheckUtils.isChinaPhoneLegal(user_name)){
			if(Md5Util.md5to32(user_name+type+ip+Constants.API_KEY).equals(sign)){				
				Sms sms = smservice.getCodeByPhone(user_name);  //手机号码获取验证码
				if(sms==null || sms.getCode()==null){
					request.setAttribute("error", "验证码错误");
					return "front/back"; 
				}
				long createTime = sms.getCreate_time().getTime();
				long nowTtime = new Date().getTime();
				
				
				if(nowTtime-createTime>60*1000*30){
					request.setAttribute("error", "验证码失效");
					return "front/back";
				}else if(sms_verify.equals(sms.getCode())){
					request.setAttribute("user_name", user_name);
					request.setAttribute("verify", sms_verify);
					request.setAttribute("type", type);
					request.setAttribute("sign", sign);
					return "front/forget";
				}else{
					request.setAttribute("error", "验证码错误");
					return "front/back";
				}
			}else{
				request.setAttribute("error", "错误请求");
				return "front/back";
			}
		}else{
			request.setAttribute("error", "手机号码有误");
			return "front/back";
		}
		
	}

	
	
	
	

	@RequestMapping("toLogin")
	public String getLogin(HttpServletRequest request,
			HttpServletResponse response){
		String url = request.getParameter("url");
		request.setAttribute("url", url);
		String domainName = request.getServerName();
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否来自手机端 手机端返回手机页面
			return "front/m/login/login";
		}else{
			return "front/login";
		}
		
	}
	@RequestMapping("toRegister")
	public String toRegister(HttpServletRequest request,
			HttpServletResponse response){
		String domainName = request.getServerName();
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否来自手机端 手机端返回手机页面
			return "front/m/login/register";
		}else{
			return "front/register";
		}
	}
	@RequestMapping("toBack")
	public String toBack(HttpServletRequest request,
			HttpServletResponse response){
		String domainName = request.getServerName();
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否来自手机端 手机端返回手机页面
			return "front/m/login/forget";
		}else{
			return "front/back";
		}
	}
	
	@RequestMapping("toLogout")
	public String toLogout(HttpServletRequest request,
			HttpServletResponse response){
		String user_name=BrowseUtil.getCookie(request, response);
		if(request.getCookies() != null){
			Cookie[] cookies=request.getCookies();
			for(int i=0;i<cookies.length;i++){
				Cookie cookie=cookies[i];
				if(cookie.getName().equals("user_name")){
					cookie=new Cookie("user_name",null);
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		return "redirect:toLogin.action";
	}

	/**
	 * 修改用户登录密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "modUserPwd", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn modUserPwd(HttpServletRequest request, HttpServletResponse response) {
		JsonReturn jr = new JsonReturn();
		String old_pwd = request.getParameter("old_pwd");
		String new_pwd = request.getParameter("new_pwd");
		String new_pwd_again = request.getParameter("new_pwd_again");
		//判断请求参数
		if(!StrUtils.emptyJudge(old_pwd)){
			jr.setStatus(101);
			jr.setContent("请输入当前密码");
			return jr;
		}
		if(!StrUtils.emptyJudge(new_pwd)){
			jr.setStatus(102);
			jr.setContent("请输入新密码");
			return jr;
		}
		if(!StrUtils.emptyJudge(new_pwd)){
			jr.setStatus(103);
			jr.setContent("请再次输入新密码");
			return jr;
		}
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		if (!user.getPasswd().equals(Md5Util.md5to32(old_pwd))){
			jr.setStatus(101);
			jr.setContent("当前密码错误");
			return jr;
		} else if (!new_pwd.equals(new_pwd_again)) {
			jr.setStatus(103);
			jr.setContent("两次输入的密码不一致");
			return jr;
		} else {//校验成功，修改密码
			user.setPasswd(Md5Util.md5to32(new_pwd));
			user.setUpdate_time(new Date());
			userService.update(user);
			//清除session，cookies重新登录
			request.getSession().invalidate();
			if(request.getCookies() != null){
				Cookie[] cookies=request.getCookies();
				for(int i=0;i<cookies.length;i++){
					Cookie cookie=cookies[i];
					if(cookie.getName().equals("user_name")){
						cookie=new Cookie("user_name",null);
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
			jr.setStatus(100);
			jr.setContent("密码修改成功！");
			return jr;
		}
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
	
}
