package com.zdjf.timejob;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjf.components.events.FundEvent;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.user.IUserService;

@Component
public class UserFundsJob {
	
	private IUserService userService;
	
	
	//private ICoinService coinService;
	
	private ICoinStreamService coinStreamService;
	
	private IUserFundStatService userFundStatService;
	
	private ApplicationContext applicationContext;
	
	//@Scheduled(cron = "0 0 12 * * ?")
	public void allUserFund() throws InterruptedException{
		Date date = new Date();
		System.out.println(String.format("time-%s",date));
		//所有用户的  账户的情况
		User user = new User();
		List<UserVo> list = userService.selectForList(user);
		
		for(int i = 0;i<list.size();i++){
			UserVo vo = list.get(i);
			/*applicationContext.publishEvent(new FundEvent(this,vo,userFundStatService,
					coinStreamService));*/
			
			Thread.sleep(1000);
		}
		
	}
	

	
	/*@Autowired(required = true)
	public void setCoinService(ICoinService coinService) {
		this.coinService = coinService;
	}*/
	
	@Autowired(required = true)
	public void setCoinStreamService(ICoinStreamService coinStreamService) {
		this.coinStreamService = coinStreamService;
	}
	
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	
	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
