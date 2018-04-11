package com.zdjf.web.sys.login;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.admin.BusinessDayStat;
import com.zdjf.domain.admin.SysUser;
import com.zdjf.domain.dto.UserXmlNode;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.DaoException;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.admin.ILoginService;
import com.zdjf.service.admin.ISysUserService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.util.VerifyCodeUtils;
import com.zdjf.util.XmlToJurisdiction;


@Controller
@RequestMapping("/sysLogin")
public class SysLoginController {

	private ILoginService loginService;
	
	private ISysUserService sysUserService;
	
	private ILenderService lenderService;
	
	private IUserService userService;
	
	private void loadCommon(Model model){
		
	}
	
	//添加借款方
	@Transactional(rollbackFor = { Exception.class }) 
	@RequestMapping(value="/lender/add",method=RequestMethod.POST)
	public Result addLender(HttpServletRequest request) throws Exception{
		
		Lender lender  = new Lender();

		String amount = request.getParameter("amount");
		String lender_type = request.getParameter("lender_type");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String idcard = request.getParameter("idcard");
		String marital = request.getParameter("marital");
		String address = request.getParameter("address");
		int mType = Integer.parseInt(lender_type);
		if(mType == 2){//出借人是公司时候  
			String comp_alias = request.getParameter("comp_alias");
			String comp_name = request.getParameter("comp_name");
			String comp_code = request.getParameter("comp_code");
			String reg_date = request.getParameter("reg_date");
			String reg_money = request.getParameter("reg_money");
			String comp_address = request.getParameter("comp_address");
			
			lender.setComp_alias(comp_alias);
			lender.setComp_name(comp_name);
			lender.setComp_code(comp_code);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
			Date dateF = sdf.parse(reg_date); 
			
			lender.setReg_date(dateF);
			lender.setReg_money(RoofNumberUtils.StringtoDouble(reg_money));
			lender.setComp_address(comp_address);
		}
		
		//String status = request.getParameter("status");
		//String create_time = request.getParameter("create_time");
		String remark = request.getParameter("remark");
		//String user_type = request.getParameter("user_type");
		String reg_ip = request.getParameter("ip");
		
		
		
		
		User user = new User();
		
		user.setUser_name(phone);
		user.setReal_name(name);
		user.setUser_type(2);
		user.setIdcard_no(idcard);
		user.setReg_ip(reg_ip);
		user.setReal_name_auth(1);
		user.setIdcard_auth_time(new Date());
		
		
		userService.save(user);
		
		String result = SinaUtil.createActiveMember(user.getId(), user.getReg_ip());
		
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			Object strMap =  resultMap.get("response_msg");
			
			String remarkO =  (String)resultMap.get("remark");
			remarkO.equalsIgnoreCase("1");
			return new Result(Result.FAIL,strMap.toString());
		}
		
		
		strCode = null;
		result = SinaUtil.setRealName(user.getId(),user.getReal_name(),idcard,user.getReg_ip());
		
		resultMap = JsonUtil.jsonToMap(result);
		
		strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			Object strMap =  resultMap.get("response_msg");
			String remarkO =  (String)resultMap.get("remark");
			remarkO.equalsIgnoreCase("1");
			return new Result(Result.FAIL,strMap.toString());
		}
		
		
		
			
			 
		lender.setAmount(RoofNumberUtils.StringtoDouble(amount));
		lender.setLender_type(Integer.parseInt(lender_type));
		lender.setName(name);
		lender.setPhone(phone);
		lender.setIdcard(idcard);
		lender.setMarital(Integer.parseInt(marital));
		lender.setAddress(address);
		
		
		lender.setStatus(1);
		lender.setCreate_time(new Date());
		lender.setRemark(remark);
		lender.setUser_type(2);
		lender.setUser_id(user.getId());
		
		lenderService.save(lender);
		
		return new Result(Result.SUCCESS,"保存成功!");
		
	}
	
	
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request) throws IOException {
		
		
		return "sysNew/admin/login";
	}
	
	@RequestMapping("/toHome")
	public String toHome(HttpServletRequest request) throws IOException {
		
		
//		return "sys/home/index";
		return "sysNew/home/index";
	}
	
	 @RequestMapping("/logout")
	    @ResponseBody
	    public Map<String, Object> logout(HttpServletRequest request,
	                                      HttpSession session) {
		 // 构造返回JsonMap
	        Map<String, Object> resMap = new HashMap<String, Object>(
	            Constants.NUM_TEN);
	        // 处理状态
	        resMap.put(Constants.JK_STATUS, Constants.STR_ONE);
	        // 错误信息
	        resMap.put(Constants.JK_ERROR_MSG, Constants.NOTHING);
	        // 详细信息
	        resMap.put(Constants.JK_DETAIL, null);
	        // 如果未发生错误则写入Session
	        if (!resMap.get(Constants.JK_STATUS).equals(Constants.STR_NEG_ONE)) {
	            // 用户名Session删除
	            session.removeAttribute("sysuser");
	            // 用户信息删除
	        }
	        // 返回结果
	        return resMap;
	    }
	@RequestMapping("/toModify")
	public String toModify(HttpServletRequest request) throws IOException {
		
		
		return "admin/modify";
	}
	
	class VerifyCodeJ{
		String verify;
		String path;
		public String getVerify() {
			return verify;
		}
		public void setVerify(String verify) {
			this.verify = verify;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		
		
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
        
        //存入会话session 
        HttpSession session = request.getSession(true); 
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        
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
	
	//验证码验证
	 @RequestMapping(value = "/checkimagecode")
	 public  @ResponseBody String checkTcode(HttpServletRequest request,HttpServletResponse response) {
	  String validateCode = request.getParameter("validateCode");
	  validateCode=validateCode.toUpperCase();
	  //1:获取cookie里面的验证码信息
//	  Cookie[] cookies = request.getCookies();
//	  for (Cookie cookie : cookies) {
//	   if ("imagecode".equals(cookie.getName())) {
//	    code = cookie.getValue();
//	    break;
//	   }
//	  }
	  
//	  1:获取session验证码的信息
	  String code1 = (String) request.getSession().getAttribute("verCode");
	  code1=code1.toUpperCase();
//	  2:判断验证码是否正确
	  if(!StringUtils.isEmpty(validateCode) && validateCode.equals(code1)){
	   return "ok"; 
	  }
	  return "error";
	 }
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
		String user_name = request.getParameter("user_name");
		String passwd = request.getParameter("passwd");
		Map<String, Object> map=new HashMap<String, Object>();
	        //获取验证码
		
		
		String code=request.getParameter("verify");
		if(null==user_name || "".equals(user_name)||null==passwd||"".endsWith(passwd)){
			request.setAttribute("errorMsg", "账户密码不能为空");
			return "sysNew/admin/login";
		}
		if(null==code||"".equals(code)){
			request.setAttribute("errorMsg", "验证码不能为空");
			return "sysNew/admin/login";
		}
		HttpSession session = request.getSession(true); 
		String verify= (String) session.getAttribute("verCode");
		if(!code.equalsIgnoreCase(verify)){
			request.setAttribute("errorMsg", "验证码错误");
			return "sysNew/admin/login";
		}
			SysUser sysUser = new SysUser();
			sysUser.setLogin_name(user_name);
			passwd = Md5Util.md5to32(passwd);
			try {
				SysUser user = loginService.getSysUser(sysUser);
				if(null!=user){
					if(user.getPassword().equalsIgnoreCase(passwd)){
						session.removeAttribute(Constants.USER_SESSION);
				        session.setAttribute(Constants.USER_SESSION, user_name);
				        user.setError_count(0);
				        user.setLast_login_time(new Date());
				        user.setLast_login_ip(request.getRemoteAddr());
				        sysUserService.update(user);
				        BusinessDayStat bds = new BusinessDayStat();
				        bds.setStat_date(DateUtil.formatDate(new Date()));
				        BusinessDayStat businessDayStat = sysUserService.getBusinessDayStat(bds);
				        request.setAttribute("businessDayStat", businessDayStat);
				        String path = getClass().getClassLoader().getResource("sysuser.xml").getPath();
				        List list = XmlToJurisdiction.getExtName(path);
				        List paretntList = new ArrayList();
				        List sunList = new ArrayList();
				        for(int i=0;i<list.size();i++){
				        	UserXmlNode xmlNode  = (UserXmlNode)list.get(i);
				        	if(xmlNode.getGrade()==1){
				        		paretntList.add(xmlNode);
				        	}else{
				        		sunList.add(xmlNode);
				        	}
				        }
				        Map myJurMap = new HashMap();
				        if(user.getRose()==-1){
				        	
				        }else{
				        	String jurisdiction = user.getJurisdiction();
					        if(jurisdiction!=null && !"".equals(jurisdiction)){
					        	String myJurisdictions[] = jurisdiction.split(",");
					        	for(int i=0;i<myJurisdictions.length;i++){
					        		myJurMap.put(Integer.valueOf(myJurisdictions[i]), true);
					        	}
					        }
				        }
				        
				        StringBuffer sb = new StringBuffer();
				        
				        for(int i=0;i<paretntList.size();i++){
				        	UserXmlNode xmlNode  = (UserXmlNode)paretntList.get(i);
				        	if(user.getRose()==-1 || myJurMap.get(xmlNode.getId())!=null){				        		
				        		sb.append("<dl id=\"menu-picture\"><dt><i class=\"Hui-iconfont\">&#xe616;</i>");
				        		sb.append(xmlNode.getName());
				        		sb.append("<i class=\"Hui-iconfont menu_dropdown-arrow\">&#xe6d5;</i></dt><dd><ul>");
				        		for(int j=0;j<sunList.size();j++){
				        			UserXmlNode x  = (UserXmlNode)sunList.get(j);
				        			if(x.getParent_id()==xmlNode.getId()){
				        				if(user.getRose()==-1 || myJurMap.get(x.getId())!=null){
				        					sb.append("<li><a data-href=\"");
				        					sb.append(request.getContextPath());
				        					sb.append(x.getUrl());
				        					sb.append("\" data-title=\""+x.getName()+"\" href=\"javascript:void(0)\">"+x.getName()+"</a></li>");
				        				}
				        			}
				        		}
				        		sb.append("</ul>			</dd>		</dl>");
				        	}
				        }
				        request.setAttribute("menu", sb.toString());
				        return "sysNew/home/index";
					}else{
						int count  = user.getError_count();
						user.setError_count(++count);
						sysUserService.update(user);
						request.setAttribute("errorMsg", "账号或密码错误");
						return "sysNew/admin/login";
					}
				}else{
					request.setAttribute("errorMsg", "账号或密码错误");
					return "sysNew/admin/login";
				}
			} catch (DaoException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", "请求异常");
				return "sysNew/admin/login";
			}
	}
	
	
	
	@Autowired(required = true)
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
	@Autowired(required = true)
	public void setSysUserService(ISysUserService userService) {
		this.sysUserService = userService;
	}
	
	@Autowired(required = true)
	public void setLoanerService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
}
