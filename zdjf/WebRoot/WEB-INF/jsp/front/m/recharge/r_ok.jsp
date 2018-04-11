<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui" />
<title>充值成功</title>
<!-- 公共文件 -->
<link rel="stylesheet"
	href="<%=path%>/static/zdjf_app/static/css/style.css" />
<!-- 样式reset -->
<link rel="stylesheet"
	href="<%=path%>/static/zdjf_app/static/css/index.css" />
<!-- 公用样式 -->
<style id="_zoom"></style>
<!-- 公共文件end -->
<link rel="stylesheet"
	href="<%=path%>/static/zdjf_app/static/css/find.css" />
<link rel="stylesheet"
	href="<%=path%>/static/zdjf_app/static/css/recharge.css" />
</head>
<body class="zoom">
	<div class="find">
		<div class="ok_prompt">
			<div class="prompt_img">
				<img src="<%=path%>/static/zdjf_app/static/img/recharge/r_ok.png"
					alt="">
			</div>
			<div class="prompt" id="amount">充值成功！到账金额¥-元</div>
		</div>
		<div class="re_cost">
			<div class="cost">账户余额(元):</div>
			<div class="co_box">
				<span class="state" id="balance">-</span>
			</div>
		</div>
		<div class="mybtn">
			<a class="toUserCenter">确定</a>
			<!--         <a class="toInvest">去投资</a> -->
		</div>
	</div>
</body>
<script type="text/javascript"
	src="<%=path%>/static/zdjf_app/staticjs/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/static/zdjf_app/staticjs/jquery.md5.js"></script>
<script type="text/javascript"
	src="<%=path%>/static/zdjf_app/staticjs/common.js"></script>
<script src="<%=path%>/module/layer_mobile/layer.js"></script>
<script src="<%=path%>/static/zdjf_app/staticjs/public.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/zoom.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
<script>
    (function() {
        var reg_source = '${reg_source}';
        var amount='${user_balance}';
        var amt='${amt}';
        $("#balance").html("¥" + amount);
        $('.prompt').html("充值成功！到账金额¥"+amt+"元");
        $('.toUserCenter').click(function () {
            if(reg_source == 2){
                window.location.;
            }else if(reg_source == 3){
                window.location = 'toUserCenter';
            }
        })
        $('.toInvest').click(function () {
            if(reg_source == 2){
            		window.location.;
            }else{
            		window.location = 'toInvest';
            }
        })
    }());
</script>
</html>



