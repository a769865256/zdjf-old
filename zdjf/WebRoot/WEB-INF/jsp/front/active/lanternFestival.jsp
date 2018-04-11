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
<div class="lanternFestival">
    <div class="lanternFestival_bg">
        <div class="lanternFestival_bottom">
            <div>
                <p>本活动PC、APP端均适用；</p>
                <p>1. 活动对象：正道金服全体用户；</p>
                <p>2. 活动时间：2018年3月1日-2018年3月7日；</p>
                <p>3. 元宵节红包将即时发放至用户账户中，详情请前往<a href="<%=path%>/myCou.action">我的财富-我的优惠</a>查看。</p>
                <p>4. 本次活动最终解释权归正道金服所有。</p>
                <p>如有疑问，请咨询客服热线：400-690-9898。</p>
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script type="text/javascript" src="<%=path%>/module/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/module/sticky-header.js"></script>
<script type="text/javascript" src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript">
    $('.header').stickMe({
        topOffset:100
    });
</script>
</body>
</html>