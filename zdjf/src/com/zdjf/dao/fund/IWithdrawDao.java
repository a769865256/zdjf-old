package com.zdjf.dao.fund;

import java.util.Map;

import com.zdjf.domain.fund.Withdraw;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IWithdrawDao extends IDaoSupport {
	Page page(Page page,Withdraw withdraw);
	Page page(Page page,Map<String, Object> map);
}
