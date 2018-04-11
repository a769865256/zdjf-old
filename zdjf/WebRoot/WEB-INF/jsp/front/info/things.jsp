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
	<link rel="stylesheet" href="<%=path %>/module/svgmap/css/SyntaxHighlighter.css">
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
	<div class="thing">
		<div class="thingBox">
			<div class="thing_top">
				<p>正道金服大事记</p>
				<p>ZHENGDAOJINFU EVENT</p>
				<div class="layui-carousel" id="thing01" lay-filter="thing01">
				  	<div carousel-item>
					    <div>2017</div>
					    <div>2016</div>
				  	</div>
				</div>
			</div>
			
			<div class="thing_bottom">
				<ul class="layui-timeline things2016" style="display: none;">
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					      	<h3 class="layui-timeline-title">2016.7.11</h3>
					      	<p>杭州云翳网络科技有限公司成立</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					      	<h3 class="layui-timeline-title">2016.12</h3>
					      	<p>正道金服平台上线公测</p>
					    </div>
				  	</li>

				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					      	<h3 class="layui-timeline-title">2016.12.5</h3>
					      	<p>迎来第一位注册用户</p>
					    </div>
				  	</li>
				  	
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2016.12.5</h3>
					      	<p>迎来第一位投资用户</p>
					    </div>
				  	</li>
				</ul>
				<ul class="layui-timeline things2017" >
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					      	<h3 class="layui-timeline-title">2017.1.5</h3>
					      	<p>正道金服迎来第一次还本付息</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					      	<h3 class="layui-timeline-title">2017.1.24</h3>
					      	<p>正道金服获批ICP经营许可证</p>
					    </div>
				  	</li>

				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					      	<h3 class="layui-timeline-title">2017.4.28</h3>
					      	<p>Pre-A轮融资签约成功</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2017.5.4</h3>
					      	<p>平台迎来第10000位注册用户</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2017.5.5</h3>
					      	<p>平台累计交易额突破1000万</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2017.6.28</h3>
					      	<p>平台获得《国家信息系统安全等级保护备案证明》三级证明</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2017.7.3</h3>
					      	<p>正道金服携手上海银行签署资金存管协议</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2017.8.5</h3>
					      	<p>平台累计交易额突破8000万</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2017.8.7</h3>
					      	<p>平台迎来第50000位注册用户</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2017.8.19</h3>
					      	<p>平台累计交易额突破1亿</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2017.9.21</h3>
					      	<p>平台获得国资企业战略入股</p>
					    </div>
				  	</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text">
					    	<h3 class="layui-timeline-title">2017.10.29</h3>
					      	<p>平台迎来第10万位注册用户</p>
					    </div>
				  	</li>
					<li class="layui-timeline-item">
						<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">2017.11.22</h3>
							<p>平台累计交易额突破2亿</p>
						</div>
					</li>
					<li class="layui-timeline-item">
						<i class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">2017.12.6</h3>
							<p>平台完成上海银行资金存管系统上线</p>
						</div>
					</li>
				  	<li class="layui-timeline-item">
					    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
					    <div class="layui-timeline-content layui-text end">
					      <div class="layui-timeline-title">未来，我们将继续努力</div>
					    </div>
					  </li>
				</ul>
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
	<script src="<%=path %>/js/front/things.js"></script>
	<script type="text/javascript">
		$('.header').stickMe({
			topOffset:100
		});
	</script>
</body>
</html>