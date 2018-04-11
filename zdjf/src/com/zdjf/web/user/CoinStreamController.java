package com.zdjf.web.user;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.fund.CoinGoods;
import com.zdjf.domain.fund.CoinGoodsVo;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.WithdrawCoupons;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.fund.ICoinGoodsService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.service.user.IWithdrawCouponsService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.webservice.express.SmsReturn;

@Controller
@RequestMapping("/coinStream")
public class CoinStreamController {
	
	/*用户正经值流详细明细*/
	private ICoinStreamService coinStreamService;
	private ICoinGoodsService coinGoodsService;
	private IUserService userService;
	private IUserFundStatService userFundStatService;
	private IUserGiftService userGiftService;
	private IUserCouponService userCouponService;
	private IWithdrawCouponsService withdrawCouponsService;
	
	@RequestMapping(value="/page",method=RequestMethod.GET)//添加管理用户
	public String getGiftPage(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		CoinStream userGift = new CoinStream();
		
		Cookie[] cookies = request.getCookies();
//        String user_name = request.getParameter("user_name");
		 String user_name = "13311530272";
        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies!=null) {
            //out.write("您上次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("user_name")) {
                	user_name = cookie.getValue();
                }
            }
        }
        String code1 = (String) request.getSession().getAttribute("user_name");
        if(null!=code1&&""!=code1){
        	user_name=code1;
        }
		User old = userService.selectForObjectByPhone(user_name);
		
		userGift.setUser_id(old.getId());
		
		Page page = PageUtils.createPage(request);
		page = coinStreamService.page(page, userGift);
		
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		return "sys/user/coinStreamPage";
	}
	
	//获取正经值兑换
		@Transactional(rollbackFor = { Exception.class })  
		@RequestMapping(value="/exchange",method=RequestMethod.POST)
		public @ResponseBody SmsReturn setExchange(HttpServletRequest request,
	            HttpServletResponse response) throws ParseException {
			
			
			//获取用户信息
			String user_name = BrowseUtil.getCookie(request, response);
			User old = userService.selectForObjectByPhone(user_name);
			
			String coin_good_id = request.getParameter("coin_good_id");
			
			
			SmsReturn sr = new SmsReturn();
			if(old == null){
				sr.setContent("用户不存在");
				sr.setReturnCase(false);
				sr.setStatus(101);
				//model.addAttribute("sr", sr);
				
			}else{
				
					CoinStream userGift = new CoinStream();
					userGift.setUser_id(old.getId());
					
					
					CoinGoods coinGoods = new CoinGoods();
					coinGoods.setId(Long.parseLong(coin_good_id));
					
					
					List<CoinGoodsVo> listGoods = coinGoodsService.selectForList(coinGoods);
					CoinGoods goods = listGoods.get(0);
					
					UserFundStat ufs=new UserFundStat();
					ufs.setUser_id(old.getId());
					ufs=userFundStatService.selectForObjectById(ufs);
					if(ufs.getCoin_balance().compareTo(goods.getCoin_cost()*1.0)<0){
						sr.setContent("正经值余额不足，无法兑换");
						sr.setReturnCase(false);
						sr.setStatus(101);
						return sr;
					}
					
					userGift.setAction(2);
					userGift.setType(44);
					userGift.setAmount(goods.getCoin_cost()*1.0);
					userGift.setCreate_time(new Date());
					userGift.setNum(goods.getCoin_cost());
					//正经值保存
//					coinStreamService.save(userGift);//2018-1-17 edit 移至下面便于balance更新
					UserFundStat  stat = new UserFundStat();
					stat.setUser_id(old.getId());
					UserFundStat  statFund = userFundStatService.selectForObjectById(stat);
					Double coin_balance = statFund.getCoin_balance() - goods.getCoin_cost();
					statFund.setCoin_balance(coin_balance);
					Double coin_cost = statFund.getCoin_cost() + goods.getCoin_cost();
					statFund.setCoin_cost(coin_cost);
					//更新正经值
					userGift.setStatus(1);//2018-1-17 add
					userGift.setBalance(coin_balance);//2018-1-17 add
					userGift.setRemark("正经值兑换");
					coinStreamService.save(userGift);
					userFundStatService.updateUserFundStatById(statFund);
					Date date = new Date();
					if(goods.getGoods_type() == 1){//兑换红包
						
						UserGift gift = new UserGift();
						gift.setUser_id(old.getId());
						gift.setAmount(goods.getAmount());
						gift.setCreate_time(new Date());
						gift.setStatus(1);
						gift.setRemark("正经值兑换" + goods.getCoin_cost());
						gift.setGift_name(goods.getGoods_name());
						gift.setMax_amount(goods.getInvestment_quota());
						gift.setUse_type(1);
						gift.setStart_date(DateUtil.addDays(date, 0));
						gift.setEnd_date(DateUtil.addDays(date,goods.getEffective_day()));
						gift.setMax_days(goods.getMin_day());
						userGiftService.save(gift);
					}else if(goods.getGoods_type() == 2){
						UserCoupon userCoupon = new UserCoupon();
						
						userCoupon.setUser_id(old.getId());
						userCoupon.setInterest(goods.getAmount());
						userCoupon.setCreate_time(new Date());
						userCoupon.setStatus(1);
						userCoupon.setRemark("正经值兑换" + goods.getCoin_cost());
						userCoupon.setUse_type(1);
						userCoupon.setCoupon_name(goods.getGoods_name());
						userCoupon.setMin_amount(goods.getInvestment_quota()*1.0);
						userCoupon.setStart_date(DateUtil.addDays(date, 0));
						userCoupon.setEnd_date(DateUtil.addDays(date,goods.getEffective_day()));
						userCoupon.setMin_days(goods.getMin_day());
						
						userCouponService.save(userCoupon);
						
					}else if(goods.getGoods_type() == 3){
						
						WithdrawCoupons withdrawCoupon = new WithdrawCoupons();
						
						withdrawCoupon.setUser_id(old.getId());
						withdrawCoupon.setAction(1);
						withdrawCoupon.setType(1);
						withdrawCoupon.setNum(goods.getCoin_cost());
						withdrawCoupon.setCreate_time(new Date());
						
						withdrawCouponsService.save(withdrawCoupon);
					}
					
					
					sr.setContent("兑换成功");
					sr.setReturnCase(true);
					sr.setStatus(100);
				
			}
			
			return sr;
		}
	
	@Autowired(required = true)
	public void setCoinStreamService(ICoinStreamService coinStreamService) {
		this.coinStreamService = coinStreamService;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Autowired(required = true)
	public void setCoinGoodsService(ICoinGoodsService coinGoodsService) {
		this.coinGoodsService = coinGoodsService;
	}
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}
	@Autowired(required = true)
	public void setUserCouponService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}
	@Autowired(required = true)
	public void setWithdrawCouponsService(IWithdrawCouponsService withdrawCouponsService) {
		this.withdrawCouponsService = withdrawCouponsService;
	}

}
