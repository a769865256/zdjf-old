package com.zdjf.domain.fund;

import java.util.List;


public class GiftVo extends Gift {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8250488473924834320L;
	private List<Gift> giftList;
	public GiftVo() {
		super();
	}
	public GiftVo(Long id) {
		super();
		this.id=id;
	}
	public List<Gift> getGiftList() {
		return giftList;
	}
	public void setGiftList(List<Gift> giftList) {
		this.giftList = giftList;
	}
}
