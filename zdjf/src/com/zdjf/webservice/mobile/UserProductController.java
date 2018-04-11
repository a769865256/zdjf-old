package com.zdjf.webservice.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdjf.dao.product.IProductBuyDao;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.frame.dataaccess_api.PageUtils;
import com.zdjf.service.product.IProductService;
import com.zdjf.util.CommonUtils;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RequestUtils;

@Controller
@RequestMapping("/m/product")
public class UserProductController {
	
	private IProductService productService;
	private IProductBuyDao productBuyDao;
	
	@RequestMapping("/list")
	public @ResponseBody String toProductList(HttpServletRequest request,HttpServletResponse response,Model model){
		String status=RequestUtils.getReqString(request,"status");
		String limit=RequestUtils.getReqString(request,"limit");
		String ratios=RequestUtils.getReqString(request,"ratios");
		String product_type=RequestUtils.getReqString(request,"product_type");
		if(""==limit|null==limit){
			limit="0";
		}
		if(""==ratios|null==ratios){
			ratios="0";
		}
		Page page=PageUtils.createPage(request);
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("pagename", "selectProductPageList");
		map.put("total", "selectProductPageListCount");
		map.put("datelimit", limit);
		map.put("status", status);
		map.put("income", ratios);
		map.put("product_type", product_type);
		page=productService.page(page, map);

		List<ProductVo> productList=(List<ProductVo>) page.getDataList();
		List<Map<String,Object>> productBuyList=(List<Map<String, Object>>) productBuyDao.selectForList("selectAmountByProductId");
		Map<String,Object> productBuyMap=new HashMap<String, Object>();
		for(int i=0;i<productBuyList.size();i++){
			Map<String,Object> nmap=productBuyList.get(i);
			productBuyMap.put(nmap.get("product_id").toString(), nmap.get("can_buy_money"));
		}

		for(int i=0;i<productList.size();i++){
			ProductVo product=(ProductVo) productList.get(i);
			String product_id=product.getId().toString();
			product.setSpeed((product.getMoney()-product.getSale_money())/product.getMoney()*100);
			if(productBuyMap.containsKey(product_id)){
				product.setCan_buy_money(product.getBalance()-Double.parseDouble(productBuyMap.get(product_id).toString()));
			}else{
				product.setCan_buy_money(product.getBalance());
			}
		}
		return JsonUtil.objectTojson(CommonUtils.packageResult(true, page));
	}
	@Autowired(required=true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	@Autowired
	public void setProductBuyDao(
			@Qualifier("productBuyDao") IProductBuyDao  productBuyDao) {
		this.productBuyDao = productBuyDao;
	}
}
