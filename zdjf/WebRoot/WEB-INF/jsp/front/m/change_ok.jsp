<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui"/>
    <title>开通上海银行存管账户</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/css/front/m/style.css"/>
    <link rel="stylesheet" href="<%=path%>/css/front/m/index.css"/>
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path%>/css/front/m/sign.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<div class="depo_ok">
	<img src="<%=path%>/images/front/m/change_ok.png" alt="">
	<a href="javascript:" class="de_ok">确定</a>
</div>
<script type="text/javascript" src="<%=path%>/js/front/m/zoom.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
<script type="text/javascript">
	var reg_source='${reg_source}';
	$('.de_ok').click(function(){
		if(reg_source=="3"){
			window.android.close();
		}else if(reg_source=="2"){  
			 window.location.href="FixTradePwd"; 
		} 
	});
</script>
</body>
</html>

