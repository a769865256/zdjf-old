package com.zdjf.dao.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.frame.dataaccess.PageQuery;
import com.zdjf.frame.dataaccess_api.AbstractDao;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.IPageQuery;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageQueryFactory;
@Service
public class ProductBuyIncomeDao extends AbstractDao implements IProductBuyIncomeDao {
	private PageQueryFactory<PageQuery> pageQueryFactory;
	@Override
	public Page page(Page page, ProductBuyIncome productBuyIncome) {
		// TODO Auto-generated method stub
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,"selectProductBuyIncomePage", "selectProductBuyIncomeCount");
		return pageQuery.select(productBuyIncome);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		// TODO Auto-generated method stub
		String pagename=(String) map.get("pagename");
		String total=(String) map.get("total");
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(page,pagename, total);
		return pageQuery.select(map);
	}
	
	public Page getProductBuyIncomePageList(Page p,Map map){
		IPageQuery pageQuery = pageQueryFactory.getPageQuery(p,"selectProductBuyIncomePageList", "selectProductBuyIncomePageListCount");
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
