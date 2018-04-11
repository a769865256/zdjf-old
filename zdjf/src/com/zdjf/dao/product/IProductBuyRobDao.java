package com.zdjf.dao.product;

import java.util.Map;

import com.zdjf.domain.product.ProductBuyRob;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductBuyRobDao extends IDaoSupport {
	Page page(Page page,ProductBuyRob productBuyRob);
	Page page(Page page, Map<String, Object> map);
}
