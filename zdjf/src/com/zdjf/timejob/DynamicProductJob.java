package com.zdjf.timejob;

import java.net.InetAddress;
import java.net.UnknownHostException;
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

import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductBuyIncome;
import com.zdjf.domain.product.ProductDynamic;
import com.zdjf.domain.product.ProductDynamicVo;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.service.product.IProductBuyIncomeService;
import com.zdjf.service.product.IProductDynamicService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.util.DateUtil;

class DynamicIncome{
	//产品ID
	Long product_id;
	//本金
	Double amount;
	//利息
	Double income;

	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
}


@Component
public class DynamicProductJob {

	private IProductDynamicService productDynamicService;

	private IProductBuyIncomeService productBuyIncomeService;

	private IProductService productService;

	private ILenderService lenderService;

	@Scheduled(cron = "0 30 9 * * ?")
	//	@Scheduled(cron = "0 0/3 * * * ?")
	public void dynamicProduct() throws UnknownHostException{

		Log log = LogFactory.getLog(this.getClass());
		Date date = DateUtil.addDate(new Date(), 7);


		String strDate = DateUtil.formatDate(date);


		log.info(String.format("DynamicProductJobtime-%s####日期%s",date,strDate));

		ProductDynamic pdc = new ProductDynamic();
		pdc.setPay_day(strDate);

		List<ProductDynamicVo> listProD = productDynamicService.selectForList(pdc);
		int step = 0;//根据 值 判断一下  进度
		if(listProD.size()==0){
			step = 1;// 今天没有数据
		}else{
			step = 2;//  已经跑完
		}

		//返回用户    
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page_name","selectProductBuyIncomeEnd");
		map.put("end_dates",strDate);

		//今天要回款buyId
		List<ProductBuyIncome> listBuyI = productBuyIncomeService.selectForList(map);

		String ip = InetAddress.getLocalHost().getHostAddress();

		Map<Long,DynamicIncome> mapO = new HashMap<Long,DynamicIncome>();//按照项目统计金额

		if(step == 1){
			log.info("ip : " + ip + "time:" + DateUtil.formatDateTime(date));
			for(int i = 0;i<listBuyI.size();i++){
				ProductBuyIncome buyIncomeV = listBuyI.get(i);
				if(buyIncomeV.getStatus() == -1){
					Double am = 0.0;//按照利息与本金统计
					Double cny = 0.0;//总计金额
					Long product_id = buyIncomeV.getProduct_id();

					//计算本金
					if(buyIncomeV.getIs_return_amount()  == 1){
						if(buyIncomeV.getAmount() == null){
							buyIncomeV.setAmount(0.0);
						}

						cny = buyIncomeV.getAmount();
						//am +=buyIncomeV.getAmount();


					}
					//计算利息
					//cny += buyIncomeV.getIncome();
					am =buyIncomeV.getIncome();

					//计算

					log.info("product_id:" + product_id +"#####user_id:" + buyIncomeV.getUser_id() + 
							"******本金：" +  cny + "~~~~利息：" + am );
					DynamicIncome ob = mapO.get(product_id);
					if(ob == null){
						//记录productid
						ob = new DynamicIncome();
						ob.setProduct_id(product_id);
						ob.setIncome(am);//利息
						ob.setAmount(cny);//本金
						mapO.put(product_id, ob);

					}else{
						ob.setAmount(ob.getAmount() + cny);//本金
						ob.setIncome(ob.getIncome() + am);
						mapO.put(product_id,ob);
					}
				}

			}
		}

		//今天本期各个项目投资金额汇总出借人金额
		Iterator<Map.Entry<Long, DynamicIncome>> its = mapO.entrySet().iterator();
		//还款
		Map<Long,Double> mapLend = new HashMap<Long,Double>();
		if(step == 1){//
			while (its.hasNext()) {
				Map.Entry<Long, DynamicIncome> entry = its.next();
				Long uid = entry.getKey();
				ProductDynamic pd = new ProductDynamic();
				pd.setProduct_id(uid);
				//从中获取
				DynamicIncome dIncome = entry.getValue();
				Double tempAmount = dIncome.getAmount();
				Double tempIncome = dIncome.getIncome();
				//利息与本金
				Double ams = tempAmount + tempIncome;
				pd.setAmount(ams);
				pd.setPay_day(strDate);
				pd.setStatus(1);


				//产品
				Product pro = new Product();
				pro.setId(uid);
				List<ProductVo> productList = productService.selectForList(pro);

				if(productList.size() > 1){
					ProductVo product = productList.get(0);
					product.setRemark("项目id不是唯一的"+uid);
					continue ;//继续项目
				}else if(productList.size() == 0){
					continue ;
				}

				ProductVo product = productList.get(0);
				pd.setDebt_code(product.getDebt_code());
				pd.setProduct_code(product.getProduct_code());
				pd.setLender_id(product.getLender_id());
				pd.setLoan_id(product.getLoan_id());

				//发布 2018-1-8
				pd.setIssure_time(product.getIssue_time());
				//计算已还金额
				ProductDynamic prodc = new ProductDynamic();
				prodc.setDebt_code(product.getDebt_code());
				//prodc.setProduct_id(product.getId());
				List<ProductDynamicVo> listProDc = productDynamicService.selectForList(prodc);
				int num = listProDc.size();
				Double incomed = 0.0;

				if(num>0){
					//已还的利息
					for(int ii = 0;ii<num;ii++){
						ProductDynamicVo dcVo = listProDc.get(ii);
						incomed += dcVo.getAmount();
					}

				}
				//已还利息
				pd.setPayed_income(incomed);
				pd.setNum(num + 1);
				pd.setTo_pay_amount(tempAmount);
				pd.setTo_pay_income(tempIncome);
				pd.setNow_to_pay_amount(tempAmount);
				pd.setNow_to_pay_income(tempIncome);
				//pd.setDay_to_pay_amount(tempAmount);
				//pd.setDay_to_pay_income(tempIncome);
				//
				Lender len = new Lender();
				len.setId(product.getLender_id());
				List<LenderVo> listVo = lenderService.selectForList(len);
				Lender lender = listVo.get(0);

				Double ob = mapLend.get(lender.getUser_id());
				if(ob == null){
					mapLend.put(lender.getUser_id(), ams);
					pd.setLender_balance(ams);
				}else{
					mapLend.put(lender.getUser_id(),ams + ob);
					pd.setLender_balance(ams +ob);
				}
				//保存  动态数据
				productDynamicService.save(pd);

			}
		}
		//时间
	}

	@Autowired(required = true)
	public void setProductDynamicService(IProductDynamicService productDynamicService) {
		this.productDynamicService = productDynamicService;
	}

	@Autowired(required = true)
	public void setProductBuyIncomeService(IProductBuyIncomeService productBuyIncomeService) {
		this.productBuyIncomeService = productBuyIncomeService;
	}

	@Autowired(required = true)
	public void setLenderService(ILenderService lenderService) {
		this.lenderService = lenderService;
	}

	@Autowired(required = true)
	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
}
