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

#xmfb {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.5);
}

#xmfb .box {
	width: 500px;
	height: 300px;
	background: #fff;
	border-radius: 4px;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	-webkit-transform: translate(-50%, -50%);
	-moz-transform: translate(-50%, -50%);
	-o-transform: translate(-50%, -50%);
}

#xmfb .box h4 {
	text-align: center;
	padding: 30px 15px 0;
	font-size: 26px;
	color: #333;
}

#xmfb .box .checkBtn {
	display: flex;
	display: -webkit-flex;
	flex-direction: row;
	-webkit-flex-direction: row;
	align-items: center;
	-webkit-align-items: center;
	justify-content: center;
	-webkit-justify-content: center;
	font-size: 16px;
}

#xmfb .box .checkBtn  div {
	margin: 15px;
}

#xmfb .box .checkBtn  div input {
	margin: 0 10px;
}

#xmfb .box .btnBox .centent {
	display: flex;
	display: -webkit-flex;
	align-items: center;
	-webkit-align-items: center;
	justify-content: center;
	-webkit-justify-content: center;
}

#xmfb .box .btnBox .centent.centent1 {
	flex-direction: row;
	-webkit-flex-direction: row;
}

#xmfb .box .btnBox .centent.centent2 {
	flex-direction: column;
	-webkit-flex-direction: column;
}

#xmfb .box .btnBox .centent a {
	padding: 15px;
	margin: 0 85px;
	color: #1187f1;
	font-size: 18px;
}

#xmfb .box .btnBox .centent a:hover {
	cursor: pointer;
}

#xmfb .box .btnBox .centent div {
	margin-top: 15px
}

#xmfb .box .btnBox .centent input[type=date] {
	width: 135px;
}

#xmfb .box .btnBox .centent input[type=text] {
	width: 50px;
}

#xmfb .box .btnBox .centent input[type=checkbox] {
	margin-right: 10px;
}

#xmfb .box .btnBox .centent span {
	margin: 0 5px;
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
				href="<%=path%>/sys/product/toDetail.action?update_type=3"><i
					class="Hui-iconfont">&#xe600;</i> 添加项目</a></span> <span class="r">共有数据：<strong>${page.total }</strong>
				条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
				<thead>
					<tr class="text-c">
						<th width="120">债权编号</th>
						<th width="120">项目编码</th>
						<th width="80">项目名称</th>
						<th width="80">转让金额</th>
						<th width="80">年化收益</th>
						<th width="80">借款人</th>
						<th width="100">发布时间</th>
						<th width="100">到期时间</th>
						<th width="100">实际还款日</th>
						<th width="80">剩余天数</th>
						<th width="80">购买记录</th>
						<th width="100">项目状态</th>
						<th width="100">操作</th>
						<th width="100">存盘</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="pro" items="${page.dataList }">
						<tr class="text-c"<%-- <c:if test="${pro.timeFlag>=2}">style="color: orangered" </c:if> --%>
						>
							<td>${pro.debt_code }</td>
							<td><a target="_blank"
								href="<%=path %>/product/detail/${pro.id }.action">${pro.product_code }</a></td>
							<td title="${pro.product_name }"><c:if
									test="${fn:length(pro.product_name) > 8}">
									${fn:substring(pro.product_name, 0, 8)}...
								</c:if> <c:if test="${fn:length(pro.product_name) <= 8}">
									${pro.product_name}
								</c:if></td>
							<td><fmt:formatNumber value="${pro.money }" type="currency"
									pattern="￥#,##0.0#" /></td>
							<td><fmt:formatNumber value="${pro.income }" type="currency"
									pattern="0.00" />%</td>
							<td>${pro.lender_name}</td>
							<%--<td >${pro.name }</td>--%>
							<td
								<c:if test="${pro.status == 31}">title="预告时间:<fmt:formatDate value="${pro.willShowTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /> 发布时间:<fmt:formatDate value="${pro.willIssueTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss" />" </c:if>>
								<fmt:formatDate value="${pro.issue_time }" type="both"
									pattern="yyyy-MM-dd" />
							</td>
							<td><fmt:formatDate value="${pro.end_date}" type="both"
									pattern="yyyy-MM-dd" /></td>
							<td><c:if test="${pro.advance_status == 3}">
									<fmt:formatDate value="${pro.advance_date}" type="both"
										pattern="yyyy-MM-dd" />
									<br>
									(提前还款)
								</c:if> <c:if test="${pro.advance_status != 3}">
									<fmt:formatDate value="${pro.end_date}" type="both"
										pattern="yyyy-MM-dd" />
								</c:if></td>
							<td>
								<%-- 	<c:if test="${pro.incomeDays >= 0}"> ${pro.incomeDays}</c:if>
								<c:if test="${pro.incomeDays < 0}">-</c:if></td> --%>
								${pro.income_day}
							<td><a
								href="<%=basePath%>financial/purchase?searchType=3&searchType=${pro.debt_code}&status=1,2"
								target="_blank"> ${pro.buyer_count}</a></td>
							<td
								<c:if test="${pro.status == 11 }">style="color: orange" </c:if>
								<c:if test="${pro.status == 4 }">style="color:limegreen" </c:if>
								<c:if test="${pro.status == 1 && pro.remark !=''}" >style="text-decoration: underline;" title="审核不通过备注：${pro.remark}"　</c:if>>
								<c:if test="${pro.status == 1 && pro.remark !=''}">
									<div class='name-tip'>
										<i class="icon-name" style="font-style:normal;"><a
											href="javascript:void(0);">${pro.create_id }</a></i>
										<div class="tip-con">
											<span class="icon-arrow"></span> <span class="icon-arrow01"></span>
											<div class="alert-table">
												<span style="font-weight:bold;">审核不通过备注:</span><br>
												<textarea rows="6" cols="33" readonly="true">${pro.remark}</textarea>
											</div>
										</div>
									</div>
								</c:if> <c:if test="${pro.status != 1 || pro.remark ==''}">${pro.create_id }</c:if>
								<c:if test="${pro.status == 5 && pro.advance_status == 2}">
									<br>
									<span style="color: orange">（待审核）</span>
								</c:if>
							</td>
							<td>
								<style>
.btn-hover {
	position: relative;
}

.btn-hover .more-btn {
	width: 88px;
	background-color: #fff;
}

.btn-hover button {
	width: 88px;
	margin: 0 auto;
}

.btn-hover .more-btn-hover {
	display: none;
	position: absolute;
	z-index: 100;
	right: 85px;
	min-width: 350px;
	/*min-width: 263px;*/
	/*background-color: #fff;*/
	/*border: 1px solid #ccc;*/
	top: 0px;
}

.btn-hover:hover .more-btn-hover {
	display: block;
}

.btn-hover .more-btn-hover button {
	border: 1px solid #ccc !important;
	float: right;
}

.btn-hover .icon-arrow {
	border: 7px solid transparent;
	border-left-color: #ccc;
	width: 0px;
	height: 0px;
	font-size: 0;
	display: inline-block;
	position: absolute;
	bottom: 6px;
	right: -14px;
	-webkit-transition: .13s ease all;
	-moz-transition: .13s ease all;
	-ms-transition: .13s ease all;
	-o-transition: .13s ease all;
	transition: .13s ease all;
}

.btn-hover .icon-arrow01 {
	border: 6px solid transparent;
	border-left-color: #fff;
	width: 0px;
	height: 0px;
	font-size: 0;
	display: inline-block;
	position: absolute;
	bottom: 7px;
	right: -12px;
	-webkit-transition: .13s ease all;
	-moz-transition: .13s ease all;
	-ms-transition: .13s ease all;
	-o-transition: .13s ease all;
	transition: .13s ease all;
}

.btn-more-hover-look:hover .icon-arrow01 {
	border-left-color: #d4d4d4;
}

#overflorw {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.4);
	display: none;
}

#overflorw .box {
	width: 500px;
	height: 500px;
	position: fixed;
	top: 50%;
	left: 50%;
	background: #fff;
	transform: translate(-50%, -50%);
	-webkit-transform: translate(-50%, -50%);
	-zom-transform: translate(-50%, -50%);
	-o-transform: translate(-50%, -50%);
}

#overflorw .box h3 {
	text-align: center;
	padding: 20px 0;
}

#overflorw .box .btnCheck {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
}

#overflorw .box .btnCheck div {
	margin: 0 20px;
}

#overflorw .box .tabCentent {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
}

#overflorw .box .tabCentent a {
	margin: 50px;
}
</style>
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										<div class="btn-hover">
											<div
												class="am-btn am-btn-default am-btn-xs am-text-secondary more-btn">更多</div>
											<c:if test="${pro.status == 11}">
												<button data-id="${pro.id }"
													class="am-btn am-btn-default am-btn-xs am-text-secondary product_audit">
													<span class="am-icon-search"></span> 审核
												</button>
											</c:if>

											<div class="more-btn-hover"
												style=" box-shadow: 2px 2px 2px #eee;">
												<button data-id="${pro.id }"
													class="am-btn am-btn-default am-btn-xs am-text-secondary product_show btn-more-hover btn-more-hover-look"
													style="margin-left: -1px; margin-right: -1px">
													<span class="am-icon-search"></span> 查看 <span
														class="icon-arrow"></span> <span class="icon-arrow01"></span>
												</button>
												<c:if
													test="${ (pro.status == 3 ||pro.status == 4 || pro.status == 5 || pro.status == 6 || pro.status ==31)}">
													<button data-id="${pro.id }"
														class="am-btn am-btn-default am-btn-xs am-text-secondary urgent_update btn-more-hover">
														<span class="am-icon-search"></span> 紧急修改
													</button>
												</c:if>
												<c:if test="${ pro.status == 5 && pro.advance_status == 1}">
													<button data-id="${pro.id }"
														data-name="${pro.product_code}"
														data-date="<fmt:formatDate value="${pro.end_date }" type="both" pattern="yyyy-MM-dd 17:30:00" />"
														class="am-btn am-btn-default am-btn-xs am-text-secondary product_adv_submit btn-more-hover"
														style="width: 130px;">
														<span class="am-icon-search"></span> 申请提前还款
													</button>
												</c:if>
												<c:if test="${ pro.status == 5 && pro.advance_status == 2}">
													<button data-id="${pro.id }"
														data-name="${pro.product_code}"
														data-date="<fmt:formatDate value="${pro.end_date }" type="both" pattern="yyyy-MM-dd 17:30:00" />"
														data-adv_date="<fmt:formatDate value="${pro.advance_date }" type="both" pattern="yyyy-MM-dd 17:30:00" />"
														class="am-btn am-btn-default am-btn-xs am-text-secondary product_adv_audit btn-more-hover"
														style="width: 130px;">
														<span class="am-icon-search"></span> 提前还款审核
													</button>
												</c:if>

												<c:if test="${pro.status ==1}">
													<button data-id="${pro.id }"
														class="am-btn am-btn-default am-btn-xs am-text-secondary product_update btn-more-hover">
														<span class="am-icon-search"></span> 编辑
													</button>
												</c:if>

												<c:if test="${pro.status == 1}">
													<button data-id="${pro.id }"
														class="am-btn am-btn-default am-btn-xs am-text-secondary product_submit btn-more-hover">
														<span class="am-icon-search"></span> 提交审核
													</button>
													<button data-id="${pro.id }"
														class="am-btn am-btn-default am-btn-xs am-text-secondary product_delete btn-more-hover">
														<span class="am-icon-search"></span> 删除
													</button>
												</c:if>

												<%--<c:if test="${!empty sessionScope.auths['product.chinapnr.submit']}" >
													<button data-id="${pro.id }" class="am-btn am-btn-default am-btn-xs am-text-secondary product_chinapnr_submit"><span class="am-icon-search"></span> 发标</button>
												</c:if>--%>
												<c:if test="${pro.status == 3 || pro.status == 31}">
													<button data-id="${pro.id }"
														class="am-btn am-btn-default am-btn-xs am-text-secondary product_delete btn-more-hover">
														<span class="am-icon-search"></span> 删除
													</button>
													<button data-id="${pro.id }"
														data-willShowTime="<fmt:formatDate value="${pro.willShowTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss" />"
														data-willIssueTime="<fmt:formatDate value="${pro.willIssueTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss" />"
														class="am-btn am-btn-default am-btn-xs am-text-secondary product_issue btn-more-hover">
														<span class="am-icon-search"></span> 发布
													</button>
													<%--<button data-id="${pro.id }" class="am-btn am-btn-default am-btn-xs am-text-secondary product_issue_auto"><span class="am-icon-search"></span> 定时发布</button>		--%>
												</c:if>

												<c:if test="${pro.status == 4}">
													<button data-id="${pro.id }"
														class="am-btn am-btn-default am-btn-xs am-text-secondary product_complete btn-more-hover">
														<span class="am-icon-search"></span> 募集完成
													</button>
												</c:if>
											</div>
										</div>
									</div>
								</div>

							</td>

							<%--<td <c:if test="${pro.status == 1}" >title="${pro.remark}"</c:if>>--%>
							<%--<c:if test="${pro.status == 1}" >--%>
							<%--<c:if test="${fn:length(pro.remark) > 8}">--%>
							<%--${fn:substring(pro.remark, 0, 8)}...--%>
							<%--</c:if>--%>
							<%--<c:if test="${fn:length(pro.remark) <= 8}">--%>
							<%--${pro.remark}--%>
							<%--</c:if>--%>
							<%--</c:if>--%>
							<%--</td>--%>
							<td>
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										<c:if test="${!empty sessionScope.auths['product.online']}">
											<c:if
												test="${pro.status == 31 || pro.status == 4 || pro.status == 5 || pro.status == 6}">
												<button data-id="${pro.product_id }"
													style="height: 23px; width: 42px; background:url(<%=basePath%>static/product/image/outline-icon.png) no-repeat; border: 0;"
													class="am-btn am-btn-default am-btn-xs am-text-secondary product_online line-outline<c:if test="${pro.online == 1 }">am-disabled</c:if>"
													title="上线"></button>
												<button data-id="${pro.product_id }"
													style="height: 23px; width: 42px; background:url(<%=basePath%>static/product/image/online-icon.png) no-repeat;border: 0;"
													class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only product_outline line-outline <c:if test="${pro.online == -1 }">am-disabled</c:if>"
													title="下线"></button>
											</c:if>
										</c:if>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<form id="fbForm" action="<%=path %>/sys/product/issue.action" method="get">
	<div id="xmfb">
		<div class="box">
			<div>
				<h4>项目发布</h4>
				<input class="hide productId" name="productId" type="text">
				<div class="checkBtn">
					<div>
						<input type="radio" name="auditType" value="1" checked="checked">立即发布
					</div>
					<div>
						<input type="radio" name="auditType" value="2">预告发布
					</div>
				</div>
				<div class="btnBox">
					<div class="centent centent1">
						<a class="qd">确定</a> <a class="qx">取消</a>
					</div>
					<div class="centent centent2" style="display: none;">
						<div>
							<span>预告时间</span> 
							<input type="text"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						id="startDate" name="willIssueTime" class="input-text Wdate"     
						style="width:160px;">
						</div>
						<div>
							<span>发布时间</span> 
							<input type="text"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						id="startDate" name="willShowTime" class="input-text Wdate"     
						style="width:160px;" >
						</div>
						<div>
							<input type="checkbox" name="isSendMsg">发送移动推送
						</div>
						<div class="centent centent1">
							<a class="qd">确定</a> <a class="qx">取消</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
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
				window.location.reload();
			});
		});
		$(".product_delete").click(function(){
			var product_id = $(this).attr("data-id");
			var url = '<%=path%>' + "/sys/product/delete.action";
			$.post(url, {
				"product_id" : product_id
			}, function(data) {
				alert("删除成功");
				url = '<%=path%>' + "/sys/product/page.action";
				window.location = url;
			});
		});

		$('.product_issue').on('click', function() {
			var product_id =  $(this).attr("data-id");
			$('.productId').val(product_id);
			$("#xmfb").show();
		});
		
		$(".qd").click(function(){
			var form=$("#fbForm").serialize();
			var url='<%=path %>'+'/sys/product/issue.action';
			$.get(url,form, function(data) {
				if(data.returnCase){
					window.location.reload();
				}else{
					alert(data.content);
				}
			});
			$("#xmfb").hide();
		});
		$(".checkBtn div").click(function() {
			var index = $(this).index();
			$(this).children().prop("checked", true).parent().siblings().find("input[type='radio']").prop("checked", false);
			$(".btnBox .centent").eq(index).show().siblings().hide();
		});
		$('.qx').click(function() {
			$('#xmfb').hide();
		});
		$('.product_complete').on('click', function() {
			var product_id = $(this).attr("data-id");
			var url = '<%=path%>' + "/sys/product/complete.action";
			$.get(url, {
				"product_id" : product_id
			}, function(data) {
				window.location.reload();
			});
		});
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
						window.location=path+'/sys/product/page.action?currentPage='+obj.curr+'&limit=10';
					}
				}
			});
		})
		$('.btnCheck input').click(function(){
			console.log($(this).parent().siblings().find("input"));
			$(this).parent().siblings().find("input").porp("checked",false);
		});
		
	</script>
</body>
</html>
