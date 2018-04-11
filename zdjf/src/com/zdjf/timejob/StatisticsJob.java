package com.zdjf.timejob;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjf.domain.source.Source;
import com.zdjf.domain.source.SourceVo;
import com.zdjf.domain.statistics.DailySurvey;
import com.zdjf.domain.statistics.DailySurveyVo;
import com.zdjf.domain.statistics.SourceSurvey;
import com.zdjf.domain.statistics.SourceSurveyVo;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.source.ISourceService;
import com.zdjf.service.statistics.IDailySurveyService;
import com.zdjf.service.statistics.ISourceSurveyService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.DateUtil;

@Component
public class StatisticsJob {
	
	private IUserService userService;
	
	
	private ISourceService sourceService;
	
	private IUserFundStatService userFundStatService;
	
	private ApplicationContext applicationContext;
	
	private IDailySurveyService dailySurveyService;
	
	private ISourceSurveyService sourceSurveyService;
	
	//@Scheduled(cron = "0 0/35 * * * ?")
	public void allUserDay(){
		
		Date date = new Date();
		System.out.println(String.format("StatisticsJob time-%s",date));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String date_while = dateFormat.format(date);
		dailySurvey(date_while,date);
		
		sourceSurvey(date_while,date);
		
	}
	
	List<UserVo> getMobileRegistrations(){
		User user = new User();
		user.setReg_source(2);
		List<UserVo> listIOS = userService.selectForList(user);
		user.setReg_source(3);
		List<UserVo> listA = userService.selectForList(user);
		
		List<UserVo> list = new ArrayList<UserVo>();
		
		list.addAll(listIOS);
		list.addAll(listA);
		
		return list;
	}
	
	List<UserVo> getNewMobileRegistrations(Date date){
		//新增
		String start = DateUtil.formatDateTimeS(DateUtil.dateBegin(date));
	    String end = DateUtil.formatDateTimeS(DateUtil.dateEnd(date));
	    
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectUserByDay");
		map.put("start_date",Timestamp.valueOf(start));
		map.put("end_date",Timestamp.valueOf(end));
		map.put("reg_source",2);
		
		List<UserVo> listIOS = userService.selectForList(map);
		map.put("reg_source",3);
		List<UserVo> listA = userService.selectForList(map);
		
		List<UserVo> list = new ArrayList<UserVo>();
		
		list.addAll(listIOS);
		list.addAll(listA);
		
		return list;
	}
	
	void dailySurvey(String date_while,Date date){
		
		//按照日来统计
		DailySurvey dailySurvey = new DailySurvey();
		dailySurvey.setDate_while(date_while);
		List<DailySurveyVo> listDaily = dailySurveyService.selectForList(dailySurvey);
		
		DailySurvey daily = null;
		User user = new User();
		List<UserVo> listUser = userService.selectForList(user);
		int total_registration = listUser.size();//总注册数
		user.setReal_name_auth(1);
		listUser = userService.selectForList(user);
		int total_real_name = listUser.size();
		//List<UserVo> listMobileRegistrations = getMobileRegistrations();//移动
		//新增
		String start = DateUtil.formatDateTimeS(DateUtil.dateBegin(date));
	    String end = DateUtil.formatDateTimeS(DateUtil.dateEnd(date));
	    
	    
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectUserByDay");
		map.put("start_date",Timestamp.valueOf(start));
		map.put("end_date",Timestamp.valueOf(end));

		List<UserVo> listUserDay = userService.selectForList(map);
		
		int new_registrations = listUserDay.size();//新注册数
		map.put("real_name_auth",1);
		listUserDay = userService.selectForList(map);
		int new_real_name_authentication = listUserDay.size();//新的实名认证
	    System.out.println(start);
	    System.out.println(end);
	    
	    List<UserVo> listNewMobileRegistrations = getNewMobileRegistrations(date);
	  
	    int newMobileRegistrations = listNewMobileRegistrations.size();//新注册移动端
		
		
		
		if(listDaily.size() == 1){
			daily = listDaily.get(0);
		}else if(listDaily.size() == 0){
			daily = new DailySurvey();
		}
		
		daily.setDate_while(date_while);
		daily.setCreate_time(date);
		//总注册
		daily.setTotal_registration(total_registration);
		daily.setTotal_real_name(total_real_name);
		
		//新增
		daily.setNew_registrations(new_registrations);
		daily.setNew_real_name_authentication(new_real_name_authentication);
		daily.setNew_mobile_registrations(newMobileRegistrations);
		if(listDaily.size() >= 1){
			dailySurveyService.updateDailySurveyById(daily);
		}else if(listDaily.size() == 0){
			dailySurveyService.save(daily);
		}
		
	}
	
	void sourceSurvey(String date_while,Date date){
		//按照资源统计
		Source sour = new Source();
		List<SourceVo> listS = sourceService.selectForList(sour);
		for(int i = 0;i<listS.size();i++){
			Source itemSource = listS.get(i);
			SourceSurvey sourceSurvey = new SourceSurvey();
			sourceSurvey.setDate_while(date_while);
			sourceSurvey.setSource_name(itemSource.getSource_name());
			sourceSurvey.setSubordinate_platform(itemSource.getSource_platform() + "");
			
			List<SourceSurveyVo> listSource = sourceSurveyService.selectForList(sourceSurvey);
			
			SourceSurvey source = null;
			if(listSource.size() == 1){
				source = listSource.get(0);
			}else if(listSource.size() == 0){
				source = new SourceSurvey();
			}
			source.setDate_while(date_while);
			source.setSource_name(itemSource.getSource_name());
			source.setSubordinate_platform(itemSource.getSource_platform() + "");
			source.setCreate_time(date);
			User user = new User();
			user.setReg_source(itemSource.getSource_platform());//这里输入用户条件
			user.setInvite_source(itemSource.getSource_name());
			List<UserVo> listUser = userService.selectForList(user);
			
			int total_registration = listUser.size();//总注册数
			user.setReal_name_auth(1);
			listUser = userService.selectForList(user);
			int total_real_name = listUser.size();
			
			source.setTotal_registration(total_registration);
			source.setTotal_real_name(total_real_name);
			
			List<UserVo> listNewMobileRegistrations = getNewMobileRegistrations(date);
			  
		    int newMobileRegistrations = listNewMobileRegistrations.size();//新注册移动端
			
			//新增
			String start = DateUtil.formatDateTimeS(DateUtil.dateBegin(date));
		    String end = DateUtil.formatDateTimeS(DateUtil.dateEnd(date));
		    
		    
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("page_name","selectUserByDay");
			map.put("start_date",Timestamp.valueOf(start));
			map.put("end_date",Timestamp.valueOf(end));
			map.put("reg_source",itemSource.getSource_platform());
			map.put("invite_source",itemSource.getSource_name());
			List<UserVo> listUserDay = userService.selectForList(map);
			int new_registrations = listUserDay.size();//新注册数
			map.put("real_name_auth",1);
			listUserDay = userService.selectForList(map);
			int new_real_name_authentication = listUserDay.size();//新的实名认证
			
		
			
			source.setNew_registrations(new_registrations);
			source.setNew_real_name_authentication(new_real_name_authentication);
			source.setNew_mobile_registrations(newMobileRegistrations);
			if(listSource.size() >= 1){
				sourceSurveyService.updateSourceSurveyById(source);
			}else if(listSource.size() == 0){
				sourceSurveyService.save(source);
			}
		}
		
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
	
	@Autowired(required = true)
	public void setDailySurveyService(IDailySurveyService dailySurveyService) {
		this.dailySurveyService = dailySurveyService;
	}
	
	@Autowired(required = true)
	public void setSourceSurveyService(ISourceSurveyService sourceSurveyService) {
		this.sourceSurveyService = sourceSurveyService;
	}
	
	@Autowired(required = true)
	public void setSourceService(ISourceService sourceService) {
		this.sourceService = sourceService;
	}

}
