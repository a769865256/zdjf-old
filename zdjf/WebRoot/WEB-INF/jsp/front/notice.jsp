<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>正道金服</title>
		<!-- reset/iconfont -->
		<link rel="stylesheet" href="<%=path%>/css/front/reset.css">
		<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
		<link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
		<link rel="stylesheet" href="<%=path%>/css/front/notice.css">
		<link rel="stylesheet" href="<%=path%>/css/front/index.css">
	</head>

	<body>
	<div class="header">
		<jsp:include page="../front/common/header.jsp"></jsp:include>
	</div>
		<div class="notice">
			<div class="noticeBox">
				<div class="tl">
					<span>
						<c:if test="${notice.type==1}"> <a href="<%=path%>/company/1/notice.action?currentPage=1&limit=10">平台公告</a></c:if>
						<c:if test="${notice.type==6}"> <a href="<%=path%>/company/6/notice.action?currentPage=1&limit=5">媒体报道</a></c:if>
					</span>
					<i class="iconfont">&#xe697;</i>
					<c:if test="${notice.type==1}"> <span>公告详情</span></c:if>
					<c:if test="${notice.type==6}"> <span>报道详情</span></c:if>
				</div>
				<div class="title">${notice.title }</div>
				<div class="noticeSj">发布时间：<span><fmt:formatDate value="${notice.create_time }" type="both" pattern="yyyy-MM-dd" />
				</span><c:if test="${notice.type==6}">来源：<a href="${notice.link}" target="_blank">${notice.source}</a></c:if></div>
				<div class="article">
					${notice.content }
				</div>
				<div class="footer" style="display: none;">
					<div class="footer_l"><a href="javascript:;">上篇：<span>没有了</span></a></div>
					<div class="footer_r"><a href="javascript:;" class="active">下篇：<span>关于存管上线后源汇付于天下余额提现的公告</span></a></div>
				</div>
			</div>
		</div>
		<jsp:include page="../front/common/footer.jsp"></jsp:include>
	</body>
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>
	<script src="<%=path %>/module/sticky-header.js"></script>
	<script type="text/javascript">
		$('.header').stickMe({
			topOffset:100
		});
		$(function(){
		    var ps = $(".article p");
		    for(var i=0;i<ps.length;i++){
		    	if(ps[i].innerHTML == "" || ps[i].innerHTML == "<br>" || ps[i].innerHTML == "<br data-filtered='filtered'>" || ps[i].innerHTML == "&nbsp;" || ps[i].firstChild.innerHTML == "<br>" || ps[i].firstChild.innerHTML == "&nbsp;" || ps[i].firstChild.innerHTML == '<span style="text-indent: 2em;"><br></span>'){
			    	ps[i].style.padding = "0";
			    	ps[i].style.height = "0";
			    }
			    if(ps[i].getAttribute("style") == "text-indent: 2em;text-align: left;"){
			    	ps[i].setAttribute("style","text-align:right;padding: 16px 0 0;");
			    }
		    }
		});
	    
	</script>
</html>