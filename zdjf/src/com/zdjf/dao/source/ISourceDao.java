package com.zdjf.dao.source;

import com.zdjf.domain.source.Source;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISourceDao extends IDaoSupport {
	Page page(Page page,Source source);
}
