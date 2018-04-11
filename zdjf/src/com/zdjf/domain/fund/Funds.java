package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

/**
 * 资金明细表
 * @author csh
 *
 */
public class Funds implements Serializable {

	private static final long serialVersionUID = -4546963834086115038L;
	
	protected Long id;
	protected Long user_id;              //int default NULL comment '用户ID',
	protected Long relation_id;          //int default NULL comment '关联业务ID',
	protected Double amount;               //decimal(20,2) default NULL comment '本次变动金额',
	protected Double balance;              //decimal(20,2) default NULL comment '可用余额',
	protected int action;               //tinyint default NULL comment '资金类型: 1收入 2支出',
	protected int operate_type;         //tinyint default NULL comment '操作类型：11充值 12理财本金回款 13理财收益 14加币 15理财募集 18提现撤销 19提现未通过 21提现 22理财产品支付 24减币',
	protected int status;               //tinyint default NULL comment '资金状态(1已成功 2进行中 3待审核 4银行处理中 -3审核不通过-5已撤销）',
	protected String remark;              //varchar(256) default NULL comment '备注',
	protected Date create_time;         //datetime default NULL comment '创建时间',
	protected Long uid;					//bigint(20) default NULL COMMENT '原uid',
	protected Long funds_id;			//bigint(20) default NULL COMMENT '原id',
	protected String trade_no;			//default NULL COMMENT '交易订单号',
	protected String ticket;			//varchar(64) default NULL COMMENT '短信确认',
	protected String summary;			//varchar(64) default NULL COMMENT '摘要',
	
	public Funds(){
		super();
	}
	
	public Funds(Long id){
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

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(int operate_type) {
		this.operate_type = operate_type;
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

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getFunds_id() {
		return funds_id;
	}

	public void setFunds_id(Long funds_id) {
		this.funds_id = funds_id;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}
