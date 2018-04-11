package com.zdjf.domain.fund;

import java.util.List;

public class BankVo extends Bank {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6487907263020085540L;
	public BankVo(){
		super();
	}
	public BankVo(Long id){
		super();
		this.id=id;
	}
	private static List<Bank> bankList;
	public static List<Bank> getBankList() {
		return bankList;
	}
	public static void setBankList(List<Bank> bankList) {
		BankVo.bankList = bankList;
	}

}
