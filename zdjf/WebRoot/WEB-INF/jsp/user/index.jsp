<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${selfSite}/zdjf">
	<meta charset="UTF-8">
	<title>正道金服 - 专业透明的互联网理财平台，注册即送288</title>
<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">

	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/>
    <meta content="telephone=no" name="format-detection" />
    <meta name="norton-safeweb-site-verification" content="uulj4e8ee4s784r9l7qh63--gjh5ooecfzpub3oboj4q5j80itv18m8f8nhyo2dcovy-fzfn-ozuqm81fltpcsyof308owfe3rm4-6ac6jv79zpvn76qd0k0654bub2h" />
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/index.css?t=20160429">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<jsp:include page="comm/header.jsp"></jsp:include>
	<!--banner-->
<div class="banner">
    <div class="slide-warp" id="slide-warp">
        <div class="slide-main">
            <ul>
                <c:forEach var="item" varStatus="itemStatus" items="${rsp.advertises}">
                    <li style="background: url(${item.imageUrl}); background-position: top center; background-repeat: no-repeat;<c:if test="${itemStatus.index == 0}">display:block;</c:if>">
                        <c:if test="${item.hrefUrl != null && item.hrefUrl != ''}">
                            <a href="${item.hrefUrl}" target="_blank"></a>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="slide-menu">
            <ul>
                <c:forEach var="item" varStatus="itemStatus" items="${rsp.advertises}">
                    <li <c:if test="${itemStatus.index == 0}">class="curr"</c:if>></li>
                </c:forEach>
            </ul>
        </div>
    </div>


    <!--login-layout-->
    <div class="login-fixed">
        <!--未登录-->
        <c:if test="${rsp.loginStatus == -1}">
        <div class="login-area show">
            <p class="profit-num"><span><fmt:formatNumber value="${rsp.income }" pattern="0.00" type="number" /></span>%</p>
            <p class="profit-tip">年化收益率</p>
            <p class="safe">账户资金由汇付天下全程托管</p>
            <p><a href="${selfSite}/zdjf/user/register" class="register-btn orange-btn">注册领红包</a></p>
            <p><a href="${selfSite}/zdjf/user/login" class="login-btn orangeline-btn">登录</a></p>
        </div>
		</c:if>
		<c:if test="${rsp.loginStatus == 1}">
        <!--已登录-->
        <div class="login-area show">
            <p class="username"><span>${rsp.phone }</span>, 欢迎来到正道金服</p>
            <p class="title">总资产(元)
                <c:if test="${rsp.info != null && rsp.info.richHide == 2}">
                    <i class="eye-show J_eye"></i>
                </c:if>
                <c:if test="${rsp.info == null || rsp.info.richHide == 1}">
                    <i class="eye-show eye-close J_eye"></i>
                </c:if>
            </p>
            <p class="assets text-shallow-orange">
            </p>
            <p><a href="${selfSite}/zdjf/center" class="register-btn orange-btn">我的资产</a></p>
            <p class="safe login"><i></i>账户资金由汇付天下全程托管</p>
        </div>
        </c:if>
    </div>
</div>

<!-- tip -->
<div class="bar">
<div class="layout text-gray">
<ul>
<li>
<i class="icon01"></i>
<p class="title">第三方资金托管</p>
<p>交易资金进出由汇付天下全程托管</p>
</li>
<li>
<i class="icon02"></i>
<p class="title">专业的法律顾问</p>
<p>律师事务所专业律师严控项目合法合规</p>
</li>
<li>
<i class="icon03"></i>
<p class="title">风险金保障</p>
<p>项目方提供风险保证资金保证安全</p>
</li>
<li>
<i class="icon04"></i>
<p class="title">交易过程安全</p>
<p>专业技术保障交易安全让您无忧理财</p>
</li>
</ul>
</div>
</div>



<div class="layout clearfix">
<!-- novice -->
<div class="novice border">
<div class="title overflow">
<a href="${selfSite}/zdjf/product/detail/${rsp.product.productId}" class="text-dark">
<span class="text-black">
<i class="icon-left"></i>${rsp.product.productCode }<i class="icon-right"></i>
</span>
${rsp.product.productName }
</a>
</div>

<div class="way text-gray"><c:if test="${rsp.product.status == 31}"><i class="herald">预告</i></c:if>计息方式：${rsp.product.incomeMethodText }</div>
<div class="info">
<div class="left"><a href="${selfSite}/zdjf/product/detail/${rsp.product.productId}"><img src="${rsp.product.photo }" alt=""></a></div>
<div class="right border text-dark">
<c:if test="${rsp.product.isFresh==1 }">
<i class="icon-new"></i>
</c:if>
<table>
<tr>
<th>年利率</th>
<th>收益天数</th>
<th>
    <c:if test="${rsp.product.status==4 || rsp.product.status==31}">
        可投金额
    </c:if>
    <c:if test="${rsp.product.status==5 || rsp.product.status==6}">
        项目总额
    </c:if>
</th>
</tr>
<tr>
<td class="text-orange"><span><fmt:formatNumber value="${rsp.product.income }" pattern="0.00" type="number" /></span>%</td>
<td><span>${rsp.product.incomeDaysTo }</span>天</td>

<td>
    <span>
        <c:if test="${rsp.product.status==4 || rsp.product.status==31}">
            <fmt:formatNumber value="${rsp.product.canBuyMoney }" pattern="#,###" type="number" />
        </c:if>
        <c:if test="${rsp.product.status==5 || rsp.product.status==6}">
            <fmt:formatNumber value="${rsp.product.money }" pattern="#,###" type="number" />
        </c:if>
    </span>
</td>
</tr>
</table>
<div>
<p>起投金额（元）：<span class="text-red"><fmt:formatNumber value="${rsp.product.payMin }" pattern="0" type="number" /></span></p>
<p>还款日期：<fmt:formatDate value="${rsp.product.endDate }" type="both" pattern="yyyy-MM-dd" /></p>
<c:if test="${rsp.product.status==4 }">
<a href="${selfSite}/zdjf/product/detail/${rsp.product.productId}" class="invest-btn red-btn">立即投资</a>
</c:if>
<c:if test="${rsp.product.status==5 }">
<a href="${selfSite}/zdjf/product/detail/${rsp.product.productId}" class="invest-btn red-btn close">履约中</a>
</c:if>
<c:if test="${rsp.product.status==6 }">
<a href="${selfSite}/zdjf/product/detail/${rsp.product.productId}" class="invest-btn red-btn close">已还款</a>
</c:if>
<c:if test="${rsp.product.status==31 }">
    <a href="${selfSite}/zdjf/product/detail/${rsp.product.productId}" class="invest-btn light-red-btn J_countdown" data-begintime="${rsp.product.willIssueTimes}">
    <span class="time">
        距上线
        <span class="hours">--</span>:
        <span class="minutes">--</span>:
        <span class="seconds">--</span>
    </span>
    </a>
</c:if>
</div>
</div>
</div>
</div>

<!-- news -->
<div class="news text-gray border">
<i class="icon-corner"></i>
<div class="title">官方公告</div>
<div class="more"><a href="${selfSite}/zdjf/notice">更多></a></div>
<ul class="news-list">
<c:forEach var="item" varStatus="noticeNo" items="${rsp.notices}">
<li><i class="icon-dian">·</i><a href="${selfSite}/zdjf/notice#${noticeNo.index + 1}" title="${item.title}" class="overflow text-dark">${item.title}</a><span class="text-gray"><fmt:formatDate value="${item.createTime}" pattern="MM-dd"/></span></li>
</c:forEach>
</ul>
</div>

</div>

<!-- flow -->
<div class="layout flow border">
<i></i>
</div>


<!-- product -->
<!-- PS:产品列表只需更改div.product后的blue或者orange即可，内部元素写法全一致，样式会自动变更 -->
<div class="product layout clearfix blue">
    <div class="tip-pic">
        <span class="pic"></span>
        <a href="${selfSite}/zdjf/product/list" class="more">更多理财项目></a>
    </div>

<c:forEach var="item" items="${rsp.list }">
<!-- product-info -->
    <div class="info">
    <c:if test="${item.status == 5 }">
        <i class="icon-agree"></i>
    </c:if>
    <c:if test="${item.status == 6 }">
        <i class="icon-repay"></i>
    </c:if>
        <div class="order-num">
            <a href="${selfSite}/zdjf/product/detail/${item.productId}" class="text-black">${item.productCode }</a>
            <c:if test="${item.status == 31}">
                <i class="herald">预告</i>
            </c:if>
            <c:if test="${item.isFresh == 1}">
                <i class="icon-new">新手专享</i>
            </c:if>
        </div>
        <div class="name"><a href="${selfSite}/zdjf/product/detail/${item.productId}" class="text-dark">${item.productName }</a></div>

    <c:if test="${item.status == 5 || item.status == 6 }">
        <div class="progress-bar">
            <div class="line">
                <!-- 此处修改百分比即可 -->
                <div class="pass" style="width:100%"></div>
            </div>
            <span class="percent">100.00%</span>
        </div>
        <div class="remain">项目总额<span class="text-red">￥<fmt:formatNumber value="${item.money }" pattern="#,###" type="number" /></span></div>
    </c:if>
    <c:if test="${item.status == 4 || item.status == 31}">
        <div class="progress-bar">
            <div class="line">
                <!-- 此处修改百分比即可 -->
                <div class="pass" style="width:${item.saleMoney/item.money*100  }%"></div>
            </div>
            <span class="percent"><fmt:formatNumber value="${item.saleMoney/item.money*100  }" pattern="0.00" type="number" />%</span>
        </div>
        <div class="remain">剩余可投金额<span class="text-red">￥<fmt:formatNumber value="${item.canBuyMoney }" pattern="#,###" type="number" /></span></div>
    </c:if>
        <div class="profit">
            <p class="num text-orange"><span><fmt:formatNumber value="${item.income }" pattern="0.00" type="number" /></span>%</p>
            <p class="text-dark">年收益率</p>
        </div>
        <div class="pro-pic">
            <a href="${selfSite}/zdjf/product/detail/${item.productId}"><img src="${item.photo }" alt=""></a>
        </div>
        <div class="other text-dark">
            <p>收益天数<span class="text-orange"><b>${item.incomeDaysTo }</b>天</span></p>
            <p>还款日期<span class="text-black"><fmt:formatDate value="${item.endDate }" type="both" pattern="yyyy-MM-dd" /></span></p>
        </div>
    <c:if test="${item.status==4 }">
        <!-- 启用中，可点击 -->
        <a  href="${selfSite}/zdjf/product/detail/${item.productId}" class="invest-btn">立即投资</a>
    </c:if>
    <c:if test="${item.status==5 || item.status==6 }">
        <!-- 关闭中，不可点击 -->
        <a  href="${selfSite}/zdjf/product/detail/${item.productId}" class="invest-btn close">查看详情</a>
    </c:if>
    <c:if test="${item.status == 31}">
        <a href="${selfSite}/zdjf/product/detail/${item.productId}" class="invest-dcbtn J_countdown" data-begintime="${item.willIssueTimes}">
            <span class="time">
                距上线
                <span class="hours">--</span>:
                <span class="minutes">--</span>:
                <span class="seconds">--</span>
            </span>
        </a>
    </c:if>
    </div>

    </c:forEach>
</div>



<%--


<!-- product -->
<div class="product layout clearfix orange hide">
<div class="tip-pic">
<span class="pic"></span>
<a href="#" class="more">更多理财项目></a>
</div>

<!-- product-info -->
<div class="info">
<i class="icon-close hide"></i>
<div class="order-num">正道金服1806期</div>
<div class="name text-dark">路虎揽胜极光汽车合格证质押债权项目</div>
<div class="progress-bar">
<div class="line">
<!-- 此处修改百分比即可 -->
<div class="pass" style="width:36%">
<i></i>
</div>
</div>
<span class="percent">36%</span>
</div>
<div class="remain">剩余可投金额<span class="text-red">￥30,000</span></div>
<div class="profit">
<p class="num text-orange"><span>12.0</span>%</p>
<p class="text-dark">年收益率</p>
</div>
<div class="pro-pic">
<img src="images/index/photo.png" alt="">
</div>
<div class="other text-dark">
<p>收益天数<span class="text-orange"><b>60</b>天</span></p>
<p>还款日期<span class="text-black">2016-01-23</span></p>
</div>
<!-- 启用中，可点击 -->
<a  href="#" class="invest-btn">立即投资</a>
<!-- 关闭中，不可点击 -->
<a  href="#" class="invest-btn close hide">履约中</a>
</div>

<!-- product-info -->
<div class="info">
<i class="icon-close hide"></i>
<div class="order-num">正道金服1806期</div>
<div class="name text-dark">路虎揽胜极光汽车合格证质押债权项目</div>
<div class="progress-bar">
<div class="line">
<!-- 此处修改百分比即可 -->
<div class="pass" style="width:36%">
<i></i>
</div>
</div>
<span class="percent">36%</span>
</div>
<div class="remain">剩余可投金额<span class="text-red">￥30,000</span></div>
<div class="profit">
<p class="num text-orange"><span>12.0</span>%</p>
<p class="text-dark">年收益率</p>
</div>
<div class="pro-pic">
<img src="${selfSite}/zdjf/res/comm/images/index/photo.png" alt="">
</div>
<div class="other text-dark">
<p>收益天数<span class="text-orange"><b>60</b>天</span></p>
<p>还款日期<span class="text-black">2016-01-23</span></p>
</div>
<!-- 启用中，可点击 -->
<a  href="#" class="invest-btn">立即投资</a>
<!-- 关闭中，不可点击 -->
<a  href="#" class="invest-btn close hide">履约中</a>
</div>
</div>
--%>
<div>
 <div class="left">
    <p>39天</p>
    <p>收益天数</p>
 </div>
 <div class="right"></div>
      <p>100,000元</p>
    <p>可投金额</p>
</div>

<!-- reason -->
<div class="reason">
<div class="layout">
<div class="title">为什么选择正道金服</div>
<ul>
<li>
<i class="icon01"></i>
<p class="name">产品可靠</p>
<p>平台产品一律经过系统严格筛选，保证投资人合法权益</p>
</li>
<li>
<i class="icon02"></i>
<p class="name">平台稳定</p>
<p>集优秀律师、风控、互联网、高管人才于一体的智略经营，保障平台稳定前行</p>
</li>
<li>
<i class="icon03"></i>
<p class="name">信息透明</p>
<p>平台所有信息公开透明，材料真实有效，精心打造安全的理财环境</p>
</li>
<li>
<i class="icon04"></i>
<p class="name">合法合规</p>
<p>严格遵守有关法律法规经营管理平台与业务，随时随地检测风险，确保全面安全</p>
</li>
<li>
<i class="icon05"></i>
<p class="name">资金安全</p>
<p>所有投资资金均由专业的第三方进行资金托管，专款专用实时监督</p>
</li>
</ul>
</div>
</div>



<!-- friend -->
<div class="friend">
    <div class="layout">
        <div class="title">合作伙伴</div>
        <div class="friend-list infoSwitch J_licenseList">
            <a class="next" href="javascript:void(0)"><i></i></a>
            <a class="prev" href="javascript:void(0)"><i></i></a>
            <div class="box">
                <ul class="list">
                    <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/ccb.gif"></li>
                    <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/abc.gif"></li>
                    <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/sunshine-ins.gif"></li>
                    <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/dmls.gif"></li>
                    <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/icbc.gif"></li>
                    <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/cba.gif"></li>
                </ul>
        </div>
        </div>
    </div>
</div>
<jsp:include page="comm/footer.jsp"></jsp:include>
<jsp:include page="comm/helper.jsp"></jsp:include>

    <!-- 注册成功跳转Alert -->
    <div class="alert reg-success J_alert hide">
        <div class="alert-darkbg"></div>
        <div class="eject">
            <%--<div class="icon-close-white J_close"></div>--%>
                <div class="con"><b><b><fmt:formatNumber value="${rsp.giftMoney}" pattern="0" type="number" />元</b>红包</b>和<b><b>${rsp.couponCount}张</b>加息券</b>已放入您的账户中，<br/>实名认证后就能使用啦！</div>
            <div><a href="${selfSite}/zdjf/chinapnr/req/register" target="_blank" class="use-btn J_toApprove">立即认证</a><a href="${selfSite}/zdjf" class="close-btn J_close">暂不认证</a></div>
        </div>
    </div>

</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery.downCount.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery.cxscroll.min.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/index.js"></script>
</html>