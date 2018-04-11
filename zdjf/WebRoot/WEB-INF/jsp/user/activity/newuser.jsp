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
    <title>新手活动</title>
    <meta name="keywords" content="正道金服">
    <meta name="description" content="正道金服">
    <meta name="copyright" content="版权所有 © 正道金服">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/activity/css/newuser.css">
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
</head>

<body>
<jsp:include page="../comm/header.jsp"></jsp:include>

<!-- detail -->
<div class="content">
    <!-- banner -->
    <div class="banner01"></div>
    <div class="banner02 J_ban02"></div>
    <div class="banner03"></div>
    <div class="banner04">
        <div class="list text-aide J_list">
            <ul>
                <li>
                    <div><span class="phone">188****9431</span>获得<span class="sum text-red">50元</span>红包券<span class="date">2016-3-24 09:44</span></div>
                    <div><span class="phone">188****9432</span>获得<span class="sum text-red">100元</span>红包券<span class="date">2016-3-24 09:44</span></div>
                </li>
                <li>
                    <div><span class="phone">188****9431</span>获得<span class="sum text-red">50元</span>红包券<span class="date">2016-3-24 09:44</span></div>
                    <div><span class="phone">188****9432</span>获得<span class="sum text-red">10元</span>红包券<span class="date">2016-3-24 09:44</span></div>
                </li>
                <li>
                    <div><span class="phone">188****9431</span>获得<span class="sum text-red">50元</span>红包券<span class="date">2016-3-24 09:44</span></div>
                    <div><span class="phone">188****9432</span>获得<span class="sum text-red">90元</span>红包券<span class="date">2016-3-24 09:44</span></div>
                </li>
            </ul>
        </div>
    </div>

    <!-- flex -->
    <div class="flex-reg">
        <div class="layout">
            <div class="con">红包券面值有<b>50元</b>，<b>100元</b>，<b>200元</b>，<b>300元</b>等投资使用红包券，项目回款后<b>即可提现</b></div>
            <a href="#" class="btn-red btn">立即注册</a>
            <span class="btn-dark btn hide">立即注册</span>
        </div>
    </div>

    <!-- flex -->
    <div class="flex-dep">
        <div class="layout">
            <div class="con">注册成功后在【我的财富>安全中心】页面<br/>开通资金托管账户即可获得<b>30元红包券</b></div>
            <a href="#" class="btn-blue btn">立即开通</a>
            <span class="btn-dark btn hide">立即开通</span>
        </div>
    </div>

    <!-- flex -->
    <div class="flex-invest">
        <div class="layout">
            <div class="con">开通托管账户后首次投资项目<br/>即可获得<b>20元红包券</b></div>
            <a href="#" class="btn-orange btn">立即开通</a>
            <span class="btn-dark btn hide">立即开通</span>
        </div>
    </div>

    <!-- flex -->
    <div class="flex-sprice">
        <div class="layout">
            <div class="con">邀请好友，好友投资任意金额<br/>即可获得<b>20元红包券+正经值</b>（投资可抵现金）</div>
            <a href="#" class="btn-red btn">立即开通</a>
            <span class="btn-dark btn hide">立即开通</span>
        </div>
    </div>

    <!-- flex -->
    <div class="info">
        <div class="layout">
            <div class="con text-aide">
                <p>1. 所获红包券和正经值在任务完成后立即存入您的账户，可至【我的财富>我的优惠】查看有效期及使用规则；</p>
                <p>2. 红包券使用方式为投资时抵扣投资金额，项目到期后以现金方式回款。红包券适用投资项目以实际使用为准，可在投资时勾选使用；</p>
                <p>3. 邀请投资赠送的正经值数量为好友投资金额的0.2%，正经值可在投资时以与人民币1:1的比例进行抵现；</p>
                <p>4. 活动结束时间以官网公告为准。</p>
            </div>
            <div class="copy text-gray">本活动最终解释权归杭州云翳网络科技有限公司所有</div>
        </div>
    </div>
</div>

<jsp:include page="../comm/footer.jsp"></jsp:include>
<jsp:include page="../comm/helper.jsp"></jsp:include>
<script>

    // banner彩灯
    setInterval("PerRefresh()", 500);
    function PerRefresh() {
        $('.J_ban02').hasClass('curr') ? $('.J_ban02').removeClass('curr') : $('.J_ban02').addClass('curr');
    }

    /**
     * 导航滑动效果
     **/
    function AutoScroll(obj){
        $(obj).find("ul:first").animate({
            marginTop:"-33px"
        },500,function(){
            $(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
        });
    }
    $(document).ready(function(){
        setInterval(function(){
            AutoScroll(".J_list");
        }, 2800);
    });
</script>
</body>
</html>
