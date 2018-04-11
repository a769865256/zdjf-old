<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="${selfSite}/zdjf">
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</head>

<body>
<form  action="${chinapnrUrl}" method="post" id="huifu" charset="UTF-8">
    <c:forEach var="item"  varStatus="i" items="${rsp }">
        <c:if test="${item.key == 'ReqExt' || 'BorrowerDetails' == item.key}">
            <input type="hidden" name="${item.key }" value='${item.value }'>
        </c:if>
        <c:if test="${item.key != 'ReqExt' && 'BorrowerDetails' != item.key }">
            <input type="hidden" name="${item.key }" value="${item.value }">
        </c:if>
    </c:forEach>
    <input id="sub" type="submit" style="display: none">
</form>
</body>
<script type="text/javascript">
    document.getElementById("huifu").submit();
</script>
</html>
