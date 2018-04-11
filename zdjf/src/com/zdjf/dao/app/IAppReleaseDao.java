package com.zdjf.dao.app;

import com.zdjf.domain.app.AppRelease;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IAppReleaseDao extends IDaoSupport{

	
	Page page(Page page, AppRelease appRelease);
}
