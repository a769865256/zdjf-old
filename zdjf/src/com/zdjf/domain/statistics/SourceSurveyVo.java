package com.zdjf.domain.statistics;

import java.util.List;

public class SourceSurveyVo extends SourceSurvey {

	private static final long serialVersionUID = 5544752454074730563L;
	public SourceSurveyVo(){
		super();
	}
	public SourceSurveyVo(Long id){
		super();
		this.id=id;
	}
	private List<SourceSurvey> sourceSurveyList;
	public List<SourceSurvey> getSourceSurveyList() {
		return sourceSurveyList;
	}
	public void setSourceSurveyList(List<SourceSurvey> sourceSurveyList) {
		this.sourceSurveyList = sourceSurveyList;
	}

}
