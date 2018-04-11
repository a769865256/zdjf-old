//立即注册
// $(".cainiao_btn a").click(function(){
// 	if($(".cainiao_getJL").is(":hidden")){
// 		$(".cainiao_registration").hide();
// 		$(".cainiao_getJL").show();
// 	} else {
// 		$(".cainiao_registration").show();
// 		$(".cainiao_getJL").hide();
// 	}
// });
// //立即投资1
// $(".yiming_btn a").click(function(){
// 	if($(".yiming_getJL").is(":hidden")){
// 		$(".yiming_investment").hide();
// 		$(".yiming_getJL").show();
// 	} else {
// 		$(".yiming_investment").show();
// 		$(".yiming_getJL").hide();
// 	}
// });
// //立即投资2
// $(".pengcheng_btn a").click(function(){
// 	if($(".pengcheng_getJL").is(":hidden")){
// 		$(".pengcheng_investment").hide();
// 		$(".pengcheng_getJL").show();
// 	} else {
// 		$(".pengcheng_investment").show();
// 		$(".pengcheng_getJL").hide();
// 	}
// });
//显示规则内容
$(".active_rule_btn i").click(function(){
	if($(".active_rule").css("visibility")!="hidden"){
		$(".active_rule").css("visibility", "hidden");
		$(this).css("transform","rotate(90deg)");
	} else {
		$(".active_rule").removeAttr("style");
		$(this).removeAttr("style");
	}
});