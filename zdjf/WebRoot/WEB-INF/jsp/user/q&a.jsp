<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>常见问题-专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/notice.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
	<style>
		.ri_main{ width: 100%; margin: 10px 20px 0; padding-bottom: 30px;}
		.ri_main h2{ color:#0f8ec7; padding-bottom:5px;}
		.ri_main p{ pcolor:#424242; line-height:24px;}
		.ri_main img{ float:left; padding-right:24px;}
		.ri_main dl{ width:224px; float:left; margin-right:6px;}
		.ri_main ul{ margin-top:30px;}
		.ri_main li{ color:#545454; line-height:30px;}
		.ri{ float:right;}
		.q-title{
			line-height: 35px;
			color: #909090;
			font-size: 16px;
			margin: 20px 0 10px;
			font-weight: bold;
			text-indent: 15px;
		}
		.q-content{
			width: 100%;
			margin: 10px auto;
		}
		.q-content dl{
			width: 715px;
			float: none;
			padding: 15px;
			border-bottom: 1px solid #dcdcdc;
			padding-bottom: 5px;
		}
		.q-content dt {
			width: 100%;
			height: 35px;
			margin-right: 6px;
			line-height: 35px;
			font-size: 14px;
			color: #323232;
			cursor: pointer;
		}
		.q-content dd{
			line-height: 25px;
			padding:5px 0px 5px 10px;
			display: none;
			color: #646464;
		}
		.q-content span{
			color: #f20;
		}
		.q-content dd span.line{
			margin-left: 22px;
			color: #646464;
		}
		.q-content img{
			float: none;
			margin-top: 5px;
		}
		.q-content a{
			color: #0088CC;
		}
		.q-content a:hover{
			text-decoration: underline;
		}
	</style>
</head>

<body>
<jsp:include page="comm/header.jsp"></jsp:include>
<!-- detail -->
<div class="content notice clearfix">
	<!-- left -->
	<div class="left-nav">
		<a href="${selfSite}/zdjf/notice">平台公告</a>
		<a href="${selfSite}/zdjf/q&a" class="curr">常见问题</a>
		<div class="second-nav">
			<a href="${selfSite}/zdjf/q&a#login">•&nbsp;&nbsp;注册登录</a>
			<a href="${selfSite}/zdjf/q&a#deposit">•&nbsp;&nbsp;充值提现</a>
			<a href="${selfSite}/zdjf/q&a#invest">•&nbsp;&nbsp;投资问题</a>
			<a href="${selfSite}/zdjf/q&a#law">•&nbsp;&nbsp;法律法规</a>
		</div>
	</div>

	<!-- right -->
	<div class="right-con">
		<div class="title">常见问题</div>
		<div class="line-red"></div>
		<!-- list -->
		<div class="ri_main">
			<div class="q-title" id="login">注册登录</div>
			<div class="q-content">
				<dl>
					<dt>注册的时候收不到短信验证码？</dt>
					<dd>短信到达时间一般在10—30秒之间，烦请耐心等待。<br/>
						若超时未收到验证码，请确认短信没有被手机安全软件拦截。<br/>
						如仍旧无法收取请通过客服：400-690-9898或在线客服查询。</dd>
				</dl>
				<dl>
					<dt>如何修改登录密码？</dt>
					<dd>登录后您可以在“我的财富”-“安全中心”页面中修改您的登录密码。</dd>
				</dl>
				<dl>
					<dt>手机号可以修改吗？</dt>
					<dd>为了保障您的资金安全，平台暂不支持手机号更改。</dd>
				</dl>
				<dl>
					<dt>忘记登录密码怎么办？</dt>
					<dd>在登录页面点击“忘记密码”，跳转到密码重置页面，成功验证您的手机号后即可重新设置登录密码。</dd>
				</dl>
			</div>
			<div class="q-title" id="deposit">充值提现</div>
			<div class="q-content">
				<dl>
					<dt>充值/提现需要手续费吗？</dt>
					<dd>正道金服平台无论充值提现，暂不向用户收取任何费用。</dd>
				</dl>
				<dl>
					<dt>充值没有到账怎么办？</dt>
					<dd>充值成功后，资金会立即到账，请确认是否支付成功。<br/>
						如超出入账时间，请致电客服热线400-690-9898或通过在线客服咨询。</dd>
				</dl>
				<dl>
					<dt>平台提现需要多久才能到账？</dt>
					<dd>用户在正道金服平台所有资金均由汇付天下托管。当用户在平台发起提现申请后，汇付天下将于T+1个工作日内（不含法定节假日）将资金转入其本人绑定的银行卡账户中。具体到账时间请参考：<br/>
						（1）工作日21:30前申请提现，T+1个工作日到账；21:30之后申请提现，顺延一个工作日到账。<br/>
						（2）非工作日的申请提现，统一在第一个工作日处理，第二个工作日到账。<br/>
						（3）由于银行处理业务的时间不同，具体到账时间请以银行为准。<br/>
						若提现过程中遇到任何问题，请拨打我们的客服热线：400-690-9898。<br/>
						PS：由于汇付天下规则调整，网银充值金额不支持当天提现，可提现余额=可用余额-当天充值金额。</dd>
				</dl>
				<dl>
					<dt>怎么更换提现绑定的银行卡？</dt>
					<dd>1、用户如果没有通过快捷支付充值过，只需要进入提现界面绑定新的银行卡，则新绑定的银行卡即默认为新的提现到账银行卡。<br/>
						2、用户如果通过快捷支付充值，则快捷支付的银行卡即为提现到账的银行卡。若需要更换银行卡则需要进入汇付天下更换。打开汇付天下账户登录界面（https://c.chinapnr.com/p2puser/），输入汇付天下账户用户名、登录密码进行登录，在“我的银行卡”-“快捷银行卡”中，进行解绑操作，输入银行预留手机号，填写短信验证码，即可解绑。然后重新进入平台绑定银行卡。<br/>
						若有疑问，请致电客服热线400-690-9898或通过在线客服咨询。</dd>
				</dl>
				<dl>
					<dt>提现不到账怎么办？</dt>
					<dd>建议您通过ATM机、银行柜台或银行客服电话确认是否到账。<br/>
						如果在等待时间范围内，请您耐心等待下；如果已经超出等待时间范围，请您致电客服热线400-690-9898或通过在线客服咨询。</dd>
				</dl>
				<dl>
					<dt>快捷支付充值与网银支付充值有何区别？</dt>
					<dd>用户开通快捷支付后，可直接登录汇付天下使用绑定的银行卡输入交易密码完成充值。<br/>
						用户使用网银支付时，需进入到汇付天下，登录到网上银行充值。</dd>
				</dl>
				<dl>
					<dt>绑定快捷充值银行卡后，还能用其它银行卡充值吗？</dt>
					<dd>用户可正常使用借记卡选择网银支付进行充值。</dd>
				</dl>
				<dl>
					<dt>绑定快捷充值银行卡对用户提现有什么影响？</dt>
					<dd>为了保障用户资金的安全性，快捷充值规定每个用户只能绑定一张银行卡，且一旦该用户绑定了快捷卡，则此卡同时自动设置为默认唯一取现卡，原绑定的取现卡将被覆盖。且不能自主进行换绑，解绑，新增取现卡的操作。</dd>
				</dl>
				<dl>
					<dt id="fast-payqa">快捷充值失败常见原因</dt>
					<dd>1、用户绑卡时没有开通需要单独开通“银联在线支付”的三家银行（上海，浦发，渤海）；<br/>
						<span class="line">目前上海，浦发，渤海的银行卡需要开通银联在线支付功能，才能进行快捷充值卡的绑定。如有疑问，请联系银联在线24小时客服热线95516进行人工咨询。</span><br/>
						2、汇付天下绑定的手机号码跟银行卡预留手机号码不一致；<br/>
						<span class="line">用户可查看汇付天下账户绑定的手机号码（即正道金服用户名）以及银行卡预留手机号码（请与银行客服联系）。</span><br/>
						3、充值金额超过银行单日限额；<br/>
						<span class="line">用户可查看已绑定银行卡对应充值限额</span><br/>
						4、如排除以上问题后充值仍未成功，可选择其他银行账户进行支付，或在充值页面选择“网银支付”进行充值。</dd>
				</dl>
				<dl>
					<dt id="bank-payqa">网银充值失败常见原因</dt>
					<dd>1、电脑端网银或者手机网银没有按银行规定下载相应控件或者设置好浏览器；<br/>
						<span class="line">个人网银的使用，银行会要求用户下载相应控件或者设置浏览器，各家银行的操作均不一致，如有疑问，请致电所属银行客服咨询并按银行客服指引处理。</span><br/>
						2、充值金额超过银行限额<br/>
						<span class="line">银行对于个人网银，电脑端与手机端，有无证书等，都会有不同的金额限制，请查看充值金额是否超限。</span></dd>
				</dl>
				<dl>
					<dt>如何解绑已绑定快捷支付的银行卡？</dt>
					<dd>打开汇付天下账户登录界面（https://c.chinapnr.com/p2puser/），输入汇付天下账户用户名、登录密码进行登录，在“我的银行卡”-“快捷银行卡”中，进行解绑操作，输入银行预留手机号，填写短信验证码，即可解绑。（汇付用户名可在本平台“我的财富”-“安全中心”中查询；汇付登录密码在您开户时以短信方式发送至您绑定的手机）</dd>
				</dl>
			</div>
			<div class="q-title" id="invest">投资问题</div>
			<div class="q-content">
				<dl>
					<dt>资金托管是什么？为什么注册完成后还需要开通托管账户？</dt>
					<dd>资金托管是指将用户资金交由第三方金融服务机构管理，平台只负责匹配借贷双方信息，不能动用用户资金。汇付天下资金托管服务是由中国互联网金融协会理事单位—汇付天下提供的一项第三方独立资金托管业务。开户完成后，除您本人外，任何单位、个人均无权对您的资金进行操作，每一笔资金的出入都受到银行监管。</dd>
				</dl>
				<dl>
					<dt>投资金额有什么限制？</dt>
					<dd>正道金服平台上的项目投资起点金额通常为1000元，并以1000元的整数倍向上递增。</dd>
				</dl>
				<dl>
					<dt>投资收益从什么时候开始计算？</dt>
					<dd>在正道金服平台投资项目后，自T+1日期计息，T日至投资当日。</dd>
				</dl>
				<dl>
					<dt>投资是否可以取消或提前收回本金？</dt>
					<dd>正道金服平台投资后不能取消交易，暂时也不支持提前赎回本金。</dd>
				</dl>
			</div>
			<div class="q-title" id="law">法律法规</div>
			<div class="q-content">
				<dl>
					<dt>民间借贷合法性？</dt>
					<dd>根据《合同法》第196条规定"借款合同是借款人向贷款人借款，到期返还借款并支付利息的合同"。<br/>
						根据《最高人民法院关于审理民间借贷案件适用法律若干问题的规定》第一条规定“本规定所称的民间借贷，是指自然人、法人、其他组织之间及其相互之间进行资金融通的行为。”<br/>
						可见，我国法律允许普通民事主体之间发生借贷关系，并允许出借方到期收回本金和符合法律规定的利息。</dd>
				</dl>
				<dl>
					<dt>民间借贷利息的合法性？</dt>
					<dd>最高人民法院《关于人民法院审理借贷案件的若干意见》第26条：“借贷双方约定的利率未超过年利率24%，出借人请求借款人按照约定的利率支付利息的，人民法院应予支持。借贷双方约定的利率超过年利率36%，超过部分的利息约定无效。借款人请求出借人返还已支付的超过年利率36%部分的利息的，人民法院应予支持。”<br/>
						通过正道金服线下合作机构撮合的民间借贷利息低于银行同类贷款利率的四倍，符合法律规定，合法有效。</dd>
				</dl>
				<dl>
					<dt>线下合作机构提供居间撮合服务的合法性？</dt>
					<dd>根据《合同法》第二十三章关于"居间合同"的规定，特别是第424条的规定"居间合同是居间人向委托人报告订立合同的机会或者提供订立合同的媒介服务，委托人支付报酬的合同"。<br/>
						正道金服线下合作机构为民间借贷双方提供撮合借贷并形成借贷关系的居间服务和正道金服为债权收益权转让双方提供信息并实现转让的居间服务有着明确的法律基础。</dd>
				</dl>
				<dl>
					<dt>债权收益权转让的合法性：明确合法。</dt>
					<dd>1、债权人可以按照法律规定对债权进行转让，债权人需要不需要行使该项权利，完全是取决于权利人自己的意愿，但权利人要合法的行使权利。《中华人民共和国合同法》中第79条规定：“债权人可以将合同的权利全部或部分转让给第三人。”这是关于合同权利转让的规定。<br/>
						2、债权收益权转让的法律规定：<br/>
						根据《中华人民共和国合同法》第八十条规定：“债权人转让权利的，应当通知债务人。未经通知，该转让对债务人不发生效力。”本条规定明确了债权人是采用通知原则来解决债务人生效问题，债权人只需通知债务人，对债务人即可生效。<br/>
						3、同时，根据《合同法》第七十九条的规定 “债权人可以将合同的权利全部或者部分转让给第三人，但有下列情形之一的除外：（一）根据合同性质不得转让；（二）按照当事人约定不得转让；（三）依照法律规定不得转让。”</dd>
				</dl>
				<dl>
					<dt>电子合同合法吗？</dt>
					<dd>1、电子合同文本的合法性。<br/>
						根据《合同法》第十条的规定“当事人订立合同，有书面形式、口头形式和其他形式。” 《合同法》第十一条的规定“书面形式是指合同书、信件和数据电文（包括电报、电传、传真、电子数据交换和电子邮件）等可以有形地表现所载内容的形式。”<br/>
						因此电子合同属于书面形式合同的一种，是受到法律保护的。<br/>
						2、电子签名的合法性。<br/>
						根据《电子签名法》的规定，民事活动中的合同或者其他文件、单证等文书，当事人可以约定使用电子签名、数据电文，不能因为合同采用电子签名、数据电文就否定其法律效力。同时，《电子签名法》中还规定，可靠的电子签名与手写签名或者盖章具有同等的法律效力。
						《电子签名法》明确肯定了符合条件的电子签名与手写签名或盖章具有同等的效力。</dd>
				</dl>
			</div>
		</div>
	</div>
</div>
<jsp:include page="comm/footer.jsp"></jsp:include>
<jsp:include page="comm/helper.jsp"></jsp:include>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script>
	$(function () {
		/**
		 * 打开关闭常见问题
		 **/
		$(".q-content dt").on('click', function() {
			if($(this).attr('class') == "open-icon"){
				$(this).removeClass('open-icon');
				$(this).next('dd').slideUp(300);
			}else{
				$('.q-content dt').removeClass('open-icon');
				$('.q-content dd').slideUp(300);
				$(this).addClass('open-icon');
				$(this).next('dd').slideDown(300);
			}
		})

		//获取链接所传参数
		var urlCode = window.location.hash.replace(/#/, '') || '';
		if(urlCode){
			var id = '#' + urlCode;
			$(id).addClass('open-icon');
			$(id).next('dd').slideDown(300);
		}
	})
</script>
</body>
</html>
