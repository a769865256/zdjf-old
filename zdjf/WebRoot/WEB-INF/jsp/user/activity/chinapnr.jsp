<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>汇付天下</title>
    <meta name="keywords" content="正道金服">
    <meta name="description" content="正道金服">
    <meta name="copyright" content="版权所有 © 正道金服">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/activity/css/hftx.css">
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
</head>

<body>
<jsp:include page="../comm/header.jsp"></jsp:include>

<!-- detail -->
<div class="content">
    <div class="banner01"></div>
    <div class="banner02"></div>
    <div class="con">
        <p>　　<span>汇付天下</span>成立于2006年7月，专注于为传统行业、金融机构、小微企业及个人投资者提供金融账户、支付结算、运营风控、数据管理等综合金融服务。拥有中国人民银行、中国证监会、国家外汇管理局等监管机构颁发的《支付业务许可证》、基金支付牌照、基金销售牌照等资质。</p>
        <p>　　汇付天下提供的资金托管服务保障用户账户中的资金独立存放，非经客户许可或司法确认，包括汇付天下在内的任何单位、个人均无权对资金进行任何操作。在这基础上，汇付天下账户中每笔资金流动明细都能被投资者清晰地监控，凸显了资金的透明度，用户对资金有完全的控制权力。正道金服携手汇付天下，全程做到不吸储、不放贷、不集资，实行专人专户、专款专用，实现实时监管，资金零挪用。并且采用极高强度的加密和鉴别算法，切实保护用户敏感信息在互联网上的传输。</p>
    </div>
    <div class="banner03"></div>
</div>

<jsp:include page="../comm/footer.jsp"></jsp:include>
<jsp:include page="../comm/helper.jsp"></jsp:include>
</body>
</html>
