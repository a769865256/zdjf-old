package com.zdjf.webservice.mobile;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zdjf.domain.app.AppRelease;
import com.zdjf.domain.app.AppReleaseVo;
import com.zdjf.service.app.IAppReleaseService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.webservice.express.SmsReturn;

@RestController
@RequestMapping("/m/appRelease")
public class AppReleaseWebService {
	
	private IUserService userService;
	
	private IAppReleaseService appReleaseService;
	
	//添加发布版本
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody SmsReturn addAppRelease(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		SmsReturn sr = new SmsReturn();
		
		String release_version = request.getParameter("release_version");
		String sub_version = request.getParameter("sub_version");
		String release_content = request.getParameter("release_content");
		String is_release = request.getParameter("is_release");
		String down_url = request.getParameter("down_url");
		String release_channel = request.getParameter("release_channel");
		String re_release_time = request.getParameter("re_release_time");
		
		AppRelease appRelease = new AppRelease();
		
		appRelease.setRelease_version(release_version);
		appRelease.setSub_version(sub_version);
		appRelease.setRelease_content(release_content);
		appRelease.setIs_release(Integer.parseInt(is_release));
		appRelease.setDown_url(down_url);
		appRelease.setRelease_channel(release_channel);
		appRelease.setRe_release_time(DateUtil.parseDateTimes(re_release_time));
		appRelease.setCreate_time(new Date());
		
		appReleaseService.save(appRelease);
		
		sr.setContent(appRelease);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	//
	@RequestMapping(value="/version",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getLatestVersion(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		SmsReturn sr = new SmsReturn();
	
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		AppRelease appRelease = new AppRelease();
		
		/*if( null==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
	
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		
		}*/
		List<AppReleaseVo> list = appReleaseService.selectForList(appRelease);
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		if(list.size() == 0){
			sr.setContent(appRelease);
		}else{
			sr.setContent(list.get(list.size() - 1));
		}
		
		return sr;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setAppReleaseService(IAppReleaseService appReleaseService) {
		this.appReleaseService = appReleaseService;
	}

}
