<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 左栏目 -->
<div class="body-left">
	<ul>
		<li class="wz <c:if test="${CenterNav == 1}">select</c:if>">
			<a href="${selfSite}/zdjf/center">
				<img src="${selfSite}/zdjf/res/center/images/user/wdcf.png" /> 我的财富
			</a>
		</li>
		<li class="wz <c:if test="${CenterNav == 2}">select</c:if>"><a
			href="${selfSite}/zdjf/center/funds"> <img
				src="${selfSite}/zdjf/res/center/images/user/zjmx.png" /> 资金明细
		</a></li>
		<li class="wz <c:if test="${CenterNav == 6}">select</c:if>"><a
			href="${selfSite}/zdjf/center/orders"> <img
				src="${selfSite}/zdjf/res/center/images/user/touzilicai.png" /> 我的理财
		</a></li>
		
		<li class="wz <c:if test="${CenterNav == 7}">select</c:if>"><a href="${selfSite}/zdjf/center/gift">
				<img src="${selfSite}/zdjf/res/center/images/user/red-bag.png" /> 我的优惠
		</a></li>
		<%--<li class="wz <c:if test="${CenterNav == 4}">select</c:if>"><a href="${selfSite}/zdjf/center/banks">--%>
				<%--<img src="${selfSite}/zdjf/res/center/images/user/smrz.png" /> 银行卡管理--%>
		<%--</a></li>--%>
		<li class="wz <c:if test="${CenterNav == 3}">select</c:if>"><a href="${selfSite}/zdjf/center/safe">
				<img src="${selfSite}/zdjf/res/center/images/user/xgmm.png" /> 安全中心
		</a></li>
		<li class="wz <c:if test="${CenterNav == 5}">select</c:if>"><a href="${selfSite}/zdjf/center/invitation">
				<img src="${selfSite}/zdjf/res/center/images/user/iconfont-liwu.png" /> 邀请有礼<i class="iconfont nav-newicon">&#xe636;</i>
		</a></li>
		<li class="wz <c:if test="${CenterNav == 8}">select</c:if>"><a href="${selfSite}/zdjf/center/remind">
			<img src="${selfSite}/zdjf/res/center/images/user/xxzx.png" /> 消息中心
		</a></li>
	</ul>
	<img class="kefu" src="${selfSite}/zdjf/res/center/images/user/kefu.png" />
	<p>无论您在正道金服平台，</p>
	<p>遇到任何问题，</p>
	<p>都可以第一时间联系我们！</p>
	<p>我们将诚挚为您服务！</p>
	<p>电话：400-690-9898</p>
</div>