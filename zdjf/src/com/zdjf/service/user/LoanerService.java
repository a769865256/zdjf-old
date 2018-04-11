package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.user.ILoanerDao;
import com.zdjf.domain.user.Loaner;
import com.zdjf.domain.user.LoanerVo;
import com.zdjf.frame.dataaccess_api.Page;

@Service
public class LoanerService implements ILoanerService{

	
	private ILoanerDao loanerDao;
	
	@Override
	public Serializable save(Loaner loaner) {
		return loanerDao.save(loaner);
	}

	@Override
	public void delete(Loaner loaner) {
		loanerDao.delete(loaner);
	}

	@Override
	public void update(Loaner loaner) {
		loanerDao.update(loaner);
	}

	@Override
	public Loaner selectForObjectById(Long id) {
		Loaner loaner=new Loaner();
		loaner.setId(id);
		return (Loaner) loanerDao.selectForObject("selectLoanerById", loaner);
	}

	@Override
	public void updateLoanerById(Loaner loaner) {
		loanerDao.update("updateLoanerById", loaner);
	}

	@Override
	public void deleteById(Long id) {
		loanerDao.delete("deleteLoanerById", id);
	}

	@Override
	public Page page(Page page, Loaner loaner) {
		return loanerDao.page(page, loaner);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Loaner loaner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Loaner loaner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Loaner loaner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Loaner loaner) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<LoanerVo> selectForList(Loaner loaner) {
		return (List<LoanerVo>) loanerDao.selectForList("selectLoaner", loaner);
	}
	
	@Autowired
	public void setILoanerDao(
			@Qualifier("loanerDao") ILoanerDao  loanerDao) {
		this.loanerDao = loanerDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> selectForMapById(Long id) {
		return (Map<String, Object>) loanerDao.selectForObject("selectLoanerMapById", id);
	}

}
