package com.zdjf.domain.fund;

import java.util.List;

public class CoinVo extends Coin {

	private static final long serialVersionUID = 6518653855096602275L;
	public CoinVo(){
		super();
	}
	public CoinVo(Long id){
		super();
		this.id=id;
	}
	private static List<Coin> coinList;
	public static List<Coin> getCoinList() {
		return coinList;
	}
	public static void setCoinList(List<Coin> coinList) {
		CoinVo.coinList = coinList;
	}

}
