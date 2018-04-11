package com.zdjf.util;

/**
 * @name 短信发送配置参数
 * @auth shuc
 * @date 01-26 17:34:13
 */
public class SmsConfig {
	/** 短信接口地址 */
	public final static String SMS_URL = "http://gw.api.taobao.com/router/rest";
	
	/** 短信接口KEY */
	public final static String SMS_KEY = "23539599";
	
	/** 短信接口SECRET */
	public final static String SMS_SECRET = "16a42c17654328e2dc4a522d377b6c47";
	
	/** 短信签名 */
	public final static String SMS_SIGN_NAME = "正道金服";
	
	/** 发送短信默认类型 */
	public final static String SMS_DEFAULT_TYPE = "normal";
	
	/** 公司名 */
	public final static String SMS_PLATFORM_NAME = "正道金服";
	
	/** 验证码有效时间 */
	public final static String SMS_CODE_TIMES = "30";
	
	/** 预计体现到账天数 */
	public final static String SMS_WITHDRAW_DAY = "1";
	
	/** 短信发送服务器IP */
	public final static String SMS_SERVER_IP = "127.0.0.1";
	
	/**********************短信模板相关***************************/

//	/** 注册验证码-短信模板编码 */
//	public final static String SMS_TEML_REGISTER_CODE = "SMS_27620072";
//
//	/** 注册验证码-短信内容 */
//	public final static String SMS_TEML_REGISTER_TEXT = "您的注册验证码：%s，有效时间为%s分钟，感谢您使用%s。";
//
//	/** 找回登陆密码-短信模板编码 */
//	public final static String SMS_TEML_LOGIN_CODE = "SMS_27750054";
//
//	/** 找回登陆密码-短信内容 */
//	public final static String SMS_TEML_LOGIN_TEXT = "您的找回登录密码验证码：%s，有效时间为%s分钟，感谢您使用%s。";
//
//	/** 还款通知-短信模板编码 */
//	public final static String SMS_TEML_RETURN_CODE = "SMS_27350180";
//
//	/** 还款通知-短信内容 */
//	public final static String SMS_TEML_RETURN_TEXT = "您购买的%s理财项目已成功回款收益%s元%s，详细信息请登录账户查看。";
//
//	/** 理财产品购买-短信模板编码 */
//	public final static String SMS_TEML_BUY_CODE = "SMS_27480216";
//
//	/** 理财产品购买-短信内容 */
//	public final static String SMS_TEML_BUY_TEXT = "您已成功购买%s理财项目%s元，项目到期时间为%s，预期收益%s元。";
//
//	/** 提现到账通知-短信模板编码 */
//	public final static String SMS_TEML_DRAW_CODE = "SMS_27640022";
//
//	/** 提现到账通知-短信内容 */
//	public final static String SMS_TEML_DRAW_TEXT = "您于%s提现的%s元已成功回款至您的%s账户（尾号%s）。";
//
//	/** 短信模板编码 */
//	public class SmsTempCode {
//		/** 红包到账通知 */
//		public final static String gift_get = "SMS_27425267";
//
//		/** 生日红包到账通知 */
//		//public final static String gift_get_by_birthday = "SMS_10665660";
//
//		/** 红包过期通知 */
//		public final static String gift_timeout = "SMS_27360223";
//
//		/** 加息券到账通知 */
//		public final static String coupon_get = "SMS_27500185";
//
//		/** 加息券过期通知 */
//		public final static String coupon_timeout = "SMS_27660065";
//
//		/** 提前还款通知 */
//		public final static String product_advance = "SMS_27435265";
//
//		/** 抢标短信通知 */
//		public final static String product_rob = "SMS_44170021";
//	}
//
//	/** 短信模板文本 */
//	public class SmsTempText {
//		/** 红包到账通知 */
//		public final static String gift_get = "价值%s元的%s已存入您的账户，赶快去使用吧。";
//
//		/** 生日红包到账通知 */
//		//public final static String gift_get_by_birthday = "尊敬的客户，今天是您的生日，正道金服全体员工祝您生日快乐。为答谢您对我们的支持，特赠价值%s元优惠券愿您投资愉快。";
//
//		/** 红包过期通知 */
//		public final static String gift_timeout = "您有价值%s元的%s即将过期，不要忘记使用哦。";
//
//		/** 加息券到账通知 */
//		public final static String coupon_get = "%s张加息券已存入您的账户，赶快去使用吧。";
//
//		/** 加息券过期通知 */
//		public final static String coupon_timeout = "您有%s张加息券即将过期，不要忘记使用哦。";
//
//		/** 提前还款通知 */
//		public final static String product_advance = "您购买的%s将于%s提前结束，根据规定，您将得到%s天的补偿，具体请登录网站查看。";
//
//		/** 抢标短信通知 */
//		public final static String product_rob = "太幸运了，您是%s单笔%s的用户，获得称号“%s”，并得到正经值奖励 %s元。再接再厉哦！";
//		/**签到奖励短信通知**/
//		public  final  static  String coupon_sign="%s张加息劵和价值%s元的%s已存入您的账户，赶快去使用吧。";
//	}
	/** 注册验证码-短信模板编码 */
	public final static String SMS_TEML_REGISTER_CODE = "SMS_27620072";

	/** 注册验证码-短信内容 */
	public final static String SMS_TEML_REGISTER_TEXT = "您的注册验证码：{$var}，有效时间为{$var}分钟，感谢您使用{$var}。";

	/** 找回登陆密码-短信模板编码 */
	public final static String SMS_TEML_LOGIN_CODE = "SMS_27750054";

	/** 找回登陆密码-短信内容 */
	public final static String SMS_TEML_LOGIN_TEXT = "您的找回登录密码验证码：{$var}，有效时间为{$var}分钟，感谢您使用{$var}。";

	/** 还款通知-短信模板编码 */
	public final static String SMS_TEML_RETURN_CODE = "SMS_27350180";

	/** 还款通知-短信内容 */
	public final static String SMS_TEML_RETURN_TEXT = "您购买的{$var}理财项目已成功回款收益{$var}元{$var}，详细信息请登录账户查看。";

	/** 理财产品购买-短信模板编码 */
	public final static String SMS_TEML_BUY_CODE = "SMS_27480216";

	/** 理财产品购买-短信内容 */
	public final static String SMS_TEML_BUY_TEXT = "您已成功购买{$var}理财项目{$var}元，项目到期时间为{$var}，预期收益{$var}元。";

	/** 提现到账通知-短信模板编码 */
	public final static String SMS_TEML_DRAW_CODE = "SMS_27640022";

	/** 提现到账通知-短信内容 */
	public final static String SMS_TEML_DRAW_TEXT = "您于{$var}提现的{$var}元已成功回款至您的{$var}账户（尾号{$var}）。";

	/** 短信模板编码 */
	public class SmsTempCode {
		/** 红包到账通知 */
		public final static String gift_get = "SMS_27425267";

		/** 生日红包到账通知 */
		//public final static String gift_get_by_birthday = "SMS_10665660";

		/** 红包过期通知 */
		public final static String gift_timeout = "SMS_27360223";

		/** 加息券到账通知 */
		public final static String coupon_get = "SMS_27500185";

		/** 加息券过期通知 */
		public final static String coupon_timeout = "SMS_27660065";

		/** 提前还款通知 */
		public final static String product_advance = "SMS_27435265";

		/** 抢标短信通知 */
		public final static String product_rob = "SMS_44170021";
	}

	/** 短信模板文本 */
	public class SmsTempText {
		/** 红包到账通知 */
		public final static String gift_get = "价值{$var}元的{$var}已存入您的账户，赶快去使用吧。";

		/** 生日红包到账通知 */
		//public final static String gift_get_by_birthday = "尊敬的客户，今天是您的生日，正道金服全体员工祝您生日快乐。为答谢您对我们的支持，特赠价值{$var}元优惠券愿您投资愉快。";

		/** 红包过期通知 */
		public final static String gift_timeout = "您有价值{$var}元的{$var}即将过期，不要忘记使用哦。";

		/** 加息券到账通知 */
		public final static String coupon_get = "{$var}张加息券已存入您的账户，赶快去使用吧。";

		/** 加息券过期通知 */
		public final static String coupon_timeout = "您有{$var}张加息券即将过期，不要忘记使用哦。";

		/** 提前还款通知 */
		public final static String product_advance = "您购买的{$var}将于{$var}提前结束，根据规定，您将得到{$var}天的补偿，具体请登录网站查看。";

		/** 抢标短信通知 */
		public final static String product_rob = "太幸运了，您是{$var}单笔{$var}的用户，获得称号“{$var}”，并得到正经值奖励 {$var}元。再接再厉哦！";
		/**签到奖励短信通知**/
		public  final  static  String coupon_sign="{$var}张加息劵和价值{$var}元的{$var}已存入您的账户，赶快去使用吧。";
	}
}
