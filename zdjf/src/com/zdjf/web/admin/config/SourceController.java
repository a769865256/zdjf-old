package com.zdjf.web.admin.config;

import java.util.ArrayList;
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
import com.zdjf.domain.source.Source;
import com.zdjf.domain.source.SourceVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.source.ISourceService;
import com.zdjf.service.statistics.ISourceSurveyService;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;


@Controller
@RequestMapping("/config/source")
public class SourceController {
	@Autowired
	private ISourceService sourceService;
	@Autowired
	private IDataFieldService dataFieldService;
	@Autowired
	private ISourceSurveyService isss;
	@Autowired
	private ISourceService iss;
	@RequestMapping("/list")
	public String toPage(HttpServletRequest req, Model model){
		Page page=PageUtils.createPage(req);
		Source source=new Source();
		page=sourceService.page(page, source);
		
		DataField dataField=new DataField();
		dataField.setData_field_id("source_type");
		List<DataFieldVo> datafieldList=dataFieldService.selectForList(dataField);
		Map<String,Object> datafieldMap=new HashMap<String, Object>();
		for(int i=0;i<datafieldList.size();i++){
			datafieldMap.put(datafieldList.get(i).getData_field_value(), datafieldList.get(i));
		}
		List<Map<String, Object>> sourceList=(List<Map<String, Object>>) page.getDataList();
		for(int j=0;j<sourceList.size();j++){
			Map<String, Object> sourceMap=sourceList.get(j);
			String source_code=sourceMap.get("source_platform").toString();
			DataFieldVo source_name=(DataFieldVo) datafieldMap.get(source_code);
			sourceMap.put("source_type", source_name.getData_field_name());
		}
		
		model.addAttribute("detail",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		return "sysNew/config/source/list";

	}
	@RequestMapping("/toDetail")
	public String toDetail(HttpServletRequest req, Model model){
		List<DataFieldVo> source_type=new ArrayList<DataFieldVo>();
		DataField dataField=new DataField();
		dataField.setData_field_id("source_type");
		source_type=dataFieldService.selectForList(dataField);
		if(null!=req.getAttribute("source_id")&&""!=req.getAttribute("source_id")){
			Long source_id=0l;
			source_id=Long.parseLong((String) req.getAttribute("source_id"));
			Source source=new Source();
			source=sourceService.selectForObjectById(source_id);
			model.addAttribute("detail", source);
		}
		model.addAttribute("source_type",source_type);
		return "sysNew/config/source/detail";
	}

	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String create(HttpServletRequest req, Model model){
		String sourcePlatform=req.getParameter("sourcePlatform");
		String sourceName=req.getParameter("sourceName");
		String sourceCode=req.getParameter("sourceCode");
		String sourceUrl=req.getParameter("sourceUrl");
		Source source=new Source();
		source.setSource_platform(Integer.parseInt(sourcePlatform));
		source.setSource_name(sourceName);
		source.setSource_code(sourceCode);
		source.setSource_url(sourceUrl);
		source.setCreate_time(new Date());
		Long a=(Long)sourceService.save(source);
		Map<String,Object> map=new HashMap<String, Object>();
		
		if(a>0l){
			map.put("status", 1);
		}else{
			map.put("status", -1);
			map.put("errorMsg", "保存失败");
		}
		return "redirect:/config/source/list.action";

	}
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public  String update(HttpServletRequest req, Model model){
		String sourcePlatform=req.getParameter("sourcePlatform");
		String sourceName=req.getParameter("sourceName");
		String sourceCode=req.getParameter("sourceCode");
		String sourceId=RequestUtils.getReqString(req, "source_id");
		Source source=new Source();
		source.setSource_platform(Integer.parseInt(sourcePlatform));
		source.setSource_name(sourceName);
		source.setSource_code(sourceCode);
		source.setId(Long.parseLong(sourceId));
		sourceService.updateSourceById(source);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", 1);
		return "redirect:/config/source/list.action";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(HttpServletRequest req, Model model){
		String sourceId=req.getParameter("source_id");
		sourceService.deleteById(StrUtils.convToLong(sourceId));
		return "success";
	}
	
	@RequestMapping("/dailySource")
	public String dailySource(HttpServletRequest req, Model model){
		String source_name=RequestUtils.getReqString(req, "source_name");
		String start_time=RequestUtils.getReqString(req, "start_time");
		String end_time=RequestUtils.getReqString(req, "end_time");
		Source s=new Source();
		List<SourceVo> sourceList=iss.selectForList(s);
		Page page=PageUtils.createPage(req);
		Map map=new HashMap();
		map.put("pagename", "selectSourceSurveyPage");
		map.put("total", "selectSourceSurveyCount");
		map.put("source_name", source_name);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		page=isss.page(page, map);
		model.addAttribute("detail",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		model.addAttribute("sourceList", sourceList);
		model.addAttribute("source_name", source_name);
		model.addAttribute("start_time", start_time);
		model.addAttribute("end_time", end_time);
		return "sysNew/statistics/dailySource";
	}

}
