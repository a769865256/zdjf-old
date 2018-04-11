package com.zdjf.dao.fund;

import java.util.Map;

import com.zdjf.domain.fund.Coin;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface ICoinDao extends IDaoSupport {
	Page page(Page page, Coin coin);
	Page page(Page page, Map<String,Object> map);
}
