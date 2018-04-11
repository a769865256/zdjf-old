package com.zdjf.domain.notice;

import java.util.List;

public class NewsVo extends News {

	private static final long serialVersionUID = 1870604794922657330L;
	public NewsVo(){
		super();
	}
	public NewsVo(Long id){
		super();
		this.id=id;
	}
	private List<News> newsList;
	public List<News> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

}
