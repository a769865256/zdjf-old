package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

public class Bank implements Serializable{

	/**
	 * 所应用的银行
	 */
	private static final long serialVersionUID = 2512849958455420934L;
	
	protected Long id;
	
	protected Date create_time;
	
	protected String remark;//备注

	protected String num;//编号
	
	protected String name;//银行名称
	
	protected Double single_max;//decimal(10,2) default NULL COMMENT '单笔交易最大值',
	
	protected Double day_max;//decimal(10,2) default NULL COMMENT '当日交易最大',

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSingle_max() {
		return single_max;
	}

	public void setSingle_max(Double single_max) {
		this.single_max = single_max;
	}

	public Double getDay_max() {
		return day_max;
	}

	public void setDay_max(Double day_max) {
		this.day_max = day_max;
	}
	
}
