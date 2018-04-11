package com.zdjf.domain.reconciliation;

import java.io.Serializable;
import java.util.Date;

/**
 * 对账
 * @author csh
 *
 */
public class Reconciliation implements Serializable {

	private static final long serialVersionUID = 3375577720131340613L;

	protected Long id;
	protected String order_date;     //varchar(8) default NULL comment '订单日期',
	protected String mer_cust_id;    //varchar(16) default NULL comment '商户客户号',
	protected String invest_cust_id;   //varchar(16) default NULL comment '投资人客户号',
	protected String borr_cust_id;   //varchar(16) default NULL comment '借款人客户号',
	protected String trans_amt;    //varchar(14) default NULL comment '交易金额',
	protected String trans_stat;     //varchar(2) default NULL comment '汇付交易状态 I--初始 P--部分成功',
	protected String pnr_date;     //varchar(8) default NULL comment '汇付交易日期',
	protected String pnr_seq_id;     //varchar(10) default NULL comment '汇付交易流水',
	protected String trans_type;     //varchar(20) default NULL comment '交易查询类型 LOANS：放款 REPAYMENT：还款',
	protected Double hf_amt;     //decimal(14,2) default NULL comment '平台交易金额',
	protected int hf_status;    //tinyint default NULL comment '平台交易状态',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected String pro_status;     //varchar(255) default '0' comment '平台处理状态：0未处理，1已处理',
	protected Long order_id;
	public Reconciliation(){
		super();
	}
	
	public Reconciliation(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getMer_cust_id() {
		return mer_cust_id;
	}

	public void setMer_cust_id(String mer_cust_id) {
		this.mer_cust_id = mer_cust_id;
	}

	public String getInvest_cust_id() {
		return invest_cust_id;
	}

	public void setInvest_cust_id(String invest_cust_id) {
		this.invest_cust_id = invest_cust_id;
	}

	public String getBorr_cust_id() {
		return borr_cust_id;
	}

	public void setBorr_cust_id(String borr_cust_id) {
		this.borr_cust_id = borr_cust_id;
	}

	public String getTrans_amt() {
		return trans_amt;
	}

	public void setTrans_amt(String trans_amt) {
		this.trans_amt = trans_amt;
	}

	public String getTrans_stat() {
		return trans_stat;
	}

	public void setTrans_stat(String trans_stat) {
		this.trans_stat = trans_stat;
	}

	public String getPnr_date() {
		return pnr_date;
	}

	public void setPnr_date(String pnr_date) {
		this.pnr_date = pnr_date;
	}

	public String getPnr_seq_id() {
		return pnr_seq_id;
	}

	public void setPnr_seq_id(String pnr_seq_id) {
		this.pnr_seq_id = pnr_seq_id;
	}

	public String getTrans_type() {
		return trans_type;
	}

	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}

	public Double getHf_amt() {
		return hf_amt;
	}

	public void setHf_amt(Double hf_amt) {
		this.hf_amt = hf_amt;
	}

	public int getHf_status() {
		return hf_status;
	}

	public void setHf_status(int hf_status) {
		this.hf_status = hf_status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getPro_status() {
		return pro_status;
	}

	public void setPro_status(String pro_status) {
		this.pro_status = pro_status;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
}
