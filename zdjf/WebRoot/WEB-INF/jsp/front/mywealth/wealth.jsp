<%@ page language="java" import="com.zdjf.domain.fund.UserFundStat" pageEncoding="UTF-8"%>
<%@ page import="com.zdjf.domain.product.Product" %>
<%@ page import="com.zdjf.domain.product.ProductBuyIncome" %>
<%@ page import="com.zdjf.domain.user.User" %>
<%@ page import="com.zdjf.util.DateUtil" %>
<%@ page import="com.zdjf.util.RoofNumberUtils" %>
<%@ page import="com.zdjf.util.StringUtil" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ path + "/";
	User user = (User)request.getAttribute("user");
	Integer giftCount = (Integer)request.getAttribute("giftCount");
	Integer couponCount = (Integer)request.getAttribute("couponCount");
	UserFundStat userFundStat = (UserFundStat)request.getAttribute("userFundStat");
	List userBuyList = (List)request.getAttribute("userBuyList");
	Map pb = (Map)userBuyList.get(0);
	Double giftUsedNum = (Double)request.getAttribute("giftUsedNum");
	String monthStr = (String)request.getAttribute("monthStr");
	Double monthAmout  = (Double)request.getAttribute("monthAmout");
	Integer todaycount  = (Integer)request.getAttribute("todaycount");
	String today  = (String)request.getAttribute("today");
	Double todayAmout  = (Double)request.getAttribute("todayAmout");
	Map hkhashMap = (Map)request.getAttribute("hkhashMap");
	Double maxAmount = (Double)request.getAttribute("maxAmount");
	String yearDate = (String)request.getAttribute("yearDate");
	Double allAmount = (Double)request.getAttribute("allAmount");
	List todayIncomeList = (List)request.getAttribute("todayIncomeList");
	List userThisMonthIncomeList = (List)request.getAttribute("userThisMonthIncomeList");
	Map proHashMap = (Map)request.getAttribute("proHashMap");
	String type = (String)request.getAttribute("type");
	String gnh_balance = (String)request.getAttribute("gnh_balance");
	Double totalIncome = (Double)request.getAttribute("totalIncome");
	String isSignTimes = (String) request.getAttribute("isSignTimes");
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
			<div class="wealthbox we_rtbox">
				<div class="subject">
					<div class="sub_logo"><a href="<%=path%>/"><img src="<%=path%>/images/front/img/wealth/sublogo.png" alt=""></a></div>
					<div class="sub_tent">
						<div class="tlt">
							<p><span class="name"><%=StringUtil.hideStr(3, user.getUser_name(), 4) %></span>,欢迎登录到个人财富中心！</p>
							<div class="realbox iconfont<%if(user.getReal_name_auth()==1){ %> active<%}%>"><i class="iconfont shiming"></i><%if(user.getReal_name_auth()==0){ %><div class="realname">您尚未实名认证。<a href="<%=path%>/toNewAudit.action">立即认证</a></div><%} %>
							</div>
							<div class="realbox iconfont<%if(user.getStatus()==3){ %> active<%}%>"><i class="iconfont bangka"></i><%if(user.getStatus()!=3){ %><div class="realcard">您尚未绑定提现银行卡。<a href="<%=path%>/toNewAudit.action">立即设置</a></div><%} %>
							</div>
						</div>
						<div class="txt">上次登录时间： <%=DateUtil.formatDateTimeS(user.getLast_login_time()) %></div>
					</div>
					<div class="sub_num">
						<ul>
							<li><a href="<%=path%>/myCou.action?type=1&discount_type=1&dis_type=1"><i class="iconfont hongbao"></i>红包券<span><%=giftCount %></span>张</a></li>
							<li><a href="<%=path%>/myCou.action?type=2&discount_type=1&dis_type=1"><i class="iconfont jiaxi"></i>加息券<span><%=couponCount %></span>张</a></li>
							<li><a href="<%=path%>/myCou.action"><i class="iconfont zhengjing"></i>正经值<span><%=userFundStat.getCoin_balance() %></span>元</a></li>
						</ul>
						<div class="sign">
							<%if(isSignTimes.equals("1")){ %> <a href="<%=path%>/toSign.action"><img src="<%=path%>/images/front/img/wealth/logo.png" alt=""></a><%}%>
							<%if(isSignTimes.equals("0")){ %> <a class="layui-disabled"><img src="<%=path%>/images/front/img/wealth/logoed.png" alt=""></a><%}%>
						</div>
					</div>
				</div>
				<div class="assets"> 
					<div class="ass">
						<h4>总资产(元)</h4>
						<p class="xs"><%=RoofNumberUtils.formatString(userFundStat.getPend_income()+userFundStat.getBalance()+userFundStat.getPend_return()) %></p>
						<p class="xs1" style="display: none;">***</p>
					</div>
					<div class="ass">
						<h4>累计收益(元)</h4>
						<%--<p class="xs"><%if(pb!=null){ %><%=pb.get("incomed") %><%} %><%if(pb==null) {%>0.0<%} %></p>--%>
						<p class="xs"><%=RoofNumberUtils.formatString(totalIncome)%></p>
						<p class="xs1" style="display: none;">***</p>
					</div>
					<div class="assbtn">
						<h4>可用余额(元)<i class="iconfont">&#xe632;</i><i class="iconfont" style="display: none;">&#xe629;</i></h4>
						<p class="xs"><%=userFundStat.getBalance() %></p>
						<p class="xs1" style="display: none;">***</p>
						<%if(user.getId()==88888888l){ %><div class="qtx">功能户余额：<%=gnh_balance %></div><%} %>
						<%--<%if(userFundStat.getHf_balance()>0){ %><div class="qtx">汇付余额：<%=userFundStat.getHf_balance() %><br>
						<a href="http://hftx.zdjfu.com/index/index.html" target="_blank">去提现</a></div><%} %>--%>
						<p>
							<a href="javascript:checkUser(0,'<%=user.getReal_name_auth()%>','<%=user.getStatus()%>');" class="recharge_btn">充值</a>
							<a href="javascript:checkUser(1,'<%=user.getReal_name_auth()%>','<%=user.getStatus()%>');" class="withdrawals_btn">提现</a>
							<%--<%if(user.getReal_name_auth()==1 && user.getStatus()==3){ %>
							<a href="<%=path%>/pay/charge.action" class="recharge_btn">充值</a>
							<a href="<%=path%>/pay/charge.action?type=1" class="withdrawals_btn">提现</a>
							<%}else if(user.getReal_name_auth()==0 || user.getStatus() < 3) { %>
							<a href="<%=path%>/toNewAudit.action" class="recharge_btn">充值</a>
							<a href="<%=path%>/toNewAudit.action" class="withdrawals_btn">提现</a>
							<%}else { %>
							<a href="<%=path%>/toAddBankCard.action" class="recharge_btn">充值</a>
							<a href="<%=path%>/toAddBankCard.action" class="withdrawals_btn">提现</a>
							<%} %>--%>
						</p>
					</div>
				</div>
				<div class="summary">
					<ul>
						<li>
							<p><i class="iconfont one">&#xe602;</i>待收本金<span><%=userFundStat.getPend_return() %></span>元</p>
						</li>
						<li>
							<p><i class="iconfont two">&#xe602;</i>已提现金额<span><%=userFundStat.getWithdrawed() %></span>元</p>
						</li>
						<li>
							<p><i class="iconfont three">&#xe602;</i>已收收益<span><%=userFundStat.getIncomed() %></span>元</p>
						</li>
						<li>
							<p><i class="iconfont four">&#xe602;</i>待收收益<span><%=userFundStat.getPend_income() %></span>元</p>
						</li>
						<li>
							<p><i class="iconfont five">&#xe602;</i>冻结金额<span><%=userFundStat.getLocked_money() %></span>元</p>
						</li>
						<li>
							<p><i class="iconfont six">&#xe602;</i>使用优惠<span><%=giftUsedNum!=null?giftUsedNum:0.00%></span>元</p>
						</li>
					</ul>
				</div>
				<!-- 日历 -->
				<div class="calendar">
					<div class="cahead">
						<h3>
							<span class="huikuanrili">回款日历</span>
							<span class="huikuanjihua" style="display: none;">回款计划</span>
							<a href="javascript:" class="go_databox"><i class="iconfont rili"></i></a>
							<a href="javascript:" class="go_datatxt" style="display: none;"><i class="iconfont huikuan"></i></a>
						</h3>
					</div>
					<div class="cabox">
						<input type="hidden" id="showType"/>
						<div class="box databox">
							<div class="calendarbox">
								<div class="ca_rili">
									<div id="ca_date"></div>
									<div class="ca_chiose">
										<span><i class="iconfont one">&#xe602;</i>选中</span>
										<span><i class="iconfont two">&#xe602;</i>待回款</span>
										<span><i class="iconfont three">&#xe602;</i>已回款</span>
									</div>
								</div>
								<div class="ca_tlt">
									<div class="datatlt">
										<div class="datatlt_box datatlt1"><p class="money_y"></p><p>本月应收</p></div>
										<div class="datatlt_box datatlt2"><p class="money_d"></p><p>本月待收</p></div>
									</div>	
									<%-- <div class="datatlt"><span><%=monthStr %>待收本息 <i><%=RoofNumberUtils.formatString(monthAmout) %></i> 元</span></div> --%>
									<div class="ca_yearbox">
										<span class="ca_year"><%=today %></span>
										<span class="dell dell1" style="display: none;">今日应收<i class="bs"></i>笔回款，共<i class="dai_money"></i>元</span>
										<span class="dell dell2" style="display: none;">今日已收<i class="bs"></i>笔回款，共<i class="yi_money"></i>元</span>
									</div>									
									<div class="ca_txt">
										<ul>
										<%
											if(todayIncomeList!=null && todayIncomeList.size()>0){
												for(int i=0;i<todayIncomeList.size();i++){
												ProductBuyIncome pbi = (ProductBuyIncome)todayIncomeList.get(i);
												Product p = (Product)proHashMap.get(pbi.getProduct_id());
										 %>
											<%-- <li>
												<p><i class="iconfont iconcs">&#xe602;</i><%=p.getProduct_name()%></p>
												<p><%if(pbi.getIs_return_amount()==1){ %>本金<i><%=pbi.getAmount() %></i>元+<%} %>收益<i><%=pbi.getIncome() %></i>元</p>
											</li> --%>
											<%}} %>
										</ul>
										<p class="page" id="page" style="display:none;"><i class="iconfont iconcs">&#xe60a;</i></p>
									</div>
									<div class="no_ca_txt" style="display: none;">
										<a href="<%=path%>/product/list.action">立即投资</a>
									</div>
								</div>
							</div>
						</div>
						<div class="box datatxt" style="display: none;">
							<div class="overflow">
								<table class="layui-table" lay-size="lg">
									<thead>
										<tr>
											<th>理财项目</th>
											<th>还款日期</th>
											<th>购买日期</th>
											<th>投资金额</th>
											<th>本期待收本金</th>
											<th>本期待收利息</th>
											<th>本期待收总额</th>
										</tr>
									</thead>
									<tbody>
									<%
										if(userThisMonthIncomeList!=null && userThisMonthIncomeList.size()>0){
											for(int i=0;i<userThisMonthIncomeList.size();i++){
												ProductBuyIncome pbi = (ProductBuyIncome)userThisMonthIncomeList.get(i);
												if(pbi.getStatus()==-1){
												Product p = (Product)proHashMap.get(pbi.getProduct_id());
									 %>
										<%-- <tr>
											<td class="fristTd"><span class="zt_project">直</span><span class="zq_project" style="display: none;">债</span><%if(p!=null){%><%=p.getProduct_code() %><%} %></td>
											<td><%=DateUtil.formatDate(pbi.getEnd_date()) %></td>
											<td><%=DateUtil.formatDate(pbi.getEnd_date()) %></td>
											<td><%=pbi.getAmount() %></td>
											<td><%=pbi.getAmount() %></td>
											<td><%=pbi.getAmount() %></td>
											<td class="orange"><%=pbi.getIncome()%></td>
										</tr> --%>
										<%}}} %>
									</tbody>
									<tfoot style="display: none;">
									    <tr>
									      	<td colspan="7">
									      		<div class="norecord">
													<a href="<%=path%>/product/list.action">立即投资</a>
												</div>
											</td>
									    </tr>
									</tfoot>
								</table>
							</div>
							<div id="pro_page" align='center'></div>
						</div>
					</div>
				</div>
				<!-- 走势图 -->
				<div class="trendbox">
					<div class="tr_head">
						<div class="tr_htlt">
							<h3>投资收益统计<span>累计收益:<i>¥<%=RoofNumberUtils.formatString(totalIncome)%></i></span><!-- <span>累计待收收益:<i>¥0.00</i></span> --></h3>
						</div>
						<div class="tr_htxt">
							<%-- <p>
								<a href="javascript:" class="subtract2"><i class="iconfont">&#xe611;</i></a>
								<span><i class="yearnum"><%=monthStr %></i></span>
								<a href="javascript:" class="addyear2"><i class="iconfont">&#xe611;</i></a>
							</p> --%>
						</div>
					</div>
					<div id="trend"></div>
				</div>
			</div>
			
		</div>
	</div>

	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<!-- footer end -->

	<!-- 用户未实名认证弹窗 -->
	<div class="addmove bank_open" style="display: none;" id="bankOpen">
        <div class="move">
            <h3>正道金服正式接入上海银行资金存管<span class="closeFun" onclick="closeAddmove();"></span></h3>
            <p>您需要开通上海银行存管，才可以进行投资、充值、提现等操作。</p>
            <a href="<%=path%>/toNewAudit.action">立即开通</a>
			<a href="javascript:void(0);" onclick="closeAddmove();">稍后开通</a>
        </div>
    </div>

	<!-- 用户未绑定银行卡和设置密码弹窗 -->
	<div class="addmove bank_bind_wad" style="display: none;" id="bankWadOpen">
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
    <script src="<%=path%>/module/cxscroll/jq_scroll.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/module/layui/layui.js"></script>
    <script src="<%=path%>/module/echarts/echarts.min.js"></script>
	<script type="text/javascript">
		$('.header').stickMe({
			topOffset:100
		});
		var myChart = echarts.init(document.getElementById('trend'));
		option = {
			backgroundColor: '#ffffff',	//背景色
			tooltip: {					//提示框内容
				trigger: 'axis'
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '3%',
				containLabel: true
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				axisLine:{  
					lineStyle:{  
						color:'#5ab2ff',  
						width:1,//这里是为了突出显示加上的  
					}  
				},
				splitLine: {// 控制网格线是否显示
                    show: true, 
                    //  改变轴线颜色
                    lineStyle: {
                        // 使用深浅的间隔色
                        color: ['#F5F5F5 ']
                    }                            
                },
				data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
			},
			yAxis: {
				type: 'value',
				axisLine:{  
					lineStyle:{  
						color:'#5ab2ff',  
						width:1,//这里是为了突出显示加上的  
					}  
				},
				splitLine: {// 控制网格线是否显示
                    show: true, 
                    //  改变轴线颜色
                    lineStyle: {
                        // 使用深浅的间隔色
                        color: ['#F5F5F5 ']
                    }                            
                },
				/*min: 0,
				max: <%=maxAmount%>*/
			},
			series: [
		        {
		         	name: '累计收益',
		        	type:'line',
		        	color:['#2298ff'],
		        	data:[<%=yearDate%>]
		        }
		        /*,{
		         	name: '已收收益',
		        	type:'line',
		        	color:['#2298ff'],
		        	data:[<%=yearDate%>]
		        }*/
		         
         	]
		};
		/*使用刚指定的配置项和数据显示图表。*/
		myChart.setOption(option);
	</script>
	<script>
		var path = '<%=path%>';
		layui.use(['layer','laydate','form','laypage'], function(){
			var layer = layui.layer,
			laydate= layui.laydate,
			form = layui.form,
			laypage=layui.laypage;

			var todayDate = new Date();
			var y = todayDate.getFullYear();  
		    var m = todayDate.getMonth() + 1;  
		    var d = todayDate.getDate();  
		    var today = y + '-' + m + '-' + d; 
			laydate.render({
				elem: '#ca_date',
				position: 'static',
				showBottom: false,
				type:'date',
				format:'yyyy-MM-dd',
				min: "2017-01-01", //2017-01-01开始
				max: 360, //360天后
				mark:{
				<%
				if(hkhashMap!=null){
					
				Iterator<String> iter = hkhashMap.keySet().iterator();		
				while (iter.hasNext()) {
				String key = iter.next();
				%>
					'<%= key%>': '<%=hkhashMap.get(key)%>',
				<% }}%>
				},
				change: function(value, date, endDate){ //日期时间被切换后的回调
					/*判断日历已回款和待回款*/
					var arrdaymark = $(".laydate-day-mark");
					for(var i=0; i<arrdaymark.length; i++){
						if(arrdaymark[i].innerHTML == "待回款") {
							arrdaymark[i].className = "laydate-day-mark dai";
							arrdaymark[i].innerHTML = arrdaymark[i].parentNode.getAttribute('lay-ymd').split("-").pop();
						}
						if(arrdaymark[i].innerHTML == "已回款"){
							arrdaymark[i].className = "laydate-day-mark yi";
							arrdaymark[i].innerHTML = arrdaymark[i].parentNode.getAttribute('lay-ymd').split("-").pop();
						}
					}
					var arrTd = $(".layui-laydate-content td");
					for(var i=0; i<arrTd.length; i++){
						if(arrTd[i].getAttribute("lay-ymd") == today) {
							arrTd[i].innerHTML = "今";
							arrTd[i].className = "activej";
						}
						if(arrTd[i].getAttribute("class") == 'layui-this' && arrTd[i].children.length >= 1){
							arrTd[i].children[0].setAttribute("class","laydate-day-mark")
						}
						if(arrTd[i].getAttribute("lay-ymd") == today && value == '<%=today %>'){
							arrTd[i].className = "layui-this";
						}
					}
					
					$.ajax({
						type:'post',
						url: path + '/queryUserIncomeByDate.action',
						data:{
							qryDate: value
						},
						success:function(data){
							if(data.status == 100) {
								$(".ca_year").html(value);
								$(".money_y").html(data.content.totalIncome);
								$(".money_d").html(data.content.willIncome);
								/*回款日历用户当日回款明细*/
								if(data.content.refundList.length == 0) {
									$(".dell1").hide();
									$(".dell2").hide();
									$(".ca_txt ul").html('');
									$(".ca_txt .page").hide();
									$(".calendar .calendarbox .ca_tlt ul").hide();
									$(".calendar .calendarbox .ca_tlt .no_ca_txt").show();
								} else {
									$(".calendar .calendarbox .ca_tlt .no_ca_txt").hide();
									$(".calendar .calendarbox .ca_tlt ul").show();
									$(".page").show();
									var arrLiZz = '';
									var arrLiZt = '';
									var arr = [];
									for(var i=0;i<data.content.refundList.length; i++){
										if(data.content.refundList[i].status == -1) { //待回款
											$(".dell1").show();
											$(".dell1 .bs").html(data.content.totalNum);
											$(".dell1 .dai_money").html(data.content.totalAmt);
											if(data.content.refundList[i].prdType == 1){ //债权项目
												arrLiZz += '<li>'+
															'<span class="zq_project">债</span>'+
															'<p>'+data.content.refundList[i].prdName+'<span class="money">'+data.content.refundList[i].totalAmt+'元</span></p>'+
															'<p><i>'+data.content.refundList[i].payTime+'购买</i><i>（本'+data.content.refundList[i].amt+'+息'+data.content.refundList[i].income+'）</i></p>'+
														'</li>';
												arr.push(arrLiZz);
											}
											if(data.content.refundList[i].prdType == 2) { //直投项目
												arrLiZt += '<li>'+
															'<span class="zt_project">直</span>'+
															'<p>'+data.content.refundList[i].prdName+'<span class="money">'+data.content.refundList[i].totalAmt+'元</span></p>'+
															'<p><i>'+data.content.refundList[i].payTime+'购买</i><i>（本'+data.content.refundList[i].amt+'+息'+data.content.refundList[i].income+'）</i></p>'+
														'</li>';
												arr.push(arrLiZt);
											}
										} else if(data.content.refundList[i].status == 1) { //已回款
											$(".dell2").show();
											$(".dell2 .bs").html(data.content.totalNum);
											$(".dell2 .yi_money").html(data.content.totalAmt);
											if(data.content.refundList[i].prdType == 1){ //债权项目
												arrLiZz += '<li>'+
															'<span class="zq_project">债</span>'+
															'<p>'+data.content.refundList[i].prdName+'<span class="money">'+data.content.refundList[i].totalAmt+'元</span></p>'+
															'<p><i>'+data.content.refundList[i].payTime+'购买</i><i>（本'+data.content.refundList[i].amt+'+息'+data.content.refundList[i].income+'）</i></p>'+
														'</li>';
												arr.push(arrLiZz);
											}
											if(data.content.refundList[i].prdType == 2) { //直投项目
												arrLiZt += '<li>'+
															'<span class="zt_project">直</span>'+
															'<p>'+data.content.refundList[i].prdName+'<span class="money">'+data.content.refundList[i].totalAmt+'元</span></p>'+
															'<p><i>'+data.content.refundList[i].payTime+'购买</i><i>（本'+data.content.refundList[i].amt+'+息'+data.content.refundList[i].income+'）</i></p>'+
														'</li>';
												arr.push(arrLiZt);
											}
										}
									}
									for(var i=0; i<arr.length; i++){
										$(".ca_txt ul").html(arr[i]);
									}
								}
							} else {
								alert(data.content);
							}
						},
						error: function(){
			                alert('请求错误，请重新再次请求！');
			                // 即使加载出错，也得重置
			            }
					});
				}
			});
			/*判断日历已回款和待回款*/
			var arrdaymark = $(".laydate-day-mark");
			for(var i=0; i<arrdaymark.length; i++){
				if(arrdaymark[i].innerHTML == "待回款") {
					arrdaymark[i].className = "laydate-day-mark dai";
					arrdaymark[i].innerHTML = arrdaymark[i].parentNode.getAttribute('lay-ymd').split("-").pop();
				}
				if(arrdaymark[i].innerHTML == "已回款"){
					arrdaymark[i].className = "laydate-day-mark yi";
					arrdaymark[i].innerHTML = arrdaymark[i].parentNode.getAttribute('lay-ymd').split("-").pop();
				}
			}
			var arrTd = $(".layui-laydate-content td");
			for(var i=0; i<arrTd.length; i++){
				if(arrTd[i].getAttribute("lay-ymd") == today) {
					arrTd[i].innerHTML = "今";
					arrTd[i].className = "active";
				}
			}
			
			//月份加减
			$('.tr_htxt p a').on('click',function(){
				var year = $('.yearnum').html().split("年").shift();
				var month = $('.yearnum').html().split("年").pop().split("月").shift();
				var yearnum = parseInt(year);
				var monthnum = parseInt(month);
				if($(this).hasClass('addyear2')){
					monthnum ++;
					if(monthnum < 10){
						$('.yearnum').html(yearnum+"年0"+monthnum+"月");
					} else {
						$('.yearnum').html(yearnum+"年"+monthnum+"月");
					}
					if(monthnum >= 13){
						yearnum ++;
						$('.yearnum').html(yearnum + "年01月");
					}
				}else{
					monthnum --;
					if(monthnum < 10){
						$('.yearnum').html(yearnum+"年0"+monthnum+"月");
					} else {
						$('.yearnum').html(yearnum+"年"+monthnum+"月");
					}
					if(monthnum <= 0){
						yearnum --;
						$('.yearnum').html(yearnum + "年12月");
					}
				}
			});


			var dateIndex=0;
			var typeIndex=0;
			$('.chiose_date a').on('click',function(){
				dateIndex=$(this).index()-1;
				typeIndex=$('.chiose_type .active').index()-1;
				$('.we_rt').load(path+'/capital/list.action?dateIndex='+dateIndex+'&typeIndex='+typeIndex,function(){
					$('.chiose_date a').eq(dateIndex).addClass('active').siblings('a').removeClass('active');
			    	$('.chiose_type a').eq(typeIndex).addClass('active').siblings('a').removeClass('active');
				});
				
			});
			$('.chiose_type a').on('click',function(){
				dateIndex=$('.chiose_date .active').index()-1;
				typeIndex=$(this).index()-1;
				$('.we_rt').load(path+'/capital/list.action?date='+dateIndex+'&type='+typeIndex,function(){
					$('.chiose_date a').eq(dateIndex).addClass('active').siblings('a').removeClass('active');
			    	$('.chiose_type a').eq(typeIndex).addClass('active').siblings('a').removeClass('active');
				});
			});

			/*显示余额*/
			$('.assbtn h4 i').click(function(){
				var index = $(this).index();
				$(this).hide().siblings(".iconfont").show();
		  		if(index == 0){
					$(".xs").hide();
			  		$(".xs1").show();
				} else {
					$(".xs").show();
			  		$(".xs1").hide();
				}
			});
			
			
			/*回款日历、回款计划切换*/
			var url = location.search;
			if (url.indexOf("?showType=1") != -1) {
				$("#showType").val(1);
				$(".huikuanjihua").hide();
				$(".go_datatxt").hide();
				$('.databox').show();
				$(".huikuanrili").show();
				$('.datatxt').hide();
			} else if (url.indexOf("?showType=2") != -1){
				$("#showType").val(2);
				$('.databox').hide();
				$(".huikuanrili").hide();
				$(".go_databox").hide();
				$('.datatxt').show();
				$(".huikuanjihua").show();
				$(".go_datatxt").show();
			} else {
				$("#showType").val(1);
			}
			
			$('.cahead h3 a').on('click',function(){
				if($(this).hasClass('go_databox')){
					var inputVal2 = $("#showType").val(2);
					var newInputVal2 = $("#showType").val();
					window.location = path + '/mywealth.action?showType='+newInputVal2;
				}else{
					var inputVal1 = $("#showType").val(1);
					var newInputVal1 = $("#showType").val();
					window.location = path + '/mywealth.action?showType='+newInputVal1;
				}
			});
			
			/*回款日历*/
			$.ajax({
				type:'post',
				url: path + '/queryUserIncomeByDate.action',
				data:{
					qryDate: '<%=today %>'
				},
				success:function(data){
					if(data.status == 100) {
						$(".money_y").html(data.content.totalIncome);
						$(".money_d").html(data.content.willIncome);
						/*回款日历用户当日回款明细*/
						if(data.content.refundList.length == 0) {
							$(".calendar .calendarbox .ca_tlt ul").hide();
							$(".calendar .calendarbox .ca_tlt .no_ca_txt").show();
						} else {
							$(".calendar .calendarbox .ca_tlt ul").show();
							$(".page").show();
							var arrLiZz = '';
							var arrLiZt = '';
							var arr = [];
							for(var i=0;i<data.content.refundList.length; i++){
								if(data.content.refundList[i].status == -1) { //待回款
									$(".dell1").show();
									$(".dell1 .bs").html(data.content.totalNum);
									$(".dell1 .dai_money").html(data.content.totalAmt);
									if(data.content.refundList[i].prdType == 1){ //债权项目
										arrLiZz += '<li>'+
													'<span class="zq_project">债</span>'+
													'<p>'+data.content.refundList[i].prdName+'<span class="money">'+data.content.refundList[i].totalAmt+'元</span></p>'+
													'<p><i>'+data.content.refundList[i].payTime+'购买</i><i>（本'+data.content.refundList[i].amt+'+息'+data.content.refundList[i].income+'）</i></p>'+
												'</li>';
										arr.push(arrLiZz);
									}
									if(data.content.refundList[i].prdType == 2) { //直投项目
										arrLiZt += '<li>'+
													'<span class="zt_project">直</span>'+
													'<p>'+data.content.refundList[i].prdName+'<span class="money">'+data.content.refundList[i].totalAmt+'元</span></p>'+
													'<p><i>'+data.content.refundList[i].payTime+'购买</i><i>（本'+data.content.refundList[i].amt+'+息'+data.content.refundList[i].income+'）</i></p>'+
												'</li>';
										arr.push(arrLiZt);
									}
								} else if(data.content.refundList[i].status == 1) { //已回款
									$(".dell2").show();
									$(".dell2 .bs").html(data.content.totalNum);
									$(".dell2 .yi_money").html(data.content.totalAmt);
									if(data.content.refundList[i].prdType == 1){ //债权项目
										arrLiZz += '<li>'+
													'<span class="zq_project">债</span>'+
													'<p>'+data.content.refundList[i].prdName+'<span class="money">'+data.content.refundList[i].totalAmt+'元</span></p>'+
													'<p><i>'+data.content.refundList[i].payTime+'购买</i><i>（本'+data.content.refundList[i].amt+'+息'+data.content.refundList[i].income+'）</i></p>'+
												'</li>';
										arr.push(arrLiZz);
									}
									if(data.content.refundList[i].prdType == 2) { //直投项目
										arrLiZt += '<li>'+
													'<span class="zt_project">直</span>'+
													'<p>'+data.content.refundList[i].prdName+'<span class="money">'+data.content.refundList[i].totalAmt+'元</span></p>'+
													'<p><i>'+data.content.refundList[i].payTime+'购买</i><i>（本'+data.content.refundList[i].amt+'+息'+data.content.refundList[i].income+'）</i></p>'+
												'</li>';
										arr.push(arrLiZt);
									}
								}
							}
							for(var i=0; i<arr.length; i++){
								$(".ca_txt ul").html(arr[i]);
							}
						}
						/* 回款日历明细翻页 */
						$(".ca_txt").Scroll({line:3,speed:200,up:"page"});
					} else {
						alert(data.content);
					}
				},
				error: function(){
	                alert('请求错误，请重新再次请求！');
	                // 即使加载出错，也得重置
	            }
			});
			
			
			/*回款计划*/
			$.ajax({
				type:'post',
				url: path + '/queryUserWillRefundList.action',
				data:{
					currentPage: 1, //当前第几页
					limit: 5 //每页记录条数
				},
				success:function(data) {
					if(data.status == 100) {
						/*回款日历用户当日回款明细*/
						if(data.content.dataList.length == 0) {
							$(".datatxt table tfoot").show();
							$("#pro_page").hide();
						} else {
							laypage.render({
								elem: 'pro_page',
								limit: 5, //每页显示的条数。
								groups: 5, //连续出现的页码个数
								first: '首页',
								last: '尾页',
								theme: '#1187f1',
								count: data.content.total,		//数据总数，从服务端得到
								jump:function(obj,first){
				                    var showType=$("#showType").val();
				                    var path = '<%=path%>';
									if(!first){
										pageFn(obj.curr);
									}
								}
							});
							$(".datatxt table tfoot").hide();
							$("#pro_page").show();
							var arrLiZz = '';
							var arrLiZt = '';
							var arr = [];
							for(var i=0;i<data.content.dataList.length; i++){
								if(data.content.dataList[i].status == -1) { //待回款
									if(data.content.dataList[i].prdType == 1){ //债权项目
										arrLiZz += '<tr>'+
														'<td class="fristTd"><span class="zq_project">债</span>'+data.content.dataList[i].prdName+'</td>'+
														'<td>'+data.content.dataList[i].refundDate+'</td>'+
														'<td>'+data.content.dataList[i].payTime+'</td>'+
														'<td>'+data.content.dataList[i].intAmt+'</td>'+
														'<td>'+data.content.dataList[i].amt+'</td>'+
														'<td>'+data.content.dataList[i].income+'</td>'+
														'<td class="orange">'+data.content.dataList[i].totalAmt+'</td>'+
													'</tr>';
										arr.push(arrLiZz);
									}
									if(data.content.dataList[i].prdType == 2) { //直投项目
										arrLiZt += '<tr>'+
														'<td class="fristTd"><span class="zt_project">直</span>'+data.content.dataList[i].prdName+'</td>'+
														'<td>'+data.content.dataList[i].refundDate+'</td>'+
														'<td>'+data.content.dataList[i].payTime+'</td>'+
														'<td>'+data.content.dataList[i].intAmt+'</td>'+
														'<td>'+data.content.dataList[i].amt+'</td>'+
														'<td>'+data.content.dataList[i].income+'</td>'+
														'<td class="orange">'+data.content.dataList[i].totalAmt+'</td>'+
													'</tr>'+
										arr.push(arrLiZt);
									}
								}
							}
							for(var i=0; i<arr.length; i++){
								$(".datatxt .layui-table tbody").html(arr[i]);
							}
						}
					} else {
						alert(data.content);
					}
				},
				error: function(){
	                alert('请求错误，请重新再次请求！');
	                // 即使加载出错，也得重置
	            }
			});
			function pageFn(currentPage){
				var curr;
				$.ajax({
					type:'post',
					url: path + '/queryUserWillRefundList.action',
					data:{
						currentPage: currentPage, //当前第几页
						limit: 5 //每页记录条数
					},
					success:function(data) {
						if(data.status == 100) {
							var arrLiZz = '';
							var arrLiZt = '';
							var arr = [];
							for(var i=0;i<data.content.dataList.length; i++){
								if(data.content.dataList[i].status == -1) { //待回款
									if(data.content.dataList[i].prdType == 1){ //债权项目
										arrLiZz += '<tr>'+
														'<td class="fristTd"><span class="zq_project">债</span>'+data.content.dataList[i].prdName+'</td>'+
														'<td>'+data.content.dataList[i].refundDate+'</td>'+
														'<td>'+data.content.dataList[i].payTime+'</td>'+
														'<td>'+data.content.dataList[i].intAmt+'</td>'+
														'<td>'+data.content.dataList[i].amt+'</td>'+
														'<td>'+data.content.dataList[i].income+'</td>'+
														'<td class="orange">'+data.content.dataList[i].totalAmt+'</td>'+
													'</tr>';
										arr.push(arrLiZz);
									}
									if(data.content.dataList[i].prdType == 2) { //直投项目
										arrLiZt += '<tr>'+
														'<td class="fristTd"><span class="zt_project">直</span>'+data.content.dataList[i].prdName+'</td>'+
														'<td>'+data.content.dataList[i].refundDate+'</td>'+
														'<td>'+data.content.dataList[i].payTime+'</td>'+
														'<td>'+data.content.dataList[i].intAmt+'</td>'+
														'<td>'+data.content.dataList[i].amt+'</td>'+
														'<td>'+data.content.dataList[i].income+'</td>'+
														'<td class="orange">'+data.content.dataList[i].totalAmt+'</td>'+
													'</tr>'+
										arr.push(arrLiZt);
									}
								}
							}
							for(var i=0; i<arr.length; i++){
								$(".datatxt .layui-table tbody").html(arr[i]);
							}
						}
					},
					error: function(){
		                alert('请求错误，请重新再次请求！');
		                // 即使加载出错，也得重置
		            }
				});
			}
			
		});
		//弹窗关闭
		function closeAddmove() {
			$(".addmove").hide();
		}
		function checkUser(type,realNameAuth,userStatus) {
            if (realNameAuth != 1 || userStatus < 2) {
                $("#bankOpen").show();
                //绑卡状态：1绑卡短信未确定 2绑卡短信已确定未设定支付密码 3 支付密码已设置成功 4 有支付密码已经解绑快捷支付
            } else if (userStatus == 4) {
                $("#bankBind").show();
                //未设置支付密码
            } else if (userStatus == 2) {
                $("#payPasWrdSet").show();
            } else {
                if (type == 0) { //去充值
                    window.location.href="<%=path%>/pay/charge.action";
                }
                if (type == 1) { //提现
                    window.location.href="<%=path%>/pay/charge.action?type=1";
                }
            }
        }
	</script>
</body>
</html>