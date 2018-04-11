<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String errorUser=(String)request.getAttribute("errorUser");
String errorPasswd=(String)request.getAttribute("errorPasswd");
String url = (String)request.getAttribute("url");
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
	<link rel="stylesheet" href="<%=path %>/css/front/login.css">
</head>
<body>
	<!-- header -->
	<div class="header">
		<jsp:include page="./common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="login">
		<div class="lo_left">
			<%-- <a href="<%=path%>/depository.action"><img src="<%=path %>/images/front/img/login/banner.png" alt=""></a> --%>
			<a href="<%=path%>/toSign.action"><img src="<%=path %>/images/front/img/login/banner.png" alt=""></a>
		</div>
		<div class="lo_right">
			<h3>登录</h3>
			<form id="login" action="<%=path %>/login.action" method="post">
				<ul>
					<li>
						<input type="text" id="user_name" name="user_name"  placeholder="请输入手机号码"/>
						<div class="error"><%=errorUser!=null?errorUser:"" %></div>
					</li>
					<li>
						<input type="password" id="passwd" name="passwd"  placeholder="请输入登入密码"/>
						<i class="iconfont wd bi">&#xe629;</i>
						<i class="iconfont wd zheng" style="display: none;">&#xe632;</i>
						<div class="error"><%=errorPasswd!=null?errorPasswd:"" %></div>
					</li>
					<%if(0>1){ %>
					<li>
						<input type="text" class="code">
						<div class="codebox">ns2a</div>
					</li>
					<%} %>
				</ul>
				<div class="tlt">没有账号？
					<a href="<%=path%>/toRegister.action">去注册</a>
					<a href="<%=path%>/toBack.action" class="f_rt">忘记密码</a>
				</div>
				<div class="btn">
					<a href="javascript:dosumbit();">登录</a>
					<img src="<%=path %>/images/front/img/login/btnbj.png" alt="">
				</div>
				<input type="hidden" name="url" value="<%=url!=null&&!url.equals("null")?url:""%>">
			</form>
		</div>
	</div>
	<!-- content end-->

	<!-- footer -->
	<jsp:include page="./common/footer.jsp"></jsp:include>
	<!-- footer end -->

	<script type="text/javascript" src="<%=path %>/module/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/module/sticky-header.js"></script>
	<script type="text/javascript">
    	$('.header').stickMe({
			topOffset:100
		});
		//密码显、隐
		$("#login li i").click(function(){
			if($(".bi").is(":hidden")){
				$(".zheng").hide();
				$(".bi").show();
				$(this).siblings("input").attr("type","password");
			} else {
				$(".zheng").show();
				$(".bi").hide();
				$(this).siblings("input").attr("type","text");
			}
		});
    </script>
</body>
</html>
<script>
var url = location.search;
if (url.indexOf("?redirect=903") != -1) {    //判断是否有参数
	document.onkeyup = function (e) {//按键信息对象以函数参数的形式传递进来了，就是那个e  
	  	var code = e.charCode || e.keyCode;  //取出按键信息中的按键代码(大部分浏览器通过keyCode属性获取按键代码，但少部分浏览器使用的却是charCode)  
	  	if (code == 13) {
	      	$("#login").attr("action","<%=path %>/login.action?url=/activity/toAprilFoolsDay.action");
	        $("#login").submit();
    	}
	}
} else {
  	document.onkeyup = function (e) {//按键信息对象以函数参数的形式传递进来了，就是那个e  
    	var code = e.charCode || e.keyCode;  //取出按键信息中的按键代码(大部分浏览器通过keyCode属性获取按键代码，但少部分浏览器使用的却是charCode)  
    	if (code == 13) {
        	//此处编写用户敲回车后的代码  
        	$("#login").submit();
    	}
	}
}


function dosumbit(){
	if(url.indexOf('?redirect=903') != -1){      //来自注册页面
      	$("#login").attr("action","<%=path %>/login.action?url=/activity/toAprilFoolsDay.action");
       	$("#login").submit();
	} else {
		//此处编写用户敲回车后的代码  
		$("#login").submit();
	}
}
/* document.onkeyup = function (e) {//按键信息对象以函数参数的形式传递进来了，就是那个e  
    var code = e.charCode || e.keyCode;  //取出按键信息中的按键代码(大部分浏览器通过keyCode属性获取按键代码，但少部分浏览器使用的却是charCode)  
    if (code == 13) {  
        //此处编写用户敲回车后的代码  
        $("#login").submit();
    }  
}  */ 

</script>