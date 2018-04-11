package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.dubbo.config.annotation.Service;
import com.zdjf.dao.fund.IPayDao;
import com.zdjf.domain.fund.Pay;
import com.zdjf.domain.fund.PayVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class PayService implements IPayService {
	private IPayDao payDao;
	@Override
	public Serializable save(Pay pay) {
		return payDao.save(pay);
	}

	@Override
	public void delete(Pay pay) {
		payDao.delete(pay);
	}

	@Override
	public void update(Pay pay) {
		payDao.update(pay);
	}

	@Override
	public Pay selectForObjectById(Long id) {
		Pay pay=new Pay();
		pay.setId(id);
		return (Pay) payDao.selectForObject("selectPayById", pay);
	}

	@Override
	public void updatePayById(Pay pay) {
		payDao.update("updatePayById",pay);
	}

	@Override
	public void deleteById(Long id) {
		payDao.delete("deletePay", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PayVo> selectForList(Pay pay) {
		return (List<PayVo>) payDao.selectForList("selectPay",pay);
	}

	@Override
	public Page page(Page page, Pay pay) {
		return payDao.page(page, pay);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		return payDao.page(page, map);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Pay pay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Pay pay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Pay pay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Pay pay) {
		// TODO Auto-generated method stub
		return null;
	}
	@Autowired
	public void setIPayDao(
			@Qualifier("payDao") IPayDao  payDao) {
		this.payDao = payDao;
	}
}
