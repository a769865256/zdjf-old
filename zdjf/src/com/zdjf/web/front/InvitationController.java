package com.zdjf.web.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.util.CommonUtils;

@Controller
@RequestMapping("/")
public class InvitationController {
	private IProductBuyService productBuyService;
	@RequestMapping("invitation")
	public String toInvitation(HttpServletRequest request,
            HttpServletResponse response,Model model){
		String user_name=BrowseUtil.getCookie(request, response);
		ProductBuy productBuy=new ProductBuy();
		productBuy.setInviter_phone(user_name);
		request.setAttribute("limit", 6);
		Page page=PageUtils.createPage(request);
		page=productBuyService.page(page, productBuy);
		model.addAllAttributes(CommonUtils.packageResult(true, page));
		return "front/mywealth/invitation";
	}
	@Autowired(required=true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	
}
