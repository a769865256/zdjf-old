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
        <h3><i class="iconfont">&#xe614;</i>恭喜您,注册成功！</h3>
        <p style="margin: 0 0 20px 0;">为保障账户和资金安全，建议您开通上海银行存管账户</p>
        <p><a href="<%=path%>/toNewAudit.action" class="subtn">开通存管账户</a></p>
        <p><a href="<%=path%>/index.action" class="go_index">返回首页</a></p>
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
