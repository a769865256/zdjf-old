package com.zdjf.dao.admin;

import com.zdjf.domain.admin.SysUser;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISysUserDao extends IDaoSupport{
	Page page(Page page, SysUser user);
}
