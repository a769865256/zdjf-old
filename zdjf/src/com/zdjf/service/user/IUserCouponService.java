package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IUserCouponService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserCoupon userCoupon);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserCoupon userCoupon);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserCoupon userCoupon);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserCouponVo load(UserCoupon userCoupon);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<UserCouponVo> selectForList(UserCoupon userCoupon);
	
	public abstract List<Map<String, Object>> selectForList(Map<String, Object> map);
	
	public abstract UserCoupon selectForObjectByCouponId(UserCoupon userCoupon);
	
	public abstract void updateUserCouponByCouponId(UserCoupon userCoupon);
	public abstract void deleteByCouponId(long id);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserCoupon userCoupon);
	public abstract Page page(Page page, Map<String, Object> map);

	public abstract Map<Object, Object> selectForMapMonth(UserCoupon userCoupon);

	public abstract Map<Object, Object> selectForMapWeek(UserCoupon userCoupon);

	public abstract Map<Object, Object> selectForMapDay(UserCoupon userCoupon);

	public abstract Map<Object, Object> selectForMapYear(UserCoupon userCoupon);
	
	public abstract List getUserCouponList(Map prammap);
	
	public abstract int selectCouponCountByUid(Long user_id);

	void updateOutOfDateUserCoupon();
	
}
