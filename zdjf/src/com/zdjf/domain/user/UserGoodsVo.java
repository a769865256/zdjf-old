package com.zdjf.domain.user;

import java.util.List;

public class UserGoodsVo extends UserGoods {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7311519131890049065L;
	public UserGoodsVo(){
		super();
	}
	public UserGoodsVo(Long id){
		super();
		this.id=id;
	}
	private List<UserGoods> userGoodsList;
	public List<UserGoods> getUserGoodsList() {
		return userGoodsList;
	}
	public void setUserGoodsList(List<UserGoods> userGoodsList) {
		this.userGoodsList = userGoodsList;
	}
}
