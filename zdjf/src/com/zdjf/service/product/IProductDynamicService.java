package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.product.ProductDynamic;
import com.zdjf.domain.product.ProductDynamicVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductDynamicService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ProductDynamic productDynamic);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ProductDynamic productDynamic);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ProductDynamic productDynamic);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract ProductDynamic selectForObjectById(Long id);
	
	public abstract void updateProductDynamicById(ProductDynamic productDynamic);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<ProductDynamicVo> selectForList(ProductDynamic productDynamic);
	public List<ProductDynamicVo> selectForList(Map<String, Object> map);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ProductDynamic productDynamic);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(ProductDynamic productDynamic);

	public abstract Map<Object, Object> selectForMapWeek(ProductDynamic productDynamic);

	public abstract Map<Object, Object> selectForMapDay(ProductDynamic productDynamic);

	public abstract Map<Object, Object> selectForMapYear(ProductDynamic productDynamic);
	public abstract List<Map> selectForMap(Map<String, Object> map);

}
