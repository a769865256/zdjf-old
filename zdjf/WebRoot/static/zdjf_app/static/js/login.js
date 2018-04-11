var countdown=60; 
function sendemail(){
    alert(1);
    var obj = $("#btn");
    obj.removeClass('verbtn');
    settime(obj);
}
function settime(obj) { //发送验证码倒计时
    if (countdown == 0) { 
        obj.removeAttr('disabled');
        obj.addClass('verbtn');
        obj.val("获取验证码");
        countdown = 60; 
        return;
    } else { 
        obj.attr('disabled',true);
        obj.val(countdown + "秒后重发");
        countdown--; 
    } 
    setTimeout(function() { 
        settime(obj) 
    },1000) 
}

function sendemail(){
    var obj = $(".btn2");
    obj.removeClass('verbtn');
    settime(obj);
}
function settime(obj) { //发送验证码倒计时
    if (countdown == 0) { 
        obj.removeAttr('disabled');
        obj.addClass('verbtn');
        obj.val("获取验证码");
        countdown = 60; 
        return;
    } else { 
        obj.attr('disabled',true);
        obj.val(countdown + "秒后重发");
        countdown--; 
    } 
    setTimeout(function() { 
        settime(obj) 
    },1000) 
}

$('.in_open').click(function(){
    if($('.inbox').hasClass('hide')){
        $('.inbox').removeClass('hide').focus();
    }else{
        $('.inbox').addClass('hide');
    }
});
$('.regist_box input').focus(function () {
    $(this).parent().addClass('active');
}).blur(function () {
    $(this).parent().removeClass('active');
})