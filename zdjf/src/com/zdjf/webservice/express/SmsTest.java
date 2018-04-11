package com.zdjf.webservice.express;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zdjf.api.sms.SmsSend;
import com.zdjf.util.SmsConfig;

public class SmsTest {

	public class SmsParamVerify {
		// 验证码
		private String code = "";

		// 有效时间(分钟)
		private String minute = "";

		// 公司名称
		private String company = "";

		public SmsParamVerify(String code) {
			this.code = code;
			this.minute = SmsConfig.SMS_CODE_TIMES;
			this.company = SmsConfig.SMS_PLATFORM_NAME;
		}

		public String toString() {
			Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			return gson.toJson(this);
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMinute() {
			return minute;
		}

		public void setMinute(String minute) {
			this.minute = minute;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SmsTest test = new SmsTest();
		SmsParamVerify spv = test.new SmsParamVerify("123456");
		SmsSend sms = new SmsSend();
//		String paramJson = "";
		sms.send(SmsConfig.SMS_TEML_REGISTER_CODE, "158888822200", spv.toString());
//		System.out.println(Md5Util.md5to32("15888882220"+1+"127.0.0.1"+Constants.API_KEY));
//		PhoneFormatCheckUtils.isChinaPhoneLegal("1111111111111");
	}

}
