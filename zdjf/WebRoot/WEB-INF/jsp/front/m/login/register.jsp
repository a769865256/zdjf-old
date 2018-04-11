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
<link rel="stylesheet" href="<%=path%>/css/front/m/login.css" />
<link rel="stylesheet" href="<%=path%>/css/front/m/index.css" />
<link rel="stylesheet" href="<%=path%>/css/front/m/style.css" />

<!-- 公共样式end -->

<style id="_zoom"></style>
</head>
<body class="zoom">
	<div class="login">
		<div class="header">
			<a class="back" href="javascript:history.go(-1)"></a>注册
		</div>
		<form action="<%=path%>/m/register.action" id="regist" method="post">
			<div class="l_box">
				<div class="relogo">
					<img src="<%=path%>/images/front/m/login/reg.png" alt="">
				</div>
				<div class="error"><%=errorMsg != null ? errorMsg : ""%></div>
				<div class="regist_box">
					<ul>
						<li class="phone"><input type="text" class="ph_num" name="user_name"
							onchange="phoneVerify();" placeholder="输入手机号码"> <input
							type="hidden" id="phoneAff"></li>
						<li class="img"><input type="text" class="ph_sign" name="verif"
							placeholder="输入验证码"><input type="hidden" name="sign"
							class="sign" id="sign"> <input type="button"
							class="verbtn btn2" id="btn" value="获取验证码"> <input
							type="hidden" id="vercodeAff"></li>
						<li class="pass"><input type="password" name="passwd"
							onchange="pwdVerify()" class="ph_pass"
							placeholder="设置6-16位数字+英文组合的登录密码"> <input type="hidden"
							id="passwdAff"></li>
					</ul>
					<div class="invita">
						<p>
							<span class="in_open"></span><input type="text" 
								placeholder="邀请人手机号码">
						</p>
						<p class="inbox hide">
							<input type="text" placeholder="输入邀请码/邀请人手机号码">
						</p>
					</div>
				</div>
				<div class="l_btn">
					<a href="javascript:" class="active">注册领红包</a>
				</div>
				<div class="xieyi">
					<p>
						<input type="checkbox" class="agreement" checked>我已阅读并同意<a
							href="javascript:">《正道金服服务协议》</a>
					</p>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="<%=path%>/js/front/m/zoom.js"></script>
	<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
	<script type="text/javascript" src="<%=path%>/js/front/m/login.js"></script>
	<script type="text/javascript">
		var path = '<%=path%>';
		function phoneVerify() {
			var phone = $(".ph_num").val();
			$.ajax({
				type : 'GET',
				url : '<%=path%>/phoneVerify.json',
				data : 'phone=' + phone + "&type=1&questType=register",
				dataType : 'json',
				success : function(data) {
					if (data.status == 100) {
						$("#sign").val(data.content);
						$(".error").text("");
						$("#phoneAff").val("Y");
					} else {
						$(".error").text(data.content);
					}
				},
				error : function() {
					alert('请求错误，请重新再次请求！');
				// 即使加载出错，也得重置
				}
			});
		}
	</script>
</body>
</html>

