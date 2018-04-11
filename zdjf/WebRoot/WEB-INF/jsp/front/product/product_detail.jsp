<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zdjf.domain.product.Product" %>
<%@ page import="com.zdjf.util.RoofNumberUtils" %>
<%@ page import="com.zdjf.domain.product.ProductBuy" %>
<%@ page import="com.zdjf.domain.product.ProductBuyRob" %>
<%@ page import="com.zdjf.domain.user.Lender" %>
<%@ page import="com.zdjf.domain.user.Loaner" %>
<%@ page import="com.zdjf.domain.user.UserCoupon" %>
<%@ page import="com.zdjf.domain.user.UserGift" %>
<%@ page import="com.zdjf.domain.user.User" %>
<%@ page import="com.zdjf.domain.file.File" %>
<%@ page import="com.zdjf.domain.product.ProductIncome" %>
<%@ page import="com.zdjf.domain.fund.UserFundStat" %>
<%@page import="com.zdjf.util.StringUtil"%>
<%@page import="com.zdjf.util.DateUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
	Product product = (Product)request.getAttribute("product");
	List productBuyVoList = (List)request.getAttribute("productBuyVoList");
	Lender lender = (Lender)request.getAttribute("lender");
	Loaner loaner = (Loaner)request.getAttribute("loaner");
	List lenderImgList = (List)request.getAttribute("lenderImgList");
	List loanerImgList = (List)request.getAttribute("loanerImgList");
	List productImgList = (List)request.getAttribute("productImgList");
	List productIncomeList = (List)request.getAttribute("productIncomeList");
	UserFundStat userFundStat = (UserFundStat)request.getAttribute("userFundStat");
	Map hashMap = (Map) request.getAttribute("hashMap");
	List productBuyRobList = (List) request.getAttribute("productBuyRobList");
	List userCouponList = (List) request.getAttribute("userCouponList");
	List userGiftList = (List) request.getAttribute("userGiftList");
	String error = (String)request.getAttribute("error");
	User user = (User)request.getAttribute("user");
	int is_newHand = 0;
	Long day = (product.getEnd_date().getTime()-new Date().getTime())/(1000*60*60*24)+1;
	if(product != null && product.getStatus()>=5){
		day = Long.valueOf((product.getEnd_date().getTime()-product.getStart_date().getTime())/(1000*3600*24));
	}
	if(user!=null){
		if(user.getNew_hand()!=1 && product.getIs_fresh()==1){
			is_newHand =1;
		}
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title><%=product.getProduct_name() %> - 正道金服</title>
	<!-- reset/iconfont -->
	<link rel="stylesheet" href="<%=path%>/css/front/reset.css">
	<link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
	<link rel="stylesheet" href="<%=path%>/module/slides/flexslider.css">
	<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
	<link rel="stylesheet" href="<%=path%>/css/front/index.css">
	<link rel="stylesheet" href="<%=path%>/css/front/project.css">
</head>
<body>
	<!-- header -->
	<div class="header">
		<jsp:include page="../common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="pro_detail">
		<!-- 债转详情 -->
		<div class="catalog">
			<div class="ca_lf">
				<h3><%=product.getProduct_code() %><span><%=product.getProduct_name() %></span><%if(product.getIs_fresh()==1){%><span class="pro_label">新手标</span><%} %></h3>
				<div class="statistics">
					<div class="sta1">
						<p><%=product.getIncome()-product.getPlatform_interest() %><%if(product.getPlatform_interest()>0.0){ %>+<%=product.getPlatform_interest() %><%} %><i>%</i></p>
						<p>预期年化收益</p>
					</div>
					<div class="sta2">
						<p><%=day %><i>天</i></p>
						<p>收益天数</p>
					</div>
					<div class="sta3">
						<p><%=product.getMoney() %><i>元</i></p>
						<p>项目总额</p>
					</div>
				</div>
				<div class="detail_bar">
					<div class="layui-progress detail_bar">
						<div class="layui-progress-bar layui-progress-detailbar layui-bg-zdjforange" lay-percent="<%=RoofNumberUtils.formatString(product.getSale_money()/product.getMoney()*100.0)%>%"><img src="<%=path%>/images/front/img/bar.png" alt=""></div>
					</div>
					<span class="bar_sum"><%=RoofNumberUtils.formatString(product.getSale_money()/product.getMoney()*100.0)%>%</span>
				</div>
				<div class="de_amount">
					<div class="amount1">
						<p>起投金额：<span><%=product.getPay_min() %>元</span></p>
						<p>起息时间：<span>T+1 投资成功后次日起息</span></p>
					</div>
					<div class="amount2">
						<p>剩余可投：<span><%=product.getBalance() %>元</span></p>
						<p>还款方式：<span>按日计息，按月付息，到期还本</span></p>
					</div>
				</div>
				<div class="de_date" <%if(userFundStat !=null){ %>style="margin-top: 25px;"<%} %>>
					<div class="state sta1">起<span>起息日期：<%if(product.getStatus()>= 5){ %><%= DateUtil.formatDate(DateUtil.addDate(product.getStart_date(), 1))%><%}else{ %><%=DateUtil.formatDate(DateUtil.addDate(new Date(), 1)) %><%} %></span></div>
					<div class="de_line"></div>
					<div class="state sta2">还款计划<i class="iconfont">&#xe697;</i>
						<div class="tlt">
							<table>
								<thead>
									<tr>
										<th>期数</th>
										<th>还款日期</th>
									</tr>
								</thead>
								<tbody>
								<%
									if(productIncomeList!=null && productIncomeList.size()>0){
										for(int i=0;i<productIncomeList.size();i++){
											ProductIncome productIncome = (ProductIncome)productIncomeList.get(i);
								 %>
								 	<tr>
										<td><%=i+1 %></td>
										<td><%= DateUtil.formatDate(productIncome.getEnd_date()) %><br>利息 <%if(i==productIncomeList.size()-1){ %>+本金<%} %></td>
									</tr>
								 <%}}else{ %>
									<tr>
										<td>1</td>
										<td><%= DateUtil.formatDate(product.getEnd_date())%><br>利息+本金</td>
									</tr>
									<%} %>
								</tbody>
							</table>
						</div>
					</div>
					<div class="de_line"></div>
					<div class="state sta3">还<span>还款日期：<%= DateUtil.formatDate(product.getEnd_date())%></span></div>
				</div>
			</div>
			<!-- 已登录 -->
			<%if(userFundStat!=null){ %>
			<div class="ca_rt">
				<h3>账户可用余额：<span><%=userFundStat.getBalance() %></span><a href="javascript:checkUser(1);">去充值</a></h3>
				<div class="error" id="error"><%=error!=null?error:"" %></div>
				<form id="form1" method="post" action="<%=path%>/pay/buy.action">
				<ul>
					<li class="rt_one"><input type="text" id="amount" name="amount" class="investor" onkeyup="get_gift(this.value)" placeholder="100元起投"><a href="javascript:allInvestor();">全投</a></li>
					<li class="interest"><input type="text" placeholder="请选择加息券" class="jiaxi" readonly="readonly"><span class="tlt"></span><a href="javascript:"><i class="iconfont">&#xe697;</i></a>
						<div class="interbox hide" id="cou">
							<div class="interlist"  <%if(userCouponList == null || userCouponList.size()== 0){ %>style="display: none;"<%} %> >
								<div class="zhezhao"></div>
								<div class="num">请选择加息券</div>
								<div class="numtxt">
									<p></p>
								</div>
							</div>
							<div class="interlist no_jxq" <%if(userCouponList != null && userCouponList.size() > 0){ %>style="display: none;"<%} %>>
								<div class="zhezhao"></div>
								<div class="num">暂无可使用加息券
									<span></span>
								</div>
								<div class="numtxt">
									<p></p>
								</div>
							</div>
							<%
								if(userCouponList!=null && userCouponList.size()>0){
									for(int i=0;i<userCouponList.size();i++){
										UserCoupon userCoupon = (UserCoupon)userCouponList.get(i);
							 %>
							<div class="interlist">
								<div class="zhezhao"></div>
								<div class="num">+<%=userCoupon.getInterest()%>%
									<%-- <span>投资≥<%=userCoupon.getMin_amount()%>元</span> --%>
								</div>
								<div class="numtxt">
									<p>
										<%if (userCoupon.getUse_type() == 1) {%>限全部<%} %>
										<%if (userCoupon.getUse_type() == 2) {%>限新手标<%} %>
										<%if (userCoupon.getUse_type() == 3) {%>限非新手标<%} %>
									</p>
									<p>收益天数≥<%=userCoupon.getMin_days()%>天</p>
									<p>有效时间 <%=DateUtil.formatDate(userCoupon.getEnd_date()) %></p>
								</div>
								<input class="userInterest" type="hidden" value="<%=userCoupon.getInterest()%>">
								<input class="userid" type="hidden" value="<%=userCoupon.getId()%>">
								<input class="dayone" type="hidden" value="<%=userCoupon.getMin_days()%>">
								<input class="monone" type="hidden" value="<%=userCoupon.getMin_amount()%>">
							</div>
							<%}} %>
						</div>
					</li>
					<input type="hidden" id="incomeDays" value="">
					<input type="hidden" id="couponId" name="couponId" value="">
					<input type="hidden" id="giftId" name="giftId" value="">
					<input type="hidden" id="balance" name="balance" value="<%=userFundStat.getBalance() %>">
					<input type="hidden" id="product_balance" name="product_balance" value="<%=product.getBalance() %>">
					<input type="hidden" id="productId" name="productId" value="<%=product.getId() %>">
					<input type="hidden" id="coinState" name="coinState" value="0">
					<li class="redbag"><input class="hongbao" type="text" placeholder="请选择红包券" readonly="readonly"><span class="tlt"></span><a href="javascript:"><i class="iconfont">&#xe697;</i></a>
						<div class="zhengjing layui-form">
							<p>
								<input type="text" placeholder="开启正经值使用抵扣优惠" readonly="readonly" class="zjzinput">
								<input type="checkbox" name="switch" lay-skin="switch">
							</p>
						</div>
						<div class="ovderlist">
							<p>
								<i class="iconfont">&#xe602;</i>
								<span>正经值余额：<i><%=userFundStat.getCoin_balance()%>元</i>,</span>
								<input type="hidden" id="coinBlance" value="<%=userFundStat.getCoin_balance()%>"/>
								<span>本次可用：<i id="activeIcon">0.00元</i></span>
							</p>
							<p style="display: none;">
								<i class="iconfont">&#xe602;</i>
								<span>单次最大抵用金额为投资本金的1.0%</span>
							</p>
						</div>
						<p>预计收益：<span id="yjsy">0.00元</span></p>
						<div class="redbox hide">
							<div class="redlist" <%if(userGiftList == null || userGiftList.size() == 0){ %>style="display: none;"<%} %>>
								<div class="zhezhao"></div>
								<div class="num">请选择红包券</div>
								<div class="numtxt">
									<p></p>
								</div>
							</div>
							<div class="redlist no_hb" <%if(userGiftList != null && userGiftList.size() > 0){ %>style="display: none;"<%} %>>
								<div class="zhezhao"></div>
								<div class="num">暂无可使用红包券
									<span></span>
								</div>
								<div class="numtxt">
									<p></p>
								</div>
							</div>
							<%
								if(userGiftList!=null && userGiftList.size()>0){
									for(int i=0;i<userGiftList.size();i++){
										UserGift userGift = (UserGift)userGiftList.get(i);
							 %>
							<div class="redlist">
								<div class="zhezhao"></div>
								<div class="num">￥<%=userGift.getAmount() %>
									<span>投资≥<%=userGift.getMax_amount() %>元</span>
								</div>
								<div class="numtxt">
									<p>
										<%if (userGift.getUse_type() == 1) {%>限全部<%} %>
										<%if (userGift.getUse_type() == 2) {%>限新手标<%} %>
										<%if (userGift.getUse_type() == 3) {%>限非新手标<%} %>
									</p>
									<p>收益天数≥<%=userGift.getMax_days() %>天</p>
									<p>有效时间  <%=DateUtil.formatDate(userGift.getEnd_date()) %></p>
								</div>
								<input type="hidden" class="useridtwo" value="<%=userGift.getId() %>">
								<input type="hidden" class="montwo" value="<%=userGift.getMax_amount() %>">
								<input type="hidden" class="daytwo" value="<%=userGift.getMax_days() %>">
							</div>
							<%}} %>

						</div>
					</li>
					<li class="rt_four">
						<%if(product.getStatus()==4 && product.getBalance()>0){ %><a href="javascript:checkUser(2);" class="investbtn">立即投资</a><%} %>
					</li>
				</ul>
				</form>
				<div class="agreement layui-form">
					<p><input type="radio" title="我已阅读并同意" name="radio" lay-skin="radio" checked="checked"><a href="<%=path%>/static/front/zdjfu_pc/zdjf_protocol.html" target="_blank">《正道金服服务协议》</a></p>
					<p>理财有风险，投资需谨慎</p>
				</div>
			</div>
			<%}else{ %>
			<!-- 未登录 -->
			<div class="ca_rt">
				<div class="nologin">
					<p class="no_lg1">账户可用余额：请<a href="<%=path%>/toLogin.action?url=product/detail/<%=product.getId()+".action" %>">登录</a>或<a href="<%=path%>/toRegister.action">注册</a>后查看</p>
					<p class="no_lg2"><input type="text" id="amount2" onkeyup="calculate(this.value);" placeholder="100元起投"><a href="javascript:calculate('<%=product.getBalance()%>');">全投</a></p>
					<p class="no_lg3">
						<span>预计收益：<i id="yjsy2">0.00元</i></span>
					</p>
					<%if(product.getStatus()==4 && product.getBalance()>0){ %><p class="no_lg4 "><a href="javascript:">立即投资</a></p><%} %>
					<p class="no_lg5">理财有风险，投资需谨慎</p>
				</div>
			</div>
			<%} %>
		</div>
		<div class="catatab">
			<div class="tab">
				<a href="javascript:" class="active">项目详情</a>
				<a href="javascript:">风控审核</a>
				<a href="javascript:">合同资料</a>
				<a href="javascript:">投资记录</a>
			</div>
			<div class="tabbox">
				<div class="tabtent">
					<div class="tabhead">
						<h4>项目概述</h4>
						<div class="logotxt">
							<%=product.getProduct_desc() %>
						</div>
					</div>
					<div class="tabfoot">
						<div class="tentbox">
							<div class="people">
								<h4>出借人信息（原始债权人）</h4>
								<p><span>姓名：<%=StringUtil.hideStr(1, lender.getName(), 0) %></span><span>年龄：<%=lender.getAge() %></span></p>
								<p><span>性别：<%=lender.getSex() %></span><span>婚姻状况：<%if(lender.getMarital()==1){ %>已婚<%}else{ %>未婚<%} %></span></p>
								<p>现居地址：<%=StringUtil.hideStr(6, lender.getAddress(), 0) %></p>
							</div>
							<div class="peobanner">
								<div class="layui-carousel" id="idbanner">
								  	<div carousel-item>
								  		<%
								  		if(lenderImgList!=null && lenderImgList.size()>0){
								  			for(int i=0;i<lenderImgList.size();i++){
								  			File file = (File)lenderImgList.get(i);
								  		 %>
									    <div><img src="<%=file.getFile_url() %>" alt=""></div>
									    <%}} %>
								  	</div>
								</div>
							</div>
						</div>
						<div class="tentbox">
							<div class="people">
								<h4>借款人信息</h4>
								<p><span>姓名：<%=StringUtil.hideStr(1, loaner.getName(), 0) %></span><span>年龄：<%=loaner.getAge() %></span></p>
								<p><span>性别：<%=loaner.getSex() %></span><span>婚姻状况：<%if(loaner.getMarital()==1){ %>已婚<%}else{ %>未婚<%} %></span></p>
								<p>现居地址：<%=StringUtil.hideStr(6, loaner.getAddress(), 0) %></p>
							</div>
							<div class="peobanner">
								<div class="layui-carousel" id="idbanner2">
								  	<div carousel-item>
									    <%
								  		if(loanerImgList!=null && loanerImgList.size()>0){
								  			for(int i=0;i<loanerImgList.size();i++){
								  			File file = (File)loanerImgList.get(i);
								  		 %>
									    <div><img src="<%=file.getFile_url() %>" alt=""></div>
									    <%}} %>
								  	</div>
								</div>
							</div>
						</div>
					</div>
					<div class="source">
						<h4>还款来源</h4>
						<%=product.getLend_use() %>
					</div>
				</div>
				<div class="tabtent" style="display: none;">
					<div class="risk">
						<h4>财产保障信息</h4>
						<%=product.getProtect_msg() %>
					</div>
					<div class="riskbanner">
						<div id="fengkong_id" class="cxscroll2">
						  <div class="box">
						    <ul class="list">
						    	<%
								if(productImgList!=null && productImgList.size()>0){
								  	for(int i=0;i<productImgList.size();i++){
								  		File file = (File)productImgList.get(i);
								  		if(file.getFile_type()==1){
								%>
						      	<li><img src="<%=file.getFile_url() %>" /></li>
						      	<%}}} %>
						    </ul>
						  </div>
						  <!-- 控制按钮会自动创建，可省略 -->
						  <a class="iconfont prev">&#xe600;</a>
						  <a class="iconfont next">&#xe697;</a>
						</div>
					</div>
					<div class="guarantee">
						<h4>保障措施</h4>
						<%
						if(product.getProtect_mode()!=null && product.getProtect_mode().indexOf("protectModeTitle:")!=-1){
							String model = product.getProtect_mode();
							model = model.replace("[{", "");
							model = model.replace("'}]", "");
							model = model.replace("'},{", "");
							model = model.replace("'},{", "");
							model = model.replace("'业务来源'", "");
							model = model.replace("'风控审核'", "");
							model = model.replace("'抵押管理'", "");
							model = model.replace("'法律监管'", "");
							model = model.replace("'资金监管'", "");
							model = model.replace("'项目赎回'", "");
							model = model.replace(",protectModeText:'", "");
							String str[] = model.split("protectModeTitle:");
						%>
						<table>
							<tr>
								<td>业务来源</td>
								<td><%=str[1] %></td>
							</tr>
							<tr>
								<td>风控审核</td>
								<td><%=str[2] %></td>
							</tr>
							<tr>
								<td>抵押管理</td>
								<td><%=str[3] %></td>
							</tr>
							<tr>
								<td>法律监管</td>
								<td><%=str[4] %></td>
							</tr>
							<tr>
								<td>资金监管</td>
								<td><%=str[5] %></td>
							</tr>
							<tr>
								<td>项目赎回</td>
								<td><%=str[6] %></td>
							</tr>
						</table>
						<%}else{ %>
						<%=product.getProtect_mode() %>
						<%} %>
					</div>
				</div>
				<div class="tabtent" style="display: none;">
					<div class="hetongbtn">
						<a href="javascript:" class="active">合同协议</a>
						<a href="javascript:">其他文件</a>
					</div>
					<div class="hetongtab">
						<div class="hetong">
							<div id="hetong_id" class="cxscroll">
							  <div class="box">
							    <ul class="list">
							    <%
								if(productImgList!=null && productImgList.size()>0){
								  	for(int i=0;i<productImgList.size();i++){
								  		File file = (File)productImgList.get(i);
								  		if(file.getFile_type()==3){
								%>
						      	<li><img src="<%=file.getFile_url() %>" /></li>
						      	<%}}} %>
							    </ul>
							  </div>
							  <!-- 控制按钮会自动创建，可省略 -->
							  <a class="iconfont prev">&#xe600;</a>
							  <a class="iconfont next">&#xe697;</a>
							</div>
						</div>
						<div class="hetong" style="display: none;">
							<ul class="qita">
								<%
								if(productImgList!=null && productImgList.size()>0){
								  	for(int i=0;i<productImgList.size();i++){
								  		File file = (File)productImgList.get(i);
								  		if(file.getFile_type()==4){
								%>
						      	<li><img src="<%=file.getFile_url() %>" /></li>
						      	<%}}} %>
							</ul>
						</div>
					</div>
				</div>
				<div class="tabtent" style="display: none;">
					<div class="record">
						<div class="record_lf">
							<table>
								<thead>
									<tr>
										<th>投资用户</th>
										<th>投资金额</th>
										<th>年化收益</th>
										<th>预期收益</th>
										<th>投资时间</th>
									</tr>
								</thead>
								<tbody>
								<%
									if(productBuyVoList!=null && productBuyVoList.size()>0){
										for(int i=0;i<productBuyVoList.size();i++){
											ProductBuy productBuy = (ProductBuy)productBuyVoList.get(i);
											UserCoupon c = null;
											if(hashMap!=null && hashMap.get(productBuy.getUser_coupon_id())!=null){
												c = (UserCoupon)hashMap.get(productBuy.getUser_coupon_id());
											}
								 %>
									<tr>
										<td><% if(productBuy.getReq_source()==1) {%><i class="iconfont pc"></i><%} else {%><i class="iconfont phone"></i><%} %><%=StringUtil.hideStr(3, productBuy.getPhone(), 4) %></td>
										<td><span class="money"><%=productBuy.getAmount() %>元</span></td>
										<td><%=product.getIncome() %>% <%if(hashMap!=null && hashMap.get(productBuy.getUser_coupon_id())!=null){ %>+ <span class="money"><%=c.getInterest()%>%加息券</span><%} %></td>
										<td><%=productBuy.getWill_income() %>元</td>
										<td><%=DateUtil.formatDateTime(productBuy.getPay_time()) %></td>
									</tr>
									<%}} %>
								</tbody>
							</table>
						</div>
						<div class="record_rt">
							<div class="record_details">
								<a href="javascript:;">详情<i class="iconfont">&#xe697;</i></a>
							</div>
							<div class="recordBox record_1">
								<ul>
									<li><i class="gold"></i><div>133****3436</div>奖励<span>5.00</span>元</li>
									<li><i class="silverMedal"></i><div>133****3436</div>奖励<span>5.00</span>元</li>
									<li><i class="copperMedal"></i><div>133****3436</div>奖励<span>5.00</span>元</li>
								</ul>
							</div>
							<div class="recordBox record_2">
								<ul>
									<li><i class="gold"></i><span class="xydw">虚以待位</span>奖励<span>--</span>元</li>
									<li><i class="silverMedal"></i><span class="xydw">虚以待位</span>奖励<span>--</span>元</li>
								</ul>
							</div>
							<div class="recordBox record_3">
								<ul>
									<li><i class="gold"></i><span class="xydw">虚以待位</span>奖励<span>--</span>元</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- content end-->


	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<!-- footer end -->
	<!-- 前往开通上海银行存管弹窗 -->
	<div class="addmove bank_open" style="display: none;" id="bankOpen">
        <div class="move">
            <h3>正道金服正式接入上海银行资金存管<span class="closeFun" onclick="closeAddmove();"></span></h3>
            <p>您需要开通上海银行存管，才可以进行投资、充值、提现等操作。</p>
            <a href="<%=path%>/toNewAudit.action">立即开通</a>
        </div>
    </div>

	<!-- 点击立即投资弹窗 -->
	<div class="addmove bank_bind_wad" style="display: none;">
        <div class="move">
            <h3>正道金服正式接入上海银行资金存管<span class="closeFun" onclick="closeAddmove();"></span></h3>
            <p>您尚未绑定银行卡和设置密码，绑定银行卡和设置支付密码后方可进行操作。</p>
            <a href="<%=path%>/toNewAudit.action">前往设置</a>
            <a href="javascript:void(0);" onclick="closeAddmove();">稍后设置</a>
        </div>
    </div>

    <!-- 用户未绑定银行卡弹窗 -->
	<div class="addmove bank_bind" style="display: none;" id="bankBind">
        <div class="move">
            <h3>正道金服正式接入上海银行资金存管<span class="closeFun" onclick="closeAddmove();"></span></h3>
            <p>您尚未绑定银行卡，绑定银行卡后方可进行操作</p>
            <a href="<%=path%>/toAddBankCard.action">立即绑定</a>
            <a href="javascript:void(0);" onclick="closeAddmove();">稍后绑定</a>
        </div>
    </div>

	<!-- 用户未设置支付密码弹窗 -->
	<div class="addmove pay_wad" style="display: none;" id="payPasWrdSet">
        <div class="move">
            <h3>正道金服正式接入上海银行资金存管<span class="closeFun" onclick="closeAddmove();"></span></h3>
            <p>您尚未设置支付密码，设置支付密码后方可进行操作</p>
            <a href="<%=path%>/toNewAudit.action">前往设置</a>
            <a href="javascript:void(0);" onclick="closeAddmove();">稍后设置</a>
        </div>
    </div>
    <script src="<%=path%>/module/jquery/jquery.min.js"></script>
    <script src="<%=path%>/module/cxscroll/jquery.cxscroll.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/module/layui/layui.js"></script>
	<script src="<%=path%>/js/front/project.js"></script>
	<script type="text/javascript">
		$('.header').stickMe({
			topOffset:100
		})
		$('#hetong_id').cxScroll();
		$('#fengkong_id').cxScroll();
        //产品可投金额
        var product_balance = parseInt($("#product_balance").val());
        //用户正经值余额
        var coinBlance = $("#coinBlance").val();
        //计算正经值本次可用显示
        function showCoin(coinBlance, coinLimit) {
            return parseFloat(coinLimit) > parseFloat(coinBlance) ? coinBlance : coinLimit;
        }
        //数据初始化
        $(function(){
            if ($("#amount").val().length > 0 ) {
                var coinLimit = ($("#amount").val()/100).toFixed(2);
                var activeCoin = showCoin(coinBlance,coinLimit);
                $("#activeIcon").html(activeCoin + "元");
                calculate($("#amount").val());
            }
            var jiaxi = $(".jiaxi").val();
            if (jiaxi.length > 0) {
                $(".jiaxi").val("");
            }
            var hobao = $(".hongbao").val();
            if (hobao.length > 0) {
                $(".hongbao").val("");
            }
            var zjz = $(".zjzinput").val();
            if (zjz.length > 0) {
                $("#coinState").val("1");
                $(".redbag .redbox").hide();
            }
            if (product_balance < 100 && product_balance > 0) {
                $("#amount").val(product_balance);
                $("#amount").attr("readonly","readonly");
                $("#amount").removeAttr("onkeyup");
                var activeCoin = (product_balance/100).toFixed(2);
                $("#activeIcon").html(activeCoin + "元");
                $('.redlist').each(function () {
                    var montwo = parseInt($(this).find(".montwo").val());
                    if(product_balance<montwo){
                        $(this).removeClass('xiaoyu')
                        $(this).addClass('dayu')
                    }else{
                        $(this).removeClass('dayu')
                        $(this).addClass('xiaoyu')
                    }
                });
                $('.interlist').each(function () {
                    var mon = parseInt($(this).find(".monone").val());
                    if(product_balance<mon){
                        $(this).removeClass('xiaoyu')
                        $(this).addClass('dayu')
                    }else{
                        $(this).removeClass('dayu')
                        $(this).addClass('xiaoyu')
                    }
                });
            }
        });
        var ll= <%=product.getIncome()%>;
        var day = <%=day%>;
        function calculate(no_, userInterest){
            if (isNaN(no_)) {
                //$("#amount").val("");
                $("#error").html("请输入正整数");
                return;
            }else if(no_ < 100) {
                $("#error").html("必须100元起投");
            }else{
                $("#error").html("");
            }
            if (String(no_).indexOf(".")>-1) {
                $("#amount").val(parseInt(no_));
            }
            var sy = userInterest>0?(no_ * (parseFloat(ll)+parseFloat(userInterest)) / 360  * day / 100):(no_ * ll / 360  * day / 100);
            $("#yjsy").html(sy.toFixed(2)+"元");
            var coinLimit = ($("#amount").val()/100).toFixed(2);
            var activeCoin = showCoin(coinBlance,coinLimit);
            $("#activeIcon").html(activeCoin + "元");
            if ($("#coinState").val() == 1) {
                $(".zjzinput").removeAttr("readonly");
                $(".zjzinput").val($("#activeIcon").text());
                $(".zjzinput").attr("readonly","readonly");
            }
        }
        function get_gift(amount){
            calculate(amount);
        }
        //投资提交
        function dosubmit(){

            //用户输入投资金额
            var amount = $("#amount").val();
            var re = /^[0-9]+$/ ;
            if(!re.test(amount)){
                $("#error").html("请输入正整数");
                return ;
            }
            if(amount<100  && product_balance >= 100){
                $("#error").html("必须100元起投");
                return ;
            }
            if('1'=='<%=is_newHand%>'){
                $("#error").html("非新手用户无法购买新手标。");
                return ;
            }
            $.ajax({
                type: 'POST',
                url: '<%=path %>/pay/orderInfoCheck.action',
                data: 'couponId='+$("#couponId").val()+"&giftId="+$("#giftId").val()+"&amount="+$("#amount").val()+"&productId="+$("#productId").val(),
                dataType: 'json',
				success: function(data){
                    if (data.status == 101) {
                        $("#error").html(data.content);
                        return;
                    } else {
                        $("#form1").submit();
                    }
                },
				error:function () {
                    $("#error").html("网络异常，请稍后再试！");
                }
            });
        }
        //立即投资前校验用户
        function checkUser(type) {
            //实名认证标示 1：审核通过   2:  处理中 ，-1：审核未通过
            var realNameAuth = <%= user!=null?user.getReal_name_auth():0%>;
            var bankCardState = <%= user!=null?user.getStatus():0%>
            if (realNameAuth != 1 || bankCardState < 2) {
                $("#bankOpen").show();
                //绑卡状态：1绑卡短信未确定 2绑卡短信已确定未设定支付密码 3 支付密码已设置成功 4 有支付密码已经解绑快捷支付
            } else if (bankCardState == 4) {
                $("#bankBind").show();
                //未设置支付密码
            } else if (bankCardState == 2) {
                $("#payPasWrdSet").show();
            } else {
                if (type == 1) { //去充值
                    window.location.href="<%=path%>/pay/charge.action";
				}
				if (type == 2) { //立即投资
                    dosubmit();
				}
            }
        }
        //全投
        function allInvestor() {
            if (product_balance >= 100) {
                var userBlance = <%= userFundStat!=null?userFundStat.getBalance():0 %>;
                if (userBlance <= product_balance && userBlance >= 100) {
                    $("#amount").val(parseInt(userBlance))
                }
                if (userBlance >= product_balance && product_balance >= 100) {
                    $("#amount").val(parseInt(product_balance))
                }
                if (userBlance < 100) {
                    $("#error").html("可用余额不足，请充值！");
                }
                calculate($("#amount").val());
            }
        }

        //弹窗关闭
        function closeAddmove() {
        	$(".addmove").hide();
        }
	</script>
</body>
</html>