package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface ICoinService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Coin coin);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Coin coin);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Coin coin);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CoinVo load(Coin coin);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<CoinVo> selectForList(Coin coin);
	
	public abstract Coin selectForObjectById(long id);
	
	public abstract void updateCoinById(Coin coin);
	public abstract void deleteById(long id);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Coin coin);
	public abstract Page page(Page page, Map<String,Object> map);

	public abstract Map<Object, Object> selectForMapMonth(Coin coin);

	public abstract Map<Object, Object> selectForMapWeek(Coin coin);

	public abstract Map<Object, Object> selectForMapDay(Coin coin);

	public abstract Map<Object, Object> selectForMapYear(Coin coin);
	
}
