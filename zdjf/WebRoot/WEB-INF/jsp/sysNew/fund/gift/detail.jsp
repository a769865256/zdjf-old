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

<title>优惠管理 - 红包列表 - <c:if
		test="${null == giftType || giftType == ''|| giftType==2}">添加红包</c:if> <c:if
		test="${null != giftType && giftType ==2}">编辑红包</c:if></title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" id="form-article-add"
			action="<%=path%>/sys/fund/gift/add.action" method="post">
			<input type="hidden" name="giftId" value="${giftId }">
			<input type="hidden" name="giftType" value="${giftType }">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>红包名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }">${detail.gift_name}</c:if>"
						placeholder="输入红包名称" id="gift_name" name="gift_name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>红包金额：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }">${detail.money}</c:if>"
						placeholder="输入红包金额" id="money" name="money">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>最低投资金额：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }">${detail.max_amount}</c:if>"
						placeholder="输入投资金额" id="max_amount" name="max_amount">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>最低收益天数：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }">${detail.max_days}</c:if>"
						placeholder="输入收益天数" id="max_days" name="max_days">
				</div>
			</div>
				<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>获取方式：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">  
					<select id="gift_source" class="select"
						name="gift_source"
						data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
							<option value=""
								<c:if test="${null == detail || detail == '' }">selected</c:if>>请选择获取方式</option>
							<c:if test="${gift_source != null }">
								<c:forEach var="item" items="${gift_source }">
									<option value="${item.data_field_value }"
										<c:if test="${null != detail && detail.gift_source == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
								</c:forEach>
							</c:if>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>使用限制：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> 
					<select id="use_type" class="select"
						name="use_type"
						data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
							<option value=""
								<c:if test="${null == detail || detail == '' }">selected</c:if>>请选择使用限制方式</option>
							<c:if test="${date_type != null }">
								<c:forEach var="item" items="${date_type }">
									<option value="${item.data_field_value }"
										<c:if test="${null != detail && detail.use_type == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
								</c:forEach>
							</c:if>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>是否有效：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box">  
					<select id="status" class="select"
						name="status"
						data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
							<c:if test="${gift_status != null }">
								<c:forEach var="item" items="${gift_status }">
									<option value="${item.data_field_value }"
										<c:if test="${null != detail && detail.status == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
								</c:forEach>
							</c:if>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>有效期：</label>
				<div class="am-u-sm-4">
					<div class="am-btn-group am-u-sm-12 no-padding" data-am-button>
						<label
							class="am-btn am-btn-secondary am-u-sm-4 diff_mode_day dateType  <c:if test="${detail.date_type == 2 || detail==null}">am-active</c:if>"
							style="float: left;"> <input type="radio"
							id="diff_mode_day" name="date_type" value="2"
							<c:if test="${detail.date_type == 2 || detail==null}">checked="checked"</c:if>>
							固定天数
						</label>
						<label
							class="am-btn am-btn-secondary am-u-sm-4 diff_mode_date dateType">
							<input type="radio" id="diff_mode_date" name="date_type"
							value="1" <c:if test="${detail.date_type == 1 }">checked</c:if>>
							指定日期
						</label> 
						

					</div>
				</div>
				<div class="am-u-sm-5 giftDays <c:if test="${detail.date_type == 1 }">hide</c:if>">
					<input type="number" id="gift_days" name="gift_days"
						value="${detail.gift_days }" placeholder="输入大于0的数字" />
				</div>
			</div>
			
			<div class="row cl startDate <c:if test="${detail.date_type == 2 || detail==null}">hide</c:if>">
					<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>开始日期：</label>
					<div class="formControls col-xs-8 col-sm-9">
					<input type="text"
						onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'end_date\')||\'\'}' })"
						id="start_date" name="start_date" class="input-text Wdate"     
						style="width:120px;" value="<c:if test="${null != detail }"><fmt:formatDate value="${detail.start_date }" type="both" pattern="yyyy-MM-dd" /></c:if>">
					</div>
			</div>
			<div class="row cl endDate <c:if test="${detail.date_type == 2 || detail==null}">hide</c:if>">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>红包结束日期：</label>
					<div class="formControls col-xs-8 col-sm-9">
					<input type="text"
						onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'start_date\')}' })"
						id="end_date" name="end_date" class="input-text Wdate"     
						style="width:120px;" value="<c:if test="${null != detail }"><fmt:formatDate value="${detail.end_date }" type="both" pattern="yyyy-MM-dd" /></c:if>">
					</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red">*</span>发放数量：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }">${detail.max_count}</c:if>"
						placeholder="输入-1为不限制" id="max_count" name="max_count">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2"><span
					class="c-red"></span>备注：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text"
						value="<c:if test="${null != detail }">${detail.remark}</c:if>"
						placeholder="输入备注内容" id="remark" name="remark">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<c:if test="${giftType==1 }">
						<button class="btn btn-primary radius" >
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>
					</c:if>
					<c:if test="${giftType==2 }">
					<button class="btn btn-primary radius" >
						<i class="Hui-iconfont">&#xe632;</i> 更新
					</button>
					</c:if>
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->

	<script type="text/javascript">
		var path = '<%=path%>';
		$('.dateType').click(function(){
			if($(this).find("input[type=radio]").val()=="2"){
				$('.giftDays').removeClass('hide');
				$('.startDate').addClass('hide');
				$('.endDate').addClass('hide');
			}else if($(this).find("input[type=radio]").val()=="1"){
				$('.giftDays').addClass('hide');
				$('.startDate').removeClass('hide');
				$('.endDate').removeClass('hide');
			}
		});
		$('.radius').click(function(){
			$('#form-article-add').submit();
			window.parent.location.reload();
		});
	</script>
</body>
</html>