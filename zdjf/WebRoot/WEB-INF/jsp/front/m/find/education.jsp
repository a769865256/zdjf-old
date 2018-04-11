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
    <title>正道金服</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/css/front/m/style.css"/>
    <link rel="stylesheet" href="<%=path%>/css/front/m/index.css"/>
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path%>/css/front/m/find.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<div class="find">
    <div class="header"><a class="back" href="<%=path%>/appStatic/about.action?isBack=1"></a>投资教育</div>
	<div class="f_edu">
		<img src="<%=path%>/images/front/m/educa.png" alt="">
	</div>
</div>
<script type="text/javascript" src="<%=path%>/js/front/m/zoom.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/find.js"></script>
</body>
</html>

