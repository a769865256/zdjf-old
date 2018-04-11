package com.zdjf.web.front;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.dao.fund.IFundsDao;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CommonUtils;
import com.zdjf.util.RequestUtils;

@Controller
@RequestMapping("/")
public class CapitalController {
	private IUserService userService;
	private IFundsDao fundsDao;
	@RequestMapping("capital/list")
	public String toList(HttpServletRequest request,
			HttpServletResponse response,Model model) throws ParseException{
		String user_name=BrowseUtil.getCookie(request, response);
		User user=userService.selectForObjectByPhone(user_name);
		Page page=PageUtils.createPage(request);
		Map<String, Object>map=new HashMap<String, Object>();
		String dateType=RequestUtils.getReqString(request, "dateIndex");
		String operateType=RequestUtils.getReqString(request, "typeIndex");
		String startTime=RequestUtils.getReqString(request, "startTime");
		String endTime=RequestUtils.getReqString(request, "endTime");
		Date date=new Date();
		if(dateType.equals("1")){

		}else if(dateType.equals("2")){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE,  - 7);
			map.put("time",  calendar.getTime());
		}else if(dateType.equals("3")){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH,  - 1);
			map.put("time",  calendar.getTime());
		}else if(dateType.equals("4")){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH,  - 3);
			map.put("time",  calendar.getTime());
		}else if(dateType.equals("5")){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH,  - 6);
			map.put("time",  calendar.getTime());
		}
		if(operateType.equals("1")){
			operateType="0";
		}else if(operateType.equals("2")){
			operateType="11";
		}else if(operateType.equals("3")){
			operateType="21";
		}else if(operateType.equals("4")){
			operateType="22";
		}else if(operateType.equals("5")){
			operateType="12";
		}else if(operateType.equals("6")){
			operateType="13";
		}
		map.put("pagename", "selectFundsList");
		map.put("total", "selectFundsListCn");
		map.put("user_id",user.getId());
		map.put("operate_type", operateType);
		if(endTime!=""){
			map.put("endTime", Timestamp.valueOf(endTime+" 00:00:00"));
		}
		if(startTime!=""){
			map.put("startTime", Timestamp.valueOf(startTime+" 00:00:00"));
		}
		page=fundsDao.page(page,map);
		model.addAllAttributes(CommonUtils.packageResult(true, page));
		return "front/mywealth/capital";
	}
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setFundsDao(
			@Qualifier("fundsDao")IFundsDao fundsDao) {
		this.fundsDao = fundsDao;
	}
	
}
