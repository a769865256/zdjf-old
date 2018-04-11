package com.zdjf.components.mobile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.CoinStreamVo;
import com.zdjf.domain.fund.Funds;
import com.zdjf.domain.fund.FundsVo;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.product.ProductBuy;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserCoupon;
import com.zdjf.domain.user.UserCouponVo;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.service.fund.ICoinStreamService;
import com.zdjf.service.fund.IFundsService;
import com.zdjf.service.fund.IUserFundStatService;
import com.zdjf.service.product.IProductBuyService;
import com.zdjf.service.user.IUserCouponService;
import com.zdjf.service.user.IUserGiftService;
import com.zdjf.service.user.IUserService;
import com.zdjf.util.JsonUtil;
import com.zdjf.util.RoofNumberUtils;
import com.zdjf.webservice.mobile.SynchronousWebService;

/*返回一些新浪一些结果，提供一些方法*/
public class SinaResultUtil {
	
	//查询用户的可用余额
	public static Double queryBalance(User user){
		
		String result = SinaUtil.queryBalance(user.getId(), user.getReg_ip(),"");
		
		Map resultMap = JsonUtil.jsonToMap(result);
		Object strCode =  resultMap.get("response_code");
		if(strCode != null && strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			System.out.println(user.getId() + result + user.getReg_ip());
			
			Map dataMap =  (Map) resultMap.get("data");
			
			//String balance = (String) dataMap.get("balance");
			String vailable_balance = (String) dataMap.get("available_balance");
			
			return RoofNumberUtils.StringtoDouble(vailable_balance);
		}
		
		return 0.0;
	}
	
	//查询用户是否支付交易密码
	public static boolean isPassword(User user){
		
		String result = SinaUtil.isSetPayPassword(user.getId(), user.getReg_ip());
		Map resultMap = JsonUtil.jsonToMap(result);
		Object strCode = resultMap.get("response_code");
		if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
			return false;
		}
		Map dataMap =  (Map) resultMap.get("data");
		Object is =  dataMap.get("is_set_paypass");
		if(is.toString().equalsIgnoreCase("Y"))
		{
			
			return true;
		}
		return false;
	}
	
	public static Double doubleToTwo(Double doub){
		BigDecimal   b   =   new   BigDecimal(doub); 
		Double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	public static String doubleToTwoToString(Double temp){
		//四舍五入  double
		BigDecimal b = new BigDecimal(temp); 
		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		//格式字符串
		java.text.DecimalFormat df = new java.text.DecimalFormat("######0.00"); 
		return df.format(f1);
	}
	
	public static Double invested(Long product_id,int status,IFundsService fundsService/*,
			IUserFundStatService userFundStatService,IProductBuyService productBuyService,
			IUserCouponService userCouponService,ICoinStreamService coinStreamService,
			IUserGiftService userGiftService,IUserService userService*/){
		
		//资金流动明细  锁定金额
		Funds fund = new Funds();
		fund.setRelation_id(product_id);
		fund.setOperate_type(22);
		fund.setStatus(status);
		List<FundsVo> listFunds = fundsService.selectForList(fund);
		Double fundAmount = 0.0;
		
		for(int i = 0;i<listFunds.size();i++){
			//fundAmount += 
			FundsVo fVo = listFunds.get(i);
			if(fVo.getTrade_no() != null){
				String result = SinaUtil.queryHostTrade(fVo.getUser_id(), fVo.getTrade_no(), "", "");
				/*String tr = fVo.getTrade_no();
				String re = SinaUtil.queryPayResult(trade_no);*/
				//返回值
				Map resultMap = JsonUtil.jsonToMap(result);
				
				Object strCode =  resultMap.get("response_code");
				
				if(strCode == null || !strCode.toString().equalsIgnoreCase("APPLY_SUCCESS")){
					
					continue ;
				}
				
				Map dataMap =  (Map) resultMap.get("data");
				
				String trade_list = (String) dataMap.get("trade_list");
				
				if(trade_list.indexOf("WAIT_PAY")>0){
					fundAmount += fVo.getAmount();
				}else if(trade_list.indexOf("TRADE_CLOSED")>0){
					fVo.setStatus(5);
					fundsService.updateFundsById(fVo);
					
					/*if(status == 9){  //付款成功的异常情况
						UserFundStat userFund = new UserFundStat();
						userFund.setUser_id(fVo.getId());
						UserFundStat voF = userFundStatService.selectForObjectById(userFund);
						ProductBuy buy = new ProductBuy();
						buy.setId(fVo.getRelation_id());
						//根据关联ID  找到buy
						ProductBuy buy = productBuyService.selectForObjectById(fVo.getRelation_id());
						//userFundStatService.selectForList(voF)
						if(buy.getUser_coupon_id() != null){//加息券回退
							UserCoupon userCoupon = new UserCoupon();
							userCoupon.setId(buy.getUser_coupon_id());
							userCoupon.setStatus(2);
							List<UserCouponVo> listCoupon  = userCouponService.selectForList(userCoupon);
							UserCouponVo userVo = listCoupon.get(0);
							userVo.setStatus(1);
							userCouponService.updateUserCouponByCouponId(userVo);
						}
						
						if(buy.getUser_gift_id() != null){//红包ID
							UserGift userCoupon = new UserGift();
							userCoupon.setId(buy.getUser_gift_id());
							userCoupon.setStatus(2);
							List<UserGiftVo> listCoupon  = userGiftService.selectForList(userCoupon);
							UserGiftVo userVo = listCoupon.get(0);
							userVo.setStatus(1);
							userGiftService.updateUserGiftByGiftId(userVo);
							
							//代收  20-12-04
							voF.setPend_income(voF.getPend_income() - buy.getIncomed() - buy.getAmount());
							//累计收益
							voF.setIncomed(voF.getIncomed() - buy.getIncomed() - userVo.getAmount());
						}
						
						if(buy.getCoin() != null){//正经值
							
							CoinStream stream = new CoinStream();
							stream.setRelation_id(buy.getId());
							stream.setAmount(buy.getCoin());
							List<CoinStreamVo> listSt = coinStreamService.selectForList(stream);
							CoinStreamVo cVo = listSt.get(0);
							cVo.setStatus(-1);
							coinStreamService.updateCoinStreamById(cVo);
							
							voF.setCoin_cost(voF.getCoin_cost() - buy.getCoin());
							voF.setCoin_balance(voF.getCoin_balance() + buy.getCoin());
							
							//代收  20-12-04
							voF.setPend_income(voF.getPend_income() - buy.getIncomed() - buy.getAmount());
							//累计收益
							voF.setIncomed(voF.getIncomed() - buy.getIncomed() - buy.getCoin());
						}
						
						voF.setBalance(voF.getBalance() + buy.getAct_pay_money());
						voF.setInvest_sum(voF.getInvest_sum() - buy.getAmount());
						voF.setInvest_frequency(voF.getInvest_frequency() - 1);
						
						userFundStatService.updateUserFundStatById(voF);
					}*/
				
				}else if(trade_list.indexOf("TRADE_FINISHED")>0){
					//成功
					
					/*if(status == 9){
						fVo.setStatus(1);
						fundsService.updateFundsById(fVo);
					}*/
					
					/*if(status == 3){
						SynchronousWebService sWeb = new SynchronousWebService();
						
						User us = new User();
						us.setId(fVo.getUser_id());
						fVo.setSummary("投标错误");
						fundsService.updateFundsById(fVo);
						//User user = userService.load(us);
						
						//sWeb.getProductBuy(user, amount, debt_code, preferential, user_coupon_id, user_gift_id, trade_no, req_source, coin_num);
						
						fVo.setStatus(1);
						fundsService.updateFundsById(fVo);
					}*/
					
					
				}else if(trade_list.indexOf("TRADE_FAILED")>0){
					
				}
			}
			
		}
		
		return doubleToTwo(fundAmount);
	}
			

}
