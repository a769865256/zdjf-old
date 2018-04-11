package com.zdjf.dao.fund;

import com.zdjf.domain.fund.Bank;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IBankDao extends IDaoSupport {
	Page page(Page page, Bank bank);
}
