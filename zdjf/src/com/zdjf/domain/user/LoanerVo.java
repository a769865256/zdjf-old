package com.zdjf.domain.user;

import java.util.List;

public class LoanerVo extends Loaner {

	private static final long serialVersionUID = 4575424977776765747L;
	public LoanerVo(){
		super();
	}
	public LoanerVo(Long id){
		super();
		this.id=id;
	}
	private List<Loaner> loanerList;
	public List<Loaner> getLoanerList() {
		return loanerList;
	}
	public void setLoanerList(List<Loaner> loanerList) {
		this.loanerList = loanerList;
	}
}
