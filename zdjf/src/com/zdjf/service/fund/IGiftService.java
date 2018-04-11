package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.GiftVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IGiftService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Gift gift);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Gift gift);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Gift gift);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract GiftVo load(Gift gift);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<GiftVo> selectForList(Gift gift); 
	
	public abstract Gift selectForObjectById(long id);
	
	public abstract void updateGiftById(Gift gift);
	public abstract void deleteById(long id);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Gift gift);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(Gift gift);

	public abstract Map<Object, Object> selectForMapWeek(Gift gift);

	public abstract Map<Object, Object> selectForMapDay(Gift gift);

	public abstract Map<Object, Object> selectForMapYear(Gift gift);
	
	
}
