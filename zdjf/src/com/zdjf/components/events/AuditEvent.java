package com.zdjf.components.events;

import org.springframework.context.ApplicationEvent;

import com.zdjf.domain.user.User;

//审核事件
public class AuditEvent extends ApplicationEvent{

	/**
	 * 
	 */
	
	private User user;
	
	private static final long serialVersionUID = 2826587510479197991L;

	public AuditEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public AuditEvent(Object source,User user) {
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
