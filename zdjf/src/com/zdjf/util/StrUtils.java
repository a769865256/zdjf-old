/*
 * StrUtils.java
 * Copyright by ddy
 * Description：
 * Modified By：Administrator
 * Modified Time：2015年7月30日
 * Modified Number：
 * Modified Contents：
 */

package com.zdjf.util;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zdjf.domain.common.DataField;

import ddy.util.DDYUtil;


/**
 * 字符串处理公共类
 * 
 * @author guanhj
 * @version 2015年7月30日
 * @see StrUtils
 * @since
 */
public class StrUtils {

    /**
     * 首字母大写
     * @param name
     * @return
     */
    public static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
    
    public static void removeNullValue(Map map){  
        Set set = map.keySet();  
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {  
            Object obj = (Object) iterator.next();  
            Object value =(Object)map.get(obj);  
            remove(value, iterator);  
        }  
    } 
    private static void remove(Object obj,Iterator iterator){  
        if(obj instanceof String){  
            String str = (String)obj;  
            if(!emptyJudge(str)){  
                iterator.remove();  
            }  
        }else if(obj instanceof Collection){  
            Collection col = (Collection)obj;  
            if(col==null||col.isEmpty()){  
                iterator.remove();  
            }  
              
        }else if(obj instanceof Map){  
            Map temp = (Map)obj;  
            if(temp==null||temp.isEmpty()){  
                iterator.remove();  
            }  
              
        }else if(obj instanceof Object[]){  
            Object[] array =(Object[])obj;  
            if(array==null||array.length<=0){  
                iterator.remove();  
            }  
        }else{  
            if(emptyJudge(obj.toString())){  
                iterator.remove();  
            }  
        }  
    }

    /**
     * 获取隐藏中间4位的手机号码
     *
     * @return
     */
    public static String phoneHide(String phone) {
        if (phone != null && phone.length() == 11)
            return phone.substring(0, 3) + "****" + phone.substring(7, 11);
        else
            return phone;
    }
    
    public static  String format(int value, int len) {
		String formatted = Integer.toString(value);
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < len; i++) {
			buf.append("0");
		}
		buf.replace(len - formatted.length(), len, formatted);
		return buf.toString();
	}

    /**
     * 根据身份证号获取性别中文
     *
     * @param idcard
     * @return
     */
    public static String idcardToSexChn(String idcard) {
        String sexChn = "男";
        if (StrUtils.emptyJudge(idcard)
                && (idcard.length() == 15 || idcard.length() == 18)) {
            // 性别
            String lastValue = idcard.length() == 15 ? idcard.substring(
                    idcard.length() - 1, idcard.length()) : idcard.substring(
                    idcard.length() - 2, idcard.length() - 1);
            if (StrUtils.convToInt(lastValue, 0) % 2 == 0) {
                sexChn = "女";
            } else {
                sexChn = "男";
            }
        }
        return sexChn;
    }

    /**
     * 根据身份证号获取性别中文
     *
     * @param idcard
     * @return
     */
    public static Integer idcardToSex(String idcard) {
        Integer sex = Constants.NUM_ONE;
        if (StrUtils.emptyJudge(idcard)
                && (idcard.length() == 15 || idcard.length() == 18)) {
            String lastValue = idcard.length() == 15 ? idcard.substring(
                    idcard.length() - 1, idcard.length()) : idcard.substring(
                    idcard.length() - 2, idcard.length() - 1);
            if (StrUtils.convToInt(lastValue, 0) % 2 == 0) {
                sex = Constants.NUM_TWO;
            } else {
                sex = Constants.NUM_ONE;
            }
        }
        return sex;
    }

    /**
     * 根据身份证号码获取年龄
     *
     * @param idcard
     * @return
     */
    public static Integer idcardToAge(String idcard) {
        // 截取身份中表示出生年份的字符
        if (StrUtils.emptyJudge(idcard)
                && (idcard.length() == 15 || idcard.length() == 18)) {
            String str = "";
            // 生日
            if (idcard.length() == 15) {
                str = "19" + idcard.substring(6, 8);
            } else {
                str = idcard.substring(6, 10);
            }
            // 转换int
            int birthYear = Integer.parseInt(str);
            // 计算当前时间的年份
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int nowYear = calendar.get(Calendar.YEAR);
            // 返回两个年份差，就是年龄
            return nowYear - birthYear;
        }
        return 0;
    }

    /**
     * Description: 解密身份证号码<br>
     * 
     * @param base64
     * @return
     */
    public static String decoudeIdcard(String base64) {
        base64 = base64.replace("\n", Constants.NOTHING);
        return DDYUtil.decodeID(base64);
    }

    /**
     * Description: 解析性别<br>
     * 
     * @param base64
     * @return
     */
    public static Integer parseGender(String base64) {
        // 解密身份证号码
        String idcardNo = decoudeIdcard(base64);
        // 异常判断
        if (!StrUtils.emptyJudge(idcardNo)) {
            return Constants.NUM_ZERO;
        }
        int len = idcardNo.length();
        String sex = Constants.NOTHING;
        if (len != Constants.NUM_15 && len != Constants.NUM_18) {
            return Constants.NUM_ZERO;
        }
        if (len == Constants.NUM_15) {
            sex = idcardNo.substring(14, 15);
        } else {
            sex = idcardNo.substring(16, 17);
        }
        if (StrUtils.convToInt(sex) % Constants.NUM_TWO == 0) {
            return Constants.NUM_TWO;
        }
        return Constants.NUM_ONE;
    }

    /**
     * Description: 拼接list到字符串<br>
     * 
     * @param stringList
     * @return String
     */
    public static String listToString(List<String> stringList) {
        // 异常判断
        if (stringList == null) {
            return Constants.NOTHING;
        }
        // 结果字符串
        StringBuilder result = new StringBuilder();
        // 判断是否加逗号
        boolean flag = false;
        // 循环处理
        for (String string : stringList) {
            // 状态判断：不是第一次
            if (flag) {
                // 追加逗号
                result.append(Constants.STR_COLON);
            } else {
                // 一次过后置为true
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    /**
     * Description: 正则校验<br>
     * 
     * @param str
     * @param pattern
     * @return boolean
     */
    public static boolean matcherReg(String str, String pattern) {
        // 正则构造器
        Pattern p = Pattern.compile(pattern);
        // 正则执行器
        Matcher m = p.matcher(str);
        // 返回执行结果
        return m.matches();
    }

    /**
     * Description: 转换Html换行符<br>
     * 
     * @param line
     * @return String
     */
    public static String convBreakLineToBr(String line) {
        // 异常判断
        if (emptyJudge(line)) {
            // 换行符正则
            Pattern p = Pattern.compile("\r|\n");
            // 正则执行对象
            Matcher m = p.matcher(line);
            // 替换所有换行符
            String strNoBlank = m.replaceAll("<br />");
            // 替换制表符
            return convHorizeontalTab(strNoBlank);
        }
        return line;
    }

    /**
     * Description: 替换Html制表符<br>
     * 
     * @param line
     * @return String
     */
    public static String convHorizeontalTab(String line) {
        // 异常判断
        if (emptyJudge(line)) {
            // 制表符正则
            Pattern p = Pattern.compile("\t");
            // 正则执行对象
            Matcher m = p.matcher(line);
            // 替换所有制表符
            String strNoBlank = m.replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;");
            // 返回对象
            return strNoBlank;
        }
        return line;
    }

    /**
     * Description: DataFieldValue列表转MAP<br>
     * 
     * @param datas
     * @return Map<String, String>
     */
    public static Map<String, String> arrayToMap(List<DataField> datas) {
        // 结果MAP
        Map<String, String> map = new HashMap<String, String>(
            Constants.NUM_TEN);
        // 异常判断
        if (null == datas || datas.isEmpty()) {
            return map;
        }
        // 长度
        int size = datas.size();
        // 循环处理
        for (int i = 0; i < size; i++ ) {
            // 当前对象
            DataField data = datas.get(i);
            // 写入MAP
            map.put(data.getData_field_value(), data.getData_field_name());
        }
        return map;
    }

    /**
     * Description: 重定向URL<br>
     * 
     * @param url
     * @return
     */
    public static String redirectUrl(String url) {
        return Constants.REDIRECT + url;
    }

    /**
     * Description: URL数据解码<br>
     * 
     * @param str
     * @param charSet
     * @return String
     */
    public static String decodeUrl(String str, String charSet) {
        // 判断非空
        if (!emptyJudge(str)) {
            return Constants.NOTHING;
        }
        // 解码
        try {
            str = URLDecoder.decode(str, charSet);
        } catch (UnsupportedEncodingException e) {
            str = Constants.NOTHING;
        }
        return str;
    }

    /**
     * Description: 字符串非空判断<br>
     * 
     * @param str
     * @return 为空时返回false 不为空时返回true
     * @see
     */
    public static boolean emptyJudge(String str) {
        if (null != str) {
            str = str.trim();
        }
        return !(null == str || str.equals(Constants.NOTHING));
    }

    /**
     * Description: 转换为Int类型<br>
     * 
     * @param str
     * @return int
     */
    public static int convToInt(String str) {
        // 最终结果
        int res = Constants.NUM_ZERO;
        // 判断非空
        if (!emptyJudge(str)) {
            return res;
        }
        // 转换类型
        try {
            res = Integer.parseInt(str);
        } catch (Exception e) {
            res = Constants.NUM_ZERO;
        }
        return res;
    }

    /**
    * Description: 转换为Int类型<br>
    * 
    * @param str
    * @return int
    */
    public static int convToInt(String str, int def) {
        // 最终结果
        int res = def;
        // 判断非空
        if (!emptyJudge(str)) {
            return res;
        }
        // 转换类型
        try {
            res = Integer.parseInt(str);
        } catch (Exception e) {
            res = Constants.NUM_ZERO;
        }
        return res;
    }

    /**
     * Description: 转换为Long类型<br>
     * 
     * @param str
     * @return long
     */
    public static long convToLong(String str) {
        // 最终结果
        long res = Constants.LONG_ZERO;
        // 判断非空
        if (!emptyJudge(str)) {
            return res;
        }
        // 转换类型
        try {
            res = Long.parseLong(str);
        } catch (Exception e) {
            res = Constants.LONG_ZERO;
        }
        return res;
    }

    /**
     * Description: 转换为Long类型<br>
     * 
     * @param str
     * @return long
     */
    public static long convToLong(String str, long def) {
        // 最终结果
        long res = def;
        // 判断非空
        if (!emptyJudge(str)) {
            return res;
        }
        // 转换类型
        try {
            res = Long.parseLong(str);
        } catch (Exception e) {
            res = Constants.LONG_ZERO;
        }
        return res;
    }

    /**
     * Description: 转换为Double类型<br>
     * 
     * @param str
     * @return long
     */
    public static double convToDouble(String str) {
        // 最终结果
        double res = Constants.DOUBLE_ZERO;
        // 判断非空
        if (!emptyJudge(str)) {
            return res;
        }
        // 转换类型
        try {
            res = Double.parseDouble(str);
        } catch (Exception e) {
            res = Constants.DOUBLE_ZERO;
        }
        return res;
    }
    public static double convToDouble(Object obj) {
        // 最终结果
        double res = Constants.DOUBLE_ZERO;
        // 判断非空
        if (null==obj) {
            return res;
        }
        // 转换类型
        try {
            res = (double) obj;
        } catch (Exception e) {
            res = Constants.DOUBLE_ZERO;
        }
        return res;
    }
    public static BigDecimal convToBigDecimal(String str) {
        BigDecimal bg=new BigDecimal("0");
        // 判断非空
        if (!emptyJudge(str)) {
            return bg;
        }
        // 转换类型
        try {
            bg = new BigDecimal(str);
        } catch (Exception e) {
        }
        return bg;
    }

    /**
     * Description: 转换为Double类型<br>
     * 
     * @param str
     * @return long
     */
    public static double convToDouble(String str, double def) {
        // 最终结果
        double res = def;
        // 判断非空
        if (!emptyJudge(str)) {
            return res;
        }
        // 转换类型
        try {
            res = Double.parseDouble(str);
        } catch (Exception e) {
            res = Constants.DOUBLE_ZERO;
        }
        return res;
    }

    /**
     * Description:转换为日期类型 <br>
     * 
     * @param str
     * @return Date
     */
    public static Date convToDate(String str) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {

        }
        return date;
    }

    /**
     * Description: 转换为时间类型<br>
     * 
     * @param str
     * @return Date
     */
    public static Date convToDateTime(String str) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {}
        return date;
    }

    /**
     * Description: 转换开始时间<br>
     * 
     * @param str
     * @return String
     */
    public static String convStartDateTime(String str) {
        Date date = convToDate(str);
        if (null == date) {
            return str;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date) + " 00:00:00";
    }

    /**
     * Description: 转换结束时间<br>
     * 
     * @param str
     * @return String
     */
    public static String convEndDateTime(String str) {
        Date date = convToDate(str);
        if (null == date) {
            return str;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date) + " 23:59:59";
    }

    /**
     * Description:转换为布尔类型 <br>
     * 
     * @param str
     * @return Boolean
     */
    public static Boolean convToBoolean(String str) {
        Boolean res = Constants.BOOL_FALSE;
        try {
            res = Boolean.parseBoolean(str);
        } catch (Exception e) {

        }
        return res;
    }

    /**
     * Description:转换为布尔类型 <br>
     * 
     * @param str
     * @return Boolean
     */
    public static Boolean convToBoolean(String str, boolean def) {
        Boolean res = def;
        try {
            res = Boolean.parseBoolean(str);
        } catch (Exception e) {

        }
        return res;
    }

    /**
     * 
     * Description: 去除html标签<br>
     * 
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

        Pattern p_script = Pattern.compile(regEx_script,
            Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签

        Pattern p_style = Pattern.compile(regEx_style,
            Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        return htmlStr.trim(); // 返回文本字符串
    }
    
    /**
     * 根据文件地址获取文件名
     * @param url
     * @return
     */
    public static String getFileNameByUrl(String url) {
    	if(!emptyJudge(url)) {
    		return null;
    	}
    	
    	int b = url.lastIndexOf("/");
    	int e = url.lastIndexOf(".");
    	
    	if(b < 0 || e < 0 || b >= e) {
    		return null;
    	}
    	
    	return url.substring(b + 1, e);
    }
    
    /**
     * 判断String集合是否为空，不为空返回True
     * @param strs
     * @return
     */
    public static boolean emptyStrArray(String[] strs) {
    	if(null == strs || strs.length == 0) {
    		return false;
    	}
    	return true;
    }


    /**
     * 根据项目Id截取16位长度的发标ID
     * @param id
     * @return
     */
    public static Long IdkeySub(Long id) {
        String str = id.toString();
        if(str.length() <= 16) {
            return id;
        }
        return convToLong(str.substring(0, 16));
    }
    
    /**
     * 获取收益天数
     */
    public static int getIncomeDays(int status,Date start_date,Date end_date,Date issue_time,int income_method) {
    	int incomeDays=0;
		if (status == 4) {
			incomeDays = DateUtil.daysBetween(new Date(), end_date);
			if(income_method == 1){
				incomeDays += 1;
			}else if(income_method == 2){
				incomeDays += 0;
			}

			return incomeDays;
		}else if(status == 5 && DateUtil.between(new Date(), end_date) >=0){
			incomeDays = DateUtil.daysBetween(new Date(), end_date);
			if(income_method == 1){
				incomeDays += 1;
			}else if(income_method == 2){
				incomeDays += 0;
			}
			return incomeDays;
		}else if(status == 3 || status == 11){
			if( DateUtil.between(new Date(), start_date) > 0){
				incomeDays = DateUtil.daysBetween(start_date, end_date);
			}else if(DateUtil.between(new Date(), start_date) <= 0){
				incomeDays = DateUtil.daysBetween(new Date(), end_date);
			}
			if(income_method == 1){
				incomeDays += 1;
			}else if(income_method == 2){
				incomeDays += 0;
			}
			return incomeDays;
		}else if(status == 31){
			incomeDays = DateUtil.daysBetween(issue_time, end_date);
			if(income_method == 1){
				incomeDays += 1;
			}else if(income_method == 2){
				incomeDays += 0;
			}
			return incomeDays;
		}else {

			return -1;
		}
	}
	/**
	 * @description 根据身份证获取生日 (格式：yyyyMMdd)
	 * @author Andrew
	 * @date_time 2018-03-17 12:43
	 * @param certificateNo
	 * @return java.lang.String
	 */
	public static String idCardNoToBirthDay(String certificateNo) {
        int birthYearSpan = 4;
        //如果是15位的证件号码
        if(certificateNo.length() == 15) {
            birthYearSpan = 2;
        }

        //出生日期
        String year = (birthYearSpan == 2 ? "19" : "") + certificateNo.substring(6, 6 + birthYearSpan);
        String month = certificateNo.substring(6 + birthYearSpan, 6 + birthYearSpan + 2);
        String day = certificateNo.substring(8 + birthYearSpan, 8 + birthYearSpan + 2);
        String birthday = year + month + day;
        return birthday;
    }
}
