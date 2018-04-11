package com.zdjf.dao.fund;

import java.util.Map;

import com.zdjf.domain.fund.CoinStream;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface ICoinStreamDao extends IDaoSupport {
	Page page(Page page, CoinStream coinStream);
	Page page(Page page, Map<String,Object> map);

}
