$(function() {
    /**
     * 注册成功弹窗
     **/
    var reg_url = getUrlParam('type') || '';
    if (reg_url == 1) {
        var reg = '<div class="alert reg-success J_alert hide">' +
            '    <div class="alert-darkbg"></div>' +
            '    <div class="eject">' +
            '        <!--<div class="icon-close-white J_close"></div>-->' +
            '        <div class="con">' +
            '            <b><b id="regRedbag">--</b>红包</b>和<b><b id="regTicket">--</b>加息券</b>已放入您的账户中，<br>实名认证后就能使用啦！' +
            '        </div>' +
            '        <div>' +
            '            <a href="/chinapnr/req/register" target="_blank" class="use-btn">立即认证</a>' +
            '            <a href="/" class="close-btn">暂不认证</a>' +
            '        </div>' +
            '    </div>' +
            '</div>';
        $('.reg-success.J_alert').removeClass('hide');
    }
    $('.use-btn').on('click', function() {
        location.href = Infs.normal.url;
    })

    // 获取链接参数，写入cookie
    var inviteSource = getUrlParam('inviteSource') || '';
    if (inviteSource)
    	document.cookie =" inviteSource =" + inviteSource  + "; path=/" 
   
//    function setCookie(name, value, seconds) {
//    	 seconds = seconds || 0;   
//    	 var expires = "";
//    	 if (seconds != 0 ) {      //设置cookie生存时间
//    	 var date = new Date();
//    	 date.setTime(date.getTime()+(seconds*1000));
//    	 expires = "; expires="+date.toGMTString();
//    	 }
//    	 document.cookie = name+"="+escape(value)+expires+"; path=/";   //转码并赋值
//    	}
    /**
     * login-fixed加载animate
     **/
    $('.login-fixed').animate({ top: 50 }, 500);
    $('.login-fixed').animate({ top: 5 }, 150);
    $('.login-fixed').animate({ top: 20 }, 100);


    //banner浮框
    var newhand;
    if (UserPhone != '') {
        $.ajax({
            type: Infs.userBalace.type,
            url: Infs.userBalace.url,
            data: { phone: UserPhone, access_token: Sid },
            dataType: "json",
            success: function(data) {
                if (data.respCode == 0) {
                    var balance = data.record.totalAssets;
                    var closeEye = data.record.richHide == 1 ? true : false;
                    newhand = data.record.realNameAuth;
                    $('.J_balance').text(commafy(balance)).data('num', balance);
                    if (closeEye) {
                        $('.J_eye').addClass('eye-close');
                        $('.J_balance').text('******')
                    }
                    //首次注册弹出框信息
                    $('#regRedbag').text(parseInt(data.record.giftMoney) + '元');
                    $('#regTicket').text(data.record.couponCount + '张');
                } else if (data.respCode == 1) {
                    loginOut()
                } else {
                    $('.J_eye').remove();
                }
                if (newhand != 1) {
                    $('.J_loginNoAutonym').addClass('show').siblings('.login-area').removeClass('show');
                } else {
                    $('.J_login').addClass('show').siblings('.login-area').removeClass('show');
                }
            }
        });
    }
    //退出登录
    function loginOut() {
        var domain = "." + window.location.host;
        delCookie("phone", "/", domain);
        delCookie("sid", "/", domain);
        location.reload();
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

    function setCookie(c_name, value, expiremins) {
        var exdate = new Date();
        exdate.setTime(exdate.getTime() + expiremins * 60 * 1000);
        var domain = '.' + window.location.host;
        document.cookie = c_name + "=" + escape(value) + ((expiremins == null) ? "" : ";expires=" + exdate.toGMTString()) + ";domain=" + domain;
    }


    /**
     * banner切换
     **/
    var Slide = {
        swarp: null,
        smain: null,
        smenu: null,
        currIndex: 0,
        len: 0,
        tim: 500,
        time: 5000,
        timer: null,
        init: function(ps) {
            var slide = this;
            if (ps.smain == null || ps.smain == undefined || ps.smain.length < 2) return;
            slide.smain = ps.smain, slide.smenu = ps.smenu, slide.len = slide.smain.length, slide.pre = slide.len - 1;

            if (ps.swarp) {
                slide.swarp = ps.swarp;
                slide.swarp.hover(function() {
                    window.clearInterval(slide.timer);
                }, function() {
                    slide.timer = window.setTimeout(function() { slide.autoPlay(); }, slide.time);
                });
            }
            if (slide.smenu) {
                slide.smenu.hover(function() {
                    slide.currIndex = $(this).index();
                    clearTimeout(slide.timer);
                    slide.changePlay(slide.currIndex);
                });
            }
            slide.timer = window.setTimeout(function() { slide.autoPlay(); }, slide.time);
        },
        changePlay: function(index) {
            var slide = this;
            // slide.smain.eq(index).fadeIn(slide.tim).siblings().fadeOut(slide.tim);
            slide.smain.eq(index).stop().siblings().stop();
            slide.smain.eq(index).animate({ opacity: "1" }, 400).css('z-index', '1').siblings().animate({ opacity: "0" }, 400).css({ 'z-index': '0' });
            slide.smenu.eq(index).addClass('curr').siblings().removeClass('curr');
        },
        autoPlay: function() {
            var slide = this;
            clearTimeout(slide.timer);
            slide.changePlay(slide.currIndex);
            slide.currIndex++;
            if (slide.currIndex >= slide.len) {
                slide.currIndex = 0;
            };
            slide.timeout = setTimeout(function() { slide.autoPlay(); }, slide.time);
        }
    };
    $.ajax({
        type: Infs.banner.type,
        url: Infs.banner.url,
        data: { currPage: 1, pageSize: 10 },
        dataType: "json",
        // async: false,
        success: function(data) {
            if (data.respCode == 0) {
                if (data.total == 0) {
                    $('#slide-warp').html('<div class="no-record">暂无banner图片</div>');
                    return
                }
                $("#slide-warp .slide-main ul, #slide-warp .slide-menu ul").html('');
                $(data.records).each(function(index, item) {
                    var sh = '<li style="opacity: 0; z-index: 0; background: url({0}); background-position: top center; background-repeat: no-repeat;" alt="{1}"> <a href="{2}" target="_blank"></a></li>';
                    var li = '<li></li>';
                    if (index == 0) {
                        sh = '<li style="opacity: 1; z-index: 1; background: url({0}); background-position: top center; background-repeat: no-repeat;" alt="{1}"> <a href="{2}" target="_blank"></a></li>';
                        li = '<li class="curr"></li>';
                    }
                    $("#slide-warp .slide-main ul").append(
                        sh.format(
                            decodeURIComponent(item.imageUrl) + '?t=20160616',
                            item.alt,
                            decodeURIComponent(item.hrefUrl)
                        )
                    );
                    $("#slide-warp .slide-menu ul").append(li);
                });
                Slide.init({
                    swarp: $('#slide-warp'),
                    smain: $('.slide-main li'),
                    smenu: $('.slide-menu li')
                });
            } else {
                $('#slide-warp').html('<div class="no-record">' + data.respDesc + '</div>');
            }
        }
    });

    /**
     * banner金额显隐
     **/
    $('.J_eye').on('click', function() {
        var e = $(this);
        if (e.hasClass('eye-close')) {
            var num = $('.J_balance').data('num');
            e.removeClass('eye-close');
            $('.J_balance').removeClass('close').text(commafy(num));
        } else {
            e.addClass('eye-close');
            $('.J_balance').addClass('close').text('******');
        }
        changeRichHide();
    })

    function changeRichHide() {
        var option = {
            type: Infs.balaceShow.type,
            url: Infs.balaceShow.url,
            data: { access_token: Sid },
            success: function(data) {
                if (data.respCode == 1) {
                    loginOut()
                }
            }
        };
        $.ajax(option);
    }


    /**
     * 圣贤讲堂
     **/
    // $.ajax({
    //     type: Infs.classRoom.type,
    //     url: Infs.classRoom.url,
    //     data: {currPage: 1, pageSize:5},
    //     dataType: "json",
    //     success: function(data){
    //         if(data.respCode == 0){
    //             if(data.total == 0){
    //                 $('#classRoom').html('<div class="no-record">暂无内容</div>');
    //                 return
    //             }
    //             $("#classRoom").html('');
    //             $(data.records).each(function(index, item){
    //                 var sh = '<a href="{0}" target="_blank" class="overflow">{1}</a>';
    //                 if(index > 3){
    //                     return;
    //                 }
    //                 $("#classRoom").append(
    //                     sh.format(
    //                         Infs.normal.url + '/about/classDetail.html?id=' + item.id,
    //                         item.title
    //                     )
    //                 );
    //             });
    //         }else{
    //             $('#classRoom').html('<div class="no-record">'+ data.respDesc +'</div>');
    //         }
    //     }
    // });

    /**
     * 首页进度条
     **/
    $(window).scroll(function() {
        scrollBar('.J_skillbar');
    });

    function scrollBar(e) {
        $(e).each(function(index, element) {
            var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0; //兼容各版本
            var heig = $(this).offset().top - $(window).height() - scrollTop;
            if (heig < 0) {
                $(this).find('.skillbar-bar').animate({
                    width: $(this).attr('data-percent')
                }, 3000);
                $(this).find('.little-ball').animate({
                   'margin-left': $(this).attr('data-percent')
                }, 3000);
                $(this).removeClass('J_skillbar');
            }
        });
    }

    /**
     * 产品列表
     **/
    $.ajax({
        type: Infs.product.type,
        url: Infs.product.url,
        data: { currPage: 1, pageSize: 7 },
        dataType: "json",
        success: function(data) {
            if (data.respCode == 0) {
                if (data.total == 0) {
                    $('#productList').html('<div class="no-record">暂无产品</div>');
                    return
                }
                $("#productList").html('');
                var incomeData = 0;
                var firstIncomeData;
                $(data.records).each(function(index, item) {
                    if (item.status == 4) {
                        incomeData = item.income > incomeData ? item.income : incomeData;
                    }

                    if (index == 0) {
                        firstIncomeData = item.income;
                        $("#proFirst").html('');

                        var sh = '<div class="info">' +
                            '    <div class="name">' +
                            '        <span class="name-num overflow"><a href="{21}">{8}</a></span>' +
                         
                            '    </div>' +
                            '    <span class="pro-tip">{22}元起投</span>' +
                            '    <span class="pro-tip">{24} 起息</span>' +
                            '    <span class="pro-tip green {23}">预告</span>' +
                            '    <span class="pro-tip green {12}">新手专享</span>' +
                            '</div>' +
                            '<div class="info-detail {25}">' +
                            '    <ul>' +
                            '        <li class="pic"><a href="{21}"><img src="{5}" alt=""></a></li>' +
                            '        <li class="day profit">' +
                            '            <p class="text-orange"><span class="ttfont">{1}</span>%<span class="add {28}"><b class="ttfont">+{29}</b>%</span></p>' +
                            '            <p class="tit text-gray">年化收益</p>' +
                            '        </li>' +
                            '        <li class="day">' +
                            '            <p><span class="ttfont">{13}</span>天</p>' +
                            '            <p class="tit text-gray">收益天数</p>' +
                            '        </li>' +
                            '        <li class="day balance">' +
                            '            <p><span class="ttfont">{16}</span>元</p>' +
                            '            <p class="tit text-gray">{15}</p>' +
                            '        </li>' +
                            '        <li class="bar-btn">' +
                            '            <a href="{21}" class="go-cash {18}">立即投资</a>' +
                            '            <a href="{21}" class="countdown light-red-btn {19}" data-begintime="{4}">' +
                            '                <i class="iconfont">&#xe603;</i>距上线' +
                            '                <span class="hours">--</span>:' +
                            '                <span class="minutes">--</span>:' +
                            '                <span class="seconds">--</span>' +
                            '            </a>' +
                            '            <a href="{21}" class="btn-dark {20}">{3}</a>' +
                            '        </li>' +
                            '    </ul>' +
                            '</div>' +
                            '<div class="percent text-gray font-arial">' +
                            '    <div class="skillbar J_skillbar {26}" data-percent="{27}%">' +
                            '        <div class="skillbar-bar"></div><span class="little-ball"></span>' +
                            '    </div>' +
                            '<span>{7}%</span></div>';

                        $("#proFirst").append(
                            sh.format(
                                index, //0
                                (parseFloat(item.income) - parseFloat(item.platform_interest)).toFixed(2), //1  年化收益(00.00)
                                item.product_id, //2  产品id
                                item.status_text, //3  进行状态
                                item.will_issue_time, //4  预募集剩余时间
                                picUrlChange(decodeURIComponent(item.photo)), //5  标的图片地址
                                item.product_name, //6  标名
                                item.speed, //7  募集进度#.00
                                item.product_code, //8  标的代码(第几期，新手标)
                                item.balance, //9  标的余额##.00
                                item.money, //10 项目总额
                                item.start_date, //11 开始日期
                                item.isFresh == 1 ? '' : 'hides', //12 是否新手标
                                item.incomeDays, //13 收益期限
                                item.status, //14 标的状态4、投资中 5、履约中6、已还款7、满标8、留标  、31、预募集
                                proBalance(item.status), //15 可投金额or项目总额
                                (item.status == 5 || item.status == 6 || item.status == 7 || item.status == 8) ? commafy(parseInt(item.money)) : commafy(parseInt(item.balance)), //16 标的余额##.00,格式化
                                item.status_text == '履约中' ? '已售罄' : item.statusTxt, //17 标的状态字典值
                                item.status == 4 ? '' : 'hides', //18 按钮显隐
                                item.status == 31 ? 'J_countdown' : 'hides', //19 按钮显隐
                                item.status != 4 && item.status != 31 ? '' : 'hides', //20 按钮显隐
                                link(item.product_id, item.product_type), //21 产品url
                                parseInt(item.pay_min), //22 最小投资金额
                                item.status == 31 ? '' : 'hides', //23 预告icon
                                item.income_method == 1 ? 'T+0' : 'T+1', //24 计息方式
                                (item.status == 5 || item.status == 6) ? 'text-gray' : '', //25 履约中&&已还款 红字变灰
                                item.speed == 100 ? 'skill-over' : '', //26 进度条投标满颜色变更
                                item.speed != 100 ? item.speed : 0, //27 进度条范围
                                parseFloat(item.platform_interest) == 0 ? 'hides' : '', //28 附加利息显隐
                                parseFloat(item.platform_interest).toFixed(2) //29 附加额度
                            )
                        );
                        return;
                    }
                    if (index > 6) {
                        return; //过滤超出首页所需条数
                    }

                    var sh = '<li class="mid-hover">' +
                        '    <div class="li-lay">' +
                        '        <div class="title"><p><a href="{21}">{8}</a><i class="newuser-icon {12} {26}">新手专享</i><i class="newuser-icon green {22}">预告</i></p>' +
                        '        </div>' +
                        '        <div class="pic">' +
                        '            <a href="{21}"><img src="{5}" alt=""></a>' +
                        '        </div>' +
                        '        <div class="dat text-gray">' +
                      
                        '                <p class="text-orange"><b class="ttfont">{1}</b><span>%</span><span class="add {27}"><b class="ttfont">+{28}</b>%</span></p>' +
                        '                <p>预计年化收益</p>' +
                     
                            
                        
                        '        </div>' +
                        '        <div class="progressbar">' +
                        '            <div class="percent text-gray">' +
                 
                    
                        '            </div>' +
                        '            <div class="skillbar J_skillbar {24}" data-percent="{25}%">' +
                        '                <div class="skillbar-bar"></div>' +
                        '            </div><span class="little-ball"></span>' +
                      
                        '        </div>' +
                        '                <span class="balance">' +
                        '                    {7}%' +
                        '                </span>' +
                        '     <div class="left"><p>{13}天</p><p>收益天数</p></div>'+
                        '    <div class="right"><p>{16}元</p><p>{15}</p></div>'+
                        '    <div class="btn">' +
                        '        <a href="{21}" class=" {18}">立即投资</a>' +
                        '        <a href="{21}" class="countdown btn-green {19}" data-begintime="{4}">' +
                     
                        '             距上线： <span class="hours">--</span>:' +
                        '            <span class="minutes">--</span>:' +
                        '            <span class="seconds">--</span>' +
                        '        </a>' +
                        '        <a href="{21}" class="btn-dark {20}">{3}</a>' +
                        '    </div>' +
                        '    </div>' +
                        ' <div class="arrow-text">加息</div>'+
                        '</li>';

                    $("#productList").append(
                        sh.format(
                            index, //0
                            (parseFloat(item.income) - parseFloat(item.platform_interest)).toFixed(2), //1  年化收益(00.00)
                            item.product_id, //2  产品id
                            item.status_text, //3  进行状态
                            item.will_issue_time, //4  预募集剩余时间
                            picUrlChange(decodeURIComponent(item.photo)), //5  标的图片地址
                            item.product_name, //6  标名
                            item.speed, //7  募集进度#.00
                            item.product_code, //8  标的代码(第几期，新手标)
                            item.balance, //9  标的余额##.00
                            item.money, //10 项目总额
                            item.start_date, //11 开始日期
                            item.isFresh == 1 ? '' : 'hides', //12 是否新手标
                            item.incomeDays, //13 收益期限
                            item.status, //14 标的状态4、投资中 5、履约中6、已还款7、满标8、留标  、31、预募集
                            proBalance(item.status), //15 可投金额or项目总额
                            (item.status == 5 || item.status == 6 || item.status == 7 || item.status == 8) ? commafy(parseInt(item.money)) : commafy(parseInt(item.balance)), //16 标的余额##.00,格式化
                            item.status_text == '履约中' ? '已售罄' : item.statusTxt, //17 标的状态字典值
                            item.status == 4 ? '' : 'hides', //18 按钮显隐
                            item.status == 31 ? 'J_countdown' : 'hides', //19 按钮显隐
                            item.status != 4 && item.status != 31 ? '' : 'hides', //20 按钮显隐
                            link(item.product_id, item.product_type), //21 产品url
                            item.status == 31 ? '' : 'hides', //22 预告icon
                            (item.status == 5 || item.status == 6) ? 'text-gray' : '', //23 履约中&&已还款 红字变灰
                            item.speed == 100 ? 'skill-over' : '', //24 进度条投标满颜色变更
                            item.speed != 100 ? item.speed : 0, //25 进度条范围
                            item.status == 31 ? 'icon-move' : '', //26 预告和新手图标同时存在
                            parseFloat(item.platform_interest) == 0 ? 'hides' : '', //27 附加利息显隐
                            parseFloat(item.platform_interest).toFixed(2) //28 附加额度
                        )
                    );
                });
                //赋值最高收益率
                if (incomeData != 0) {
                    $('#income').text(incomeData);
                } else {
                    $('#income').text(firstIncomeData);
                }

                //无法读取图片
                noPic();
                //倒计时
                if ($('.J_countdown').length != 0) {
                    countdown()
                }

                //进度条
                scrollBar('.J_skillbar');
            } else {
                $('#productList').html('<div class="no-record">' + data.respDesc + '</div>');
            }
        },
        error:function(data) {
        	alert(111);
        }
    });

    function proBalance(e) {
        if (e == 5 || e == 6) {
            return '项目总额'
        } else {
            return '可投金额'
        }
    }
    //修改图片url解码问题>>"+"替换为空格
    function picUrlChange(url) {
        return url.replace(/\+/g, " ");
    }
    // 跳转到详情页链接
    function link(id, type) {
        if (type == 2) {
            return Infs.normal.url + '/zdjf/product/detail-zt.html?productId=' + id;
        } else {
            return Infs.normal.url + '/zdjf/product/detail.html?productId=' + id;
        }
    }

    /**
     * 获取公告信息
     **/
    $.ajax({
        type: Infs.notice.type,
        url: Infs.notice.url,
        data: { currPage: 1, pageSize: 4 },
        dataType: "json",
        success: function(data) {
            if (data.respCode == 0) {
                if (data.total == 0) {
                    $('#noticeList').html('<div class="no-record">暂无公告</div>');
                    return
                }
                $("#noticeList").html('');
                $(data.records).each(function(index, item) {
                    var sh = '<a href="{0}" target="_blank"><span class="overflow">{1}</span><span class="date">{2}</span></a>';
                    $("#noticeList").append(
                        sh.format(
                            Infs.normal.url + '/about/noticeDetail.html?id=' + item.id,
                            item.title,
                            item.createTime,
                            item.content
                        )
                    );
                });
                if (data.total > 2) {
                    setInterval(function() {
                        ClassAutoScroll(".J_notice");
                    }, 5000);
                }
            } else {
                $('#noticeList').html('<div class="no-record">' + data.respDesc + '</div>');
            }
        }
    });
    //导航滑动效果
    function ClassAutoScroll(obj) {
        var e = $(obj).find("p");
        e.animate({ marginTop: "-40px" }, 600, function() {
            $(obj).find("a:first").appendTo(e);
            e.css({ marginTop: "0px" })
        });
    }

    /**
     * 获取媒体信息
     **/
    $.ajax({
        type: Infs.media.type,
        url: Infs.media.url,
        data: { currPage: 1, pageSize: 5 },
        dataType: "json",
        success: function(data) {
            if (data.respCode == 0) {
                if (data.total == 0) {
                    $('#news').html('<div class="no-record">暂无内容</div>');
                    return
                }
                $("#news").html('');
                $(data.records).each(function(index, item) {
                    if (index > 1) {
                        return
                    }
                    var sh = '<li>' +
                        '<p><a href="{0}" target="_blank"><img src="{1}" alt=""></a></p>' +
                        '<p class="news-con"><a href="{0}" target="_blank">{3}</a></p>' +
                        '</li>';
                    // var sh = '<a href="{0}" target="_blank" class="item-tit overflow" title="{3}"><i>•</i>{2}</a>';
                    $("#news").append(
                        sh.format(
                            Infs.normal.url + '/about/mediaDetail.html?id=' + item.id, //0 链接路径
                            item.imageUrl, //1 图片
                            item.title, //2 标题
                            item.desc //3 介绍
                        )
                    );

                });
                //无法读取图片
                noPic();
                //限制介绍字数
                $('.news-con a').each(function() {
                    wordLimits($(this), 72);
                })
            } else {
                $('#news').html('<div class="no-record">' + data.respDesc + '</div>');
            }
        }
    });


    /**
     * 投资列表
     **/
    // $.ajax({
    //     type: Infs.investList.type,
    //     url: Infs.investList.url,
    //     data: {currPage: 1, pageSize:10},
    //     dataType: "json",
    //     success: function(data){
    //         if(data.respCode == 0){
    //             if(data.total == 0){
    //                 $('#investList').html('<div class="no-record">暂无内容</div>');
    //                 return
    //             }
    //             $("#investList").html('');
    //             $(data.records).each(function(index, item){
    //                 var sh = '<li><div class="time">{4}</div>{0}投资了<span class="font-padding">{1}</span>元<a href="{2}" class="text-gray">{3}</a></li>';
    //                 $("#investList").append(
    //                     sh.format(
    //                         item.mrOrMs,
    //                         commafy(item.amount),
    //                         Infs.normal.url + '/product/detail.html?productId=' + item.productId,
    //                         item.productName,
    //                         item.payTime
    //                     )
    //                 );
    //             });
    //             setInterval(function(){
    //                 AutoScroll(".J_investList");
    //             }, 5000);
    //         }else{
    //             $('#investList').html('<div class="no-record">'+ data.respDesc +'</div>');
    //         }
    //     }
    // });
    // //导航滑动效果
    // function AutoScroll(obj){
    //     var e = $(obj).find("ul");
    //     e.animate({marginTop:"-37px"},600, function(){
    //         $(obj).find("li:first").appendTo(e);
    //         e.css({marginTop:"0px"})
    //     });
    // }


    // 飞机位移
    setTimeout(function() {
        $('#fixCode').animate({ left: "3%" }, 800, function() {
            $('.fix-ticket').addClass('move')
        });
    }, 600);



    /**
     * 图片读取不到转换成默认图片
     **/
    function noPic() {
        $('img').each(function() {
            var dfd = $.Deferred();
            $(this).bind('load', function() {
                dfd.resolve();
            }).bind('error', function() {
                $(this).attr('src', "https://www.zdjfu.com/index/images/public/no-pic.png")
            })
        })
    }

    /**
     * 倒计时
     **/
    function countdown() {
        $(".J_countdown").each(function() {
            var time = $(this).data('begintime');
            if (time < 0) {
                return;
            }
            $(this).downCount({
                date: time
            })
        })
    }

    /**
     * 拓展
     **/
    //扩展方法获取url参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
    //金额千分位化
    function commafy(num) {
        if ((num + "").trim() == "") {
            return "";
        }
        if (isNaN(num)) {
            return "";
        }
        num = num + "";
        if (/^.*\..*$/.test(num)) {
            var pointIndex = num.lastIndexOf(".");
            var intPart = num.substring(0, pointIndex);
            var pointPart = num.substring(pointIndex + 1, num.length);
            intPart = intPart + "";
            var re = /(-?\d+)(\d{3})/
            while (re.test(intPart)) {
                intPart = intPart.replace(re, "$1,$2")
            }
            num = intPart + "." + pointPart;
        } else {
            num = num + "";
            var re = /(-?\d+)(\d{3})/
            while (re.test(num)) {
                num = num.replace(re, "$1,$2")
            }
        }
        return num;
    }
    //重新定义trim方法，兼容IE7/8
    String.prototype.trim = function() {
            return this.replace(/(^\s*)|(\s*$)/g, "");
        }
        //文字超出省略
    function wordLimits(st, e) { //st >> class或id，e >> 所需限制的字数
        if (GetLength($(st).text()) > e) {
            var okFont = cutstr($(st).text(), e);
            $(st).text(okFont);
            return;
        }
    }

    function GetLength(str) {
        var realLength = 0,
            len = str.length,
            charCode = -1;
        for (var i = 0; i < len; i++) {
            charCode = str.charCodeAt(i);
            if (charCode >= 0 && charCode <= 128) realLength += 1;
            else realLength += 2;
        }
        return realLength;
    };

    function cutstr(str, len) {
        var str_length = 0;
        var str_len = 0;
        str_cut = new String();
        str_len = str.length;
        for (var i = 0; i < str_len; i++) {
            a = str.charAt(i);
            str_length++;
            if (escape(a).length > 4) {
                //中文字符的长度经编码之后大于4  
                str_length++;
            }
            str_cut = str_cut.concat(a);
            if (str_length >= len) {
                str_cut = str_cut.concat("...");
                return str_cut;
            }
        }
        //如果给定字符串小于指定长度，则返回源字符串；  
        if (str_length < len) {
            return str;
        }
    }


});