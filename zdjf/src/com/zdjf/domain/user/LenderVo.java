package com.zdjf.domain.user;

import java.util.List;

public class LenderVo extends Lender {

	private static final long serialVersionUID = 3650803114127792085L;
	public LenderVo(){
		super();
	}
	public LenderVo(Long id){
		super();
		this.id=id;
	}
	private List<Lender> lenderList;
	public List<Lender> getLenderList() {
		return lenderList;
	}
	public void setLenderList(List<Lender> lenderList) {
		this.lenderList = lenderList;
	}
}
