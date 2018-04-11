<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<style>
#pro_page {
	text-align: center;
}
#pro_page2 {
	text-align: center;
}
#pro_page3 {
	text-align: center;
}
</style>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>基本设置</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>
	理财项目
	<span class="c-gray en">&gt;</span>
	出借方管理
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>会员资金变动记录</span>
				<span>充值记录</span>
				<span>提现记录</span>
			</div>
			<div class="tabCon">
				<div class="page-container">
					<form id="param" action="<%=path %>/sys/finance/cash/toList.action" method="get">
					<div class="text-c">
						<input type="text" name="real_name" id="" placeholder="请输入姓名" style="width:300px" class="input-text" value="${real_name }">
						<input type="text" name="user_name" id="" placeholder="请输入手机号码" style="width:300px" class="input-text" value="${ user_name}">
						<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
					</div>
					</form>
					<div class="mt-20">
							<table
								class="table table-border table-bordered table-hover table-bg table-sort">
								<thead>
									<tr class="text-c">
										<th width="100">ID</th>
										<th width="100">用户名/手机</th>
										<th width="100">真实姓名</th>
										<th width="120">关联资金单号</th>
										<th width="100">可用余额</th>
										<th width="100">资金状态</th>
										<th width="100">资金类型</th>
										<th width="100">本次变动金额</th>
										<!-- <th width="100">手续费</th>
								<th width="100">收取对象</th> -->
										<th width="150">变动时间</th>
										<th width="100">备注</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="change" items="${change.dataList }">
										<tr class="text-c">
											<td>${change.id }</td>
											<td>${change.phone }</td>
											<td>${change.real_name }</td>
											<td>${change.trade_no }</td>
											<td>${change.balance }</td>
											<td>
												<c:forEach var="item" items="${funds_vary_status }">
												<c:if test="${null != change && change.status == item.data_field_value }">${item.data_field_name }</c:if>
												</c:forEach>
											</td>
											<td>
												<c:forEach var="item" items="${funds_operate_type }">
												<c:if test="${null != change && change.operate_type == item.data_field_value }">${item.data_field_name }</c:if>
												</c:forEach>
											</td>
											<td>${change.amount }</td>
											<td>${change.create_time }</td>
											<td>${change.remark }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
				</div>
				<div id="pro_page"></div>
			</div>
			<div class="tabCon">
				<div class="page-container">
				<div class="mt-20">
					<table class="table table-border table-bordered table-hover table-bg table-sort">
						<thead>
							<tr class="text-c">
								<th width="200">ID</th>
								<th width="200">用户名/手机</th>
								<th width="120">姓名</th>
								<th width="200">充值金额</th>
								<th width="100">平台手续费</th>
								<th width="100">创建时间</th>
								<th width="100">支付类型</th>
								<th width="100">渠道</th>
								<th width="100">状态</th>
								<th width="100">流水号</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="rechage"  items="${recharge.dataList }">
                                        <tr>
                                           <td>${rechage.id }</td>
											<td>${rechage.phone }</td>
											<td>${rechage.real_name }</td>
											<td>${rechage.money }</td>
											<td>${rechage.fee_amt }</td>
											<td>${rechage.create_time }</td>
											<td>
												<c:if test="${rechage.pay_type == 1 }">汇付-网银充值</c:if>
												<c:if test="${rechage.pay_type == 2 }">汇付-快捷支付</c:if>
											</td>
											<td>
												<c:forEach var="item" items="${recharge_source }">
													<c:if test="${null != rechage && rechage.req_source == item.data_field_value }">${item.data_field_name }</c:if>
												</c:forEach>
											</td>
											<td>
											<c:forEach var="item" items="${recharge_status }">
												<c:if test="${null != rechage && rechage.status == item.data_field_value }">${item.data_field_name }</c:if>
											</c:forEach>
											</td>
											<td>${rechage.pp_serial_no }</td>
                                        </tr>
                                    </c:forEach>
						</tbody>
					</table>
					</div>
				</div>
				<div id="pro_page2"></div>
			</div>
			<div class="tabCon">
				<div class="page-container">
				<div class="mt-20">
					<table class="table table-border table-bordered table-hover table-bg table-sort">
						<thead>
							<tr class="text-c">
								<th width="200">ID</th>
								<th width="200">用户名/手机</th>
								<th width="120">姓名</th>
								<th width="200">创建时间</th>
								<th width="200">更新时间</th>
								<th width="200">金额</th>
								<th width="200">手续费</th>
								<th width="200">收取对象</th>
								<th width="200">渠道</th>
								<th width="200">状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="withdraw"  items="${withdraw.dataList }">
                                        <tr>
                                            <td>${withdraw.id }</td>
                                            <td>${withdraw.phone }</td>
                                            <td>${withdraw.name }</td>
                                            <td>${withdraw.create_time }</td>
                                            <td>${withdraw.update_time }</td>
                                            <td>${withdraw.money }</td>
                                            <td>${withdraw.fee_amt }</td>
                                            <td>
                                            <c:forEach var="item" items="${withdraw_fee_obj }">
												<c:if test="${null != withdraw && withdraw.fee_obj == item.data_field_value }">${item.data_field_name }</c:if>
												</c:forEach>
                                            </td>
                                            <td>
                                            	<c:forEach var="item" items="${recharge_source }">
													<c:if test="${null != withdraw && withdraw.req_source == item.data_field_value }">${item.data_field_name }</c:if>
												</c:forEach>
                                            </td>
                                            <td>
                                            	<c:forEach var="item" items="${withdraw_status }">
												<c:if test="${null != withdraw && withdraw.status == item.data_field_value }">${item.data_field_name }</c:if>
												</c:forEach>
                                            </td>
                                        </tr>
                                    </c:forEach>
						</tbody>
					</table>
					</div>
				</div>
				<div id="pro_page3"></div>
			</div>
		</div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/js/sys/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/sys/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/sys/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/sys/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/sys/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=path%>/js/sys/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<!-- <script type="text/javascript" src="<%=path%>/js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>  -->
<script type="text/javascript" src="<%=path%>/js/sys/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript">
	/* $('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"pading":false,
		"ordering":false
	}); */
	var path = '<%=path%>';
	var total = "${change.total}";
	var total2 = "${recharge.total}";
	var total3 = "${withdraw.total}";
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
				var page = location.search.match(/changecurrentPage=(\d+)/);
				return page ? page[1] : 1;
			}(),
			jump : function(obj, first) {
				if (!first) {
					var data=$("#param").serialize();
					window.location = path + '/sys/finance/cash/toList.action?changecurrentPage=' + obj.curr + '&limit=10&'+data;
				}
			}
		});
		laypage.render({
			elem : 'pro_page2', //这里是 ID，不用加 # 号
			limit : 10,
			groups : 5,
			first : '首页',
			last : '尾页',
			theme : '#1187f1',
			count : total2, //数据总数，从服务端得到
			curr : function() {
				var page = location.search.match(/rechargecurrentPage=(\d+)/);
				return page ? page[1] : 1;
			}(),
			jump : function(obj, first) {
				if (!first) {
					window.location = path + '/sys/finance/cash/toList.action?rechargecurrentPage=' + obj.curr + '&limit=10';
				}
			}
		});
		laypage.render({
			elem : 'pro_page3', //这里是 ID，不用加 # 号
			limit : 10,
			groups : 5,
			first : '首页',
			last : '尾页',
			theme : '#1187f1',
			count : total3, //数据总数，从服务端得到
			curr : function() {
				var page = location.search.match(/withdrawcurrentPage=(\d+)/);
				return page ? page[1] : 1;
			}(),
			jump : function(obj, first) {
				if (!first) {
					window.location = path + '/sys/finance/cash/toList.action?withdrawcurrentPage=' + obj.curr + '&limit=10';
				}
			}
		});

	});
	/*用户-编辑*/
	function member_edit(title, url, id, w, h) {
		url = url + "?lenderId=" + id;
		layer_show(title, url, w, h);
	}
	/*用户-删除*/
	function member_del(obj, id) {
		layer.confirm('确认要删除吗？', function(index) {
			$.ajax({
				type : 'POST',
				url : '',
				dataType : 'json',
				success : function(data) {
					$(obj).parents("tr").remove();
					layer.msg('已删除!', {
						icon : 1,
						time : 1000
					});
				},
				error : function(data) {
					console.log(data.msg);
				},
			});
		});
	}


	$(function() {
		$('.skin-minimal input').iCheck({
			checkboxClass : 'icheckbox-blue',
			radioClass : 'iradio-blue',
			increaseArea : '20%'
		});
		$("#tab-system").Huitab({
			index : 0
		});
	});
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
