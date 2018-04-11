<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<div class="payok pay_success">
    <div class="paytlt">
        <h3><i class="iconfont">&#xe614;</i>投资完成(<span class="timeOut">3</span>秒返回)</h3>
        <ul class="layui-timeline">
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis tou">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <div class="layui-timeline-title"><span class="time"><fmt:formatDate value="${content.pay_time}" pattern="yyyy-MM-dd HH:mm:ss" /></span>您已投资<span>￥<fmt:formatNumber value="${content.buy_amount }" pattern="#,##0.00" type="number" />元</span></div>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis qi">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <div class="layui-timeline-title"><span class="time"><fmt:formatDate value="${content.start_date}" pattern="yyyy-MM-dd" /></span>预计开始计息</div>
                </div>
            </li>
            <li class="layui-timeline-item">
                <i class="layui-icon layui-timeline-axis jie">&#xe63f;</i>
                <div class="layui-timeline-content layui-text">
                    <div class="layui-timeline-title"><span class="time"><fmt:formatDate value="${content.return_date}" pattern="yyyy-MM-dd" /></span>预计本金全部结清</div>
                </div>
            </li>
        </ul>
        <div class="btn">
            <a href="<%=path%>/myFunds.action?type=22">查看详情</a>
            <a href="<%=path%>/product/list.action">继续投资</a>
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
                window.location.href="<%=path%>/myFunds.action?type=22";
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

