package com.zdjf.domain.notify;

import java.io.Serializable;

/**
 * 异步调用日志记录表
 * @author csh
 *
 */
public class NotifyLog implements Serializable{

	private static final long serialVersionUID = 1471021831570924353L;

	protected Long id;
	
	protected String service_name;  //接口名称
	
	protected String return_content;  //返回内容
	
	protected String status;      //状态
	
	protected String notify_time;  //返回时间
	
	public NotifyLog() {
		super();
	}

	public NotifyLog(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getReturn_content() {
		return return_content;
	}

	public void setReturn_content(String return_content) {
		this.return_content = return_content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}
	
	
}
