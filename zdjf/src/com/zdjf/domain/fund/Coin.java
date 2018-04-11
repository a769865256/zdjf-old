package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

public class Coin implements Serializable {

	private static final long serialVersionUID = -1460261383255436547L;
	
	protected Long id;
	protected String coin_name;// varchar(128) default NULL COMMENT '正经值名称',
	protected int coin_source;//tinyint(3) default NULL COMMENT '获取方式 1 注册 2 实名认证',
	protected int coin_num;//varchar(256) default NULL COMMENT '固定数量',
	protected int coin_max;//int(11) default NULL COMMENT '正经值上限',
	protected int coin_min;//int(11) default NULL COMMENT '正经值下限',
	protected int coin_multiple;//tinyint(4) default NULL COMMENT '倍数',
	protected double invite_ratio;//decimal(10,2) default NULL COMMENT '邀请比例',
	protected double deduction_ratio;//decimal(10,2) default NULL COMMENT '抵扣比例',
	protected int coin_type;//tinyint(3) default NULL COMMENT '有效期类型:1.指定范围 2.指定数量',
	protected Date create_time;//datetime default NULL COMMENT '创建时间',
	protected int status;//tinyint(4) default NULL COMMENT '状态:1.有效 2.无效',
	protected String remark;//varchar(256) default NULL COMMENT '备注',
	protected Long coin_id;//bigint(20) default NULL COMMENT '原id',
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCoin_name() {
		return coin_name;
	}
	public void setCoin_name(String coin_name) {
		this.coin_name = coin_name;
	}
	public int getCoin_source() {
		return coin_source;
	}
	public void setCoin_source(int coin_source) {
		this.coin_source = coin_source;
	}
	public int getCoin_max() {
		return coin_max;
	}
	public void setCoin_max(int coin_max) {
		this.coin_max = coin_max;
	}
	public int getCoin_min() {
		return coin_min;
	}
	public void setCoin_min(int coin_min) {
		this.coin_min = coin_min;
	}
	public int getCoin_multiple() {
		return coin_multiple;
	}
	public void setCoin_multiple(int coin_multiple) {
		this.coin_multiple = coin_multiple;
	}
	public double getInvite_ratio() {
		return invite_ratio;
	}
	public void setInvite_ratio(double invite_ratio) {
		this.invite_ratio = invite_ratio;
	}
	public double getDeduction_ratio() {
		return deduction_ratio;
	}
	public void setDeduction_ratio(double deduction_ratio) {
		this.deduction_ratio = deduction_ratio;
	}
	
	public int getCoin_type() {
		return coin_type;
	}
	public void setCoin_type(int coin_type) {
		this.coin_type = coin_type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCoin_id() {
		return coin_id;
	}
	public void setCoin_id(Long coin_id) {
		this.coin_id = coin_id;
	}
	public int getCoin_num() {
		return coin_num;
	}
	public void setCoin_num(int coin_num) {
		this.coin_num = coin_num;
	}
}
