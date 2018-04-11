<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tag/encode.tld" prefix="el" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/sys/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>推荐位管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 理财项目 <span class="c-gray en">&gt;</span> 推荐位管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	<span class="l"> <a class="btn btn-primary order_save"
				href="javascript:"><i
					class="Hui-iconfont">&#xe600;</i> 保存</a></span>
		<span class="r">共有数据：<strong>${page.total }</strong> 条</span>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="200">债权编号</th>
					<th width="200">项目编码</th>
					<th width="200">项目名称</th>
					<th width="200">项目状态</th>
					<th width="200">排序</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="pro"  items="${page.dataList }">
                        <tr class="trPro text-c" data-id="${pro.id }">
                            <td>${pro.debt_code }</td>
                            <td><a target="_blank" href="${platformUrl}/product/detail.html?productId=${pro.product_id }">${pro.product_code }</a></td>
                                <%--<td>${pro.productName }</td>--%>
                            <td title="${pro.product_name }">
                                <c:if test="${fn:length(pro.product_name) > 8}">
                                    ${fn:substring(pro.product_name, 0, 8)}...
                                </c:if>
                                <c:if test="${fn:length(pro.product_name) <= 8}">
                                    ${pro.product_name}
                                </c:if>
                            </td>

                            <td>${pro.status_text }</td>
                            <td class="am-text-middle">
                               <%--  <c:if test="${!empty sessionScope.auths['product.order']}"> --%>
                                    <div class="am-input-group am-input-group-sm ">
                                        <input type="number" class="am-form-field order_no" id="${pro.id }" name="order_${pro.id }" value="<c:if test="${pro.order_no != null}">${pro.order_no }</c:if>">

                                    </div>
                              <%--   </c:if> --%>
                              <%--   <c:if test="${empty sessionScope.auths['product.order']}">
                                    ${pro.order_no }
                                </c:if> --%>
                            </td>
                        </tr>
                    </c:forEach>
			</tbody>
		</table>
	</div>
</div>
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
	</script>
</body>
</html>