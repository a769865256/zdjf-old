package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.product.IProductDao;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.frame.dataaccess_api.Page;

@Service
public class ProductService implements IProductService{

	
	private IProductDao productDao;
	
	@Override
	public Serializable save(Product product) {
		return productDao.save(product);
	}

	
	@Autowired
	public void setIProductDao(
			@Qualifier("productDao") IProductDao  productDao) {
		this.productDao = productDao;
	}

	@Override
	public void delete(Product product) {
		productDao.delete(product);
	}

	@Override
	public void update(Product product) {
		productDao.update(product);
	}

	@Override
	public Product selectForObjectById(Long id) {
		return (Product) productDao.selectForObject("selectProductById", id);
	}

	@Override
	public void updateProductById(Product product) {
		productDao.update("updateProductById", product);
	}

	@Override
	public void deleteById(Long id) {
		productDao.delete("deleteProductById",id);
	}

	@SuppressWarnings("unchecked")
	public List<ProductVo> selectForList(Product product) {
		return (List<ProductVo>) productDao.selectForList("selectProduct", product);
	}

	@Override
	public Page page(Page page, Product product) {
		return productDao.page(page, product);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Product product) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List getProductList(Map map){
		return productDao.selectForList("getProductList",map);
	}


	@Override
	public Page page(Page page, Map<String, Object> map) {
		return productDao.page(page, map);
	}


	@Override
	public List<ProductVo> selectForList(Map<String, Object> map) {
		String pagename=map.get("page_name").toString();
		return (List<ProductVo>) productDao.selectForList(pagename,map);
	}

}
