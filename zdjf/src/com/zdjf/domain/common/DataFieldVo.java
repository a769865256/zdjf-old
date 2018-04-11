package com.zdjf.domain.common;

import java.util.List;

public class DataFieldVo extends DataField {

	private static final long serialVersionUID = -8470003595136840486L;
	public DataFieldVo(){
		super();
	}
	private List<DataField> dataFieldList;
	public List<DataField> getDataFieldList() {
		return dataFieldList;
	}
	public void setDataFieldList(List<DataField> dataFieldList) {
		this.dataFieldList = dataFieldList;
	}

}
