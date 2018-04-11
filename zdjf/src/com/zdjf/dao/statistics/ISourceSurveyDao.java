package com.zdjf.dao.statistics;

import java.util.Map;

import com.zdjf.domain.statistics.SourceSurvey;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface ISourceSurveyDao extends IDaoSupport {
	Page page(Page page,SourceSurvey sourceSurvey);
	Page page(Page page,Map<String, Object> map);
}
