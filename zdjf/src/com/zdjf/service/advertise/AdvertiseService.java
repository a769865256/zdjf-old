package com.zdjf.service.advertise;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.advertise.IAdvertiseDao;
import com.zdjf.domain.advertise.Advertise;
import com.zdjf.domain.advertise.AdvertiseVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class AdvertiseService implements IAdvertiseService {
	private IAdvertiseDao advertiseDao;
	@Override
	public Serializable save(Advertise advertise) {
		return advertiseDao.save(advertise);
	}

	@Override
	public void delete(Advertise advertise) {
		advertiseDao.delete(advertise);
	}

	@Override
	public void update(Advertise advertise) {
		advertiseDao.update(advertise);
	}

	@Override
	public Advertise selectForObjectById(Long id) {
		Advertise advertise=new Advertise();
		advertise.setId(id);
		return (Advertise) advertiseDao.selectForObject("selectAdvertiseById", advertise);
	}

	@Override
	public void updateAdvertiseById(Advertise advertise) {
		advertiseDao.update("updateAdvertiseById", advertise);
	}

	@Override
	public void deleteById(Long id) {
		advertiseDao.delete("deleteAdvertiseById", id);
	}

	@Override
	public Page page(Page page, Advertise advertise) {
		return advertiseDao.page(page, advertise);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Advertise advertise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Advertise advertise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Advertise advertise) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Advertise advertise) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<AdvertiseVo> selectForList(Advertise advertise) {
		return (List<AdvertiseVo>) advertiseDao.selectForList("selectAdvertise", advertise);
	}
	@Autowired
	public void setAdvertiseDao(
			@Qualifier("advertiseDao")IAdvertiseDao advertiseDao) {
		this.advertiseDao = advertiseDao;
	}

}
