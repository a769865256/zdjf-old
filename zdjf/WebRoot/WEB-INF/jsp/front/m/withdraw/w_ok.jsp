<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui" />
<title>提现成功</title>
<!-- 公共样式 -->
<link rel="stylesheet" href="<%=path%>/css/front/m/index.css" />
<!-- 公共样式end -->
<link rel="stylesheet" href="<%=path%>/css/front/m/style.css" />
	<link rel="stylesheet" href="<%=path%>/css/front/m/find.css" />
<style id="_zoom"></style>
	<style>
	.re_cost{height: 95px;line-height: 95px;padding: 0 30px;display: flex;background: #ffffff;}
	.re_cost .cost:first-child{font-size: 30px;color: #3b3b3b;flex:1;}
	.re_cost .co_box .cost_mon{font-size: 28px;color: #3b3b3b;}
	.re_cost .co_box .cost_btn{font-size: 28px;color: #008eff;border: 1px solid #008eff;padding: 5px 10px;margin: 0 0 0 48px;}
	.re_cost .co_box .dedu{font-size: 28px;color: #fa6453;}
	.re_addbtn .costsub{background: #2ca1fe;}
	.recost_tlt{padding: 10px 30px 50px;background: #ffffff;}
	.recost_tlt p{font-size: 20px;color: #9f9f9f;line-height: 30px;}
	.re_addbtn .costok{background: #2ca1fe;}
	.okstate{height: 270px;background:url(../../../../../images/front/m/withok.png) left 70px center no-repeat #ffffff;margin: 0 0 15px;background-size: 44px;text-indent: 140px;}
	.okstate .sta1{height: 185px;}
	.okstate .sta2{height: 85px;}
	.okstate h3{font-size: 30px;color: #3b3b3b;}
	.okstate p{font-size: 20px;color: #9f9f9f;}
	.okstate .sta1 h3{padding: 50px 0 10px;}
	.okstate .sta2 h3{padding-top: 4px;margin: 0;}

	.re_cost .co_box .state{font-size: 28px;color: #3b3b3b;}
	.re_addbtn a{display: block;margin: 0 30px;border-radius: 5px;line-height: 85px;text-align: center;background: #bababa;color: #ffffff;font-size: 32px;}
	.re_addbtn a.active{background:#008eff;}
	.re_addbtn p{font-size: 20px;text-align: center;color: #9f9f9f;padding: 10px 30px;line-height: 30px;}
	</style>
</head>
<body class="zoom">
	<div class="find">
		<div class="okstate">
			<div class="sta1">
				<h3>提现申请已提交</h3>
				<p><fmt:formatDate value="${content.withdraw_time }" pattern="yyyy-MM-dd" /></p> 
			</div>
			<div class="sta2">
				<h3>申请处理中</h3>
			</div>
		</div>
		<div class="re_cost">
			<div class="cost">提现金额(元):</div>
			<div class="co_box">
				<span class="state">¥${content.amount }</span>
			</div>
		</div>
		<div class="re_cost">
			<div class="cost">账户余额(元):</div>
			<div class="co_box">
				<span class="state">¥${content.balance }</span>
			</div>
		</div>
		<div class="re_addbtn">
			<a href="javascript:" class="costok">完成</a>
			<p style="text-align: left;">实际到账时间以银行为准，一般到账时间不超过24小时（法定节 假日除外）</p>
		</div>
	</div>
</body>
	<script>
	(function(){
	var _w,_zoom,_hd, _orientationChange,_doc = document,__style = _doc.getElementById("_zoom");
	__style || (_hd = _doc.getElementsByTagName("head")[0],__style=_doc.createElement("style"),_hd.appendChild(_style));
	_orientationChange = function(){
	_w    = _doc.documentElement.clientWidth || _doc.body.clientWidth;
	_zoom = _w / 640;
	__style.innerHTML = ".zoom {zoom:" + _zoom + ";-webkit-text-size-adjust:auto !important;text-size-adjust:auto !important;}";
	};
	__style.setAttribute("zoom",_zoom);
	_orientationChange();
	window.addEventListener("resize",_orientationChange,false);
	})();
	</script>
<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/recharge.js"></script>
<script type="text/javascript">
	var state='${content.returnCase }';
	var reg_source='${content.reg_source}';
	
	 $('.costok').click(function(){
		  if(reg_source=="3"){
			window.android.showWithDrawSuccessState(state);
		}else if(reg_source=="2"){  
			 window.location.href="WithdrawSuccess"; 
		  }  
 	});  
		 
	
	
</script>
</html>
