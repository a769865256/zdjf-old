package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface ILenderService {
	
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Lender lender);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Lender lender);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Lender lender);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Lender selectForObjectById(Long id);
	public abstract Map<String,Object> selectForMapById(Long id);
	
	public abstract void updateLenderById(Lender lender);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<LenderVo> selectForList(Lender lender);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Lender lender);

	public abstract Map<Object, Object> selectForMapMonth(Lender lender);

	public abstract Map<Object, Object> selectForMapWeek(Lender lender);

	public abstract Map<Object, Object> selectForMapDay(Lender lender);

	public abstract Map<Object, Object> selectForMapYear(Lender lender);
	

}
