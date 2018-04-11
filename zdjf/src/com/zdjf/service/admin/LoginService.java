package com.zdjf.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.admin.ISysUserDao;
import com.zdjf.domain.admin.SysUser;
import com.zdjf.frame.dataaccess_api.DaoException;

@Service
public class LoginService implements ILoginService{
	private ISysUserDao sysUserDao;
	
	public SysUser getSysUser(SysUser sysUser) throws DaoException{
		
		return (SysUser) sysUserDao.selectForObject("loginSysUser",sysUser);
	}
	
	@Autowired
	public void setSysUserDao(
			@Qualifier("sysUserDao") ISysUserDao  sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
}
