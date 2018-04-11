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
    <title>计息Demo</title>
    <meta name="keywords" content="正道金服">
    <meta name="description" content="正道金服">
    <meta name="copyright" content="版权所有 © 正道金服">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">

    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/product/css/demo.css">
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<jsp:include page="../comm/header.jsp"></jsp:include>

<!-- detail -->
<div class="content clearfix">

    <!-- info -->
    <div class="layout info">
        <div class="title"><b></b>账户信息</div>
        <div class="con">
            <ul>
                <li>
                    用户名：${rsp.phone}
                </li>
                <li>
                    可用余额：<fmt:formatNumber value="${rsp.fundStat.balance }" pattern="#,##0.00" type="number" />
                </li>
                <li>
                    待回收益：<fmt:formatNumber value="${rsp.fundStat.pendIncome }" pattern="#,##0.00" type="number" />
                </li>
                <li>
                    待回本金：<fmt:formatNumber value="${rsp.fundStat.pendReturn }" pattern="#,##0.00" type="number" />
                </li>
            </ul>
        </div>
    </div>

    <div class="line"></div>

    <!-- 产品列表 -->
    <div class="layout pro-list">
        <div class="title"><b></b>产品列表<span>(仅显示投资中产品)<span></div>
        <div class="con">
            <table>
                <tr>
                    <th>产品名称</th>
                    <th class="w280">操作</th>
                </tr>
                <c:forEach var="pro" items="${rsp.products}">
                    <tr>
                        <td><a href="${selfSite}/zdjf/product/detail/${pro.productId}" class="name" target="_blank">${pro.productCode} ${pro.productName}</a></td>
                        <td>
                            <input type="text">
                            <a href="javascript:void(0)" class="btn JBuy" data-id="${pro.productId}">购买</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>

    <div class="line"></div>

    <!-- 计息 -->
    <div class="layout profit">
        <div class="title"><b></b>计息</div>
        <div class="con">
            <table>
                <tr>
                    <th class="w200">产品名称</th>
                    <th class="w200">订单编号</th>
                    <th class="w100">还款类型</th>
                    <th class="w100">金额</th>
                    <th class="w150">预计还款日期</th>
                    <th class="w150">实际还款时间</th>
                    <th class="w100">状态</th>
                </tr>
                <c:forEach var="income" items="${rsp.incomes}">
                    <tr>
                        <td><a class="name profit-name">${income.productCode}</a></td>
                        <td><a class="name profit-name">${income.buyId}</a></td>
                        <td>
                            利息<c:if test="${income.returnFlag == 1}">+本金</c:if>
                        </td>
                        <td>
                            <c:if test="${income.returnFlag == 1}">
                                <fmt:formatNumber value="${income.amount + income.income }" pattern="#,##0.00" type="number" />
                            </c:if>
                            <c:if test="${income.returnFlag == -1}">
                                <fmt:formatNumber value="${income.income }" pattern="#,##0.00" type="number" />
                            </c:if>
                        </td>
                        <td>
                            <fmt:formatDate value="${income.payDate}" pattern="yyyy-MM-dd" />
                        </td>
                        <td>
                            <fmt:formatDate value="${income.actPayTime}" pattern="yyyy-MM-dd" />
                        </td>
                        <td>
                            <c:if test="${income.status == 1}">
                                <span class="text-green">已还</span>
                            </c:if>
                            <c:if test="${income.status == -1}">
                                <a href="javascript:void(0)" data-id="${income.incomeId}" class="btn-replay JRepay">还款</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="line"></div>

    <!-- 资金明细 -->
    <div class="layout amount">
        <div class="title"><b></b>资金明细</div>
        <div class="con">
            <table>
                <tr>
                    <th class="w150">日期</th>
                    <th class="w200">流水号</th>
                    <th class="w100">类型</th>
                    <th class="w150">金额（元）</th>
                    <th class="w150">余额</th>
                    <th class="w100">状态</th>
                </tr>
                <c:forEach var="fund" items="${rsp.fundses}">
                    <tr>
                        <td>
                            <fmt:formatDate value="${fund.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                            ${fund.fundsId}
                        </td>
                        <td>
                            ${fund.operateTypeText}
                        </td>
                        <td>
                            <c:if test="${fund.action == 2}">
                                -
                            </c:if>
                            <fmt:formatNumber value="${fund.amount }" pattern="#,##0.00" type="number" />
                        </td>
                        <td>
                            <fmt:formatNumber value="${fund.balance }" pattern="#,##0.00" type="number" />
                        </td>
                        <td>
                            成功
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

<jsp:include page="../comm/footer.jsp"></jsp:include>
<jsp:include page="../comm/helper.jsp"></jsp:include>

</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/product/js/demo.js"></script>
<!-- 图片切换引用 -->
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
</html>