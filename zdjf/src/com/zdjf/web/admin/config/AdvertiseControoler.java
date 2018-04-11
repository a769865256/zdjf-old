package com.zdjf.web.admin.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.domain.advertise.Advertise;
import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.advertise.IAdvertiseService;
import com.zdjf.service.common.DataFieldService;
import com.zdjf.service.front.IFrontService;
import com.zdjf.util.AdvConstants;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.webservice.express.SmsReturn;



@Controller
@RequestMapping("/config/advertise")
public class AdvertiseControoler {
	private IAdvertiseService advertiseService;
	private DataFieldService dataFieldService;
	private	IFrontService frontService;
	@RequestMapping("/toList")
	public	String toList(HttpServletRequest req, Model model){
		Advertise advertise=new Advertise();
		Page page=PageUtils.createPage(req);
		page=advertiseService.page(page, advertise);
		model.addAttribute("detail",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sysNew/config/advertise/list";
	}
	@RequestMapping("/toDetail")
	public	String toDetail(HttpServletRequest req, Model model){
		String advertiseId=RequestUtils.getReqString(req, "advertiseId");
		String advertiseType=RequestUtils.getReqString(req, "advertiseType");
		if(StrUtils.emptyJudge(advertiseId)){
			Advertise ad=advertiseService.selectForObjectById(Long.valueOf(advertiseId));
			model.addAttribute("detail", ad);
		}
		DataField dataField=new DataField();
		dataField.setData_field_id("advertise_web_site");
		List<DataFieldVo> advertise_web_site=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("advertise_position");
		List<DataFieldVo> advertise_position=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("advertise_is_show");
		List<DataFieldVo> advertise_is_show=dataFieldService.selectForList(dataField);
		model.addAttribute("advertiseId",advertiseId);
		model.addAttribute("advertiseType",advertiseType);
		model.addAttribute("advertise_web_site",advertise_web_site);
		model.addAttribute("advertise_position",advertise_position);
		model.addAttribute("advertise_is_show",advertise_is_show);
		return "sysNew/config/advertise/detail";
	}
	@RequestMapping(value="create",method = RequestMethod.POST)
	public @ResponseBody SmsReturn create(HttpServletRequest req,
			Model model){
		SmsReturn sr=new SmsReturn();
		String advertiseId=RequestUtils.getReqString(req, "advertiseId");
		String advertiseType=RequestUtils.getReqString(req, "advertiseType");
		Advertise adv=new Advertise();
		if(advertiseType.equals("2")){
			adv=advertiseService.selectForObjectById(Long.valueOf(advertiseId));
		}
		// 广告位ID
		// 站点：1、web；2、ios；3、android；
		adv.setWeb_site(StrUtils.convToInt(
				RequestUtils.getReqString(req, AdvConstants.FIELD_WEB_SITE)));
		// 存放位置：1、首页；
		adv.setPosition(StrUtils.convToInt(
				RequestUtils.getReqString(req, AdvConstants.FIELD_POSITION)));
		// 标题
		adv.setTitle(RequestUtils.getReqString(req, AdvConstants.FIELD_TITLE));
		// 图片alt属性
		adv.setAlt(RequestUtils.getReqString(req, AdvConstants.FIELD_ALT));
		// 图片存放地址
		String image_url= RequestUtils.getReqString(req, AdvConstants.FIELD_IMAGE_URL);
		image_url=image_url.substring(image_url.indexOf("src=\"")+5, (image_url.indexOf("\" ")>(image_url.indexOf("src=\"")+5))?image_url.indexOf("\" "):image_url.indexOf("\"/"));
		adv.setImage_url(image_url);
		// 图片超链接
		adv.setHref_url(
				RequestUtils.getReqString(req, AdvConstants.FIELD_HREF_URL));
		// 序号
		adv.setOrder_number(StrUtils.convToInt(
				RequestUtils.getReqString(req, AdvConstants.FIELD_ORDER_NUMBER)));
		// 是否显示：1、显示；2、不显示
		adv.setIs_show(StrUtils.convToInt(
				RequestUtils.getReqString(req, AdvConstants.FIELD_SHOW)));
		// 创建时间
		adv.setCreate_time(new Date());
		if(advertiseType.equals("2")){
			advertiseService.updateAdvertiseById(adv);
		}else{
			advertiseService.save(adv);
		}
		frontService.reloadIndexCache();
		sr.setStatus(1);
		return sr;
	}
	@RequestMapping("/delete")
	public @ResponseBody String delete(HttpServletRequest req, Model model){
		String advertise_id=RequestUtils.getReqString(req, "advertise_id");
		advertiseService.deleteById(Long.valueOf(advertise_id));
		return "success";
	}
	@Autowired(required = true)
	public void setAdvertiseService(IAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}
	@Autowired(required = true)
	public void setDataFieldService(DataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}
	@Autowired(required = true)
	public void setFrontService(IFrontService frontService) {
		this.frontService = frontService;
	}

}
