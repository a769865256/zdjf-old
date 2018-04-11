package com.zdjf.components.events;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.domain.fund.Coupon;
import com.zdjf.domain.fund.CouponVo;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.GiftVo;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserGift;
import com.zdjf.service.fund.ICoinService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;

public class UserBenefitsEvent extends ApplicationEvent{

	/**
	 * 所有给用户馈赠的事件都在此实现
	 */
	private static final long serialVersionUID = -6482676129853585616L;
	
	
	private User user;

	public UserBenefitsEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public UserBenefitsEvent(Object source,User user) {
		super(source);
		// TODO Auto-generated constructor stub
		this.user = user;
	}
	
	
	public UserBenefitsEvent(Object source,Long id,Coin coin,
			ICoinService coinService,ICoinStreamService coinStreamService,int action) {
		super(source);
		// TODO Auto-generated constructor stub
		
		List<CoinVo> listCo = coinService.selectForList(coin);
		
		for(CoinVo vo : listCo){
			
			CoinStream coinStream = new CoinStream();
			
			coinStream.setUser_id(id);
			coinStream.setAction(action);
			//coinStream.setType(1);
			coinStream.setCreate_time(new Date());
			coinStream.setNum(vo.getCoin_num());
			coinStream.setStatus(1);
			coinStream.setRemark(coin.getCoin_name());
			
			double amount = (double) vo.getCoin_num();
			coinStream.setAmount(amount);
			coinStreamService.save(coinStream);
		}
	
	}
	
	public UserBenefitsEvent(Object source,List<Long> list,Gift gift,
			IGiftService giftService,IUserGiftService userGiftService) {
		super(source);
		
		
		//红包 流程
		for(int i = 0;i<list.size();i++){
			
			Long id = list.get(i);
			List<GiftVo> listG = giftService.selectForList(gift);
			
			for(GiftVo vo : listG){
				UserGift userGift = new UserGift();
				
				Date date = new Date();
				userGift.setGift_id(vo.getId());
				userGift.setGift_name(vo.getGift_name());
				userGift.setUser_id(id);
				userGift.setAmount(vo.getMoney());
				userGift.setCreate_time(date);
				userGift.setRemark(vo.getGift_name());
			    
			    if(vo.getDate_type() == 2){
			    	userGift.setStart_date(date);
			    	Calendar calendar = new GregorianCalendar(); 
				    calendar.setTime(date); 
				    calendar.add(Calendar.DATE, vo.getGift_days()-1);//把日期往后增加一年.整数往后推,负数往前移动
				    userGift.setEnd_date(calendar.getTime());
			    }else if(vo.getDate_type() == 1){
			    	//比较一下时间  那个比较晚  就用那个时间
			    	userGift.setStart_date((date.getTime()>vo.getStart_date().getTime())?date:vo.getStart_date());
			    	userGift.setEnd_date(vo.getEnd_date());
			    }
			    
			    userGift.setStatus(1);
			    userGift.setMax_days(vo.getMax_days());
			    userGift.setMax_amount(vo.getMax_amount());
			    userGift.setUse_type(vo.getUse_type());
			    
				userGiftService.save(userGift);
			}
		}
		
		
		
	}
	
	public UserBenefitsEvent(Object source,Long id,Gift gift,
			IGiftService giftService,IUserGiftService userGiftService) {
		super(source);
		System.out.println("红包名称"+gift.getGift_name());
		//红包 流程
		List<GiftVo> listG = giftService.selectForList(gift);
		
		for(GiftVo vo : listG){
			UserGift userGift = new UserGift();
			
			Date date = new Date();
			userGift.setGift_id(vo.getId());
			userGift.setGift_name(vo.getGift_name());
			userGift.setUser_id(id);
			userGift.setAmount(vo.getMoney());
			userGift.setCreate_time(date);
			userGift.setRemark(vo.getGift_name());
		    
		    if(vo.getDate_type() == 2){
		    	userGift.setStart_date(date);
		    	Calendar calendar = new GregorianCalendar(); 
			    calendar.setTime(date); 
			    calendar.add(Calendar.DATE, vo.getGift_days()-1);//把日期往后增加一年.整数往后推,负数往前移动
			    userGift.setEnd_date(calendar.getTime());
		    }else if(vo.getDate_type() == 1){
		    	//比较一下时间  那个比较晚  就用那个时间
		    	userGift.setStart_date((date.getTime()>vo.getStart_date().getTime())?date:vo.getStart_date());
		    	userGift.setEnd_date(vo.getEnd_date());
		    }
		    
		    userGift.setStatus(1);
		    userGift.setRelation_id((long)vo.getGift_source());
		    userGift.setMax_days(vo.getMax_days());
		    userGift.setMax_amount(vo.getMax_amount());
		    userGift.setUse_type(vo.getUse_type());
		    
			userGiftService.save(userGift);
		}
	}
	
	
	public UserBenefitsEvent(Object source,Long id,Coupon coupon,
			ICouponService couponService,IUserCouponService userCouponService) {
		super(source);
		
		List<CouponVo> listC = couponService.selectForList(coupon);
		
		for(CouponVo vo : listC){
			
			UserCoupon userCoupon = new UserCoupon();
			Date date = new Date();
			userCoupon.setCoupon_name(vo.getCoupon_name());
			userCoupon.setCreate_time(date);
			userCoupon.setInterest(vo.getInterest());
			
			if(vo.getInvalid_type() == 2){
				Calendar calendar = new GregorianCalendar(); 
			    calendar.setTime(date); 
			    calendar.add(Calendar.DATE, vo.getInvalid_days()-1);
			    userCoupon.setStart_date(date);
			    userCoupon.setEnd_date(calendar.getTime());
			}else if(vo.getInvalid_type() == 1){
				//比较一下时间  那个比较晚  就用那个时间
				userCoupon.setStart_date((date.getTime()>vo.getInvalid_start_date().getTime())?date:vo.getInvalid_start_date());
				userCoupon.setEnd_date(vo.getInvalid_end_date());
			}
		    userCoupon.setUser_id(id);
		    userCoupon.setCoupon_id(vo.getId());
		    userCoupon.setStatus(vo.getStatus());
		    userCoupon.setRemark(vo.getRemark());
		    userCoupon.setCoupon_name(vo.getCoupon_name());
		    userCoupon.setUse_type(vo.getUse_type());
		    userCoupon.setMin_amount(vo.getMin_amount());
		    userCoupon.setMin_days(vo.getMin_days());
		    userCoupon.setRemark(vo.getCoupon_name());
		    
		    userCouponService.save(userCoupon);
		    
		}
	}
	
	
	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}

	
	
}
