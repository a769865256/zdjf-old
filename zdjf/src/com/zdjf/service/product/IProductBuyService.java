package com.zdjf.service.product;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IProductBuyService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(ProductBuy productBuy);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(ProductBuy productBuy);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(ProductBuy productBuy);
	
	public abstract void updateProductBuy(ProductBuy productBuy);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract ProductBuy selectForObjectById(Long id);
	
	public abstract void updateProductBuyById(ProductBuy productBuy);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<ProductBuyVo> selectForList(ProductBuy productBuy);
	
	public List<ProductBuy> selectForList(Map<String, Object> map);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, ProductBuy productBuy);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(ProductBuy productBuy);

	public abstract Map<Object, Object> selectForMapWeek(ProductBuy productBuy);

	public abstract Map<Object, Object> selectForMapDay(ProductBuy productBuy);

	public abstract Map<Object, Object> selectForMapYear(ProductBuy productBuy);
	public abstract List<Map<String, Object>> selectAmountByProductId();
	public abstract List selectProductBuyList(ProductBuy productBuy);
	
	public abstract List selectUserBuyList(Long user_id);
	
	public abstract List selectUserMonthBuyList(Map hashMap);
	
	public abstract List<Long> selectInvestor();

	/**
	 * 查询用户累计投资额
	 * @param map
	 * @return
	 */
	Long selectUserTotalInvest(Map map);

	/**
	 * 查询用户首次投资额
	 * @param map
	 * @return
	 */
	int selectUserFirstInvest(Map map);

	/**
	 * 查询用户是否投资过
	 * @param map
	 * @return
	 */
	boolean selectUserIsInvested(Map map);

	/**
	 * 查询用户当日累计投资额
	 * @param map
	 * @return
	 */
	Double selectUserInvestByCurDay(Map map);

	/**
	 * 查询活动时间范围内的投资排行
	 * @param hashMap
	 * @return
	 */
	List<Map<String, Object>> selectNewYearInvt(Map hashMap);

	/**
	 * 平台数据累计成交额
	 * @return
	 */
	Double staUserProductBuy();

	/**
	 * 平台数据累计投资笔数
	 * @return
	 */
	long staInvtNum();

	/**
	 * 平台数据累计投资人数
	 * @return
	 */
	long staInvtUsers();

	/**
	 * 平台数据累计女性总投资人数
	 * @return
	 */
	long staInvtWomanUsers();

	/**
	 * 平台数据累计男性总投资人数
	 * @return
	 */
	long staInvtManUsers();
}
