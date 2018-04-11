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
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.file.File;
import com.zdjf.domain.user.Loaner;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.frame.spring.Result;
import com.zdjf.service.file.IFileService;
import com.zdjf.service.user.ILoanerService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.Constants;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RequestUtils;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.util.StrUtils;
import com.zdjf.util.ProductConstants.FileType;

@Controller
@RequestMapping("/sys/loaner")
public class LoanerController {


	private ILoanerService loanerService;

	private IUserService userService;
	@Autowired
	private IFileService fileService;

	private void loadCommon(Model model){

	}

	//添加
	@Transactional(rollbackFor = { Exception.class }) 
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public  @ResponseBody Result addLoaner(HttpServletRequest request) throws Exception{

		Loaner loaner = new Loaner();

		String loaner_type = request.getParameter("loanerType");
		int mType = Integer.parseInt(loaner_type);
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String idcard = request.getParameter("idcard");
		String marital = request.getParameter("marital");
		String address = request.getParameter("address");
		String reg_date = request.getParameter("regDate");
		if(mType == 2){
			String comp_alias = request.getParameter("compAlias");
			String comp_name = request.getParameter("compName");
			String comp_code = request.getParameter("compCode");
			String comp_address = request.getParameter("compAddress");
			String reg_money = request.getParameter("regMoney");


			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");  
			Date dateF = sdf.parse(reg_date); 

			loaner.setReg_date(dateF);
			loaner.setComp_alias(comp_alias);
			loaner.setComp_name(comp_name);
			loaner.setComp_code(comp_code);
			loaner.setReg_date(new Date());
			loaner.setComp_address(comp_address);
			loaner.setReg_money(RoofNumberUtils.StringtoDouble(reg_money));
		}

		String status = request.getParameter("status");
		String remark = request.getParameter("remark");

		String reg_ip = RequestUtils.getIp(request);


		User user = new User();

		user.setUser_name(phone);
		user.setReal_name(name);
		user.setUser_type(3);
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


			//			String remarkO =  (String)resultMap.get("remark");
			//			remarkO.equalsIgnoreCase("1");
			throw new RuntimeException();
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

		loaner.setLoaner_type(Integer.parseInt(loaner_type));
		loaner.setName(name);
		loaner.setPhone(phone);
		loaner.setIdcard(idcard);
		loaner.setMarital(Integer.parseInt(marital));
		loaner.setAddress(address);

		loaner.setStatus(Integer.parseInt(status));
		loaner.setCreate_time(new Date());
		loaner.setRemark(remark);

		Long loaner_id=StrUtils.convToLong(loanerService.save(loaner).toString());
		
		String loanerUrl=RequestUtils.getReqString(request, "loanerUrls");
		String[] loanerUrls=loanerUrl.split("<p><img");
		if(StrUtils.emptyStrArray(loanerUrls)&&!"".equals(loanerUrl)) {
			for (int i=1;i< loanerUrls.length;i++) {
				String url=loanerUrls[i].substring(loanerUrls[i].indexOf("src=\"")+5,(loanerUrls[i].indexOf("\" ")>(loanerUrls[i].indexOf("src=\"")+5))?loanerUrls[i].indexOf("\" "):loanerUrls[i].indexOf("\"/"));
				fileService.save(getLoanerFile(loaner_id, url, i+1,"0","0", FileType.INFO));
			}
		}
		

		return new Result(Result.SUCCESS,"保存成功!");
	}

	private File getLoanerFile(Long loaner_id, String url, int i, String w, String h, int info) {
		File file=new File();
		file.setFrom_id(loaner_id);
		file.setFrom_table("zd_loaner");
		file.setFile_url(url);//产品地址
		file.setFile_name(StrUtils.getFileNameByUrl(url));//产品名称
		file.setFile_num(i);
		file.setFile_type(FileType.OTHER);
		file.setCreate_time(new Date());
		return file;
	}

	@RequestMapping(value="/detail",method=RequestMethod.GET)//添加管理用户
	public String getLoaner(HttpServletRequest request, Model model) {
		String id = RequestUtils.getReqString(request, "loanerId");
		if(null!=id&&""!=id){
			Map<String,Object> newLoaner = loanerService.selectForMapById(Long.parseLong(id));
			model.addAttribute("detail", newLoaner);
		}else{
			model.addAttribute("detail", null);
		}
		this.loadCommon(model);

		return "sysNew/loaner/detail";
	}
	@RequestMapping(value="/list",method=RequestMethod.GET)//添加管理用户
	public String getLoanerList(HttpServletRequest request, Model model) {

		Page page=PageUtils.createPage(request);
		Page page1=PageUtils.createPage("comp",request);
		Loaner loaner=new Loaner();
		loaner.setLoaner_type(1);
		page=loanerService.page(page, loaner);
		Map<String, Object>map=new HashMap<String, Object>();
		List<Long> userIdList=new ArrayList<Long>();
		List<Map<String,Object>> loanerList=(List<Map<String,Object>>) page.getDataList();
		for(int m=0;m<loanerList.size();m++){
			Map<String,Object> loanerVo=loanerList.get(m);
			loanerVo.put("age", StrUtils.idcardToAge(loanerVo.get("idcard").toString()));
			loanerVo.put("sex", StrUtils.idcardToSexChn(loanerVo.get("idcard").toString()));
			loanerVo.put("account", loanerVo.get("phone").toString()+"（"+loanerVo.get("name").toString()+"）");
		}

		map.put("loaner", page);
		loaner.setLoaner_type(2);
		page1=loanerService.page(page1, loaner);
		map.put("comp", page1);
		model.addAttribute("detail", map);
		model.addAttribute("loaner",PageUtils.createPagePar(page));
		model.addAttribute("comp",PageUtils.createPagePar("comp",page1));
		this.loadCommon(model);

		return "sysNew/loaner/list";
	}

	@Autowired(required = true)
	public void setLoanerService(ILoanerService loanerService) {
		this.loanerService = loanerService;
	}

	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
