<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>正道金服</title>
    <!-- reset/iconfont -->
    <link rel="stylesheet" href="<%=path%>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path%>/css/front/index.css">
    <link rel="stylesheet" href="<%=path%>/css/front/annualBonus.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->
<div class="springFestival">
    <div class="springFestival_banner"></div>
    <div class="springFestival_middle">
        <div class="springFestival_middle_box">
            <div class="springFestival_centent1"></div>
            <div class="springFestival_centent2">
                <c:if test="${flag == 3}"><div class="btn lijidenglu"></div></c:if>
                <c:if test="${flag == 0}"><div class="btn lijilingqu"></div></c:if>
                <c:if test="${flag == 2}"><div class="btn lijitouzi"></div></c:if>
                <c:if test="${flag == 1}"><div class="btn yilingqu"></div></c:if>
            </div>
            <div class="springFestival_centent3">
                <ul>
                    <li class="li1">
                        <div class="first"><i></i></div>
                        <div class="phoneNub">${investList[0].phone}</div>
                        <div class="money">￥${investList[0].amt}</div>
                    </li>
                    <li class="li2">
                        <div class="sce"><i></i></div>
                        <div class="phoneNub">${investList[1].phone}</div>
                        <div class="money">￥${investList[1].amt}</div>
                    </li>
                    <li class="li1">
                        <div class="three"><i></i></div>
                        <div class="phoneNub">${investList[2].phone}</div>
                        <div class="money">￥${investList[2].amt}</div>
                    </li>
                    <li class="li2">
                        <div>4</div>
                        <div class="phoneNub">${investList[3].phone}</div>
                        <div class="money">￥${investList[3].amt}</div>
                    </li>
                    <li class="li1">
                        <div>5</div>
                        <div class="phoneNub">${investList[4].phone}</div>
                        <div class="money">￥${investList[4].amt}</div>
                    </li>
                    <li class="li2">
                        <div>6</div>
                        <div class="phoneNub">${investList[5].phone}</div>
                        <div class="money">￥${investList[5].amt}</div>
                    </li>
                    <li class="li1">
                        <div>7</div>
                        <div class="phoneNub">${investList[6].phone}</div>
                        <div class="money">￥${investList[6].amt}</div>
                    </li>
                    <li class="li2">
                        <div>8</div>
                        <div class="phoneNub">${investList[7].phone}</div>
                        <div class="money">￥${investList[7].amt}</div>
                    </li>
                    <li class="li1">
                        <div>9</div>
                        <div class="phoneNub">${investList[8].phone}</div>
                        <div class="money">￥${investList[8].amt}</div>
                    </li>
                    <li class="li2">
                        <div>10</div>
                        <div class="phoneNub">${investList[9].phone}</div>
                        <div class="money">￥${investList[9].amt}</div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="springFestival_bottom">
        <div class="springFestival_bottom_box">
            <div class="bottom"></div>
        </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script type="text/javascript" src="<%=path%>/module/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/module/sticky-header.js"></script>
<script type="text/javascript" src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript">
    var path = '<%=path%>';
    $('.header').stickMe({
        topOffset:100
    });
    
    layui.use(['layer','laydate','form','laypage'], function() {
        /*登录*/
        $(".lijidenglu").click(function(){
            window.location.href = path + '/toLogin.action';
        });
        /*立即投资*/
        $(".lijitouzi").click(function(){
            window.location.href = path + '/product/list.action';
        });
        /*立即领取*/
        $(".lijilingqu").click(function(){
        	var bFlag = true;
        	if(bFlag) {
	            $.ajax({
	                type: 'post',
	                url: path + '/activity/getNewYearCoupon.action?reg_source=1',
	                success:function(data){
	                    if(data.status == 100){
	                        layer.msg(data.content, {
	                            icon: 1,
	                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
	                        });
	                        $(".btn").removeClass("lijilingqu").addClass("yilingqu");
	                        bFlag = false;
	                        return false;
	                    } else {
	                        layer.msg(data.content, {
	                            icon: 2,
	                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
	                        });
	                    }
	                },
	                error: function(data) {
	                    layer.msg(data.content, {
	                        icon: 2,
	                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
	                    });
	                }
	            });
        	}
        });
    })
    

</script>
</body>
</html>