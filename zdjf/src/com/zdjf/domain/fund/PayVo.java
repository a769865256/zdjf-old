package com.zdjf.domain.fund;

import java.util.List;

public class PayVo extends Pay {
	
	public PayVo(){
		super();
	}
	public PayVo(Long id){
		super();
		this.id=id;
	}
	private static final long serialVersionUID = -5424040457829238228L;
	private List<Pay> payList;
	public List<Pay> getPayList() {
		return payList;
	}
	public void setPayList(List<Pay> payList) {
		this.payList = payList;
	}
	

}
