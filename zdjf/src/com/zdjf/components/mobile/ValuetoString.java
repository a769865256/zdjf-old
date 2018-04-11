package com.zdjf.components.mobile;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ValuetoString {
	
	public static Map<String,String> eachProperties(Object model) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException{
	    Field[] field = model.getClass().getDeclaredFields(); //获取实体类的所有属性，返回Field数组
	    Map<String,String> map = new HashMap<String,String>();
	    
	    for(int j=0 ; j<field.length ; j++){ //遍历所有属性
	    	Field fields = field[j];
	        String name = fields.getName(); //获取属性的名字
	        if(name.equalsIgnoreCase("serialVersionUID") /*|| fields.get(model) == null*/)
	        	continue ;
	        fields.setAccessible(true);
	        
	        String type = field[j].getGenericType().toString(); //获取属性的类型
	        
	        String value = "";
	        if(fields.get(model) != null){

	        	/*if(type.equals("class java.lang.Double")){
	        		Double temp = (Double) fields.get(model);
	        		//四舍五入  double
	        		BigDecimal b = new BigDecimal(temp); 
	        		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
	        		//格式字符串
	        		java.text.DecimalFormat df = new java.text.DecimalFormat("######0.00"); 
	        		value = df.format(f1);
	        		//System.out.println(value);//出现  100.00数据问题
	        	}else{*/
	        		value = fields.get(model).toString();
	        	//}
		        	
	        	
	        }
	        	
	  
	        //System.out.println("attribute name:" + name +"value"+value);
	        //name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
	       
	       
	      
	        if(type.equals("class java.util.Date")){
	        	
	        	SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
	        	SimpleDateFormat sdf2 = null;
	        	if(name.equalsIgnoreCase("create_time")){
	        		sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	}else{
	        		sdf2= new SimpleDateFormat("yyyy-MM-dd");
	        	}
	        		
	        	//数据
	        	if(!value.isEmpty())
	        		value = sdf2.format(sdf1.parse(value));
	    
	        }
	        
	        map.put(name, value);
	    }
	    return map;
	}

}
