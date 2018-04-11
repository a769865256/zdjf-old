package com.zdjf.service.front;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;

public interface IFrontService {

	void reloadIndexCache();
	
	void reloadFundsCache(Boolean flage,String lender_id);
	
	Page getProductPageList(Page p,Map map);

	String createHeader(HttpServletRequest request);

	User getUser(HttpServletRequest request);
	
}
