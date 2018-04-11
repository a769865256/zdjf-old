package com.zdjf.domain.admin;

import java.io.Serializable;

public class BusinessDayStat implements Serializable{

	private static final long serialVersionUID = 6725800060388477025L;

	protected Long id;

	protected String stat_date;    //varchar not null comment '统计日期',
	protected int web_register;   //int comment 'web注册人数',
	protected int android_register;   //int comment '安卓注册人数',
	protected int ios_register;   //int comment 'ios注册人数',
	protected int wap_register;   //int comment 'wap注册人数',
	protected int first_invest_count; //int comment '首投人数',
	protected Double web_recharge;   //decimal(11,2) comment 'web充值金额',
	protected Double android_recharge;   //decimal(11,2) comment '安卓充值金额',
	protected Double ios_recharge;   //decimal(11,2) comment 'ios充值金额',
	protected Double wap_recharge;   //decimal(11,2) comment 'wap充值金额',
	protected Double web_invest_amount;  //decimal(11,2) comment 'web投资金额',
	protected Double android_invest_amount; //decimal(11,2) comment '安卓投资金额',
	protected Double ios_invest_amount;  //decimal(11,2) comment 'ios投资金额',
	protected Double wap_invest_amount;  //decimal(11,2) comment 'wap投资金额',
	protected Double web_withdrawals_amount; //decimal(11,2) comment 'web提现金额',
	protected Double android_withdrawals_amount; //decimal(11,2) comment '安卓提现金额',
	protected Double ios_withdrawals_amount; //decimal(11,2) comment 'ios提现金额',
	protected Double wap_withdrawals_amount; //decimal(11,2) comment 'wap提现金额',
	protected Double invest_service_charge; //decimal(11,2) comment '充值手续费',
	protected Double withdrawals_service_charge; //decimal(11,2) comment '提现手续费',
	protected int pending_withdrawals_count; //int comment '待审核提现人数',
	protected int user_count;     //int comment '总用户数',
	protected int real_user_count;  //int comment '实名用户数',
	protected int invest_user_count;  //int comment '投资用户数量',
	protected Double invest_amount;    //decimal(11,2) comment '在投金额',
	protected Double pending_principal;  //decimal(11,2) comment '待回本金',
	protected Double interest;     //decimal(11,2) comment '待回利息',
	protected Double all_invest_amount;  //decimal(11,2) comment '总投资金额',
	protected Double all_behind_amout;   //decimal(11,2) comment '总留存金额',
	protected Double rest_amout;     //decimal(11,2) comment '平台站岗资金',
	protected Double all_service_charge; //decimal(11,2) comment '总手续费',
	protected Double today_pending_amout;  //decimal(11,2) comment '今日待回款金额',
	protected Double tomorrow_pending_amout; //decimal(11,2) comment '第二日待回款金额',
	protected Double third_pending_amout;  //decimal(11,2) comment '第三日待回款金额',
	protected Double fourth_pending_amout; //decimal(11,2) comment '第四日待回款金额',
	protected Double fifth_pending_amout;  //decimal(11,2) comment '第5日待回款金额',
	protected Double sixth_pending_amout;  //decimal(11,2) comment '第6日待回款金额',
	protected Double seventh_pending_amout; //decimal(11,2) comment '第7日待回款金额',
	
	public BusinessDayStat(){
		super();
	}
	
	public BusinessDayStat(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStat_date() {
		return stat_date;
	}

	public void setStat_date(String stat_date) {
		this.stat_date = stat_date;
	}

	public int getWeb_register() {
		return web_register;
	}

	public void setWeb_register(int web_register) {
		this.web_register = web_register;
	}

	public int getAndroid_register() {
		return android_register;
	}

	public void setAndroid_register(int android_register) {
		this.android_register = android_register;
	}

	public int getIos_register() {
		return ios_register;
	}

	public void setIos_register(int ios_register) {
		this.ios_register = ios_register;
	}

	public int getWap_register() {
		return wap_register;
	}

	public void setWap_register(int wap_register) {
		this.wap_register = wap_register;
	}

	public int getFirst_invest_count() {
		return first_invest_count;
	}

	public void setFirst_invest_count(int first_invest_count) {
		this.first_invest_count = first_invest_count;
	}

	public Double getWeb_recharge() {
		return web_recharge;
	}

	public void setWeb_recharge(Double web_recharge) {
		this.web_recharge = web_recharge;
	}

	public Double getAndroid_recharge() {
		return android_recharge;
	}

	public void setAndroid_recharge(Double android_recharge) {
		this.android_recharge = android_recharge;
	}

	public Double getIos_recharge() {
		return ios_recharge;
	}

	public void setIos_recharge(Double ios_recharge) {
		this.ios_recharge = ios_recharge;
	}

	public Double getWap_recharge() {
		return wap_recharge;
	}

	public void setWap_recharge(Double wap_recharge) {
		this.wap_recharge = wap_recharge;
	}

	public Double getWeb_invest_amount() {
		return web_invest_amount;
	}

	public void setWeb_invest_amount(Double web_invest_amount) {
		this.web_invest_amount = web_invest_amount;
	}

	public Double getAndroid_invest_amount() {
		return android_invest_amount;
	}

	public void setAndroid_invest_amount(Double android_invest_amount) {
		this.android_invest_amount = android_invest_amount;
	}

	public Double getIos_invest_amount() {
		return ios_invest_amount;
	}

	public void setIos_invest_amount(Double ios_invest_amount) {
		this.ios_invest_amount = ios_invest_amount;
	}

	public Double getWap_invest_amount() {
		return wap_invest_amount;
	}

	public void setWap_invest_amount(Double wap_invest_amount) {
		this.wap_invest_amount = wap_invest_amount;
	}

	public Double getWeb_withdrawals_amount() {
		return web_withdrawals_amount;
	}

	public void setWeb_withdrawals_amount(Double web_withdrawals_amount) {
		this.web_withdrawals_amount = web_withdrawals_amount;
	}

	public Double getAndroid_withdrawals_amount() {
		return android_withdrawals_amount;
	}

	public void setAndroid_withdrawals_amount(Double android_withdrawals_amount) {
		this.android_withdrawals_amount = android_withdrawals_amount;
	}

	public Double getIos_withdrawals_amount() {
		return ios_withdrawals_amount;
	}

	public void setIos_withdrawals_amount(Double ios_withdrawals_amount) {
		this.ios_withdrawals_amount = ios_withdrawals_amount;
	}

	public Double getWap_withdrawals_amount() {
		return wap_withdrawals_amount;
	}

	public void setWap_withdrawals_amount(Double wap_withdrawals_amount) {
		this.wap_withdrawals_amount = wap_withdrawals_amount;
	}

	public Double getInvest_service_charge() {
		return invest_service_charge;
	}

	public void setInvest_service_charge(Double invest_service_charge) {
		this.invest_service_charge = invest_service_charge;
	}

	public Double getWithdrawals_service_charge() {
		return withdrawals_service_charge;
	}

	public void setWithdrawals_service_charge(Double withdrawals_service_charge) {
		this.withdrawals_service_charge = withdrawals_service_charge;
	}

	public int getPending_withdrawals_count() {
		return pending_withdrawals_count;
	}

	public void setPending_withdrawals_count(int pending_withdrawals_count) {
		this.pending_withdrawals_count = pending_withdrawals_count;
	}

	public int getUser_count() {
		return user_count;
	}

	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}

	public int getReal_user_count() {
		return real_user_count;
	}

	public void setReal_user_count(int real_user_count) {
		this.real_user_count = real_user_count;
	}

	public int getInvest_user_count() {
		return invest_user_count;
	}

	public void setInvest_user_count(int invest_user_count) {
		this.invest_user_count = invest_user_count;
	}

	public Double getInvest_amount() {
		return invest_amount;
	}

	public void setInvest_amount(Double invest_amount) {
		this.invest_amount = invest_amount;
	}

	public Double getPending_principal() {
		return pending_principal;
	}

	public void setPending_principal(Double pending_principal) {
		this.pending_principal = pending_principal;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getAll_invest_amount() {
		return all_invest_amount;
	}

	public void setAll_invest_amount(Double all_invest_amount) {
		this.all_invest_amount = all_invest_amount;
	}

	public Double getAll_behind_amout() {
		return all_behind_amout;
	}

	public void setAll_behind_amout(Double all_behind_amout) {
		this.all_behind_amout = all_behind_amout;
	}

	public Double getRest_amout() {
		return rest_amout;
	}

	public void setRest_amout(Double rest_amout) {
		this.rest_amout = rest_amout;
	}

	public Double getAll_service_charge() {
		return all_service_charge;
	}

	public void setAll_service_charge(Double all_service_charge) {
		this.all_service_charge = all_service_charge;
	}

	public Double getToday_pending_amout() {
		return today_pending_amout;
	}

	public void setToday_pending_amout(Double today_pending_amout) {
		this.today_pending_amout = today_pending_amout;
	}

	public Double getTomorrow_pending_amout() {
		return tomorrow_pending_amout;
	}

	public void setTomorrow_pending_amout(Double tomorrow_pending_amout) {
		this.tomorrow_pending_amout = tomorrow_pending_amout;
	}

	public Double getThird_pending_amout() {
		return third_pending_amout;
	}

	public void setThird_pending_amout(Double third_pending_amout) {
		this.third_pending_amout = third_pending_amout;
	}

	public Double getFourth_pending_amout() {
		return fourth_pending_amout;
	}

	public void setFourth_pending_amout(Double fourth_pending_amout) {
		this.fourth_pending_amout = fourth_pending_amout;
	}

	public Double getFifth_pending_amout() {
		return fifth_pending_amout;
	}

	public void setFifth_pending_amout(Double fifth_pending_amout) {
		this.fifth_pending_amout = fifth_pending_amout;
	}

	public Double getSixth_pending_amout() {
		return sixth_pending_amout;
	}

	public void setSixth_pending_amout(Double sixth_pending_amout) {
		this.sixth_pending_amout = sixth_pending_amout;
	}

	public Double getSeventh_pending_amout() {
		return seventh_pending_amout;
	}

	public void setSeventh_pending_amout(Double seventh_pending_amout) {
		this.seventh_pending_amout = seventh_pending_amout;
	}
	
	
	
}
