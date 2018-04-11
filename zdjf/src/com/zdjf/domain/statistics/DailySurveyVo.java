package com.zdjf.domain.statistics;

import java.util.List;

public class DailySurveyVo extends DailySurvey {

	private static final long serialVersionUID = 2755432628604418772L;
	public DailySurveyVo(){
		super();
	}
	public DailySurveyVo(Long id){
		super();
		this.id=id;
	}
	private List<DailySurvey> dailySurveyList;
	public List<DailySurvey> getDailySurveyList() {
		return dailySurveyList;
	}
	public void setDailySurveyList(List<DailySurvey> dailySurveyList) {
		this.dailySurveyList = dailySurveyList;
	}
	
}
