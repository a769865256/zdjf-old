layui.use(['layer','laypage','element','carousel','form'], function(){
	var layer = layui.layer,
		laypage=layui.laypage,
		element = layui.element,
        form = layui.form,
		carousel=layui.carousel;
    //监听正经值开关
    form.on('switch', function(data){
        if(data.elem.checked){ //开关是否开启，true或者false
            $(".hongbao").val("");
            $(".tlt").html("");
            $(".redbag .redbox").hide();
            $("#coinState").val("1");//使用用正经值
            $("#giftId").val();//使用正经值则不能使用红包
            $(".zjzinput").removeAttr("readonly");
            //本次可用正经值上限
            var zjzLimit = ($("#amount").val()/100).toFixed(2);
            //正经值余额
            var zjzInput = $("#activeIcon").text();
            $(".zjzinput").val(zjzInput);
            $(".zjzinput").attr("readonly","readonly");
        } else {
            //$(".redbag .ovderlist").hide();
            //$(".zjzinput").attr("readonly","readonly");
            $(".zjzinput").val("");
            //$(".hongbao").removeAttr("readonly");
            $(".redbag .redbox").show();
            $("#coinState").val("0");//不使用正经值
            $(".zjzinput").val("");
        }
    });

    
	$('.tab span').on('click',function(){
		if($(this).hasClass('active')){
			return;
		}else{
			$(this).addClass('active').siblings('span').removeClass('active');
		}
	})
	$('.chiose p a').on('click',function(){
		if($(this).hasClass('active')){
			return;
		}else{
			$(this).addClass('active').siblings('a').removeClass('active');
		}
	})
//	$('.investbtn').on('click',function(){
//		var amount=$('.rt_one input').val();
//		var gift_id=$('.redbag input').val();
//		var coupon_id=$('.interest input').val();
//		var product_id=$('.product_id').text();
//		window.location=path+'/product/order.action?amount='+amount+'&gift_id='+gift_id+'&coupon_id='+coupon_id+'&product_id='+product_id;
//	})
// 	$('.rt_one a').on('click',function(){
//     	var product_balance = parseInt($("#product_balance").val());
//     	$("#amount").val(product_balance);
//     	var this_val=product_balance;
//     	var balance = parseInt($("#balance").val());
//     	calculate(this_val);
//     	$("#couponId").val("");
//     	$("#giftId").val("");
//     	$(".hongbao").val("");
//     	$(".jiaxi").val("");
//
//         $('.redlist').each(function () {
//             var montwo = parseInt($(this).find(".montwo").val());
// 			console.log(111);
//             if(this_val<montwo){
//             	$(this).removeClass('xiaoyu');
//             	$(this).addClass('dayu');
//             }else{
//             	$(this).removeClass('dayu');
//             	$(this).addClass('xiaoyu');
//             }
//         });
// 	});
	
	laypage.render({  /*参考链接http://www.layui.com/doc/modules/laypage.html*/
		elem: 'recrdpage', 	//这里是 ID，不用加 # 号
		limit:10,
		groups:5,
		first:'首页',
		last:'尾页',
		theme:'#1187f1',
		count: 100		//数据总数，从服务端得到
	});
/*弹出加息券选择*/
$('.interest a').on('click',function(event){
    //用户输入投资金额
    var amount = $("#amount").val();
    var re = /^[0-9]+$/ ;
    if(!re.test(amount)){
        $("#error").html("请输入投资金额");
        return ;
    }
	event.stopPropagation();
    if($(this).siblings(".interbox").is(":hidden")){
        $(this).find("i").css("transform","rotate(270deg)");
        $('.interbox').removeClass('hide');
        $('.redbox').addClass('hide');
    } else {
        $(this).find("i").css("transform","rotate(90deg)");
        $('.interbox').addClass('hide');
    }
});
/*弹出红包券选择*/
$('.redbag a').on('click',function(event){
    //用户输入投资金额
    var amount = $("#amount").val();
    var re = /^[0-9]+$/ ;
    if(!re.test(amount)){
        $("#error").html("请输入投资金额");
        return ;
    }
	event.stopPropagation();
    if($(this).siblings(".redbox").is(":hidden")){
        $(this).find("i").css("transform","rotate(270deg)");
        $('.redbox').removeClass('hide');
        $('.interbox').addClass('hide');   
    } else {
        $(this).find("i").css("transform","rotate(90deg)");
        $('.redbox').addClass('hide');
    }
    $("#error").html("");
});
$('body').click(function(){
	$('.interbox').addClass('hide');
	$('.redbox').addClass('hide');
})


$('.catatab .tab a').on('click',function(){
	var index=$(this).index();
	$(this).addClass('active').siblings().removeClass('active');
	$('.tabbox .tabtent').eq(index).show().siblings().hide();
})

carousel.render({
	elem: '#idbanner',
	width: '181px', //设置容器宽度
	height:'112px', //设置容器高度
	autoplay:false,//是否自动切换
	arrow: 'always', //始终显示箭头
	anim: 'default', //切换动画方式
	indicator:'none'
});
carousel.render({
    elem: '#idbanner2',
    width: '181px', //设置容器宽度
    height:'112px', //设置容器高度
    autoplay:false,//是否自动切换
    arrow: 'always', //始终显示箭头
    anim: 'default', //切换动画方式
    indicator:'none'
});


	$('.hetongbtn a').on('click',function(){
		var index=$(this).index();
		$(this).addClass('active').siblings().removeClass('active');
		$('.hetongtab .hetong').eq(index).show().siblings().hide();
	})
	$('.zhengjing p a').on('click',function(){
		$(this).hide().siblings('a').show()
	})
	/*加息券/红包券选择*/
	var coupon_id="";
	$('.interlist .num').on('click',function(){
        var index = $(this).parent().index();
        $(this).parent().addClass("active").siblings().removeClass("active");
        var isnewHand_jx = $(this).siblings(".numtxt").find("p:first-child").html();
        $('.pro_detail .catalog .ca_rt ul li.interest .tlt').html(isnewHand_jx);
		$('.jiaxi').val($(this).text());
		$('.interbox').addClass('hide');
		var conId = $(this).siblings(".userid").val();
		var userInterest = $(this).siblings(".userInterest").val();//用户使用的加息券面值
		$("#couponId").val(conId);
        //用户输入投资金额
        var amount = $("#amount").val();
        calculate(amount,userInterest);
        if(index==1||index==0){
            $('.jiaxi').val("");
        }
	})

	var gift_id="";
	$('.redlist .num').click(function(){
        var index = $(this).parent().index();
        $(this).parent().addClass("active").siblings().removeClass("active");
        var isnewHand_hb = $(this).siblings(".numtxt").find("p:first-child").html();
        $('.pro_detail .catalog .ca_rt ul li.redbag .tlt').html(isnewHand_hb);
		$('.hongbao').val($(this).text());
		$('.redbox').addClass('hide');
		var giftId = $(this).siblings(".useridtwo").val();
		$("#giftId").val(giftId);
        if(index==1||index==0){
            $('.hongbao').val("");
        }
	});




	/*加息券动态获取并渲染隐藏域的值*/
	$('.investor').bind('input propertychange', function() {
    	var this_val=parseInt($(this).val());
    	var balance = parseInt($("#balance").val());
    	var product_balance = parseInt($("#product_balance").val());
    	if(this_val>product_balance){
			$("#amount").val(product_balance);
			this_val = product_balance;
		}
    	$("#couponId").val("");
    	$("#giftId").val("");
    	$(".hongbao").val("");
    	$(".tlt").html("");
    	$(".jiaxi").val("");
		$('.interlist').each(function () {
		   var mon = parseInt($(this).find(".monone").val());
		   if(this_val<mon){
			$(this).removeClass('xiaoyu')
			$(this).addClass('dayu')
		   }else{
			$(this).removeClass('dayu')
			$(this).addClass('xiaoyu')
		   }
	   });

        $('.redlist').each(function () {
            var montwo = parseInt($(this).find(".montwo").val());

            if(this_val<montwo){
            	$(this).removeClass('xiaoyu')
            	$(this).addClass('dayu')
            }else{
            	$(this).removeClass('dayu')
            	$(this).addClass('xiaoyu')
            }
        });
	});

	$('.allmon').click(function(){
		var allmon=parseInt($('.ca_rt h3 span').text());
		$('.investor').val(200)

		var this_val=parseInt($('.investor').val());
    	var balance = parseInt($("#balance").val());
    	var product_balance = parseInt($("#product_balance").val());
    	if(this_val>balance && this_val>product_balance){
    		if(balance>product_balance){
    			$("#amount").val(product_balance);
    			this_val = product_balance;
    		}else{
    			$("#amount").val(balance);
    			this_val = balance;
    		}
    	}else{
    		if(balance>product_balance){
    			if(this_val>product_balance){
    				$("#amount").val(product_balance);
    				this_val = product_balance;
    			}
    			
    		}else{
    			if(this_val>balance){
    				$("#amount").val(balance);
    				this_val = balance;
    			}
    			
    		}
    	}
    	$("#couponId").val("");
    	$("#giftId").val("");
    	$(".hongbao").val("");
    	$(".jiaxi").val("");
		$('.interlist').each(function () {
		   var mon = parseInt($(this).find(".monone").val());
		   if(this_val<mon){
			$(this).removeClass('xiaoyu')
			$(this).addClass('dayu')
		   }else{
			$(this).removeClass('dayu')
			$(this).addClass('xiaoyu')
		   }
	   });

        $('.redlist').each(function () {
            var montwo = parseInt($(this).find(".montwo").val());

            if(this_val<montwo){
            	$(this).removeClass('xiaoyu')
            	$(this).addClass('dayu')
            }else{
            	$(this).removeClass('dayu')
            	$(this).addClass('xiaoyu')
            }
        });	
	})
})
