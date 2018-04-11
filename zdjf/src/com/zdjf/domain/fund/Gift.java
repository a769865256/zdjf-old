package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

/**
 * 红包详情表
 * @author csh
 *
 */
public class Gift implements Serializable {

	private static final long serialVersionUID = 7317460767692882189L;

	protected Long id;
	protected String gift_name;    //varchar(100) default NULL comment '红包名称',
	protected int gift_source;    //tinyint default NULL comment '红包来源: 1.注册红包',
	protected Double money;      //decimal(10,2) default NULL comment '红包金额',
	protected int status;     //tinyint default NULL comment '红包状态: 1.有效 2.无效',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected int max_count;    //int default NULL comment '限制个数: 输入-1为不限制',
	protected int date_type;    //tinyint default NULL comment '有效期: 1.指定日期 2.指定天数',
	protected Date start_date;     //date default NULL comment '开始日期',
	protected Date end_date;     //date default NULL comment '结束日期',
	protected int gift_days;    //int default NULL comment '红包天数',
	protected Double max_amount;     //decimal(10,2) default 0.00 comment '投资金额限制',
	protected int max_days;     //int default 0 comment '投资天数限制',
	protected int use_type;     //tinyint default NULL comment '使用限制:1.全部 2.新手标 3.非新手标',
	protected String remark;     //varchar(200) default NULL comment '备注',
	protected Long old_gift_id;   //bigint(20)  default NULL
	

	public Gift(){
		super();
	}
	
	public Gift(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGift_name() {
		return gift_name;
	}

	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}

	public int getGift_source() {
		return gift_source;
	}

	public void setGift_source(int gift_source) {
		this.gift_source = gift_source;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getMax_count() {
		return max_count;
	}

	public void setMax_count(int max_count) {
		this.max_count = max_count;
	}

	public int getDate_type() {
		return date_type;
	}

	public void setDate_type(int date_type) {
		this.date_type = date_type;
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

	public int getGift_days() {
		return gift_days;
	}

	public void setGift_days(int gift_days) {
		this.gift_days = gift_days;
	}

	public Double getMax_amount() {
		return max_amount;
	}

	public void setMax_amount(Double max_amount) {
		this.max_amount = max_amount;
	}

	public int getMax_days() {
		return max_days;
	}

	public void setMax_days(int max_days) {
		this.max_days = max_days;
	}

	public int getUse_type() {
		return use_type;
	}

	public void setUse_type(int use_type) {
		this.use_type = use_type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getOld_gift_id() {
		return old_gift_id;
	}

	public void setOld_gift_id(Long old_gift_id) {
		this.old_gift_id = old_gift_id;
	}
	
	
}
