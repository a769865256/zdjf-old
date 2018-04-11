<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
  	<base href="${selfSite}/zdjf">
	<meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<title>产品列表-专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有  正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/> 
	
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/kkpager_blue.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/product/css/list.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>

  </head>
  
  <body>
  
  <jsp:include page="../comm/header.jsp"></jsp:include>
  
   <!-- list -->
	<div class="list">
	    <!-- bar -->
	    <div class="bar clearfix">
	        <span>排序：</span>
	        <ul class="J_listBar">
	            <li>默认排序</li>
	            <li>收益天数
	            	<c:if test="${type==2 }">
	            		<i class="icon-up"></i>
	            	</c:if>
	            	<c:if test="${type==2*3 }">
	            		<i class="icon-down"></i>
	            	</c:if>
	            </li>
	            <li>年化收益
	            	<c:if test="${type==3 }">
	            		<i class="icon-up"></i>
	            	</c:if>
	            	<c:if test="${type==3*3 }">
	            		<i class="icon-down"></i>
	            	</c:if>
	            </li>
	            <li>可投额度
					<c:if test="${type==4 }">
	            		<i class="icon-up"></i>
	            	</c:if>
	            	<c:if test="${type==4*3 }">
	            		<i class="icon-down"></i>
	            	</c:if>
				</li>
	        </ul>
	    </div>
	
	
		<c:forEach var="item" items="${rsp.datas }">
		    <!-- item -->
		    <div class="item">
		        <div class="title"><a href="${selfSite}/zdjf/product/detail/${item.productId}"><span class="num">${item.productCode }</span><span class="name">${item.productName }</span>
		        <c:if test="${item.isFresh==1 }">
		        	<i class="icon-new">新手专享</i>
		        </c:if>
				<c:if test="${item.status==31 }">
					<i class="herald">预告</i>
				</c:if>
		        </a></div>
		        <div class="info">
		            <ul>
		                <li class="photo">
		                    <a href="${selfSite}/zdjf/product/detail/${item.productId}"><img src="${item.photo }" alt=""></a>
		                </li>
		                <li class="profit">
		                    <p class="font text-red"><span><fmt:formatNumber value="${item.income }" pattern="0.00" type="number" /></span>%</p>
		                    <p>年化收益</p>
		                </li>
		                <li class="days">
		                    <p class="font"><span>${item.incomeDaysTo }</span>天</p>
		                    <p>收益天数</p>
		                </li>
		                <li class="balance">
							<c:if test="${item.status==4 }">
								<p class="font"><span><fmt:formatNumber value="${item.canBuyMoney }" pattern="#,###" type="number" /></span>元</p>
								<p>剩余可投</p>
							</c:if>
							<c:if test="${item.status==5 || item.status==6 || item.status==31 }">
								<p class="font"><span><fmt:formatNumber value="${item.money }" pattern="#,###" type="number" /></span>元</p>
								<p>项目总额</p>
							</c:if>
		                </li>
		                <li class="progress">
		                    <div class="processingbar">
								<c:if test="${item.status==4 }">
									<font><fmt:formatNumber value="${item.saleMoney/item.money*100  }" pattern="0" type="number" />%</font>
								</c:if>
								<c:if test="${item.status==5 }">
									<font>100%</font>
									<div class="finish-font">履约中</div>
								</c:if>
								<c:if test="${item.status==6 }">
									<font>100%</font>
									<div class="finish-font">已还款</div>
								</c:if>
								<c:if test="${item.status==31 }">
									<font>0%</font>
									<div class="finish-font">预募集</div>
								</c:if>
		                    </div>
		                </li>
		                <li class="btn">
							<c:if test="${item.status==4 }">
								<a href="${selfSite}/zdjf/product/detail/${item.productId}" class="invest-btn open">立即投资</a>
							</c:if>
							<c:if test="${item.status==5 || item.status==6 }">
								<a href="${selfSite}/zdjf/product/detail/${item.productId}" class="invest-btn open close">详情</a>
							</c:if>
							<c:if test="${item.status==31 }">
								<div class="countdown J_countdown" data-begintime="${item.willIssueTimes}">
									<p><i class="iconfont">&#xe600;</i>距上线</p>
									<a href="${selfSite}/zdjf/product/detail/${item.productId}" class="time">
										<span class="hours">--</span>:
										<span class="minutes">--</span>:
										<span class="seconds">--</span>
									</a>
								</div>
							</c:if>
		                </li>
		            </ul>
		        </div>
		    </div>
		</c:forEach>
	   
	   <c:if test="${rsp.totalPage > 1 }">
	   <div id="kkpager" class="kp-left"></div>
	   </c:if>
			<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/kkpager.min.js"></script>
			<script>
			initPage('${rsp.totalPage}', '${rsp.total}', '${rsp.currPage}');
			function initPage(totalPage, totalRecords, pageNo){
				//生成分页
				kkpager.generPageHtml({
					pno : pageNo,
					//总页码
					total : totalPage,
					//总数据条数
					totalRecords : totalRecords,
					//默认值是link，可选link或者click
					mode : 'click',
			        click : function(n){
			            //手动选中按钮
			            var url = window.location.href;
			            if(url.indexOf("page=") > 0 ){
			                url = url.replace("page=${rsp.currPage}", "page=" + n);
			            } else {
			            	if(url.indexOf("?") > 0){
			            		url += "&page=" + n;
			            	} else {
			            		url += "?page=" + n;
			            	}
			            }
			            
			            window.location.href = url;
			            this.selectPage(n);
			            return false;
			        }
				});
			}
		</script>
	   </div>
	  
  	
	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>
  </body>
  
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/cycle.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/raphael.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery.downCount.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/product/js/list.js"></script>
</html>
