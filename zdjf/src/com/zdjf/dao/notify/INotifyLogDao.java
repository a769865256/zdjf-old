package com.zdjf.dao.notify;

import com.zdjf.domain.notify.NotifyLog;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface INotifyLogDao extends IDaoSupport{

	Page page(Page page, NotifyLog notifyLog);
	
}
