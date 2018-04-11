package com.zdjf.dao.fund;

import java.util.Map;

import com.zdjf.domain.fund.Gift;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IGiftDao extends IDaoSupport {
	Page page(Page page, Gift gift);
	Page page(Page page, Map<String,Object> map);
}
