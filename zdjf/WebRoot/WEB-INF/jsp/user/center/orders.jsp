<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${selfSite}/zdjf">
	<meta charset="UTF-8">
	<title>我的理财-我的财富-专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/>
	
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/home.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/kkpager_gray.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>
	<div class="body">
		<jsp:include page="center_nav.jsp"></jsp:include>

		<div class="body-right-bottom order-list">
			<p class="title">
				我的理财 <a
					class="item end <c:if test="${rsp.orderType == 2}">select</c:if>"
					href="${selfSite}/zdjf/center/orders?type=2">已回款</a> <a
					class="item <c:if test="${rsp.orderType == 4}">select</c:if>"
					href="${selfSite}/zdjf/center/orders?type=4">履约中</a> <a
					class="item <c:if test="${rsp.orderType == 0}">select</c:if>"
					href="${selfSite}/zdjf/center/orders">全部</a>
			</p>
			<c:forEach var="buy" items="${rsp.pageData.datas }">
				<div class="pocd-info">
					<p>
						<span>${buy.productName } <c:if test="${buy.willIncomeDays != buy.incomeDays}">(提前还款)</c:if></span>
					</p>
					<ul>
						<li class="yield "><span><c:if test="${buy.status == 2}">实际收益</c:if><c:if test="${buy.status != 2}">预期收益</c:if></span> <span class="f18 text-red">
							<c:if test="${buy.status != -1}">
								<fmt:formatNumber value="${buy.incomed }" pattern="0.00" type="number" />
							</c:if>
							<c:if test="${buy.status == -1}">
								-
							</c:if>
						</span></li>
						<li class="yieldrate"><span>年化收益率</span> <span class="f18">
							<fmt:formatNumber value="${buy.productInterest }" pattern="0.00" type="number" />%
						</span></li>
						<li class="cycle"><span>投资本金</span> <span class="f18">
							<fmt:formatNumber value="${buy.amount }" pattern="0" type="number" />元
						</span></li>
						<li class="state">
							<div class="processingbar">
		                        <font>${buy.circlePercent}%</font>
		                    </div>
							<c:if test="${buy.status == 1}">
								<c:if test="${buy.remainDays == 0}">
									<p class="remain-date yhk">
										<br />回款中
									</p>
								</c:if>
								<c:if test="${buy.remainDays != 0}">
									<p class="remain-date"><i>${buy.remainDays}</i><br /><b>天到期</b>
									</p>
								</c:if>
							</c:if> <c:if test="${buy.status != 1}">
								<p class="remain-date yhk">
									<br />${buy.statusText}</p>
							</c:if>
						</li>
						<c:if test="${buy.status == 1 || buy.status == 2}">
							<li class="xx">
								<a href="javascript:void(0);" class="JOpenDetail" data-id="${buy.buyId }">
									<i class="icon-detail"></i>详情
								</a>
							</li>
						</c:if>
						<c:if test="${buy.status == -1}">
							<li class="pay">
								<a href="javascript:void(0);" data-id="${buy.buyId}" class="btn-orange pay-btn JPay">立即付款</a>
								<a href="javascript:void(0);" data-id="${buy.buyId}" class="pay-close JClose">取消订单</a>
							</li>
						</c:if>
							<li class="pay hide">
								待审核　
								<a href="javascript:void(0);" data-id="${buy.buyId}" class="pay-close">撤销</a>
							</li>
					</ul>
					<div class="time">
						<c:if test="${buy.status != -1}">
							<fmt:formatDate value="${buy.payTime }" pattern="MM-dd" />
						</c:if>
						<c:if test="${buy.status == -1}">
							${buy.remainMinute}后订单过期
						</c:if>
					</div>
				</div>
			</c:forEach>
			<c:if test="${empty rsp.pageData.datas }">
				<p class="zgm list">
					<i class="icon-invent"></i>
					<span>
						<c:if test="${rsp.orderType == 0}">您尚未购买理财产品</c:if>
						<c:if test="${rsp.orderType == 2}">您尚无已回款的理财产品</c:if>
						<c:if test="${rsp.orderType == 4}">您尚无履约中的理财产品</c:if>
						<a href="${selfSite}/zdjf/product/search.html" class="btn-red">前去投资</a>
					</span>
				</p>
				<%--
				<div class="tip-cons">
					<span class="icon-arrow"></span>
					<span class="icon-arrow01"></span>
					<div><i class="tip-cons-icon"><img src="${selfSite}/zdjf/res/center/images/home/tip-cons-icon.png" alt=""></i>完成首次投资立返<span class="text-red">20元</span>哦</div>
				</div>
				--%>
			</c:if>
			<div class="page">
				<jsp:include page="../comm/pager.jsp"></jsp:include>
			</div>
		</div>
	</div>

	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>

	<div class="box-info hide">
		<span class="close-btn"></span>
		<p class="title">
			<span class="box-left">▪ 理财产品详情 </span><span class="box-right"> 编号：<span class="marginright10"
				id="b_buyId_title"></span> 状态：<span id="b_status"></span>
			</span>
		</p>
		<p>
			<span class="line">产品名称：<a href="javascript:void(0);" id="b_productName" class="text-red link-name"></a></span>
		</p>
		<p>
			<span class="box-left"> 购买本金：<span id="b_amount"></span></span>
			<span class="box-right"> 优惠抵扣：<span id="b_couponMsg"></span></span>
		</p>
		<p>
			<span class="box-left"> 收益天数：<span id="b_days"></span></span>
			<span class="box-right"> 投资时间：<span id="b_payTime"></span></span>
		</p>
		<p>
			<span class="box-left"> <c id="t_productInterest">预期年化收益率：</c><span id="b_productInterest"></span></span>
			<span class="box-right"> 起息日期：<span id="b_startDate"></span></span>
		</p>
		<p>
			<span class="box-left"> <c id="t_income">预计总收益：</c><span id="b_income"></span></span>
			<span class="box-right"> 到期日期：<span id="b_endDate"></span></span>
		</p>
		<p>
			<span class="box-left"> 协议书：
				<a href="javascript:void(0);" class="box-right JContract" title="点击查看协议详情">《债权转让协议》</a>
			</span>
			<span class="box-right"> 到期处理方式：<span id="b_returnMethod"></span></span>
		</p>
		<input type="hidden" name="contractUrl" id="contractUrl" value="${selfSite}/zdjf${contractUrl}" />
		<input type="hidden" name="producttype" id="producttype">
		<!-- 还款计划 -->
		<div class="plan">
			<div class="name">还款计划<span class="JReturned">已还期数：<b class="text-red">1</b>/13</span></div>
			<div class="th">
				<span class="w65">期数</span>
				<span class="w148">收款日</span>
				<span class="w179">利息（元）</span>
				<span class="w179">本金（元）</span>
				<span class="w125">状态</span>
			</div>
			<table>
				<thead>
					<tr>
						<th class="w65">期数</th>
						<th class="w148">收款日</th>
						<th class="w179">利息（元）</th>
						<th class="w179">本金（元）</th>
						<th class="w125">状态</th>
					</tr>
				</thead>
				<tbody class="JIncomeList">

				</tbody>
			</table>
		</div>
	</div>
	<div id="bj"></div>
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/cycle-order.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/raphael.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/user_public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/orders.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/show_order_detail.js"></script>
</html>