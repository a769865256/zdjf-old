package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.fund.IWithdrawDao;
import com.zdjf.domain.fund.Withdraw;
import com.zdjf.domain.fund.WithdrawVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class WithdrawService implements IWithdrawService {

	private IWithdrawDao withdrawDao;
	@Override
	public Serializable save(Withdraw withdraw) {
		return withdrawDao.save(withdraw);
	}

	@Override
	public void delete(Withdraw withdraw) {
		withdrawDao.delete(withdraw);
	}

	@Override
	public void update(Withdraw withdraw) {
		withdrawDao.update(withdraw);
	}

	@Override
	public Withdraw selectForObjectById(Long id) {
		Withdraw withdraw=new Withdraw();
		withdraw.setId(id);
		return (Withdraw) withdrawDao.selectForObject("selectWithdrawById", withdraw);
	}

	@Override
	public void updateWithdrawById(Withdraw withdraw) {
		withdrawDao.update("updateWithdrawById", withdraw);
	}

	@Override
	public void deleteById(Long id) {
		withdrawDao.delete("deleteWithdrawById", id);
	}
	@Override
	public Page page(Page page, Withdraw withdraw) {
		return withdrawDao.page(page, withdraw);
	}
	@Override
	public Page page(Page page, Map<String, Object> map) {
		return withdrawDao.page(page, map);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Withdraw withdraw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Withdraw withdraw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Withdraw withdraw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Withdraw withdraw) {
		// TODO Auto-generated method stub
		return null;
	}
	@Autowired
	public void setWithdrawDao(
			@Qualifier("withdrawDao")IWithdrawDao withdrawDao) {
		this.withdrawDao = withdrawDao;
	}

	@SuppressWarnings("unchecked")
	public List<WithdrawVo> selectForList(Withdraw withdraw) {
		return (List<WithdrawVo>) withdrawDao.selectForList("selectWithdraw", withdraw);
	}
}
