package com.zdjf.service.product;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.product.IProductIncomeDao;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductIncome;
import com.zdjf.domain.product.ProductIncomeVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class ProductIncomeService implements IProductIncomeService {

private IProductIncomeDao productIncomeDao;

	@Override
	public Serializable save(ProductIncome productIncome) {
		return productIncomeDao.save(productIncome);
	}

	
	@Autowired
	public void setIProductIncomeDao(
			@Qualifier("productIncomeDao") IProductIncomeDao  productIncomeDao) {
		this.productIncomeDao = productIncomeDao;
	}

	@Override
	public void delete(ProductIncome productIncome) {
		productIncomeDao.delete(productIncome);
	}

	@Override
	public void update(ProductIncome productIncome) {
		productIncomeDao.update(productIncome);
	}

	@Override
	public ProductIncome selectForObjectById(ProductIncome productIncome) {
		return (ProductIncome) productIncomeDao.selectForObject("selectProductIncomeById", productIncome);
	}

	@Override
	public void updateProductIncomeById(ProductIncome productIncome) {
		productIncomeDao.update("updateProductIncomeById", productIncome);
	}

	@Override
	public void deleteById(Long id) {
		productIncomeDao.delete("deleteProductIncomeById",id);
	}

	@SuppressWarnings("unchecked")
	public List<ProductIncomeVo> selectForList(ProductIncome productIncome) {
		return (List<ProductIncomeVo>) productIncomeDao.selectForList("selectProductIncome", productIncome);
	}
	
	@SuppressWarnings("unchecked")
	public List selectUserMonthIncomeList(Map hashMap){
		return productIncomeDao.selectForList("selectUserMonthIncomeList",hashMap);
	}
	
	@SuppressWarnings("unchecked")
	public List selectUserBuyIncomeByYear(Map hashMap){
		return productIncomeDao.selectForList("selectUserBuyIncomeByYear",hashMap);
	}

	@Override
	public Page page(Page page, ProductIncome productIncome) {
		return productIncomeDao.page(page, productIncome);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(ProductIncome productIncome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(ProductIncome productIncome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(ProductIncome productIncome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(ProductIncome productIncome) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page page(Page page, Map<String, Object> map) {
		return productIncomeDao.page(page, map);
	}
	
	@Override
	public List<ProductIncome> selectForList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String page_name=(String) map.get("page_name");
		return (List<ProductIncome>)productIncomeDao.selectForList(page_name,map);
	}

	@Override
	public Double queryUserIncomeByMonth(Date date,String status, Long userId) {
		Map<String, Object> para = new HashMap<>();
		para.put("qryDate",date);
		para.put("userId",userId);
		para.put("status",status);
		return (Double) productIncomeDao.selectForObject("queryUserIncomeByMonth", para);
	}

	@Override
	public List<Map<String, Object>> queryUserIncomeList(Date date, String status, Long userId, String ordType) {
		Map<String, Object> para = new HashMap<>();
		para.put("qryDate",date);
		para.put("userId",userId);
		para.put("status",status);
		para.put("ordType",ordType);//排序类型 1：按购买日期正序 2：按还款日期正序
		return (List<Map<String, Object>>)productIncomeDao.selectForList("queryUserIncomeList", para);
	}
}
