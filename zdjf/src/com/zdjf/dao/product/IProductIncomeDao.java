package com.zdjf.dao.product;

import java.util.Map;

import com.zdjf.domain.product.ProductIncome;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductIncomeDao extends IDaoSupport {
	Page page(Page page,ProductIncome productIncome);
	Page page(Page page, Map<String, Object> map);
}
