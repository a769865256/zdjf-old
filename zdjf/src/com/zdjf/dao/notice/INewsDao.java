package com.zdjf.dao.notice;

import com.zdjf.domain.notice.News;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface INewsDao extends IDaoSupport {
	Page page(Page page,News news);
}
