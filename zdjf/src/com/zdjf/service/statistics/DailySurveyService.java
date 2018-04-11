package com.zdjf.service.statistics;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.statistics.IDailySurveyDao;
import com.zdjf.domain.statistics.DailySurvey;
import com.zdjf.domain.statistics.DailySurveyVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class DailySurveyService implements IDailySurveyService {

	private IDailySurveyDao dailySurveyDao;
	
	@Override
	public Serializable save(DailySurvey dailySurvey) {
		return dailySurveyDao.save(dailySurvey);
	}

	
	@Autowired
	public void setIDailySurveyDao(
			@Qualifier("dailySurveyDao") IDailySurveyDao  dailySurveyDao) {
		this.dailySurveyDao = dailySurveyDao;
	}

	@Override
	public void delete(DailySurvey dailySurvey) {
		dailySurveyDao.delete(dailySurvey);
	}

	@Override
	public void update(DailySurvey dailySurvey) {
		dailySurveyDao.update(dailySurvey);
	}

	@Override
	public DailySurvey selectForObjectById(Long id) {
		return (DailySurvey) dailySurveyDao.selectForObject("selectDailySurveyById", id);
	}

	@Override
	public void updateDailySurveyById(DailySurvey dailySurvey) {
		dailySurveyDao.update("updateDailySurveyById", dailySurvey);
	}

	@Override
	public void deleteById(Long id) {
		dailySurveyDao.delete("deleteDailySurveyById",id);
	}

	@SuppressWarnings("unchecked")
	public List<DailySurveyVo> selectForList(DailySurvey dailySurvey) {
		return (List<DailySurveyVo>) dailySurveyDao.selectForList("selectDailySurvey", dailySurvey);
	}

	@Override
	public Page page(Page page, DailySurvey dailySurvey) {
		return dailySurveyDao.page(page, dailySurvey);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(DailySurvey dailySurvey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(DailySurvey dailySurvey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(DailySurvey dailySurvey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(DailySurvey dailySurvey) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page page(Page page, Map<String, Object> map) {
		return dailySurveyDao.page(page, map);
	}

}
