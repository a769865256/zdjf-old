package com.zdjf.components.events;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.user.User;
import com.zdjf.service.fund.ICoinService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;


@Component
public class AuditEventListener implements ApplicationListener<AuditEvent>{

	private IUserFundStatService userFundStatService;
	
	private ICoinService coinService;
	
	private ICoinStreamService coinStreamService;
	
	private IGiftService giftService;
	
	private IUserGiftService userGiftService;
	
	private ICouponService couponService;
	
	private IUserCouponService userCouponService;
	
	private ApplicationContext applicationContext;
	
	
	@Override
	public void onApplicationEvent(AuditEvent arg0) {
		// TODO Auto-generated method stub
		User user = arg0.getUser();
		
		
		
		//正经值
		Coin coin = new Coin();
		coin.setCoin_source(2);
		coin.setCoin_name("实名认证");
		
		/*applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
				coin,coinService,coinStreamService,1));*/
		List<CoinVo> listCo = coinService.selectForList(coin);
		
		for(CoinVo vo : listCo){
			//实名认证
			applicationContext.publishEvent(new FundEvent(this,user.getId(),23,vo.getCoin_num()*1.0,
					userFundStatService,coinStreamService));
			/*CoinStream coinStream = new CoinStream();
			
			coinStream.setUser_id(user.getId());
			coinStream.setAction(1);
			//coinStream.setType(1);
			coinStream.setCreate_time(new Date());
			coinStream.setNum(vo.getCoin_num());
			coinStream.setStatus(1);
			
			
			coinStreamService.save(coinStream);*/
		}
		//渠道红包  2017-12-29
		
		if(user.getInvite_source()!=null && !user.getInvite_source().isEmpty()){
			if(user.getInvite_source().equalsIgnoreCase("501") || 
					user.getInvite_source().equalsIgnoreCase("502")||
					user.getInvite_source().equalsIgnoreCase("503")||
					user.getInvite_source().equalsIgnoreCase("504")||
					user.getInvite_source().equalsIgnoreCase("505")||
					user.getInvite_source().equalsIgnoreCase("506")){
				Gift gift = new Gift();
				if(user.getInvite_source().equalsIgnoreCase("504")){
					gift.setGift_name("车主首投红包");
					gift.setGift_source(55);
					gift.setStatus(1);
				}
				
				if(user.getInvite_source().equalsIgnoreCase("501")||
						user.getInvite_source().equalsIgnoreCase("502")||
						user.getInvite_source().equalsIgnoreCase("503")||
						user.getInvite_source().equalsIgnoreCase("505")||
						user.getInvite_source().equalsIgnoreCase("506")){
					gift.setGift_name("首投红包");
					gift.setGift_source(55);
					gift.setStatus(1);
				}
				
				
				//红包 流程
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						gift,giftService,userGiftService));
			}
		}
		
		
		/*List<GiftVo> listG = giftService.selectForList(gift);
		
		for(GiftVo vo : listG){
			UserGift userGift = new UserGift();
			
			Date date = new Date();
			userGift.setGift_id(vo.getId());
			userGift.setGift_name(vo.getGift_name());
			userGift.setUser_id(user.getId());
			userGift.setAmount(vo.getMoney());
			userGift.setCreate_time(date);
			userGift.setStart_date(date);
			
			
			Calendar calendar = new GregorianCalendar(); 
		    calendar.setTime(date); 
		    calendar.add(Calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动
		    userGift.setEnd_date(calendar.getTime());
		    userGift.setStatus(1);
		    userGift.setMax_days(vo.getMax_days());
		    userGift.setMax_amount(vo.getMax_amount());
		    userGift.setUse_type(vo.getUse_type());
		    
			userGiftService.save(userGift);
		}*/
		
		
		
		
		//加息券
		/*Coupon coupon = new Coupon();
		coupon.setCoupon_source(11);
		coupon.setStatus(1);
		coupon.setCoupon_name("新手注册加息券");
		applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
				coupon,couponService,userCouponService));*/
		/*List<CouponVo> listC = couponService.selectForList(coupon);
		
		for(CouponVo vo : listC){
			
			UserCoupon userCoupon = new UserCoupon();
			Date date = new Date();
			userCoupon.setCoupon_name(vo.getCoupon_name());
			userCoupon.setCreate_time(date);
			userCoupon.setInterest(vo.getInterest());
			
			
			Calendar calendar = new GregorianCalendar(); 
		    calendar.setTime(date); 
		    calendar.add(Calendar.YEAR, 1);
		    userCoupon.setStart_date(date);
		    userCoupon.setEnd_date(calendar.getTime());
		    userCoupon.setUser_id(user.getId());
		    userCoupon.setCoupon_id(vo.getId());
		    userCoupon.setStatus(vo.getStatus());
		    userCoupon.setRemark(vo.getRemark());
		    userCoupon.setCoupon_name(vo.getCoupon_name());
		    userCoupon.setUse_type(vo.getUse_type());
		    userCoupon.setMin_amount(vo.getMin_amount());
		    userCoupon.setMin_days(vo.getMin_days());
		    
		    userCouponService.save(userCoupon);
		    
		}*/
		
		
	}
	
	
	
	@Autowired(required = true)
	public void setCoinService(ICoinService coinService) {
		this.coinService = coinService;
	}
	
	@Autowired(required = true)
	public void setCoinStreamService(ICoinStreamService coinStreamService) {
		this.coinStreamService = coinStreamService;
	}
	
	@Autowired(required = true)
	public void setGiftService(IGiftService giftService) {
		this.giftService = giftService;
	}
	
	@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}

	@Autowired(required = true)
	public void setCouponService(ICouponService couponService) {
		this.couponService = couponService;
	}
	
	@Autowired(required = true)
	public void setUserCouponService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}
	
	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}

}
