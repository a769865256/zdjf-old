package com.zdjf.service.statistics;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.statistics.ISourceSurveyDao;
import com.zdjf.domain.statistics.SourceSurvey;
import com.zdjf.domain.statistics.SourceSurveyVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class SourceSurveyService implements ISourceSurveyService {

private ISourceSurveyDao sourceSurveyDao;
	
	@Override
	public Serializable save(SourceSurvey sourceSurvey) {
		return sourceSurveyDao.save(sourceSurvey);
	}

	
	@Autowired
	public void setISourceSurveyDao(
			@Qualifier("sourceSurveyDao") ISourceSurveyDao  sourceSurveyDao) {
		this.sourceSurveyDao = sourceSurveyDao;
	}

	@Override
	public void delete(SourceSurvey sourceSurvey) {
		sourceSurveyDao.delete(sourceSurvey);
	}

	@Override
	public void update(SourceSurvey sourceSurvey) {
		sourceSurveyDao.update(sourceSurvey);
	}

	@Override
	public SourceSurvey selectForObjectById(Long id) {
		return (SourceSurvey) sourceSurveyDao.selectForObject("selectSourceSurveyById", id);
	}

	@Override
	public void updateSourceSurveyById(SourceSurvey sourceSurvey) {
		sourceSurveyDao.update("updateSourceSurveyById", sourceSurvey);
	}

	@Override
	public void deleteById(Long id) {
		sourceSurveyDao.delete("deleteSourceSurveyById",id);
	}

	@SuppressWarnings("unchecked")
	public List<SourceSurveyVo> selectForList(SourceSurvey sourceSurvey) {
		return (List<SourceSurveyVo>) sourceSurveyDao.selectForList("selectSourceSurvey", sourceSurvey);
	}

	@Override
	public Page page(Page page, SourceSurvey sourceSurvey) {
		return sourceSurveyDao.page(page, sourceSurvey);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(SourceSurvey sourceSurvey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(SourceSurvey sourceSurvey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(SourceSurvey sourceSurvey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(SourceSurvey sourceSurvey) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page page(Page page, Map<String, Object> map) {
		return sourceSurveyDao.page(page, map);
	}


	@Override
	public List<Map> selectForListByMap(Map map) {
		String sqlName=map.get("sql_name").toString();
		return (List<Map>) sourceSurveyDao.selectForList(sqlName, map);
	}

}
