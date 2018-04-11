<%@page import="com.zdjf.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zdjf.domain.product.ProductBuy" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String showPage = (String)request.getAttribute("showPage");
List myProductBuyList = (List)request.getAttribute("myProductBuyList");
String beginDate = (String)request.getAttribute("beginDate");
String endDate = (String)request.getAttribute("endDate");
String dateType = (String)request.getAttribute("dateType");
String status = (String)request.getAttribute("status");
String showTypeStr = (String)request.getAttribute("showTypeStr");
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
			<!-- 我的理财 -->
			<form action="<%=path%>/myProductBuy.action" id="form1" method="get">
			<input type="hidden" id="showTypeStr" name="showTypeStr" value="<%=showTypeStr!=null?showTypeStr:""%>">
			<input type="hidden" id="beginDate" name="beginDate" value="<%=beginDate!=null?beginDate:""%>">
			<input type="hidden" id="endDate" name="endDate" value="<%=endDate!=null?endDate:""%>">
			<input type="hidden" id="dateType" name="dateType" value="<%=dateType!=null?dateType:""%>">
			<input type="hidden" id="status" name="status" value="<%=status!=null?status:""%>">
			<div class="duct we_rtbox">
				<div class="du_tav">
					<p>
						<span <%if(showTypeStr==null || "".equals(showTypeStr)){ %> class="active"<%} %> onclick="setStrvla('showTypeStr','');">投资记录</span>
						<span <%if(showTypeStr!=null && "1".equals(showTypeStr)){ %> class="active"<%} %> onclick="setStrvla('showTypeStr','1');">订单记录</span>
					</p>
					<a href="<%=path%>/product/list.action">立即投资></a>
				</div>
				<div class="du_tab">
					<div class="ca_table tzjl"<%if(showTypeStr!=null && "1".equals(showTypeStr)){ %>  style="display: none;"<%} %>>
						<div class="du_datebox">
							投资时间:<input type="text" placeholder="开始时间" class="layui-input" value="<%=beginDate!=null?beginDate:""%>" id="dufrom" placeholder="">到
							<input type="text" placeholder="结束时间" class="layui-input" value="<%=endDate!=null?endDate:""%>" id="duto" placeholder="">
							<a href="javascript:;" class="xcBtn hide">查询</a>
							<span <%if(dateType==null || "".equals(dateType)){ %> class="active"<%} %> onclick="setStrvla('dateType','');">全部</span>
							<span <%if(dateType!=null && "1".equals(dateType)){ %> class="active"<%} %> onclick="setStrvla('dateType','1');">最近7天</span>
							<span<%if(dateType!=null && "2".equals(dateType)){ %> class="active"<%} %> onclick="setStrvla('dateType','2');">1个月</span>
							<span<%if(dateType!=null && "3".equals(dateType)){ %> class="active"<%} %> onclick="setStrvla('dateType','3');">3个月</span>
							<p class="search">
								<span>项目状态:</span>
								<a href="javascript:"<%if(status==null || "".equals(status)){ %> class="active"<%} %> onclick="setStrvla('status','');">全部状态</a>
								<a href="javascript:"<%if(status!=null && "-1".equals(status)){ %> class="active"<%} %> onclick="setStrvla('status','-1');">投资中</a>
								<a href="javascript:"<%if(status!=null && "1".equals(status)){ %> class="active"<%} %> onclick="setStrvla('status','1');">履约中</a>
								<a href="javascript:"<%if(status!=null && "2".equals(status)){ %> class="active"<%} %> onclick="setStrvla('status','2');">已回款</a>
								<a href="javascript:" class="hide">流标</a>
							</p>
						</div>
						<table>
							<thead>
								<tr>
									<th>项目名称</th>
									<th>预期年化收益</th>
									<th>收益天数(天)</th>
									<th>投资金额(元)</th>
									<th>收益(元)</th>
									<th>项目状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<%
								if(myProductBuyList!=null && myProductBuyList.size()>0){
									for(int i=0;i<myProductBuyList.size();i++){
										ProductBuy pb = (ProductBuy)myProductBuyList.get(i);
							 %>
								<tr>
									<td><%=pb.getProduct_name() %></td>
									<td><%=pb.getProduct_interest() %>%</td>
									<td><%=pb.getWill_income_days() %>天</td>
									<td><%=pb.getAmount() %></td>
									<td><%=pb.getWill_income()!=null?pb.getWill_income():"" %></td>
									<td>
										<span class="state1">
										<%if(pb.getStatus()==1){ %>
											履约中
										<%}else if(pb.getStatus()==-1){ %>
											投资中
										<%}else if(pb.getStatus()<=-2){ %>
											<span class="payno">失败</span>
										<%}else if(pb.getStatus()==2){ %>
											已回款
										<%} %>
										</span>
									</td>
									<td><a href="<%=path%>/product/detail/<%=pb.getProduct_id() %>.action" class="see" target="_blank">查看</a></td>
								</tr>
								<%}} %>
								
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
						<div id="myconduct_pag1" align="center"></div>
					</div>
					<div class="ca_table ddjl"  <%if(showTypeStr==null || "".equals(showTypeStr)){ %>  style="display: none;"<%} %>>
						<div class="du_datebox">
							下单时间:<input type="text" placeholder="开始时间" class="layui-input" value="<%=beginDate!=null?beginDate:""%>" id="dufrom2" placeholder="">到
							<input type="text" placeholder="结束时间" class="layui-input" value="<%=endDate!=null?endDate:""%>" id="duto2" placeholder="">
							<span <%if(dateType==null || "".equals(dateType)){ %> class="active"<%} %> onclick="setStrvla('dateType','');">全部</span>
							<span <%if(dateType!=null && "1".equals(dateType)){ %> class="active"<%} %> onclick="setStrvla('dateType','1');">最近7天</span>
							<span<%if(dateType!=null && "2".equals(dateType)){ %> class="active"<%} %> onclick="setStrvla('dateType','2');">1个月</span>
							<span<%if(dateType!=null && "3".equals(dateType)){ %> class="active"<%} %> onclick="setStrvla('dateType','3');">3个月</span>
							<p class="search">
								<span>项目状态:</span>
								<a href="javascript:"<%if(status==null || "".equals(status)){ %> class="active"<%} %> onclick="setStrvla('status','');">全部状态</a>
								<a href="javascript:"<%if(status!=null && "-1".equals(status)){ %> class="active"<%} %> onclick="setStrvla('status','-1');">投资中</a>
								<a href="javascript:"<%if(status!=null && "1".equals(status)){ %> class="active"<%} %> onclick="setStrvla('status','1');">履约中</a>
								<a href="javascript:"<%if(status!=null && "2".equals(status)){ %> class="active"<%} %> onclick="setStrvla('status','2');">已回款</a>
								<!-- <a href="javascript:">支付成功</a>
								<a href="javascript:">支付失败</a> -->
							</p>
						</div>
						<table>
							<thead>
								<tr>
									<th>下单时间</th>
									<th>订单号</th>
									<th>项目名称</th>
									<th>订单金额(元)</th>
									<th>支付状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							
							<%
								if(myProductBuyList!=null && myProductBuyList.size()>0){
									for(int i=0;i<myProductBuyList.size();i++){
										ProductBuy pb = (ProductBuy)myProductBuyList.get(i);
							 %>
								<tr>
									<td><%=DateUtil.formatDateTimeS(pb.getCreate_time())%></td>
									<td><%=pb.getTrade_no()!=null?pb.getTrade_no():"" %></td>
									<td><%=pb.getProduct_name() %></td>
									<td><%=pb.getAmount() %></td>
									<td>
										<%if(pb.getStatus()==1){ %>
											成功
										<%}else if(pb.getStatus()==-1){ %>
											未支付
										<%}else if(pb.getStatus()<=-2){ %>
											<span class="payno">失败</span>
										<%}else if(pb.getStatus()==2){ %>
											已回款
										<%} %>
									</td>
									<td>
										<a href="<%=path%>/product/detail/<%=pb.getProduct_id() %>.action" class="keepsee">查看</a>
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
						<div id="myconduct_pag2" align="center"></div>
					</div>
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
	function setStrvla(sid,va){
		$("#"+sid).val(va);
		if(sid!="showTypeStr")
		$("#form1").submit();
		if(sid == "showTypeStr") {
            window.location = '<%=path%>/myProductBuy.action?page=1&beginDate='+$("#beginDate").val()+'&endDate='+$("#endDate").val()+'&dateType='+$("#dateType").val()+'&type='+$("#type").val()+'&status='+$("#status").val()+'&showTypeStr='+$("#showTypeStr").val();
		}
	}
	/*我的理财投资有无记录显示状态*/
	if($(".tzjl table tbody tr").length == 0){
		$(".tzjl table tfoot").show();
		$(".tzjl .cap_page").show();
		$(".tzjl #myconduct_pag1").hide();
	} else {
		$(".tzjl table tfoot").hide();
		$(".tzjl .cap_page").show();
		$(".tzjl #myconduct_pag1").show();
	}
	/*我的理财订单有无记录显示状态*/
	if($(".ddjl table tbody tr").length == 0){
		$(".ddjl table tfoot").show();
		$(".tzjl .cap_page").show();
		$(".ddjl #myconduct_pag2").hide();
	} else {
		$(".ddjl table tfoot").hide();
		$(".tzjl .cap_page").show();
		$(".ddjl #myconduct_pag2").show();
	}
    layui.use(['layer','laypage','element','carousel'], function(){
        var laypage = layui.laypage;

        //执行一个投资记录分页实例
        laypage.render({
            elem: 'myconduct_pag1', //注意，这里的myconduct_pag1 是 ID，不用加 # 号
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
                    window.location = '<%=path%>/myProductBuy.action?page='+obj.curr+'&beginDate='+$("#beginDate").val()+'&endDate='+$("#endDate").val()+'&dateType='+$("#dateType").val()+'&type='+$("#type").val()+'&status='+$("#status").val()+'&showTypeStr='+$("#showTypeStr").val();
                }
            }
        });
        
        //执行一个订单记录分页实例
        laypage.render({
            elem: 'myconduct_pag2', //注意，这里的 myconduct_pag2 是 ID，不用加 # 号
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
                    window.location = '<%=path%>/myProductBuy.action?page='+obj.curr+'&beginDate='+$("#beginDate").val()+'&endDate='+$("#endDate").val()+'&dateType='+$("#dateType").val()+'&type='+$("#type").val()+'&status='+$("#status").val()+'&showTypeStr='+$("#showTypeStr").val();
                }
            }
        });
    });

	</script>
</body>
</html>
