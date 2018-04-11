package com.zdjf.components.mobile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/*提现手续费*/

public class ServiceChargeUtil {
	
	private volatile static ServiceChargeUtil uniqueInstance;
	
	private ServiceChargeUtil(){} //类外无法访问
	
	private static List<InvestmentOrder> list;
	
	private static List<Long> listProductId;
	
	private static boolean  fundsShutDown = false;//关闭回款
	
	private static Long lender_id = -1L;
	
	public  static synchronized ServiceChargeUtil getInstance(){ 
		
		  if (uniqueInstance == null) {  
			  uniqueInstance = new ServiceChargeUtil();  
			  
	     }  
  
        return uniqueInstance;  
    }  
	
	
	
	public static Long getLender_id() {
		return lender_id;
	}



	public static void setLender_id(Long lender_id) {
		ServiceChargeUtil.lender_id = lender_id;
	}



	public static boolean isFundsShutDown() {
		return fundsShutDown;
	}

	public static void setFundsShutDown(boolean fundsShutDown) {
		ServiceChargeUtil.fundsShutDown = fundsShutDown;
	}

	public static List<InvestmentOrder> addLatest(String phone,String amount,String type){//提现计算公式
		
		if(list == null){
			list = new ArrayList<InvestmentOrder>();
		}
		
		Collections.sort(list,new Comparator<InvestmentOrder>(){
            public int compare(InvestmentOrder arg0, InvestmentOrder arg1) {
                return -arg0.getPay_time().compareTo(arg1.getPay_time());
            }
	    });
		
		if(list.size()>=60){
			list.remove(59);
		}
		
		 
		Date date = new Date();
		InvestmentOrder order = new InvestmentOrder();
		
		order.setAmount(amount);
		order.setPhone(phone);
		order.setPay_time(date);
		order.setType(type);
		list.add(order);
		
		
		 
		 for(int i = 0;i<list.size();i++){
			 InvestmentOrder item = list.get(i);
			 item.setPay_time_ago(RelativeDateFormat.format(item.getPay_time()));
		 }
		
		return  list;
	}

	public static List<InvestmentOrder>  getList(){
		
		return  list;
	}
	
	public static void addProductId(Long product_id){
		
		if(listProductId == null){
			listProductId = new ArrayList<Long>();
		}
		listProductId.add(product_id);
	}
	
	public static List<Long> getProduct(){
		return listProductId;
	}

}
