package com.zdjf.dao.fund;

import java.util.Map;

import com.zdjf.domain.fund.Pay;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IPayDao extends IDaoSupport {
	Page page(Page page,Pay pay);
	Page page(Page page,Map<String, Object> map);
}
