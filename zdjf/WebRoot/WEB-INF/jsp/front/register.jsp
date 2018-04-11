<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String errorPhone=(String)request.getAttribute("errorPhone");
String errorCode=(String)request.getAttribute("errorCode");
String errorPasswd=(String)request.getAttribute("errorPasswd");
String errorPasswd2=(String)request.getAttribute("errorPasswd2");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册有礼-正道金服</title>
	<!-- reset/iconfont -->
	<link rel="stylesheet" href="<%=path %>/css/front/reset.css">
	<link rel="stylesheet" href="<%=path %>/module/iconfont/iconfont.css">
	<link rel="stylesheet" href="<%=path %>/css/front/index.css">
	<link rel="stylesheet" href="<%=path %>/css/front/register.css">
</head>
<body>
	<!-- header -->
	<div class="header">
		<jsp:include page="./common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="register">
		<div class="rebox">
			<form action="<%=path %>/register.action" id="regist" method="post">
				<input type="hidden" name="invite_source" id="invite_source">
				<h3>注册</h3>
				<ul>
					<li>
						<input type="text" id="user_name" name="user_name" onkeyup="phoneVerify();" placeholder="请输入手机号">
						<div class="error" id="user_name_error"><%=errorPhone!=null?errorPhone:"" %></div>
						<input type="hidden" id="phoneAff">
					</li>
					<li>
						<input type="text" id="verif" name="verif" class="vercode" placeholder="请输入验证码">
						<input type="button" class="verbtn" id="btn2" value="获取验证码" onclick="sendemail()" />
						<input type="hidden" name="sign" id="sign">
						<div class="error" id="vercode_error"><%=errorCode!=null?errorCode:"" %></div>
						<input type="hidden" id="vercodeAff">
					</li>
					<li>
						<input type="password" id="passwd" class="passwd" name="passwd" onchange="pwdVerify()" placeholder="设置6-16位数字+英文组合的登录密码
">
						<i class="iconfont wd bi">&#xe629;</i>
						<i class="iconfont wd zheng" style="display: none;">&#xe632;</i>
						<div class="error" id="errorPasswd" ><%=errorPasswd!=null?errorPasswd:"" %><%=errorPasswd2!=null?errorPasswd2:"" %></div>
						<input type="hidden" id="passwdAff">
					</li>
					<li>
						<input type="password" id="passwd2" class="passwd2" name="passwd2"  onchange="pwdCheck()" placeholder="请再次输入密码">
						<i class="iconfont wd1 bi1">&#xe629;</i>
						<i class="iconfont wd1 zheng1" style="display: none;">&#xe632;</i>
						<input type="hidden" id="passwd2Aff">
					</li>
					<li>
						<p id="yqm">邀请码(选填)</p>
					</li>
					<li style="display: none;" class="yqm">
						<input type="text" id="inviter_phone" name="inviter_phone" placeholder="请输入邀请人手机号">
						<div class="error"></div>
					</li>
					<li>
						<label for=""><input checked type="checkbox" class="agreement">我已阅读并同意<a href="<%=path%>/static/front/zdjfu_pc/zdjf_protocol.html" target="_blank">《正道金服服务协议》</a></label>
					</li>
					<li>
						<a href="javascript:dosubmit();" class="registbtn">注册</a>
						<p class="btntxt">已有账号?<a href="<%=path %>/toLogin.action">去登录</a></p>
					</li>
				</ul>
			</form>
		</div>
	</div>
	<!-- content end-->

	<!-- footer -->
	<jsp:include page="./common/footer.jsp"></jsp:include>
	<!-- footer end -->

    <script src="<%=path %>/module/jquery/jquery.min.js"></script>
    <script src="<%=path %>/module/sticky-header.js"></script>
    <script src="<%=path %>/js/front/login.js"></script>
</body>
<script>
	$(function(){
		/* 监听url */
		var url = location.search;
	   	if (url.indexOf("?") != -1) {    //判断是否有参数
	      	var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
	      	strs = str.split("=");   //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
	      	$("#invite_source").val(strs[1]); //直接弹出第一个参数 （如果有多个参数 还要进行循环的）
	   	}
	   	$(".register .rebox form ul li p#yqm").on("click",function(){
	   		if($(".yqm").is(":hidden")){
	   			$(".yqm").slideDown();
	   			$("#yqm").addClass("active");
	   		} else {
	   			$(".yqm").slideUp();
	   			$("#yqm").removeAttr("class");
	   		}
	   	});
	});
function phoneVerify(){
	var phone = $("#user_name").val();
	$.ajax({
                    type: 'GET',
                    url: '<%=path %>/phoneVerify.json',
                    data: 'phone='+phone+"&type=1&questType=register",
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
	                    data: 'phone='+phone+"&type=1&sign="+sign,
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
</script>
</html>
