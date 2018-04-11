package com.zdjf.components.events;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.zdjf.domain.user.User;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;

@Component
public class OpenAccountEventListener implements ApplicationListener<OpenAccountEvent>{

	
	private IUserService userService; 
	
	@Override
	public void onApplicationEvent(OpenAccountEvent arg0) {
		// TODO Auto-generated method stub
		
		User user = arg0.getUser();
		String userip = arg0.getIp();
		//
		int reslut = openAccount(user,userip);
		
		if(reslut != 5){
			openAccount(user,userip);
		}
		
	}
	
	
	private int openAccount(User user,String uip){
		
		/*String result = SinaUtil.setOpenAccount(user.getId(), uip);
		
		
		System.out.println(result);
		
		
		String remark = user.getRemark();
		if(remark == null || remark.isEmpty())
			user.setRemark("open:" + result);
		else{
			
			user.setRemark(remark + ";open:" + result);
		}
		
		int times = user.getOpen_account();
		user.setOpen_account(++times);
		
		
		userService.update(user);
		
		if(result.trim().isEmpty()){
			System.out.println("sina in error");
			return times;
		}
		
		Map resultMap = JsonUtil.jsonToMap(result);
		
		String strCode = (String) resultMap.get("response_code");
		
		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			System.out.println("open account ");
			return times;
		}*/
		
		user.setOpen_account(Constants.OPEN_ACCOUNT_KEY);
		user.setOpen_account_time(new Date());
		
		userService.update(user);
		
		
		return Constants.OPEN_ACCOUNT_KEY;
	}
	
	
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
