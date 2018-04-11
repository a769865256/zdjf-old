var _py = _py || [];
_py.push(['a', 'K684T..7hkEBJ_EO4VnZL10GoueAP']);
_py.push(['domain', 'stats.ipinyou.com']);
_py.push(['e', '']); -

function(d) {
    var s = d.createElement('script'),
        e = d.body.getElementsByTagName('script')[d.body.getElementsByTagName('script').length - 1];
    e.parentNode.insertBefore(s, e),
        f = 'https:' == location.protocol;
    s.src = (f ? 'https' : 'http') + '://' + (f ? 'fm.ipinyou.com' : 'fm.p0y.cn') + '/j/adv.js';
}(document);

$(function() {

    //定义全局的借口
    var API_ROOT = {
      //  Host: "https://" + window.location.host,
        Host: "http://" + window.location.host,
        Active: ""
    };

    //本地接口api用
    API_ROOT.Active = API_ROOT.Host.indexOf('web') >= 0 ? (API_ROOT.Host + '/api') : API_ROOT.Host;

    Infs = {
        normal: {
            url: API_ROOT.Host //normal
        },
        userBalace: {
            url: API_ROOT.Active + "/web/user/userTotalAssets", //个人信息
            type: "post"
        },
        balaceShow: {
            url: API_ROOT.Active + "/web/user/changeRichHide", //金额显隐
            type: "post"
        },
        banner: {
            url: API_ROOT.Active + "/mob/common/bannerList?webSite=1", //banner大图
            type: "post"
        },
        notice: {
            url: API_ROOT.Active + "/mob/common/noticeList", //公告列表
            type: "post"
        },
        classRoom: {
            url: API_ROOT.Active + "/mob/common/noticeList?type=2",
            type: "post"
        },
        media: {
            url: API_ROOT.Active + "/mob/common/noticeList?type=6", //媒体报道
            type: "post"
        },

        product: {
            url: "http://localhost:8080/zdjf/web/product/list_v2.action", //产品列表
            type: "post"
        },
        investList: {
            url: API_ROOT.Active + "/web/common/topten", //投资列表
            type: "post"
        },
        couponCount: {
            url: API_ROOT.Active + "/mob/common/couponCount", //个人优惠券数量
            type: "post"
        }
    };

    UserPhone = getCookie("phone") || ''; // 全局变量
    Sid = getCookie("sid") || ''; // sid

    // 获取未读消息条数
    if (UserPhone != '') {
        $.ajax({
            type: "POST",
            url: API_ROOT.Active + "/center/msgCount",
            dataType: "json",
            success: function(result) {
                if (result.errcode == 0) {
                    if (result.data == 0) {
                        return false;
                    }
                    $('#navMsgCount i').text(result.data);
                    $('#navMsgCount').removeClass('hides');
                }
            }
        });
    }

    /**
     * 获取删除cookie
     **/
    function getCookie(name) {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) {
            return unescape(arr[2]);
        } else {
            return null;
        }
    }

    function delCookie(name, path, domain) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + "; path=" + path + "; domain=" + domain + ";expires=" + exp.toGMTString();
    }


    /** 
     *判断登陆 
     **/
    $(".nav-right").removeClass("hide");
    if (UserPhone != '') {
        $(".user").removeClass("hide");
        $(".tologin").addClass("hide");
        //$(".J_phone").text(phone);
        var hidePhone = UserPhone.substring(0, 3) + "****" + UserPhone.substring(7, 11);
        $(".J_phone").text(hidePhone);
        $(".hi").text("Hi," + hidePhone);
        $('.J_phoneNumber').text(hidePhone);
        // 读取优惠券数
        $.ajax({
            type: Infs.couponCount.type,
            url: Infs.couponCount.url,
            data: { phone: UserPhone },
            dataType: "json",
            success: function(data) {
                if (data.respCode == 0) {
                    var num = data.record;
                    if (num == 0)
                        return;
                    $('#couponCount .num').text(num);
                    $('#couponCount').show();
                }
            }
        });
    }


    // 清除cookie实现退出登录
    $('.J_loginOut').on('click', function() {
        var domain = "." + window.location.host;
        delCookie("phone", "/", domain);
        delCookie("sid", "/", domain);
        $.ajax({
            type: "POST",
            url: Infs.normal.url + "/user/loginout",
            success: function(data) {
                location.reload();
            }
        });
        return false;
    })


    // 字符串format方法
    String.prototype.format = function(args) {
        var result = this;
        if (arguments.length > 0) {
            if (arguments.length == 1 && typeof(args) == "object") {
                for (var key in args) {
                    if (args[key] != undefined) {
                        var reg = new RegExp("({" + key + "})", "g");
                        result = result.replace(reg, args[key]);
                    }
                }
            } else {
                for (var i = 0; i < arguments.length; i++) {
                    if (arguments[i] != undefined) {
                        var reg = new RegExp("({)" + i + "(})", "g");
                        result = result.replace(reg, arguments[i]);
                    }
                }
            }
        }
        return result;
    }


    //bottom二维码切换
    $('#bottomCode p').hover(function() {
        var e = $(this).index() - 2;
        $(this).addClass('curr').siblings('p').removeClass('curr').siblings('div').addClass('hide');
        $('#bottomCode div').eq(e).removeClass('hide');
    })


    /**
     * 浮动框
     **/
    // function boxScroll(){
    //     if($(this).scrollTop()==0){
    //         $("#fixBox").css('top', 597);
    //     }
    //     if( 0 < $(this).scrollTop() < 567){
    //         $("#fixBox").css('top', 597 - $(this).scrollTop());
    //     }
    //     if($(this).scrollTop() >= 567){
    //         $("#fixBox").css('top', 30);
    //     }
    // }
    // boxScroll();
    // window.onscroll = function(){
    //     boxScroll();
    // }

    //返回顶部
    if ($(this).scrollTop() == 0) {
        $("#toTop").hide();
    } else {
        $("#toTop").show();
    }
    $(window).scroll(function(event) {
        /* Act on the event */
        if ($(this).scrollTop() == 0) {
            $("#toTop").fadeOut(300);
        }
        if ($(this).scrollTop() != 0) {
            $("#toTop").fadeIn(300);
        }
    });
    $("#toTop").click(function(event) {
        /* Act on the event */
        $("html,body").animate({
                scrollTop: "0px"
            },
            666)
    });


    // calculator
    $('.J_count-alert').on('click', function() {
            $('#countLay').removeClass('hide');
            $('#clYield').val('12.88');
            $('#clCapital').val('10000');
            $('#clDay').val('60');
            count();
        })
        // 关闭计算器
    $('.J_CloseCountLay').on('click', function() {
        $('#countLay').addClass('hide');
    })

    $('.J_count-submit').on('click', function() {
            count();
        })
        // 重置
    $('.J_count-reset').on('click', function() {
        $('#clYield').val('');
        $('#clCapital').val('');
        $('#clDay').val('');
        $('.J_recSxcf, .J_recBao, .J_recBank').animate({ height: '1px' }, 0).siblings('b').text('0.00');
    })

    function count() {
        $('.J_recSxcf, .J_recBao, .J_recBank').animate({ height: '1px' }, 0);
        var profit = $('#clYield').val(); // 年化收益
        var e = $('#clCapital').val(); // 投资本金
        var limit = $('#clDay').val(); // 收益天数
        var paySxcf = sxcfProfit(profit, e, limit);
        var payBao = alipayProfit(e, limit);
        var payBank = bankProfit(e, limit);
        if (paySxcf > payBao) {
            $('.J_recSxcf').animate({ height: '160px' }, 300).siblings('b').text(paySxcf);
            $('.J_recSxcfProfit').text(paySxcf).siblings('span').text(paySxcf + parseFloat(e));
            var heiBao = payBao / paySxcf * 160;
            $('.J_recBao').animate({ height: heiBao }, 300).siblings('b').text(payBao);
            var heiBank = payBank / paySxcf * 160;
            $('.J_recBank').animate({ height: heiBank }, 300).siblings('b').text(payBank);
        }
        if (paySxcf < payBao) {
            var heiSxcf = paySxcf / payBao * 160;
            $('.J_recSxcf').animate({ height: heiSxcf }, 300).siblings('b').text(paySxcf);
            $('.J_recSxcfProfit').text(paySxcf).siblings('span').text(paySxcf + parseFloat(e));
            $('.J_recBao').animate({ height: '160px' }, 300).siblings('b').text(payBao);
            var heiBank = payBank / payBao * 160;
            $('.J_recBank').animate({ height: heiBank }, 300).siblings('b').text(payBank);
        }
    }


    //某宝同期收益
    function sxcfProfit(profit, e, limit) {
        var pay = 0.00
        if (e > 0) {
            pay = profit / 100 * e / 360 * limit;
        }
        return parseFloat(pay.toFixed(2));
    }
    //某宝同期收益>>“2.5%*本金/365*产品期限”
    function alipayProfit(e, limit) {
        var pay = 0.00
        if (e > 0) {
            pay = 0.025 * e / 365 * limit;
        }
        return parseFloat(pay.toFixed(2));
    }
    //某宝同期收益>>“1.72%*本金/365*产品期限”
    function bankProfit(e, limit) {
        var pay = 0.00
        if (e > 0) {
            pay = 0.0172 * e / 365 * limit;
        }
        return parseFloat(pay.toFixed(2));
    }
})