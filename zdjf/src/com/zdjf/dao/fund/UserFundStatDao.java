package com.zdjf.dao.fund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.frame.dataaccess.PageQuery;
import com.zdjf.frame.dataaccess_api.AbstractDao;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.IPageQuery;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageQueryFactory;

@Service
public class UserFundStatDao extends AbstractDao implements IUserFundStatDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	@Override
	public Page page(Page page, UserFundStat userFundStat) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectUserFundStatPage", "selectUserFundStatCount");
		return pageQuery.select(userFundStat);
	}

	@Autowired
	public void setDaoSupport(
			@Qualifier("roofDaoSupport")IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	@Autowired
	public void setPageQueryFactory(
			@Qualifier("pageQueryFactory") PageQueryFactory<PageQuery> pageQueryFactory) {
		this.pageQueryFactory = pageQueryFactory;
	}

}
