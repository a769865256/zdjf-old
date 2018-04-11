package com.zdjf.dao.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.domain.file.Guarantee;
import com.zdjf.frame.dataaccess.PageQuery;
import com.zdjf.frame.dataaccess_api.AbstractDao;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.IPageQuery;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageQueryFactory;


@Service
public class GuaranteeDao extends AbstractDao implements IGuaranteeDao{

	
	private PageQueryFactory<PageQuery> pageQueryFactory;
	
	@Override
	public Page page(Page page, Guarantee guarantee) {
		// TODO Auto-generated method stub
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectloanerPage", "selectloanerCount");
		return pageQuery.select(guarantee);
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
