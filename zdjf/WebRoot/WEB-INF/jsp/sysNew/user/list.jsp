
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	href="../../js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="../../js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="../../js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="../../js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="../../js/sys/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
<style>
#pro_page {
	text-align: center;
}

#pro_page2 {
	text-align: center;
}

.modal-textarea {
	width: 90%;
	margin: 15px;
	margin-bottom: 0;
	padding: 5px;
	min-height: 42px;
}

.modal-label {
	margin: 1.5rem 0;
}

.modal-label select {
	width: 30%;
}

.layui-layer-btn {
	text-align: center !important;
}

.layui-layer-btn a {
	width: 40%;
}

.layui-layer-btn .layui-layer-btn0 {
	border-color: #dedede !important;
	background-color: #fff !important;
	color: #333 !important;
}

.layui-layer-btn .layui-layer-btn1 {
	border-color: #5a98de !important;
	background-color: #5a98de !important;
	color: #fff !important;
}
</style>
<!--[if IE 6]>
        <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
        <script>DD_belatedPNG.fix('*');</script>
        <![endif]-->
<title>用户管理</title>
</head>
<body>
	<form  id="conpon_form" >
	<div class="am-modal am-modal-prompt" id="coupon-prompt"
		style="display:none;">
		<div class="am-modal-dialog">
				<div class="am-modal-bd" style="margin-top: 15px;">
					<div class="am-form-group am-form-groupmodal-label">
						<label style="margin: 0 15px 0 15px;font-weight:normal;">赠送对象：</label>
						<label style="cursor: pointer;font-weight:normal;"><input
							type="radio" class="user_type_radio" value="1"
							name="user_type_radio" checked="checked"> 选中用户</label> <label
							style="cursor: pointer;font-weight:normal;"><input
							type="radio" class="user_type_radio" value="4"
							name="user_type_radio"> 筛选用户</label> <label
							style="cursor: pointer;font-weight:normal;"><input
							type="radio" class="user_type_radio" value="2"
							name="user_type_radio"> 所有用户</label> <label
							style="cursor: pointer;font-weight:normal;"><input
							type="radio" class="user_type_radio" value="3"
							name="user_type_radio"> 实名用户</label>
						<textarea class="am-modal-prompt-input modal-textarea JUserIds"
							rows="2" id="user_show_ids" name="user_ids" readonly></textarea>
						<input type="hidden" id="user_ids" value="">
					</div>
					<div class="am-form-group modal-label">
						<label style="margin: 0 15px 0 15px;font-weight:normal;">优惠券类型：</label>
						<select class="coupon_type" name="coupon_type" lay-verify="">
							<option value="1" selected>加息券</option>
							<option value="2">红包券</option>
							<option value="3">正经值</option>
						</select>
					</div>
					<div class="am-form-group modal-label coupon_select">
						<label style="margin: 0 15px 0 15px;font-weight:normal;">优惠券名称：</label>
						<select id="select_coupon_list" name="select_coupon_list">
							<option value="0" selected>请选择...</option>
							<c:forEach var="line" items="${params.couponList }">
							<option value="${line.id }">${line.coupon_name} ${line.interest}% ${line.min_days}天</option>
							</c:forEach>
						</select>
					</div>
					<div class="am-form-group modal-label coupon_input hide">
						<label style="margin: 0 15px 0 15px;font-weight:normal;">正经值金额：</label>
						<input type="text" id="input_coin" name="input_coin" style="width: 260px;">
					</div>
				</div>
			
		</div>
	</div>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		会员管理 <span class="c-gray en">&gt;</span> 会员列表 <a
			class="btn btn-success radius r"
			style="line-height:1.6em;margin-top:3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="text-c">
			<span class="select-box" style="width: 150px;"> <select
				name="searchType" class="select">
					<option value="0" selected>请选择查询值</option>
					<option value="1">手机号码</option>
					<option value="2">用户名</option>
					<option value="3">邀请人号码</option>
			</select>
			</span> <input type="text" name="searchParam" id="" placeholder="请输入要查询的值"
				style="width:200px" class="input-text" value="${params.searchParam }"> 注册时间： <input
				type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
				id="logmin" name="startDate" class="input-text Wdate" style="width:120px;"> -
			<input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="logmax" name="endDate" class="input-text Wdate" style="width:120px;">
			认证时间： <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })"
				id="expire" name="authStartDate" class="input-text Wdate" style="width:120px;"> -
			<input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })"
				id="expire" name="authEndDate" class="input-text Wdate" style="width:120px;">
			<p style="margin-top: 10px;">
				<span class="select-box" style="width: 150px;"> <select
					name="regSource" class="select">
						<option value="">注册来源</option>
						<c:forEach var="line" items="${params.reg_source }">
						<option value="${line.data_field_value}" <c:if test="${params.regSource==line.data_field_value }">selected</c:if> >${line.data_field_name }</option>
						</c:forEach>
				</select>
				</span> <span class="select-box" style="width: 150px;"> <select
					name="sourceType" class="select">
						<option value="">请选择渠道</option>
						<c:forEach var="line" items="${params.source_type }">
						<option value="${line.data_field_value}" <c:if test="${params.sourceType==line.data_field_value }">selected</c:if> >${line.data_field_name }</option>
						</c:forEach>
				</select>
				</span> <span class="select-box" style="width: 150px;"> <select
					name="userType" class="select">
						<option value="">请选择状态</option>
						<c:forEach var="line" items="${params.user_type }">
						<option value="${line.data_field_value}" <c:if test="${params.userType==line.data_field_value }">selected</c:if> >${line.data_field_name }</option>
						</c:forEach>
				</select>
				</span> <span class="select-box" style="width: 150px;"> <select
					name="isRealName" class="select">
						<option value="">是否实名</option>
						<option value="1">是</option>
						<option value="2">否</option>
				</select>
				</span> <span class="select-box" style="width: 150px;"> <select
					name="isRecharged" class="select">
						<option value="">是否充值</option>
						<option value="1">是</option>
						<option value="2">否</option>
				</select>
				</span> <span class="select-box" style="width: 150px;"> <select
					name="isPayed" class="select">
						<option value="">是否投资</option>
						<option value="1">是</option>
						<option value="2">否</option>
				</select>
				</span>
				<button name="" id="" class="btn btn-success search" >
					<i class="Hui-iconfont">&#xe665;</i> 搜索
				</button>
			</p>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"> <a href="javascript:;"
				onclick="picture_add('详情','','','520')"
				class="btn btn-primary radius"> 新增借款方</a> <a href="javascript:;"
				class="btn btn-primary send_coupon"> 赠送红包</a>
			</span> <span class="r">共有数据：<strong>${detail.total }</strong> 条
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="150">用户手机号</th>
						<th width="100">用户名</th>
						<th width="100">性别</th>
						<th width="100">可用余额</th>
						<th width="100">累计充值</th>
						<th width="100">累计提现</th>
						<th width="100">累计投资</th>
						<th width="100">投资次数</th>
						<th width="100">邀请人手机号</th>
						<th width="100">邀请人数</th>
						<th width="100">注册时间</th>
						<th width="100">注册来源</th>
						<th width="100">渠道</th>
						<th width="100">状态</th>
						<th width="100">备注</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="line" items="${detail.dataList }">
						<tr class="text-c">
							<td class="am-text-middle"><label class="am-checkbox">
									<input type="checkbox" id="send_${line.id }" name="msgPhone"
									value="${line.user_name }" data-name="${line.real_name}"
									data-am-ucheck>
							</label></td>
							<td class="am-text-middle">${line.phone}</td>
							<td class="am-text-middle">${line.real_name }</td>
							<td class="am-text-middle"><c:if test="${line.sex=='1'}">
                男
            </c:if> <c:if test="${line.sex=='2'}">
                女
            </c:if> <c:if test="${line.sex=='0'}">
                用户尚未实名认证
            </c:if></td>
							<td class="am-text-middle"><c:if
									test="${!empty line.balance }">
									<fmt:formatNumber value="${line.balance }" type="currency"
										pattern="￥#,##0.0#" />
								</c:if></td>
							<td class="am-text-middle"><c:if
									test="${!empty line.grandTotalInvested }">
									<fmt:formatNumber value="${line.totalRecharged }"
										type="currency" pattern="￥#,##0.0#" />
								</c:if></td>
							<td class="am-text-middle"><c:if
									test="${!empty line.withdrawed }">
									<fmt:formatNumber value="${line.withdrawed}" type="currency"
										pattern="￥#,##0.0#" />
								</c:if></td>
							<td class="am-text-middle"><c:if
									test="${!empty line.totalRecharged }">
									<fmt:formatNumber value="${line.grandTotalInvested }"
										type="currency" pattern="￥#,##0.0#" />
								</c:if></td>
							<td class="am-text-middle">${line.invest_frequency }</td>
							<td class="am-text-middle">${line.inviter_phone }</td>
							<td class="am-text-middle"><c:if
									test="${!empty sessionScope.auths['res.showPhone']}">
									<a target="_blank"
										href="<%=basePath%>user/list?searchType=4&searchParam=${line.phone }">
										<fmt:formatNumber value="${line.inviters }" type="currency"
											pattern="#,##0人" />
									</a>
								</c:if> <%-- <c:if test="${empty sessionScope.auths['res.showPhone']}">
                <a target="_blank" href="<%=basePath%>user/list?searchType=4&searchParam=${line.phoneFull }"><fmt:formatNumber value="${line.inviters }" type="currency" pattern="#,##0人"/></a>
            </c:if> --%></td>
							<td class="am-text-middle"><fmt:formatDate
									value="${line.create_time }" type="both" pattern="yyyy-MM-dd" />
								<br>
							<fmt:formatDate value="${line.create_time }" type="both"
									pattern="HH:mm:ss" /></td>
							<td class="am-text-middle">${line.reg_source }</td>
							<td class="am-text-middle">${line.invite_source }</td>
							<td class="am-text-middle">${line.status }</td>
							<td class="am-text-middle" title="${line.remark }"><span
								class="am-text-truncate" style="width: 90px;display :block;">
									<c:if test="${fn:length(line.remark ) > 12}">
                ${fn:substring(line.remark, 0, 12)}...
            </c:if> <c:if test="${fn:length(line.remark) <= 12}">
                ${line.remark}
            </c:if>
							</span></td>
							<td class="am-text-middle">
								<div class="am-btn-toolbar">
									<div class="am-btn-group am-btn-group-xs">
										<%-- <c:if test="${!empty sessionScope.auths['user.detail']}"> --%>
										<a title="编辑" href="javascript:;"
											onclick="article_edit('详情','./sys/user/toDetail.action','${line.phone}
            ','','510')"
											class="ml-5" style="text-decoration:none">详情</a>
										<%-- </c:if> --%>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="pro_page"></div>
		</div>
	</div>
	</form>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript"
		src="../../js/sys/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="../../js/sys/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="../../js/sys/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="../../js/sys/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer
        作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="../../js/sys/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<!-- <script type="text/javascript" src="../../js/sys/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
        -->
	<script type="text/javascript"
		src="../../js/sys/lib/laypage/1.2/laypage.js"></script>
	<script src="<%=path%>/module/layui/layui.js"></script>
	<script type="text/javascript">
        $('input:radio[name="user_type_radio"]').change(function(){
	        if($('input:radio[name="user_type_radio"]:checked').val()=='1'){
	       		$('.modal-textarea').show();
	        }else{
	        	$('.modal-textarea').hide();
	        }
        })
        $('.coupon_type').change(function(){
        	if($(this).val() == 3){
       			$('.coupon_select').hide().next().show();
        	}else if($(this).val() == 2){
        		$('.coupon_select').show().next().hide();
        		$('#select_coupon_list').empty();
        		$('#select_coupon_list').append("<option value='0'>请选择...</option>");
        		var couponList='${params.giftList}';
        		var obj=$.parseJSON(couponList);
        		for(var i=0;i<obj.length;i++){
        			var gift=obj[i];
        			var opt="<option value='"+gift.id+"'>"+gift.gift_name+" "+gift.money+"元 "+gift.max_amount+"元起投 "+gift.max_days+"天 </option>";
        			$('#select_coupon_list').append(opt);
        		}
        	}else if($(this).val() == 1){
        		$('.coupon_select').show().next().hide();
        		$('#select_coupon_list').empty();
        		$('#select_coupon_list').append("<option value='0'>请选择...</option>");
        		var giftList='${params.couponList}';
        		var obj=$.parseJSON(giftList);
        		for(var i=0;i<obj.length;i++){
        			var gift=obj[i];
        			var opt="<option value='"+gift.id+"'>"+gift.coupon_name+" "+gift.interest+"% "+gift.min_days+"天 </option>";
        			$('#select_coupon_list').append(opt);
        		}
        	}
        })
        
        $('.search').click(function(){
        	$('#conpon_form').attr('action','<%=path%>/sys/user/toList.action');
        	$('#conpon_form').attr('method','get');
        	$('#conpon_form').submit();
        });
        
        $('.send_coupon').click(function(){
        	var ids=false;
        	$('[name="msgPhone"]:checked').each(function() {
        		var val=$(this).val();
        		if(ids){
        			ids+=','+val;
        		}else{
        			ids=val;
        		}
        	})
        	if(ids){
        		$('.JUserIds').val(ids);
        	}
	        layer.open({
	            type: 1,
	            title: "优惠券赠送",
	            closeBtn: 1,
	            shadeClose: true,
	            btn: ['取消', '赠送'], //可以无限个按钮
				btn2: function(index, layero){
					$('#conpon_form').attr('action','<%=path%>/sys/user/sendGift.action');
        			$('#conpon_form').attr('method','post');
        			$('#conpon_form').submit();
				},
	            area: '540px',
	            skin: 'yourclass',
	            content: $('.am-modal')
        	});
        })
        /* $(function(){
        $('.table-sort').dataTable({
        "aaSorting": [[ 1, "desc" ]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
        //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
        {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
        ],
        "ordering":false
        });
        }); */
        /*会员详情*/
        function picture_add(title,url){
        var index = layer.open({
        type: 2,
        title: title,
        content: url
        });
        layer.full(index);
        }
        var path='<%=path%>';
        var total="${detail.total}";
        layui.use(['layer','laypage','element','carousel'], function(){
        var laypage=layui.laypage;
        laypage.render({ /*参考链接http://www.layui.com/doc/modules/laypage.html*/
        elem: 'pro_page', //这里是 ID，不用加 # 号
        limit:10,
        groups:5,
        first:'首页',
        last:'尾页',
        theme:'#1187f1',
        count: total, //数据总数，从服务端得到
        curr: function(){
        var page = location.search.match(/currentPage=(\d+)/);
        return page ? page[1] : 1;
        }(),
        jump:function(obj,first){
        if(!first){
        window.location=path+'/sys/user/toList.action?currentPage='+obj.curr+'&limit=10';
        }
        }
        });
        });
        /*用户-添加*/
        function member_add(title,url,w,h){
        layer_show(title,url,w,h);
        }
        /*用户-查看*/
        function member_show(title,url,id,w,h){
        layer_show(title,url,w,h);
        }


        /*用户-编辑*/
        function member_edit(title,url,id,w,h){
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
        </script>
</body>
</html>