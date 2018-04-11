package com.zdjf.components.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;



public class InvestmentEvent extends ApplicationEvent{

	/**
	 * 投资的事务
	 */
	private static final long serialVersionUID = 8257841459147145432L;
	
	private Long user_id;
	private Double amount;
	private String debt_code;
	
	

	public InvestmentEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public InvestmentEvent(Object source,Long user_id ,Double amount,String debt_code) {
		super(source);
		// TODO Auto-generated constructor stub
		this.user_id = user_id;
		this.debt_code = debt_code;
		this.amount = amount;
		
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDebt_code() {
		return debt_code;
	}

	public void setDebt_code(String debt_code) {
		this.debt_code = debt_code;
	}

	

}
