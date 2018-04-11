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
	<link rel="stylesheet" href="<%=path%>/css/front/depo.css">
</head>
<body>
<!-- header -->
<div class="header">
	<jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="depository">
	<span class="depo_bg"></span>
	<a href="<%=path%>/fundsDeposit/1/help.action" class="moer"></a>
	<div class="debtn">
		<div class="debox">
			<c:if test="${empty user || user.status != 3}"><a href="<%=path%>/toAudit.action" class="go_kt">马上开通</a></c:if>
			<c:if test="${not empty user && user.status == 3}"><a href="javascript:" class="yi_kt" >已开通</a></c:if>
		</div>
	</div>
</div>


<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path%>/module/jquery/jquery.min.js"></script>
<script src="<%=path%>/module/sticky-header.js"></script>
<script type="text/javascript">
    $('.header').stickMe({
        topOffset:100
    });
</script>
</body>
</html>
