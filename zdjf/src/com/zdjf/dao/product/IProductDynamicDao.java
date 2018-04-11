package com.zdjf.dao.product;

import java.util.Map;

import com.zdjf.domain.product.ProductDynamic;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductDynamicDao extends IDaoSupport {
	Page page(Page page, ProductDynamic productDynamic);
	Page page(Page page, Map<String, Object> map);
}
