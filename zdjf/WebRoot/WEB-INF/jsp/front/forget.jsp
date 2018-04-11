<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sign = (String)request.getAttribute("sign");
String user_name = (String)request.getAttribute("user_name");
String error = (String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>正道金服</title>
	<!-- reset/iconfont -->
	<link rel="stylesheet" href="<%=path %>/css/front/reset.css">
	<link rel="stylesheet" href="<%=path %>/module/iconfont/iconfont.css">
	<link rel="stylesheet" href="<%=path %>/css/front/index.css">
	<link rel="stylesheet" href="<%=path %>/css/front/back.css">
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>	
</head>
<body>
	<!-- header -->
	<div class="header">
	<jsp:include page="./common/header.jsp"></jsp:include>
	</div>

	<!-- content -->
	<div class="back">
		<div class="head">
			<h3>找回密码</h3>
			<p>没有账号？<a href="<%=path%>/toRegister.action">立马注册</a></p>
		</div>
		<div class="tent">
			<div class="state">
				<span class="statenum active">1</span>
				<span class="line active"></span>
				<span class="statenum active">2</span>
				<p>
					<i>填写账号信息</i>
					<i>重置登录密码</i>
				</p>
			</div>
			<div class="tabbox">
				<form action="<%=path %>/forget.action" id="forget" method="post">
				<div class="tab" id="tab2">
					<ul>
						<li><span>新密码:</span><input type="password" id="passwd" onchange="pwdVerify();" name="passwd" placeholder="请输入新密码">
						<div class="error" id="errorPasswd" ><%=error!=null?error:"" %></div></li>
						<li><span>确认新密码:</span><input type="password" id="passwd2" onchange="pwdCheck();" name="passwd2" placeholder="再次输入密码"></li>
						<li><a href="javascript:dosubmit();" class="okbtn">确认修改</a></li>
						<input type="hidden" id="sign" name="sign" value="<%=sign%>" >
						<input type="hidden" id="type" name="type" value="2">
						<input type="hidden" id="user_name" name="user_name" value="<%=user_name%>">
						<input type="hidden" id="passwdAff">
						<input type="hidden" id="passwd2Aff">
					</ul>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!-- content end-->

	<!-- footer -->
	<jsp:include page="./common/footer.jsp"></jsp:include>
	<!-- footer end -->

	
<script type="text/javascript">
function pwdVerify(){
	var regex = new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,})$');
	if(regex.test($("#passwd").val())){
		$("#errorPasswd").html("");
		$("#passwdAff").val("Y");
	}else{
		$("#errorPasswd").html("密码必须大于8位小于30位，并且含字母与数字");
	}
}

function pwdCheck(){
	if($("#passwd").val()!=$("#passwd2").val()){
		$("#errorPasswd").html("2次输入的密码不一致！");
	}else{
		$("#errorPasswd").html("");
		$("#passwd2Aff").val("Y");
	}
}
function dosubmit(){
	if($("#passwdAff").val()=="Y" && $("#passwd2Aff").val()=="Y"){
		$("#forget").submit();
	}
	
}
</script>
    
</body>
</html>