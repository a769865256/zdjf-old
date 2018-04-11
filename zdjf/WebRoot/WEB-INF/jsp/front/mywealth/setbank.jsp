<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>正道金服</title>
    <!-- reset/iconfont -->
    <link rel="stylesheet" href="<%=path%>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path%>/css/front/index.css">
    <link rel="stylesheet" href="<%=path%>/css/front/wealth.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="open hide">
    <div class="tlt">
        <p>为了确保您的正常投资和资金安全，请开通上海银行存管账户。<a href="javascript:">立即开通</a></p>
    </div>
</div>
<div class="wealth">
    <jsp:include page="./left.jsp"></jsp:include>
    <div class="we_rt">
        <!-- 账户设置-绑定 -->
        <div class="go_unbund">
            <div class="go_unbund_head">
                <h3>银行卡设置</h3>
            </div>
            <div class="go_unbox">
                <ul>
                    <c:if test="${user.status==3}">
                    <li>
                        <p>
                            <span name="${userBanks[0].bank_alias}"></span>
                            <a href="<%=path%>/toUnboundBankCard.action" class="jiebang">解绑</a>
                        </p>
                        <p class="banknumber">${userBanks[0].bank_no}</p>
                        <p><span>默认提现卡</span></p>
                    </li>
                    </c:if>
                    <c:if test="${user.status==4}">
                    <li>
                        <a href="<%=path%>/toAddBankCard.action" class="iconfont addbank"></a>
                    </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- content end-->

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path%>/module/jquery/jquery.min.js"></script>
<script src="<%=path%>/module/sticky-header.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script src="<%=path%>/js/front/wealth.js"></script>
<script>
    /*银行卡显示前四后四，其他换成星星*/
    if ($(".banknumber").html() != null && $(".banknumber").html().length > 0) {
        var newBankCard = $(".banknumber").html().replace(/^(\d{4})\d+(\d{4})$/, '$1 **** **** $2');
        $(".banknumber").html(newBankCard);
    }
</script>
</body>
</html>
