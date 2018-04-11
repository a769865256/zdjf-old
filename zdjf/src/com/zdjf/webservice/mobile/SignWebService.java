package com.zdjf.webservice.mobile;

import com.zdjf.components.events.FundEvent;
import com.zdjf.components.mobile.SignMathRandom;
import com.zdjf.components.mobile.TenRandom;
import com.zdjf.components.mobile.ThousandRandom;
import com.zdjf.domain.activity.Sign;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.user.User;
import com.zdjf.service.activity.ISignService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.UrlConstant;
import com.zdjf.webservice.express.SmsReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/m/sign")
public class SignWebService {
	
	private IUserService userService;
	
	private ISignService signService;
	
	private IProductBuyService productBuyService;
	
	private ICoinStreamService coinStreamService;
	
	private IUserFundStatService userFundStatService;
	
	private ApplicationContext applicationContext;
	
	@RequestMapping(value="/mathrandom",method=RequestMethod.GET)
	public @ResponseBody SmsReturn getMathRandom(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		SmsReturn sr = new SmsReturn();
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(1, 0);
		map.put(2, 0);
		map.put(3, 0);
		map.put(4, 0);
		map.put(5, 0);
		map.put(6, 0);
		for(int i = 0;i<10000; i++){
			int key = SignMathRandom.PercentageRandom();
			int value = map.get(key);
			map.put(key, ++value);
			System.out.println(key);
			
		}
		
		for(int i = 0;i<50; i++){
			int key = ThousandRandom.PercentageRandom();
			/*int value = map.get(key);
			map.put(key, ++value);*/
			System.out.println(key);
			
		}
		
		for(int i = 0;i<50; i++){
			int key = TenRandom.PercentageRandom();
			/*int value = map.get(key);
			map.put(key, ++value);*/
			System.out.println(key);
			
		}
		
		
	
		sr.setContent(map);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	
	
	//获取签到
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public @ResponseBody SmsReturn createSign(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign1 = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		/*if( null==sign1 || sign1.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign1.equalsIgnoreCase(md5Sign)){
	
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("无效数据");
		
		}*/
		
		//签到
		Sign sign  = new Sign();
		Date date = new Date();
		User user = userService.selectForObjectByPhone(user_name);
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    Double coin = SignMathRandom.PercentageRandom()*1.0;
	    //判断是不是首次签到
	    Sign week = new Sign();
    	week.setUser_id(user.getId());
    	week.setCreate_date(DateUtil.formatDate(date));
    	//首次签到
    	List<Sign> list = signService.selectForList(week);
    	if(list.size() == 0){
		    //昨日发生的—代码需要优化
		    ProductBuy buy = new ProductBuy();
		    buy.setUser_id(user.getId());
		    buy.setStatus(1);//已付款
		    
		    //  昨天
		    Date yesterday = DateUtil.addDay(date, -1);
		    String start = DateUtil.formatDateTimeS(DateUtil.dateBegin(yesterday));
		    String end = DateUtil.formatDateTimeS(DateUtil.dateEnd(yesterday));
		    
		    Map<String,Object> map = new HashMap<String,Object>();
			map.put("page_name","selectProductBuyYesterday");
			map.put("start_date",Timestamp.valueOf(start));
			map.put("end_date",Timestamp.valueOf(end));
			map.put("status",2);
			map.put("user_id",user.getId() + "");
		    
		    List<ProductBuy> listBuy = productBuyService.selectForList(map);
		    
		    Double totle = 0.0;
		    
		    
		    for(int i = 0;i<listBuy.size();i++){
		    	totle += listBuy.get(i).getAmount();
		    }
	   
		    //三选一
    		if(totle>=Constants.SIGN_TOTLE_YESTERDAY_TEN){
    	    	coin = coin*TenRandom.PercentageRandom();
    	    }else if(totle>=Constants.SIGN_TOTLE_YESTERDAY){
    	    	coin = coin*ThousandRandom.PercentageRandom();
    	    }else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY||//周末所发
    	    		cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
    		 {
        		coin = coin*2.0;
    		 }
    	}else {
    		
    		if(user.getSign_num()>0){
    			int num = user.getSign_num();
        		user.setSign_num(--num);
        		
        		userService.updateForObjectByPhone(user);
    		}else{
    			sr.setReturnCase(false);
    			sr.setStatus(101);
    			sr.setContent("已经没有抽奖机会");
    			return sr;
    		}
    		
    	}
	    
		
	    sign.setCoin(coin);
		sign.setCreate_time(date);
		sign.setCreate_date(DateUtil.formatDate(date));
		sign.setPhone(user_name);
		sign.setUser_id(user.getId());
		sign.setReg_source(Integer.parseInt(reg_source));
		sign.setStatus(1);
		
		signService.save(sign);
		
		//正经值
		//签到
		applicationContext.publishEvent(new FundEvent(this,user.getId(),33,coin,
				userFundStatService,coinStreamService));
		/*CoinStream coinStream = new CoinStream();
		
		coinStream.setUser_id(user.getId());
		coinStream.setAmount(coin);
		coinStream.setType(33);
		coinStream.setAction(1);
		coinStream.setRemark("签到");
		coinStream.setStatus(1);
		
		coinStreamService.save(coinStream);*/
		
		
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(sign);
		return sr;
	}
	
	//签到次数获得
	@RequestMapping(value="/sign/times",method=RequestMethod.GET)
	public @ResponseBody SmsReturn timesSign(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign1 = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		String url = UrlConstant.API_WEIXIN_SHARE_URL + user_name;
		User user = userService.selectForObjectByPhone(user_name);

		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(user.getSign_num());
		return sr;
	}
	
	//分享微信接口
	@RequestMapping(value="/share",method=RequestMethod.GET)
	public @ResponseBody SmsReturn shareSign(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String sign1 = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		String url = UrlConstant.API_WEIXIN_SHARE_URL + user_name;
		User user = userService.selectForObjectByPhone(user_name);
		
		Date date = new Date();
		boolean result = false;
		Date sign_date = user.getSign_data();
		if(sign_date != null){//用此标示每天首次分享获得签到机会的时间
//			String sign_date_string = DateUtil.formatDate(user.getSign_data());
//			String date_string = DateUtil.formatDate(date);
//			if(!date_string.equalsIgnoreCase(sign_date_string)){
				result = false;//2018-01-05 edit 用此标示每天首次分享获得签到机会的时间
//			}
		}else{
			result = true;
		}
		
		if(result){
			user.setSign_date(date);
			int num = user.getSign_num();
			user.setSign_num(++num);
			userService.updateForObjectByPhone(user);
		}
	
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(url);
		return sr;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	
	@Autowired(required = true)
	public void setSignService(ISignService signService) {
		this.signService = signService;
	}
	
	@Autowired(required = true)
	public void setCoinStreamService(ICoinStreamService coinStreamService) {
		this.coinStreamService = coinStreamService;
	}
	
	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	
	@Autowired(required = true)
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
