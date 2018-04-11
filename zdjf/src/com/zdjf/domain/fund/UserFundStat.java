package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户资金统计
 * @author csh
 *
 */
public class UserFundStat implements Serializable {

	private static final long serialVersionUID = 8700246491601114089L;
	
	protected Long id;
	protected Long user_id;              //int not null comment '用户id',
	protected Double invest_sum;           //decimal(20,2) not null default 0.00 comment '总投资金额',
	protected Double balance;              //decimal(20,2) not null default 0.00 comment '可用余额',
	protected Double recharged;            //decimal(20,2) not null default 0.00 comment '总充值金额',
	protected Double withdrawed;           //decimal(20,2) not null default 0.00 comment '总提现金额',
	protected Double pend_withdraw;        //decimal(20,2) not null default 0.00 comment '待提现金额（冻结资金）',
	protected Double incomed;              //decimal(20,2) not null default 0.00 comment '总收益金额',
	protected Double pend_income;          //decimal(20,2) not null default 0.00 comment '待收益金额',
	protected Double returned;             //decimal(20,2) not null default 0.00 comment '已回款金额',
	protected Double pend_return;          //decimal(20,2) not null default 0.00 comment '待回款金额',
	protected Double coin_total;          //decimal(20,2) not null default 0.00 comment '正经值总收益',
	protected Double coin_cost;            //decimal(20,2) not null default 0.00 comment '正经值总消费',
	protected Double coin_balance;         //decimal(20,2) not null default 0.00 comment '正经值余额',
	protected Double locked_money;         //decimal(20,2) not null default 0.00 comment '冻结金额',
	protected Double coin_invest;          //decimal(20,2) not null default 0.00 comment '投资贡献正经值数',
	protected int withdraw_coupons;     //int not null default 0 comment '提现券数量',
	protected Date update_time;          //datetime default NULL comment '最近更新时间',
	protected long uid;
	protected int invest_frequency;		//int(11) default NULL COMMENT '总投资次数',
	protected double hf_balance;
	

	public UserFundStat(){
		super();
	}
	
	public UserFundStat(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Double getInvest_sum() {
		return invest_sum;
	}

	public void setInvest_sum(Double invest_sum) {
		this.invest_sum = invest_sum;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getRecharged() {
		return recharged;
	}

	public void setRecharged(Double recharged) {
		this.recharged = recharged;
	}

	public Double getWithdrawed() {
		return withdrawed;
	}

	public void setWithdrawed(Double withdrawed) {
		this.withdrawed = withdrawed;
	}

	public Double getPend_withdraw() {
		return pend_withdraw;
	}

	public void setPend_withdraw(Double pend_withdraw) {
		this.pend_withdraw = pend_withdraw;
	}

	public Double getIncomed() {
		return incomed;
	}

	public void setIncomed(Double incomed) {
		this.incomed = incomed;
	}

	public Double getPend_income() {
		return pend_income;
	}

	public void setPend_income(Double pend_income) {
		this.pend_income = pend_income;
	}

	public Double getReturned() {
		return returned;
	}

	public void setReturned(Double returned) {
		this.returned = returned;
	}

	public Double getPend_return() {
		return pend_return;
	}

	public void setPend_return(Double pend_return) {
		this.pend_return = pend_return;
	}

	public Double getCoin_total() {
		return coin_total;
	}

	public void setCoin_total(Double coin_total) {
		this.coin_total = coin_total;
	}

	public Double getCoin_cost() {
		return coin_cost;
	}

	public void setCoin_cost(Double coin_cost) {
		this.coin_cost = coin_cost;
	}

	public Double getCoin_balance() {
		return coin_balance;
	}

	public void setCoin_balance(Double coin_balance) {
		this.coin_balance = coin_balance;
	}

	public Double getLocked_money() {
		return locked_money;
	}

	public void setLocked_money(Double locked_money) {
		this.locked_money = locked_money;
	}

	public Double getCoin_invest() {
		return coin_invest;
	}

	public void setCoin_invest(Double coin_invest) {
		this.coin_invest = coin_invest;
	}

	public int getWithdraw_coupons() {
		return withdraw_coupons;
	}

	public void setWithdraw_coupons(int withdraw_coupons) {
		this.withdraw_coupons = withdraw_coupons;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getInvest_frequency() {
		return invest_frequency;
	}

	public void setInvest_frequency(int invest_frequency) {
		this.invest_frequency = invest_frequency;
	}

	public double getHf_balance() {
		return hf_balance;
	}

	public void setHf_balance(double hf_balance) {
		this.hf_balance = hf_balance;
	}
	
}
