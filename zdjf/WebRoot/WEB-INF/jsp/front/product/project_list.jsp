<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zdjf.domain.product.Product" %>
<%@ page import="com.zdjf.util.RoofNumberUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List productList = (List)request.getAttribute("productList");
String showPage = (String)request.getAttribute("showPage");
String status = (String)request.getAttribute("status");
String dateType = (String)request.getAttribute("dateType");
String type = (String)request.getAttribute("type");
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
	<link rel="stylesheet" href="<%=path%>/css/front/project.css">
</head>
<body>
	<!-- header -->
	<div class="header">
		<jsp:include page="../common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="project">
		<!-- 我的理财 -->
		<div class="pro_chiose">
			<form id="form1" method="get" action="<%=path%>/product/list.action">
			<input type="hidden" id="status" name="status" value="<%=status!=null?status:""%>">
			<input type="hidden" id="dateType" name="dateType" value="<%=dateType!=null?dateType:""%>">
			<input type="hidden" id="lltype" name="lltype" value="<%=type!=null?type:""%>">
			<div class="tab">
				<span class="active">全部</span>
				<span>债转项目</span>
			</div>
			<div class="chiose">
				<p>
					<span>项目状态:</span>
					<a href="javascript:search('status','');"<%if(status==null || status.equals("")){ %> class="active"<%} %>>全部状态</a>
					<a href="javascript:search('status','4');"<%if(status!=null && status.equals("4")){ %> class="active"<%} %>>投资中</a>
					<a href="javascript:search('status','5');"<%if(status!=null && status.equals("5")){ %> class="active"<%} %>>履约中</a>
					<a href="javascript:search('status','6');"<%if(status!=null && status.equals("6")){ %> class="active"<%} %>>已还款</a>
				</p>
				<p>
					<span>项目期限:</span>
					<a href="javascript:search('dateType','');"<%if(dateType==null || dateType.equals("")){ %> class="active"<%} %>>默认全部</a>
					<a href="javascript:search('dateType','1');"<%if(dateType!=null && dateType.equals("1")){ %> class="active"<%} %>>1个月</a>
					<a href="javascript:search('dateType','2');"<%if(dateType!=null && dateType.equals("2")){ %> class="active"<%} %>>2个月</a>
					<a href="javascript:search('dateType','3');"<%if(dateType!=null && dateType.equals("3")){ %> class="active"<%} %>>3个月</a>
					<a href="javascript:search('dateType','4');"<%if(dateType!=null && dateType.equals("4")){ %> class="active"<%} %>>6个月以上</a>
				</p>
				<p>
					<span>项目利率:</span>
					<a href="javascript:search('lltype','');"<%if(type==null || type.equals("")){ %> class="active"<%} %>>默认全部</a>
					<a href="javascript:search('lltype','1');"<%if(type!=null && type.equals("1")){ %> class="active"<%} %>>9-10%</a>
					<a href="javascript:search('lltype','2');"<%if(type!=null && type.equals("2")){ %> class="active"<%} %>>10-11%</a>
					<a href="javascript:search('lltype','3');"<%if(type!=null && type.equals("3")){ %> class="active"<%} %>>11-12%</a>
					<a href="javascript:search('lltype','4');"<%if(type!=null && type.equals("4")){ %> class="active"<%} %>>12%以上</a>
				</p>
			</div>
			</form>
		</div>
		<div class="prolist">
			<ul>
			<%
				if(productList!=null && productList.size()>0){
					for(int i=0;i<productList.size();i++){
					Product p = (Product)productList.get(i);
					long day = 0;
					if(p.getStatus()==4){
						day = (p.getEnd_date().getTime()-new Date().getTime())/(1000*3600*24)+1;
					}else{
						day = (p.getEnd_date().getTime()-p.getStart_date().getTime())/(1000*3600*24);
					}
			 %>
				<li>
					<div class="logo">
						<a href="<%=path %>/product/detail/<%=p.getId() %>.action">
							<img src="<%=p.getPhoto() %>" alt="">
						</a>
					</div>
					<div class="proteng">
						<a href="<%=path %>/product/detail/<%=p.getId() %>.action">
							<div class="tlt">
								<span class="pro_name"><%=p.getProduct_code() %></span>
								<span class="pro_tlt"><%=p.getProduct_name() %></span>
								<%if(p.getIs_fresh()==1){%><span class="pro_label">新手标</span><%} %>
								<span class="pro_timeout"></span>
							</div>
						</a>
						<div class="txt">
							<div class="txt_box1">
								<p><%=new java.text.DecimalFormat("#.00").format(p.getIncome()-p.getPlatform_interest()) %><%if(p.getPlatform_interest()>0.0){ %>+<%=p.getPlatform_interest() %><%} %><i>%</i></p>
								<p>预期年化收益</p>
							</div>
							<div class="txt_box2">
								<p><%=day %>天</p>
								<p>收益天数</p>
							</div>
							<div class="txt_box3">
								<p><%=p.getPay_min() %>元</p>
								<p>起投金额</p>
							</div>
							<div class="txt_box4">
								<p><%=p.getBalance() %></p>
								<p>剩余可投</p>
							</div>
							<div class="txt_box5">
								<div class="layui-progress probar">
									<div class="layui-progress-bar layui-bg-zdjforange" lay-percent="<%=RoofNumberUtils.formatString(p.getSale_money()/p.getMoney()*100.0)%>%"></div>
								</div>
								<p>进度 : <%=RoofNumberUtils.formatString(p.getSale_money()/p.getMoney()*100.0)%>%</p>
							</div>
						</div>
					</div>
					<div class="probtn">
						<%if(p.getStatus()==4){ %><a href="<%=path %>/product/detail/<%=p.getId() %>.action" class="investment">立即投资</a>
						<%}else if(p.getStatus()==5){ %><a href="<%=path %>/product/detail/<%=p.getId() %>.action" class="performance">履约中</a>
						<%}else if(p.getStatus()==6){ %><a href="<%=path %>/product/detail/<%=p.getId() %>.action" class="performance">已还款</a><%} %>
					</div>
				</li>
				<%
					}}
				 %>

			</ul>
			<%--<%=showPage %>--%>
			<div id="project_list_pag" align="center"></div>
		</div>
	</div>
	<!-- content end-->


	<!-- footer -->
	<jsp:include page="/WEB-INF/jsp/front/common/footer.jsp"></jsp:include>
	<!-- footer end -->
    <script src="<%=path%>/module/jquery/jquery.min.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/module/layui/layui.js"></script>
	<script src="<%=path%>/js/front/project.js"></script>
	<script type="text/javascript">
    	$('.header').stickMe({
			topOffset:100
		});
		function search(sid,va){
			$("#"+sid).val(va);
			$("#form1").submit();
		}

        layui.use(['layer','laypage','element','carousel'], function(){
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'project_list_pag', //注意，这里的 project_list_pag 是 ID，不用加 # 号
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
                        window.location = '<%=path%>/product/list.action?page='+obj.curr+'&status='+$("#status").val()+'&dateType='+$("#dateType").val()+'&lltype='+$("#lltype").val();
                    }
                }
            });
        });
    </script>
</body>
</html>