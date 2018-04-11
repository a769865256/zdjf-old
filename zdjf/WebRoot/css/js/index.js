//通过主模块，运用AMD规范定义的的require()函数调用其他模块。这里分别是(jquery.js)、、、等子模块。
require([], function (){
    //banner切换
    $('.flexslider').flexslider({
    	animation: "slide",//String: Select your animation type, "fade" or "slide"图片变换方式：淡入淡出或者滑动
    	slideshow: true,//Boolean: Animate slider automatically 载入页面时，是否自动播放
    	slideshowSpeed: 4000, // 自动播放速度毫秒
    	directionNav: false //Boolean:  (true/false)是否显示左右控制按钮
    })
    $('.flexslider_news').flexslider({
    	direction:"vertical",
    	animation: "slide",
    	slideshow: true,
    	slideshowSpeed: 3000,
    	directionNav: false
    })    
   	$(".activ").flexslider({
   		controlNav: false,
   		directionNav: true,
  		animation: "slide",
  		slideshowSpeed: 3000, // 自动播放速度毫秒
  		itemWidth: 260,
  		itemMargin: 20,//滚动项目之间的间距
	});

});
