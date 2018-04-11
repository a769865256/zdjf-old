package com.zdjf.service.reconciliation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.reconciliation.Reconciliation;
import com.zdjf.domain.reconciliation.ReconciliationVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface IReconciliationService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Reconciliation reconciliation);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Reconciliation reconciliation);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Reconciliation reconciliation);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Reconciliation selectForObjectById(Long id);
	
	public abstract void updateReconciliationById(Reconciliation reconciliation);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<ReconciliationVo> selectForList(Reconciliation reconciliation);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Reconciliation reconciliation);

	public abstract Map<Object, Object> selectForMapMonth(Reconciliation reconciliation);

	public abstract Map<Object, Object> selectForMapWeek(Reconciliation reconciliation);

	public abstract Map<Object, Object> selectForMapDay(Reconciliation reconciliation);

	public abstract Map<Object, Object> selectForMapYear(Reconciliation reconciliation);
}
