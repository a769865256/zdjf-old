package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.fund.ICoinGoodsDao;
import com.zdjf.domain.fund.CoinGoods;
import com.zdjf.domain.fund.CoinGoodsVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class CoinGoodsService implements ICoinGoodsService {

	private ICoinGoodsDao coinGoodsDao;
	@Override
	public Serializable save(CoinGoods coinGoods) {
		return coinGoodsDao.save(coinGoods);
	}

	@Override
	public void delete(CoinGoods coinGoods) {
		coinGoodsDao.delete(coinGoods);
	}

	@Override
	public void update(CoinGoods coinGoods) {
		coinGoodsDao.update(coinGoods);
	}

	@Override
	public CoinGoods selectForObjectById(Long id) {
		CoinGoods coinGoods=new CoinGoods();
		coinGoods.setId(id);
		return (CoinGoods) coinGoodsDao.selectForObject("selectCoinGoodsById", coinGoods);
	}

	@Override
	public void updateCoinGoodsById(CoinGoods coinGoods) {
		coinGoodsDao.update("updateCoinGoodsById", coinGoods);
	}

	@Override
	public void deleteById(Long id) {
		coinGoodsDao.delete("deleteCoinGoodsById", id);
	}

	@Override
	public Page page(Page page, CoinGoods coinGoods) {
		return coinGoodsDao.page(page, coinGoods);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(CoinGoods coinGoods) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(CoinGoods coinGoods) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(CoinGoods coinGoods) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(CoinGoods coinGoods) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<CoinGoodsVo> selectForList(CoinGoods coinGoods) {
		return (List<CoinGoodsVo>) coinGoodsDao.selectForList("selectCoinGoods", coinGoods);
	}
	@Autowired
	public void setCoinGoodsDao(
			@Qualifier("coinGoodsDao")ICoinGoodsDao coinGoodsDao) {
		this.coinGoodsDao = coinGoodsDao;
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		return coinGoodsDao.page(page, map);
	}
}
