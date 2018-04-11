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
    <link rel="stylesheet" href="<%=path%>/module/swiper/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="<%=path%>/module/swiper/animate.min.css">
    <link rel="stylesheet" href="<%=path%>/css/front/index.css">
    <link rel="stylesheet" href="<%=path%>/css/front/sbnbg_2017.css">
</head>
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->
<div class="swiper-container">
    <div class="swiper-wrapper">
        <!-- 第一个 -->
        <div class="swiper-slide">
            <div class="yybg_1">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.8s" swiper-animate-delay="0.5s";></div>
                <p class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"><img src="<%=path%>/images/front/img/informData/jdyybg_2017/2017.png" alt=""></p>
                <p class="ani" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"><img src="<%=path%>/images/front/img/informData/jdyybg_2017/tlt.png" alt=""></p>
            </div>
        </div>
        <!-- 第二个 -->
        <div class="swiper-slide">
            <div class="yybg_2">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.8s" swiper-animate-delay="0.5s";></div>
                <ul>
                    <li>
                        <i class="jinbi ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"></i>
                        <span class="jinbinum ani" swiper-animate-effect="slideInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"></span>
                    </li>
                    <li>
                        <i class="name ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"></i>
                        <span class="namenum ani" swiper-animate-effect="slideInRight" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"></span>
                    </li>
                    <li>
                        <i class="pig ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"></i>
                        <span class="pignum ani" swiper-animate-effect="slideInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"></span>
                    </li>
                    <li>
                        <i class="nopad ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"></i>
                        <span class="nopadnum ani" swiper-animate-effect="slideInRight" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"></span>
                    </li>
                </ul>

            </div>
        </div>
        <!-- 第三个 -->
        <div class="swiper-slide">
            <div class="yybg_3">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.8s" swiper-animate-delay="0.5s";></div>
                <ul>
                    <li>
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_748.png" alt="" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/hong1.png" alt="">
                    </li>
                    <li>
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_252.png" alt="" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/lan1.png" alt="">
                    </li>
                    <li>
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_75.png" alt="" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/hong2.png" alt="">
                    </li>
                    <li>
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_154.png" alt="" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/lan2.png" alt="">
                    </li>
                    <li>
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_96.png" alt="" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s">
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/h.png" alt="">
                    </li>
                </ul>
            </div>
        </div>
        <!-- 第四个 -->
        <div class="swiper-slide">
            <div class="yybg_4">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.8s" swiper-animate-delay="0.5s";></div>
            </div>
        </div>
        <!-- 第五个 -->
        <div class="swiper-slide">
            <div class="yybg_5">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="6.5s";></div>
                <ul>
                    <li>
                        <p>
                            <img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_188.png" alt="" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_269.png" alt="" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/qita.png" alt="" class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_90.png" alt="" class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_2996.png" alt="" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="1.4s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_1514.png" alt="" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="1.4s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_3193.png" alt="" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="2.6s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_3754.png" alt="" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="2.6s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_80.png" alt="" class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="2s"/></p></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_70.png" alt="" class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="3.2s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_1751.png" alt="" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="3.8s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_2148.png" alt="" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="3.8s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_1251.png" alt="" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="5s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_1577.png" alt="" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="5s"/></p>
                    </li>
                    <li>
                        <img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_60.png" alt="" class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="4.4s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_50.png" alt="" class="ani" swiper-animate-effect="zoomIn" swiper-animate-duration="0.5s" swiper-animate-delay="5.6s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_621.png" alt="" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="6.2s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/yybg_738.png" alt="" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="6.2s"/></p>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 第六个 -->
        <div class="swiper-slide">
            <div class="yybg_6">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="1.5s";></div>
                <ul>
                    <li>
                        <p>
                            <img src="<%=path%>/images/front/img/informData/jdyybg_2017/syzg1.png" alt="" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/syzg2.png" alt="" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/tzzq1.png" alt="" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/tzzq2.png" alt="" class="ani" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/tzzh1.png" alt="" class="ani" swiper-animate-effect="bounceInDown" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/tzzh2.png" alt="" class="ani" swiper-animate-effect="bounceInUp" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/dbzh1.png" alt="" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="1.4s"/></p>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/dbzh2.png" alt="" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="0.5s" swiper-animate-delay="1.4s"/></p>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 第七个 -->
        <div class="swiper-slide">
            <div class="yybg_7">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="1.7s";></div>
                <ul>
                    <li>
                        <p>
                            <img src="<%=path%>/images/front/img/informData/jdyybg_2017/shuangdan.png" alt="" class="ani" swiper-animate-effect="flipInY" swiper-animate-duration="0.5s" swiper-animate-delay="0.3s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/shuang11.png" alt="" class="ani" swiper-animate-effect="flipInY" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/8yuehuodong.png" alt="" class="ani" swiper-animate-effect="flipInY" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/zhongqiu.png" alt="" class="ani" swiper-animate-effect="flipInY" swiper-animate-duration="0.5s" swiper-animate-delay="1.1s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/sign.png" alt="" class="ani" swiper-animate-effect="flipInY" swiper-animate-duration="0.5s" swiper-animate-delay="1.4s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/huafeihongbao.png" alt="" class="ani" swiper-animate-effect="flipInY" swiper-animate-duration="0.5s" swiper-animate-delay="1.7s"/></p>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 第八个 -->
        <div class="swiper-slide">
            <div class="yybg_8">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="3.8s";></div>
                <ul>
                    <li>
                        <p>
                            <img src="<%=path%>/images/front/img/informData/jdyybg_2017/2017126.png" alt="" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="3.8s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/7_1.png" alt="" class="ani" swiper-animate-effect="fadeInRightBig" swiper-animate-duration="0.5s" swiper-animate-delay="4s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/20171122.png" alt="" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="3.3s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/7_2.png" alt="" class="ani" swiper-animate-effect="fadeInRightBig" swiper-animate-duration="0.5s" swiper-animate-delay="3.5s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/20171029.png" alt="" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="2.8s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/7_3.png" alt="" class="ani" swiper-animate-effect="fadeInRightBig" swiper-animate-duration="0.5s" swiper-animate-delay="3s"/></p>
                    </li>
                    <li>
                        <p>
                            <img src="<%=path%>/images/front/img/informData/jdyybg_2017/2017921.png" alt="" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="2.3s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/7_4.png" alt="" class="ani" swiper-animate-effect="fadeInRightBig" swiper-animate-duration="0.5s" swiper-animate-delay="2.5s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/2017819.png" alt="" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="1.8s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/7_5.png" alt="" class="ani" swiper-animate-effect="fadeInRightBig" swiper-animate-duration="0.5s" swiper-animate-delay="2s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/2017628.png" alt="" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="1.3s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/7_6.png" alt="" class="ani" swiper-animate-effect="fadeInRightBig" swiper-animate-duration="0.5s" swiper-animate-delay="1.5s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/2017428.png" alt="" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.8s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/7_7.png" alt="" class="ani" swiper-animate-effect="fadeInRightBig" swiper-animate-duration="0.5s" swiper-animate-delay="1s"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/2017124.png" alt="" class="ani" swiper-animate-effect="fadeIn" swiper-animate-duration="0.5s" swiper-animate-delay="0.3"/></p>
                    </li>
                    <li>
                        <p><img src="<%=path%>/images/front/img/informData/jdyybg_2017/7_8.png" alt="" class="ani" swiper-animate-effect="fadeInRightBig" swiper-animate-duration="0.5s" swiper-animate-delay="0.5s"/></p>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 第九个 -->
        <div class="swiper-slide">
            <div class="yybg_9">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0.8s";></div>
            </div>
        </div>
        <!-- 第十个 -->
        <div class="swiper-slide">
            <div class="yybg_10">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0.8s";></div>
            </div>
        </div>
        <!-- 第十一个 -->
        <div class="swiper-slide">
            <div class="yybg_11">
                <div class="xingdian ani" swiper-animate-effect="fadeIn" swiper-animate-duration="1s" swiper-animate-delay="0.8s";></div>
                <ul>
                    <li><img src="<%=path%>/images/front/img/footer/code1.png" alt="" class="ani" swiper-animate-effect="fadeInLeft" swiper-animate-duration="1s" swiper-animate-delay="0.3s"/></li>
                    <li><img src="<%=path%>/images/front/img/footer/code2.png" alt="" class="ani" swiper-animate-effect="fadeInRight" swiper-animate-duration="1s" swiper-animate-delay="0.3s"/></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="swiper-button-prev"></div>
    <div class="swiper-button-next"></div>
</div>
<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path%>/module/jquery/jquery.min.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script src="<%=path%>/module/swiper/swiper-3.4.2.jquery.min.js"></script>
<script src="<%=path%>/module/swiper/swiper.animate1.0.2.min.js"></script>
<script language="javascript">
    var mySwiper = new Swiper('.swiper-container',{
        loop : true,
        direction : 'horizontal',
        prevButton:'.swiper-button-prev',
        nextButton:'.swiper-button-next',
        observer:true,
        observeParents:true,
        onInit: function(swiper) {
            swiper.update();
            swiper.onResize();
            swiperAnimateCache(swiper); //隐藏动画元素 
            swiperAnimate(swiper); //初始化完成开始动画
        },
        onSlideChangeEnd: function(swiper) {
            swiperAnimate(swiper); //每个slide切换结束时也运行当前slide动画
            if (swiper.activeIndex == 11||swiper.activeIndex == 0) {
                swiper.nextButton.css({
                    "width":"102px",
                    "height":"98px",
                    "background":"url(<%=path%>/images/front/img/informData/jdyybg_2017/backSY.png) center no-repeat",
                    "right":"188px",
                });
            } else {
                swiper.nextButton.removeAttr("style");
            }
            if (swiper.activeIndex == 1 || swiper.activeIndex == 12) {
                swiper.prevButton.hide();
            } else {
                swiper.prevButton.show();
            }
        }
    });

</script>
</body>
</html>
