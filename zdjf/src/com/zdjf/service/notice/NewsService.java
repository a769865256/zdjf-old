package com.zdjf.service.notice;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.notice.INewsDao;
import com.zdjf.domain.notice.News;
import com.zdjf.domain.notice.NewsVo;
import com.zdjf.frame.dataaccess_api.Page;

@Service
public class NewsService implements INewsService {
	private INewsDao newsDao;
	@Override
	public Serializable save(News news) {
		return newsDao.save(news);
	}

	@Override
	public void delete(News news) {
		newsDao.delete(news);
	}

	@Override
	public void update(News news) {
		newsDao.update(news);
	}

	@Override
	public News selectForObjectById(Long id) {
		News news=new News();
		news.setId(id);
		return (News) newsDao.selectForObject("selectNewsById", news);
	}

	@Override
	public void updateNewsById(News news) {
		newsDao.update("updateNewsById", news);
	}

	@Override
	public void deleteById(Long id) {
		newsDao.delete("deleteNewsById", id);
	}

	@Override
	public Page page(Page page, News news) {
		return newsDao.page(page, news);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(News news) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(News news) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(News news) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(News news) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<NewsVo> selectForList(News news) {
		return (List<NewsVo>) newsDao.selectForList("selectNews", news);
	}
	@Autowired
	public void setNewsDao(
			@Qualifier("newsDao")INewsDao newsDao) {
		this.newsDao = newsDao;
	}
}
