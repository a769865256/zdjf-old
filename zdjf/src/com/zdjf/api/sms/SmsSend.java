package com.zdjf.api.sms;



import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zdjf.util.HttpClientUtil;
import com.zdjf.util.SmsConfig;
import com.zdjf.util.UrlConstant;

import net.sf.json.JSONObject;

/**
 * @name 发送短信
 * @auth shuc
 * @date 01-26 17:30:21
 */
public class SmsSend {
	

	/**
	 * 发送短信
	 * @param temlCode 模板ID
	 * @param phone 手机号
	 * @param paramJson 参数(json格式)
	 * @return
	 */
	public String send(String temlCode, String phone, String paramJson) {
		

		//新建连接
		DefaultTaobaoClient client = new DefaultTaobaoClient(
			SmsConfig.SMS_URL, 
			SmsConfig.SMS_KEY, 
			SmsConfig.SMS_SECRET);
		
		//配置参数
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		
		//设置参数
		req.setSmsFreeSignName(SmsConfig.SMS_SIGN_NAME);//短信签名
		req.setSmsType(SmsConfig.SMS_DEFAULT_TYPE);//短信类型，使用默认值
		req.setSmsTemplateCode(temlCode);//短信模板CODE
		req.setSmsParam(paramJson);//短信模板参数
		req.setRecNum(phone);//手机号
		
		try {
			//发送
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			//返回的json字符串
			String res = rsp.getBody();
			String err_code = "";
			//json串中包含["err_code":"0"]则提交成功
			if(res.indexOf("\"err_code\":\"0\"")!=-1) {
				JSONObject jb = JSONObject .fromObject(res);
				String response = jb.get("alibaba_aliqin_fc_sms_num_send_response").toString();
				JSONObject jsonObject = JSONObject .fromObject(response);
				String result = jsonObject.get("result").toString();
				JSONObject object = JSONObject .fromObject(result);
				err_code = object.get("err_code").toString();
			} else {
				JSONObject jb = JSONObject .fromObject(res);
				String error_response= jb.get("error_response").toString();
				JSONObject jsonObject = JSONObject .fromObject(error_response);
				String sub_msg = jsonObject.get("sub_msg").toString();
				err_code = sub_msg;
			}
			return err_code;
		} catch (Exception e) {
			//提交失败
//			eRsp.setErrcodeAndMsg(ErrorCode.failure, e.getErrMsg());
		}
		return null;
	}
	
	//2018-01-22
	public String sendSaYen(String temlCode,String phone,String msg) {
		Properties prop = new Properties();
		Resource resource = new ClassPathResource("project.properties");
		InputStream in = null;
		try {
			in = resource.getInputStream();
			prop.load(in);
			String account=prop.getProperty("SMS.account").trim();
			String pswd=prop.getProperty("SMS.pswd").trim();
			DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
			Map map=new HashMap();
			map.put("account", account);
			map.put("ts", df.format(new Date()));
			map.put("pswd", pswd);
			map.put("mobile", phone);
			map.put("msg", msg);
			map.put("needstatus", true);
			map.put("resptype", "json");
			return HttpClientUtil.post(UrlConstant.SMS_SAYEN_URL, map);
		} catch (IOException e) {
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
}
