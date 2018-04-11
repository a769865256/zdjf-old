<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String user_name = "";
	String sname = "";
	String message = "";
	String text = "";
	Cookie myCookie[] = request.getCookies();//创建一个Cookie对象数组
	if (myCookie != null) {
		for (int n = 0; n < myCookie.length; n++) {//设立一个循环，来访问Cookie对象数组的每一个元素
			Cookie newCookie = myCookie[n];
			if (newCookie.getName().equals("user_name")) {
				user_name = newCookie.getValue();
			}
		}
	}

	if ("" != user_name) {
		String name1 = user_name.substring(3, 8);
		sname = user_name.replace(name1, "*****");
	}
	String type = (String) request.getAttribute("showType");
%>

	<div class="nav">
		<div class="pack">
			<span><i class="iconfont">&#xe656;</i>客服电话:400-690-9898</span>
			<div class="p_right">
				<div class="login_ok">
					<c:if test="<%=user_name != text%>">
						<span class="user_info"><%=sname%></span>
					</c:if>
					<c:if test="<%=user_name != text%>">
					<span><a href="<%=path%>/toLogout.action">安全退出</a></span>
					</c:if>
					<c:if test="<%=message != text%>">
						<span>消息(<%=message%>)<i class="iconfont">&#xe602;</i>
						</span>
					</c:if>
            	</div>
				<a href="<%=path%>/downLApp.action" <%if(type!=null && type.equals("downLApp")){ %>class="active"<%} %>>
					<span><i class="iconfont"></i>下载APP</span>
				</a>
				<a href="<%=path%>/help/main.action" <%if(type!=null && type.equals("helpMain")){ %>class="active"<%} %>><span>帮助中心</span></a>
				<a href="<%=path%>/newHand/guide.action" <%if(type!=null && type.equals("newHand")){ %>class="active"<%} %>><span>新手指引</span></a>
			</div>
		</div>
	</div>
	<div class="bar">
		<div class="pack">
			<div class="l_pk"><a href="<%=path%>/"></a></div>
			<div class="r_pk">
				<ul>
					<li><a href="<%=path%>/">首页</a></li>
					<li><a href="<%=path%>/product/list.action">理财项目</a>
						<div class="nav hide">
							<a href="<%=path%>/product/list.action?product_type=1">债转专区</a> <a href="<%=path%>/product/list.action?product_type=2" class="hide">直投专区</a>
						</div>
					</li>
					<li><a href="<%=path%>/platform/data.action">信息披露</a>
						<div class="nav">
							<a href="<%=path%>/platform/data.action" <%if(type!=null && type.equals("data")){ %>class="active"<%} %>>平台数据</a>
							<a href="<%=path%>/insurance.action" <%if(type!=null && type.equals("secure")){ %>class="active"<%} %>>安全保障</a>
							<a href="<%=path%>/company/profile.action" <%if(type!=null && type.equals("profile")){ %>class="active"<%} %>>公司简介</a>
							<a href="<%=path%>/company/things.action" <%if(type!=null && type.equals("things")){ %>class="active"<%} %>>公司事记</a>
							<a href="<%=path%>/company/1/notice.action" <c:if test="${not empty type && type=='notice'}"> class="active" </c:if>>公司动态</a>
							<a href="<%=path%>/contact.action" <%if(type!=null && type.equals("contact")){ %>class="active"<%} %>>联系我们</a>
						</div>
					</li>
					<li><a href="<%=path%>/mywealth.action">我的财富</a>
						<div class="nav nav_wealth">
							<!-- <a href="<%=path%>/mywealth.action">我的财富</a> -->
							<a href="<%=path%>/myFunds.action" <%if(type!=null && type.equals("mycapital")){ %>class="active"<%} %>>资金明细</a>
							<a href="<%=path%>/myProductBuy.action"  <%if(type!=null && type.equals("myconduct")){ %>class="active"<%} %>>我的理财</a>
							<a href="<%=path%>/myCou.action"  <%if(type!=null && type.equals("mydiscount")){ %>class="active"<%} %>>我的优惠</a>
							<a href="<%=path%>/toSetAccount.action"  <%if(type!=null && type.equals("myaccount")){ %>class="active"<%} %>>账户设置</a>
							<a href="javascript:"  <%if(type!=null && type.equals("myinvitation")){ %>class="active"<%} %>>邀请有礼</a>
							<a href="javascript:"  <%if(type!=null && type.equals("mynews")){ %>class="active"<%} %>>消息中心</a>
						</div>
					</li>
				</ul>
				<div class="sign">
					<c:if test="<%=user_name == text%>">
						<a href="<%=path%>/toLogin.action">登录签到</a>
						<a href="<%=path%>/toRegister.action">注册有礼</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=path %>/module/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
        $(".r_pk ul li>a").each(function(){
			if($($(this))[0].href == String(window.location)){
		     	$(this).parent().addClass('active');
			}
		});
		$(".r_pk ul li .nav>a").each(function(){
			if($($(this))[0].href == String(window.location)){
		     	$(this).parent().parent().addClass('active');
			}
		});
	</script>