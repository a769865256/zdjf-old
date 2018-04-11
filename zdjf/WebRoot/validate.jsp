<%@ page autoFlush="false"%>
<%@ page import="com.img.util.VerifyCode"%>
<%
	out.clear();
	new VerifyCode(request, response, 4);
%>