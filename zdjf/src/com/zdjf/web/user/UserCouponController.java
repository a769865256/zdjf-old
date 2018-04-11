package com.zdjf.web.user;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserService;

@Controller
@RequestMapping("/userCoupon")
public class UserCouponController {
	
	/*用户加息券详细明细*/
	
	private IUserCouponService userCouponService;
	
	private IUserService userService;
	
	@RequestMapping(value="/page",method=RequestMethod.GET)//添加管理用户
	public String getGiftPage(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		UserCoupon userGift = new UserCoupon();
		
//		Cookie[] cookies = request.getCookies();
//        String user_name = request.getParameter("user_name");
//        String code1 = (String) request.getSession().getAttribute("user_name");
		 String user_name = "13311530272";
//        if(null!=code1&&""!=code1){
//        	user_name=code1;
//        }
        //如果用户是第一次访问，那么得到的cookies将是null
//        if (cookies!=null) {
//            //out.write("您上次访问的时间是：");
//            for (int i = 0; i < cookies.length; i++) {
//                Cookie cookie = cookies[i];
//                if (cookie.getName().equals("user_name")) {
//                	user_name = cookie.getValue();
//                }
//            }
//        }
		
		User old = userService.selectForObjectByPhone(user_name);
		
		userGift.setUser_id(old.getId());
		
		Page page = PageUtils.createPage(request);
		page = userCouponService.page(page, userGift);
		
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		return "sys/user/userCouponPage";
	}
	
	@Autowired(required = true)
	public void setUserCouponService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
