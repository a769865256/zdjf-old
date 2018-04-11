package com.zdjf.service.admin;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.admin.IBusinessDayStatDao;
import com.zdjf.domain.admin.BusinessDayStat;

@Service
public class StatService implements IStatService{

	private IBusinessDayStatDao businessDayStatDao;
	
	public Serializable saveBusinessDayStat(BusinessDayStat businessDayStat){
		System.out.println("2222222222222222=");
		return businessDayStatDao.save(businessDayStat);
	}
	
	public void updateBusinessDayStat(BusinessDayStat businessDayStat){
		businessDayStatDao.update(businessDayStat);
	}
	
	
	@Autowired
	public void setBusinessDayStatDao(
			@Qualifier("businessDayStatDao") IBusinessDayStatDao  businessDayStatDao) {
		this.businessDayStatDao = businessDayStatDao;
	}
}
