package com.zdjf.web.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.notice.News;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.notice.INewsService;
import com.zdjf.service.user.IUserService;

@Controller
@RequestMapping("/")
public class NewsController {
	private INewsService newsServcie;
	private IUserService userService;
	@RequestMapping("message_center")
	public String toNewsList(HttpServletRequest request,
            HttpServletResponse response,Model model){
		String user_name=BrowseUtil.getCookie(request, response);
		User user=userService.selectForObjectByPhone(user_name);
		News news=new News();
		news.setUser_id(user.getId());
		Page page=PageUtils.createPage(request);
		page=newsServcie.page(page, news);
		return "front/mywealth/message_center";
	}
	@Autowired(required=true)
	public void setNewsServcie(INewsService newsServcie) {
		this.newsServcie = newsServcie;
	}
	@Autowired(required=true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
}
