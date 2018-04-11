package com.zdjf.service.advertise;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.advertise.Advertise;
import com.zdjf.domain.advertise.AdvertiseVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IAdvertiseService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Advertise advertise);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Advertise advertise);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Advertise advertise);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Advertise selectForObjectById(Long id);
	
	public abstract void updateAdvertiseById(Advertise advertise);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<AdvertiseVo> selectForList(Advertise advertise);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Advertise advertise);

	public abstract Map<Object, Object> selectForMapMonth(Advertise advertise);

	public abstract Map<Object, Object> selectForMapWeek(Advertise advertise);

	public abstract Map<Object, Object> selectForMapDay(Advertise advertise);

	public abstract Map<Object, Object> selectForMapYear(Advertise advertise);
	
}
