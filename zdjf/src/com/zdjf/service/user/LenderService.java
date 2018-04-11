package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.user.ILenderDao;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.frame.dataaccess_api.Page;

@Service
public class LenderService implements ILenderService{

	
	private ILenderDao lenderDao;
	
	@Override
	public Serializable save(Lender lender) {
		return lenderDao.save(lender);
	}

	@Override
	public void delete(Lender lender) {
		lenderDao.delete(lender);
	}

	@Override
	public void update(Lender lender) {
		lenderDao.update(lender);
	}

	@Override
	public Lender selectForObjectById(Long id) {
		Lender lender=new Lender();
		lender.setId(id);
		return (Lender) lenderDao.selectForObject("selectLenderById", lender);
	}
	
	@Override
	public Map<String,Object> selectForMapById(Long id) {
		Lender lender=new Lender();
		lender.setId(id);
		return (Map<String,Object>) lenderDao.selectForObject("selectLenderMapById", lender);
	}

	@Override
	public void updateLenderById(Lender lender) {
		lenderDao.update("updateLenderById", lender);
	}

	@Override
	public void deleteById(Long id) {
		lenderDao.delete("deleteLenderById", id);
	}

	@Override
	public Page page(Page page, Lender lender) {
		return lenderDao.page(page, lender);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Lender lender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Lender lender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Lender lender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Lender lender) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<LenderVo> selectForList(Lender lender) {
		return (List<LenderVo>) lenderDao.selectForList("selectLender", lender);
	}

	
	@Autowired
	public void setILenderDao(
			@Qualifier("lenderDao") ILenderDao  lenderDao) {
		this.lenderDao = lenderDao;
	}

}
