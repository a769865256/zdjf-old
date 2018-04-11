<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zdjf.domain.product.Product" %>
<%@ page import="com.zdjf.domain.product.ProductBuy" %>
<%@ page import="com.zdjf.domain.user.UserCoupon" %>
<%@ page import="com.zdjf.domain.user.UserGift" %>
<%@ page import="com.zdjf.domain.product.ProductIncome" %>
<%@ page import="com.zdjf.domain.fund.UserFundStat" %>
<%@ page import="com.zdjf.util.RoofNumberUtils" %>
<%@page import="com.zdjf.util.DateUtil"%>
<%@ page import="com.zdjf.domain.product.ProductBuyIncome" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserFundStat ufs = (UserFundStat)request.getAttribute("userFundStat");
Product product = (Product)request.getAttribute("product");
List productIncomeList = (List)request.getAttribute("productIncomeList");
UserCoupon uc = (UserCoupon)request.getAttribute("uc");
UserGift ug = (UserGift)request.getAttribute("ug");
String amount = (String)request.getAttribute("amount");
String coinState = (String)request.getAttribute("coinState");
Long day = (product.getEnd_date().getTime()-new Date().getTime())/(1000*60*60*24)+1; 
if(day<0) day =0L;
double income = (product.getIncome())/100;
String ucId = "";
String ugId = "";
if(uc!=null && uc.getInterest()!=null){
	income = income+uc.getInterest()/100;
	ucId = uc.getId().toString();
}
double availableCon = ufs.getCoin_balance();
double useCon = 0.0;
double gift_amount = 0.0;
if(ug==null){
	useCon = Double.valueOf(amount) / 100;
	if(useCon>availableCon){
		useCon = availableCon;
	}
}else {
	gift_amount = ug.getAmount();
	ugId = ug.getId().toString();
}
String error = (String)request.getAttribute("error");
String totalljsy = RoofNumberUtils.formatString(Double.valueOf(amount)*income/360*day);
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
		<jsp:include page="../common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="pro_detail">
		<!-- 债转详情 -->
		<div class="ovderconfirm">
			<div class="ca_lf">
				<h3 class="ddqd">订单确认</h3>
				<div class="confirm">
					<p><span>项目名称：<i><%=product.getProduct_code() %></i></span><span>年化收益：<i><%=RoofNumberUtils.formatString(income*100)%>%</i></span></p>
					<p class="firm"><span>收益天数：<i><%=day %>天</i></span><span>还款方式：<i>按日计息，按月付息，到期还本</i></span></p>
					<p><span>投资本金：<i><%=amount %>元</i></span><span>预计收益：<i><%=totalljsy %>元</i></span></p>
				</div>
				<h3>还款计划</h3>
				<table class="layui-table" lay-skin="nob">
					<thead>
						<tr>
							<th>期数</th>
							<th>还款日期</th>
							<th>本金(元)</th>
							<th>利息(元)</th>
						</tr>
					</thead>
					<tbody>
					<%
						if(productIncomeList!=null && productIncomeList.size()>0){
							Date sxday = new Date();
							List<ProductBuyIncome> pbIncomes = new ArrayList<>();
							boolean flag = false;
							for(int i=0;i<productIncomeList.size();i++) {
								ProductIncome productIncome = (ProductIncome) productIncomeList.get(i);
								int lday;
								if (0 == i) {
									lday = DateUtil.between(sxday, productIncome.getEnd_date());//T+1开始计息，故不用加1天
									if (lday <= 0) {
										flag = true;
										continue;
									}
								} else {
									if (flag) {
										lday = DateUtil.between(sxday, productIncome.getEnd_date());
										if (lday <= 0) {
											flag = true;
											continue;
										} else {
											flag = false;
										}
									} else {
										//T+1开始计息
										lday = DateUtil.between(productIncome.getStart_date(),productIncome.getEnd_date())+1;
									}
								}
								ProductBuyIncome one = new ProductBuyIncome();
								one.setPay_date(productIncome.getEnd_date());
								one.setDays(lday);
								pbIncomes.add(one);
							}
							for (int j=0;j<pbIncomes.size();j++) {
							    ProductBuyIncome pbIncome = pbIncomes.get(j);
					 %>
						<tr>
							<td><%=j+1 %></td>
							<td><%= DateUtil.formatDate(pbIncome.getPay_date()) %></td>
							<td><% if(pbIncomes.size()==j+1) out.println(Double.valueOf(amount)); else out.println("0.0"); %></td>
							<td><%=RoofNumberUtils.formatString(Double.valueOf(totalljsy)/day*pbIncome.getDays())%> </td>
						</tr>
						<%

						}}else { %>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<%} %>
					</tbody>
				</table>
			</div>
			<!-- 已登录 -->
			<div class="ca_rt">
			<form id="form1" class="layui-form" name="form1" method="post" action="<%=path%>/pay/topay.action">
				<h3>
					账户可用余额：<span><%=ufs.getBalance() %></span>
					<a href="javascript:;">
					优惠抵现：<span><%if(coinState.equals("1")) { %><%= gift_amount+useCon %>
						<%}else {%> <%= gift_amount %> <%}%>元</span>
					</a>
				</h3>
				<div class="voucher">
					<p>加息券：<span><%if(uc!=null) { %><%=uc.getInterest()%>%<%}else{ %>未选择加息券<%} %></span></p>
					<%if(coinState.equals("0")) { %><p>红包券：<span><%if(ug!=null) { %>￥<%=ug.getAmount()%><%}else{ %>未选择红包券<%} %></span></p><% } %>
					<%if(coinState.equals("1")) { %><p>正经值：<span>￥<%=useCon%></span></p><% } %>
					<%if(coinState.equals("0") && ug==null) { %><p>正经值：<span>未选择正经值</span></p><% } %>
					<p>支付金额：
						<span>￥
							<%if(coinState.equals("1")) { %><%=Double.valueOf(amount)-gift_amount-useCon %>
							<%}else {%> <%=Double.valueOf(amount)-gift_amount %> <%}%>
						</span>
					</p>
				</div>
				<input type="hidden" id="amount" name="amount" value="<%=amount%>">
				<input type="hidden" id="userCouponId" name="userCouponId" value="<%=ucId%>">
				<input type="hidden" id="userGiftId" name="userGiftId" value="<%=ugId%>">
				<input type="hidden" id="productId" name="productId" value="<%=product.getId()%>">
				<input type="hidden" id="useCon" name="useCon" value="<%=useCon%>">
				<input type="hidden" id="balance" name="balance" value="<%=ufs.getBalance() %>">
				<input type="hidden" id="coinState" name="coinState" value="<%=coinState %>">
				<%if(ug==null){ %>
				<div class="zhengjing hide">
					<p>正经值：<input type="text" id="interest" name="interest" onkeyup="tj(this.value)" value="<%=useCon %>" placeholder="开启正经值使用抵扣优惠">
						<input type="checkbox" name="switch" lay-skin="switch">
					</p>
				</div>
				<div class="ovderlist hide">
					<p>
						<i class="iconfont">&#xe646;</i>
						<span>正经值余额：<i><%=availableCon %>元</i>,</span>
						<span>本次可用：<i><%=useCon %>元</i></span>
					</p>
					<p>
						<i class="iconfont">&#xe646;</i>
						<span>单次最大抵用金额为投资本金的1.0%</span>
					</p>
				</div>
				<%} %>
				<div class="error" id="error">
					<%=error!=null?error:"" %>
				</div>
				<div class="ovderbtn">
					<a href="javascript:dosubmit();" id="sf_str">立即支付</a>
				</div>
				<div class="agreement">
					<p><input type="radio" title="我已阅读并同意" name="radio" lay-skin="radio" checked="checked"><a href="<%=path%>/product/agreement.action?type=0&proId=<%=product.getId() %>&amt=<%=amount %>&totalIncome=<%=totalljsy%>&interest=<%=RoofNumberUtils.formatString(income*100)%>" target="_blank">《债权转让协议范本》</a></p>
				</div>
				</form>
			</div>
		</div>
	</div>
	<!-- content end-->


	<!-- footer -->
	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
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
		function tj(str){
			if (isNaN(str)) {
			    $("#interest").val("");
		　　　　return ;
		　　}
			var useCon = $("#useCon").val();
			if(parseFloat(useCon)<parseFloat(str)){
				$("#interest").val(useCon);
			}
			var interest  = $("#interest").val();
			if(interest==null || interest==''){
				interest = 0;
			}
			var amount = parseFloat($("#amount").val())-parseFloat(interest);
			$("#sf_str").html("实付："+amount+"，立即支付");
		}
		function dosubmit(){
			var interest = parseFloat($("#interest").val());
			var balance = parseFloat($("#balance").val());
			var amount = parseFloat($("#amount").val());
			var gift_amount = '<%=gift_amount%>';
			var coinState = $("#coinState").val();
			var ll;
			if (coinState == 1) {
                ll = parseFloat(amount-interest-parseFloat(gift_amount));
			} else {
                ll = parseFloat(amount-parseFloat(gift_amount));
			}
			if(balance<ll){
				$("#error").html("余额不足");
				return;
			}
			$("#form1").submit();
		}
		/*判断余额和支付金额*/
        var voucher = $(".voucher p:last-child span").html().replace(/[^0-9]/,"")
        if(parseFloat($(".ca_rt h3 span").html()) >= parseFloat(voucher)){
            $(".ovderbtn #sf_str").html("立即支付");
        } else {
            $(".ovderbtn #sf_str").html("可用余额不足，请充值");
            $("#sf_str").attr("href","<%=path%>/pay/charge.action");
        }
    </script>
</body>
</html>
