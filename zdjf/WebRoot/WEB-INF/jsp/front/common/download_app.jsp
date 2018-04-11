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
    <link rel="stylesheet" href="<%=path %>/css/front/downloadApp.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="download">
    <div class="top">
        <div class="ios">
            <a href="javascript:;"><i class="hide"></i>APP版下载</a>
        </div>
        <div class="Android hide">
            <a href="javascript:;"><i></i>Android版下载</a>
        </div>
        <div class="erwm">
            <a href="javascript:;"><img src="<%=path %>/images/front/img/footer/code2.png" alt="">扫二维码下载</a>
        </div>
    </div>
    <div class="centent_1"></div>
    <div class="centent_2"></div>
    <div class="centent_3"></div>
    <div class="footer">
        <div class="footerM">
            <div class="ios">
                <a href="javascript:;"><i class="hide"></i>APP版下载</a>
            </div>
            <div class="Android hide">
                <a href="javascript:;"><i></i>Android版下载</a>
            </div>
            <div class="erwm">
                <a href="javascript:;"><img src="<%=path %>/images/front/img/footer/code2.png" alt="">扫二维码下载</a>
            </div>
        </div>
    </div>
</div>
<!-- content end-->

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->

<script src="<%=path %>/module/jquery/jquery.min.js"></script>
<script src="<%=path %>/module/sticky-header.js"></script>
<script src="<%=path %>/module/layui/layui.js"></script>
<script src="<%=path %>/js/front/downloadApp.js"></script>
<script type="text/javascript">
    $('.header').stickMe({
        topOffset:100
    });
</script>
</body>
</html>