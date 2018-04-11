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
    <title>平台数据</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/style.css"/>
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/index.css"/>
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/iSlider.css"/>
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/plat.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<div class="plat">
	<div class="f_plat">
		<div class="mon_num">
			<h3>实时数据</h3>
			<span>239,612,137.00</span>
			<h4>累计成交余额(元)</h4>
		</div>
	</div>
	<div class="cumulative">
		<div class="cumu">
			<div class="cubox cu1">
				<h3>累计注册人数</h3>
				<p>11万9542人</p>
			</div>
			<div class="cubox cu2">
				<h3>累计赚取收益</h3>
				<p>312万9891元</p>
			</div>
			<div class="cubox cu3">
				<h3>累计投资笔数</h3>
				<p>5万3316笔</p>
			</div>
		</div>
	</div>
	<div class="operate">
		<h3>运营数据</h3>
		<div class="islider_box">
			<div id="iSlider-wrapper">
			
			</div>
		</div>	
		<div>
			<h3>投资人概括</h3>
			<img src="<%=path%>/static/zdjf_app/static/img/plat_in.png" alt="">
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
	};
	__style.setAttribute("zoom",_zoom);
	_orientationChange();
	window.addEventListener("resize",_orientationChange,false);
	})();
	</script>
	<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/iSlider.min.js"></script>
	<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/iSlider.animate.min.js"></script>
<script>
	var list = [
		{
			content: '<a href="<%=path%>/static/zdjf_app/page/report/report_2017.html"><img src="<%=path%>/static/zdjf_app/static/img/active/report_2017.png" alt=""></a>'
		},{
			content: '<a href="<%=path%>/static/zdjf_app/page/report/report_half.html"><img src="<%=path%>/static/zdjf_app/static/img/active/report_half.jpg" alt=""></a>'
		},{
			content: '<a href="<%=path%>/static/zdjf_app/page/report/report_quarter.html"><img src="<%=path%>/static/zdjf_app/static/img/active/report_quarter.jpg" alt=""></a>'
		}
	];
	var list_a = ['<%=path%>/static/zdjf_app/page/report/report_quarter.html','<%=path%>/static/zdjf_app/page/report/report_2017.html','<%=path%>/static/zdjf_app/page/report/report_half.html']
	var S = new iSlider({
		data: list,
		dom: document.getElementById("iSlider-wrapper"),
		type: 'dom',
        isLooping: 1,
        isOverspread: 1,
        isAutoplay: 0,
		isDebug: 0,
        animateTime: 800,
        animateType: 'depth',
    });
</script>
</body>
</html>

