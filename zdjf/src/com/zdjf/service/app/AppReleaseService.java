package com.zdjf.service.app;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.app.IAppReleaseDao;
import com.zdjf.domain.app.AppRelease;
import com.zdjf.domain.app.AppReleaseVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class AppReleaseService implements IAppReleaseService{
	
	private IAppReleaseDao appReleaseDao;
	@Override
	public Serializable save(AppRelease appRelease) {
		return appReleaseDao.save(appRelease);
	}

	@Override
	public void delete(AppRelease appRelease) {
		appReleaseDao.delete(appRelease);
	}

	@Override
	public void update(AppRelease appRelease) {
		appReleaseDao.update(appRelease);
	}

	@Override
	public AppRelease selectForObjectById(Long id) {
		AppRelease appRelease=new AppRelease();
		appRelease.setId(id);
		return (AppRelease) appReleaseDao.selectForObject("selectAppReleaseById", appRelease);
	}

	@Override
	public void updateAppReleaseById(AppRelease appRelease) {
		appReleaseDao.update("updateAppReleaseById", appRelease);
	}

	@Override
	public void deleteById(Long id) {
		appReleaseDao.delete("deleteAppReleaseById", id);
	}

	@Override
	public Page page(Page page, AppRelease appRelease) {
		return appReleaseDao.page(page, appRelease);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(AppRelease appRelease) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(AppRelease appRelease) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(AppRelease appRelease) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(AppRelease appRelease) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<AppReleaseVo> selectForList(AppRelease appRelease) {
		return (List<AppReleaseVo>) appReleaseDao.selectForList("selectAppRelease", appRelease);
	}
	@Autowired
	public void setAdvertiseDao(
			@Qualifier("appReleaseDao")IAppReleaseDao appReleaseDao) {
		this.appReleaseDao = appReleaseDao;
	}

}
