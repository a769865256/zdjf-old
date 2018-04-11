package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.fund.IBankDao;
import com.zdjf.domain.fund.Bank;
import com.zdjf.domain.fund.BankVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class BankService implements IBankService {

	private IBankDao bankDao;
	@Override
	public Serializable save(Bank bank) {
		return bankDao.save(bank);
	}

	@Override
	public void delete(Bank bank) {
		bankDao.delete(bank);
	}

	@Override
	public void update(Bank bank) {
		bankDao.update(bank);
	}

	@Override
	public Bank selectForObjectById(Bank bank) {
		return (Bank) bankDao.selectForObject("selectBankById", bank);
	}

	@Override
	public void updateBankById(Bank bank) {
		bankDao.update("updateBankById", bank);
	}

	@Override
	public void deleteById(Long id) {
		bankDao.delete("deleteBankById", id);
	}

	@Override
	public Page page(Page page, Bank bank) {
		return bankDao.page(page, bank);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Bank bank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Bank bank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Bank bank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Bank bank) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<BankVo> selectForList(Bank bank) {
		return (List<BankVo>) bankDao.selectForList("selectBank", bank);
	}
	@Autowired
	public void setBankDao(
			@Qualifier("bankDao")IBankDao bankDao) {
		this.bankDao = bankDao;
	}
}
