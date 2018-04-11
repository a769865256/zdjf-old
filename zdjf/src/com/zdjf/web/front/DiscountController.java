package com.zdjf.web.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.dao.user.IUserCouponDao;
import com.zdjf.dao.user.IUserGiftDao;
import com.zdjf.dao.user.IWithdrawCouponsDao;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.fund.ICoinGoodsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CommonUtils;
import com.zdjf.util.RequestUtils;

@Controller
@RequestMapping("/")
public class DiscountController {
	private IUserService userService;
	private IUserGiftDao userGiftDao;
	private IUserCouponDao userCouponDao;
	private IWithdrawCouponsDao withdrawCouponsDao;
	private IUserFundStatService userFundStatService;
	private ICoinGoodsService coinGoodsService;
	@RequestMapping("discount")
	public String discountList(HttpServletRequest request,
			HttpServletResponse response,Model model){
		String user_name=BrowseUtil.getCookie(request, response);
		User user=userService.selectForObjectByPhone(user_name);
		String tab_num=RequestUtils.getReqString(request, "tab_num");
		String discount_type=RequestUtils.getReqString(request, "discount_type");
		String statusStr=RequestUtils.getReqString(request, "status");
		if(statusStr.equals("")){
			statusStr="0";
		}
		if(tab_num.equals("")){
			tab_num="0";
		}
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("user_id", user.getId());
		int status=Integer.parseInt(statusStr);
		status+=1;
		request.setAttribute("limit", 6);
		Page page=PageUtils.createPage(request);
		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user.getId());
		userFundStat=userFundStatService.selectForObjectById(userFundStat);
		if(tab_num.equals("0")){
			map.put("giftCount", userGiftDao.selectForObject("selectGiftCountByUid", map));
			map.put("couponCount", userCouponDao.selectForObject("selectCouponCountByUid",map));
			map.put("withdrawCount", withdrawCouponsDao.selectForObject("selectWithdrawCouponCountByUid", map));
			map.put("coinBalance", userFundStat.getCoin_balance());
			if(discount_type.equals("")){
				discount_type="0";
			}
			if(discount_type.equals("0")){
				map.put("pagename", "selectGiftPageByMap");
				map.put("total", "selectGiftPageCnByMap");
				map.put("status", status);
				page=userGiftDao.page(page, map);
			}else if(discount_type.equals("1")){
				map.put("pagename", "selectCouponPageByMap");
				map.put("total", "selectCouponPageCnByMap");
				map.put("status", status);
				page=userCouponDao.page(page, map);
			}else if(discount_type.equals("2")){
				map.put("pagename", "selectWithdrawCouponPageByMap");
				map.put("total", "selectWithdrawCouponPageCnByMap");
				if(status==1){
				}else if(status==2){
					status=-1;
				}
				map.put("status", status);
				page=withdrawCouponsDao.page(page, map);
			}
		}else if(tab_num.equals("1")){
			map.put("pagename", "selectCoinGoodsPage");
			map.put("total", "selectCoinGoodsCount");
			map.put("goods_type", status);
			page=coinGoodsService.page(page, map);
		}
		map.put("page", page);
		model.addAllAttributes(CommonUtils.packageResult(true, map));
		return "front/mywealth/discount";
	}
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Autowired(required = true)
	public void setUserGiftDao(
			@Qualifier("userGiftDao")IUserGiftDao userGiftDao) {
		this.userGiftDao = userGiftDao;
	}
	@Autowired(required = true)
	public void setUserCouponDao(
			@Qualifier("userCouponDao")IUserCouponDao userCouponDao) {
		this.userCouponDao = userCouponDao;
	}
	@Autowired(required = true)
	public void setWithdrawCouponsDao(
			@Qualifier("withdrawCouponsDao")IWithdrawCouponsDao withdrawCouponsDao) {
		this.withdrawCouponsDao = withdrawCouponsDao;
	}
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	@Autowired(required = true)
	public void setCoinGoodsService(ICoinGoodsService coinGoodsService) {
		this.coinGoodsService = coinGoodsService;
	}
	
}
