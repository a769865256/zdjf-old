<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
				<span class="line"></span>
				<span class="statenum">2</span>
				<p>
					<i>填写账号信息</i>
					<i>重置登录密码</i>
				</p>
			</div>
			<div class="tabbox">
				<form action="<%=path %>/verifySms.action" id="regist" method="post">
				<div class="tab" id="tab1">
					<ul>
						<li><span>手机号:</span><input type="text" id="user_name" name="user_name" onkeyup="phoneVerify();" placeholder="请输入手机号">
						<div class="error" id="user_name_error"></div>
						<input type="hidden" id="phoneAff">
						<input type="hidden" id="sign" name="sign">
						<input type="hidden" id="type" name="type" value="2">
						</li>
						<li>
							<span>短信验证码:</span>
							<input type="text" id="verif" name="verify" class="message" placeholder="请输入短信验证码">
							<input type="button" class="" id="btn2" value="获取验证码" onclick="sendemail()" />
							<div class="error" id="vercode_error"><%=error!=null?error:"" %></div>
							<input type="hidden" id="vercodeAff">
						</li>
						<li><a href="javascript:dosubmit();" class="nextbtn">下一步</a></li>
					</ul>
				</div>
				</form>
				<div class="tab" style="display: none;" id="tab2">
					<ul>
						<li><span>新密码:</span><input type="password" id="passwd" placeholder="请输入新密码"></li>
						<li><span>确认新密码:</span><input type="password" id="passwd2" placeholder="再次输入密码"></li>
						<li><a href="javascript:" class="okbtn">确认修改</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- content end-->

	<!-- footer -->
	<jsp:include page="./common/footer.jsp"></jsp:include>
	<!-- footer end -->

	<script src="<%=path%>/module/jquery/jquery.min.js"></script>	
    <script src="<%=path%>/module/sticky-header.js"></script>    
    <script src="<%=path%>/js/front/public/navigation.js"></script>
    <script src="<%=path%>/js/front/back.js"></script>
	<script type="text/javascript">
		function phoneVerify(){
			var phone = $("#user_name").val();
			$.ajax({
		                    type: 'GET',
		                    url: '<%=path %>/phoneVerify.json',
		                    data: 'phone='+phone+"&type=2&questType=forget",
		                    dataType: 'json',
		                    success: function(data){
		                    	if(data.status==100){
		                    		$("#sign").val(data.content);
		                    		$("#user_name_error").html("");
		                    		$("#phoneAff").val("Y");
		                    	}else{
		                    		$("#user_name_error").html(data.content);
		                    	}
		                    },
		                    error: function(){
		                        alert('请求错误，请重新再次请求！');
		                        // 即使加载出错，也得重置
		                    }
		     });
		}
		
		function smsSend(){
			  var sign = $("#sign").val();
				if(sign==null || sign==''){
					$("#vercode_error").html("错误请求！");
					return ;
				}else{
					$("#vercode_error").html("");
					var phone = $("#user_name").val();
					$.ajax({
	                    type: 'GET',
	                    url: '<%=path %>/m/sms/send.json',
	                    data: 'phone='+phone+"&type=2&sign="+sign,
	                    dataType: 'json',
	                    success: function(data){
	                    	if(data.status==100){
	                    		$("#vercode_error").html("");
	                    		$("#vercodeAff").val("Y");
	                    	}else{
	                    		$("#vercode_error").html(data.content);
	                    	}
	                    },
	                    error: function(){
	                        alert('请求错误，请重新再次请求！');
	                    }
	     			});
				}
			
			}
	function dosubmit(){
		$("#regist").submit();
	}
	</script>
    
</body>
</html>