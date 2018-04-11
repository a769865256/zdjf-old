package com.zdjf.service.sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.api.sms.SmsParams;
import com.zdjf.api.sms.SmsParams.SmsParamBuy;
import com.zdjf.api.sms.SmsParams.SmsParamDraw;
import com.zdjf.api.sms.SmsParams.SmsParamGift;
import com.zdjf.api.sms.SmsParams.SmsParamReturn;
import com.zdjf.api.sms.SmsParams.SmsParamVerify;
import com.zdjf.api.sms.SmsSend;
import com.zdjf.dao.sms.ISmsDao;
import com.zdjf.domain.sms.Sms;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.util.DateUtil;
import com.zdjf.util.SmsConfig;

@Service
public class SmsService implements ISmsService {

	private ISmsDao smsDao;
	
	/**
	 * 短信发送
	 * 返回0表示短信发送成功。
	 */
	public String sendSms(Sms sms){
		String res = null;
		if(null==sms){
			
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("create_time", DateUtil.addDate(DateUtil.nowDate(), -1));
			map.put("phone", sms.getPhone());
			List<Sms> list = (List<Sms>)smsDao.selectForList("selectSmsList", map);
			if(null != list && list.size()>0){
				if(list.size()>10){
					return "24小时内短信不能超过10条！";
				}
				Sms s = (Sms)list.get(0);
				if(DateUtil.nowDate().getTime()-s.getCreate_time().getTime()<1000*60){
					return "1分钟内禁止重复发送短信！";
				}
			}
			SmsParams sp = new SmsParams();
			SmsSend smsSend = new SmsSend();
			 if(sms.getSms_type()==1){ //发送注册验证码短信
				 SmsParamVerify spv = sp.new SmsParamVerify(sms.getCode());
				 res = smsSend.send(SmsConfig.SMS_TEML_REGISTER_CODE, sms.getPhone(), spv.toString());
			 }else if(sms.getSms_type()==2){ //发送找回登陆密码验证码短信
				 SmsParamVerify spv = sp.new SmsParamVerify(sms.getCode());
				 res = smsSend.send(SmsConfig.SMS_TEML_LOGIN_CODE, sms.getPhone(), spv.toString());
			 }else if(sms.getSms_type()==3){ //还款通知
				 SmsParamReturn spv = sp.new SmsParamReturn();
				 spv.setAmount(sms.getAmount());
				 spv.setIncomed(sms.getIncome());
				 spv.setProductName(sms.getProduct());
				 res = smsSend.send(SmsConfig.SMS_TEML_RETURN_CODE, sms.getPhone(), spv.toString());
			 }else if(sms.getSms_type()==4){//发送理财产品购买短信
				 SmsParamBuy spv = sp.new SmsParamBuy();
				 res = smsSend.send(SmsConfig.SMS_TEML_BUY_CODE, sms.getPhone(), spv.toString());
			 }else if(sms.getSms_type()==5){//发送提现通知
				 SmsParamDraw spv = sp.new SmsParamDraw();
				 res = smsSend.send(SmsConfig.SMS_TEML_DRAW_CODE, sms.getPhone(), spv.toString());
			 }else if(sms.getSms_type()==6){//红包 红包金额写到内容里
				 SmsParamGift spv = sp.new SmsParamGift(sms.getContent());
				 res = smsSend.send(SmsConfig.SmsTempCode.gift_get, sms.getPhone(), spv.toString());
			 }
			 System.out.println("短信发送结果"+res);
			 sms.setCreate_time(DateUtil.nowDate());
			 if(res!=null && "0".equals(res)){
				 sms.setStatus(1);
			 }else{
				 sms.setStatus(-1);
			 }
			 smsDao.save(sms);
		}
		
		return res;
	}
	
	/**
	 * 
	 * 根据手机号获取最新验证码
	 * 返回验证码
	 */
	@SuppressWarnings("unchecked")
	public Sms getCodeByPhone(String phone){
		String code=null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("create_time", DateUtil.addDate(DateUtil.nowDate(), -1));
		map.put("phone", phone);
		List<Sms> list = (List<Sms>)smsDao.selectForList("selectSmsList", map);
		if(null != list && list.size()>0){
			
			Sms s = (Sms)list.get(0);
			return s;
		}
		return  null;
	}
	
	@Override
	public Page page(Page page, Map<String, Object> map) {
		return smsDao.page(page, map);
	}
	@Override
	public Page page(Page page, Sms sms) {
		return smsDao.page(page, sms);
	}
	
	@Autowired
	public void setSmsDao(
			@Qualifier("smsDao") ISmsDao  smsDao) {
		this.smsDao = smsDao;
	}

	

	
	
}
