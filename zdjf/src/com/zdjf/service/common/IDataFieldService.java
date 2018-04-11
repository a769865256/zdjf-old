package com.zdjf.service.common;

import java.io.Serializable;
import java.util.List;

import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;

public interface IDataFieldService {
	public abstract List<DataFieldVo> selectForList(DataField dataField);
	public abstract Serializable save(DataField dataField);
	public abstract void deleteByPk(DataField dataField);
	public abstract void updateDataFieldByPk(DataField dataField);
}
