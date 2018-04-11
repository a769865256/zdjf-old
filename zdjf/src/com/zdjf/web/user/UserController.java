package com.zdjf.web.user;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zdjf.components.events.AuditEvent;
import com.zdjf.components.events.RegisterEvent;
import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.sms.Sms;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.sms.ISmsService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.PhoneFormatCheckUtils;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.StringUtil;
import com.zdjf.util.UserConstants;
import com.zdjf.util.VerifyCodeUtils;
import com.zdjf.webservice.express.SmsReturn;




@Controller
@RequestMapping("/user")
public class UserController {

	private IUserService userService;

	private ISmsService smservice;

	private IUserBankService userBankService;

	private ApplicationContext applicationContext;


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

	}

	@RequestMapping(value="/register",method=RequestMethod.POST)//添加用户
	public String registerUser(HttpServletRequest request,HttpServletResponse response) {


		String user_name = request.getParameter("user_name");
		User old = userService.selectForObjectByPhone(user_name);
		boolean result = false;
		Cookie[] cookies=request.getCookies();
		if (cookies!=null) {
			//out.write("您上次访问的时间是：");
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("user_name")) {
					//strVerify = cookie.getValue();
					cookie.setValue(user_name);
					response.addCookie(cookie);
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
		if(old == null){
			String passwd = request.getParameter("passwd");
			String inviter_phone = request.getParameter("inviter_phone");
			String verif = request.getParameter("verif");
			String reg_ip = request.getParameter("reg_ip");
			Sms sms = smservice.getCodeByPhone(user_name);  //手机号码获取验证码

			if(sms == null || sms.getCode()==null){
				return "重新获取手机验证码";
			}

			if(!sms.getCode().equalsIgnoreCase("")){
				if(sms.getCode().equalsIgnoreCase(verif)){
					User user = new User();

					user.setUser_name(user_name);
					String md5 =  Md5Util.md5to32(passwd);
					user.setPasswd(md5);
					user.setPhone(user_name);
					user.setInviter_phone(inviter_phone);
					//user.setInvite_source(invite_source);
					user.setCreate_time(new Date());
					user.setReg_source(1);
					user.setReg_ip(reg_ip);
					user.setLast_login_time( new Date());
					user.setUser_type(1);

					userService.save(user); //to sun user

					applicationContext.publishEvent(new RegisterEvent(this,user));
					HttpSession session = request.getSession(true); 
					session.removeAttribute("user_name");
					session.setAttribute("user_name", user_name);
					return "user/audit";
				}else{
					return "验证码失效!";
				}
			}else{
				return "手机验证码不能为空";
			}

		}else{
			return "用户已存在，建议选其他手机注册";
		}


	}

	//检验手机号
	@RequestMapping(value="/check",method=RequestMethod.GET)
	public @ResponseBody String getCheckPhone(HttpServletRequest request,
			HttpServletResponse response, Model model){

		return "";
	}


	//用户详情
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public @ResponseBody String getUserDet(HttpServletRequest request,
			HttpServletResponse response, Model model){

		String user_name = request.getParameter("user_name");


		User user = userService.selectForObjectByPhone(user_name);

		if(user == null){
			return JSON.toJSONString(new Result("user do not exist"));
		}else{

			model.addAttribute("user",user);
		}

		return JSON.toJSONString(user);
	}

	//用户详情
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody String getUserUpdate(HttpServletRequest request,
			HttpServletResponse response){

		String user_name = request.getParameter("user_name");
		String passwd = request.getParameter("passwd");

		User user = userService.selectForObjectByPhone(user_name);

		if(user == null){
			return "user do not exist";
		}else{
			String md5 =  Md5Util.md5to32(passwd);
			user.setPasswd(md5);
			userService.updateForObjectByPhone(user);// 更新用户信息接口
		}

		return "SUCCESS";
	}

	//用户详情
	@RequestMapping(value="/passwd",method=RequestMethod.POST)
	public @ResponseBody String getUserModifyPassword(HttpServletRequest request,
			HttpServletResponse response){

		String user_name = request.getParameter("user_name");
		String passwd = request.getParameter("passwd");

		User user = userService.selectForObjectByPhone(user_name);

		if(user == null){
			return JSON.toJSONString(new Result("user do not exist"));
		}else{
			String md5 =  Md5Util.md5to32(passwd);
			user.setPasswd(md5);
			userService.update(user);// 更新用户信息接口
		}

		return JSON.toJSONString("password  modify!!!");
	}

	//用户详情
	@RequestMapping(value="/del",method=RequestMethod.DELETE)
	public @ResponseBody String getUserDel(HttpServletRequest request,
			HttpServletResponse response){

		String user_name = request.getParameter("user_name");

		User user = userService.selectForObjectByPhone(user_name);


		if(user == null){
			return JSON.toJSONString(new Result("user do not exist"));
		}else{

			//model.addAttribute("user",user);

			//userService.save(user);// 删除
		}

		return JSON.toJSONString("delete user !!!");
	}



	/**
	 * 文件下载方法
	 * @param uuid 上传文件时生成的文件记录号
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
	public void getFile(HttpServletRequest request, HttpServletResponse response) throws IOException {


		File dir = new File(System.getProperty("catalina.home") + "/download/verifies");
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		File file = new File(dir, verifyCode + ".jpg");
		VerifyCodeUtils.outputImage(200, 80, file, verifyCode);

		//获取浏览器访问访问服务器时传递过来的cookie数组
		        Cookie[] cookies = request.getCookies();
		        //如果用户是第一次访问，那么得到的cookies将是null
		        if (cookies!=null) {
		        	boolean result = false;
		            for (int i = 0; i < cookies.length; i++) {
		                Cookie cookie = cookies[i];
		                if (cookie.getName().equals("verify")) {
		                	result = true;
		                    System.out.println(cookie.getValue());        
		                    cookie.setValue(verifyCode.toLowerCase());
		                    response.addCookie(cookie);
		                    break;
		                }
		            }
		            
		            if(!result){
		            	//用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
		                Cookie cookie = new Cookie("verify", verifyCode.toLowerCase());//创建一个cookie，cookie的名字是lastAccessTime
		                //将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
		                response.addCookie(cookie);
		                
		            }
		        }else {
		        	//用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
		            Cookie cookie = new Cookie("verify", verifyCode.toLowerCase());//创建一个cookie，cookie的名字是lastAccessTime
		            //将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
		            response.addCookie(cookie);
		        }

//		//存入会话session 
//		HttpSession session = request.getSession(true); 
//		//删除以前的
//		session.removeAttribute("verify");
//		session.setAttribute("verify", verifyCode.toLowerCase());

		//

		if (file.exists()) {

			byte[] buffer = new byte[1024];

			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);

				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while ( i != -1 ) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}

			} catch(Exception e) {

				e.printStackTrace();

			} finally {				
				try {
					bis.close();
					fis.close();
				} catch (IOException e) {
				}

			}
		} 

	}

	@RequestMapping(value = "/verifySms", method = RequestMethod.GET)
	public @ResponseBody String verify(HttpServletRequest request, HttpServletResponse response){
		String user_name = request.getParameter("user_name");
		String sms_verify = request.getParameter("verify");
		Sms sms = smservice.getCodeByPhone(user_name);  //手机号码获取验证码
		if(sms!=null && !sms.getCode().equalsIgnoreCase("")){
			if(sms.getCode().equalsIgnoreCase(sms_verify)){
				return "SUCCESS";
			}else{
				return "验证码失效";
			}
		}else{
			return "手机验证码不能为空";
		}
	}

	//验证码验证
	@RequestMapping(value = "/checkimagecode", method=RequestMethod.GET)
	public @ResponseBody String checkTcode(HttpServletRequest request,HttpServletResponse response) {
		String validateCode = request.getParameter("validateCode");
		validateCode=validateCode.toUpperCase();
		Cookie[] cookies = request.getCookies();
		String strVerify = "";
		//如果用户是第一次访问，那么得到的cookies将是null
		if (cookies!=null) {
			//out.write("您上次访问的时间是：");
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("verify")) {
					strVerify = cookie.getValue();
				}
			}
		}
		if(!StringUtils.isEmpty(validateCode)){
			if(validateCode.equalsIgnoreCase(strVerify)){
				return "SUCCESS"; 
			}return "图形验证失败";
		}else{
			return "图形验证码不能为空" ;
		}
	}


	//用户登录    放假期间  验证码去掉
	@SuppressWarnings("unused")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public  String getUserLogin(HttpServletRequest request,
			HttpServletResponse response){

		String user_name = request.getParameter("user_name");
		String passwd = request.getParameter("passwd");

		User user = userService.selectForObjectByPhone(user_name);

		//获取浏览器访问访问服务器时传递过来的cookie数组
		Cookie[] cookies = request.getCookies();
		//		//存入会话session 
		////        HttpSession session = request.getSession(true); 
		//        String strVerify = "";
		////        strVerify=(String) request.getSession().getAttribute("verify");
		////        如果用户是第一次访问，那么得到的cookies将是null
		//        if (cookies!=null) {
		//            //out.write("您上次访问的时间是：");
		//            for (int i = 0; i < cookies.length; i++) {
		//                Cookie cookie = cookies[i];
		//                if (cookie.getName().equals("verify")) {
		//                	strVerify = cookie.getValue();
		//                }
		//            }
		//        }

		//		if(verify.toLowerCase().equalsIgnoreCase(strVerify)){
		String md5 =  Md5Util.md5to32(passwd);
		if(user == null){
			return JSON.toJSONString(new Result("user do not exist"));
		}else{

			if(!user.getPasswd().equalsIgnoreCase(md5)){
				return JSON.toJSONString(new Result("password error"));
			}else{
				
				// 登录用户放入Session
				HttpSession session = request.getSession();
				session.setAttribute("CURRENT_LOGED_IN_USER", user_name);
				
				String login_ip = request.getParameter("login_ip");
				user.setLogin_ip(login_ip);
				user.setUpdate_time(new Date());
				userService.update(user);
				//					session.removeAttribute("session");
				//					session.setAttribute("user_name", user_name);
				boolean result = false;
				if (cookies!=null) {
					//out.write("您上次访问的时间是：");
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						if (cookie.getName().equals("user_name")) {
							//strVerify = cookie.getValue();
							cookie.setValue(user_name);
							//response.addCookie(cookie);
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
		}
		//		}else{
		//			return JSON.toJSONString(new Result("verify error"));
		//		}


		return "sys/user/userMain";
	}

	//用户审核
	@RequestMapping(value="/audit",method=RequestMethod.POST)
	public @ResponseBody Result auditUser(HttpServletRequest request,
            HttpServletResponse response) {
		String user_name = BrowseUtil.getCookie(request, response);

		if(!user_name.isEmpty()){
			User user = userService.selectForObjectByPhone(user_name);


			String idcard_no = request.getParameter("idcard_no").trim();
			String real_name = request.getParameter("real_name").trim();

			if(idcard_no.isEmpty() || real_name.isEmpty()){
				return new Result(Result.FAIL,"输入内容不为空");
			}

			if((user.getIdcard_no() == null||user.getIdcard_no()=="")&&!idcard_no.isEmpty()){

				//String result = IDCardUtil.IDCardValidate(idcard_no);//身份证验证是否合法

				String result = SinaUtil.setRealName(user.getId(),real_name,idcard_no,user.getReg_ip());
				System.out.println(result);

				System.out.println(idcard_no  + "======="+ real_name);

				String remark = user.getRemark();
				if(remark == null || remark.isEmpty())
					user.setRemark("audit:" + result);
				else{

					user.setRemark(remark + ";audit:" + result);
				}

				userService.update(user);

				if(result.trim().isEmpty()){

					return new Result(Result.FAIL,"新浪接口问题");

				}

				Map resultMap = JsonUtil.jsonToMap(result);

				String strCode = (String) resultMap.get("response_code");



				if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
					return new Result(Result.FAIL,"实名验证未通过");
				}


				user.setIdcard_no(idcard_no);
				user.setReal_name(real_name);
				//调用新浪支付接口  实名认证进行
				user.setReal_name_auth(1);
				user.setIdcard_auth_time(new Date());
				userService.update(user);

				applicationContext.publishEvent(new AuditEvent(this,user));

				return new Result(Result.SUCCESS,"审核通过成功!");
			}


		}

		return new Result(Result.FAIL,"没有获取的用户信息");
	}

	//用户绑定银行卡
	@RequestMapping(value="/bind",method=RequestMethod.POST)
	public Result bindBankCard(HttpServletRequest request,
			HttpServletResponse response) {

		String user_name = BrowseUtil.getCookie(request, response);

		if(user_name.isEmpty()){
			return new Result(Result.FAIL,"获取的用户信息失败");
		}

		//String user_id = request.getParameter("user_id");
		String bank_no = request.getParameter("bank_no");
		String bank_alias = request.getParameter("bank_alias");
		String bank_open = request.getParameter("bank_open");
		String card_user_name = request.getParameter("card_user_name");
		String phone = request.getParameter("phone");
		//String type = request.getParameter("type");
		//String remark = request.getParameter("remark");


		UserBank userBank = new UserBank();



		User user = userService.selectForObjectByPhone(user_name);

		userBank.setUser_id(user.getId());
		userBank.setBank_alias(bank_alias);
		userBank.setBank_no(bank_no);
		userBank.setBank_open(bank_open);
		userBank.setPhone(phone);
		userBank.setCard_user_name(card_user_name);
		userBank.setCreate_time(new Date());
		userBank.setStatus(1);
		userBank.setType(2);
		userBank.setRemark("绑定此卡");

		userBankService.save(userBank);

		return new Result(Result.SUCCESS,"银行卡绑定成功");
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)//添加管理用户
	public Result addUser(@RequestBody User user) {


		userService.save(user);

		return new Result("保存成功!");
	}

	@RequestMapping(value="/toAudit",method=RequestMethod.GET)//添加管理用户
	public String toAudit(HttpServletRequest request,
			HttpServletResponse response) {
		return "user/audit";
	}
	@RequestMapping(value="/toInvest",method=RequestMethod.GET)//添加管理用户
	public String toInvest(HttpServletRequest req,
			HttpServletResponse response,Model model) {
		Map<String, Object> params = new HashMap<String, Object>(
				Constants.NUM_TEN);
		// 取得请求中的查询类型参数
		params.put(Constants.REQ_PARAM_SEARCH_TYPE, StrUtils.convToInt(
				RequestUtils.getReqString(req, Constants.REQ_PARAM_SEARCH_TYPE)));


		// 取得请求中的查询名称参数
		params.put(Constants.REQ_PARAM_SEARCH_PARAM, RequestUtils.getReqString(
				req, Constants.REQ_PARAM_SEARCH_PARAM, true));
		// 取得请求中的开始时间
		params.put(Constants.REQ_PARAM_START_DATE,
				RequestUtils.getReqString(req, Constants.REQ_PARAM_START_DATE));
		// 取得请求中的结束时间
		params.put(Constants.REQ_PARAM_END_DATE,
				RequestUtils.getReqString(req, Constants.REQ_PARAM_END_DATE));

		//范围时间内投资起始金额
		params.put("investTotalMin",
				RequestUtils.getReqString(req,
						"investTotalMin"));

		//范围时间内投资最大金额
		params.put("investTotalMax",
				RequestUtils.getReqString(req,
						"investTotalMax"));


		params.put(UserConstants.REQ_PARAM_MONEY_MIN,
				RequestUtils.getReqString(req,
						UserConstants.REQ_PARAM_MONEY_MIN));
		params.put(UserConstants.REQ_PARAM_MONEY_MAX,
				RequestUtils.getReqString(req,
						UserConstants.REQ_PARAM_MONEY_MAX));
		params.put("pagename", "selectInvestPage");
		params.put("total", "selectInvestCount");
		Page page = PageUtils.createPage(req);
		page=userService.page(page, params);
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sys/user/invest";
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
	public void setUserBankService(IUserBankService userBankService) {
		this.userBankService = userBankService;
	}

	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
