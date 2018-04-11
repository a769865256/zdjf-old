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
    <title>新手专享</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/module/layui/css/layui.mobile.css"/>
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/style.css"/>
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/index.css"/>
    <!-- 公共样式end -->
    <link rel="stylesheet" href="<%=path%>/static/zdjf_app/static/css/welfare.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<%--<div class="header"><a class="back" href="<%=path%>/static/zdjf_app/index.html"></a>新人福利</div>--%>
<div class="walfare">
    <%--菜鸟先飞，一鸣惊人,鹏程万里判断用户是否登录，未登录全部显示立即注册，不添加任何类名.添加a标签，跳转到app注册页面--%>
    <%--已登录：菜鸟先飞添加acitve类名  一鸣惊人和鹏程万里判断是否达到获得奖励条件，是：添加active类名，否：添加invest类名--%>
    <%--一鸣惊人去除还需投资多少提示，用户单次达到1000元就可以获得奖励（添加active类名）--%>
    <img src="<%=path%>/static/zdjf_app/static/img/active/xr1.jpg" alt="">
    <div <c:if test="${empty user}">class="cnxf"</c:if><c:if test="${not empty user}">class="cnxf active"</c:if>>
    </div>
    <img src="<%=path%>/static/zdjf_app/static/img/active/xr3.jpg" alt="">
    <c:if test="${empty user}">
        <div class="ymjr">
            <%--<div class="invest_need">还需投资<span>1000</span>元</div>--%>
        </div>
    </c:if>
    <c:if test="${not empty user && investFlag == 0}">
    <div class="ymjr invest">
        <%--<div class="invest_need">还需投资<span>1000</span>元</div>--%>
    </div>
    </c:if>
    <c:if test="${not empty user && investFlag == 1}">
        <div class="ymjr active"></div>
    </c:if>
    <!--如果已达到获得奖励条件，添加active类名-->
    <img src="<%=path%>/static/zdjf_app/static/img/active/xr5.jpg" alt="">
    <c:if test="${empty user}">
        <div class="pcwl">
            <%--<c:if test="${empty user}"><a href="<%=path%>/static/zdjf_app/page/login/reg.html"></a></c:if>--%>
            <%--<div class="invest_need">还需投资<span>10000</span>元</div>--%>
        </div>
    </c:if>
    <c:if test="${not empty user && empty totalInvest}">
        <div class="pcwl invest">
            <div class="invest_need">还需投资<span>10000</span>元</div>
        </div>
    </c:if>
    <c:if test="${not empty user && totalInvest < 10000}">
        <div class="pcwl invest">
            <div class="invest_need">还需投资<span>${10000-totalInvest}</span>元</div>
        </div>
    </c:if>
    <c:if test="${not empty user && totalInvest >= 10000}">
        <div class="pcwl active"></div>
    </c:if>
    <img src="<%=path%>/static/zdjf_app/static/img/active/xr7.jpg" alt="" style="margin-top: -1px;">
    <div class="rule_active"></div>
</div>
</body>
<script>
    (function(){
        var _w,_zoom,_hd, _orientationChange,_doc = document,__style = _doc.getElementById("_zoom");
        __style || (_hd = _doc.getElementsByTagName("head")[0],__style=_doc.createElement("style"),_hd.appendChild(_style));
        _orientationChange = function(){
            _w    = _doc.documentElement.clientWidth || _doc.body.clientWidth;
            _zoom = _w / 750;
            __style.innerHTML = ".zoom {zoom:" + _zoom + ";-webkit-text-size-adjust:auto !important;text-size-adjust:auto !important;}";
        };
        __style.setAttribute("zoom",_zoom);
        _orientationChange();
        window.addEventListener("resize",_orientationChange,false);
    })();
</script>
<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/jquery.md5.js"></script>
<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/public.js"></script>
<script type="text/javascript" src="<%=path%>/static/zdjf_app/static/js/common.js"></script>
<script>
    // 控制活动规则显示隐藏
    $('.rule_active').click(function () {
        if($(this).hasClass('show')){
            $(this).removeClass('show');
        }else{
            $(this).addClass('show');
        }
    })
    <%--菜鸟先飞，一鸣惊人,鹏程万里点击跳转页面--%>
    $('.cnxf,.ymjr,.pcwl').click(function(){
        if($(this).hasClass('invest')){
            <%--跳转到理财页面--%>
            window.location = 'toInvest';
        }else if(!$(this).hasClass('active')){
            <%--跳转到注册页面--%>
            window.location = 'toRegister';
        }
    })
</script>
</html>
