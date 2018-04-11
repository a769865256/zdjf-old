(function() {
    /* 判断密码格式是否正确 */
    var regex = new RegExp('^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,16})$');
    //ios readonly失效问题解决；
    $('.in_open input').on('focus', function() {
        $(this).trigger('blur');
    });
    var flag = true;
    $("body").delegate("#btn:not([disabled])", "click", function() {
        var acc = $("#acc").val();
        var pwd = $("#pwd").val();
        if (!acc) {
            $com.app.tip("请输入手机号");
            return;
        }
        if (!pwd) {
            $com.app.tip("请输入密码");
            return;
        }
        if(!regex.test(pwd)){
            $com.app.tip("密码必须为6-16位，数字和英文的组合");
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
    $("body").delegate("#register", "click", function() {
        var acc = $("#acc").val(),
            pwd = $("#pwd").val(),
            code = $("#code").val(),
            invite = '';
        if (!acc) {
            $com.app.tip("请输入手机号");
            return;
        }
        if (!pwd) {
            $com.app.tip("请输入密码");
            return;
        }
        if(!regex.test(pwd)){
            $com.app.tip("密码必须为6-16位，数字和英文的组合");
            return;
        }
        if (!code) {
            $com.app.tip("请输入验证码");
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
            }, 10);
        }, function() {
        }]);
    });
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
}());