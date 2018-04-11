package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.Recharge;
import com.zdjf.domain.fund.RechargeVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IRechargeService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Recharge recharge);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Recharge recharge);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Recharge recharge);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract RechargeVo load(Recharge recharge);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<RechargeVo> selectForList(Recharge recharge); 
	
	public abstract Recharge selectForObjectById(long id);
	
	public abstract void updateRechargeById(Recharge recharge);
	public abstract void deleteById(long id);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Recharge recharge);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(Recharge recharge);

	public abstract Map<Object, Object> selectForMapWeek(Recharge recharge);

	public abstract Map<Object, Object> selectForMapDay(Recharge recharge);

	public abstract Map<Object, Object> selectForMapYear(Recharge recharge);
	
}
