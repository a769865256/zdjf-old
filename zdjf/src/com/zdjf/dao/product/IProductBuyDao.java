package com.zdjf.dao.product;

import java.util.Map;

import com.zdjf.domain.product.ProductBuy;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductBuyDao extends IDaoSupport {
	Page page(Page page,ProductBuy productBuy);
	Page page(Page page, Map<String, Object> map);
}
