package com.zdjf.web.admin.config;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.domain.fund.Bank;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.fund.IBankService;


@Controller
@RequestMapping("config/bank")
public class BankController {
	private IBankService bankService;
	@RequestMapping("/toList")
	public	String toBankList(HttpServletRequest req, Model model){
		Bank bank=new Bank();
		Page page=PageUtils.createPage(req);
		page=bankService.page(page, bank);
		model.addAttribute("detail",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sysNew/config/bank/list";
	}
	@Autowired(required = true)
	public void setBankService(IBankService bankService) {
		this.bankService = bankService;
	}
}
