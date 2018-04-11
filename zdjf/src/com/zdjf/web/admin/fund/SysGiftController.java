package com.zdjf.web.admin.fund;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.GiftVo;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.UserConstants;

@Controller
@RequestMapping("/sys/fund/gift")
public class SysGiftController {
private IDataFieldService dataFieldService;
	
	private IUserService userService;
	
	private IGiftService giftService;
	
	
	private IUserGiftService userGiftService;
	
	@RequestMapping("/toList")
	public String  gettoList(HttpServletRequest req, Model model) throws DocumentException {
		
		//红包管理
		Page page = PageUtils.createPage(req);
		Map<String, Object> params =new HashMap<String, Object>();
		
		params.put("page_name", "selectUserGiftGroupByGiftId");
		List<Map<String, Object>> userGiftList=(List<Map<String, Object>>) userGiftService.selectForList(params);
		params.remove("page_name");
		Map<String, Object> userGiftMap= new HashMap<String, Object>();
		for(int i=0;i<userGiftList.size();i++){
			Map<String, Object> map=userGiftList.get(i);
			if(map.containsKey("gift_id")&&map.containsKey("amount")){
				userGiftMap.put(map.get("gift_id").toString(), map.get("amount").toString());
			}
		}
		
		
		DataField dataField=new DataField();
		dataField.setData_field_id("search_type");
		params.put("searchType", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("user_login_status");
		params.put("userStatus", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("reg_source");
		params.put("regSource", dataFieldService.selectForList(dataField));
		
		
		
		
		// 取得请求中的当前页码
		int currPage = StrUtils.convToInt(
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_CURR_PAGE));
		// 取得请求中的每页显示条数
		int limit = StrUtils.convToInt(
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_LIMIT));
		// 取得请求中的状态
		params.put(UserConstants.REQ_PARAM_USER_STATUS,
		    StrUtils.convToInt(RequestUtils.getReqString(req,
		        UserConstants.REQ_PARAM_USER_STATUS)));
		// 取得请求中的查询类型参数
		params.put(Constants.REQ_PARAM_SEARCH_TYPE, StrUtils.convToInt(
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_SEARCH_TYPE)));
		// 取得请求中的查询类型参数
		if(StrUtils.convToInt(RequestUtils.getReqString(req, "type")) ==0){
		    params.put("type", 1);
		}else {
		    params.put("type", StrUtils.convToInt(
		            RequestUtils.getReqString(req, "type")));
		}
		
		// 取得请求中的查询名称参数
		params.put(Constants.REQ_PARAM_SEARCH_PARAM, RequestUtils.getReqString(
		    req, Constants.REQ_PARAM_SEARCH_PARAM, true));
		// 取得请求中的开始时间
		params.put(Constants.REQ_PARAM_START_DATE,
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_START_DATE));
		// 取得请求中的结束时间
		params.put(Constants.REQ_PARAM_END_DATE,
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_END_DATE));
		// 取得请求中的认证开始时间
		params.put(UserConstants.REQ_PARAM_AUTH_START_DATE,
		    RequestUtils.getReqString(req,
		        UserConstants.REQ_PARAM_AUTH_START_DATE));
		// 取得请求中的认证结束时间
		params.put(UserConstants.REQ_PARAM_AUTH_END_DATE,
		    RequestUtils.getReqString(req,
		        UserConstants.REQ_PARAM_AUTH_END_DATE));
		// 取得请求中的查询是否充值参数
		params.put(UserConstants.REQ_PARAM_IS_RECHARGED,
		    RequestUtils.getReqString(req,
		        UserConstants.REQ_PARAM_IS_RECHARGED));
		// 取得请求中的查询是否投资参数
		params.put("isPayed",RequestUtils.getReqString(req,"isPayed"));
		// 取得请求中的查询是否实名参数
		params.put("isRealName",RequestUtils.getReqString(req,"isRealName"));
		//取得请求参数注册来源
		params.put("regSource",StrUtils.convToInt(RequestUtils.getReqString(req,"regSource")));
		//可用余额最小金额
		params.put("balance",RequestUtils.getReqString(req,"balance"));
		params.put("balanceMax",RequestUtils.getReqString(req,"balanceMax"));
		//累计投资最小金额
		params.put("totalInvestMin",RequestUtils.getReqString(req,"totalInvestMin"));
		params.put("totalInvestMax",RequestUtils.getReqString(req,"totalInvestMax"));
		//用户类型：1，普通用户；2，出借人
		params.put("userType", RequestUtils.getReqString(req,"userType"));
		//渠道
		String sourceType = RequestUtils.getReqString(req,"sourceType");
		params.put("sourceType",sourceType );
		params.put(UserConstants.REQ_PARAM_LIMIT, StrUtils.convToInt(
		        RequestUtils.getReqString(req, UserConstants.REQ_PARAM_LIMIT)));
		
		params.put("pagename", "selectGiftPage");
		params.put("total", "selectGiftCount");
		
		page = giftService.page(page, params);
		
		List<Map<String, Object>> giftList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<giftList.size();i++){
			Map<String, Object> map=giftList.get(i);
			map.put("send_count", userGiftMap.get(map.get("id").toString()));
		}
		
		model.addAttribute("detail", page);
		model.addAttribute("params", params);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		
		return "sysNew/fund/gift/list";
	}
	
	@RequestMapping("/toReceive")
	public String toReceive(HttpServletRequest req, Model model){
		Page page=PageUtils.createPage(req);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pagename", "selectUserGiftPage");
		map.put("total", "selectUserGiftCount");
		page=userGiftService.page(page, map);
		
		User user=new User();
		List<UserVo> userList=userService.selectForList(user);
		Map<String, Object> userMap=new HashMap<String, Object>();
		for(int i=0;i<userList.size();i++){
			userMap.put(userList.get(i).getId().toString(), userList.get(i));
		}
		
		Gift gift=new Gift(); 
		List<GiftVo> giftList=giftService.selectForList(gift);
		Map<String, Object> giftMap=new HashMap<String, Object>();
		for(int i=0;i<giftList.size();i++){
			giftMap.put(giftList.get(i).getId().toString(), giftList.get(i));
		}
		
		List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> dataMap=dataList.get(i);
			String gift_id=dataMap.get("gift_id").toString();
			String user_id=dataMap.get("user_id").toString();
			GiftVo giftVo=(GiftVo) giftMap.get(gift_id);
			if(userMap.containsKey(user_id)){
				UserVo userVo=(UserVo) userMap.get(user_id);
				dataMap.put("phone", userVo.getPhone());
				dataMap.put("real_name", userVo.getReal_name());	
			}
			dataMap.put("gift_name", giftVo.getGift_name());
		}
		
		model.addAttribute("detail",page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		return "sysNew/fund/gift/receive";
	}
	
	@RequestMapping("/use")
	public String  getUser(HttpServletRequest req, Model model) throws DocumentException {
		
		//用户红包
		Page page = PageUtils.createPage(req);
		Map<String, Object> params =new HashMap<String, Object>();
		DataField dataField=new DataField();
		dataField.setData_field_id("search_type");
		params.put("searchType", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("user_login_status");
		params.put("userStatus", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("reg_source");
		params.put("regSource", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("source_type");
		params.put("sourceType", dataFieldService.selectForList(dataField));
		
		// 取得请求中的当前页码
		int currPage = StrUtils.convToInt(
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_CURR_PAGE));
		// 取得请求中的每页显示条数
		int limit = StrUtils.convToInt(
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_LIMIT));
		// 取得请求中的状态
		params.put(UserConstants.REQ_PARAM_USER_STATUS,
		    StrUtils.convToInt(RequestUtils.getReqString(req,
		        UserConstants.REQ_PARAM_USER_STATUS)));
		// 取得请求中的查询类型参数
		params.put(Constants.REQ_PARAM_SEARCH_TYPE, StrUtils.convToInt(
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_SEARCH_TYPE)));
		// 取得请求中的查询类型参数
		if(StrUtils.convToInt(RequestUtils.getReqString(req, "type")) ==0){
		    params.put("type", 1);
		}else {
		    params.put("type", StrUtils.convToInt(
		            RequestUtils.getReqString(req, "type")));
		}
		
		// 取得请求中的查询名称参数
		params.put(Constants.REQ_PARAM_SEARCH_PARAM, RequestUtils.getReqString(
		    req, Constants.REQ_PARAM_SEARCH_PARAM, true));
		// 取得请求中的开始时间
		params.put(Constants.REQ_PARAM_START_DATE,
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_START_DATE));
		// 取得请求中的结束时间
		params.put(Constants.REQ_PARAM_END_DATE,
		    RequestUtils.getReqString(req, Constants.REQ_PARAM_END_DATE));
		// 取得请求中的认证开始时间
		params.put(UserConstants.REQ_PARAM_AUTH_START_DATE,
		    RequestUtils.getReqString(req,
		        UserConstants.REQ_PARAM_AUTH_START_DATE));
		// 取得请求中的认证结束时间
		params.put(UserConstants.REQ_PARAM_AUTH_END_DATE,
		    RequestUtils.getReqString(req,
		        UserConstants.REQ_PARAM_AUTH_END_DATE));
		// 取得请求中的查询是否充值参数
		params.put(UserConstants.REQ_PARAM_IS_RECHARGED,
		    RequestUtils.getReqString(req,
		        UserConstants.REQ_PARAM_IS_RECHARGED));
		// 取得请求中的查询是否投资参数
		params.put("isPayed",RequestUtils.getReqString(req,"isPayed"));
		// 取得请求中的查询是否实名参数
		params.put("isRealName",RequestUtils.getReqString(req,"isRealName"));
		//取得请求参数注册来源
		params.put("regSource",StrUtils.convToInt(RequestUtils.getReqString(req,"regSource")));
		//可用余额最小金额
		params.put("balance",RequestUtils.getReqString(req,"balance"));
		params.put("balanceMax",RequestUtils.getReqString(req,"balanceMax"));
		//累计投资最小金额
		params.put("totalInvestMin",RequestUtils.getReqString(req,"totalInvestMin"));
		params.put("totalInvestMax",RequestUtils.getReqString(req,"totalInvestMax"));
		//用户类型：1，普通用户；2，出借人
		params.put("userType", RequestUtils.getReqString(req,"userType"));
		//渠道
		String sourceType = RequestUtils.getReqString(req,"sourceType");
		params.put("sourceType",sourceType );
		params.put(UserConstants.REQ_PARAM_LIMIT, StrUtils.convToInt(
		        RequestUtils.getReqString(req, UserConstants.REQ_PARAM_LIMIT)));
		
		params.put("pagename", "selectUserGiftPageNew");
		params.put("total", "selectUserGiftCount");
		
		page = userGiftService.page(page, params);
		User user=new User();
		List<UserVo> userList=userService.selectForList(user);
		Map<String, Object> userParams =new HashMap<String, Object>(userList.size());
		for(int i=0;i<userList.size();i++){
			userParams.put(userList.get(i).getId().toString(), userList.get(i));
		}
		List list=(List) page.getDataList();
		for(int j=0;j<list.size();j++){
			Map<String,Object> map= (Map<String, Object>) list.get(j);
			map.put("real_name", ((UserVo)userParams.get(map.get("user_id").toString())).getReal_name());
		}
		model.addAttribute("detail", page);
		model.addAttribute("params", params);
		model.addAttribute("userParams", userParams);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		
		return "sys/gift/use";
	}
	@RequestMapping("/toDetail")
	public String toDetail(HttpServletRequest req, Model model){
		String giftId=RequestUtils.getReqString(req, "giftId");
		String giftType=RequestUtils.getReqString(req, "giftType");
		if(giftType.equals("2")){
			Gift gift=giftService.selectForObjectById(Long.valueOf(giftId));
			model.addAttribute("detail", gift);
		}
		
		DataField dataField=new DataField();
		dataField.setData_field_id("gift_source");
		List<DataFieldVo> gift_source=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("coupon_use_type");
		List<DataFieldVo> date_type=dataFieldService.selectForList(dataField);
		dataField.setData_field_id("gift_status");
		List<DataFieldVo> gift_status=dataFieldService.selectForList(dataField);
		model.addAttribute("gift_source", gift_source);
		model.addAttribute("date_type", date_type);
		model.addAttribute("gift_status", gift_status);
		model.addAttribute("giftId", giftId);
		model.addAttribute("giftType", giftType);
		return "sysNew/fund/gift/detail";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest req, Model model){
		String giftId=RequestUtils.getReqString(req, "giftId");
		String giftType=RequestUtils.getReqString(req, "giftType");
		Gift gift=new Gift();
		if(giftType.equals("2")){
			gift.setId(StrUtils.convToLong(giftId));
			gift=giftService.selectForObjectById(Long.valueOf(giftId));
		}
		gift.setGift_name(RequestUtils.getReqString(req, "gift_name"));
		gift.setGift_source(StrUtils.convToInt(RequestUtils.getReqString(req, "gift_source")));
		gift.setMoney(StrUtils.convToDouble(RequestUtils.getReqString(req, "money")));
		gift.setStatus(StrUtils.convToInt(RequestUtils.getReqString(req, "status")));
		gift.setMax_count(StrUtils.convToInt(RequestUtils.getReqString(req, "max_count")));
		gift.setDate_type(StrUtils.convToInt(RequestUtils.getReqString(req, "date_type")));
		if(RequestUtils.getReqString(req, "date_type").equals("1")){
			gift.setStart_date(StrUtils.convToDate(RequestUtils.getReqString(req, "start_date")));
			gift.setEnd_date(StrUtils.convToDate(RequestUtils.getReqString(req, "end_date")));
		}else if(RequestUtils.getReqString(req, "date_type").equals("2")){
			gift.setGift_days(StrUtils.convToInt(RequestUtils.getReqString(req, "gift_days")));
		}
		gift.setMax_amount(StrUtils.convToDouble(RequestUtils.getReqString(req, "max_amount")));
		gift.setMax_days(StrUtils.convToInt(RequestUtils.getReqString(req, "max_days")));
		gift.setUse_type(StrUtils.convToInt(RequestUtils.getReqString(req, "use_type")));
		gift.setRemark(RequestUtils.getReqString(req, "remark"));
		if(giftType.equals("2")){
			giftService.updateGiftById(gift);
		}else if(giftType.equals("1")){
			giftService.save(gift);
		}
		return "";
	}
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public @ResponseBody String delete(HttpServletRequest req){
		String gift_id=RequestUtils.getReqString(req, "gift_id");
		giftService.deleteById(StrUtils.convToLong(gift_id));
		return "success";
	}
	@Autowired(required = true)
	public void setDataFieldService(IDataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setGiftService(IGiftService giftService) {
		this.giftService = giftService;
	}
	
	@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}
	
}
