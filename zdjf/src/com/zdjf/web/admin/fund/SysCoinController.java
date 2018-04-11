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

import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
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
@RequestMapping("/sys/fund")
public class SysCoinController {
private IDataFieldService dataFieldService;
	
	private IUserService userService;
	
	private IGiftService giftService;
	
	private ICouponService couponService;
	private ICoinService coinService;
	
	private IUserGiftService userGiftService;
	
	private IUserCouponService userCouponService;
	
	
	
	
	
	@RequestMapping("/coin/toList")
	public String  getCointoList(HttpServletRequest req, Model model) throws DocumentException {
		
		//正经值管理
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
		
		params.put("pagename", "selectUserPage");
		params.put("total", "selectUserCount");
		page = coinService.page(page, params);
		model.addAttribute("detail", page);
		model.addAttribute("params", params);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		
		return "sys/coin/list";
	}
	
	@RequestMapping("/coin/toReceive")
	public String  getCoinStream(HttpServletRequest req, Model model) throws DocumentException {
		
		//正经值用户
		Page page = PageUtils.createPage(req);
		Map<String, Object> params =new HashMap<String, Object>();
		DataField dataField=new DataField();
		dataField.setData_field_id("coin_type");
		List<DataFieldVo> coin_type=dataFieldService.selectForList(dataField);
		Map<String, Object> cointypeMap=new HashMap<String, Object>();
		params.put("coin_type", coin_type);
		for(int i=0;i<coin_type.size();i++){
			cointypeMap.put(coin_type.get(i).getData_field_value(), coin_type.get(i).getData_field_name());
		}
//		dataField.setData_field_id("user_login_status");
//		params.put("userStatus", dataFieldService.selectForList(dataField));
//		dataField.setData_field_id("reg_source");
//		params.put("regSource", dataFieldService.selectForList(dataField));
//		dataField.setData_field_id("source_type");
//		params.put("sourceType", dataFieldService.selectForList(dataField));
		
		
		
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
		
		params.put("pagename", "selectCoinStreamPage");
		params.put("total", "selectCoinStreamCount");
		page = coinService.page(page, params);
		
		User user=new User();
		List<UserVo> userList=userService.selectForList(user);
		Map<String, Object> userMap=new HashMap<String, Object>();
		for(int i=0;i<userList.size();i++){
			userMap.put(userList.get(i).getId().toString(), userList.get(i));
		}
		
		List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> dataMap=dataList.get(i);
			String user_id=dataMap.get("user_id").toString();
			String cointype=dataMap.get("type").toString();
			UserVo userVo=(UserVo) userMap.get(user_id);
			dataMap.put("phone", userVo.getPhone());
			dataMap.put("real_name", userVo.getReal_name());
			dataMap.put("type_text", cointypeMap.get(cointype));
		}
		
		model.addAttribute("detail", page);
		model.addAttribute("params", params);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		
		return "sysNew/fund/coin/receive";
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
