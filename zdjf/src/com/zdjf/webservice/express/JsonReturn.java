package com.zdjf.webservice.express;

public class JsonReturn {

	private int status; //(100为正确，101为错误)
	private Object content;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	
}
