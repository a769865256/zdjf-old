package com.zdjf.webservice.mobile;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zdjf.components.events.FundEvent;
import com.zdjf.components.mobile.UniqueUtil;
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

@RestController
@RequestMapping("/m/coinStream")
public class CoinStreamWebService {
	
	private ICoinStreamService coinStreamService;
	
	private IUserService userService;
	
	private IUserGiftService userGiftService;
	
	private IUserCouponService userCouponService;
	
	private ICoinGoodsService coinGoodsService;
	
	private IUserFundStatService userFundStatService;
	
	private IWithdrawCouponsService  withdrawCouponsService;
	
	private ApplicationContext applicationContext;

	//获取正经值page
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getGiftPage(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
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
			//model.addAttribute("sr", sr);
			
		}else{
			
			if(sign.equalsIgnoreCase(md5Sign)){
				CoinStream userGift = new CoinStream();
				userGift.setUser_id(old.getId());
				
				Page page = PageUtils.createPage(request);
				page = coinStreamService.page(page, userGift);
				
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
	
	//获取正经值余额
	@RequestMapping(value="/balance",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getCoinBalance(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(user == null){
			
			
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
			
		if(!sign.equalsIgnoreCase(md5Sign)){
			
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		UserFundStat userFund = new UserFundStat();
		
		userFund.setUser_id(user.getId());
		
		UserFundStat stat = userFundStatService.selectForObjectById(userFund);
		
		
		sr.setContent(stat.getCoin_balance() + "");
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
		
	}
	
	
	//获取可兑换的list
	@RequestMapping(value="/goods/list",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getGoodsList(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User old = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		
		String coin_good_id = request.getParameter("coin_good_id");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(old == null){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			//model.addAttribute("sr", sr);
			
		}else{
			
			if(!sign.equalsIgnoreCase(md5Sign)){
				sr.setContent("无效数据");
				sr.setReturnCase(false);
				sr.setStatus(101);
			}
			
			CoinGoods coinGoods = new CoinGoods();
			
			List<CoinGoodsVo>list = coinGoodsService.selectForList(coinGoods);
			
			sr.setMapContent(list);
			sr.setReturnCase(true);
			sr.setStatus(100);
		}
		
		
		
		return sr;
	}
	
	//获取正经值
	@Transactional(rollbackFor = { Exception.class })  
	@RequestMapping(value="/user/add",method=RequestMethod.POST)
	public @ResponseBody SmsReturn setUserAdd(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String amount = request.getParameter("amount");
		
		if(user !=null){
			//金额
			Double cny = Double.parseDouble(amount);
			
			Double value = cny*0.0007;
			String trade_no = UniqueUtil.getTradeNo();
			//正经值
			applicationContext.publishEvent(new FundEvent(this,user.getId(),11,value*1.0,
					userFundStatService,coinStreamService,trade_no));
			
			
		}
		
		return sr;
	}
	
	//获取正经值兑换
	@Transactional(rollbackFor = { Exception.class })  
	@RequestMapping(value="/exchange",method=RequestMethod.POST)
	public @ResponseBody SmsReturn setExchange(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User old = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		
		String coin_good_id = request.getParameter("coin_good_id");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(old == null){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			//model.addAttribute("sr", sr);
			
		}else{
			
			if(sign.equalsIgnoreCase(md5Sign)){
				CoinStream userGift = new CoinStream();
				userGift.setUser_id(old.getId());
				
				
				CoinGoods coinGoods = new CoinGoods();
				coinGoods.setId(Long.parseLong(coin_good_id));
				List<CoinGoodsVo> listGoods = coinGoodsService.selectForList(coinGoods);
				CoinGoods goods = listGoods.get(0);
				
				userGift.setAction(2);
				userGift.setType(44);
				userGift.setAmount(goods.getCoin_cost()*1.0);
				userGift.setCreate_time(new Date());
				userGift.setNum(goods.getCoin_cost());
				userGift.setRemark("兑换");
				//正经值保存
//				coinStreamService.save(userGift);//2018-1-17 edit 移至下面便于balance更新
				UserFundStat  stat = new UserFundStat();
				stat.setUser_id(old.getId());
				UserFundStat  statFund = userFundStatService.selectForObjectById(stat);
				
				if(statFund.getCoin_balance().compareTo( goods.getCoin_cost()*1.0)<0){
					sr.setContent("正经值余额不足");
					sr.setReturnCase(false);
					sr.setStatus(102);
					return sr;
				}
				Double coin_balance = statFund.getCoin_balance() - goods.getCoin_cost();
				statFund.setCoin_balance(coin_balance);
				Double coin_cost = statFund.getCoin_cost() + goods.getCoin_cost();
				statFund.setCoin_cost(coin_cost);
				//更新正经值
				userGift.setStatus(1);//2018-1-17 add
				userGift.setBalance(coin_balance);//2018-1-17 add
				coinStreamService.save(userGift);//2018-1-17 add
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
					gift.setMax_days(goods.getMin_day());
					gift.setStart_date(DateUtil.addDays(date, 0));
					gift.setEnd_date(DateUtil.addDays(date,goods.getEffective_day()));
					
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
					userCoupon.setMin_days(goods.getMin_day());
					userCoupon.setStart_date(DateUtil.addDays(date, 0));
					userCoupon.setEnd_date(DateUtil.addDays(date,goods.getEffective_day()));
					userCouponService.save(userCoupon);
					
				}else if(goods.getGoods_type() == 3){
					
					WithdrawCoupons withdrawCoupon = new WithdrawCoupons();
					
					withdrawCoupon.setUser_id(old.getId());
					withdrawCoupon.setAction(1);
					withdrawCoupon.setType(1);
					withdrawCoupon.setNum(goods.getCoin_cost());
					withdrawCoupon.setCreate_time(new Date());
					//withdrawCoupon.set
					/*withdrawCoupon.setStart_date(DateUtil.addDays(date, 1));
					withdrawCoupon.setEnd_date(DateUtil.addDays(date,goods.getEffective_day() + 1));*/
					withdrawCouponsService.save(withdrawCoupon);
				}
				
				
				sr.setContent("兑换成功");
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
	public void setCoinStreamService(ICoinStreamService coinStreamService) {
		this.coinStreamService = coinStreamService;
	}
	
	@Autowired(required = true)
	public void setCoinGoodsService(ICoinGoodsService coinGoodsService) {
		this.coinGoodsService = coinGoodsService;
	}

	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
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
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	
	@Autowired(required = true)
	public void setWithdrawCouponService(IWithdrawCouponsService withdrawCouponsService) {
		this.withdrawCouponsService = withdrawCouponsService;
	}
	
	
	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
