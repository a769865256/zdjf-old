package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IUserBankService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserBank userBank);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserBank userBank);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserBank userBank);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserBankVo load(UserBank userBank);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<UserBankVo> selectForList(UserBank userBank);
	
	public abstract UserBank selectForObjectById(long id);
	
	public abstract void updateUserBankById(UserBank userBank);
	public abstract void deleteById(long id);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserBank userBank);

	public abstract Map<Object, Object> selectForMapMonth(UserBank userBank);

	public abstract Map<Object, Object> selectForMapWeek(UserBank userBank);

	public abstract Map<Object, Object> selectForMapDay(UserBank userBank);

	public abstract Map<Object, Object> selectForMapYear(UserBank userBank);
	
	public abstract UserBank selectUserBankById(UserBank userBank);
	
}
