package com.zdjf.service.app;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.app.AppRelease;
import com.zdjf.domain.app.AppReleaseVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IAppReleaseService {
	
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(AppRelease appRelease);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(AppRelease appRelease);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(AppRelease appRelease);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract AppRelease selectForObjectById(Long id);
	
	public abstract void updateAppReleaseById(AppRelease appRelease);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<AppReleaseVo> selectForList(AppRelease appRelease);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, AppRelease appRelease);

	public abstract Map<Object, Object> selectForMapMonth(AppRelease appRelease);

	public abstract Map<Object, Object> selectForMapWeek(AppRelease appRelease);

	public abstract Map<Object, Object> selectForMapDay(AppRelease appRelease);

	public abstract Map<Object, Object> selectForMapYear(AppRelease appRelease);

}
