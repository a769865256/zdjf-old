package com.zdjf.dao.notice;

import com.zdjf.domain.notice.Notice;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface INoticeDao extends IDaoSupport {
	Page page(Page page,Notice notice);

}
