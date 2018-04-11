package com.zdjf.util;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;






public final class DateUtil {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DateUtil.class);

    /**
     * 日期格式化对象
     */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    private static DateFormat dateFormat_yy = new SimpleDateFormat("yy-MM-dd");

    private static DateFormat dateFormat_input = new SimpleDateFormat("yyyyMMdd");
    
    private static DateFormat dateFormat_md = new SimpleDateFormat("MM月dd日");
    
    private static DateFormat dateFormat_ym = new SimpleDateFormat("yy年MM月");
    /**
     * 日期时间格式化对象
     */
    private static DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static DateFormat dateTimeFormatS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static DateFormat dateTimeFormat_input = new SimpleDateFormat("yyyyMMddHHmm");
    
    private static DateFormat dateTimeFormat_Str = new SimpleDateFormat("yyyyMMddHHmmss");
    /**
     * 时间格式化对象
     */
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");

	/**
     * 获取时间格式化对象 "yyyy-MM-dd"
     * @return
     */
    public static final DateFormat getDateFormat(){
        return dateFormat;
    }

    /**
     * 获取时间格式化对象 "yy-MM-dd"
     * @return
     */
    public static DateFormat getDateFormat_yy() {
		return dateFormat_yy;
	}

	/**
     * 日期输入格式采用"yyyyMMdd"
     * @return
     */
    public static final DateFormat getDateFormat_input(){
        return dateFormat_input;
    }

    /**
     * 获取时间日期格式化对象 "yyyy-MM-dd HH:mm"
     * @return
     */
    public static final DateFormat getDateTimeFormat(){
        return dateTimeFormat;
    }

    /**
     * 获取当前时间的时间对象
     * @return
     */
    public static final Date nowDate(){
        return new Date();
    }
    /**
     * 系统最小时间
     * @return
     */
    public static final Date minDate() {
        return dateBegin(getDate(1900,1,1));
    }
    /**
     * 系统最大时间
     * @return
     */
    public static final Date maxDate() {
        return dateEnd(getDate(2079,1,1));
    }
    /**
     * 获取指定时间的年
     * @param date
     * @return
     */
    public static final int year(Date date){
    	if(date==null)return 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 获取指定时间的月
     * @param date
     * @return
     */
    public static final int month(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH)+1;
    }
    /**
     * 获取指定时间的日
     * @param date
     * @return
     */
    public static final int day(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
    /**
     * 获取一个时间对象
     * @param year 格式为：2004
     * @param month 从1开始
     * @param date 从1开始
     * @return
     */
    public static final Date getDate(int year,int month,int date){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, date);
        return calendar.getTime();
    }

    /**
     * 获取一个时间对象
     * @param year 格式为：2004
     * @param month 从1开始
     * @param date 从1开始
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static final Date getDateTime(int year, int month, int date, int hour, int minute,
            int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, date,hour,minute,second);
        return calendar.getTime();
    }

    /**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     * @param oleDate
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static final Date addDate(Date oldDate, int year, int month, int date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, date);
        return calendar.getTime();
    }



    public static int constDateSub=-36500;
    
    
    /**
     * date2比date1多的天数
     * @param date1    
     * @param date2
     * @return    
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            //System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }


    /**
     * 返回两个时间相差的天数
     * @param a
     * @param b
     * @return
     */
    public static final int dateSub(Date a,Date b) {
    	if (a==null||b==null) {
			return constDateSub;
		} 
        int date = (int)(a.getTime()/(24*60*60*1000) - b.getTime()/(24*60*60*1000));
        return  date;
    }
    
    public static final int incomeDays(Date a,Date b){
    	return differentDays(b,a) + 1;
    }

    public static final int dateSubAddOne(Date a,Date b) {
        int date = (int)(a.getTime()/(24*60*60*1000) - b.getTime()/(24*60*60*1000));
        return date<=0?1:date+1;
    }

    public static final boolean isBetweenDateS(Date beginDate,Date nowDate,Date endDate){
    	if(beginDate!=null&&nowDate!=null&&endDate!=null){
    		if((beginDate.getTime()/(24*60*60*1000))<=(nowDate.getTime()/(24*60*60*1000))&&(nowDate.getTime()/(24*60*60*1000))<=(endDate.getTime()/(24*60*60*1000))){
    			return true;
    		}
    	}else
    	if(beginDate!=null&&nowDate!=null&&endDate==null){
    		if((beginDate.getTime()/(24*60*60*1000))<=(nowDate.getTime()/(24*60*60*1000))){
    			return true;
    		}
    	}else
    	if(beginDate==null&&nowDate!=null&&endDate!=null){
    		if((nowDate.getTime()/(24*60*60*1000))<=(endDate.getTime()/(24*60*60*1000))){
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * 返回两个时间相差多少分钟
     * @param a
     * @param b
     * @return
     */
    public static final int subSecond(Date a,Date b) {
        return (int)(a.getTime()/(1000) - b.getTime()/(1000));
    }
    public static final int subSecond(String str,Date b){
        Date a = null;
        try {
            a = timeFormat.parse(str);
        } catch (ParseException e) {

            return 0;
        }
        return (int)((a.getTime()%(24*60*60*1000))/1000-(b.getTime()%(24*60*60*1000))/1000);
    }
    /**
     * 一天的开始时间
     * @param date
     * @return
     */
    public static final Date dateBegin(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateBegin(calendar);
        return calendar.getTime();
    }
    /**
     * 一天的结束时间
     * @param date
     * @return
     */
    public static final Date dateEnd(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateEnd(calendar);
        return calendar.getTime();
    }
   
    /**
     * 一天的结束时间
     * @param calendar
     * @return
     */
    public static final Calendar dateEnd(Calendar calendar) {
        if(calendar == null)
            return null;
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
    /**
     * 一天的开始时间
     * @param calendar
     * @return
     */
    public static final Calendar dateBegin(Calendar calendar) {
        if(calendar == null)
            return null;
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
    /**
     * 一月的开始时间
     * @param date
     * @return
     */
    public static final Date monthBegin(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateBegin(calendar);
        return calendar.getTime();
    }
    /**
     * 一月的结束时间
     * @param date
     * @return
     */
    public static final Date monthEnd(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    }
    /**
     * 一年的开始时间
     * @param date
     * @return
     */
    public static final Date yearBegin(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, month);
        dateBegin(calendar);
        return calendar.getTime();
    	//return  parseDate(formatDate(date).substring(0,4)+"-01-01");
    }
    
    /**
     * 一年的结束时间
     * @param date
     * @return
     */
    public static final Date yearEnd(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    	//return  parseDate(formatDate(date).substring(0,4)+"-12-31");
    }
    /**
     * 从字符串转换为date
     * 默认格式为 "yyyy-MM-dd"
     * @param source
     * @return
     */
    public static final Date parseDate(String source){
        if(source==null || source.trim().length()==0)
            return null;
        Date returnDate;
        if(source.trim().length()==8)
        try {
			returnDate = dateFormat_input.parse(source);
        } catch (ParseException e) {
			logger.error("DateUtil parseDate error", e);
            return null;
        }

        try {
			returnDate = dateFormat.parse(source);
        } catch (ParseException e) {
			logger.error("DateUtil parseDate error", e);
            return null;
        }
        
        return returnDate;
    }
    

    /**
     * 从字符串转换为date
     * 默认格式为 "yyyy-MM-dd HH:mm"
     * @param source
     * @return
     */
    public static final Date parseDateTime(String source){
        if(source==null || source.length()==0)
            return null;
        try {
            return dateTimeFormat.parse(source);
        } catch (ParseException e) {
        	logger.error("DateUtil parseDate error", e);
            return null;
        }
    }
    
    /**
     * 从字符串转换为date
     * 默认格式为 "yyyy-MM-dd HH:mm:ss"
     * @param source
     * @return
     */
    public static final Date parseDateTimes(String source){
        if(source==null ||source.equals("") || source.length()==0)
            return null;
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(source);
        } catch (ParseException e) {
        	logger.error("DateUtil parseDate error", e);
        }
        return null;
    }
    
    /**
     * 从字符串转换为date
     * 自定义格式转换  (如type 格式为 "yyyy/MM/dd HH:mm:ss")
     * @param source
     * @return
     */
    public static final Date parseDateTimes(String source,String type){
        if(source==null ||source.equals("") || source.length()==0)
            return null;
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat(type);
            return sdf.parse(source);
        } catch (ParseException e) {
        	logger.error("DateUtil parseDate error", e);
        }
        return null;
    }
    
    /**
     * 从字符串转换为date
     * 默认格式为 "yyyyMMddHHmmss"
     * @param source
     * @return
     */
    public static final Date parseDateTimesToss(String source){
        if(source==null ||source.equals("") || source.length()==0)
            return null;
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            return sdf.parse(source);
        } catch (ParseException e) {
        	logger.error("DateUtil parseDate error", e);
        }
        return null;
    }
    
    /**
     * 格式化输出（只读的时候）
     * 默认格式为 "yyyy-MM-dd"
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        if(date == null)return "";
        return dateFormat.format(date);
    }
    
    public static String formatDateMd(Date date){
        if(date == null)return "";
        return dateFormat_md.format(date);
    }
    
    /**
     * 输入yy-mm
     * @param date
     * @return
     */
    public static String formatDateYm(Date date){
        if(date == null)return "";
        return dateFormat_ym.format(date);
    }
    /**
     * 格式化输出（只读的时候）
     * 默认格式为 "yy-MM-dd"
     * @param date
     * @return
     */
    public static String formatDateYY(Date date){
    	if(date == null)return "";
    	return dateFormat_yy.format(date);
    }
    /**
     * 格式化输出显示（填写的时候） yyyyMMdd
     * @param date
     * @return
     */
    public static String formatDate_input(Date date){
        if(date == null)return "";
        return dateFormat_input.format(date);
    }

    /**
     * 格式化输出
     * 默认格式为 "yyyy-MM-dd HH:mm"
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        if(date == null)return "";
        return dateTimeFormat.format(date);
    }
    
    /**
     * 格式化输出
     * 默认格式为 "yyyy-MM-dd HH:mm:ss"
     * @param date
     * @return
     */
    public static String formatDateTimeS(Date date){
        if(date == null)return "";
        return dateTimeFormatS.format(date);
    }

    /**
     * 格式化输出
     * 默认格式为 "yyyy-MM-dd HH:mm"
     * @param date
     * @return
     */
    public static String getDateTime(Date date){
        if(date == null)return "";
        return dateTimeFormat.format(date).substring(5,10).replaceAll("_", "/");
    }
    
    public static String formatDateTime_input(Date date){
        if(date == null)return "";
        return dateTimeFormat_input.format(date);
    }
    
    public static String formatDateTime_Str(Date date){
        if(null == date) return StringUtils.EMPTY;
        return dateTimeFormat_Str.format(date);
    }
    /**
     * 判断是否是闰年
     * @param yearInt
     * @return
     */
    public static boolean isLeapYear(int yearInt){
    	boolean flag = false;
		if(((yearInt%4==0)&&(yearInt%100!=0))||((yearInt%4==0)&&(yearInt%400==0))){
			return true;
		}
    	return flag;
    }

	/**
	 * 在指定的时间内增加天数。负数表示为减
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date,int days){
		Date newdate=new Date();
		long newtimems=date.getTime()+((long)days*24*60*60*1000);
		newdate.setTime(newtimems);
		return newdate;
	}


    /**
     * 根据日期判断今天 昨天 前天3个时间段，如果不是返回String类型
     * @param date
     * @return
     */
    public static String cnDate(Date date){
    	if(date ==null){
    		return "";
    	}
    	Date newdate=new Date();
    	Long newTimes = newdate.getTime();
    	Long cdTimes = date.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    	String dateStr1 = DateUtil.formatDate(DateUtil.addDays(newdate, -1))+ " 23:59:59";
    	String dateStr2 = DateUtil.formatDate(DateUtil.addDays(newdate, -2))+ " 23:59:59";
    	String dateStr3 = DateUtil.formatDate(DateUtil.addDays(newdate, -3))+ " 23:59:59";
    	Date date1 = DateUtil.parseDateTimes(dateStr1);
    	Date date2 = DateUtil.parseDateTimes(dateStr2);
    	Date date3 = DateUtil.parseDateTimes(dateStr3);
    	if(newTimes >= cdTimes && cdTimes>date1.getTime() ){
    		return "今天 "+sdf.format(date);
    	}else if(cdTimes<date1.getTime() && cdTimes>date2.getTime()){
    		return "昨天 "+sdf.format(date);
    	}else if(cdTimes<date2.getTime() && cdTimes>date3.getTime()){
    		return "前天 "+sdf.format(date);
    	}else
    	
    	return DateUtil.formatDateTime(date);
    }


    /**
     * 判断8位的日期的字符串是否为正确的日期字符串
     * @param dateString
     * @return 不是正确的日期字符串返回true
     */
    public static boolean isErrorFormatDateString(String dateString){
    	boolean flag =false;
    	String yearString = "";
    	String monthString = "";
    	String dayString = "";
    	if(dateString.length()==8){
    		yearString = dateString.substring(0,4);
        	monthString = dateString.substring(4,6);
        	dayString = dateString.substring(6,8);
    	}else{
    		return true;
    	}
    	int yearInt = Integer.parseInt(yearString);
    	int monthInt = Integer.parseInt(monthString);
    	int dayInt = Integer.parseInt(dayString);
    	if(DateUtil.year(DateUtil.nowDate())!=yearInt){
    		return true;
    	}
    	if(monthInt>0&&monthInt<12){
    		switch (monthInt) {
			case 1:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 2:
				if(isLeapYear(yearInt)){
					if(dayInt>29||dayInt<1)flag=true;
				}else{
					if(dayInt>28||dayInt<1)flag=true;
				}
				break;
			case 3:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 4:
				if(dayInt>30||dayInt<1)flag=true;
				break;
			case 5:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 6:
				if(dayInt>30||dayInt<1)flag=true;
				break;
			case 7:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 8:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 9:
				if(dayInt>30||dayInt<1)flag=true;
				break;
			case 10:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 11:
				if(dayInt>30||dayInt<1)flag=true;
				break;
			case 12:
				if(dayInt>31||dayInt<1)flag=true;
				break;

			default:break;
			}
    	}else{
    		flag=true;
    	}
    	return flag;
    }
    
    /**
     * 两个Double数相除，并保留scale位小数
     * @param v1
     * @param v2
     * @param scale
     * @return Double
     */
    public static Double div(Double v1,Double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
            "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 保留double scale位小数
     * @param v
     * @param scale
     * @return
     */
    public static double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static String biliRound(String shuzi,String shuzi2){
    	
    	if(shuzi!=null && !"".equals(shuzi) && shuzi2!=null && !"".equals(shuzi2)){
    		double  number1 = Double .valueOf(shuzi);
    		double number2 = Double .valueOf(shuzi2);
    		if(number1==0 || number2==0){
    			return "";
    		}else{
    			Double bili = round((number1-number2)/number2*100,2);
    			return bili.toString();
    		}
    	}
    	return "";
    }
    
    
    public static String round(String str){
    	if(str!=null && str.indexOf(".")!=-1){
        	int i = str.indexOf(".");
        	if(str.length()-i>2){
        		return str.substring(0, i+3);
        	}else if(str.length()-i>1){
        		str = str +"0";
        	}
    	}else if(str==null){
    		return "0.00";
    	}else{
    		str = str +".00";
    	}
    	return str;
    }

	
	/**
     * 在一个已知时间的基础上增加指定的时间,负数表示减少
     * @param oleDate
     * @param year
     * @param month
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static final Date addDate(Date oldDate, int year, int month, int date,int hour,int minute,int second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, date);
        calendar.add(Calendar.HOUR_OF_DAY,hour);
        calendar.add(Calendar.MINUTE,minute);
        calendar.add(Calendar.SECOND,second);
        return calendar.getTime();
    }
    
    /**
     * 返回“yyyy-MM”格式的String日期
     * @param d “yyyy-MM”
     * @return
     */
    public static String toDateStr(java.util.Date d){
		if (d == null) {
			return "";
		} else {
			return (new SimpleDateFormat("yyyy-MM")).format(d);
		}
	}
    
    public static String formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
    /**
     * 从字符串转换为date
     * 默认格式为 "yyyyMMdd HH:mm:ss"
     * @param source
     * @return
     */
    public static final Date parseDateStr(String source){
        if(source==null ||source.equals("") || source.length()==0)
            return null;
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            return sdf.parse(source);
        } catch (ParseException e) {
        	logger.error("DateUtil parseDate error", e);
        }
        return null;
    }
    
    /**
     * String 转 Date
     * @return
     */
    public static Date StingToDate(String s,String format){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
		try {
			date = sdf.parse(s);
		} catch (ParseException e) {
			logger.error("时间转换错误", e);
		}
    	return date;
    }
    
    /**
     * Date 转 String
     * @param format
     * @return
     */
    public static String DateToString(Date date,String format){
    	SimpleDateFormat sdf=new SimpleDateFormat(format);
    	String str=sdf.format(date);
    	return str;
    }
    
    /**
     * 根据当前时间，获取本周的开始时间
     */
    public static String getWeekStart(){
    	Calendar cal = Calendar.getInstance();
    	int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
		cal.add(Calendar.DATE, -day_of_week);
		String t = DateToString(cal.getTime(),"yyyy-MM-dd");
		return t;
    }
    
    /**
     * 根据当前时间，获取本周的结束时间
     */
    public static String getWeekEnd(){
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DATE, 6);
		String t = DateToString(cal.getTime(),"yyyy-MM-dd");
		return t;
    }
    
    /**
     * 判断当前日期是星期
     * @param pTime
     * @param format
     * @return
     * @throws Exception
     */
	public static int dayForWeek(String pTime,String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	
	/**
	 * 比较时间大小
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean compare_date(String d1,String d2){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = df.parse(d1);
			Date date2 = df.parse(d2);
			if(date1.getTime() > date2.getTime()){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取当前时间一个月前的时间
	 */
	public static Date getLastMonth(String ptime,String format,int month){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(ptime));
			c.add(Calendar.MONTH, month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c.getTime();
	}
	
	/**
	 * 获取当前时间一年前的时间
	 */
	public static Date getLastYear(String ptime, String format, int year) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(ptime));
			c.add(Calendar.YEAR, year);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c.getTime();
	}
	
	/**
	 * 根据时间，返回季度值，3-1季度，6-2季度，9-3季度，12-4季度
	 * @return
	 */
	public static String JudgeSeason(String date){
		if(StringUtils.isNotBlank(date)){
			String m = date.substring(5, 7);
			if(m.equals("03")){
				return "1";
			}else if(m.equals("06")){
				return "2";
			}else if(m.equals("09")){
				return "3";
			}else if(m.equals("12")){
				return "4";
			}
		}
		return "";
	}
	
	/**
	 * 获取指定上周周几的时间
	 * @param oldDate
	 * @param add_day
	 * @return
	 */
	public static final Date addDate(Date oldDate,int add_day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.DATE, add_day);
        return calendar.getTime();
    }
	
	/**
	 * 返回当前时间的 day的值
	 * @param date
	 * @return
	 */
	public static final int getDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取指定上个月的时间
	 * @param oldDate
	 * @param add_day
	 * @return
	 */
	public static final Date addMonth(Date oldDate,int month_day,int add_day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.DAY_OF_MONTH, add_day);
        calendar.add(Calendar.MONTH, month_day);
        return calendar.getTime();
    }
	
	/**
	 * 得到小数点后2位小数
	 * @param obj
	 * @return
	 */
	public static final Object setTwoRound(Object obj){
		if(obj == null){
			return "";
		}else if("".equals(obj.toString())){
			return "";
		}		
		BigDecimal bd = new BigDecimal(obj.toString());
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	
	
	public static Date dateAdd(Date beforDate, int unit, int value) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beforDate);
		
		switch (unit) {
			case 1://天
				calendar.add(Calendar.DATE, value);
				break;
			case 2://月
				calendar.add(Calendar.MONTH, value);
				break;
			case 3://年
				calendar.add(Calendar.YEAR, value);
				break;
			default:
				break;
		}
		
		return calendar.getTime();
	}
	  /**
     * 日期转yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String toShortString(Date date) {
        if(null == date) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 日期转yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String toLongString(Date date) {
        if(null == date) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**  
     * 计算两个日期之间相差的天数  
     * 
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差数 
     */
    public static int daysBetween(Date smdate, Date bdate) {
        // 格式化日期对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 格式化日期一
            smdate = sdf.parse(sdf.format(smdate));
            // 格式化日期二
            bdate = sdf.parse(sdf.format(bdate));
        } catch (Exception e) {
            return Constants.NUM_NEG_ONE;
        }
        // Calendar对象实例化
        Calendar cal = Calendar.getInstance();
        // 设定日期一
        cal.setTime(smdate);
        // 获得日期一的milliseconds
        long time1 = cal.getTimeInMillis();
        // 设定日期二
        cal.setTime(bdate);
        // 获得日期二的milliseconds
        long time2 = cal.getTimeInMillis();
        // 日期相减获得相差天数
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        // 转换为int值
        return Math.abs(Integer.parseInt(String.valueOf(between_days)));
    }

    /**
     * Description: 计算日期相差月数<br>
     * 
     * @param smdate
     * @param bdate
     * @return int
     */
    public static int monthsBetween(Date smdate, Date bdate) {
        // 格式化日期对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 格式化日期一
            smdate = sdf.parse(sdf.format(smdate));
            // 格式化日期二
            bdate = sdf.parse(sdf.format(bdate));
        } catch (Exception e) {
            return Constants.NUM_NEG_ONE;
        }
        // Calendar对象实例化
        Calendar cal = Calendar.getInstance();
        // 设定日期一
        cal.setTime(smdate);
        // 获得日期一的月数
        int month1 = cal.get(Calendar.MONTH);
        // 设定日期二
        cal.setTime(bdate);
        // 获得日期二的月数
        int month2 = cal.get(Calendar.MONTH);
        // 日期相减获得相差天数
        int between_months = month2 - month1;
        // 取得绝对值
        return Math.abs(between_months);
    }

    /**
     * Description: 计算日期相差年数<br>
     * 
     * @param smdate
     * @param bdate
     * @return int
     */
    public static int yearsBetween(Date smdate, Date bdate) {
        // 格式化日期对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 格式化日期一
            smdate = sdf.parse(sdf.format(smdate));
            // 格式化日期二
            bdate = sdf.parse(sdf.format(bdate));
        } catch (Exception e) {
            return Constants.NUM_NEG_ONE;
        }
        // Calendar对象实例化
        Calendar cal = Calendar.getInstance();
        // 设定日期一
        cal.setTime(smdate);
        // 获得日期一的年数
        int year1 = cal.get(Calendar.YEAR);
        // 设定日期二
        cal.setTime(bdate);
        // 获得日期二的年数
        int year2 = cal.get(Calendar.YEAR);
        // 日期相减获得相差天数
        int between_years = year2 - year1;
        // 取得绝对值
        return Math.abs(between_years);
    }

    /**
     * 
     * Description: 指定日期加或减days天 <br>
     * 
     * @param date
     * @param days
     * @return
     */
    public static Date addDay(Date date, int days) {
        // 获取对象
        Calendar calendar = Calendar.getInstance();
        // 初始化
        calendar.setTime(date);
        // 加自然日
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 
     * Description: 指定日期加或减months月<br>
     * 
     * @param date
     * @param months
     * @return
     */
    public static Date addMonth(Date date, int months) {
        // 获取对象
        Calendar calendar = Calendar.getInstance();
        // 初始化
        calendar.setTime(date);
        // 加自然月
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /**
     * 
     * Description: 指定日期加或减years年 <br>
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date addYear(Date date, int years) {
        // 获取对象
        Calendar calendar = Calendar.getInstance();
        // 初始化
        calendar.setTime(date);
        // 加自然年
        calendar.add(Calendar.YEAR, years);
        return calendar.getTime();
    }
    
    
    public static Long secondsBetween(Date smdate, Date bdate) {
       if(smdate == null) return null;
       if(bdate == null) return null;
       return (bdate.getTime() - smdate.getTime())/1000;
    }
    
    public static String unitFormat(Long i) {  
        String retStr = null;  
        if (i >= 0 && i < 10)  
            retStr = "0" + Long.toString(i);  
        else  
            retStr = "" + i;  
        return retStr;  
    }  
	  
	public static String secToTime(Long time) {  
        String timeStr = null;  
        Long hour = 0L;  
        Long minute = 0L;  
        Long second = 0L;  
        if (time <= 0)  
            return "00:00";  
        else {  
            minute = time / 60;  
            if (minute < 60) {  
                second = time % 60;  
                timeStr = unitFormat(minute) + ":" + unitFormat(second);  
            } else {  
                hour = minute / 60;  
                if (hour > 99)  
                    return "99:59:59";  
                minute = minute % 60;  
                second = time - hour * 3600 - minute * 60;  
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);  
            }  
        }  
        return timeStr;  
    }

    public static int between(Date startDate, Date endDate ) {
        // 格式化日期对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 格式化日期一
            startDate = sdf.parse(sdf.format(startDate));
            // 格式化日期二
            endDate = sdf.parse(sdf.format(endDate));
        } catch (Exception e) {
            return 0;
        }
        // Calendar对象实例化
        Calendar cal = Calendar.getInstance();
        // 设定日期一
        cal.setTime(startDate);
        // 获得日期一的milliseconds
        long time1 = cal.getTimeInMillis();
        // 设定日期二
        cal.setTime(endDate);
        // 获得日期二的milliseconds
        long time2 = cal.getTimeInMillis();
        // 日期相减获得相差天数
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        // 转换为int值，因需包含当天在内的天数，所以最终返回结果+1
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 判断第一个时间是否小于第二个时间
     * @param firstDate 第一个时间
     * @param secondDate 第二个时间
     * @return 第一个时间小于第二个时间，返回true，否则返回false
     */
    public static boolean company(Date firstDate, Date secondDate) {
        Long num = firstDate.getTime() - secondDate.getTime();
        if(num >= 0) {
            return false;
        }
        return true;
    }

    /**
     * 将指定格式的字符串转换为时间
     * @param str 需要转换的字符串，必须满足yyyy-MM-dd HH:mm:ss的格式
     * @return 返回对应的时间
     */
    public static Date convert(String str){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(str);
        } catch (Exception e){
            return null;
        }
    }

    /**
     * 将指定格式的字符串转换为时间
     * @param str 需要转换的字符串，必须满足yyyy-MM-dd 的格式
     * @param num
     * @return 返回对应的时间
     */
    public static Date convert(String str,int num){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(str);
        } catch (Exception e){
            return null;
        }
    }

    /**
     *
     * @Title: remindDate
     * @Description:(这里用一句话描述这个方法的作用)
     * @param status
     *            流水状态
     * @param updateTime
     *            修改时间
     * @return String 返回类型
     * @throws
     */
    public static Date remindDate(int status, Date updateTime) {
        int addDay = 1;
        int weekday = getWeekOfDate(updateTime);
        int hour = updateTime.getHours();
        if (status == 3) {// 待审核
            if ((weekday == 5 && hour >= 18) || weekday == 6 || weekday == 0) {
                if (weekday == 5) {
                    addDay = 4;
                }
                if (weekday == 6) {
                    addDay = 3;
                }
                if (weekday == 0) {
                    addDay = 2;
                }
            } else if (hour < 18) {
                if (weekday == 5) {
                    addDay = 3;
                } else {
                    addDay = 1;
                }
            } else {
                if (weekday == 4) {
                    addDay = 4;
                } else {
                    addDay = 2;
                }
            }
        } else if (status == 5) {// 银行处理中
            switch (weekday) {
                case 5:
                    addDay = 3;
                    break;
                case 6:
                    addDay = 2;
                    break;
                default:
                    addDay = 1;

            }
        }
        return DateUtil.addDay(updateTime, addDay);
    }

    private static int getWeekOfDate(Date dt) {
        int[] weekDays = { 0, 1, 2, 3, 4, 5, 6 };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}

