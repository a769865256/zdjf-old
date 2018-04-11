package com.zdjf.dao.reconciliation;

import com.zdjf.domain.reconciliation.Reconciliation;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IReconciliationDao extends IDaoSupport {
	Page page(Page page,Reconciliation reconciliation);
}
