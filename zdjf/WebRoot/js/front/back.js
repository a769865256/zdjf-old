var flag=false;

$('.messbtn').on('click',function(){
	var ip=returnCitySN["cip"];
	var user_name=$('#user_name').val();
	var url=path+"/send.action";
	$.get(url,{"phone":user_name,"type":1,"ip":ip},function(data){
		if(data.status='100'){
			
		}
	});
});



/*找回密码倒计时*/
var countdown=60;
function sendemail(){
    var obj = $("#btn2");
    obj.addClass('noverbtn');
    settime(obj);
    smsSend();
}
function settime(obj) { //发送验证码倒计时
    if (countdown == 0) { 
        obj.removeAttr('disabled');
        obj.removeClass('noverbtn');
        obj.removeAttr('style');
        obj.val("获取验证码");
        countdown = 60; 
        return;
    } else { 
        obj.attr('disabled',true);
        obj.val(countdown + "秒后重发");
        countdown--; 
    } 
setTimeout(function() { 
    settime(obj) }
    ,1000) 
}