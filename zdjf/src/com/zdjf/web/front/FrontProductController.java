package com.zdjf.web.front;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductBuyIncomeVo;
import com.zdjf.domain.product.ProductBuyRob;
import com.zdjf.domain.product.ProductIncome;
import com.zdjf.domain.product.ProductIncomeVo;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.Loaner;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.service.file.IFileService;
import com.zdjf.service.fund.ICouponService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductBuyRobService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductIncomeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.ILoanerService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CommonUtils;
import com.zdjf.util.DateUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.UrlConstant;
import com.zdjf.webservice.express.SmsReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/")
public class FrontProductController {

	private IProductService productService;
	private IProductBuyService productBuyService;
	private ILenderService lenderService;
	private ILoanerService loanerService;
	private IUserService userService;
	private IUserFundStatService userFundStatService;
	private ICouponService couponService;
	private IUserCouponService userCouponService;
	private IUserGiftService userGiftService;
	private IProductIncomeService productIncomeService;
	private IFileService fileService;
	private IProductBuyRobService productBuyRobService;
	@Autowired
	private IProductBuyIncomeService productBuyIncomeService;


	@RequestMapping("product/list")
	public String toProductList(HttpServletRequest request,HttpServletResponse response,Model model){
		String status = request.getParameter("status");
		String dateType = request.getParameter("dateType");
		String type = request.getParameter("lltype");//类型
		String page = request.getParameter("page");//当前页
		
		Long start = 0L;
		Page p = new Page();
		if(page!=null && !"".equals(page)){
			start = p.getLimit() * (Long.valueOf(page)-1);
		}
		p.setStart(start);
		Map hashMap = new HashMap();
		if(status!=null && !"".equals(status)){
			hashMap.put("status", status);
		}
		if(dateType!=null && !"".equals(dateType)){
			Long day = 24*60*60L;
			String beginDay = "";
			String endDay = "";
			if(dateType.equals("1")){
				endDay = day * 30 + "";
			}else if(dateType.equals("2")){
				endDay = day * 60 + "";
				beginDay = day * 30 + "";
			}else if(dateType.equals("3")){
				endDay = day * 90 + "";
				beginDay = day * 60 + "";
			}else if(dateType.equals("4")){
				beginDay = day * 180 + "";
			}
			hashMap.put("beginDay", beginDay);
			hashMap.put("endDay", endDay);
		}
		if(type!=null && !"".equals(type)){
			String begin_income = "";
			String end_income = "";
			if(type.equals("1")){
				begin_income = "9";
				end_income = "10";
			}else if(type.equals("2")){
				begin_income = "10";
				end_income = "11";
			}else if(type.equals("3")){
				begin_income = "11";
				end_income = "12";
			}else if(type.equals("4")){
				begin_income = "12";
			}
			hashMap.put("begin_income", begin_income);
			hashMap.put("end_income", end_income);
		}
		if(type!=null && !"".equals(type)){
			hashMap.put("type", type);
		}
		hashMap.put("pagename", "selectProductListForMap");
		hashMap.put("total", "selectProductListCountForMap");
		Page productList = productService.page(p,hashMap);
		String showPage = productList.showPage(request, "product/list.action"); //获取分页
		request.setAttribute("productList", productList.getDataList());
		request.setAttribute("showPage", showPage);
		request.setAttribute("dateType", dateType);
		request.setAttribute("type", type);
		request.setAttribute("status", status);
		request.setAttribute("totalRecord",productList.getTotal());
		return "front/product/project_list";
	}
	
	/**
	 * 产品详情页
	 * @param request
	 * @param response
	 * @param model
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "product/detail/{proId}", method = RequestMethod.GET)
	public String toProductDetail(HttpServletRequest request,HttpServletResponse response,Model model, @PathVariable String proId){
		Long productId = StrUtils.convToLong(proId);
		String user_name = BrowseUtil.getCookie(request, response);
		String error = request.getParameter("error");

		Product product=productService.selectForObjectById(productId);  //获取产品详细信息
		ProductBuy productBuy=new ProductBuy();
		productBuy.setProduct_id(productId);
		List<ProductBuy> productBuyVoList= productBuyService.selectProductBuyList(productBuy);  //购买记录
		Lender lender=lenderService.selectForObjectById(product.getLender_id());  //出借人
		Loaner loaner=loanerService.selectForObjectById(product.getLoan_id());   //借款人
		Map fileMap = new HashMap();
		fileMap.put("from_id", lender.getId());
		fileMap.put("from_table","zd_lender");
		List lenderImgList = fileService.getFileList(fileMap);  //获取出借人图片列表
		fileMap = new HashMap();
		fileMap.put("from_id", loaner.getId());
		fileMap.put("from_table","zd_loaner");
		List loanerImgList = fileService.getFileList(fileMap);  //获取借款人图片列表
		fileMap = new HashMap();
		fileMap.put("from_id", product.getId());
		fileMap.put("from_table","zd_product");
		List productImgList = fileService.getFileList(fileMap);  //获取产品所有图片列表
		
		ProductIncome productIncome=new ProductIncome();
		productIncome.setProduct_id(product.getId());
		List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome); //获取产品利息
		ProductBuyRob productBuyRob = new ProductBuyRob();
		productBuyRob.setProduct_id(product.getId());
		List productBuyRobList = productBuyRobService.selectForList(productBuyRob); //获取夺标记录
		
		List user_coupon_ids = new ArrayList();
		if(productBuyVoList!=null && productBuyVoList.size()>0){
			for (int i=0;i<productBuyVoList.size();i++){
				ProductBuy pb = (ProductBuy)productBuyVoList.get(i);
				user_coupon_ids.add(pb.getUser_coupon_id());
			}
			Map<String, Object> prammap = new HashMap<String, Object>();
			prammap.put("user_coupon_ids", user_coupon_ids);
			List userCouponList = userCouponService.getUserCouponList(prammap);  //所有用户加息券信息
			Map hashMap = new HashMap();
			if(userCouponList!=null && userCouponList.size()>0){
				for(int i=0;i<userCouponList.size();i++){
					UserCoupon userCoupon = (UserCoupon)userCouponList.get(i);
					hashMap.put(userCoupon.getId(), userCoupon);
				}
			}
			request.setAttribute("hashMap", hashMap);
		}
		
		request.setAttribute("product", product);
		request.setAttribute("productBuyVoList", productBuyVoList);
		request.setAttribute("lender", lender);
		request.setAttribute("loaner", loaner);
		request.setAttribute("lenderImgList", lenderImgList);
		request.setAttribute("loanerImgList", loanerImgList);
		request.setAttribute("productImgList", productImgList);
		request.setAttribute("productIncomeList", productIncomeList);
		request.setAttribute("productBuyRobList", productBuyRobList);
		request.setAttribute("error", error);
		
		if(user_name!=null && !"".equals(user_name)){
			User user = userService.selectForObjectByPhone(user_name);
			UserFundStat userFundStat=new UserFundStat();
			userFundStat.setUser_id(user.getId());
			userFundStat=userFundStatService.selectForObjectById(userFundStat);  //用户资金统计信息
			request.setAttribute("userFundStat", userFundStat);
			Map hashMap = new HashMap();
			hashMap.put("min_days", product.getIncomeDays());
			hashMap.put("user_id", user.getId());
			hashMap.put("status", 1);//未使用
			hashMap.put("end_date", DateUtil.addDay(new Date(), -1));
			List userCouponList = couponService.getUserCouponList(hashMap); //查询用户加息券
			hashMap.put("amount", 1000000);
			List userGiftList = userGiftService.getUserGiftList(hashMap); //用户红包券
			request.setAttribute("userCouponList", userCouponList);
			request.setAttribute("userGiftList", userGiftList);
			request.setAttribute("user", user);
		}
		
		
		
/*
		Coupon coupon=new Coupon();
		List<CouponVo> couponList=couponService.selectForList(coupon);
		Map<String,Object> couponMap=new HashMap<String, Object>();
		for(int i=0;i<couponList.size();i++){
			couponMap.put(couponList.get(i).getId().toString(), couponList.get(i).getInterest());
		}
		User user=new User();
		user=userService.selectForObjectByPhone(user_name);
		
		UserCoupon userCoupon=new UserCoupon();
		userCoupon.setUser_id(user.getId());
		userCoupon.setStatus(1);
		List<UserCouponVo> userCouponList=userCouponService.selectForList(userCoupon);
		UserGift userGift=new UserGift();
		userGift.setUser_id(user.getId());
		userGift.setStatus(1);
		List<UserGiftVo> userGiftList=userGiftService.selectForList(userGift);
		
		for(int i=0;i<productBuyVoList.size();i++){
			Map<String, Object> productBuyVoMap=productBuyVoList.get(i);
			String coupon_id=productBuyVoMap.get("user_coupon_id").toString();
			productBuyVoMap.put("interest",couponMap.get(coupon_id));
			String phone=productBuyVoMap.get("phone").toString();
			String nphone=phone.substring(3, 8);
			nphone=phone.replace(nphone, "*****");
			productBuyVoMap.put("nphone", nphone);
		}

		

		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user.getId());
		userFundStat=userFundStatService.selectForObjectById(userFundStat);

		Map<String, Object>map=new HashMap<String, Object>();
		map.put("product", product);
		map.put("lender", lender);
		map.put("loaner", loaner);
		map.put("userFundStat", userFundStat);
		map.put("productBuy", productBuyVoList);
		map.put("userCouponList", userCouponList);
		map.put("userGiftList", userGiftList);
		map.put("user", user);
		Format f = new SimpleDateFormat("yyyy-MM-dd");  
		Date today = new Date();  

		Calendar c = Calendar.getInstance();  
		c.setTime(today);  
		c.add(Calendar.DAY_OF_MONTH, 1);
		Date tomorrow = c.getTime();
		map.put("incomeStart",f.format(tomorrow));
		model.addAllAttributes(CommonUtils.packageResult(true, map));*/
		return "front/product/product_detail";
	}

	@RequestMapping(value = "product/getIncome", method = RequestMethod.GET)
	public @ResponseBody SmsReturn getIncome(HttpServletRequest request,HttpServletResponse response){
		String user_name=BrowseUtil.getCookie(request, response);
		User user=userService.selectForObjectByPhone(user_name);
		Map<String, Object>map=new HashMap<String, Object>();
		String coupon_id=RequestUtils.getReqString(request, "coupon_id");
		String amount=RequestUtils.getReqString(request, "amount");
		SmsReturn sr=new SmsReturn();
		if(amount.equals("")){
			map.put("income", "未输入投资金额");
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent(map);
			return sr;
		}
		UserCoupon userCoupon = new UserCoupon();
		userCoupon.setCoupon_id(StrUtils.convToLong(coupon_id));
		userCoupon.setUser_id(user.getId());
		double income=0.0;
		List<UserCouponVo> listCoupon  = userCouponService.selectForList(userCoupon);
		if(listCoupon.size() == 1){
			UserCoupon coupon = listCoupon.get(0);
			if(coupon.getStatus() == 1){
				coupon.setStatus(0);
				income =StrUtils.convToDouble(amount) *coupon.getInterest()*DateUtil.dateSub(coupon.getEnd_date(), 
						coupon.getStart_date())*0.01/360;
				coupon.setCoupon_id(StrUtils.convToLong(coupon_id));
				coupon.setUser_id(user.getId());
				userCouponService.updateUserCouponByCouponId(userCoupon);
			}
		}
		map.put("income", income);
		sr.setReturnCase(true);
		sr.setStatus(100);
		sr.setContent(map);
		return sr;
	}

	@RequestMapping("/product/order")
	public String toOrder(HttpServletRequest request,HttpServletResponse respons,Model model){
		String user_id=BrowseUtil.getCookie(request, respons);
		String amount=RequestUtils.getReqString(request, "amount");
		String gift_id=RequestUtils.getReqString(request, "gift_id");
		String coupon_id=RequestUtils.getReqString(request, "coupon_id");
		String product_id=RequestUtils.getReqString(request, "product_id");
		Map<String, Object>map =new HashMap<String, Object>();
		User user=userService.selectForObjectByPhone(user_id);
		if(""==gift_id){
			map.put("gift_value", "暂无可用");
		}
		if(""==coupon_id){
			map.put("coupon_value", "暂无可用");
		}
		UserGift userGift=new UserGift();
		userGift.setUser_id(user.getId());
		userGift.setGift_id(StrUtils.convToLong(gift_id));
		List<UserGiftVo> userGiftList=userGiftService.selectForList(userGift);
		UserCoupon userCoupon=new UserCoupon();
		userCoupon.setCoupon_id(StrUtils.convToLong(coupon_id));
		userCoupon.setUser_id(user.getId());
		userCoupon=userCouponService.selectForObjectByCouponId(userCoupon);
		UserFundStat userFundStat=new UserFundStat();
		userFundStat.setUser_id(user.getId());
		userFundStat=userFundStatService.selectForObjectById(userFundStat);
		Product product=productService.selectForObjectById(StrUtils.convToLong(product_id));
		ProductIncome productIncome=new ProductIncome();
		productIncome.setProduct_id(StrUtils.convToLong(product_id));
		List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome);
		double willIncome=StrUtils.convToDouble(amount) *userCoupon.getInterest()*DateUtil.dateSub(userCoupon.getEnd_date(), 
				userCoupon.getStart_date())*0.01/360;
		map.put("userGift", userGiftList.get(0));
		map.put("userCoupon", userCoupon);
		map.put("userFundStat", userFundStat);
		map.put("product", product);
		map.put("productIncomeList", productIncomeList);
		map.put("amount", amount);
		map.put("willIncome", willIncome);
		model.addAllAttributes(CommonUtils.packageResult(true, map));
		return "front/product/order";
	}

	@RequestMapping(value = "/product/agreement", method = RequestMethod.GET)
	public String agreement (HttpServletRequest request,HttpServletResponse response,Model model){
		String domainName = request.getServerName();
		String type = request.getParameter("type");
		model.addAttribute("type",Integer.parseInt(type));
		String user_name="";
		if ("1".equals(type)) {//非范本，生成pdf
			user_name = request.getParameter("user_name");
		} else {//范本展示
			if(UrlConstant.MOBILE_URL.equals(domainName)) {  //判断是否来自手机端 手机端返回手机页面
				return "front/m/agreement_app";
			}
			user_name=BrowseUtil.getCookie(request, response);
			//预期年化收益
			Double interest = Double.valueOf(request.getParameter("interest"));
			model.addAttribute("interest",interest);
		}
		double amt = Double.valueOf(request.getParameter("amt"));//用户投资本金
		model.addAttribute("amt",amt);
		double totalIncome = Double.valueOf(request.getParameter("totalIncome"));//用户预计总收益
		model.addAttribute("income",totalIncome);
		User user=userService.selectForObjectByPhone(user_name);
		model.addAttribute("user",user);
		//用户投标信息
		String proId = request.getParameter("proId");
		Long productId;
		ProductBuy productBuy = null;
		if("1".equals(type)){
			productBuy = productBuyService.selectForObjectById(Long.valueOf(proId));
			productId = productBuy.getProduct_id();
			model.addAttribute("productBuy",productBuy);
		}else{
			productId = Long.valueOf(proId);
		}
		Product product=productService.selectForObjectById(productId);
		model.addAttribute("product",product);

		//合同天数
		long day;
		if(product.getStatus()==4){
			day = (product.getEnd_date().getTime()- new Date().getTime())/(1000*3600*24)+1;
		}else{
			day = (productBuy.getEnd_date().getTime() - productBuy.getStart_date().getTime())/(1000*3600*24) + 1;
		}
		model.addAttribute("day",day);

		//出借方信息
		Lender lender = lenderService.selectForObjectById(product.getLender_id());
		model.addAttribute("lender",lender);

		//当前系统时间
		String currDateStr = DateUtil.formatDate(new Date());
		if("1".equals(type)){
			model.addAttribute("currDateStr",DateUtil.formatDate(productBuy.getPay_time()));
		}else{
			model.addAttribute("currDateStr",currDateStr);
		}

		//还款计划
		model.addAttribute("tempMap",buyIncomes(product,productBuy, amt,totalIncome,day));
		if(UrlConstant.MOBILE_URL.equals(domainName)) {  //判断是否来自手机端 手机端返回手机页面
			return "front/m/agreement_app";
		}
		return "front/product/agreement";
	}

	/**
	 * 根据产品获取还款计划
	 * @param product
	 * @param productBuy
	 * @param amount
	 * @param totalIncome
	 * @param totalDays
	 * @return
	 */
	public TreeMap<String, Double> buyIncomes(Product product,ProductBuy productBuy, Double amount, Double totalIncome,long totalDays) {
		// 计息记录
		TreeMap<String, Double> incomesMap = new TreeMap<>();
		// 系统当前时间
		Date date = new Date();
		// T+1时，计息起始时间要加一天
		if(product.getIncome_method() == 2) {
			date = DateUtil.addDate(date,1);
		}
		if (product.getStatus() == 4) {//投资中
			// 若是按月付息，查询计息模板
			if (2 == product.getReturn_method()) {
				// 查询计息结束日期大于支付时间的全部计息模板，用以生成用户计息记录
				ProductIncome productIncome=new ProductIncome();
				productIncome.setProduct_id(product.getId());
				List<ProductIncomeVo> productIncomeList=productIncomeService.selectForList(productIncome); //获取产品利息
				boolean flag = false;
				for (int i = 0; i < productIncomeList.size(); i++) {
					ProductIncome pIncome = productIncomeList.get(i);
					Integer days = 0;// 计息天数
					// 第一条记录因未满足完全位于计息周期内，所以 需要手动计算开始日期和天数，往后的记录直接从模板中拿
					if (0 == i) {
						days = DateUtil.between(date, pIncome.getEnd_date())+1;
						if (days <= 0) {
							flag = true;
							continue;
						}
					} else {
						if (flag) {
							days = DateUtil.between(date, pIncome.getEnd_date())+1;
							if (days <= 0) {
								flag = true;
								continue;
							} else {
								flag = false;
							}
						} else {
							days = DateUtil.between(pIncome.getStart_date(),pIncome.getEnd_date())+1;
						}
					}

					Double incomed = calIncome(totalIncome, totalDays, days);// 利息收益
					// 如果是最后一条计息记录，则此条记录应一起归还本金
					if ((i + 1) == productIncomeList.size()) {
						incomed += amount;
					}

					// 保留两位小数，四舍五入
					incomed = new BigDecimal(incomed).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

					// 添加到集合
					incomesMap.put(DateUtil.formatDate(pIncome.getPay_date()), incomed);// 付息日
				}
			} else {
				Integer days = DateUtil.between(date, product.getEnd_date()) + 1; //计息天数
				Double incomed = calIncome(totalIncome, totalDays, days); //收益
				incomed += amount; //加上本金

				// 保留两位小数，四舍五入
				incomed = new BigDecimal(incomed).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				// 添加到集合
				incomesMap.put(DateUtil.formatDate(product.getEnd_date()), incomed);// 付息日
			}
		} else {//履约中或已还款
			ProductBuyIncome productBuyIncome = new ProductBuyIncome();
			productBuyIncome.setBuy_id(productBuy.getId());
			List<ProductBuyIncomeVo> buysIncomes = productBuyIncomeService.selectForList(productBuyIncome);
			for (int i = 0; i < buysIncomes.size(); i++) {
				ProductBuyIncome pbIncome = buysIncomes.get(i);
				Double incomed = calIncome(totalIncome, totalDays, pbIncome.getDays());// 利息收益
				// 如果是最后一条计息记录，则此条记录应一起归还本金
				if ((i + 1) == buysIncomes.size()) {
					incomed += amount;
				}
				// 保留两位小数，四舍五入
				incomed = new BigDecimal(incomed).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				// 添加到集合
				incomesMap.put(DateUtil.formatDate(pbIncome.getPay_date()), incomed);// 付息日
			}
		}

		return incomesMap;
	}
	/**
	 * 计算收益(计算公式：总收益/年计息天数*收益天数)
	 * @param totalIncome 总收益
	 * @param totalDays 总收益天数
	 * @param days 每一期收益天数
	 */
	public Double calIncome(Double totalIncome, long totalDays, int days) {
		//计算收益
		return Double.valueOf(RoofNumberUtils.formatString(totalIncome/totalDays*days));
	}
	@Autowired(required=true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	@Autowired(required=true)
	public void setLenderService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}
	@Autowired(required=true)
	public void setLoanerService(ILoanerService loanerService) {
		this.loanerService = loanerService;
	}
	@Autowired(required=true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@Autowired(required=true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}
	@Autowired(required=true)
	public void setCouponService(ICouponService couponService) {
		this.couponService = couponService;
	}
	@Autowired(required=true)
	public void setUserCouponService(IUserCouponService userCouponService) {
		this.userCouponService = userCouponService;
	}
	@Autowired(required=true)
	public void setUserGiftService(IUserGiftService userGiftService) {
		this.userGiftService = userGiftService;
	}
	@Autowired(required=true)
	public void setProductIncomeService(IProductIncomeService productIncomeService) {
		this.productIncomeService = productIncomeService;
	}
	@Autowired(required=true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	
	@Autowired(required=true)
	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
	}
	
	@Autowired(required=true)
	public void setProductBuyRobService(IProductBuyRobService productBuyRobService) {
		this.productBuyRobService = productBuyRobService;
	}
	
	

}
