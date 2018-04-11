package com.zdjf.web.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.VerifyCodeUtils;

@Controller
@RequestMapping("/ybf/userAction")
public class UserAction {
	
	private IUserService userService;

	// 加载页面的通用数据  SysUser_exp_mapper
	private void loadCommon(Model model){
		
	}

	@RequestMapping("/index")
	public String index() {
		return "/jiuzhou/manage/bloodFat/bloodFat_index.jsp";
	}

	@RequestMapping("/list")
	public String list(User user, HttpServletRequest request, Model model) {
		Page page = PageUtils.createPage(request);
		page = userService.page(page, user);
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		this.loadCommon(model);
		return "/jiuzhou/manage/bloodFat/bloodFat_list.jsp";
	}
	
	@RequestMapping("/create_page")
	public String create_page(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		this.loadCommon(model);
		return "/jiuzhou/manage/bloodFat/bloodFat_create.jsp";
	}
	
	@RequestMapping("/update_page")
	public String update_page(User user, Model model) {
		user = userService.load(user);
		model.addAttribute("user", user);
		this.loadCommon(model);
		return "/jiuzhou/manage/bloodFat/bloodFat_update.jsp";
	}

	@RequestMapping("/detail_page")
	public String detail_page(User user, Model model) {
		user = userService.load(user);
		model.addAttribute("user", user);
		this.loadCommon(model);
		return "/jiuzhou/manage/bloodFat/bloodFat_detail.jsp";
	}
	
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request, Model model) {
		String user_name = request.getParameter("user_login");
		user.setUser_name(user_name);
		user = userService.login(user);
		model.addAttribute("user", user);
		this.loadCommon(model);
		return "/jiuzhou/manage/bloodFat/bloodFat_detail.jsp";
	}
	
	
	
	@RequestMapping("/verify")
	public String verifyCode(Model model) {
		
		String code = VerifyCodeUtils.generateVerifyCode(4);
		
		model.addAttribute("code", code);
		this.loadCommon(model);
		
		return "";
	}

	@RequestMapping("/create")
	public @ResponseBody Result create(User user) {
		if (user != null) {
			userService.save(user);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/update")
	public @ResponseBody Result update(User user) {
		if (user != null) {
			userService.updateIgnoreNull(user);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Result delete(User user) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		userService.delete(user);
		return new Result("删除成功!");
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
