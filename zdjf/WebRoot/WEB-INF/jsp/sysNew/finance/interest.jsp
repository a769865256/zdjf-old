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
		理财项目 <span class="c-gray en">&gt;</span>计息列表<a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<form id="searchParam" action="<%=path%>/sys/finance/interest/toList.action" method="get">
		<div class="text-c">
			用户名：<input type="text" name="userName" value="${userName }" placeholder="请输入手机号"
				style="width:300px" class="input-text">
			 还款时间： <input
				type="text"
				value="${payStartDate }"
				onfocus="WdatePicker({  })"
				id="logmin" name="payStartDate" class="input-text Wdate" style="width:120px;"> -
			<input type="text"
			value="${payEndDate }"
				onfocus="WdatePicker({  })"
				id="logmax" name="payEndDate" class="input-text Wdate" style="width:120px;">
			<br>
			产品名称：<input type="text" value="${productName }" name="productName" id="" placeholder="请输入产品名称"
				style="width:300px" class="input-text">
			<p style="margin-top: 20px;">
				<span class="select-box inline">
					<select name="status" class="select">
						<option value="">请选择回款状态</option>
						<option value="1" <c:if test="${status==1 }">selected</c:if>>已回款</option>
						<option value="-1" <c:if test="${status==1 }">selected</c:if>>待回款</option>
						<option value="-2" <c:if test="${status==1 }">selected</c:if>>支付失败</option>
						<option value="-3" <c:if test="${status==1 }">selected</c:if>>作废</option>
					</select>
					
					<select name="isReturnAmount" class="select">
						<option value="">请选择回款类型</option>
						<option value="1" <c:if test="${isReturnAmount==1 }">selected</c:if>>本息</option>
						<option value="2" <c:if test="${isReturnAmount==2 }">selected</c:if>>利息</option>
					</select>
				</span>
				<button name="" id="search" class="btn btn-success" type="submit">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</p>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"></span> <span class="r">共有数据：<strong>${page.total }</strong>
				条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
				<thead>
					<tr class="text-c">
						<th width="100">用户名</th>
						<th width="100">真实姓名</th>
						<th width="100">产品名称</th>
						<th width="100">出借人</th>
						<th width="100">还款时间</th>
						<th width="100">类型</th>
						<th width="100">金额</th>
						<th width="100">状态</th>
					</tr>
				</thead>
				<tbody>
						<c:forEach var="pro" items="${page.dataList }">
						<tr class="text-c">
							<td>${pro.phone }</td>
							<td>${pro.real_name }</td>
							<td>${pro.product_code }</td>
							<td>${pro.loan_name }</td>
							<td>${pro.pay_date }</td>
							<td>
								<c:if test="${pro.is_return_amount==1 }">本息</c:if>
								<c:if test="${pro.is_return_amount==2 }">利息</c:if>
							</td>
							<td>
								<c:if test="${pro.is_return_amount==1 }">${pro.amount+pro.income }</c:if>
								<c:if test="${pro.is_return_amount==2 }">${pro.income }</c:if>
							</td>
							<td>
								<c:if test="${pro.status==1 }">已回款</c:if>
								<c:if test="${pro.status==-1 }">待回款</c:if>
								<c:if test="${pro.status==-2 }">支付失败</c:if>
								<c:if test="${pro.status==-3}">作废</c:if>
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
		var path='<%=path%>';
	
		function admin_role_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	
		var total="${page.total}";
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
						window.location=path+'/sys/finance/interest/toList.action?currentPage='+obj.curr+'&limit=10&'+data;
					}
				}
			});
		})
	
		
	</script>
</body>
</html>
