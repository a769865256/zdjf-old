<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui"/>
    <title>每日签到</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/module/layui/css/layui.mobile.css"/>
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/style.css"/>
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/index.css"/>
    <!-- 公共样式end -->
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/sign.css"/>
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/sign2.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<div class="sign">
    <div class="s_one">
        <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_01.jpg" alt="">
    </div>
    <div class="s_one_one">
        <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_01_01.png" alt="">
        <div class="s_one_con">
            <div class="s_one_help flex">
                <img src="<%=path%>/static/zdjf_app/static/img/sign/help.png" alt="">
            </div>
            <div class="s_one_tit">签到机会：<span> <c:if test="${empty user}">0</c:if>
                <c:if test="${not empty user}">${signTotalNum}</c:if></span>次</div>
        </div>
        <div class="s_one_con2">
            <div class="s_one_dbx hide">
                <div class="s_one_qd_box">
                    <div class="s_one_qd">今日签到：<c:if test="${empty user}">0</c:if>
                        <c:if test="${not empty user}">${currSignNum}</c:if>次</div>
                    <div class="s_one_qd">分享签到：<c:if test="${empty user}">0</c:if>
                        <c:if test="${not empty user}">${shareNum}</c:if>次</div>
                    <div class="s_one_qd">邀请好友：<c:if test="${empty user}">0</c:if>
                        <c:if test="${not empty user}">${inviteNum}</c:if>次</div>
                </div>
            </div>
        </div>
    </div>
    <div class="s_two">
        <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_02.jpg" alt="">
        <div class="twobox">
            <input type="hidden" id="userPhone" value="${phone}"/>
            <input type="hidden" id="coin" value="${coin}"/>
            <input type="hidden" id="errMsg" value="${errMsg}"/>
            <input type="hidden" id="doubleStatus" value="${doubleStatus}"/>
            <span class="code_entrance"><img src="<%=path%>/static/zdjf_app/static/img/sign/code.png" alt=""></span>
            <c:if test="${empty user}"><a href="toLogin();" class="sign_click">点击签到</a><span class="date_entrance"></span> </c:if>
            <c:if test="${not empty user && signTotalNum > 0}"><a href="javascript:sign();" class="sign_click" >点击签到</a><span class="date_entrance"></span></c:if>
            <c:if test="${not empty user && signTotalNum == 0}"> <a class="sign_click sign_click_no">今日已签到</a><span class="date_entrance"></span></c:if>
        </div>
        <div class="s_two_datebox hide"></div>
    </div>
    <div class="s_three">
        <div class="threebox" id="scrollBox">
            <div id="con1">
                <c:choose>
                    <c:when test="${not empty latestSignList}">
                        <c:forEach items="${latestSignList}" var="latestSign">
                            <p>${latestSign.user}签到成功，领取了<span>${latestSign.coin} 点正经值</span></p>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>183******61  签到成功，领取了<span>10正经值</span></p>
                        <p>183******62  签到成功，领取了<span>8正经值</span></p>
                        <p>183******63  签到成功，领取了<span>2正经值</span></p>
                        <p>183******64  签到成功，领取了<span>10正经值</span></p>
                        <p>183******65  签到成功，领取了<span>8正经值</span></p>
                        <p>183******66  签到成功，领取了<span>2正经值</span></p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="s_four">
        <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_04.jpg" alt="">
        <c:if test="${empty user}">
            <div class="fourbox">
                <span>您当前暂未登录</span><a href="toLogin();" >立即登录>></a>
            </div>
        </c:if>
        <c:if test="${not empty user && isRank==0}">
            <div class="fourbox">
                <span>您当前暂未上榜</span>
            </div>
        </c:if>
        <c:if test="${not empty user && isRank>0}">
            <div class="fourbox">
                <span>您已占据风云榜第${isRank}名</span>
            </div>
        </c:if>
    </div>
    <c:if test="${empty frontEightSignUsers}">
    <div class="s_five">
        <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_05.jpg" alt="">
        <div class="fivebox">
            <div class="box">
                <h3>183******65</h3>
                <p><img src="<%=path%>/static/zdjf_app/static/img/sign/five_01.png" alt="">X800点</p>
            </div>
            <div class="box">
                <h3>183******65</h3>
                <p><img src="<%=path%>/static/zdjf_app/static/img/sign/five_02.png" alt="">X800点</p>
            </div>
            <div class="box">
                <h3>183******65</h3>
                <p><img src="<%=path%>/static/zdjf_app/static/img/sign/five_03.png" alt="">X500点</p>
            </div>
        </div>
    </div>
    <div class="s_six">
        <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_06.jpg" alt="">
        <div class="sixbox">
            <p>183******65<span>500点</span></p>
            <p>183******65<span>500点</span></p>
            <p>183******65<span>500点</span></p>
            <p>183******65<span>500点</span></p>
            <p>183******65<span>500点</span></p>
        </div>
    </div>
    </c:if>
    <c:if test="${not empty frontEightSignUsers}">
        <div class="s_five">
            <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_05.jpg" alt="">
            <div class="fivebox">
                <div class="box">
                    <h3>${frontEightSignUsers[1].user}</h3>
                    <p><img src="<%=path%>/static/zdjf_app/static/img/sign/five_01.png" alt="">X${frontEightSignUsers[1].coins}点</p>
                </div>
                <div class="box">
                    <h3>${frontEightSignUsers[0].user}</h3>
                    <p><img src="<%=path%>/static/zdjf_app/static/img/sign/five_02.png" alt="">X${frontEightSignUsers[0].coins}点</p>
                </div>
                <div class="box">
                    <h3>${frontEightSignUsers[2].user}</h3>
                    <p><img src="<%=path%>/static/zdjf_app/static/img/sign/five_03.png" alt="">X${frontEightSignUsers[2].coins}点</p>
                </div>
            </div>
        </div>
        <div class="s_six">
            <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_06.jpg" alt="">
            <div class="sixbox">
                <c:if test="${not empty frontEightSignUsers[3]}"><p>${frontEightSignUsers[3].user}<span>${frontEightSignUsers[3].coins}点</span></p></c:if>
                <c:if test="${not empty frontEightSignUsers[4]}"><p>${frontEightSignUsers[4].user}<span>${frontEightSignUsers[4].coins}点</span></p></c:if>
                <c:if test="${not empty frontEightSignUsers[5]}"><p>${frontEightSignUsers[5].user}<span>${frontEightSignUsers[5].coins}点</span></p></c:if>
                <c:if test="${not empty frontEightSignUsers[6]}"><p>${frontEightSignUsers[6].user}<span>${frontEightSignUsers[6].coins}点</span></p></c:if>
                <c:if test="${not empty frontEightSignUsers[7]}"><p>${frontEightSignUsers[7].user}<span>${frontEightSignUsers[7].coins}点</span></p></c:if>
            </div>
        </div>
    </c:if>
    <div class="s_seven_box">
        <div class="s_seven">
            <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_07.jpg" alt="">
            <div class="sevenbox">
                <a href="javascript:" class="s_seven_click"></a>
            </div>
        </div>
        <div class="s_seven s_seven_02">
            <img src="<%=path%>/static/zdjf_app/static/img/sign/sign_07_02.jpg" alt="">
            <div class="sevenbox">
                <a href="javascript:" class="s_seven_click"></a>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    (function(){
    var _w,_zoom,_hd, _orientationChange,_doc = document,__style = _doc.getElementById("_zoom");
    __style || (_hd = _doc.getElementsByTagName("head")[0],__style=_doc.createElement("style"),_hd.appendChild(_style));
    _orientationChange = function(){
    _w    = _doc.documentElement.clientWidth || _doc.body.clientWidth;
    _zoom = _w / 640;
    __style.innerHTML = ".zoom {zoom:" + _zoom + ";-webkit-text-size-adjust:auto !important;text-size-adjust:auto !important;}";
    };
    __style.setAttribute("zoom",_zoom);
    _orientationChange();
    window.addEventListener("resize",_orientationChange,false);
    })();
    //rem布局
    (function(win) {
    var doc = win.document;
    var docEl = doc.documentElement;
    var tid;

    function refreshRem() {
    var width = docEl.getBoundingClientRect().width;
    if (width > 640) { // 最大宽度
    width = 640;
    }
    var rem = width / 10;
    docEl.style.fontSize = rem + 'px';
    }

    win.addEventListener('resize', function() {
    clearTimeout(tid);
    tid = setTimeout(refreshRem, 300);
    }, false);
    win.addEventListener('pageshow', function(e) {
    if (e.persisted) {
    clearTimeout(tid);
    tid = setTimeout(refreshRem, 300);
    }
    }, false);

    refreshRem();

    })(window);
</script>
<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/jquery.md5.js"></script>
    <script type="text/javascript" src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/static/zdjf_app/module/layer_mobile/layer.js"></script>
<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/public.js"></script>
<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/common.js"></script>
<script>
    $(".date_entrance").html(new Date().getMonth()+1+"月");
    <%--分享弹窗--%>
    $('.sign_click_no').click(function () {
        layer.open({
            content: '今日签到次数已用完，分享增加签到机会'
            ,btn: '去分享'
            ,yes:function () {
                window.location.href = 'toShareRegist';
            }
        });
    })
    function toLogin() {
        window.location = "toLogin";
    }
    var flag = true;
    function sign() {
        if(flag){
            flag = false;
            var reg_source;
            var u = navigator.userAgent;
            var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
            if(isAndroid){
            reg_source=3;
            }
            if(isiOS){
            reg_source=2
            }
            window.location.href="<%=path%>/sign.action?phone="+$("#userPhone").val()+"&reg_source="+reg_source;
            flag = true;
        }
    }
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        var markDay = {};
        $.ajax({
            type: 'POST',
            url: '<%=path%>/getUserSignInfo.action?phone='+$("#userPhone").val(),
            dataType: 'json',
            success: function(resdata){
                if(resdata.content != null) {
                    $.each(resdata.content,function(i,j){
                        markDay[j.create_date]=parseInt(j.create_day);
                    });
                }

                //执行一个laydate实例
                laydate.render({
                    elem: '.s_two_datebox' //指定元素
                    ,trigger: 'click' //采用click弹出
                    ,show: false //直接显示
                    ,type: 'date' //默认，可不填
                    ,closeStop: '.date_entrance' //这里代表的意思是：点击 test1 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
                    ,position: 'static'
                    ,btns: []
                    ,mark: markDay
                    ,ready:function() {
                        if(resdata.content != null) {
                            $.each(resdata.content,function(k,v){
                                var qd_year = parseInt(v.create_date.split("-")[0]);
                                var qd_month = parseInt(v.create_date.split("-")[1]);
                                var qd_day = parseInt(v.create_date.split("-")[2]);
                                var qd_str = qd_year + '-' + qd_month + '-' + qd_day;
                                console.log(qd_str);
                                $('.layui-laydate-content td[lay-ymd='+ qd_str +'] span.laydate-day-mark').append('<div class="markDiv">+'+v.coins+'</div>');
                            });
                        }
                    }
                });
            },
            error:function(resdata) {
                alert(resdata.msg);
            },
        });
    });
    //轮播
    function AutoScroll(obj) {
    $(obj).find("#con1").animate({
    marginTop: "-50px"
    },
    1000,
    function() {
    $(this).css({
    marginTop: "0px"
    }).find("p:first").appendTo(this);
    });
    }
    $(document).ready(function() {
    setInterval('AutoScroll("#scrollBox")', 2000)
    });

    //分享
    $('.code_entrance').click(function(){
        window.location.href = 'toShareRegist';
        //分享链接 https://www.zdjfu.com/static/zdjf_app/page/login/reg.html
        //分享title、内容、图片跟产品对接
    })
    // 控制日历显示隐藏
    $('.date_entrance').click(function () {
        if($('.s_two_datebox').hasClass('hide')){
            $('.s_two_datebox').removeClass('hide');
        }else{
            $('.s_two_datebox').addClass('hide');
        }
    })
    // 控制签到次数显示隐藏
    $('.s_one_help').click(function () {
        if($('.s_one_dbx').hasClass('hide')){
            $('.s_one_dbx').removeClass('hide');
        }else{
            $('.s_one_dbx').addClass('hide');
        }
    })
    // 控制活动规则显示隐藏
    $('.s_seven_click').click(function () {
        $(this).parents('.s_seven').hide().siblings().show();
    })

    // 签到弹框控制
    <%--$('.sign_click').click(function () {--%>
        <%--// 判断是否登录--%>
        <%--if ($.getUrlParam('phone')=='') {--%>
            <%--//window.location = "toLogin";--%>
        <%--}else{--%>
            <%--$('body').append('<div class="jm_kt_alerts_sign"><div class="jm_kt_mask"></div><div class="jm_kt_box"><div class="jm_kt"><div class="jm_kt_img"><img src="<%=path%>/static/zdjf_app/static/img/active/success_sign.png" alt=""></div><div class="jm_kt_word"><div class="zjz">正经值+10，已×2倍</div><div class="success">今日签到成功！</div></div></div><div class="jm_kt_close_sign"><img src="<%=path%>/static/zdjf_app/static/img/active/close.png" alt=""></div></div></div>');--%>
        <%--}--%>
    <%--})--%>
    $('body').delegate('.jm_kt_close_sign','click',function () {
        $(this).parents('.jm_kt_alerts_sign').remove();
    })
    $(function(){
        var coin = $("#coin").val();
        var errMsg = $("#errMsg").val();
        var doubleStatus = $("#doubleStatus").val();
        if (coin != null && coin > 0 && doubleStatus == 1) {
            $('body').append('<div class="jm_kt_alerts_sign"><div class="jm_kt_mask"></div><div class="jm_kt_box"><div class="jm_kt"><div class="jm_kt_img"><img src="<%=path%>/static/zdjf_app/static/img/active/success_sign.png" alt=""></div><div class="jm_kt_word"><div class="zjz">正经值+'+coin+'，已×2倍</div><div class="success">今日签到成功！</div></div></div><div class="jm_kt_close_sign"><img src="<%=path%>/static/zdjf_app/static/img/active/close.png" alt=""></div></div></div>');
        }
        if (coin != null && coin > 0 && doubleStatus != 1) {
            $('body').append('<div class="jm_kt_alerts_sign"><div class="jm_kt_mask"></div><div class="jm_kt_box"><div class="jm_kt"><div class="jm_kt_img"><img src="<%=path%>/static/zdjf_app/static/img/active/success_sign.png" alt=""></div><div class="jm_kt_word"><div class="zjz">正经值+'+coin+'</div><div class="success">今日签到成功！</div></div></div><div class="jm_kt_close_sign"><img src="<%=path%>/static/zdjf_app/static/img/active/close.png" alt=""></div></div></div>');
        }
        if (errMsg != null && errMsg.length > 0) {
            alert(errMsg);
            return;
        }
    });
</script>
</html>
