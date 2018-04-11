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
<div class="capitalbox we_rtbox">
	<div class="capital">
		<div class="tlt">
			<h3>资金明细</h3>
		</div>
		<div class="time">
			选择日期: <input type="text" placeholder="开始时间" class="layui-input"
				id="from" placeholder=""> 到 <input type="text"
				placeholder="结束时间" class="layui-input" id="to" placeholder="">
		</div>
	</div>
	<div class="ca_chiose">
		<ul>
			<li class="chiose_date"><span>交易日期:</span> <a href="javascript:" class="active">全部</a>
				<a href="javascript:">最近7天</a> <a href="javascript:">1个月</a> <a
				href="javascript:">3个月</a> <a href="javascript:">半年以上</a></li>
			<li class="chiose_type"><span>交易类型:</span> <a href="javascript:" class="active">全部</a>
				<a href="javascript:">充值</a> <a href="javascript:">提现</a> <a
				href="javascript:">投资</a> <a href="javascript:">回款</a> <a
				href="javascript:">利息</a></li>
		</ul>
	</div>
	<div class="ca_table">
		<table>
			<thead>
				<tr>
					<th>日期</th>
					<th>流水号</th>
					<th>类型</th>
					<th>金额(元)</th>
					<th>可用余额(元)</th>
					<th>状态</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="list" items="${content.dataList }">
			<tr>
					<td>${list.create_time }</td>
					<td>${list.id }</td>
					<td>${list.operate_type }</td>
					<td>+${list.amount }</td>
					<td>${list.balance }</td>
					<td><span class="no">${list.status }</span></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="ca_page">
			<a href="javascript:">首页</a> <i>《</i> <span class="active">1</span> <span>2</span>
			<i>》</i> <a href="javascript:">尾页</a>
		</div>
	</div>
	</div>
	<script src="<%=path%>/js/front/wealth.js"></script>
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>
	<script src="<%=path%>/module/echarts/echarts.min.js"></script>
</body>