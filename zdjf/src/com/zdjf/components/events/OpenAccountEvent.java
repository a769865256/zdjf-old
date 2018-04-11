package com.zdjf.components.events;

import org.springframework.context.ApplicationEvent;

import com.zdjf.domain.user.User;

public class OpenAccountEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7232619142852515896L;
	
	
	private User user;
	
	private String ip;

	public OpenAccountEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public OpenAccountEvent(Object source,User user,String ip) {
		super(source);
		// TODO Auto-generated constructor stub
		
		this.user = user;
		
		this.ip = ip;
	}

	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
