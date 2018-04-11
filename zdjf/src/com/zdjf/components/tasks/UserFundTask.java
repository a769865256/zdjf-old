package com.zdjf.components.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.util.JsonUtil;

public class UserFundTask {
	
	//定时器没有没有加入
	//@Scheduled(cron = "0 0 * * * ?")
	public void userFunds() {
		Date date = new Date();
		System.out.println(String.format("time-%s",date));
		
	}
	
	@Async
    public static void testAsyncMethod(Long user_id ,Double amount,String debt_code,IProductService productService,
			ILenderService lenderService){
        try {
            //让程序暂停100秒，相当于执行一个很耗时的任务
        	String out_trade_code = "2001";
    		String trade_no = UniqueUtil.getTradeNo();
    		String summary = "放款" ;
    		
    		
    		Lender len = new Lender();
    		len.setId(user_id);
    		List<LenderVo> listVo = lenderService.selectForList(len);
    		Lender lender = listVo.get(0);
    	
    		Thread.sleep(1000*60*3);
    		
    		String result = SinaUtil.createSingleHostPayTrade(lender.getUser_id(), trade_no, out_trade_code, 
    				summary, amount, debt_code, "117.149.16.71",0.0,0,0.0,"");
    		
    		//返回值
    		Map resultMap = JsonUtil.jsonToMap(result);
    		
    		Object strCode =  resultMap.get("response_code");
    		
    		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
    			
    			System.out.println(resultMap.get("response_msg"));
    			
    		}
    		
    		Product pro = new Product();
    		pro.setDebt_code(debt_code);
    		List<ProductVo> productList = productService.selectForList(pro);
    		if(productList.size() != 1){
    			System.out.println("存在多个标");
    		}
    		Product product = productList.get(0);
    		
    		if(product.getStatus() == 7){
    			product.setStatus(5);
    		}
    		productService.updateProductById(product);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
