package com.zdjf.service.file;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.file.File;
import com.zdjf.domain.file.FileVo;

public interface IFileService {
	
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(File file);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract File load(File file);
	
	public abstract void insertBatch(List<FileVo> fileList);
	
	public abstract List getFileList(Map fileMap);
	
	public abstract  void delete(File file);

}
