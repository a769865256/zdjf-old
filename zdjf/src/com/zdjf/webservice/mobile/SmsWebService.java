package com.zdjf.webservice.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.domain.sms.Sms;
import com.zdjf.service.sms.ISmsService;
import com.zdjf.util.Constants;
import com.zdjf.util.Md5Util;
import com.zdjf.util.PhoneFormatCheckUtils;
import com.zdjf.util.StringUtil;
import com.zdjf.webservice.express.SmsReturn;

@Controller
@RequestMapping("/m/sms")
public class SmsWebService {

	private ISmsService smservice;
	
	
	
	@RequestMapping("/send")
	public @ResponseBody SmsReturn sendSms(HttpServletRequest request,
            HttpServletResponse response){
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		String ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		SmsReturn sr = new SmsReturn();
		if(phone!=null && type!=null && sign!=null && PhoneFormatCheckUtils.isChinaPhoneLegal(phone)){
			if(Md5Util.md5to32(phone+type+ip+Constants.API_KEY).equals(sign)){
				Sms sms = new Sms();
				sms.setSms_type(Integer.valueOf(type));
				sms.setPhone(phone);
				sms.setSource_ip(ip);
				sms.setStatus(2);
				sms.setStatus(0);
				if(type.equals("1")||type.equals("2")){
					sms.setCode(StringUtil.getRandom());
				}
				String res = smservice.sendSms(sms);
				
				if(res!=null && "0".equals(res)){					
					sr.setStatus(100);
					sr.setReturnCase(true);
					
				}else{
					sr.setStatus(101);
					sr.setReturnCase(false);
				}
					sr.setContent(res);
				return sr;
			}else{
				sr.setStatus(102);
				sr.setReturnCase(false);
				sr.setContent("请求失败");
			}
		}else{
			sr.setStatus(101);
			sr.setReturnCase(false);
			sr.setContent("请求失败");
		}
		
		return sr;
	}
	
	@Autowired(required = true)
	public void setSmsService(ISmsService smservice) {
		this.smservice = smservice;
	}
	
}
