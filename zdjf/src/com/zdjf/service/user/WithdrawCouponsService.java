package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.user.IWithdrawCouponsDao;
import com.zdjf.domain.user.WithdrawCoupons;
import com.zdjf.domain.user.WithdrawCouponsVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class WithdrawCouponsService implements IWithdrawCouponsService {

	private IWithdrawCouponsDao withdrawCouponsDao;
	@Override
	public Serializable save(WithdrawCoupons withdrawCoupons) {
		return withdrawCouponsDao.save(withdrawCoupons);
	}

	@Override
	public void delete(WithdrawCoupons withdrawCoupons) {
		withdrawCouponsDao.delete(withdrawCoupons);
	}

	@Override
	public void update(WithdrawCoupons withdrawCoupons) {
		withdrawCouponsDao.update(withdrawCoupons);
	}

	@Override
	public WithdrawCoupons selectForObjectById(Long id) {
		WithdrawCoupons withdrawCoupons=new WithdrawCoupons();
		withdrawCoupons.setId(id);
		return (WithdrawCoupons) withdrawCouponsDao.selectForObject("selectWithdrawCouponsById", withdrawCoupons);
	}

	@Override
	public void updateWithdrawCouponsById(WithdrawCoupons withdrawCoupons) {
		withdrawCouponsDao.update("updateWithdrawCouponsById", withdrawCoupons);
	}

	@Override
	public void deleteById(Long id) {
		withdrawCouponsDao.delete("deleteWithdrawCouponsById", id);
	}

	@Override
	public Page page(Page page, WithdrawCoupons withdrawCoupons) {
		return withdrawCouponsDao.page(page, withdrawCoupons);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(WithdrawCoupons withdrawCoupons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(WithdrawCoupons withdrawCoupons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(WithdrawCoupons withdrawCoupons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(WithdrawCoupons withdrawCoupons) {
		// TODO Auto-generated method stub
		return null;
	}
	@Autowired
	public void setWithdrawCouponsDao(
			@Qualifier("withdrawCouponsDao")IWithdrawCouponsDao withdrawCouponsDao) {
		this.withdrawCouponsDao = withdrawCouponsDao;
	}

	@SuppressWarnings("unchecked")
	public List<WithdrawCouponsVo> selectForList(WithdrawCoupons withdrawCoupons) {
		return (List<WithdrawCouponsVo>) withdrawCouponsDao.selectForList("selectWithdrawCoupons", withdrawCoupons);
	}
}
