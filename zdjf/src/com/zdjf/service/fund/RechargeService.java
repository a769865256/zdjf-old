package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.fund.IRechargeDao;
import com.zdjf.domain.fund.Recharge;
import com.zdjf.domain.fund.RechargeVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class RechargeService implements IRechargeService {

	private IRechargeDao rechargeDao;
	@Override
	public Serializable save(Recharge recharge) {
		return rechargeDao.save(recharge);
	}

	@Override
	public void delete(Recharge recharge) {
		rechargeDao.delete(recharge);
	}

	@Override
	public void update(Recharge recharge) {
		rechargeDao.update(recharge);
	}
	
	

	@Override
	public Page page(Page page, Recharge recharge) {
		return rechargeDao.page(page, recharge);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Recharge recharge) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Recharge recharge) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Recharge recharge) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Recharge recharge) {
		return null;
	}

	@Override
	public Recharge selectForObjectById(long id) {
		Recharge recharge=new Recharge();
		recharge.setId(id);
		return (Recharge) rechargeDao.selectForObject("selectRechargeById", recharge);
	}
	@Override
	public void updateRechargeById(Recharge recharge) {
		rechargeDao.update("updateRechargeById", recharge);
	}
	@Override
	public void deleteById(long id) {
		rechargeDao.delete("deleteRechargeById", id);
	}
	@Autowired
	public void setIRechargeDao(
			@Qualifier("rechargeDao") IRechargeDao  rechargeDao) {
		this.rechargeDao = rechargeDao;
	}

	@Override
	public RechargeVo load(Recharge recharge) {
		return (RechargeVo)rechargeDao.reload(recharge);
	}

	@SuppressWarnings("unchecked")
	public List<RechargeVo> selectForList(Recharge recharge) {
		return (List<RechargeVo>)rechargeDao.selectForList("selectRecharge",recharge);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		return rechargeDao.page(page, map);
	}

}
