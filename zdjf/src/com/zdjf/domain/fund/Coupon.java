package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

/**
 * 加息券
 * @author csh
 *
 */
public class Coupon implements Serializable {

	private static final long serialVersionUID = -3597560068751526306L;

	protected Long id;       
	protected String coupon_name;    //varchar(100) default NULL comment '加息券名称',
	protected int coupon_source;    //tinyint default NULL comment '获取方式:1.注册 2.活动',
	protected Double interest;     //decimal(10,2) default NULL comment '面值(%)',
	protected int invalid_type;   //tinyint default NULL comment '有效期类型:1.指定范围 2.指定天数',
	protected int invalid_days;   //int default NULL comment '指定天数',
	protected Date invalid_start_date; //date default NULL comment '指定开始日期',
	protected Date invalid_end_date;   //date default NULL comment '指定结束日期',
	protected int limit_count;    //int default NULL comment '限制个数: 输入-1为不限制',
	protected int use_type;     //tinyint default NULL comment '使用限制 1.全部 2.新手标 3.非新手标',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected int status;     //tinyint default NULL comment '状态:1.有效 2.无效',
	protected Double min_amount;     //decimal(11,2) default NULL comment '投资金额限制',
	protected int min_days;     //int default NULL comment '投资天数限制',
	protected String remark;     //varchar(200) default NULL comment '备注',
	protected long coupon_id;
	
	public long getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(long coupon_id) {
		this.coupon_id = coupon_id;
	}

	public Coupon(){
		super();
	}
	
	public Coupon(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public int getCoupon_source() {
		return coupon_source;
	}

	public void setCoupon_source(int coupon_source) {
		this.coupon_source = coupon_source;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public int getInvalid_type() {
		return invalid_type;
	}

	public void setInvalid_type(int invalid_type) {
		this.invalid_type = invalid_type;
	}

	public int getInvalid_days() {
		return invalid_days;
	}

	public void setInvalid_days(int invalid_days) {
		this.invalid_days = invalid_days;
	}

	public Date getInvalid_start_date() {
		return invalid_start_date;
	}

	public void setInvalid_start_date(Date invalid_start_date) {
		this.invalid_start_date = invalid_start_date;
	}

	public Date getInvalid_end_date() {
		return invalid_end_date;
	}

	public void setInvalid_end_date(Date invalid_end_date) {
		this.invalid_end_date = invalid_end_date;
	}

	public int getLimit_count() {
		return limit_count;
	}

	public void setLimit_count(int limit_count) {
		this.limit_count = limit_count;
	}

	public int getUse_type() {
		return use_type;
	}

	public void setUse_type(int use_type) {
		this.use_type = use_type;
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

	public Double getMin_amount() {
		return min_amount;
	}

	public void setMin_amount(Double min_amount) {
		this.min_amount = min_amount;
	}

	public int getMin_days() {
		return min_days;
	}

	public void setMin_days(int min_days) {
		this.min_days = min_days;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
