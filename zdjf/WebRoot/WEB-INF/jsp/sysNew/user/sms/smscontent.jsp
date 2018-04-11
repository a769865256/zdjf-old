<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../../js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<style>
#pro_page {
	text-align: center;
}
#pro_page2 {
	text-align: center;
}
</style>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>短信列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 会员管理 <span class="c-gray en">&gt;</span> 短信列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	 <span class="select-box inline">
		<select name="" class="select">
			<option value="0">手机号</option>
			<option value="1">分类一</option>
			<option value="2">分类二</option>
		</select>
	</span>
	<input type="text" name="" id="" placeholder="输入要查的值" style="width:250px" class="input-text">
	<span class="select-box inline">
		<select name="" class="select">
			<option value="0">所有状态</option>
			<option value="1">分类一</option>
			<option value="2">分类二</option>
		</select>
	</span>
	<span class="select-box inline">
		<select name="" class="select">
			<option value="0">所有类型</option>
			<option value="1">分类一</option>
			<option value="2">分类二</option>
		</select>
	</span>创建时间：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="r">共有数据：<strong>54</strong> 条</span>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="150">用户ID</th>
					<th width="150">手机号码</th>
					<th width="150">真实姓名</th>
					<th width="200">短信详情</th>
					<th width="150">类型</th>
					<th width="150">状态</th>
					<th width="150">IP</th>
					<th width="150">创建时间</th>
					<th width="150">备注</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="line"  items="${detail.params.dataList }">
								<tr>
									<td class="am-text-middle">${line.id }</td>
									<td class="am-text-middle">
<%-- 										<c:if test="${!empty line.uid && line.uid != 0 && !empty sessionScope.auths['user.detail']}">
 --%>										<a href="<%=basePath%>sys/user/toDetail.action?uid=${line.phone }" target="_blank">${line.phone }</a>
<%-- 										</c:if>
 --%>										<%-- <c:if test="${!empty line.uid && line.uid == 0 || empty sessionScope.auths['user.detail']}">${line.phone }</c:if> --%>
									</td>
									<td class="am-text-middle">${line.real_name }</td> 
									<td class="am-text-middle">${line.content }</td>
									<td class="am-text-middle">${line.sms_type }</td>
									<td class="am-text-middle">${line.status }</td>
									<td class="am-text-middle">${line.source_ip }</td>
									<td class="am-text-middle"><fmt:formatDate value="${line.create_time }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td class="am-text-middle">${line.remark }</td>
								</tr>
							</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<div id="pro_page"></div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../js/sys/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../../js/sys/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../js/sys/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../../js/sys/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../../js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="../../js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../../js/sys/lib/laypage/1.2/laypage.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript">
/* $('.table-sort').dataTable({
	"bStateSave": true,//状态保存
	"pading":false,
	"ordering":false
}); */
var path='<%=path%>';
		var total="${detail.lender.total}";
		layui.use(['layer','laypage','element','carousel'], function(){
			var laypage=layui.laypage;
			laypage.render({ 
				elem: 'pro_page', 	
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
						window.location=path+'/sys/sms/smsContent.action?currentPage='+obj.curr+'&limit=10';
					}
				}
			});
		});
</script> 
</body>
</html>