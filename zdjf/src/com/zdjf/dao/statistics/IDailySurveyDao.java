package com.zdjf.dao.statistics;

import java.util.Map;

import com.zdjf.domain.statistics.DailySurvey;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IDailySurveyDao extends IDaoSupport {
	Page page(Page page,DailySurvey dailySurvey);
	Page page(Page page,Map<String, Object> map);
}
