package com.zdjf.dao.user;

import com.zdjf.domain.user.UserBank;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IUserBankDao extends IDaoSupport {
	Page page(Page page, UserBank userBank);
}
