package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户充值记录表
 * @author csh
 *
 */
public class Recharge implements Serializable {

	private static final long serialVersionUID = 8717993371438975615L;

	protected Long id;                   
	protected Long user_id;              //int default NULL comment '用户ID',
	protected String real_name;            //varchar(30) default NULL comment '真实姓名',
	protected Double money;                //decimal(20,2) default NULL comment '充值金额',
	protected int pay_type;             //tinyint default NULL comment '充值方式：1汇付-网银充值 2汇付-快捷支付',
	protected int status;               //tinyint default NULL comment '充值状态: 1成功  -1未充值 -2失败 -3超时',
	protected String bank_no;              //varchar(30) default NULL comment '银行账号',
	protected String bank_alias;           //char(10) default NULL comment '银行别名',
	protected String bank_serial_no;       //char(64) default NULL comment '银行流水号',
	protected int source;               //int default NULL comment '1 web, 2 iOS, 3 android 4 weixin',
	protected String pp_serial_no;         //varchar(100) default NULL comment 'PP流水号',
	protected Date create_time;          //datetime default NULL comment '创建时间',
	protected Date update_time;          //datetime default NULL comment '修改时间',
	protected Double fee_amt;              //decimal(20,2) default 0.00 comment '充值手续费',
	protected int req_source;           //int default 1 comment '请求来源:1:web,2:ios,3:安卓,4:微信',
	protected Long recharge_id;
	protected Long uid;
	protected String return_content;       //varchar(512) comment '支付返回报文',
	   
	public Recharge(){
		super();
	}
	
	public Recharge(Long id){
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

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public int getPay_type() {
		return pay_type;
	}

	public void setPay_type(int pay_type) {
		this.pay_type = pay_type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBank_no() {
		return bank_no;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}

	public String getBank_alias() {
		return bank_alias;
	}

	public void setBank_alias(String bank_alias) {
		this.bank_alias = bank_alias;
	}

	public String getBank_serial_no() {
		return bank_serial_no;
	}

	public void setBank_serial_no(String bank_serial_no) {
		this.bank_serial_no = bank_serial_no;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getPp_serial_no() {
		return pp_serial_no;
	}

	public void setPp_serial_no(String pp_serial_no) {
		this.pp_serial_no = pp_serial_no;
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

	public Double getFee_amt() {
		return fee_amt;
	}

	public void setFee_amt(Double fee_amt) {
		this.fee_amt = fee_amt;
	}

	public int getReq_source() {
		return req_source;
	}

	public void setReq_source(int req_source) {
		this.req_source = req_source;
	}

	public String getReturn_content() {
		return return_content;
	}

	public void setReturn_content(String return_content) {
		this.return_content = return_content;
	}

	public Long getRecharge_id() {
		return recharge_id;
	}

	public void setRecharge_id(Long recharge_id) {
		this.recharge_id = recharge_id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	
}
