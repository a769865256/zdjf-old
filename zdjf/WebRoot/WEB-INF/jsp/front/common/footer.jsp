<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="footer">
		<div class="f_top">
			<div class="top_one">
				<div class="topbox">
					<span>信息披露</span>
					<a href="<%=path%>/platform/data.action">平台数据</a>
					<a href="<%=path%>/insurance.action">安全保障</a>
					<a href="<%=path%>/company/profile.action">公司简介</a>
					<a href="<%=path%>/company/things.action">公司事记</a>
					<a href="<%=path%>/company/1/notice.action">公司动态</a>
					<a href="<%=path%>/contact.action">联系我们</a>
				</div>
				<div class="topbox">
					<span>帮助中心</span>
					<a href="<%=path%>/commonQuestion/1/help.action">常见问题</a>
					<a href="<%=path%>/investGuide/1/help.action">投资指南</a>
					<a href="<%=path%>/userWeal/1/help.action">会员福利</a>
					<a href="<%=path%>/fundsDeposit/1/help.action">资金存管</a>
					<a href="<%=path%>/otherQuestion/1/help.action">其他问题</a>
				</div>
				<div class="topbox">
					<span>新手指南</span>
					<a href="<%=path %>/toRegister.action">注册登录</a>
					<a href="<%=path %>/mywealth.action">充值提现</a>
					<a href="<%=path %>/product/list.action">投资理财</a>
				</div>
			</div>
			<div class="top_two">
				<ul>
					<li><p>官方微信</p></li><li><p>移动客户端</p></li>
				</ul>
			</div>
			<div class="top_three">
				<div class="tlt">全国服务热线</div>
				<p class="txt">400-690-9898</p>
				<p class="txt">0571-56929176</p>
				<div class="tlt addtlt">
					<p>工作日: 9:30-17:30</p>
				</div>
				<div class="tlt">官方交流QQ群：463637911</div>
				<div class="police"></div>
			</div>
		</div>
		<div class="f_bot">Copyright © 2016 杭州云翳网络科技有限公司 版权所有　ICP经营许可证：浙B2-20170101 浙ICP备1</div>
	</div>