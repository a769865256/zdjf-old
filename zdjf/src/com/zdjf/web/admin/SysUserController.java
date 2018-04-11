package com.zdjf.web.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zdjf.domain.admin.SysUser;
import com.zdjf.domain.common.DataField;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.Coupon;
import com.zdjf.domain.fund.CouponVo;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.GiftVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.admin.ISysUserService;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.UserConstants;
import com.zdjf.util.XmlToJurisdiction;

import sun.net.www.content.image.gif;


@Controller
@RequestMapping("/sys/user")
public class SysUserController{

	private ISysUserService sysUserService;

	private IUserService userService;

	private IDataFieldService dataFieldService;

	private IUserFundStatService userFundStatService;

	private IFundsService fundsService;
	private IGiftService giftService;
	private ICouponService couponService;
	private IUserGiftService userGiftService;
	@Autowired(required=true)
	private IUserCouponService userCouponService;
	@Autowired(required=true)
	private ICoinStreamService coinStreamService;
	// 加载页面的通用数据  SysUser_exp_mapper
	private void loadCommon(Model model){

	}

	@RequestMapping("/create")//添加管理用户
	public @ResponseBody Result create(SysUser user,HttpServletRequest request, Model model) {
		if (user != null) {
			String password = user.getPassword();
			String md5 = Md5Util.md5to32(password);
			user.setPassword(md5);
			sysUserService.save(user);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/menus")
	public void  getMenus(HttpServletRequest request, Model model) throws DocumentException {

		String str1 = getClass().getClassLoader().getResource("sysuser.xml").getPath();

		//List<XmlNode> menus =  XmlToJurisdiction.getExtName(str1);

		model.addAttribute("menus", XmlToJurisdiction.getExtName(str1));
		this.loadCommon(model);
	}

	@RequestMapping("/details")
	public String  getDetails(HttpServletRequest request, Model model) throws DocumentException {

		//存入会话session 
		HttpSession session = request.getSession(true); 

		String user_name = (String) session.getAttribute("sysuser");

		SysUser sUser = new SysUser();
		sUser.setLogin_name(user_name);

		SysUser user = sysUserService.loginSysUser(sUser);

		model.addAttribute("sysuser", user);
		this.loadCommon(model);

		return "admin/sysuser";
	}

	@RequestMapping(value="/toDetail",method=RequestMethod.GET)
	public String gettoDetail(HttpServletRequest req, Model model)  {
		User user=new User();
		Long user_id=0l;
		String user_phone="";
		if(""!=req.getParameter("uid")&&null!=req.getParameter("uid")){
			user_id=Long.parseLong((String) req.getParameter(
					"uid"));
			user.setId(user_id);
		}
		if(""!=req.getParameter("phone")&&null!=req.getParameter("phone")){
			user_phone=(String) req.getParameter(
					"phone");
			user.setPhone(user_phone);
		}

		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user_id);
		userFundStat=userFundStatService.selectForObjectById(userFundStat);

		user=userService.selectForObject(user);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("total", userFundStat);
		map.put("info", user);
		model.addAttribute("detail",map);
		return "sysNew/user/detail";
	}

	private Map<String,Object> getParams(HttpServletRequest req){
		Map<String, Object> params =new HashMap<String, Object>();
		params.put(Constants.REQ_PARAM_SEARCH_TYPE, StrUtils.convToInt(
				RequestUtils.getReqString(req, Constants.REQ_PARAM_SEARCH_TYPE)));
		// 取得请求中的查询类型参数

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
		//用户类型：1，普通用户；2，出借人
		params.put("userType", RequestUtils.getReqString(req,"userType"));
		//渠道
		params.put("sourceType",RequestUtils.getReqString(req,"sourceType") );
		return params;
	}

	@RequestMapping("/toList")
	public String  gettoList(HttpServletRequest req, Model model) throws DocumentException {


		Page page = PageUtils.createPage(req);
		Map<String, Object> params =new HashMap<String, Object>();
		params=getParams(req);
		// 取得请求中的查询类型参数

		params.put("pagename", "UserListResul");
		params.put("total", "UserListResulCn");
		page = userService.page(page, params);

		//user_fund_stat
		List<Map<String, Object>> userFundStatList=userFundStatService.selectUserFundStatForList();
		Map<String,Object> userFundStatMap=new HashMap<String, Object>();
		for(int i=0;i<userFundStatList.size();i++){
			Map<String, Object> map=userFundStatList.get(i);
			userFundStatMap.put(map.get("user_id").toString(), userFundStatList.get(i));
		}


		List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
		for(int j=0;j<dataList.size();j++){
			Map<String, Object> map=dataList.get(j);
			String user_id=map.get("id").toString();
			if(userFundStatMap.containsKey(user_id)){
				Map<String, Object> ufsMap=(Map<String, Object>) userFundStatMap.get(user_id);
				map.put("withdrawed", ufsMap.get("withdrawed"));
				map.put("balance", ufsMap.get("balance"));
				map.put("total_recharged", ufsMap.get("total_recharged"));
				map.put("grandTotalInvested", ufsMap.get("grandTotalInvested"));
				map.put("invest_frequency", ufsMap.get("invest_frequency"));
			}
		}


		Gift gift=new Gift();
		gift.setStatus(1);
		List<GiftVo> giftList=giftService.selectForList(gift);
		Coupon coupon=new Coupon();
		coupon.setStatus(1);
		List<CouponVo> couponList=couponService.selectForList(coupon);
		params.put("giftList", JSONArray.toJSON(giftList));
		params.put("couponList", JSONArray.toJSON(couponList));

		DataField dataField=new DataField();
		dataField.setData_field_id("reg_source");
		params.put("reg_source", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("source_type");
		params.put("source_type", dataFieldService.selectForList(dataField));
		dataField.setData_field_id("user_type");
		params.put("user_type", dataFieldService.selectForList(dataField));

		model.addAttribute("detail", page);
		model.addAttribute("params", params);
		model.addAllAttributes(PageUtils.createPagePar(page));

		return "sysNew/user/list";
	}


	@RequestMapping(value="/sendGift",method=RequestMethod.POST)
	public String sendGift(HttpServletRequest request){
		String user_type_radio=RequestUtils.getReqString(request, "user_type_radio");
		String coupon_type=RequestUtils.getReqString(request, "coupon_type");
		String select_coupon_list=RequestUtils.getReqString(request, "select_coupon_list");
		User user=new User();
		List<UserVo> userList=new ArrayList<UserVo>();
		if(user_type_radio.equals("1")){
			String user_ids=RequestUtils.getReqString(request, "user_ids");
			String[] userId=user_ids.split(",");
			for(int i=0;i<userId.length;i++){
				user=userService.selectForObjectByPhone(userId[i]);
				UserVo userVo=new UserVo();
				userVo.setId(user.getId());
				userList.add(userVo);
			}
			
		}else if(user_type_radio.equals("2")){
			user=new User();
			userList=userService.selectForList(user);

		}else if(user_type_radio.equals("3")){
			user=new User();
			user.setReal_name_auth(1);
			userList=userService.selectForList(user);
		}else if(user_type_radio.equals("4")){
			Page page = PageUtils.createPage(request);
			page.setLimit(999999l);
			Map<String, Object> params =new HashMap<String, Object>();
			params=getParams(request);
			params.put("pagename", "UserListResul");
			params.put("total", "UserListResulCn");
			page = userService.page(page, params);
			List<Map> dataList=(List<Map>) page.getDataList();
			for(int i=0;i<dataList.size();i++){
				Map dataMap=dataList.get(i);
				UserVo uv=new UserVo();
				if(dataMap.containsKey("id")){
					uv.setId(StrUtils.convToLong(dataMap.get("id").toString()));
					userList.add(uv);
				}
			}
		}
		if(coupon_type.equals("1")){
			Coupon coupon=new Coupon();
			coupon=couponService.selectForObjectById(StrUtils.convToLong(select_coupon_list));
			for(int i=0;i<userList.size();i++){
				UserCoupon userCoupon=new UserCoupon();
				userCoupon.setCoupon_id(coupon.getId());
				Date date = new Date();
				userCoupon.setCoupon_id(coupon.getId());
				userCoupon.setCoupon_name(coupon.getCoupon_name());
				userCoupon.setUser_id(userList.get(i).getId());
				userCoupon.setInterest(coupon.getInterest());
				userCoupon.setCreate_time(date);
				userCoupon.setRemark(coupon.getCoupon_name());
				userCoupon.setUse_type(coupon.getUse_type());
			    
			    if(coupon.getInvalid_type() == 2){
			    	userCoupon.setStart_date(date);
			    	Calendar calendar = new GregorianCalendar(); 
				    calendar.setTime(date); 
				    calendar.add(Calendar.DATE, coupon.getInvalid_days()-1);//把日期往后增加一年.整数往后推,负数往前移动
				    userCoupon.setEnd_date(calendar.getTime());
			    }else if(coupon.getInvalid_type() == 1){
			    	//比较一下时间  那个比较晚  就用那个时间
			    	userCoupon.setStart_date((date.getTime()>coupon.getInvalid_start_date().getTime())?date:coupon.getInvalid_start_date());
			    	userCoupon.setEnd_date(coupon.getInvalid_end_date());
			    }
			    
			    userCoupon.setStatus(1);
			    userCoupon.setMin_days(coupon.getMin_days());
			    userCoupon.setMin_amount(coupon.getMin_amount());
			    userCoupon.setUse_type(coupon.getUse_type());
				userCouponService.save(userCoupon);
			}
		}else if(coupon_type.equals("2")){
			Gift gift=new Gift();
			gift=giftService.selectForObjectById(StrUtils.convToLong(select_coupon_list));
			for(int i=0;i<userList.size();i++){
				UserGift userGift = new UserGift();
				Date date = new Date();
				userGift.setGift_id(gift.getId());
				userGift.setGift_name(gift.getGift_name());
				userGift.setUser_id(userList.get(i).getId());
				userGift.setAmount(gift.getMoney());
				userGift.setCreate_time(date);
				userGift.setRemark(gift.getGift_name());
				userGift.setUse_type(gift.getUse_type());
			    
			    if(gift.getDate_type() == 2){
			    	userGift.setStart_date(date);
			    	Calendar calendar = new GregorianCalendar(); 
				    calendar.setTime(date); 
				    calendar.add(Calendar.DATE, gift.getGift_days()-1);//把日期往后增加一年.整数往后推,负数往前移动
				    userGift.setEnd_date(calendar.getTime());
			    }else if(gift.getDate_type() == 1){
			    	//比较一下时间  那个比较晚  就用那个时间
			    	userGift.setStart_date((date.getTime()>gift.getStart_date().getTime())?date:gift.getStart_date());
			    	userGift.setEnd_date(gift.getEnd_date());
			    }
			    
			    userGift.setStatus(1);
			    userGift.setMax_days(gift.getMax_days());
			    userGift.setMax_amount(gift.getMax_amount());
			    userGift.setUse_type(gift.getUse_type());
			    
				userGiftService.save(userGift);
			}
			
		}else if(coupon_type.equals("3")){
			String coin=RequestUtils.getReqString(request, "input_coin");
			double amount=StrUtils.convToDouble(coin);
			for(int i=0;i<userList.size();i++){
				UserFundStat ufs=new UserFundStat();
				ufs.setUser_id(userList.get(i).getId());
				ufs=userFundStatService.selectForObjectById(ufs);
				CoinStream coinStream=new CoinStream();
				coinStream.setUser_id(userList.get(i).getId());
				coinStream.setAction(2);
				coinStream.setType(15);
				coinStream.setAmount(amount);
				coinStream.setBalance(ufs.getCoin_balance()+amount);
				coinStream.setStatus(1);
				ufs.setCoin_balance(coinStream.getBalance());
				userFundStatService.updateUserFundStatById(ufs);
				coinStreamService.save(coinStream);
			}
		}
		return "redirect:/sys/user/toList.action";
	}

	@RequestMapping("/loginsys")
	public String login(SysUser user,HttpServletRequest request, Model model) throws DocumentException {



		String str1 = getClass().getClassLoader().getResource("sysuser.xml").getPath();

		XmlToJurisdiction.getExtName(str1);
		String user_name = request.getParameter("login_name");
		String user_passwrod = request.getParameter("password");
		user.setLogin_name(user_name);

		user = sysUserService.loginSysUser(user);

		if(user == null){
			model.addAttribute("text", "用户名不存在！！");
			this.loadCommon(model);
		}else{
			String password = user.getPassword();
			if(password.equalsIgnoreCase(user_passwrod)){
				return "/jiuzhou/manage/bloodFat/bloodFat_detail.jsp";
			}else{
				int count  = user.getError_count();
				user.setError_count(++count);


				sysUserService.update(user);
				model.addAttribute("user", user);
				this.loadCommon(model);


			}
		}

		return "";
	}

	@Autowired(required = true)
	public void setSysUserService(ISysUserService userService) {
		this.sysUserService = userService;
	}

	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Autowired(required = true)
	public void setDataFieldService(IDataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}

	@Autowired(required = true)
	public void setUserFundStat(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}

	@Autowired(required = true)
	public void setFunds(IFundsService fundsService) {
		this.fundsService = fundsService;
	}
	@Autowired(required = true)
	public void setGiftService(IGiftService giftService) {
		this.giftService = giftService;
	}
	@Autowired(required = true)
	public void setCouponService(ICouponService couponService) {
		this.couponService = couponService;
	}
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	@Autowired(required = true)
	public void setFundsService(IFundsService fundsService) {
		this.fundsService = fundsService;
	}
	@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}


}
