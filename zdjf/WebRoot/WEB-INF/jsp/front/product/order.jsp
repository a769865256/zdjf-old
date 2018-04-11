<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	<link rel="stylesheet" href="<%=path%>/module/slides/flexslider.css"> 
	<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
	<link rel="stylesheet" href="<%=path%>/css/front/index.css">
	<link rel="stylesheet" href="<%=path%>/css/front/project.css">
</head>
<body>
	<!-- header -->
	<div class="header">
		<jsp:include page="/WEB-INF/jsp/front/common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="pro_detail">
		<!-- 债转详情 -->
		<div class="ovderconfirm">
			<div class="ca_lf">
				<h3 class="ddqd">订单确认</h3>
				<div class="confirm">
					<p><span>项目名称：<i>${content.product.product_name }</i></span><span>年化收益：<i>${content.product.income- content.product.platform_interest}+${ content.product.platform_interest}%</i></span></p>
					<p class="firm"><span>收益天数：<i>${content.product.incomeDays }天</i></span><span>还款方式：<i>${content.product.return_method }</i></span></p>
					<p><span>投资本金：<i>${content.amount }元</i></span><span>预计收益：<i>${content.willIncome }元</i></span></p>
				</div>
				<h3>还款计划</h3>
				<table>
					<thead>
						<tr>
							<th>期数</th>
							<th>还款日期</th>
							<th>本金(元)</th>
							<th>利息(元)</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>2017-10-10</td>
							<td>1000.00</td>
							<td>0.52</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 已登录 -->
			<div class="ca_rt">
				<h3>账户可用余额：<span>${content.userFundStat.balance }</span><a href="javascript:">去充值</a></h3>
				<div class="voucher">
					<p>加息券：<span>${content.coupon_value }</span></p>
					<p>红包券：<span>${content.gift_value }</span></p>
				</div>
				<div class="zhengjing">
					<p>正经值：<input type="text" placeholder="请输入正经值">
						<a href="javascript:"><i class="iconfont">&#xe7b7;</i></a>
						<a href="javascript:" style="display: none;"><i class="iconfont">&#xe602;</i></a>
					</p>
				</div>
				<div class="ovderlist">
					<p>
						<i class="iconfont">&#xe646;</i>
						<span>正经值余额：<i>${content.userFundStat.coin_balance }元</i>,</span>
						<span>本次可用：<i>10.00元</i></span>
					</p>
					<p>
						<i class="iconfont">&#xe646;</i>
						<span>单次最大抵用金额为投资本金的1.0%</span>
					</p>
				</div>
				<div class="ovderbtn">
					<a href="javascript:">实付：1000，立即支付</a>
				</div>
				<div class="agreement">
					<p><input type="radio">我已阅读并同意<a href="javascript:">《正道金服服务协议》</a></p>
				</div>
			</div>
		</div>
	</div>
	<!-- content end-->


	<!-- footer -->
	<jsp:include page="/WEB-INF/jsp/front/common/footer.jsp"></jsp:include>
	<!-- footer end -->
    <script src="<%=path%>/module/jquery/jquery.min.js"></script>
    <script src="<%=path%>/module/cxscroll/jquery.cxscroll.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/module/layui/layui.js"></script>
	<script src="<%=path%>/js/front/project.js"></script>
	<script type="text/javascript">
    	$('.header').stickMe({
			topOffset:100
		});
    </script>
</body>
</html>