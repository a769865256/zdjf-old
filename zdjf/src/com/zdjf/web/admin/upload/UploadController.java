/*
 * UploadController.java
 * Copyright by ddy
 * Description：
 * Modified By：Administrator
 * Modified Time：2015年7月30日
 * Modified Number：
 * Modified Contents：
 */

package com.zdjf.web.admin.upload;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zdjf.util.Constants;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;

import net.sf.json.JSON;




/**
 *  文件上传Controller
 * 
 * @author guanhj
 * @version 2015年7月31日
 * @see UploadController
 * @since
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

	/** 日志管理 */
	private static final Logger logger = LoggerFactory.getLogger(
			UploadController.class);

	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadFiles(@RequestParam MultipartFile[] files,
			String dir,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Start UploadFile");
		// 返回数据
		Map<String, Object> resMap = new HashMap<String, Object>();
		// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
		// 这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
		String realPath = request.getSession().getServletContext().getRealPath(
				Constants.FILE_UPLOAD_DIR + dir);
		// 返回地址
		String serverPath = request.getContextPath() + Constants.STATIC_FILE_DIR
				+ Constants.FILE_UPLOAD_DIR + dir;
		// 设置响应给前台内容的数据格式
		response.setContentType(Constants.CONTENT_TYPE);
		// 如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		// 如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		// 上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		// 多文件路径集合
		List<String> filePaths = new ArrayList<String>(Constants.NUM_TEN);
		// 错误信息
		String errorMsg = Constants.NOTHING;
		// 是否有错误
		boolean hasError = false;
		// 文件个数
		int size = files.length;
		// 循环处理
		for (int i = 0; i < size; i++ ) {
			// 当前文件
			MultipartFile file = files[i];
			// 上传当前文件
			String msg = upload(serverPath, realPath, file);
			// 如果返回信息不是错误信息则写入路径集合
			if (!msg.equals(Constants.FILE_NULL_MSG)
					&& !msg.equals(Constants.FILE_UPLOAD_NG_MSG)) {
				filePaths.add(msg);
			} else {
				hasError = true;
				errorMsg = msg;
				break;
			}
		}
		// 转换返回信息
		if (hasError) {
			//            errorMsg = MsgUtils.getInstance().getMsg(errorMsg);
			resMap.put(Constants.JK_STATUS, Constants.STR_NEG_ONE);
			resMap.put(Constants.JK_ERROR_MSG, errorMsg);
		} else {
			resMap.put(Constants.JK_DETAIL, filePaths);
		}
		// 此时在Windows下输出的是[D:\Develop\apache-tomcat-6.0.36\webapps\AjaxFileUpload\\upload\愤怒的小鸟.jpg]
		// System.out.println(realPath + "\\" + originalFilename);
		// 此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]
		// System.out.println(request.getContextPath() + "/upload/" + originalFilename);
		// 不推荐返回[realPath + "\\" + originalFilename]的值
		// 因为在Windows下<img src="file:///D:/aa.jpg">能被firefox显示,而<img src="D:/aa.jpg">firefox是不认的
		logger.info("End UploadFile");
		// 返回结果
		return resMap;
	}

	@RequestMapping(value = "/uploadFile1", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadFile( MultipartFile file, String dir,
			String needClear,String needWaterMark,
			HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Start UploadFile");
		request.getParameter("upfile");
		// 返回数据
		Map<String, Object> resMap = new HashMap<String, Object>();
		// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
		// 这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
		//http
		//        String realPath = request.getSession().getServletContext().getRealPath(
		//            Constants.FILE_UPLOAD_DIR + dir);
		//https
		String realPath = "E://workspace/img/WebRoot/upload_files"+dir;
		// 异常判断
		//        if (StrUtils.emptyJudge(needClear)) {
		//            if (needClear.equals(Constants.STR_ONE)) {
		//            	main.java.com.sxcf.common.utils.FileUtils.deepClearPath(realPath);
		//            }
		//        }
		// 返回地址
		String serverPath = request.getContextPath() + Constants.STATIC_FILE_DIR
				+ Constants.FILE_UPLOAD_DIR + dir;
		serverPath="http://127.0.0.1:8080/img/upload_files"+dir;
		// 设置响应给前台内容的数据格式
		response.setContentType(Constants.CONTENT_TYPE);
		// 如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		// 如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		// 上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		String msg = upload(serverPath, realPath, file);

		if(needWaterMark!=null&&needWaterMark.equals("1")){
			//        	String planeImage = request.getSession().getServletContext().getRealPath(SxcfConfig.getWatermarkImageUrl()).replace("\\", "/");
			//            String smallImage = request.getSession().getServletContext().getRealPath(SxcfConfig.getWatermarkSmallImageUrl()).replace("\\", "/");
			//            String initTarget =  (realPath+"\\"+file.getOriginalFilename());
			//        	ImageMarkLogoUtil.markImageByIcon(resMap,planeImage,smallImage, initTarget, initTarget, -45);

		}
		// 转换返回信息
		if (msg.equals(Constants.FILE_NULL_MSG)
				|| msg.equals(Constants.FILE_UPLOAD_NG_MSG)) {
			//            msg = MsgUtils.getInstance().getMsg(msg);
			resMap.put(Constants.JK_STATUS, Constants.STR_NEG_ONE);
			resMap.put(Constants.JK_ERROR_MSG, msg);
		} else {
			resMap.put(Constants.JK_DETAIL, msg);
		}
		// 此时在Windows下输出的是[D:\Develop\apache-tomcat-6.0.36\webapps\AjaxFileUpload\\upload\愤怒的小鸟.jpg]
		// System.out.println(realPath + "\\" + originalFilename);
		// 此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]
		// System.out.println(request.getContextPath() + "/upload/" + originalFilename);
		// 不推荐返回[realPath + "\\" + originalFilename]的值
		// 因为在Windows下<img src="file:///D:/aa.jpg">能被firefox显示,而<img src="D:/aa.jpg">firefox是不认的
		logger.info("End UploadFile");
		// 返回结果
		return resMap;
	}

	@RequestMapping(value ="uploadFile",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String uploadImage(
			HttpServletRequest request,HttpServletResponse response)
					throws IllegalStateException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		//		ReturnUploadImage rui = null;//这个是UEditor需要的返回值内容，UEditor要的返回值需要封装成Json格式
		// 转型为MultipartHttpRequest：
		Map<String, Object> resMap = new HashMap<String, Object>();
		MultipartHttpServletRequest multipartRequest=(MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file =multipartRequest.getFile("upfile"); //UEditor传到后台的是这个upfile，其他的文件上传，应该也差不多是这个，还没去研究，断点一下就知道了

		// 写入文件
		String dir=RequestUtils.getReqString(request, "dir");
		Long advertiseId=0l;
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String date = df.format(new Date());
		Integer hashCode = UUID.randomUUID().toString().hashCode();
		if (hashCode < 0) {
			hashCode = -hashCode;
		}
		date = date + StrUtils.format(hashCode, 10);
		advertiseId=Long.parseLong(date);
		String realPath = "/home/tomcat_instances/tomcat_img/webapps/img/upload_files/"+dir+"/"+advertiseId;
		String serverPath="/upload_files/"+dir+"/"+advertiseId;
		response.setContentType(Constants.CONTENT_TYPE);
		// 如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
		// 如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
		// 上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		String msg = upload(serverPath, realPath, file);
		if (msg.equals(Constants.FILE_NULL_MSG)
				|| msg.equals(Constants.FILE_UPLOAD_NG_MSG)) {
			//	            msg = MsgUtils.getInstance().getMsg(msg);
			resMap.put("name", file.getOriginalFilename());//新的文件名  
			resMap.put("original", file.getOriginalFilename());//原始文件名  
			resMap.put("state", "FAIL");
		} else {
			resMap.put("name", file.getOriginalFilename());//新的文件名  
			resMap.put("original", file.getOriginalFilename());//原始文件名  
			resMap.put("size", file.getSize());  
			resMap.put("state", "SUCCESS");
			resMap.put("url", serverPath+"/"+file.getOriginalFilename());
		}
		return JsonUtil.objectTojson(resMap);
	}

	@RequestMapping(value = "/cutImg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> cutImg(String imgPath,String x, String y ,String imgW,String imgH,
			HttpServletRequest request,String width,String height,
			HttpServletResponse response) {
		logger.info("Start cutImg..................");
		// 返回数据
		Map<String, Object> resMap = new HashMap<String, Object>();
		String msg="";
		imgPath = imgPath.substring(imgPath.indexOf("/upload"));
		int x1 = StrUtils.convToInt(x);
		int y1 = StrUtils.convToInt(y);
		int w = StrUtils.convToInt(imgW);
		int h = StrUtils.convToInt(imgH);
		int w2 = StrUtils.convToInt(width);
		int h2 = StrUtils.convToInt(height);
		String planeImage = request.getSession().getServletContext().getRealPath(imgPath).replace("\\", "/");
		System.out.println("planeImage"+planeImage);
		//        ImageMarkLogoUtil.abscut(planeImage, planeImage, x1, y1, w, h);
		if(w2>0 &&h2 >0 && width !=null && height!=null){
			//            ImageMarkLogoUtil.reduceImg(planeImage, planeImage, w2, h2);
		}else if(height == null || width ==null){
			//            ImageMarkLogoUtil.reduceImg(planeImage, planeImage, 130, 130);
		}

		// 转换返回信息
		if (msg.equals(Constants.FILE_NULL_MSG)
				|| msg.equals(Constants.FILE_UPLOAD_NG_MSG)) {
			//            msg = MsgUtils.getInstance().getMsg(msg);
			resMap.put(Constants.JK_STATUS, Constants.STR_NEG_ONE);
			resMap.put(Constants.JK_ERROR_MSG, msg);
		} else {
			resMap.put(Constants.JK_DETAIL, msg);
		}
		// 此时在Windows下输出的是[D:\Develop\apache-tomcat-6.0.36\webapps\AjaxFileUpload\\upload\愤怒的小鸟.jpg]
		// System.out.println(realPath + "\\" + originalFilename);
		// 此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]
		// System.out.println(request.getContextPath() + "/upload/" + originalFilename);
		// 不推荐返回[realPath + "\\" + originalFilename]的值
		// 因为在Windows下<img src="file:///D:/aa.jpg">能被firefox显示,而<img src="D:/aa.jpg">firefox是不认的
		logger.info("End cutImg..................");
		// 返回结果
		return resMap;
	}
	/**
	 * Description: 上传文件<br>
	 * 
	 * @param serverPath
	 * @param realPath
	 * @param file
	 * @return String
	 */
	private String upload(String serverPath, String realPath,
			MultipartFile file) {
		if (null == file || file.isEmpty()) {
			return Constants.FILE_NULL_MSG;
		}
		// 上传文件的原名(即上传前的文件名字)
		String originalFilename = file.getOriginalFilename();
		try {
			// 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
			// 此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
			FileUtils.copyInputStreamToFile(file.getInputStream(),
					new File(realPath, originalFilename));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Constants.FILE_UPLOAD_NG_MSG;
		}
		return serverPath + originalFilename;
	}





	//    @RequestMapping(value = "/uploadApkFile", method = RequestMethod.POST)
	//    @ResponseBody
	//    public Map<String, Object> uploadApkFile(MultipartFile file, String dir,
	//                                          String needClear,
	//                                          HttpServletRequest request,
	//                                          HttpServletResponse response) {
	//        logger.info("Start uploadApkFile");
	//        // 返回数据
	//        Map<String, Object> resMap = RequestUtils.initBackJson();
	//        // 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
	//        // 这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
	////        String realPath = request.getSession().getServletContext().getRealPath(
	////                Constants.FILE_UPLOAD_DIR + dir);
	//        String realPath = SxcfConfig.getApkUploadUrl();
	//
	//        String subVersion = RequestUtils.getReqString(request, "subVersion");
	//        String dateTime = RequestUtils.getReqString(request, "dateTime");
	//        // 异常判断
	//        if (StrUtils.emptyJudge(needClear)) {
	//            if (needClear.equals(Constants.STR_ONE)) {
	//            	main.java.com.sxcf.common.utils.FileUtils.deepClearPath(realPath);
	//            }
	//        }
	//        // 返回地址
	////        String serverPath = request.getContextPath() + Constants.STATIC_FILE_DIR
	////                + Constants.FILE_UPLOAD_DIR + dir;
	//        String serverPath = SxcfConfig.getApkUploadUrl();
	//        // 设置响应给前台内容的数据格式
	//        response.setContentType(Constants.CONTENT_TYPE);
	//        // 如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
	//        // 如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
	//        // 上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
	//        String msg = uploadApk(serverPath, realPath, file ,dateTime);
	//        // 转换返回信息
	//        if (msg.equals(Constants.FILE_NULL_MSG)
	//                || msg.equals(Constants.FILE_UPLOAD_NG_MSG)) {
	//            msg = MsgUtils.getInstance().getMsg(msg);
	//            resMap.put(Constants.JK_STATUS, Constants.STR_NEG_ONE);
	//            resMap.put(Constants.JK_ERROR_MSG, msg);
	//        } else {
	//            resMap.put(Constants.JK_DETAIL, msg);
	//        }
	//        // 此时在Windows下输出的是[D:\Develop\apache-tomcat-6.0.36\webapps\AjaxFileUpload\\upload\愤怒的小鸟.jpg]
	//        // System.out.println(realPath + "\\" + originalFilename);
	//        // 此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]
	//        // System.out.println(request.getContextPath() + "/upload/" + originalFilename);
	//        // 不推荐返回[realPath + "\\" + originalFilename]的值
	//        // 因为在Windows下<img src="file:///D:/aa.jpg">能被firefox显示,而<img src="D:/aa.jpg">firefox是不认的
	//        logger.info("End uploadApkFile");
	//        // 返回结果
	//        return resMap;
	//    }

	//    /**
	//     * Description: 上传文件<br>
	//     *
	//     * @param serverPath
	//     * @param realPath
	//     * @param file
	//     * @return String
	//     */
	//    private String uploadApk(String serverPath, String realPath,
	//                          MultipartFile file ,String dateTime) {
	//        if (null == file || file.isEmpty()) {
	//            return Constants.FILE_NULL_MSG;
	//        }
	//
	//        Date currentTime = new Date();
	//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	//        String dateString = formatter.format(currentTime);
	//
	//        String newFilename ="_"+dateString+".apk";
	//        // 上传文件的原名(即上传前的文件名字)
	//        String originalFilename = file.getOriginalFilename();
	//        String test = originalFilename.substring(0,originalFilename.length()-4);
	//        originalFilename =test + newFilename;
	//        try {
	//            // 这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
	//            // 此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
	//            FileUtils.copyInputStreamToFile(file.getInputStream(),
	//                    new File(realPath, originalFilename));
	//        } catch (Exception e) {
	//            logger.error(e.getMessage());
	//            return Constants.FILE_UPLOAD_NG_MSG;
	//        }
	//        return originalFilename;
	//    }
}
