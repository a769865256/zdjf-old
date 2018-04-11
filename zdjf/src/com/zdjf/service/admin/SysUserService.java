package com.zdjf.service.admin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.admin.IBusinessDayStatDao;
import com.zdjf.dao.admin.ISysUserDao;
import com.zdjf.domain.admin.BusinessDayStat;
import com.zdjf.domain.admin.SysUser;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class SysUserService implements ISysUserService{
	
	private ISysUserDao sysUserDao;
	
	private IBusinessDayStatDao businessDayStatDao;

	public Serializable save(SysUser sysUser){
		return sysUserDao.save(sysUser);
	}

	public void delete(SysUser sysUser){
		sysUserDao.delete(sysUser);
	}
	
	public void deleteByExample(SysUser sysUser){
		sysUserDao.deleteByExample(sysUser);
	}

	public void update(SysUser sysUser){
		sysUserDao.update(sysUser);
	}
	
	public void updateIgnoreNull(SysUser sysUser){
		sysUserDao.updateIgnoreNull(sysUser);
	}
		
	public void updateByExample(SysUser sysUser){
		sysUserDao.update("updateByExampleSysUser", sysUser);
	}

	public SysUser load(SysUser sysUser){
		return (SysUser)sysUserDao.reload(sysUser);
	}
	
	public SysUser loginSysUser(SysUser sysUser){
		
		Object user = sysUserDao.selectForObject("loginSysUser",sysUser);
		
		return (SysUser)user;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUser> selectForList(SysUser sysUser){
		return (List<SysUser>)sysUserDao.selectForList("selectSysUser",sysUser);
	}
	
	public BusinessDayStat getBusinessDayStat(BusinessDayStat businessDayStat){
		
		return (BusinessDayStat) businessDayStatDao.selectForObject("selectBusinessDayStat",businessDayStat);
	}
	
	public Page page(Page page, SysUser sysUser) {
		return sysUserDao.page(page, sysUser);
	}
	
	@Override
	public Map<Object, Object> selectForMapMonth(SysUser sysUser) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(SysUser sysUser) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(SysUser sysUser) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(SysUser sysUser) {
		return null;
	}
	
	@Autowired
	public void setSysUserDao(
			@Qualifier("sysUserDao") ISysUserDao  sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	
	@Autowired
	public void setBusinessDayStatDao(
			@Qualifier("businessDayStatDao") IBusinessDayStatDao  businessDayStatDao) {
		this.businessDayStatDao = businessDayStatDao;
	}

}
