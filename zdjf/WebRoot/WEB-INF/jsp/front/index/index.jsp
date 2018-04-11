<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>正道金服</title>
	<!-- reset/iconfont -->
	<link rel="stylesheet" href="<%=path%>/css/front/reset.css">
	<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
	<link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
	<link rel="stylesheet" href="<%=path%>/css/front/index.css">
	<!-- banner切换 -->
	<link rel="stylesheet" href="<%=path%>/module/slides/flexslider.css">
</head>
<body>
<!-- header -->
<div class="header">
	<jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="banner">
	<div class="layui-carousel" id="homebanner">
		<div carousel-item="">
			<c:forEach var="list" items="${content.advertiseList }">
				<div><a href="${list.href_url}" target="_blank"><span style="background: url(${list.image_url}) center 0 no-repeat;display: block;width: 100%;height: 100%;"></span></a></div>
			</c:forEach>
		</div>
	</div>
</div>
<div class="news">
	<div class="new_tent">
		<div class="box"></div>
		<div class="tabbox">
			<!-- 未登录 -->
			<c:if test="${empty content.user}">
				<div class="tab tab1">
					<p class="tlt">账户资金由银行全程存管</p>
					<p class="txt">注册即送</p>
					<p class="sum">688<span>元</span></p>
					<p class="btn">
						<a href="<%=path%>/toRegister.action">立即注册</a>
					</p>
					<p class="go_login">
						已有账号？<a href="<%=path%>/toLogin.action" class="f_rt">登录</a>
					</p>
				</div>
			</c:if>
			<!-- 已登录 -->
			<c:if test="${!empty content.user}">
				<c:if test="${content.user.status >= 3}">
					<div class="tab tab2" >
						<h3>${content.user.encrypt_phone }</h3>
						<p class="tlt">欢迎来到正道金服</p>
						<p class="money hd1">总资产<i class="iconfont">&#xe629;</i></p>
						<p class="money hd2" style="display: none;">总资产<i class="iconfont by">&#xe632;</i></p>
						<p class="mon mon1">***</p>
						<p class="mon mon2" style="display: none;"><fmt:formatNumber
								value="${content.all}" type="number"
								pattern="0.00" /></p>
						<p class="btn">
							<a href="<%=path%>/mywealth.action">我的账户</a>
						</p>
						<p class="montlt">账户资金由银行全程存管</p>
					</div>
				</c:if>
				<!-- 已登录未开通银行存管 -->
				<c:if test="${content.user.real_name_auth!=1 || content.user.status < 3}">
					<div class="tab tab3" >
						<p class="name_tlt">新手指引</p>
						<p class="name_txt">您尚未开通银行存管<br>开通银行存管可获得奖励</p>
						<p class="btn">
							<a href="<%=path%>/toNewAudit.action">立即开通</a>
						</p>
						<p class="montlt">账户资金由银行全程存管</p>
					</div>
				</c:if>
			</c:if>
		</div>
		<span class="msg"><i class="iconfont">&#xe64c;</i></span>
		<div class="flexslider_news">
			<ul class="slides">
				<c:forEach var="list" items="${content.noticeList }">
					<li><a href="<%=path %>/notice.action?notice_id=${list.id }" title="${list.title}"
						   class="overflow text-dark">${list.title}</a><span
							class="text-gray"><fmt:formatDate
							value="${list.create_time}" pattern="MM-dd" /></span></li>
				</c:forEach>
			</ul>
		</div>
		<a href="<%=path%>/company/1/notice.action?currentPage=1&limit=10" class="moremsg">查看更多<i class="iconfont">&#xe611;</i></a>
	</div>
</div>
<div class="content">
	<div class="prompt">
		<div class="pto_box">
			<i class="iconfont bank"><img src="<%=path %>/images/front/img/bank.png"></i>
			<div>
				<p>银行存管 新浪支付</p>
				<p>银行资金存管，新浪支付技术保障</p>
			</div>
		</div>
		<div class="pto_box">
			<i class="iconfont rz"><img src="<%=path %>/images/front/img/rz.png"></i>
			<div>
				<p>A轮融资 国企控股</p>
				<p>千万A轮融资及国资控股实缴资本5千万</p>
			</div>
		</div>
		<div class="pto_box">
			<i class="iconfont xmhg"><img src="<%=path %>/images/front/img/xmhg.png"></i>
			<div>
				<p>项目合规 信息透明</p>
				<p>多重风控严选项目，信息披露真实透明</p>
			</div>
		</div>
		<!-- 签到 -->
		<div class="qiandao <c:if test="${not empty content.user && content.isSignTimes == 0}">hide</c:if>">
			<a href="<%=path%>/toSign.action" class="go_qiandao"><img src="<%=path%>/images/front/img/wealth/logo.png" alt=""/></a>
		</div>
	</div>
	<!-- 新手专区 -->
	<div class="novice">
		<h3>新手专区<span>新手投资 专享通道</span></h3>
		<div class="nobox">
			<div class="no_l_img"><img src="<%=path%>/images/front/img/novice.png" alt=""></div>
			<div class="no_r_txt">
				<div class="tlt">
					<p>${content.productList[0].product_code}<c:if test="${content.productList[0].is_fresh == 1}"><span>新手标</span></c:if><span>
						<fmt:formatNumber value="${content.productList[0].pay_min }"
										  type="number" pattern="0" />起投</span></p>
				</div>
				<div class="txt">
					<div class="logo">
						<a href="<%=path%>/product/detail/${content.productList[0].id}.action">
							<img src="${content.productList[0].photo}" alt="">
						</a>
					</div>
					<div class="sum">
						<div class="sumbox">
							<div class="list">
								<p><fmt:formatNumber
										value="${content.productList[0].income }" type="number"
										pattern="0.00" /><i>%</i>
								</p>
								<p>预期年化收益</p>
							</div>
							<div class="list">
								<p>
									${content.productList[0].incomeDays }
									<i>天</i>
								</p>
								<p>收益天数</p>
							</div>
							<div class="list">
								<p>${content.productList[0].can_buy_money}<i>元</i>
								</p>
								<p>可投金额</p>
							</div>
						</div>
						<div class="barline">
							<p><span style="width: ${content.productList[0].speed}%;"><img
									src="<%=path%>/images/front/img/bar.png" alt=""></span></p>
							<i><fmt:formatNumber value="${content.productList[0].speed}" type="number" pattern="0.00" />%</i>
						</div>
					</div>
					<div class="btn">
						<c:if test="${content.productList[0].status==4 }">
							<a href="<%=path%>/product/detail/${content.productList[0].id}.action">立即投资</a>
						</c:if>
						<c:if test="${content.productList[0].status==5}">
							<a href="<%=path%>/product/detail/${content.productList[0].id}.action" class="performance">履约中</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 理财项目 -->
	<div class="project">
		<h3>精选理财项目</h3>
		<div class="prolist">
			<c:forEach var="list" begin="1" end="4"
					   items="${content.productList }">
				<div class="list">
					<h4>${list.product_code }</h4>
					<div class="h_img">
						<a href="<%=path%>/product/detail/${list.id}.action">
							<img src="${list.photo }" alt="">
						</a>
					</div>
					<p class="tlt">
							<span><fmt:formatNumber
									value="${list.income-list.platform_interest}" type="number"
									pattern="0.00"/><c:if test="${list.platform_interest<=0.0 }">%</c:if></span>
						<c:if test="${list.platform_interest>0.0 }"><span>+<fmt:formatNumber
								value="${list.platform_interest}" type="number"
								pattern="0.00"/>%</span></c:if>
					</p>
					<p class="txt">预期年化收益</p>
					<div class="listbar">
						<p>
								<span style="width: ${list.speed }%"><img
										src="<%=path%>/images/front/img/bar.png" alt=""></span>
						</p>
						<i><fmt:formatNumber
								value="${list.speed }" type="number"
								pattern="0.00"/>%</i>
					</div>
					<div class="sum">
						<div class="sum_list">
							<p>${list.incomeDays }天</p>
							<p>收益天数</p>
						</div>
						<div class="sum_list">
							<p>
								<fmt:formatNumber value="${list.can_buy_money }" type="currency"
												  pattern="0.00" />
								元</p>
							<p>可投金额</p>
						</div>
					</div>
					<div class="probtn">
						<c:if test="${list.status==4 }">
							<a href="<%=path%>/product/detail/${list.id}.action">立即投资</a>
						</c:if>
						<c:if test="${list.status==5}">
							<a href="<%=path%>/product/detail/${list.id}.action" class="performance">履约中</a>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:if test="${content.productList.size()>5 }">
			<div class="prolist move_clean">
				<c:forEach var="list" begin="5" end="8"
						   items="${content.productList }">
					<div class="list">
						<h4>${list.product_code }</h4>
						<div class="h_img">
							<a href="<%=path%>/product/detail/${list.id}.action">
								<img src="${list.photo }" alt="">
							</a>
						</div>
						<p class="tlt">
							<span><fmt:formatNumber
									value="${list.income-list.platform_interest}" type="number"
									pattern="0.00"/><c:if test="${list.platform_interest<=0.0 }">%</c:if></span>
							<c:if test="${list.platform_interest>0.0 }">
							<span>+<fmt:formatNumber
									value="${list.platform_interest}" type="number"
									pattern="0.00"/>%</span>
							</c:if>
						</p>
						<p class="txt">预期年化收益</p>
						<div class="listbar">
							<p>
								<span style="width: ${list.speed }%"><img
										src="<%=path%>/images/front/img/bar.png" alt=""></span>
							</p>
							<i><fmt:formatNumber
									value="${list.speed }" type="number"
									pattern="0.00"/>%</i>
						</div>
						<div class="sum">
							<div class="sum_list">
								<p>${list.incomeDays }天</p>
								<p>收益天数</p>
							</div>
							<div class="sum_list">
								<p>${list.can_buy_money }元</p>
								<p>可投金额</p>
							</div>
						</div>
						<div class="probtn">
							<c:if test="${list.status==4 }">
								<a href="<%=path%>/product/detail/${list.id}.action">立即投资</a>
							</c:if>
							<c:if test="${list.status==5}">
								<a href="<%=path%>/product/detail/${list.id}.action" class="performance">履约中</a>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
	<!-- 活动 -->
	<div class="activity">
		<h3>平台活动</h3>
		<div id="hezuo_id" class="cxscroll3">
			<div class="box">
				<ul class="list">
					<li><a href="<%=path%>/toSign.action"><img src="<%=path%>/images/front/img/active/a1.png" /><span></span><div>每日一签 好运相伴</div></a></li>
					<li><a href="<%=path%>/newHand/award.action"><img src="<%=path%>/images/front/img/active/a2.png" /><span></span><div>新手专享3重礼</div></a></li>
					<li><a href="javascript:"><img src="<%=path%>/images/front/img/active/a3.png" /><span></span><div>玩赚正经值</div></a></li>
					<%--<li><a href="<%=path%>/playPositiveValue.action"><img src="<%=path%>/images/front/img/active/a3.png" /><span></span><div>玩赚正经值</div></a></li>--%>
					<li><a href="javascript:"><img src="<%=path%>/images/front/img/active/a4.png" /><span></span><div>邀请好友投资</div></a></li>
				</ul>
			</div>
			<!-- 控制按钮会自动创建，可省略 -->
			<a class="iconfont prev">&#xe600;</a>
			<a class="iconfont next">&#xe697;</a>
		</div>
	</div>
	<!-- 媒体报道 -->
	<div class="report">
		<div class="re_l">
			<h3>媒体报道<a href="<%=path%>/company/6/notice.action?currentPage=1&limit=5">查看更多<i class="iconfont">&#xe611;</i></a></h3>
			<div class="re_lbox">
				<div class="box_img"><img src="${content.newList[0].image_url }" alt=""></div>
				<div class="txt">
					<ul>
						<c:forEach var="newsReport" items="${content.newList }">
							<li><a href="<%=path %>/notice.action?notice_id=${newsReport.id }">${newsReport.title}<span><fmt:formatDate
									value="${newsReport.create_time}" pattern="yyyy.MM.dd" /></span></a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="re_r">
			<h3>常见问题<a href="<%=path%>/commonQuestion/1/help.action">查看更多<i class="iconfont">&#xe611;</i></a></h3>
			<div class="re_rbox">
				<ul>
					<li><a href="<%=path %>/commonQuestion/2/help.action"><i class="iconfont"></i>新手注册有什么奖励？</a></li>
					<li><a href="<%=path%>/fundsDeposit/3/help.action"><i class="iconfont"></i>如何开通银行存管账户？</a></li>
					<li><a href="<%=path%>/investGuide/5/help.action"><i class="iconfont"></i>申请提现后多久能到账？</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 合作伙伴 -->
<div class="partner">
	<div class="box">
		<div class="boxx">
			<h3>合作伙伴</h3>
		</div>
	</div>
	<div class="parlist">
		<%-- <div class="list"><img src="<%=path%>/images/front/img/partner/p1.png" alt=""></div> --%>
		<div class="list"><a href="https://www.aliyun.com/" target="_blank"><img src="<%=path%>/images/front/img/partner/p2.png" alt=""></a></div>
		<div class="list"><a href="https://pay.sina.com.cn" target="_blank"><img src="<%=path%>/images/front/img/partner/p3.png" alt=""></a></div>
		<div class="list"><a href="http://www.alidayu.com/" target="_blank"><img src="<%=path%>/images/front/img/partner/p4.png" alt=""></a></div>
		<div class="list"><a href="javascript:"><img src="<%=path%>/images/front/img/partner/p5.png" alt=""></a></div>
	</div>
	<div class="tlt">理财有风险 投资需谨慎</div>
</div>
<!-- content end-->

<!-- 侧导航栏-->
<div class="floating_ck">
	<dl>
		<dd class="kf">
			<div class="floating_left"><i></i><a href="http://zdjfu.udesk.cn/im_client/?web_plugin_id=24428" target="_blank">在线客服</a></div>
		</dd>
		<!-- <dd class="jsq">
            <div class="floating_left"><a href="javascript:;">收益计算器</a></div>
        </dd> -->
		<dd class="wxin">
			<div class="floating_left floating_gf"><i></i><a href="javascript:;">官方微信</a></div>
		</dd>
		<dd class="phone">
			<div class="floating_left floating_yd">
				<i></i>
				<a href="javascript:;">移动客户端</a>
			</div>
		</dd>
		<dd class="return" onclick="returnTop();">
			<i class="iconfont">&#xe600;</i>
		</dd>
	</dl>
</div>

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script type="text/javascript" src="<%=path%>/module/jquery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/module/slides/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="<%=path%>/module/cxscroll/jquery.cxscroll.js"></script>
<script type="text/javascript" src="<%=path%>/module/sticky-header.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/index.js"></script>
<script type="text/javascript">
    $('.header').stickMe({
        topOffset:100
    });
</script>
</body>
</html>
