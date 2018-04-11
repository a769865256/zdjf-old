<!--_meta 作为公共模版分离出去-->
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
<link rel="Bookmark" href="<%=path%>/js/sys//favicon.ico">
<link rel="Shortcut Icon" href="<%=path%>/js/sys//favicon.ico" />
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
<link rel="stylesheet" type="text/css"
	href="<%=path%>/module/plupload-2.1.8/js/jquery.plupload.queue/css/jquery.plupload.queue.css" />
<script type="text/javascript"
	src="<%=path%>/js/sys/lib/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/module/jquery/jquery-ui.min.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=path%>/js/sys/lib/layer/2.4/layer.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/sys/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/sys/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/sys/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/sys/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="<%=path%>/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript"
	src="<%=path%>/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript"
	src="<%=path%>/module/plupload-2.1.8/js/plupload.full.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=path%>/module/plupload-2.1.8/js/jquery.ui.plupload/jquery.ui.plupload.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=path%>/module/plupload-2.1.8/js/i18n/zh_CN.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>理财管理 - 项目列表 - <c:if
		test="${null == detail || detail == '' }">添加项目</c:if> <c:if
		test="${null != detail && detail.status ==1}">编辑项目</c:if> <c:if
		test="${null != detail && detail.status !=1}">紧急修改项目</c:if></title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<style>
.errorMsg{
	color:red;
}
</style>
<body>
	<article class="page-container">
		<form class="form form-horizontal" id="form-article-add" 
			action="<%=path%>/sys/product/add.action" method="post">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>债权编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }">${detail.debt_code}</c:if>"
						placeholder="输入债权编号" id="debtCode" name="debtCode">
					<div class="errorMsg">${errorMsg }</div>
				</div>
			</div>
			<input type="hidden" id="isSerial" name="isSerial"
				value="${isSerial}" /> 
				<input type="hidden" id="update_type" name="update_type" value="<c:if test="${null != update_type }">${update_type}</c:if>">
				<input type="hidden" id="productId"
				name="productId"
				value="<c:if test="${null != product_id }">${product_id}</c:if>" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>分期设置：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<div class="am-u-sm-4-1">
						<div class="am-btn-group am-u-sm-12 no-padding" data-am-button>
							<label
								class="am-btn am-btn-secondary am-u-sm-6 data-am-ucheck <c:if test="${detail ==null || detail==''||detail.is_serial == -1}">am-active</c:if> ">
								<input type="radio" name="product_serial" value="-1"
								<c:if test="${detail ==null || detail==''||detail.is_serial == -1 }">checked</c:if>>
								不分期
							</label> <label
								class="am-btn am-btn-secondary am-u-sm-6 <c:if test="${detail.is_serial == 1 }">am-active</c:if> ">
								<input type="radio" name="product_serial" value="1"
								<c:if test="${detail.is_serial == 1 }">checked</c:if>>
								分期
							</label>
						</div>
					</div>
					<div class="am-u-sm-5-1">
						<input type="text" id="serialNo" name="serialNo"
							value="<c:if test="${null != detail }">${detail.serial_no}</c:if>"
							<c:if test="${detail ==null || detail==''||detail.is_serial == -1}">readonly</c:if>
							placeholder="输入分期编号" />
					</div>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>项目编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }">${detail.product_code}</c:if>"
						placeholder="输入项目编号" id="productCode" name="productCode">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>项目名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }">${detail.product_name}</c:if>"
						placeholder="输入项目名称" id="productName" name="productName">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>担保方式：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> <!-- <select name="articlecolumn" class="select">
					<option value="0">全部栏目</option>
					<option value="1">新闻资讯</option>
					<option value="11">├行业动态</option>
					<option value="12">├行业资讯</option>
					<option value="13">├行业新闻</option>
				</select> --> <select id="ensureType" class="select"
						name="ensureType"
						data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
							<option value=""
								<c:if test="${null == detail || detail == '' }">selected</c:if>>请选择担保方式</option>
							<c:if test="${ensure_type != null }">
								<c:forEach var="item" items="${ensure_type }">
									<option value="${item.data_field_value }"
										<c:if test="${null != detail && detail.ensure_type == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
								</c:forEach>
							</c:if>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>抵押方式：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> <select id="mortgageType"
						class="select" name="mortgageType"
						data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
							<option value=""
								<c:if test="${null == detail || detail == '' }">selected</c:if>>请选择抵押方式</option>
							<c:if test="${mortgage_type != null }">
								<c:forEach var="item" items="${mortgage_type }">
									<option value="${item.data_field_value }"
										<c:if test="${null != detail && detail.mortgage_type == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
								</c:forEach>
							</c:if>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>借款日期：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text"
						onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'endDate\')||\'\'}' })"
						id="startDate" name="startDate" class="input-text Wdate"     
						style="width:120px;" value="<c:if test="${null != detail }"><fmt:formatDate value="${detail.start_date }" type="both" pattern="yyyy-MM-dd" /></c:if>"> - <input type="text"
						onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'startDate\')}' })"
						id="endDate" name="endDate" class="input-text Wdate"
						style="width:120px;" value="<c:if test="${null != detail }"><fmt:formatDate value="${detail.end_date }" type="both" pattern="yyyy-MM-dd" /></c:if>">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>项目金额：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" style="width: 300px;" class="input-text"
						value="<c:if test="${null != detail }"><fmt:formatNumber value="${detail.debt_money}"
						  pattern="####"/></c:if>"
						placeholder="输入债权总额" id="debtMoney" name="debtMoney"> - <input
						type="text" style="width: 300px;" class="input-text"
						value="<c:if test="${null != detail }"><fmt:formatNumber value="${detail.money}"  pattern="####"/></c:if>"
						placeholder="输入本次转让金额" id="money" name="money">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>投资金额：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" style="width: 300px;" class="input-text"
						value="<c:if test="${null != detail }"><fmt:formatNumber value="${detail.pay_min}"  pattern="####"/></c:if>"
						placeholder="输入起投金额" id="payMin" name="payMin"> - <input
						type="text" style="width: 300px;" class="input-text"
						value="<c:if test="${null != detail }"><fmt:formatNumber value="${detail.pay_max}" pattern="####"/></c:if>"
						placeholder="输入最大投资金额" id="payMax" name="payMax">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>递增金额：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }"><fmt:formatNumber value="${detail.pay_add}"  pattern="####"/></c:if>"
						placeholder="输入递增金额" id="payAdd" name="payAdd">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>出借人：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> <c:if
							test="${null != detail && detail.status != 1}">
							<select id="lendId" class="select" name="lendId"
								data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}"
								readonly>
								<option value=""
									<c:if test="${null == detail || detail == '' }">selected</c:if>
									disabled>请选择出借人</option>
								<c:if test="${lender_list != null }">
									<c:forEach var="item" items="${lender_list }">
										<option value="${item.id }"
											<c:if test="${null != detail && detail.lender_id == item.id }">selected</c:if>
											<c:if test="${null != detail && detail.lender_id != item.id }">disabled</c:if>>${item.name }</option>
									</c:forEach>
								</c:if>
							</select>
						</c:if> <c:if test="${null == detail || detail.status == 1}">
							<select id="lendId" class="select" name="lendId"
								data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
								<option value=""
									<c:if test="${null == detail || detail == '' }">selected</c:if>>请选择出借人</option>
								<c:if test="${lender_list != null }">
									<c:forEach var="item" items="${lender_list }">
										<option value="${item.id }"
											<c:if test="${null != detail && detail.lender_id == item.id }">selected</c:if>>${item.name }</option>
									</c:forEach>
								</c:if>
							</select>
						</c:if>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>计息方式：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">
						<div class="am-u-sm-9">
							<select id="incomeMethod" class="select" name="incomeMethod"
								data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
								<option value=""
									<c:if test="${null == detail || detail == '' }">selected</c:if>
									disabled>请选择计息方式</option>
								<c:if test="${income_method != null }">
									<c:forEach var="item" items="${income_method }">
										<option value="${item.data_field_value }"
											<c:if test="${null != detail && detail.income_method == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>年化利率：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" style="width: 300px;" class="input-text"
						value="<c:if test="${null != detail }"><fmt:formatNumber value="${detail.income-detail.platform_interest}" pattern="0.00" /></c:if>"
						<c:if test="${(detail.status == 4 || detail.status == 5 || detail.status == 6 || detail.status == 31) }">readonly</c:if>
						placeholder="年化收益" id="income" name="income"> + <input
						type="text" style="width: 300px;" class="input-text"
						value="<c:if test="${null != detail }">${detail.platform_interest}</c:if>"
						<c:if test="${(detail.status == 4 || detail.status == 5 || detail.status == 6 || detail.status == 31) }">readonly</c:if>
						placeholder="贴息（选填）" id="platformInterest" name="platformInterest">
					/ <input type="text" style="width: 300px;" class="input-text"
						value="<c:if test="${null != detail }">${detail.income_offline}</c:if>"
						placeholder="线下年化利率" id="incomeOffline" name="incomeOffline">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>借款人：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> <select id="loanId" name="loanId"
						class="select"
						data-am-selected="{btnWidth: '100%', btnStyle: 'secondary',maxHeight: '200px'}">
							<option value=""
								<c:if test="${null == detail || detail == '' }">selected</c:if>>请选择借款人</option>
							<c:if test="${loaner_list != null }">
								<c:forEach var="item" items="${loaner_list }">
									<option value="${item.id }"
										<c:if test="${null != detail && detail.loan_id == item.id }">selected</c:if>><c:if
											test="${item.status ==1 }">${item.name }</c:if></option>
								</c:forEach>
							</c:if>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>付息方式：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> <select id="returnMethod"
						name="returnMethod" class="select"
						data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
							<option value=""
								<c:if test="${null == detail || detail == '' }">selected</c:if>>请选择还本付息方式</option>
							<c:if test="${return_method != null }">
								<c:forEach var="item" items="${return_method }">
									<option value="${item.data_field_value }"
										<c:if test="${null != detail && detail.return_method == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
								</c:forEach>
							</c:if>
					</select>
					</span>
					<td colspan="2">
						<div class="am-form-group JIncomes 
						<c:if test="${null==detail||(null!=detail and detail.return_method==1) }">hide</c:if>
						<c:if test="${null!=detail and detail.return_method==2 }"></c:if>
						">
							<label for="status" class="am-u-sm-3-1 am-form-label"><span
								class="am-icon-asterisk am-text-xs admin-icon-red"></span>付息模板</label>
							<div class="am-u-sm-9-1">
								<div class="am-form-group">
									<table
										class="am-table am-table-striped am-table-hover table-main">
										<thead>
											<tr>
												<th class="table-type">期次</th>
												<th class="table-type">起息日</th>
												<th class="table-type">结息日</th>
												<th class="table-type">付息日</th>
												<th class="table-type">操作</th>
											</tr>
										</thead>
										<tbody id="tbody_income">
											<c:if test="${null==detail }">
											<tr>
												<td>1</td>
												<td class="start_date"><input name="start_date"
													class="input-text Wdate" onfocus="WdatePicker({  })"  style="width:135px;height:20px;" type="text"></td>
												<td class="end_date"><input name="end_date" onfocus="WdatePicker({  })" class="input-text Wdate"  style="width:135px;height:20px;"
													type="text"></td>
												<td class="pay_date"><input name="pay_date" onfocus="WdatePicker({  })" class="input-text Wdate"  style="width:135px;height:20px;"
													type="text"></td>
												<td class="">
													<button type="button" class="income_delete">删除</button>
												</td>
											</tr>
											</c:if>
											<c:if test="${null!=detail and detail.return_method==2 and null!=productIncomeList }">
												<c:forEach varStatus="i" var="item" items="${productIncomeList }">
													<tr>
												<td>${i.count }</td>
												<td class="start_date"><input name="start_date"
													class="input-text Wdate" value="<fmt:formatDate value="${item.start_date }" type="both" pattern="yyyy-MM-dd" />" onfocus="WdatePicker({ minDate:'#F{\'%y-%M-%d\'}' })"  style="width:135px;height:20px;" type="text"></td>
												<td class="end_date"><input name="end_date" value="<fmt:formatDate value="${item.end_date }" type="both" pattern="yyyy-MM-dd" />" onfocus="WdatePicker({ minDate:'#F{\'%y-%M-%d\'}' })" class="input-text Wdate"  style="width:135px;height:20px;"
													type="text"></td>
												<td class="pay_date"><input name="pay_date" value="<fmt:formatDate value="${item.pay_date }" type="both" pattern="yyyy-MM-dd" />" onfocus="WdatePicker({ minDate:'#F{\'%y-%M-%d\'}' })" class="input-text Wdate"  style="width:135px;height:20px;"
													type="text"></td>
												<td class="">
													<button type="button" class="income_delete">删除</button>
												</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
									<table
										class="am-table am-table-striped am-table-hover table-main">
										<tbody>
											<tr>
												<td><c:if
														test="${detail.status != 4 && detail.status != 5 && detail.status !=6 && detail.status !=31 }">
														<button class="income_add" type="button">增加一行</button>
													</c:if></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</td>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>新手项目：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> <select id="isFresh"
						name="isFresh" class="select"
						data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
							<option value="-1"
								<c:if test="${(null == detail || detail =='')||(null != detail && detail.is_fresh == -1)}">selected</c:if>>非新手</option>
							<option value="1"
								<c:if test="${null != detail && detail.is_fresh == 1 }">selected</c:if>>新手</option>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">标签：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="<c:if test="${null != detail }">${detail.label}</c:if>" placeholder="">
				</div>
			</div>
			<div class="row cl">
				<!-- <label class="form-label col-xs-4 col-sm-2">封面图片：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor5" name="photo" type="text/plain"
						style="width:100%;height:400px;"></script>
				</div> -->
				<label class="form-label col-xs-4 col-sm-2">封面图片：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script type="text/plain" id="upload_ue"></script>
					<input type="text" id="picture" name="photo" value="<c:if test="${null != detail }">${detail.photo}</c:if>" /> <input
						type="button" class="datepicker" value="上传图片" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">项目概述：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor" type="text/plain" name="productDesc"
						style="width:100%;height:400px;" >
<c:if test="${null != detail }">${detail.product_desc}</c:if>
</script>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">借款用途及还款保障：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor2" type="text/plain" name="lendUse"
						style="width:100%;height:400px;" >
<c:if test="${null != detail }">${detail.lend_use}</c:if>
</script>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">财产保障信息：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor3" type="text/plain" name="protectMsg"
						style="width:100%;height:400px;">
<c:if test="${null != detail }">${detail.protect_msg}</c:if>
</script>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">保障措施：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor4" type="text/plain" name="protectMode"
						style="width:100%;height:400px;">
	 <c:if test="${null != detail }">${detail.protect_mode}</c:if>
					</script>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">抵押、信用相关图片：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor6" type="text/plain" name="infoUrls"
						style="width:100%;height:400px;">
					 <c:if test="${null != infoList }">
						<c:forEach var="item" items="${infoList }">
							<p><img src="${item.file_url }" alt="${item.file_name}"/></p>
						</c:forEach>
					</c:if>
					</script>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">律师意见图片：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor7" type="text/plain" name="suggestUrls"
						style="width:100%;height:400px;">
	<c:if test="${null != suggestList }">
						<c:forEach var="item" items="${suggestList }">
							<p><img src="${item.file_url }" alt="${item.file_name}"/></p>
						</c:forEach>
					</c:if>
	</script>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">合同文件图片：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor8" type="text/plain" name="contractUrls"
						style="width:100%;height:400px;">
<c:if test="${null != contranctList }">
						<c:forEach var="item" items="${contranctList }">
							<p><img src="${item.file_url }" alt="${item.file_name}"/></p>
						</c:forEach>
					</c:if>
</script>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">其它文件图片：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor9" type="text/plain" name="otherUrls"
						style="width:100%;height:400px;">
	<c:if test="${null != otherList }">
						<c:forEach var="item" items="${otherList }">
							<p><img src="${item.file_url }" alt="${item.file_name}"/></p>
						</c:forEach>
					</c:if>
</script>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<button class="btn btn-primary radius" type="submit">
						<i class="Hui-iconfont">&#xe632;</i> 保存并提交审核
					</button>
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->

	<script type="text/javascript">
		var path = '<%=path%>';
		$(function() {
			var editor_a1 = new baidu.editor.ui.Editor({
				imageActionName : "/upload/uploadFile.action?dir=desc"
			});
			editor_a1.render('editor');
			var editor_a2 = new baidu.editor.ui.Editor({
				imageActionName : "/upload/uploadFile.action?dir=lend_use"
			});
			editor_a2.render('editor2');
			var editor_a3 = new baidu.editor.ui.Editor({
				imageActionName : "/upload/uploadFile.action?dir=protect_msg"
			});
			editor_a3.render('editor3');
			var editor_a4 = new baidu.editor.ui.Editor({
				imageActionName : "/upload/uploadFile.action?dir=measures"
			});
			editor_a4.render('editor4');
			var editor_a5 = new baidu.editor.ui.Editor({
				toolbars : [ [ 'simpleupload', 'insertimage' ] ],
				imageActionName : "/upload/uploadFile.action?dir=measures"
			});
			editor_a5.render('upload_ue');
	
			/* 	var _editor = UE.getEditor(''); */
	
			editor_a5.ready(function() {
				//设置编辑器不可用
				//_editor.setDisabled();  这个地方要注意 一定要屏蔽
				//隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
				editor_a5.hide();
				//侦听图片上传
				editor_a5.addListener('beforeinsertimage', function(t, arg) {
					var imgs;
					for (var a in arg) {
						imgs += arg[a].src + ',';
					}
	
					$("#picture").attr("value", arg[0].src);
					//图片预览
					$("#preview").attr("src", arg[0].src);
				})
	
			});
			//弹出图片上传的对话框
			$('.datepicker').click(function() {
				var myImage = editor_a5.getDialog("insertimage");
				myImage.open();
			});
			var editor_a6 = new baidu.editor.ui.Editor({
				toolbars : [ [ 'simpleupload', 'insertimage' ] ],
				imageActionName : "/upload/uploadFile.action?dir=measures"
			});
			editor_a6.render('editor6');
			var editor_a7 = new baidu.editor.ui.Editor({
				toolbars : [ [ 'simpleupload', 'insertimage' ] ],
				imageActionName : "/upload/uploadFile.action?dir=measures"
			});
			editor_a7.render('editor7');
			var editor_a8 = new baidu.editor.ui.Editor({
				toolbars : [ [ 'simpleupload', 'insertimage' ] ],
				imageActionName : "/upload/uploadFile.action?dir=measures"
			});
			editor_a8.render('editor8');
			var editor_a9 = new baidu.editor.ui.Editor({
				toolbars : [ [ 'simpleupload', 'insertimage' ] ],
				imageActionName : "/upload/uploadFile.action?dir=measures"
			});
			editor_a9.render('editor9');
			UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
			UE.Editor.prototype.getActionUrl = function(action) {
				if (action.indexOf('/upload/uploadFile.action') > -1) {
					return path + action;
				} else {
					return this._bkGetActionUrl.call(this, action);
				}
			}
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});
	
			$('#returnMethod').change(function() {
				var index = $(this).children('option:selected').val();
				if (index == 1) {
					$('.JIncomes').addClass("hide");
				} else {
					$('.JIncomes').removeClass("hide");
				}
			});
			//表单验证
			/*	$("#form-article-add").validate({
					rules : {
						articletitle : {
							required : true,
						},
						articletitle2 : {
							required : true,
						},
						articlecolumn : {
							required : true,
						},
						articletype : {
							required : true,
						},
						articlesort : {
							required : true,
						},
						keywords : {
							required : true,
						},
						abstract : {
							required : true,
						},
						author : {
							required : true,
						},
						sources : {
							required : true,
						},
						allowcomments : {
							required : true,
						},
						commentdatemin : {
							required : true,
						},
						commentdatemax : {
							required : true,
						},
					},
					onkeyup : false,
					focusCleanup : true,
					success : "valid",
					submitHandler : function(form) {
						//$(form).ajaxSubmit();
						var index = parent.layer.getFrameIndex(window.name);
						//parent.$('.btn-refresh').click();
						parent.layer.close(index);
					}
				});*/
	
	
	
	
		});
		$(".income_add").click(function() {
		var len = $("#tbody_income > tr").size();
		var html = "";
		html += "<tr>"
		html += "<td>" + (len+1) + "</td>"
		html += "<td class=\"td_date\"><input class=\"input-text Wdate\"  style=\"width:135px;height:20px;\" type=\"text\" name=\"start_date\"/></td>";
		html += "<td class=\"td_date\"><input class=\"input-text Wdate\"  style=\"width:135px;height:20px;\" type=\"text\" name=\"end_date\"/></td>";
		html += "<td class=\"td_date\"><input class=\"input-text Wdate\"  style=\"width:135px;height:20px;\" type=\"text\" name=\"pay_date\"/></td>";
		html += "<td class=\"td_date\"><button  type=\"button\" class=\"income_delete\">删除</button></td>";
		html += "</tr>"
		$("#tbody_income").append(html);
		bindDateButton();
	});
	function bindDateButton() {
		$('.Wdate').focus(function(){
			WdatePicker({  });
		});
	}
	$(".income_delete").click(function() {
		$(this).parent().parent().remove();
	});

	</script>
</body>
</html>