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
    <title>正道金服-公告详情</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/css/front/m/style.css"/>
    <link rel="stylesheet" href="<%=path%>/css/front/m/index.css"/>
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path%>/css/front/m/find.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<div class="find">
	<div class="re_detail">
		<div class="tlt">
			<h3>${notice.title }</h3>
			<p><fmt:formatDate value="${notice.create_time }" pattern="yyyy-MM-dd"/>
			</p>
		</div>
		<div class="txt">
			${notice.content }
		</div>
	</div>
</div>
<script>
	(function(doc, win) {
	var docEl = doc.documentElement;
	var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';
	var recalc = function() {
	var clientWidth = docEl.clientWidth;
	if(!clientWidth) return;
	if(clientWidth >= 640) {
	docEl.style.fontSize = '100px';
	} else {
	docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
	}
	};
	if(!doc.addEventListener) return;
	win.addEventListener(resizeEvt, recalc, false);
	doc.addEventListener('DOMContentLoaded', recalc, false);
	})(document, window);
</script>
</body>
</html>

