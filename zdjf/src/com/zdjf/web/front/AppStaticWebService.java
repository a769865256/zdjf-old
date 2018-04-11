package com.zdjf.web.front;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.dao.activity.ISignDao;
import com.zdjf.domain.activity.Sign;
import com.zdjf.domain.advertise.Advertise;
import com.zdjf.domain.advertise.AdvertiseVo;
import com.zdjf.domain.notice.Notice;
import com.zdjf.domain.notice.NoticeVo;
import com.zdjf.service.activity.ISignService;
import com.zdjf.service.advertise.IAdvertiseService;
import com.zdjf.service.notice.INoticeService;
import com.zdjf.util.DateUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;

@Controller
@RequestMapping("/appStatic")
public class AppStaticWebService {
	
	private IAdvertiseService advertiseService;
	private INoticeService noticeService;
	private ISignDao signDao;
	
	@RequestMapping("/find")
	public String find(HttpServletRequest request,
            HttpServletResponse response,Model model){
		Notice notice=new Notice();
		notice.setType(6);
		notice.setIs_show(1);
		List<NoticeVo> noticeList=noticeService.selectForList(notice);
		model.addAttribute("noticeList",noticeList);
		return "front/m/find/find";
	}
	
	@RequestMapping("/about")
	public String about(HttpServletRequest request,
            HttpServletResponse response){
		return "front/m/find/about";
	}
	@RequestMapping("/safe")
	public String safe(HttpServletRequest request,
            HttpServletResponse response){
		return "front/m/find/safe";
	}
	@RequestMapping("/team")
	public String team(HttpServletRequest request,
            HttpServletResponse response){
		return "front/m/find/team";
	}
	@RequestMapping("/information")
	public String information(HttpServletRequest request,
            HttpServletResponse response){
		return "front/m/find/information";
	}
	@RequestMapping("/development")
	public String development(HttpServletRequest request,
            HttpServletResponse response){
		return "front/m/find/development";
	}
	@RequestMapping("/education")
	public String education(HttpServletRequest request,
            HttpServletResponse response){
		return "front/m/find/education";
	}
	
	@RequestMapping("/depository")
	public String depository(HttpServletRequest request,
            HttpServletResponse response){
		return "front/m/depository";
	}
	@RequestMapping("/active")
	public String active(HttpServletRequest request,
            HttpServletResponse response,Model model){
		Advertise advertise=new Advertise();
		advertise.setPosition(3);
		advertise.setIs_show(1);
		advertise.setWeb_site(2);
		List<AdvertiseVo> advertiseList=advertiseService.selectForList(advertise);
		model.addAttribute("list",advertiseList);
		return "front/m/active";
	}
	
	@RequestMapping("/customer")
	public String customer(HttpServletRequest request,
            HttpServletResponse response){
		return "front/m/find/customer";
	}
	@RequestMapping("/re_detail/{noticeId}")
	public String re_detail(HttpServletRequest request,
            HttpServletResponse response,@PathVariable String noticeId,Model model){
		if(StrUtils.emptyJudge(noticeId)){
			Notice notice=noticeService.selectForObjectById(Long.valueOf(noticeId));
			model.addAttribute("notice", notice);
		}
		return "front/m/find/re_detail";
	}
	@RequestMapping("/platform")
	public String platform(HttpServletRequest request,
            HttpServletResponse response,Model model){
		return "redirect:/static/zdjf_app/page/platform.html";
	}
	@RequestMapping("/sign")
	public String sign(HttpServletRequest request,
            HttpServletResponse response,Model model){
		String phone=RequestUtils.getReqString(request, "phone");
		String reg_source=RequestUtils.getReqString(request, "reg_source");
		String today=DateUtil.formatDate(new Date());
		if(StrUtils.emptyJudge(phone)){
			Sign sign=new Sign();
			sign.setCreate_date(today);
			sign.setPhone(phone);
			double totalcoin= 0.0;
			List<Sign> signList=(List<Sign>) signDao.selectForList("selectSign",sign);
			if(signList.size()>0){
				totalcoin=(double) signDao.selectForObject("selectSumCoin", sign);
				sign=signList.get(0);
			}
			model.addAttribute("totalcoin", totalcoin);
			model.addAttribute("nums", 1+sign.getNums()-sign.getUsed_nums());
			model.addAttribute("used_nums", sign.getUsed_nums());
		}else{
			model.addAttribute("totalcoin", "0.0");
			model.addAttribute("nums", 1);
			model.addAttribute("used_nums", 0);
		}
		model.addAttribute("phone", phone);
		model.addAttribute("reg_source", reg_source);
		
		return "front/m/sign";
	}

	@Autowired(required=true)
	public void setAdvertiseService(IAdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}
	@Autowired(required=true)
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}
	@Autowired(required=true)
	public void setSignDao(
			@Qualifier("signDao")ISignDao signDao) {
		this.signDao = signDao;
	}
	
	
}
