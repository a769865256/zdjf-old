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
import com.zdjf.domain.fund.Coupon;
import com.zdjf.domain.fund.CouponVo;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.fund.ICoinService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.UserConstants;

@Controller
@RequestMapping("/sys/fund/coupon")
public class SysCouponController {
private IDataFieldService dataFieldService;
	
	private IUserService userService;
	
	private IGiftService giftService;
	
	private ICouponService couponService;
	private ICoinService coinService;
	
	private IUserGiftService userGiftService;
	
	private IUserCouponService userCouponService;
	@RequestMapping("/toList")
	public String  getCoupontoList(HttpServletRequest req, Model model) throws DocumentException {
		
		//加息券管理
		Page page = PageUtils.createPage(req);
		Map<String, Object> params =new HashMap<String, Object>();
		
		params.put("page_name", "selectUserCouponListByCouponId");
		List<Map<String, Object>> userCouponList=(List<Map<String, Object>>) userCouponService.selectForList(params);
		params.remove("page_name");
		Map<String, Object> userCouponMap= new HashMap<String, Object>();
		for(int i=0;i<userCouponList.size();i++){
			Map<String, Object> map=userCouponList.get(i);
			if(map.containsKey("coupon_id")&&map.containsKey("amount")){
				userCouponMap.put(map.get("coupon_id").toString(), map.get("amount").toString());
			}
		}
		
		DataField dataField=new DataField();
		dataField.setData_field_id("search_type");
		params.put("searchType", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("user_login_status");
		params.put("userStatus", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("reg_source");
		params.put("regSource", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("source_type");
		params.put("sourceType", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("coupon_source");
		params.put("couponType", dataFieldService.selectForList(dataField));
		
		
		
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
		
		params.put("pagename", "selectCouponPage");
		params.put("total", "selectCouponCount");
		page = couponService.page(page, params);
		
		List<Map<String, Object>> couponList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<couponList.size();i++){
			Map<String, Object> map=couponList.get(i);
			map.put("send_count", userCouponMap.get(map.get("id").toString()));
			String invalid_type=map.get("invalid_type").toString();
			if(invalid_type.equals("2")){
				map.put("invalid_value", String.format("获取后%s天", map.get("invalid_days").toString()));
			}
			if(invalid_type.equals("1")&&map.containsKey("invalid_start_date")&&map.containsKey("invalid_end_date")){
				map.put("invalid_value",String.format(
		                "%s至%s",
		                map.get("invalid_start_date").toString(),
		                map.get("invalid_end_date").toString()
		            ));
			}
		}
		
		model.addAttribute("detail", page);
		model.addAttribute("params", params);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		
		return "sysNew/fund/coupon/list";
	}
	
	@RequestMapping("/toDetail")
	public String toDetail(HttpServletRequest req, Model model){
		String giftId=RequestUtils.getReqString(req, "giftId");
		String giftType=RequestUtils.getReqString(req, "giftType");
		if(giftType.equals("2")){
			Coupon coupon=couponService.selectForObjectById(Long.valueOf(giftId));
			model.addAttribute("detail", coupon);
		}
		
		DataField dataField=new DataField();
		dataField.setData_field_id("coupon_source");
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
		return "sysNew/fund/coupon/detail";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(HttpServletRequest req, Model model){
		String giftId=RequestUtils.getReqString(req, "giftId");
		String giftType=RequestUtils.getReqString(req, "giftType");
		Coupon gift=new Coupon();
		if(giftType.equals("2")){
			gift.setId(StrUtils.convToLong(giftId));
			gift=couponService.selectForObjectById(Long.valueOf(giftId));
		}
		gift.setCoupon_name(RequestUtils.getReqString(req, "gift_name"));
		gift.setCoupon_source(StrUtils.convToInt(RequestUtils.getReqString(req, "gift_source")));
		gift.setInterest(StrUtils.convToDouble(RequestUtils.getReqString(req, "money")));
		gift.setStatus(StrUtils.convToInt(RequestUtils.getReqString(req, "status")));
		gift.setLimit_count(StrUtils.convToInt(RequestUtils.getReqString(req, "max_count")));
		gift.setInvalid_type(StrUtils.convToInt(RequestUtils.getReqString(req, "date_type")));
		if(RequestUtils.getReqString(req, "date_type").equals("1")){
			gift.setInvalid_start_date(StrUtils.convToDate(RequestUtils.getReqString(req, "start_date")));
			gift.setInvalid_end_date(StrUtils.convToDate(RequestUtils.getReqString(req, "end_date")));
		}else if(RequestUtils.getReqString(req, "date_type").equals("2")){
			gift.setInvalid_days(StrUtils.convToInt(RequestUtils.getReqString(req, "gift_days")));
		}
		gift.setMin_amount(StrUtils.convToDouble(RequestUtils.getReqString(req, "max_amount")));
		gift.setMin_days(StrUtils.convToInt(RequestUtils.getReqString(req, "max_days")));
		gift.setUse_type(StrUtils.convToInt(RequestUtils.getReqString(req, "use_type")));
		gift.setRemark(RequestUtils.getReqString(req, "remark"));
		if(giftType.equals("2")){
			couponService.updateCouponById(gift);
		}else if(giftType.equals("1")){
			couponService.save(gift);
		}
		return "";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody String delete(HttpServletRequest req){
		String gift_id=RequestUtils.getReqString(req, "gift_id");
		couponService.deleteById(StrUtils.convToLong(gift_id));
		return "success";
	}
	@RequestMapping("/coupon/toReceive")
	public String  getCoupontoReceive(HttpServletRequest req, Model model) throws DocumentException {
		
//		//用户加息券 
//		Page page = PageUtils.createPage(req);
//		Map<String, Object> params =new HashMap<String, Object>();
//		DataField dataField=new DataField();
//		dataField.setData_field_id("search_type");
//		params.put("searchType", dataFieldService.selectForList(dataField));
//		dataField.setData_field_id("user_login_status");
//		params.put("userStatus", dataFieldService.selectForList(dataField));
//		dataField.setData_field_id("reg_source");
//		params.put("regSource", dataFieldService.selectForList(dataField));
//		dataField.setData_field_id("source_type");
//		params.put("sourceType", dataFieldService.selectForList(dataField));
//		
//		
//		
//		// 取得请求中的当前页码
//		int currPage = StrUtils.convToInt(
//		    RequestUtils.getReqString(req, Constants.REQ_PARAM_CURR_PAGE));
//		// 取得请求中的每页显示条数
//		int limit = StrUtils.convToInt(
//		    RequestUtils.getReqString(req, Constants.REQ_PARAM_LIMIT));
//		// 取得请求中的状态
//		params.put(UserConstants.REQ_PARAM_USER_STATUS,
//		    StrUtils.convToInt(RequestUtils.getReqString(req,
//		        UserConstants.REQ_PARAM_USER_STATUS)));
//		// 取得请求中的查询类型参数
//		params.put(Constants.REQ_PARAM_SEARCH_TYPE, StrUtils.convToInt(
//		    RequestUtils.getReqString(req, Constants.REQ_PARAM_SEARCH_TYPE)));
//		// 取得请求中的查询类型参数
//		if(StrUtils.convToInt(RequestUtils.getReqString(req, "type")) ==0){
//		    params.put("type", 1);
//		}else {
//		    params.put("type", StrUtils.convToInt(
//		            RequestUtils.getReqString(req, "type")));
//		}
//		
//		// 取得请求中的查询名称参数
//		params.put(Constants.REQ_PARAM_SEARCH_PARAM, RequestUtils.getReqString(
//		    req, Constants.REQ_PARAM_SEARCH_PARAM, true));
//		// 取得请求中的开始时间
//		params.put(Constants.REQ_PARAM_START_DATE,
//		    RequestUtils.getReqString(req, Constants.REQ_PARAM_START_DATE));
//		// 取得请求中的结束时间
//		params.put(Constants.REQ_PARAM_END_DATE,
//		    RequestUtils.getReqString(req, Constants.REQ_PARAM_END_DATE));
//		// 取得请求中的认证开始时间
//		params.put(UserConstants.REQ_PARAM_AUTH_START_DATE,
//		    RequestUtils.getReqString(req,
//		        UserConstants.REQ_PARAM_AUTH_START_DATE));
//		// 取得请求中的认证结束时间
//		params.put(UserConstants.REQ_PARAM_AUTH_END_DATE,
//		    RequestUtils.getReqString(req,
//		        UserConstants.REQ_PARAM_AUTH_END_DATE));
//		// 取得请求中的查询是否充值参数
//		params.put(UserConstants.REQ_PARAM_IS_RECHARGED,
//		    RequestUtils.getReqString(req,
//		        UserConstants.REQ_PARAM_IS_RECHARGED));
//		// 取得请求中的查询是否投资参数
//		params.put("isPayed",RequestUtils.getReqString(req,"isPayed"));
//		// 取得请求中的查询是否实名参数
//		params.put("isRealName",RequestUtils.getReqString(req,"isRealName"));
//		//取得请求参数注册来源
//		params.put("regSource",StrUtils.convToInt(RequestUtils.getReqString(req,"regSource")));
//		//可用余额最小金额
//		params.put("balance",RequestUtils.getReqString(req,"balance"));
//		params.put("balanceMax",RequestUtils.getReqString(req,"balanceMax"));
//		//累计投资最小金额
//		params.put("totalInvestMin",RequestUtils.getReqString(req,"totalInvestMin"));
//		params.put("totalInvestMax",RequestUtils.getReqString(req,"totalInvestMax"));
//		//用户类型：1，普通用户；2，出借人
//		params.put("userType", RequestUtils.getReqString(req,"userType"));
//		//渠道
//		String sourceType = RequestUtils.getReqString(req,"sourceType");
//		params.put("sourceType",sourceType );
//		params.put(UserConstants.REQ_PARAM_LIMIT, StrUtils.convToInt(
//		        RequestUtils.getReqString(req, UserConstants.REQ_PARAM_LIMIT)));
//		
//		params.put("pagename", "selectUserPage");
//		params.put("total", "selectUserCount");
//		page = userService.page(page, params);
//		model.addAttribute("detail", page);
//		model.addAttribute("params", params);
//		model.addAllAttributes(PageUtils.createPagePar(page));
//		
//		
//		return "sys/coupon/receive";
		
			Page page=PageUtils.createPage(req);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("pagename", "selectUserCouponPage");
			map.put("total", "selectUserCouponCount");
			page=userCouponService.page(page, map);
			
			User user=new User();
			List<UserVo> userList=userService.selectForList(user);
			Map<String, Object> userMap=new HashMap<String, Object>();
			for(int i=0;i<userList.size();i++){
				userMap.put(userList.get(i).getId().toString(), userList.get(i));
			}
			
			Coupon coupon=new Coupon(); 
			List<CouponVo> couponList=couponService.selectForList(coupon);
			Map<String, Object> couponMap=new HashMap<String, Object>();
			for(int i=0;i<couponList.size();i++){
				couponMap.put(couponList.get(i).getId().toString(), couponList.get(i));
			}
			
			List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
			for(int i=0;i<dataList.size();i++){
				Map<String, Object> dataMap=dataList.get(i);
				String coupon_id=dataMap.get("coupon_id").toString();
				String user_id=dataMap.get("user_id").toString();
				CouponVo CouponVo=(CouponVo) couponMap.get(coupon_id);
				if(userMap.containsKey(user_id)){
					UserVo userVo=(UserVo) userMap.get(user_id);
					dataMap.put("phone", userVo.getPhone());
					dataMap.put("real_name", userVo.getReal_name());
				}
				dataMap.put("gift_name", CouponVo.getCoupon_name());
			}
			
			model.addAttribute("detail",page);
			model.addAllAttributes(PageUtils.createPagePar(page));
			
			return "sysNew/fund/coupon/receive";
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
	
	@Autowired(required = true)
	public void setCouponService(ICouponService couponService) {
		this.couponService = couponService;
	}
	
	@Autowired(required = true)
	public void setCoinService(ICoinService coinService) {
		this.coinService = coinService;
	}
	
	@Autowired(required = true)
	public void setUserCouponervice(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}
}
