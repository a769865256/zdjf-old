package com.zdjf.components.mobile;

public class SignMathRandom {
	
	/** 
     * 0出现的概率为%50 
     */  
 private static double rate0 = 0.55;  
 /** 
     * 1出现的概率为%20 
     */  
 private static double rate1 = 0.15;  
 /** 
     * 2出现的概率为%15 
     */  
 private static double rate2 = 0.12;  
 /** 
     * 3出现的概率为%10 
     */  
 private static double rate3 = 0.08;  
 /** 
     * 4出现的概率为%4 
     */  
 private static double rate4 = 0.06;  
 /** 
     * 5出现的概率为%1 
     */  
 private static double rate5 = 0.04;  
  
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
		  return 1;  
	  }  
	  else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1)  
	  {  
		  return 2;  
	  }  
	  else if (randomNumber >= rate0 + rate1  
	    && randomNumber <= rate0 + rate1 + rate2)  
	  {  
		  return 3;  
	  }  
	  else if (randomNumber >= rate0 + rate1 + rate2  
	    && randomNumber <= rate0 + rate1 + rate2 + rate3)  
	  {  
		  return 4;  
	  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3  
	    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4)  
	  {  
		  return 5;  
	  }  
	  else if (randomNumber >= rate0 + rate1 + rate2 + rate3 + rate4  
	    && randomNumber <= rate0 + rate1 + rate2 + rate3 + rate4  
	      + rate5)  
	  {  
		  return 6;  
	  }  
	  	return 0;  
	 }  

}
