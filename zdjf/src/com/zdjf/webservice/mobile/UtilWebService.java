package com.zdjf.webservice.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zdjf.util.CityMapUtil;
import com.zdjf.util.Constants;
import com.zdjf.util.Md5Util;
import com.zdjf.webservice.express.SmsReturn;


@RestController
@RequestMapping("/m/util")
public class UtilWebService {
	
	//获取省份
	@RequestMapping(value="/province",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getProvince(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		Object[] province = CityMapUtil.getProvince();
		if(!sign.equalsIgnoreCase(md5Sign)){
			
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		}
		
		if(province != null){
			sr.setReturnCase(true);
			sr.setStatus(100);
			sr.setMapContent(province);
		}else{
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("no");
		}
		
	
		return sr;
	}
	
	//获取城市
	@RequestMapping(value="/city",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getCity(HttpServletRequest request,
            HttpServletResponse response){
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("province");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		Object[] city = CityMapUtil.getCity(reg_ip);
		if(!sign.equalsIgnoreCase(md5Sign)){
			
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		}
		
		if(city != null){
			sr.setReturnCase(true);
			sr.setStatus(100);
			sr.setMapContent(city);
		}else{
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("no");
		}
		
	
		return sr;
	}

}
