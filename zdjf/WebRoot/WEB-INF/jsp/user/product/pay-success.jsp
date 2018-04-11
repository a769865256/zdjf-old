<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${selfSite}/zdjf">
	<meta charset="UTF-8">
	<title>投标成功</title>
	<meta name="keywords" content="正道金服">
	<meta name="description" content="正道金服">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/product/css/pay-success.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>

	<div class="loading">
		<div class="alert">
			<div class="alert-darkbg"></div>
			<div class="pay-show">
				<i></i>
			</div>
		</div>
	</div>

	<!-- detail -->
	<div class="content succ clearfix hide">
		<div class="succ-icon">
			<i></i>
			恭喜您投标成功
		</div>
		<c:if test="${!empty title}">
			<div class="active-tip">
				太幸运了，您是该项目的${orderStr}投资用户，<br/>获得称号${title}，并得到正经值奖励 <b>${coin}</b> 元。再接再厉哦！
			</div>
		</c:if>
		<div class="flow">
			<div class="flow-pic"></div>
			<div class="date">
				<span><b>今天</b>成功投资<fmt:formatNumber value="${rsp.amount}" pattern="0.00" />元</span>
				<span><b><fmt:formatDate value="${rsp.startDate}" pattern="yyyy-MM-dd" /></b>开始计算收益</span>
				<span><b><fmt:formatDate value="${rsp.returnDate}" pattern="yyyy-MM-dd" /></b>本息全部到账</span>
			</div>
		</div>
		<%--首投成功时触发--%>
		<%--<div class="pay-first <c:if test="${firstBuy == 2}">hide</c:if>">
			<a href="${selfSite}/zdjf/center/gift"><img src="${selfSite}/zdjf/res/product/images/pay/pay-first.png" alt=""></a>
		</div>--%>
		<div class="btn">
			<a href="${selfSite}/zdjf/center" class="btn-red">我的财富</a>
			<a href="${selfSite}/zdjf/product/search.html" class="btn-red-border">继续投资</a>
		</div>
		<div class="tel">如遇到问题，请拨打服务热线 400-690-9898</div>
	</div>
	<input type="hidden" id="relationId" value="${rsp.buyId }">
	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
<script>
	setTimeout("loca()",2000)
	function loca(){
		$('.loading').addClass('hide');
		$('.content').removeClass('hide');
	}
</script>
</html>