package com.zdjf.service.reconciliation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.reconciliation.IReconciliationDao;
import com.zdjf.domain.reconciliation.Reconciliation;
import com.zdjf.domain.reconciliation.ReconciliationVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class ReconciliationService implements IReconciliationService {
	private IReconciliationDao reconciliationDao;
	@Override
	public Serializable save(Reconciliation reconciliation) {
		return reconciliationDao.save(reconciliation);
	}

	@Override
	public void delete(Reconciliation reconciliation) {
		reconciliationDao.delete(reconciliation);
	}

	@Override
	public void update(Reconciliation reconciliation) {
		reconciliationDao.update(reconciliation);
	}

	@Override
	public Reconciliation selectForObjectById(Long id) {
		return (Reconciliation) reconciliationDao.selectForObject("selectForReconciliationById", id);
	}

	@Override
	public void updateReconciliationById(Reconciliation reconciliation) {
		reconciliationDao.update("updateForReconciliationById", reconciliation);
	}

	@Override
	public void deleteById(Long id) {
		reconciliationDao.delete("deleteForReconciliationById", id);
	}

	@Override
	public List<ReconciliationVo> selectForList(Reconciliation reconciliation) {
		return (List<ReconciliationVo>) reconciliationDao.selectForList("selectReconciliation",reconciliation);
	}

	@Override
	public Page page(Page page, Reconciliation reconciliation) {
		return reconciliationDao.page(page, reconciliation);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Reconciliation reconciliation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Reconciliation reconciliation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Reconciliation reconciliation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Reconciliation reconciliation) {
		// TODO Auto-generated method stub
		return null;
	}
	@Autowired(required=true)
	public void setReconciliationDao(
			@Qualifier("reconciliationDao")IReconciliationDao reconciliationDao) {
		this.reconciliationDao = reconciliationDao;
	}

}
