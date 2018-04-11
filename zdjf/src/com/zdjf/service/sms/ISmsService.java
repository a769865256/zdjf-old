package com.zdjf.service.sms;

import java.util.Map;

import com.zdjf.domain.sms.Sms;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISmsService {

	/**
	 * 发送短信
	 * @param sms
	 * @return
	 */
	String sendSms(Sms sms);
	
	/**
	 * 根据手机号获取最新验证码
	 * @param phone
	 * @return
	 */
	Sms getCodeByPhone(String phone);
	public abstract Page page(Page page, Map<String, Object> map);
	public abstract Page page(Page page, Sms sms);
	
}
