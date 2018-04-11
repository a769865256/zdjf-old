package com.zdjf.web.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.dao.fund.IPayDao;
import com.zdjf.dao.product.IProductBuyDao;
import com.zdjf.domain.common.DataField;
import com.zdjf.domain.common.DataFieldVo;
import com.zdjf.domain.user.User;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.common.IDataFieldService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CommonUtils;
import com.zdjf.util.DateUtil;
import com.zdjf.util.RequestUtils;

@Controller
@RequestMapping("/")
public class ConductController {
	
	private IProductBuyDao productBuyDao;
	private IDataFieldService dataFieldService;
	private IUserService userService;
	private IPayDao payDao;
	
	

	@RequestMapping("conduct")
	public String toConduct(HttpServletRequest request,HttpServletResponse response,Model model){
		String status=RequestUtils.getReqString(request,"status" );
		String user_id=RequestUtils.getReqString(request,"user_name" );
		String tab=RequestUtils.getReqString(request,"tab" );
		Cookie[] cookies=request.getCookies();
		for(int i=0;i<cookies.length;i++){
			Cookie cookie=cookies[i];
			if(cookie.getName().equals("user_name")){
				user_id=cookie.getValue();
			}
			if(cookie.getName().equals("status")){
				status=cookie.getValue();
			}
		}
		if(status==""){
			status="0";
		}
		if(tab==""){
			tab="0";
		}
		User user=userService.selectForObjectByPhone(user_id);
		Map<String, Object> dataMap=new HashMap<String, Object>();
		if(tab.equals("0")){
			Map<String, Object>map=new HashMap<String, Object>();
			map.put("status", status);
			map.put("user_id", user.getId());
			map.put("pagename", "selectConductListByUid");
			map.put("total", "selectConductListByUidCn");
			
			Page page=PageUtils.createPage(request);
			page= productBuyDao.page(page,map);
			DataField dataField=new DataField();
			dataField.setData_field_id("fina_sale_status");
			List<DataFieldVo> fina_sale_status=dataFieldService.selectForList(dataField);
			Map<String,Object> dataFieldMap=new HashMap<String, Object>();
			for(int i=0;i<fina_sale_status.size();i++){
				dataFieldMap.put(fina_sale_status.get(i).getData_field_value(), fina_sale_status.get(i).getData_field_name());
			}
			List<Map<String, Object>> dataList=(List<Map<String, Object>>) page.getDataList();
			
			for(int i=0;i<dataList.size();i++){
				Map<String, Object> datamap=dataList.get(i);
				String sale_status=datamap.get("status").toString();
				datamap.put("status_text", dataFieldMap.get(sale_status));
				dataMap.put(datamap.get("id").toString(), datamap);
			}
			
			model.addAllAttributes(CommonUtils.packageResult(true, page));
			model.addAttribute("creat_days",DateUtil.dateSub(new Date(), user.getCreate_time()));
			model.addAllAttributes(PageUtils.createPagePar(page));
		}else{
			Page orderpage=PageUtils.createPage(request);
			Map<String, Object>nmap=new HashMap<String, Object>();
			nmap.put("user_id", user.getId());
			nmap.put("status", status);
			nmap.put("pagename", "selectUserPayByUid");
			nmap.put("total", "selectUserPayByUidCn");
			orderpage=payDao.page(orderpage, nmap);
			List<Map<String, Object>> orderdataList=(List<Map<String, Object>>) orderpage.getDataList();
			for(int i=0;i<orderdataList.size();i++){
				Map<String, Object> orderdataMap=orderdataList.get(i);
				String id=orderdataMap.get("relation_id").toString();
				Map<String, Object> pb=(Map<String, Object>) dataMap.get(id);
				orderdataMap.put("product_name",pb.get("product_name"));
				orderdataMap.put("trade_no",pb.get("trade_no"));
			}
			model.addAllAttributes(CommonUtils.packageResult(true, orderpage));
			model.addAllAttributes(PageUtils.createPagePar(orderpage));
		}
		return "front/mywealth/conduct";
	}
	
	@Autowired(required = true)
	public void setProductBuyDao(
			@Qualifier("productBuyDao")IProductBuyDao productBuyDao) {
		this.productBuyDao = productBuyDao;
	}
	@Autowired(required = true)
	public void setDataFieldService(IDataFieldService dataFieldService) {
		this.dataFieldService = dataFieldService;
	}
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setPayDao(
			@Qualifier("payDao")IPayDao payDao) {
		this.payDao = payDao;
	}
	
}
