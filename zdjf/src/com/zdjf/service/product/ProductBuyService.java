package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.product.IProductBuyDao;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class ProductBuyService implements IProductBuyService {

private IProductBuyDao productBuyDao;
	
	@Override
	public Serializable save(ProductBuy productBuy) {
		return productBuyDao.save(productBuy);
	}

	
	@Autowired
	public void setIProductBuyDao(
			@Qualifier("productBuyDao") IProductBuyDao  productBuyDao) {
		this.productBuyDao = productBuyDao;
	}

	@Override
	public void delete(ProductBuy productBuy) {
		productBuyDao.delete(productBuy);
	}

	@Override
	public void update(ProductBuy productBuy) {
		productBuyDao.update(productBuy);
	}
	@Override
	public void updateProductBuy(ProductBuy productBuy){
		productBuyDao.update("updateProductBuy",productBuy);
	}

	@Override
	public ProductBuy selectForObjectById(Long id) {
		return (ProductBuy) productBuyDao.selectForObject("selectProductBuyById", id);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProductBuy> selectForList(Map<String, Object> map){
		String page_name=(String) map.get("page_name");
		return (List<ProductBuy>)productBuyDao.selectForList(page_name,map);
	}

	@Override
	public void updateProductBuyById(ProductBuy productBuy) {
		productBuyDao.update("updateProductBuyById", productBuy);
	}

	@Override
	public void deleteById(Long id) {
		productBuyDao.delete("deleteProductBuyById",id);
	}

	@SuppressWarnings("unchecked")
	public List<ProductBuyVo> selectForList(ProductBuy productBuy) {
		return (List<ProductBuyVo>) productBuyDao.selectForList("selectProductBuy", productBuy);
	}

	@Override
	public Page page(Page page, ProductBuy productBuy) {
		return productBuyDao.page(page, productBuy);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(ProductBuy productBuy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(ProductBuy productBuy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(ProductBuy productBuy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(ProductBuy productBuy) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page page(Page page, Map<String, Object> map) {
		return productBuyDao.page(page, map);
	}


	@Override
	public List<Map<String, Object>> selectAmountByProductId() {
		return (List<Map<String, Object>>) productBuyDao.selectForList("selectAmountByProductId");
	}


	@Override
	public List selectProductBuyList(ProductBuy productBuy) {
		return  productBuyDao.selectForList("selectProductBuyList",productBuy);
	}
	
	@Override
	public List selectUserBuyList(Long user_id){
		return productBuyDao.selectForList("selectUserBuyList",user_id);
	}
	@Override
	public List selectUserMonthBuyList(Map hashMap){
		return productBuyDao.selectForList("selectUserMonthBuyList",hashMap);
	}


	@Override
	public List<Long> selectInvestor() {
		return (List<Long>) productBuyDao.selectForList("selectInvestor");
	}

	@Override
	public Long selectUserTotalInvest(Map map) {
		List<Long> total = (List<Long>)productBuyDao.selectForList("selectUserTotalInvest",map);
		return total.get(0);
	}

	@Override
	public int selectUserFirstInvest(Map map) {
		List<Long> total = (List<Long>)productBuyDao.selectForList("selectUserFirstInvest",map);
		return total!=null&&total.size()>0?1:0;
	}

	@Override
	public boolean selectUserIsInvested(Map map) {
		List<Long> total = (List<Long>)productBuyDao.selectForList("selectUserIsInvested",map);
		return total!=null&&total.size()>0?true:false;
	}

	@Override
	public List<Map<String, Object>> selectNewYearInvt(Map hashMap) {
		List<Map<String, Object>> list = (List<Map<String, Object>>)productBuyDao.selectForList("selectNewYearInvt",hashMap);
		return list;
	}

	@Override
	public Double staUserProductBuy() {
		List<Double> list = (List<Double>)productBuyDao.selectForList("staUserProductBuy");
		return list!=null&&list.size()>0&&list.get(0)!=null?list.get(0):0.0;
	}

	@Override
	public long staInvtNum() {
		List<Long> total = (List<Long>)productBuyDao.selectForList("staInvtNum");
		return total!=null&&total.size()>0&&total.get(0)!=null?total.get(0):0L;
	}

	@Override
	public long staInvtUsers() {
		List<Long> total = (List<Long>)productBuyDao.selectForList("staInvtUsers");
		return total!=null&&total.size()>0&&total.get(0)!=null?total.get(0):0L;
	}

	@Override
	public long staInvtWomanUsers() {
		List<Long> total = (List<Long>)productBuyDao.selectForList("staInvtWomanUsers");
		return total!=null&&total.size()>0&&total.get(0)!=null?total.get(0):0L;
	}

	@Override
	public long staInvtManUsers() {
		List<Long> total = (List<Long>)productBuyDao.selectForList("staInvtManUsers");
		return total!=null&&total.size()>0&&total.get(0)!=null?total.get(0):0L;
	}

	@Override
	public Double selectUserInvestByCurDay(Map map) {
		List<Double> total = (List<Double>)productBuyDao.selectForList("selectUserInvestByCurDay",map);
		return total!=null&&total.size()>0&&total.get(0)!=null?total.get(0):0.0;
	}
}
