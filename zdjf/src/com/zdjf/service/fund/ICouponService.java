package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.Coupon;
import com.zdjf.domain.fund.CouponVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface ICouponService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Coupon coupon);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Coupon coupon);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Coupon coupon);
	
	public abstract Coupon selectForObjectById(long id);
	
	public abstract void updateCouponById(Coupon coupon);
	public abstract void deleteById(long id);
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CouponVo load(Coupon coupon);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<CouponVo> selectForList(Coupon coupon); 
	
	public abstract List getUserCouponList(Map hashMap);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Coupon coupon);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(Coupon coupon);

	public abstract Map<Object, Object> selectForMapWeek(Coupon coupon);

	public abstract Map<Object, Object> selectForMapDay(Coupon coupon);

	public abstract Map<Object, Object> selectForMapYear(Coupon coupon);
}
