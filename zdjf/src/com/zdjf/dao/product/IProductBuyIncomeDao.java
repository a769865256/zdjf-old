package com.zdjf.dao.product;

import java.util.Map;

import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductBuyIncomeDao extends IDaoSupport {
	Page page(Page page,ProductBuyIncome productBuyIncome);
	Page page(Page page, Map<String, Object> map);
}
