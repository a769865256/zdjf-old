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
    <link rel="stylesheet" href="<%=path %>/css/front/guide.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="wrap">
    <div class="guide">
        <span class="fm active">1.免费注册</span>
        <i></i>
        <span class="ktyhcg">2.开通银行存管</span>
        <i></i>
        <span class="cz">3.充值</span>
        <i></i>
        <span class="tzxm">4.投资项目</span>
    </div>
    <div class="layui-carousel" id="guide" lay-filter="guide">
        <div carousel-item="">
            <div value="0"><img src="<%=path %>/images/front/img/guide/zc.png"></div>
            <div><img src="<%=path %>/images/front/img/guide/smrz.png"></div>
            <div><img src="<%=path %>/images/front/img/guide/bdyhk.png"></div>
            <div><img src="<%=path %>/images/front/img/guide/szmmkt.png"></div>
            <div><img src="<%=path %>/images/front/img/guide/srczje.png"></div>
            <div><img src="<%=path %>/images/front/img/guide/dxyzm.png"></div>
            <div><img src="<%=path %>/images/front/img/guide/tjcz.png"></div>
            <div><img src="<%=path %>/images/front/img/guide/xzlcxm.png"></div>
            <div value="9"><img src="<%=path %>/images/front/img/guide/syyhq.png"></div>
            <div><img src="<%=path %>/images/front/img/guide/end.png"></div>
        </div>
    </div>
    <a href="<%=path %>/toRegister.action">注册领红包</a>
    <div class="bai"></div>
</div>
<!-- content end-->

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path %>/module/jquery/jquery.min.js"></script>
<script src="<%=path %>/module/sticky-header.js"></script>
<script src="<%=path %>/module/layui/layui.js"></script>
<script src="<%=path %>/js/front/guide.js"></script>
<script type="text/javascript">
    $('.header').stickMe({
        topOffset:100
    });
</script>
</body>
</html>