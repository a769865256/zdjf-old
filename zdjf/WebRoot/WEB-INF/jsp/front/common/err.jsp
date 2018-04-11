<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>正道金服</title>
    <!-- reset/iconfont -->
    <link rel="stylesheet" href="<%=path %>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path %>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path %>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path %>/css/front/index.css">
    <link rel="stylesheet" href="<%=path %>/css/front/error_404.css">
</head>
<body>
<!-- header -->
<jsp:include page="header.jsp"></jsp:include>
<!-- header end -->

<!-- content -->
<div class="error">
    <div class="errorCentent">
        <img src="<%=path %>/images/front/img/informData/404_03.png" alt="" class="error_404">
        <p><span>无法访问网页，您可以尝试重新输入网址后刷新</span><a href="<%=path %>">返回首页</a></p>
        <p>正道金服-安享收益</p>
        <img src="<%=path %>/images/front/img/footer/code2.png" alt="" class="ewm">
        <p>扫一扫下载正道金服APP</p>
    </div>
</div>
<!-- content end-->


<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path %>/module/jquery/jquery.min.js"></script>
<script src="<%=path %>/module/sticky-header.js"></script>
<script src="<%=path %>/module/layui/layui.js"></script>
</body>
</html>