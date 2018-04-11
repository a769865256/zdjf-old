package com.zdjf.dao.user;

import com.zdjf.domain.user.Lender;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

//借款人
public interface ILenderDao extends IDaoSupport{

	Page page(Page page, Lender lender);
}
