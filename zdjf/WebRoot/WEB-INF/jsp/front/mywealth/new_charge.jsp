<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zdjf.domain.fund.UserFundStat" %>
<%@ page import="com.zdjf.domain.user.UserBank" %>
<%@ page import="com.zdjf.domain.fund.Bank" %>
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

		<!-- 我的财富》充值 -->
		<div class="recharge">
			<div class="charge_head">
				<h3>充值</h3>
			</div>
			<div class="retab">
				<div class="tabbox">
					<div class="tab">
						<form method="post" id="kjpay" action="<%=path%>/pay/toBankPay.action" target="_blank">
							<ul class="kjzf">
								<li class="dqye">
									<span>当前余额:</span>
									<span class="money"><i><%=userFundStat.getBalance() %></i>元</span>
									<input type="hidden" id="balance" value="<%=userFundStat.getBalance() %>">
								</li>
								<li>
									<span>充值金额:</span>
									<input type="text" id="amount" name="amount" onkeyup="balances(this);" placeholder="请输入充值金额"/>
									<span class="money">充值后金额<i id="now_balance"><%=userFundStat.getBalance() %></i>元</span>
									<input type="hidden" id="czh_balance"/>
								</li>
								<%--<li class="bankbox">
									<span>充值银行:</span>
									<input type="text">
									<p>单笔充值限额<%=bank.getSingle_max() %>元,每日充值限额<%=bank.getDay_max() %>元</p>
									<div class="banklogo">
										<img src="<%=path%>/images/front/img/bank/<%=bank.getNum() %>.png" alt="<%=bank.getName() %>">
										<span>尾号<i><%=card %></i></span>
										<input type="hidden" name="pay_type" value="1" />
										<input type="hidden" id="fundsId" name="fundsId" value="" />
									</div>
								</li>--%>
								<li>
									<a href="javascript:" date-cz="1" class="rc_btn">充值</a>
									<div class="sle">
										<%if(userFundStat.getUser_id()==98999 || userFundStat.getUser_id()==88888888){ %>
										<select id="account_identity" name="account_identity">
											<option value="101">红包户</option>
											<option value="102">收益户</option>
											<option value="103">风险准备金</option>
										</select>
										<%} %>
									</div>
									<p>*温馨提示：快捷充值和网银充值入口合并。</p>
								</li>
							</ul>
						</form>
					</div>
				</div>
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
<script src="<%=path%>/module/echarts/echarts.min.js"></script>
<script src="<%=path%>/js/front/wealth.js"></script>
<script type="text/javascript">
	var path = '<%=path%>';
    function balances(obj){
        obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
        var mybalance = parseFloat($("#balance").val());
        if($("#amount").val() == ''){
            $("#now_balance").html(mybalance);
            $("#czh_balance").val(mybalance);
        } else {
            $("#now_balance").html((mybalance+parseFloat(obj.value)));
            $("#czh_balance").val((mybalance+parseFloat(obj.value)));
        }
    }
</script>
</body>
</html>