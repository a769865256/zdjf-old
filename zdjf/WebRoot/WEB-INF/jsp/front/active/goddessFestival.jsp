<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>正道金服</title>
    <!-- reset/iconfont -->
    <link rel="stylesheet" href="<%=path %>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path %>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path %>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path %>/css/front/index.css">
    <link rel="stylesheet" href="<%=path %>/css/front/annualBonus.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->
<div class="goddessFestival">
    <div class="goddessFestival_banner"></div>
    <div class="goddessFestival_centent1">
        <div class="btnsBox">
            <div class="btns">
                <c:if test="${empty user}"><a href="<%=path%>/toLogin.action" class="ljlq"></a></c:if>
                <!-- 立即领取 -->
                <c:if test="${giftFlag == 1}"><a href="javascript:" class="ljlq lqhb"></a></c:if>
                <!-- 已领取 -->
                <c:if test="${giftFlag == 2}"><a href="javascript:" class="ylq layui-disabled"></a></c:if>
            </div>
            <div class="btns">
                <!-- 立即领取 -->
                <c:if test="${empty user}"><a href="<%=path%>/toLogin.action" class="ljlq"></a></c:if>
                <c:if test="${couponFlag == 'a'}"><a href="javascript:" class="ljlq lqjxq"></a></c:if>
                <!-- 已领取 -->
                <c:if test="${couponFlag == 'b'}"><a href="javascript:" class="ylq layui-disabled"></a></c:if>
                <!-- 未满足条件 -->
                <c:if test="${couponFlag == 'c'}"><a href="javascript:" class="no_mztj layui-disabled"></a></c:if>
            </div>
        </div>
    </div>
    <div class="goddessFestival_centent2"></div>
    <div class="goddessFestival_centent3">
        <ul>
            <li>
                <div class="ranking sred">1</div>
                <div class="phoneNumber sred">${investList[0].phone}</div>
                <div class="money pink">¥${investList[0].amt}</div>
            </li>
            <li>
                <div class="ranking sred">2</div>
                <div class="phoneNumber sred">${investList[1].phone}</div>
                <div class="money pink">¥${investList[1].amt}</div>
            </li>
            <li>
                <div class="ranking sred">3</div>
                <div class="phoneNumber sred">${investList[2].phone}</div>
                <div class="money pink">¥${investList[2].amt}</div>
            </li>
            <li>
                <div class="ranking sred">4</div>
                <div class="phoneNumber sred">${investList[3].phone}</div>
                <div class="money pink">¥${investList[3].amt}</div>
            </li>
            <li>
                <div class="ranking sred">5</div>
                <div class="phoneNumber sred">${investList[4].phone}</div>
                <div class="money pink">¥${investList[4].amt}</div>
            </li>
            <li>
                <div class="ranking gay">6</div>
                <div class="phoneNumber gay">${investList[5].phone}</div>
                <div class="money gay">¥${investList[5].amt}</div>
            </li>
            <li>
                <div class="ranking gay">7</div>
                <div class="phoneNumber gay">${investList[6].phone}</div>
                <div class="money gay">¥${investList[6].amt}</div>
            </li>
            <li>
                <div class="ranking gay">8</div>
                <div class="phoneNumber gay">${investList[7].phone}</div>
                <div class="money gay">¥${investList[7].amt}</div>
            </li>
            <li>
                <div class="ranking gay">9</div>
                <div class="phoneNumber gay">${investList[8].phone}</div>
                <div class="money gay">¥${investList[8].amt}</div>
            </li>
            <li>
                <div class="ranking gay">10</div>
                <div class="phoneNumber gay">${investList[9].phone}</div>
                <div class="money gay">¥${investList[9].amt}</div>
            </li>
        </ul>
    </div>
    <div class="goddessFestival_bottom"></div>
</div>

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script type="text/javascript" src="<%=path %>/module/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/module/sticky-header.js"></script>
<script type="text/javascript" src="<%=path %>/module/layui/layui.js"></script>
<script type="text/javascript">
    $('.header').stickMe({
        topOffset:100
    });
    var path = '<%=path%>';
    layui.use(['layer','laydate','form','laypage'], function() {
    	/* 领取红包 */
        $(".lqhb").click(function(){
        	var that = $(this);
            goddessActivityAjax('/activity/getGoddessActivityGift.action',1,1,that,'ljlq lqhb','ylq layui-disabled');
            $(".lqhb").unbind("click");
        });
        /* 领取加息券 */
        $(".lqjxq").click(function(){
        	var that = $(this);
            goddessActivityAjax('/activity/getGoddessActivityGift.action',1,2,that,'ljlq lqjxq','ylq layui-disabled');
            $(".lqjxq").unbind("click");
        });
        function goddessActivityAjax(url,data_reg_source,dataType,that,thisRemoveClass,thisAddClass){
        	$.ajax({
                type: 'post',
                url: path + url,
                data:{
                    reg_source: data_reg_source,
                    type: dataType
                },
                success:function(data){
                    if(data.status == 100){
                        that.removeClass(thisRemoveClass).addClass(thisAddClass);
                        layer.msg(data.content, {
                            icon: 1,
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        });
                    } else {
                        layer.msg(data.content, {
                            icon: 2,
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        });
                    }
                },
                error: function() {
                    layer.msg('请求错误！', {
                        icon: 2,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    });
                }
            });
        }
    });
</script>
</body>
</html>
