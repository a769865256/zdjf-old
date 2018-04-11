layui.use(['layer','laydate','form'], function(){
	var layer = layui.layer,
	laydate= layui.laydate,
	form = layui.form,
	carousel = layui.carousel;
	//轮播
  	var area =document.getElementById('scrollBox');
    var con1 = document.getElementById('con1');
    var con2 = document.getElementById('con2');
    con2.innerHTML=con1.innerHTML;
    function scrollUp(){
	    if(area.scrollTop>=con1.offsetHeight){
	        area.scrollTop=0;
	    }else{
	        area.scrollTop++
	    }
    }                
    var time = 50;
    var mytimer=setInterval(scrollUp,time);
    area.onmouseover=function(){
        clearInterval(mytimer);
    }
    area.onmouseout=function(){
        mytimer=setInterval(scrollUp,time);
    }


	var todayDate = new Date();
	var y = todayDate.getFullYear();  
    var m = todayDate.getMonth() + 1;  
    var d = todayDate.getDate();  
    var today = y + '-' + m + '-' + d; 
	laydate.render({
		elem: '#sign_date',
		position: 'static',
		showBottom: false,
		type:'date',
		format:'yyyy-MM-dd',
		istoday: true,
		min: -90, //7天前
		max: 360, //7天后
		mark: {
			'2017-12-5': '5',
			'2017-11-29': '29',
		},
		done: function(value, date, endDate){
			var arrTd = $(".layui-laydate-content td");
			for (var i=0; i<arrTd.length; i++) {
				if(arrTd[i].getAttribute("lay-ymd") == today) {
					arrTd[i].innerHTML = "今";
					arrTd[i].className = "active";
					if(value == today) {
						arrTd[i].innerHTML = "<span class='laydate-day-mark layui-disabled'>今</span>";
					}
				}
			}
			$.each($('.layui-laydate-content td span'),function(i,j){
			  	//i为元素的索引，从0开始,j为当前处理的元素对象
			  	if($(j).html() == "29"){
			  		$(j).html('29<div class="markDiv">+30</div>')
			  	}
			  	if($(j).html() == "5"){
			  		$(j).html('5<div class="markDiv">+10</div>')
			  	}
			});
		}
	});
	var arrTd = $(".layui-laydate-content td");
	for(var i=0; i<arrTd.length; i++){
		if(arrTd[i].getAttribute("lay-ymd") == today) {
			arrTd[i].innerHTML = "今";
			arrTd[i].className = "active";
		}
	}
	/*点击问号*/
	$(".jihui i").click(function() {
		if($(".jihui_msg").is(":hidden")) {
			$(".jihui_msg").show();
		} else {
			$(".jihui_msg").hide();
		}
	});
	$(".jihui i").hover(function() {
		if($(".jihui_msg").is(":hidden")) {
			$(".jihui_msg").show();
		} else {
			$(".jihui_msg").hide();
		}
	});
	/*一进页面判断是否已登录账号*/
	/*if(window.localStorage.c) { //已登录账号
		$(".click_qiandao").show();
		$(".login_qiandao").hide();
		$(".qiandaoDe").hide();
		$(".noBang").show();
		$(".noLogin").hide();
		$(".rili").show();
		$(".sign_centent1 img").hide();
	} else {  //未登录账号
		$(".click_qiandao").hide();
		$(".login_qiandao").show();
		$(".qiandaoDe").hide();
		$(".noBang").hide();
		$(".noLogin").show();
		$(".rili").hide();
	}*/
	/*点击今天日期签到*/
	$(".layui-laydate-content td.active").click(function(){
		$(".qd_success").show();
    	$(".qiandaoDe").show();
		$(".click_qiandao").hide();
		$(this).html("<span class='laydate-day-mark layui-disabled'>今</span>");
		$(".layui-laydate-content td.active").unbind("click"); //执行方法后 移除click方法
	});
	/*签到弹窗关闭*/
	$(".close").click(function() {
		$(".qd_success").hide();
		$(".ewm_box").hide();
	});
	/*日历显示*/
	$(".rili_btn").click(function() {
		if($(".rili").is(":hidden")) {
			$(".rili").show();

			$(".sign_centent1 img").hide();
		} else {
			$(".rili").hide();
			$(".sign_centent1 img").show();
		}
	});
	/*日历按钮月份获取*/
	$(".yue").html(m + "月");

	/*展示规则*/
	$(".ahook i").click(function() {
		if($(".guize").is(":hidden")) {
			$(".guize").show();
			$(this).css("transform","translateX(-50%) rotate(-90deg)");
			$(".sign_centent2 img").hide();
		} else {
			$(".guize").hide();
			$(this).css("transform","translateX(-50%) rotate(90deg)");
			$(".sign_centent2 img").show();
		}
	});
});

