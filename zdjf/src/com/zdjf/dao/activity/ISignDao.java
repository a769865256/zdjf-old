package com.zdjf.dao.activity;

import com.zdjf.domain.activity.Sign;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISignDao extends IDaoSupport{
	Page page(Page page, Sign sign);
}
