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
<div class="annualBonus">
    <div class="annualBonus_banner"></div>
    <div class="annualBonus_middle">
        <div class="annualBonus_middle_box">
            <div class="baiyin"></div>
            <div class="glod">
                <div class="hbBox">
                    <div class="first_hb hb">
                        <!-- 红包 -->
                        <div class="first_hb_div">
                            <div class="hb1 centent">
                                <img src="<%=path%>/images/front/img/active/annualBonus/hb.png" alt="">
                                <div class="left">起投5000元<br>时长≥30天</div>
                                <div class="rmb">¥50</div>
                            </div>
                            <div class="hb2 centent" style="display: none;">
                                <img src="<%=path%>/images/front/img/active/annualBonus/hb.png" alt="">
                                <div class="left">起投5000元<br>时长≥50天</div>
                                <div class="rmb">¥118</div>
                            </div>
                            <div class="hb3 centent" style="display: none;">
                                <img src="<%=path%>/images/front/img/active/annualBonus/hb.png" alt="">
                                <div class="left">起投5000元<br>时长≥80天</div>
                                <div class="rmb">¥188</div>
                            </div>
                        </div>
                        <!-- 按钮 -->
                        <div class="btnBox1">
                            <div class="btn1 btn active">￥50</div>
                            <div class="btn2 btn">￥118</div>
                            <div class="btn3 btn">￥188</div>
                        </div>
                    </div>
                    <div class="second_hb hb">
                        <!-- 红包 -->
                        <div class="second_hb_div">
                            <div class="hb1 centent">
                                <img src="<%=path%>/images/front/img/active/annualBonus/hb.png" alt="">
                                <div class="left">起投50000元<br>时长≥30天</div>
                                <div class="rmb">￥500</div>
                            </div>
                            <div class="hb2 centent" style="display: none;">
                                <img src="<%=path%>/images/front/img/active/annualBonus/hb.png" alt="">
                                <div class="left">起投50000元<br>时长≥50天</div>
                                <div class="rmb">￥1180</div>
                            </div>
                            <div class="hb3 centent" style="display: none;">
                                <img src="<%=path%>/images/front/img/active/annualBonus/hb.png" alt="">
                                <div class="left">起投50000元<br>时长≥80天</div>
                                <div class="rmb">￥1880</div>
                            </div>
                        </div>
                        <!-- 按钮 -->
                        <div class="btnBox2">
                            <div class="btn1 btn active">￥500</div>
                            <div class="btn2 btn">￥1180</div>
                            <div class="btn3 btn">￥1880</div>
                        </div>
                    </div>
                    <div class="third_hb hb">
                        <!-- 红包 -->
                        <div class="third_hb_div">
                            <div class="hb1 centent">
                                <img src="<%=path%>/images/front/img/active/annualBonus/hb.png" alt="">
                                <div class="left">起投100000元<br>时长≥30天</div>
                                <div class="rmb">￥1160</div>
                            </div>
                            <div class="hb2 centent" style="display: none;">
                                <img src="<%=path%>/images/front/img/active/annualBonus/hb.png" alt="">
                                <div class="left">起投100000元<br>时长≥50天</div>
                                <div class="rmb">￥2360</div>
                            </div>
                            <div class="hb3 centent" style="display: none;">
                                <img src="<%=path%>/images/front/img/active/annualBonus/hb.png" alt="">
                                <div class="left">起投100000元<br>时长≥80天</div>
                                <div class="rmb">￥3960</div>
                            </div>
                        </div>
                        <!-- 按钮 -->
                        <div class="btnBox3">
                            <div class="btn1 btn active">￥1160</div>
                            <div class="btn2 btn">￥2360</div>
                            <div class="btn3 btn">￥3960</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="zuanshi"></div>
        </div>
    </div>
    <div class="annualBonus_bottom">
        <div class="annualBonus_bottom_box">
            <div class="bottom"></div>
        </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->

<script type="text/javascript" src="<%=path%>/module/jquery/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        /*所有已投资用户点击*/
        $(".btnBox1 .btn").on("click",function(){
            var index = $(this).index();
            $(this).addClass("active").siblings().removeClass("active");
            $(".first_hb_div .centent").eq(index).show().siblings().hide();
        });
        /*历史累计投资满10万用户点击*/
        $(".btnBox2 .btn").on("click",function(){
            var index = $(this).index();
            $(this).addClass("active").siblings().removeClass("active");
            $(".second_hb_div .centent").eq(index).show().siblings().hide();
        });
        /*历史累计投资满30万用户点击*/
        $(".btnBox3 .btn").on("click",function(){
            var index = $(this).index();
            $(this).addClass("active").siblings().removeClass("active");
            $(".third_hb_div .centent").eq(index).show().siblings().hide();
        });
    });
</script>
</body>
</html>
