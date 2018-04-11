package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.product.IProductBuyDao;
import com.zdjf.dao.product.IProductDynamicDao;
import com.zdjf.domain.product.ProductDynamic;
import com.zdjf.domain.product.ProductDynamicVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class ProductDynamicService implements IProductDynamicService {
	
	private IProductDynamicDao productDynamicDao;
	
	@Override
	public Serializable save(ProductDynamic productDynamic) {
		return productDynamicDao.save(productDynamic);
	}

	@Override
	public void delete(ProductDynamic productDynamic) {
		productDynamicDao.delete(productDynamic);
	}

	@Override
	public void update(ProductDynamic productDynamic) {
		productDynamicDao.update(productDynamic);
	}

	@Override
	public ProductDynamic selectForObjectById(Long id) {
		return (ProductDynamic) productDynamicDao.selectForObject("selectProductDynamicById", id);
	}

	@Override
	public void updateProductDynamicById(ProductDynamic productDynamic) {
		productDynamicDao.update("updateProductDynamicById", productDynamic);
	}

	@Override
	public void deleteById(Long id) {
		productDynamicDao.delete("deleteProductDynamicById",id);
	}

	@Override
	public List<ProductDynamicVo> selectForList(ProductDynamic productDynamic) {
		return (List<ProductDynamicVo>) productDynamicDao.selectForList("selectProductDynamic", productDynamic);
	}

	@Override
	public List<ProductDynamicVo> selectForList(Map<String, Object> map) {
		String pagename=map.get("page_name").toString();
		return (List<ProductDynamicVo>) productDynamicDao.selectForList(pagename, map);
	}
	
	@Override
	public List<Map> selectForMap(Map<String, Object> map) {
		String pagename=map.get("page_name").toString();
		return (List<Map>) productDynamicDao.selectForList(pagename, map);
	}

	@Override
	public Page page(Page page, ProductDynamic productDynamic) {
		return productDynamicDao.page(page, productDynamic);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		return productDynamicDao.page(page, map);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(ProductDynamic productDynamic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(ProductDynamic productDynamic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(ProductDynamic productDynamic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(ProductDynamic productDynamic) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Autowired
	public void setIProductDynamicDao(
			@Qualifier("productDynamicDao") IProductDynamicDao  productDynamicDao) {
		this.productDynamicDao = productDynamicDao;
	}

}
