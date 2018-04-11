package com.zdjf.dao.admin;

import com.zdjf.domain.admin.BusinessDayStat;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IBusinessDayStatDao extends IDaoSupport{
	Page page(Page page, BusinessDayStat businessDayStat);
}
