<!DOCTYPE html>
<html lang="zh-cn" class="no-js">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta name="description" content="正道金服，第三方债权交易金融信息服务平台"/>
<meta name="keywords" content="正道金服，第三方债权交易金融信息服务平台"/>
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" /> 
<link rel="stylesheet" href="http://m.zdjfu.com/assets/css/reset.css">
    <style>
        body, html{
            background-color: #ffffff;
        }
        .con .title-icon img{
            width: 22%;
            margin: 40px auto 0;
            display: block;
        }
        .con .text{
            margin-top: 15px;
            font-size: 22px;
            color: #323232;
            line-height: 35px;
            height: 35px;
            text-align: center;
            border-bottom: 1px solid #dcdcdc;
            padding-bottom: 15px;
        }
        .con table{
            width: 100%;
            margin-left: 8px;
            font-size: 14px;
            line-height: 160%;
            color: #323232;
            border-left: 3px solid #fe9500;
            text-indent: 10px;
        }
        .con table td{
            padding: 0 10px;
            text-align: right;
        }
        .con table td.name{
            text-align: left;
        }
        .con table td .red{
            color: #fb1010;
            margin-right: 5px;
        }
        .recharge {
            padding: 10px 5%;
            line-height: 150%;;
        }
        .recharge span{
            float: right;
        }
        .recharge b{
            font-weight: normal;
        }
        .text-red {
            color: #fb1010;
            margin-right: 5px;
        }
        .con .tip{
            font-size: 14px;
            color: #909090;
            line-height: 180%;
            width: 90%;
            margin: 30px auto;
            position: relative;
        }
        .con .tip span{
            color: #323232;
            display: inline-block;
        }
        .order-paysucc table{
            width: 60%;
            margin: 30px auto 0px;
            font-size: 14px;
            line-height: 180%;
            color: #909090;
            border: 0;

        }
        .order-paysucc table tr{
        	height: 33%;
        }
        .order-paysucc table img.progress{
            width: 35px;
            padding-right: 30px;
        }
        .order-paysucc table span.tit{
            color: #646464;
            font-size: 16px;
        }
        .order-paysucc .text-red{
            color: #e93636 !important;
        }
        .order-paysucc .look-btn{
            height: 42px;
            line-height: 42px;
            width: 85%;
            display: block;
            font-size: 18px;
            background: #f84141;
            color: #fff;
            border-radius: 5px;
            text-align: center;
            margin: -20px auto 10px;
        }
        .active-tip{
            width: 80%;
            font-size: 14px;
            margin: 20px auto 0;
            line-height: 25px;
            color: #323232;
            border: 1px solid #f9b243;
            padding: 10px 15px;
            border-radius: 5px;
            background-color: #f9ecd8;
        }
        .active-tip b{
            color: #fd4f4f;
            font-size: 16px;
        }
        .back-home {
            border-radius: 23px !important;
            height: 46px !important;
            line-height: 46px !important;
            width: 70% !important;
            font-size: 18px !important;
            margin-top: 40px !important;
        }
        .take-way{
            padding: 0 5% 0;
            color: #909090;
        }
        .take-way img {
            width: 20px;
            margin-right: 10px;
        }
        .take-way table{
            border-left: 3px solid #fe9500;
        }
        .text-orange {
            color: #fe9500;
        }
        .tab-right {
            float: right;
        }
        .border-gray {
            width: 90%;
            margin: 0 auto;
            font-size: 14px;
            line-height: 150%;
            color: #323232;
            padding-left: 16px;
        }
        .border-gray span{
            min-height: 35px;
            border-left: 3px solid #c9c9c9;
            display: block;
        }
    </style>
</head>
<body>
<!-- bar**public -->
<div class="bar">
    <a href="http://m.zdjfu.com/index.html" class="iconfont back">&#xe605;</a>
    <span>成功</span>
    <div class="iconfont nav J_nav">&#xe604;</div>
    <div class="bar-bg" style="display:none"></div>
    <div class="nav-con" id="barNav" style="display:none">
        <div class="icon-arrow"></div>
        <a href="http://m.zdjfu.com/index.html">首页</a>
        <a href="javascript:void(0)" class="J_appDownload">下载APP</a>
    </div>
</div>

<c:if test="${tipInfo.tipType!='InitiativeTender'}">
<div class="con">
    <p class="title-icon"><img src="${selfSite}/zdjf/res/mob/img/pay/succ-icon01.png" alt=""></p>
    <c:if test="${tipInfo.tipType=='UserRegister'}">
	<!-- 开户成功 -->
 	<p class="text">恭喜您实名认证成功！</p>
	</c:if>
	<c:if test="${tipInfo.tipType=='UserBindCard'}">
	<!-- 开户成功 -->
 	<p class="text">恭喜您绑卡成功！</p>
	</c:if>
   <c:if test="${tipInfo.tipType=='NetSave'}">
	<!-- 充值成功 -->
 	<p class="text">恭喜您充值成功！</p>
 	<%--<p class="text" style="width: 100%; text-align: center;height: 35px; line-height: 35px; color: #646464; font-size:16px; border: 0;">--%>
 		<%--<span id="djs">5</span>秒后自动返回首页--%>
 	<%--</p>--%>
    <div class="recharge">
        <p>本次充值<span><b class="text-red"><fmt:formatNumber value="${transAmt }" pattern="#,##0.00" type="number" /></b>元</span></p>
        <p>当前余额<span><b class="text-red"><fmt:formatNumber value="${balance }" pattern="#,##0.00" type="number" /></b>元</span></p>
    </div>
    <a href="http://m.zdjfu.com/index.html" class="back-home">完成</a>
    <script type="text/javascript">  
//	 var t = 500;
//	 function showTime(){
//	    t -= 1;
//	    document.getElementById('djs').innerHTML= t;
//	    if(t==0){
//	         location.href='http://m.zdjfu.com/index.html';
//	    }
//	     //每秒执行一次,showTime()
//	     setTimeout("showTime()",1000);
//	 }
// 	showTime();
 </script> 
    
	</c:if>
	<c:if test="${tipInfo.tipType=='Cash'}">
	<p class="text">恭喜您提现申请成功！</p>
    <div class="table take-way" style="margin-top: 25px;">
        <img src="${selfSite}/zdjf/res/mob/img/pay/arrow-orange.png" alt=""><span class="text-orange">申请提现已提交</span>
        <table>
            <tr>
                <td class="name">申请提现</td>
                <td><span class="red"><fmt:formatNumber value="${realTransAmt }" pattern="#,##0.00" type="number" /></span>元</td>
            </tr>
            <tr>
                <td class="name">提现手续费</td>
                <td><span class="red"><fmt:formatNumber value="${feeAmt }" pattern="#,##0.00" type="number" /></span>元</td>
            </tr>
            <tr>
                <td class="name">实际到账</td>
                <td><span class="red"><fmt:formatNumber value="${transAmt }" pattern="#,##0.00" type="number" /></span>元</td>
            </tr>
            <tr>
                <td class="name">当前余额</td>
                <td><span class="red"><fmt:formatNumber value="${balance }" pattern="#,##0.00" type="number" /></span>元</td>
            </tr>
            <tr>
                <td class="name">&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </div>
    <p class="take-way"><img src="${selfSite}/zdjf/res/mob/img/pay/arrow-orange.png" alt=""><span class="text-orange">审核处理中</span><span class="tab-right"><fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd HH:mm" /></span></p>
        <div class="border-gray"><span></span></div>
    <p class="take-way"><img src="${selfSite}/zdjf/res/mob/img/pay/arrow-gray.png" alt="">到账成功<span class="tab-right J_postedTime">--</span></p>
    <p class="tip">
        <span>提现小贴士：</span>如遇法定节假日将顺延一个工作日到账。
    </p>
	</c:if>
</div>
</c:if>
<c:if test="${tipInfo.tipType=='InitiativeTender'}">
	<!-- 支付成功 -->
	<div class="order-paysucc">
        <c:if test="${!empty tipInfo.record.title}">
            <p class="active-tip">
                太幸运了，您是该项目的${tipInfo.record.orderStr}投资用户，获得称号${tipInfo.record.title}，并得到正经值奖励 <b>${tipInfo.record.coin}</b> 元。再接再厉哦！
            </p>
        </c:if>
    <p class="table">
        <table>
            <tr>
                <td><img src="${selfSite}/zdjf/res/mob/img/pay/pay-bg_01.png" class="progress" alt=""></td>
                <td><span class="tit text-red">今天</span><br/>成功投资<fmt:formatNumber value="${tipInfo.record.amount}" pattern="0.00" />元</td>
            </tr>
            <tr>
            	<td><img src="${selfSite}/zdjf/res/mob/img/pay/pay-bg_02.png" class="progress" alt=""></td>
                <td><span class="tit"><fmt:formatDate value="${tipInfo.record.startDate}" pattern="yyyy-MM-dd" /></span><br/>开始计算收益</td>
            </tr>
            <tr>
            	<td><img src="${selfSite}/zdjf/res/mob/img/pay/pay-bg_03.png" class="progress" alt=""></td>
                <td><span class="tit"><fmt:formatDate value="${tipInfo.record.returnDate}" pattern="yyyy-MM-dd" /></span><br/>本息全部到账</td>
            </tr>
        </table>
    </p>
<!--     <p> -->
<!--         <a href="javascript:myRichOnClick();" class="look-btn">查看我的财富</a> -->
<!--       </p> -->
    </div>
</c:if>
<c:if test="${tipInfo.tipType!='NetSave'}">
<a href="http://m.zdjfu.com/index.html" class="back-home">完成</a>
</c:if>
<script type="text/javascript" src="http://m.zdjfu.com/assets/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="http://m.zdjfu.com/assets/js/common.js"></script>
<script>
//计算提现时间
var date = new Date();
var weekday = date.getDay();
var hour = date.getHours();
var minute = date.getMinutes();
var updateTime;
if ((weekday == 5 && hour >= 18) || weekday == 6 || weekday == 0) {
	if(weekday == 5){
		updateTime = Days(date, 4);
	}
	if(weekday == 6){
		updateTime = Days(date, 3);
	}
	if(weekday == 0){
		updateTime = Days(date, 2);
	}
}else{
	if(hour < 18){
		if(weekday == 5){
			updateTime = Days(date, 3);
		}else{
			updateTime = Days(date, 1);
		}
	}else{
		if( weekday == 4){
			updateTime = Days(date, 4);
		}else{
			var day = weekday + 1;
			updateTime = Days(date, 1);
		}
	}
}
$(".J_postedTime").text('预计' + updateTime + '到账');

// 获取一段时间前或后日期
function Days(day1, days){

	//用距标准时间差来获取相距时间
	var add = days * 1000 * 60 * 60 * 24; //factor: second / minute / hour / day
	var future = Date.parse(day1) + add;
	var dateFuture = new Date(future);

	var myArray = Array();
	myArray[0] = dateFuture.getFullYear();
	myArray[1] = dateFuture.getMonth() + 1;
	myArray[2] = dateFuture.getDate();

//	if( myArray[1] < 10){
//		myArray[1] = '0' + myArray[1];
//	}
//	if( myArray[2] < 10){
//		myArray[2] = '0' + myArray[2];
//	}

	//改写所需输出格式
	var Days =  myArray[0] + '年' +
			myArray[1] + '月' +
			myArray[2] + '日';
	return Days;
}
function week(today){
	switch(today){
		case 1:
			return " （星期二）"
			break;
		case 2:
			return " （星期三）"
			break;
		case 3:
			return " （星期四）"
			break;
		case 4:
			return " （星期五）"
			break;
	}
}
    function myRichOnClick(){
        /*
         * 智能机浏览器版本信息:
         *
         */
        var browser = {
            versions: function() {
                var u = navigator.userAgent, app = navigator.appVersion;
                return {//移动终端浏览器版本信息
                    trident: u.indexOf('Trident') > -1, //IE内核
                    presto: u.indexOf('Presto') > -1, //opera内核
                    webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                    gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                    mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
                    ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                    android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                    iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
                    iPad: u.indexOf('iPad') > -1, //是否iPad
                    webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
                };

            }(),
            language: (navigator.browserLanguage || navigator.language).toLowerCase()
        }
        if (browser.versions.ios || browser.versions.iPhone || browser.versions.iPad) {
        	goMyWealthCenter();
        }else if (browser.versions.android) {
        	window.android.goMyWealthCenter();
        }else{

        }
    }
</script>
</body>
</html>