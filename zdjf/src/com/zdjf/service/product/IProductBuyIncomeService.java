package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductBuyIncomeVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductBuyIncomeService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ProductBuyIncome productBuyIncome);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ProductBuyIncome productBuyIncome);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ProductBuyIncome productBuyIncome);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract ProductBuyIncome selectForObjectById(Long id);
	
	public abstract void updateProductBuyIncomeById(ProductBuyIncome productBuyIncome);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<ProductBuyIncomeVo> selectForList(ProductBuyIncome productBuyIncome);
	
	public List<ProductBuyIncome> selectForList(Map<String, Object> map);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ProductBuyIncome productBuyIncome);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(ProductBuyIncome productBuyIncome);

	public abstract Map<Object, Object> selectForMapWeek(ProductBuyIncome productBuyIncome);

	public abstract Map<Object, Object> selectForMapDay(ProductBuyIncome productBuyIncome);

	public abstract Map<Object, Object> selectForMapYear(ProductBuyIncome productBuyIncome);
}
