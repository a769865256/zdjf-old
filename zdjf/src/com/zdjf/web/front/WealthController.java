package com.zdjf.web.front;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.dao.product.IProductBuyDao;
import com.zdjf.dao.product.IProductBuyIncomeDao;
import com.zdjf.dao.user.IUserBankDao;
import com.zdjf.dao.user.IUserCouponDao;
import com.zdjf.dao.user.IUserGiftDao;
import com.zdjf.domain.fund.Bank;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.service.fund.FundsService;
import com.zdjf.service.fund.IBankService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CommonUtils;
import com.zdjf.util.Constants;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.webservice.express.SmsReturn;

@Controller
@RequestMapping("/")
public class WealthController {

	private static IUserFundStatService userFundStatService;
	private static IUserService userService;
	private IUserGiftDao userGiftDao;
	private IUserCouponDao userCouponDao;
	private IUserBankDao userBankDao;
	private IUserBankService userBankService;
	private FundsService fundsService;
	private IBankService bankService;
	private IProductBuyDao productBuyDao;
	private IProductBuyIncomeDao productBuyIncomeDao;

	@RequestMapping("wealth")
	public String toMyWealth(HttpServletRequest request,
			HttpServletResponse response,Model model){
		String user_id=BrowseUtil.getCookie(request, response);
		String returnDate=RequestUtils.getReqString(request, "returnDate");
		DateFormat df=new SimpleDateFormat("yyyy-MM");
		if(""==returnDate){
			returnDate=df.format(new Date());
		}
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("returnDate", returnDate);
		if(""==user_id){
			model.addAttribute(CommonUtils.packageResult(false, ""));
		}else{
			
			User user=new User();
			user=userService.selectForObjectByPhone(user_id);
			UserFundStat userFundStat=new UserFundStat();
			userFundStat.setUser_id(user.getId());
			userFundStat=userFundStatService.selectForObjectById(userFundStat);
			if(null==userFundStat){
				userFundStat=new UserFundStat();
				userFundStat.setUser_id(user.getId());
				userFundStat.setBalance(0.00);
				userFundStat.setCoin_balance(0.00);
				userFundStat.setCoin_cost(0.00);
				userFundStat.setCoin_invest(0.00);
				userFundStat.setCoin_total(0.00);
				userFundStat.setIncomed(0.00);
				userFundStat.setInvest_frequency(0);
				userFundStat.setInvest_sum(0.00);
				userFundStat.setLocked_money(0.00);
				userFundStat.setPend_income(0.00);
				userFundStat.setPend_return(0.00);
				userFundStat.setPend_withdraw(0.00);
				userFundStat.setRecharged(0.00);
				userFundStat.setReturned(0.00);
				userFundStat.setWithdraw_coupons(0);
				userFundStat.setWithdrawed(0.00);
				userFundStatService.save(userFundStat);
			}
			int giftCount= (int) userGiftDao.selectForObject("selectGiftCountByUid",user.getId());
			int couponCount= (int) userCouponDao.selectForObject("selectCouponCountByUid",user.getId());
			int bankCount= (int) userBankDao.selectForObject("selectBankCountByUid",user.getId());
			double couponForPay=0.00;
			couponForPay= productBuyDao.selectForObject("selectCouponForPayByUid",user.getId())==null?0.00:(double)productBuyDao.selectForObject("selectCouponForPayByUid",user.getId());
			map.put("user_id", user_id);
			List<Map<String,Object>> payDateList=(List<Map<String, Object>>) productBuyIncomeDao.selectForList("selectPayDate", map);
			Map<String,Object> payDateMap=new HashMap<String, Object>();
			if(null!=payDateList && payDateList.size()>0){
				for(int i=0;i<payDateList.size();i++){
					DateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");
					String date=sdf.format(payDateList.get(i).get("pay_date").toString());
					String status=payDateList.get(i).get("status").toString();
					payDateMap.put(date, status.equals("1")?"已回款":"待回款");
				}
			}
			Map<String, Object> payReturnTotal=(Map<String, Object>) productBuyIncomeDao.selectForObject("selectPayReturnTotal",map);
			map.put("stat", userFundStat);
			map.put("user", user);
			map.put("giftCount", giftCount);
			map.put("couponCount", couponCount);
			map.put("bankCount", bankCount);
			map.put("payReturn", payReturnTotal);
			map.put("payDateMap", JsonUtil.objectTojson(payDateMap));
			map.put("couponForPay", couponForPay);
			map.put("allIncome", couponForPay + userFundStat.getIncomed()
			+ userFundStat.getPend_income());
			map.remove("user_id");
			map.remove("returnDate");
			model.addAllAttributes(CommonUtils.packageResult(true, map));
			Cookie cookie=new Cookie("user_name",user_id);
			response.addCookie(cookie);
		}
		return "front/mywealth/wealth";
	}

	public static double getAllIncome(String user_id){
		User user=new User();
		user=userService.selectForObjectByPhone(user_id);
		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user.getId());
		userFundStat=userFundStatService.selectForObjectById(userFundStat);
		if(null==userFundStat){
			userFundStat=new UserFundStat();
			userFundStat.setUser_id(user.getId());
			userFundStat.setBalance(0.00);
			userFundStat.setCoin_balance(0.00);
			userFundStat.setCoin_cost(0.00);
			userFundStat.setCoin_invest(0.00);
			userFundStat.setCoin_total(0.00);
			userFundStat.setIncomed(0.00);
			userFundStat.setInvest_frequency(0);
			userFundStat.setInvest_sum(0.00);
			userFundStat.setLocked_money(0.00);
			userFundStat.setPend_income(0.00);
			userFundStat.setPend_return(0.00);
			userFundStat.setPend_withdraw(0.00);
			userFundStat.setRecharged(0.00);
			userFundStat.setReturned(0.00);
			userFundStat.setWithdraw_coupons(0);
			userFundStat.setWithdrawed(0.00);
			userFundStatService.save(userFundStat);
		}
		double all=userFundStat.getBalance()+ userFundStat.getPend_income()+userFundStat.getPend_return();
		return all;
	}

	@RequestMapping("list")
	public String toList(HttpServletRequest request,
			HttpServletResponse response){
		String user_name=BrowseUtil.getCookie(request, response);
		if("".equals(user_name)||null==user_name){
			return "front/login";
		}
		return "front/list";
	}
	
	@RequestMapping("toBind")
	public String toBind(HttpServletRequest request,
			HttpServletResponse response){
		return "front/mywealth/gounbind";
	}
	
	@RequestMapping("toUnbind")
	public String toUnbind(HttpServletRequest request,
			HttpServletResponse response){
		return "front/mywealth/unbind";
	}

	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	@Autowired(required = true)
	public void setUserCouponDao(
			@Qualifier("userCouponDao")IUserCouponDao userCouponDao) {
		this.userCouponDao = userCouponDao;
	}
	@Autowired(required = true)
	public void setUserGiftDao(
			@Qualifier("userGiftDao")IUserGiftDao userGiftDao) {
		this.userGiftDao = userGiftDao;
	}
	@Autowired(required = true)
	public void setUserBankDao(
			@Qualifier("userBankDao")IUserBankDao userBankDao) {
		this.userBankDao = userBankDao;
	}
	@Autowired(required = true)
	public void setproductBuyDao(
			@Qualifier("productBuyDao")IProductBuyDao productBuyDao) {
		this.productBuyDao = productBuyDao;
	}

	@Autowired(required = true)
	public void setBankService(IBankService bankService) {
		this.bankService = bankService;
	}

	@Autowired(required = true)
	public void setUserBankService(IUserBankService userBankService) {
		this.userBankService = userBankService;
	}

	@Autowired(required = true)
	public void setFundsService(FundsService fundsService) {
		this.fundsService = fundsService;
	}

	@Autowired(required = true)
	public  void setProductBuyDao(
			@Qualifier("productBuyDao")IProductBuyDao productBuyDao) {
		this.productBuyDao = productBuyDao;
	}
	
	@Autowired(required = true)
	public  void setProductBuyIncomeDao(
			@Qualifier("productBuyIncomeDao")IProductBuyIncomeDao productBuyIncomeDao) {
		this.productBuyIncomeDao = productBuyIncomeDao;
	}
}
