package com.zdjf.web.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zdjf.domain.activity.Sign;
import com.zdjf.domain.user.User;
import com.zdjf.service.activity.ISignService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.dto.Cache;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.service.front.IFrontService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/")
public class FrontController {

	private IFrontService frontService;

	@Autowired
	private ISignService signService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value="index", method=RequestMethod.GET)
	public String index(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		String domainName = request.getServerName();
		String cacheName = "";
		String source= RequestUtils.getReqString(request,"source");
		String reg_source=RequestUtils.getReqString(request,"reg_source");
		if(domainName.equals(UrlConstant.MOBILE_URL)){
			cacheName = "m";
		}else{
			cacheName = "pc";
		}
		if(source.equals("h5")){
			cacheName = "m";
		}
		Cache c = CacheManager.getCacheInfo(cacheName+"_index_cache");
		if(c==null || c.getTimeOut()<new Date().getTime()){
			frontService.reloadIndexCache();
			c = CacheManager.getCacheInfo(cacheName+"_index_cache");
		}
		Cache ca = (Cache)c.getValue();
		Map<String,Object> map = (Map<String,Object>)ca.getValue();
		if("2".equals(reg_source)){
			List productList=(List)map.get("productList");
			for(int i=0;i<productList.size();i++){
				ProductVo pv=(ProductVo) productList.get(i);
				pv.setProduct_code(pv.getProduct_code().replace("车财道", "正道金服"));
			}
		}
		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否来自手机端 手机端返回手机页面
			model.addAllAttributes(CommonUtils.packageResult(true, map));
			return "front/index/index";
		}else{
			String user_id=BrowseUtil.getCookie(request, response);
			if(null!=user_id&&""!=user_id){
				map.put("all", WealthController.getAllIncome(user_id));
				/**
				 * 2018-1-12 ADD 获取登录用户签到次数
				 */
				User signUser = userService.selectForObjectByPhone(user_id);
				//当日签到记录
				Sign sg = new Sign();
				sg.setUser_id(signUser.getId());
				sg.setCreate_date(DateUtil.formatDate(new Date()));
				sg.setStatus(1);
				List<Sign> list = signService.selectForList(sg);
				//当日已签到次数
				int signUsedNum = list.size();
				if (signUsedNum == 0 || signUser.getSign_num() > 0) {//当日未签到，有一次基础签到机会
					map.put("isSignTimes",1);//1 表示有签到机会
				} else if (signUser.getSign_num() == 0) {//当日已签到
					map.put("isSignTimes",0);//0 表示无签到机会
				}
			}
			map.put("user",frontService.getUser(request));
			model.addAllAttributes(CommonUtils.packageResult(true, map));
			response.setHeader("Access-Control-Allow-Origin", "*");
			return "front/index/index";
		}

	}

	/**
	 * 老版本url地址更新
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="appurl", method=RequestMethod.GET)
	public @ResponseBody JSONObject appurl(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = "{	\"respCode\":\"000\",	\"record\": {\"releaseVersion\": \"101\",\"isRelease\": \"1\",\"isForceUpdate\": \"1\","
				+ "	\"downUrl\": \"http://hftx.zdjfu.com/download/zdjfu_101_1.0.1_20171207_101_jiagu_sign.apk\",\"releaseContent\": \"银行存管上线\",		\"subVersion\": \"v1.0.0\",}}";
//		JSONObject jo = new JSONObject();
		System.out.println("app=============");
		return JSONObject.fromObject(str);
	}

	@RequestMapping(value="product", method=RequestMethod.GET)
	public String product(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		String domainName = request.getServerName();
		String pageStr = request.getParameter("page"); //当前页码
		String type = request.getParameter("type");   //类型
		String status = request.getParameter("status"); //状态
		String income = request.getParameter("income"); //利率(传进来的参数是1 2 3 4)
		String reg_source=RequestUtils.getReqString(request, "reg_source");
		Long page = 1L; 
		if(pageStr!=null &&!"".equals(pageStr)){
			page = Long.valueOf(pageStr);
		}
		try{
			Page p = new Page();
			p.setStart((page-1)*p.getLimit());
			Map map = new HashMap();
			if(type!=null && !"".equals(type)){
				map.put("product_type", type);
			}
			if(status!=null && !"".equals(status)){
				map.put("status", status);
			}
			if(income!=null && !"".equals(income)){
				map.put("income", income);
			}
			p = frontService.getProductPageList(p,map);
			if("2".equals(reg_source)){
				for(int i=0;i<p.getDataList().size();i++){
					ProductVo pv=(ProductVo) ((List)p.getDataList()).get(i);
					pv.setProduct_code(pv.getProduct_code().replace("车财道", "正道金服"));
				}
			}
			model.addAllAttributes(CommonUtils.packageResult(true, p));
		}catch (Exception e){
			e.printStackTrace();
		}

		if(domainName.equals(UrlConstant.MOBILE_URL)){  //判断是否来自手机端 手机端返回手机页面
			return "front/m_index/product";
		}else{
			return "front/index/product";
		}
	}


	@Autowired(required = true)
	public void setFrontService(IFrontService frontService) {
		this.frontService = frontService;
	}


}
