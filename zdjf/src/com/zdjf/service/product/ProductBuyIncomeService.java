package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.product.IProductBuyIncomeDao;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductBuyIncomeVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class ProductBuyIncomeService implements IProductBuyIncomeService {

private IProductBuyIncomeDao productBuyIncomeDao;
	
	@Override
	public Serializable save(ProductBuyIncome productBuyIncome) {
		return productBuyIncomeDao.save(productBuyIncome);
	}

	
	@Autowired
	public void setIProductBuyIncomeDao(
			@Qualifier("productBuyIncomeDao") IProductBuyIncomeDao  productBuyIncomeDao) {
		this.productBuyIncomeDao = productBuyIncomeDao;
	}

	@Override
	public void delete(ProductBuyIncome productBuyIncome) {
		productBuyIncomeDao.delete(productBuyIncome);
	}

	@Override
	public void update(ProductBuyIncome productBuyIncome) {
		productBuyIncomeDao.update(productBuyIncome);
	}

	@Override
	public ProductBuyIncome selectForObjectById(Long id) {
		return (ProductBuyIncome) productBuyIncomeDao.selectForObject("selectProductBuyIncomeById", id);
	}

	@Override
	public void updateProductBuyIncomeById(ProductBuyIncome productBuyIncome) {
		productBuyIncomeDao.update("updateProductBuyIncomeById", productBuyIncome);
	}

	@Override
	public void deleteById(Long id) {
		productBuyIncomeDao.delete("deleteProductBuyIncomeById",id);
	}

	@SuppressWarnings("unchecked")
	public List<ProductBuyIncomeVo> selectForList(ProductBuyIncome productBuyIncome) {
		return (List<ProductBuyIncomeVo>) productBuyIncomeDao.selectForList("selectProductBuyIncome", productBuyIncome);
	}

	@Override
	public Page page(Page page, ProductBuyIncome productBuyIncome) {
		return productBuyIncomeDao.page(page, productBuyIncome);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(ProductBuyIncome productBuyIncome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(ProductBuyIncome productBuyIncome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(ProductBuyIncome productBuyIncome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(ProductBuyIncome productBuyIncome) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page page(Page page, Map<String, Object> map) {
		return productBuyIncomeDao.page(page, map);
	}


	@Override
	public List<ProductBuyIncome> selectForList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String page_name=(String) map.get("page_name");
		return (List<ProductBuyIncome>)productBuyIncomeDao.selectForList(page_name,map);
	}

}
