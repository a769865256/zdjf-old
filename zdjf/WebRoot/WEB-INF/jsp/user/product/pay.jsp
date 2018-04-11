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
	<title>正道金服-订单支付</title>
	<meta name="keywords" content="正道金服">
	<meta name="description" content="正道金服">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/product/css/pay.css?t=20160804">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>

	<input type="hidden" id="productInterest" value="${rsp.buy.productInterest}">
	<input type="hidden" id="incomeDays" value="${rsp.buy.incomeDays}">
	<input type="hidden" id="amount" value="${rsp.buy.amount}">
	<input type="hidden" id="depositNum" value="${rsp.coinMax}">
	<input type="hidden" id="coinPercent" value="${rsp.coinPercent}">
	<!-- begin -->

	<%--new--%>
	<div class="container">
		<div class="flex w62">
			<div class="title b-bottom">订单确认<span class="hide">订单号：${rsp.buy.buyId}</span></div>
			<div class="con">
				<ul>
					<li>
						<span>${rsp.buy.productName}</span>
						<span class="JInterest">年化收益：<b class="text-red"><fmt:formatNumber value="${rsp.buy.productInterest}" pattern="0.00" />%</b></span>
					</li>
					<li>
						<span>收益天数：<i class="text-dark">${rsp.buy.incomeDays}天</i></span>
						<span>还款方式：<i class="text-dark">${rsp.buy.returnMethodText}</i></span>
					</li>
					<li>
						<span>投资本金：<b class="text-red"><fmt:formatNumber value="${rsp.buy.amount}" pattern="#,##0.00" /></b> 元</span>
						<span class="JWillIncome">预计收益：<b class="text-red"><fmt:formatNumber value="${rsp.buy.incomed}" pattern="#,##0.00" /></b>元</span>
					</li>
				</ul>
			</div>
			<div class="title b-top">还款计划</div>
			<div class="con">
				<table>
					<tr>
						<th>期数</th>
						<th>还款日期</th>
						<th>本金(元)</th>
						<th>利息(元)</th>
					</tr>
					<c:forEach var="item" varStatus="status" items="${rsp.incomes}">
						<tr>
							<td>${status.index + 1}</td>
							<td><fmt:formatDate value="${item.payDate}" pattern="yyyy-MM-dd" /></td>
							<td>
								<c:if test="${(status.index + 1) == fn:length(rsp.incomes)}">
									<fmt:formatNumber value="${item.amount}" pattern="0.00" />
								</c:if>
								<c:if test="${(status.index + 1) != fn:length(rsp.incomes)}">
									-
								</c:if>
							</td>
							<td class="JIncomes" data-value="${item.days}"><fmt:formatNumber value="${item.income}" pattern="0.00" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="flex w38">
			<div class="title">
				使用优惠
				<span>抵用<b class="text-red J_useAmount">0.00</b>元</span>
				<span class="J_addProfit hide">已加息<b class="text-red">1.50%</b></span>
			</div>
			<div class="ticket">
				<ul>
					<!--加息券-->
					<li class="line clearfix">
						<a href="javascript:void(0)" class="checkbox hide J_profitBtn <c:if test="${!empty rsp.coupon}">curr</c:if>"></a>
						<div class="name">加息券</div>
						<div class="select-list pro-list <c:if test="${fn:length(rsp.coupons) != 0}">J_profitList</c:if>">
							<div class="list-default">
								<c:if test="${rsp.couponCount != 0}">
									<div class="JCouponDefault <c:if test="${empty rsp.coupon}">hide</c:if>">
										<p class="num text-red J_SLNum"><b>+</b><fmt:formatNumber value="${rsp.coupon.interest}" pattern="0.00" />%</p>
										<p class="info J_SLInfo">${rsp.coupon.useTypeText}</p>
									</div>
									<div class="choose-normal JCouponNum <c:if test="${!empty rsp.coupon}">hide</c:if>">
										<span class="text-red">${rsp.couponCount}</span>张可用
									</div>
									<div class="choose-normal JCouponDontuse <c:if test="${!empty rsp.coupon}">hide</c:if>"></div>
								</c:if>
								<c:if test="${rsp.couponCount == 0}">
									<div class="choose-normal">
										暂无可用
									</div>
								</c:if>
								<div class="iconfont crow-icon">&#xe606;</div>
							</div>
							<ul>
								<c:forEach var="item" items="${rsp.coupons}">
									<li data-id="${item.id}" data-profit="<fmt:formatNumber value="${item.interest}" pattern="0.00" />"  data-status="${item.status}" class="<c:if test="${item.status == -1}">no-use</c:if> <c:if test="${!empty rsp.coupon && rsp.coupon.id==item.id}">curr</c:if>">
										<p class="num">
											<span class="text-red"><b>+</b><fmt:formatNumber value="${item.interest}" pattern="0.00" />%</span>
										</p>
										<p class="info">
											<span>${item.useTypeText}</span><br/>
											<span>收益天数≥${item.minDays}天</span><br/>
											<fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd" />过期
										</p>
									</li>
								</c:forEach>
								<c:if test="${rsp.couponCount != 0}">
									<li class="dont-use">
										<span>暂不使用</span>
									</li>
								</c:if>
							</ul>
						</div>
						<div class="t-tip JCouponMsg hide">
							该券正在使用中，请至个人中心选择相应订单继续支付或取消订单。 <a href="${selfSite}/zdjf/center/orders" target="_blank" class="text-blue">查看订单>></a>
						</div>
					</li>
					<!--红包券-->
					<li class="line clearfix">
						<a href="javascript:void(0)" class="checkbox J_packetBtn hide <c:if test="${!empty rsp.gift}">curr</c:if>"></a>
						<div class="name">红包券</div>
						<div class="select-list red-bag <c:if test="${fn:length(rsp.gifts) != 0}">J_packetList</c:if>">
							<div class="list-default">
								<c:if test="${rsp.giftCount != 0}">
									<div class="JGiftDefault <c:if test="${empty rsp.gift}">hide</c:if>">
										<p class="num text-red J_SLNum"><fmt:formatNumber value="${rsp.gift.amount}" pattern="0"/>元</p>
										<p class="info J_SLInfo">${rsp.gift.useTypeText}</p>
									</div>
									<div class="choose-normal JGiftNum <c:if test="${!empty rsp.gift}">hide</c:if>">
										<span class="text-red">${rsp.giftCount}</span>张可用
									</div>
									<div class="choose-normal JGiftDontuse <c:if test="${!empty rsp.gift}">hide</c:if>"></div>
								</c:if>
								<c:if test="${rsp.giftCount == 0}">
									<div class="choose-normal">
										暂无可用
									</div>
								</c:if>
								<div class="iconfont crow-icon">&#xe606;</div>
							</div>
							<ul>
								<c:forEach var="item" items="${rsp.gifts}">
									<li data-id="${item.id}" data-packet= "<fmt:formatNumber value="${item.amount}" pattern="0"/>" data-status="${item.status}" class="<c:if test="${item.status == -1}">no-use</c:if> <c:if test="${!empty rsp.gift && rsp.gift.id==item.id}">curr</c:if>"> <!--class="no-use"-->
										<p class="num">
											<span class="text-red"><fmt:formatNumber value="${item.amount}" pattern="0"/><b>元</b></span><br/>
											投资≥<fmt:formatNumber value="${item.maxAmount}" pattern="0"/>元
										</p>
										<p class="info">
											<span>${item.useTypeText}</span><br/>
											<span>收益天数≥${item.maxDays}天</span><br/>
											<fmt:formatDate value="${item.endDate}" pattern="yyyy-MM-dd" />过期
										</p>
									</li>
								</c:forEach>
								<c:if test="${rsp.giftCount != 0}">
									<li class="dont-use">
										<span>暂不使用</span>
									</li>
								</c:if>
							</ul>
						</div>
						<div class="t-tip JGiftMsg hide">
							该券正在使用中，请至个人中心选择相应订单继续支付或取消订单。 <a href="/center/orders" target="_blank" class="text-blue">查看订单>></a>
						</div>
					</li>
					<!--正经值-->
					<c:if test="${null != rsp.stat && rsp.stat.coinBalance != 0 }">
						<li class="line clearfix">
							<%--提示--%>
							<div class="currency-tip hide">
								<div class="tip-con">
									<span class="icon-arrow"></span>
									<span class="icon-arrow01"></span>
									<div class="text-red">单次最大抵用金额为投资本金的${rsp.coinPercent}%</div>
								</div>
							</div>
							<div class="name">正经值</div>
							<input type="text" class="use-currency J_inputUC" <c:if test="${!empty rsp.gift}">disabled="true"</c:if> value="<fmt:formatNumber value="${rsp.coinMax}" pattern="0.00"/>" placeholder="最多可抵用<fmt:formatNumber value="${rsp.coinMax}" pattern="0.00"/>元" data-capital= "${rsp.buy.amount}" data-sxamount= "${rsp.stat.coinBalance}">
							<a href="javascript:void(0)" class="choose-btn J_currencyBtn <c:if test="${empty rsp.gift}">curr</c:if>"></a>
							<div class="t-tip <c:if test="${!empty rsp.gift}">hide</c:if>">
								• 正经值余额：<b class="text-red"><fmt:formatNumber value="${rsp.stat.coinBalance}" pattern="0.00"/></b>元，
								本次可用：<b class="text-red"><fmt:formatNumber value="${rsp.coinMax}" pattern="0.00"/></b>元<br/>
								• 单次最大抵用金额为投资本金的<b class="text-red">${rsp.coinPercent}%</b>
							</div>
						</li>
					</c:if>
				</ul>
			</div>
			<div class="pact"><i class="iconfont">&#xe637;</i>我已阅读并同意<a href="${selfSite}/zdjf/product/agreement/${rsp.buy.productId}?amount=${rsp.buy.amount}" target="_blank" class="text-red">《债权转让协议范本》</a></div>
			<a href="javascript:void(0);" id="submit" class="submit btn-red JPay" data-balance="<fmt:formatNumber value="${rsp.stat.balance}" pattern="0.00"/>">
				确认支付
				<b>
					<fmt:formatNumber value="${rsp.buy.amount}" pattern="0.00"/>
				</b>元
			</a>
		</div>
	</div>

	<%--new--%>
	<input type="hidden" id="relationId" value="${rsp.buy.buyId }">

	<!--开通托管提示弹窗-->
	<div class="alert alert-tip JWait hide">
		<div class="alert-darkbg J_alertClose"></div>
		<div class="eject">
			<div class="title"></div>
			<div class="content">
				<p class="tip-title">支付遇到问题？</p>
				<p class="con">请在新打开的汇付天下页面完成支付</p>
				<p class="pay-question-tip">*请在确认定单后<span>5分钟内</span>完成支付*</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JComplete">已完成</a>
					<a href="javascript:void(0);" class="btn-red-border JError">遇到问题未完成</a>
				</p>
			</div>
		</div>
	</div>

	<input type="hidden" id="quickCardFlag" value="<c:if test="${rsp.userBank != null}">1</c:if>">

	<c:if test="${rsp.userBank != null}">
		<!--已帮快捷卡余额不足时-->
		<div class="alert alert-tip pay-fast JDeposit hide">
			<div class="alert-darkbg J_alertClose"></div>
			<div class="eject">
				<div class="title"><i class="iconfont J_closeDeposit">&#xe631;</i></div>
				<div class="content">
					<p class="con">余额不足，请先充值<b class="J_payBalance">--</b>元</p>
					<p class="pay-question-tip">选择充值方式</p>
					<ul>
						<li class="J_payFast curr">
							<div class="bank-logo"><img src="${selfSite}/zdjf/res/product/images/bank-logo/${rsp.userBank.bankAlias}.png" alt=""></div>
							<div class="cot">
								<p class="na">${rsp.userBank.bankName}　<span class="tit">储蓄卡</span></p>
								<p class="num">${rsp.userBank.bankNo}</p>
							</div>
							<img src="${selfSite}/zdjf/res/product/images/bank-logo/gou.png" class="default-card" alt="">
						</li>
					</ul>
					<p>
						<a href="javascript:void(0);" class="pay-bank J_payBank">
							使用其他银行卡网银支付
							<img src="${selfSite}/zdjf/res/product/images/bank-logo/gou.png" alt="">
						</a>
					</p>
					<p>
						<a href="javascript:void(0);" class="btn-red J_payBtn">下一步</a>
					</p>
				</div>
			</div>
		</div>
	</c:if>



	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>

</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/product/js/pays.js?t=20160804"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
</html>