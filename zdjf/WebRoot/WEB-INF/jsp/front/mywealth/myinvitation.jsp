<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String showPage=(String)request.getAttribute("showPage");
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
			<!-- 邀请有礼 -->
			<div class="invitation we_rtbox">
				<h3>邀请好友</h3>
				<div class="friend">
					<div class="f_banner"><img src="<%=path%>/images/front/img/footer/code2.png" alt=""></div>
					<div class="f_code">
						<ul>
							<li><span>我的邀请码:<i>${invite_code }</i></span></li>
							<li><span>分享给朋友:</span></li>
							<li>
								<a href="javascript:" class="iconfont wx">&#xe62a;</a>
								<a href="javascript:" class="iconfont qq">&#xe617;</a>
								<a href="javascript:" class="iconfont qqkj">&#xe65e;</a>
								<a href="javascript:" class="iconfont xl">&#xe60e;</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="copy_link">
					<span>邀请链接:</span><input id="url" type="text" value="https://www.zdjfu.com/user/register?code=t3Hazd90" readonly="true"><a href="javascript:" class="copy_url_btn">复制链接</a>
					<p>共邀请 <i>${invited_num }</i> 人注册，被邀请人累计投资 <i>${invited_amount }</i> 元，您获得正经值奖励 <i>${invited_amount*0.002 }</i> 元 </p>
				</div>
				<div class="friend_list">
					<h3>邀请好友列表</h3>
					<table>
						<thead>
							<tr>
						      	<th>用户名</th>
								<th>投资时间</th>
								<th>投资项目</th>
								<th>投资金额(元)</th>
								<th>贡献正经值</th>
						    </tr>
						</thead>
						<tbody>
							<c:forEach var="line"  items="${detail.dataList }">
							<tr>
								<th>${line.phone }</th>
								<th><fmt:formatDate value="${line.pay_time }" type="both" pattern="yyyy年MM月dd日" /></th>
								<th>${line.product_name }期</th>
								<th><fmt:formatNumber value="${line.amount }" type="currency"
									pattern="0.00" /></th>
								<th>
								<fmt:formatNumber value="${line.amount*0.002 }" type="currency"
									pattern="0.00" />
								</th>
							</tr>
							</c:forEach>
						</tbody>
						<tfoot style="display: none;">
						    <tr>
						      	<td colspan="5">
						      		<div class="norecord"></div>
								</td>
						    </tr>
						</tfoot>				
					</table>
					<!-- 分页 -->
					<%=showPage %>
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
	<script type="text/javascript">
		/*邀请好友订单有无记录显示状态*/
		if($(".friend_list table tbody tr").length == 0){
			$(".friend_list table tfoot").show();
			$(".friend_list .cap_page").hide();
		} else {
			$(".friend_list table tfoot").hide();
			$(".friend_list .cap_page").show();
		}
	</script>
</body>
</html>
