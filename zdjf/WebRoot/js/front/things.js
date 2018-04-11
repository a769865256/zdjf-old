layui.use('carousel', function(){
	var carousel = layui.carousel;
	//建造实例
	carousel.render({
	    elem: '#thing01'
	    ,width: '100px' //设置容器宽度
	    ,height: "16px"
	    ,arrow: 'always' //始终显示箭头
	    //,anim: 'updown' //切换动画方式
	    ,indicator: "none"
	    ,autoplay: false
	});
  	//监听轮播切换事件
	carousel.on('change(thing01)', function(obj){ 
		if(obj.index == 1){
			$(".things2016").show();
			$(".things2017").hide();
		} else {
			$(".things2017").show();
			$(".things2016").hide();
		}
	});
});