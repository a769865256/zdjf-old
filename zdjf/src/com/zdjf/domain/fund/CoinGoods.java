package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

public class CoinGoods implements Serializable {

	/**
	 * 正经值兑换
	 */
	private static final long serialVersionUID = 3812021548581662062L;
	
	protected Long id;
	protected String goods_name;			//default NULL COMMENT '商品名称',
	protected int coin_cost;				// default NULL COMMENT '正经值花费',
	protected int goods_type;				//tinyint(10) default NULL COMMENT '商品类型1：红包 2：加息券 3：提现券',
	protected String exchange_name;			//varchar(30) default NULL COMMENT '优惠名城',
	protected String goods_number;			//int(10) default NULL COMMENT '兑换数量',
	protected double amount;				// decimal(10,2) default NULL COMMENT '面额',
	protected int max_count;					//varchar(30) default NULL COMMENT '限制',
	protected int status;					//int(10) default NULL COMMENT '状态1：有限 2：无效',
	protected int order_num;					//int(10) default NULL COMMENT '排序',
	protected String des;					//varchar(255) default NULL COMMENT '描述',
	protected String remark;				//varchar(255) default NULL COMMENT '备注',
	protected Long relation_id;
	protected Date create_time;
	protected Date update_time;
	protected double investment_quota;
	protected int min_day;
	protected int effective_day;
	protected int use_type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public int getCoin_cost() {
		return coin_cost;
	}
	public void setCoin_cost(int coin_cost) {
		this.coin_cost = coin_cost;
	}
	public int getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(int goods_type) {
		this.goods_type = goods_type;
	}
	public String getExchange_name() {
		return exchange_name;
	}
	public void setExchange_name(String exchange_name) {
		this.exchange_name = exchange_name;
	}
	public String getGoods_number() {
		return goods_number;
	}
	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getMax_count() {
		return max_count;
	}
	public void setMax_count(int max_count) {
		this.max_count = max_count;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public Long getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(Long relation_id) {
		this.relation_id = relation_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public double getInvestment_quota() {
		return investment_quota;
	}
	public void setInvestment_quota(double investment_quota) {
		this.investment_quota = investment_quota;
	}
	public int getMin_day() {
		return min_day;
	}
	public void setMin_day(int min_day) {
		this.min_day = min_day;
	}
	public int getEffective_day() {
		return effective_day;
	}
	public void setEffective_day(int effective_day) {
		this.effective_day = effective_day;
	}
	public int getUse_type() {
		return use_type;
	}
	public void setUse_type(int use_type) {
		this.use_type = use_type;
	}
	
	
}
