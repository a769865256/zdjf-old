package com.zdjf.service.notify;

import java.io.Serializable;
import java.util.Map;

import com.zdjf.domain.notify.NotifyLog;

public interface INotifyService {

	public abstract Serializable save(NotifyLog notifyLog);
	
	public abstract NotifyLog selectNotifyLogByTradeNo(Map map);
	
}
