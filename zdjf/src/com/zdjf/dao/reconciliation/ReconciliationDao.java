package com.zdjf.dao.reconciliation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.domain.reconciliation.Reconciliation;
import com.zdjf.frame.dataaccess.PageQuery;
import com.zdjf.frame.dataaccess_api.AbstractDao;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.IPageQuery;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageQueryFactory;
@Service
public class ReconciliationDao extends AbstractDao implements IReconciliationDao {
private PageQueryFactory<PageQuery> pageQueryFactory;
	
	
	public Page page(Page page,Reconciliation reconciliation) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectReconciliationPage", "selectReconciliationCount");
		return pageQuery.select(reconciliation);
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
