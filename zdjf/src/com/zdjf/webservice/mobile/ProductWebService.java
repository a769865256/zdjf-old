package com.zdjf.webservice.mobile;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.SinaResultUtil;
import com.zdjf.components.mobile.ValuetoString;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.notice.News;
import com.zdjf.domain.notice.NewsVo;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductBuyIncomeVo;
import com.zdjf.domain.product.ProductBuyVo;
import com.zdjf.domain.product.ProductIncome;
import com.zdjf.domain.product.ProductIncomeVo;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.domain.user.Loaner;
import com.zdjf.domain.user.LoanerVo;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.file.IFileService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductIncomeService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.ILoanerService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.DateUtil;
import com.zdjf.util.Md5Util;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.util.StringUtil;
import com.zdjf.webservice.express.SmsReturn;


class Rank{
	String id;
	String phone;
	String propervalue;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPropervalue() {
		return propervalue;
	}
	public void setPropervalue(String propervalue) {
		this.propervalue = propervalue;
	}
	
	
}
@Controller
@RequestMapping("/m/product")
public class ProductWebService {
	
	private IUserService userService;
	
	private ILenderService lenderService;
	
	private ILoanerService loanerService;
	
	private IProductBuyService productBuyService;
	
	private IProductBuyIncomeService productBuyIncomeService;
	
	private IProductIncomeService productIncomeService;
	
	private IProductService productService;
	
	private IFileService fileService;
	
	private IFundsService fundsService;
	
	private ICoinStreamService coinStreamService;
	
	private IUserFundStatService userFundStatService;
	
	private ApplicationContext applicationContext;
	
	private  boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
	}
	
	//产品详情
	@RequestMapping(value="/details",method=RequestMethod.GET)
	public @ResponseBody SmsReturn details(HttpServletRequest request,
            HttpServletResponse response) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		//获取用户信息
		
		String product_id = request.getParameter("product_id");
		String reg_source=RequestUtils.getReqString(request, "reg_source");
		SmsReturn sr = new SmsReturn();
		if(product_id.isEmpty() || !isInteger(product_id)){
			sr.setContent("产品ID不能为空");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		
		
		Product product = productService.selectForObjectById(Long.parseLong(product_id));
		if(product == null){
			sr.setContent("没有此产品");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		if("2".equals(reg_source)){
			product.setProduct_code(product.getProduct_code().replace("车财道", "正道金服"));
		}
		product.setBalance(product.getBalance() - SinaResultUtil.invested(product.getId(), 3, fundsService));
		Map<String,String> map = ValuetoString.eachProperties(product);
		Lender lender = new Lender();
		lender.setId(product.getLender_id());
		List<LenderVo> lenderVo = lenderService.selectForList(lender);
		Loaner loander = new Loaner();
		loander.setId(product.getLoan_id());
		List<LoanerVo> loanerVo  = loanerService.selectForList(loander);
		
		Map<String,Object> mapResult = new HashMap<String,Object>();
		
		ProductIncome productIncome = new ProductIncome();
		productIncome.setProduct_id(Long.parseLong(product_id));
		
		List<ProductIncomeVo> list_income = productIncomeService.selectForList(productIncome);
		
	
		
		Map<String,String> fileMap = new HashMap<String,String>();
		
		fileMap.put("from_id", lenderVo.get(0).getId() + "");
		fileMap.put("from_table","zd_lender");
		List lenderImgList = fileService.getFileList(fileMap);  //获取出借人图片列表
		fileMap = new HashMap();
		fileMap.put("from_id", loanerVo.get(0).getId() + "");
		fileMap.put("from_table","zd_loaner");
		List loanerImgList = fileService.getFileList(fileMap);  //获取借款人图片列表
		fileMap = new HashMap();
		fileMap.put("from_id", product.getId() + "");
		fileMap.put("from_table","zd_product");
		List productImgList = fileService.getFileList(fileMap);  //获取产品所有图片列表
		
		
		
		Date date = new Date();
		Date start_dates = date;
		if(product.getIncome_method() == 2)//T+1
		{
			start_dates = DateUtil.addDay(date, 1);
			
		}
		
		if(product.getStatus() != 4){
			if(product.getIncome_method() == 2){
				start_dates = DateUtil.addDay(product.getStart_date(),1);
			}else{
				start_dates = product.getStart_date();
			}
			
		}
		
		int income_days = DateUtil.incomeDays(product.getEnd_date(),start_dates);
		
		if(income_days<0)
			income_days = 0;
		map.put("income_days", income_days + "");//收益天数
		map.put("start_dates", DateUtil.toShortString(start_dates));//开始时间
		mapResult.put("product", map);//产品map
		if(lenderVo.size()>0){
			LenderVo vo = lenderVo.get(0);
			String address = vo.getAddress();
			if(address != null){
				int index = address.indexOf("市");
				int ind = address.indexOf("县");
				if(index<ind)
					index = ind;
				String hide = StringUtil.hideStr(index+1, address, 0);

				vo.setAddress(hide);
			}
			String name = vo.getName();
			if(name != null){
				int index = 1;
				String hide = StringUtil.hideStr(index, name, 0);

				vo.setName(hide);
			}
			
			mapResult.put("lender", vo);
		}
			
		if(loanerVo.size()>0){
			LoanerVo vo = loanerVo.get(0);
			
			String address = vo.getAddress();
			if(address != null){
				int index = address.indexOf("市");
				int ind = address.indexOf("县");
				if(index<ind)
					index = ind;
				String hide = StringUtil.hideStr(index+1, address, 0);
				vo.setAddress(hide);
			}
			String name = vo.getName();
			if(name != null){
				int index = 1;
				String hide = StringUtil.hideStr(index, name, 0);
				vo.setName(hide);
			}
			
			mapResult.put("loan", vo);
		}
			
		mapResult.put("list_income", list_income);//分期
		mapResult.put("lenderImgList", lenderImgList);
		mapResult.put("loanerImgList", loanerImgList);
		mapResult.put("productImgList", productImgList);
		//mapResult.put("income_days", income_days + "");//时间间隔
		//mapResult.put("start_dates", DateUtil.toShortString(start_dates));//开始时间
		
		sr.setContent(mapResult);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	
	//产品详情
	@RequestMapping(value="/invest",method=RequestMethod.GET)
	public @ResponseBody SmsReturn invest(HttpServletRequest request,
            HttpServletResponse response) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	
		String product_id = request.getParameter("project_id");
		SmsReturn sr = new SmsReturn();
		
		Product product = productService.selectForObjectById(Long.parseLong(product_id));
		if(product == null){
			sr.setContent("没有此产品");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		Double balance = product.getBalance() - SinaResultUtil.invested(product.getId(), 3, fundsService);
		
		sr.setContent(SinaResultUtil.doubleToTwo(balance).toString());
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
			
	
	//获取购买取消
	@RequestMapping(value="/buy/cancel",method=RequestMethod.POST)
	public @ResponseBody SmsReturn buyCancel(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		String product_id = request.getParameter("buy_id");
		SmsReturn sr = new SmsReturn();
		ProductBuy buy = new ProductBuy();
		buy.setId(Long.parseLong(product_id));
		
		List<ProductBuyVo> buys = productBuyService.selectForList(buy);
		
		ProductBuy vo = buys.get(0);
		vo.setStatus(-4);
		
		productBuyService.updateProductBuyById(vo);
		
		
		
		sr.setContent("");
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	@RequestMapping(value="/my/investment/details",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getProjectDetails(HttpServletRequest request,
            HttpServletResponse response)throws Exception{
		SmsReturn sr = new SmsReturn();
		
		String user_name = request.getParameter("phone");
		String reg_source = request.getParameter("reg_source");
		String project_id = request.getParameter("project_id");
		String buy_id = request.getParameter("buy_id");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		if( null ==sign || sign.isEmpty()){
			sr.setReturnCase(false);
			sr.setStatus(101);
			sr.setContent("加密数据为空");
			
			return sr;
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		User user = userService.selectForObjectByPhone(user_name);
		Product product = productService.selectForObjectById(Long.parseLong(project_id));
		
		if(product == null){
			sr.setContent("没有此产品");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		
		if(buy_id==null||buy_id.equalsIgnoreCase("0")){
			sr.setContent("请添加数据buy_id");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}
		ProductBuy allBuy = new ProductBuy();
		allBuy.setId(Long.parseLong(buy_id));
		List<ProductBuyVo> allList = productBuyService.selectForList(allBuy);
		
		if(allList.size() == 0){
			sr.setContent("没买此产品");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		
		
		ProductBuyIncome productBuyIncome = new ProductBuyIncome();
		productBuyIncome.setUser_id(user.getId());
		productBuyIncome.setBuy_id(Long.parseLong(buy_id));
		List<ProductBuyIncomeVo> buys = productBuyIncomeService.selectForList(productBuyIncome);
		
		
		ProductBuyVo vo = allList.get(0);
		ProductBuy ns = new ProductBuy();
		BeanUtils.copyProperties(vo,ns);
		
		Map<String,Object> mapO = new HashMap<String,Object>();
		
		Map<String,String> map = ValuetoString.eachProperties(ns);
		mapO.put("product", map);
		mapO.put("buyIncome",buys);
		mapO.put("return_method", product.getReturn_method() + "");
		
		
		/*map.put("myStart", DateUtil.formatDate(buy.getStart_date()));//我的计息开始
		map.put("allBuy", allList.size() + "");//一共多少投资人
*/		
		sr.setContent(mapO);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	//获取利息四舍五入
	@RequestMapping(value="/investment/can",method=RequestMethod.POST)
	public @ResponseBody SmsReturn investmentCan(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		String coupon_interest = request.getParameter("coupon_interest");
		String amount = request.getParameter("amount");
		String income_days = request.getParameter("income_days");
		SmsReturn sr = new SmsReturn();
		
		Double income = RoofNumberUtils.StringtoDouble(amount)*RoofNumberUtils.StringtoDouble(coupon_interest)*Integer.parseInt(income_days)*0.01/360;
		//操持两位小数
		income = SinaResultUtil.doubleToTwo(income);
		sr.setContent(income);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	//获取投资记录
	@RequestMapping(value="/investment/records",method=RequestMethod.POST)
	public @ResponseBody SmsReturn investmentRecords(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		String product_id = request.getParameter("product_id");
		SmsReturn sr = new SmsReturn();
		ProductBuy buy = new ProductBuy();
		buy.setProduct_id(Long.parseLong(product_id));
		buy.setStatus(1);
		//List<ProductBuyVo> buys = productBuyService.selectForList(buy);
		
		Page page = PageUtils.createPage(request);
		page = productBuyService.page(page, buy);
		
		
		
		sr.setContent(page);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	//获取投资
	@RequestMapping(value="/investment/rank",method=RequestMethod.POST)
	public @ResponseBody SmsReturn investmentRank(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		String product_id = request.getParameter("product_id");
		SmsReturn sr = new SmsReturn();
		ProductBuy buy = new ProductBuy();
		buy.setProduct_id(Long.parseLong(product_id));
		
		List<ProductBuyVo> buys = productBuyService.selectForList(buy);
		
		
		Collections.sort(buys,new Comparator<ProductBuyVo>(){
            public int compare(ProductBuyVo arg0, ProductBuyVo arg1) {
                return arg0.getId().compareTo(arg1.getId());
            }
	    });
		if(buys.size()<=0){
			Rank rank = new Rank();
			sr.setMapContent(rank);
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}
		
		List<Rank> fastList = new ArrayList<Rank>();
		
		int end = buys.size()>3?3:buys.size();
		
		for(int i = 0;i<end;i++){
			
			Rank rank = new Rank();
			rank.setId(i+1 + "");
			rank.setPhone(buys.get(i).getPhone());
			Integer value = 0;
			if(i == 0)
				value = 5;
			else if(i == 1)
				value = 3;
			else if(i == 2)
				value = 1;
			
			rank.setPropervalue(value.toString());
			
			//applicationContext.publishEvent(new FundEvent(this,));
			
			fastList.add(rank);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		map.put("fast", fastList);//快手
		
		
		
		Product pro = new Product();
		pro.setId(Long.parseLong(product_id));
		List<ProductVo> productList = productService.selectForList(pro);
		if(productList.size() != 1){
			System.out.println("存在多个标");
		}
		Product product = productList.get(0);
		
		if(product.getBalance() != 0.0){
			Rank rank1 = new Rank();
			map.put("tail", rank1);//尾
		}else{
			Rank rank1 = new Rank();
			rank1.setId(1 + "");
			rank1.setPhone(buys.get(buys.size()-1).getPhone());
			rank1.setPropervalue(8+"");
			map.put("tail", rank1);//尾
		}
		
		
		Collections.sort(buys,new Comparator<ProductBuyVo>(){
            public int compare(ProductBuyVo arg0, ProductBuyVo arg1) {
                return arg1.getAmount().compareTo(arg0.getAmount());
            }
	    });
		
		List<Rank> heavyweight = new ArrayList<Rank>();
		
		
		if(product.getBalance() != 0.0)
			map.put("heavyweight", heavyweight);//重量级
		else{
			heavyweight = new ArrayList<Rank>();
			map.put("heavyweight", heavyweight);
			int ends = buys.size()>2?2:buys.size();
			for(int i = 0;i<ends;i++){
				Rank rank = new Rank();
				rank.setId( i + 1 + "");
				rank.setPhone(buys.get(i).getPhone());
				Double value = 0.0;
				if(i == 0)
					value = buys.get(i).getAmount()*0.0007;
				else if(i == 1)
					value = buys.get(i).getAmount()*0.0003;
		
				
				rank.setPropervalue(SinaResultUtil.doubleToTwo(value).toString());
				
				heavyweight.add(rank);
			}
		}
		
		
		sr.setMapContent(map);
		sr.setReturnCase(true);
		sr.setStatus(100);
		return sr;
	}
	
	
	//未支付的订单
	@RequestMapping(value="/investment/details",method=RequestMethod.POST)
	public @ResponseBody SmsReturn detailsInvestment(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String product_id = request.getParameter("product_id");
		String status = request.getParameter("status");
		String reg_ip = request.getParameter("ip");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(user == null){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
			
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
		}
		
		Product product = productService.selectForObjectById(Long.parseLong(product_id));
		
		ProductBuy productBuy = new ProductBuy();
		productBuy.setStatus(Integer.parseInt(status));
		productBuy.setProduct_id(Long.parseLong(product_id));
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
		ProductBuy buy = listBuy.get(0);
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("product", product);//产品详情
		map.put("buy", buy);//购买详情
		
		sr.setContent(map);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	//我的投资
	@RequestMapping(value="/mybuy",method=RequestMethod.GET)
	public @ResponseBody SmsReturn myBuy(HttpServletRequest request,
            HttpServletResponse response) throws ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		//获取用户信息
		String user_name = request.getParameter("phone");
		User user = userService.selectForObjectByPhone(user_name);
		String reg_source = request.getParameter("reg_source");
		String reg_ip = request.getParameter("ip");
		String status = request.getParameter("status");
		String sign = request.getParameter("sign");
		String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
				Constants.API_KEY);
		
		
		SmsReturn sr = new SmsReturn();
		if(user == null){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
			
		}
		
		if(!sign.equalsIgnoreCase(md5Sign)){
			sr.setContent("无效数据");
			sr.setReturnCase(false);
			sr.setStatus(101);
		}
		
		//Product product = productService.selectForObjectById(Long.parseLong(product_id));
		
		ProductBuy productBuy = new ProductBuy();
		productBuy.setUser_id(user.getId());
		
		
		if(status != null )
			productBuy.setStatus(Integer.parseInt(status));
		//productBuy.setProduct_id(Long.parseLong(product_id));
		//List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
		Page page = PageUtils.createPage(request);
		page = productBuyService.page(page, productBuy);
		
		int currentPage = page.getCurrentPage().intValue();
		int limit = page.getLimit().intValue();
		
		int total = page.getTotal().intValue();
		int start = limit * (currentPage - 1);
		int end = total >= limit*currentPage?limit*currentPage:total;
		
		
		List<ProductBuyVo> list = productBuyService.selectForList(productBuy);
		
		List<Map<String,String>> listS = new ArrayList<Map<String,String>>();
		for(int i = start;i<end;i++){
			ProductBuyVo vo = (ProductBuyVo)list.get(i);
			ProductBuy ns = new ProductBuy();
			BeanUtils.copyProperties(vo,ns);
			
			Map<String,String> map = ValuetoString.eachProperties(ns);
			
			listS.add(map);
		}
		
		page.setDataList(listS);
		
		sr.setMapContent(page);
		sr.setReturnCase(true);
		sr.setStatus(100);
		
		return sr;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setLenderService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}
	
	@Autowired(required = true)
	public void setLoanerService(ILoanerService loanerService) {
		this.loanerService = loanerService;
	}
	
	@Autowired(required = true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	
	@Autowired(required = true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	
	@Autowired(required = true)
	public void setProductIncomeService(IProductIncomeService productIncomeService) {
		this.productIncomeService = productIncomeService;
	}
	
	@Autowired(required = true)
	public void setProductBuyIncomeService(IProductBuyIncomeService productBuyIncomeService) {
		this.productBuyIncomeService = productBuyIncomeService;
	}
	
	@Autowired(required = true)
	public void setFileService(IFileService fileService) {
		this.fileService = fileService;
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
	
	@Autowired(required = true)
	public void setFundsService(IFundsService fundsService) {
		this.fundsService = fundsService;
	}

}
