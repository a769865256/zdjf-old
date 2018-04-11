package com.zdjf.domain.statistics;

import java.io.Serializable;
import java.util.Date;

/*按照日统计量*/
public class DailySurvey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -402301031004956963L;
	
	protected Long id;  
	
	protected Date create_time;    //datetime default NULL comment '创建时间',
	
	protected int new_effective_investment_times;//新增有效投资次数

	protected int new_effective_investment_people;//新增有效投资人数
	
	protected Double new_first_investment;//新增首笔投资额
	
	protected int new_registrations;//新增注册量
	
	protected int new_mobile_registrations;//移动新增注册量
	
	protected int new_real_name_authentication;//新增实名认证量
	
	protected int new_registrations_investments;//新注册并投资人数
	
	protected Double new_registered_investment_rate;//新注册新增投资率
	
	protected int daily_investment;//日投资人数
	
	protected int daily_investment_times;//日投资次数
	
	protected Double daily_investment_amount;//日投资额

	protected Double daily_coupon_deduction_amoun;//日优惠券抵扣金额
	
	protected Double daily_coupon_deduction_rate;//日优惠券抵扣比例
	
	protected Double daily_per_capita_investment;//日人均投资额
	
	protected int withdrawals_times;//提现次数
	
	protected int withdrawals_people;//提现人数
	
	protected Double withdrawals_amount;//提现金额
	
	protected Double mobile_withdrawals_amount;//移动提现金额
	
	protected int recharge_times;//充值次数
	
	protected int recharge_people;//充值人数
	
	protected Double recharge_amount;//充值金额
	
	protected Double mobile_recharge_amount;//移动充值金额
	
	protected Double net_inflow_funds;//资金净流入
	
	protected int repeat_investment_people;//重复投资人数
	
	protected Double repeat_investment_amount;//重复投资均额
	
	protected Double repeat_investment_rate;//重复投资率
	
	protected Double platform_fee;//平台手续费
	
	protected int participants_lottery_people;//抽奖活动参与人数
	
	protected int total_registration;//总注册数
	
	protected int total_real_name;//总实名认证数
	
	protected Double total_investment_rate;//总投资率
	
	protected int total_effective_capital;//总有效资人数
	
	protected Double total_investment;//总投资额
	
	protected Double total_mobile_investment;//总移动投资额
	
	protected Double total_per_capita_investment;//总人均投资额
	
	protected Double sentry_funds;//站岗资金
	
	protected int total_repeat_investment_people;//总重复投资人数
	
	protected Double total_repeat_investment_rate;//总重复投资率
	
	protected int total_debt_repayment;//总还本再投人数
	
	protected int total_debt_service_interest;//总还本付息人数
	
	protected Double total_debt_service_rate;//总还本付息重投率
	
	protected int total_debt_service_people;//还本付息重投人数
	
	protected Double total_debt_service_amount;//还本付息重投金额
	
	protected Double total_debt_per_amount;//还本付息重投均额
	
	protected Double debt_return_rate;//还本重投率
	
	protected String date_while;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getNew_effective_investment_times() {
		return new_effective_investment_times;
	}

	public void setNew_effective_investment_times(int new_effective_investment_times) {
		this.new_effective_investment_times = new_effective_investment_times;
	}

	public int getNew_effective_investment_people() {
		return new_effective_investment_people;
	}

	public void setNew_effective_investment_people(int new_effective_investment_people) {
		this.new_effective_investment_people = new_effective_investment_people;
	}

	public Double getNew_first_investment() {
		return new_first_investment;
	}

	public void setNew_first_investment(Double new_first_investment) {
		this.new_first_investment = new_first_investment;
	}

	public int getNew_registrations() {
		return new_registrations;
	}

	public void setNew_registrations(int new_registrations) {
		this.new_registrations = new_registrations;
	}

	public int getNew_mobile_registrations() {
		return new_mobile_registrations;
	}

	public void setNew_mobile_registrations(int new_mobile_registrations) {
		this.new_mobile_registrations = new_mobile_registrations;
	}

	public int getNew_real_name_authentication() {
		return new_real_name_authentication;
	}

	public void setNew_real_name_authentication(int new_real_name_authentication) {
		this.new_real_name_authentication = new_real_name_authentication;
	}

	public int getNew_registrations_investments() {
		return new_registrations_investments;
	}

	public void setNew_registrations_investments(int new_registrations_investments) {
		this.new_registrations_investments = new_registrations_investments;
	}

	public Double getNew_registered_investment_rate() {
		return new_registered_investment_rate;
	}

	public void setNew_registered_investment_rate(Double new_registered_investment_rate) {
		this.new_registered_investment_rate = new_registered_investment_rate;
	}

	public int getDaily_investment() {
		return daily_investment;
	}

	public void setDaily_investment(int daily_investment) {
		this.daily_investment = daily_investment;
	}

	public int getDaily_investment_times() {
		return daily_investment_times;
	}

	public void setDaily_investment_times(int daily_investment_times) {
		this.daily_investment_times = daily_investment_times;
	}

	public Double getDaily_investment_amount() {
		return daily_investment_amount;
	}

	public void setDaily_investment_amount(Double daily_investment_amount) {
		this.daily_investment_amount = daily_investment_amount;
	}

	public Double getDaily_coupon_deduction_amoun() {
		return daily_coupon_deduction_amoun;
	}

	public void setDaily_coupon_deduction_amoun(Double daily_coupon_deduction_amoun) {
		this.daily_coupon_deduction_amoun = daily_coupon_deduction_amoun;
	}

	public Double getDaily_coupon_deduction_rate() {
		return daily_coupon_deduction_rate;
	}

	public void setDaily_coupon_deduction_rate(Double daily_coupon_deduction_rate) {
		this.daily_coupon_deduction_rate = daily_coupon_deduction_rate;
	}

	public Double getDaily_per_capita_investment() {
		return daily_per_capita_investment;
	}

	public void setDaily_per_capita_investment(Double daily_per_capita_investment) {
		this.daily_per_capita_investment = daily_per_capita_investment;
	}

	public int getWithdrawals_times() {
		return withdrawals_times;
	}

	public void setWithdrawals_times(int withdrawals_times) {
		this.withdrawals_times = withdrawals_times;
	}

	public int getWithdrawals_people() {
		return withdrawals_people;
	}

	public void setWithdrawals_people(int withdrawals_people) {
		this.withdrawals_people = withdrawals_people;
	}

	public Double getWithdrawals_amount() {
		return withdrawals_amount;
	}

	public void setWithdrawals_amount(Double withdrawals_amount) {
		this.withdrawals_amount = withdrawals_amount;
	}

	public Double getMobile_withdrawals_amount() {
		return mobile_withdrawals_amount;
	}

	public void setMobile_withdrawals_amount(Double mobile_withdrawals_amount) {
		this.mobile_withdrawals_amount = mobile_withdrawals_amount;
	}

	public int getRecharge_times() {
		return recharge_times;
	}

	public void setRecharge_times(int recharge_times) {
		this.recharge_times = recharge_times;
	}

	public int getRecharge_people() {
		return recharge_people;
	}

	public void setRecharge_people(int recharge_people) {
		this.recharge_people = recharge_people;
	}

	public Double getRecharge_amount() {
		return recharge_amount;
	}

	public void setRecharge_amount(Double recharge_amount) {
		this.recharge_amount = recharge_amount;
	}

	public Double getMobile_recharge_amount() {
		return mobile_recharge_amount;
	}

	public void setMobile_recharge_amount(Double mobile_recharge_amount) {
		this.mobile_recharge_amount = mobile_recharge_amount;
	}

	public Double getNet_inflow_funds() {
		return net_inflow_funds;
	}

	public void setNet_inflow_funds(Double net_inflow_funds) {
		this.net_inflow_funds = net_inflow_funds;
	}

	public int getRepeat_investment_people() {
		return repeat_investment_people;
	}

	public void setRepeat_investment_people(int repeat_investment_people) {
		this.repeat_investment_people = repeat_investment_people;
	}

	public Double getRepeat_investment_amount() {
		return repeat_investment_amount;
	}

	public void setRepeat_investment_amount(Double repeat_investment_amount) {
		this.repeat_investment_amount = repeat_investment_amount;
	}

	public Double getRepeat_investment_rate() {
		return repeat_investment_rate;
	}

	public void setRepeat_investment_rate(Double repeat_investment_rate) {
		this.repeat_investment_rate = repeat_investment_rate;
	}

	public Double getPlatform_fee() {
		return platform_fee;
	}

	public void setPlatform_fee(Double platform_fee) {
		this.platform_fee = platform_fee;
	}

	public int getParticipants_lottery_people() {
		return participants_lottery_people;
	}

	public void setParticipants_lottery_people(int participants_lottery_people) {
		this.participants_lottery_people = participants_lottery_people;
	}

	public int getTotal_registration() {
		return total_registration;
	}

	public void setTotal_registration(int total_registration) {
		this.total_registration = total_registration;
	}

	public int getTotal_real_name() {
		return total_real_name;
	}

	public void setTotal_real_name(int total_real_name) {
		this.total_real_name = total_real_name;
	}

	public Double getTotal_investment_rate() {
		return total_investment_rate;
	}

	public void setTotal_investment_rate(Double total_investment_rate) {
		this.total_investment_rate = total_investment_rate;
	}

	public int getTotal_effective_capital() {
		return total_effective_capital;
	}

	public void setTotal_effective_capital(int total_effective_capital) {
		this.total_effective_capital = total_effective_capital;
	}

	public Double getTotal_investment() {
		return total_investment;
	}

	public void setTotal_investment(Double total_investment) {
		this.total_investment = total_investment;
	}

	public Double getTotal_mobile_investment() {
		return total_mobile_investment;
	}

	public void setTotal_mobile_investment(Double total_mobile_investment) {
		this.total_mobile_investment = total_mobile_investment;
	}

	public Double getTotal_per_capita_investment() {
		return total_per_capita_investment;
	}

	public void setTotal_per_capita_investment(Double total_per_capita_investment) {
		this.total_per_capita_investment = total_per_capita_investment;
	}

	public Double getSentry_funds() {
		return sentry_funds;
	}

	public void setSentry_funds(Double sentry_funds) {
		this.sentry_funds = sentry_funds;
	}

	public int getTotal_repeat_investment_people() {
		return total_repeat_investment_people;
	}

	public void setTotal_repeat_investment_people(int total_repeat_investment_people) {
		this.total_repeat_investment_people = total_repeat_investment_people;
	}

	public Double getTotal_repeat_investment_rate() {
		return total_repeat_investment_rate;
	}

	public void setTotal_repeat_investment_rate(Double total_repeat_investment_rate) {
		this.total_repeat_investment_rate = total_repeat_investment_rate;
	}

	public int getTotal_debt_repayment() {
		return total_debt_repayment;
	}

	public void setTotal_debt_repayment(int total_debt_repayment) {
		this.total_debt_repayment = total_debt_repayment;
	}

	public int getTotal_debt_service_interest() {
		return total_debt_service_interest;
	}

	public void setTotal_debt_service_interest(int total_debt_service_interest) {
		this.total_debt_service_interest = total_debt_service_interest;
	}

	public Double getTotal_debt_service_rate() {
		return total_debt_service_rate;
	}

	public void setTotal_debt_service_rate(Double total_debt_service_rate) {
		this.total_debt_service_rate = total_debt_service_rate;
	}

	public int getTotal_debt_service_people() {
		return total_debt_service_people;
	}

	public void setTotal_debt_service_people(int total_debt_service_people) {
		this.total_debt_service_people = total_debt_service_people;
	}

	public Double getTotal_debt_service_amount() {
		return total_debt_service_amount;
	}

	public void setTotal_debt_service_amount(Double total_debt_service_amount) {
		this.total_debt_service_amount = total_debt_service_amount;
	}

	public Double getTotal_debt_per_amount() {
		return total_debt_per_amount;
	}

	public void setTotal_debt_per_amount(Double total_debt_per_amount) {
		this.total_debt_per_amount = total_debt_per_amount;
	}

	public Double getDebt_return_rate() {
		return debt_return_rate;
	}

	public void setDebt_return_rate(Double debt_return_rate) {
		this.debt_return_rate = debt_return_rate;
	}

	public String getDate_while() {
		return date_while;
	}

	public void setDate_while(String date_while) {
		this.date_while = date_while;
	}
	
	

}
