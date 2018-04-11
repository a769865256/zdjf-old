package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.fund.ICoinDao;
import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class CoinService implements ICoinService {
	
	private ICoinDao coinDao;
	

	@Override
	public Serializable save(Coin coin) {
		return coinDao.save(coin);
	}

	@Override
	public void delete(Coin coin) {
		coinDao.delete(coin);
	}

	@Override
	public void update(Coin coin) {
		coinDao.update(coin);
	}

	@Override
	public CoinVo load(Coin coin) {
		return (CoinVo) coinDao.reload(coin);
	}

	@SuppressWarnings("unchecked")
	public List<CoinVo> selectForList(Coin coin) {
		return (List<CoinVo>) coinDao.selectForList("selectCoin", coin);
	}

	@Override
	public Coin selectForObjectById(long id) {
		Coin coin=new Coin();
		coin.setId(id);
		return (Coin) coinDao.selectForObject("selectCoinById", coin);
	}

	@Override
	public void updateCoinById(Coin coin) {
		coinDao.update("updateCoinById", coin);
	}

	@Override
	public void deleteById(long id) {
		coinDao.delete("deleteCoinById", id);
	}

	@Override
	public Page page(Page page, Coin coin) {
		return coinDao.page(page, coin);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Coin coin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Coin coin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Coin coin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Coin coin) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Autowired
	public void setCoinDao(
			@Qualifier("coinDao")ICoinDao coinDao) {
		this.coinDao = coinDao;
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		
		return coinDao.page(page, map);
	}

}
