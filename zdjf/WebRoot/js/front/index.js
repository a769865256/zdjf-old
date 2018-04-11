layui.use(['layer','laypage','element','carousel'], function(){
  var layer = layui.layer,
    carousel=layui.carousel;
    //图片轮播
  carousel.render({
    elem: '#homebanner',
    width: '100%',
    height: '360px',
    arrow:'none',
    anim:'default',
    interval: 5000
  });

});

$('.flexslider_news').flexslider({
	direction:"vertical",
	animation: "slide",
	slideshow: true,
	slideshowSpeed: 3000,
	directionNav: false,
  controlNav: false
});

$('#hezuo_id').cxScroll();
/*金钱显示切换*/
$('.money i').click(function(){
  $(this).parent().hide().siblings(".money").show();
  if($('.hd1').is(":hidden")){
    $('.mon2').show();
    $('.mon1').hide();
  } else if($('.hd2').is(":hidden")){
    $('.mon1').show();
    $('.mon2').hide();
  }
});

/*弹窗提示*/
function popup(){
  if(window.localStorage.c){
        $('.addmove').hide();
        return false;
      }else{
        var storage=window.localStorage;
        storage.setItem("c",'olduser');
        $('.addmove').show();
      }
}        
popup();
$('.closeFun').click(function(){
  $('.addmove').hide();
})
/*返回顶部*/
function returnTop() {
  var speed = 200;//滑动的速度
  $('body,html').animate({ scrollTop: 0 }, speed);
  return false;
}