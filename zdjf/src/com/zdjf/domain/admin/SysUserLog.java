package com.zdjf.domain.admin;

import java.io.Serializable;
import java.util.Date;

public class SysUserLog implements Serializable{
	
	/**
	 * 管理员操作日志
	 */
	private static final long serialVersionUID = 1L;

	protected Date create_time;

	protected String content;
	
	protected Long sys_user_id;

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSys_user_id() {
		return sys_user_id;
	}

	public void setSys_user_id(Long sys_user_id) {
		this.sys_user_id = sys_user_id;
	}
	
	
}
