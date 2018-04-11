(function() {
    // 活动规则显示隐藏
    $('.rule_box').click(function(){
        $('.jm_kt_alerts_sign').show();
    })
    $('.jm_kt_mask,.jm_kt_close_sign').click(function(){
        $('.jm_kt_alerts_sign').hide();
    })
    //发送验证码倒计时
    var countdown=60;
    function settime(obj) {
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
    /* 判断密码格式是否正确 */
    var regex = new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,16})$');
    var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-8]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1})|(16[6]{1})|(19[8-9]{1}))+\d{8})$/;
    var flag = true;
    $("body").delegate("#btn:not([disabled])", "click", function() {
        var acc = $("#acc").val();
        if (!acc) {
            $com.app.tip("请输入手机号");
            return;
        }
        if(!myreg.test(acc)){  
            $com.app.tip("请输入有效的手机号码！");
            return;  
        } 
        var $this = $(this);
        if(flag){
            flag = false;
            $com.app.ajax({
                phone : acc,
                type : 1,
                ip : $com.IP,
                sign : $.md5(acc + "1" + $com.IP + $com.KEY),
                $TYPE : "post",
                $URI : "/m/sms/send.json"
            }, [function(data) {
                $this.removeClass("verbtn");
                settime($this);
                flag = true;
            }, function() {
                flag = true;
            }]);
        }
    });
    console.log($com.uri.getUrlQuery().invite_source);//推广渠道
    $("body").delegate("#register", "click", function() {
        var acc = $("#acc").val(),
            code = $("#code").val(),
            pwd = $("#pwd").val(),
            invite = '';
        if (!acc) {
            $com.app.tip("请输入手机号");
            return;
        }
        if(!myreg.test(acc)){  
            $com.app.tip("请输入正确的手机号码！");
            return;  
        }
        if(!code){
            $com.app.tip("请输入短信验证码");
            return;
        }
        if(!pwd){
            $com.app.tip("密码不能为空");
            return;
        }
        if(!regex.test(pwd)){
            $com.app.tip("密码必须为6-16位，数字和英文的组合");
            return;
        }
        $com.app.ajax({
            phone : acc,
            passwd : pwd,
            ip : $com.IP,
            verif : code,
            reg_source : 4,
            inviter_phone : invite,
            invite_source : $com.uri.getUrlQuery().invite_source,
            host_role : $com.uri.getUrlQuery().host_role,
            sign : $.md5(acc + code + $com.IP + $com.KEY),
            $TYPE : "post",
            $URI : "/m/user/register.json"
        }, [function(data) {
            $com.cookie.set("user_name", acc);
            $com.cookie.del("__DIS__");
            setTimeout(function() {
                upload_app();
            }, 3000);
            $com.app.tip("注册成功，3s后跳转APP下载页面");
        }, function() {
            $com.app.tip("网络错误，请重试");
        }]);
    });
    //查询平台数据
    $com.app.ajax({
        $TYPE : "post",
        $URI : "/info/platformData.action"
    }, function(data) {
        $('#day_yy').html(data.omDays);
    });
}());