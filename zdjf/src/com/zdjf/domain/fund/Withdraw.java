package com.zdjf.domain.fund;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户提现表
 * @author csh
 *
 */
public class Withdraw implements Serializable {

	private static final long serialVersionUID = -7555488114124416153L;
	
	protected Long id;
	protected Long user_id;              //int default NULL comment '用户ID',
	protected Double money;                //decimal(20,2) default NULL comment '提现金额',
	protected String real_name;            //varchar(15) default NULL comment '真实姓名',
	protected String bank_no;              //varchar(30) default NULL comment '用户指定的提现卡卡号',
	protected int status;               //tinyint default NULL comment '提现状态：1 成功 2 已申请 3 待复核 4 待汇付反馈 5银行处理中 -5 已撤销 -1 失败 -2 申请失败 -3平台复核不通过 -4汇付复核不通过',
	protected String remark;               //varchar(255) default NULL comment '备注',
	protected String yeepay_batchno;       //varchar(15) default NULL comment '易宝支付批号',
	protected String bank_serial_no;       //char(64) default NULL comment '银行流水号【财务人员打款后将流水号记录下来】',
	protected String pp_serial_no;         //varchar(100) default NULL comment '第三方交易流水号',
	protected Date create_time;          //datetime default NULL comment '创建时间',
	protected Date update_time;          //datetime default NULL comment '修改时间',
	protected String resp_desc;            //varchar(255) default NULL comment '应答描述',
	protected String resp_code;            //varchar(5) default NULL comment '应答编码',
	protected String fee_amt;              //varchar(14) default NULL comment '手续费金额',
	protected String fee_cust_id;          //varchar(16) default NULL comment '手续费扣款客户号',
	protected String fee_acct_id;          //varchar(9) default NULL comment '手续费扣款子账户号',
	protected int req_source;           //int default 1 comment '请求来源:1:web,2:ios,3:安卓,4:微信',
	protected int fee_obj;              //int default 1 comment '取现手续费收取方向1平台,0客户',
	protected Double real_trans_amt;       //decimal(20,2) default 0.00 comment '实际到账金额',
	protected Date delay_time;           //datetime default NULL comment '延迟审核时间（为空时不延迟）',
	protected int is_use_coupon;        //tinyint default -1 comment '是否使用免手续费券:1.使用 -1.不适用',
	protected Long wd_id;				//bigint(20) default NULL COMMENT '原提现ID',
	protected Long uid;					//bigint(20) default NULL COMMENT '原uid',

	public Withdraw(){
		super();
	}
	
	public Withdraw(Long id){
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getBank_no() {
		return bank_no;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
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

	public String getYeepay_batchno() {
		return yeepay_batchno;
	}

	public void setYeepay_batchno(String yeepay_batchno) {
		this.yeepay_batchno = yeepay_batchno;
	}

	public String getBank_serial_no() {
		return bank_serial_no;
	}

	public void setBank_serial_no(String bank_serial_no) {
		this.bank_serial_no = bank_serial_no;
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

	public String getResp_desc() {
		return resp_desc;
	}

	public void setResp_desc(String resp_desc) {
		this.resp_desc = resp_desc;
	}

	public String getResp_code() {
		return resp_code;
	}

	public void setResp_code(String resp_code) {
		this.resp_code = resp_code;
	}

	public String getFee_amt() {
		return fee_amt;
	}

	public void setFee_amt(String fee_amt) {
		this.fee_amt = fee_amt;
	}

	public String getFee_cust_id() {
		return fee_cust_id;
	}

	public void setFee_cust_id(String fee_cust_id) {
		this.fee_cust_id = fee_cust_id;
	}

	public String getFee_acct_id() {
		return fee_acct_id;
	}

	public void setFee_acct_id(String fee_acct_id) {
		this.fee_acct_id = fee_acct_id;
	}

	public int getReq_source() {
		return req_source;
	}

	public void setReq_source(int req_source) {
		this.req_source = req_source;
	}

	public int getFee_obj() {
		return fee_obj;
	}

	public void setFee_obj(int fee_obj) {
		this.fee_obj = fee_obj;
	}

	public Double getReal_trans_amt() {
		return real_trans_amt;
	}

	public void setReal_trans_amt(Double real_trans_amt) {
		this.real_trans_amt = real_trans_amt;
	}

	public Date getDelay_time() {
		return delay_time;
	}

	public void setDelay_time(Date delay_time) {
		this.delay_time = delay_time;
	}

	public int getIs_use_coupon() {
		return is_use_coupon;
	}

	public void setIs_use_coupon(int is_use_coupon) {
		this.is_use_coupon = is_use_coupon;
	}
	
	
	
}
