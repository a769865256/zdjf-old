$('.invest .tab span').on('click',function(){
	var index=$(this).index();
	$(this).addClass('active');
	$(this).siblings().removeClass('active');
	$('.tabbox .box').eq(index).show().siblings().hide();
})

/*我的投资-详情*/
$('.detail').on('click',function(){
	var nexts=$(this).next().find('a');
	if(nexts.hasClass('stay_pay')){
		console.log('待支付');
		$('.no_pay').show();
		return false;
	}else{
		console.log('已支付');
		return true;
	}
})
$('.cancelbtn').on('click',function(){
	$('.no_pay').hide();
})

/*我的优惠*/
$('.discount .tab span').on('click',function(){
	var index=$(this).index();
	$(this).addClass('active');
	$(this).siblings().removeClass('active');
	$('.tabbox .box').eq(index).show().siblings().hide();
})

/*资金明细*/
$('.capital .tab span').on('click',function(){
	$(this).addClass('active');
	$(this).siblings().removeClass('active');
})




























