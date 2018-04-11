package com.zdjf.dao.advertise;

import com.zdjf.domain.advertise.Advertise;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IAdvertiseDao extends IDaoSupport {
	Page page(Page page,Advertise advertise);
	
	
}
