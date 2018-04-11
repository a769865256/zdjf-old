package com.zdjf.dao.fund;

import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IUserFundStatDao extends IDaoSupport {
	Page page(Page page,UserFundStat  userFundStat);
}
