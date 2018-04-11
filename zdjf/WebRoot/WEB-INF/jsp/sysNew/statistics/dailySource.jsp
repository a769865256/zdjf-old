<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
	String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+path+"/";
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
<title>渠道统计</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		数据统计 <span class="c-gray en">&gt;</span> 渠道管理 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
	<form action="<%=path %>/config/source/dailySource.action" method="get" id="form">
		<div class="text-c">
		<p style="margin-top: 20px;">
					<span class="select-box inline"> <select id="source_name"
						class="select" name="source_name">
							<option value=""
								<c:if test="${null == source_name || source_name == '' }">selected</c:if>>请选择渠道</option>
							<c:if test="${sourceList != null }">
								<c:forEach var="item" items="${sourceList }">
									<option value="${item.source_code }"
										<c:if test="${null != source_name && source_name == item.source_code }">selected</c:if>>${item.source_name }</option>
								</c:forEach>
							</c:if>
					</select>
					</span>
			日期： <input
				type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'end_time\')||\'\'}' })"
				id="start_time" name="start_time" value="<c:if test="${null != start_time }"><fmt:formatDate value="${start_time }" type="both" pattern="yyyy-MM-dd" /></c:if>" class="input-text Wdate" style="width:120px;" placeholder="请输入开始时间"> -
			<input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'start_time\')||\'\'}' })"
				id="end_time" name="end_time" value="<c:if test="${null != end_time }"><fmt:formatDate value="${end_time }" type="both" pattern="yyyy-MM-dd" /></c:if>" class="input-text Wdate" style="width:120px;" placeholder="请输入结束时间">
			
			<button name="" id="" class="btn btn-success" type="submit">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
				</p>
			</div>
			 <span class="r">共有数据：<strong>${page.total }</strong> 条
			</span>
		</form>
		<table class="table table-border table-bordered table-hover table-bg">
			<thead>
				<tr>
					<th scope="col" colspan="7">渠道统计</th>
				</tr>
				<tr class="text-c">
					<th width="150">日期</th>
					<th width="150">所属平台</th>
					<th width="150">渠道名称</th>
					<th width="200">新增有效投资次数</th>
					<th width="200">新增有效投资人数</th>
					<th width="200">新增首笔投资额</th>
					<th width="200">新增实名认证量</th>
					<th width="200">新注册并投资人数</th>
					<th width="200">新注册新增投资率</th>
					<th width="200">日投资人数</th>
					<th width="200">当日投资额</th>
					<th width="200">日人均投资额</th>
					<th width="200">提现次数</th>
					<th width="200">提现人数</th>
					<th width="200">提现金额</th>
					<th width="200">充值次数</th>
					<th width="200">充值人数</th>
					<th width="200">充值金额</th>
					<th width="200">重复投资人数</th>
					<th width="200">重复投资金额</th>
					<th width="200">重复投资均额</th>
					<th width="200">重复投资率</th>
					<th width="200">总注册数</th>
					<th width="200">总实名认证数</th>
					<th width="200">总投资率</th>
					<th width="200">总有效投资人数</th>
					<th width="200">总投资额</th>
					<th width="200">总人均投资额</th>
					<th width="200">站岗资金</th>
					<th width="200">总重复投资人数</th>
					<th width="200">总重复投资率</th>
					<th width="200">总还本付息人数</th>
					<th width="200">总还本付息重投率</th>
					<th width="200">还本付息重投人数</th>
					<th width="200">还本付息重投金额</th>
					<th width="200">还本付息重投均额</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="line" items="${detail.dataList }">
					<tr class="trLine">
						<td><fmt:formatDate value="${line.create_time }" type="both"
								pattern="yyyy-MM-dd" /></td>
						<td><c:if test="${line.subordinate_platform=='1' }">
                               网站
                               </c:if> <c:if
								test="${line.subordinate_platform=='2' }">
               微信
                               </c:if> <c:if
								test="${line.subordinate_platform=='3' }">
         APP
                               </c:if></td>
						<td><c:forEach var="item" items="${sourceList}">
								<c:if test="${line.source_name == item.source_code}">${item.source_name}</c:if>
							</c:forEach></td>
						<td>${line.new_effective_investment_times}</td>
						<td>${line.new_effective_investment_people}</td>
						<td>${line.new_first_investment}</td>
						<td>${line.new_registrations}</td>
						<td>${line.new_real_name_authentication}</td>
						<td>${line.new_registrations_investments}</td>
						<td>${line.new_registered_investment_rate}</td>
						<td>${line.daily_investment}</td>
						<td>${line.daily_investment_times}</td>
						<td>${line.daily_investment_amount}</td>
						<td>${line.daily_per_capita_investment}</td>
						<td>${line.withdrawals_times}</td>
						<td>${line.withdrawals_people}</td>
						<td>${line.withdrawals_amount}</td>
						<td>${line.recharge_times}</td>
						<td>${line.recharge_people}</td>
						<td>${line.recharge_amount}</td>
						<td>${line.repeat_investment_people}</td>
						<td>${line.repeat_investment_amount}</td>
						<td>${line.repeat_investment_rate}</td>
						<td>${line.total_registration}</td>
						<td>${line.total_real_name}</td>
						<td>${line.total_investment_rate}</td>
						<td>${line.total_effective_capital}</td>
						<td>${line.total_investment}</td>
						<td>${line.total_per_capita_investment}</td>
						<td>${line.sentry_funds}</td>
						<td>${line.total_repeat_investment_people}</td>
						<td>${line.total_repeat_investment_rate}</td>
						<td>${line.total_debt_service_interest}</td>
						<td>${line.total_debt_service_rate}</td>
						<td>${line.total_debt_service_amount}</td>
						<td>${line.total_debt_per_amount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pro_page"></div>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->
	<script src="<%=path%>/module/layui/layui.js"></script>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
		src="<%=path%>/js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript">
/*管理员-角色-添加*/
var path='<%=path%>';
/*管理员-角色-添加*/
function admin_role_add(title,url,w,h){
	layer_show(title,url,w,h);
}
$('.ml-5').click(function(){
	var url=path+"/config/source/delete.action";
	var source_id = $(this).attr("data-id");
	$.post(url, {
				"source_id" : source_id
			}, function(data) {
				alert("删除成功");
				url = '<%=path%>' + "/config/source/dailySource.action";
				window.location = url;
			});
});
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
						var data=$('#form').serialize();
						window.location=path+'/config/source/dailySource.action?currentPage='+obj.curr+'&limit=10&'+data;
					}
				}
			});
		});
</script>
</body>
</html>