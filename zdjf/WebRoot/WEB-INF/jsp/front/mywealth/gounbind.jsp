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
<html lang="en">
<head>
<meta charset="UTF-8">
<title>正道金服</title>
<!-- reset/iconfont -->
<link rel="stylesheet" href="<%=path%>/css/front/reset.css">
<link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/css/front/index.css">
<link rel="stylesheet" href="<%=path%>/css/front/wealth.css">
</head>
<body>
	<div class="go_unbund" style="display: none;">
		<div class="go_unbund_head">
			<h3>银行卡设置</h3>
		</div>
		<div class="go_unbox">
			<ul>
				<li>
					<p>
						<img src="<%=path%>/images/front/img/wealth/bank1.png" alt="">
						<a href="unbund.html">解绑</a>
					</p>
					<p class="banknumber">6222 **** **** 2407</p>
					<p>
						<span>默认取现卡</span>
					</p>
				</li>
				<li><a href="javascript:" class="iconfont addbank">&#xe68b;</a>
				</li>
			</ul>
		</div>
	</div>
	<script src="<%=path%>/js/front/wealth.js"></script>
</body>