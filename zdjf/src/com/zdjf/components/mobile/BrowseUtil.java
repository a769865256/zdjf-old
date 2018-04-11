package com.zdjf.components.mobile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



//与浏览器  缓存有关系  
public class BrowseUtil {
	
	
	public static String getCookie(HttpServletRequest request,HttpServletResponse response){
		
		Cookie[] cookies = request.getCookies();
        String user_name = request.getParameter("user_name");
        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies!=null) {
            //out.write("您上次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("user_name")) {
                	cookie.setMaxAge(30*60);
                	user_name=cookie.getValue();
                	return user_name;
                }
            }
        }
        
		
		
		return user_name;
	}
	
	
	public static String getSession(HttpServletRequest request,String name){
		//存入会话session 
        HttpSession session = request.getSession(true); 
        //获取
        String user_name = (String) session.getAttribute(name);
		return user_name;
	}
	
	public static String createSessionPhone(HttpServletRequest request,String name){
		//存入会话session 
        HttpSession session = request.getSession(true); 
        //获取
        if(session.getAttribute("phone") == null){
        	session.setAttribute("phone", name);
        }
        session.removeAttribute("phone");
	    session.setAttribute("phone", name);
        
		return name;
	}
	
	public static String getSessionPhone(HttpServletRequest request){
		//存入会话session 
	    HttpSession session = request.getSession(true);
	    Object phone = session.getAttribute("phone");
	    
	    String trade_no = "";
	    if(phone != null)
	    	trade_no = phone.toString();
	    
	    return trade_no;
	}
	
	
	public static String createSessionToken(HttpServletRequest request){
		//存入会话session 
	    HttpSession session = request.getSession(true); 
	    String trade_no = UniqueUtil.getTradeNo();
	    if(session.getAttribute("token") == null){
	    	session.setAttribute("token", trade_no);
	    }
	    session.removeAttribute("token");
	    session.setAttribute("token", trade_no);
	    
	    return trade_no;
	}
	
	
	public static String getSessionToken(HttpServletRequest request){
		//存入会话session 
	    HttpSession session = request.getSession(true);
	    Object token = session.getAttribute("token");
	    
	    String trade_no = "";
	    if(token != null)
	    	trade_no = token.toString();
	    
	    return trade_no;
	}
	
	


}
