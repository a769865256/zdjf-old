package com.zdjf.web.front;

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
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserBank;
import com.zdjf.domain.user.UserBankVo;
import com.zdjf.service.user.IUserBankService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.StringUtil;
import com.zdjf.webservice.express.SmsReturn;
@Controller
@RequestMapping("/")
public class BankBindController {
	private IUserBankService userBankService;
	private IUserService userService;
	//用户绑定银行卡
	@Transactional(rollbackFor = { Exception.class }) 
	@RequestMapping(value="bind",method=RequestMethod.POST)
	public @ResponseBody SmsReturn  bindBankCard(HttpServletRequest request,
			HttpServletResponse response) {

		SmsReturn sr = new SmsReturn();
		String user_name = BrowseUtil.getCookie(request, response);

		if(user_name.isEmpty()){
			sr.setContent("用户不存在");
			sr.setReturnCase(false);
			sr.setStatus(101);
			return sr;
		}

		String card_attribute = request.getParameter("card_attribute");
		String bank_no = request.getParameter("bank_no");
		String bank_alias = request.getParameter("bank_alias");
		String phone = request.getParameter("other_phone");
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
			sr.setContent("存在快捷支付");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Date date = new Date();

		//唯一标识   设定
		String request_no = "zdjfu" + date.getTime() + StringUtil.getRandom();
		String result = SinaUtil.bindBankCard(user.getId(),request_no,bank_alias,
				bank_no,card_attribute,phone,province,city,user.getReg_ip(),user.getReal_name_auth(),real_name,idcard_no);
		System.out.println(result);

		if(result == null || result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
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
			sr.setContent((String) resultMap.get("response_msg"));
			if(resultMap.containsKey("response_msg")&&resultMap.get("resultMap").toString().indexOf("银行")>0){
				user.setReal_name_auth(1);
				userService.updateForObjectByPhone(user);
			}
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}


		String ticket = (String) resultMap.get("ticket");
		user.setPay_passwd(ticket);
		user.setPay_account(request_no);
		if(StrUtils.emptyJudge(real_name)&&StrUtils.emptyJudge(idcard_no)){
			user.setReal_name(real_name);
			user.setIdcard_no(idcard_no);
			user.setReal_name_auth(1);
		}
		user.setStatus(1);
		userService.updateForObjectByPhone(user);


		sr.setContent("发送验证码成功");
		sr.setReturnCase(true);
		sr.setStatus(100);

		return sr;
	}

	//获取短信验证
	@Transactional(rollbackFor = { Exception.class }) 
	@RequestMapping(value="bank/advance",method=RequestMethod.POST)
	public @ResponseBody SmsReturn getBankAdvance(HttpServletRequest request,
			HttpServletResponse response, Model model) throws ParseException {
		SmsReturn sr = new SmsReturn();
		String user_name = BrowseUtil.getCookie(request, response);
		String reg_source = request.getParameter("valid_code");
		String reg_ip = RequestUtils.getIp(request);

		//银行卡内容
//		String card_attribute = request.getParameter("card_attribute");
//		String bank_no = request.getParameter("bank_no");
//		String bank_alias = request.getParameter("bank_alias");
//		String phone = request.getParameter("other_phone");
//		String province = request.getParameter("province");
//		String city = request.getParameter("city");

		//				String md5Sign = Md5Util.md5to32(user_name + reg_source + reg_ip +
		//						Constants.API_KEY);
		//				
		//				if( null ==sign || sign.isEmpty()){
		//					sr.setReturnCase(false);
		//					sr.setStatus(101);
		//					sr.setContent("加密数据为空");
		//					
		//					return sr;
		//				}
		//				
		//				if(!sign.equalsIgnoreCase(md5Sign)){
		//					sr.setContent("无效数据");
		//					sr.setReturnCase(false);
		//					sr.setStatus(101);
		//					
		//					return sr;
		//				}

		User user = userService.selectForObjectByPhone(user_name);
		String ticket = user.getPay_passwd();
		//
		String result = SinaUtil.bindBankCardAdvance(ticket, reg_source, reg_ip);
		System.out.println(result);

		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		Map resultMap = JsonUtil.jsonToMap(result);

		String strCode = (String) resultMap.get("response_code");

		if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
			sr.setContent((String) resultMap.get("response_msg"));
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		String card_id = (String) resultMap.get("card_id");
		user.setUpdate_time(new Date());
		result=SinaUtil.isSetPayPassword(user.getId(), user.getReg_ip());
		System.out.println("isplaypassword :" + result);

		if(result.trim().isEmpty()){
			sr.setContent("新浪接口问题");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}

		resultMap = JsonUtil.jsonToMap(result);
		strCode = (String) resultMap.get("response_code");
		if(strCode == null ){
			sr.setContent("此用户没有通过审核");
			sr.setReturnCase(false);
			sr.setStatus(101);

			return sr;
		}else{
			Map<String, Object> data=(Map<String, Object>) resultMap.get("data");
			strCode=data.get("is_set_paypass").toString();
			if(strCode.equalsIgnoreCase("N")){
				result = SinaUtil.setPayPassword(user.getId(),1,user.getReg_ip(),user_name,reg_source);
				System.out.println("playpassword :" + result);
				String remark = user.getRemark();
				if(remark == null || remark.isEmpty())
							user.setRemark("playpassword:" + result);
						else{

							user.setRemark(remark + ";playpassword:" + result);
						}

				if(result.trim().isEmpty()){
					sr.setContent("新浪接口问题");
					sr.setReturnCase(false);
					sr.setStatus(101);

					return sr;
				}

				resultMap = JsonUtil.jsonToMap(result);
				strCode = (String) resultMap.get("response_code");

				if(strCode == null || !strCode.equalsIgnoreCase("APPLY_SUCCESS")){
					sr.setContent((String) resultMap.get("response_msg"));
					sr.setReturnCase(false);
					sr.setStatus(101);

					return sr;
				}

				Map dataMap =  (Map) resultMap.get("data");
				String redirect_url = (String) dataMap.get("redirect_url");

				//支付密码用md5
				user.setPay_passwd(redirect_url);
				user.setStatus(2);
				sr.setMapContent(redirect_url);
			}else if(strCode.equalsIgnoreCase("Y")){
				user.setStatus(3);
				sr.setMapContent("支付密码已经设置");
			}else{
				sr.setContent("新浪接口问题");
				sr.setReturnCase(false);
				sr.setStatus(101);

				return sr;
			}
		}


		//支付密码
		user.setReal_name_auth(1);
		userService.updateForObjectByPhone(user);
		UserBank userBank = new UserBank();

		userBank.setCard_id(card_id);
		userBank.setUser_id(user.getId());
//		userBank.setBank_alias(bank_alias);
//		userBank.setBank_no(bank_no);
//		userBank.setPhone(phone);
		userBank.setCreate_time(new Date());
		userBank.setStatus(1);
		userBank.setType(1);//快捷卡
		userBank.setTicket(ticket);
//		userBank.setProvince(province);
//		userBank.setCity(city);
//		userBank.setCard_attribute(card_attribute);
		userBank.setRequest_no(user.getPay_account());//
		//保存银行
		userBankService.save(userBank);

		//sr.setContent(redirect_url);

		sr.setReturnCase(true);
		sr.setStatus(100);

		return sr;
	}
	@Autowired(required = true)
	public void setUserBankService(IUserBankService userBankService) {
		this.userBankService = userBankService;
	}
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
