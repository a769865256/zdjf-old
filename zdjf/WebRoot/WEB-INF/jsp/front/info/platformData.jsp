<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
	<div class="platformData">
		<div class="platformDataBox">
			<div class="platformData_top">
				<table>
					<thead>
						<tr>
							<th>累计注册人数（人）</th>
							<th>累计成交额（元）</th>
							<th>累计投资笔数（笔）</th>
							<th>为用户赚取收益（元）</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${userCount}</td>
							<td>${sumAmt}</td>
							<td>${invtNum}</td>
							<td>${totalIncomed}</td>
						</tr>
					</tbody>
				</table>
				<div class="yyTime">
					<p>累计运营时间：</p>
					<ul class="time">
						<li>3</li>
						<li>6</li>
						<li>5</li>
					</ul>
					<p>天</p>
				</div>
			</div>
			<div class="platformData_yybg">
				<div class="nian">
					<span>2017</span>
				</div>
				<div id="hezuo_id" class="cxscroll3">
				  	<div class="box">
						<ul class="list">
							<li><a href="<%=path%>/platdata/tosbnReport.action"><img src="<%=path %>/images/front/img/informData/swiper1.png" alt="暂无更多"></a></li>
							<li><a href="<%=path%>/platdata/tojdReport.action"><img src="<%=path %>/images/front/img/informData/swiper2.png" alt="暂无更多"></a></li>
							<li><a href="javascript:;">暂无更多</a></li>
							<%--<li><a href="javascript:;">暂无更多</a></li>--%>
						</ul>
				  	</div>
				  	<!-- 控制按钮会自动创建，可省略 -->
				  	<a class="iconfont prev">&#xe600;</a>
				  	<a class="iconfont next">&#xe697;</a>
				</div>
			</div>
			<div class="platformData_dyfbg">
				<div class="platformData_dyfbg_end">
					<div id="ChinaMap" class="ChinaMap" style="width: 600px;height: 520px;"></div>
					<div class="tzmsgBox">
						<div class="tzmsg">
							<div>投资人数</div>
							<%--<div>投资金额</div>--%>
						</div>
						<p>TOP10</p>
						<ul>
							<li>
								<div class="layui-progress layui-progress-big">
	  								<div class="layui-progress-bar one" lay-percent="78%"></div>
								</div>
								<span>浙江</span>
							</li>
							<li>
								<div class="layui-progress layui-progress-big">
							  		<div class="layui-progress-bar tow" lay-percent="50%"></div>
								</div>
								<span>广东</span>
							</li>
							<li>
								<div class="layui-progress layui-progress-big">
							  		<div class="layui-progress-bar three" lay-percent="50%"></div>
								</div>
								<span>上海</span>
							</li>
							<li>
								<div class="layui-progress layui-progress-big">
	  								<div class="layui-progress-bar fouer" lay-percent="50%"></div>
								</div>
								<span>北京</span>
							</li>
						</ul>
					</div>
				</div>
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
	<script src="<%=path %>/module/cxscroll/jquery.cxscroll.js"></script>
	<script src="<%=path %>/module/echarts/highmaps.js"></script>
	<script src="<%=path %>/js/front/platformData.js"></script>
	<script type="text/javascript">
		$('.header').stickMe({
			topOffset:100
		});
	</script>
</body>
</html>