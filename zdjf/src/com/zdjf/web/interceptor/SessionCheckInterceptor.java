package com.zdjf.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zdjf.components.mobile.BrowseUtil;
import com.zdjf.util.Constants;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter {
	
	Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String className = handler.toString();
		String url = request.getRequestURL().toString();
		
		if (StringUtils.isNotBlank(url) && StringUtils.isNotBlank(className)
				&& className.indexOf("com.zdjf.web.admin")!=-1) {
			if (request.getSession().getAttribute(Constants.USER_SESSION) == null
					&& url.lastIndexOf("check_user") == -1) {
				response.sendRedirect(request.getContextPath() + "/sysLogin/toLogin.action");
				return false;
			} else {
				return true;
			}
		}else{
			if(className.indexOf("com.zdjf.web.user")!=-1){   //判断用户是否登录，未登录状态下拦截。
				
				HttpSession session = request.getSession();
				Object sessionObject = session.getAttribute("CURRENT_LOGED_IN_USER");
				if (sessionObject != null ) {
					logger.debug("user_name in session: {}", sessionObject.toString());
				} else {
					logger.debug("user_name in session is null.");
					String user_name=BrowseUtil.getCookie(request, response);
					if(request.getCookies() != null){
						Cookie[] cookies=request.getCookies();
						for(int i=0;i<cookies.length;i++){
							Cookie cookie=cookies[i];
							if(cookie.getName().equals("user_name")){
								cookie=new Cookie("user_name",null);
								cookie.setMaxAge(0);
								response.addCookie(cookie);
							}
						}
					}
					response.sendRedirect(request.getContextPath() + "/toLogin.action");
					return false;
				}
				
				String user_name=BrowseUtil.getCookie(request, response);
				if(null==user_name || "".equals(user_name)  
						 ||  !sessionObject.toString().equalsIgnoreCase(user_name) // 既然cookie里面有用户名，那就和session里面对比一下
						){
					response.sendRedirect(request.getContextPath() + "/toLogin.action");
					return false;
				} else {
					logger.debug("用户{}登录检查成功", user_name);
				}
				
			}
			String appoint = request.getParameter("appoint");
			if(appoint!=null){
				return true;
			}else{
				String query = request.getQueryString();
				String domainName = request.getServerName(); //域名
				boolean isMoblie = JudgeIsMoblie(request);  
				
				String geturl = "";
				
//		        if(isMoblie){  
//		        	if(!domainName.equals("m.zdjf.com")){  //测试地址
//		        		geturl = request.getScheme()+"://"+ "m.zdjf.com:8080"+request.getRequestURI();  //测试地址
//		        		if(query!=null && !"".equals(query)){
//							geturl += "?"+query;
//						}
//		        		response.sendRedirect(geturl);
//		        		return false;
//		        	}
//		        }else{
//		        	if(domainName.equals("zdjf.com")){
//		        		geturl = request.getScheme()+"://"+ "www.zdjf.com:8080"+request.getRequestURI();  //测试地址
//		        		if(query!=null && !"".equals(query)){
//							geturl += "?"+query;
//						}
//		        		response.sendRedirect(geturl);
//		        		return false;
//		        	}
//		        }
			}
			
			
		}
		return true;
	}
	
	public boolean JudgeIsMoblie(HttpServletRequest request) {  
        boolean isMoblie = false;  
        String[] mobileAgents = { "iphone", "android", "phone", "mobile",  
                "wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",  
                "windows ce", "symbian", "series", "webos", "sony",  
                "blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",  
                "pieplus", "meizu", "midp", "cldc", "motorola", "foma",  
                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin",  
                "huawei", "novarra", "coolpad", "webos", "techfaith",  
                "palmsource", "alcatel", "amoi", "ktouch", "nexian",  
                "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",  
                "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",  
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop",  
                "benq", "haier", "^lct", "320x320", "240x320", "176x220",  
                "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",  
                "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",  
                "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",  
                "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",  
                "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",  
                "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",  
                "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",  
                "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",  
                "sony", "sph-", "symb", "t-mo", "teli", "tim-", /*"tosh",*/ "tsm-",  
                "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",  
                "wapr", "webc", "winw", "winw", "xda", "xda-",  
                "Googlebot-Mobile" };  
        if (request.getHeader("User-Agent") != null) {  
            for (String mobileAgent : mobileAgents) {  
                //<span class="comment" style="margin: 0px; padding: 0px; border: none; color: rgb(0, 130, 0); font-family: Consolas, 'Courier New', Courier, mono, serif; line-height: 18px;">//这里本宝宝表示不怎么了解它的内部原理，但是知道个大概意思就得了。</span>  
                if (request.getHeader("User-Agent").toLowerCase()  
                        .indexOf(mobileAgent) >= 0) {  
                    isMoblie = true;  
                    break;  
                }  
            }  
        }  
        return isMoblie;  
    }  
	
}
