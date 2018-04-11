<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>正道金服</title>
	<!-- reset/iconfont -->
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
			<h3><i class="iconfont">&#xe614;</i>提现完成(<span class="timeOut">3</span>秒返回)</h3>
			<ul class="layui-timeline">
		  		<li class="layui-timeline-item">
		    		<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
		    		<div class="layui-timeline-content layui-text">
		     			<div class="layui-timeline-title"><span class="time"><fmt:formatDate
									value="${content.withdraw_time }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>您提现金额为<span>￥${content.amount }</span></div>
		    		</div>
		  		</li>
		  		<li class="layui-timeline-item">
		    		<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
		    		<div class="layui-timeline-content layui-text">
		     			<div class="layui-timeline-title">提现成功</div>
		    		</div>
		  		</li>
		  		<li class="layui-timeline-item">
		    		<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
		    		<div class="layui-timeline-content layui-text">
		     			<div class="layui-timeline-title"><span class="time"><fmt:formatDate
									value="${content.account_time }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span>预计到账<span class="rmb"></span></div>
		    		</div>
		  		</li>
			</ul>
			<div class="btn">
				<a href="<%=path%>/myFunds.action?type=21">查看提现记录</a>
				<a href="<%=path%>/product/list.action">立即投资</a>
			</div>
		</div>	
	</div>
	<!-- content end-->


	<!-- footer -->
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
        $(function(){
            var wait = $(".timeOut").html();
            timeOut();
            function timeOut() {
                if (wait == 0) {
                    window.location.href="<%=path%>/myFunds.action?type=21";
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
