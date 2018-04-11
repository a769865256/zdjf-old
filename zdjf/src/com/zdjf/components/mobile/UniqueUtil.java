package com.zdjf.components.mobile;

import java.util.Date;

import com.zdjf.util.StringUtil;

public class UniqueUtil {
	
	public static String getTradeNo(){
		Date date = new Date();
		
		String trade_no= "zdjfu" + date.getTime() + StringUtil.getRandom();
		
		return trade_no;
	}

}
