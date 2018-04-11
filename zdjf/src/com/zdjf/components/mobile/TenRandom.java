package com.zdjf.components.mobile;

public class TenRandom {
	
	/** 
     * 0出现的概率为%50 
     */  
 private static double rate0 = 0.80;  
 /** 
     * 1出现的概率为%20 
     */  
 private static double rate1 = 0.20;  
   
  
 /** 
  * Math.random()产生一个double型的随机数，判断一下 
  * 例如0出现的概率为%50，则介于0到0.50中间的返回0 
     * @return int 
     * 
     */  
 public static int PercentageRandom()  
 {  
	  double randomNumber;  
	  randomNumber = Math.random();  
	  if (randomNumber >= 0 && randomNumber <= rate0)  
	  {  
		  return 2;  
	  }  
	  else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1)  
	  {  
		  return 3;  
	  }  
	  
	  	return 0;  
	 } 

}
