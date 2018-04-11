package com.zdjf.web.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.user.User;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CommonUtils;

@Controller
@RequestMapping("/")
public class AccountController {
	private IUserService userService;
	@RequestMapping("account")
	public String toAccount(HttpServletRequest request,
			HttpServletResponse response,Model model){
		String user_name=BrowseUtil.getCookie(request, response);
		User user=userService.selectForObjectByPhone(user_name);
		String nphone=user_name.substring(3,8);
		nphone=user_name.replace(nphone, "*****");
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("phone", nphone);
		map.put("user", user);
		model.addAllAttributes(CommonUtils.packageResult(true, map));
		return "front/mywealth/account";
	}
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
