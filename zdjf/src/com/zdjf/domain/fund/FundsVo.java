package com.zdjf.domain.fund;

import java.util.List;

public class FundsVo extends Funds {

	private static final long serialVersionUID = 3730844341320315804L;
	public FundsVo(){
		super();
	}
	public FundsVo(Long id){
		super();
		this.id=id;
	}
	private List<Funds> fundsList;
	public List<Funds> getFundsList() {
		return fundsList;
	}
	public void setFundsList(List<Funds> fundsList) {
		this.fundsList = fundsList;
	}
}
