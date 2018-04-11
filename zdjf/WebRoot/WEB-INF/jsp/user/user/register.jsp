<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>

<head>
	<base href="${selfSite}/zdjf">
	<meta charset="UTF-8">
	<title>注册-专业透明的互联网理财平台</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand"> 
	<meta name="viewport" content="width=1200"/>
	<link rel="stylesheet" style="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
	<link rel="stylesheet" style="text/css" href="${selfSite}/zdjf/res/user/css/register.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
		#code
		{
			font-family:Arial;
			font-style:italic;
			font-weight:bold;
			border:0;
			letter-spacing:2px;
			color:blue;
		}
	</style>
</head>

<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>
	<div class="conert">
		<!-- 注册链接 -->
		<p class="reg">
			用户注册<span>已有账号？<a href="${selfSite}/zdjf/user/login">立即登录</a></span>
		</p>
		<!-- 左边 -->
		<div class="left">
			<p class="margintop-5">
				<span class="leftspan">手机号码</span>
				<span class="rightspan">
					<input type="text" id="phone" placeholder="请输入用户名" />
					<span class="tal"></span>
				</span>
			</p>
			<p>
				<span class="leftspan">验证码</span>
				<span class="rightspan">
					<span class="clearfix">
						<input type="text" id="checkcode" placeholder="请输入验证码" class="code" />
						<input type = "button" id="code" onclick="createCode()"/>
					</span>
					<span class="tal"></span>
				</span>
			</p>
			<p>
				<span class="leftspan">手机验证码</span>
				<span class="rightspan">
					<span class="clearfix">
						<input type="text" id="codetext" placeholder="请输入验证码" class="code" />
						<span id="getcode" class="getcode">获取验证码</span>
					</span>
					<span class="tal"></span>
				</span>
			</p>
			<p>
				<span class="leftspan">登录密码</span>
				<span class="rightspan">
					<input type="password" id="pass" placeholder="请输入登录密码" />
					<span class="tal"></span>
				</span>
			</p>
			<p>
				<span class="leftspan">确认登录密码</span>
				<span class="rightspan">
					<input type="password" id="repeatpass" placeholder="请再次输入登录密码" />
					<span class="tal"></span>
				</span>
			</p>
			<p>
				<span class="leftspan">邀请人(选填)</span>
				<span class="rightspan">
					<input type="text" id="invitephone" value="${code }" placeholder="请输入邀请人手机号或邀请码" />
					<span class="tal"></span>
				</span>
			</p>
			
			<p class="agreement">
				<span class="leftspan"></span>
				<span class="rightspan">
					<input id="state" checked="checked" type="checkbox" />
					<span>我已阅读并同意<a>《正道金服服务协议》</a></span>
				</span>
			</p>
			<p>
				<span id="tel"></span>
				<input type="button" value="立即注册" class="register_but" /><br />
				<span class="guarantee">
					<img src="${selfSite}/zdjf/res/user/images/register/icon-yun.png">
					阿里巴巴提供安全云技术
				</span>
			</p>
		</div>
		<!-- 右边栏目 -->
		<div class="right">
			<a href="${selfSite}/zdjf/active/newuser.html"><img src="${selfSite}/zdjf/res/user/images/register/signpic.png?t=20170331" /></a>
		</div>
	</div>
	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>
	
	<div id="xy">
	<a class="close-btn"></a>
	<p>·&nbsp;正道金服服务协议</p>
		<div>
  		<iframe scrolling="yes" frameborder="0" src="${selfSite}/zdjf/res/html/sxcffwxy.htm?v=1"  >
  		</iframe>
		</div>
	</div>
	<div id="bj"></div>
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/user/js/register.js?v=1"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
</html>

