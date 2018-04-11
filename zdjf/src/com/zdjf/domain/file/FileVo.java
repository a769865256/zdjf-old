package com.zdjf.domain.file;

import java.util.List;

public class FileVo extends File {

	private static final long serialVersionUID = 865411391514737104L;
	public FileVo(){
		super();
	}
	public FileVo(Long id){
		super();
		this.id=id;
	}
	private List<File> fileList;
	public List<File> getFileList() {
		return fileList;
	}
	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

}
