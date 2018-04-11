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
<div class="account we_rtbox" >
	<div class="ac_banner"></div>
	<div class="ac_personal">
		<h3>个人信息</h3>
		<div class="perlist">
			手机号码
			<p>
				<span>${content.phone }</span><a href="javascript:">已注册</a>
			</p>
		</div>
		<div class="perlist">
			登录密码
			<p>
				<span><c:if test="${!empty content.user.passwd}">已设置</c:if></span><a
					href="javascript:">修改</a>
			</p>
		</div>
	</div>
	<div class="ac_personal">
		<h3>安全中心</h3>
		<div class="perlist">
			实名认证
			<p>
				<span><c:if
						test="${!empty content.user.real_name_auth and content.user.real_name_auth == 1}">已认证</c:if></span><a
					href="javascript:">查看</a>
			</p>
		</div>
		<div class="perlist">
			银行卡
			<p>
				<c:if test="${content.user.status>1 and content.user.status != 4}">
					<span> 已设置 </span>
					<a href="javascript:" class="go_nounbund_btn">解绑</a>
				</c:if>
				<c:if test="${content.user.status<1}">
					<span> 未设置 </span>
					<a href="javascript:" class="go_unbund_btn">绑定</a>
				</c:if>
				<!-- <a class="go_unbund_btn" href="javascript:">绑定</a> -->
			</p>
		</div>
		<div class="perlist">
			支付密码
			<p>
				<c:if test="${content.user.status>=3}">
					<span>已设置</span>
					<a href="javascript:">修改/找回</a>
				</c:if>
				<c:if test="${content.user.status<3}">
					<span>未设置</span>
					<a href="javascript:">设置</a>
				</c:if>
			</p>
		</div>
		<div class="perlist">
			风险测评
			<p>
				<span>已评估</span><a href="javascript:">查看</a>
			</p>
		</div>
	</div>
	</div>
	<script src="<%=path%>/js/front/wealth.js"></script>
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>
</body>