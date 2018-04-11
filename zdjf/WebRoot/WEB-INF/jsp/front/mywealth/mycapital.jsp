<%@page import="com.zdjf.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zdjf.domain.fund.Funds" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List list = (List)request.getAttribute("myFundsList");
String showPage = (String)request.getAttribute("showPage");
String beginDate = (String)request.getAttribute("beginDate");
String endDate = (String)request.getAttribute("endDate");
String type = (String)request.getAttribute("type");
String dateType = (String)request.getAttribute("dateType");
Long totalRecord = (Long) request.getAttribute("totalRecord");
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
			<!-- 资金明细 -->
			<form id="myFund" action="<%=path%>/myFunds.action" method="get">
			<div class="capitalbox we_rtbox">
				<div class="capital">
					<div class="tlt">
						<h3>资金明细</h3>
					</div>
					<div class="time">
						选择日期:
						<input type="text" placeholder="开始时间" class="layui-input" id="beginDate" name="beginDate" value="<%=beginDate!=null?beginDate:"" %>" placeholder="">
						到
						<input type="text" placeholder="结束时间" class="layui-input" id="endDate" name="endDate"  value="<%=endDate!=null?endDate:"" %>" placeholder="">
					</div>
				</div>
				<input type="hidden" id="type" name="type" value="<%=type!=null?type:"" %>" />
				<input type="hidden" id="dateType" name="dateType" value="<%=dateType!=null?dateType:"" %>" />
				<div class="ca_chiose">
					<ul>
						<li>
							<span>交易日期:</span>
							<a href="javascript:dateTypeDosubmit('')"<%if(dateType==null || "".equals(dateType)){ %> class="active"<%} %>>全部</a>
							<a href="javascript:dateTypeDosubmit('1')"<%if(dateType!=null && "1".equals(dateType)){ %> class="active"<%} %>>最近7天</a>
							<a href="javascript:dateTypeDosubmit('2')"<%if(dateType!=null && "2".equals(dateType)){ %> class="active"<%} %>>1个月</a>
							<a href="javascript:dateTypeDosubmit('3')"<%if(dateType!=null && "3".equals(dateType)){ %> class="active"<%} %>>3个月</a>
							<a href="javascript:dateTypeDosubmit('4')"<%if(dateType!=null && "4".equals(dateType)){ %> class="active"<%} %>>半年以上</a>
						</li>
						<li>
							<span>交易类型:</span>
							<a href="javascript:typeDosubmit('');"<%if(type==null || "".equals(type)){ %> class="active"<%} %>>全部</a>
							<a href="javascript:typeDosubmit('11');"<%if(type!=null && "11".equals(type)){ %> class="active"<%} %>>充值</a>
							<a href="javascript:typeDosubmit('21');"<%if(type!=null && "21".equals(type)){ %> class="active"<%} %>>提现</a>
							<a href="javascript:typeDosubmit('22');"<%if(type!=null && "22".equals(type)){ %> class="active"<%} %>>投资</a>
							<a href="javascript:typeDosubmit('12');"<%if(type!=null && "12".equals(type)){ %> class="active"<%} %>>回款</a>
							<a href="javascript:typeDosubmit('13');"<%if(type!=null && "13".equals(type)){ %> class="active"<%} %>>利息</a>
						</li>
					</ul>
				</div>
				<div class="ca_table">
					<table>
						<thead>
							<tr>
								<th>日期</th>
								<th>流水号</th>
								<th>类型</th>
								<th>金额(元)</th>
								<th>可用余额(元)</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>
						<%
							if(list!=null && list.size()>0){
								for(int i=0;i<list.size();i++){
								Funds fund = (Funds)list.get(i);
						 %>
							<tr>
								<td><%=DateUtil.formatDateTimeS(fund.getCreate_time())%></td>
								<td><%=fund.getTrade_no() %></td>
								<td>
								<%
									String str = "";
									if(fund.getOperate_type()==11){
										str = "充值";
									}else if(fund.getOperate_type()==12){
										str = "理财本金回款";
									}else if(fund.getOperate_type()==13){
										str = "理财收益";
									}else if(fund.getOperate_type()==18){
										str = "提现撤销";
									}else if(fund.getOperate_type()==19){
										str = "提现未通过";
									}else if(fund.getOperate_type()==21){
										str = "提现";
									}else if(fund.getOperate_type()==22){
										str = "理财产品支付";
									}
									String action = "+";
									if(fund.getAction()==2){
										action = "-";
									}
									String statusStr = "";
									if(fund.getStatus()==1){
										statusStr = "成功";
									}else if(fund.getStatus()==2){
										statusStr = "进行中";
									}else if(fund.getStatus()==3){
										statusStr = "待审核";
									}else if(fund.getStatus()==4){
										statusStr = "银行处理中";
									}else if(fund.getStatus()==-3){
										statusStr = "审核不通过";
									}else if(fund.getStatus()==-5){
										statusStr = "已撤销";
									}
								 %>
								 <%=str %>
								</td>
								<td><%=action+fund.getAmount() %></td>
								<td><%=fund.getBalance() %></td>
								<td>
									<span class="<%if(fund.getStatus()==1) out.print("ok"); else out.print("no"); %>"><%=statusStr %></span>
								</td>
							</tr>
							<%}} %>
							
						</tbody>
						<tfoot style="display: none;">
						    <tr>
								<td colspan="6">
									<div class="norecord">
										<a href="<%=path%>/product/list.action">立即投资</a>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
					<%--<%=showPage %>--%>
					<div id="mycapital_pag" align="center"></div>
				</div>
			</div>
			</form>
		</div>
	</div>
	
	<!-- footer -->
	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<!-- footer end -->
    <script src="<%=path%>/module/jquery/jquery.min.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/module/layui/layui.js"></script>
	<script src="<%=path%>/js/front/wealth.js"></script>
	<script>
		function typeDosubmit(str){
			$("#type").val(str);
			$("#myFund").submit();
		}
		function dateTypeDosubmit(str){
			$("#beginDate").val('');
			$("#endDate").val('');
			$("#dateType").val(str);
			$("#myFund").submit();
		}
		/*资金明细有无记录显示状态*/
		if($(".ca_table table tbody tr").length == 0){
			$(".ca_table table tfoot").show();
			$(".cap_page").hide();
		} else {
			$(".ca_table table tfoot").hide();
			$(".cap_page").show();
		}

        layui.use(['layer','laypage','element','carousel'], function(){
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'mycapital_pag', //注意，这里的 project_list_pag 是 ID，不用加 # 号
                limit: 10, //每页显示的条数
                groups:5, //连续出现的页码个数
                first:'首页',
                last:'尾页',
                count: <%=totalRecord%> ,//数据总数，从服务端得到
                curr: function(){ //起始页
                    var page = location.search.match(/page=(\d+)/);
                    return page ? page[1] : 1;
                }(),
                jump:function(obj,first){ //切换分页的回调
                    if(!first){
                        window.location = '<%=path%>/myFunds.action?page='+obj.curr+'&beginDate='+$("#beginDate").val()+'&endDate='+$("#endDate").val()+'&dateType='+$("#dateType").val()+'&type='+$("#type").val();
                    }
                }
            });
        });
	</script>
</body>
</html>
