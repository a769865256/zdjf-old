package com.zdjf.api.sms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zdjf.util.SmsConfig;

/**
 * @name 短信发送参数
 * @auth shuc
 * @date 01-26 20:11:02
 */
public class SmsParams {

	/**
	 * 验证码短信参数类（包含注册、找回登陆密码、找回交易密码）
	 */
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

	/**
	 * 还款通知短信参数类
	 */
	public class SmsParamReturn {
		// 产品名称
		private String productName = "";

		// 利息收益
		private String incomed = "";

		// 本金信息（格式为: ，本金1000元）
		private String amount = "";

		public SmsParamReturn() {

		}

		public String toString() {
			Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			return gson.toJson(this);
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getIncomed() {
			return incomed;
		}

		public void setIncomed(String incomed) {
			this.incomed = incomed;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}
	}

	/**
	 * 理财产品购买短信参数类
	 */
	public class SmsParamBuy {
		// 理财产品名称
		private String productName = "";

		// 购买金额
		private String money = "";

		// 项目到期时间
		private String returnDate = "";

		// 预期收益
		private String incomed;

		public SmsParamBuy() {

		}

		public String toString() {
			Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			return gson.toJson(this);
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getReturnDate() {
			return returnDate;
		}

		public void setReturnDate(String returnDate) {
			this.returnDate = returnDate;
		}

		public String getIncomed() {
			return incomed;
		}

		public void setIncomed(String incomed) {
			this.incomed = incomed;
		}
	}

	/**
	 * 提现到账通知短信参数类
	 */
	public class SmsParamDraw {
		// 提现时间
		private String drawTime = "";

		// 提现金额
		private String money = "";

		// 银行名称
		private String bankName = "";

		// 银行卡号（后4位）
		private String bankNo = "";

		public SmsParamDraw() {

		}

		public String toString() {
			Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			return gson.toJson(this);
		}

		public String getDrawTime() {
			return drawTime;
		}

		public void setDrawTime(String drawTime) {
			this.drawTime = drawTime;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBankNo() {
			return bankNo;
		}

		public void setBankNo(String bankNo) {
			this.bankNo = bankNo;
		}
	}

	/**
	 * 支付宝转账充值到账通知短信参数类
	 */
	public class SmsParamRecharge {
		// 充值金额
		private String money = "";

		public SmsParamRecharge() {

		}

		public String toString() {
			Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			return gson.toJson(this);
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}
	}

	/**
	 * 红包到账通知短信参数类
	 */
	public class SmsParamGift {
		// 红包金额
		private String money = "";

		// 红包名称
		private String giftName = "";

		// 红包有效期
		private String startDate = "";

		// 红包有效期
		private String endDate = "";

		public SmsParamGift(String money) {
			this.money = money;
		}

		public String toString() {
			Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			return gson.toJson(this);
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getGiftName() {
			return giftName;
		}

		public void setGiftName(String giftName) {
			this.giftName = giftName;
		}

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
	}
}
