<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>正道金服</title>
    <!-- reset/iconfont -->
    <link rel="stylesheet" href="<%=path%>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path%>/css/front/index.css">
    <link rel="stylesheet" href="<%=path%>/css/front/annualBonus.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->
<div class="newYearFestival">
    <div class="newYearFestival_banner"></div>
    <div class="newYearFestival_middle">
        <div class="newYearFestival_middle_box">
            <div class="newYear_tlt"></div>
            <div class="tzdy30"></div>
            <div class="tzdy50"></div>
            <div class="tzdy80"></div>
        </div>
    </div>
    <div class="newYearFestival_bottom">
        <div class="newYearFestival_bottom_box">
            <div class="bottom"></div>
        </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->

<script type="text/javascript" src="<%=path%>/module/jquery/jquery.min.js"></script>
</body>
</html>
