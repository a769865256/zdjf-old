package com.zdjf.dao.user;

import java.util.Map;

import com.zdjf.domain.user.UserGoods;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IUserGoodsDao extends IDaoSupport {
	Page page(Page page, UserGoods userGoods);
	Page page(Page page, Map<String, Object> map);
}
