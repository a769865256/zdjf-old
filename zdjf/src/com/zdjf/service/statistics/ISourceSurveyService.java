package com.zdjf.service.statistics;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.statistics.SourceSurvey;
import com.zdjf.domain.statistics.SourceSurveyVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISourceSurveyService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(SourceSurvey sourceSurvey);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(SourceSurvey sourceSurvey);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(SourceSurvey sourceSurvey);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract SourceSurvey selectForObjectById(Long id);
	
	public abstract void updateSourceSurveyById(SourceSurvey sourceSurvey);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<SourceSurveyVo> selectForList(SourceSurvey sourceSurvey);
	public abstract List<Map> selectForListByMap(Map map);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, SourceSurvey sourceSurvey);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(SourceSurvey sourceSurvey);

	public abstract Map<Object, Object> selectForMapWeek(SourceSurvey sourceSurvey);

	public abstract Map<Object, Object> selectForMapDay(SourceSurvey sourceSurvey);

	public abstract Map<Object, Object> selectForMapYear(SourceSurvey sourceSurvey);
}
