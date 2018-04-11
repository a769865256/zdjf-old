package com.zdjf.web.admin.product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.util.StrUtils;

@Controller
@RequestMapping("/sys/lender")
public class LenderController {
	
	private ILenderService lenderService;
	
	private IUserService userService;
	
	private void loadCommon(Model model){
		
	}
	//添加借款方
	@Transactional(rollbackFor = { Exception.class }) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result addLender(HttpServletRequest request) throws Exception{
		
		Lender lender  = new Lender();

		String amount = request.getParameter("amount");
		String lender_type = request.getParameter("lender_type");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String idcard = request.getParameter("idcard");
		String marital = request.getParameter("marital");
		String address = request.getParameter("address");
		int mType = Integer.parseInt(lender_type);
		if(mType == 2){//出借人是公司时候  
			String comp_alias = request.getParameter("comp_alias");
			String comp_name = request.getParameter("comp_name");
			String comp_code = request.getParameter("comp_code");
			String reg_date = request.getParameter("reg_date");
			String reg_money = request.getParameter("reg_money");
			String comp_address = request.getParameter("comp_address");
			
			lender.setComp_alias(comp_alias);
			lender.setComp_name(comp_name);
			lender.setComp_code(comp_code);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
			Date dateF = sdf.parse(reg_date); 
			
			lender.setReg_date(dateF);
			lender.setReg_money(RoofNumberUtils.StringtoDouble(reg_money));
			lender.setComp_address(comp_address);
		}
		
		//String status = request.getParameter("status");
		//String create_time = request.getParameter("create_time");
		String remark = request.getParameter("remark");
		//String user_type = request.getParameter("user_type");
		String reg_ip = request.getParameter("ip");
		
		
		
		
		User user = new User();
		
		user.setUser_name(phone);
		user.setReal_name(name);
		user.setUser_type(2);
		user.setIdcard_no(idcard);
		user.setReg_ip(reg_ip);
		user.setReal_name_auth(1);
		user.setIdcard_auth_time(new Date());
		
		
		userService.save(user);
		//老接口
//		String result = SinaUtil.createActiveMember(user.getId(), user.getReg_ip());
		//新接口 2018-1-17
		String result = SinaUtil.createActiveMember(user.getId(), user.getReg_ip(),Constants.HOST_ROLE_BORROWER);
		Map resultMap = JsonUtil.jsonToMap(result);
		
		Object strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			Object strMap =  resultMap.get("response_msg");
			
			String remarkO =  (String)resultMap.get("remark");
			remarkO.equalsIgnoreCase("1");
			return new Result(Result.FAIL,strMap.toString());
		}
		
		strCode = null;
		result = SinaUtil.setRealName(user.getId(),user.getReal_name(),idcard,user.getReg_ip());
		
		resultMap = JsonUtil.jsonToMap(result);
		
		strCode =  resultMap.get("response_code");
		
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			
			Object strMap =  resultMap.get("response_msg");
			String remarkO =  (String)resultMap.get("remark");
			remarkO.equalsIgnoreCase("1");
			return new Result(Result.FAIL,strMap.toString());
		}
		
		
		
			
			 
		lender.setAmount(RoofNumberUtils.StringtoDouble(amount));
		lender.setLender_type(Integer.parseInt(lender_type));
		lender.setName(name);
		lender.setPhone(phone);
		lender.setIdcard(idcard);
		lender.setMarital(Integer.parseInt(marital));
		lender.setAddress(address);
		
		
		lender.setStatus(1);
		lender.setCreate_time(new Date());
		lender.setRemark(remark);
		lender.setUser_type(2);
		lender.setUser_id(user.getId());
		
		lenderService.save(lender);
		
		return new Result(Result.SUCCESS,"保存成功!");
		
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)//添加管理用户
	public String getLenderList(HttpServletRequest request, Model model) {
		
		Page page=PageUtils.createPage(request);
		Page page1=PageUtils.createPage("comp",request);
		Lender lender=new Lender();
		lender.setLender_type(1);
		page=lenderService.page(page, lender);
		List<Long> userIdList=new ArrayList<Long>();
		List<Map<String,Object>> lenderList=(List<Map<String,Object>>) page.getDataList();
		for(int m=0;m<lenderList.size();m++){
			Map<String,Object> lenderVo=lenderList.get(m);
			lenderVo.put("age", StrUtils.idcardToAge(lenderVo.get("idcard").toString()));
			lenderVo.put("sex", StrUtils.idcardToSexChn(lenderVo.get("idcard").toString()));
			lenderVo.put("account", lenderVo.get("phone").toString()+"（"+lenderVo.get("name").toString()+"）");
		}
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("lender", page);
		lender.setLender_type(2);
		page1=lenderService.page(page1, lender);
		map.put("comp", page1);
		model.addAttribute("detail", map);
		model.addAttribute("lender",PageUtils.createPagePar(page));
		model.addAttribute("comp",PageUtils.createPagePar("comp",page1));
		this.loadCommon(model);
		
		return "sysNew/lender/list";
	}
	
	@Autowired(required = true)
	public void setLoanerService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String toDetail(HttpServletRequest request, Model model){
		String id = request.getParameter("lenderId");
		if(null!=id&&""!=id){
		Map<String, Object> lender=(Map<String, Object>) lenderService.selectForMapById(Long.parseLong(id));
		lender.put("account", lender.get("phone").toString()+"("+lender.get("name").toString()+")");
		model.addAttribute("detail", lender);
		}else{
			model.addAttribute("detail", null);
		}
		return "sysNew/lender/detail";
		
	}

}
