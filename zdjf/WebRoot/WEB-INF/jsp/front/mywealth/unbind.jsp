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
	<div class="unbund" style="display: none;">
		<div class="unbund_head">
			<h3>解绑银行卡</h3>
		</div>
		<div class="unbox">
			<ul>
				<li><span>银行卡信息:</span> <span class="bankimg"><img
						src="../../../../images/front/img/wealth/bank1.png" alt=""></span>
					<span class="banknum">6228480348197124277</span></li>
				<li><span>预留手机号:</span> <input type="text"
					placeholder="请输入银行卡预留手机号"></li>
				<li><span>动态验证码:</span> <input type="text" placeholder="请输入验证码">
					<a href="javascript:" class="obtain">获取验证码</a><span
					class="obtaintxt">验证码15分钟内有效，请注意查收</span></li>
				<li><a class="sub" href="javascript:">提交</a><a class="cancel"
					href="javascript:">取消</a></li>
			</ul>
		</div>
	</div>
</body>