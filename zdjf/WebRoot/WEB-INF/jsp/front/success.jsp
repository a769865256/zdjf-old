<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type = (String)request.getAttribute("type");
String url = "";
if("1".equals(type)){
	url = "";
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>正道金服</title>
	<!-- reset/iconfont -->
	<link rel="stylesheet" href="<%=path%>/css/front/reset.css">
	<link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
	<link rel="stylesheet" href="<%=path%>/css/front/index.css">
	<link rel="stylesheet" href="<%=path%>/css/front/register.css">
</head>
<body>
	<!-- header -->
	<div class="header">
	<jsp:include page="./common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="success">
		<div class="su_box">
			<h3 style="height: auto;"><i class="iconfont">&#xe614;</i><%if(type!=null && "1".equals(type)){ %>密码重置成功<br><span class="timeOut" style="margin-right: 8px;">3</span>秒以后自动返回登录页面<%} %></h3>
			<p><a href="<%=path%>/toLogin.action" class="subtn">立即登录</a></p>
			<%-- <p><a href="<%=path+"/"+ url%>" class="subtn">前往投资</a></p>
			<p><a href="<%=path%>/" class="go_index">返回首页</a></p> --%>
		</div>
	</div>
	<!-- content end-->

	<!-- footer -->
	<jsp:include page="./common/footer.jsp"></jsp:include>
	<!-- footer end -->

    <script src="<%=path%>/module/jquery/jquery.min.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/js/front/public/navigation.js"></script>
    <script type="text/javascript">
	    $(function(){
			var wait = $(".timeOut").html();
		    timeOut();
		    function timeOut() {
		        if (wait == 0) {
		            window.location.href="<%=path%>/toLogin.action";
		        } else {
		            setTimeout(function() {
		                $('.timeOut').text(--wait);
		                timeOut();
		            },1000);
		        }
		    }
	    });
    </script>
</body>
</html>
