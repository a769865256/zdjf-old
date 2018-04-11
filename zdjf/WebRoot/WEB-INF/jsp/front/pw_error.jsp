<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String response_msg = (String)request.getAttribute("response_msg");

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
			<h3><i class="iconfont">&#xe614;</i><%=response_msg %></h3>
			<p><a href="<%=path%>/mywealth.action" class="go_index">返回</a>我的财富，如有疑问，请联系客服。</p>
		</div>
	</div>
	<!-- content end-->

	<!-- footer -->
	<jsp:include page="./common/footer.jsp"></jsp:include>
	<!-- footer end -->

    <script src="<%=path%>/module/jquery/jquery.min.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/js/front/public/navigation.js"></script>
</body>
</html>
