<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<title>银行卡管理-我的财富-专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/> 
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/approve.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
</head>

<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>

	<div class="body">
		<jsp:include page="center_nav.jsp"></jsp:include>
		<input type="hidden" id="approve_status" name="approve_status" value="${user.realNameAuth}">
		<!-- 内容 -->
	    <div class="body-right">
	        <p class="title">银行卡管理</p>
	        <div class="con">
				 <c:if test="${data != null }">
					 <c:forEach var="item"  items="${data}" varStatus="status">
						<!-- 已认证 -->
						 <div class="approve-info">
							 <div class="card">
								 <p class="pic"><img src="${selfSite}/zdjf/res/product/images/banks/${item.bankAlias}.png" alt=""><span><i></i>储蓄卡</span></p>
								 <p class="line">${item.bankNo}</p>
								 <p class="line">${item.cardUserName}</p>
								 <c:if test="${status.index == 0}">
									 <div class="service-phone">默认取现卡</div>
								 </c:if>
							 </div>
						 </div>
					 </c:forEach>
				</c:if>
				<!-- 未认证 -->
				<a href="javascript:void(0);" class="default-card J_defaultCard" data-flag="${quickCardFlag}">
					<p class="icon"><span></span></p>
					<p>绑定银行卡</p>
				</a>
	        </div>
	    </div>
	</div>

	<!--设置托管账户弹窗-->
	<div class="alert alert-tip JChinapnrRegister hide">
		<div class="alert-darkbg J_alertClose"></div>
		<div class="eject">
			<div class="title"><i class="icon-close J_alertClose">×</i></div>
			<div class="content">
				<p class="tip-title">您尚未进行实名认证</p>
				<p class="con">
					实名认证（资金托管）能有效保障您的资金安全，<br/>使资金进出均受银行监管。 <a href="/active/hftx.html" class="text-blue btn-more" target="_blank">了解更多>></a>
				</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JApprove">前去认证</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 设置托管账户弹窗 -->
	<div class="alert alert-tip JChinapnrRegisterWait hide">
		<div class="alert-darkbg J_alertClose"></div>
		<div class="eject">
			<div class="title"><i class="icon-close J_alertClose">×</i></div>
			<div class="content">
				<p class="tip-title">账户实名认证</p>
				<p class="con">正在进行实名认证，请耐心等待</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JKnow">知道了</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 设置托管账户弹窗 -->
	<div class="alert alert-tip JQuickCard hide">
		<div class="alert-darkbg J_alertClose"></div>
		<div class="eject">
			<div class="title"><i class="icon-close J_alertClose">×</i></div>
			<div class="content">
				<p class="tip-title">提示</p>
				<p class="con">您已绑定快捷卡，不能换绑。<br>若需解绑，请致电客服热线400-690-9898进行咨询。</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JKnow">确定</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 设置托管账户等待回调弹窗 -->
	<div class="alert alert-tip JChinapnrRegisterBack hide">
		<div class="alert-darkbg"></div>
		<div class="eject">
			<div class="title"></div>
			<div class="content">
				<p class="tip-title">账户实名认证</p>
				<p class="con">请在新打开的汇付天下页面完成实名认证</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JRegisterComplete">已完成</a>
					<a href="javascript:void(0);" class="btn-red-border JRegisterError">遇到问题未完成</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 设置托管账户等待回调弹窗 -->
	<div class="alert alert-tip JBindBack hide">
		<div class="alert-darkbg"></div>
		<div class="eject">
			<div class="title"></div>
			<div class="content">
				<p class="tip-title">绑定银行卡</p>
				<p class="con">请在新打开的汇付天下页面完成银行卡绑定</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JBindComplete">已完成</a>
					<a href="javascript:void(0);" class="btn-red-border JBindError">遇到问题未完成</a>
				</p>
			</div>
		</div>
	</div>

	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
	<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/banks.js"></script>
</body>
</html>
