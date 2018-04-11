package com.zdjf.domain.notice;

import java.util.List;

public class NoticeVo extends Notice {
	
	public NoticeVo(){
		super();
	}
	public NoticeVo(Long id){
		super();
		this.id=id;
	}
	private static final long serialVersionUID = -3102739608800107578L;
	private List<Notice> noticeList;
	public List<Notice> getNoticeList() {
		return noticeList;
	}
	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}
	

}
