package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.CoinStreamVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface ICoinStreamService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(CoinStream coinStream);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(CoinStream coinStream);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(CoinStream coinStream);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CoinStreamVo load(CoinStream coinStream);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<CoinStreamVo> selectForList(CoinStream coinStream);
	
	public abstract CoinStream selectForObjectById(long id);
	
	public abstract void updateCoinStreamById(CoinStream coinStream);
	public abstract void updateCoinStream(CoinStream coinStream);
	public abstract void deleteById(long id);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, CoinStream coinStream);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(CoinStream coinStream);

	public abstract Map<Object, Object> selectForMapWeek(CoinStream coinStream);

	public abstract Map<Object, Object> selectForMapDay(CoinStream coinStream);

	public abstract Map<Object, Object> selectForMapYear(CoinStream coinStream);

	/**
	 * 查询用户正经值明细
	 * @param coinStream
	 * @return
	 */
	List<Map<String, Object>> selectUserCoinForList(CoinStream coinStream);

	/**
	 * 根据条件查询用户正经值明细
	 * @param userId
	 * @param type
	 * @return
	 */
	List<Map<String, Object>> selectUserCoinForListByType(Long userId,Integer type);

	/**
	 * 统计用户投资>=1000获得正经值奖励
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> selectUserCoinForListByInvest(Long userId);
}
