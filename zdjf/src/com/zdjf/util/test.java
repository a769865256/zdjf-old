package com.zdjf.util;

import java.util.HashMap;
import java.util.Map;

import com.zdjf.components.mobile.SinaUtil;

import ddy.util.MD5Util;

public class test {

	public static void main(String[] args) {
		Map parameterMap=new HashMap();
//		String md5Sign = Md5Util.md5to32("15966625198" + "2" + "172.16.0.213" +
//				Constants.API_KEY);
////		parameterMap.put("phone", "15966625198");
////		parameterMap.put("ip", "172.16.0.213");
////		parameterMap.put("reg_source", 2);
////		parameterMap.put("sign", md5Sign);
		parameterMap.put("stat", 1);
		parameterMap.put("trade_no", "zdjfu1523415212969711287");
////		String url ="http://pctest.zdjfu.com/m/userFundStat/pre/recharge.json";
		String url="http://localhost:8080/zdjf/sina/asy/collect/trade.json";
		System.out.println(HttpClientUtil.post(url, parameterMap));
//		Map parameterMap=new HashMap();	
//		String pay_sign = Md5Util.md5to32("13757110536" + "2" + "172.16.0.139" +
//				Constants.API_KEY);
//		parameterMap.put("phone", "13757110536");
//		parameterMap.put("sign", pay_sign);
//		parameterMap.put("reg_source", "2");
//		parameterMap.put("ip", "172.16.0.139");
//		System.out.println(HttpClientUtil.post(url, parameterMap));
//		System.out.println(StrUtils.idcardToSex("330261198206243529"));
//		System.out.println(MD5Util.md5("zdjfu@888888"));
//		System.out.println(SinaUtil.queryAccountDetails(6863l, "20180207000000", "20180208000000", 30, 1, "192.168.0.143","101"));
//		System.out.println(SinaUtil.queryBankCard("31850"));
//		String[] str={"88907596","9884","14068","83998","88901621","85925","29917","88895042","106951","88900234","133391","111882","60801","38261","109059","109474"};
//		for(int i=0;i<str.length;i++){
//			System.out.println(SinaUtil.setMemberHostRole(StrUtils.convToLong(str[i]), Constants.HOST_ROLE_INVESTOR));
//		}
//		String url = "http://localhost:8080/zdjf/m/userGift/Payment.action";
//		Map<String,Object> parameterMap = new HashMap<String,Object>();
//		parameterMap.put("gift_name", "回款红包");
//		parameterMap.put("gift_source", 105);
//		/*String str =*/ HttpClientUtil.post(url, parameterMap);
		//zdjfu1522769435325220325 88916845
		//zdjfu1522770175939781690 88916846
//		System.out.println(SinaUtil.createHostRefund(88918789l, "zdjfu1523346911134798711", "zdjfu1523346911134798710", "满标退款",6000.00, "172.16.0.213"));
	}

}
