<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="${selfSite}/zdjf" />
    <meta charset="UTF-8">
    <title>错误</title>
    <meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
    <meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
    <meta name="copyright" content="版权所有 © 正道金服">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=1200"/>
    <link href="${selfSite}/zdjf/res/user/css/login.css" style="text/css" rel="stylesheet">
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="conert">
    ${msg}
    <input type="hidden" value="${info}">
</div>

<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="helper.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
</html>
