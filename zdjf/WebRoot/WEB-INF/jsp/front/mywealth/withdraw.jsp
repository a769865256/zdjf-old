<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zdjf.domain.fund.UserFundStat" %>
<%@ page import="com.zdjf.domain.user.UserBank" %>
<%@ page import="com.zdjf.domain.fund.Bank" %>
<%@page import="com.zdjf.util.DateUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
	UserFundStat userFundStat = (UserFundStat)request.getAttribute("userFundStat");
	UserBank ub = (UserBank)request.getAttribute("userBank");
	Bank bank = (Bank)request.getAttribute("bank");
	String card = ub.getBank_no().substring(ub.getBank_no().length()-4, ub.getBank_no().length());
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
		<div class="we_rt">
			<!-- 我的财富 -->
			
			<!-- 我的财富》提现 -->
			<div class="with">
				<div class="with_head">
					<h3>提现</h3>
				</div>
				<div class="withbox">
					<ul>
						<li>
							<span>当前可提现余额:</span>
							<span><i><%=userFundStat.getBalance() %></i>元</span>
							<input type="hidden" id="balance" value="<%=userFundStat.getBalance() %>">
						</li>
						<li class="carry">
							<span>提现金额:</span>
							<input type="text" id="amount" name="amount" onkeyup="tx_balance(this.value);" placeholder="请输入提现金额">
							<span>提现后余额<i id="tx_balance"><%=userFundStat.getBalance() %></i>元</span>
						</li>
						
						<li>
							<a href="javascript:" class="withbtn">申请提现</a>
							<span class="withtxt">预期到账时间:<%=DateUtil.formatDateMd(DateUtil.addDate(new Date(), 1)) %></span>
						</li>
					</ul>
					
				</div>
			</div>
			<!-- 我的财富》充值 -->
			
		</div>
	</div>

	<!-- 申请提现弹窗 -->
	<div id="with_box" style="display:none;">
		<h3>提现遇到问题？<a href="javascript:" class="with_close iconfont">&#xe64e;</a></h3>
		<div class="with_con">
			<p>提现完成前请不要关闭此窗口，提现<br>完成后请根据您的需要点击下面的按钮</p>
		</div>
		<div class="with_btn">
			<a href="<%=path%>/myFunds.action?type=21" class="with_see">查看提现记录</a>
			<a href="<%=path%>/investGuide/5/help.action" class="with_pay">提现遇到问题</a>
		</div>
	</div>

	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<!-- footer end -->
    <script src="<%=path%>/module/jquery/jquery.min.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/module/layui/layui.js"></script>
    <script src="<%=path%>/module/echarts/echarts.min.js"></script>
    <script src="<%=path%>/js/front/wealth.js"></script>
	<script type="text/javascript">
		var path = '<%=path%>';
		function reload(){
			 location.reload();
		}
		function tx_balance(va){
			if (isNaN(va)) {
				$("#amount").val("");
			return ;
			}
			newVal = va.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
			newVal = va.replace(/^\./g,""); //验证第一个字符是数字
			newVal = va.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
			newVal = va.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			newVal = va.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
			$("#amount").val(newVal);
			var mybalance1 = parseFloat($("#balance").val());
			if($("#amount").val() == ''){
				$("#tx_balance").html(mybalance1);
			} else {
				$("#tx_balance").html(parseFloat(mybalance1-parseFloat(newVal)));
			}
			/*提现后金额和提现金额对比，让按钮变颜色*/
			var val = Number($("#tx_balance").html());
			if( val < 0){
				$(".withbtn").css({"background":"#c7c7c7","cursor": "no-drop"});
			} else {
				$(".withbtn").removeAttr("style");
			}
		}
	</script>
</body>
</html>