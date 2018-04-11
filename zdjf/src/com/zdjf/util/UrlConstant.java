package com.zdjf.util;

public class UrlConstant {

	//	/*-----------以下测试部分地址----------  */
//	public static final String MOBILE_URL= "m.zdjfu.com";  //手机端域名地址
//	public static final String API_RE_URL = "http://172.16.0.139:8080/zdjf/sina/jump.action";//同步回调测试地址
//	public static final String API_URL = "http://172.16.0.213:8080/xlpay/member/send.json";//新浪支付接口
//	public static final String API_S_COLLECT_TRADE_URL = "http://172.16.0.139:8080/zdjf/sina/collect/trade.action?uid=";//同步代收回调测试地址
//	public static final String API_S_PASSWORD_URL = "http://172.16.0.139:8080/zdjf/sina/passwd.action?phone=";//同步回调测试地址
//	public static final String API_S_WITHDRAW_URL = "http://172.16.0.139:8080/zdjf/sina/withdraw.action?phone=";  //测试地址	
//	public static final String API_S_DEPOSIT_URL = "http://172.16.0.139:8080/zdjf/sina/deposit.action?uid=";  //测试地址
//	public static final String API_WEIXIN_SHARE_URL = "http://172.16.0.139:8080/zdjf/sign/share.action?invite_source=weixin&" + "reg_source=4&inviter_phone=";//微信分享
//	public static final String API_NO_URL = "http://172.16.0.213/xlpay/notify/return/";//异步回调接口
//	public static final String API_S_BACK_URL = "http://172.16.0.139:8080/zdjf/sina/jump.action";//同步回调测试地址
//	public static final String API_S_REPAYMENT_URL = "http://172.16.0.139:8080/zdjf/sina/repayment.action?trade_no=";
//	//	//lig 2017-12-17
//	public static final String API_H5_REG_URL = "http://172.16.0.139:8080/zdjf/static/zdjf_app/page/login/reg.html?invite_source=";//H5  注册
//	public static final String API_H5_PASSWORD_URL = "http://172.16.0.139:8080/zdjf/static/zdjf_app/page/de_ok.html";//设置密码
//	public static final String API_H5_COLLECT_TRADE_URL = "http://172.16.0.139:8080/zdjf/static/zdjf_app/page/wealth/in_ok.html";//投资回调
//	//	//投资回调
//	public static final String API_H5_WITHDRAW_URL = "http://172.16.0.139:8080/zdjf/static/zdjf_app/page/recharge/w_ok.html";
//	//网管地址 此处为测试会员类接口地址，请根据实际情况填写
//	public static final String SINA_MEMBER_URL = "https://testgate.pay.sina.com.cn/mgs/gateway.do";	 //测试地址
//	public static final String RETURN_URL = "http://localhost:8080/zdjf/sina/asy/collect/trade.json";
//	public static final String RETURN_URL_WITHDRAW = "http://172.16.0.139:8080/zdjf/sina/asy/withdraw.json";
//	public static final String SINA_ORDER_URL = "https://testgate.pay.sina.com.cn/mas/gateway.do"; //测试地址
//	public static final String WY_RECHARGE = "http://localhost:8080/zdjf/sina/deposit.action"; 
//	public static final String partner_id = "200008641511";  //合作者身份ID  测试


	/** 正式生产环境结束*/

	public static final String version = "1.2";  //版本号
	public static final String _input_charset = "utf-8";  //参数编码字符集

	public static final String sign_type = "RSA";  //参数编码字符集

	public static final String API_KEY = "3QvlI7PArDBTa@4YC%efmFxU"; 

	public final static int NUM_NEG_ONE = -1;
	/**
	 * 以下部分是正式生产环境地址
	 */
	/**
	 * 以下部分是正式生产环境地址
	 */
		public static final String SINA_MEMBER_URL = "https://fintechsn.bosc.cn:8007/mgs/gateway.do";	 //正式地址
		public static final String SINA_ORDER_URL = "https://fintechsn.bosc.cn:8007/mas/gateway.do"; //正式地址
		
		public static final String RETURN_URL = "http://pre.zdjfu.com/sina/return/";   //正式异步投资请求地址
		public static final String RETURN_URL_WITHDRAW = "http://pre.zdjfu.com/sina/asy/withdraw.json"; //正式异步提现请求地址
		public static final String WY_RECHARGE = "http://pre.zdjfu.com/sina/deposit.action";   //正式异步网银充值请求
		public static final String partner_id = "200064029213";  //正式
		//	
		public static final String MOBILE_URL= "pctest.zdjfu.com";  //手机端域名地址
		public static final String API_RE_URL = "http://pre.zdjfu.com/sina/jump.action";//同步回调正式地址
		public static final String API_URL = "http://xlpay2.zdjfu.com/xlpay/member/send.json";//新浪支付正式接口
	
		public static final String API_NO_URL = "http://xlpay2.zdjfu.com/xlpay/notify/return/";//异步回调接口
	
		public static final String API_S_COLLECT_TRADE_URL = "http://pre.zdjfu.com/sina/collect/trade.action?uid=";//同步代收回调正式地址
	
		public static final String API_S_PASSWORD_URL = "http://pre.zdjfu.com/sina/passwd.action?phone=";//同步回调正式地址
	
		public static final String API_S_WITHDRAW_URL = "http://pre.zdjfu.com/sina/withdraw.action?phone=";
		public static final String API_S_DEPOSIT_URL = "http://pre.zdjfu.com/sina/deposit.action?uid=";
	
		public static final String API_WEIXIN_SHARE_URL = "http://pre.zdjfu.com/sign/share.action?invite_source=weixin&" + "reg_source=4&inviter_phone=";//同步回调地址
	
		public static final String API_S_BACK_URL = "http://pre.zdjfu.com/sina/jump.action";//同步回调测试地址
		public static final String API_S_REPAYMENT_URL = "http://pre.zdjfu.com/sina/repayment.action?trade_no=";
		public static final String API_S_BATCH_URL = "http://pre.zdjfu.com/sina/batch.action?trade_no=";
		//lig 2017-12-17
		public static final String API_H5_REG_URL = "http://pre.zdjfu.com/static/zdjf_app/page/login/reg.html?invite_source=";//H5  注册
	
	
		public static final String API_H5_PASSWORD_URL = "http://pre.zdjfu.com/static/zdjf_app/page/de_ok.html";//设置密码
	
		//
		public static final String API_H5_COLLECT_TRADE_URL = "http://pre.zdjfu.com/static/zdjf_app/page/wealth/in_ok.html";//投资回调
	
		//投资回调
		public static final String API_H5_WITHDRAW_URL = "http://pre.zdjfu.com/static/zdjf_app/page/recharge/w_ok.html";
	//	//示远短信地址
	public static final String SMS_SAYEN_URL="http://send.18sms.com/msg/HttpBatchSendSM";


}
