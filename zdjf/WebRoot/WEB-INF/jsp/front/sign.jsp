<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<%=path%>/css/front/sign.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="./common/header.jsp"></jsp:include>
</div>
<!-- header end -->
<div class="sign_s">
    <input id="path" type="hidden" value="<%=path%>"/>
    <div class="sign_top"></div>
    <div class="sign_centent1">
        <p class="huodong1">活动一：签到享好礼</p>
        <div class="qiandaomsg">
            <p>每天签到送<span>1-6点</span>正经值随机奖励；投资签到、周末签到再享<span>翻倍奖励</span></p>
            <p>签到后成功分享致朋友圈，签到机会<span>+1</span></p>
            <p>通过分享签到链接邀请用户成功注册则签到机会<span>+1</span></p>
        </div>
        <div class="jihui">
            <span>签到机会：
                <c:if test="${empty user}">999</c:if>
                <c:if test="${not empty user}">${signTotalNum}</c:if>
            </span><i class="iconfont">&#xe605;</i>
            <div class="jihui_msg">
                <div>
                    <p>今日签到： <c:if test="${empty user}">999</c:if>
                        <c:if test="${not empty user}">${currSignNum}</c:if>次</p>
                    <p>分享签到： <c:if test="${empty user}">999</c:if>
                        <c:if test="${not empty user}">${shareNum}</c:if>次</p>
                    <p>邀请好友： <c:if test="${empty user}">999</c:if>
                        <c:if test="${not empty user}">${inviteNum}</c:if>次</p>
                </div>
            </div>
        </div>
        <div class="sign_centent1_btn">
            <c:if test="${empty user}"><a href="<%=path%>/toLogin.action" class="login_qiandao">登录签到</a> </c:if>
            <c:if test="${not empty user && signTotalNum > 0}"><a href="<%=path %>/sign.action" class="click_qiandao" >点击签到</a></c:if>
            <c:if test="${not empty user && signTotalNum == 0}"> <a href="javascript:;" class="qiandaoDe" >登录已签到</a></c:if>
        </div>
        <c:if test="${not empty user}"><div class="rili_btn"><a href="javascript:;" class="yue">12月</a></div></c:if>
        <div class="rili">
            <div class="sign_rili">
                <div id="sign_date"></div>
            </div>
        </div>
        <div class="zuijinqiandao">
            <p>最近签到</p>
            <div id="scrollBox">
                <ul id="con1">
                    <c:choose>
                        <c:when test="${not empty latestSignList}">
                            <c:forEach items="${latestSignList}" var="latestSign">
                                <li>${latestSign.user}签到成功，领取了<span>${latestSign.coin} 点正经值</span></li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <li>183****3565签到成功，领取了<span>20点正经值</span></li>
                            <li>183****3785签到成功，领取了<span>20点正经值</span></li>
                            <li>183****5965签到成功，领取了<span>10点正经值</span></li>
                            <li>183****5965签到成功，领取了<span>10点正经值</span></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                <ul id="con2"></ul>
            </div>
        </div>
        <img src="<%=path%>/images/front/m/sign/qiandao_down.png" alt="">
        <div class="gua">
            <div class="guajian"></div>
            <div class="guajian"></div>
        </div>
    </div>
    <div class="sign_centent2">
        <p class="huodong2">活动二：签到勤奋榜</p>
        <p class="ti">每月签到所获正经值<span>累计前三名</span>的用户将分别获得对应的奖励。</p>
        <p class="bang">
            <c:if test="${empty user}"><span class="noLogin">你当前暂未登录</span>
            <a href="<%=path%>/toLogin.action" class="login_btn1">点击登录<i class="iconfont">&#xe611;</i></a></c:if>
            <c:if test="${not empty user && isRank ==0}"><span class="noBang" >你当前暂未上榜</span></c:if>
            <c:if test="${not empty user && isRank !=0 && isRank < 4}"><span class="signed1" >您已签到<i class="dco">${frontEightSignUsers[isRank-1].coins}</i>点，占据风云榜第<i class="mingci">${isRank}</i>名!</span></c:if>
            <c:if test="${not empty user && isRank !=0 && isRank > 3}"><span class="signed2" >您已签到<i class="dco">${frontEightSignUsers[isRank-1].coins}</i>点，加油哦！</span></c:if>
        </p>
        <c:if test="${empty frontEightSignUsers}">
        <div class="ljt">
            <div class="tow">
                <div class="gold2"></div>
                <div class="getMsg">
                    <div class="phoneNmb">183*******65</div>
                    <div class="beis"><span class="jinbi"></span>X<span class="point">800点</span></div>
                </div>
            </div>
            <div class="one">
                <div class="gold1"></div>
                <div class="getMsg">
                    <div class="phoneNmb">183*******65</div>
                    <div class="beis"><span class="jinbi"></span>X<span class="point">1000点</span></div>
                </div>
            </div>
            <div class="three">
                <div class="gold3"></div>
                <div class="getMsg">
                    <div class="phoneNmb">183*******65</div>
                    <div class="beis"><span class="jinbi"></span>X<span class="point">500点</span></div>
                </div>
            </div>
        </div>
        <div class="huojiang_4_8">
            <ul>
                <li><span class="rank4"></span><span class="phonenber">183*******65</span><span class="jinbi"></span>X<span class="point">500点</span></li>
                <li><span class="rank5"></span><span class="phonenber">183*******65</span><span class="jinbi"></span>X<span class="point">500点</span></li>
                <li><span class="rank6"></span><span class="phonenber">183*******65</span><span class="jinbi"></span>X<span class="point">500点</span></li>
                <li><span class="rank7"></span><span class="phonenber">183*******65</span><span class="jinbi"></span>X<span class="point">500点</span></li>
                <li><span class="rank8"></span><span class="phonenber">183*******65</span><span class="jinbi"></span>X<span class="point">500点</span></li>
            </ul>
        </div>
        </c:if>
        <c:if test="${not empty frontEightSignUsers}">
            <div class="ljt">
                <div class="tow">
                    <div class="gold2"></div>
                    <div class="getMsg">
                        <div class="phoneNmb">${frontEightSignUsers[1].user}</div>
                        <div class="beis"><span class="jinbi"></span>X<span class="point">${frontEightSignUsers[1].coins}点</span></div>
                    </div>
                </div>
                <div class="one">
                    <div class="gold1"></div>
                    <div class="getMsg">
                        <div class="phoneNmb">${frontEightSignUsers[0].user}</div>
                        <div class="beis"><span class="jinbi"></span>X<span class="point">${frontEightSignUsers[0].coins}点</span></div>
                    </div>
                </div>
                <div class="three">
                    <div class="gold3"></div>
                    <div class="getMsg">
                        <div class="phoneNmb">${frontEightSignUsers[2].user}</div>
                        <div class="beis"><span class="jinbi"></span>X<span class="point">${frontEightSignUsers[2].coins}点</span></div>
                    </div>
                </div>
            </div>
            <div class="huojiang_4_8">
                <ul>
                    <c:if test="${not empty frontEightSignUsers[3]}"><li><span class="rank4"></span><span class="phonenber">${frontEightSignUsers[3].user}</span><span class="jinbi"></span>X<span class="point">${frontEightSignUsers[3].coins}点</span></li></c:if>
                    <c:if test="${not empty frontEightSignUsers[4]}"><li><span class="rank5"></span><span class="phonenber">${frontEightSignUsers[4].user}</span><span class="jinbi"></span>X<span class="point">${frontEightSignUsers[4].coins}点</span></li></c:if>
                    <c:if test="${not empty frontEightSignUsers[5]}"><li><span class="rank6"></span><span class="phonenber">${frontEightSignUsers[5].user}</span><span class="jinbi"></span>X<span class="point">${frontEightSignUsers[5].coins}点</span></li></c:if>
                    <c:if test="${not empty frontEightSignUsers[6]}"><li><span class="rank7"></span><span class="phonenber">${frontEightSignUsers[6].user}</span><span class="jinbi"></span>X<span class="point">${frontEightSignUsers[6].coins}点</span></li></c:if>
                    <c:if test="${not empty frontEightSignUsers[7]}"><li><span class="rank8"></span><span class="phonenber">${frontEightSignUsers[7].user}</span><span class="jinbi"></span>X<span class="point">${frontEightSignUsers[7].coins}点</span></li></c:if>
                </ul>
            </div>
        </c:if>
        <div class="ahook">
            <p>活动规则</p>
            <i class="iconfont">&#xe611;</i>
        </div>
        <div class="guize">
            <ul>
                <li><span>1、活动时间：</span>2017年7月1日-长期</li>
                <li><span>2、活动对象：</span>所有正道金服注册用户均可参与签到领奖励</li>
                <li><span>3、签到享好礼：</span>
                    <p>a.用户每日登录签到即可领取随机奖励，每日仅有一次签到机会；</p>
                    <p>b.周末签到最高可获得2倍随机奖励；当日投资额≥1000的用户，次日签到最高可获得3倍随机翻倍奖励；若周五周六投资，则次日签到按较高的倍数奖励；</p>
                    <p>c.签到后成功分享至朋友圈，签到机会+1；通过分享签到链接邀请用户成功注册则签到机会再+1；</p>
                    <p>d.分享仅限移动端，通过分享朋友圈和邀请好友成功注册所额外获得的签到机会均不享受翻倍奖励，好友注册奖励签到机会上不封顶；</p>
                    <p>e.通过成功分享朋友圈所获得的签到机会当日有效，次日失效；分享签到链接邀请用户成功注册所获得的签到机会当月有效，次月无效；</p>
                </li>
                <li><span>4、签到勤奋榜：</span>
                    <p>a.签到勤奋榜排名每月统计一次，排名结果以平台系统统计为准，次月1日发放上月奖励；</p>
                    <p>b.宝箱奖励使用规则见券面，请前往我的财富-我的优惠查看；</p>
                    <p>5、本次活动的所有数据均以正道金服官方统计为准，如有违反活动公平性的问题平台将取消活动资格和已发放奖励。</p>
                    <p>正道金服对本次活动规则享有最终解释权，如有其他疑问请致电客服400-690-9898。</p>
                </li>
            </ul>
        </div>
        <input type="hidden" id="coin" value="${coin}"/>
        <input type="hidden" id="errMsg" value="${errMsg}"/>
        <img src="<%=path%>/images//front/m/sign/yun1.png" alt="">
        <img src="<%=path%>/images//front/m/sign/yun2.png" alt="">
    </div>
</div>

<!-- footer -->
<jsp:include page="./common/footer.jsp"></jsp:include>
<!-- footer end -->

<!-- 弹窗 -->
<div class="qd_success">
    <div class="success">
        <span class="close"></span>
        <p>正经值+${coin}<c:if test="${doubleStatus == 1}">，已X2倍</c:if></p>
        <p>今日签到成功</p>
    </div>
</div>
<!-- 二维码 -->
<div class="ewm_box">
    <div class="ewm">
        <div class="centent">
            <div class="top"><span class="close"></span><span class="xian"></span></div>
            <img src="<%=path%>/images/front/img/footer/code2.png" alt="">
            <p>扫码分享，赢更多签到机会！</p>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/module/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/module/sticky-header.js"></script>
<script type="text/javascript" src="<%=path%>/module/layui/layui.js"></script>

<script type="text/javascript">
	$('.header').stickMe({
		topOffset:100
	});
    $(".qiandaoDe").click(function(){
        $(".ewm_box").show();
    });
    $(function(){
        var coin = $("#coin").val();
        var errMsg = $("#errMsg").val();
        if (coin != null && coin > 0) {
            $(".qd_success").show();
        }
        if (errMsg != null && errMsg.length > 0) {
            alert(errMsg);
            return;
        }
    });

    layui.use(['layer','laydate','form'], function(){
        var layer = layui.layer,
            laydate= layui.laydate,
            form = layui.form,
            carousel = layui.carousel;
        var markDay = {};
        $.ajax({
            type: 'POST',
            url: '<%=path%>/getUserSignInfo.action',
            dataType: 'json',
            success: function(data){
                if(data.content != null) {
                    $.each(data.content, function (i, j) {
                        markDay[j.create_date] = parseInt(j.create_day);
                    });
                }
                laydate.render({
                    elem: '#sign_date',
                    position: 'static',
                    showBottom: false,
                    type:'date',
                    format:'yyyy-MM-dd',
                    istoday: true,
                    min: -90, //7天前
                    max: 360, //7天后
                    mark:markDay,
                    change: function(value, date){ //监听日期被切换
                        $.each(data.content,function(k,v){
                            $.each($('.layui-laydate-content td span'),function(i,j){
                                //i为元素的索引，从0开始,j为当前处理的元素对象
                                if($(j).html() == parseInt(v.create_day)){
                                    $(j).html(parseInt(v.create_day)+'<div class="markDiv">+'+v.coins+'</div>')
                                }
                            });
                        });
                    },
                    ready: function(value, date, endDate){
                        /* var arrTd = $(".layui-laydate-content td");
                        for (var i=0; i<arrTd.length; i++) {
                            if(arrTd[i].getAttribute("lay-ymd") == today) {
                                arrTd[i].innerHTML = "今";
                                arrTd[i].className = "active";
                                if(value == today) {
                                    arrTd[i].innerHTML = "<span class='laydate-day-mark layui-disabled'>今</span>";
                                }
                            }
                        } */
                        $.each(data.content,function(k,v){
                            $.each($('.layui-laydate-content td span'),function(i,j){
                                //i为元素的索引，从0开始,j为当前处理的元素对象
                                if($(j).html() == parseInt(v.create_day)){
                                    $(j).html(parseInt(v.create_day)+'<div class="markDiv">+'+v.coins+'</div>')
                                }
                            });
                        });
                    },
                    /* done: function(value, date, endDate){
                        var arrTd = $(".layui-laydate-content td");
                        for (var i=0; i<arrTd.length; i++) {
                            if(arrTd[i].getAttribute("lay-ymd") == today) {
                                arrTd[i].innerHTML = "今";
                                arrTd[i].className = "active";
                                if(value == today) {
                                    arrTd[i].innerHTML = "<span class='laydate-day-mark layui-disabled'>今</span>";
                                }
                            }
                        }
                    } */
                });

                $.each(data.content,function(k,v){
                    $.each($('.layui-laydate-content td span'),function(i,j){
                        //i为元素的索引，从0开始,j为当前处理的元素对象
                        if($(j).html() == parseInt(v.create_day)){
                            $(j).html(parseInt(v.create_day)+'<div class="markDiv">+'+v.coins+'</div>')
                        }
                    });
                });
            },
            error:function(data) {
                alert(data.msg);
            },
        });
        //轮播
        var area =document.getElementById('scrollBox');
        var con1 = document.getElementById('con1');
        var con2 = document.getElementById('con2');
        con2.innerHTML=con1.innerHTML;
        function scrollUp(){
            if(area.scrollTop>=con1.offsetHeight){
                area.scrollTop=0;
            }else{
                area.scrollTop++
            }
        }
        var time = 50;
        var mytimer=setInterval(scrollUp,time);
        area.onmouseover=function(){
            clearInterval(mytimer);
        }
        area.onmouseout=function(){
            mytimer=setInterval(scrollUp,time);
        }
        var todayDate = new Date();
        var y = todayDate.getFullYear();
        var m = todayDate.getMonth() + 1;
        var d = todayDate.getDate();
        var today = y + '-' + m + '-' + d;
        /*日历按钮月份获取*/
        $(".yue").html(m + "月");

        /*鼠标移入问号*/
        $(".jihui i").hover(function() {
            if($(".jihui_msg").is(":hidden")) {
                $(".jihui_msg").show();
            } else {
                $(".jihui_msg").hide();
            }
        });

        /*点击今天日期签到*/
        /*$(".layui-laydate-content td.active").click(function(){
            $(".qd_success").show();
            $(".qiandaoDe").show();
            $(".click_qiandao").hide();
            $(this).html("<span class='laydate-day-mark layui-disabled'>今</span>");
            $(".layui-laydate-content td.active").unbind("click"); //执行方法后 移除click方法
        });*/
        /*签到弹窗关闭*/
        $(".close").click(function() {
            $(".qd_success").hide();
            $(".ewm_box").hide();
        });
        /*日历显示*/
        $(".rili_btn").click(function() {
            if($(".rili").is(":hidden")) {
                $(".rili").show();

                $(".sign_centent1 img").hide();
            } else {
                $(".rili").hide();
                $(".sign_centent1 img").show();
            }
        });



        /*展示规则*/
        $(".ahook i").click(function() {
            if($(".guize").is(":hidden")) {
                $(".guize").show();
                $(this).css("transform","translateX(-50%) rotate(-90deg)");
                $(".sign_centent2 img").hide();
            } else {
                $(".guize").hide();
                $(this).css("transform","translateX(-50%) rotate(90deg)");
                $(".sign_centent2 img").show();
            }
        });
    });
</script>
</body>
</html>
