package com.zdjf.components.mobile;

import java.util.Date;

public class InvestmentOrder {

	
private String phone;//手机号码
	
	private String amount;//充值金额
	
	private Date pay_time;//支付时间
	
	private String pay_time_ago;//时间
	
	private String type;//类型名称
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public String getPay_time_ago() {
		return pay_time_ago;
	}
	public void setPay_time_ago(String pay_time_ago) {
		this.pay_time_ago = pay_time_ago;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
