package com.zdjf.domain.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户提现券记录表
 * @author csh
 *
 */
public class WithdrawCoupons implements Serializable {

	private static final long serialVersionUID = -1324227406892464934L;

	protected Long id;
	protected Long user_id;      //int default NULL comment '用户ID',
	protected int action;     //tinyint default NULL comment '类型: 1.获取 2.消耗',
	protected int type;       //tinyint default NULL comment '操作类型: 1.兑换 -1.使用 -2.冻结 2.取消冻结',
	protected int num;      //int default NULL comment '变动数量',
	protected int balance;      //int default NULL comment '剩余张数',
	protected int relation_id;    //bigint default NULL comment '关联ID',
	protected String remark;     //varchar(256) default NULL comment '备注',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected Long old_id;			//原id
	protected Long uid;				//原用户id
	protected Long old_relation_id;	//原关联id
	
	public WithdrawCoupons(){
		super();
	}
	
	public WithdrawCoupons(Long id){
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

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(int relation_id) {
		this.relation_id = relation_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Long getOld_id() {
		return old_id;
	}

	public void setOld_id(Long old_id) {
		this.old_id = old_id;
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
