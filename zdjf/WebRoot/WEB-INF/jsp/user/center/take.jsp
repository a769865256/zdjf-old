<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${selfSite}/zdjf">
	<meta charset="UTF-8">
	<title>提现-我的财富-专业透明的互联网理财平台</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/>
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/take.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>
	<div class="body">
		<jsp:include page="center_nav.jsp"></jsp:include>


		<!-- 内容 -->
		<div class="body-right take">
			<div class="title">提现</div>
           	<%--<div class="fast-card">
                <p class="pic"><img src="images/banks/${rsp.userBank.bankAlias}.png" alt=""><span><i></i>储蓄卡</span></p>
                <p class="card-line">${rsp.userBank.bankNo}</p>
                <p class="card-line">${rsp.userBank.cardUserName}</p>
                <div class="service-phone">绑定的银行卡</div>
            </div>
            <div class="input-title color-narmal">
            	填写提现金额<span class="color-gray">（如有疑问可咨询客服热线：400-999－1266）</span>
            </div>
            <div class="take-lay color-narmal">
            	<div class="line">
            		<span class="name">提现金额：</span>
            		<input type="text" placeholder="请输入提现金额" class="J_amount" autocomplete="off">元
            		<span class="text-red">　手续费0元</span>
            		<span class="tip text-red J_tip-amount" style="display:none;">请输入提现金额</span>
            	</div>
            	<div class="line">
            		<span class="name">可提现金额：</span>
            		<span id="balance">${rsp.balance}</span>元，单日提现限额50万元
            	</div>
            	<div class="line">
            		<span class="name">预计到账时间：</span>
            		24小时内
            	</div>
            	<div class="line hide">
            		<span class="name">交易密码：</span>
            		<input type="password" placeholder="请输入交易密码" class="J_password" autocomplete="off">
            		<span class="tip text-red J_tip-pd" style="display:none;">请输入交易密码</span>
            	</div>
            	<div class="line">
            		<a id="J_takeSubmit" class="btn-red submit-btn J_takeSubmit">确认提现</a>
            	</div>
            </div>--%>

			<input type="hidden" id="approve_status" name="approve_status" value="${rsp.userInfo.realNameAuth}">

			<div class="pay-layout">
				<p class="bar clearfix">
					<span class="name">可用余额</span>
					<span class="balabce-num" data-balance="<fmt:formatNumber value="${rsp.balance}" pattern="0.00" />"><fmt:formatNumber value="${rsp.balance}" pattern="#,##0.00" /></span>
				</p>
				<p class="clearfix">
					<span class="name">提现金额</span>
					<input type="text" class="deposit-input J_amount" placeholder="请输入提现金额">
					<span class="balabce-tip">今日可取现余额<b class="text-red J_balance" data-balance="<fmt:formatNumber value="${rsp.canWithdrawBalance}" pattern="0.00" />">
						<fmt:formatNumber value="${rsp.canWithdrawBalance}" pattern="#,##0.00" />
					</b>元<i class="balabce-icon">?
						<span class="tip-con">
							• 为了账户安全，客户当日充值未投资的金额不支持当日提现；<br/>• 用户在平台的非首次提现，提现金额≥100元不收取手续费，提现金额＜100元按2元每笔收取手续费
							<span class="icon-arrow"></span>
							<span class="icon-arrow01"></span>
						</span></i>
					<a href="javascript:void(0);" class="text-red J_allBtn">全额提现</a></span>
					<span class="err-tip text-red J_tip-amount" style="display: none;"></span>
				</p>
				<div class="card-layout clearfix">
					<span class="name">提现银行卡</span>
					<c:if test="${empty rsp.userBank}">
						<!-- 未认证 -->
						<a href="javascript:void(0);" target="_blank" class="default-card J_defaultCard">
							<p class="icon"><span></span></p>
							<p>绑定银行卡</p>
						</a>
					</c:if>
					<c:if test="${!empty rsp.userBank}">
						<!-- 已认证 -->
						<div class="approve-info">
							<div class="card">
								<p class="pic"><img src="${selfSite}/zdjf/res/product/images/banks/${rsp.userBank.bankAlias}.png" alt=""><span><i></i>储蓄卡</span></p>
								<p class="line">${rsp.userBank.bankNo}</p>
								<p class="line">${rsp.userBank.cardUserName}</p>
							</div>
							<a href="javascript:void(0);" class="other-card text-blue JSetOtherCard" data-flag="${quickCardFlag}">设置其它提现银行卡</a>
						</div>
					</c:if>
				</div>
				<div class="card-err-tip text-red J_tip-card" style="display:none;">请先添加提现银行卡</div>
				<div class="take-tip JTakeTip" style="display: none;" data-id="${rsp.stat.withdrawCoupons}">
					<p>提现手续费: <span class="JTakeCash">2.00</span>　　实际到账: <span class="text-red JActMoney">0.00</span>元　
						<a href="/about/noticeDetail.html?id=780716906758799360" class="text-blue" target="_blank"><c:if test="${rsp.feeAmt == 0}">首次提现免手续费</c:if><c:if test="${rsp.feeAmt != 0}">手续费说明</c:if><!----></a></p>
					<div>

						<c:if test="${rsp.stat.withdrawCoupons == 0}">
							<input type="checkbox" id="J_chk_coupon" style="display: none; float: left; margin-top: 7px; margin-right: 3px;" disabled>
							<a target="_blank" href="/center/gift" class="text-blue">兑换免提现手续费券</a>
						</c:if>

						<c:if test="${rsp.stat.withdrawCoupons != 0}">
							<input type="checkbox" id="J_chk_coupon" style="display: inline-block; float: left; margin-top: 7px; margin-right: 3px;">
							使用免提现手续费券
							<span>剩余 <span class="text-red">${rsp.stat.withdrawCoupons}</span>张</span>
						</c:if>
					</div>

				</div>
				<div class="btn">
					<a href="javascript:void(0);" class="btn-red pay-submit J_takeSubmit">确认提现</a>
				</div>
			</div>

			<c:if test="${rsp.couponCount > 0}">
				<div class="ticket-lay">
					您还有<span>${rsp.couponCount}张</span>优惠券未使用,使用投资后提现更划算 <a href="../center/gift">详情></a>
				</div>
			</c:if>

			<input type="hidden" id="minTakeMoney" value="${rsp.minTakeMoney}">
			<input type="hidden" id="isWithdraw" value="${rsp.isWithdraw}">
			<input type="hidden" id="feeAmt" value="${rsp.feeAmt}">

			<div class="withdraw-tip">
				<div class="name">温馨提示</div>
				<div>
					<p>1、提现前请先将本人银行卡与第三方托管“汇付天下”账户进行绑定。</p>
					<p>2、由于汇付天下规则调整，客户当日充值未投资的金额不支持当日提现，当日可取现余额=可用余额+当日投资+当日还款-客户当日充值。（举个例子：客户当日充值10000元，当日投资9900元，余额为100元，当日回款为0元，当日可提现余额的计算公式如下：（可用余额）100元+（当日投资）9900元+（当日回款）0元-（客户当日充值）10000元=（当日可提现余额）0元。</p>
					<p>3、在收到您的提现申请后，汇付天下将于T+1个工作日内（不含国家节假日）将资金转入您本人绑定的银行卡账户中。具体到账时间请参考：</p>
					<p>（1）工作日18:00前申请提现，平台当日审核，T+1工作日到账；18:00之后申请提现，平台次日审核，审核通过后，T+2工作日到账。</p>
					<p>（2）非工作日的申请提现，统一在节假日后的第一个工作日处理，第二个工作日到账。</p>
					<p>（3）平台上的提现手续费规则如下：用户在平台首次提现，无论金额大小均不收取手续费；用户在平台的非首次提现，提现金额≥100元不收取手续费，提现金额＜100元按2元每笔收取手续费。</p>
					<p>（4）由于银行处理业务的时间不同，具体到账时间请以银行为准，如有疑问，请致电您的提现银行。</p>
					<p>4、提现申请及到账时间对应表如下图：</p>
					<table>
						<tr>
							<td>申请时间</td><td>周一</td><td>周二</td><td>周三</td><td>周四</td><td>周五</td><td>周六</td><td>周日</td>
						</tr>
						<tr>
							<td>到账时间</td><td>周二</td><td>周三</td><td>周四</td><td>周五</td><td>周一</td><td>周二</td><td>周二</td>
						</tr>
					</table>
					<p>5、给您带来的不便，还望得到您的谅解与支持，若提现过程中遇到任何问题，请拨打我们的客服热线：400-690-9898。</p>
				</div>
			</div>

		</div>
	</div>

	<input type="hidden" id="bankNo" value="${rsp.bankNo}" />


	<!--设置托管账户弹窗-->
	<div class="alert alert-tip JChinapnrRegister hide">
		<div class="alert-darkbg J_alertClose"></div>
		<div class="eject">
			<div class="title"><i class="icon-close J_alertClose">×</i></div>
			<div class="content">
				<p class="tip-title">您尚未进行实名认证</p>
				<p class="con">
					实名认证（资金托管）能有效保障您的资金安全，<br/>使资金进出均受银行监管。 <a href="/active/hftx.html" class="text-blue btn-more" target="_blank">了解更多>></a>
				</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JApprove">前去认证</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 设置托管账户弹窗 -->
	<div class="alert alert-tip JChinapnrRegisterWait hide">
		<div class="alert-darkbg J_alertClose"></div>
		<div class="eject">
			<div class="title"><i class="icon-close J_alertClose">×</i></div>
			<div class="content">
				<p class="tip-title">账户实名认证</p>
				<p class="con">正在进行实名认证，请耐心等待</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JKnow">知道了</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 设置托管账户等待回调弹窗 -->
	<div class="alert alert-tip JChinapnrRegisterBack hide">
		<div class="alert-darkbg"></div>
		<div class="eject">
			<div class="title"></div>
			<div class="content">
				<p class="tip-title">账户实名认证</p>
				<p class="con">请在新打开的汇付天下页面完成实名认证</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JRegisterComplete">已完成</a>
					<a href="javascript:void(0);" class="btn-red-border JRegisterError">遇到问题未完成</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 设置托管账户等待回调弹窗 -->
	<div class="alert alert-tip JBindBack hide">
		<div class="alert-darkbg"></div>
		<div class="eject">
			<div class="title"></div>
			<div class="content">
				<p class="tip-title">绑定银行卡</p>
				<p class="con">请在新打开的汇付天下页面完成银行卡绑定</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JBindComplete">已完成</a>
					<a href="javascript:void(0);" class="btn-red-border JBindError">遇到问题未完成</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 设置提现等待回调弹窗 -->
	<div class="alert alert-tip JWithdrawBack hide">
		<div class="alert-darkbg"></div>
		<div class="eject">
			<div class="title"></div>
			<div class="content">
				<p class="tip-title">提现遇到问题？</p>
				<p class="con">提现完成前请不要关闭此窗口<br>完成提现后请根据您的情况点击下面按钮</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JWithdrawComplete">已完成</a>
					<a href="javascript:void(0);" class="btn-red-border JWithdrawError">提现遇到问题</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 设置托管账户弹窗 -->
	<div class="alert alert-tip JQuickCard hide">
		<div class="alert-darkbg J_alertClose"></div>
		<div class="eject">
			<div class="title"><i class="icon-close J_alertClose">×</i></div>
			<div class="content">
				<p class="tip-title">提示</p>
				<p class="con">您已绑定快捷卡，不能换绑。<br>若需解绑，请致电客服热线400-690-9898进行咨询。</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JKnow">确定</a>
				</p>
			</div>
		</div>
	</div>

	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/user_public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/take.js"></script>
</html>