<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>忘记密码-专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand"> 
	<meta name="viewport" content="width=1200"/>  
	<link href="${selfSite}/zdjf/res/user/css/forgetPass.css" style="text/css" rel="stylesheet">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
  	<jsp:include page="../comm/header.jsp"></jsp:include>
	<div class="conert">
		<p class="reg">
			忘记密码<span>已有账号？<a href="${selfSite}/zdjf/user/login">返回登录</a></span>
		</p>
		<div class="phone">
			<p>
				<span class="tel-span">手机号码：</span>
				<input type="text" id="phone" placeholder="请输入手机号"  />
				<span class="tel"></span>
			</p>
			<p>
				<span class="tel-span">手机验证码：</span>
				<input type="text" id="codetext" placeholder="请输入验证码" />
				<span id="getcode" class="getcode">获取验证码</span>
				<span class="tel"></span>
			</p>
			<p>
				<input type="button" value="下一步" />
			</p>
		</div>
		<div class="pass hide">
			<p>
				<span class="tel-span">登录密码：</span>
				<input type="password" id="pass" placeholder="请输入登录密码"  />
				<span class="tel"></span>
			</p>
			<p>
				<span class="tel-span">确定登录密码：</span>
				<input type="password" id="repeatpass" placeholder="请再次输入登录密码" />
				<span class="tel"></span>
			</p>
			<p>
				<input type="button" value="确认" />
			</p>
		</div>
		<div class="succ hide">
			<p>重置成功</p>
			<p><a href="${selfSite}/zdjf/user/login">立即登录</a></p>
		</div>
	</div>
	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/user/js/forgetPass.js"></script>
</html>
