package com.zdjf.service.notify;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.notify.INotifyLogDao;
import com.zdjf.domain.notify.NotifyLog;

@Service
public class NotifyService implements INotifyService{

	private INotifyLogDao notifyLogDao;
	
	public Serializable save(NotifyLog notifyLog){
		return notifyLogDao.save(notifyLog);
	}
	
	public NotifyLog selectNotifyLogByTradeNo(Map map){
		return (NotifyLog) notifyLogDao.selectForObject("selectNotifyLogByTradeNo", map);
	}
	
	@Autowired
	public void setINotifyLogDao(
			@Qualifier("notifyLogDao") INotifyLogDao  notifyLogDao) {
		this.notifyLogDao = notifyLogDao;
	}
	
}
