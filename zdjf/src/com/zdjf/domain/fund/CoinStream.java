package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户正经值流水记录表
 * @author csh
 *
 */
public class CoinStream implements Serializable {

	private static final long serialVersionUID = 2634144473746866442L;

	protected Long id;       
	protected Long user_id;      //int default NULL comment '用户ID',
	protected Long relation_id;    //int default NULL comment '关联业务ID',
	protected int action;     //tinyint default NULL comment '类别: 1收益 2.消费',
	protected int type;       //tinyint default NULL comment '操作类型: 11.投资返利 21.投资抵扣 31.抵扣冻结 32.抵扣解冻',
	protected Double amount;     //decimal(11,2) default NULL comment '本次变动值',
	protected Double balance;      //decimal(11,2) default NULL comment '本次变动后余额',
	protected int status;     //tinyint default NULL comment '状态: 1.成功 -1.失败',
	protected String remark;     //varchar(100) default NULL comment '备注',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected Long stream_id;// bigint(20) default NULL COMMENT '原流水id',
	protected int num;//int(11) default NULL COMMENT '本次的数量',
	protected Long uid;//bigint(20) default NULL COMMENT '原用户id',
	protected Long old_relation_id;// bigint(20) default NULL COMMENT '关联业务ID',
	
	public CoinStream(){
		super();
	}
	
	public CoinStream(Long id){
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

	public Long getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(Long relation_id) {
		this.relation_id = relation_id;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
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

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Long getStream_id() {
		return stream_id;
	}

	public void setStream_id(Long stream_id) {
		this.stream_id = stream_id;
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
	
}
