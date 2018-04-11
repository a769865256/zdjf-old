package com.zdjf.service.statistics;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.statistics.DailySurvey;
import com.zdjf.domain.statistics.DailySurveyVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IDailySurveyService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(DailySurvey dailySurvey);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(DailySurvey dailySurvey);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(DailySurvey dailySurvey);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract DailySurvey selectForObjectById(Long id);
	
	public abstract void updateDailySurveyById(DailySurvey dailySurvey);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<DailySurveyVo> selectForList(DailySurvey dailySurvey);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, DailySurvey dailySurvey);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(DailySurvey dailySurvey);

	public abstract Map<Object, Object> selectForMapWeek(DailySurvey dailySurvey);

	public abstract Map<Object, Object> selectForMapDay(DailySurvey dailySurvey);

	public abstract Map<Object, Object> selectForMapYear(DailySurvey dailySurvey);
}
