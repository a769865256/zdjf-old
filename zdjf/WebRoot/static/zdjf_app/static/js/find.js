$(function(){
    console.log('初始化');
})
$('.tab .btn').on('click',function(){
    var index=$(this).index();
    $(this).find('span').addClass('active');
    $(this).siblings().find('span').removeClass('active');
    $('.tabbox .box').eq(index).show().siblings().hide();
})
$('.ask ul li h4').on('click',function(){
    var abtn=$(this).find('.a_h4');
    var txt=$(this).next('.txt');
    if(abtn.hasClass('active')){
        abtn.removeClass('active');
        txt.hide();
    }else{
        abtn.addClass('active');
        txt.show();
        /*$(this).siblings().find('a').removeClass('active');*/
        $(this).parents('li').siblings().find('a').removeClass('active');
        $(this).parents('li').siblings().find('.txt').hide();
    }
})
//安卓不需要返回键，ios需要
var u = navigator.userAgent;
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
if(isiOS){
    $('.find .header').show().next().addClass('body_content');
}
//帮助中心点击展示隐藏
$('.f_recharge').delegate('.recharge_item_con_tit','click',function () {
    if($(this).parent().hasClass('active')){
        $(this).parent().removeClass('active');
    }else{
        $(this).parent().addClass('active').siblings().removeClass('active');
    }
}).delegate('.recharge_item_tit','click',function () {
    if($(this).parent().hasClass('active')){
        $(this).parent().removeClass('active');
    }else{
        $(this).parent().addClass('active');
    }
})
//银行限额封装函数
function money_zh($num){
    $num = Number($num);
    if($num < 1000 || $num%1000 !==0) {
        return $num;
    } else if($num >=1000 && $num < 10000){
        return Number($num/1000,1)+'K';
    } else if ($num >= 10000) {
        return Number($num/10000,2)+'W';
    }
}