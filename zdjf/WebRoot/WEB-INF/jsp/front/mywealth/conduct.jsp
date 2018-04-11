<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>正道金服</title>
	<!-- reset/iconfont -->
</head>
<body>
	<!-- header -->
	<!-- header end -->

	<!-- content -->
			<div class="duct we_rtbox">
				<div class="du_tav">
					<p>
						<span class="active">投资记录</span>
						<span>订单记录</span>
					</p>
					<a href="javascript:">立即投资></a>
				</div>
				<div class="du_tab">
					<div class="ca_table">
						<div class="du_datebox">
							投资时间:<input type="text" placeholder="开始时间" class="layui-input" id="dufrom" placeholder="">到<input type="text" placeholder="结束时间" class="layui-input" id="duto" placeholder="">
							<span class="active">全部</span>
							<span>最近7天</span>
							<span>1个月</span>
							<span>3个月</span>
							<p class="search">
								<span>项目状态:</span>
								<a href="javascript:" class="active">全部状态</a>
								<a href="javascript:">投资中</a>
								<a href="javascript:">履约中</a>
								<a href="javascript:">已回款</a>
								<a href="javascript:">流标</a>
							</p>
						</div>
						<table>
							<thead>
								<tr>
									<th>项目名称</th>
									<th>预期年化收益</th>
									<th>收益天数(天)</th>
									<th>投资金额(元)</th>
									<th>累计收益(元)</th>
									<th>项目状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
								<c:forEach var="list" items="${detail.dataList }">
									<td>${list.product_name }</td>
									<td>${list.product_interest }</td>
									<td>${list.income_days }</td>
									<td>${list.amount }</td>
									<td>${list.incomed }</td>
									<td><span class="state${list.status } ">${list.status_text }</span></td>
									<td><a href="javascript:" class="see">查看</a></td>
								</c:forEach>
								</tr>
							</tbody>
						</table>
						<div class="ca_page">
							<a href="javascript:">首页</a>
							<i>《</i>
							<span class="active">1</span>
							<span>2</span>
							<i>》</i>
							<a href="javascript:">尾页</a>
						</div>
						<!-- 无记录 -->
						<div class="norecord hide">
							<a href="javascript:">立即投资</a>
						</div>
					</div>
					<div class="ca_table" style="display: none;">
						<div class="du_datebox">
							下单时间:<input type="text" placeholder="开始时间" class="layui-input" id="dufrom2" placeholder="">到<input type="text" placeholder="结束时间" class="layui-input" id="duto2" placeholder="">
							<span class="active">全部</span>
							<span>最近7天</span>
							<span>1个月</span>
							<span>3个月</span>
							<p class="search">
								<span>项目状态:</span>
								<a href="javascript:" class="active">全部状态</a>
								<a href="javascript:">投资中</a>
								<a href="javascript:">履约中</a>
								<a href="javascript:">已回款</a>
								<a href="javascript:">流标</a>
							</p>
						</div>
						<table>
							<thead>
								<tr>
									<th>下单时间</th>
									<th>订单号</th>
									<th>项目名称</th>
									<th>订单金额(元)</th>
									<th>支付状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="orderlist" items="${orderdetail.dataList }">
									<td>${orderlist.update_time }</td>	
									<td>${orderlist.trade_no }</td>
									<td>${orderlist.product_name }</td>
									<td>${orderlist.total_money }</td>
									<td>${orderlist.pay_type }</td>
									<td>
										<a href="javascript:" class="keepsee">查看</a>
									</td>
								</c:forEach>
							</tbody>
						</table>
						<div class="ca_page">
							<a href="javascript:">首页</a>
							<i>《</i>
							<span class="active">1</span>
							<span>2</span>
							<i>》</i>
							<a href="javascript:">尾页</a>
						</div>
						<!-- 无记录 -->
						<div class="norecord hide">
							<a href="javascript:">立即投资</a>
						</div>
					</div>
				</div>
			</div>
	<!-- content end-->

	<!-- footer -->
	<!-- footer end -->
	<script src="<%=path%>/js/front/wealth.js"></script>
	<script src="<%=path%>/module/jquery/jquery.min.js"></script>
</body>
</html>