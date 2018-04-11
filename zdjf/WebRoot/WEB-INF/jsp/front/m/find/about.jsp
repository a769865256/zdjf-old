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
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path%>/css/front/m/find.css"/>
    <style id="_zoom"></style>
	<style>
		img{
			display:block;
		}
	</style>
</head>
<body class="zoom">
<div class="find">
	<div class="header"><a class="back" href="<%=path%>/appStatic/find.action?isBack=1"></a>正道金服</div>
	<div class="f_about">
		<div class="tab">
			<div class="btn"><span class="active">关于我们</span></div>
			<div class="btn"><span>安全保障</span></div>
		</div>
		<div class="tabbox">
			<div class="box">
				<img src="<%=path%>/images/front/m/about01.png" alt="">
				<a href="<%=path%>/appStatic/team.action" class="a_isBack"><img src="<%=path%>/images/front/m/about02.jpg" alt=""></a>
				<a href="<%=path%>/appStatic/information.action" class="a_isBack"><img src="<%=path%>/images/front/m/about03.jpg" alt=""></a>
				<a href="<%=path%>/appStatic/development.action" class="a_isBack"><img src="<%=path%>/images/front/m/about04.jpg" alt=""></a>
				<a href="<%=path%>/appStatic/education.action" class="a_isBack"><img src="<%=path%>/images/front/m/about05.png" alt=""></a>
			</div>
			<div class="box" style="display: none;">
				<img src="<%=path%>/images/front/m/recu02.png" alt="">
			</div>
		</div>
	</div>
</div>
	<script>
	(function(){
	var _w,_zoom,_hd, _orientationChange,_doc = document,__style = _doc.getElementById("_zoom");
	__style || (_hd = _doc.getElementsByTagName("head")[0],__style=_doc.createElement("style"),_hd.appendChild(_style));
	_orientationChange = function(){
	_w    = _doc.documentElement.clientWidth || _doc.body.clientWidth;
	_zoom = _w / 750;
	__style.innerHTML = ".zoom {zoom:" + _zoom + ";-webkit-text-size-adjust:auto !important;text-size-adjust:auto !important;}";
	// var rem = _w / 10;
	// window.document.documentElement.style.fontSize = rem + 'px';
	};
	__style.setAttribute("zoom",_zoom);
	_orientationChange();
	window.addEventListener("resize",_orientationChange,false);
	})();
	//rem布局
	(function(win) {
	var doc = win.document;
	var docEl = doc.documentElement;
	var tid;

	function refreshRem() {
	var width = docEl.getBoundingClientRect().width;
	if (width > 750) { // 最大宽度
	width = 750;
	}
	var rem = width / 7.5;
	docEl.style.fontSize = rem + 'px';
	}

	win.addEventListener('resize', function() {
	clearTimeout(tid);
	tid = setTimeout(refreshRem, 300);
	}, false);
	win.addEventListener('pageshow', function(e) {
	if (e.persisted) {
	clearTimeout(tid);
	tid = setTimeout(refreshRem, 300);
	}
	}, false);

	refreshRem();

	})(window);
	</script>
<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/find.js"></script>
<script type="text/javascript">
	 $(function(){  
	 	window.android.fundBackBtn();
	 });
</script>
</body>
</html>

