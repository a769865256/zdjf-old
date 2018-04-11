<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>正道金服</title>
    <!-- reset/iconfont -->
    <link rel="stylesheet" href="<%=path%>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path%>/css/front/index.css">
    <link rel="stylesheet" href="<%=path%>/css/front/wealth.css">
</head>
<body>
<!-- header -->
<div class="header">
    <jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="open hide">
    <div class="tlt">
        <p>为了确保您的正常投资和资金安全，请开通上海银行存管账户。<a href="javascript:">立即开通</a></p>
    </div>
</div>
<div class="wealth">
    <jsp:include page="./left.jsp"></jsp:include>
    <input type="hidden" id="path" value="<%=path%>"/>
    <div class="we_rt">
        <!-- 账户设置-解绑 -->
        <div class="unbund">
            <div class="unbund_head">
                <h3><a href="<%=path%>/toSetBankCard.action" class="set_bank_back">银行卡设置</a><i class="iconfont">&#xe697;</i><a href="javascript:" class="active">解绑银行卡</a></h3>
            </div>
            <div class="unbox">
                <ul>
                    <li style="margin: 0 20px 10px 20px;">
                        <span>*解绑银行卡前请将账户可用余额清零</span>
                        <span style="display: none;color: #1088F1;">*解绑银行卡前提是用户账户可用金额为零</span>
                    </li>
                    <li>
                        <span class="bankimg"><span class="bankIocn" name="${userBanks[0].bank_alias}"></span></span>
                        <span class="banknum">${userBanks[0].bank_no}</span>
                    </li>
                    <li>
                        <span>预留手机号:</span>
                        <input type="text" placeholder="请输入银行卡预留手机号" id="phone" onkeyup="cleanMsg('phoneErr')">
                        <input type="hidden" id="ticket">
                        <p id="phoneErr"></p>
                    </li>
                    <li>
                        <span>动态验证码:</span>
                        <input type="text" placeholder="请输入验证码" id="valid_code" onkeyup="cleanMsg('codeErr')">
                        <input type="button" class="obtain" id="" value="获取验证码" onclick="sendsms()">
                        <span class="obtaintxt">验证码60秒内有效，请注意查收</span>
                        <p id="codeErr"></p>
                    </li>
                    <li><a class="sub" href="javascript:">提交</a><a class="cancel" href="<%=path%>/toSetBankCard.action">取消</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- content end-->

<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path%>/module/jquery/jquery.min.js"></script>
<script src="<%=path%>/module/sticky-header.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script src="<%=path%>/js/front/wealth.js"></script>
<script type="text/javascript">
    /*银行卡显示后四，其他换成星星*/
    if ($(".banknum").html().length > 0) {
        var newBankCard = $(".banknum").html().replace(/^(.)+(\w{4})$/g,"****$2");
        $(".banknum").html(newBankCard);
    }

    function sendsms() {
        var path = $("#path").val();
        var phone = $('#phone').val();
        var pattern = /^1[34578]\d{9}$/;
        if(phone == null || phone.length ==0 || !pattern.test(phone) ){
            $("#phoneErr").html("请输入正确的手机号！");
            return false;
        } else {
            $("#phoneErr").html("");
        }
        $.ajax({
            type: 'POST',
            url: path+'/sendUnboundBankCard.json',
            data: {
                phone:phone
            },
            dataType: 'json',
            success: function(data){
                layui.use('layer', function(){
                    var layer = layui.layer;
                    if(data.status==100){
                        $("#ticket").val(data.content);
                        sendemail();
                    } else if(data.status==101) {
                        $("#phoneErr").html(data.content)
                    } else if(data.status==102) {
                        layer.open({
                            type: 1,
                            skin: 'bank_ts',
                            title: ['温馨提示','background:#ffffff;text-align:center;font-size: 18px;color: #1088F1;padding: 0;height:60px;line-height: 60px;'],
                            area: ['320px','220px'],
                            content:'<div class="sub_box"><p>您的账户可用余额为¥'+data.content+'元,账户可用余额为零再进行解绑</p><p style="margin: 16px 10px;"><a href="javascript:" class="qd">确定</a></p></div>',
                            success: function(){
                                $('.qd').click(function(){
                                    layer.closeAll();
                                });
                            }
                        })
                    } else {
                        layer.open({
                            type: 1,
                            skin: 'bank_bat',
                            title: ['','background:#ffffff;text-align:center;font-size: 18px;color: #1088F1;padding: 0;height:60px;line-height: 60px;'],
                            area: ['320px','220px'],
                            content:'<div class="sub_box"><i class="iconfont" style="font-size: 50px;margin-top: -32px;display: block;color: #FF5959;">&#xe61c;</i><p style="color: #FF5959;font-size: 18px;margin: 10px 32px 0;">发送短信验证码失败<br><span style="color: #333;font-size: 14px;">'+data.content+'</span></p><p style="margin: 16px 10px;"><a href="javascript:" class="qd">确定</a></p></div>',
                            success: function(){
                                $('.qd').click(function(){
                                    layer.closeAll();
                                });
                            }
                        })
                    }
                });
            },
            error: function(){
                $("#phoneErr").html('请求错误，请重新再次请求！');
                // 即使加载出错，也得重置
            }
        });
    }
    /*获取验证码倒计时*/
    var countdown=60;
    function sendemail(){
        var obj = $(".obtain");
        obj.addClass('noverbtn');
        settime(obj);
    }
    function settime(obj) { //发送验证码倒计时
        if (countdown == 0) {
            obj.removeClass('noverbtn');
            obj.attr('disabled',false);
            obj.val("获取验证码");
            countdown =60;
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

    function cleanMsg(t) {
        $("#"+t).html("");
    }
</script>
</body>
</html>