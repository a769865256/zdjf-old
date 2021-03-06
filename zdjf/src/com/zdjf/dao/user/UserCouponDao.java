package com.zdjf.dao.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.domain.user.UserCoupon;
import com.zdjf.frame.dataaccess.PageQuery;
import com.zdjf.frame.dataaccess_api.AbstractDao;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.IPageQuery;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageQueryFactory;
@Service
public class UserCouponDao extends AbstractDao implements IUserCouponDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	@Override
	public Page page(Page page, UserCoupon userCoupon) {
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectUserCouponPage", "selectUserCouponCount");
		return pageQuery.select(userCoupon);
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
	@Override
	public Page page(Page page, Map<String, Object> map) {
		String pagename=map.get("pagename").toString();
		String total=map.get("total").toString();
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,pagename, total);
		return pageQuery.select(map);
	}

}
