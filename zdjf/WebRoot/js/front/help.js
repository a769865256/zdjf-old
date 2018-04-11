/*help页面左边菜单点击切换右边内容*/
$(".h_left ul a").click(function(){
	var index = $(this).index();
	$(this).addClass("active").siblings().removeClass("active");
	$(this).addClass("active").parent().parent().siblings().find("a").removeClass("active");
	$(".newHand .new_guide").eq(index).show().siblings().hide();
	$(".touzi .new_guide").eq(index).show().siblings().hide();
	$(".vip .new_guide").eq(index).show().siblings().hide();
	$(".zijin .new_guide").eq(index).show().siblings().hide();
	$(".other .new_guide").eq(index).show().siblings().hide();
});

/*help页面常见问题左边下拉菜单*/
$('.h_left ul li>p').on('click',function(){
	$(this).parent().siblings().find("p").removeClass("active");
	$(this).parent().siblings().find(".he_more").addClass("hide");
	$(this).parent().siblings().find("a").removeClass("active");
	$(this).siblings(".he_more").children(":first").addClass("active");
	if($(this).next('.he_more').hasClass('hide')){
		$(this).next('.he_more').removeClass('hide');
		$(this).addClass('active');
	}else{
		$(this).next('.he_more').addClass('hide');
		$(this).removeClass('active');
	}
	if($(".h_left ul li p.active").html() == "常见问题"){
		$(".h_right .touzi").hide();
		$(".h_right .newHand").show();
		$(".h_right .vip").hide();
		$(".h_right .zijin").hide();
		$(".h_right .other").hide();
	}
	if($(".h_left ul li p.active").html() == "投资指南"){
		$(".h_right .touzi").show();
		$(".h_right .newHand").hide();
		$(".h_right .vip").hide();
		$(".h_right .zijin").hide();
		$(".h_right .other").hide();
	}
	if($(".h_left ul li p.active").html() == "会员福利"){
		$(".h_right .touzi").hide();
		$(".h_right .newHand").hide();
		$(".h_right .vip").show();
		$(".h_right .zijin").hide();
		$(".h_right .other").hide();
	}
	if($(".h_left ul li p.active").html() == "资金存管"){
		$(".h_right .touzi").hide();
		$(".h_right .newHand").hide();
		$(".h_right .vip").hide();
		$(".h_right .zijin").show();
		$(".h_right .other").hide();
	}
	if($(".h_left ul li p.active").html() == "其他问题"){
		$(".h_right .touzi").hide();
		$(".h_right .newHand").hide();
		$(".h_right .vip").hide();
		$(".h_right .zijin").hide();
		$(".h_right .other").show();
	}
})


/*help页面常见问题右边下拉菜单*/
$('.new_guide .tent ul li>a').on('click',function(){
	if($(this).next('.moretxt').hasClass('hide')){
		$(this).next('.moretxt').removeClass('hide');
		$(this).addClass('active');
	}else{
		$(this).next('.moretxt').addClass('hide');
		$(this).removeClass('active');
	}
})

$(function(){
	var showType = $("#showType").val();
	var secMenu = $("#secMenu").val();
    if(showType == "commonQuestion"){
        $(".h_right .touzi").hide();
        $(".h_right .newHand").show();
        $(".h_right .vip").hide();
        $(".h_right .zijin").hide();
        $(".h_right .other").hide();
		$(".h_left li:nth-of-type(1) .he_more a").eq(secMenu-1).addClass("active").siblings().removeClass("active");
		$(".h_right .newHand .new_guide:nth-of-type("+secMenu+")").show().siblings().hide();
    }
    if(showType == "investGuide"){
        $(".h_right .touzi").show();
        $(".h_right .newHand").hide();
        $(".h_right .vip").hide();
        $(".h_right .zijin").hide();
        $(".h_right .other").hide();
		$(".h_left li:nth-of-type(2) .he_more a").eq(secMenu-1).addClass("active").siblings().removeClass("active");
		$(".h_right .touzi .new_guide:nth-of-type("+secMenu+")").show().siblings().hide();
    }
    if(showType == "userWeal"){
        $(".h_right .touzi").hide();
        $(".h_right .newHand").hide();
        $(".h_right .vip").show();
        $(".h_right .zijin").hide();
        $(".h_right .other").hide();
		$(".h_left li:nth-of-type(3) .he_more a").eq(secMenu-1).addClass("active").siblings().removeClass("active");
		$(".h_right .vip .new_guide:nth-of-type("+secMenu+")").show().siblings().hide();
    }
    if(showType == "fundsDeposit"){
        $(".h_right .touzi").hide();
        $(".h_right .newHand").hide();
        $(".h_right .vip").hide();
        $(".h_right .zijin").show();
        $(".h_right .other").hide();
		$(".h_left li:nth-of-type(4) .he_more a").eq(secMenu-1).addClass("active").siblings().removeClass("active");
        $(".h_right .zijin .new_guide:nth-of-type("+secMenu+")").show().siblings().hide();
    }
    if(showType == "otherQuestion"){
        $(".h_right .touzi").hide();
        $(".h_right .newHand").hide();
        $(".h_right .vip").hide();
        $(".h_right .zijin").hide();
        $(".h_right .other").show();
        $(".h_left li:nth-of-type(5) .he_more a").eq(secMenu-1).addClass("active").siblings().removeClass("active");
        $(".h_right .other .new_guide:nth-of-type("+secMenu+")").show().siblings().hide();
    }
});