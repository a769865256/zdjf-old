package com.zdjf.domain.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户加息券
 * @author csh
 *
 */
public class UserCoupon implements Serializable {

	private static final long serialVersionUID = 2259238223555511093L;

	protected Long id;
	protected Long user_id;      //int default NULL comment '用户ID',
	protected Long coupon_id;    //int default NULL comment '加息券ID',
	protected Double interest;     //decimal(11,2) default NULL comment '面值',
	protected Date start_date;     //date default NULL comment '开始日期',
	protected Date end_date;     //date default NULL comment '结束日期',
	protected Date create_time;    //datetime default NULL comment '领取时间',
	protected Date use_time;     //datetime default NULL comment '使用时间',
	protected int status;     //tinyint default NULL comment '状态: 1.未使用 2.已使用 3.已过期  0.使用中',
	protected String remark;     //varchar(200) default '备注',
	protected String coupon_name;    //varchar(100) default NULL comment '加息券名称',
	protected int use_type;     //tinyint default NULL comment '使用限制 1.全部 2.新手标 3.非新手标',
	protected Double min_amount;     //decimal(11,2) default NULL comment '投资金额限制',
	protected int min_days;     //int default NULL comment '投资天数限制',
	protected long old_id;
	

	protected long uid;
	protected long old_coupon_id;
	
	public UserCoupon(){
		super();
	}
	
	public UserCoupon(Long id){
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

	public Long getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Long coupon_id) {
		this.coupon_id = coupon_id;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUse_time() {
		return use_time;
	}

	public void setUse_time(Date use_time) {
		this.use_time = use_time;
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

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public int getUse_type() {
		return use_type;
	}

	public void setUse_type(int use_type) {
		this.use_type = use_type;
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
	public long getOld_id() {
		return old_id;
	}

	public void setOld_id(long old_id) {
		this.old_id = old_id;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public long getOld_coupon_id() {
		return old_coupon_id;
	}

	public void setOld_coupon_id(long old_coupon_id) {
		this.old_coupon_id = old_coupon_id;
	}
	
}
