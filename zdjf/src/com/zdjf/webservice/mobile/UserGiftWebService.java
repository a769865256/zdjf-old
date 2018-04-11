package com.zdjf.webservice.mobile;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zdjf.components.events.UserBenefitsEvent;
import com.zdjf.components.mobile.SinaResultUtil;
import com.zdjf.components.mobile.ValuetoString;
import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.GiftVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.fund.IGiftService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.webservice.express.SmsReturn;


@RestController
@RequestMapping("/m/userGift")
public class UserGiftWebService {
	
	Log log = LogFactory.getLog(this.getClass());
	
	private IUserGiftService userGiftService;
	
	private IUserService userService;
	
	private IGiftService giftService;
	
	private IProductBuyService productBuyService;
	
	private ApplicationContext applicationContext;
	
	private IUserFundStatService userFundStatService;
	
	private IProductBuyIncomeService productBuyIncomeService;

	//获取红包page
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getGiftPage(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User old = userService.selectForObjectByPhone(user_name);
		
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(old == null){
			
			
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			model.addAttribute("sr", sr);
			return sr;
			
		}
			
		if(!sign.equalsIgnoreCase(md5Sign)){
			
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		UserGift userGift = new UserGift();
		
		
		userGift.setUser_id(old.getId());
		
		Page page = PageUtils.createPage(request);
		page = userGiftService.page(page, userGift);
		
		int currentPage = page.getCurrentPage().intValue();
		int limit = page.getLimit().intValue();
		
		int total = page.getTotal().intValue();
		int start = limit * (currentPage - 1);
		int end = total >= limit*currentPage?limit*currentPage:total;

		
		List<UserGiftVo> listGift = userGiftService.selectForList(userGift);
		
		
		List<Map<String,String>> listS = new ArrayList<Map<String,String>>();
		Date date = new Date();
		for(int i = start;i<end;i++){
			UserGiftVo vo = listGift.get(i);
			UserGift gift = new UserGift();
			BeanUtils.copyProperties(vo,gift);
			Map<String,String> map = ValuetoString.eachProperties(gift);
			Date end_date = gift.getEnd_date();
			
			int days = DateUtil.daysBetween(date, end_date);
			
			if(days>=0){
				if(days<4){//小于四天{
					map.put("quick_outtime" , "即将过期");
				}else{
					map.put("quick_outtime" , "");
				}
			}else{
				map.put("quick_outtime" , "过期");
			}
			
			listS.add(map);
		}
		
		page.setDataList(listS);
		
		
		sr.setContent(page);
		sr.setReturnCase(true);
		sr.setStatus(100);
			
		
		
		return sr;
	}
	
	
	//红包发放
	@RequestMapping(value="/envelope/sal",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getGiftEnvelope(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		SmsReturn sr = new SmsReturn();
		
		//获取用户信息
		String phone = request.getParameter("phone");
		String gift_name = request.getParameter("gift_name");
		String gift_source = request.getParameter("gift_source");
		String income_days = request.getParameter("income_days");
		Gift gift = new Gift();
		gift.setGift_name(gift_name);
		gift.setGift_source(Integer.parseInt(gift_source));
		
		User user = userService.selectForObjectByPhone(phone);
		if(user != null){
			//红包 流程
			/*applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
					gift,giftService,userGiftService));
			
			sr.setContent("红包发放");
			sr.setReturnCase(true);
			sr.setStatus(100);*/
			
			
			List<GiftVo> listG = giftService.selectForList(gift);
			int total = listG.size() - 1;
			for(int i = total;i>=0;i--){
				GiftVo vo = listG.get(i);
				if(Integer.parseInt(income_days) >= vo.getMax_days()){
					UserGift userGift = new UserGift();
					
					Date date = new Date();
					userGift.setGift_id(vo.getId());
					userGift.setGift_name(vo.getGift_name());
					userGift.setUser_id(user.getId());
					userGift.setAmount(vo.getMoney());
					userGift.setCreate_time(date);
					userGift.setRemark(vo.getGift_name());
				    
				    if(vo.getDate_type() == 2){
				    	userGift.setStart_date(date);
				    	Calendar calendar = new GregorianCalendar(); 
					    calendar.setTime(date); 
					    calendar.add(Calendar.DATE, vo.getGift_days()-1);//把日期往后增加一年.整数往后推,负数往前移动
					    userGift.setEnd_date(calendar.getTime());
				    }else if(vo.getDate_type() == 1){
				    	//比较一下时间  那个比较晚  就用那个时间
				    	userGift.setStart_date((date.getTime()>vo.getStart_date().getTime())?date:vo.getStart_date());
				    	userGift.setEnd_date(vo.getEnd_date());
				    }
				    
				    userGift.setStatus(1);
				    userGift.setMax_days(vo.getMax_days());
				    userGift.setMax_amount(vo.getMax_amount());
				    userGift.setUse_type(vo.getUse_type());
				    
					userGiftService.save(userGift);
					
					return sr;
				}
				
				
			}
		}else{
			sr.setContent("红包发放失败");
			sr.setReturnCase(false);
			sr.setStatus(101);
		}
		
		
		return sr;
	}
	
	//红包发放
	@RequestMapping(value="/Payment",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getGiftPayment(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		SmsReturn sr = new SmsReturn();
		//获取用户信息
		String gift_name = request.getParameter("gift_name");
		String gift_source = request.getParameter("gift_source");
		
	
		Gift gift = new Gift();
		gift.setGift_name(gift_name);
		gift.setGift_source(Integer.parseInt(gift_source));
		
		
//		String strDate = DateUtil.formatDate(new Date());
		String	strDate = "2018-04-01";//这可以 更改时间
		//返回用户    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductBuyIncomeEnd");
		map.put("end_dates",strDate);
		
	    //今天要回款buyId
	    List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);
	    Map<Long,String> mapO = new HashMap<Long,String>();
	    for(int i = 0;i<listBuyI.size();i++){
			ProductBuyIncome buyIncomeV = listBuyI.get(i);
			if(buyIncomeV.getStatus() == -1){
				mapO.put(buyIncomeV.getUser_id(), buyIncomeV.getUser_id().toString());
				
			}
			
		}
	    
	    Set<Long> set = mapO.keySet();
	    
	 
	    List<Long> valueList = new ArrayList<Long>(set);
	    
	   /* Iterator<Map.Entry<Long, String>> its = mapO.entrySet().iterator();
		List<Long> list = new ArrayList<Long>();
	    while (its.hasNext()) {
			Map.Entry<Long, String> entry = its.next();
			Long uid = entry.getKey();
			list.add(uid);
			
		}*/

	  //红包 流程
		applicationContext.publishEvent(new UserBenefitsEvent(this,valueList,
				gift,giftService,userGiftService));
	
			sr.setContent("红包发放");
			sr.setReturnCase(true);
			sr.setStatus(100);
		
		
		return sr;
	}
	
	void yearUser(User user,UserFundStat voF) throws InterruptedException{
		
		//黄金年终奖   所有已投
		
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put("userId",user.getId());
		//查询用户总投资额
		Long totalInvest = productBuyService.selectUserTotalInvest(hashMap);
		Double invest_sum = (double)totalInvest;
		voF.setInvest_sum(invest_sum);
		voF.setBalance(SinaResultUtil.queryBalance(user));
		userFundStatService.updateUserFundStatById(voF);
		if(invest_sum.compareTo(0.0)>0){
			
			UserGift gift = new UserGift();
			gift.setUser_id(user.getId());
			gift.setRelation_id(5L);
			List<UserGiftVo> listGift = userGiftService.selectForList(gift);
			
			if(listGift.size() >1){
			
			}else{
				Gift gift0 = new Gift();
				
				gift0.setGift_name("新春开门红包");
				gift0.setGift_source(5);
				gift0.setStatus(1);
				Thread.sleep(100);
				//红包 流程
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						gift0,giftService,userGiftService));
				
				Thread.sleep(100);
				
			}
			
		}
		
		// 10万
		/*if(invest_sum.compareTo(100000.0)>0){
			
			UserGift gift = new UserGift();
			gift.setUser_id(user.getId());
			gift.setRelation_id(58L);
			List<UserGiftVo> listGift = userGiftService.selectForList(gift);
			
			if(listGift.size() >1){
			
			}else{
				Gift gift0 = new Gift();
				
				gift0.setGift_name("黄金年终奖");
				gift0.setGift_source(58);
				gift0.setStatus(1);
				
				//红包 流程
				Thread.sleep(100);
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						gift0,giftService,userGiftService));
				
				Thread.sleep(100);
				
			}
			
		}*/
		
		// 30万
		/*if(invest_sum.compareTo(300000.0)>0){
			
			UserGift gift = new UserGift();
			gift.setUser_id(user.getId());
			gift.setRelation_id(59L);
			List<UserGiftVo> listGift = userGiftService.selectForList(gift);
			
			if(listGift.size() >1){
			
			}else{
				Gift gift0 = new Gift();
				
				gift0.setGift_name("黄金年终奖");
				gift0.setGift_source(59);
				gift0.setStatus(1);
				
				//红包 流程
				Thread.sleep(100);
				applicationContext.publishEvent(new UserBenefitsEvent(this,user.getId(),
						gift0,giftService,userGiftService));
				
				Thread.sleep(100);
				
			}
			
		}*/
	}
	
	//红包发放
	@RequestMapping(value="/envelopes",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getGiftEnvelopes(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
		
		
		SmsReturn sr = new SmsReturn();
		//获取用户信息
		String gift_name = request.getParameter("gift_name");
		String gift_source = request.getParameter("gift_source");
		
	
		Gift gift = new Gift();
		gift.setGift_name(gift_name);
		gift.setGift_source(Integer.parseInt(gift_source));

		
		List<Long> list = productBuyService.selectInvestor();
		
		for(int i = 15539;i<list.size();i++){
			
			Long uid = list.get(i);
			
			log.info("index ："+ i+ "   ######## user id " + uid);
			UserFundStat userFund = new UserFundStat();
			userFund.setUser_id(uid);
			UserFundStat voF = userFundStatService.selectForObjectById(userFund);
			
			User us = new User();
			us.setId(uid);
			
			User user = userService.load(us);
			
			Thread.sleep(100);
			yearUser(user,voF);
			Thread.sleep(100);
			
		}
		/*applicationContext.publishEvent(new UserBenefitsEvent(this,list,
				gift,giftService,userGiftService));*/
		
			sr.setContent("红包发放");
			sr.setReturnCase(true);
			sr.setStatus(100);
		
		
		return sr;
	}
	
	@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}

	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Autowired(required = true)
	public void setGiftService(IGiftService giftService) {
		this.giftService = giftService;
	}
	
	@Autowired(required = true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	
	@Autowired(required = true)
	public void setProductBuyIncomeService(IProductBuyIncomeService productBuyIncomeService) {
		this.productBuyIncomeService = productBuyIncomeService;
	}
	
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	
}
