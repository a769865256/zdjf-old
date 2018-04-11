package com.zdjf.timejob;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SinaJob {
	
	Log log = LogFactory.getLog(this.getClass());
	
	/*
	 * 新浪定时任务  由于
	 */
	//@Scheduled(cron = "0 0/7 * * * ?")
	public void sinaJobReturn(){
		
	}

}
