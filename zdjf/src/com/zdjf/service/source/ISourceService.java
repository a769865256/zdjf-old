package com.zdjf.service.source;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.source.Source;
import com.zdjf.domain.source.SourceVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISourceService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Source source);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Source source);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Source source);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Source selectForObjectById(Long id);
	
	public abstract void updateSourceById(Source source);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<SourceVo> selectForList(Source source);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Source source);

	public abstract Map<Object, Object> selectForMapMonth(Source source);

	public abstract Map<Object, Object> selectForMapWeek(Source source);

	public abstract Map<Object, Object> selectForMapDay(Source source);

	public abstract Map<Object, Object> selectForMapYear(Source source);
	
}
