package com.zdjf.service.admin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.admin.BusinessDayStat;
import com.zdjf.domain.admin.SysUser;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISysUserService {
	
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(SysUser sysUser);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(SysUser sysUser);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(SysUser sysUser);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(SysUser sysUser);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(SysUser sysUser);
	
	
	public abstract SysUser loginSysUser(SysUser sysUser);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(SysUser sysUser);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract SysUser load(SysUser sysUser);
	
	
	
	//public abstract SysUser login(SysUser SysUser);

	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<SysUser> selectForList(SysUser sysUser);
	
	public BusinessDayStat getBusinessDayStat(BusinessDayStat businessDayStat);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, SysUser sysUser);

	public abstract Map<Object, Object> selectForMapMonth(SysUser sysUser);

	public abstract Map<Object, Object> selectForMapWeek(SysUser sysUser);

	public abstract Map<Object, Object> selectForMapDay(SysUser sysUser);

	public abstract Map<Object, Object> selectForMapYear(SysUser sysUser);

}
