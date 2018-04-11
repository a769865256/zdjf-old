package com.zdjf.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.domain.user.Lender;
import com.zdjf.frame.dataaccess.PageQuery;
import com.zdjf.frame.dataaccess_api.AbstractDao;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.IPageQuery;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageQueryFactory;


@Service
public class LenderDao extends AbstractDao implements ILenderDao{

	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	@Override
	public Page page(Page page, Lender lender) {
		// TODO Auto-generated method stub
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectLenderPage", "selectLenderCount");
		return pageQuery.select(lender);
	}

	

	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport") IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	
	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}
	
}
