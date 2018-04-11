package com.zdjf.dao.user;

import java.util.Map;

import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IUserDao extends IDaoSupport {
	Page page(Page page, User user);
	Page page(Page page, Map<String, Object> map);
}