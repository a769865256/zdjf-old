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
	<title>充值-我的财富-专业透明的互联网理财平台</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/>
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/deposit.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/user_public.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>
	<div class="body">
		<jsp:include page="center_nav.jsp"></jsp:include>

		<!-- 内容 -->
		<div class="body-right deposit color-narmal">
			<p class="title">充值</p>

			<div class="pay-layout">
				<p class="clearfix">
					<span class="name">充值金额</span>
					<input type="text" class="deposit-input J_inputUC" placeholder="请输入充值金额">
					<span class="balabce-tip">当前余额<b class="text-red J_balance" data-balance="<fmt:formatNumber value="${rsp.balance }" pattern="0.00"/>">
						<fmt:formatNumber value="${rsp.balance }" pattern="#,##0.00"/>
					</b>元　充值后余额<b class="text-red J_payBalance" data-value=""><fmt:formatNumber value="${rsp.balance }" pattern="#,##0.00"/></b>元</span>
					<span class="err-tip text-red J_tip-amount" style="display: none;"></span>
				</p>
				<p class="bar clearfix J_payBar">
					<span class="name">支付方式</span>
					<span href="javascript:void(0)" data-pay="fast" class="way curr"><i></i>快捷支付</span>
					<span href="javascript:void(0)" data-pay="bank" id="bankWay" class="way"><i></i>网银支付</span>
				</p>

				<input type="hidden" id="payaccount" value="${rsp.payAccount}">

				<c:if test="${!empty rsp.quickBank}">
				<div class="clearfix payQuick">
					<span class="name">选择银行</span>
					<div class="card clearfix">
						<div class="J_faseCard">
							<!-- 未认证 -->
							<%--<a href="javascript:void(0);" target="_blank" class="default-card J_defaultCard">--%>
								<%--<p class="icon"><span></span></p>--%>
								<%--<p>绑定银行卡</p>--%>
							<%--</a>--%>

								<!-- 已认证 -->
								<div class="approve-info">
									<div class="card">
										<p class="pic"><img src="${selfSite}/zdjf/res/product/images/banks/${rsp.quickBank.bankAlias}.png" alt=""><span><i></i>储蓄卡</span></p>
										<p class="line">${rsp.quickBank.bankNo}</p>
										<p class="line card-tip">
											<c:if test="${rsp.quickBank.bankServiceStatus == 1}">
												单笔限额<b>${rsp.quickBank.rulesSingle}万元</b>，单日累计限额<b>${rsp.quickBank.rulesDays}万元</b>
											</c:if>
											<c:if test="${rsp.quickBank.bankServiceStatus != 1}">
												暂停服务
											</c:if>
										</p>
									</div>
								</div>

						</div>
					</div>
				</div>
				</c:if>

				<div class="clearfix payBank hides">
					<span class="name">选择银行</span>
					<div class="card clearfix">
						<div class="bank-card J_bankCard">
							<div class="card-layout clearfix">

								<span data="ICBC" class="curr"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/ICBC.png" alt="bank-icbc"></span>
								<span data="CCB"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/CCB.png" alt="bank-contus"></span>
								<span data="CITIC"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/CITIC.png" alt="bank-zhongxin"></span>
								<span data="BOC"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/BOC.png" alt="bank-bcs"></span>

								<span data="PSBC"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/PSBC.png" alt="bank-cps"></span>
								<span data="CMB"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/CMB.png" alt="bank-cmb"></span>
								<span data="CMBC"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/CMBC.png" alt="bank-cmbc"></span>
								<span data="SPDB"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/SPDB.png" alt="bank-pf"></span>

								<span data="CEB"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/CEB.png" alt="bank-ceb"></span>
								<span data="CIB"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/CIB.png" alt="bank-cib"></span>
								<span data="BOS"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/BOS.png" alt="bank-shyh"></span>
								<span data="SRCB"><i></i><img src="${selfSite}/zdjf/res/product/images/banks-normal/SRCB.png" alt="bank-srcb"></span>

							</div>
							<div>
								<a href="javascript:void(0)" class="bank-more J_bankMore">展开选择更多银行</a>
							</div>
						</div>
					</div>
				</div>


				<div class="btn">
					<a href="javascript:void(0);" class="btn-red pay-submit">充值</a>
				</div>
			</div>

			<div class="pay-table clearfix J_faseCard">
				<div class="quota">附各大银行快捷支付限额表</div>
				<table>
					<tr><th>编号</th><th>银行名称</th><th>接入方式</th><th>卡性质</th><th>单笔限额</th><th>单卡单日累计限额</th></tr>
					<c:forEach var="bank" items="${rsp.banksList}" varStatus="idx">
						<tr>
							<td>${idx.index+1}</td>
							<td>${bank.bankName}</td>
							<td>全国</td>
							<td>借</td>
							<c:if test="${bank.status == 1}">
								<td>${bank.rulesSingle}万元</td>
								<td>${bank.rulesDays}万元</td>
							</c:if>
							<c:if test="${bank.status != 1}">
								<td>暂停服务</td>
								<td>暂停服务</td>
							</c:if>
						</tr>
					</c:forEach>

					<%--<tr><td>1</td><td>光大银行</td><td>全国</td><td>借</td><td>5万元</td><td>10万元</td></tr>
					<tr><td>2</td><td>建设银行</td><td>全国</td><td>借</td><td>5万元</td><td>10万元</td></tr>
					<tr><td>3</td><td>农业银行</td><td>全国</td><td>借</td><td>0.2万元</td><td>1万元</td></tr>
					<tr><td>4</td><td>平安银行</td><td>全国</td><td>借</td><td>0.5万元</td><td>0.5万元</td></tr>
					<tr><td>5</td><td>浦发银行</td><td>全国</td><td>借</td><td>0.5万元</td><td>5万元</td></tr>
					<tr><td>6</td><td>上海银行</td><td>全国</td><td>借</td><td>0.5万元</td><td>5万元</td></tr>
					<tr><td>7</td><td>兴业银行</td><td>全国</td><td>借</td><td>5万元</td><td>5万元</td></tr>
					<tr><td>8</td><td>邮储银行</td><td>全国</td><td>借</td><td>10万元</td><td>30万元</td></tr>
					<tr><td>9</td><td>中信银行</td><td>全国</td><td>借</td><td>5万元</td><td>10万元</td></tr>
					<tr><td>10</td><td>中国银行</td><td>全国</td><td>借</td><td>5万元</td><td>10万元</td></tr>
					<tr><td>11</td><td>渤海银行</td><td>全国</td><td>借</td><td>5万元</td><td>10万元</td></tr>
					<tr><td>12</td><td>工商银行</td><td>全国</td><td>借</td><td>5万元</td><td>5万元</td></tr>
					<tr><td>13</td><td>招商银行</td><td>全国</td><td>借</td><td>暂停服务</td><td>暂停服务</td></tr>
					<tr><td>14</td><td>交通银行</td><td>全国</td><td>借</td><td>暂停服务</td><td>暂停服务</td></tr>
					<tr><td>15</td><td>民生银行</td><td>全国</td><td>借</td><td>暂停服务</td><td>暂停服务</td></tr>
					<tr><td>16</td><td>广发银行</td><td>全国</td><td>借</td><td>暂停服务</td><td>暂停服务</td></tr>--%>
				</table>
			</div>

			<div class="pay-table J_positionTable">
				<ul>
					<li id="bank-icbc" title="中国工商银行">
						<table>
							<tr><th rowspan="3">卡种</th><th rowspan="2" colspan="2">U盾客户</th><th>柜面注册存量</th><th rowspan="2" colspan="2">已开通手机短信认证<br>的电子银行口令卡客户</th><th rowspan="2" colspan="2">未开通手机短信认证<br>的电子银行口令卡客户</th></tr>
							<tr><th>静态密码客户</th></tr>
							<tr><th>单笔限额</th><th>每日限额</th><th>总累计限额</th><th>单笔限额</th><th>每日限额</th><th>单笔限额</th><th>每日限额</th></tr>
							<tr><td>普通信用卡</td><td colspan="7">不支持</td></tr>
							<tr><td>普通借记卡</td><td>无限额</td><td>无限额</td><td>300</td><td>2000</td><td>5000</td><td>500</td><td>1000</td></tr>
						</table>
					</li>
					<li id="bank-bcs" title="中国银行">
						<table>
							<tr><th>卡种</th><th>单笔限额</th><th>每日限额</th></tr>
							<tr><td>借记卡</td><td>1万</td><td>10万</td></tr>
							<tr><td>准贷记卡</td><td>5,000</td><td>100,000</td></tr>
							<tr><td>信用卡</td><td colspan="2">不支持</td></tr>
						</table>
					</li>
					<li id="bank-shyh" title="上海银行">
						<table>
							<tr><th width="100">支持卡种</th><th width="117">单笔限额(元</th><th width="116">每日限额(元)</th><th width="136">需要满足条件</th><th>备注</th></tr>
							<tr><td rowspan="2">借记卡</td><td>5,000</td><td>5,000</td><td>专业版文件证书</td><td rowspan="4">至上海银行营业网点申请开通个人网上银行专业版，获得文件证书或usbkey证书，成为专业版客户。</td></tr>
							<tr><td>5万</td><td>5万</td> <td>专业版USBKEY证书</td></tr><tr><td align="center" rowspan="2">贷记卡</td><td>3,000</td><td>6,000</td><td>大众版<br>专业版文件证书</td></tr>
							<tr><td>5,000</td><td>1万</td><td>专业版USBKEY证书</td></tr>
						</table>
					</li>
					<li id="bank-contus" title="中国建设银行">
						<table>
							<tr><th rowspan="2">卡种</th><th colspan="2">网银盾</th><th colspan="2">动态口令卡、短信动态口令</th><th colspan="2">账号支付</th><th colspan="2">非签约客户</th></tr>
							<tr><th>单笔限额</th><th>每日限额</th><th>单笔限额</th><th>每日限额</th><th>单笔限额</th><th>每日限额</th><th>单笔限额</th><th>每日限额</th></tr>
							<tr><td>借记卡</td><td>100万</td><td>100万</td><td>3,000</td><td>3,000</td><td>1000</td><td>1000</td><td colspan="2">不支持</td></tr>
							<tr><td>信用卡</td><td colspan="8">不支持</td></tr>
						</table>
					</li>
					<li id="bank-ceb" title="中国光大银行">
						<table>
							<tr><th width="100">支持卡种</th><th width="149">单笔限额(元)</th><th width="140">每日限额(元)</th><th width="140">需要满足条件</th><th>备注</th></tr>
							<tr><td rowspan="2">借记卡</td><td>5,000（5,000）</td> <td>5,000（50万）</td><td>手机动态密码<br>（令牌）</td><td rowspan="4">1.办理数字证书支付签约需开通个人网上银行专业版，并登录网上银行专业版进行签约。<br> 2.办理银行卡直接支付签约可通过我行网点柜台办理，也可登录个人网上银行专业版进行签约。</td></tr>
							<tr><td>20万</td> <td>50万</td><td>网银专业版<br> 阳光网盾</td></tr>
							<tr><td rowspan="2">贷记卡</td><td>5,000元与信用额度中较小的一个</td><td>5,000元与信用额度中较小的一个</td><td>手机动态密码<br>（令牌）</td></tr>
							<tr><td>5,000元与信用额度中较小的一个</td><td>5,000元与信用额度中较小的一个</td><td>网银专业版<br> 阳光网盾</td></tr>
						</table>
					</li>
					<li id="bank-cib" title="兴业银行">
						<table>
							<tr><th rowspan="2">卡种</th><th colspan="2">证书客户</th><th colspan="2">非证书客户</th><th colspan="2">电子支付卡(e卡)</th></tr>
							<tr><th>单笔限额</th><th>每日限额</th><th>单笔限额</th><th>每日限额</th><th>单笔限额</th><th>每日限额</th></tr>
							<tr><td>借记卡</td><td>无限额</td><td>无限额</td><td>1,000 或 5,000</td><td>1,000 或 5,000</td><td>5,000</td><td>5,000</td></tr>
							<tr><td>信用卡</td><td colspan="2">不支持</td><td>无限额</td><td>无限额</td><td colspan="2">不支持</td></tr>
							<tr><td>信用卡</td><td colspan="6">不支持</td></tr>
						</table>
					</li>
					<li id="bank-zhongxin" title="中信银行">
						<table >
							<tr><th>支持卡种</th><th>单笔限额(元)</th><th>每日限额(元)</th><th>需要满足条件</th><th>备注</th></tr>
							<tr><td>借记卡</td><td>无限额</td><td>无限额</td><td rowspan="2">证书客户<br>（个人网上银行加强版）</td><td rowspan="2" >中信信用卡日累计支付次数：20次</td></tr>
							<tr><td>贷记卡</td><td>1,000</td><td>月交易金额1万</td></tr>
						</table>
					</li>
					<li id="bank-cmb" title="招商银行">
						<table>
							<tr><th rowspan="2">卡种</th><th colspan="2">专业版(签约客户)</th><th colspan="2">大众版(非签约客户)</th></tr>
							<tr><th>单笔限额</th><th>每日限额</th><th>单笔限额</th><th>每日限额</th></tr>
							<tr><td>借记卡</td><td>无限额</td><td>无限额</td><td>500</td><td>500</td></tr>
							<tr><td>贷记卡</td><td>500</td><td>信用限额</td><td colspan="2">不支持</td></tr>
						</table>
					</li>
					<li id="bank-cmbc" title="中国民生银行">
						<table>
							<tr><th rowspan="2" width="100">卡种</th><th colspan="2">网上银行u宝（含VIP+）版</th><th colspan="2">贵宾版</th><th colspan="2">大众版</th></tr>
							<tr><th width="100">单笔限额</th><th>每日限额</th><th>单笔限额</th><th>每日限额</th><th>单笔限额</th><th>每日限额</th></tr>
							<tr><td>借记卡</td><td>50万</td><td>50万</td><td>5,000</td><td>5,000</td><td>300</td><td>300</td></tr>
							<tr><td>贷记卡</td><td>50万元与 信用额度中较小的一个</td><td>50万元与 信用额度中较小的一个</td><td>5,000元与 信用额度中较小的一个</td><td>5,000元与 信用额度中较小的一个</td><td>300元与 信用额度中较小的一个</td><td>300元与 信用额度中较小的一个</td></tr>
							<tr><td>信用卡</td><td colspan="6">不支持</td></tr>
						</table>
					</li>
					<li id="bank-cps" title="中国邮政">
						<table>
							<tr><th align="center" width="100">支持卡种</th><th width="100">单笔限额(元)</th><th width="100">每日限额(元)</th><th width="133">需要满足条件</th><th>备注</th></tr>
							<tr><td align="center">借记卡 </td><td>3,000 </td><td>3,000 </td><td>短信动态密码版客户<br>（签约用户）</td><td>您必须在邮政储蓄网点进行网上支付业务申请、注册和开通，并开通短信动态密码认证功能。</td></tr>
						</table>
					</li>
					<li id="bank-pf" title="浦发银行">
						<table>
							<tr><th rowspan="3">卡种</th><th colspan="4">支付限额</th></tr>
							<tr><th colspan="2">动态密码版客户（签约用户）</th><th colspan="2">证书客户(游览器版和usbkey版)</th></tr>
							<tr><th>单笔支付限额</th><th>日累计支付限额</th><th>单笔支付限额</th><th>日累计支付限额</th></tr>
							<tr><td>东方卡借记卡</td><td>50000</td><td>50000</td><td>无限额</td><td>无限额</td></tr>
						</table>
					</li>
					<li id="bank-srcb" title="上海农商银行">
						<table>
							<tr><th width="100">支持卡种</th><th width="100">单笔限额(元)</th><th width="100">每日限额(元)</th><th width="100">需要满足条件</th><th>备注</th></tr>
							<tr><td rowspan="2">借记卡</td><td>1,000</td><td>5,000</td><td>个人网银短信专业版</td><td rowspan="3">1.个人网银短信专业版：请携带上海农商银行借记卡、本人身份证件至上海农商银行任意网点办理注册、绑定手机并开通网上支付功能等手续。<br>2.个人网银证书专业版：请携带上海农商银行借记卡、本人身份证件至上海农商银行任意网点办理申请数字证书手续并开通网上支付功能等手续。<br>3.信用卡卡密支付：仅须确保信用卡卡片状态正常，且办卡时已预留手机号码。</td></tr>
							<tr><td>1万</td><td>50万</td><td>个人网银证书专业版</td></tr>
							<tr><td>贷记卡</td><td>2,000</td><td>5,000</td><td>信用卡卡密支付</td></tr>
						</table>
					</li>
				</ul>
			</div>

			<c:if test="${1 == 2}">

				<!-- alipay -->
				<div class="alipay color-red hide">
					<p>
						<i class="alipay-logo"></i>支付宝转账（手机转账0手续费）
					</p>
					<p>
						支付宝账号：<input type="text" id="alipayID">
					</p>
					<p class="alipay-tip color-red J_tip" style="display: none;"></p>
					<p>
						<a href="javascript:void(0)" class="btn-red btn-pay J_alipaySubmit">确认支付</a>
					</p>
				</div>

				<!-- alipay第二页 -->
				<div class="alipay2 hide">
				<p class="title">
					收款账号：<span>zdjfu@zdjfu.com</span> （杭州云翳网络科技有限公司）
				</p>
				<div class="erweiima">
					<div class="ewm-left">
						<p>
							手机支付宝扫一扫，快速转账，<span class="red">0手续费</span>
						</p>
						<img alt="" src="${selfSite}/zdjf/res/center/images/user/erweima.png">
					</div>
					<div class="ewm-right">
						<p>去支付宝网站付款</p>
						<img alt="" src="${selfSite}/zdjf/res/center/images/user/zhifublogo.png">
					</div>
				</div>
				<hr>
				<p class="p-tel">08:30-20:30支付（六十分钟内到账）20:30后支付（次日12:00前到账）</p>
				<p class="p-tel">若规定时间内充值未到账，请联系客服：400-690-9898</p>
				<p class="p-tel">支付宝和圣贤账户的手机号必须相同，以免无法到账。</p>
			</div>

			</c:if>
		</div>
	</div>

	<!-- 支付成功Alert -->
	<div class="J_alert pay-success hide">
		<div class="alert-graybg"></div>
		<div class="alert-con-tip">
			<div class="close-btn J_close"></div>
			<div class="con-tip color-red">
				<p>
					<i></i>恭喜您，充值成功！
				</p>
				<p>
					<a href="javascript:void(0);" class="btn-red J_close">知道了</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 支付失败Alert -->
	<div class="J_alert pay-failing hide">
		<div class="alert-graybg"></div>
		<div class="alert-con-tip">
			<div class="close-btn J_close"></div>
			<div class="con-tip color-gray">
				<p>
					<i></i>抱歉，支付未成功。
				</p>
				<p>
					<a href="javascript:void(0);" class="btn-red">重新支付</a>
				</p>
			</div>
		</div>
	</div>

	<!-- 订单支付成功Alert -->
	<div class="J_alert orderPsySucc hide">
		<div class="alert-graybg"></div>
		<div class="alert-con">
			<div class="close-btn J_close"></div>
			<div class="ord-con">
				<p class="title color-red">
					订单支付成功<i></i>
				</p>
				<p class="que color-narmal">如果您支付遇到问题可咨询在线客服</p>
				<p class="tip color-gray">
					付款完成前请不要关闭此窗口。<br />完成付款后请根据您的情况点击下面的按钮：
				</p>
				<p>
					<a href="#" class="btn-red ord-succ">已完成支付</a><a href="#"
						class="pay-que-btn">付款遇到问题，重新支付</a>
				</p>
			</div>
		</div>
	</div>

	<!--设置托管账户弹窗-->
	<div class="alert alert-tip JChinapnrRegister hide">
		<div class="alert-darkbg J_alertClose"></div>
		<div class="eject">
			<div class="title"><i class="icon-close J_alertClose">×</i></div>
			<div class="content">
				<p class="tip-title">您尚未进行实名认证</p>
				<p class="con">
					实名认证（资金托管）能有效保障您的资金安全，<br/>使资金进出均受银行监管。 <a href="/active/hftx.html" target="_blank" class="text-blue btn-more">了解更多>></a>
				</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JApprove">前去认证</a>
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

	<!-- 设置充值等待回调弹窗 -->
	<div class="alert alert-tip JRechargeBack hide">
		<div class="alert-darkbg"></div>
		<div class="eject">
			<div class="title"></div>
			<div class="content">
				<p class="tip-title">充值遇到问题？</p>
				<p class="con">充值完成前请不要关闭此窗口<br>完成充值后请根据您的情况点击下面按钮</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JRechargeComplete">已完成</a>
					<a href="javascript:void(0);" class="btn-red-border JRechargeError">充值遇到问题</a>
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
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/recharge.js"></script>
</html>