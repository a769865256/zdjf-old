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
    <link rel="stylesheet" href="<%=path %>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path %>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path %>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path %>/module/swiper/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="<%=path %>/css/front/index.css">
    <link rel="stylesheet" href="<%=path %>/css/front/sbnbg_2017.css">
</head>
</head>
<body>

<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<div class="sbnbg_2017_warp">
    <div class="swiper-container" id="sbnbg_2017">
        <div class="sbnbg_2017_Box swiper-wrapper" id="content">
            <!-- 轮播图 -->
            <div class="list_div bg1 swiper-slide"></div>
            <div class="list_div bg2 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="tlt">截至2017年6月30日</p>
                        <ul class="msg top_msg">
                            <li class="msg_tlt"><h3>平台累计投资金额(元)</h3><h3>平台累计注册用户(人)</h3></li>
                            <li class="data"><h2>3,607,516,9</h2><h2>28531</h2></li>
                        </ul>
                        <ul class="msg bottom_msg">
                            <li class="msg_tlt"><h3>平台累计为用户赚取收益(元)</h3><h3>平台累计交易笔数(笔)</h3></li>
                            <li class="data"><h2>1,970,34.18</h2><h2>1,058,9</h2></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="list_div bg3 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">平台数据</p>
                        <p class="Subtitle">— 2017年上半年</p>
                        <div class="pieChart_tlt">
                            <p class="pieChart_tlt1">2017年上半年成交额</p>
                            <p class="pieChart_tlt2">项目期限分布</p>
                        </div>
                        <div class="pieChart">
                            <div id="pieChart_left" style="width:300px;height:300px"></div>
                            <div id="pieChart_right" style="width:300px;height:300px"></div>
                        </div>
                        <div class="msg_box">
                            <ul class="msg left_msg">
                                <li class="msg_app">58.9%</li>
                                <li class="msg_pc">41.1%</li>
                            </ul>
                            <ul class="msg center_msg">
                                <li class="msg_tlt"><h2>69.28%</h2><h3>0-1个月</h3></li>
                                <li class="data"><h2>10.36%</h2><h3>2-3个月</h3></li>
                            </ul>
                            <ul class="msg right_msg">
                                <li class="msg_tlt"><h2>18.58%</h2><h3>1-2个月</h3></li>
                                <li class="data"><h2>1.78%</h2><h3>3-6个月</h3></li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
            <div class="list_div bg4 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">用户数据</p>
                        <p class="Subtitle">— 2017年上半年</p>
                        <div class="msg_box">
                            <ul class="msg left_msg">
                                <li class="data"><h2>56.53%</h2></li>
                                <li class="data"><h2>43.47%</h2></li>
                            </ul>
                            <ul class="msg right_msg">
                                <li class="data"><h2>54.70%</h2></li>
                                <li class="data"><h2>45.30%</h2></li>
                            </ul>
                        </div>
                        <div class="barGraphBox">
                            <div id="barGraph" style="width:850px;height:400px;margin: 0 auto;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list_div bg5 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">人气活动</p>
                        <p class="Subtitle">— 2017年上半年</p>
                        <div id="focus_Box">
                            <div class="iSlider-wrapper">
                                <div class="swiper-wrapper">  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;">
                                            <img src="<%=path %>/images/front/img/informData/PlatformReport_2017/yybgb5_ewm.png" alt="">
                                        </a>
                                        <p>
                                            <span>关注微信送红包 2017年6月26起</span>
                                        </p>
                                    </div>  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;">
                                            <img src="<%=path %>/images/front/img/informData/PlatformReport_2017/yybg5_hb.png" alt="">
                                        </a>
                                        <p>
                                            <span>春节话费红包大派送 2017.1.18-2017.2.10</span>
                                        </p> 
                                    </div>  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;">
                                            <img src="<%=path %>/images/front/img/informData/PlatformReport_2017/yybg5_newhand.png" alt="">
                                        </a>
                                        <p>
                                            <span>新手专享3重礼 2017年6月16起</span>
                                        </p>
                                    </div>  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;">
                                            <img src="<%=path %>/images/front/img/informData/PlatformReport_2017/yybg5_GoddessFestival.png" alt="">
                                        </a>
                                        <p>
                                            <span>女神节，红包加息无限送 2017.3.7-2017.3.9</span>
                                        </p>
                                    </div>
                                    <div class="swiper-slide">  
                                        <a href="javascript:;">
                                            <img src="<%=path %>/images/front/img/informData/PlatformReport_2017/yybg5_AprilFoolsDay.png" alt="">
                                        </a>
                                        <p>
                                            <span>乐活愉人节，红包加息任性送 2017.4.1-2017.4.5</span>
                                        </p>   
                                    </div>  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;">
                                            <img src="<%=path %>/images/front/img/informData/PlatformReport_2017/yybg5_HappyAWheel.png" alt="">
                                        </a>
                                        <p>
                                            <span>喜庆A轮，欢乐“送”红包 2017.4.29-2017.5.10</span>
                                        </p>
                                    </div> 
                                    <div class="swiper-slide">  
                                        <a href="javascript:;">
                                            <img src="<%=path %>/images/front/img/informData/PlatformReport_2017/yybg5_Sign.png" alt="">
                                        </a>
                                        <p>
                                            <span>每日一签，好运相伴 2017.5.18-2017.6.30</span>
                                        </p>
                                    </div> 
                                    <div class="swiper-slide">  
                                       <a href="javascript:;">
                                            <img src="<%=path %>/images/front/img/informData/PlatformReport_2017/yybg5_weekend.png" alt="">
                                        </a>
                                        <p>
                                            <span>这个周末有约会 2017.5.19-2017.6.18</span>
                                        </p>
                                    </div> 
                                    <div class="swiper-slide">  
                                       <a href="javascript:;">
                                            <img src="<%=path %>/images/front/img/informData/PlatformReport_2017/yybg5_signCarnival.png" alt="">
                                        </a>
                                        <p>
                                            <span>签到狂欢月，红包送不停 2017.6.10-2017.6.30</span>
                                        </p>
                                    </div> 
                                </div>  
                                <div class="swiper-button-next"></div>  
                                <div class="swiper-button-prev"></div>  
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list_div bg6 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">平台大事记</p>
                        <p class="Subtitle">— 2017年上半年</p>
                    </div>
                </div>
            </div>


            <div class="list_div bg7 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">媒体报道精选</p>
                        <p class="Subtitle">— 2017年上半年</p>
                        <ul class="top">
                            <li>
                                <p>2017/1/16</p>
                                <h3>《创新与专业 正道金服引领互联网金融新型理财》</h3>
                            </li>
                            <li>
                                <p>2017/2/10</p>
                                <h3>《正道金服携手阿里云 预防黑客攻击技术是关键》</h3>
                            </li>
                            <li>
                                <p>2017/3/9</p>
                                <h3>《正道金服：解决资金监管的新思路》</h3>
                            </li>
                        </ul>
                        <ul class="bottom">
                            <li>
                                <p>2017/4/1</p>
                                <h3>《正道金服：解决资金监管的新思路》</h3>
                            </li>
                            <li>
                                <p>2017/5/2</p>
                                <h3>《正道金服：解决资金监管的新思路》</h3>
                            </li>
                            <li>
                                <p>2017/6/1</p>
                                <h3>《正道金服：解决资金监管的新思路》</h3>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>


            <div class="list_div bg8 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">关注我们</p>
                        <div class="ewm">
                            <img src="<%=path %>/images/front/img/footer/code1.png" alt="">
                            <img src="<%=path %>/images/front/img/footer/code2.png" alt="">
                        </div>
                    </div>
                </div>
            </div>
            <!-- end -->
        </div>
        <div class="swiper-pagination swiper-pagination-h"></div>
    </div>
</div>

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->

<script src="<%=path %>/module/jquery/jquery.min.js"></script>
<script src="<%=path %>/module/layui/layui.js"></script>
<script src="<%=path %>/module/swiper/swiper-3.4.2.jquery.min.js"></script>
<script src="<%=path %>/module/echarts/highstock.js"></script>
<script src="<%=path %>/module/echarts/highcharts-3d.js"></script>
<script src="<%=path %>/js/front/sbnbg_2017.js"></script>
</body>
</html>