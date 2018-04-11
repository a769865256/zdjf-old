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
</style>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>公告管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 配置管理 <span class="c-gray en">&gt;</span> 渠道管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray">
		<span class="l">
			<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
			<a class="btn btn-primary radius" href="javascript:;" onclick="admin_role_add('添加渠道','<%=path%>/config/source/toDetail.action','800','550')"><i class="Hui-iconfont">&#xe600;</i> 新增渠道</a>
		</span>
		<span class="r">共有数据：<strong>${page.total }</strong> 条</span> </div>
	<table class="table table-border table-bordered table-hover table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="7">渠道管理</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" value="" name=""></th>
				<th width="150">所属平台</th>
				<th width="150">渠道名称</th>
				<th width="200">渠道代码</th>
				<th width="200">创建时间</th>
				<th width="200">操作</th>
				<th width="70">备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="line"  items="${detail.dataList }">
                            <tr  class="trLine" >
	                            <td class="am-text-middle">
									<label class="am-checkbox">
										<input type="checkbox" id="send_${line.id }" name="msgPhone" value="${line.id }" data-name="${line.id}" data-am-ucheck>
									</label>
								</td>
                                <td class="am-text-middle">
                                <c:if test="${line.source_platform==1 }">网站</c:if>
                                <c:if test="${line.source_platform==2 }">微信</c:if>
                                <c:if test="${line.source_platform==3 }">APP</c:if>
                                </td>
                                <td class="am-text-middle">${line.source_name}</td>
                                <td class="am-text-middle">${line.source_code }</td>
                                <td class="am-text-middle"><fmt:formatDate value="${line.create_time }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                <%--<td class="am-text-middle">${line.remark }</td>--%>
                                 <td class="f-14">
                                    <a title="渠道链接" href="javascript:;" onclick="" style="text-decoration:none">${line.source_url }</a>
									<a title="编辑" href="javascript:;" onclick="admin_role_add('编辑渠道','<%=path%>/config/source/toDetail.action?source_id='+${line.id},'800','550')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
									<a title="删除" href="javascript:;" data-id="${line.id }"  class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                                </td>
                                <td title="${line.remark }">
                                    <c:if test="${fn:length(line.remark) > 30}">
                                        ${fn:substring(line.remark, 0, 30)}...
                                    </c:if>
                                    <c:if test="${fn:length(line.remark) <= 30}">
                                        ${line.remark}
                                    </c:if>
                                </td>
                                <%--<td class="am-text-middle">${line.sourceUrl }</td>--%>
                               
                            </tr>
                        </c:forEach>
		</tbody>
	</table>
	<div id="pro_page"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=path%>/js/sys/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/sys/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/sys/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/sys/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->
<script src="<%=path%>/module/layui/layui.js"></script>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=path%>/js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
/*管理员-角色-添加*/
var path='<%=path%>';
/*管理员-角色-添加*/
function admin_role_add(title,url,w,h){
	layer_show(title,url,w,h);
}
$('.ml-5').click(function(){
	var url=path+"/config/source/delete.action";
	var source_id = $(this).attr("data-id");
	$.post(url, {
				"source_id" : source_id
			}, function(data) {
				alert("删除成功");
				url = '<%=path%>' + "/config/source/list.action";
				window.location = url;
			});
});
		var total="${detail.total}";
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
						window.location=path+'/config/source/list.action?currentPage='+obj.curr+'&limit=10';
					}
				}
			});
		});
</script>
</body>
</html>