package com.zdjf.domain.product;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zdjf.util.DateUtil;

public class test {
public static void main(String[] args) {
	DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	Date start_date;
	try {
		start_date = df.parse("2017-12-08");
	
	Date end_date=df.parse("2018-01-10");
	 int incomeDays = DateUtil.daysBetween(start_date, end_date);
			incomeDays += 1;
			System.out.println(incomeDays-1); 
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
