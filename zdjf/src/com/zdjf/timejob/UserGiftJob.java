package com.zdjf.timejob;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjf.util.DateUtil;
import com.zdjf.util.HttpClientUtil;

@Component
public class UserGiftJob {
	
	Log log = LogFactory.getLog(this.getClass());
	
	/*
	 * 新浪定时任务  由于
	 */
//	@Scheduled(cron = "0 18 16 * * ?")
	public void userGiftReturn(){
		
		Date date = new Date();
		
		Date yearEnd = DateUtil.getDateTime(2018, 4, 1,0,0,0);//2018-1-13 ~2018
		Date shuang = DateUtil.getDateTime(2018, 2, 10,0,0,0);//2018-2-10 ~ 2018-3-1
		if(shuang.getTime()<date.getTime()&&date.getTime()<yearEnd.getTime()){//2018-1-12
			
			String url = "https://www.zdjfu.com/m/userGift/Payment.action";
			Map<String,Object> parameterMap = new HashMap<String,Object>();
			parameterMap.put("gift_name", "回款红包");
			parameterMap.put("gift_source", 105);
			/*String str =*/ HttpClientUtil.post(url, parameterMap);
			
			//String str1 = str;
				
		}
		
	}

}
