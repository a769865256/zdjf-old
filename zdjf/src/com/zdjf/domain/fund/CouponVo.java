package com.zdjf.domain.fund;

import java.util.List;

public class CouponVo extends Coupon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7884652202060695553L;
	private List<Coupon> couponList;
	public CouponVo(){
		super();
	}
	public CouponVo(Long id){
		super();
		this.id=id;
	}
	public List<Coupon> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}
}
