package com.zdjf.frame.dataaccess;

import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageQueryFactory;

public class DefaultPageQueryFactory implements PageQueryFactory<PageQuery> {
	private IDaoSupport daoSupport;

	@Override
	public PageQuery getPageQuery() {
		return new PageQuery(daoSupport);
	}

	@Override
	public PageQuery getPageQuery(Page page, String queryStr, String countStr) {
		PageQuery pageQuery = new PageQuery(page, queryStr, countStr);
		pageQuery.setDaoSupport(daoSupport);
		return pageQuery;
	}

	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}
