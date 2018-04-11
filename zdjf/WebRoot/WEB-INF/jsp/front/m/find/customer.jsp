<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui"/>
    <title>正道金服-发现</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/css/front/m/style.css"/>
    <link rel="stylesheet" href="<%=path%>/css/front/m/index.css"/>
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path%>/css/front/m/find.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<div class="find">
	<div class="header"><a class="back" href="<%=path%>/appStatic/find.action?isBack=1"></a>客服帮助</div>
	<div class="f_cust">
		<div class="cust">
			<div class="cubox">
				<a href="../recharge/recharge.html">
					<img src="<%=path%>/images/front/m/help/help_01.png" alt="">
					<p>充值</p>
				</a>
			</div>
			<div class="cubox">
				<a href="../recharge/with.html">
					<img src="<%=path%>/images/front/m/help/help_02.png" alt="">
					<p>提现</p>
				</a>
			</div>
			<div class="cubox">
				<a href="javascript:">
					<img src="<%=path%>/images/front/m/help/help_03.png" alt="">
					<p>投资理财</p>
				</a>
			</div>
		</div>
		<div class="cust">
			<div class="cubox">
				<a href="javascript:">
					<img src="<%=path%>/images/front/m/help/help_04.png" alt="">
					<p>账户设置</p>
				</a>
			</div>
			<div class="cubox">
				<a href="javascript:">
					<img src="<%=path%>/images/front/m/help/help_05.png" alt="">
					<p>银行存管</p>
				</a>
			</div>
			<div class="cubox">
				<a href="javascript:">
					<img src="<%=path%>/images/front/m/help/help_06.png" alt="">
					<p>优惠福利</p>
				</a>
			</div>
		</div>
		<div class="ask">
			<h3>猜你想问</h3>
			<ul>
				<li>
					<h4><span class="active">申请提现后多久到账？</span><div class="a_h4"></div></h4>
					<div class="txt">
						<div class="diamond">
							<img src="<%=path%>/images/front/m/help/lx.png">
						</div>
						<div class="p_box">
							<p>上海银行在收到您的提现申请后，将于T+1个自然日内将资金转入您本人绑定的银行卡账户中。具体到账时间请参考：<br>
						（1）平日的提现申请，银行当天处理， T+1个自然日到账。<br>
						（2）法定节假日的提现申请，到账日统一以平台公告为准。<br>
						（3）由于银行处理业务的时间不同，具体到账时间请以银行为准，如有疑问，请致电您的提现银行。</p>
						</div>
					</div>
				</li>
				<li>
					<h4><span>项目到期后何时回款？</span><div class="a_h4"></div></h4>
					<div class="txt">
						<div class="diamond">
							<img src="<%=path%>/images/front/m/help/lx.png">
						</div>
						<div class="p_box">
							<p>平台回款是在项目到期日当天下午17：00左右，不区分节假日。具体以上海银行操作为准，不排除有特殊情况导致回款晚于该时间（特殊情况包括但不限于系统延迟、支付操作问题、网络故障、借款人还款时间过晚等）。<br>
							注：按照相关协议规定，只要在还款当天还清，均不属于逾期。如晚于该时间，请各位用户耐心等待。</p>
						</div>
					</div>
				</li>
				<li>
						<h4><span>正经值有什么用？</span><div class="a_h4"></div></h4>
						<div class="txt">
							<div class="diamond">
								<img src="<%=path%>/images/front/m/help/lx.png">
							</div>
							<div class="p_box">
								<p>（1）经值可在投资时抵扣现金，抵扣比例为1%，正经值不得与红包券同时使用。<br>
								（2）正经值可兑换等额红包券，可用作投资抵扣现金。<br>
								（3）正经值可兑换加息券，可提升用户投资项目的年化收益
								</p>
							</div>
						</div>
				</li>
				<li>
					<h4><span>如何获得正经值？</span><div class="a_h4"></div></h4>
					<div class="txt">
						<div class="diamond">
							<img src="<%=path%>/images/front/m/help/lx.png">
						</div>
						<div class="p_box">
							<p>正经值可通过参与正道金服推出的各项活动、邀请好友、每日签到等多种渠道获得。<br>
							1、邀请好友：好友注册并投资，您可获得好友投资额2‰的正经值奖励。<br>
							2、参与正道金服推出的各项活动，赢取正经值。例如：抢标活动。<br>
							3、每日签到领正经值，周末签到、投资签到还可享受翻倍奖励。
							</p>
						</div>
					</div>
				</li>
				<li>
					<h4><span>充值到账时间需要多久？</span><div class="a_h4"></div></h4>
					<div class="txt">
						<div class="diamond">
							<img src="<%=path%>/images/front/m/help/lx.png">
						</div>
						<div class="p_box">
							<p>正常情况下，平台充值实时到账，若银行提示扣款但账户金额未增加，可能是同一时间段充值人数过多，请稍后刷新页面，如刷新后仍未到账，请联系客服：400-690-9898。</p>
						</div>
					</div>
				</li>
				<li>
					<h4><span>如何解除、更换已绑定的银行卡？</span><div class="a_h4"></div></h4>
					<div class="txt">
						<div class="diamond">
							<img src="<%=path%>/images/front/m/help/lx.png">
						</div>
						<div class="p_box">
							<p>
								如您需要解除/更换已绑定的银行卡，请进入“银行卡管理”页面，确保账户可用余额=0，如账户可用余额>0时，请先申请提现，再解绑。<br>
								解绑具体操作：进入个人中心-我的账户-银行卡管理-银行卡解绑。填写银行卡预留手机号码，获取验证码，正确填写后即可成功解绑。解绑成功后可重新绑定新银行卡。<br>
								若还有其他疑问，请致电客服热线400-690-9898或通过在线客服咨询。
							</p>
						</div>
					</div>
				</li>
				<li>
					<h4><span>为何注册时手机收不到验证码？</span><div class="a_h4"></div></h4>
					<div class="txt">
						<div class="diamond">
							<img src="<%=path%>/images/front/m/help/lx.png">
						</div>
						<div class="p_box">
							<p>（1）请检查您的手机号输入是否正确；<br>
（2）若手机安装了拦截软件请查看拦截记录/垃圾短信，确认是否被拦截； <br>
（3）短信运营商通道可能会出现阻塞情况，短信会出现延时或失败的情况，请耐心等候； <br>
（4）若没有收到验证码短信，可以点击“语音验证码”接听语音验证电话； <br>
（5）若仍然收不到验证码，请联系客服电话400-690-9898；
</p>
						</div>
					</div>
				</li>
				<li>
					<h4><span>为什么会充值失败？</span><div class="a_h4"></div></h4>
					<div class="txt">
						<div class="diamond">
							<img src="<%=path%>/images/front/m/help/lx.png">
						</div>
						<div class="p_box">
							<p>1、银行卡余额不足以支付充值金额：请更换银行卡充值<br>
							2、交易密码输入有误：请输入正确的交易密码<br>
							3、充值金额超过银行单日限额：请联系银行查询及更改支付额度<br>
							4、您的银行卡未开通网上银行：请前往银行柜台或通过银行官网进行开通<br>
							5、银行卡出现过期、挂失、作废等情况：请更换银行卡或将绑定银行状态恢复正常<br>
							若以上方法如仍未能解决您的问题，请联系客服寻求帮助（服务热线：400-690-9898）
							</p>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="phone">
		<img src="<%=path%>/images/front/m/help/help_phone.jpg" alt="">
		<div class="p_txt">
			<p>联系客服</p>
			<p>每日9:00-19:00</p>
		</div>		
	</div>
</div>
<script>
(function(){
	var _w,_zoom,_hd, _orientationChange,_doc = document,__style = _doc.getElementById("_zoom");
	__style || (_hd = _doc.getElementsByTagName("head")[0],__style=_doc.createElement("style"),_hd.appendChild(_style));
	_orientationChange = function(){
	_w    = _doc.documentElement.clientWidth || _doc.body.clientWidth;
	_zoom = _w / 750;
	__style.innerHTML = ".zoom {zoom:" + _zoom + ";-webkit-text-size-adjust:auto !important;text-size-adjust:auto !important;}";
	// var rem = _w / 10;
	// window.document.documentElement.style.fontSize = rem + 'px';
	};
	__style.setAttribute("zoom",_zoom);
	_orientationChange();
	window.addEventListener("resize",_orientationChange,false);
})();
</script>
<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/find.js"></script>
</body>
</html>

