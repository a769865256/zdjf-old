package com.zdjf.dao.fund;

import java.util.Map;

import com.zdjf.domain.fund.Funds;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IFundsDao extends IDaoSupport {
	Page page(Page page,Funds funds);
	Page page(Page page,Map<String, Object> map);
}
