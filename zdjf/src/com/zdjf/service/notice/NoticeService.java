package com.zdjf.service.notice;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.notice.INoticeDao;
import com.zdjf.domain.notice.Notice;
import com.zdjf.domain.notice.NoticeVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class NoticeService implements INoticeService {

	private INoticeDao noticeDao;
	@Override
	public Serializable save(Notice notice) {
		return noticeDao.save(notice);
	}

	@Override
	public void delete(Notice notice) {
		noticeDao.delete(notice);
	}

	@Override
	public void update(Notice notice) {
		noticeDao.update(notice);
	}

	@Override
	public Notice selectForObjectById(Long id) {
		Notice notice=new Notice();
		notice.setId(id);
		return (Notice) noticeDao.selectForObject("selectNoticeById", notice);
	}

	@Override
	public void updateNoticeById(Notice notice) {
		noticeDao.update("updateNoticeById", notice);
	}

	@Override
	public void deleteById(Long id) {
		noticeDao.delete("deleteNoticeById", id);
	}

	@Override
	public Page page(Page page, Notice notice) {
		return noticeDao.page(page, notice);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Notice notice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Notice notice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Notice notice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Notice notice) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public List<NoticeVo> selectForList(Notice notice) {
		return (List<NoticeVo>) noticeDao.selectForList("selectNotice", notice);
	}
	@Autowired
	public void setNoticeDao(
			@Qualifier("noticeDao")INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

}
