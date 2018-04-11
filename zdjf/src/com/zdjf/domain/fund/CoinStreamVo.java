package com.zdjf.domain.fund;

import java.util.List;

public class CoinStreamVo extends CoinStream {

	private static final long serialVersionUID = -5476185878037724425L;
	private List<CoinStream> coinStreamList;
	public  CoinStreamVo(){
		super();
	}
	public  CoinStreamVo(Long id){
		super();
		this.id=id;
	}
	public List<CoinStream> getCoinStreamList() {
		return coinStreamList;
	}
	public void setCoinStreamList(List<CoinStream> coinStreamList) {
		this.coinStreamList = coinStreamList;
	}

}
