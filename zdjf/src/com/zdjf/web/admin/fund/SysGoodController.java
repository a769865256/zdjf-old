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
import com.zdjf.domain.fund.CoinGoods;
import com.zdjf.domain.fund.CoinGoodsVo;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.fund.ICoinGoodsService;
import com.zdjf.service.user.IUserGoodsService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.UserConstants;

@Controller
@RequestMapping("/sys/fund")
public class SysGoodController {
private IDataFieldService dataFieldService;
	
	private IUserService userService;
	private ICoinGoodsService coinGoodsService;
	private IUserGoodsService userGoodsService;
	
	@RequestMapping("/good/toList")
	public String  getGoodList(HttpServletRequest req, Model model) throws DocumentException {
		
		//兑换管理
		Page page = PageUtils.createPage(req);
		Map<String, Object> params =new HashMap<String, Object>();
		params.put("pagename", "selectCoinGoodsPage");
		params.put("total", "selectCoinGoodsCount");
		page = coinGoodsService.page(page, params);
		model.addAttribute("detail", page);
		model.addAttribute("params", params);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		
		return "sysNew/fund/good/list";
	}
	
	@RequestMapping("/good/toReceive")
	public String  getGoodConvert(HttpServletRequest req, Model model) throws DocumentException {
		
		//用户兑换列表
		Page page = PageUtils.createPage(req);
		Map<String, Object> params =new HashMap<String, Object>();
		
		User user=new User();
		List<UserVo> userList=userService.selectForList(user);
		Map<String, Object> userMap=new HashMap<String, Object>();
		for(int i=0;i<userList.size();i++){
			userMap.put(userList.get(i).getId().toString(), userList.get(i));
		}
		
		CoinGoods coinGoods=new CoinGoods();
		List<CoinGoodsVo> coinGoodsList=coinGoodsService.selectForList(coinGoods);
		Map<String, Object> coinGoodsMap=new HashMap<String, Object>();
		for(int i=0;i<coinGoodsList.size();i++){
			coinGoodsMap.put(coinGoodsList.get(i).getId().toString(), coinGoodsList.get(i));
		}
		
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
		
		params.put("pagename", "selectUserGoodsPage");
		params.put("total", "selectUserGoodsCount");
		page = userGoodsService.page(page, params);
		
		List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		for(int i=0;i<dataList.size();i++){
			Map<String, Object> dataMap=dataList.get(i);
			String product_id="";
			if(dataMap.containsKey("product_id")){
				product_id=dataMap.get("product_id").toString();
			}
			String user_id=dataMap.get("user_id").toString();
			CoinGoodsVo coinGoodsVo=(CoinGoodsVo) coinGoodsMap.get(product_id);
			if(userMap.containsKey(user_id)){
				UserVo userVo=(UserVo) userMap.get(user_id);
				dataMap.put("phone", userVo.getPhone());
				dataMap.put("real_name", userVo.getReal_name());
			}
			dataMap.put("goods_name", coinGoodsVo.getGoods_name());
		}
		
		model.addAttribute("detail", page);
		model.addAttribute("params", params);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		
		return "sysNew/fund/good/receive";
	}
	
	@RequestMapping("/good/withdrawcoupons")
	public String  getGoodWithdrawCoupons(HttpServletRequest req, Model model) throws DocumentException {
		
		//提现券记录
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
		page = userService.page(page, params);
		model.addAttribute("detail", page);
		model.addAttribute("params", params);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		
		return "sys/good/withdrawcoupons";
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
	public void setCoinGoodsService(ICoinGoodsService coinGoodsService) {
		this.coinGoodsService = coinGoodsService;
	}
	
	@Autowired(required = true)
	public void setUserGoodsService(IUserGoodsService userGoodsService) {
		this.userGoodsService = userGoodsService;
	}
}
