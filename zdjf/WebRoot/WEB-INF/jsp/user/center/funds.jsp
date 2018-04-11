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
	<title>资金明细-我的财富-专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/> 
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/statements.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/kkpager_gray.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>
	<div class="body clearfix">
		<jsp:include page="center_nav.jsp"></jsp:include>

		<!-- 内容 -->
		<div class="body-right">
			<p class="title">资金明细</p>
			<p class="search-time JDateType">
				起止时间： 
				<span data-id="0"
					class="date <c:if test="${rsp.dayType == 0}">select</c:if>">全部</span>
				<span data-id="1"
					class="date <c:if test="${rsp.dayType == 1}">select</c:if>">最近3天</span>
				<span data-id="2"
					class="date <c:if test="${rsp.dayType == 2}">select</c:if>">1个月</span>
				<span data-id="3"
					class="date <c:if test="${rsp.dayType == 3}">select</c:if>">2个月</span>
				<span data-id="4"
					class="date <c:if test="${rsp.dayType == 4}">select</c:if>">3个月</span>
			</p>
			<p class="search-time JType">
				交易类型： <span data-id="0"
					class="date <c:if test="${rsp.fundsType == 0}">select</c:if>">全部</span>
				<span data-id="2"
					class="date <c:if test="${rsp.fundsType == 2}">select</c:if>">提现</span>
				<span data-id="3"
					class="date <c:if test="${rsp.fundsType == 3}">select</c:if>">充值</span>
				<span data-id="1"
					class="date <c:if test="${rsp.fundsType == 1}">select</c:if>">购买</span>
				<span data-id="4"
					class="date <c:if test="${rsp.fundsType == 4}">select</c:if>">回款</span>
				<span data-id="5"
					class="date <c:if test="${rsp.fundsType == 5}">select</c:if>">利息</span>
			</p>
			<%-- <p class="search-type">
	   			交易类型：
	   			<span <c:if test="${rsp.fundsType == 0}">class="select"</c:if>>全部</span>
	   			<span <c:if test="${rsp.fundsType == 1}">class="select"</c:if>>购买</span>
	   			<span <c:if test="${rsp.fundsType == 2}">class="select"</c:if>>提现</span>
	   			<span <c:if test="${rsp.fundsType == 3}">class="select"</c:if>>充值</span>
	   			<span <c:if test="${rsp.fundsType == 4}">class="select"</c:if>>回款</span>
	   			<span <c:if test="${rsp.fundsType == 5}">class="select"</c:if>>利息</span>
   			</p> --%>
			<table>
				<tr>
					<th width="108">日期</th>
					<th width="136">流水号</th>
					<th width="82">类型</th>
					<th width="116">金额（元）</th>
					<th width="90">余额（元）</th>
					<th width="72">状态</th>
				</tr>
				<c:forEach var="funds" items="${rsp.pageData.datas }">
					<tr>
						<td><fmt:formatDate value="${funds.createTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>${funds.fundsId}</td>
						<td>${funds.operateTypeText}</td>
						<td
							class="padding <c:if test="${funds.action == 1}">income</c:if><c:if test="${funds.action == 2}">pay</c:if>">
							<c:if test="${funds.action == 1}">+</c:if>
							<c:if test="${funds.action == 2}">-</c:if>
							<fmt:formatNumber value="${funds.amount }" pattern="#,##0.00" type="number" />
						</td>
						<td class="padding"><fmt:formatNumber value="${funds.balance }" pattern="#,##0.00" type="number" /></td>
						<td class="reason">
							${funds.statusText}
							<c:if test="${funds.status == -3}">
								<a href="javascript:void(0);">[原因]
									<div class="tip-con">
									 <c:if test="${empty funds.remark}">
										<span>我们将尽快联系您告知原因，也可拨打400-690-9898咨询</span>
									 </c:if>
									 <c:if test="${! empty funds.remark}">
										<span>${funds.remark}</span>
									 </c:if>
										<span class="icon-arrow"></span>
										<span class="icon-arrow01"></span>
									</div>
								</a>
							</c:if>
							<c:if test="${funds.status == 4 || funds.status == 3}"	>
								<a href="javascript:void(0);" class="pay-close ">[详情]
									<div class="tip-con repeal">
										<div>
											<i class="iconfont">&#xe635;</i>
											预计到账时间
											<b><fmt:formatDate value="${funds.withdrawDate}" pattern="yyyy年MM月dd日"/>(<fmt:formatDate value="${funds.withdrawDate}" pattern="E"/>)</b>
											<c:if test="${funds.operateType==21&&funds.status==3}">
												<span class="btn-red J_repeal" data-fundsid="${funds.fundsId}" data-relationid="${funds.relationId}" >撤销提现</span>
											</c:if>
										</div>
										<span class="icon-arrow"></span>
										<span class="icon-arrow01"></span>
									</div>
								</a>
							</c:if>


						</td>
					</tr>
				</c:forEach>
			</table>
			<p class="page">
				<jsp:include page="../comm/pager.jsp"></jsp:include>
			</p>
			
			<!-- 暂无资金明细 -->
			<c:if test="${empty rsp.pageData || fn:length(rsp.pageData.datas) == 0}">
				<div class="nothing-show">
					<p class="invitation-empty">
						<i class="icon-invent"></i>
						<span>暂无资金明细</span>
					</p>
				</div>
			</c:if>
		</div>
	</div>
    <div id="alert" style="z-index:-1;"></div>
	<!--弹窗-->
	<div class="alert alert-tip hide" id="repealAlert">
		<div class="alert-darkbg"></div>
		<div class="eject">
			<div class="title"></div>
			<div class="content">
				<p class="tip-title" style="margin-bottom: 20px;">是否确认撤销提现？</p>
				<p>
					<a href="javascript:void(0);" class="btn-red repeal">确定</a>
					<a href="javascript:void(0);" class="btn-red-border J_alertClose">取消</a>
				</p>
			</div>
		</div>
	</div>

	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>

</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/funds.js"></script>
</html>