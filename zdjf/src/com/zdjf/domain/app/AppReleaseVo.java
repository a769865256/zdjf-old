package com.zdjf.domain.app;

import java.util.List;

public class AppReleaseVo extends AppRelease {

	private static final long serialVersionUID = -7721233042785830538L;
	public AppReleaseVo(){
		super();
	}
	public AppReleaseVo(Long id){
		super();
		this.id=id;
	}
	private List<AppRelease> appRelease;
	public List<AppRelease> getAppRelease() {
		return appRelease;
	}
	public void setAppRelease(List<AppRelease> appRelease) {
		this.appRelease = appRelease;
	}
}
