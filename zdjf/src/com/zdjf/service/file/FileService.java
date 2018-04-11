package com.zdjf.service.file;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.file.IFileDao;
import com.zdjf.domain.file.File;
import com.zdjf.domain.file.FileVo;

@Service
public class FileService implements IFileService{

	
	private IFileDao fileDao;
	
	@Override
	public Serializable save(File file) {
		// TODO Auto-generated method stub
		return fileDao.save(file);
	}

	@Override
	public File load(File file) {
		// TODO Auto-generated method stub
		return (File)(fileDao).reload(file);
	}
	
	@Override
	public void delete(File file) {
		// TODO Auto-generated method stub
		 fileDao.delete(file);
	}
	
	@Autowired
	public void setILenderDao(
			@Qualifier("fileDao") IFileDao  fileDao) {
		this.fileDao = fileDao;
	}

	@Override
	public void insertBatch(List<FileVo> fileList) {
		fileDao.save("insertFileBatch", fileList);
		
	}
	@Override
	public List getFileList(Map fileMap){
		return fileDao.selectForList("getFileList", fileMap);
	}
	

}
