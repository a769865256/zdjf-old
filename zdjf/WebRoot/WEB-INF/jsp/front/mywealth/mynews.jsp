<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<!-- header -->
	<div class="header">
		<jsp:include page="../common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="open hide">
		<div class="tlt">
			<p>为了确保您的正常投资和资金安全，请开通新浪支付存钱罐。<a href="javascript:">立即开通</a></p>
		</div>
	</div>
	<div class="wealth">
		<jsp:include page="./left.jsp"></jsp:include>
		<div class="we_rt layui-form">
			<!-- 消息中心 -->
			<div class="message_center we_rtbox">
				<h3>消息中心<span>7</span></h3>
				<div class="layui-form-item mes_chiose">
				    <div class="layui-input-block" style="margin-left: 0;">
				      	<input type="radio" name="message" value="" title="系统公告" checked>
				      	<input type="radio" name="message" value="" title="收益通知">
				      	<a href="javascript:" class="mess_sign_btn">全部标记已读</a>
				    </div>
				</div>
				<div class="ms_list_box">
					<div class="ms_list ms_list_1">
						<ul>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">1道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">2道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">3道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li class="no_active">
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li class="no_active">
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li class="no_active">
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
						</ul>
						<!-- 分页 -->
						<div id="messpagebtn_1"></div>
					</div>
					<div class="ms_list ms_list_2" style="display: none;">
						<ul>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">1道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">2道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">3道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li>
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li class="no_active">
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li class="no_active">
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
							<li class="no_active">
								<i class="iconfont">&#xe646;</i>
								<span class="txt">正道金服支付方式全新升级啦！即日起投资、充值、提现均需输入支付密码......</span>
								<a href="javascript:">查看详情</a>
								<span class="time">2017-07-30 10:31:00</span>
							</li>
						</ul>
						<!-- 分页 -->
						<div id="messpagebtn_2"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- content end-->
	<!-- footer -->
	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<!-- footer end -->
    <script src="<%=path%>/module/jquery/jquery.min.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/module/layui/layui.js"></script>
	<script src="<%=path%>/js/front/wealth.js"></script>
</body>
</html>
