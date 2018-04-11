package com.zdjf.web.admin.config;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
import com.zdjf.domain.notice.Notice;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.front.FrontService;
import com.zdjf.service.front.IFrontService;
import com.zdjf.service.notice.INoticeService;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;

@Controller
@RequestMapping("/config/notice")
public class NoticeController {
	private INoticeService noticeService;
	private IDataFieldService dataFieldService;
	private	IFrontService frontService;
	@RequestMapping("/toList")
	public	String toList(HttpServletRequest req, Model model){
		Notice notice=new Notice();
		Page page=PageUtils.createPage(req);
		page=noticeService.page(page, notice);
		DataField dataField=new DataField();
		dataField.setData_field_id("notice_type");
		List<DataFieldVo> notice_type=dataFieldService.selectForList(dataField);
		Map<String, Object> noticeTypeMap=new HashMap<String, Object>();
		for(int i=0;i<notice_type.size();i++){
			noticeTypeMap.put(notice_type.get(i).getData_field_value(),notice_type.get(i).getData_field_name() );
		}
		
		List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> dataMap=dataList.get(i);
			String noticeType=dataMap.get("type").toString();
			dataMap.put("type_text", noticeTypeMap.get(noticeType).toString());
		}
 		
		model.addAttribute("detail",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sysNew/config/notice/list";
	}
	@RequestMapping("/toDetail")
	public String toDetail(HttpServletRequest req, Model model){
		String noticeId=RequestUtils.getReqString(req, "noticeId");
		String noticeType=RequestUtils.getReqString(req, "noticeType");
		if(StrUtils.emptyJudge(noticeId)){
			Notice notice=new Notice();
			notice=noticeService.selectForObjectById(Long.valueOf(noticeId));
			model.addAttribute("detail", notice);
		}
		DataField dataField=new DataField();
		dataField.setData_field_id("notice_type");
		List<DataFieldVo> notice_type=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("advertise_web_site");
		List<DataFieldVo> notice_web_site=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("notice_is_show");
		List<DataFieldVo> notice_is_show=dataFieldService.selectForList(dataField);
		model.addAttribute("notice_type",notice_type);
		model.addAttribute("notice_web_site",notice_web_site);
		model.addAttribute("notice_is_show",notice_is_show);
		model.addAttribute("noticeType",noticeType);
		model.addAttribute("noticeId",noticeId);
		return "sysNew/config/notice/detail";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody String add(HttpServletRequest req, Model model){
		String noticeId=RequestUtils.getReqString(req, "noticeId");
		String noticeType=RequestUtils.getReqString(req, "noticeType");
		Notice notice=new Notice();
		if(noticeType.equals("2")){
			notice=noticeService.selectForObjectById(Long.valueOf(noticeId));
			notice.setCreate_time(notice.getCreate_time());
			notice.setUpdate_time(new Date());
		}else{
			notice.setCreate_time(new Date());
		}
		notice.setTitle(RequestUtils.getReqString(req, "title"));
		notice.setKeywords(RequestUtils.getReqString(req, "keywords"));
		notice.setContent(RequestUtils.getReqString(req, "content"));
		notice.setType(StrUtils.convToInt(RequestUtils.getReqString(req, "notice_type")));
		notice.setLink(RequestUtils.getReqString(req, "link"));
		notice.setIs_show(StrUtils.convToInt(RequestUtils.getReqString(req, "is_show")));
		notice.setOrder_number(StrUtils.convToInt(RequestUtils.getReqString(req, "order_number")));
		notice.setWeb_desc(RequestUtils.getReqString(req, "web_desc"));
		notice.setDescription(RequestUtils.getReqString(req, "description"));
		notice.setSource(RequestUtils.getReqString(req, "source"));
		notice.setWeb_site(StrUtils.convToInt(RequestUtils.getReqString(req, "web_site")));
		notice.setImage_url(RequestUtils.getReqString(req, "image_url"));
		if(noticeType.equals("1")){
			noticeService.save(notice);
		}else if(noticeType.equals("2")){
			notice.setUpdate_time(new Date());
			noticeService.updateNoticeById(notice);
		}
		frontService.reloadIndexCache();
		return "success";
	}
	@RequestMapping("/delete")
	public @ResponseBody String delete(HttpServletRequest req, Model model){
		String notice_id=RequestUtils.getReqString(req, "notice_id");
		noticeService.deleteById(Long.valueOf(notice_id));
		return "success";
	}
	@Autowired(required = true)
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}
	@Autowired(required = true)
	public void setDataFieldService(IDataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}
	@Autowired(required = true)
	public void setFrontService(IFrontService frontService) {
		this.frontService = frontService;
	}
	
}
