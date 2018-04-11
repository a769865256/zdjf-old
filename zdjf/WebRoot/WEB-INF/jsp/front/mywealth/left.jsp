<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String showType = (String)request.getAttribute("showType");
%>
<div class="we_lf">
			<ul>
				<li <%if(showType!=null && showType.equals("mywealth")){ %>class="active"<%} %>><i class="iconfont mycf"></i><a data-html="wealthbox" href="<%=path%>/mywealth.action">我的财富</a></li>
				<li <%if(showType!=null && showType.equals("mycapital")){ %>class="active"<%} %>><i class="iconfont zjmx"></i><a data-html="capitalbox" href="<%=path%>/myFunds.action">资金明细</a></li>
				<li <%if(showType!=null && showType.equals("myconduct")){ %>class="active"<%} %>><i class="iconfont mylc"></i><a data-html="duct" href="<%=path%>/myProductBuy.action">我的理财</a></li>
				<li <%if(showType!=null && showType.equals("mydiscount")){ %>class="active"<%} %>><i class="iconfont myyh"></i><a data-html="discount" href="<%=path%>/myCou.action">我的优惠</a></li>
				<li <%if(showType!=null && showType.equals("myaccount")){ %>class="active"<%} %>><i class="iconfont zhsz"></i><a data-html="account" href="<%=path%>/toSetAccount.action">账户设置</a></li>
				<li <%if(showType!=null && showType.equals("myinvitation")){ %>class="active"<%} %>><i class="iconfont yqyl"></i><a data-html="invitation" href="javascript:;">邀请有礼</a></li>
				<li <%if(showType!=null && showType.equals("mynews")){ %>class="active"<%} %>><i class="iconfont msg"><i class="iconfont dco">&#xe602;</i></i><a data-html="message_center" href="javascript:;">消息中心</a></li>
			</ul>
			<div class="service">
				<i class="iconfont">&#xe730;</i>
			<p>电话：400-690-9898<br>我们将诚挚为您服务</p>
	</div>
</div>
