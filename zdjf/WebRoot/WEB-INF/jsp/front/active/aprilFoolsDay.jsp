<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="aprilFoolsDay">
    <div class="aprilFoolsDay_bg">
        <div class="active1"></div>
        <div class="active2"></div>
        <div class="active3">
            <c:if test='${empty coinBlance}'><p>老板，您的正经值剩余 <a href="<%=path%>/toLogin.action?redirect=903">登录查看</a></p></c:if>
            <c:if test='${not empty coinBlance}'><p class="zjzP">老板，您的正经值剩余 <span>${coinBlance}</span></p></c:if>
            <div id="lottery">
                <table border="0" cellpadding="0" cellspacing="0">
                    <tr class="lottery-group">
                        <td class="lottery-unit td_1" >
                            <div class="img">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery1_0.png" class="dark" />
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery_0.png" class="light" />
                            </div>
                        </td>
                        <td class="gap"></td>
                        <td class="lottery-unit td_2">
                            <div class="img">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery1_1.png" class="dark" />
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery_1.png" class="light" />
                            </div>
                        </td>
                        <td class="gap"></td>
                        <td class="lottery-unit td_3">
                            <div class="img">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery1_2.png" class="dark" />
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery_2.png" class="light" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="gap-2" colspan="5"></td>
                    </tr>
                    <tr class="lottery-group">
                        <td class="lottery-unit td_4">
                            <div class="img">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery1_7.png" class="dark" />
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery_7.png" class="light" />
                            </div>
                        </td>
                        <td class="gap"></td>
                        <td class="td_5">
                        	<c:if test='${not empty coinBlance}'>
	                            <a href="javascript:" class="go">
	                                <div class="img">
	                                    <img src="<%=path%>/images/front/img/active/aprilFoolsDay/go.png" />
	                                    <p>（剩余<span>${remainAwardNum}</span>次）</p>
	                                </div>
	                            </a>
                            </c:if>
                            <c:if test='${empty coinBlance}'>
	                            <div class="img no_login">
	                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/go.png" />
	                                <p>（剩余<span>3</span>次）</p>
	                            </div>
                            </c:if>
                            <!-- 正经值不足 -->
                            <div class="img no_go" style="display: none;">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/no_go.png" />
                            </div>
                            <!-- 次数不足 -->
                            <div class="img no_number" style="display: none;">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/no_number.png" />
                            </div>
                        </td>
                        <td class="gap"></td>
                        <td class="lottery-unit td_6">
                            <div class="img">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery1_3.png" class="dark" />
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery_3.png" class="light" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="gap-2" colspan="5"></td>
                    </tr>
                    <tr class="lottery-group">
                        <td class="lottery-unit td_7">
                            <div class="img">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery1_6.png" class="dark" />
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery_6.png" class="light" />
                            </div>
                        </td>
                        <td class="gap"></td>
                        <td class="lottery-unit td_8">
                            <div class="img">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery1_5.png" class="dark" />
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery_5.png" class="light" />
                            </div>
                        </td>
                        <td class="gap"></td>
                        <td class="lottery-unit td_9">
                            <div class="img">
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery1_4.png" class="dark" />
                                <img src="<%=path%>/images/front/img/active/aprilFoolsDay/lottery_4.png" class="light" />
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="active4">
            <ul>
                <li>
                    <div class="ranking sred">
                        <img src="<%=path%>/images/front/img/active/aprilFoolsDay/1.png" alt="">
                    </div>
                    <div class="phoneNumber sred">${investList[0].phone}</div>
                    <div class="money sred">${investList[0].amt}</div>
                </li>
                <li>
                    <div class="ranking sred">
                        <img src="<%=path%>/images/front/img/active/aprilFoolsDay/2.png" alt="">
                    </div>
                    <div class="phoneNumber sred">${investList[1].phone}</div>
                    <div class="money sred">${investList[1].amt}</div>
                </li>
                <li>
                    <div class="ranking sred three">
                        <img src="<%=path%>/images/front/img/active/aprilFoolsDay/3.png" alt="">
                    </div>
                    <div class="phoneNumber sred three">${investList[2].phone}</div>
                    <div class="money sred three">${investList[2].amt}</div>
                </li>
                <li>
                    <div class="ranking gay">4</div>
                    <div class="phoneNumber gay">${investList[3].phone}</div>
                    <div class="money gay">${investList[3].amt}</div>
                </li>
                <li>
                    <div class="ranking gay">5</div>
                    <div class="phoneNumber gay">${investList[4].phone}</div>
                    <div class="money gay">${investList[4].amt}</div>
                </li>
                <li>
                    <div class="ranking gay">6</div>
                    <div class="phoneNumber gay">${investList[5].phone}</div>
                    <div class="money gay">${investList[5].amt}</div>
                </li>
                <li>
                    <div class="ranking gay">7</div>
                    <div class="phoneNumber gay">${investList[6].phone}</div>
                    <div class="money gay">${investList[6].amt}</div>
                </li>
                <li>
                    <div class="ranking gay">8</div>
                    <div class="phoneNumber gay">${investList[7].phone}</div>
                    <div class="money gay">${investList[7].amt}</div>
                </li>
                <li>
                    <div class="ranking gay">9</div>
                    <div class="phoneNumber gay">${investList[8].phone}</div>
                    <div class="money gay">${investList[8].amt}</div>
                </li>
                <li>
                    <div class="ranking gay">10</div>
                    <div class="phoneNumber gay">${investList[9].phone}</div>
                    <div class="money gay">${investList[9].amt}</div>
                </li>
            </ul>
        </div>
    </div>
</div>

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script type="text/javascript" src="<%=path%>/module/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/module/sticky-header.js"></script>
<script type="text/javascript" src="<%=path%>/module/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/module/lottery/lottery.js"></script>
<script type="text/javascript">
    $('.header').stickMe({
        topOffset:100
    });
	var path = '<%=path%>';
    $(function(){
	    if(window.name != "bencalie"){
			location.reload();
			window.name = "bencalie";
		}else{
			window.name = "";
		}
    	$(".no_login").click(function() {
    		window.location.href = path + "/toLogin.action?redirect=903";
    	});
        if($(".go .img p span").html() <= 0) { //次数为0抽奖按钮置灰，点击无效
            $(".go").hide();
            $(".no_number").show();
        } else if($(".active3 .zjzP span").html() < 5) { //正经值小于5显示正经值不足
            $(".go").hide();
            $(".no_go").show();
        } else if($(".active3 .zjzP span").html() < 5 && $(".go .img p span").html() <= 0){ //判断总正经值是否小于5
            $(".go").hide();
            $(".no_number").show();
        }
    });
    layui.use(['layer','laydate','form','laypage'], function() {
        var layer = layui.layer;
        if($(".go .img p span").html() <= 0){ //次数为0抽奖按钮置灰，点击无效
            $(".go").hide();
            $(".no_number").show();
        } else if($(".active3 .zjzP span").html() < 5){ //判断总正经值是否小于5
            $(".go").hide();
            $(".no_go").show();
        } else if($(".active3 .zjzP span").html() < 5 && $(".go .img p span").html() <= 0){
        	$(".go").hide();
            $(".no_number").show();
        }else{
        	lottery.lottery({
                selector:     '#lottery',
                width:        3,
                height:       3,
                index:        -1,    // 初始位置
                initSpeed:    200,  // 初始转动速度
                beforeRoll: function () { // 重写滚动前事件：beforeRoll
                    var self = this;
                    $.ajax({
                    	type: 'post',
                    	url: path + '/activity/doAward.action',
                    	data: {
                    		reg_source: 1
                    	},
                    	success:function(data){
                    		if(data.status == 100){
	                    		$(".dark").show();
			                    $(".light").hide();
			                    // 获取次数
			                    var number = $(".go .img p span").html();
			                    var newNumber = number - 1;
			                    $(".go .img p span").html(newNumber);
			                    // 获取正经值
			                    var zjzNumber = $(".active3 .zjzP span").html();
			                    var newZjzNumber = zjzNumber - 5;
			                    $(".active3 .zjzP span").html(newZjzNumber.toFixed(1)); //保留一位小数
	                    		var hbArr = [2,7];
	                    		var jxqArr = [3,6];
	                    		var zjzArr = [1,4];
	                    		var hbIndex = Math.floor((Math.random()*hbArr.length));
	                    		var jxqIndex = Math.floor((Math.random()*jxqArr.length));
	                    		var zjzIndex = Math.floor((Math.random()*zjzArr.length));
                    			if(data.content == 1){ //抽中红包
                    				self.options.target = hbArr[hbIndex];
                    			}
                    			if(data.content == 2){ //抽中加息券
                    				self.options.target = jxqArr[jxqIndex];
                    			}
                    			if(data.content == 3){ //抽中正经值
                    				self.options.target = zjzArr[zjzIndex];
                    			}
                    		} else {
	                    		clearTimeout(self.downTimer);
								clearTimeout(self.rollerTimer);
								self.options.speed = self.options.initSpeed;
								self.options.target = -1;
								self.options.isRunning = false;
								self._enable();
                    			layer.msg(data.content, {
			                        icon: 2,
			                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
			                    });
                    		}
                    	},
		                error: function() {
		                	self._disable();
                   			layer.msg('请求错误！', {
		                        icon: 2,
		                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
		                    });
		                }
                    });
                },
                _roll: function () {
                	
                },
                aim: function () { // 重写计算中奖号的方法：aim（默认随机数字）
                    
                },
                _stop: function () { // 转盘停止
                    if(this.options.isRunning == false) {
                        layer.msg('您的奖品已放入我的优惠中。', {
                            icon: 1,
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        });
                        if($(".go .img p span").html() <= 0){ //次数为0抽奖按钮置灰，点击无效
				            $(".go").hide();
				            $(".no_number").show();
				        } else if($(".active3 .zjzP span").html() < 5){ //判断总正经值是否小于5
				            $(".go").hide();
				            $(".no_go").show();
				        } else if($(".active3 .zjzP span").html() < 5 && $(".go .img p span").html() <= 0){ //判断总正经值是否小于5
				            $(".go").hide();
				            $(".no_number").show();
				        }
                    }
                }
            });
        }
    });
</script>
</body>
</html>