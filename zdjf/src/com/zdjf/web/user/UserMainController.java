package com.zdjf.web.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/userMain")
public class UserMainController {
	
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request) throws IOException {
		return "user/user/login";
	}
	@RequestMapping("/toForget")
	public String toForget(HttpServletRequest request) throws IOException {
		return "user/forgetpass";
	}
	@RequestMapping("/toRegister")
	public String toRegister(HttpServletRequest request) throws IOException {
		return "user/register";
	}
}
