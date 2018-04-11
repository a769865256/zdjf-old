package com.zdjf.service.activity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.activity.Sign;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISignService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Sign sign);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Sign sign);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Sign sign);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Sign selectForObjectById(Long id);
	
	public abstract void updateSignById(Sign sign);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<Sign> selectForList(Sign Sign);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Sign sign);

	public abstract Map<Object, Object> selectForMapMonth(Sign sign);

	public abstract Map<Object, Object> selectForMapWeek(Sign sign);

	public abstract Map<Object, Object> selectForMapDay(Sign sign);

	public abstract Map<Object, Object> selectForMapYear(Sign sign);

	/**
	 * 按签到获取的正经值高低查询最近的三条记录
	 * @return
	 */
	List<Map<String, Object>> selectForLatestSignMap();

	/**
	 * 查询用户正经值信息
	 * @return
	 */
	List<Map<String, Object>> selectForSignUserByCoins();

	/**
	 * 查询用户每天签到信息
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> selectForUserSignInfEveryDay(Long userId);
}
