package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.fund.IGiftDao;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.GiftVo;
import com.zdjf.frame.dataaccess_api.Page;

@Service
public class GiftService implements IGiftService {
	
	private IGiftDao giftDao;
	@Override
	public Serializable save(Gift gift) {
		return giftDao.save(gift);
	}

	@Override
	public void delete(Gift gift) {
		giftDao.delete(gift);
	}

	@Override
	public void update(Gift gift) {
		giftDao.update(gift);
	}
	
	

	@Override
	public Page page(Page page, Gift gift) {
		return giftDao.page(page, gift);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Gift gift) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Gift gift) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Gift gift) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Gift gift) {
		return null;
	}

	@Override
	public Gift selectForObjectById(long id) {
		Gift gift=new Gift();
		gift.setId(id);
		return (Gift) giftDao.selectForObject("selectGiftById", gift);
	}
	@Override
	public void updateGiftById(Gift gift) {
		giftDao.update("updateGiftById", gift);
	}
	@Override
	public void deleteById(long id) {
		giftDao.delete("deleteGiftById", id);
	}
	@Autowired
	public void setIGiftDao(
			@Qualifier("giftDao") IGiftDao  giftDao) {
		this.giftDao = giftDao;
	}

	@Override
	public GiftVo load(Gift gift) {
		return (GiftVo)giftDao.reload(gift);
	}

	@SuppressWarnings("unchecked")
	public List<GiftVo> selectForList(Gift gift) {
		return (List<GiftVo>)giftDao.selectForList("selectGift",gift);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		return giftDao.page(page, map);
	}
}
