<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport"> 
	<meta content="yes" name="apple-mobile-web-app-capable">
	<meta content="black" name="apple-mobile-web-app-status-bar-style"> 
	<meta content="telephone=no" name="format-detection"> 
    <title>发现</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/css/front/m/style.css"/>
    <link rel="stylesheet" href="<%=path%>/css/front/m/index.css"/>
    <style id="_zoom"></style>
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path%>/css/front/m/find.css"/>
    <style>
    .find .header{height:44px;line-height: 44px;font-size: 17px;}
    .find .body_content{ padding-top: 44px;}
    </style>
</head>
<body>
<div class="find">
    <div class="header">发现</div>
	<div class="f_box">
		<div class="box">
			<div class="state1"><a href="<%=path%>/appStatic/safe.action?isBack=1"><span>安全保障</span></a></div>
			<div class="state2">
				<div class="about"><a href="<%=path%>/appStatic/about.action?isBack=1"><span>关于我们</span></a></div>
				<div class="mess"><a href="<%=path%>/static/zdjf_app/page/help/customer.html?isBack=1"><span>帮助中心</span></a></div>
				<%-- <%=path%>/appStatic/customer.action --%>
			</div>
		</div>
		<div class="title">
			<h3 class="depository">正道金服</h3>
		</div>
		<div class="report">
			<h3>媒体报道</h3>
			<ul>
			<c:forEach var="pro" items="${noticeList }">
				<li>
					<a href="${pro.link }" class="link">
						<div class="relist">
							<div class="l_txt">
								<h4>${pro.title }</h4>
								<span><fmt:formatDate value="${pro.create_time }" pattern="yyyy-MM-dd"/></span>
							</div>
							<div class="l_img"><img src="${pro.image_url }" alt=""></div>
						</div>
					</a>					
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
</body>
    <script>
    (function(doc, win) {
    var docEl = doc.documentElement;
    var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize';
    var recalc = function() {
    var clientWidth = docEl.clientWidth;
    if(!clientWidth) return;
    if(clientWidth >= 640) {
    docEl.style.fontSize = '100px';
    } else {
    docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
    }
    };
    if(!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
    })(document, window);
    </script>
    <script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
    <script type="text/javascript" src="<%=path%>/js/front/m/find.js"></script>
    <script type="text/javascript">
        $('.link').click(function(){
            window.android.fundBackBtn();
        });
        $('.depository').click(function(){
            window.android.fundBackBtn();
            window.location='<%=path%>'+'/appStatic/depository.action';
        });
        if(isiOS){
            $('.find .header').show().next().addClass('body_content');
        }
    </script>
</html>

