<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="we_rt">
        <!-- 账户设置-添加银行卡 -->
        <div class="add_bank">
            <div class="add_bank_head">
                <h3><a href="<%=path%>/toSetBankCard.action" class="set_bank_back">银行卡设置</a><i class="iconfont">&#xe697;</i><a href="javascript:" class="active">添加银行卡</a></h3>
            </div>
            <div class="go_add_bank_box">
                <ul>
                    <li>*该绑定卡将默认为充值提现唯一银行卡</li>
                    <li><h3>实名信息</h3></li>
                    <li>
                        <span>姓名：<i class="name">${user.real_name}</i></span>
                        <span>身份证号：<i class="IDcard">${user.idcard_no}</i></span>
                    </li>
                </ul>
                <ul class="addBankBox">
                    <li><h3>添加银行卡</h3></li>
                    <form action="<%=path %>/addBankCardAdvance.action" method="post">
                    <li>
                        <div class="layui-form">
                            <div class="layui-form-item">
                                <label class="layui-form-label">所属银行：</label>
                                <div class="layui-inline">
                                    <div class="layui-input-inline" style="width: 336px;">
                                        <select name="bank_accounts" id="bank_accounts" class="bank_accounts" lay-filter="myselect1">
                                            <option value="">请选择银行</option>
                                            <c:forEach items="${bankList}" var="bank">
                                                <option value="${bank.num}">${bank.name}</option>
                                                <optgroup label="单笔限额${bank.single_max}万元，单卡单日限额${bank.day_max}万元"></optgroup>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">银行卡号：</label>
                                <div class="layui-input-block">
                                    <input type="text" name="bankCard" id="bankCard" required  lay-verify="required" placeholder="请输入银行卡号" autocomplete="off" class="layui-input" style="width: 336px;">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">开 户 地：</label>
                                <div class="layui-input-inline">
                                    <select name="province" id="province" lay-filter="myselect2">
                                        <option value="">请选择省</option>
                                        <c:forEach items="${province}" var="p">
                                            <option value="${p}">${p}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="layui-input-inline">
                                    <select name="city" id="city">
                                        <option value="">请选择市</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">手机号码：</label>
                                <div class="layui-input-block">
                                    <input type="text" id="phone" required  lay-verify="required" placeholder="此手机号需为银行卡预留手机号" autocomplete="off" class="layui-input" style="width: 336px;float: left;">
                                    <input type="hidden" name="ticket" id="ticket" >
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">验 证 码：</label>
                                <div class="layui-input-block">
                                    <input type="text" id="valid_code" required  lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input" style="width: 220px;float: left;">
                                    <input type="button" class="phoneCd" id="" value="获取验证码" onclick="sendsms()">
                                    <div class="layui-form-mid layui-word-aux" style="margin: 0 10px;">验证码60S内有效，请注意查收</div>
                                </div>
                               <%-- <div class="error" style="display: none;">请输入有效验证码</div>
                                <div class="error">验证码无效，重新发送</div>--%>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <a href="javascript:" class="layui-btn ljtj">立即添加</a>
                                    <a href="<%=path%>/toSetBankCard.action" class="layui-btn layui-btn-primary">暂不添加</a>
                                </div>
                            </div>
                        </div>
                    </li>
                    </form>
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
    var path = '<%=path%>';
    function alertErrMsg(msg) {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.open({
                type: 1,
                skin: 'addBank_bat',
                title: ['','background:#ffffff;text-align:center;font-size: 18px;color: #1088F1;padding: 0;height:60px;line-height: 60px;'],
                area: ['320px','220px'],
                content:'<div class="sub_box"><i class="iconfont" style="font-size: 50px;margin-top: -32px;display: block;color: #FF5959;">&#xe61c;</i><p style="color: #FF5959;font-size: 18px;margin: 10px 32px 0;">'+msg+'<br><span style="color: #333;font-size: 14px;"></span></p><p style="margin: 16px 10px;"><a href="javascript:" class="qd">确定</a></p></div>',
                success: function(){
                    $('.qd').click(function(){
                        layer.closeAll();
                    });
                }
            });
        });
    }
    layui.use('form', function(){
        var form = layui.form;

        /*监听省select选择*/
        form.on('select(myselect2)', function(data2){
            var province = data2.value;
            if (province == null || province.length == 0) {
                $("#city").empty();
                $("#city").append("<option value=''>请选择市</option>");
                form.render('select');
            } else {
                $.ajax({
                    type: 'POST',
                    url: path+'/getCity.json',
                    data: 'province='+province,
                    dataType: 'json',
                    success: function(resData){
                        var json=eval(resData);
                        $("#city").empty();
                        $.each(json,function(index,item){
                            var s1=json[index];
                            $("#city").append("<option value='"+s1+"'>"+s1+"</option>");
                            form.render('select');
                        });
                    },
                    error: function(){
                        alert('请求错误，请重新再次请求！');
                        // 即使加载出错，也得重置
                    }
                });
            }
        });
    });
    /*实名认证的名字显示后一，其他换成星星*/
    var newName = $(".name").html().replace(/.(?=.)/g, '*');
    $(".name").html(newName);
    /*实名认证的身份证显示前三后四，其他换成星星*/
    var newIDcard = $(".IDcard").html().replace(/^(\d{3})\d+(\w{4})$/, '$1 **** $2');
    $(".IDcard").html(newIDcard);

    function sendsms(){
        var bank_accounts = $('#bank_accounts').val();
        if (bank_accounts == null || bank_accounts.length == 0){
            alertErrMsg('请选择开户行！');
            return false;
        }
        var bankCard = $('#bankCard').val();
        if(bankCard == null || bankCard.length ==0) {
            alertErrMsg('请输入银行卡号');
            return false;
        }
        var province = $('#province').val();
        if (province == null || province.length == 0) {
            alertErrMsg('请选择开户地！');
            return false;
        }
        var city = $('#city').val();
        var phone = $('#phone').val();
        var pattern = /^1[34578]\d{9}$/;
        if(phone == null || phone.length ==0 || !pattern.test(phone) ){
            alertErrMsg('请输入正确的手机号！');
            return false;
        }
        $.ajax({
            type: 'POST',
            url: path+'/sendAddBankCard.json',
            data: {
                province:$('#province').val(),
                city:$('#city').val(),
                bankCard:$('#bankCard').val(),
                bank_accounts:$('#bank_accounts').val(),
                phone:$('#phone').val()
            },
            dataType: 'json',
            success: function(data){
                if(data.status==100){
                    $("#ticket").val(data.content);
                    sendemail();
                } else {
                    alertErrMsg(data.content);
                }
            },
            error: function(){
                alertErrMsg('请求错误，请重新再次请求！');
                // 即使加载出错，也得重置
            }
        });
    }
    /*获取验证码倒计时*/
    var countdown=60;
    function sendemail(){
        var obj = $(".phoneCd");
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
</script>
</body>
</html>