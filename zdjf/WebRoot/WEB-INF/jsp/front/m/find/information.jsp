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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui"/>
    <title>正道金服</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/css/front/m/style.css"/>
    <link rel="stylesheet" href="<%=path%>/css/front/m/index.css"/>
    <link rel="stylesheet" href="<%=path%>/module/swiper/swiper-3.4.2.min.css"/>
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path%>/css/front/m/find.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<div class="find">
	<div class="header"><a class="back" href="<%=path%>/appStatic/about.action?isBack=1"></a>信息披露</div>
	<div class="f_infor">
		<img src="<%=path%>/images/front/m/message_01.jpg" alt="">
		<img src="<%=path%>/images/front/m/message_02.png" alt="">
		<div class="wo_office">
			<img src="<%=path%>/images/front/m/mywealth/work_tlt.png" alt="">
			<div class="swiper-container work_off">
			  <div class="swiper-wrapper">
				<div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/bg2.jpg" alt=""></div>
				<div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/bg4.jpg" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/bg1.jpg" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/bg3.jpg" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/bg5.jpg" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/bg6.jpg" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/bg7.jpg" alt=""></div>
			  </div>
			  <div class="swiper-pagination"></div>
			</div>
		</div>
		<div class="qual">
			<img src="<%=path%>/images/front/m/mywealth/qual_tlt.png" alt="">
			<div class="swiper-container qualbox">
			  <div class="swiper-wrapper">
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/zz1.jpg" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/zz2.jpg" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/zz3.png" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/zz4.png" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/zz5.png" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/zz6.jpg" alt=""></div>
			    <div class="swiper-slide"><img src="<%=path%>/images/front/m/mywealth/zz7.jpg" alt=""></div>
			  </div>
			  <div class="swiper-pagination"></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=path%>/js/front/m/zoom.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
<script type="text/javascript" src="<%=path%>/module/swiper/swiper-3.4.2.jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/find.js"></script>
	<script>
	var workSwiper = new Swiper('.work_off', {
	loop:true,
	autoplay: 3000,//可选选项，自动滑动
	pagination : '.swiper-pagination'
	})

	var qualSwiper = new Swiper('.qualbox', {
	loop:true,
	autoplay: 3000,//可选选项，自动滑动
	pagination : '.swiper-pagination'
	})
	</script>
</body>
</html>

