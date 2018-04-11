package com.zdjf.dao.fund;

import java.util.Map;

import com.zdjf.domain.fund.Recharge;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IRechargeDao extends IDaoSupport {
	Page page(Page page, Recharge recharge);
	Page page(Page page, Map<String,Object> map);
}
