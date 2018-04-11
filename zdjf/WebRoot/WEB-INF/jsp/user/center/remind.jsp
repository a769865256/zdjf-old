<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="${selfSite}/zdjf">
    <meta charset="UTF-8">
    <title>消息中心-我的财富-专业透明的互联网理财平台</title>
    <meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
    <meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
    <meta name="copyright" content="版权所有 © 正道金服">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=1200"/>
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/remind.css">
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/kkpager_gray.css">
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>

    <style>

    </style>
</head>
<body>
<jsp:include page="../comm/header.jsp"></jsp:include>
<div class="body clearfix">
    <jsp:include page="center_nav.jsp"></jsp:include>

    <div class="body-right">
        <p class="title">消息中心<a href="javascript:void(0);" class="J_all_read">全部标记为已读</a></p>
        <div class="con">
            <div class="info-lay">
                <ul>
                    <c:forEach var="item" items="${rsp.pageData.datas}">
                        <li <c:if test="${item.status ==1}">class="looked"</c:if>>
                            <span class="info-icon iconfont">&#xe640;</span>
                            <div class="tip-cons">
                                <span class="icon-arrow"></span>
                                <span class="icon-arrow01"></span>
                                <div>
                                    <p class="detail">${item.msgTxt}<a href="/center/remind/read?msgType=${item.msgType}&msgId=${item.msgId}">查看详情</a></p>
                                        <%--<c:if test="${item.msgType == 1 || item.msgType == 3 || item.msgType == 4}">
                                            <a href="/center/gift">查看详情</a>
                                        </c:if>
                                        <c:if test="${item.msgType == 2}">
                                            <a href="/center/gift?active=2">查看详情</a>
                                        </c:if>
                                        <c:if test="${item.msgType == 5}">
                                            <a href="/center">查看详情</a>
                                        </c:if>
                                        <c:if test="${item.msgType == 6}">
                                            <a href="/center/funds">查看详情</a>
                                        </c:if>--%>
                                    <p class="date"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" /></p>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <c:if test="${rsp.pageData.total == 0}">
                <!-- 无记录 -->
                <div class="nothing J_nothing">
                    <i class="iconfont">&#xe62a;</i>
                    暂无消息通知
                </div>
            </c:if>

            <p class="page">
                <jsp:include page="../comm/pager.jsp"></jsp:include>
            </p>
        </div>
    </div>
</div>
<div id="alert" style="z-index:-1;"></div>
<!--弹窗-->
<div class="alert alert-tip hide" id="repealAlert">
    <div class="alert-darkbg"></div>
    <div class="eject">
        <div class="title"></div>
        <div class="content">
            <p class="tip-title" style="margin-bottom: 20px;">是否确认撤销提现？</p>
            <p>
                <a href="javascript:void(0);" class="btn-red repeal">确定</a>
                <a href="javascript:void(0);" class="btn-red-border J_alertClose">取消</a>
            </p>
        </div>
    </div>
</div>

<jsp:include page="../comm/footer.jsp"></jsp:include>
<jsp:include page="../comm/helper.jsp"></jsp:include>

</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/remind.js"></script>
</html>