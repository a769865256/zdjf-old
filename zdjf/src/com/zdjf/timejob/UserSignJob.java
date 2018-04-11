package com.zdjf.timejob;

import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class UserSignJob {

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserGiftService userGiftService;

	@Autowired
	private IUserCouponService userCouponService;
	
	Log log = LogFactory.getLog(this.getClass());
	
	/*@Scheduled(cron = "0 1 0 * * ?")
	public void allUserSign() throws InterruptedException{

		//邀请签到当月有效 2018-1-11 add
		if (Integer.parseInt(DateUtil.formatDate(new Date(),"dd"))==1) {//判断是否为每月第一天，是签到次数清0，否只清除分享获得的签到机会
			userService.cleanSignTimes();
		} else {
			userService.cleanSignTimesByShare();
		}
		//User user = new User();
		//List<UserVo> list = userService.selectForList(user);
		
		List<Long> list = productBuyService.selectInvestor();
		Date date = new Date();
		for(int i = 0;i<list.size();i++){
			Long uid = list.get(i);
			User us = new User();
			us.setId(uid);
			
			User user = userService.load(us);
			
			*//*user.setSign_num(0);//签到次数
			userService.updateForObjectByPhone(user);*//*
			
			//红包过期实现
			
			//可用红包  
			UserGift gift = new UserGift();
			gift.setUser_id(uid);
			gift.setStatus(1);
			List<UserGiftVo> listGift = userGiftService.selectForList(gift);
			
			for(int j = 0;j<listGift.size();j++){
				UserGiftVo userVo = listGift.get(j);
			
				Date end = DateUtil.dateEnd(userVo.getEnd_date());
				
				
				
				if(end.getTime() <date.getTime()){
					userVo.setStatus(3);
					userGiftService.updateUserGiftByGiftId(userVo);
					
					log.info("红包过期  ID" + userVo.getId() + "*****user_id" +userVo.getUser_id()+ String.format("过期time-%s",userVo.getEnd_date()));
					Thread.sleep(100);
				}
				
			}
			
			UserCoupon coupon = new UserCoupon();
			coupon.setUser_id(user.getId());
			coupon.setStatus(1);
			List<UserCouponVo> listCoupon = userCouponService.selectForList(coupon);
			
			for(int j = 0;j<listCoupon.size();j++){
				UserCouponVo userVo = listCoupon.get(j);
				Date end = DateUtil.dateEnd(userVo.getEnd_date());
				
				if(end.getTime() <date.getTime()){
					userVo.setStatus(3);
					userCouponService.updateUserCouponByCouponId(userVo);
					log.info("加息券过期  ID" + userVo.getId() + "####user_id" +userVo.getUser_id()+String.format("过期time-%s",userVo.getEnd_date()));
					//log.info(String.format("加息券过期  ID%ld####结束时间%s",userVo.getId(),userVo.getEnd_date()));
					Thread.sleep(100);
				}
			}
			
		}
		
	}*/

	/**
	 * 2018-02-28 优化
	 * 签到机会失效、红包过期、加息券过期定时任务 每天（0 1 0 * * ?）执行
	 * @throws InterruptedException
	 */
//	@Scheduled(cron = "0 1 0 * * ?")
	public void allUserSign() throws InterruptedException{

		//当前系统时间
		Date curDate = new Date();
		Long time1 = curDate.getTime();

		//邀请签到当月有效 2018-1-11 add
		if (Integer.parseInt(DateUtil.formatDate(new Date(),"dd"))==1) {//判断是否为每月第一天，是签到次数清0，否只清除分享获得的签到机会
			userService.cleanSignTimes();
		} else {
			userService.cleanSignTimesByShare();
		}

		//红包过期
		userGiftService.updateOutOfDateUserGift();

		//加息券过期
		userCouponService.updateOutOfDateUserCoupon();

		Long time2 = new Date().getTime();
		log.info("签到机会失效、红包过期、加息券过期定时任务耗时:" + (time2-time1)/1000 + "秒");

	}

}
