<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui"/>
    <title>投资成功</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path %>/css/front/m/style.css"/>
    <link rel="stylesheet" href="<%=path %>/css/front/m/index.css"/>
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path %>/css/front/m/wealth.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<div class="wealth">
    <div class="in_ok">
        <img src="<%=path %>/images/front/m/mywealth/in_ok_01.png" alt="">
        <div class="in_ok_box">
           <h3><fmt:formatDate value="${content.pay_time }" pattern="yyyy-MM-dd" /></h3>
            <p>成功投资</p>
            <h3><fmt:formatDate value="${content.start_date }" pattern="yyyy-MM-dd" /></h3>
            <p>开始计算收益</p>
           <h3><fmt:formatDate value="${content.return_date }" pattern="yyyy-MM-dd" /></h3>
           <p>本息全部到账</p>
        </div>
         <a href="javascript:" class="in_ok">查看投资详情</a> 
    </div>
</div>



<script type="text/javascript" src="<%=path %>/js/front/m/zoom.js"></script>
<script type="text/javascript" src="<%=path %>/js/front/m/zepto.js"></script>
<script type="text/javascript" src="<%=path %>/js/front/m/wealth.js"></script>
<script type="text/javascript">
	var state='${content.returnCase }';
	var reg_source='${content.req_source}';
	var product_id='${content.product_id}';
	var buy_id='${content.buy_id}';
	$('.in_ok').click(function(){
		if(reg_source=="3"){
			window.android.showInvestSuccessState(product_id,buy_id);
		}else if(reg_source=="2"){  
			 window.location.href="checkProductDetail_"+product_id; 
		  } 
	});
</script>
</body>
</html>

