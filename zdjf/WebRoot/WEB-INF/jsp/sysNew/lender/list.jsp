<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../../js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../../js/sys/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<style>
#pro_page {
	text-align: center;
}
#pro_page2 {
	text-align: center;
}
</style>
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>基本设置</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
	<span class="c-gray en">&gt;</span>
	理财项目
	<span class="c-gray en">&gt;</span>
	出借方管理
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<form class="form form-horizontal" id="form-article-add">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>个人</span>
				<span>公司</span>
			</div>
			<div class="tabCon">
				<div class="page-container">
					<div class="text-c">
						<input type="text" name="" id="" placeholder="请输入姓名" style="width:300px" class="input-text">
						<input type="text" name="" id="" placeholder="请输入手机号码" style="width:300px" class="input-text">
						<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
					</div>
					<div class="mt-20">
					<table class="table table-border table-bordered table-hover table-bg table-sort">
						<thead>
							<tr class="text-c">
								<th width="200">前端账号</th>
								<th width="200">身份证号码</th>
								<th width="100">姓名</th>
								<th width="120">手机号码</th>
								<th width="100">年龄</th>
								<th width="100">性别</th>
								<th width="100">婚姻情况</th>
								<th width="100">状态</th>
								<th width="300">创建时间</th>
								<th width="100">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lender"  items="${detail.lender.dataList }">
                                        <tr class="text-c">
                                            <td><a href="<%=basePath%>user/detail?uid=${lender.id }" target="_blank">${lender.account }</a></td> 
                                            <td>${lender.idcard }</td>
                                            <td>${lender.name }</td>
                                            <td>${lender.phone }</td>
                                            <td>${lender.age }</td>
                                            <td>${lender.sex }</td>
                                            <td>
                                                <c:if test="${lender.marital == 1 }">
                                                    已婚
                                                </c:if>
                                                <c:if test="${lender.marital == -1 }">
                                                    未婚
                                                </c:if>
                                            </td>
                                            <td>${lender.status }</td>
                                            <td><fmt:formatDate value="${lender.create_time }" pattern="yyyy年MM月dd日 HH时mm分ss秒" /></td>
                                            <td>
                                                <div class="am-btn-toolbar">
                                                    <div class="am-btn-group am-btn-group-xs">

                                                       <%--  <c:if test="${!empty sessionScope.auths['lender.update']}" > 
                                                        </c:if>
                                                         <c:if test="${!empty sessionScope.auths['lender.delete'] && !empty lender.idcard}" > 
                                                        </c:if>  --%> 
                                                        <a title="编辑" href="javascript:;" onclick="member_edit('编辑','../../sys/lender/detail.action','${lender.id}','','600')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
														<a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
						</tbody>
					</table>
					</div>
				</div>
				<div id="pro_page"></div>
			</div>
			<div class="tabCon">
				<div class="page-container">
				<div class="mt-20">
					<table class="table table-border table-bordered table-hover table-bg table-sort">
						<thead>
							<tr class="text-c">
								<th width="200">公司</th>
								<th width="200">营业执照编号</th>
								<th width="120">公司所在地</th>
								<th width="200">注册时间</th>
								<th width="100">法人名称</th>
								<th width="100">联系电话</th>
								<th width="100">状态</th>
								<th width="100">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lender"  items="${detail.comp.dataList }">
                                        <tr>
                                            <td>${lender.comp_name }</td>
                                            <td>${lender.comp_code }</td>
                                            <td>${lender.comp_address }</td>
                                            <td>${lender.reg_date }</td>
                                            <td>${lender.name }</td>
                                             <td>${lender.phone }</td>
                                              <td>
                                                <c:if test="${lender.status == 1}">有效</c:if>
                                                <c:if test="${lender.status != 1}">无效</c:if>
                                                </td>
                                            <td>
                                                <div class="am-btn-toolbar">
                                                    <div class="am-btn-group am-btn-group-xs">

                                                       <%--  <c:if test="${!empty sessionScope.auths['lender.update']}" > 
                                                        </c:if>
                                                         <c:if test="${!empty sessionScope.auths['lender.delete'] && !empty lender.idcard}" > 
                                                        </c:if>  --%> 
                                                        <a title="编辑" href="javascript:;" onclick="member_edit('编辑','../../sys/lender/detail.action','${lender.id}','','600')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
														<a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
						</tbody>
					</table>
					</div>
				</div>
				<div id="pro_page2"></div>
			</div>
		</div>
	</form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../../js/sys/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../../js/sys/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../../js/sys/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../../js/sys/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../../js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="../../js/sys/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="../../js/sys/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<!-- <script type="text/javascript" src="../../js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>  -->
<script type="text/javascript" src="../../js/sys/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript">
/* $('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"pading":false,
	"ordering":false
}); */
var path='<%=path%>';
		var total="${detail.lender.total}";
		var total2="${detail.comp.total}";
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
						window.location=path+'/sys/loaner/list.action?currentPage='+obj.curr+'&limit=10';
					}
				}
			});
			laypage.render({  
				elem: 'pro_page2', 	//这里是 ID，不用加 # 号
				limit:10,
				groups:5,
				first:'首页',
				last:'尾页',
				theme:'#1187f1',
				count: total2,		//数据总数，从服务端得到
				curr: function(){
					  var page = location.search.match(/compcurrentPage=(\d+)/);  
	                   return page ? page[1] : 1;  
				}(),
				jump:function(obj,first){
					if(!first){
						window.location=path+'/sys/loaner/list.action?compcurrentPage='+obj.curr+'&limit=10';
					}
				}
			}); 
			
		});
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	url=url+"?lenderId="+id;
	layer_show(title,url,w,h);
}
/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});
	});
}


$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#tab-system").Huitab({
		index:0
	});
});
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
