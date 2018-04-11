<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${selfSite}/zdjf" />
	<meta charset="UTF-8">
	<title>登录-专业透明的互联网理财平台</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand"> 
	<meta name="viewport" content="width=1200"/>
	<link href="${selfSite}/zdjf/res/user/css/login.css" style="text/css" rel="stylesheet">
	<link href="${selfSite}/zdjf/res/comm/css/public.css" style="text/css" rel="stylesheet">
	<link href="${selfSite}/zdjf/res/comm/css/reset.css" style="text/css" rel="stylesheet">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>

<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>
	<div class="conert">
		<!-- 注册链接 -->
		<p class="reg">
			用户登录<span>没有账号？<a href="${selfSite}/zdjf/user/toRegister.action">立即注册</a></span>
		</p>
		<!-- 左边 -->
		<div class="left">
			<c:if test="${type == 1}">
				<div class="login-timeout"><i>-</i>登录超时，请重新登录</div>
			</c:if>
			<p class="input">
				<span class="name">手机号码：</span>
				<input type="text" id="phone" placeholder="请输入您的手机号" />
				<span class="tal-tip hide">请输入手机号码</span>
			</p>
			<p class="input">
				<span class="name">登录密码：</span>
				<input type="password" id="pass" placeholder="请输入登录密码" />
				<span class="pd-tip hide">请输入手机号码</span>
			</p>
			<p class="forget-btn">
				<a href="${selfSite}/zdjf/user/forgetPass" class="forget_pass">忘记密码？</a>
			</p>
			<p>
				<input type="button" value="立即登录" class="login_but" />
			</p>
		</div>
		<!-- 右边栏目 -->
		<div class="right">
			<img src="${selfSite}/zdjf/res/user/images/login/logpic.png?t=20160330" />
		</div>
	</div>

	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/user/js/login.js">
</script>
</html>
