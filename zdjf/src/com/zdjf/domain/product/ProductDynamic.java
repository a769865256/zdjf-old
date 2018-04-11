package com.zdjf.domain.product;

import java.io.Serializable;
import java.util.Date;

public class ProductDynamic implements Serializable {

	private static final long serialVersionUID = -5148017164365845061L;
	protected Long id;
	protected Long product_id;
	protected String debt_code;
	protected String product_code;
	protected Long loan_id;
	protected Long lender_id;
	protected Date issure_time;
	protected int num;
	protected String pay_day;
	protected double to_pay_amount;
	protected double payed_amount;
	protected double to_pay_income;
	protected double payed_income;
	protected int status;
	protected double amount;
	protected double lender_balance;
	protected double now_to_pay_amount;
	protected double now_payed_amount;
	protected double now_to_pay_income;
	protected double now_payed_income;
	protected double day_to_pay_amount;
	protected double day_payed_amount;
	protected double day_to_pay_income;
	protected double day_payed_income;
	
	protected String trade_no;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDebt_code() {
		return debt_code;
	}
	public void setDebt_code(String debt_code) {
		this.debt_code = debt_code;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public Long getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(Long loan_id) {
		this.loan_id = loan_id;
	}
	public Long getLender_id() {
		return lender_id;
	}
	public void setLender_id(Long lender_id) {
		this.lender_id = lender_id;
	}
	public Date getIssure_time() {
		return issure_time;
	}
	public void setIssure_time(Date issure_time) {
		this.issure_time = issure_time;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPay_day() {
		return pay_day;
	}
	public void setPay_day(String pay_day) {
		this.pay_day = pay_day;
	}
	public double getTo_pay_amount() {
		return to_pay_amount;
	}
	public void setTo_pay_amount(double to_pay_amount) {
		this.to_pay_amount = to_pay_amount;
	}
	public double getPayed_amount() {
		return payed_amount;
	}
	public void setPayed_amount(double payed_amount) {
		this.payed_amount = payed_amount;
	}
	public double getTo_pay_income() {
		return to_pay_income;
	}
	public void setTo_pay_income(double to_pay_income) {
		this.to_pay_income = to_pay_income;
	}
	public double getPayed_income() {
		return payed_income;
	}
	public void setPayed_income(double payed_income) {
		this.payed_income = payed_income;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getLender_balance() {
		return lender_balance;
	}
	public void setLender_balance(double lender_balance) {
		this.lender_balance = lender_balance;
	}
	public double getNow_to_pay_amount() {
		return now_to_pay_amount;
	}
	public void setNow_to_pay_amount(double now_to_pay_amount) {
		this.now_to_pay_amount = now_to_pay_amount;
	}
	public double getNow_payed_amount() {
		return now_payed_amount;
	}
	public void setNow_payed_amount(double now_payed_amount) {
		this.now_payed_amount = now_payed_amount;
	}
	public double getNow_to_pay_income() {
		return now_to_pay_income;
	}
	public void setNow_to_pay_income(double now_to_pay_income) {
		this.now_to_pay_income = now_to_pay_income;
	}
	public double getNow_payed_income() {
		return now_payed_income;
	}
	public void setNow_payed_income(double now_payed_income) {
		this.now_payed_income = now_payed_income;
	}
	public double getDay_to_pay_amount() {
		return day_to_pay_amount;
	}
	public void setDay_to_pay_amount(double day_to_pay_amount) {
		this.day_to_pay_amount = day_to_pay_amount;
	}
	public double getDay_payed_amount() {
		return day_payed_amount;
	}
	public void setDay_payed_amount(double day_payed_amount) {
		this.day_payed_amount = day_payed_amount;
	}
	public double getDay_to_pay_income() {
		return day_to_pay_income;
	}
	public void setDay_to_pay_income(double day_to_pay_income) {
		this.day_to_pay_income = day_to_pay_income;
	}
	public double getDay_payed_income() {
		return day_payed_income;
	}
	public void setDay_payed_income(double day_payed_income) {
		this.day_payed_income = day_payed_income;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	
	
	
}
