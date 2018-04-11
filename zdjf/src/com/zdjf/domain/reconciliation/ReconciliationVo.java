package com.zdjf.domain.reconciliation;

import java.util.List;

public class ReconciliationVo extends Reconciliation {

	private static final long serialVersionUID = -7454435569475772553L;
	private List<Reconciliation> reconciliationList;
	public ReconciliationVo(){
		super();
	}
	public ReconciliationVo(Long id){
		super();
		this.id=id;
	}
	public List<Reconciliation> getReconciliationList() {
		return reconciliationList;
	}
	public void setReconciliationList(List<Reconciliation> reconciliationList) {
		this.reconciliationList = reconciliationList;
	}
	
}
