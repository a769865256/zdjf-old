package com.zdjf.domain.user;

import java.util.List;

public class UserBankVo extends UserBank {

	private static final long serialVersionUID = -5343525490211980136L;
	private List<UserBank> userBankList;
	public UserBankVo(){
		super();
	}
	public UserBankVo(Long id){
		super();
		this.id=id;
	}
	public List<UserBank> getUserBankList() {
		return userBankList;
	}
	public void setUserBankList(List<UserBank> userBankList) {
		this.userBankList = userBankList;
	}
}
