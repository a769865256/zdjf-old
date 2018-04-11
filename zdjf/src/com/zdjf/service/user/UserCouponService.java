package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.user.IUserCouponDao;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class UserCouponService implements IUserCouponService {

	private IUserCouponDao userCouponDao;
	@Autowired
	public void setIUserCouponDao(
			@Qualifier("userCouponDao")IUserCouponDao userCouponDao) {
		this.userCouponDao = userCouponDao;
	}

	@Override
	public Serializable save(UserCoupon userCoupon) {
		return userCouponDao.save(userCoupon);
	}

	@Override
	public void delete(UserCoupon userCoupon) {
		userCouponDao.delete(userCoupon);
	}

	@Override
	public void update(UserCoupon userCoupon) {
		userCouponDao.update(userCoupon);
	}

	@Override
	public UserCouponVo load(UserCoupon userCoupon) {
		return (UserCouponVo) userCouponDao.reload(userCoupon);
	}

	@SuppressWarnings("unchecked")
	public List<UserCouponVo> selectForList(UserCoupon userCoupon) {
		return (List<UserCouponVo>) userCouponDao.selectForList("selectUserCoupon", userCoupon);
	}

	@Override
	public UserCoupon selectForObjectByCouponId(UserCoupon userCoupon) {
		return (UserCoupon) userCouponDao.selectForObject("selectUserCouponByCouponId", userCoupon);
	}
	@Override
	public void updateUserCouponByCouponId(UserCoupon userCoupon) {
		userCouponDao.update("updateUserCouponByCouponId", userCoupon);
	}

	@Override
	public void deleteByCouponId(long id) {
		userCouponDao.delete("deleteByCouponId", id);
	}

	@Override
	public Page page(Page page, UserCoupon userCoupon) {
		return userCouponDao.page(page, userCoupon);
	}
	

	@Override
	public List getUserCouponList(Map prammap){
		return (List) userCouponDao.selectForList("getUserCouponList", prammap);
	}
	
	@Override
	public int selectCouponCountByUid(Long user_id){
		return (int)userCouponDao.selectForObject("selectCouponCountByUid",user_id);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(UserCoupon userCoupon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(UserCoupon userCoupon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(UserCoupon userCoupon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(UserCoupon userCoupon) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectForList(Map<String, Object> map) {
		String page_name=map.get("page_name").toString();
		return (List<Map<String, Object>>) userCouponDao.selectForList(page_name, map);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		return userCouponDao.page(page, map);
	}

	@Override
	public void updateOutOfDateUserCoupon() {
		userCouponDao.update("updateOutOfDateUserCoupon");
	}
}
