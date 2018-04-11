package com.zdjf.domain.user;

import java.util.List;

public class UserCouponVo extends UserCoupon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6969295161551702587L;
	private List<UserCoupon> userCouponList;
	public UserCouponVo(){
		super();
	}
	public UserCouponVo(Long id){
		super();
		this.id=id;
	}
	public List<UserCoupon> getUserCouponList() {
		return userCouponList;
	}
	public void setUserCouponList(List<UserCoupon> userCouponList) {
		this.userCouponList = userCouponList;
	}
}
