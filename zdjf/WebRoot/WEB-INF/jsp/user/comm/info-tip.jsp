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
	<base href="${selfSite}/zdjf" />
	<meta charset="UTF-8">
	<title>正道金服 - 专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/>
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/public.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/profit-success.css?t=20160616">
</head>

<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="loading">
	<div class="alert">
		<div class="alert-darkbg"></div>
		<div class="pay-show">
			<i></i>
		</div>
	</div>
</div>

<div class="profit-layout hide">

	<c:if test="${rsp.type=='netsave'}">
		<!-- 充值成功 -->
		<div class="profit">
			<p><i class="icon-succ"></i></p>
			<p class="tit text-dark">恭喜您充值成功！</p>
			<p class="con">本次充值<span class="text-orange"><fmt:formatNumber value="${rsp.transAmt }" pattern="#,##0.00" type="number" /></span>元，当前可用余额为<span class="text-orange">
			<fmt:formatNumber value="${rsp.balance }" pattern="#,##0.00" type="number" /></span>元。</p>
			<p class="btn">
				<a href="${selfSite}/zdjf/product/search.html" class="btn-red">我要投资</a>
				<a href="${selfSite}/zdjf/center" class="btn-red-border">我的财富</a>
			</p>
		</div>
	</c:if>

	<c:if test="${rsp.type=='cash'}">
		<!-- 提现成功 -->
		<div class="withdraw">
			<p><i class="icon-succ"></i></p>
			<p class="tit text-dark">恭喜您申请提现成功！</p>
			<p class="con">您已申请提现<span class="text-orange"><fmt:formatNumber value="${rsp.realTransAmt }" pattern="#,##0.00" type="number" /></span>元，提现手续费<span class="text-orange"><fmt:formatNumber value="${rsp.feeAmt }" pattern="#,##0.00" type="number" /></span>元，实际到账<span class="text-orange"><fmt:formatNumber value="${rsp.transAmt }" pattern="#,##0.00" type="number" /></span>元，当前余额为
				<span class="text-orange"><fmt:formatNumber value="${rsp.balance }" pattern="#,##0.00" type="number" /></span>元。</p>
			<p class="con">预计提现到账时间为<span class="text-orange J_postedTime">--</span>，如遇法定节假日将顺延一个工作日。</p>
			<p class="btn">
				<a href="${selfSite}/zdjf" class="btn-red">返回首页</a>
				<a href="${selfSite}/zdjf/center" class="btn-red-border">我的财富</a>
			</p>
		</div>
	</c:if>

	<c:if test="${rsp.type=='reg'}">
		<!-- 托管开通申请成功 -->
		<div class="apply">
			<p><i class="icon-succ"></i></p>
			<p class="tit text-dark">恭喜您实名认证成功！</p>
			<p class="btn">
				<a href="${selfSite}/zdjf/product/search.html" class="btn-red">立即投资</a>
				<a href="${selfSite}/zdjf" class="btn-red-border">返回首页</a>
			</p>
			<c:if test="${!empty product}">
			<div class="offer" id="offer">
				<div class="icon-offer"></div>
				<div class="item">
					<p class="name">
						<a href="javascript:void(0);" class="overflow">${product.productCode}</a>
					</p>
					<c:if test="${product.isFresh == 1}">
						<i class="newhand-icon">新手专享</i>
					</c:if>
					<ul>
						<li class="photo"><span><img src="${product.photo}" alt=""></span></li>
						<li class="day text-light-red ">
							<p><span class="font-arial"><fmt:formatNumber pattern="0.00" value="${product.income}" /></span>%</p>
							<p class="tit text-gray">年化收益</p>
						</li>
						<li class="day income-day">
							<p><span class="font-arial">${product.incomeDays}</span>天</p>
							<p class="tit text-gray">收益天数</p>
						</li>
					</ul>
				</div>
			</div>
			</c:if>
		</div>
	</c:if>

	<c:if test="${rsp.type=='err'}">
		<!-- 失败提示信息 -->
		<div class="fail">
			<p><i class="icon-fail"></i></p>
			<p class="tit text-dark">${rsp.desc}</p>
			<p class="con">如有疑问请联系客服：400-690-9898　<script charset="utf-8" type="text/javascript" src="http://wpa.b.qq.com/cgi/wpa.php?key=XzkzODA3MDgyMV80NjAzMjRfNDAwNjkwOTg5OF8"></script></p>
			<p class="btn">
				<a href="/center" class="btn-red">个人中心</a>
				<a href="${selfSite}/zdjf" class="btn-red-border">返回首页</a>
			</p>
			<div class="qa-link">
				<p class="title">常见问题：<a href="${selfSite}/zdjf/q&a" target="_blank" class="more">更多>></a></p>
				<p><a href="${selfSite}/zdjf/about/qa02.html#fast-payqa" target="_blank">• 快捷支付失败常见问题</a></p>
				<p><a href="${selfSite}/zdjf/about/qa02.html#bank-payqa" target="_blank">• 网银充值失败常见问题</a></p>
			</div>
			<div class="qa-height"></div>
		</div>
	</c:if>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<jsp:include page="helper.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>

<script>
	setTimeout("loca()",2000)
	function loca() {
		$('.loading').addClass('hide');
		$('.profit-layout').removeClass('hide');
	}

	//推荐跳转
	$('#offer').on('click', function(){
		location.href = '${selfSite}/zdjf/product/detail.html?productId=${product.productId}';
	})

	//计算提现时间
	var date = new Date();
	var weekday = date.getDay();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var updateTime;
	if ((weekday == 5 && hour >= 18) || weekday == 6 || weekday == 0) {
		if(weekday == 5){
			updateTime = Days(date, 4) + " （星期二）";
		}
		if(weekday == 6){
			updateTime = Days(date, 3) + " （星期二）";
		}
		if(weekday == 0){
			updateTime = Days(date, 2) + " （星期二）";
		}
	}else{
		if(hour < 18){
			if(weekday == 5){
				updateTime = Days(date, 3) + " （星期一）";
			}else{
				updateTime = Days(date, 1) + week(weekday);
			}
		}else{
			if( weekday == 4){
				updateTime = Days(date, 4) + " （星期一）";
			}else{
				var day = weekday + 1;
				updateTime = Days(date, 1) + week(day);
			}
		}
	}
	$(".J_postedTime").text(updateTime);

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

//		if( myArray[1] < 10){
//			myArray[1] = '0' + myArray[1];
//		}
//		if( myArray[2] < 10){
//			myArray[2] = '0' + myArray[2];
//		}

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
</script>
</html>
