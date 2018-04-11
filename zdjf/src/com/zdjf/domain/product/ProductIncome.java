package com.zdjf.domain.product;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品计息
 * @author csh
 *
 */
public class ProductIncome implements Serializable {

	private static final long serialVersionUID = -7346753120335617175L;

	protected Long id;
	protected Long product_id;     //int default NULL comment '项目ID',
	protected Date start_date;     //date default NULL comment '计息起始时间',
	protected Date end_date;     //date default NULL comment '计息结束时间',
	protected int days;       //int default NULL comment '计息天数',
	protected Date pay_date;     //date default NULL comment '付息日',
	protected Double income_dailly;    //decimal(10,2) default NULL comment '日利息',
	protected Double income;     //decimal(10,2) default NULL comment '总利息',
	protected Double amount;     //decimal(10,2) default NULL comment '本金',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected int status;     //int default 1 comment '状态:1.有效 2.无效',
	protected Long buy_id;
	protected Long income_id;
	protected Long old_product_id;
	public ProductIncome(){
		super();
	}
	
	public ProductIncome(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
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

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	public Double getIncome_dailly() {
		return income_dailly;
	}

	public void setIncome_dailly(Double income_dailly) {
		this.income_dailly = income_dailly;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getBuy_id() {
		return buy_id;
	}

	public void setBuy_id(Long buy_id) {
		this.buy_id = buy_id;
	}

	public Long getIncome_id() {
		return income_id;
	}

	public void setIncome_id(Long income_id) {
		this.income_id = income_id;
	}

	public Long getOld_product_id() {
		return old_product_id;
	}

	public void setOld_product_id(Long old_product_id) {
		this.old_product_id = old_product_id;
	}
	
}
