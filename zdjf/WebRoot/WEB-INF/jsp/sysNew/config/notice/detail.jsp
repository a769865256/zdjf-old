<i><%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String path = request.getContextPath();
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）: 
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<head>
<!-- base需要放到head中 -->
<base href=" <%=basePath%>">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui/css/H-ui.min.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui.admin/css/H-ui.admin.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/lib/Hui-iconfont/1.0.8/iconfont.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui.admin/skin/default/skin.css" id="skin">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui.admin/css/style.css">
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/module/plupload-2.1.8/js/jquery.ui.plupload/css/jquery.ui.plupload.css" media="screen">
<script type="text/javascript" src="<%=path%>/js/sys/lib/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/sys/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="<%=path%>/module/jquery/jquery-ui.min.js" charset="UTF-8"></script>
<%-- <script type="text/javascript" src="<%=path %>/module/amui/amazeui.min.js"></script> --%>
<!-- 首先需要引入plupload的源代码 -->
<script type="text/javascript" src="<%=path%>/module/plupload-2.1.8/js/plupload.full.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path%>/module/plupload-2.1.8/js/jquery.ui.plupload/jquery.ui.plupload.min.js" charset="UTF-8"></script>
<!-- activate Georgian translation -->
<script type="text/javascript" src="<%=path%>/module/plupload-2.1.8/js/i18n/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/create.js"></script>
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="<%=path%>/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript"
	src="<%=path%>/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<!-- header end -->
	<div class="page-container">
		<!-- sidebar start -->
		<!-- sidebar end -->
		<!-- content start -->
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">公告管理</strong> / <small>新增公告</small>
				</div>
			</div>
			<hr>
			<div class="am-g">
				<form id="form" class="am-form am-form-horizontal" action="<%=path %>/config/notice/add.action" method="post">
					<input type="hidden" id="noticeId" name="noticeId" value="${noticeId }">
					<input type="hidden" id="noticeType" name="noticeType" value="${noticeType }">
					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-10">
							<div class="am-u-sm-12">
							<div class="am-form-group">
									<label for="incomeMethod" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>分类</label>
									<div class="am-u-sm-9">
										<select id="notice_type" name="notice_type" data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
											<%--<option value="all"  <c:if test="${null == detail}">selected</c:if>>请选择站点</option>--%>
											<c:if test="${notice_type != null }">
												<c:forEach var="item" items="${notice_type }">
													<option value="${item.data_field_value }" <c:if test="${null != detail && notice_type == item.data_field_value }">selected</c:if>="">${item.data_field_name }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
								<div class="am-form-group">
									<label for="title" class="am-u-sm-3 am-form-label "><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>标题</label>
									<div class="am-u-sm-9">
										<input type="text" id="title" name="title" value='<c:if test="${null != detail }">${detail.title}</c:if>' placeholder="输入标题">
									</div>
								</div>
								<div class="am-form-group">
									<label for="hrefUrl" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>外部链接</label>
									<div class="am-u-sm-9">
										<input type="text" id="link" name="link" value='<c:if test="${null != detail }">${detail.link}</c:if>' placeholder="输入外部链接">
									</div>
								</div>
								<div class="am-form-group">
									<label for="hrefUrl" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>来源</label>
									<div class="am-u-sm-9">
										<input type="text" id="source" name="source" value='<c:if test="${null != detail }">${detail.source}</c:if>' placeholder="输入来源">
									</div>
								</div>
								<div class="am-form-group">
									<label for="incomeMethod" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>选择站点</label>
									<div class="am-u-sm-9">
										<select id="web_site" name="web_site" data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
											<%--<option value="all"  <c:if test="${null == detail}">selected</c:if>>请选择站点</option>--%>
											<c:if test="${notice_web_site != null }">
												<c:forEach var="item" items="${notice_web_site }">
													<option value="${item.data_field_value }" <c:if test="${null != detail && detail.web_site == item.data_field_value }">selected</c:if>="">${item.data_field_name }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
								<div class="am-form-group">
									<label for="orderNumber" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>关键字</label>
									<div class="am-u-sm-9">
										<input type="text" id="keywords" name="keywords" value='<c:if test="${null != detail }">${detail.keywords}</c:if>' placeholder="输入关键字">
									</div>
								</div>
								<div class="am-form-group">
									<label for="orderNumber" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>描述</label>
									<div class="am-u-sm-9">
										<input type="text" id="web_desc" name="web_desc" value='<c:if test="${null != detail }">${detail.web_desc}</c:if>' placeholder="输入描述">
									</div>
								</div>
								<div class="am-form-group">
									<label for="orderNumber" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>摘要</label><div class="am-u-sm-9">
										<input type="text" id="description" name="description" value='<c:if test="${null != detail }">${detail.description}</c:if>' placeholder="输入摘要">
									</div>
									
								</div>
								<div class="am-form-group">
									<label for="orderNumber" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>排序</label>
									<div class="am-u-sm-9">
										<input type="text" id="order_number" name="order_number" value='<c:if test="${null != detail }">${detail.order_number}</c:if>' placeholder="输入排序">
									</div>
								</div>
								<div class="am-form-group">
									<label for="repayType" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>是否显示</label>
									<div class="am-u-sm-9">
										<select id="is_show" name="is_show" data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
											<%--<option value="all"  <c:if test="${null == detail}">selected</c:if>>请选择是否显示</option>--%>
											<c:if test="${notice_is_show != null }">
												<c:forEach var="item" items="${notice_is_show }">
													<option value="${item.data_field_value }" <c:if test="${null != detail && detail.is_show == item.data_field_value }">selected</c:if>="">${item.data_field_name }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
				<div class="row cl">
									<label class="form-label col-xs-4 col-sm-2">网站logo：</label>
									<div class="formControls col-xs-8 col-sm-9">
										<script type="text/plain" id="upload_ue"></script>
										<input type="text" id="picture" name="image_url" value="<c:if test="${null != detail }">${detail.image_url}</c:if>" /> <input
											type="button" class="datepicker" value="上传图片" />
										<img id="preview" src="<c:if test="${null != detail }">${detail.image_url}</c:if>">
				</div>
			</div>
								<div class="am-form-group">
								<label for="repayType" class="am-u-sm-3 am-form-label"><span class="am-icon-asterisk am-text-xs admin-icon-red"></span>内容</label>
										<div class="am-u-sm-9">
									<script id="editor" type="text/plain" name="content" style="width:100%;height:400px;">
${detail.content}									
</script>
								</div>
								</div>
								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3" style="margin-bottom: 100px;">
										<%-- <c:if test="${!empty sessionScope.auths['config.advertise.create']}" > --%>
										<c:if test="${noticeType=='1' }">
										<button type="button" class="am-btn am-btn-primary am-u-sm-12 notice_create">发布公告</button>
										</c:if>
										<c:if test="${noticeType=='2' }">
										<button type="button" class="am-btn am-btn-primary am-u-sm-12 notice_create">更新公告</button>
										</c:if>
										<%-- </c:if> --%>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- content end -->
	</div>
	<script type="text/javascript">
		var path='<%=path%>';
		$(function() {
			var editor_a1 = new baidu.editor.ui.Editor({
				imageActionName : "/upload/uploadFile.action?dir=notice"
			});
			editor_a1.render('editor');
			UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
			UE.Editor.prototype.getActionUrl = function(action) {
				if (action.indexOf('/upload/uploadFile.action') > -1) {
					return path + action;
				} else {
					return this._bkGetActionUrl.call(this, action);
				}
			}
		});
	$('.notice_create').unbind('click').on('click', function(){
		var path='<%=path%>';
		var url =  path + '/config/notice/add.action';
		var formData = $('#form').serialize();
		var options = {  
				url: url,   
	            type: 'POST',  
	            data: formData,
	            success: function(data) {  
	            			window.parent.location.reload();
	            }, 
	            error: function(data) {  
//	            	layer.close(loading);
//        			showMessage(data.responseText);
					alert("更新/新增失败");
	            }  
	        };
		$.ajax(options);
		return false;
	});
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
	</script>
</body>
</html></i>