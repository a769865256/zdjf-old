package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.user.IUserBankDao;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class UserBankService implements IUserBankService {
	private IUserBankDao userBankDao;
	@Override
	public Serializable save(UserBank userBank) {
		return userBankDao.save(userBank);
	}

	@Override
	public void delete(UserBank userBank) {
		userBankDao.delete(userBank);
	}

	@Override
	public void update(UserBank userBank) {
		userBankDao.update(userBank);
	}

	@Override
	public UserBankVo load(UserBank userBank) {
		return (UserBankVo) userBankDao.reload(userBank);
	}

	@SuppressWarnings("unchecked")
	public List<UserBankVo> selectForList(UserBank userBank) {
		return (List<UserBankVo>) userBankDao.selectForList("selectUserBank", userBank);
	}

	@Override
	public UserBank selectForObjectById(long id) {
			UserBank userBank=new UserBank();
			userBank.setId(id);
			return (UserBank) userBankDao.selectForObject("selectUserBankById", userBank);
	}

	@Override
	public void updateUserBankById(UserBank userBank) {
		userBankDao.update("updateUserBankById", userBank);
	}

	@Override
	public void deleteById(long id) {
		userBankDao.delete("deleteUserBankById", id);
	}

	@Override
	public Page page(Page page, UserBank userBank) {
		return userBankDao.page(page, userBank);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(UserBank userBank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(UserBank userBank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(UserBank userBank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(UserBank userBank) {
		// TODO Auto-generated method stub
		return null;
	}
	public UserBank selectUserBankById(UserBank userBank){
		return (UserBank) userBankDao.selectForObject("selectUserBankById", userBank);
	}
	@Autowired
	public void setUserBankDao(
			@Qualifier("userBankDao")IUserBankDao userBankDao) {
		this.userBankDao = userBankDao;
	}
}
