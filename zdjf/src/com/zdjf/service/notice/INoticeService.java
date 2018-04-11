package com.zdjf.service.notice;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.notice.Notice;
import com.zdjf.domain.notice.NoticeVo;
import com.zdjf.frame.dataaccess_api.Page;

public interface INoticeService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Notice notice);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Notice notice);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Notice notice);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	
	public abstract Notice selectForObjectById(Long id);
	
	public abstract void updateNoticeById(Notice notice);
	public abstract void deleteById(Long id);
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<NoticeVo> selectForList(Notice notice);
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Notice notice);

	public abstract Map<Object, Object> selectForMapMonth(Notice notice);

	public abstract Map<Object, Object> selectForMapWeek(Notice notice);

	public abstract Map<Object, Object> selectForMapDay(Notice notice);

	public abstract Map<Object, Object> selectForMapYear(Notice notice);
	
}
