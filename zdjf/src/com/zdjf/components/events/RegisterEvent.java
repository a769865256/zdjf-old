package com.zdjf.components.events;

import org.springframework.context.ApplicationEvent;

import com.zdjf.domain.user.User;


//注册事件
public class RegisterEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3305590472073045977L;
	
	private User user;

	public RegisterEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public RegisterEvent(Object source,User user) {
		super(source);
		// TODO Auto-generated constructor stub
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
