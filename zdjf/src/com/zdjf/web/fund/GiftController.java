package com.zdjf.web.fund;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.domain.fund.Gift;
import com.zdjf.domain.fund.GiftVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.fund.IGiftService;


@Controller
@RequestMapping("/git")
public class GiftController {
	
	private IGiftService giftService;
	
	//private IUserGiftService userGiftService;
	
	
	@Transactional(rollbackFor = { Exception.class })  
	@RequestMapping(value="/add",method=RequestMethod.POST)//添加管理用户
	public Result addGift(HttpServletRequest request) throws ParseException {
		
		Gift gift = new Gift();
		
		String gift_name = request.getParameter("gift_name");
		String gift_source = request.getParameter("gift_source");
		String money = request.getParameter("money");
		String status = request.getParameter("status");
		//String create_time = request.getParameter("create_time");
		String max_count = request.getParameter("max_count");
		String date_type = request.getParameter("date_type");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String gift_days = request.getParameter("gift_days");
		String max_amount = request.getParameter("max_amount");
		String max_days = request.getParameter("max_days");
		String use_type = request.getParameter("use_type");
		String remark = request.getParameter("remark");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		
		gift.setGift_name(gift_name);
		gift.setGift_source(Integer.parseInt(gift_source));
		gift.setMoney(Double.parseDouble(money));
		gift.setStatus(Integer.parseInt(status));
		gift.setCreate_time(new Date());
		gift.setMax_count(Integer.parseInt(max_count));
		gift.setDate_type(Integer.parseInt(date_type));
		if(date_type.equalsIgnoreCase("1")){
			if(!start_date.isEmpty())
				gift.setStart_date(sdf.parse(start_date));
			if(end_date.isEmpty())
				gift.setEnd_date(sdf.parse(end_date));
		}else if(date_type.equalsIgnoreCase("2")){
			gift.setGift_days(Integer.parseInt(gift_days));
		}
			
		gift.setMax_amount(Double.parseDouble(max_amount));
		gift.setMax_days(Integer.parseInt(max_days));
		gift.setUse_type(Integer.parseInt(use_type));
		gift.setRemark(remark);

		
		/*测试回滚方式
		 * UserGift userGift = new UserGift();
		userGift.setComment("daf");*/
		
		 try {
			 giftService.save(gift);
			 //userGiftService.save(userGift);
			 
		 } catch (Exception e) {  
	          e.printStackTrace();     
	          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//就是这一句了，加上之后，如果doDbStuff2()抛了异常,                                                                                       //doDbStuff1()是会回滚的  
	     }  
		
		return new Result("保存成功!");
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)//添加管理用户
	public String getGiftPage(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		Gift gift = new Gift();
		String name = "";
		name=request.getParameter("gift_name");
		gift.setGift_name(name);
		Page page = PageUtils.createPage(request);
		page = giftService.page(page, gift);
		
		//List<>page.getDataList();
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		model.addAttribute("gift_name", name);
		
		return "fund/gift/giftManager";
	} 
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)//添加管理用户
	public  String getListPage(HttpServletRequest request,
            HttpServletResponse response, Model model){
		
		String name = "";
		name=request.getParameter("gift_name");
		
		Gift gift = new Gift();
		
		gift.setGift_name(name);
		
		List<GiftVo> list = giftService.selectForList(null);
		if(model.containsAttribute("list")){
		}
		model.addAttribute("list", list);
		model.addAttribute("giftname",name);
		return "fund/gift/giftManager";
	}
	
	
	
	
	
	@RequestMapping(value="/details",method=RequestMethod.GET)//添加管理用户
	public String getGiftDetails(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		String id  = request.getParameter("id");
		Gift gift = giftService.selectForObjectById(Long.parseLong(id));
		model.addAttribute("gift", gift);
		
		return "gift";
	}
	
	@RequestMapping(value="/del",method=RequestMethod.GET)//添加管理用户
	public @ResponseBody String delGift(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		String id  = request.getParameter("id");
		String[] idarray=id.split(",");
		for(int i=0;i<idarray.length;i++){
			giftService.deleteById(Integer.parseInt(idarray[i]));
		}
		return "ok";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)//添加管理用户
	public Result updateGift(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		String id = request.getParameter("id");
		String gift_name = request.getParameter("gift_name");
		String gift_source = request.getParameter("gift_source");
		String money = request.getParameter("money");
		String status = request.getParameter("status");
		//String create_time = request.getParameter("create_time");
		String max_count = request.getParameter("max_count");
		String date_type = request.getParameter("date_type");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String gift_days = request.getParameter("gift_days");
		String max_amount = request.getParameter("max_amount");
		String max_days = request.getParameter("max_days");
		String use_type = request.getParameter("use_type");
		String remark = request.getParameter("remark");
		
		Gift gift = giftService.selectForObjectById(Long.parseLong(id));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		
		gift.setGift_name(gift_name);
		gift.setGift_source(Integer.parseInt(gift_source));
		gift.setMoney(Double.parseDouble(money));
		gift.setStatus(Integer.parseInt(status));
		gift.setMax_count(Integer.parseInt(max_count));
		gift.setDate_type(Integer.parseInt(date_type));
		if(date_type.equalsIgnoreCase("1")){
			if(!start_date.isEmpty())
				gift.setStart_date(sdf.parse(start_date));
			if(end_date.isEmpty())
				gift.setEnd_date(sdf.parse(end_date));
		}else if(date_type.equalsIgnoreCase("2")){
			gift.setGift_days(Integer.parseInt(gift_days));
		}
			
		gift.setMax_amount(Double.parseDouble(max_amount));
		gift.setMax_days(Integer.parseInt(max_days));
		gift.setUse_type(Integer.parseInt(use_type));
		gift.setRemark(remark);


		giftService.updateGiftById(gift);
		
		
		
		return new Result("ok");
	}
	
	
	
	@Autowired(required = true)
	public void setGiftService(IGiftService giftService) {
		this.giftService = giftService;
	}
	
	/*@Autowired(required = true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}*/
	

}
