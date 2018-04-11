<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${selfSite}/zdjf">
	<meta charset="UTF-8">
	<title>我的财富-专业透明的互联网理财平台</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/>
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/home.css?t=20161013">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/kkpager_gray.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/calendar.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>
	<div class="body">
		<jsp:include page="center_nav.jsp"></jsp:include>
		<input type="hidden" id="approveStatus" name="approveStatus" value="${rsp.info.realNameAuth}" />
		<!-- 内容new -->
		<div class="body-right-top">
			<!-- bar -->
		    <div class="bar">
		        <div class="left">
		            <span class="icon-user"></span>
		            <div class="font">
		                <div class="clearfix">
		                    <span class="phone">您好，<b class="J_phoneNumber">--</b></span>
		                    <!-- 未验证-实名 -->
		                    <div class='name-tip ${rsp.info.realNameAuth==1?"red":"" }'>
		                        <i class="icon-name"></i>
		                        <div class="tip-con">
		                            <span class="icon-arrow"></span>
		                            <span class="icon-arrow01"></span>
		                            <c:if test="${rsp.info.realNameAuth==1 }"><div>您已实名认证</div></c:if>
								 	<c:if test="${rsp.info.realNameAuth!=1 }">
								 		<div>您尚未实名认证。<a href="${selfSite}/zdjf/center/safe">立即认证</a></div>
		                            </c:if>
		                        </div>
		                    </div>
		                    <!-- 未验证-交易密码 -->
		                    <div class='lock-tip ${rsp.bankCount > 0?"red":"" }'>
		                        <i class="icon-lock"></i>
		                        <div class="tip-con">
		                            <span class="icon-arrow"></span>
		                            <span class="icon-arrow01"></span>
		                            <c:if test="${rsp.bankCount==0 }">
		                            	<div>您尚未绑定提现银行卡。<a href="${selfSite}/zdjf/center/banks">立即设置</a></div>
		                            </c:if>
		                            <c:if test="${rsp.bankCount > 0 }">
		                            	<div>您已绑定提现银行卡。<a href="${selfSite}/zdjf/center/banks">修改</a></div>
		                            </c:if>
		                        </div>
		                    </div>
		                   
		                </div>
		                <div class="last-time">
		                    上次登录时间：<fmt:formatDate value="${rsp.info.lastLoginTime }" pattern="yyyy-MM-dd HH:mm:ss" />
		                </div>
		            </div>
		        </div>
		
		        <div class="right">
		            <p class="balance">可用余额：<span class="text-red">
		            	<fmt:formatNumber value="${rsp.stat.balance }" pattern="#,##0.00"
						type="number" />
					</span>元</p>
		            <p>
		                <a href="javascript:void(0);" class="btn-red JRecharge">充值</a>
		                <a href="javascript:void(0);" class="btn-red-border JWithdraw">提现</a>
						<a href="${selfSite}/zdjf/center/funds" class="funds-detail">资金明细</a>
		            </p>
		        </div>
		    </div>
		
		    <!-- balance -->
		    <div class="balance-con">
		        <i class="icon-detail J_iconDetail"></i>
		        <div class="narmal">
		            <ul>
		                <li>
		                    <p>总资产（元）</p>
		                    <p class="num">
								<span class="J_iconDetail" id="loadNum01" data-num="<fmt:formatNumber value="${rsp.stat.balance + rsp.stat.pendWithdraw+rsp.stat.pendReturn + rsp.stat.pendIncome}" pattern="0.00" type="number" />">
									<fmt:formatNumber value="${rsp.stat.balance + rsp.stat.pendWithdraw + rsp.stat.pendReturn + rsp.stat.pendIncome}" pattern="#,##0.00" type="number" />
								</span>
							</p>
		                </li>
		                <li>
		                    <p>待收收益（元）</p>
		                    <p class="num">
		                    	<span class="J_iconDetail" id="loadNum02" data-num="<fmt:formatNumber value="${rsp.stat.pendIncome + rsp.couponWillReturn }" pattern="0.00" type="number" />">
									<fmt:formatNumber value="${rsp.stat.pendIncome + rsp.couponWillReturn }" pattern="#,##0.00" type="number" />
								</span>
							</p>
		                </li>
		                <li>
		                    <p>累计赚取（元）</p>
		                    <p class="num">
		                    	<span class="J_iconDetail" id="loadNum03"
									  data-num="<fmt:formatNumber value="${rsp.allIncome}" pattern="0.00" type="number" />">
									<fmt:formatNumber value="${rsp.allIncome}" pattern="#,##0.00" type="number" />
								</span>
							</p>
		                </li>
		            </ul>
					<div class="hftx-tip">汇付天下提供资金托管服务，银行全程监管</div>
		        </div>
		        <div class="hover J_balanceHover" style="display:none;">
		            <div class="left">
		                <div class="amount">
		                    <p>总资产（元）</p>
		                    <p class="num"><fmt:formatNumber value="${rsp.stat.balance + rsp.stat.pendWithdraw+rsp.stat.pendReturn + rsp.stat.pendIncome}"
						pattern="#,##0.00" type="number" /></p>
		                </div>
		                <i class="icon-branch"></i>
		                <div class="branch">
		                    <p>可用余额：<b>
								<fmt:formatNumber value="${rsp.stat.balance }" pattern="#,##0.00"
						type="number" />
							</b>元</p>
		                    <p>提现中金额：<a <c:if test="${rsp.stat.pendWithdraw != 0 }">class="draw-money" href="/center/funds?fundsType=2"</c:if> ><fmt:formatNumber value="${rsp.stat.pendWithdraw }" pattern="#,##0.00" type="number" /></a> 元</p>
		                    <p>待收收益：<b>
		                    	<fmt:formatNumber value="${rsp.stat.pendIncome }"
						pattern="#,##0.00" type="number" />
							</b>元</p>
		                    <p>待收本金：<b>
								<fmt:formatNumber value="${rsp.stat.pendReturn }"
						pattern="#,##0.00" type="number" />
							</b>元</p>
		                </div>
		            </div>
		            <div class="right">
		                <div class="clearfix">
		                    <div class="amount">
		                        <p>累计赚取（元）</p>
		                        <p class="num">
									<fmt:formatNumber value="${rsp.allIncome}" pattern="#,##0.00" type="number" />
								</p>
		                    </div>
		                    <i class="icon-branch"></i>
		                    <div class="branch">
		                        <p>已收收益：<b>
		                        	<fmt:formatNumber value="${rsp.stat.incomed }" pattern="#,##0.00" type="number" />
								</b>元</p>
		                        <p>待收收益：<b>
		                        	<fmt:formatNumber value="${rsp.stat.pendIncome }" pattern="#,##0.00" type="number" />
								</b>元</p>
								<p>使用优惠：<b>
									<fmt:formatNumber value="${rsp.couponForPay }" pattern="#,##0.00" type="number" />
								</b>元</p>
		                    </div>
		                </div>
		                <div class="amount-other">
		                    <span>累计充值：<b>
								<fmt:formatNumber value="${rsp.stat.recharged }" pattern="#,##0.00" type="number" />
							</b>元</span>
		                    <span>累计提现：<b>
		                    	<fmt:formatNumber value="${rsp.stat.withdrawed }" pattern="#,##0.00" type="number" />
							</b>元</span>
		                </div>
		            </div>
		        </div>
		    </div>
		
		    <div class="gift">
		        <div class="gift-num">
		            <span>
						<i class="scb"></i>
						正经值:
						<a class="text-red" href="${selfSite}/zdjf/center/gift">
							<fmt:formatNumber type="number" pattern="#,##0.00" value="${rsp.stat.coinBalance}"  />
						</a>
						　　　<a class="text-orange" href="${selfSite}/zdjf/center/gift">兑换></a>
					</span>|
		            <span>
						<i class="hb"></i>
						可用红包<a class="text-red" href="${selfSite}/zdjf/center/gift">${rsp.giftCount}</a>个
					</span>|
					<span>
						<i class="jxq"></i>
						可用加息券<a class="text-red" href="${selfSite}/zdjf/center/gift?active=2">${rsp.couponCount}</a>个
					</span>
		        </div>
		        <%--<a href="${selfSite}/zdjf/center/gift" class="look-detail">优惠详情</a>--%>
		    </div>
		
		</div>

		<input type="hidden" id="waitList" value="${detail.waitList}" />
		<input type="hidden" id="returnList" value="${detail.returnList}" />

		<div class="calendar-lay">
			<p class="title">
				<span class="J_caTitle">回款日历</span>
				<span class="switch-btn sb-calendar J_switchBtn"></span>
			</p>
			<!-- 日历表格-->
			<div class="calendar-tab">
				<div id="calendar">
					<img src="${selfSite}/zdjf/res/center/images/home/loading-calendar.jpg" alt="" style="position: absolute; z-index: 0; top:156px;left:70px;">
				</div>
				<!-- 下方添加‘dark’为置换成灰色背景 -->
				<div class="calendar-detail">
					<div class="detail-title"><c></c>月应收本息<span>--</span>元</div>
					<div class="cd-info">
						<div class="cd-lay">
							<p class="month">--月</p>
							<p class="day">--</p>
						</div>
						<div class="cd-amount">
							<p class="hide"><span class="name">下个回款日</span>--月--日</p>
							<p><span class="name">回款项目</span><c class="J_count">--</c>个</p>
							<p><span class="name">回款总计</span><b class="J_money">--</b>元</p>
						</div>
					</div>
					<div class="cd-list">
						<!-- 无记录 -->
						<div class="nothing J_nothing hide">
							<i class="iconfont">&#xe62a;</i>
							没有相关的记录哦
						</div>
						<div class="cd-list-btndown J_listBtnDown hide"></div>
						<div class="list-lay J_listLay" data-num ='3'>
							<ul class="J_list_ul">

							</ul>
						</div>
						<div class="cd-list-btn J_listBtnTop"></div>
					</div>
				</div>
				<div class="calendar-tip">
					<span><i class="ct-icon red"></i>选中</span>
					<span><i class="ct-icon blue"></i>待回款</span>
					<span class="last"><i class="ct-icon gray"></i>已回款</span>
				</div>
			</div>

			<!-- 回款计划-->
			<div class="plan-tab" style="display:none;">
				<table class="hide J_return_list">
					<tr>
						<th>还款日期</th>
						<th>理财项目</th>
						<th>本金(元)</th>
						<th>收益(元)</th>
						<th>状态</th>
					</tr>
					<tbody class="J_tbody_records">
						<tr class="over">
							<td>2016-10-09</td>
							<td>正道金服4052期(1/2)</td>
							<td>100.00</td>
							<td>2.01</td>
							<td>待回款</td>
						</tr>
						<tr>
							<td>2016-10-09</td>
							<td>正道金服4052期(1/2)</td>
							<td>100.00</td>
							<td>2.01</td>
							<td>待回款</td>
						</tr>
						<tr>
							<td>2016-10-09</td>
							<td>正道金服4052期(1/2)</td>
							<td>100.00</td>
							<td>2.01</td>
							<td>待回款</td>
						</tr>
						<tr>
							<td>2016-10-09</td>
							<td>正道金服4052期(1/2)</td>
							<td>100.00</td>
							<td>2.01</td>
							<td>待回款</td>
						</tr>
						<tr>
							<td>2016-10-09</td>
							<td>正道金服4052期(1/2)</td>
							<td>100.00</td>
							<td>2.01</td>
							<td>待回款</td>
						</tr>
						<tr>
							<td>2016-10-09</td>
							<td>正道金服4052期(1/2)</td>
							<td>100.00</td>
							<td>2.01</td>
							<td>待回款</td>
						</tr>
						<tr>
							<td>2016-10-09</td>
							<td>正道金服4052期(1/2)</td>
							<td>100.00</td>
							<td>2.01</td>
							<td>待回款</td>
						</tr>
						<tr>
							<td>2016-10-09</td>
							<td>正道金服4052期(1/2)</td>
							<td>100.00</td>
							<td>2.01</td>
							<td>待回款</td>
						</tr>
					</tbody>
				</table>


				<div class="kp-left hide" id="kkpager" ></div>

				<!--无回款记录-->
				<div class="nothing J_noRecord" style="margin-top: 50px">
					<i class="iconfont">&#xe62a;</i>
					暂无回款记录哦
				</div>
			</div>
		</div>

		<div class="body-right-bottom">
			<p class="title">
				近期投资 <!-- <a href="${selfSite}/zdjf">继续投资</a> -->
			</p>
			<c:forEach var="buy" items="${rsp.buys }">
				<div class="pocd-info">
					<p>
						<span>${buy.productName } <c:if test="${buy.willIncomeDays != buy.incomeDays}">(提前还款)</c:if></span>
					</p>
					<ul>
						<li class="yield "><span><c:if test="${buy.status == 2}">实际收益</c:if><c:if test="${buy.status != 2}">预期收益</c:if></span> <span class="f18 text-red">
							<c:if test="${buy.status != -1}">
								<fmt:formatNumber value="${buy.incomed }" pattern="0.00" type="number" />
							</c:if>
							<c:if test="${buy.status == -1}">
								-
							</c:if>
						</span></li>
						<li class="yieldrate"><span>年化收益率</span> <span class="f18">
							<fmt:formatNumber value="${buy.productInterest }" pattern="0.00" type="number" />%
						</span></li>
						<li class="cycle"><span>投资本金</span> <span class="f18">
							<fmt:formatNumber value="${buy.amount }" pattern="0" type="number" />元
						</span></li>
						<li class="state">
							<div class="processingbar">
								<font>${buy.circlePercent}%</font>
							</div>
							<c:if test="${buy.status == 1}">
								<c:if test="${buy.remainDays == 0}">
									<p class="remain-date yhk">
										<br />回款中
									</p>
								</c:if>
								<c:if test="${buy.remainDays != 0}">
									<p class="remain-date"><i>${buy.remainDays}</i><br /><b>天到期</b>
									</p>
								</c:if>
							</c:if> <c:if test="${buy.status != 1}">
							<p class="remain-date yhk">
								<br />${buy.statusText}</p>
						</c:if>
						</li>
						<c:if test="${buy.status == 1 || buy.status == 2}">
							<li class="xx">
								<a href="javascript:void(0);" class="JOpenDetail" data-id="${buy.buyId }">
									<i class="icon-detail"></i>详情
								</a>
							</li>
						</c:if>
						<c:if test="${buy.status == -1}">
							<li class="pay">
								<a href="javascript:void(0);" data-id="${buy.buyId}" class="btn-orange pay-btn JPay">立即付款</a>
								<a href="javascript:void(0);" data-id="${buy.buyId}" class="pay-close JClose">取消订单</a>
							</li>
						</c:if>
							<li class="pay hide">
								待审核　
								<a href="javascript:void(0);" data-id="${buy.buyId}" class="pay-close">撤销</a>
							</li>
					</ul>
					<div class="time">
						<c:if test="${buy.status != -1}">
							<fmt:formatDate value="${buy.payTime }" pattern="MM-dd" />
						</c:if>
						<c:if test="${buy.status == -1}">
							${buy.remainMinute}后订单过期
						</c:if>
					</div>
				</div>
			</c:forEach>
			
			<c:if test="${empty rsp.buys }">
				<p class="zgm">
					<i class="icon-invent"></i>
					<span>您尚未购买理财产品<a href="${selfSite}/zdjf/product/search.html" class="btn-red">前去投资</a></span>
				</p>
				<div class="tip-cons">
					<span class="icon-arrow"></span>
					<span class="icon-arrow01"></span>
					<div><i class="tip-cons-icon"><img src="${selfSite}/zdjf/res/center/images/home/tip-cons-icon.png" alt=""></i>完成首次投资立返<span class="text-red">20元</span>哦</div>
				</div>
			</c:if>
			
			<c:if test="${!empty rsp.buys && fn:length(rsp.buys) >= 3 }">
				<p class="page">
					<a href="${selfSite}/zdjf/center/orders" id="ckqb">查看全部</a>
				</p>
			</c:if>
			
		</div>
	</div>



	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>

	<div class="box-info hide">
		<span class="close-btn"></span>
		<p class="title">
			<span class="box-left">▪ 理财产品详情 </span><span class="box-right"> 编号：
			<span class="marginright10" id="b_buyId_title"></span> 状态：<span id="b_status"></span>
			</span>
		</p>
		<p>
			<span class="line">产品名称：<a href="javascript:void(0);" id="b_productName" class="text-red link-name" target="_blank"></a></span>
		</p>
		<p>
			<span class="box-left"> 购买本金：<span id="b_amount"></span></span>
			<span class="box-right"> 优惠抵扣：<span id="b_couponMsg"></span></span>
		</p>
		<p>
			<span class="box-left"> 收益天数：<span id="b_days"></span></span>
			<span class="box-right"> 投资时间：<span id="b_payTime"></span></span>
		</p>
		<p>
			<span class="box-left"> <c id="t_productInterest">预期年化收益率：</c><span id="b_productInterest"></span></span>
			<span class="box-right"> 起息日期：<span id="b_startDate"></span></span>
		</p>
		<p>
			<span class="box-left"> <c id="t_income">预计总收益：</c><span id="b_income"></span></span>
			<span class="box-right"> 到期日期：<span id="b_endDate"></span></span>
		</p>
		<p>
			<span class="box-left"> 协议书：
				<a href="javascript:void(0);" class="box-right JContract" title="点击查看协议详情">《债权转让协议》</a>
			</span>
			<span class="box-right"> 到期处理方式：<span id="b_returnMethod"></span></span>
		</p>
		<input type="hidden" name="contractUrl" id="contractUrl" value="${selfSite}/zdjf${contractUrl}" />

		<!-- 还款计划 -->
		<div class="plan">
			<div class="name">还款计划<span class="JReturned">已还期数：<b class="text-red">1</b>/13</span></div>
			<div class="th">
				<span class="w65">期数</span>
				<span class="w148">收款日</span>
				<span class="w179">利息（元）</span>
				<span class="w179">本金（元）</span>
				<span class="w125">状态</span>
			</div>
			<table>
				<thead>
				<tr>
					<th class="w65">期数</th>
					<th class="w148">收款日</th>
					<th class="w179">利息（元）</th>
					<th class="w179">本金（元）</th>
					<th class="w125">状态</th>
				</tr>
				</thead>
				<tbody class="JIncomeList">

				</tbody>
			</table>
		</div>
	</div>
	<div id="bj"></div>

	<c:if test="${firstLogin == 1}">
		<!-- 注册成功跳转Alert -->
		<div class="alert reg-success J_alert hide">
			<div class="alert-darkbg"></div>
			<div class="eject">
				<%--<div class="icon-close-white J_close"></div>--%>
				<div class="con"><b><b><fmt:formatNumber value="${rsp.giftMoney}" pattern="0" type="number" />元</b>红包</b>和<b><b>${rsp.couponCount}张</b>加息券</b>已放入您的账户中，<br/>实名认证后就能使用啦！</div>
				<div><a href="${selfSite}/zdjf/chinapnr/req/register" target="_blank" class="use-btn J_toApprove">立即认证</a><a href="${selfSite}/zdjf/center" class="close-btn J_close">暂不认证</a></div>
			</div>
		</div>
	</c:if>

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
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/kkpager.min.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/show_income.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/jquery-ui-datepicker.min.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/cycle-order.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/raphael.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/countUp.min.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/user_public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/home.js?t=20161013"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/show_order_detail.js"></script>
</html>