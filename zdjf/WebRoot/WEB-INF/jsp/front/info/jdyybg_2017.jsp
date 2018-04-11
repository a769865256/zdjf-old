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
    <link rel="stylesheet" href="<%=path %>/css/front/jdyybg_2017.css">
</head>
</head>
<body>

<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<div class="jdyybg_2017_warp">
    <div class="swiper-container" id="jdyybg_2017">
        <div class="jdyybg_2017_Box swiper-wrapper">
            <!-- 轮播图 -->
            <div class="list_div bg1 swiper-slide"></div>
            <div class="list_div bg2 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="tlt">2017年7月1日-2017年9月30日</p>
                        <ul class="msg top_msg">
                            <li class="msg_tlt"><h3>平台累计投资金额(元)</h3><h3>平台累计注册用户(人)</h3></li>
                            <li class="data"><h2>110,514,654.00</h2><h2>61095</h2></li>
                        </ul>
                        <ul class="msg bottom_msg">
                            <li class="msg_tlt"><h3>平台累计为用户赚取收益(元)</h3><h3>平台累计交易笔数(笔)</h3></li>
                            <li class="data"><h2>867,865.12</h2><h2>27,287</h2></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="list_div bg3 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">平台数据</p>
                        <p class="Subtitle">— 2017年7月1日-2017年9月30日</p>
                        <div class="pieChart_tlt">
                            <p class="pieChart_tlt1">2017年第三季度成交额</p>
                            <p class="pieChart_tlt2">项目期限分布</p>
                        </div>
                        <div class="pieChart">
                            <div id="pieChart_left" style="width:300px;height:300px"></div>
                            <div id="pieChart_right" style="width:300px;height:300px"></div>
                        </div>
                        <div class="msg_box">
                            <ul class="msg left_msg">
                                <li class="msg_app">71.73%</li>
                                <li class="msg_pc">28.27%</li>
                            </ul>
                            <ul class="msg center_msg">
                                <li class="msg_tlt"><h2>36.42%</h2><h3>0-1个月</h3></li>
                                <li class="data"><h2>2.78%</h2><h3>2-3个月</h3></li>
                            </ul>
                            <ul class="msg right_msg">
                                <li class="msg_tlt"><h2>56.17%</h2><h3>1-2个月</h3></li>
                                <li class="data"><h2>4.36%</h2><h3>3-6个月</h3></li>
                            </ul>
                        </div>

                    </div>
                </div>
            </div>
            <div class="list_div bg4 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">用户数据</p>
                        <p class="Subtitle">— 2017年7月1日-2017年9月30日</p>
                        <div class="msg_box">
                            <ul class="msg left_msg">
                                <li class="data"><h2>54.77%</h2></li>
                                <li class="data"><h2>45.23%</h2></li>
                            </ul>
                            <ul class="msg right_msg">
                                <li class="data"><h2>53.65%</h2></li>
                                <li class="data"><h2>46.35%</h2></li>
                            </ul>
                        </div>
                        <div class="barGraphBox">
                            <div id="barGraph" style="width:720px;height:350px;margin: 0 auto;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list_div bg5 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">投资风云榜</p>
                        <p class="Subtitle">— 2017年7月1日-2017年9月30日</p>
                        <div class="msgBox">
                            <ul>
                                <li>133****3160</li>
                                <li>累计赚取：7338.44</li>
                            </ul>
                            <ul>
                                <li>135****5870</li>
                                <li>投资501次</li>
                            </ul>
                            <ul>
                                <li>136****3065</li>
                                <li>累计投资498915.00</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list_div bg6 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">人气活动</p>
                        <p class="Subtitle">— 2017年7月1日-2017年9月30日</p>
                        <div id="focus_Box">
                            <div class="iSlider-wrapper">
                                <div class="swiper-wrapper">  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;"><img src="<%=path %>/images/front/img/informData/jdyybg_2017/yybg6_jbks.png" alt=""></a><p><span>机不可失-iphone8免费送   9.26-10.26</span></p>  
                                    </div>  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;"><img src="<%=path %>/images/front/img/informData/jdyybg_2017/yybg6_zq.png" alt=""></a><p><span>迎中秋庆国庆   9.15-10.15</span></p> 
                                    </div>  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;"><img src="<%=path %>/images/front/img/informData/jdyybg_2017/yybg6_newhand.png" alt=""></a><p><span>新手专享体验标  9.13-9.28</span></p>  
                                    </div>  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;"><img src="<%=path %>/images/front/img/informData/jdyybg_2017/yybg6_zmkl.png" alt="" class="swiper-lazy"></a><p><span>快乐周末福来DAY   9.8-9.24</span></p>
                                    </div>
                                    <div class="swiper-slide">  
                                        <a href="javascript:;"><img src="<%=path %>/images/front/img/informData/jdyybg_2017/yybg6_hb.png" alt=""></a><p><span>一起来抢红包雨   9.1-9.26</span></p>   
                                    </div>  
                                    <div class="swiper-slide">  
                                        <a href="javascript:;"><img src="<%=path %>/images/front/img/informData/jdyybg_2017/yybg6_qx.png" alt=""></a><p><span>约惠七夕，疯狂加息   8.25-8.28</span></p>
                                    </div> 
                                    <div class="swiper-slide">  
                                        <a href="javascript:;"><img src="<%=path %>/images/front/img/informData/jdyybg_2017/yybg6_cg.png" alt=""></a><p><span>喜庆存管四重奏   8.1-8.31</span></p> 
                                    </div> 
                                    <div class="swiper-slide">  
                                       <a href="javascript:;"><img src="<%=path %>/images/front/img/informData/jdyybg_2017/yybg6_wzp.png" alt=""></a><p><span>运转不停，天天有惊喜   7.7-7.23</span></p>
                                    </div> 
                                </div>  
                                <div class="swiper-button-next"></div>  
                                <div class="swiper-button-prev"></div>  
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="list_div bg7 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">平台大事记</p>
                        <p class="Subtitle">— 2017年7月1日-2017年9月30日</p>
                    </div>
                </div>
            </div>

            <div class="list_div bg8 swiper-slide">
                <div class="centent">
                    <div class="padding">
                        <p class="positiveTitle">媒体报道精选</p>
                        <p class="Subtitle">— 2017年7月1日-2017年9月30日</p>
                        <ul class="top">
                            <li>
                                <p>2017/1/16</p>
                                <h3>《正道金服与上海银行签订资金存管协议》</h3>
                            </li>
                            <li>
                                <p>2017/3/9</p>
                                <h3>《感恩有你 亿路同行 正道金服累计成交额突破1亿元》</h3>
                            </li>
                        </ul>
                        <ul class="bottom">
                            <li>
                                <p>2017/4/1</p>
                                <h3>《监管一周年，正道金服继续合规前行》</h3>
                            </li>
                            <li>
                                <p>2017/6/1</p>
                                <h3>《正道金服获国资战略入股，平台实力再升级》</h3>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="list_div bg9 swiper-slide">
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
<script src="<%=path %>/js/front/jdyybg_2017.js"></script>
</body>
</html>