package com.zdjf.domain.fund;

import java.util.List;

public class UserFundStatVo extends UserFundStat {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1728888362130183417L;
	private List<UserFundStat>	userFundStatList;
	public UserFundStatVo(){
		super();
	}
	public UserFundStatVo(Long id){
		super();
		this.id=id;
	}
	public List<UserFundStat> getUserFundStatList() {
		return userFundStatList;
	}
	public void setUserFundStatList(List<UserFundStat> userFundStatList) {
		this.userFundStatList = userFundStatList;
	}
}
