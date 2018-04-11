package com.zdjf.service.file;

import java.io.Serializable;
import java.util.List;

import com.zdjf.domain.file.Guarantee;
import com.zdjf.domain.file.GuaranteeVo;

public interface IGuaranteeService {
	
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Guarantee guarantee);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract Guarantee load(Guarantee guarantee);
	public abstract void insertBatch(List<GuaranteeVo> guaranteeList);

}
