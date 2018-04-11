<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../../js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>银行卡列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 会员管理 <span class="c-gray en">&gt;</span> 银行卡列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	 <span class="select-box inline">
		<select name="" class="select">
			<option value="0">用户名</option>
			<option value="1">分类一</option>
			<option value="2">分类二</option>
		</select>
	</span>
	<input type="text" name="" id="" placeholder=" 请输入要查询的值" style="width:250px" class="input-text">
	<span class="select-box inline">
		<select class="select" >
                                                    <option value="all"  >全部类型</option>
                                                   <option value="1">快捷支付</option>
													<option value="2">普通</option>
                                                </select>
	</span>
	<input type="text" name="" id="" placeholder=" 请输入要查询的值" style="width:250px" class="input-text">
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="r">共有数据：<strong>${page.total }</strong> 条</span>
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th class="table-title">用户名</th>
                        <th class="table-title">真实姓名</th>
                        <th class="table-title">银行名称</th>
                        <th class="table-type">银行卡号</th>
                        <th class="table-type">类型</th>
                        <th class="table-type">创建时间</th>
                        <th class="table-date am-hide-sm-only">状态</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${null != page && null != page.dataList }">
                        <c:forEach var="bank"  items="${page.dataList }">
                            <tr class="trLine text-c" data-id="${bank.bank_no}">
                                <td>${bank.phone }</td>
                                <td>${bank.card_user_name }</td>
                                <td>${bank.bank_alias }</td>
                                <td  id="${bank.bank_no}">${bank.bank_no }</td>
                                <td>
                                <c:if test="${bank.type==1 }">
                                	快捷支付
                                </c:if>
                                <c:if test="${bank.type==2 }">
                                	普通
                                </c:if>
                                </td>
                                <td><c:if test="${!empty bank.create_time }"><fmt:formatDate value="${bank.create_time }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></c:if></td>
                                <td>${bank.status }</td>
                            </tr>
                        </c:forEach>
                    </c:if>
			</tbody>
		</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../js/sys/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="../../js/sys/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../js/sys/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="../../js/sys/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../../js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="../../js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="../../js/sys/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"bStateSave": true,//状态保存
	"pading":false,
	"ordering":false
});
</script> 
</body>
</html>