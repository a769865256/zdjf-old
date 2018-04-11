package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.product.IProductBuyRobDao;
import com.zdjf.domain.product.ProductBuyRob;
import com.zdjf.domain.product.ProductBuyRobVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class ProductBuyRobService implements IProductBuyRobService {

private IProductBuyRobDao productBuyRobDao;
	
	@Override
	public Serializable save(ProductBuyRob productBuyRob) {
		return productBuyRobDao.save(productBuyRob);
	}

	
	@Autowired
	public void setIProductBuyRobDao(
			@Qualifier("productBuyRobDao") IProductBuyRobDao  productBuyRobDao) {
		this.productBuyRobDao = productBuyRobDao;
	}

	@Override
	public void delete(ProductBuyRob productBuyRob) {
		productBuyRobDao.delete(productBuyRob);
	}

	@Override
	public void update(ProductBuyRob productBuyRob) {
		productBuyRobDao.update(productBuyRob);
	}

	@Override
	public ProductBuyRob selectForObjectById(Long id) {
		return (ProductBuyRob) productBuyRobDao.selectForObject("selectProductBuyRobById", id);
	}

	@Override
	public void updateProductBuyRobById(ProductBuyRob productBuyRob) {
		productBuyRobDao.update("updateProductBuyRobById", productBuyRob);
	}

	@Override
	public void deleteById(Long id) {
		productBuyRobDao.delete("deleteProductBuyRobById",id);
	}

	@SuppressWarnings("unchecked")
	public List<ProductBuyRobVo> selectForList(ProductBuyRob productBuyRob) {
		return (List<ProductBuyRobVo>) productBuyRobDao.selectForList("selectProductBuyRobBuy", productBuyRob);
	}

	@Override
	public Page page(Page page, ProductBuyRob productBuyRob) {
		return productBuyRobDao.page(page, productBuyRob);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(ProductBuyRob productBuyRob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(ProductBuyRob productBuyRob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(ProductBuyRob productBuyRob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(ProductBuyRob productBuyRob) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page page(Page page, Map<String, Object> map) {
		return productBuyRobDao.page(page, map);
	}

}
