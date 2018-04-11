package com.zdjf.web.admin.sms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
import com.zdjf.domain.sms.Sms;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.sms.ISmsService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.UrlConstant;

@Controller
@RequestMapping("/sys/sms")
public class SysSmsController {
	private ISmsService smsService;
	private IDataFieldService dataFieldService;
	private IUserService userService;
	@RequestMapping("/smsContent")
	public String toSmsContent(HttpServletRequest req, Model model){
		Sms sms=new Sms();
		sms.setSms_type(1);
		Page page=PageUtils.createPage(req);
		page=smsService.page(page, sms);

		User user=new User();
		List<UserVo> userList=userService.selectForList(user);
		Map<String,Object> userMap=new HashMap<String, Object>();
		for(int i=0;i<userList.size();i++){
			userMap.put(userList.get(i).getPhone(), userList.get(i));
		}

		List<Map<String, Object>> smsList=(List<Map<String, Object>>) page.getDataList();
		for(int j=0;j<smsList.size();j++){
			Map<String, Object> smsMap=smsList.get(j);
			String phone=smsMap.get("phone").toString();
			UserVo uv=(UserVo) userMap.get(phone);
			smsMap.put("real_name", uv.getReal_name());
		}

		Map<String, Object>map = new HashMap<String, Object>();
		map.put("params",page);

		List<DataFieldVo> sms_status=new ArrayList<DataFieldVo>();
		DataField dataField=new DataField();
		dataField.setData_field_id("sms_status");
		sms_status=dataFieldService.selectForList(dataField);
		map.put("smsStatus",sms_status);
		List<DataFieldVo> sms_type=new ArrayList<DataFieldVo>();
		dataField.setData_field_id("sms_type");
		sms_type=dataFieldService.selectForList(dataField);
		map.put("smsType",sms_type);

		model.addAttribute("detail",map);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sysNew/user/sms/smscontent";
	}

	@RequestMapping("/smsCode")
	public String toSmsCode(HttpServletRequest req, Model model){
		Sms sms=new Sms();
		Page page=PageUtils.createPage(req);
		sms.setSms_type(0);
		page=smsService.page(page, sms);
		User user=new User();
		List<UserVo> userList=userService.selectForList(user);
		Map<String,Object> userMap=new HashMap<String, Object>();
		for(int i=0;i<userList.size();i++){
			userMap.put(userList.get(i).getPhone(), userList.get(i));
		}

		List<Map<String, Object>> smsList=(List<Map<String, Object>>) page.getDataList();
		for(int j=0;j<smsList.size();j++){
			Map<String, Object> smsMap=smsList.get(j);
			String phone=smsMap.get("phone").toString();
			if(userMap.containsKey(phone)){
				UserVo uv=(UserVo) userMap.get(phone);
				smsMap.put("real_name", uv.getReal_name());
			}
		}

		Map<String, Object>map = new HashMap<String, Object>();
		map.put("params",page);

		List<DataFieldVo> sms_status=new ArrayList<DataFieldVo>();
		DataField dataField=new DataField();
		dataField.setData_field_id("sms_status");
		sms_status=dataFieldService.selectForList(dataField);
		map.put("smsStatus",sms_status);
		List<DataFieldVo> sms_type=new ArrayList<DataFieldVo>();
		dataField.setData_field_id("sms_type");
		sms_status=dataFieldService.selectForList(dataField);
		map.put("smsType",sms_type);

		model.addAttribute("detail",map);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "sysNew/user/sms/smscode";
	}
	
	@Autowired(required = true)
	public void setSmsService(ISmsService smsService) {
		this.smsService = smsService;
	}
	@Autowired(required = true)
	public void setDataFieldService(IDataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
