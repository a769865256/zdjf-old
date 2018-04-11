package com.zdjf.components.mobile;

import java.util.HashMap;
import java.util.Map;

import com.zdjf.util.Constants;
import com.zdjf.util.HttpClientUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.util.UrlConstant;

/*新浪所有调用接口统一类*/

public class SinaUtil {


	private static String getAsynchronousUrl(String service){
		return UrlConstant.API_NO_URL + service + ".action";
	}

	public static String createActiveMember(Long id,String ip){

		//新浪 激活会员
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", "create_activate_member");
		parameterMap.put("identity_id", id);
		//		parameterMap.put("notify_url", UrlConstant.API_NO_URL+"create_activate_member" + ".action");//业务
		String pay_sign = Md5Util.md5to32(id +"create_activate_member" +
				Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("identity_type", "UID");
		parameterMap.put("member_type", 1);
		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	/**
	 * 新浪 激活会员
	 * 2018-1-17
	 * @param id 用户uid
	 * @param ip 访问ip
	 * @param host_role 用户角色
	 * @return
	 */
	public static String createActiveMember(Long id,String ip,String host_role){

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", "create_activate_member");
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+"create_activate_member" + ".action");//业务
		String pay_sign = Md5Util.md5to32(id +"create_activate_member" +
				Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("identity_type", "UID");
		parameterMap.put("member_type", 1);
		parameterMap.put("host_role", host_role);
		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String setMemberHostRole(Long id,String host_role){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", "set_member_host_role");
		parameterMap.put("identity_id", id);
		parameterMap.put("identity_type", Constants.IDENTITY_TYPE);
		parameterMap.put("host_role", host_role);
		String pay_sign=Md5Util.md5to32(id +"set_member_host_role" +
				Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String setRealName(Long id,String real_name,String idcard_no,String ip){
		//新浪实名认证
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", "set_real_name");
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+"set_real_name" + ".action");//业务
		String pay_sign = Md5Util.md5to32(id +"set_real_name" +
				Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("identity_type", "UID");
		parameterMap.put("real_name", real_name);
		parameterMap.put("cert_type", "IC");
		parameterMap.put("cert_no", idcard_no);
		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String bindBankCard(Long id,String request_no,String bank_code,String bank_account_no,
			String card_attribute,String phone_no,String province,String city,String ip){
		//新浪绑定银行卡
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String strService = "binding_bank_card";


		parameterMap.put("service", strService);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+"strService" + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + strService +
				Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("request_no", request_no);//唯一标识
		parameterMap.put("identity_type", "UID");
		parameterMap.put("bank_code", bank_code);
		parameterMap.put("bank_account_no", bank_account_no);
		parameterMap.put("card_type", "DEBIT");
		parameterMap.put("card_attribute", card_attribute);
		parameterMap.put("phone_no", phone_no);
		parameterMap.put("province", province);
		parameterMap.put("city", city);
		parameterMap.put("verify_mode", "SIGN");
		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	/**
	 * 2018-1-17合并实名绑卡接口
	 * @param id uid
	 * @param request_no 商户订单号
	 * @param bank_code 银行简称
	 * @param bank_account_no 银行卡号
	 * @param card_attribute 卡属性
	 * @param phone_no 手机号
	 * @param province 省
	 * @param city 市
	 * @param ip 绑卡ip
	 * @param real_name_auth 是否实名
	 * @param real_name 姓名
	 * @param idcard_no 身份证号
	 * @return
	 */
	public static String bindBankCard(Long id,String request_no,String bank_code,String bank_account_no,
			String card_attribute,String phone_no,String province,String city,
			String ip,int real_name_auth,String real_name,String idcard_no){
		//新浪绑定银行卡
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String strService = "binding_bank_card";

		if(1!=(real_name_auth)){
			parameterMap.put("account_name", real_name);
			parameterMap.put("cert_type", "IC");
			parameterMap.put("cert_no", idcard_no);
			parameterMap.put("extend_param", Constants.SET_NAME_TRUE);
		}
		parameterMap.put("service", strService);
		parameterMap.put("identity_id", id);
		//异步回调
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+"strService" + ".action");
		String pay_sign = Md5Util.md5to32(id + strService +
				Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		//唯一标识
		parameterMap.put("request_no", request_no);
		parameterMap.put("identity_type", "UID");
		parameterMap.put("bank_code", bank_code);
		parameterMap.put("bank_account_no", bank_account_no);
		parameterMap.put("card_type", "DEBIT");
		parameterMap.put("card_attribute", card_attribute);
		parameterMap.put("phone_no", phone_no);
		parameterMap.put("province", province);
		parameterMap.put("city", city);
		parameterMap.put("verify_mode", "SIGN");
		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	/**
	 * 2018-3-13合并实名绑卡接口
	 * @param id uid
	 * @param real_name 姓名
	 * @param idcard_no 身份证号
	 * @return
	 */
	public static String bindBankCard(Long id,String real_name,String idcard_no,String reg_source){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String strService = "web_binding_bank_card";
		parameterMap.put("real_name", real_name);
		parameterMap.put("cert_no", idcard_no);
		parameterMap.put("service", strService);
		parameterMap.put("identity_id", id);
		if(reg_source.equals("4")||reg_source.equals("3")||reg_source.equals("2")){
			parameterMap.put("cashdesk_addr_category", "MOBILE");
		}
		//异步回调
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+"strService" + ".action");
		String pay_sign = Md5Util.md5to32(id + strService +
				Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		parameterMap.put("return_url", UrlConstant.API_RE_URL + "?reg_source="+reg_source+"&type=bindCard&uid="+id);
		parameterMap.put("memo", "正道金服");
		//唯一标识
		parameterMap.put("identity_type", "UID");
		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	/**
	 * 2018-3-13查询用户银行卡信息
	 * @param id uid
	 * @return
	 */
	public static String queryBankCard(String id){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String service = "query_bank_card";
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("identity_type", "UID");
		String pay_sign = Md5Util.md5to32(id + service +
				Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}
	
	public static String bindBankCardAdvance(String ticket,String valid_code,String ip){
		//新浪银行卡推进

		String service = "binding_bank_card_advance";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("ticket", ticket);
		parameterMap.put("valid_code", valid_code);
		parameterMap.put("client_ip", ip);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String withholdAuthority(Long id){
		//交易免密重定向

		String service = "handle_withhold_authority";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("quota", "++");
		parameterMap.put("day_quota", "++");
		parameterMap.put("auth_type_whitelist", "ALL,ACCOUNT");

		parameterMap.put("identity_type", "UID");
		parameterMap.put("identity_id", id);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String findVerifyMobile(Long id,int type,String reg_source){
		//找回认证手机
		String service = "find_verify_mobile";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_S_BACK_URL+"?reg_source="+reg_source+"&type=back");
		parameterMap.put("memo", "正道金服");
		parameterMap.put("identity_type", "UID");

		if(type == 1)
			parameterMap.put("cashdesk_addr_category", "MOBILE");


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String modifyPayPassword(Long id,int type,String ip,String reg_source,String jump_type){
		//修改支付密码
		String service = "modify_pay_password";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		//parameterMap.put("verify_type", "MOBILE");
		//String url = UrlConstant.API_S_PASSWORD_URL + phone;
		parameterMap.put("return_url", UrlConstant.API_S_BACK_URL+"?reg_source="+reg_source+"&type="+jump_type);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("identity_type", "UID");

		if(type == 1)
			parameterMap.put("cashdesk_addr_category", "MOBILE");

		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	/**
	 * 会员开户
	 * @param id
	 * @param ip
	 * @return
	 */
	public static String open_account(Long id,String ip){

		String service = "open_account";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		//parameterMap.put("verify_type", "MOBILE");
		//String url = UrlConstant.API_S_PASSWORD_URL + phone;
		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("account_type", "BANK");
		parameterMap.put("identity_type", "UID");


		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String findPayPassword(Long id,int type,String reg_source,String jump_type){
		//找回支付密码
		String service = "find_pay_password";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		//parameterMap.put("verify_type", "MOBILE");
		//String url = UrlConstant.API_S_PASSWORD_URL + phone;
		parameterMap.put("return_url", UrlConstant.API_S_BACK_URL+"?reg_source="+reg_source+"&type="+jump_type);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("identity_type", "UID");

		if(type == 1)
			parameterMap.put("cashdesk_addr_category", "MOBILE");

		//parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String setPayPassword(Long id,int type,String ip,String phone,String reg_source){
		//设定支付密码
		String service = "set_pay_password";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		String url = UrlConstant.API_S_PASSWORD_URL + phone+"&reg_source="+reg_source;
		parameterMap.put("return_url", url);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("identity_type", "UID");

		if(type == 1)
			parameterMap.put("cashdesk_addr_category", "MOBILE");

		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String isSetPayPassword(Long id,String ip){
		//是否交易密码
		String service = "query_is_set_pay_password";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("identity_type", "UID");


		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}


	public static String unbindBankCard(Long id,String card_id,String ip){

		//新浪解绑银行卡
		String service = "unbinding_bank_card";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("identity_type", "UID");
		parameterMap.put("card_id", card_id);
		parameterMap.put("advance_flag", "Y");
		parameterMap.put("client_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String unbindBankCardAdvance(Long id,String ticket,String valid_code,String ip){

		//新浪解绑银行卡验证
		String service = "unbinding_bank_card_advance";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("client_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("ticket", ticket);
		parameterMap.put("valid_code", valid_code);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String balanceFreeze(Long id,String out_freeze_no,
			String summary,Double amount,String ip){

		//新浪冻结余额
		String service = "balance_freeze";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("client_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("out_freeze_no", out_freeze_no);
		parameterMap.put("account_type", "BANK");
		parameterMap.put("summary", summary);
		parameterMap.put("amount", amount);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String queryBalance(Long id,String ip,String account_identity){

		//新浪查询余额
		String service = "query_balance";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+"create_activate_member" + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		if(account_identity!=null && ("101".equals(account_identity) || "103".equals(account_identity) || "102".equals(account_identity)
				|| "002".equals(account_identity)  ||"001".equals(account_identity))){
			parameterMap.put("account_identity", account_identity);
		}

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("client_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("account_type", "BANK");
		/*parameterMap.put("valid_code", valid_code);*/


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String queryAccountDetails(Long id,String start_time,String end_time,
			int page_size,int page_no,String ip){

		//新浪查询收支明细
		String service = "query_account_details";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("client_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("account_type", "BANK");
		parameterMap.put("start_time", start_time);
		parameterMap.put("end_time", end_time);
		parameterMap.put("page_no", page_no);
		parameterMap.put("page_size", page_size);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}
	public static String queryAccountDetails(Long id,String start_time,String end_time,
			int page_size,int page_no,String ip,String account_identity){

		//新浪查询收支明细
		String service = "query_account_details";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("client_ip", ip);
		parameterMap.put("identity_type", "UID");

		parameterMap.put("account_identity", account_identity);

		//以上几乎不变     以下参数会改变
		parameterMap.put("account_type", "BANK");
		parameterMap.put("start_time", start_time);
		parameterMap.put("end_time", end_time);
		parameterMap.put("page_no", page_no);
		parameterMap.put("page_size", page_size);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String balanceUnfreeze(Long id,String out_freeze_no,String out_unfreeze_no,
			String summary,Double amount,String ip){

		//新浪解冻余额
		String service = "balance_unfreeze";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("client_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("out_freeze_no", out_freeze_no);
		parameterMap.put("out_unfreeze_no", out_unfreeze_no);
		parameterMap.put("summary", summary);
		parameterMap.put("amount", amount);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}
	//标的查询
	public static String ItemQurty(String out_bid_no){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String service="query_bid_info";
		String pay_sign = Md5Util.md5to32(service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		parameterMap.put("service", service);
		parameterMap.put("out_bid_no", out_bid_no);
		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}
	//标的录入
	public static String ItemEntry(String debt_code,String bid_name , Double debt_money , Double income ,
			String bid_duration , String repay_type , String  protocol_type, String bid_product_type , 
			String recommend_inst , String limit_min_bid_copys , Double pay_add , 
			Double pay_max , Double pay_min , String summary , String url ,
			String begin_date , String term , String guarantee_method , String extend_param ,
			String borrower_info_list){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		//生成规则
		String service="create_bid_info";
		String out_bid_no=debt_code;
		String web_site_name=Constants.MEMO;
		parameterMap.put("service", service);//服务名称
		parameterMap.put("out_bid_no", out_bid_no);//商户标的号
		parameterMap.put("web_site_name", web_site_name);//网站名称/平台名称
		parameterMap.put("bid_name", bid_name);//标的名称
		parameterMap.put("bid_type", Constants.BID_TYPE);//标的类型
		parameterMap.put("bid_amount", debt_money);//发标金额
		parameterMap.put("bid_year_rate", income);//年化收益率
		parameterMap.put("bid_duration", bid_duration);//借款期限
		parameterMap.put("repay_type", repay_type);//还款方式
		parameterMap.put("protocol_type", protocol_type);//协议类型
		String pay_sign = Md5Util.md5to32(service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		parameterMap.put("bid_product_type", bid_product_type);//标的产品类型

		parameterMap.put("recommend_inst", recommend_inst);//推荐机构

		parameterMap.put("limit_min_bid_copys", limit_min_bid_copys);//限定最低投标份数
		parameterMap.put("limit_per_copy_amount", pay_add);//限定每份投标金额
		parameterMap.put("limit_max_bid_amount", pay_max);//限定最多投标金额
		parameterMap.put("limit_min_bid_amount", pay_min);//限定最少投标金额

		parameterMap.put("summary", summary);//摘要

		parameterMap.put("url", url);//标的url

		parameterMap.put("begin_date", begin_date);//标的开始时间
		parameterMap.put("term", term);//还款期限
		parameterMap.put("guarantee_method", guarantee_method);//担保方式
		parameterMap.put("extend_param", extend_param);//扩展信息
		parameterMap.put("borrower_info_list", borrower_info_list);//借款人信息列表
		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String createHostDeposit(Long id,String out_trade_no,
			String summary,Double amount,String pay_method,String ip,String account_identity,String reg_source){

		//新浪充值
		String service = "create_hosting_deposit";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		//String str = UrlConstant.API_NO_URL + service + ".action";
		parameterMap.put("notify_url", getAsynchronousUrl(service));//业务

		//System.out.println(str);
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		if(account_identity!=null && ("101".equals(account_identity) || "102".equals(account_identity) || "103".equals(account_identity))){
			parameterMap.put("account_identity", account_identity);
		}


		String url = UrlConstant.API_S_DEPOSIT_URL + id + "&out_trade_no=" + out_trade_no+"&reg_source="+reg_source;

		parameterMap.put("return_url", url);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("payer_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("out_trade_no", out_trade_no);
		parameterMap.put("account_type", "BANK");
		parameterMap.put("summary", summary);
		parameterMap.put("amount", amount);
		parameterMap.put("pay_method", pay_method);

		parameterMap.put("deposit_close_time","30m");
		if("2".equals(reg_source)||"3".equals(reg_source)||"4".equals(reg_source)){
			parameterMap.put("cashdesk_addr_category", "MOBILE");
		}

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String advanceHostPay(Long uid,String out_advance_no,String ticket,String valid_code,String ip){
		//新浪支付推进

		String service = "advance_hosting_pay";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL + service + ".action");//业务
		String pay_sign = Md5Util.md5to32(service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		String str = UrlConstant.API_NO_URL + service + ".action";


		String url = UrlConstant.API_S_DEPOSIT_URL + uid + "&out_trade_no=" + out_advance_no;
		parameterMap.put("return_url", UrlConstant.API_RE_URL);

		System.out.println(str);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("ticket", ticket);
		parameterMap.put("out_advance_no", out_advance_no);
		parameterMap.put("summary", "充值");
		parameterMap.put("validate_code", valid_code);
		parameterMap.put("user_ip", ip);

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}
	//查询支付结果
	public static String queryPayResult(String trade_no){


		String service = "query_pay_result";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("out_pay_no", trade_no);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String queryHostTrade(Long id,String trade_no,String start_time,String end_time){
		//新浪银行卡推进

		String service = "query_hosting_trade";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service   + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		parameterMap.put("identity_id", id);
		parameterMap.put("identity_type", "UID");
		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		//
		parameterMap.put("out_trade_no", trade_no);

		if(!start_time.isEmpty()&&!end_time.isEmpty()){
			parameterMap.put("start_time", start_time);
			parameterMap.put("end_time", end_time);
		}


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	public static String queryHostDeposit(Long id,String trade_no,String ip){

		//新浪查询收支明细
		String service = "query_hosting_deposit";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("client_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("account_type", "BANK");
		parameterMap.put("out_trade_no", trade_no);



		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String queryHostDeposit(Long id,String start_time,String end_time,
			int page_size,int page_no,String ip){

		//新浪查询收支明细
		String service = "query_hosting_deposit";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("client_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("account_type", "BANK");
		parameterMap.put("start_time", start_time);
		parameterMap.put("end_time", end_time);
		parameterMap.put("page_no", page_no);
		parameterMap.put("page_size", page_size);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String createHostWithdraw(Long id,String out_trade_no,String summary,Double amount,
			String card_id,Double user_fee,String payto_type,String cashdesk_addr_category,String ip,String phone,String reg_source){

		//新浪提现
		String service = "create_hosting_withdraw";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		String url = UrlConstant.API_S_WITHDRAW_URL + phone + "&out_trade_no=" + out_trade_no+"&amount="+amount+"&reg_source="+reg_source;

		parameterMap.put("return_url", url);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("user_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("out_trade_no", out_trade_no);
		parameterMap.put("account_type", "BANK");
		parameterMap.put("summary", summary);
		parameterMap.put("amount", amount);
		parameterMap.put("withdraw_mode", "CASHDESK");
		parameterMap.put("card_id", card_id);
		parameterMap.put("withdraw_close_time","10m");
		parameterMap.put("payto_type",payto_type);
		parameterMap.put("cashdesk_addr_category",cashdesk_addr_category);
		if(user_fee>0.0){
			parameterMap.put("user_fee",user_fee);

			url = url + "&fee_amt=" + user_fee;
		}

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String queryHostWithdraw(Long id,String out_trade_no,String ip){

		//新浪提现
		String service = "query_hosting_withdraw";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		//String url = UrlConstant.API_S_WITHDRAW_URL + phone + "&out_trade_no=" + out_trade_no;

		parameterMap.put("return_url", UrlConstant.API_NO_URL);
		parameterMap.put("memo", "正道金服");
		//parameterMap.put("user_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("out_trade_no", out_trade_no);
		parameterMap.put("account_type", "BANK");

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}


	public static String createHostCollectTrade(Long id,String out_trade_no,String out_trade_code,String summary,
			Double amount,String goods_id,String pay_method,String gift_money,String cashdesk_addr_category,
			String ip,String coin,String gift_id,String req_source,String user_coupon_id){

		//新浪代收
		String service = "create_hosting_collect_trade";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("payer_id", id);
		parameterMap.put("notify_url", UrlConstant.RETURN_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);
		//System.out.println("id:" + id + "---service:" + service);
		String url = "";
		if(out_trade_code == "1001")
			url = UrlConstant.API_S_COLLECT_TRADE_URL + id + "&trade=" + out_trade_no + "&debt=" + 
					goods_id + "&gift_id=" + gift_id +"&coin=" + coin + "&amount=" + 
					amount + "&req=" + req_source + "&coupon=" + user_coupon_id +"&gift=" + gift_money;
		else if(out_trade_code.equalsIgnoreCase("1002") || out_trade_code.equalsIgnoreCase("1003")){
			url = UrlConstant.API_S_REPAYMENT_URL + out_trade_no;
		}
		parameterMap.put("return_url", url);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("payer_ip", ip);
		parameterMap.put("payer_identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("out_trade_no", out_trade_no);
		parameterMap.put("out_trade_code", out_trade_code);
		//parameterMap.put("account_type", "BANK");
		parameterMap.put("summary", summary);
		parameterMap.put("amount", amount);
		parameterMap.put("pay_method", pay_method);
		parameterMap.put("goods_id", goods_id);
		parameterMap.put("trade_close_time","3m");
		//parameterMap.put("payto_type",payto_type);
		parameterMap.put("cashdesk_addr_category",cashdesk_addr_category);

		if(out_trade_code.equalsIgnoreCase("1001")&&!gift_money.equalsIgnoreCase("0.0")) //	特殊参数-投标（1001）
			parameterMap.put("gift_money",RoofNumberUtils.StringtoDouble(gift_money));


		if(out_trade_code.equalsIgnoreCase("1003")) //特殊参数-投标（1003）
			parameterMap.put("account_identity",103);




		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String createHostRefund(Long id,String out_trade_no,String orig_outer_trade_no,
			String summary,Double amount,String ip){

		//新浪退款
		String service = "create_hosting_refund";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(id + service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("user_ip", ip);
		parameterMap.put("identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("out_trade_no", out_trade_no);
		parameterMap.put("orig_outer_trade_no", orig_outer_trade_no);
		parameterMap.put("summary", summary);
		parameterMap.put("refund_amount", amount);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String createSingleHostPayTrade(Long id,String out_trade_no,String out_trade_code,
			String summary,Double amount,String goods_id,String ip,
			Double repay_principal,int repay_period,Double repay_profit,String extend_param){

		//新浪单笔代付
		String service = "create_single_hosting_pay_trade";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("payee_identity_id", id);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("user_ip", ip);
		parameterMap.put("payee_identity_type", "UID");


		//以上几乎不变     以下参数会改变
		parameterMap.put("out_trade_no", out_trade_no);
		parameterMap.put("out_trade_code", out_trade_code);
		parameterMap.put("account_type", "BANK");
		parameterMap.put("summary", summary);
		parameterMap.put("amount", amount);
		//parameterMap.put("cashdesk_addr_category",cashdesk_addr_category);

		//if(out_trade_code.equalsIgnoreCase("2001")) //	特殊参数-投标（1001）
		parameterMap.put("goods_id", goods_id);


		if(out_trade_code.equalsIgnoreCase("2002")){
			parameterMap.put("repay_principal", repay_principal);
			parameterMap.put("repay_period", repay_period);
			parameterMap.put("repay_profit", repay_profit);

			parameterMap.put("extend_param", extend_param);
		}



		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	public static String createBatchHostPayTrade(String out_pay_no,String trade_list,String ip){

		//新浪单笔代付
		String service = "create_batch_hosting_pay_trade";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("service", service);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("memo", "正道金服");
		parameterMap.put("user_ip", ip);
		parameterMap.put("notify_method", "batch_notify");
		parameterMap.put("trade_list", trade_list);

		//以上几乎不变     以下参数会改变
		parameterMap.put("out_pay_no", out_pay_no);
		parameterMap.put("out_trade_code", "2002");

		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);

	}

	/**
	 * 
	 * @param out_trade_no (交易订单号)
	 * @param payer_identity_id(付款人标识)
	 * @param payee_identity_id(收款人标识)
	 * @param amount(金额)
	 * @param goods_id(商户标的号)
	 * @param summary(摘要)
	 * @param extend_param 扩展信息
	 * @return
	 */
	public static String createDebtMigration(String out_trade_no,String payer_identity_id,String payee_identity_id,Double amount
			,String goods_id,String summary,String extend_param){
		String service = "create_debt_migration";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		String payer_identity_type = "UID";
		String payee_identity_type = "UID";
		parameterMap.put("service", service);
		parameterMap.put("notify_url", UrlConstant.API_NO_URL+service + ".action");//业务
		String pay_sign = Md5Util.md5to32(service + Constants.API_KEY);
		parameterMap.put("pay_sign", pay_sign);

		parameterMap.put("return_url", UrlConstant.API_RE_URL);
		parameterMap.put("out_trade_no", out_trade_no);
		parameterMap.put("payer_identity_id", payer_identity_id);
		parameterMap.put("payee_identity_id", payee_identity_id);
		parameterMap.put("amount", amount);
		parameterMap.put("goods_id", goods_id);
		parameterMap.put("summary", summary);
		parameterMap.put("extend_param", extend_param);
		parameterMap.put("payer_identity_type", payer_identity_type);
		parameterMap.put("payee_identity_type", payee_identity_type);


		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}

	/**
	 * 企业会员资质审核
	 * @return
	 */
	public static String audit_member_infos(){
		String service = "audit_member_infos";
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		return HttpClientUtil.post(UrlConstant.API_URL, parameterMap);
	}


}
