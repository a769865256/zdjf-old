package com.zdjf.timejob;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyVo;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.User;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.DateUtil;

@Component
public class PaymentJob {
	
	private IProductService productService;
	
	private IUserService userService;
	
	private IProductBuyService productBuyService;
	
	void Batch(Product product){
		ProductBuy buy = new ProductBuy();
		buy.setProduct_id(product.getId());
		String goods_id = product.getId().toString();
		List<ProductBuyVo> listBuy = productBuyService.selectForList(buy);
		String trade_list = "";
		String summary="批量还钱";
		for(int j = 0;j<listBuy.size();j++){//批量还款
			
			ProductBuy productBuy = listBuy.get(j);
			
			User us = new User();
			us.setId(productBuy.getUser_id());
			User user = userService.selectForObject(us);
			if(j != 0){
				trade_list += "$";
			}
			
			String trade_no = UniqueUtil.getTradeNo();
			Long id = user.getId();
			String uid = "UID";
			String bank = "BANK";
			
			Double repay_profit = productBuy.getWill_income();//利息
			Double repay_principal = productBuy.getAmount();//本金
		
			Double amount1 = repay_profit + repay_principal;
			int repay_period = 1;
			
			String list_extend_param = "repay_principal^" + repay_principal.toString() +
					"|repay_profit^" + repay_profit.toString()+ "|repay_period^" + repay_period;
			
			trade_list += trade_no + "~" + id.toString() + "~UID~BANK~" 
						 + amount1.toString() + "~~" + summary + "~"+ list_extend_param+ "~~" + goods_id+"~";
			
		}
		
		System.out.println(trade_list);
		//调用支付接口
		String ip = "117.149.16.71";//公司IP
		String trade_no = UniqueUtil.getTradeNo();
		String result = SinaUtil.createBatchHostPayTrade(trade_no, trade_list, ip);
		System.out.println("trade_no:" + trade_no + ":"+ result);
	}
	
	void productEnd(){
		Date date = new Date();
		String start = DateUtil.formatDateTimeS(DateUtil.dateBegin(date));
	    String end = DateUtil.formatDateTimeS(DateUtil.dateEnd(date));
	    
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductByWillEndDate");
		map.put("start_date",Timestamp.valueOf(start));
		map.put("end_date",Timestamp.valueOf(end));
		map.put("status",5);
		
		List<ProductVo> listPro = productService.selectForList(map);
		
		for(int i = 0;i<listPro.size();i++){
			Product product = listPro.get(i);
			Batch(product);
		}
		
	}
	
	//@Scheduled(cron = "0 0/10 17 * * ?")
	public void allUserPayment(){

		Date date = new Date();
		System.out.println(String.format("PaymentJob time-%s",date));
	}
	
	
	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@Autowired(required = true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	
	@Autowired(required = true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}

}
