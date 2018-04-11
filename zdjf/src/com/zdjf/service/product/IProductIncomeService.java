package com.zdjf.service.product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductIncome;
import com.zdjf.domain.product.ProductIncomeVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductIncomeService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ProductIncome productIncome);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ProductIncome productIncome);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ProductIncome productIncome);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract ProductIncome selectForObjectById(ProductIncome productIncome);
	
	public abstract void updateProductIncomeById(ProductIncome productIncome);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<ProductIncomeVo> selectForList(ProductIncome productIncome);
	
	public List<ProductIncome> selectForList(Map<String, Object> map);
	
	public abstract List selectUserMonthIncomeList(Map hashMap);
	
	public abstract List selectUserBuyIncomeByYear(Map hashMap);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ProductIncome productIncome);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(ProductIncome productIncome);

	public abstract Map<Object, Object> selectForMapWeek(ProductIncome productIncome);

	public abstract Map<Object, Object> selectForMapDay(ProductIncome productIncome);

	public abstract Map<Object, Object> selectForMapYear(ProductIncome productIncome);

	/**
	 * @description 按条件查询用户当月收益
	 * @author Andrew
	 * @date_time 2018-03-28 15:19
	 * @param date
	 * @param status 1 已回款 -1 待回款
	 * @param userId
	 * @return java.lang.Double
	 */
	Double queryUserIncomeByMonth(Date date,String status, Long userId);


	/**
	 * @description 查询用户回款明细
	 * @author Andrew
	 * @date_time 2018-03-28 17:02
	 * @param date
	 * @param status 1 已回款 -1 未回款
	 * @param userId 用户ID
	 * @param ordType 排序类型 1：按购买日期正序 2：按还款日期正序
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 */
	List<Map<String, Object>> queryUserIncomeList(Date date,String status, Long userId, String ordType);
}
