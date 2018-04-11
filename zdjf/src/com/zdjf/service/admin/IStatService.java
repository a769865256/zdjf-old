package com.zdjf.service.admin;

import java.io.Serializable;

import com.zdjf.domain.admin.BusinessDayStat;

public interface IStatService {

	public abstract Serializable saveBusinessDayStat(BusinessDayStat businessDayStat);
	
	public abstract void updateBusinessDayStat(BusinessDayStat businessDayStat);
	
}
