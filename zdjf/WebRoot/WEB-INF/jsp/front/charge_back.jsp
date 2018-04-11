<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>正道金服</title>
    <link rel="stylesheet" href="<%=path%>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path%>/module/slides/flexslider.css">
    <link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path%>/css/front/index.css">
    <link rel="stylesheet" href="<%=path%>/css/front/project.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="./common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="payok">
    <div class="paytlt">
        <c:if test="${resStatus==1}"><h3><i class="iconfont">&#xe614;</i>账户充值成功(<span class="timeOut">3</span>秒返回)</h3></c:if>
        <c:if test="${resStatus==0}"><h3>账户充值失败</h3></c:if>
        <ul class="layui-timeline">
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <div class="layui-timeline-title"><span class="time"><fmt:formatDate value="${trade_time }" pattern="yyyy-MM-dd HH:mm:ss" /></span>您充值金额为<span>￥${amt}</span></div>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <div class="layui-timeline-title">${resMsg}</div>
                </div>
            </li>
        <c:if test="${resStatus==1}">
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <div class="layui-timeline-title"><span class="time"><fmt:formatDate value="${trade_time }" pattern="yyyy-MM-dd HH:mm:ss" /></span>您的账户余额为<span class="rmb">￥${user_balance}</span></div>
                </div>
            </li>
        </c:if>
        </ul>
        <div class="btn">
            <a href="<%=path%>/myFunds.action?type=11">查看充值记录</a>
            <a href="<%=path%>/product/list.action">立即投资</a>
        </div>
    </div>
</div>
<!-- content end-->


<!-- footer -->
<jsp:include page="./common/footer.jsp"></jsp:include>
<!-- footer end -->

<script src="<%=path%>/module/jquery/jquery.min.js"></script>
<script src="<%=path%>/module/cxscroll/jquery.cxscroll.js"></script>
<script src="<%=path%>/module/sticky-header.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script src="<%=path%>/js/front/project.js"></script>
<script type="text/javascript">
    $('.header').stickMe({
        topOffset:100
    });
    /*充值成功倒计时*/
    $(function(){
        var wait = $(".timeOut").html();
        timeOut();
        function timeOut() {
            if (wait == 0) {
                window.location.href="<%=path%>/myFunds.action?type=11";
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

