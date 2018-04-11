$(".insurance_bottom ul li").click(function(){
  var index = $(this).index();
  $(this).addClass("active").siblings().removeClass("active");
  $(".tabBox .tab").eq(index).show().siblings().hide();
});
