package com.zdjf.service.admin;

import com.zdjf.domain.admin.SysUser;
import com.zdjf.frame.dataaccess_api.DaoException;

public interface ILoginService {

	SysUser getSysUser(SysUser sysUser) throws DaoException;
	
}
