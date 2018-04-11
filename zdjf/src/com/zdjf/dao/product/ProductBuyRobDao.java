package com.zdjf.dao.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.domain.product.ProductBuyRob;
import com.zdjf.frame.dataaccess.PageQuery;
import com.zdjf.frame.dataaccess_api.AbstractDao;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.IPageQuery;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageQueryFactory;
@Service
public class ProductBuyRobDao extends AbstractDao implements IProductBuyRobDao {

private PageQueryFactory<PageQuery> pageQueryFactory;
	
	@Override
	public Page page(Page page, ProductBuyRob productBuyRob) {
		// TODO Auto-generated method stub
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProductBuyRobPage", "selectProductBuyRobCount");
		return pageQuery.select(productBuyRob);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String pagename=(String) map.get("pagename");
		String total=(String) map.get("total");
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,pagename, total);
		return pageQuery.select(map);
	}
	
	public Page getProductBuyRobPageList(Page p,Map map){
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(p,"selectProductBuyRobPageList", "selectProductBuyRobPageListCount");
		return pageQuery.select(map);
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
