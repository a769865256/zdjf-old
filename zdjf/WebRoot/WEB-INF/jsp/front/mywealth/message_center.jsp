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
	<div class="message_center we_rtbox" >
		<h3>
			消息中心<span>7</span>
		</h3>
		<div class="mes_chiose">
			<label for=""><input type="radio" value="系统公告" name="message">系统公告</label>
			<label for=""><input type="radio" value="收益通知" name="message">收益通知</label>
			<a href="javascript:" class="mess_sign_btn">全部标记已读</a>
		</div>
		<div class="ms_list">
			<ul>
				<li><i class="iconfont">&#xe646;</i> <span class="txt">1道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
					<a href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
				<li><i class="iconfont">&#xe646;</i> <span class="txt">2道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
					<a href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
				<li><i class="iconfont">&#xe646;</i> <span class="txt">3道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
					<a href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
				<li><i class="iconfont">&#xe646;</i> <span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
					<a href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
				<li><i class="iconfont">&#xe646;</i> <span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
					<a href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
				<li><i class="iconfont">&#xe646;</i> <span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
					<a href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
				<li><i class="iconfont">&#xe646;</i> <span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
					<a href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
				<li class="no_active"><i class="iconfont">&#xe646;</i> <span
					class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span> <a
					href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
				<li class="no_active"><i class="iconfont">&#xe646;</i> <span
					class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span> <a
					href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
				<li class="no_active"><i class="iconfont">&#xe646;</i> <span
					class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span> <a
					href="javascript:">查看详情</a> <span class="time">2017-07-30
						10:31:00</span></li>
			</ul>
			<!-- 分页 -->
			<div id="messpagebtn"></div>
		</div>
	</div>
	<script src="<%=path%>/js/front/wealth.js"></script>
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>
</body>