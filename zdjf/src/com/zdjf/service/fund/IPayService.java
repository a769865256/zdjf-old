package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.Pay;
import com.zdjf.domain.fund.PayVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IPayService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Pay pay);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Pay pay);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Pay pay);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Pay selectForObjectById(Long id);
	
	public abstract void updatePayById(Pay pay);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<PayVo> selectForList(Pay pay);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Pay pay);
	public abstract Page page(Page page, Map<String, Object> map);

	public abstract Map<Object, Object> selectForMapMonth(Pay pay);

	public abstract Map<Object, Object> selectForMapWeek(Pay pay);

	public abstract Map<Object, Object> selectForMapDay(Pay pay);

	public abstract Map<Object, Object> selectForMapYear(Pay pay);
}
