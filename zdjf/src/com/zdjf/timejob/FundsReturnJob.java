package com.zdjf.timejob;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjf.components.mobile.ServiceChargeUtil;
import com.zdjf.components.mobile.SinaResultUtil;
import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.domain.dto.Cache;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductBuyVo;
import com.zdjf.domain.product.ProductDynamic;
import com.zdjf.domain.product.ProductDynamicVo;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.reconciliation.Reconciliation;
import com.zdjf.domain.sms.Sms;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.domain.user.User;
import com.zdjf.service.front.IFrontService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.product.IProductDynamicService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.reconciliation.IReconciliationService;
import com.zdjf.service.sms.ISmsService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.CacheManager;
import com.zdjf.util.DateUtil;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.StrUtils;

@Component
public class FundsReturnJob {

	private IProductBuyIncomeService productBuyIncomeService;

	private IProductService productService;

	private IFundsService fundsService;

	private ILenderService lenderService;

	private IProductDynamicService productDynamicService;

	private IUserFundStatService userFundStatService;

	private IReconciliationService   reconciliationService;

	private IUserService userService;

	private ISmsService smservice;

	private IProductBuyService productBuyService;
	@Autowired
	private IFrontService frontService;


	private Map<String,String> tradeMap = new HashMap<String,String>();



	@Scheduled(cron = "0 0/3 16-21 * * ?")
	public void fundsReturn() throws InterruptedException, UnknownHostException{

		Cache c = CacheManager.getCacheInfo("fundsTimeJob");
		if(null==c || c.getTimeOut()<new Date().getTime()){
			frontService.reloadFundsCache(false,"");
			c = CacheManager.getCacheInfo("fundsTimeJob");
		}
		Cache ca = (Cache)c.getValue();
		Map<String,Object> map = (Map<String,Object>)ca.getValue();


		Log log = LogFactory.getLog(this.getClass());

		Date date = new Date();
		//trade(2050L,log);String.format("放款time-%s",date)
		String strDate = DateUtil.formatDate(date);

		ProductDynamic pdc = new ProductDynamic();
		pdc.setPay_day(strDate);
		List<ProductDynamicVo> listProD = productDynamicService.selectForList(pdc);
		//今天的金额
		String out_trade_code = "1002";
		String cashdesk_addr_category = "MOBILE";
		String pay_method = "";

//		if(!map.containsKey("isFundsOpen")||(map.containsKey("isFundsOpen")&&!map.get("isFundsOpen").toString().equals("true")))
//			return ;//加一个开关
		Double cny = 0.0;//总计金额
		//比对出借人账户金额
		for(int i = 0;i<listProD.size();i++){
			ProductDynamicVo vo = listProD.get(i);

			if(vo.getStatus() == 1){
				Long in = vo.getProduct_id();
				
				if(in != 2548l){
					continue ;//继续项目
				}
				Product pro = new Product();
				pro.setId(in);
				List<ProductVo> productList = productService.selectForList(pro);

				if(productList.size() != 1){
					ProductVo product = productList.get(0);
					product.setRemark("项目id不是唯一的"+in);
					continue ;//继续项目
				}

				ProductVo product = productList.get(0);
				//针对不同的 借款人  进行筛选

//				if(map.containsKey("lender_id")){
//					Long lender_id=StrUtils.convToLong(map.get("lender_id").toString());
//					if(lender_id==0){
//						//一次性回款  不分出借人
//
//						log.info(String.format("回款时间time-%s",date) + "====="  +  "onetime");
//					}else if(lender_id == product.getLender_id()){
//						//不同出借人回款
//
//						log.info(String.format("回款时间time-%s",date) + "########"  +  "many times id " + lender_id );
//					}else{
//						continue ;//继续项目
//					}
//				}else{
//					continue;
//				}


				
				
				cny = SinaResultUtil.doubleToTwo(vo.getAmount());
				String amount=cny.toString();
				String type = "2";//免密功能
				if(type.equalsIgnoreCase("1"))
					pay_method = "online_bank^" + amount + "^BANKPAY,DEBIT,C";
				else if(type.equalsIgnoreCase("2"))
					pay_method = "balance^" + amount + "^BANK";
				Lender len = new Lender();
				len.setId(product.getLender_id());
				List<LenderVo> listVo = lenderService.selectForList(len);
				Lender lender = listVo.get(0);

				User us = new User();
				us.setId(lender.getUser_id());

				User  user = userService.load(us);

				Double an = SinaResultUtil.queryBalance(user);
				Double com = an-cny;

				Thread.sleep(100);//加上锁控制一下  lig 2018-1-4
				String tempNum = tradeMap.get(product.getDebt_code());
				if(tempNum != null)
					continue ;
				if(com.compareTo(0.0)<0){//对比出借人的钱
					log.info("FundsReturnJob：" + user.getId() + "金额不足 pro_id " + in +" 相差 " + com);
					continue ;
				}




				String trade_no = UniqueUtil.getTradeNo();
				String gift_money="0.0";
				//
				String summary = "还钱" + product.getDebt_code();

				tradeMap.put(product.getDebt_code(), product.getDebt_code());
				Thread.sleep(100);
				String result = SinaUtil.createHostCollectTrade(lender.getUser_id(),trade_no, out_trade_code, summary, cny,
						product.getDebt_code(), pay_method, gift_money, cashdesk_addr_category, "117.149.16.71","","","","");
				log.info("trade_no:" + trade_no + ":"+ result);


				Map resultMap = JsonUtil.jsonToMap(result);

				Object strCode =  resultMap.get("response_code");

				if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
					log.info("FundsReturnJob：" + trade_no + "失败了" + result);
					vo.setTrade_no(trade_no);
					vo.setNum(55);
					productDynamicService.updateProductDynamicById(vo);
					continue ;
				}






				Date end_date = product.getEnd_date();
				String endDate = DateUtil.formatDate(end_date);
				if(strDate.contentEquals(endDate)){
					product.setStatus(6);
					productService.updateProductById(product);
				}


				//资金统计
				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(lender.getUser_id());
				UserFundStat voF = userFundStatService.selectForObjectById(userFund);
				voF.setBalance(SinaResultUtil.queryBalance(user));
				userFundStatService.updateUserFundStatById(voF);
				//按照项目回款
				trade(product.getId(),log);
			}
		}

		listProD = productDynamicService.selectForList(pdc);
		for(int i = 0;i<listProD.size();i++){
			ProductDynamicVo vo = listProD.get(i);
			if(vo.getStatus() == 1){
				return ;
			}
		}

		if(listProD.size()>0){
			frontService.reloadFundsCache(false,"");
			hostBatch(log);
		}
			
	}

	void trade(Long product_id,Log log) throws UnknownHostException{

		String strDate = DateUtil.formatDate(new Date());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductBuyIncomeEnd");
		map.put("end_dates",strDate);
		map.put("product_id",product_id.toString());

		List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);

		Double cny = 0.0;
		String trade_list = "";
		String summary="批量还钱";
		boolean resultProduct = false;
		for(int j = 0;j<listBuyI.size();j++){

			if(j != 0){
				trade_list += "$";
			}
			ProductBuyIncome vo = listBuyI.get(j);

			if(vo.getStatus() == -1){

				String trade_no = UniqueUtil.getTradeNo();
				Long id = vo.getUser_id();
				String uid = "UID";
				String bank = "BANK";
				Double repay_profit = 0.0;//利息


				Double repay_principal = 0.0;//本金

				Product pro = new Product();
				pro.setId(vo.getProduct_id());
				List<ProductVo> productList = productService.selectForList(pro);

				if(productList.size() != 1){
					continue ;
				}

				ProductVo product = productList.get(0);
				//资金流动明细
				Reconciliation reconciliation = new  Reconciliation();
				if(vo.getIs_return_amount()  == 1){
					if(vo.getAmount() != null){
						repay_principal = vo.getAmount();
						//am +=buyIncomeV.getAmount();
						reconciliation.setHf_amt(vo.getAmount());
						//放款
						if(!resultProduct){
							product.setStatus(6);
							productService.updateProductById(product);
							resultProduct = true;
						}

					}

				}
				repay_profit = vo.getIncome();



				reconciliation.setOrder_date(DateUtil.formatDate(new Date()));
				reconciliation.setCreate_time(new Date());
				reconciliation.setTrans_amt(repay_profit.toString());
				reconciliation.setMer_cust_id(trade_no);
				reconciliation.setTrans_type("REPAYMENT");
				reconciliationService.save(reconciliation);


				Double amount1 = repay_profit + repay_principal;
				Funds fund = new Funds();
				String strAm = "本金：" + repay_principal + "利息：" + repay_profit;
				fund.setUser_id(id);
				fund.setAmount(amount1);
				fund.setRelation_id(vo.getId());
				fund.setAction(1);
				fund.setOperate_type(12);
				fund.setStatus(3);
				fund.setTrade_no(trade_no);
				fund.setTicket(strAm);
				fundsService.save(fund);

				int repay_period = vo.getNum();

				if(repay_period == 0){
					repay_period = 1;
				}

				String list_extend_param = "repay_principal^" + repay_principal.toString() +
						"|repay_profit^" + repay_profit.toString()+ "|repay_period^" + repay_period;
				//System.out.println(id.toString() );
				trade_list += trade_no + "~" + id.toString() + "~UID~BANK~" 
						+ amount1.toString() + "~~" + summary + "~"+ list_extend_param+ "~~" + product.getDebt_code()+"~";

			}
		}
		log.info(trade_list);
		String trade_no = UniqueUtil.getTradeNo();
		String sip  = InetAddress.getLocalHost().getHostAddress();
		String result = SinaUtil.createBatchHostPayTrade(trade_no, trade_list, sip);
		/*String result = SinaUtil.createSingleHostPayTrade(user.getId(), trade_no, out_trade_code, 
				summary, cny, goods_id, user.getReg_ip(),cny,repay_period,repay_profit,extend_param);*/

		log.info("product_id" + product_id + "trade_no:" + trade_no + ":"+ result );


		//返回值
		Map resultMap = JsonUtil.jsonToMap(result);

		Object strCode =  resultMap.get("response_code");

		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			log.info("********失败*********" + strCode + "####" + "product_id" + product_id);
		}else{
			log.info("成功 product_id"  + product_id);

			ProductDynamic pd = new ProductDynamic();
			pd.setPay_day(strDate);
			pd.setProduct_id(product_id);

			List<ProductDynamicVo> listProD = productDynamicService.selectForList(pd);

			if(listProD.size() == 1){
				ProductDynamic productDynamic = listProD.get(0);

				productDynamic.setStatus(2);
				productDynamic.setTrade_no(trade_no);

				productDynamicService.updateProductDynamicById(productDynamic);
			}
		}

	}


	public  void hostBatch(Log log) throws UnknownHostException{

		String strDate = DateUtil.formatDate(new Date());
		//返回用户    
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductBuyIncomeEnd");
		map.put("end_dates",strDate);
		//map.put("user_id", 7);

		List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);

		Map<Long,Double> mapO = new HashMap<Long,Double>();
		Map<Long,Double> mapIn = new HashMap<Long,Double>();
		boolean result = false;
		for(int i = 0;i<listBuyI.size();i++){
			ProductBuyIncome buyIncomeV = listBuyI.get(i);
			if(buyIncomeV.getStatus() == -1){

				Long uid = buyIncomeV.getUser_id();
				Double am = 0.0;
				Double cny=0.0;
				if(buyIncomeV.getIs_return_amount()  == 1){
					if(buyIncomeV.getAmount() != null){
						cny += buyIncomeV.getAmount();
					}	
				}

				Double income = buyIncomeV.getIncome();
				//cny += income;

				Object ob = mapO.get(uid);
				if(ob == null){
					mapO.put(uid, cny);
					mapIn.put(uid, income);
				}else{
					//本金
					am = mapO.get(uid);
					mapO.put(buyIncomeV.getUser_id(), am + cny);
					//利息
					Double in = mapIn.get(uid);
					mapIn.put(uid, in + income);
				}



				Funds fund = new Funds();

				fund.setRelation_id(buyIncomeV.getId());
				fund.setOperate_type(12);
				fund.setStatus(3);

				List<FundsVo> listVo = fundsService.selectForList(fund);

				if(listVo.size() == 1)
				{
					if(!result )
						result = true;//确定发短信

					if(buyIncomeV.getIs_return_amount()  == 1){
						//金额
						ProductBuy productBuy = new ProductBuy();
						productBuy.setId(buyIncomeV.getBuy_id());
						@SuppressWarnings("unchecked")
						List<ProductBuyVo> listBuyVo = productBuyService.selectForList(productBuy);
						ProductBuyVo buyVo = listBuyVo.get(0);
						buyVo.setStatus(2);

						productBuyService.updateProductBuyById(buyVo);
					}


					buyIncomeV.setStatus(1);
					productBuyIncomeService.updateProductBuyIncomeById(buyIncomeV);


					User user = new User();
					user.setId(uid);
					User user1 = userService.load(user);
					//更新  

					Funds vo = listVo.get(0);
					vo.setStatus(1);
					vo.setCreate_time(new Date());
					vo.setBalance(SinaResultUtil.queryBalance(user1));
					fundsService.updateFundsById(vo);
				}

			}

		}
		String ip = InetAddress.getLocalHost().getHostAddress();
		if(result){


			Iterator<Map.Entry<Long, Double>> it = mapO.entrySet().iterator();
			while (it.hasNext()) {

				Map.Entry<Long, Double> entry = it.next();
				Long in = entry.getKey();
				UserFundStat userFund = new UserFundStat();
				userFund.setUser_id(in);

				User user = new User();
				user.setId(in);
				User user1 = userService.load(user);

				UserFundStat voF = userFundStatService.selectForObjectById(userFund);
				Double cny = entry.getValue();
				voF.setBalance(SinaResultUtil.queryBalance(user1));

				Double amountUid = entry.getValue();
				Double incomeUid = mapIn.get(in);
				//待收减少
				voF.setPend_return(voF.getPend_return() - amountUid);//本金
				voF.setPend_income(voF.getPend_income() - incomeUid);//利息
				//已收增加
				voF.setReturned(voF.getReturned() + amountUid);
				voF.setIncomed(voF.getIncomed() + incomeUid);
				//
				userFundStatService.updateUserFundStatById(voF);

				Sms sms = new Sms();
				sms.setSms_type(Integer.valueOf(3));
				sms.setPhone(user1.getUser_name());
				sms.setSource_ip(ip);
				Double temp = cny + incomeUid;

				DecimalFormat df = new DecimalFormat("######0.00");
				String strTemp = df.format(temp);
				sms.setStatus(0);
				sms.setContent("amount:" + strTemp);

				sms.setProduct("正道");
				sms.setAmount("");
				sms.setIncome(strTemp);
				String res = smservice.sendSms(sms);

				if(res!=null && "0".equals(res)){					

					System.out.println("ok ___id : "  +  user1.getId() + "name : " + user1.getReal_name());
				}else{
					System.out.println("no ___id : "  +  user1.getId() + "name : " + user1.getReal_name());
				}
			}
			log.info("短信发送完成");
			tradeMap.clear();
			ServiceChargeUtil.getInstance().setFundsShutDown(false);
		}


		return ;
	}

	@Autowired(required = true)
	public void setProductBuyIncomeService(IProductBuyIncomeService productBuyIncomeService) {
		this.productBuyIncomeService = productBuyIncomeService;
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
	public void setFundsService(IFundsService fundsService) {
		this.fundsService = fundsService;
	}

	@Autowired(required = true)
	public void setLenderService(ILenderService lenderService) {
		this.lenderService = lenderService;
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
	public void setProductDynamicService(IProductDynamicService productDynamicService) {
		this.productDynamicService = productDynamicService;
	}

	@Autowired(required = true)
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Autowired(required = true)
	public void setSmsService(ISmsService smservice) {
		this.smservice = smservice;
	}

}
