<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>关于我们-专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/notice.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>

<body>
<jsp:include page="comm/header.jsp"></jsp:include>
<!-- detail -->
<div class="content notice clearfix">
	<!-- left -->
	<div class="left-nav">
		<a href="${selfSite}/zdjf/notice" class="curr">平台公告</a>
		<a href="${selfSite}/zdjf/q&a">常见问题</a>
	</div>

	<!-- right -->
	<div class="right-con">
		<div class="title">平台公告</div>
		<div class="line-red"></div>
		<!-- list -->
		<div class="list J_list">
			<ul>
				<c:forEach var="item" items="${notices}">
					<li>
						<div class="name">${item.title}<span class="date"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd"/></span></div>
						<div class="con">
							${item.content}
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</div>
<jsp:include page="comm/footer.jsp"></jsp:include>
<jsp:include page="comm/helper.jsp"></jsp:include>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script>
	$(function () {
		$('.con').hide();
		$('.J_list .name').on('click', function(){
			var e = $(this);
			$('.con').slideUp(300);
			if(!(e.next('.con').css('display') == 'block')){
				e.next('.con').slideDown(300);
			}
		})

		//获取链接所传参数
		var open = parseInt(window.location.hash.replace(/#/, '')) - 1;
		var leng = $('.J_list .con').length - 1;
		if(open > leng)(
			open = leng
		)
		var place = '.J_list .con:eq(' + open + ')';
		$(place).slideDown(300);
	})
</script>
</body>
</html>
