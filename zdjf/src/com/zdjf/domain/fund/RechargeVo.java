package com.zdjf.domain.fund;

import java.util.List;

public class RechargeVo extends Recharge {

	private static final long serialVersionUID = 4563525023974955740L;
	private List<Recharge> rechargeList;
	public RechargeVo(){
		super();
	}
	public RechargeVo(Long id){
		super();
		this.id=id;
	}
	public List<Recharge> getRechargeList() {
		return rechargeList;
	}
	public void setRechargeList(List<Recharge> rechargeList) {
		this.rechargeList = rechargeList;
	}
	
}
