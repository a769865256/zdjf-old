package com.zdjf.web.admin.fund;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.ValuetoString;
import com.zdjf.dao.user.IUserCouponDao;
import com.zdjf.dao.user.IUserGiftDao;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.fund.ICoinService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RequestUtils;


@Controller
@RequestMapping("/sys/fund")
public class FundController {
	
	private IDataFieldService dataFieldService;
	
	private IUserService userService;
	
	private IGiftService giftService;
	
	private ICouponService couponService;
	private ICoinService coinService;
	
	private IUserGiftService userGiftService;
	
	private IUserCouponDao userCouponDao;
	private IUserGiftDao userGiftDao;
	private IProductService ip;
	private IFundsService fs;
	
	@RequestMapping("test")
	public String test(HttpServletRequest req){
		Funds f=new Funds();
		f.setTrade_no("123");
		return fs.save(f).toString();
	}
	@RequestMapping("/ticket/get")
	public @ResponseBody String getTicket(HttpServletRequest req, Model model){
		String user_id=RequestUtils.getReqString(req, "user_id");
		String status=RequestUtils.getReqString(req, "status");
		String max_amount=RequestUtils.getReqString(req, "max_amount");
		String max_days=RequestUtils.getReqString(req, "max_days");
		String min_amount=RequestUtils.getReqString(req, "min_amount");
		String min_days=RequestUtils.getReqString(req, "min_days");
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("user_id", user_id);
		paramMap.put("status", status);
		paramMap.put("max_amount", max_amount);
		paramMap.put("max_days", max_days);
		paramMap.put("min_amount", min_amount);
		paramMap.put("min_days", min_days);
		List<UserCouponVo> userCouponVo=(List<UserCouponVo>) userCouponDao.selectForList("selectCouponListByMap",paramMap);
		List<UserGiftVo> userGiftVo=(List<UserGiftVo>) userGiftDao.selectForList("selectGiftListByMap",paramMap);
		model.addAttribute("giftList",userGiftVo);
		model.addAttribute("couponList",userCouponVo);
		paramMap=new HashMap<String, Object>();
		try {
			paramMap.put("giftList",ValuetoString.eachProperties(userGiftVo));
			paramMap.put("couponList",ValuetoString.eachProperties(userCouponVo));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JsonUtil.objectTojson(paramMap);
	}
	
	@Autowired
	public void setUserCouponDao(
			@Qualifier("userCouponDao") IUserCouponDao  userCouponDao) {
		this.userCouponDao = userCouponDao;
	}
	@Autowired
	public void setUserGiftDao(
			@Qualifier("userGiftDao") IUserGiftDao  userGiftDao) {
		this.userGiftDao = userGiftDao;
	}
	@Autowired(required=true)
	public void setIp(IProductService ip) {
		this.ip = ip;
	}
	@Autowired(required=true)
	public void setFs(IFundsService fs) {
		this.fs = fs;
	}
	
	
	
	

}
