package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IFundsService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Funds funds);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Funds funds);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Funds funds);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Funds selectForObjectById(Long id);
	
	public abstract List getAmountFundsList();
	
	public abstract void updateFundsById(Funds funds);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<FundsVo> selectForList(Funds funds);
	
	public abstract List<Map<String,Object>> selectForMap(Funds funds);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Funds funds);
	public abstract Page page(Page page, Map map) ;
	
	public abstract Page getMyFundsList(Page p,Map map);

	public abstract Map<Object, Object> selectForMapMonth(Funds funds);

	public abstract Map<Object, Object> selectForMapWeek(Funds funds);

	public abstract Map<Object, Object> selectForMapDay(Funds funds);

	public abstract Map<Object, Object> selectForMapYear(Funds funds);
	public abstract List<Map<String, Object>> selectForList(String sqlName);

	//查询用户11充值  21提现 22理财产品支付不成功的交易记录
	List<FundsVo> selectFundListNoRes(Long user_id);
}
