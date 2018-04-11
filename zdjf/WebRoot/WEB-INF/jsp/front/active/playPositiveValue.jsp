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
    <link rel="stylesheet" href="<%=path%>/css/front/playPositiveValue.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->
<div class="playPositiveValue">
    <div class="playPositiveValue_top"></div>
    <div class="playPositiveValue_middle">
        <div class="playPositiveValue_middle_box">
            <div class="tlt_bj">做任务赢好礼，多做任务赢取更多正经值<span class="get6"></span></div>
            <ul class="task">
                <li>
                    <i class="zhuce_ico"></i>
                    <span class="zheng_tlt">新手注册</span>
                    <span class="fu_tlt">成功注册奖励10正经值</span>
                    <c:if test="${not empty user}"><span class="yidacheng">已达成</span></c:if>
                    <%-- <a href="javascript:;" style="display: none;"><span class="nodacheng">未达成</span></a>--%>
                    <c:if test="${empty user}"><a href="<%=path%>/toLogin.action"><span class="nodacheng">立即登录</span></a></c:if>
                </li>
                <li>
                    <i class="smrz_ico"></i>
                    <span class="zheng_tlt">实名认证</span>
                    <span class="fu_tlt">成功实名认证奖励10正经值</span>
                    <c:if test="${not empty user && user.real_name_auth == 1}"><span class="yidacheng">已达成</span></c:if>
                    <c:if test="${not empty user && user.real_name_auth != 1}"><a href="<%=path%>/toNewAudit.action" ><span class="nodacheng">未达成</span></a></c:if>
                    <c:if test="${empty user}"><a href="<%=path%>/toLogin.action"><span class="nodacheng">立即登录</span></a></c:if>
                </li>
                <li>
                    <i class="bBank_ico"></i>
                    <span class="zheng_tlt">绑定储蓄卡</span>
                    <span class="fu_tlt">成功绑定储蓄卡奖励15正经值</span>
                    <c:if test="${not empty user && user.status >= 2}"><span class="yidacheng" >已达成</span></c:if>
                    <c:if test="${not empty user && user.status < 2}"><a href="<%=path%>/toNewAudit.action" ><span class="nodacheng">未达成</span></a></c:if>
                    <c:if test="${empty user}"><a href="<%=path%>/toLogin.action"><span class="nodacheng">立即登录</span></a></c:if>
                </li>
                <li>
                    <i class="download_ico"></i>
                    <span class="zheng_tlt">下载APP并投资</span>
                    <span class="fu_tlt">使用APP首次投资奖励25正经值</span>
                    <c:if test="${downloadApp > 0}"><span class="yidacheng">已达成</span></c:if>
                    <c:if test="${downloadApp == 0}"><a href="<%=path%>/downLApp.action" target="_blank"><span class="nodacheng">未达成</span></a></c:if>
                    <c:if test="${empty user}"><a href="<%=path%>/toLogin.action"><span class="nodacheng">立即登录</span></a></c:if>
                </li>
                <li>
                    <i class="firstTZ_ico"></i>
                    <span class="zheng_tlt">首次投资</span>
                    <span class="fu_tlt">首次投资≥1000元奖励20正经值</span>
                    <c:if test="${coinsByInvestTimes > 0}"><span class="yidacheng" >已达成</span></c:if>
                    <c:if test="${coinsByInvestTimes == 0}"><a href="<%=path%>/product/list.action" ><span class="nodacheng">未达成</span></a></c:if>
                    <c:if test="${empty user}"><a href="<%=path%>/toLogin.action"><span class="nodacheng">立即登录</span></a></c:if>
                </li>
                <li>
                    <i class="firstFT_ico"></i>
                    <span class="zheng_tlt">首次复投</span>
                    <span class="fu_tlt">回款后首次复投≥1000元奖励20正经值</span>
                    <c:if test="${coinsByInvestTimes > 1}"><span class="yidacheng" >已达成</span></c:if>
                    <c:if test="${coinsByInvestTimes <= 1}"><a href="<%=path%>/product/list.action" ><span class="nodacheng">未达成</span></a></c:if>
                    <c:if test="${empty user}"><a href="<%=path%>/toLogin.action"><span class="nodacheng">立即登录</span></a></c:if>
                </li>
            </ul>
            <div class="what_zjz">
                <p class="what_zjztlt">什么是“正经值”</p>
                <p class="centent">正经值是正道金服向用户提供的一种优惠积分活动，可以兑换平台内不同的优惠活动兑换后可直接用于投资。</p>
                <span class="get5"></span>
            </div>
            <div class="zjz_purpose">
                <div class="purpose_tlt"><span>正</span><span>经</span><span>值</span><span>用</span><span>途</span></div>
                <ul class="purpose_centent">
                    <li>1.正经值可直接用作投资抵等额现金，最高可抵扣投资金额1%；</li>
                    <li>2.正经值可兑换的等额红包券，可用作投资抵等额现金；</li>
                    <li>3.正经值可兑换加息券，可用作提升用户投资项目的年化收益。</li>
                    <li class="prompt">温馨提示：后期我们将开发更多好玩有趣的兑换内容，敬请期待</li>
                </ul>
                <span class="get4"></span>
            </div>
        </div>
    </div>
    <div class="playPositiveValue_bottom">
        <div class="playPositiveValue_bottom_box">
            <div class="bottom">
                <div class="bottom_tlt">如何获得正经值</div>
                <div class="bottom_dai"></div>
                <ul class="bottom_centent">
                    <li>1.邀请好友：好友注册并投资，你可获得好友投资额的2‰的正经值奖励；</li>
                    <li>2.用户可参与正道金服推出的活动，赢取正经值。如“抢标活动”等</li>
                </ul>
                <span class="get1"></span>
                <span class="get7"></span>
            </div>
            <div class="bottom">
                <div class="bottom_tlt">如何邀请好友</div>
                <div class="bottom_dai"></div>
                <ul class="bottom_centent">
                    <li>1.邀请好友注册时，需好友输入推荐人的手机号码；2‰的正经值奖励；</li>
                    <li>2.好友通过推荐人唯一的邀请链接，进入注册页面成功注册<br/>（邀请链接见“我的财富-邀请有礼”）。</li>
                </ul>
                <span class="get3"></span>
            </div>
            <div class="bottom">
                <div class="bottom_tlt">如何兑换正经值</div>
                <div class="bottom_dai"></div>
                <ul class="bottom_centent">
                    <li>STEP1：用户登录正道金服，进入“我的财富-我的优惠-正经值余额”；</li>
                    <li>STEP2：正经值集满10可点击领取0.5%的加息券；</li>
                    <li>STEP3：正经值集满16可点击领取15元红包券；</li>
                </ul>
                <span class="get2"></span>
            </div>
            <div class="guize">
                <div class="guize_tlt">正经值<br/>使用规则</div>
                <ul class="guize_centent">
                    <li>1.正经值可累计，无上限，永久有效；</li>
                    <li>2.正经值不可转让，不可折现。</li>
                </ul>
                <span class="get2"></span>
                <span class="get3"></span>
            </div>
            <p class="end_tlt">关于正经值的最终解释权归正道金服所有</p>
        </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script type="text/javascript" src="<%=path%>/module/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/module/sticky-header.js"></script>
<script type="text/javascript" src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/playPositiveValue.js"></script>
<script type="text/javascript">
    $('.header').stickMe({
        topOffset:100
    });
</script>
</body>
</html>
