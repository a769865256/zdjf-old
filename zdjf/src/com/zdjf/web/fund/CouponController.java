package com.zdjf.web.fund;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zdjf.domain.fund.Coupon;
import com.zdjf.domain.fund.CouponVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.fund.ICouponService;

@Controller
@RequestMapping("/coupon")
public class CouponController {
	
	private ICouponService couponService;
	
	
	@SuppressWarnings("null")
	@RequestMapping(value="/add",method=RequestMethod.POST)//添加管理用户
	public Result addCoupon(HttpServletRequest request) throws ParseException {
		
		String coupon_name = request.getParameter("coupon_name");
		String coupon_source = request.getParameter("coupon_source");
		String interest = request.getParameter("interest");
		String invalid_type = request.getParameter("invalid_type");
		String invalid_days = request.getParameter("invalid_days");
		String invalid_start_date = request.getParameter("invalid_start_date");
		String invalid_end_date = request.getParameter("invalid_end_date");
		String limit_count = request.getParameter("limit_count");
		String use_type = request.getParameter("use_type");
		//String create_time = request.getParameter("create_time");
		String status = request.getParameter("status");
		String min_amount = request.getParameter("min_amount");
		String min_days = request.getParameter("min_days");
		String remark = request.getParameter("remark");
		
		Coupon coupon = new Coupon();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		
		coupon.setCoupon_name(coupon_name);
		coupon.setCoupon_source(Integer.parseInt(coupon_source));
		coupon.setInterest(Double.parseDouble(interest));
		
		coupon.setUse_type(Integer.parseInt(use_type));
		coupon.setInvalid_type(Integer.parseInt(invalid_type));
		if(invalid_type.equalsIgnoreCase("1")){
			if((null == invalid_start_date)&&(!invalid_start_date.isEmpty()))
				coupon.setInvalid_start_date(sdf.parse(invalid_start_date));
			if((null == invalid_end_date)&&(!invalid_end_date.isEmpty()))
				coupon.setInvalid_end_date(sdf.parse(invalid_end_date));
		}else{
			coupon.setInvalid_days(Integer.parseInt(invalid_days));
		}
		
		coupon.setLimit_count(Integer.parseInt(limit_count));
		
		coupon.setCreate_time(new Date());
		coupon.setStatus(Integer.parseInt(status));
		coupon.setMin_amount(Double.parseDouble(min_amount));
		coupon.setMin_days(Integer.parseInt(min_days));
		coupon.setRemark(remark);
		coupon.setCreate_time(new Date());
		coupon.setInvalid_type(Integer.parseInt(invalid_type));

		couponService.save(coupon);
		
		return new Result("保存成功!");
	}
	
	
	@RequestMapping(value="/page",method=RequestMethod.POST)//添加管理用户
	public String getCouponPage(Coupon coupon,HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		Page page = PageUtils.createPage(request);
		page = couponService.page(page, coupon);
		
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		return "giftPage";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)//添加管理用户
	public String getCouponList(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		Coupon coupon = new Coupon();
		coupon.setCoupon_source(1);
		coupon.setCoupon_name("新手注册加息券");
		
		
		List<CouponVo> list = couponService.selectForList(coupon);
		
		model.addAttribute("list", list);
	
		
		return "couponList";
	}
	
	@RequestMapping(value="/details",method=RequestMethod.GET)//添加管理用户
	public String getCouponDetails(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		String id  = request.getParameter("id");
		Coupon coupon = couponService.selectForObjectById(Long.parseLong(id));
		model.addAttribute("coupon", coupon);
		
		return "gift";
	}
	
	@RequestMapping(value="/del",method=RequestMethod.DELETE)//添加管理用户
	public Result delCoupon(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		String id  = request.getParameter("id");
		couponService.deleteById(Integer.parseInt(id));
		
		
		return new Result("ok");
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)//添加管理用户
	public Result updateCoupon(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		String id = request.getParameter("id");
		String coupon_name = request.getParameter("coupon_name");
		String coupon_source = request.getParameter("coupon_source");
		String interest = request.getParameter("interest");
		String invalid_type = request.getParameter("invalid_type");
		String invalid_days = request.getParameter("invalid_days");
		String invalid_start_date = request.getParameter("invalid_start_date");
		String invalid_end_date = request.getParameter("invalid_end_date");
		String limit_count = request.getParameter("limit_count");
		String use_type = request.getParameter("use_type");
		//String create_time = request.getParameter("create_time");
		String status = request.getParameter("status");
		String min_amount = request.getParameter("min_amount");
		String min_days = request.getParameter("min_days");
		String remark = request.getParameter("remark");
		
		Coupon coupon = couponService.selectForObjectById(Long.parseLong(id));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		
		coupon.setCoupon_name(coupon_name);
		coupon.setCoupon_source(Integer.parseInt(coupon_source));
		coupon.setInterest(Double.parseDouble(interest));
		coupon.setInvalid_days(Integer.parseInt(invalid_days));
		if(!invalid_start_date.isEmpty())
			coupon.setInvalid_start_date(sdf.parse(invalid_start_date));
		if(!invalid_end_date.isEmpty())
			coupon.setInvalid_end_date(sdf.parse(invalid_end_date));
		coupon.setLimit_count(Integer.parseInt(limit_count));
		coupon.setUse_type(Integer.parseInt(use_type));
		coupon.setCreate_time(new Date());
		coupon.setStatus(Integer.parseInt(status));
		coupon.setMin_amount(Double.parseDouble(min_amount));
		coupon.setMin_days(Integer.parseInt(min_days));
		coupon.setRemark(remark);
		coupon.setCreate_time(new Date());
		coupon.setInvalid_type(Integer.parseInt(invalid_type));

		couponService.save(coupon);
		
		
		
		return new Result("ok");
	}
	
	@Autowired(required = true)
	public void setCouponService(ICouponService couponService) {
		this.couponService = couponService;
	}

}
