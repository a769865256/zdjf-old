package com.zdjf.webservice.express;

import java.util.HashMap;
import java.util.Map;

public class SmsReturn {

	private int status;
	private boolean returnCase;
	private Object content;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isReturnCase() {
		return returnCase;
	}
	public void setReturnCase(boolean returnCase) {
		this.returnCase = returnCase;
	}
	
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	
	public void setMapContent(Object content){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("dataList", content);
		this.content = obj;
		
	}
}
