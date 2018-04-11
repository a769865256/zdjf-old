<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sign = (String) request.getAttribute("sign");
	String error = (String) request.getAttribute("error");
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
			<a class="back" href="javascript:history.go(-1)"></a>忘记密码
		</div>
		<div class="l_box">
			<div class="zdlogo">
				<img src="<%=path%>/images/front/m/login/login.png" alt="">
			</div>
			<div class="error">
				<%=error != null ? error : ""%>
			</div>
			<div class="regist_box">
				<form action="<%=path%>/m/forget.action" id="forget" method="post">
					<ul>
						<li class="phone"><input type="text" class="ph_num"
							id="user_name" name="user_name" onchange="phoneVerify()" placeholder="输入手机号码"></li>
						<li class="img"><input type="text" class="ph_num"
							placeholder="输入验证码"> <input type="button" class="verbtn"
							id="btn" value="获取验证码" onclick="sendemail()"></li>
						<li class="pass"><input type="password"
							onchange="pwdVerify();" id="passwd" name="passwd" class="ph_num"
							placeholder="设置6-16位数字+英文组合的登录密码"></li>
						<input type="hidden" id="sign" name="sign" class="sign" value="<%=sign%>">
						<input type="hidden" id="type" name="type" value="1">
						<input type="hidden" id="passwdAff">
					</ul>
				</form>
			</div>
			<div class="l_btn">
				<a href="javascript:" class="active">确定</a>
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
		var path='<%=path%>';
		function pwdVerify() {
			var regex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}');
			if (regex.test($("#passwd").val())) {
				$("#errorPasswd").html("");
				$("#passwdAff").val("Y");
			} else {
				$("#errorPasswd").html("密码必须大于8位小于30位，并且含字母、字符与数字");
			}
		}
		$('.active').click(function() {
			if ($("#passwdAff").val() == "Y") {
				$("#forget").submit();
			}
		});
		function phoneVerify() {
			var phone = $(".ph_num").val();
			$.ajax({
				type : 'GET',
				url : '<%=path%>/phoneVerify.json',
				data : 'phone=' + phone + "&type=1&questType=forget",
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

