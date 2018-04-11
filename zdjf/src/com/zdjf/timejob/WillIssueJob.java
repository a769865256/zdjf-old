package com.zdjf.timejob;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.service.front.IFrontService;
import com.zdjf.service.product.IProductService;

@Component
public class WillIssueJob {
	@Autowired
	private IProductService productService;
	@Autowired
	private IFrontService frontService;

	@Scheduled(cron = "0 0/5 * * * ?")
	public void willIssue() throws InterruptedException{
		Product pro = new Product();
		pro.setStatus(21);
		List<ProductVo> listPro = productService.selectForList(pro);
		if(listPro.size()>0){
			for(int i = 0;i<listPro.size();i++){
				Product product = listPro.get(i);
				if(product.getWill_issue_time().before(new Date())){
					product.setStatus(31);
					productService.updateProductById(product);
				}
			}
			frontService.reloadIndexCache();
		}

	}
	@Scheduled(cron = "0 0/5 * * * ?")
	public void Issue() throws InterruptedException{
		Product pro = new Product();
		pro.setStatus(31);
		List<ProductVo> listPro = productService.selectForList(pro);
		if(listPro.size()>0){
			for(int i = 0;i<listPro.size();i++){
				Product product = listPro.get(i);
				if(product.getWill_show_time().before(new Date())){
					product.setStatus(4);
					product.setOnline(1);
					product.setAct_show_time(new Date());
					product.setIssue_time(new Date());
					productService.updateProductById(product);
				}
			}
			frontService.reloadIndexCache();
		}
	}
}
