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
<title>资讯列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		财务管理 <span class="c-gray en">&gt;</span> 还款计划列表 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<form id="param" action="<%=path%>/sys/dynamic/toPage.action" method="get">
		<div class="text-c">
			出借人：<input type="text" name="lenderName" id="" placeholder="请输入出借人"
				style="width:300px" class="input-text" value="${lenderName }">
			借款人：<input type="text" name="loanName" id="" placeholder="请输入借款人"
				style="width:300px" class="input-text" value="${loanName }">
			还款时间： <input name="payStartTime"
				type="text"
				onfocus="WdatePicker({  })"
				id="logmin" class="input-text Wdate" style="width:120px;" value="${payStartTime }"> -
			<input type="text" name="payEndTime"
				onfocus="WdatePicker({  })"
				id="logmax" class="input-text Wdate" style="width:120px;" value="${payEndTime }">
			项目编码：<input type="text" name="productCode" id="" placeholder="请输入借款人"
				style="width:300px" class="input-text" value="${productCode }">

			
				<button name="" id="" class="btn btn-success" type="submit">
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
		</div>
		</form>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
				<button data-id="0" class="funds btn btn-primary radius">
					全部
				</button>
				<button data-id="3" class="funds btn btn-primary radius">
					金幸焕
				</button>
				<button data-id="4" class="funds btn btn-primary radius">
					周志平
				</button>
				<button data-id="1" class="funds btn btn-primary radius">
					王金涛
				</button>
				<button data-id="9" class="funds btn btn-primary radius">
					取消
				</button>
				
			<span class="l"> </span> <span class="r">共有数据：<strong>${page.total }</strong>
				条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
				<thead>
					<tr class="text-c">
						<th width="80">还款时间</th>
						<th width="80">出借人</th>
						<th width="80">借款人</th>
						<th width="100">项目编码</th>
						<th width="80">出借人余额</th>
						<th width="100">本金总额</th>
						<th width="100">当期应还本金</th>
						<th width="100">当期已还本金</th>
						<th width="100">剩余应还本金</th>
						<th width="100">当期应还利息</th>
						<th width="100">当期已还利息</th>
						<th width="100">当日应还本金</th>
						<th width="100">当日已还本金</th>
						<th width="100">当日应还利息</th>
						<th width="100">当日已还利息</th>
						<th width="100">还款状态</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="pro" items="${page.dataList }">
						<tr class="text-c">
						<c:if test="${pro.rowSpanStatus !=null && pro.rowSpanStatus==1}">
							<td rowspan="${pro.rowSpan}">${pro.pay_day }</td>
						</c:if>
						<c:if test="${pro.rowSpanStatus ==null && pro.rowSpan==null}">
						</c:if>
						<c:if test="${pro.rowSpanStatuss !=null && pro.rowSpanStatuss==1}">
							<td rowspan="${pro.rowSpans}">${pro.lender_name }</td>
						</c:if>
						<c:if test="${pro.rowSpanStatuss ==null && pro.rowSpans==null}">
						</c:if>
							<td>${pro.loan_name }</td>
							<td>${pro.product_code }</td>
						<c:if test="${pro.rowSpanStatuss !=null && pro.rowSpanStatuss==1}">
							<td rowspan="${pro.rowSpans}">${pro.lender_balance }</td>
						</c:if>
						<c:if test="${pro.rowSpanStatuss ==null && pro.rowSpans==null}">
						</c:if>
							<td>${pro.amount }</td>
							<td>${pro.now_to_pay_amount }</td>
							<td>${pro.now_payed_amount }</td>
							<td>${pro.now_to_pay_amount-pro.now_payed_amount }</td>
							<td>${pro.now_to_pay_income }</td>
							<td>${pro.now_payed_income }</td>
						<c:if test="${pro.rowSpanStatus !=null && pro.rowSpanStatus==1}">
							<td rowspan="${pro.rowSpan}">${pro.day_to_pay_amount }</td>
						</c:if>
						<c:if test="${pro.rowSpanStatus ==null && pro.rowSpan==null}">
						</c:if>
						<c:if test="${pro.rowSpanStatus !=null && pro.rowSpanStatus==1}">
							<td rowspan="${pro.rowSpan}">${pro.day_payed_amount }</td>
						</c:if>
						<c:if test="${pro.rowSpanStatus ==null && pro.rowSpan==null}">
						</c:if>
						<c:if test="${pro.rowSpanStatus !=null && pro.rowSpanStatus==1}">
							<td rowspan="${pro.rowSpan}">${pro.day_to_pay_income }</td>
						</c:if>
						<c:if test="${pro.rowSpanStatus ==null && pro.rowSpan==null}">
						</c:if>
						<c:if test="${pro.rowSpanStatus !=null && pro.rowSpanStatus==1}">
							<td rowspan="${pro.rowSpan}">${pro.day_payed_income }</td>
						</c:if>
						<c:if test="${pro.rowSpanStatus ==null && pro.rowSpan==null}">
						</c:if>
						<td>
						<c:if test="${pro.status==2 }">完成</c:if>
						<c:if test="${pro.status==1 }">未完成</c:if>
						</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<div id="pro_page"></div>
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
		
		var total="${page.total}";
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
						var data=$('#param').serialize();
						window.location=path+'/sys/dynamic/toPage.action?currentPage='+obj.curr+'&limit=10&'+data;
					}
				}
			});
		})
		$('.funds').on('click', function(){
			var that = $(this);
			var lender_id = that.attr("data-id");
			var flag=true;
			if(lender_id==9){
				flag=false;
			}
			var url=path+'/sys/dynamic/lock.action';
			$.get(url, {
				"lender_id" : lender_id,
				"flag":flag
			}, function(data) {
				alert(data.content);
				that.attr('disabled',true).siblings().attr('disabled',false);
			});
		});
		
	</script>
</body>
</html>
