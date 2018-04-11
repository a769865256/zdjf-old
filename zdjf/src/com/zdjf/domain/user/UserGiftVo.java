package com.zdjf.domain.user;

import java.util.List;

public class UserGiftVo extends UserGift {

	/**
	 * 
	 */
	private static final long serialVersionUID = -791736058279634273L;
	
	private List<UserGift> userGiftList;
	
	public UserGiftVo(){
		super();
	}
	public UserGiftVo(Long id){
		super();
		this.id=id;
	}
	public List<UserGift> getUserGiftList() {
		return userGiftList;
	}
	public void setUserGiftList(List<UserGift> userGiftList) {
		this.userGiftList = userGiftList;
	}

}
