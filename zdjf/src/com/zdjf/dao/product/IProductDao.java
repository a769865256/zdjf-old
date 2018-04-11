package com.zdjf.dao.product;

import java.util.Map;

import com.zdjf.domain.product.Product;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

//借款人
public interface IProductDao extends IDaoSupport{

	Page page(Page page, Product product);
	Page page(Page page, Map<String, Object> map);
	
	Page getProductPageList(Page p,Map map);
}
