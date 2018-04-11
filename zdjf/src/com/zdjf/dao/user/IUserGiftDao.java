package com.zdjf.dao.user;

import java.util.Map;

import com.zdjf.domain.user.UserGift;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IUserGiftDao extends IDaoSupport {
	Page page(Page page, UserGift userGift);
	Page page(Page page, Map<String, Object> map);
}
