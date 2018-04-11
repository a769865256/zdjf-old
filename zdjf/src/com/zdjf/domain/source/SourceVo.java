package com.zdjf.domain.source;

import java.util.List;

public class SourceVo extends Source {

	/**
	 * 
	 */
	private static final long serialVersionUID = -285131189871880344L;
	public SourceVo(){
		super();
	}
	public SourceVo(Long id){
		super();
		this.id=id;
	}
	public List<SourceVo> getSourceList() {
		return sourceList;
	}
	public void setSourceList(List<SourceVo> sourceList) {
		this.sourceList = sourceList;
	}
	private List<SourceVo> sourceList;

}
