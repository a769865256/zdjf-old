package com.zdjf.domain.fund;

import java.util.List;

public class WithdrawVo extends Withdraw {

	private static final long serialVersionUID = -2301429113796505402L;
	public WithdrawVo(){
		super();
	}
	public WithdrawVo(Long id){
		super();
		this.id=id;
	}
	private List<Withdraw> withdrawList;
	public List<Withdraw> getWithdrawList() {
		return withdrawList;
	}
	public void setWithdrawList(List<Withdraw> withdrawList) {
		this.withdrawList = withdrawList;
	}

}
