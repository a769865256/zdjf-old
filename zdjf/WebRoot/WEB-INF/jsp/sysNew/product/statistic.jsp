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
		理财项目 <span class="c-gray en">&gt;</span> 项目列表 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="text-c">
			<input type="text" name="" id="" placeholder="请输入债权编号/项目编码/项目名称/出借人"
				style="width:300px" class="input-text"> 发布时间： <input
				type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
				id="logmin" class="input-text Wdate" style="width:120px;"> -
			<input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="logmax" class="input-text Wdate" style="width:120px;">
			到期时间： <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
				id="expire" class="input-text Wdate" style="width:120px;"> -
			<input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="expire" class="input-text Wdate" style="width:120px;"><br>

			<p style="margin-top: 20px;">
				转让金额： <input type="number" class="input-text"> - <input
					type="number" class="input-text"> 收益剩余天数： <input
					type="number" class="input-text"> - <input type="number"
					class="input-text"> <span class="select-box inline">
					<select name="" class="select">
						<option value="0">请选择项目状态</option>
						<option value="1">分类一</option>
						<option value="2">分类二</option>
				</select>
				</span>
				<button name="" id="" class="btn btn-success" type="submit">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</p>
		</div>

		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a class="btn btn-primary radius"
				data-title="添加资讯" data-href="financial_add.html"
				href="<%=path%>/sys/product/toDetail.action"><i
					class="Hui-iconfont">&#xe600;</i> 添加项目</a></span> <span class="r">共有数据：<strong>${page.total }</strong>
				条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
				<thead>
					<tr class="text-c">
						<!-- <th class="table-title">ID</th> -->
						<th class="table-type" width="120">债权编号</th>
                        <th class="table-type" width="120">项目编码</th>
                        <th class="table-type" width="100">转让金额<br>(已转让)</th>
                        <th class="table-type" width="100">线下支付利率</th>
                        <th class="table-type" width="100">出借人</th>
                        <th class="table-type" width="80">项目周期</th>
                        <th class="table-type" width="140">投资完成时间</th>
                        <th class="table-type" width="100">线下利息结算<br>(已结算)</th>
                        <th class="table-type" width="140">出借人支付投资人利息<br>(已支付)</th>
                        <th class="table-type" width="80">红包券支出</th>
                        <th class="table-type" width="80">正经值支出</th>
                        <th class="table-type" width="80">加息券支出<br>(已支出)</th>
                        <th class="table-type" width="150">平台毛利润<br>(已结算)</th>
                        <th class="table-type" width="100">平台利润<br>(已结算)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="pro" items="${page.dataList }">
						<tr class="text-c">
							<td>${pro.debt_code }</td>
                            <td>${pro.product_code }</td>
                            <td>
                            	${pro.money }<br>
                                ${pro.sale_money }
                            </td>
                            <td>${pro.income_offline }%</td>
                            <td>
                                ${pro.name }
                            </td>
                            <td>${pro.income_total_days}</td> 
                            <td>${pro.full_time}<c:if test="${pro.full_time ==null || pro.full_time ==''}">未完成</c:if></td>
                            <td> ${pro.total_income_offline}<br>
                                 ${pro.payed_total_income_offline}
                            </td>
                            <td> ${pro.total_incomed }<br>
                                 ${pro.payed_total_incomed }
                            </td>

                            <td> ${pro.total_gift_amount}</td>
                            <td> ${pro.total_coin}</td>
                            <td> ${pro.total_coupon_amount}<br>
                                 ${pro.payed_total_coupon_amount}
                            </td>

                            <td> ${pro.profit}<br>
                                 ${pro.payed_profit}
                            </td>
                            <td> ${pro.net_profit}<br>
                                 ${pro.payed_net_profit}
                            </td>


						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div id="pro_page"></div>
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
		var path = '<%=path%>';
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
				url = '<%=path%>' + "/sys/product/page.action";
				window.location = url;
			});
		});
	
		function admin_role_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	
		var total = "${page.total}";
		layui.use([ 'layer', 'laypage', 'element', 'carousel' ], function() {
			var laypage = layui.laypage;
			laypage.render({ /*参考链接http://www.layui.com/doc/modules/laypage.html*/
				elem : 'pro_page', //这里是 ID，不用加 # 号
				limit : 10,
				groups : 5,
				first : '首页',
				last : '尾页',
				theme : '#1187f1',
				count : total, //数据总数，从服务端得到
				curr : function() {
					var page = location.search.match(/currentPage=(\d+)/);
					return page ? page[1] : 1;
				}(),
				jump : function(obj, first) {
					if (!first) {
						window.location = path + '/sys/product/page.action?currentPage=' + obj.curr + '&limit=10';
					}
				}
			});
		})
	</script>
</body>
</html>
