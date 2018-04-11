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
<html lang="en">
<head>
<meta charset="UTF-8">
<title>正道金服</title>
<!-- reset/iconfont -->
<link rel="stylesheet" href="<%=path%>/css/front/reset.css">
<link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<link rel="stylesheet" href="<%=path%>/css/front/index.css">
<link rel="stylesheet" href="<%=path%>/css/front/wealth.css">
</head>
<body>
	<div class="header">
		<jsp:include page="/WEB-INF/jsp/front/common/header.jsp"></jsp:include>
	</div>
	<div class="open">
		<div class="tlt">
			<p>
				为了确保您的正常投资和资金安全，请开通新浪支付存钱罐。<a href="javascript:">立即开通</a>
			</p>
		</div>
	</div>
	<div class="wealth">
		<div class="we_lf">
			<ul>
				<li class="active"><i class="iconfont">&#xe625;</i><a
					data-html="wealthbox" href="javascript:">我的财富</a></li>
				<li><i class="iconfont">&#xe606;</i><a data-html="capitalbox"
					href="javascript:">资金明细</a></li>
				<li><i class="iconfont">&#xe64a;</i><a data-html="duct"
					href="javascript:">我的理财</a></li>
				<li><i class="iconfont">&#xe613;</i><a data-html="discount"
					href="javascript:">我的优惠</a></li>
				<li><i class="iconfont">&#xe650;</i><a data-html="account"
					href="javascript:">账户设置</a></li>
				<li><i class="iconfont">&#xe8b0;</i><a data-html="invitation"
					href="javascript:">邀请有礼</a></li>
				<li><i class="iconfont">&#xe61e;</i><a
					data-html="message_center" href="javascript:">消息中心</a></li>
			</ul>
			<div class="service">
				<i class="iconfont">&#xe61f;</i>
				<p>
					电话：400-690-9898<br>我们将诚挚为您服务
				</p>
			</div>
		</div>
		<div class="we_rt">
			<!-- 我的财富 -->
			<!-- 资金明细 -->
			<!-- 我的理财 -->
			<!-- 我的优惠 -->
			<!-- 账户设置 -->

			<!-- 邀请有礼 -->

			<!-- 消息中心 -->

			<!-- 我的财富》提现 -->
			<!-- 我的财富》充值 -->
			<!-- 账户设置-绑定 -->
			<!-- 账户设置-解绑 -->
		</div>

	</div>
	<jsp:include page="/WEB-INF/jsp/front/common/footer.jsp"></jsp:include>
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>
	<script src="<%=path%>/module/sticky-header.js"></script>
	<script src="<%=path%>/module/layui/layui.js"></script>
	<script src="<%=path%>/module/echarts/echarts.min.js"></script>
	<script src="<%=path%>/js/front/list.js"></script>
	<script type="text/javascript">
	$('.header').stickMe({
		topOffset:100
	});
		var path='<%=path%>';
		$(document).ready(function(){ 
			var loc = location.href;
			var n1 = loc.length;//地址的总长度
			var n2 = loc.indexOf("wid=");//取得=号的位置
			var index = parseInt(loc.substr(n2+4, n1-n2));//从=号后面的内容
			$('.we_lf ul li').eq(index).addClass('active').siblings().removeClass('active');
			if(n2<0){
				$('.we_rt').load(path+'/wealth.action');
			}else{
				if(index==0){
				$('.we_rt').load(path+'/wealth.action');
				}
				if(index==1){
					$('.we_rt').load(path+'/capital/list.action');
				}
				if(index==2){
					$('.we_rt').load(path+'/conduct.action');
				}
				if(index==3){
					$('.we_rt').load(path+'/discount.action');
				}
				if(index==4){
					$('.we_rt').load(path+'/account.action');
				}
				if(index==5){
					$('.we_rt').load(path+'/invitation.action');
				}
				if(index==6){
					$('.we_rt').load(path+'/message_center.action');
				}
			}
			
	}); 
	</script>
</body>