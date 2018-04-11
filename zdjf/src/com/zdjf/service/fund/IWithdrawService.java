package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.Withdraw;
import com.zdjf.domain.fund.WithdrawVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IWithdrawService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Withdraw withdraw);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Withdraw withdraw);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Withdraw withdraw);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Withdraw selectForObjectById(Long id);
	
	public abstract void updateWithdrawById(Withdraw withdraw);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<WithdrawVo> selectForList(Withdraw withdraw);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Withdraw withdraw);
	public abstract Page page(Page page, Map<String, Object> map);

	public abstract Map<Object, Object> selectForMapMonth(Withdraw withdraw);

	public abstract Map<Object, Object> selectForMapWeek(Withdraw withdraw);

	public abstract Map<Object, Object> selectForMapDay(Withdraw withdraw);

	public abstract Map<Object, Object> selectForMapYear(Withdraw withdraw);
	
}
