<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Nav -->
<%--<div class="site-nav">
	<div class="site-navlayout">
		<div class="nav-left">
			<span>客服热线 : 400-690-9898 </span> &lt;%&ndash; <a href="${selfSite}/zdjf"
				target="_blank">下载手机APP</a> &ndash;%&gt;
		</div>
		<div class="nav-right hide">
			<div class="user hide">
				您好， <a href="${selfSite}/zdjf/center" class="borderN phone Jphone"></a> <a
					href="${selfSite}/zdjf/user/loginout" class="borderN loginout nobord"
					title="安全退出">安全退出</a>
			</div>
			<div class="help tologin ">
				<a href="${selfSite}/zdjf/user/login" class="borderN loginout nobord"
					title="立即登录">立即登录</a> <a href="${selfSite}/zdjf/user/register"
					class="borderN loginout register" title="免费注册">免费注册</a>
			</div>
			<div class="help">
				<a href="${selfSite}/zdjf/q&a" class="borderN loginout" title="常见问题">常见问题</a>
			</div>
			<div class="help">
				&lt;%&ndash; <a href="${selfSite}/zdjf" class="borderN loginout" title="帮助中心">帮助中心</a> &ndash;%&gt;
				<a href="${selfSite}/zdjf/guidance" class="new borderN loginout hover-other register" title="新手指引">
					<img src="${selfSite}/zdjf/res/comm/images/public/new-icon.gif" alt=""></a>
			</div>
		</div>
	</div>
</div>

<!-- header -->
<div class="header">
	<div class="header-layout">
		<a href="${selfSite}/zdjf" class="header-logo-sx"></a>
		<div class="header-nav">
			<ul>
				<li <c:if test="${Nav == 1}">class="curr"</c:if>><a
					href="${selfSite}/zdjf">首页</a></li>
				<li <c:if test="${Nav == 2}">class="curr"</c:if>><a
					href="${selfSite}/zdjf/product/list">理财项目</a></li>
				<li <c:if test="${Nav == 4}">class="curr"</c:if>><a
					href="${selfSite}/zdjf/baozhang">安全保障</a></li>
				<li <c:if test="${Nav == 5}">class="curr"</c:if>><a
					href="${selfSite}/zdjf/about">关于我们</a></li>
				<li class="curr-box"><a
						href="${selfSite}/zdjf/center">我的财富</a></li>
			</ul>
		</div>
	</div>
</div>--%>

<!-- nav -->
<div class="site-nav">
    <div class="site-navlayout">
        <div class="nav-left">
            <span><span class="iconfont">&#xe60a;</span>客服热线 : 400-690-9898 </span>
            <a href="http://android.myapp.com/myapp/detail.htm?apkName=com.hz.zdjfu.application" target="_blank"><span class="iconfont">&#xe609;</span>下载手机客户端<i class="ap-code"><i><i></i></i></i></a>
        </div>
        <div class="nav-right">
            <div class="user hide">
                您好， 
                <a href="/center" class="phone J_phone">--</a>
                <a href="/center/remind" class="nav-info hides" id="navMsgCount">消息<i>--</i></a>
                <a href="/user/loginout" class="J_loginOut" title="安全退出">安全退出</a>
            </div>
            <div class="help tologin">
                <a href="/user/login" title="立即登录">立即登录</a>
                <a href="/user/register" title="免费注册">免费注册</a>
            </div>
            <div class="help">
                <a href="/active/qd-sign.html?re=pc" title="立即签到">立即签到</a>
            </div>
            <div class="help">
                <a href="/about/qa01.html" title="常见问题">常见问题</a>
                <a href="/about/guidance.html" class="nobord"><i class="iconfont">&#xe63d;</i>新手引导</a>
            </div>
        </div>
    </div>
</div>
<!-- header -->
<div class="header">
    <div class="header-layout">
        <a href="/" class="header-logo-sx"><img src="zdjf/index/images/public/logo.png" alt=""></a>
        <span class="slogan"></span>
        <div class="header-nav">
            <ul>
                <li><a href="/">首页</a></li>
                <li><a href="/product/search.html">理财项目</a></li>
                <li><a href="/about/safe.html">安全保障</a></li>
                <li class="pulldown">
                    <a href="/about/about.html">关于我们<i class="arrow"></i></a>
                    <div class="about-list">
                        <a href="/about/about.html" class="normal">关于我们<i class="iconfont">&#xe606;</i></a>
                        <a href="/about/about.html">平台简介</a>
                        <!--<a href="/about/team.html">团队介绍</a>-->
                        <a href="/about/risk-control.html" class="navRiskControl">风控体系</a>
                        <a href="/about/classroom.html" class="navClassroom">投资讲堂</a>
                        <a href="/about/media.html">媒体报道</a>
                        <a href="/about/contact.html">联系我们</a>
                    </div>
                </li>
                <li class="center curr"><a href="/center">我的财富</a></li>
            </ul>
        </div>
    </div>
</div>