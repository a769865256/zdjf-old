<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">

    <title>我的优惠-我的财富-专业透明的互联网理财平台，注册即送288</title>
    <meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
    <meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
    <meta name="copyright" content="版权所有 © 正道金服">

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=1200"/>
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/packet.css?t=20160604">
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/kkpager_gray.css">
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/plugin/swiper/swiper-3.4.2.min.css">
    <style>
        .swiper-slide-active{
            margin-left: 30px!important;
        }
    </style>
</head>

<body>
<jsp:include page="../comm/header.jsp"></jsp:include>
<div class="body">
    <jsp:include page="center_nav.jsp"></jsp:include>

    <!-- 内容 -->
    <div class="body-right">
        <p class="title">我的优惠
            <span style="margin-left: 500px;font-size: 16px;">正经值余额 <fmt:formatNumber type="number" pattern="#,##0.00" value="${rsp.stat.coinBalance}" />
        <a href="javascript:void(0)" class="look-detail J_openDetail" style="color: red;font-size: 14px;margin-left: 10px;">查看明细</a>
        </span></p>
        <p style="margin-left: 30px;font-size: 17px;">兑换专区</p>
        <div class="currency">
            <ul>
                <%--<li class="red-bg">--%>
                    <%--<p class="tit">正经值余额（元） <div class="iconfont">&#xe63b;--%>
                        <%--<div class="tip-con">--%>
                            <%--<span>正经值可在正道金服平台任一投资项目中使用，是平台的“硬通货”。投资人在投资时可用正经值直接抵扣本金。  <a href="${selfSite}/zdjf/about/noticeDetail.html?id=725645633406369792" target="_blank" class="btn-blue-t">了解更多</a></span>--%>
                            <%--<span class="icon-arrow"></span>--%>
                            <%--<span class="icon-arrow01"></span>--%>
                        <%--</div>--%>
                    <%--</div></p>--%>
                    <%--<p>--%>
                        <%--<fmt:formatNumber type="number" pattern="#,##0.00" value="${rsp.stat.coinBalance}" />--%>
                    <%--</p>--%>
                    <%--<a href="javascript:void(0)" class="look-detail J_openDetail">查看明细</a>--%>
                <%--</li>--%>
                <div class="swiper-container" style="height: 200px">
                    <div class="swiper-wrapper">
                        <c:forEach var="item" varStatus="idx" items="${rsp.goodses}">
                            <li  class="swiper-slide">
                           <%--<c:if test="${idx.index == 0}"><span class="ticket-title">兑换专区</span></c:if>--%>

                            <c:if test="${item.goodsType == 2}">
                                <div class="ticket-lay lay-jxq">
                                    <div class="iconfont">&#xe63b;
                                        <div class="tip-con">
                                            <span>收益天数≥${item.days}天<br/>${item.use}<br/>有效时间：${item.validDate}</span>
                                            <span class="icon-arrow"></span>
                                            <span class="icon-arrow01"></span>
                                        </div>
                                    </div>
                                    <p class="tic-jxq"><span>+</span>${item.relationValue}<span>%</span>　</p>
                                    <p class="ti-btn"><a href="javascript:void(0);" onclick="exchange('${item.goodsId}',${item.price},${item.goodsType},${item.relationValue});" class="J_convert"><fmt:formatNumber value="${item.price}" pattern="0.00"/>正经值兑换</a></p>
                                </div>
                            </c:if>
                                <c:if test="${item.goodsType == 3}">
                                    <div class="ticket-lay lay-hb">
                                        <div class="iconfont">&#xe63b;
                                            <div class="tip-con">
                                                <span>收益天数≥${item.days}天<br/>投资≥<fmt:formatNumber value="${item.minAmount}" pattern="#,###" />元<br/>${item.use}<br/>有效时间：${item.validDate}</span>
                                                <span class="icon-arrow"></span>
                                                <span class="icon-arrow01"></span>
                                            </div>
                                        </div>
                                        <p class="tic-hb"><fmt:formatNumber value="${item.relationValue}" pattern="0"/><span>元</span><span class="text-yellow"> 红包</span></p>
                                        <p class="ti-btn"><a href="javascript:void(0);" onclick="exchange('${item.goodsId}',${item.price},${item.goodsType},${item.relationValue});" class="J_convert"><fmt:formatNumber value="${item.price}" pattern="0.00"/>正经值兑换</a></p>
                                    </div>
                                </c:if>
                                <c:if test="${item.goodsType == 1}">
                                    <div class="ticket-lay lay-yhq">
                                        <p class="ti-btn "><a href="javascript:void(0);" onclick="exchange('${item.goodsId}',${item.price},${item.goodsType});" class="J_convert"><fmt:formatNumber value="${item.price}" pattern="0.00"/>正经值兑换</a></p>
                                    </div>
                                </c:if>
                            </i>

                            </li>
                        </c:forEach>
                    </div>
                    <%--<div class="swiper-pagination swiper-pagination"></div>--%>
                    <!-- 如果需要导航按钮 -->
                    <div class="swiper-button-prev swiper-button" ></div>
                    <div class="swiper-button-next swiper-button"></div>
                    <!-- 如果需要滚动条 -->
                    <%--<div class="swiper-scrollbar"></div>--%>
                </div>
                <%--<c:forEach var="item" varStatus="idx" items="${rsp.goodses}">--%>
                    <%--<li>--%>
                        <%--<c:if test="${idx.index == 0}"><span class="ticket-title">兑换专区</span></c:if>--%>

                        <%--<c:if test="${item.goodsType == 2}">--%>
                            <%--<div class="ticket-lay lay-jxq">--%>
                                <%--<div class="iconfont">&#xe63b;--%>
                                    <%--<div class="tip-con">--%>
                                        <%--<span>收益天数≥${item.days}天<br/>${item.use}<br/>有效时间：${item.validDate}</span>--%>
                                        <%--<span class="icon-arrow"></span>--%>
                                        <%--<span class="icon-arrow01"></span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<p class="tic-jxq"><span>+</span>${item.relationValue}<span>%</span>　</p>--%>
                                <%--<p class="ti-btn"><a href="javascript:void(0);" onclick="exchange('${item.goodsId}',${item.price},${item.goodsType},${item.relationValue});" class="J_convert"><fmt:formatNumber value="${item.price}" pattern="0.00"/>正经值兑换</a></p>--%>
                            <%--</div>--%>
                        <%--</c:if>--%>

                        <%--<c:if test="${item.goodsType == 3}">--%>
                            <%--<div class="ticket-lay lay-hb">--%>
                                <%--<div class="iconfont">&#xe63b;--%>
                                    <%--<div class="tip-con">--%>
                                        <%--<span>收益天数≥${item.days}天<br/>投资≥<fmt:formatNumber value="${item.minAmount}" pattern="#,###" />元<br/>${item.use}<br/>有效时间：${item.validDate}</span>--%>
                                        <%--<span class="icon-arrow"></span>--%>
                                        <%--<span class="icon-arrow01"></span>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<p class="tic-hb"><fmt:formatNumber value="${item.relationValue}" pattern="0"/><span>元</span><span class="text-yellow"> 红包</span></p>--%>
                                <%--<p class="ti-btn"><a href="javascript:void(0);" onclick="exchange('${item.goodsId}',${item.price},${item.goodsType},${item.relationValue});" class="J_convert"><fmt:formatNumber value="${item.price}" pattern="0.00"/>正经值兑换</a></p>--%>
                            <%--</div>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${item.goodsType == 1}">--%>
                            <%--<div class="ticket-lay lay-yhq">--%>
                                <%--<p class="ti-btn "><a href="javascript:void(0);" onclick="exchange('${item.goodsId}',${item.price},${item.goodsType});" class="J_convert"><fmt:formatNumber value="${item.price}" pattern="0.00"/>正经值兑换</a></p>--%>
                            <%--</div>--%>
                        <%--</c:if>--%>
                    <%--</li>--%>
                <%--</c:forEach>--%>
            </ul>
        </div>


        <p class="column J_column">
            <a href="${selfSite}/zdjf/center/gift" <c:if test="${rsp.active == 1}">class="btn-red"</c:if>>红包券<c:if test="${rsp.giftCount != 0}">(${rsp.giftCount})</c:if></a>
            <a href="${selfSite}/zdjf/center/gift?active=2" <c:if test="${rsp.active == 2}">class="btn-red"</c:if>>加息券<c:if test="${rsp.couponCount != 0}">(${rsp.couponCount})</c:if></a>
        </p>

        <c:if test="${rsp.active == 1}">
            <!--红包券-->
            <div class="con clearfix J_con">
                <!-- item- 添加used为已经使用过的，添加past-due为已过期红包 -->
                <c:forEach var="item" items="${ rsp.pageData.datas}">
                    <!-- item -->
                    <div
                        <c:if test="${item.status == 1 || item.status == 0}">
                            class="item "
                        </c:if>
                        <c:if test="${item.status == 2 }">
                            class="item used"
                        </c:if>
                        <c:if test="${item.status == 3 }">
                            class="item past-due"
                        </c:if>
                        <c:if test="${item.status == 4 }">
                            class="item ending"
                        </c:if>
                    >
                        <div class="info">
                            <div class="info-num overflow">
                                ￥<fmt:formatNumber value="${item.amount }" pattern="0" />
                            </div>
                            <div class="info-detail">
                                <div class="clearfix">
                                    <span class="font20 overflow" title="${item.giftName }">${item.giftName }</span>
                                    <c:if test="${item.status == 1 }">
                                        <a href="${selfSite}/zdjf/product/search.html">立即使用</a>
                                    </c:if>
                                    <c:if test="${item.status == 0 }">
                                        <span class="using-btn">使用中
                                            <div class="using-tip">
                                                <span class="icon-arrow"></span>
                                                <span class="icon-arrow01"></span>
                                                该券正在使用中，请至个人中心选择相应订单继续支付或取消订单<a href="${selfSite}/zdjf/center/orders">查看订单</a>
                                            </div>
                                        </span>
                                    </c:if>
                                </div>
                                <div class="font12">
                                    <p>投资≥<fmt:formatNumber value="${item.maxAmount}" pattern="0" />元  收益天数≥${item.maxDays}天</p>
                                    <p>
                                        <c:if test="${item.useType == 1}">
                                            不限
                                        </c:if>
                                        <c:if test="${item.useType == 2}">
                                            限新手标
                                        </c:if>
                                        <c:if test="${item.useType == 3}">
                                            限非新手标
                                        </c:if>
                                    </p>
                                    <p>有效时间：<fmt:formatDate value="${item.startDate }" type="both" pattern="yyyy.MM.dd" />-<fmt:formatDate value="${item.endDate }" type="both" pattern="yyyy.MM.dd" /></p>
                                </div>
                            </div>
                        </div>
                        <span class="icon-reason"></span>
                        <span class="icon-mark"></span>
                    </div>

                </c:forEach>

                <c:if test="${ rsp.pageData.total == 0 }">
                    <!-- 暂无红包 -->
                    <div class="nothing-show">
                        <p class="invitation-empty">
                            <i class="icon-invent"></i>
                            <span>暂无红包</span>
                        </p>
                    </div>
                </c:if>

                <div class="page">
                    <jsp:include page="../comm/pager.jsp"></jsp:include>
                </div>

            </div>
        </c:if>
        <c:if test="${rsp.active == 2}">
            <!--加息券-->
            <div class="con clearfix premium J_con">
                <!-- item- 添加used为已经使用过的，添加past-due为已过期红包 -->


                <c:forEach var="item" items="${ rsp.pageData.datas}">
                    <!-- item -->
                    <div
                            <c:if test="${item.status == 1 || item.status == 0}">
                                class="item "
                            </c:if>
                            <c:if test="${item.status == 2 }">
                                class="item used"
                            </c:if>
                            <c:if test="${item.status == 3 }">
                                class="item past-due"
                            </c:if>
                            <c:if test="${item.status == 4 }">
                                class="item ending"
                            </c:if>
                    >
                        <div class="info">
                            <div class="info-num overflow">
                                <b>+</b><fmt:formatNumber value="${item.interest }" pattern="0.00" />%
                            </div>
                            <div class="info-detail">
                                <div class="clearfix">
                                    <span class="font20 overflow" title="${item.couponName }">${item.couponName }</span>
                                    <c:if test="${item.status == 1 }">
                                        <a href="${selfSite}/zdjf/product/search.html">立即使用</a>
                                    </c:if>
                                    <c:if test="${item.status == 0 }">
                                        <span class="using-btn">使用中
                                            <div class="using-tip">
                                                <span class="icon-arrow"></span>
                                                <span class="icon-arrow01"></span>
                                                该券正在使用中，请至个人中心选择相应订单继续支付或取消订单<a href="${selfSite}/zdjf/center/orders">查看订单</a>
                                            </div>
                                        </span>
                                    </c:if>
                                </div>
                                <div class="font12">
                                    <p>收益天数≥${item.minDays}天</p>
                                    <p>
                                        <c:if test="${item.useType == 1}">
                                            不限
                                        </c:if>
                                        <c:if test="${item.useType == 2}">
                                            限新手标
                                        </c:if>
                                        <c:if test="${item.useType == 3}">
                                            限非新手标
                                        </c:if>
                                    </p>
                                    <p>有效时间：<fmt:formatDate value="${item.startDate }" type="both" pattern="yyyy.MM.dd" />-<fmt:formatDate value="${item.endDate }" type="both" pattern="yyyy.MM.dd" /></p>
                                </div>
                            </div>
                        </div>
                        <span class="icon-reason"></span>
                        <span class="icon-mark"></span>
                    </div>
                </c:forEach>

                <c:if test="${ rsp.pageData.total == 0 }">
                    <!-- 暂无红包 -->
                    <div class="nothing-show">
                        <p class="invitation-empty">
                            <i class="icon-invent"></i>
                            <span>暂无加息券</span>
                        </p>
                    </div>
                </c:if>

                <div class="page">
                    <jsp:include page="../comm/pager.jsp"></jsp:include>
                </div>

            </div>
        </c:if>
    </div>

</div>

<!-- 正经值明细 -->
<div class="alert currency-detail J_CDAlert hide">
    <div class="alert-darkbg J_alertClose"></div>
    <div class="eject">
        <input type="hidden" class="JCurrPage" value="1" >
        <div class="icon-close-black J_alertClose">×</div>
        <div class="box-info">
            <div class="name"><span>◆</span>正经值明细</div>
            <!-- tab -->
            <div class="plan hide">
                <div class="th">
                    <span class="w190">日期</span>
                    <span class="w170">类型</span>
                    <span class="text-left w160">变动金额（元）</span>
                    <span class="text-left w160">当前余额（元）</span>
                    <%--<span class="w180">备注</span>--%>
                </div>
                <div class="table">
                    <table>
                        <tr class="tit">
                            <th class="w190">日期</th>
                            <th class="w170">类型</th>
                            <th class="text-left w160">变动金额（元）</th>
                            <th class="text-left w160">当前余额（元）</th>
                            <%--<th class="w180">备注</th>--%>
                        </tr>
                        <tbody class="JDatas">

                        </tbody>
                        <!-- more -->
                        <tr class="more">
                            <td colspan="10">
                                <span class="hide JComplete">已显示全部</span>
                                <span class="hide JLoading">加载中...</span>
                                <a href="javascript: void(0)" class="JLoadMore">点击加载更多∨</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <!-- 暂无 -->
            <div class="nothing-show hide">
                <p class="invitation-empty">
                    <i class="icon-invent"></i>
                    <span>暂无记录</span>
                </p>
            </div>
        </div>
    </div>
</div>

<!--正经值兑换弹窗-->
<div class="alert alert-tip J_convertAlert hide">
    <div class="alert-darkbg J_alertClose"></div>
    <div class="eject">
        <div class="title"><i class="icon-close J_alertClose">×</i></div>
        <div class="content">
            <p class="tip-title">正经值兑换提醒</p>
            <p class="con J_exchange_msg" style="padding-bottom: 25px;">
                您确定用10正经值兑换一张1.00%的加息券吗？
            </p>
            <p>
                <a href="javascript:void(0);" class="btn-red J_exchange">确定</a>
            </p>
        </div>
    </div>
</div>

<!--正经值兑换弹窗-->
<div class="alert alert-tip J_exchange_alert hide">
    <div class="alert-darkbg J_alertClose"></div>
    <div class="eject">
        <div class="title"><i class="icon-close J_alertClose">×</i></div>
        <div class="content">
            <p class="tip-title">正经值兑换提醒</p>
            <p class="con J_exchange_result_msg" style="padding-bottom: 25px;">
                您确定用10正经值兑换一张1.00%的加息券吗？
            </p>
            <p>
                <a href="javascript:void(0);" class="btn-red J_exchange_complete">确定</a>
            </p>
        </div>
    </div>
</div>

<jsp:include page="../comm/footer.jsp"></jsp:include>
<jsp:include page="../comm/helper.jsp"></jsp:include>

<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/gift.js?v=1"></script>
<script type="text/javascript" src="${selfSite}/zdjf/plugin/swiper/swiper-3.4.2.jquery.min.js"></script>


</body>
</html>
