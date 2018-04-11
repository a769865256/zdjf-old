package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.fund.ICouponDao;
import com.zdjf.domain.fund.Coupon;
import com.zdjf.domain.fund.CouponVo;
import com.zdjf.frame.dataaccess_api.Page;

@Service
public class CouponService implements ICouponService {
	private ICouponDao couponDao;
	@Override
	public Serializable save(Coupon coupon) {
		return couponDao.save(coupon);
	}

	@Override
	public void delete(Coupon coupon) {
		couponDao.delete(coupon);
	}

	@Override
	public void update(Coupon coupon) {
		couponDao.update(coupon);
	}

	@Override
	public Coupon selectForObjectById(long id) {
		Coupon coupon=new Coupon();
		coupon.setId(id);
		return (Coupon) couponDao.selectForObject("selectCouponById", coupon);
	}

	@Override
	public void updateCouponById(Coupon coupon) {
		couponDao.update("updateCouponById", coupon);
	}

	@Override
	public void deleteById(long id) {
		couponDao.delete("deleteCouponById", id);
	}
	
	@Override
	public List getUserCouponList(Map hashMap){
		return couponDao.selectForList("selectUserCouponList",hashMap);
	}

	@Override
	public Page page(Page page, Coupon coupon) {
		return couponDao.page(page, coupon);
	}
	@Override
	public Page page(Page page, Map<String, Object> map) {
		
		return couponDao.page(page, map);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Coupon coupon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Coupon coupon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Coupon coupon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Coupon coupon) {
		// TODO Auto-generated method stub
		return null;
	}
	@Autowired
	public void setICouponDao(
			@Qualifier("couponDao") ICouponDao  couponDao) {
		this.couponDao = couponDao;
	}

	@Override
	public CouponVo load(Coupon coupon) {
		return (CouponVo)couponDao.reload(coupon);
	}

	@SuppressWarnings("unchecked")
	public List<CouponVo> selectForList(Coupon coupon) {
		return (List<CouponVo>) couponDao.selectForList("selectCoupon", coupon);
	}
}
