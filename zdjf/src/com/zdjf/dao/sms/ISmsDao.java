package com.zdjf.dao.sms;

import java.util.Map;

import com.zdjf.domain.sms.Sms;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISmsDao extends IDaoSupport {
	Page page(Page page, Sms sms);
	Page page(Page page, Map<String, Object>map);
}
