package com.zdjf.web.front;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.notice.Notice;
import com.zdjf.domain.notice.NoticeVo;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.notice.INoticeService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.DateUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.UrlConstant;
import com.zdjf.webservice.express.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
@PropertySource(value= "classpath:project.properties")
public class CommonController {

	@Autowired
	private INoticeService noticeService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductBuyService productBuyService;

	@Autowired
	private ICoinStreamService coinStreamService;

	@RequestMapping(value="/notice/test", method = RequestMethod.GET)
	public String test(){
		Notice notice=new Notice();
		List<NoticeVo> noticeList=noticeService.selectForList(notice);
		for(int i=0;i<noticeList.size();i++){
			notice=noticeList.get(i);
			String url=notice.getImage_url();
			if(null!=url&&url.indexOf("www.zdjfu.com")>-1){
				notice.setImage_url(url.replace("https://www.zdjfu.com", "http://img.zdjfu.com"));
			}
			noticeService.updateNoticeById(notice);
		}
		return "front/notice";
	}

	@RequestMapping(value="/notice", method = RequestMethod.GET)
	public String mynotice(HttpServletRequest request,Model model){
		String notice_id=RequestUtils.getReqString(request, "notice_id");
		Notice notice=noticeService.selectForObjectById(StrUtils.convToLong(notice_id));
		model.addAttribute("notice", notice);
		return "front/notice";
	}

	/**
	 * banner 银行存管
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/depository", method = RequestMethod.GET)
	public String depository(HttpServletRequest request,
							 HttpServletResponse response,Model model){
		String user_name = BrowseUtil.getCookie(request, response);
		User user = userService.selectForObjectByPhone(user_name);
		model.addAttribute("user",user);
		return "front/active/depository";
	}

	/**
	 * 跳转至平台数据 web端
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/platform/data", method = RequestMethod.GET)
	public String platformData(HttpServletRequest request, Model model) {
		request.setAttribute("showType","data");
		//累计注册人数
		long userCount = userService.userCount();
		model.addAttribute("userCount", new DecimalFormat(",###").format(userCount));
		//累计成交额
		Double sumAmt = productBuyService.staUserProductBuy();
		model.addAttribute("sumAmt",new DecimalFormat("#,###.00").format(sumAmt));
		//累计投资笔数
		long invtNum = productBuyService.staInvtNum();
		model.addAttribute("invtNum",new DecimalFormat(",###").format(invtNum));
		//累计为用户赚取收益
		Double totalIncomed = userService.staUserTotalIncomed();
		model.addAttribute("totalIncomed", new DecimalFormat("#,###.00").format(totalIncomed));

		return "front/info/platformData";
	}

	/**
	 * 平台数据页面跳至上半年运营报告页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/platdata/tosbnReport", method = RequestMethod.GET)
	public String toSbnReportFromPlatData(HttpServletRequest request) {
		request.setAttribute("showType","data");
		return "front/info/sbnbg_2017";
	}

	/**
	 * 平台数据页面跳至第三季度运营报告页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/platdata/tojdReport", method = RequestMethod.GET)
	public String toJdReportFromPlatData(HttpServletRequest request) {
		request.setAttribute("showType","data");
		return "front/info/jdyybg_2017";
	}

	/**
	 * 平台数据接口
	 * @return
	 */
	@RequestMapping(value = "/info/platformData", method = RequestMethod.POST)
	@ResponseBody
	public JsonReturn platformData() {
		JsonReturn jr = new JsonReturn();
		Map<String,Object> dataMap = new HashMap<>();
		//累计注册人数
		long userCount = userService.userCount();
		dataMap.put("userCount",userCount);
		//累计成交额
		Double sumAmt = productBuyService.staUserProductBuy();
		dataMap.put("sumAmt",sumAmt);
		//累计投资笔数
		long invtNum = productBuyService.staInvtNum();
		dataMap.put("invtNum",invtNum);
		//累计为用户赚取收益
		Double totalIncomed = userService.staUserTotalIncomed();
		dataMap.put("totalIncomed",totalIncomed);
		//累计男性投资人数
		double invtTotalMan = productBuyService.staInvtManUsers();
		//累计女性投资人数
		double invtTotalWoman = productBuyService.staInvtWomanUsers();
		double invtTotalUsers = invtTotalMan + invtTotalWoman;
		dataMap.put("manPercent",new DecimalFormat("#.##%").format(invtTotalMan/invtTotalUsers));
		dataMap.put("womanPercent",new DecimalFormat("#.##%").format(invtTotalWoman/invtTotalUsers));
		//平台运营时间：2016-12-08
		dataMap.put("omDays", DateUtil.between(DateUtil.parseDateTimes("2016-12-08","yyyy-MM-dd"),new Date()));
		jr.setStatus(100);
		jr.setContent(dataMap);
		return jr;
	}

	/**
	 * 跳转至公司简介
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/company/profile", method = RequestMethod.GET)
	public String companyProfile(HttpServletRequest request) {
		request.setAttribute("showType","profile");
		return "front/info/companyProfile";
	}

	/**
	 * 跳转至公司大事记
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/company/things", method = RequestMethod.GET)
	public String companyThings(HttpServletRequest request) {
		request.setAttribute("showType","things");
		return "front/info/things";
	}

	/**
	 * 跳转至安全保障
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/insurance", method = RequestMethod.GET)
	public String insurance(HttpServletRequest request) {
		request.setAttribute("showType","secure");
		return "front/info/insurance";
	}

	/**
	 * 跳转至公司动态页面
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/company/{type}/notice", method = RequestMethod.GET)
	public ModelAndView noticeMain(HttpServletRequest request,@PathVariable String type) {

		String currPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("limit");
		pageSize = pageSize == null ? "10" : pageSize;
		ModelAndView mv = new ModelAndView("front/info/noticeMain");
		Notice notice = new Notice();
		notice.setType(Integer.parseInt(type));//1平台公告,6媒体公告
		Page p = new Page();
		Long pageNum = Long.valueOf(currPage == null ? "1" : currPage);
		p.setCurrentPage(pageNum);
		if (Long.valueOf(pageNum) > 1) {
			p.setStart((pageNum-1)*Long.valueOf(pageSize));
		}

		p.setLimit(Long.parseLong(pageSize));
		Page noticePage = noticeService.page(p,notice);
		mv.addObject("noticePage", noticePage);
		mv.addObject("showType",type);
		mv.addObject("type","notice");
		return mv;
	}

	/**
	 * 跳转至联系我们
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactUs(HttpServletRequest request) {
		request.setAttribute("showType","contact");
		return "front/info/contactUs";
	}

	/**
	 * 页面头部帮助中心跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/help/main", method = RequestMethod.GET)
	public String helpMain(HttpServletRequest request) {
	    request.setAttribute("showType", "helpMain");
		return "front/help/helpMain";
	}

	/**
	 * 页面头部新手指引跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/newHand/guide", method = RequestMethod.GET)
	public String newHandGuide(HttpServletRequest request) {
		request.setAttribute("showType","newHand");
		return "front/newhand/guide";
	}

	/**
	 * 页面头部下载app跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/downLApp", method = RequestMethod.GET)
	public String downloadApp(HttpServletRequest request) {
		request.setAttribute("showType","downLApp");
		return "front/common/download_app";
	}

	/**
	 * 页面底部跳转至帮助中心子模块
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/{type}/{secMenu}/help", method = RequestMethod.GET)
	public String helpChild(HttpServletRequest request, @PathVariable String type, @PathVariable String secMenu) {
		request.setAttribute("showType", type);
		request.setAttribute("secMenu", secMenu);
		return "front/help/help";
	}

	/**
	 * 跳转至新手专享页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newHand/award", method = RequestMethod.GET)
	public String newHandAward(HttpServletRequest request, HttpServletResponse response,Model model) {
		String domainName = request.getServerName();
		String user_name = "";
		if(UrlConstant.MOBILE_URL.equals(domainName)){  //判断是否来自手机端 手机端返回手机页面
			user_name = request.getParameter("phone");
		}else{
			user_name = BrowseUtil.getCookie(request, response);
		}
		User user = userService.selectForObjectByPhone(user_name);
		if (user != null) {
			Map<String,Object> hashMap = new HashMap<>();
			hashMap.put("userId",user.getId());
			//查询用户总投资额
			Long totalInvest = productBuyService.selectUserTotalInvest(hashMap);
			model.addAttribute("totalInvest",totalInvest);
			//查询用户是否有单笔投资>=1000的记录  0否1是
			int investFlag = productBuyService.selectUserFirstInvest(hashMap);
			model.addAttribute("investFlag",investFlag);
		}
		model.addAttribute("user",user);
		if(UrlConstant.MOBILE_URL.equals(domainName)){  //判断是否来自手机端 手机端返回手机页面
			return "front/m/welfare_register";
		}else{
			return "front/active/newHand";
		}
	}

	/**
	 * banner新年活动页面
	 * @return
	 */
	@RequestMapping(value = "/toAnnualBonus", method = RequestMethod.GET)
	public String toAnnualBonus() {
		return "front/active/annualBonus";
	}

	/**
	 * banner新年活动页面2
	 * @return
	 */
	@RequestMapping(value = "/toNewYearFestival", method = RequestMethod.GET)
	public String toNewYearFestival() {
		return "front/active/newYearFestival";
	}

	/**
	 * 至玩转正经值页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/playPositiveValue", method = RequestMethod.GET)
	public String toPlayPositiveValue(HttpServletRequest request, HttpServletResponse response,Model model) {
		String domainName = request.getServerName();
		String user_name = "";
		if(UrlConstant.MOBILE_URL.equals(domainName)){  //判断是否来自手机端 手机端返回手机页面
			user_name = request.getParameter("phone");
		}else{
			user_name = BrowseUtil.getCookie(request, response);
		}
		if (StrUtils.emptyJudge(user_name)) {
			User user = userService.selectForObjectByPhone(user_name);
			model.addAttribute("user",user);
			//查询下载app获得正经值记录
			List<Map<String, Object>> list1 = coinStreamService.selectUserCoinForListByType(user.getId(),46);
			int downloadApp = list1.size();
			model.addAttribute("downloadApp",downloadApp);//0 未达成
			//查询投资获得的正经值记录
			List<Map<String, Object>> list2 = coinStreamService.selectUserCoinForListByInvest(user.getId());
			int coinsByInvestTimes = list2.size();
			model.addAttribute("coinsByInvestTimes",coinsByInvestTimes);//0 未达成
		}
		return "front/active/playPositiveValue";
	}

	/**
	 * banner跳至运营报告页面
	 * @return
	 */
	@RequestMapping(value = "/toOperationReport", method = RequestMethod.GET)
	public String toOperationReport() {
		return "front/operation/operation_rep";
	}
}