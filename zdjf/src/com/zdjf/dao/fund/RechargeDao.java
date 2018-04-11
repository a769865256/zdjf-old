package com.zdjf.dao.fund;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.domain.fund.Recharge;
import com.zdjf.frame.dataaccess.PageQuery;
import com.zdjf.frame.dataaccess_api.AbstractDao;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.IPageQuery;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageQueryFactory;
@Service
public class RechargeDao extends AbstractDao implements IRechargeDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;

	@Override
	public Page page(Page page, Recharge recharge) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectRechargePage", "selectRechargeCount");
		return pageQuery.select(recharge);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		String pagename=(String) map.get("pagename");
		String total=(String) map.get("total");
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,pagename, total);
		return pageQuery.select(map);
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
