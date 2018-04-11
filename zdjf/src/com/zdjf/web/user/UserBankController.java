package com.zdjf.web.user;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.fund.Bank;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.fund.IBankService;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StringUtil;
import com.zdjf.webservice.express.SmsReturn;


@Controller
@RequestMapping("/userBank")
public class UserBankController {
	
	private IUserBankService userBankService;
	
	private IBankService bankService;
	
	private IUserService userService;
	
	//添加可用银行
	@RequestMapping(value="/bank",method=RequestMethod.POST)
	public Result bindBank(HttpServletRequest request,
            HttpServletResponse response) {
		
		
		String number = request.getParameter("number").trim();
		String bank_name = request.getParameter("bank_name").trim();
		
		
		Bank bank = new Bank();
		bank.setName(bank_name);
		bank.setNum(number);
		bank.setCreate_time(new Date());
		bank.setRemark("后台添加银行名称");
		
		bankService.save(bank);
		
		return new Result(Result.SUCCESS,"银行卡绑定成功");
	}
		
	
	
	//用户绑定银行卡
	@RequestMapping(value="/bind",method=RequestMethod.POST)
	public Result bindBankCard(HttpServletRequest request,
            HttpServletResponse response) {
		
		String user_name = BrowseUtil.getCookie(request, response);
		if(user_name.isEmpty()){
			return new Result(Result.FAIL,"获取的用户信息失败");
		}
		

		String bank_no = request.getParameter("bank_no");
		String bank_alias = request.getParameter("bank_alias");
		String phone = request.getParameter("phone");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String real_name = RequestUtils.getReqString(request,"real_name");
		String idcard_no = RequestUtils.getReqString(request,"idcard_no");

		User user = userService.selectForObjectByPhone(user_name);
		
		//验证一下  是否存在快捷支付
		UserBank userBank = new UserBank();
		userBank.setUser_id(user.getId());
		userBank.setType(1);
		
		List<UserBankVo> list = userBankService.selectForList(userBank);
		
		if(list.size()>0){

			return new Result(Result.FAIL,"此用户绑定银行卡没有");
		}
		
		Date date = new Date();
		
		//唯一标识   设定
		String card_attribute = "C";
		String request_no = "zdjfu" + date.getTime() + StringUtil.getRandom();
		String result = SinaUtil.bindBankCard(user.getId(),request_no,bank_alias,
				bank_no,card_attribute,phone,province,city,user.getReg_ip(),user.getReal_name_auth(),real_name,idcard_no);
		System.out.println(result);
		
		if(result == null || result.trim().isEmpty()){
			
			return new Result(Result.FAIL,"新浪接口问题");
		}
		
		String remark = user.getRemark();
		if(remark == null || remark.isEmpty())
			user.setRemark("bank:" + result);
		else{
			
			user.setRemark(remark + ";bank:" + result);
		}
			
		
		Map resultMap = JsonUtil.jsonToMap(result);
		String strCode = (String) resultMap.get("response_code");
		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			
			return new Result(Result.FAIL,"此用户绑定银行卡没有");
		}
		
		
		String ticket = (String) resultMap.get("ticket");
		user.setPay_passwd(ticket);
		user.setPay_account(request_no);
		user.setStatus(1);
		userService.updateForObjectByPhone(user);
		
		return new Result(Result.SUCCESS,"银行卡绑定成功");
	}
	
	
	//获取短信验证
	@Transactional(rollbackFor = { Exception.class }) 
	@RequestMapping(value="/bank/advance",method=RequestMethod.POST)
	public @ResponseBody Result getBankAdvance(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		SmsReturn sr = new SmsReturn();
		String user_name = request.getParameter("user_name");
		String reg_source = request.getParameter("valid_code");
		
		
		//银行卡内容
		String bank_no = request.getParameter("bank_no");
		String bank_alias = request.getParameter("bank_alias");
		String phone = request.getParameter("phone");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		
		
		
		
		String card_attribute = "C";
		User user = userService.selectForObjectByPhone(user_name);
		String ticket = user.getPay_passwd();
		//
		String result = SinaUtil.bindBankCardAdvance(ticket, reg_source, user.getReg_ip());
		System.out.println(result);
		
		if(result.trim().isEmpty()){
			
			return new Result(Result.FAIL,"新浪接口问题");
		}
		
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent("此用户绑定银行卡确实失败");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return new Result(Result.ERROR,"error");
		}
		
		String card_id = (String) resultMap.get("card_id");
		user.setUpdate_time(new Date());
		
		//支付密码
		result = SinaUtil.setPayPassword(user.getId(),1,user.getReg_ip(),user_name,"1");
		System.out.println("playpassword :" + result);
		user.getRemark();
		
		
		/*if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}*/
		
		resultMap = JsonUtil.jsonToMap(result);
		strCode = (String) resultMap.get("response_code");
		
		/*if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);
			
			return sr;
		}*/
		
		Map dataMap =  (Map) resultMap.get("data");
		String redirect_url = (String) dataMap.get("redirect_url");
		
		//支付密码用md5
		user.setPay_passwd(redirect_url);
		if(user.getStatus() == 1){
			user.setStatus(2);
			sr.setMapContent(redirect_url);
		}
		else if(user.getStatus() == 4){
			user.setStatus(3);
			sr.setMapContent("支付密码已经设置");
		}
		userService.updateForObjectByPhone(user);
		UserBank userBank = new UserBank();
		
		userBank.setCard_id(card_id);
		userBank.setUser_id(user.getId());
		userBank.setBank_alias(bank_alias);
		userBank.setBank_no(bank_no);
		userBank.setPhone(phone);
		userBank.setCreate_time(new Date());
		userBank.setStatus(1);
		userBank.setType(1);//快捷卡
		userBank.setTicket(ticket);
		userBank.setProvince(province);
		userBank.setCity(city);
		userBank.setCard_attribute(card_attribute);
		userBank.setRequest_no(user.getPay_account());//
		//保存银行
		userBankService.save(userBank);
		
		return new Result(Result.SUCCESS,redirect_url);
	}
	
	//获取用户绑定卡
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public String getBankPage(HttpServletRequest request,
            HttpServletResponse response, Model model) throws ParseException {
		
		String user_name = (String) request.getSession().getAttribute("user_name");
		
		UserBank userBank = new UserBank();
		if(!user_name.isEmpty()){
			User user = userService.selectForObjectByPhone(user_name);
			if(user != null){
				userBank.setUser_id(user.getId());
			}
		}
		
		
		Page page = PageUtils.createPage(request);
		page = userBankService.page(page, userBank);
		
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		
		return "sys/user/userBankPage";
	}
	
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
	@Autowired(required = true)
	public void setUserBankService(IUserBankService userBankService) {
		this.userBankService = userBankService;
	}
	
	@Autowired(required = true)
	public void setBankService(IBankService bankService) {
		this.bankService = bankService;
	}

}
