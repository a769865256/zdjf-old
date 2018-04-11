package com.zdjf.components.events;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.domain.fund.Coupon;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.service.fund.ICoinService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;





@Component
@PropertySource(value= "classpath:project.properties")
public class RegisterEventListener implements ApplicationListener<RegisterEvent>{

	private IUserService userService; 
	
	private ICoinService coinService;
	
	private ICoinStreamService coinStreamService;
	
	private ApplicationContext applicationContext;
	
	private IUserFundStatService userFundStatService;
	
	
	private IGiftService giftService;
	
	private IUserGiftService userGiftService;
	
	private ICouponService couponService;
	
	private IUserCouponService userCouponService;

	@Value("${activity.start.date}")
	private String newYearActivityStartDate;

	@Value("${activity.end.date}")
	private String newYearActivityEndDate;

	@Value("${gift.name}")
	private String newYearActivityGiftName;
	
	@Override
	public void onApplicationEvent(RegisterEvent arg0) {
		// TODO Auto-generated method stub
		User user = arg0.getUser();
		
		
		//审核通过用户资金统计项目
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		userFund.setUpdate_time(new Date());
		userFundStatService.save(userFund);
		
		registerEvent(user);
//		//老接口
//		String result = SinaUtil.createActiveMember(user.getId(), user.getReg_ip());
		//新接口2018-1-17
		String result = SinaUtil.createActiveMember(user.getId(), user.getReg_ip(),Constants.HOST_ROLE_INVESTOR);
		
		User other = new User();
		//好友注册成功给正经值
		//String invite_code = "";邀请的有问题给邀请人  数据问题2017-11-17
		/*if(user.getInvite_code() != null && !user.getInvite_code().isEmpty()){
			String invite_code = user.getInvite_code();
			if(isInteger(invite_code))
				other.setId(Long.parseLong(invite_code));
			else{
				//2017-11-20 之前邀请码
			}
		}*/
			
		
		if(user.getInviter_phone() != null && !user.getInviter_phone().isEmpty()) {
			other.setUser_name(user.getInviter_phone());
			User inv = userService.selectForObjectByPhone(user.getInviter_phone());
			inv.setSign_num(inv.getSign_num() + 1);
			userService.updateForObjectByPhone(inv);
		}
			/*List<UserVo> inviter = userService.selectForList(other);
		if(inviter != null && inviter.size()>0){
			int num = inviter.get(0).getSign_num();
			inviter.get(0).setSign_num(++num);
			userService.updateForObjectByPhone(inviter.get(0));
		}*/
		
		user.setRemark("register:" + result);
		userService.update(user);
		
		System.out.println(result);
	}
	
	private void registerEvent(User user){
		
		//  2017-12-05
		Gift gift = new Gift();
		
		gift.setGift_name("新手注册红包");
		gift.setGift_source(12);
		gift.setStatus(1);
		
		//红包 流程
		applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
				gift,giftService,userGiftService));

		//新春注册送红包活动 2018-2-5 add
		//当前系统时间
		Date curDate = new Date();
		//当前系统时间yyyy-MM-dd 毫秒值
		long currDateLong = DateUtil.convert(DateUtil.formatDate(curDate,"yyyy-MM-dd"),2).getTime();
		//在活动时间范围内，发放红包
		if (currDateLong >= DateUtil.convert(newYearActivityStartDate,0).getTime()
				&& currDateLong <= DateUtil.convert(newYearActivityEndDate,0).getTime()) {
			Gift nygift = new Gift();
			nygift.setGift_name(newYearActivityGiftName);
//			nygift.setGift_source();
			nygift.setStatus(1);

			//红包 流程
			applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
					nygift,giftService,userGiftService));
		}
		
		//加息券
		Coupon coupon = new Coupon();
		coupon.setCoupon_source(11);
		coupon.setStatus(1);
		coupon.setCoupon_name("新手注册加息券");
		applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
				coupon,couponService,userCouponService));
		
		//正经值
		Coin coin = new Coin();
		coin.setCoin_source(1);
		coin.setCoin_name("新手注册");
		
		/*applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
				coin,coinService,coinStreamService,1));*/
		List<CoinVo> listCo = coinService.selectForList(coin);
		
		for(CoinVo vo : listCo){
			//注册
			applicationContext.publishEvent(new FundEvent(this,user.getId(),22,vo.getCoin_num()*1.0,
					userFundStatService,coinStreamService));
		}
		
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
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
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

}
