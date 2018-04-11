<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="<%=path%>/css/front/newHand.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->
<div class="newHand">
    <div class="newHandBox">
        <img src="<%=path%>/images/front/img/active/newHand/banner_word.png" alt="banner_tlt" class="banner_tlt">
        <div class="flower"></div>
        <div class="cainiao">
            <div class="cainiao_tlt">
                <div class="cainiao_hd">活动期间，凡注册正道金服的新用户，即可获得206元专享红包和0.8%加息券。</div>
            </div>
            <div class="cainiao_table"></div>
            <div class="cainiao_btn">
                <c:if test="${not empty user}"><a class="cainiao_getJL">您已获得奖励</a></c:if>
                <c:if test="${empty user}"><a href="<%=path%>/toRegister.action" class="cainiao_registration" >立即注册</a></c:if>
                <span class="cainiao_btn_down"></span>
            </div>
        </div>
        <div class="yimingjingren">
            <div class="yiming_tlt"></div>
            <div class="yiming_table"></div>
            <div class="yiming_btn">
                <c:if test="${not empty user && investFlag == 1}"><a class="yiming_getJL" >您已获得奖励</a></c:if>
                <c:if test="${empty user || investFlag == 0}"><a href="<%=path%>/product/list.action" class="yiming_investment">立即投资</a></c:if>
                <span class="yiming_btn_down"></span>
            </div>
            <c:if test="${empty user || investFlag == 0}"><p class="need_investment yiming_need_investment">还需投资<span>1000</span>元</p></c:if>
            <div class="libao yiming_libao"></div>
        </div>
        <div class="pengchengwanli">
            <div class="pengcheng_tlt"></div>
            <div class="pengcheng_table"></div>
            <div class="pengcheng_btn">
                <c:if test="${not empty user && totalInvest >= 10000}"><a class="pengcheng_getJL">您已获得奖励</a></c:if>
                <c:if test="${empty user || empty totalInvest || totalInvest < 10000}"><a href="<%=path%>/product/list.action" class="pengcheng_investment">立即投资</a></c:if>
                <span class="pengcheng_btn_down"></span>
            </div>
            <c:if test="${empty user || empty totalInvest}"><p class="need_investment pengcheng_need_investment">还需投资<span>10000</span>元</p></c:if>
            <c:if test="${not empty user && totalInvest < 10000}"><p class="need_investment pengcheng_need_investment">还需投资<span>${10000-totalInvest}</span>元</p></c:if>
        </div>
        <div class="rule">
            <div class="rule_bj">
                <div class="rule_box">
                    <h3>红包使用规则</h3>
                    <h5>(红包可与加息券同时使用)</h5>
                    <p>举个例子：正经先生选择30天的项目，7日年化利率为10%，他投资了1000元，使用红包20元，同时使用加息券1.2%，那么正经先生实际投资金额为980元，使用加息券利率为11.2%，最终将获得9.33元利息，项目到期后，可提现金为1009.33元，实际累计赚取29.33元。</p>
                </div>
            </div>
        </div>
        <div class="active_rule_btn">活动规则<i class="iconfont">&#xe697;</i></div>
        <div class="active_rule">
            <ul>
                <li>1、本活动所有优惠只针对活动期间注册的新手用户，活动开始前注册的用户不享受奖励；</li>
                <li>2、正经值是正道金服优惠积分，用户可通过每日参与正道金服官方活动获得；正经值可在投资时抵扣现金，抵扣比例为1%，正经值不得与红包券同时使用；</li>
                <li>3、红包券在投资时可抵扣现金进行投资，例如投资1000元，使用20元红包券，则只需再投980元现金即可，红包券只能用于投资，不能提现、转账和找零；</li>
                <li>4、加息券可使您的收益增加。例如，您投资的项目原年化利率为10%，使用0.5%的收益券之后，利率上涨为10.5%；</li>
                <li>5、活动中所获红包券和正经值在任务完成后即时存入您的账户，可至【我的财富>我的优惠】查看有效期及使用规则；</li>
                <li>6、红包券及正经值使用方式为投资时抵扣投资本金，项目到期后以现金方式回款。红包券适用投资项目以实际使用为准；</li>
                <li>7.活动结束时间以官网公告为准。</li>
            </ul>
        </div>
    </div>
</div>
<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script type="text/javascript" src="<%=path%>/module/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/module/sticky-header.js"></script>
<script type="text/javascript" src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/newHand.js"></script>
<script type="text/javascript">
	$('.header').stickMe({
		topOffset:100
	});
</script>
</body>
</html>