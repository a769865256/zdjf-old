package com.zdjf.web.transfer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.User;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.DateUtil;

@Controller
@RequestMapping("/data")
public class DataTransfer {

	Log log = LogFactory.getLog(this.getClass());
	
	private IUserService userService;
	
	private IProductService productService;
	
	private ILenderService lenderService;
	
	private IProductBuyService productBuyService;
	
	@RequestMapping("/activate")
	public void activate(HttpServletRequest request) throws IOException, InterruptedException {
		
		List userList = userService.getUserList();
		String type = request.getParameter("type");
		if(userList!=null && userList.size()>0){
			for(int i=0;i<userList.size();i++){
				User user = (User)userList.get(i);
//				String createActiveMember = SinaUtil.createActiveMember(user.getId(), "117.149.16.71");
				String createActiveMember = SinaUtil.createActiveMember(user.getId(), "117.149.16.71");
				
				Thread.sleep(100);
//				if(createActiveMember.indexOf("APPLY_SUCCESS")!=-1){
//					String setRealName = SinaUtil.setRealName(user.getId(), user.getReal_name(), user.getIdcard_no(), "117.149.16.71");
//					Thread.sleep(100);
//					if(setRealName.indexOf("APPLY_SUCCESS")!=-1){
//						user.setReal_name_auth(1);
//						userService.update(user);
//					}else{
//						log.info(user.getId()+"==setRealNameError："+setRealName);
//					}
//				}else{					
//					log.info(user.getId()+"==createActiveMemberError："+createActiveMember);
//				}
				if(type!=null && !"".equals(type)){
					if(i==Integer.valueOf(type))
						break;
				}
			}
		}
		
	}
	
	/**
	 * 标迁移
	 * @param request
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@RequestMapping("/create_bid_info")
	public void bid(HttpServletRequest request) throws IOException, InterruptedException {
		System.out.println("============标的迁移=========");
		String productId = request.getParameter("productId");
		Product product = new Product();
		product.setStatus(5);
		if(productId!=null && !"".equals(productId)){
			product.setId(Long.valueOf(productId));
		}
		List productList = productService.selectForList(product);
		List lenderList= lenderService.selectForList(null);
		Map lenderHashMap = new HashMap(); //出借人map
		if(lenderList!=null && lenderList.size()>0){
			for(int i=0;i<lenderList.size();i++){
				Lender lender = (Lender)lenderList.get(i);
				lenderHashMap.put(lender.getId(), lender);
			}
		}
		
		if(productList!=null && productList.size()>0){
			for(int i=0;i<productList.size();i++){
				Product p = (Product)productList.get(i);
				Lender lender = (Lender)lenderHashMap.get(p.getLender_id());
				int days = DateUtil.dateSub(p.getEnd_date(), p.getStart_date());
				String ItemEntry = SinaUtil.ItemEntry(p.getDebt_code(), p.getProduct_code(), p.getDebt_money(), p.getIncome(),
						days+"","SCHEDULED_INTEREST_PAYMENTS_DUE", "", "", "", "",
						null, null, null, p.getRemark(), "", DateUtil.formatDateTime_Str(p.getStart_date()), DateUtil.formatDateTime_Str(p.getEnd_date()),
						"银行担保", "migration_flag^Y", lender.getUser_id()+"~"+"UID"+"~"+p.getDebt_money()+"~"+"买车"+"~"+lender.getPhone());
				
				Thread.sleep(100);
				if(ItemEntry.indexOf("APPLY_SUCCESS")!=-1){
					System.out.println("成功product_id==="+p.getId());
				}else{
					System.out.println("user_id=========="+lender.getUser_id());
					System.out.println("product_id==="+p.getId());
					log.info(p.getId()+"==bidError："+p.getProduct_name());
				}
			}
		}
	}
	
	/**
	 * 债权迁移
	 * @param request
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@RequestMapping("/debt")
	public void debt(HttpServletRequest request) throws IOException, InterruptedException {
		String productId = request.getParameter("productId");
		String buyId = request.getParameter("buyId");
		Product product = new Product();
		product.setStatus(5);
		if(productId!=null && !"".equals(productId)){
			product.setId(Long.valueOf(productId));
		}
		List productList = productService.selectForList(product); //获取标
		List lenderList= lenderService.selectForList(null);
		Map proHashMap = new HashMap(); //产品map
		Map lenderHashMap = new HashMap(); //出借人map
		ProductBuy productBuy = new ProductBuy();
		productBuy.setStatus(1);
		productBuy.setIs_debt(0);
		if(buyId!=null && !"".equals(buyId)){
			productBuy.setId(Long.valueOf(buyId));;;
		}
		List buyList = productBuyService.selectForList(productBuy);
		if(lenderList!=null && lenderList.size()>0){
			for(int i=0;i<lenderList.size();i++){
				Lender lender = (Lender)lenderList.get(i);
				lenderHashMap.put(lender.getId(), lender);
			}
		}
		if(productList!=null && productList.size()>0){
			for(int i=0;i<productList.size();i++){
				Product p = (Product)productList.get(i);
				proHashMap.put(p.getId(), p);
			}
		}
		
		if(buyList!=null && buyList.size()>0){
			for(int i=0;i<buyList.size();i++){
				ProductBuy b = (ProductBuy)buyList.get(i);
				System.out.println("b.id=="+b.getId());
				if(proHashMap.get(b.getProduct_id())!=null){					
					Product p = (Product)proHashMap.get(b.getProduct_id());
					Lender lender = (Lender)lenderHashMap.get(p.getLender_id());
					String result = SinaUtil.createDebtMigration(b.getTrade_no(), b.getUser_id().toString(), lender.getUser_id().toString(),
							b.getAmount(), p.getDebt_code(), "", "");
//				System.out.println(b.getTrade_no()+3);
					if(result.indexOf("APPLY_SUCCESS")!=-1){
						b.setIs_debt(1);
						productBuyService.updateProductBuy(b);
					}else{
						System.out.println("b.getUser_id()=="+b.getUser_id());
						System.out.println("product=========="+p.getDebt_code()+"===id="+p.getId());
						System.out.println("user_id=========="+lender.getUser_id());
						System.out.println("错误第=========="+i);
						log.info(p.getId()+"==debtError："+p.getProduct_name());
					}
				}
//				break;
			}
		}
		
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
	public void setLenderService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}
	
	@Autowired(required = true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}
	
}
