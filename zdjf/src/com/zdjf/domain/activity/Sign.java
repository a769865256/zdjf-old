package com.zdjf.domain.activity;

import java.io.Serializable;
import java.util.Date;

public class Sign implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1429485497535432570L;
	
	protected Long id;
	protected Long user_id;  //用户ID
	protected Double coin;    //正经值数量
	protected int reg_source;     //签到来源:1.web 2.ios 3.安卓
	protected String remark;     //备注
	protected String create_date;  //创建日期
	protected Date create_time;  //datetime default NULL comment '预发布时间',
	protected int status;    //datetime default NULL comment '创建时间',
	protected String phone;//手机号码
	protected int nums;//本日获取抽奖机会
	protected int used_nums;//已经用的
	
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
	public Double getCoin() {
		return coin;
	}
	public void setCoin(Double coin) {
		this.coin = coin;
	}
	public int getReg_source() {
		return reg_source;
	}
	public void setReg_source(int reg_source) {
		this.reg_source = reg_source;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public int getUsed_nums() {
		return used_nums;
	}
	public void setUsed_nums(int used_nums) {
		this.used_nums = used_nums;
	}
	
	

}
