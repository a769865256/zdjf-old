<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<link rel="stylesheet" href="<%=path%>/css/front/index.css">
<link rel="stylesheet" href="<%=path%>/css/front/wealth.css">
</head>
<body>
	<div class="discount we_rtbox">
	<div class="dis_tav">
		<p>
			<span class="dis_active">优惠券管理</span> <span>兑换中心</span>
		</p>
		<div class="dis_tav_txt">
			<span>正经值余额: <i>${content.coinBalance }</i></span> <a href="javascript:">查看</a>
		</div>
	</div>
	<div class="dis_tab">
		<div class="dis_tab_box">
			<div class="dis_cher_ticket">
				<a href="javascript:">红包券(<i>${content.giftCount }</i>)
				</a> <a href="javascript:">加息券(<i>${content.couponCount }</i>)
				</a> <a href="javascript:" class="active">提现券(<i>${content.withdrawCount }</i>)
				</a>
			</div>
			<div class="dis_cher_list">
				<div class="dis_cher_overdue <c:if test="${empty content.page.dataList }">hide</c:if>">
					<a href="javascript:" class="active">未使用</a> <a href="javascript:">已使用</a>
					<a href="javascript:">已过期</a> <a href="javascript:">即将过期</a>
				</div>
				<ul>
					<c:forEach var="list" items="${content.page.dataList }">
					<li>
						<div class="listbox">
							<div class="list_state state1"></div>
							<div class="list_stlt">
								<p>投资≥${list.max_amount }元 收益天数≥${list.max_days }天</p>
								<p>${list.use_type }</p>
								<p>有效时间：${list.start_date }-${list.end_date }</p>
							</div>
						</div>
					</li>
					</c:forEach>
				</ul>
				<div class="no_listbox <c:if test="${!empty content.page.dataList }">hide</c:if>">
					<!-- 无红包时显示 -->
				</div>
				<!-- 分页 -->
				<div id="dis_page"></div>
			</div>
		</div>
		<div class="dis_tab_box" style="display: none;">
			<div class="dis_cher_ticket">
				<a href="javascript:" class="active">红包券</a> <a href="javascript:">加息券</a>
				<a href="javascript:">提现券</a>
			</div>
			<div class="dis_cher_list">
				<ul>
					<li>
						<div class="listtent tent1">
							<h3>13元红包</h3>
							<p>
								投资≥1000元 收益天数≥20天<br>限非新手标<br>领取后15天有效
							</p>
							<a href="javascript:">15.00正经值兑换</a>
						</div>
					</li>
					<li>
						<div class="listtent tent2">
							<h3>0.50%加息券</h3>
							<p>
								投资≥1000元 收益天数≥20天<br>限非新手标<br>领取后15天有效
							</p>
							<a href="javascript:">15.00正经值兑换</a>
						</div>
					</li>
					<li>
						<div class="listtent tent3">
							<h3>提现券</h3>
							<p>
								投资≥100元 收益天数≥5天<br>限非新手标<br>领取后15天有效
							</p>
							<a href="javascript:">15.00正经值兑换</a>
						</div>
					</li>
					<li>
						<div class="listtent tent1">
							<h3>13元红包</h3>
							<p>
								投资≥1000元 收益天数≥20天<br>限非新手标<br>领取后15天有效
							</p>
							<a href="javascript:">15.00正经值兑换</a>
						</div>
					</li>
					<li>
						<div class="listtent tent1">
							<h3>13元红包</h3>
							<p>
								投资≥1000元 收益天数≥20天<br>限非新手标<br>领取后15天有效
							</p>
							<a href="javascript:">15.00正经值兑换</a>
						</div>
					</li>
					<li>
						<div class="listtent tent1">
							<h3>13元红包</h3>
							<p>
								投资≥1000元 收益天数≥20天<br>限非新手标<br>领取后15天有效
							</p>
							<a href="javascript:">15.00正经值兑换</a>
						</div>
					</li>
				</ul>
				<!-- 分页 -->
				<div id="dis_page2"></div>
			</div>
		</div>
	</div>
	</div>
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>
	<script src="<%=path%>/module/echarts/echarts.min.js"></script>
	<script src="<%=path%>/js/front/wealth.js"></script>
</body>
</html>