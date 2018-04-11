package com.zdjf.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.common.IDataFieldDao;
import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
@Service
public class DataFieldService implements IDataFieldService {
	
	private IDataFieldDao dataFieldDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DataFieldVo> selectForList(DataField dataField) {
		return (List<DataFieldVo>) dataFieldDao.selectForList("selectDataField", dataField);
	}

	@Override
	public Serializable save(DataField dataField) {
		return dataFieldDao.save(dataField);
	}

	@Override
	public void deleteByPk(DataField dataField) {
		dataFieldDao.delete("deleteDataFieldByPk", dataField);
	}

	@Override
	public void updateDataFieldByPk(DataField dataField) {
		dataFieldDao.update("updateDataFieldByPk", dataField);
	}
	@Autowired
	public void setDataFieldDao(
			@Qualifier("dataFieldDao")IDataFieldDao dataFieldDao) {
		this.dataFieldDao = dataFieldDao;
	}

}
