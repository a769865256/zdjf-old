$('.agreement').click(function(){
	if ($(this).is(":checked")) {
        $('.registbtn').removeClass('nocheck');
    } else {
        $('.registbtn').addClass('nocheck');
    }
})

$('li').on('click','.wd',function(){
	if($(".bi").is(":hidden")){
		$(".zheng").hide();
		$(".bi").show();
		$(this).siblings("#passwd").attr("type","password");
	} else {
		$(".zheng").show();
		$(".bi").hide();
		$(this).siblings("#passwd").attr("type","text");
	}
})

$('li').on('click','.wd1',function(){
	if($(".bi1").is(":hidden")){
		$(".zheng1").hide();
		$(".bi1").show();
		$(this).siblings("#passwd").attr("type","password");
	} else {
		$(".zheng1").show();
		$(".bi1").hide();
		$(this).siblings("#passwd").attr("type","text");
	}
})

/*获取验证码倒计时*/
var countdown=60; 
function sendemail(){
	var sign = $("#sign").val();
	if(sign==null || sign==""){
		$("#vercode_error").html("错误请求！");
		return ;
	}
    var obj = $("#btn2");
    obj.addClass('noverbtn');
    settime(obj);
    smsSend();
}
function settime(obj) { //发送验证码倒计时
    if (countdown == 0) { 
    	obj.removeClass('noverbtn');
        obj.attr('disabled',false);
        obj.val("获取验证码");
        countdown = 60; 
        return;
    } else { 
        obj.attr('disabled',true);
        obj.val("重新发送(" + countdown + ")");
        countdown--; 
    } 
    setTimeout(function() { 
    	settime(obj) 
	},1000) 
}

function pwdVerify(){
	var regex = new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,16})$');
	if(regex.test($("#passwd").val())){
		$("#errorPasswd").html("");
		$("#passwdAff").val("Y");
	}else{
		$("#errorPasswd").html("密码必须大于6位小于16位，并且含字母与数字");
	}
}

function pwdCheck(){
	if($(".passwd").val()!=$(".passwd2").val()){
		$("#errorPasswd").html("2次输入的密码不一致！");
	}else{
		$("#errorPasswd").html("");
		$("#passwd2Aff").val("Y");
	}
}

function dosubmit(){
	if($("#phoneAff").val()=="Y" && $("#vercodeAff").val()=="Y" && $("#passwdAff").val()=="Y" && $("#passwd2Aff").val()=="Y"){
		$("#regist").submit();
	}
	
}
