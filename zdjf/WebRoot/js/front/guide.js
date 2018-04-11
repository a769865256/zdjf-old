layui.use(['carousel', 'form'], function(){
	var carousel = layui.carousel

	//常规轮播
	carousel.render({
  	elem: '#guide',
  	arrow: 'always',
  	width: '670px',
  	height: '380px',
  	anim: "fade",
  	autoplay: false,
  	indicator: "none"
	});
  	
  //监听轮播切换事件
	carousel.on('change(guide)', function(obj){ //guide来源于对应HTML容器的 lay-filter="guide" 属性值
      if(obj.index == 1){
        $("button[lay-type='sub']").show();
        $(".ktyhcg").addClass("active");
      }
      if(obj.index == 0){
        $("button[lay-type='sub']").hide();
        $(".ktyhcg").removeClass("active");
      }
      if(obj.index == 3){
        $(".cz").removeClass("active");
      }
      if(obj.index == 4){
        $(".cz").addClass("active");
      }
      if(obj.index == 6){
        $(".tzxm").removeClass("active");
      }
      if(obj.index == 7){
        $(".tzxm").addClass("active");
      }
      if(obj.index == 8){
        $("button[lay-type='add']").show();
      }
      if(obj.index == 9){
        $("button[lay-type='add']").hide();
      }
	});
  $("button[lay-type='sub']").hide();
});