package com.zdjf.web.admin.fund;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.domain.user.UserBank;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.user.IUserBankService;

@Controller
@RequestMapping("/sys/bank")
public class SysBankController {
	private IUserBankService userBankService;
	private IDataFieldService dataFieldService;
	@RequestMapping("/toList")
	public String toList(HttpServletRequest req, Model model){
		UserBank userBank=new UserBank();
		Page page=PageUtils.createPage(req);
		page=userBankService.page(page, userBank);
		model.addAttribute("page",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sysNew/user/bank/list";
	}
	@RequestMapping("/toChangeList")
	public String toChangeList(HttpServletRequest req, Model model){
		UserBank userBank=new UserBank();
		Page page=PageUtils.createPage(req);
		page=userBankService.page(page, userBank);
		model.addAttribute("page",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sysNew/user/bank/changelist";
		
	}
	@Autowired(required = true)
	public void setUserBankService(IUserBankService userBankService) {
		this.userBankService = userBankService;
	}
	@Autowired(required = true)
	public void setDataFieldService(IDataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}
}
