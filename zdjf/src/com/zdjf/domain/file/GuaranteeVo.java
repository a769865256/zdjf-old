package com.zdjf.domain.file;

import java.util.List;

public class GuaranteeVo extends Guarantee {

	private static final long serialVersionUID = -6036254148341664761L;
	public GuaranteeVo(){
		super();
	}
	public GuaranteeVo(Long id){
		super();
		this.id=id;
	}
	private List<Guarantee> guaranteeList;
	public List<Guarantee> getGuaranteeList() {
		return guaranteeList;
	}
	public void setGuaranteeList(List<Guarantee> guaranteeList) {
		this.guaranteeList = guaranteeList;
	}
}
