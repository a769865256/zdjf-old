package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.user.WithdrawCoupons;
import com.zdjf.domain.user.WithdrawCouponsVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IWithdrawCouponsService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(WithdrawCoupons withdrawCoupons);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(WithdrawCoupons withdrawCoupons);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(WithdrawCoupons withdrawCoupons);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract WithdrawCoupons selectForObjectById(Long id);
	
	public abstract void updateWithdrawCouponsById(WithdrawCoupons withdrawCoupons);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<WithdrawCouponsVo> selectForList(WithdrawCoupons withdrawCoupons);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, WithdrawCoupons withdrawCoupons);

	public abstract Map<Object, Object> selectForMapMonth(WithdrawCoupons withdrawCoupons);

	public abstract Map<Object, Object> selectForMapWeek(WithdrawCoupons withdrawCoupons);

	public abstract Map<Object, Object> selectForMapDay(WithdrawCoupons withdrawCoupons);

	public abstract Map<Object, Object> selectForMapYear(WithdrawCoupons withdrawCoupons);
	
}
