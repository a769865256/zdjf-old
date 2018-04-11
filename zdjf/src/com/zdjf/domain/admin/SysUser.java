package com.zdjf.domain.admin;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SysUser implements Serializable{
	
	/**
	 * 后台管理员
	 */
	private static final long serialVersionUID = 735789358891367809L;

	protected Long id;
	
	protected String login_name;
	
	protected String password;
	
	protected String real_name;
	
	protected int status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date last_login_time;
	
	protected String last_login_ip;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date create_time;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date update_time;
	
	protected  String  jurisdiction;
	
	protected int error_count;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date error_time;
	
	protected int rose; 

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
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

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public int getError_count() {
		return error_count;
	}

	public void setError_count(int error_count) {
		this.error_count = error_count;
	}

	public Date getError_time() {
		return error_time;
	}

	public void setError_time(Date error_time) {
		this.error_time = error_time;
	}

	public int getRose() {
		return rose;
	}

	public void setRose(int rose) {
		this.rose = rose;
	}

}
