package com.zdjf.web.sys.login;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.JsonUtil;

import oracle.sql.DATE;

@Controller
@RequestMapping("/sysMain")
public class SysMainController {
	@Autowired
	private IUserService userService;
	@Autowired
	private ILenderService lenderService;
	
	@RequestMapping("/toLoaner")
	public String toLoaner(HttpServletRequest request) throws IOException {
		
		
		return "admin/loaner";
	}
	
	
	@RequestMapping("/test1")
	public void test(){
//		User user=new User();
////		user.setUser_type(1);
//		List<UserVo> userList=userService.selectForList(user);
////		Lender lender=new Lender();
////		List<LenderVo> lenderList=lenderService.selectForList(lender);
//		for(int i=137000;i<userList.size();i++){
//			String result=SinaUtil.setMemberHostRole(userList.get(i).getId(), Constants.HOST_ROLE_INVESTOR);
//			Map restMap = JsonUtil.jsonToMap(result);
//			Object resCode = restMap.get("response_code");
//			System.out.println(i+1);
//		}
		
	}
	
	@RequestMapping("/toLender")
	public String toLender(HttpServletRequest request) throws IOException {
		
		
		return "admin/lender";
	}
	
	@RequestMapping("/toProduct")
	public String toProduct(HttpServletRequest request) throws IOException {
		
		
		return "admin/product";
	}
	
	@RequestMapping("/toRegister")
	public String toRegister(HttpServletRequest request) throws IOException {
		
		
		return "admin/register";
	}
	@RequestMapping("/toModify")
	public String toModify(HttpServletRequest request) throws IOException {
		
		
		return "admin/modify";
	}
}
