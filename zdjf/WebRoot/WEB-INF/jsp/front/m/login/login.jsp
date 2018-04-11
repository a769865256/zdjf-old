<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui" />
<title>正道金服-发现</title>
<!-- 公共样式 -->
<link rel="stylesheet" href="<%=path%>/css/front/m/style.css" />
<link rel="stylesheet" href="<%=path%>/css/front/m/index.css" />
<!-- 公共样式end -->
<link rel="stylesheet" href="<%=path%>/css/front/m/login.css" />
<style id="_zoom"></style>
</head>
<body class="zoom">
	<div class="login">
		<div class="header">
			<a class="back" href="javascript:history.go(-1)"></a>登录<a
				href="<%=path %>/toRegister.action" class="register">注册</a>
		</div>
		<div class="l_box">
			<div class="zdlogo">
				<img src="<%=path%>/images/front/m/login/login.png" alt="">
			</div>
			<div class="error"><%=errorMsg != null ? errorMsg : ""%></div>
			<div class="formbox">
				<form id="login" action="<%=path%>/login.action" method="post">
					<ul>
						<li class="phone"><input type="text" class="ph_num" name="user_name" id="user_name"
							placeholder="输入手机号码"></li>
						<!-- 输入错误三次之后显示该区域 控制显示隐藏的class类名：hide -->
						<li class="img hide"><input type="text" class="ph_num" 
							placeholder="输入图片验证码"> <span class="cord">HLR9</span></li>
						<li class="pass"><input type="password" class="ph_num" name="passwd" id="passwd"
							placeholder="请输入登录密码"></li>
					</ul>
					<p>
						<a href="<%=path%>/toBack.action" class="forget">忘记密码</a>
					</p>
				</form>
			</div>
			<div class="l_btn">
				<a href="javascript:" class="active">登录</a>
				<p>
					<img src="<%=path%>/images/front/m/login/btntxt.png" alt="">上海银行资金监管
				</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=path%>/js/front/m/zoom.js"></script>
	<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
	<script type="text/javascript" src="<%=path%>/js/front/m/login.js"></script>
	<script type="text/javascript">
		$('.active').click(function(){
			$("#login").submit();
		});
	</script>
</body>
</html>

