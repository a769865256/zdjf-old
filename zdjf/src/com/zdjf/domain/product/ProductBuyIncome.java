package com.zdjf.domain.product;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户购买产品计息
 * @author csh
 *
 */
public class ProductBuyIncome implements Serializable {

	private static final long serialVersionUID = -4586167099570121504L;

	protected Long id;
	protected Long buy_id;     //int default NULL comment '购买记录ID',
	protected Long product_id;     //int default NULL comment '产品ID',
	protected int num;//期数
	protected Date start_date;     //date default NULL comment '开始时间',
	protected Date end_date;     //date default NULL comment '结束时间',
	protected int days;       //int default NULL comment '计息天数',
	protected int is_return_amount;   //tinyint default 2 comment '本次是否归还本金: 1是 2否',
	protected Double amount;     //decimal(10,2) default NULL comment '本金',
	protected Double interest;     //decimal(10,2) default NULL comment '年化收益',
	protected Double income;     //decimal(10,2) default NULL comment '利息收益',
	protected Date pay_date;     //date default NULL comment '付息日',
	protected Date act_pay_time;   //datetime default NULL comment '实际支付时间',
	protected int status;     //tinyint default NULL comment '状态: 1.已回款 -1.待回款 -2.支付失败 -3.作废',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected String remark;     //varchar(200) default NULL comment '备注',
	protected Long user_id;
	protected Long income_id;
	protected Long old_buy_id;
	protected Long old_product_id;
	
	public ProductBuyIncome(){
		super();
	}
	
	public ProductBuyIncome(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBuy_id() {
		return buy_id;
	}

	public void setBuy_id(Long buy_id) {
		this.buy_id = buy_id;
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

	public int getIs_return_amount() {
		return is_return_amount;
	}

	public void setIs_return_amount(int is_return_amount) {
		this.is_return_amount = is_return_amount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	public Date getAct_pay_time() {
		return act_pay_time;
	}

	public void setAct_pay_time(Date act_pay_time) {
		this.act_pay_time = act_pay_time;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Long getIncome_id() {
		return income_id;
	}

	public void setIncome_id(Long income_id) {
		this.income_id = income_id;
	}

	public Long getOld_buy_id() {
		return old_buy_id;
	}

	public void setOld_buy_id(Long old_buy_id) {
		this.old_buy_id = old_buy_id;
	}

	public Long getOld_product_id() {
		return old_product_id;
	}

	public void setOld_product_id(Long old_product_id) {
		this.old_product_id = old_product_id;
	}
	
	
}
