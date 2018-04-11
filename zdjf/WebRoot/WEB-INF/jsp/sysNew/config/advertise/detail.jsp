<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<link rel="stylesheet" type="text/css"
	href="<%=path%>/module/plupload-2.1.8/js/jquery.ui.plupload/css/jquery.ui.plupload.css"
	media="screen">
<script type="text/javascript"
	src="<%=path%>/js/sys/lib/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/sys/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=path%>/module/jquery/jquery-ui.min.js" charset="UTF-8"></script>
<%-- <script type="text/javascript" src="<%=path %>/module/amui/amazeui.min.js"></script> --%>
<!-- 首先需要引入plupload的源代码 -->
<script type="text/javascript"
	src="<%=path%>/module/plupload-2.1.8/js/plupload.full.min.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<%=path%>/module/plupload-2.1.8/js/jquery.ui.plupload/jquery.ui.plupload.min.js"
	charset="UTF-8"></script>
<!-- activate Georgian translation -->
<script type="text/javascript"
	src="<%=path%>/module/plupload-2.1.8/js/i18n/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="<%=path%>/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript"
	src="<%=path%>/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/create.js"></script>
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
					<strong class="am-text-primary am-text-lg">广告位管理</strong> / <small>新增广告位</small>
				</div>
			</div>
			<hr />
			<div class="am-g">
				<form id="form" class="am-form am-form-horizontal">
					<input type="hidden" id=advertiseId name="advertiseId"
						value="${advertiseId }" />
						<input type="hidden" id=advertiseId name="advertiseType"
						value="${advertiseType }" />
					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-10">
							<div class="am-u-sm-12">
								<div class="am-form-group">
									<label for="title" class="am-u-sm-3 am-form-label "><span
										class="am-icon-asterisk am-text-xs admin-icon-red"></span>标题</label>
									<div class="am-u-sm-9">
										<input type="text" id="title" name="title"
											value="<c:if test="${null != detail }">${detail.title}</c:if>"
											placeholder="输入标题" />
									</div>
								</div>
								<div class="am-form-group">
									<label for="alt" class="am-u-sm-3 am-form-label"><span
										class="am-icon-asterisk am-text-xs admin-icon-red"></span>alt属性</label>
									<div class="am-u-sm-9">
										<input type="text" id="alt" name="alt"
											value="<c:if test="${null != detail }">${detail.alt}</c:if>"
											placeholder="输入alt属性" />
									</div>
								</div>
							<div class="am-form-group">
									<label for="hrefUrl" class="am-u-sm-3 am-form-label"><span
										class="am-icon-asterisk am-text-xs admin-icon-red"></span>banner图片</label>
									<div class="am-u-sm-9">
										<script id="editor" type="text/plain" name="imageUrl" style="width:100%;height:400px;">
<c:if test="${null != detail }">											
<p><img src='${detail.image_url}' /></p>
</c:if>							
										</script>
									</div>
								</div>
								<div class="am-form-group">
									<label for="hrefUrl" class="am-u-sm-3 am-form-label"><span
										class="am-icon-asterisk am-text-xs admin-icon-red"></span>链接地址</label>
									<div class="am-u-sm-9">
										<input type="text" id="hrefUrl" name="hrefUrl"
											value="<c:if test="${null != detail }">${detail.href_url}</c:if>"
											placeholder="输入链接地址" />
									</div>
								</div>
								<div class="am-form-group">
									<label for="orderNumber" class="am-u-sm-3 am-form-label"><span
										class="am-icon-asterisk am-text-xs admin-icon-red"></span>排序</label>
									<div class="am-u-sm-9">
										<input type="number" id="orderNumber" name="orderNumber"
											value="<c:if test="${null != detail }">${detail.order_number}</c:if>"
											placeholder="输入排序" />
									</div>
								</div>

								<div class="am-form-group">
									<label for="incomeMethod" class="am-u-sm-3 am-form-label"><span
										class="am-icon-asterisk am-text-xs admin-icon-red"></span>站点</label>
									<div class="am-u-sm-9">
										<select id="webSite" name="webSite"
											data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
											<%--<option value="all"  <c:if test="${null == detail}">selected</c:if>>请选择站点</option>--%>
											<c:if test="${advertise_web_site != null }">
												<c:forEach var="item" items="${advertise_web_site }">
													<option value="${item.data_field_value }"
														<c:if test="${null != detail && detail.web_site == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
								<div class="am-form-group">
									<label for="type" class="am-u-sm-3 am-form-label"><span
										class="am-icon-asterisk am-text-xs admin-icon-red"></span>存放位置</label>
									<div class="am-u-sm-9">
										<select id="position" name="position"
											data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
											<%--<option value="all"  <c:if test="${null == detail}">selected</c:if>>请选择存放位置</option>--%>
											<c:if test="${advertise_position != null }">
												<c:forEach var="item" items="${advertise_position }">
													<option value="${item.data_field_value }"
														<c:if test="${null != detail && detail.position == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
								<div class="am-form-group">
									<label for="repayType" class="am-u-sm-3 am-form-label"><span
										class="am-icon-asterisk am-text-xs admin-icon-red"></span>是否显示</label>
									<div class="am-u-sm-9">
										<select id="show" name="show"
											data-am-selected="{btnWidth: '100%', btnStyle: 'secondary'}">
											<%--<option value="all"  <c:if test="${null == detail}">selected</c:if>>请选择是否显示</option>--%>
											<c:if test="${advertise_is_show != null }">
												<c:forEach var="item" items="${advertise_is_show }">
													<option value="${item.data_field_value }"
														<c:if test="${null != detail && detail.is_show == item.data_field_value }">selected</c:if>>${item.data_field_name }</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
								</div>
								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3"
										style="margin-bottom: 100px;">
										<%-- <c:if test="${!empty sessionScope.auths['config.advertise.create']}" > --%>
										<c:if test="${advertiseType==1 }">
										<button type="button"
											class="am-btn am-btn-primary am-u-sm-12 adv_create">发布广告位</button>
										</c:if>
										<c:if test="${advertiseType==2 }">
										<button type="button"
											class="am-btn am-btn-primary am-u-sm-12 adv_create">更新广告位</button>
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
</body>
<script type="text/javascript">
	var path='<%=path%>';
		$(function() {
			var editor_a1 = new baidu.editor.ui.Editor({
				toolbars : [ [ 'simpleupload', 'insertimage' ] ],
				imageActionName : "/upload/uploadFile.action?dir=advertise"
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
</script>
</html>