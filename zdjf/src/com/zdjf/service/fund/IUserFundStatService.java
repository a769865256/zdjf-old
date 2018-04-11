package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.fund.UserFundStatVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IUserFundStatService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserFundStat userFundStat);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserFundStat userFundStat);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserFundStatVo load(UserFundStat userFundStat);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<UserFundStatVo> selectForList(UserFundStat userFundStat); 
	public abstract List<Map<String, Object>> selectUserFundStatForList(); 
	public abstract void update(UserFundStat userFundStat);
	
	public abstract UserFundStat selectForObjectById(UserFundStat userFundStat);
	
	public abstract void updateUserFundStatById(UserFundStat userFundStat);
	
	public abstract void updateUserBalance(UserFundStat userFundStat);
	
	public abstract void updateUserBalance(Long id,String ip);
	
	public abstract void deleteById(long id);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserFundStat userFundStat);

	public abstract Map<Object, Object> selectForMapMonth(UserFundStat userFundStat);

	public abstract Map<Object, Object> selectForMapWeek(UserFundStat userFundStat);

	public abstract Map<Object, Object> selectForMapDay(UserFundStat userFundStat);

	public abstract Map<Object, Object> selectForMapYear(UserFundStat userFundStat);
}
