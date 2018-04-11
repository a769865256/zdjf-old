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
<div class="invitation we_rtbox" >
	<h3>邀请好友</h3>
	<div class="friend">
		<div class="f_banner"></div>
		<div class="f_code">
			<ul>
				<li><span>我的邀请码:<i>13082015471</i></span></li>
				<li><span>分享给朋友:</span></li>
				<li><a href="javascript:" class="iconfont wx">&#xe62a;</a> <a
					href="javascript:" class="iconfont qq">&#xe617;</a> <a
					href="javascript:" class="iconfont qqkj">&#xe65e;</a> <a
					href="javascript:" class="iconfont xl">&#xe60e;</a></li>
			</ul>
		</div>
	</div>
	<div class="copy_link">
		<span>邀请链接:</span><input id="url" type="text"
			value="https://www.zdjfu.com/user/register?code=t3Hazd90"
			readonly="true"><a href="javascript:" class="copy_url_btn">复制链接</a>
		<p>
			共邀请 <i>0</i> 人注册，被邀请人累计投资 <i>0</i> 元，您获得正经值奖励 <i>0.00</i> 元
		</p>
	</div>
	<div class="friend_list">
		<h3>邀请好友列表</h3>
		<table>
			<thead>
				<tr>
					<th>用户名</th>
					<th>投资时间</th>
					<th>投资项目</th>
					<th>投资金额(元)</th>
					<th>贡献正经值</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>13083074845</th>
					<th>2017/8/11 14:00:00</th>
					<th>车财道12548-1期</th>
					<th>3,000.00</th>
					<th>100</th>
				</tr>
				<tr>
					<th>13083074845</th>
					<th>2017/8/11 14:00:00</th>
					<th>车财道12548-1期</th>
					<th>3,000.00</th>
					<th>100</th>
				</tr>
				<tr>
					<th>13083074845</th>
					<th>2017/8/11 14:00:00</th>
					<th>车财道12548-1期</th>
					<th>3,000.00</th>
					<th>100</th>
				</tr>
				<tr>
					<th>13083074845</th>
					<th>2017/8/11 14:00:00</th>
					<th>车财道12548-1期</th>
					<th>3,000.00</th>
					<th>100</th>
				</tr>
				<tr>
					<th>13083074845</th>
					<th>2017/8/11 14:00:00</th>
					<th>车财道12548-1期</th>
					<th>3,000.00</th>
					<th>100</th>
				</tr>
				<tr>
					<th>13083074845</th>
					<th>2017/8/11 14:00:00</th>
					<th>车财道12548-1期</th>
					<th>3,000.00</th>
					<th>100</th>
				</tr>
			</tbody>
		</table>
		<!-- 分页 -->
		<div id="friend_page"></div>
	</div>
	</div>
	<script src="<%=path%>/js/front/wealth.js"></script>
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>
</body>