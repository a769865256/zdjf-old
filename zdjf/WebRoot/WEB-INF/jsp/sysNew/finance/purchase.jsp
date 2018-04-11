<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/sys/static/h-ui.admin/skin/default/skin.css"
	id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/sys/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<style>
#pro_page {
	text-align: center;
}
.select-box{
	padding: 5px;
}
</style>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>资讯列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		财务管理 <span class="c-gray en">&gt;</span> 购买明细 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<form id="searchParam" action="<%=path%>/sys/finance/purchase/toList.action" method="get">
		<div class="text-c">
			<span class="select-box" style="width: 150px;"> <select
				name="searchType" class="select">
					<option value="1" <c:if test="${searchType==1||searchType==null }">selected</c:if>>手机号码</option>
					<option value="2" <c:if test="${searchType==2 }">selected</c:if>>真实姓名</option>
					<option value="3" <c:if test="${searchType==3 }">selected</c:if>>债券编码</option>
			</select>
			</span> 
			<input type="text" name="searchParam" id="" placeholder="请输入要查询的值" style="width:200px" class="input-text" value="${searchParam }"> 
			产品名称:<input type="text" name="productName"  placeholder="请输入要查询的产品名称" style="width:200px" class="input-text" value="${productName }"> 
			下单时间： <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" name="createStartTime" value="${createStartTime }" class="input-text Wdate" style="width:120px;"> 
				- 
				<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" name="createEndTime" value="${createEndTime }" class="input-text Wdate" style="width:120px;"> 
			支付时间： <input type="text"onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="expire" name="payStartTime" value="${payStartTime }" class="input-text Wdate" style="width:120px;"> - <input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="expire" name="payEndTime" value="${payEndTime }" class="input-text Wdate"
				style="width:120px;">
			<p style="margin-top: 10px;">
				<span class="select-box" style="width: 150px;"> <select
					name="regSource" class="select">
						<option value="">请选择渠道</option>
						<c:forEach var="line" items="${recharge_source}">
							<option value="${line.data_field_value}"
								<c:if test="${regSource==line.data_field_value }">selected</c:if>>${line.data_field_name }</option>
						</c:forEach>
				</select>
				</span> <span class="select-box" style="width: 150px;"> <select
					name="status" class="select">
						<option value="">请选择状态</option>
						<c:forEach var="item" items="${fina_sale_status }">
							<option value="${item.data_field_value }" <c:if test="${status==item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
						</c:forEach>
				</select>
				</span>
				<button name="" id="" class="btn btn-success search">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</p>
			</form>
		</div>

		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> </span> <span class="r">共有数据：<strong>${detail.total }</strong>
				条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort table-responsive" >
				<thead>
					<tr class="text-c">
						<th width="100">用户名/手机</th>
						<th width="80">真实姓名</th>
						<th width="120">产品名称</th>
						<th width="80">年化收益</th>
						<th width="80">收益天数</th>
						<th width="100">购买本金</th>
						<th width="80">加息券</th>
						<th width="80">红包券</th>
						<th width="80">正经值</th>
						<th width="100">实际支付</th>
						<th width="100">购买渠道</th>
						<th width="100">状态</th>
						<th width="100">预期收益</th>
						<th width="100">实际收益</th>
						<th width="100">支付单号</th>
						<th width="100">创建时间</th>
						<th width="100">支付时间</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="pro" items="${detail.dataList }">
						<tr class="text-c">
							<td>${pro.phone }</td>
							<td>${pro.real_name }</td>
							<td>${pro.product_name }</td>
							<td>${pro.product_interest }</td>
							<td>${pro.will_income_days }</td>
							<td>${pro.amount }</td>
							<td><c:if test="${pro.couponInterest!=null }">${pro.couponInterest }</c:if>
								<c:if test="${pro.couponInterest==null }">-</c:if></td>
							<td><c:if test="${pro.giftAmount!=null }">${pro.giftAmount }</c:if>
								<c:if test="${pro.giftAmount==null }">-</c:if></td>
							<td><c:if test="${pro.coin!=null }">${pro.coin }</c:if> <c:if
									test="${pro.coin==null }">-</c:if></td>
							<td>${pro.act_pay_money }</td>
							<td><c:forEach var="item" items="${recharge_source }">
									<c:if
										test="${null != pro && pro.req_source == item.data_field_value }">${item.data_field_name }</c:if>
								</c:forEach></td>
							<td><c:forEach var="item" items="${fina_sale_status }">
									<c:if
										test="${null != pro && pro.status == item.data_field_value }">${item.data_field_name }</c:if>
								</c:forEach></td>
							<td>${pro.will_income }</td>
							<td>${pro.incomed }</td>
							<td>
							<%-- <a href="<%=path %>/sys/finance/purchase/query.action" target="_Blank">${pro.trade_no }</a> --%>
							${pro.trade_no }
							</td>
							<td>${pro.create_time }</td>
							<td>${pro.pay_time }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div id="pro_page"></div>
	<table  width="50%"
		class="table table-border table-bordered table-bg table-hover table-sort table-responsive" style="
    width: 50%;
    margin: 0 auto;">
		<thead>
			<tr class="text-c">
				<th width="80">红包汇总</th>
				<th width="80">正经值汇总</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${pro.phone }</td>
				<td>${pro.real_name }</td>
			</tr>
		</tbody>
	</table>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/layer/2.4/layer.js"></script>
	<script src="<%=path%>/module/layui/layui.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="<%=path%>/js/sysNew/product.js"></script>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		var path='<%=path%>';
		$('.product_audit').on('click', function() {
			var product_id = $(this).attr("data-id");
			var url = '<%=path%>' + "/sys/product/toAudit.action?product_id=" + product_id;
			layer_show('审核', url, '200', '400');
		});
		$(".product_submit").click(function() {
			var product_id = $(this).attr("data-id");
			var url = '<%=path%>' + "/sys/product/submit.action?product_id=" + product_id;
			$.get(url, {
				"product_id" : product_id
			}, function(data) {
				alert("提交成功");
				url = '<%=path%>' + "/sys/product/page.action";
				window.location = url;
			});
		});
		$('.product_issue').on('click', function() {
			var product_id = $(this).attr("data-id");
			var url = '<%=path%>' + "/sys/product/issue.action";
			$.get(url, {
				"product_id" : product_id
			}, function(data) {
				if(data.returnCase){
					url = '<%=path%>' + "/sys/product/page.action";
					window.location = url;
				}else{
					alert(data.content);
				}
			});
		});
	
		function admin_role_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		
		var total="${detail.total}";
		layui.use(['layer','laypage','element','carousel'], function(){
			var laypage=layui.laypage;
			laypage.render({  /*参考链接http://www.layui.com/doc/modules/laypage.html*/
				elem: 'pro_page', 	//这里是 ID，不用加 # 号
				limit:10,
				groups:5,
				first:'首页',
				last:'尾页',
				theme:'#1187f1',
				count: total,		//数据总数，从服务端得到
				curr: function(){
					  var page = location.search.match(/currentPage=(\d+)/);  
	                   return page ? page[1] : 1;  
				}(),
				jump:function(obj,first){
					if(!first){
						var data=$('#searchParam').serialize();
						window.location=path+'/sys/finance/purchase/toList.action?currentPage='+obj.curr+'&limit=10&'+data;
					}
				}
			});
		})
	
		
	</script>
</body>
</html>
