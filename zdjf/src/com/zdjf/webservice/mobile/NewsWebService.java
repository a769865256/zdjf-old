package com.zdjf.webservice.mobile;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.ValuetoString;
import com.zdjf.domain.notice.News;
import com.zdjf.domain.notice.NewsVo;
import com.zdjf.domain.notice.Notice;
import com.zdjf.domain.notice.NoticeVo;
import com.zdjf.domain.source.Source;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.notice.INewsService;
import com.zdjf.service.notice.INoticeService;
import com.zdjf.service.source.ISourceService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.Md5Util;
import com.zdjf.util.UrlConstant;
import com.zdjf.webservice.express.SmsReturn;

@Controller
@RequestMapping("/m/news")
public class NewsWebService {
	
	private INewsService newsService;
	
	private INoticeService noticeService;
	
	private IUserService userService;
	
	private ISourceService sourceService;
	
	//添加消息的信息
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getNewsAdd(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String url = request.getParameter("url");
		
		News news = new News();
		User user = userService.selectForObjectByPhone(user_name);
		news.setContent(content);
		news.setTitle(title);
		news.setUrl(url);
		news.setCreate_time(new Date());
		news.setUser_id(user.getId());
		news.setIs_read(1);
		newsService.save(news);
		
		sr.setMapContent(news);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	//获取消息列表
	@RequestMapping(value="/allread",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getAllRead(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if( null==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
	
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		
		}
		
		
		News news = new News();
		User user = userService.selectForObjectByPhone(user_name);
		news.setUser_id(user.getId());
		
		List<NewsVo> list = newsService.selectForList(news);
		for(int i = 0;i<list.size();i++){
			NewsVo ne = list.get(i);
			/*News ns = new News();
			BeanUtils.copyProperties(ne,ns);*/
			ne.setIs_read(2);
			newsService.updateNewsById(ne);
		}
		
		
		sr.setContent("全部已读");
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	//获取消息列表
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getNewsList(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String url = request.getParameter("url");
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		
		if( null==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
	
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		
		}
		
		News news = new News();
		User user = userService.selectForObjectByPhone(user_name);
		news.setUser_id(user.getId());
		
		
		Page page = PageUtils.createPage(request);
		page = newsService.page(page, news);
		int currentPage = page.getCurrentPage().intValue();
		int limit = page.getLimit().intValue();
		
		int total = page.getTotal().intValue();
		int start = limit * (currentPage - 1);
		int end = total >= limit*currentPage?limit*currentPage:total;
		
		//Map<String,String> st = ValuetoString.eachProperties(news);
		
		List<NewsVo> list = newsService.selectForList(news);
		
		List<Map<String,String>> listS = new ArrayList<Map<String,String>>();
		for(int i = start;i<end;i++){
			NewsVo vo = (NewsVo)list.get(i);
			News ns = new News();
			BeanUtils.copyProperties(vo,ns);
			/*Map<String,String> map = ValuetoString.eachProperties(ns);
			
			listS.add(map);*/
			
			Map<String,String> map = ValuetoString.eachProperties(ns);
			
			listS.add(map);
		}
		
		page.setDataList(listS);
		
		
		sr.setContent(page);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	@RequestMapping(value="/h",method=RequestMethod.POST)
	public @ResponseBody SmsReturn addH5(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		SmsReturn sr = new SmsReturn();
		
		String source_platform = request.getParameter("source_platform");
		
		String source_code = request.getParameter("source_code");
		String source_name = request.getParameter("source_name");
		
		Source sou = new Source();
		
		sou.setCreate_time(new Date());
		sou.setSource_code(source_code);
		sou.setSource_name(source_name);
		sou.setSource_platform(Integer.parseInt(source_platform));
		
		
		String url = UrlConstant.API_H5_REG_URL + source_code + "&reg_source=4";
		sou.setSource_url(url);
		
		sourceService.save(sou);
		
		sr.setContent(url);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	//获取公告列表
	@RequestMapping(value="/notice",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getNotice(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		SmsReturn sr = new SmsReturn();
		/*String user_name = request.getParameter("phone");
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if( null==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
	
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		
		}*/
		
		
		Notice news = new Notice();
		
		
		Page page = PageUtils.createPage(request);
		page = noticeService.page(page, news);
		int currentPage = page.getCurrentPage().intValue();
		int limit = page.getLimit().intValue();
		
		int total = page.getTotal().intValue();
		int start = limit * (currentPage - 1);
		int end = total >= limit*currentPage?limit*currentPage:total;
		
		List<NoticeVo> list = noticeService.selectForList(news);
		
		Collections.sort(list,new Comparator<NoticeVo>(){
            public int compare(NoticeVo arg0, NoticeVo arg1) {
                return -arg0.getCreate_time().compareTo(arg1.getCreate_time());
            }
	    });
		
		List<Map<String,String>> listS = new ArrayList<Map<String,String>>();
		for(int i = start;i<end;i++){
			NoticeVo ne = list.get(i);
			Notice ns = new Notice();
			BeanUtils.copyProperties(ne,ns);
			
			Map<String,String> map = ValuetoString.eachProperties(ns);
			
			listS.add(map);
		}
		page.setDataList(listS);
		
		sr.setContent(page);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	//获取消息详情
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getNewsDetails(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("phone");
		String news_id = request.getParameter("news_id");
		String is_read = request.getParameter("is_read");
		String url = request.getParameter("url");
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		
		if( null==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
	
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		
		}
		
		News news = new News();
		User user = userService.selectForObjectByPhone(user_name);
		news.setUser_id(user.getId());
		news.setId(Long.parseLong(news_id));
		
		List<NewsVo> list = newsService.selectForList(news);
		
		News ne = list.get(0);
		if(is_read!=null &&is_read.equalsIgnoreCase("0"))
			ne.setIs_read(2);
		
		newsService.updateNewsById(ne);
		
		NewsVo vo = (NewsVo)list.get(0);
		News ns = new News();
		BeanUtils.copyProperties(vo,ns);
		
		sr.setContent(ValuetoString.eachProperties(ns));
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	@Autowired(required = true)
	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
	
	@Autowired(required = true)
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setSourceService(ISourceService sourceService) {
		this.sourceService = sourceService;
	}

}
