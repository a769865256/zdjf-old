package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.user.IUserGoodsDao;
import com.zdjf.domain.user.UserGoods;
import com.zdjf.domain.user.UserGoodsVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class UserGoodsService implements IUserGoodsService {
	private IUserGoodsDao userGoodsDao;
	@Override
	public Serializable save(UserGoods userGoods) {
		return userGoodsDao.save(userGoods);
	}

	@Override
	public void delete(UserGoods userGoods) {
		userGoodsDao.delete(userGoods);
	}

	@Override
	public void update(UserGoods userGoods) {
		userGoodsDao.update(userGoods);
	}

	@Override
	public UserGoods selectForObjectById(Long id) {
		UserGoods userGoods=new UserGoods();
		userGoods.setId(id);
		return (UserGoods) userGoodsDao.selectForObject("selectUserGoodsById", userGoods);
	}

	@Override
	public void updateBankById(UserGoods userGoods) {
		userGoodsDao.update(userGoods);
	}

	@Override
	public void deleteById(Long id) {
		userGoodsDao.delete("deleteUserGoodsById", id);
	}

	@SuppressWarnings("unchecked")
	public List<UserGoodsVo> selectForList(UserGoods userGoods) {
		return (List<UserGoodsVo>) userGoodsDao.selectForList("selectUserGoods", userGoods);
	}

	@Override
	public Page page(Page page, UserGoods userGoods) {
		return userGoodsDao.page(page, userGoods);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		return userGoodsDao.page(page, map);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(UserGoods userGoods) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(UserGoods userGoods) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(UserGoods userGoods) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(UserGoods userGoods) {
		// TODO Auto-generated method stub
		return null;
	}
	@Autowired
	public void setUserGoodDao(
			@Qualifier("userGoodsDao")IUserGoodsDao userGoodsDao){
		this.userGoodsDao=userGoodsDao;
	}
}
