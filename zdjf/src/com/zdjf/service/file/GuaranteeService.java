package com.zdjf.service.file;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.file.IGuaranteeDao;
import com.zdjf.domain.file.Guarantee;
import com.zdjf.domain.file.GuaranteeVo;

@Service
public class GuaranteeService implements IGuaranteeService{

	
	private IGuaranteeDao guaranteeDao;
	
	@Override
	public Serializable save(Guarantee guarantee) {
		// TODO Auto-generated method stub
		return guaranteeDao.save(guarantee);
	}

	@Override
	public Guarantee load(Guarantee guarantee) {
		// TODO Auto-generated method stub
		return (Guarantee)(guaranteeDao).reload(guarantee);
	}
	
	@Autowired
	public void setILenderDao(
			@Qualifier("guaranteeDao") IGuaranteeDao  guaranteeDao) {
		this.guaranteeDao = guaranteeDao;
	}

	@Override
	public void insertBatch(List<GuaranteeVo> guaranteeList) {
		 guaranteeDao.save("insertGuaranteeBatch", guaranteeList);
	}

}
