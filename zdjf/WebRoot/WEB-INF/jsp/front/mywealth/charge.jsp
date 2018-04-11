<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zdjf.domain.fund.UserFundStat" %>
<%@ page import="com.zdjf.domain.user.UserBank" %>
<%@ page import="com.zdjf.domain.fund.Bank" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
	UserFundStat userFundStat = (UserFundStat)request.getAttribute("userFundStat");
	UserBank ub = (UserBank)request.getAttribute("userBank");
	Bank bank = (Bank)request.getAttribute("bank");
	String card = ub.getBank_no().substring(ub.getBank_no().length()-4, ub.getBank_no().length());
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
		<p>为了确保您的正常投资和资金安全，请开通新浪支付存钱罐。<a href="javascript:">立即开通</a></p>
	</div>
</div>
<div class="wealth">
	<jsp:include page="./left.jsp"></jsp:include>
	<div class="we_rt">
		<!-- 我的财富 -->

		<!-- 我的财富》提现 -->

		<!-- 我的财富》充值 -->
		<div class="recharge">
			<div class="charge_head">
				<h3>充值</h3>
			</div>
			<div class="retab">
				<div class="tabbtn">
					<a class="active" href="javascript:">快捷支付</a>
					<a href="javascript:">网银支付</a>
				</div>
				<div class="tabbox">
					<div class="tab">
						<form method="post" id="kjpay" action="<%=path%>/pay/fastPay.action" target="_blank">
							<ul class="kjzf">
								<li class="dqye">
									<span>当前余额:</span>
									<span class="money"><i><%=userFundStat.getBalance() %></i>元</span>
									<input type="hidden" id="balance" value="<%=userFundStat.getBalance() %>">
								</li>
								<li>
									<span>充值金额:</span>
									<input type="text" id="amount" name="amount" onkeyup="balances(this);" placeholder="请输入充值金额"/>
									<span class="money">充值后金额<i id="now_balance"><%=userFundStat.getBalance() %></i>元</span>
									<input type="hidden" id="czh_balance"/>
								</li>
								<li class="bankbox">
									<span>充值银行:</span>
									<input type="text">
									<p>单笔充值限额<i class="danbi"><%=bank.getSingle_max() %></i>元,每日充值限额<i class="meiri"><%=bank.getDay_max() %></i>元</p>
									<div class="banklogo">
										<img src="<%=path%>/images/front/img/bank/<%=bank.getNum() %>.png" alt="<%=bank.getName() %>">
										<span>尾号<i><%=card %></i></span>
										<input type="hidden" name="pay_type" value="1" />
										<input type="hidden" id="fundsId" name="fundsId" value="" />
									</div>
								</li>
								<%--<li>
                                    <span>验证码:</span>
                                    <input class="message" id="valid_code" name="valid_code" type="text" placeholder="请输入短信验证码">
                                    <input type="button" class="" id="chongzhi" value="获取验证码" />
                                </li>--%>
								<li>
									<a href="javascript:" date-cz="1" class="rc_btn">充值</a>
								</li>
							</ul>
						</form>
					</div>

					<div class="tab" style="display: none;">
						<form method="post" id="wypay" action="<%=path%>/pay/wyRecharge.action" target="_blank">
							<ul class="wyzf">
								<li class="dqye">
									<span>当前余额:</span>
									<span class="money"><i><%=userFundStat.getBalance() %></i>元</span>
								</li>
								<li>
									<span>充值金额:</span>
									<input type="text" id="wy_amount" onkeyup="wy_balance(this.value)" name="wy_amount" placeholder="请输入充值金额">
									<span class="money">充值后金额<i id="wy_now_balance"><%=userFundStat.getBalance() %></i>元</span>
								</li>
								<input type="hidden" id="bank_alias" name="bank_alias">
								<li class="xzbank">
									<span class="banktlt">选择银行:</span>
									<div class="banklist">
										<a class="bankname" href="javascript:"><input type="hidden" class="bank_hidden" value="ABC"><img src="<%=path%>/images/front/img/bank/ABC.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:"><input type="hidden" class="bank_hidden" value="BOC"><img src="<%=path%>/images/front/img/bank/BOC.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:"><input type="hidden" class="bank_hidden" value="BOS"><img src="<%=path%>/images/front/img/bank/BOS.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:"><input type="hidden" class="bank_hidden" value="CCB"><img src="<%=path%>/images/front/img/bank/CCB.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:"><input type="hidden" class="bank_hidden" value="CEB"><img src="<%=path%>/images/front/img/bank/CEB.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:"><input type="hidden" class="bank_hidden" value="CIB"><img src="<%=path%>/images/front/img/bank/CIB.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:"><input type="hidden" class="bank_hidden" value="CITIC"><img src="<%=path%>/images/front/img/bank/CITIC.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:" style="display: none;"><input type="hidden" class="bank_hidden" value="CMB"><img src="<%=path%>/images/front/img/bank/CMB.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:" style="display: none;"><input type="hidden" class="bank_hidden" value="CMBC"><img src="<%=path%>/images/front/img/bank/CMBC.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:" style="display: none;"><input type="hidden" class="bank_hidden" value="GDB"><img src="<%=path%>/images/front/img/bank/GDB.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:" style="display: none;"><input type="hidden" class="bank_hidden" value="HXB"><img src="<%=path%>/images/front/img/bank/HXB.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:" style="display: none;"><input type="hidden" class="bank_hidden" value="ICBC"><img src="<%=path%>/images/front/img/bank/ICBC.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:" style="display: none;"><input type="hidden" class="bank_hidden" value="PSBC"><img src="<%=path%>/images/front/img/bank/PSBC.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:" style="display: none;"><input type="hidden" class="bank_hidden" value="SPDB"><img src="<%=path%>/images/front/img/bank/SPDB.png" alt=""><span class="curtain"></span></a>
										<a class="bankname" href="javascript:" style="display: none;"><input type="hidden" class="bank_hidden" value="SZPAB"><img src="<%=path%>/images/front/img/bank/SZPAB.png" alt=""><span class="curtain"></span></a>
										<a href="javascript:" class="morebank">更多银行<i class="iconfont">&#xe697;</i></a>
									</div>
									<div>
										<%if(userFundStat.getUser_id()==98999 || userFundStat.getUser_id()==88888888){ %>
										<select id="account_identity" name="account_identity">
											<option value="101">红包户</option>
											<option value="102">收益户</option>
											<option value="103">风险准备金</option>
										</select>
										<%} %>
									</div>
								</li>
								<li>
									<a href="javascript:" class="rc_btn">充值</a><span class="btntlt">目前暂不支持信用卡支付</span>
								</li>
							</ul>
						</form>
						<!-- <table>
                            <tr>
                                <th>卡类型</th>
                                <th>用户类型</th>
                                <th>单笔限额</th>
                                <th>每日限额</th>
                            </tr>
                            <tr>
                                <td rowspan="3">储蓄卡</td>
                                <td>$100</td>
                                <td>$100</td>
                                <td>$100</td>
                            </tr>
                            <tr>
                                <td>$100</td>
                                <td>$100</td>
                                <td>$100</td>
                            </tr>
                            <tr>
                                <td>$100</td>
                                <td>$100</td>
                                <td>$100</td>
                            </tr>
                        </table> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- content end-->

<!-- 充值成功 -->
<div id="prompt" style="display:none;">
	<h3>充值成功<a href="javascript:" class="pro_close iconfont">&#xe64e;</a></h3>
	<div class="pro_con">
		<!-- <p>本次充值金额为<span>30.00</span>元</p>
        <p>账户可用余额为<span>60.00</span>元</p> -->
	</div>
	<div class="probtn">
		<a href="<%=path%>/myFunds.action?type=11">查看充值记录</a>
		<a href="<%=path%>/product/list.action">立即投资</a>
	</div>
</div>

<!-- 充值失败弹窗提示 -->
<div id="charge_err" style="display:none;">
	<h3>充值失败<a href="javascript:" class="pro_close iconfont">&#xe64e;</a></h3>
	<div class="pro_con" id="cz_err">
		<!-- <p>本次充值金额为<span>30.00</span>元</p>
        <p>账户可用余额为<span>60.00</span>元</p> -->
	</div>
	<div class="probtn">
		<a href="<%=path%>/myFunds.action?type=11">查看充值记录</a>
		<a href="<%=path%>/investGuide/2/help.action">充值遇到问题</a>
	</div>
</div>




<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path%>/module/jquery/jquery.min.js"></script>
<script src="<%=path%>/module/sticky-header.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script src="<%=path%>/module/echarts/echarts.min.js"></script>
<script src="<%=path%>/js/front/wealth.js"></script>
<script type="text/javascript">
    function balances(obj){
        obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
        obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字
        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
        var mybalance = parseFloat($("#balance").val());
        if($("#amount").val() == ''){
            $("#now_balance").html(mybalance);
            $("#czh_balance").val(mybalance);
        } else {
            $("#now_balance").html((mybalance+parseFloat(obj.value)));
            $("#czh_balance").val((mybalance+parseFloat(obj.value)));
        }
    }

    var path = '<%=path %>';

    function advance (){
        var valid_code = $("#valid_code").val();
        if($("#fundsId").val()==null || $("#fundsId").val()=='' || valid_code==null || valid_code==''){
            return ;
        }else{
            $.ajax({
                type: 'POST',
                url: path+'/pay/recharge/advance.json',
                data: 'valid_code='+valid_code+"&fundsId="+$("#fundsId").val(),
                dataType: 'json',
                success: function(data){
                    if(data.status==100){
                        $("#fundsId").val(data.content);
                    }else{
                        alert(data.content);
                    }
                },
                error: function(){
                    alert('请求错误，请重新再次请求！');
                }
            });
        }
    }

    function wy_balance(va){
        if (isNaN(va)) {
            $("#wy_amount").val("");
            return ;
        }
        newVal = va.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
        newVal = va.replace(/^\./g,""); //验证第一个字符是数字
        newVal = va.replace(/\.{2,}/g,"."); //只保留第一个, 清除多余的
        newVal = va.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        newVal = va.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
        $("#wy_amount").val(newVal);
        var mybalance1 = parseFloat($("#balance").val());
        if($("#wy_amount").val() == ''){
            $("#wy_now_balance").html(mybalance1);
        } else {
            $("#wy_now_balance").html((mybalance1+parseFloat(newVal)));
        }
    }

    function wySend(){
        if($("#bank_alias").val()==null || $("#bank_alias").val()==''){
            return ;
        }
        var amount = $("#wy_amount").val();
        if(amount==null || amount==''){
            return ;
        }
        $("#wypay").submit();
    }

</script>
</body>
</html>