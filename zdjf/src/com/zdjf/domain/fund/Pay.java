package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

/**
 * 支出明细
 * @author csh
 *
 */
public class Pay implements Serializable {

	private static final long serialVersionUID = -2109038572284229797L;

	protected Long id;       
	protected Long relation_id;    //int default NULL comment '关联业务id',
	protected Long user_id;      //int default NULL comment '用户ID',
	protected int pay_type;     //tinyint default NULL comment '支付方式：1余额，2网银，3混合，4:连连支付',
	protected Double total_money;    //decimal(20,2) default NULL comment '支付总金额',
	protected int status;     //tinyint default NULL comment '支付状态：1已支付  -1未支付  -2超时  -3失败  -4取消',
	protected Double real_recharged;   //decimal(20,2) default NULL comment '实际充值金额',
	protected Double available_paid;   //decimal(20,2) default NULL comment '余额支付金额',
	protected int biz_type;     //int default NULL comment '业务类型：1理财',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected Date update_time;    //datetime default NULL comment '修改时间',
	
	public Pay(){
		super();
	}
	
	public Pay(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(Long relation_id) {
		this.relation_id = relation_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public int getPay_type() {
		return pay_type;
	}

	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}

	public Double getTotal_money() {
		return total_money;
	}

	public void setTotal_money(Double total_money) {
		this.total_money = total_money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Double getReal_recharged() {
		return real_recharged;
	}

	public void setReal_recharged(Double real_recharged) {
		this.real_recharged = real_recharged;
	}

	public Double getAvailable_paid() {
		return available_paid;
	}

	public void setAvailable_paid(Double available_paid) {
		this.available_paid = available_paid;
	}

	public int getBiz_type() {
		return biz_type;
	}

	public void setBiz_type(int biz_type) {
		this.biz_type = biz_type;
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
	
	
	
}
