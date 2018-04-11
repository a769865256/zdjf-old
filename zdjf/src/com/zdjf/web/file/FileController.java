package com.zdjf.web.file;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zdjf.frame.spring.Result;
import com.zdjf.service.file.IFileService;

public class FileController {
	
	private IFileService fileService;
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)//添加管理用户
	public  Result addFile(HttpServletRequest request) {
	
		if (/*product != null*/true) {
			
			//productService.save(product);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@Autowired(required = true)
	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}

}
