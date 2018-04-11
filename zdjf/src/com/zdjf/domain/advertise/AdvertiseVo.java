package com.zdjf.domain.advertise;

import java.util.List;

public class AdvertiseVo extends Advertise {
	
	public AdvertiseVo(){
		super();
	}
	public AdvertiseVo(Long id){
		super();
		this.id=id;
	}
	private static final long serialVersionUID = -4867488648673209003L;
	private List<Advertise> advertiseList;
	public List<Advertise> getAdvertiseList() {
		return advertiseList;
	}
	public void setAdvertiseList(List<Advertise> advertiseList) {
		this.advertiseList = advertiseList;
	}
	
	
}
