//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
var curWwwPath = window.document.location.href;
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName = window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
//获取域名（主机地址），如： http://localhost:8083
var localhostPaht = curWwwPath.substring(0, pos);
//获取带"/"的项目名，如：/uimcardprj
var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
//获取项目根目录
var rootDirectory = localhostPaht + projectName;
//获取域名（主机地址），如：localhost:8083
var hostUrl =  window.location.host;
if(hostUrl == 'www.zdjfu.com'){
	var rootUrl = '';
}else{
	var rootUrl = '/zdjf';
}
var hostRootUrl = hostUrl + rootUrl;
//提醒用户开通账户弹框,jm_kt_entrance类名可以开启入口
var jm_kt_alert = function(){
    var jm_kt_str = '<div class="jm_kt_alert"><div class="jm_kt_mask"></div><div class="jm_kt_box"><div class="jm_kt"><img src="'+rootUrl+'/static/zdjf_app/static/img/jm_kt.png" alt=""></div><a href="javascript:;" id="dialog_btn">立即开通</a><div class="jm_kt_close"><img src="'+rootUrl+'/static/zdjf_app/static/img/close.png" alt=""></div></div></div>';
    $('body').prepend(jm_kt_str);
}
$('body').delegate('.jm_kt_close','click',function () {
    $('.jm_kt_alert').remove();
})
$('.jm_kt_entrance').click(function () {
    jm_kt_alert();
})
// 充值提现输入验证码
var jm_yz_alert = function () {
    var jm_yz_str = '<div class="jm_password_pay_alert" style="display:none;"><div class="jm_password_pay_mask"></div><div class="jm_password_pay_box"><div class="jm_password_pay_title">付款认证</div><div class="jm_password_pay_content">付款确认：本次交易需要短信验证，短信验证码已发送至<span id="phone">18334797684</span></div><div class="jm_password_pay"><input id="num1" data-next="num2" type="number" maxlength="1" /><input id="num2" data-next="num3" type="number" maxlength="1" /><input id="num3" data-next="num4" type="number" maxlength="1" /><input id="num4" data-next="num5" type="number" maxlength="1" /><input id="num5" data-next="num6" type="number" maxlength="1" /><input id="num6" data-next="finish" type="number" maxlength="1" /></div><div class="jm_password_pay_btn"><input type="button" id="paycode" value="重新获取"></div><div class="jm_password_pay_close"><img src="../../static/img/close.png" alt=""></div></div></div>';
    $('body').prepend(jm_yz_str);
}
//实名认证中途过程中用户中断认证弹框
var jm_smzd_alert = function (obj) {
    obj.click(function () {
        layer.open({
            title:'提示'
            ,content: '<img src="../../static/img/real/kt_no.png" alt="">'
            ,btn: ['确定', '取消']
            ,yes: function(index){
                location.reload();
                layer.close(index);
            }
        });
    })
}
//判断手机系统ios，android;0:iPhone 1:Android 通过app_upload_btn类名
function ismobile(test){
    var u = navigator.userAgent, app = navigator.appVersion;
    if(/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(navigator.userAgent))){
        if(window.location.href.indexOf("?mobile")<0){
            try{
                if(/iPhone|mac|iPod|iPad/i.test(navigator.userAgent)){
                    return '0';
                }else{
                    return '1';
                }
            }catch(e){}
        }
    }else if( u.indexOf('iPad') > -1){
        return '0';
    }else{
        return '1';
    }
};
var browser = {
    versions: function () {
        var u = navigator.userAgent, app = navigator.appVersion;
        return {         //移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase()
}
if (browser.versions.mobile) {//判断是否是移动设备打开。browser代码在下面
        var ua = navigator.userAgent.toLowerCase();//获取判断用的对象
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            //在微信中打开
        }
        if (ua.match(/WeiBo/i) == "weibo") {
            //在新浪微博客户端打开
        }
        if (ua.match(/QQ/i) == "qq") {
            //在QQ空间打开
        }
        if (browser.versions.ios) {
            //是否在IOS浏览器打开
        } 
        if(browser.versions.android){
            //是否在安卓浏览器打开
        }
} else {
      //在pc中打开  
}
// 用户下载app按钮
function upload_ismobile(app_upload_btn) {
    if(ismobile(1) == 0){
        app_upload_btn.click(function () {
            window.location.href = "https://itunes.apple.com/cn/app/id1335863998";
        })
    }else{
        app_upload_btn.click(function () {
            if (ua.match(/MicroMessenger/i) == "micromessenger") {
                //在微信中打开
                window.location.href = "http://android.myapp.com/myapp/detail.htm?apkName=com.hz.zdjfu.application&ADTAG=mobile";//应用宝链接
            }else{
                //在手机浏览器中打开
                window.location.href = "http://img.zdjfu.com/upload/app/zdjfu.apk";//浏览器链接
            }
            
        })
    }
}
upload_ismobile($('.app_upload_btn'));
// 用户下载app方法
function upload_app() {
    if(ismobile(1) == 0){
        window.location.href = "https://itunes.apple.com/cn/app/id1335863998";
    }else{
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            //在微信中打开
            window.location.href = "http://android.myapp.com/myapp/detail.htm?apkName=com.hz.zdjfu.application&ADTAG=mobile";//应用宝链接
        }else{
            //在手机浏览器中打开
            window.location.href = "http://img.zdjfu.com/upload/app/zdjfu.apk";//浏览器链接
        }
    }
}
// 获取地址栏参数方法
(function($){
    $.getUrlParam
        = function(name)
    {
        var reg
            = new RegExp("(^|&)"+
            name +"=([^&]*)(&|$)");
        var r
            = window.location.search.substr(1).match(reg);
        if (r!=null) return unescape(r[2]); return null;
    }
})(jQuery);