package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductService {
	
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Product product);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Product product);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Product product);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Product selectForObjectById(Long id);
	
	public abstract void updateProductById(Product product);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<ProductVo> selectForList(Product product);
	public List<ProductVo> selectForList(Map<String, Object> map);
	
	public List getProductList(Map map);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Product product);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(Product product);

	public abstract Map<Object, Object> selectForMapWeek(Product product);

	public abstract Map<Object, Object> selectForMapDay(Product product);

	public abstract Map<Object, Object> selectForMapYear(Product product);
}
