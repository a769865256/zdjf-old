<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
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
		<p>为了确保您的正常投资和资金安全，请开通上海银行存管账户。<a href="javascript:">立即开通</a></p>
	</div>
</div>
<div class="wealth">
	<jsp:include page="./left.jsp"></jsp:include>
	<div class="we_rt">
		<!-- 账户设置 -->
		<input type="hidden" id="path" value="<%=path%>"/>
		<div class="account we_rtbox">
			<div class="ac_banner"></div>
			<div class="ac_personal">
				<h3>个人信息</h3>
				<div class="perlist">手机号码
					<p><span class="phoneNub">${user.phone}</span><a href="javascript:">已注册</a></p>
				</div>
				<div class="perlist">登录密码
					<p><span>已设置</span><a href="javascript:" class="pass_modify">修改</a></p>
				</div>
			</div>
			<div class="ac_personal">
				<h3>安全中心</h3>
				<div class="perlist">实名认证
					<c:if test="${user.real_name_auth!=1 || user.status < 3}"><p class="no_rz"><a href="<%=path%>/toNewAudit.action" class="authen">开通银行存管</a></p></c:if>
					<c:if test="${user.real_name_auth==1}"><p style=""><em><i class="name">${user.real_name}</i><i class="IDcard">${user.idcard_no}</i></em><c:if test="${user.status > 2}"><i><a href="javascript:" class="look">已实名</a></i></c:if></p></c:if>
				</div>
				<div class="perlist">银行卡
					<c:if test="${user.real_name_auth!=1 || user.status < 2}"><p class="no_band" ><a href="<%=path%>/toNewAudit.action" class="go_unbund_btn">开通银行存管</a></p></c:if>
					<c:if test="${user.status==2}"><p class="no_band" ><em><i class="bankCard">${userBanks[0].bank_no}</i></em><i><a href="<%=path%>/toNewAudit.action" class="go_nounbund_btn">开通银行存管</a></i></p></c:if>
					<c:if test="${user.status==3 || user.status==4}"><p style=""><em><i class="bankCard">${userBanks[0].bank_no}</i></em><i><a href="<%=path%>/toSetBankCard.action" class="go_nounbund_btn">设置</a></i></p></c:if>
				</div>
				<div class="perlist">支付密码
					<c:if test="${user.real_name_auth!=1 || user.status < 3}"><p class="no_sz" ><a href="<%=path%>/toNewAudit.action" class="set_paywad">开通银行存管</a></p></c:if>
					<%--<c:if test="${user.status == 2}"><p><a href="<%=path%>/pay_password.action" class="back_paywad">设置</a></p></c:if>--%>
					<c:if test="${user.status==4 || user.status==3}"><p><a href="<%=path%>/modPayPassword.action"  class="back_paywad">修改</a><span style="width: auto;color: #999;margin: 0 10px;">/</span><a href="<%=path%>/backPayPassword.action" class="find_paywad">找回</a></p></c:if>
				</div>
				<!-- <div class="perlist">交易免密
                    <p class="no_sz" style="display: none;"><a href="javascript:" class="set_jymm">开通银行存管</a></p>
                    <p><span>已设置</span><a href="javascript:" class="look_jymm">查看/解除</a></p>
                </div>
				<div class="perlist">风险测评
					<p class="no_pg" style="display: none;"><a href="javascript:" class="go_pg">去评估</a></p>
					<p><span>平衡性</span><a href="../informationDisclosure/risk.html" class="again_pg">重新评估</a></p>
				</div>-->
			</div>
		</div>
	</div>
</div>
<!-- content end-->

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path%>/module/jquery/jquery.min.js"></script>
<script src="<%=path%>/module/sticky-header.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script src="<%=path%>/js/front/wealth.js"></script>
<script type="text/javascript">
	/*手机号码显示前三后四，其他换成星星*/
	if($(".phoneNub").html().length > 0){
        var newPhone = $(".phoneNub").html().replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');
        $(".phoneNub").html(newPhone);
	}

	/*实名认证的名字显示后一，其他换成星星*/
	if($(".name").html().length > 0){
        var newName = $(".name").html().replace(/.(?=.)/g, '*');
        $(".name").html(newName);
	}

	/*实名认证的身份证显示前三后四，其他换成星星*/
	if($(".IDcard").html().length > 0){
        var newIDcard = $(".IDcard").html().replace(/(\d{3})\d{11}(\w{4})/,"$1****$2");
        $(".IDcard").html(newIDcard);
	}

	/*银行卡显示后四，其他换成星星*/
	if($(".bankCard").html().length > 0){
        var newBankCard = $(".bankCard").html().replace(/^(.)+(\w{4})$/g,"****$2");
        $(".bankCard").html(newBankCard);
	}

</script>
</body>
</html>