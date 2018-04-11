<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../../../js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../../../js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../../../js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../../../js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../../../js/sys/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 会员管理 <span class="c-gray en">&gt;</span> 会员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<span class="select-box" style="width: 150px;">
			<select name="articlecolumn" class="select">
				<option value="0">请选择项目状态</option>
				<option value="1">手机号码</option>
				<option value="1">用户名</option>
				<option value="11">邀请人号码</option>
			</select>
		</span>
		<input type="text" name="" id="" placeholder="请输入要查询的值" style="width:200px" class="input-text">
		注册时间：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
		认证时间：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="expire" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="expire" class="input-text Wdate" style="width:120px;">
		<p style="margin-top: 10px;">
		<span class="select-box" style="width: 150px;">
			<select name="articlecolumn" class="select">
				<option value="0">注册来源</option>
				<option value="1">手机号码</option>
				<option value="1">用户名</option>
				<option value="11">邀请人号码</option>
			</select>
		</span>
		<span class="select-box" style="width: 150px;">
			<select name="articlecolumn" class="select">
				<option value="0">请选择渠道</option>
				<option value="1">手机号码</option>
				<option value="1">用户名</option>
				<option value="11">邀请人号码</option>
			</select>
		</span>
		<span class="select-box" style="width: 150px;">
			<select name="articlecolumn" class="select">
				<option value="0">请选择状态</option>
				<option value="1">手机号码</option>
				<option value="1">用户名</option>
				<option value="11">邀请人号码</option>
			</select>
		</span>
		<span class="select-box" style="width: 150px;">
			<select name="articlecolumn" class="select">
				<option value="0">是否实名</option>
				<option value="1">手机号码</option>
				<option value="1">用户名</option>
				<option value="11">邀请人号码</option>
			</select>
		</span>
		<span class="select-box" style="width: 150px;">
			<select name="articlecolumn" class="select">
				<option value="0">是否充值</option>
				<option value="1">手机号码</option>
				<option value="1">用户名</option>
				<option value="11">邀请人号码</option>
			</select>
		</span>
		<span class="select-box" style="width: 150px;">
			<select name="articlecolumn" class="select">
				<option value="0">是否投资</option>
				<option value="1">手机号码</option>
				<option value="1">用户名</option>
				<option value="11">邀请人号码</option>
			</select>
		</span>
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
		</p>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a href="javascript:;" onclick="picture_add('详情','','','520')" class="btn btn-primary radius"> 新增红包</a>
		</span>
		<span class="r">共有数据：<strong>${detail.total }</strong> 条</span>
	</div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="150">商品名</th>
				<th width="100">正经值花费</th>
				<th width="100">商品类型</th>
				<th width="100">优惠名称</th>
				<th width="100">兑换数量</th>
				<th width="100">面额</th>
				<th width="100">限制数量</th>
				<th width="100">状态</th>
				<th width="100">排序</th>
				<th width="100">描述</th>
				<th width="100">备注</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach var="line"  items="${detail.dataList }">
						<tr class="text-c">
							<td class="am-text-middle">${line.goods_name}</td>
							<td class="am-text-middle"><fmt:formatNumber value="${line.coin_cost }" type="currency" pattern="￥#,##0.0#"/></td>
							<td class="am-text-middle">${line.goods_type }</td>
							<td class="am-text-middle">${line.exchange_name }</td>
							<%-- <td class="am-text-middle">
								<c:if test="${!empty sessionScope.auths['res.showPhone']}">
									<a target="_blank" href="<%=basePath%>user/list?searchType=4&searchParam=${line.max_amount }"><fmt:formatNumber value="${line.max_amount }" type="currency" pattern="#,##0人"/></a>
								</c:if>
								<c:if test="${empty sessionScope.auths['res.showPhone']}">
									<a target="_blank" href="<%=basePath%>user/list?searchType=4&searchParam=${line.phoneFull }"><fmt:formatNumber value="${line.inviters }" type="currency" pattern="#,##0人"/></a>
								</c:if>
							</td> --%>
							<td>${line.goods_number}</td>
							<td> 
							<fmt:formatNumber value="${line.amount }" type="currency" pattern="￥#,##0.0#"/>
							</td>
							<td>
									${line.max_count}
							</td>
							<td class="am-text-middle">
							<c:if test="${line.status ==1}">
							有效
							</c:if>
							<c:if test="${line.status ==2}">
							无效
							</c:if>
							</td>
							<td class="am-text-middle">
								${line.order_num}
							</td>
							<td class="am-text-middle">
								${line.des}
							</td>
							<td class="am-text-middle">
								${line.remark}
							</td>
							<td class="am-text-middle">
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										<%-- <c:if test="${!empty sessionScope.auths['user.detail']}"> --%>
											<button data-id="${line.id }" class="am-btn am-btn-default am-btn-xs am-text-secondary user_detail btn btn-primary radius" ><span class="am-icon-search"></span> 详情</button>
										<%-- </c:if> --%>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
		</tbody>
	</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../../js/sys/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../../../js/sys/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../../js/sys/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../../../js/sys/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../../../js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="../../../js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../../../js/sys/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
		],
		"ordering":false
	});
});
/*会员详情*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}


/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}

/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
</script> 
</body>
</html>