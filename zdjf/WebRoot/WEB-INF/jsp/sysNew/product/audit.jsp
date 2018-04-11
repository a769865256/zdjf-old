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
	href="<%=path%>/js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/js/sys/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>资讯列表</title>
</head>
<body>
<script type="text/javascript"
		src="<%=path%>/js/sys/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->
	<div class="page-container">
		<div class="am-modal-dialog">
			<div class="am-modal-hd"><strong class=" ">项目审核</strong></div>
			<div class="am-form-group">
				<label for="auditRemark" class="am-u-sm-12 modal-label">审核意见：</label>
				<textarea class="am-modal-prompt-input modal-textarea" rows="2"  id="auditRemark"></textarea>
			</div>
			<div class="hide product_id">${product_id }</div>
			<div class="am-modal-footer">
				<input type="button" class="pass" value="不通过"></input>
				<input type="button" class="pass" value="通过"></input>
			</div>
		</div>
	</div>
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="<%=path%>/js/sys/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript">
		$('.am-modal-footer input').on('click',function(){
			var remark=$('.modal-textarea').text();
			var index=$(this).index();
			console.log(index);
			var url='<%=path%>'+"/sys/product/audit.json";
			var product_id=$('.product_id').text();
			$.get(url, {"product_id" : product_id,"index":index,"remark":remark}, function(data) {
				console.log(data);
				if(data.status=='1'){
					alert("审核通过");
				}else{
				    alert(data.response_msg);
				}
				window.parent.location.reload();
			});
		});
	</script>
</body>
