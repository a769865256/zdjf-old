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
	<title>${rsp.product.productCode} ${rsp.product.productName } - 正道金服 - 专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="${rsp.product.productCode} ${rsp.product.productName },正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/> 
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/product/css/detail.css?t=20160429">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/layer.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/alert.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/pages.css">
	<!--弹框样式-->
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/product/css/jquery.lightbox.css?t=20160429">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<jsp:include page="../comm/header.jsp"></jsp:include>
	<input type="hidden" id="approveStatus" name="approveStatus" value="${rsp.info.realNameAuth}" />

	<!-- projectInfo -->
	<div class="project-info">

		<c:if test="${rsp.product.isFresh == 1}">
			<i class="new-userIcon"></i>
		</c:if>
		
	 	<input type="hidden" id="productId" value="${rsp.product.productId}" />
		<input type="hidden" id="productBalance" value="${rsp.product.canBuyMoney}" />
		<h1>
			${rsp.product.productCode }&nbsp;${rsp.product.productName }<c:if test="${rsp.product.status == 31}"><i class="herald">预告</i></c:if>
		</h1>
		<div class="accrual-mode">计息方式：${rsp.product.incomeMethodText }</div>
		<div class="info-left">
			<table>
				<tr>
					<th>
						<b class="text-red">
							<fmt:formatNumber value="${rsp.product.canBuyMoney }" pattern="#,###" />
						</b>元
					</th>
					<th>
						<b id="income-b"><fmt:formatNumber value="${rsp.product.income }" pattern="0.00" /></b>%
					</th>
					<th>
						<b id="incomeDays-b"><c:if test="${rsp.product.status == 4}">${rsp.product.incomeDays }</c:if><c:if test="${rsp.product.status != 4}">${rsp.product.incomeDaysTo }</c:if></b>天
					</th>
				</tr>
				<tr>
					<td>可投金额</td>
					<td>年利率</td>
					<td>收益期限</td>
				</tr>
			</table>
			<div class="info-content">
				<div class="ic-left">
					<p>
						<span class="text-aide">项目总额</span>
						￥<fmt:formatNumber value="${rsp.product.money }" pattern="#,###" type="number" />
					</p>
					<p>
						<span class="text-aide">起投金额</span>
						￥<fmt:formatNumber value="${rsp.product.payMin }" pattern="#,###" type="number" />
					</p>
					<p class="progress-bar">
						<span class="text-aide">募集进度</span>
						<c:if test="${rsp.product.status == 4}">
							<strong>
								<i class="J_wid" style="width:${rsp.product.saleMoney/rsp.product.money*100 }%"></i>
							</strong>
							<!-- 此处进度条将完成率的百分比导入即可 -->
							<b class="progress-num">
								<fmt:formatNumber value="${rsp.product.saleMoney/rsp.product.money*100 }" pattern="0.00" type="number" />%
							</b>
						</c:if>
						<c:if test="${rsp.product.status != 4}">
							<strong>
								<i class="J_wid" style="width:100%"></i>
							</strong>
							<!-- 此处进度条将完成率的百分比导入即可 -->
							<b class="progress-num">
								${rsp.product.statusText}
							</b>
						</c:if>
					</p>
				</div>
				<div class="ic-right">
					<p>
						<span class="text-aide">还款方式</span> ${rsp.product.returnMethodText }
					</p>
					<p>
						<span class="text-aide">发布日期</span>
						<fmt:formatDate value="${rsp.product.issueTime}" pattern="yyyy-MM-dd" />
					</p>
					<p>
						<span class="text-aide">还款日期</span>
						<fmt:formatDate value="${rsp.product.endDate}" pattern="yyyy-MM-dd" />
					</p>
					<div class="repayment-tip">
						<div class="btn">还款计划<i></i></div>
						<div class="repayment-con">
							<span class="icon-arrow"></span>
							<span class="icon-arrow01"></span>
							<div class="title"><span class="num">期数</span><span class="date">还款日期</span></div>
							<ul>
								<c:if test="${rsp.product.returnMethod == 1 }">
									<li>
										<span class="num">1</span>
										<span class="date">
											<fmt:formatDate value="${rsp.product.endDate}" pattern="yyyy-MM-dd" /> 利息+本金
										</span>
									</li>
								</c:if>
								<c:if test="${rsp.product.returnMethod == 2 }">
									<c:forEach varStatus="status" var="pincome" items="${rsp.product.incomes }">
										<li>
											<span class="num">${status.index + 1}</span>
											<span class="date">
												<fmt:formatDate value="${pincome.payDate}" pattern="yyyy-MM-dd" />
												利息<c:if test="${(status.index + 1) == fn:length(rsp.product.incomes) }">+本金</c:if>
											</span>
										</li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="info-right">
			<c:if test="${rsp.product.status == 4 }">
				<p class="tip">
					可用余额：
					<span class="J_loginOOut">
						<a href="${selfSite}/zdjf/user/login?url=${selfSite}/zdjf%2Fproduct%2Fdetail%2F${rsp.product.productId}">登录</a>
						<span class="t-font">后可见</span>
					</span>
					<span class="J_loginIn hide" data-balance="<fmt:formatNumber value="${rsp.amount }" pattern="0.00" type="number" />">
						<span class="red ">
							<fmt:formatNumber value="${rsp.amount }" pattern="#,##0.00" type="number" />
						</span>
						<a href="javascript:void(0);" class="JRecharge">充值</a>
					</span>
				</p>
				<p>
					<input type="text" id="investAmount" <c:if test="${rsp.lastFlag == 1}">readonly value="<fmt:formatNumber value="${rsp.product.canBuyMoney}" pattern="0"/>"</c:if> placeholder="请输入<fmt:formatNumber value="${rsp.product.payAdd}" pattern="0" />的整数倍">
				</p>
				<p class="error-tip">
					<span class="J_errorTip">
						<c:if test="${rsp.lastFlag == 1}">
							<font style="color: #34abf3;">产品可投金额不足，请一次性投完</font>
						</c:if>
					</span>
					<a class="approve hide" href="${selfSite}/zdjf/center/safe">点击前往</a>
				</p>
				<p class="res-amount">
					投资￥<span id="money-span">10000</span>，预计获得收益：
					<span class="red bold" id="income-span">
						<fmt:formatNumber value="${rsp.income }" pattern="#,##0.00" type="number" />
					</span>
				</p>
				<a href="javascript:void(0);" class="invest-submit">立即投资</a>
			</c:if>

			<c:if test="${rsp.product.status == 5}">
				<div class="state-icon using"></div>
			</c:if>
			<c:if test="${rsp.product.status == 6 }">
				<div class="state-icon finish"></div>
			</c:if>

			<c:if test="${rsp.product.status == 31 }">
				<div class="countdown J_countdown" data-begintime="${rsp.product.willIssueTimes}">
					<p>可投金额：<span class="text-red">￥<fmt:formatNumber value="${rsp.product.canBuyMoney }" pattern="#,###" /></span></p>
					<p class="other-item"><i class="iconfont">&#xe601;</i>投标尚未开始，<a href="${selfSite}/zdjf/product/list">查看其它标 <i class="iconfont">&#xe604;</i></a></p>
					<p class="time">
						<%--<span class="days">--</span>天--%>
						<i class="iconfont">&#xe603;</i>距上线
						<span class="hours">--</span>:
						<span class="minutes">--</span>:
						<span class="seconds">--</span>
					</p>
				</div>
			</c:if>
		</div>
	</div>
	<!--projectInfo-->


	<!--intro 广告位 -->
	<!-- <div class="layout intro">
	    <ul>
	        <li>aa</li>
	        <li>bb</li>
	        <li>cc</li>
	        <li>dd</li>
	    </ul>
	</div> -->


	<!--fixBar begining-->
	<div id="fixedTitle" class="hide">
		<div class="fixedBar">
			<div class="layout">
				<ul class="J_scrollTitle">
					<li class="curr">项目详情</li>
					<li>风控审核</li>
					<li>合同资料</li>
					<li>投资记录</li>
				</ul>
				<div class="bar-r">
					<span class="text-red">年化收益
						<em><fmt:formatNumber value="${rsp.product.income }" pattern="0.00" />%</em>
					</span> 
					<a href="javascript:void(0);" class="J_fixSubmit">立即投资</a>
				</div>
			</div>
		</div>
	</div>
	<!--fixBar end-->


	<!--detail-->
	<div class="layout  clearfix">
		<div class="detail titleHeight">
			<!-- title -->
			<div class="title" id="absoluteTitle">
				<ul class="J_scrollTitle">
					<li class="curr">项目详情</li>
					<li>风控审核</li>
					<li>合同资料</li>
					<li>投资记录</li>
				</ul>
			</div>

			<div class="height20"></div>

			<!--项目详情-->
			<div class="lump border-normal">
				<div class="item-title bg-blue">
					<strong>项目详情</strong>
				</div>
				<div class="detail-info">
					<!-- <div class="text-aide con">该项目车辆市场指导价为人民币2.98万元/辆，5辆总价为14.9万元，借款金额为10万元。严格控制了借款金额，风控师审核适批。</div> -->

					<!-- 基本情况 -->
					<div class="title">项目概述</div>
					<p class="con text-aide">${rsp.product.productAttach.productDesc }</p>


					<!-- 出借人信息 -->
					<div class="title">出借人信息（原始债权人）</div>
					<div class="table">
						<c:if test="${rsp.product.lender.lenderType == 1 }">
							<table>
								<tr>
									<td class="na">姓名</td>
									<td class="co">${rsp.product.lender.name }</td>
									<td class="na">年龄</td>
									<td class="co">${rsp.product.lender.age }</td>
								</tr>
								<tr>
									<td>性别</td>
									<td>${rsp.product.lender.sexText }</td>
									<td>婚姻情况</td>
									<td>${rsp.product.lender.maritalText }</td>
								</tr>
								<tr>
									<td>地址</td>
									<td colspan="3">${rsp.product.lender.address }</td>
								</tr>
							</table>
						</c:if>
						<c:if test="${rsp.product.lender.lenderType == 2 }">
							<table>
								<tr>
									<td class="na">企业名称</td>
									<td class="co">${rsp.product.lender.compName }</td>
									<td class="na">注册时间</td>
									<td class="co"><fmt:formatDate value="${rsp.product.lender.regDate }" pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<td>法定代表人</td>
									<td>${rsp.product.lender.name }</td>
									<td>注册资本</td>
									<td><fmt:formatNumber value="${rsp.product.lender.regMoney }" pattern="0" type="number" />万元</td>
								</tr>
								<tr>
									<td>注册地址</td>
									<td colspan="3">${rsp.product.lender.compAddress }</td>
								</tr>
							</table>
						</c:if>
					</div>
					
					<c:if test="${null != rsp.product.lender.images && fn:length(rsp.product.lender.images) != 0 }">
						<div class="scroll_horizontal infoSwitch">
							<div class="owl-demo">
								<c:forEach var="img" items="${rsp.product.lender.images }">
									<div class="item">
										<a href="${img.imageUrl }" class="lightbox" rel="list1" title="${img.imageName}">
											<img src="${img.imageUrl }">
										</a>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:if>
					

					<!-- 借款人信息 -->
					<div class="title">借款人信息</div>
					<div class="table">
						<c:if test="${rsp.product.loaner.loanerType == 1 }">
							<table>
								<tr>
									<td class="na">姓名</td>
									<td class="co">${rsp.product.loaner.name }</td>
									<td class="na">年龄</td>
									<td class="co">${rsp.product.loaner.age }</td>
								</tr>
								<tr>
									<td>性别</td>
									<td>${rsp.product.loaner.sexText }</td>
									<td>婚姻情况</td>
									<td>${rsp.product.loaner.maritalText }</td>
								</tr>
								<tr>
								<tr>
									<td>地址</td>
									<td colspan="3">${rsp.product.loaner.address }</td>
								</tr>
								</tr>
							</table>
						</c:if>
						<c:if test="${rsp.product.loaner.loanerType == 2 }">
							<table>
								<tr>
									<td class="na">企业名称</td>
									<td class="co">${rsp.product.loaner.compName }</td>
									<td class="na">注册时间</td>
									<td class="co"><fmt:formatDate value="${rsp.product.loaner.regDate }" pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<td>法定代表人</td>
									<td>${rsp.product.loaner.name }</td>
									<td>注册资本</td>
									<td><fmt:formatNumber value="${rsp.product.loaner.regMoney }" pattern="0" type="number" />万元</td>
								</tr>
								<tr>
									<td>注册地址</td>
									<td colspan="3">${rsp.product.loaner.compAddress }</td>
								</tr>
							</table>
						</c:if>
					</div>

					<c:if test="${null != rsp.product.loaner.images && fn:length(rsp.product.loaner.images) != 0 }">
						<div class="scroll_horizontal infoSwitch">
							<div class="owl-demo">
								<c:forEach var="img" items="${rsp.product.loaner.images }">
									<div class="item">
										<a href="${img.imageUrl }" class="lightbox" rel="list2" title="${img.imageName }">
											<img src="${img.imageUrl }" alt="11">
										</a>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:if>


					<!-- 基本情况 -->
					<div class="title">借款用途及还款保障</div>
					<p class="con text-aide">
						${rsp.product.productAttach.lendUse }
					</p>


					<!-- 抵（质）押管理 
	                <div class="title">
	                  	  抵（质）押管理
	                </div>
	                <div class="text-aide con">
	                    	浙江易慧隆资产管理有限公司，注册资本为5000万元，主要从事企业资产管理、投资管理、实业投资等。现公司接受浙江广众金融服务外包有限公司的委托，代为管理抵（质）押物，并向祺天优贷平台投资人出具承诺函，如下所示：
	                </div>
	                <div class="pact">
	                    <img src="./images/detail/sfz-01.png">
	                </div>
					-->

				</div>
			</div>

			<!--风控审核-->
			<div class="lump border-normal titleHeight">
				<div class="item-title bg-blue">
					<strong>风控审核</strong>
				</div>
				<div class="detail-info">
					<!-- 财产保障信息 -->
					<div class="title">财产保障信息</div>
					<p class="con text-aide">
						${rsp.product.productAttach.protectMsg }
					</p>
					
					<div class="scroll_horizontal dataSwitch">
						<div class="owl-demo01">
							<c:forEach var="file" items="${rsp.product.files }">
								<c:if test="${file.fileType == 1}">
									<div class="item">
										<a href="${file.fileUrl}" class="lightbox" rel="list3" title="${file.fileName}">
											<img src="${file.fileUrl}">
										</a>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>


					<!-- 保障措施 -->
					<div class="title">保障措施</div>
					<div class="table ensure">

						<c:if test="${empty rsp.product.productAttach.protectModeList}">
							${rsp.product.productAttach.protectMode }
						</c:if>
						<c:if test="${!empty rsp.product.productAttach.protectModeList}">
							<table>
								<c:forEach varStatus="idx" var="item" items="${rsp.product.productAttach.protectModeList}">
									<tr>
										<td>${item.protectModeTitle}</td>
										<td>${item.protectModeText}</td>
									</tr>
								</c:forEach>
							</table>
						</c:if>


						<!-- 
						<table>
							<tr>
								<td class="num">1</td>
								<td class="na">债权推荐</td>
								<td class="text">项目借款项目由杭州**投资咨询有限公司推荐，该公司保证债权真实有效。</td>
							</tr>
							<tr>
								<td class="num">2</td>
								<td class="na">车辆评估</td>
								<td class="text">项目借款人提供的汽车由杭州**投资咨询有限公司专业二手车评估人员进行估值，严格控制借款金额与质押物价值的比例。</td>
							</tr>
							<tr>
								<td class="num">3</td>
								<td class="na">车辆质押安全</td>
								<td class="text">项目借款人提供的质押车辆，均停放在指定停车场，24小时安保措施。</td>
							</tr>
							<tr>
								<td class="num">4</td>
								<td class="na">法律保障</td>
								<td class="text">专业律师对平台模式及债权形成流程进行严格法律风险把控，并提供法律保障。</td>
							</tr>
							<tr>
								<td class="num">5</td>
								<td class="na">资金监管</td>
								<td class="text">投资资金由**支付进行资金托管实行专款专用实现实时监管，资金零挪用。</td>
							</tr>
						</table> -->
					</div>
				</div>
			</div>

			<!--合同资料-->
			<div class="lump border-normal titleHeight">
				<div class="item-title bg-blue">
					<strong>合同资料</strong>
				</div>
				<div class="detail-info">

					<!-- 合同协议 -->
					<div class="title tab J_picTab">

						<c:if test="${rsp.imageCount3 != 0}">
							<a href="javascript:void(0);" class="curr" data-tab="pact">合同协议</a>
						</c:if>
						<c:if test="${rsp.imageCount2 != 0}">
							<a href="javascript:void(0);" data-tab="lawyer">律师意见图片</a>
						</c:if>
						<c:if test="${rsp.imageCount4 != 0}">
							<a href="javascript:void(0);" data-tab="other">其他文件图片</a>
						</c:if>

					</div>

					<%--合同协议--%>
					<div class="scroll_horizontal pactSwitch J_pact">
						<div class="owl-demo02">
							<c:forEach var="file" items="${rsp.product.files }">
								<c:if test="${file.fileType == 3}">
									<div class="item">
										<a href="${file.fileUrl}" class="lightbox" rel="list4" title="${file.fileName}">
											<img src="${file.fileUrl}">
										</a>
									</div>			
								</c:if>
							</c:forEach>
						</div>
					</div>

					<%--律师意见图片--%>
					<div class="scroll_horizontal pactSwitch J_lawyer hide">
						<div class="owl-demo02">
							<c:forEach var="file" items="${rsp.product.files }">
								<c:if test="${file.fileType == 2}">
									<div class="item">
										<a href="${file.fileUrl}" class="lightbox" rel="list04" title="${file.fileName}">
											<img src="${file.fileUrl}">
										</a>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>

					<%--其他文件图片--%>
					<div class="scroll_horizontal pactSwitch J_other hide">
						<div class="owl-demo02">
							<c:forEach var="file" items="${rsp.product.files }">
								<c:if test="${file.fileType == 4}">
									<div class="item">
										<a href="${file.fileUrl}" class="lightbox" rel="list004" title="${file.fileName}">
											<img src="${file.fileUrl}">
										</a>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>

					<!-- 财产质押清单与银行回单 
	                <div class="title">
	                   	 财产质押清单与银行回单
	                </div>
	                <div class="receipt">
	                    <img src="./images/detail/sfz-01.png">
	                    <img src="./images/detail/sfz-01.png">
	                </div>
					-->

				</div>
			</div>

			<!--投资记录-->
			<div class="lump invest-list titleHeight">
				<div class="name">
					<strong>投资记录</strong>
				</div>
				<div class="list">
					<div class="invest-title bg-blue">
						<span class="w96">序号</span> 
						<span class="w230">投资用户</span> 
						<span class="w288">投资资金</span> 
						<span class="w195">投资时间</span>
					</div>
					<ul id="investList">
						<%--<c:forEach var="sale" varStatus="status" items="${rsp.productBuys }">--%>
							<%--<li>--%>
								<%--<span class="w96"> ${status.index + 1 } </span> --%>
								<%--<span class="w230"> ${sale.hidePhone } </span> --%>
								<%--<span class="w288">--%>
									<%--<i></i> --%>
									<%--<fmt:formatNumber value="${sale.amount }" pattern="#,###" type="number" />元--%>
								<%--</span> --%>
								<%--<span class="w195"> --%>
									<%--<fmt:formatDate value="${sale.payTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
								<%--</span>--%>
							<%--</li>--%>
						<%--</c:forEach>--%>
						<%----%>
						<%--<c:if test="${empty rsp.productBuys || fn:length(rsp.productBuys) == 0 }">--%>
							<%--<p class="invitation-empty">--%>
								<%--<i class="icon-invent"></i>--%>
								<%--<span>暂无投资记录</span>--%>
							<%--</p>--%>
						<%--</c:if>--%>

						<img src="${selfSite}/zdjf/res/product/images/detail-loading.gif" class="loading" alt="">
						
					</ul>
				</div>
				<!-- pages -->
				<div class="pages J_pages hide">
					<div id="Pagination" class="pagination">
						<div class="pagination">
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	
	<!-- 图片弹出层 -->
	<div class="alert" style="display:none;">
	    <div class="alert-darkbg"></div>
	    <div class="eject">
	    	<div class="close-icon">×</div>
	    	<p>
	        	<img src="" id="alertPic" alt="">
	        </p>
	    </div>
	</div>

	<!--设置托管账户弹窗-->
	<div class="alert alert-tip JChinapnrRegister hide">
		<div class="alert-darkbg J_alertClose"></div>
		<div class="eject">
			<div class="title"><i class="icon-close J_alertClose">×</i></div>
			<div class="content">
				<p class="tip-title">开通汇付天下托管账户</p>
				<p class="con">
					资金托管服务能有效保障您的资金安全，<br/>使资金进出均受银行监管。 <a href="/active/hftx.html" class="text-blue btn-more" target="_blank">了解更多>></a>
				</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JApprove">立即开通</a>
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
				<p class="tip-title">开通汇付天下托管账户</p>
				<p class="con">正在设置托管账户，请耐心等待</p>
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
				<p class="tip-title">开通汇付天下托管账户</p>
				<p class="con">请在新打开的汇付天下页面完成托管账户设置</p>
				<p>
					<a href="javascript:void(0);" class="btn-red JRegisterComplete">已完成</a>
					<a href="javascript:void(0);" class="btn-red-border JRegisterError">遇到问题未完成</a>
				</p>
			</div>
		</div>
	</div>

	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>

</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery.lightbox.min.js"></script>

<script type="text/javascript">

	jQuery(document).ready(function($){

		$('.lightbox').lightbox();

	});

</script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery.downCount.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/product/js/detail.js"></script>
<!-- 图片切换引用 -->
<script type="text/javascript" src="${selfSite}/zdjf/res/product/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
<!--分页-->
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/pagination.js"></script>
</html>