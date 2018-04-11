package com.zdjf.util;

public class StringUtil {

	/**
	 * 随机取一个6位数的验证码
	 * @return
	 * @throws Exception
	 */
	public static String getRandom(){
		Integer randNum = 1 + (int)(Math.random() * 999999);
		String rand = "";
		if(randNum.toString().length()==1){
			rand = randNum + "00000";
		}else if(randNum.toString().length()==2){
			rand = randNum + "0000";
		}else if(randNum.toString().length()==3){
			rand = randNum + "000";
		}else if(randNum.toString().length()==4){
			rand = randNum + "00";
		}else if(randNum.toString().length()==5){
			rand = randNum + "0";
		}else{
			rand = randNum +"";
		}
		return rand;
	}
	
	/**
	 * 隐藏字符串*来代替
	 * @param showNumber 开头展示字数
	 * @param str
	 * @param showLastNumber 结尾展示字数
	 * @return
	 */
	public static String hideStr(int showNumber,String str,int showLastNumber){
		if(str!=null && !"".equals(str)){
			if(showNumber<str.length() && showLastNumber<str.length()){
				String returnStr = str.substring(0, showNumber);
				for(int i=0;i<str.length()-showNumber-showLastNumber;i++){
					returnStr += "*";
				}
				if(showLastNumber>0){
					returnStr += str.substring(str.length()-showLastNumber, str.length());
				}
				return returnStr;
			}else{
				return str;
			}
		}
		return null;
	}
	
	
}
