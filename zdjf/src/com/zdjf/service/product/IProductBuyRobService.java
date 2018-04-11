package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.product.ProductBuyRob;
import com.zdjf.domain.product.ProductBuyRobVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductBuyRobService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ProductBuyRob productBuyRob);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ProductBuyRob productBuyRob);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ProductBuyRob productBuyRob);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract ProductBuyRob selectForObjectById(Long id);
	
	public abstract void updateProductBuyRobById(ProductBuyRob productBuyRob);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<ProductBuyRobVo> selectForList(ProductBuyRob productBuyRob);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ProductBuyRob productBuyRob);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(ProductBuyRob productBuyRob);

	public abstract Map<Object, Object> selectForMapWeek(ProductBuyRob productBuyRob);

	public abstract Map<Object, Object> selectForMapDay(ProductBuyRob productBuyRob);

	public abstract Map<Object, Object> selectForMapYear(ProductBuyRob productBuyRob);
}
