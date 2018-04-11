package com.zdjf.domain.user;

import java.util.List;

public class WithdrawCouponsVo extends WithdrawCoupons {

	private static final long serialVersionUID = 2843000174807558232L;
	public WithdrawCouponsVo(){
		super();
	}
	public WithdrawCouponsVo(Long id){
		super();
		this.id=id;
	}
	private List<WithdrawCoupons> withdrawCouponsList;
	public List<WithdrawCoupons> getWithdrawCouponsList() {
		return withdrawCouponsList;
	}
	public void setWithdrawCouponsList(List<WithdrawCoupons> withdrawCouponsList) {
		this.withdrawCouponsList = withdrawCouponsList;
	}
}
