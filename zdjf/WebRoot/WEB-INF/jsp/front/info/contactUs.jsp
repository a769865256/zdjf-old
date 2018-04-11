<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>正道金服</title>
    <!-- reset/iconfont -->
    <link rel="stylesheet" href="<%=path %>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path %>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path %>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path %>/css/front/index.css">
    <link rel="stylesheet" href="<%=path %>/css/front/platformData.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="contactUs">
    <div class="contactUsBox">
        <div class="contactUs_centent">
            <div class="contactUs_top">
                <div>
                    <p>客服服务</p>
                    <ul>
                        <li><i></i>服务时间：工作日: 9:30-17:30</li>
                        <li><i></i>客服电话：400-690-9898 或  0571-56929176</li>
                        <li><i></i>客服邮箱：service@zdjfu.com</li>
                        <li><i></i>媒体邮箱：media@zdjfu.com</li>
                        <li><i></i>在线客服：<img src="<%=path %>/images/front/img/informData/qq_03.png" alt="qq" /><a href="http://zdjfu.udesk.cn/im_client/?web_plugin_id=24428" target="_blank">马上联系</a></li>
                        <li><i></i>理财交流群：463637911<a href="javascripe:;">加入群聊</a></li>
                    </ul>
                </div>
                <div>
                    <p>联系方式</p>
                    <ul>
                        <li><i></i>地址：杭州市西湖区三墩镇1号世创国际大厦16楼</li>
                        <li><i></i>公司官网：https://www.zdjfu.com</li>
                        <li><i></i>邮编：310000</li>
                        <li><i></i>微信：zdjfu161208</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="contactUs_map" id="container1">
            <div id="allmap"></div>
        </div>
    </div>
</div>
<!-- content end-->


<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path %>/module/jquery/jquery-1.9.1.min.js"></script>
<script src="<%=path %>/module/sticky-header.js"></script>
<script src="<%=path %>/module/layui/layui.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=3SqBtGUFiWGXnvgtQitO2hO5LBRz6ljN&s=1"></script>
<script src="<%=path %>/js/front/contactUs.js"></script>
<script type="text/javascript">
	$('.header').stickMe({
		topOffset:100
	});
    // 百度地图
    var map = new BMap.Map("allmap");
    var point = new BMap.Point(120.096772,30.327738);
    map.centerAndZoom(point,18);
    var marker = new BMap.Marker(point);  // 创建标注
    map.addOverlay(marker);               // 将标注添加到地图中
    marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
</script>
</body>
</html>