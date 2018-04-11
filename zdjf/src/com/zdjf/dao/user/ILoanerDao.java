package com.zdjf.dao.user;

import com.zdjf.domain.user.Loaner;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

//借款人
public interface ILoanerDao extends IDaoSupport{

	Page page(Page page, Loaner loaner);
}
