<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="../../js/sys/favicon.ico">
<link rel="Shortcut Icon" href="../../js/sys/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="../../js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="../../js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="../../js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="../../js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="../../js/sys/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>借款方管理-编辑</title>
<meta name="keywords"
	content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<article class="page-container">
		<form action="<%=path%>/sys/loaner/add.action" method="post"
			class="form form-horizontal" id="form-member-add">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>出借类型：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> <select class="select loanerType"
						id="loanerType" name="loanerType" size="1">
							<option value="" selected>请选择出借类型</option>
							<option value="1">个人</option>
							<option value="2">企业</option>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>姓名/法人：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value=""
						placeholder="输入姓名/法人" id="name" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>手机号码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" placeholder="输入手机号码"
						id="phone" name="phone">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>身份证号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" placeholder="输入身份证号"
						id="idcard" name="idcard">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>婚姻状况：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> <select class="select" size="1"
						id="marital" name="marital">
							<option value="0" selected>请选择婚姻状态</option>
							<option value="1">已婚</option>
							<option value="-1">未婚</option>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>联系地址：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="输入联系地址"
						id="address" name="address">
				</div>
			</div>
			<div class="row com hide">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>企业简称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="输入企业简称"
						id="compAlias" name="compAlias">
				</div>
			</div>
			<div class="row com hide">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>企业名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="输入企业名称"
						id="compName" name="compName">
				</div>
			</div>
			<div class="row com hide">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>营业执照：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="输入营业执照"
						id="compCode" name="compCode">
				</div>
			</div>
			<div class="row com hide">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>注册日期：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="选择注册日期"
						id="regDate" name="regDate">
				</div>
			</div>
			<div class="row com hide">
				<label class="form-label col-xs-4 col-sm-3">注册资本：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="输入注册资本"
						id="regMoney" name="regMoney">
				</div>
			</div>
			<div class="row com hide">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>企业地址：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="输入企业地址"
						id="compAddress" name="compAddress">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">备注：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="输入备注"
						id="remark" name="remark">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>状态：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box"> <select class="select" size="1"
						id="status" name="status">
							<option value="0" selected>请选择状态</option>
							<option value="1">有效</option>
							<option value="2">无效</option>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">借款方相关图片：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<script id="editor6" type="text/plain" name="loanerUrls"
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
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input class="btn btn-primary radius save" type="submit"
							value="&nbsp;&nbsp;保存借款方&nbsp;&nbsp;">
					</div>
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="../../js/sys/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="../../js/sys/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="../../js/sys/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="../../js/sys/static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript"
		src="<%=path%>/ueditor/ueditor.config.js"></script>
	<script type="text/javascript"
		src="<%=path%>/ueditor/ueditor.all.min.js"> </script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="../../js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="../../js/sys/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="../../js/sys/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="../../js/sys/lib/jquery.validation/1.14.0/messages_zh.js"></script>

	<script type="text/javascript">
		var path = '<%=path%>';
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});
	
			$("#form-member-add").validate({
				rules : {
					username : {
						required : true,
						minlength : 2,
						maxlength : 16
					},
					sex : {
						required : true,
					},
					mobile : {
						required : true,
						isMobile : true,
					},
					email : {
						required : true,
						email : true,
					},
					uploadfile : {
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
			});
		});
		$('.loanerType').change(function() {
			var val = $(this).val();
			if ("2" == val) {
				$('.com').removeClass("hide");
			} else {
				$('.com').addClass("hide");
			}
		});
		$('.save').click(function() {
			var formData = $('#form-member-add').serialize();
			var url = path + '/sys/loaner/add.action';
			$.post(url, formData, function(data) {
				console.log(data);
			});
		});
		var editor_a6 = new baidu.editor.ui.Editor({
			toolbars : [ [ 'simpleupload', 'insertimage' ] ],
			imageActionName : "/upload/uploadFile.action?dir=loaner"
		});
		editor_a6.render('editor6');
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
		UE.Editor.prototype.getActionUrl = function(action) {
			if (action.indexOf('/upload/uploadFile.action') > -1) {
				return path + action;
			} else {
				return this._bkGetActionUrl.call(this, action);
			}
		}
	</script>
	<!--/请在上方写此页面业务相关的脚本-->

</body>
</html>