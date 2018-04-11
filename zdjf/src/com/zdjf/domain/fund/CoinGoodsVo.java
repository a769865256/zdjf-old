package com.zdjf.domain.fund;

import java.util.List;

public class CoinGoodsVo extends CoinGoods {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2621628562931636264L;
	public CoinGoodsVo(){
		super();
	}
	
	public CoinGoodsVo(Long id){
		super();
		this.id=id;
	}
	private List<CoinGoods> coinGoodsList;

	public List<CoinGoods> getCoinGoodsList() {
		return coinGoodsList;
	}

	public void setCoinGoodsList(List<CoinGoods> coinGoodsList) {
		this.coinGoodsList = coinGoodsList;
	}
}
