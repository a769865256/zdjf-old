package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.fund.IFundsDao;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class FundsService implements IFundsService {
	private IFundsDao fundsDao;
	@Override
	public Serializable save(Funds funds) {
		return fundsDao.save(funds);
	}

	@Override
	public void delete(Funds funds) {
		fundsDao.delete(funds);
	}

	@Override
	public void update(Funds funds) {
		fundsDao.update(funds);
	}
	
	public List getAmountFundsList(){
		return fundsDao.selectForList("getAmountFundsList");
	}

	@Override
	public Funds selectForObjectById(Long id) {
		Funds funds=new Funds();
		funds.setId(id);
		return (Funds) fundsDao.selectForObject("selectFundsById", funds);
	}

	@Override
	public void updateFundsById(Funds funds) {
		fundsDao.update("updateFundsById", funds);
	}

	@Override
	public void deleteById(Long id) {
		fundsDao.delete("deleteFundsById", id);
	}

	@SuppressWarnings("unchecked")
	public List<FundsVo> selectForList(Funds funds) {
		return (List<FundsVo>) fundsDao.selectForList("selectFunds", funds);
	}

	@Override
	public Page page(Page page, Funds funds) {
		return fundsDao.page(page, funds);
	}
	@Override
	public Page page(Page page, Map map) {
		return fundsDao.page(page, map);
	}
	
	public Page getMyFundsList(Page p,Map map){
		map.put("pagename", "selectMyFundsList");
		map.put("total", "selectMyFundsListCount");
		return fundsDao.page(p, map);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Funds funds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Funds funds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Funds funds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Funds funds) {
		// TODO Auto-generated method stub
		return null;
	}
	@Autowired
	public void setFundsDao(
			@Qualifier("fundsDao")IFundsDao fundsDao) {
		this.fundsDao = fundsDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectForMap(Funds funds) {
		return (List<Map<String, Object>>) fundsDao.selectForList("selectFirstFunds",funds);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectForList(String sqlName) {
		return (List<Map<String, Object>>) fundsDao.selectForList(sqlName);
	}

	@Override
	public List<FundsVo> selectFundListNoRes(Long user_id) {
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put("user_id",user_id);
		return (List<FundsVo>)fundsDao.selectForList("selectFundListNoRes",hashMap);
	}
}
