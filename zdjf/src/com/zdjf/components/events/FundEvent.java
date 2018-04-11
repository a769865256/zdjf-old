package com.zdjf.components.events;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationEvent;

import com.zdjf.components.mobile.SinaUtil;
import com.zdjf.components.mobile.UniqueUtil;
import com.zdjf.domain.fund.Coin;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.CoinVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.product.Product;
import com.zdjf.domain.product.ProductVo;
import com.zdjf.domain.user.Lender;
import com.zdjf.domain.user.LenderVo;
import com.zdjf.domain.user.User;
import com.zdjf.service.fund.ICoinService;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductService;
import com.zdjf.service.user.ILenderService;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RoofNumberUtils;

public class FundEvent extends ApplicationEvent{

	/**
	 * 
	 */
	
	private User user;
	
	private static final long serialVersionUID = -4264627574441675226L;

	public FundEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public FundEvent(Object source,User user,String name,
			ICoinStreamService coinStreamService,ICoinService coinService) {
		super(source);
		// TODO Auto-generated constructor stub
		Coin coin = new Coin();
		coin.setCoin_name(name);
		
		List<CoinVo> list = coinService.selectForList(coin);
		
		if(list.size() == 0)
			return ;
		
		Coin vo = list.get(0);
		
		CoinStream stream = new CoinStream();
		stream.setUser_id(user.getId());
		stream.setAction(1);
		stream.setAmount((double)vo.getCoin_num());
		stream.setStatus(1);
		stream.setRemark(vo.getCoin_name());
		stream.setCreate_time(new Date());
		stream.setNum(vo.getCoin_num());
		
		coinStreamService.save(stream);
	}
	
	//@SuppressWarnings("null")
	public FundEvent(Object source,User user,IUserFundStatService userFundStatService,
			ICoinStreamService coinStreamService) {
		super(source);
		// TODO Auto-generated constructor stub
		this.user = user;
		
		boolean re = false;
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user.getId());
		
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		if(voF == null)
			return ;
		//调用支付接口
		String result = SinaUtil.queryBalance(user.getId(), user.getReg_ip(),"");
		
		Map resultMap = JsonUtil.jsonToMap(result);
		Object strCode =  resultMap.get("response_code");
		if(strCode != null && strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			System.out.println(user.getId() + result + user.getReg_ip());
			Map dataMap =  (Map) resultMap.get("data");
			
			String balance = (String) dataMap.get("balance");
			String vailable_balance = (String) dataMap.get("available_balance");
			//统计资金
			Double q_vailable_balance = RoofNumberUtils.StringtoDouble(vailable_balance);
			if(voF != null && q_vailable_balance != voF.getBalance()){
				voF.setBalance(RoofNumberUtils.StringtoDouble(vailable_balance));
				voF.setUpdate_time(new Date());
				voF.setLocked_money(RoofNumberUtils.StringtoDouble(balance)-RoofNumberUtils.StringtoDouble(vailable_balance));
				re = true;
			}
			
		}
		
		/*CoinStream stream = new CoinStream();//每一步进行累加
		stream.setUser_id(user.getId());
		stream.setAction(1);
		List<CoinStreamVo> listStream = coinStreamService.selectForList(stream);
		if(listStream.size()>0){
			
			double coin_total = 0.0;
			//double coin_balance = voF.getCoin_balance();
			for(int i = 0;i<listStream.size();i++){
				CoinStreamVo vo = listStream.get(i);
				Double amount = vo.getAmount();
				if(amount != null){
					coin_total += amount;
					//coin_balance += amount;
				}
			}
			
			if(voF != null && voF.getCoin_total() != coin_total){
				voF.setCoin_total(coin_total);//获取正经值
				//voF.setCoin_balance(coin_balance);
				re = true;
			}
		}*/
		
		
		//更新统计信息
		if(re)
			userFundStatService.updateUserFundStatById(voF);
	}
	
	public FundEvent(Object source,Long user_id,int type,Double coin_num,IUserFundStatService userFundStatService,
			ICoinStreamService coinStreamService) {
		super(source);
		// TODO Auto-generated constructor stub
		
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user_id);
		
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		
		Double coin_total = voF.getCoin_total() + coin_num;
		Double coin_balance = voF.getCoin_balance() + coin_num;
		
		voF.setCoin_balance(coin_balance);
		voF.setCoin_total(coin_total);
		voF.setUpdate_time(new Date());
		userFundStatService.updateUserFundStatById(voF);

		CoinStream coinStream = new CoinStream();

		coinStream.setAmount(coin_num*1.0);
		coinStream.setUser_id(user_id);
		coinStream.setCreate_time(new Date());
		coinStream.setAction(1);
		coinStream.setType(type);
		coinStream.setNum(coin_num.intValue());
//		coinStream.setRemark("注册");
		coinStream.setStatus(1);//2018-1-12 add
		coinStream.setBalance(coin_balance);
		coinStreamService.save(coinStream);
	}
	
	public FundEvent(Object source,Long user_id,int type,Double coin_num,IUserFundStatService userFundStatService,
			ICoinStreamService coinStreamService,String trade_no) {
		super(source);
		// TODO Auto-generated constructor stub
		
		CoinStream coinStream = new CoinStream();
		
		coinStream.setAmount(coin_num*1.0);
		coinStream.setUser_id(user_id);
		coinStream.setCreate_time(new Date());
		coinStream.setAction(1);
		coinStream.setType(type);
		coinStream.setStatus(1);
		coinStream.setNum(coin_num.intValue());
		coinStream.setRemark(trade_no);
		coinStream.setStream_id(-7L);
		coinStreamService.save(coinStream);
		
		UserFundStat userFund = new UserFundStat();
		userFund.setUser_id(user_id);
		
		UserFundStat voF = userFundStatService.selectForObjectById(userFund);
		
		Double coin_total = voF.getCoin_total() + coin_num;
		Double coin_balance = voF.getCoin_balance() + coin_num;
		
		voF.setCoin_balance(coin_balance);
		voF.setCoin_total(coin_total);
		voF.setUpdate_time(new Date());
		userFundStatService.updateUserFundStatById(voF);
	}
	
	
	public FundEvent(Object source,Long user_id ,Double amount,String debt_code,IProductService productService,
			ILenderService lenderService) throws InterruptedException{
		super(source);
		// TODO Auto-generated constructor stub
		
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
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
