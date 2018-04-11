package com.zdjf.timejob;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjf.components.mobile.SinaResultUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyVo;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.reconciliation.Reconciliation;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.domain.user.User;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.reconciliation.IReconciliationService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.DateUtil;
import com.zdjf.util.JsonUtil;

@Component
public class LoanJob {

	private IProductService productService;

	private IProductBuyService productBuyService;

	private ILenderService lenderService;

	private IUserFundStatService userFundStatService;

	private IReconciliationService   reconciliationService;

	private IFundsService fundsService;

	private IUserService userService;
	Log log = LogFactory.getLog(this.getClass());
	//放款

//	@Scheduled(cron = "0 0/2 * * * ?")
	public void loanFund() throws InterruptedException{

		Product pro = new Product();
		pro.setStatus(7);
		
		
		List<ProductVo> listPro = productService.selectForList(pro);
		for(int i = 0;i<listPro.size();i++){
			Product product = listPro.get(i);


			loan(product.getLender_id(),product.getSale_money(),
					product.getDebt_code(),log);
		}



		Date date = new Date();
		log.info(String.format("放款time-%s",date));
	}


	public void loan(Long user_id ,Double amount,String debt_code,Log log) throws InterruptedException{


		Product pro = new Product();
		pro.setDebt_code(debt_code);

		List<ProductVo> productList = productService.selectForList(pro);

		if(productList.size() != 1){

			return ;
		}

		ProductVo product = productList.get(0);

		if(product.getBalance().compareTo(0.0) != 0){
			return ;
		}

		Lender len = new Lender();
		len.setId(product.getLender_id());
		List<LenderVo> listVo = lenderService.selectForList(len);
		Lender lender = listVo.get(0);
		//lenderService.getLender_id();

		ProductBuy productBuy = new ProductBuy();
		productBuy.setProduct_id(product.getId());
		productBuy.setStatus(1);;

		Double wait = 0.0;
		Double close = 0.0;
		Double fish = 0.0;
		Double fail = 0.0;
		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);
		for(int i = 0;i<listBuy.size();i++){
			ProductBuy buy = listBuy.get(i);
			Long buy_user_id = buy.getUser_id();
			String buy_trade_no = buy.getTrade_no();
			String result = SinaUtil.queryHostTrade(buy_user_id, buy_trade_no, "", "");

			//返回值
			Map resultMap = JsonUtil.jsonToMap(result);

			Object strCode =  resultMap.get("response_code");

			if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){

				continue ;
			}

			Map dataMap =  (Map) resultMap.get("data");

			String trade_list = (String) dataMap.get("trade_list");

			if(trade_list.indexOf("WAIT_PAY")>0){
				wait += buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_CLOSED")>0){
				close+=buy.getAmount();


				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_FINISHED")>0){
				//成功
				fish +=buy.getAmount();

				System.out.println("trade_no:  " + buy_trade_no + " succ :");
			}else if(trade_list.indexOf("TRADE_FAILED")>0){
				fail +=buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " fail :");
			}
		}



		String out_trade_code = "2001";
		String trade_no = UniqueUtil.getTradeNo();

		Long uid = lender.getUser_id();
		if(amount.compareTo(fish)  == 0){
			String result = SinaUtil.createSingleHostPayTrade(uid, trade_no, out_trade_code,
					"放款", amount, debt_code, "202.101.172.35",0.0,0,0.0,"");

			log.info("trade_no:" + trade_no + ":"+ result);


			//返回值
			Map resultMap = JsonUtil.jsonToMap(result);

			Object strCode =  resultMap.get("response_code");

			if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
				hostPayTradeRefund(product.getId().toString());
				return ;
			}

			if(product.getStatus() == 7){
				product.setStatus(5);
				//product.setFull_time(full_time);
				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(uid);
				UserFundStat voF = userFundStatService.selectForObjectById(userFund);
				voF.setBalance(voF.getBalance() + amount);
				userFundStatService.updateUserFundStatById(voF);

				//资金流动明细
				Reconciliation reconciliation = new  Reconciliation();
				reconciliation.setOrder_date(DateUtil.formatDate(new Date()));
				reconciliation.setCreate_time(new Date());
				reconciliation.setTrans_amt(amount.toString());
				reconciliation.setInvest_cust_id(out_trade_code);
				reconciliation.setMer_cust_id(trade_no);
				reconciliation.setBorr_cust_id(product.getDebt_code());//
				reconciliation.setHf_amt(voF.getBalance());//余额
				reconciliation.setTrans_type("LOANS");
				reconciliation.setPro_status(1+"");
				reconciliation.setPnr_seq_id(voF.getUser_id().toString());
				reconciliationService.save(reconciliation);//放款流水
			}
			productService.updateProductById(product);
		}else if(amount.compareTo(fish) < 0){//小于募集款
			product.setSale_money(fish);
			product.setBalance(product.getMoney()-fish);
			product.setStatus(4);
			productService.updateProductById(product);
		}


	}

	@Autowired(required = true)
	public void setLenderService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}

	//把多余的钱
	public void hostPayTradeRefund(String product_id){
		ProductBuy productBuy = new ProductBuy();
		productBuy.setProduct_id(Long.parseLong(product_id));
		productBuy.setStatus(9);


		Double wait = 0.0;
		Double close = 0.0;
		Double fish = 0.0;
		Double fail = 0.0;

		List<ProductBuyVo> listBuy = productBuyService.selectForList(productBuy);

		for(int i = 0;i<listBuy.size();i++){
			ProductBuy buy = listBuy.get(i);
			Long buy_user_id = buy.getUser_id();
			String buy_trade_no = buy.getTrade_no();
			String result = SinaUtil.queryHostTrade(buy_user_id, buy_trade_no, "", "");

			//返回值
			Map resultMap = JsonUtil.jsonToMap(result);

			Object strCode =  resultMap.get("response_code");

			if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){

				continue ;
			}

			Map dataMap =  (Map) resultMap.get("data");

			String trade_list = (String) dataMap.get("trade_list");

			if(trade_list.indexOf("WAIT_PAY")>0){
				wait += buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_CLOSED")>0){
				close+=buy.getAmount();

				System.out.println("trade_no:  " + buy_trade_no + " close :");
			}else if(trade_list.indexOf("TRADE_FINISHED")>0){
				//成功
				fish +=buy.getAmount();

				Funds fund = new Funds();
				fund.setRelation_id(Long.parseLong(product_id));
				fund.setTrade_no(buy_trade_no);
				List<FundsVo> listFunds = fundsService.selectForList(fund);

				if(listFunds.size() == 1){
					FundsVo fVo = listFunds.get(0);

					fVo.setStatus(5);
					fundsService.updateFundsById(fVo);
				}



				String trade_no = UniqueUtil.getTradeNo();
				//资金流动明细
				Reconciliation reconciliation = new  Reconciliation();
				reconciliation.setOrder_date(DateUtil.formatDate(new Date()));
				reconciliation.setCreate_time(new Date());
				reconciliation.setTrans_amt(buy.getAmount().toString());
				reconciliation.setInvest_cust_id(buy_trade_no);
				reconciliation.setMer_cust_id(trade_no);
				reconciliation.setHf_amt(buy.getAct_pay_money());
				reconciliation.setTrans_type("REFUND");
				reconciliationService.save(reconciliation);//放款流水

				Long uid = buy.getUser_id();
				String result1 = SinaUtil.createHostRefund(uid, trade_no, buy_trade_no, "投资失败", buy.getAmount(),"117.149.16.71");
				System.out.println(result1 + "trade_no:  " + buy_trade_no + " finished :" + buy.getAmount() +  "uid:"  + uid);

				//更新退款信息
				User us = new User();
				us.setId(uid);
				User user = userService.load(us);

				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(uid);
				UserFundStat voF = userFundStatService.selectForObjectById(userFund);
				voF.setBalance(SinaResultUtil.queryBalance(user));
				userFundStatService.updateUserFundStatById(voF);

			}else if(trade_list.indexOf("TRADE_FAILED")>0){
				fail +=buy.getAmount();
				System.out.println("trade_no:  " + buy_trade_no + " fail :");
			}
		}
		return ;
	}

	@Autowired(required = true)
	public void setProductBuyService(IProductBuyService productBuyService) {
		this.productBuyService = productBuyService;
	}

	@Autowired(required = true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}

	@Autowired(required = true)
	public void setUserFundStatService(IUserFundStatService userFundStatService) {
		this.userFundStatService = userFundStatService;
	}

	@Autowired(required = true)
	public void setReconciliationService(IReconciliationService reconciliationService) {
		this.reconciliationService = reconciliationService;
	}

	@Autowired(required = true)
	public void setFundsService(IFundsService fundsService) {
		this.fundsService = fundsService;
	}

	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
