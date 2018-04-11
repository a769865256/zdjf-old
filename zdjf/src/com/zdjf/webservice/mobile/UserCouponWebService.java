package com.zdjf.webservice.mobile;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zdjf.components.mobile.ValuetoString;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.webservice.express.SmsReturn;

@RestController
@RequestMapping("/m/userCoupon")
public class UserCouponWebService {

	
	private IUserCouponService userCouponService;
	
	private IUserService userService;

	//获取加息券page
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getGiftPage(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User old = userService.selectForObjectByPhone(user_name);
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(old == null){
			
			
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			model.addAttribute("sr", sr);
			
		}else{
			
			if(sign.equalsIgnoreCase(md5Sign)){
				UserCoupon userGift = new UserCoupon();
				userGift.setUser_id(old.getId());
				
				Page page = PageUtils.createPage(request);
				page = userCouponService.page(page, userGift);
				
				int currentPage = page.getCurrentPage().intValue();
				int limit = page.getLimit().intValue();
				
				int total = page.getTotal().intValue();
				int start = limit * (currentPage - 1);
				int end = total >= limit*currentPage?limit*currentPage:total;

				
				List<UserCouponVo> listGift = userCouponService.selectForList(userGift);
				
				
				List<Map<String,String>> listS = new ArrayList<Map<String,String>>();
				Date date = new Date();
				for(int i = start;i<end;i++){
					UserCouponVo vo = listGift.get(i);
					UserCoupon gift = new UserCoupon();
					BeanUtils.copyProperties(vo,gift);
					Map<String,String> map = ValuetoString.eachProperties(gift);
					Date end_date = gift.getEnd_date();
					
					int days = DateUtil.daysBetween(date, end_date);
					
					if(days>=0){
						if(days<4){//小于四天{
							map.put("quick_outtime" , "即将过期");
						}else{
							map.put("quick_outtime" , "");
						}
					}else{
						map.put("quick_outtime" , "过期");
					}
					
					listS.add(map);
				}
				
				page.setDataList(listS);
				
				sr.setContent(page);
				sr.setReturnCase(true);
				sr.setStatus(100);
			}else{
				sr.setContent("无效数据");
				sr.setReturnCase(false);
				sr.setStatus(101);
			}
			
		}
		
		
		
		
		return sr;
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
