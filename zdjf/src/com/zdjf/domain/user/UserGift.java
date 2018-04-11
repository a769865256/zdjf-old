package com.zdjf.domain.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户红包使用记录
 * @author csh
 *
 */
public class UserGift implements Serializable {

	private static final long serialVersionUID = 8103947398188485341L;

	protected Long id;       
	protected Long gift_id;      //int default NULL comment '红包ID',
	protected Long user_id;      //int default NULL comment '用户ID',
	protected Double amount;     //decimal(10,2) default NULL comment '红包金额',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected Date used_time;    //datetime default NULL comment '使用时间',
	protected Date start_date;     //date default NULL comment '开始日期',
	protected Date end_date;     //date default NULL comment '结束日期',
	protected int status;     //tinyint default NULL comment '状态: 1.未使用 2.已使用 3.已过期 0.使用中',
	protected String remark;     //varchar(200) default NULL comment '备注',
	protected Long relation_id;    //int default NULL comment '关联ID',
	protected String gift_name;    //varchar(100) default '' comment '红包名称',
	protected Double max_amount;     //decimal(10,2) default NULL comment '投资金额限制',
	protected int use_type;     //tinyint default NULL comment '使用限制:1.全部 2.新手标 3.非新手标',
	protected int max_days;     //int default NULL comment '投资天数限制',
	protected Long old_id;
	

	protected Long old_gift_id;
	protected Long uid;
	protected Long old_relation_id;
	
	
	public UserGift(){
		super();
	}
	
	public UserGift(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGift_id() {
		return gift_id;
	}

	public void setGift_id(Long gift_id) {
		this.gift_id = gift_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUsed_time() {
		return used_time;
	}

	public void setUsed_time(Date used_time) {
		this.used_time = used_time;
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

	public Long getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(Long relation_id) {
		this.relation_id = relation_id;
	}

	public String getGift_name() {
		return gift_name;
	}

	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}

	public Double getMax_amount() {
		return max_amount;
	}

	public void setMax_amount(Double max_amount) {
		this.max_amount = max_amount;
	}

	public int getUse_type() {
		return use_type;
	}

	public void setUse_type(int use_type) {
		this.use_type = use_type;
	}

	public int getMax_days() {
		return max_days;
	}

	public void setMax_days(int max_days) {
		this.max_days = max_days;
	}
	public Long getOld_id() {
		return old_id;
	}

	public void setOld_id(Long old_id) {
		this.old_id = old_id;
	}

	public Long getOld_gift_id() {
		return old_gift_id;
	}

	public void setOld_gift_id(Long old_gift_id) {
		this.old_gift_id = old_gift_id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getOld_relation_id() {
		return old_relation_id;
	}

	public void setOld_relation_id(Long old_relation_id) {
		this.old_relation_id = old_relation_id;
	}	
}
