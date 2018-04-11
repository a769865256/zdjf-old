<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
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
	<link rel="stylesheet" href="<%=path%>/css/front/platformData.css">
</head>
<body>
<!-- header -->
<div class="header">
	<jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="noticeMain">
	<input type="hidden" value="${showType}" id="showType"/>
	<div class="noticeMainBox">
		<div class="noticeMainCentent">
			<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
				<ul class="layui-tab-title">
					<li id="sysNotice" <c:if test="${showType==1}">class="layui-this"</c:if> >平台公告</li>
					<li id="newsReport" <c:if test="${showType==6}">class="layui-this"</c:if> >媒体报道</li>
				</ul>
				<div class="layui-tab-content">
					<div <c:choose>
					<c:when test="${showType==1}">
					class="layui-tab-item layui-show"
						</c:when>
						<c:otherwise>
						class="layui-tab-item"
							</c:otherwise>
							</c:choose> >
						<div class="noticeMain_1">
							<c:forEach items="${noticePage.dataList}" var="notice" varStatus="vs">
								<a href="<%=path %>/notice.action?notice_id=${notice.id }">
									<div>
										<p>${notice.title}</p>
										<span>
											<fmt:formatDate value="${notice.create_time}"
													type="both" pattern="yyyy-MM-dd" />
										</span>
									</div>
								</a>
							</c:forEach>
						</div>
					</div>
					<div <c:choose>
						<c:when test="${showType==6}">
							class="layui-tab-item layui-show"
						</c:when>
						<c:otherwise>
							class="layui-tab-item"
						</c:otherwise>
					</c:choose> >
						<div class="noticeMain_2">
							<c:forEach items="${noticePage.dataList}" var="newReport" varStatus="vs2">
								<a href="<%=path %>/notice.action?notice_id=${newReport.id }">
								<div class="noticeMain_2_b">
									<div class="left"><img src="${newReport.image_url}" alt=""></div>
									<div class="right">
										<p class="tltB_1">${newReport.title}</p>
										<p class="tltB_2">${newReport.keywords}&nbsp;&nbsp;<fmt:formatDate value="${newReport.create_time}" pattern="yyyy.MM.dd" /></p>
										<p class="tltB_3"> ${fn:substring(newReport.description,0,100)}...</p>
									</div>
								</div>
								</a>
							</c:forEach>
						</div>
					</div>
					<div id="noticeMain_pag"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- content end-->


<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path %>/module/jquery/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/module/sticky-header.js"></script>
<script src="<%=path%>/module/layui/layui.js"></script>
<script src="<%=path%>/js/front/noticMain.js"></script>
<script type="text/javascript">
	$('.header').stickMe({
		topOffset:100
	});
    var total;
    var pageSize;
	$(function () {
        if ($("#showType").val() == 1) {
            pageSize = 10;
            total="${noticePage.total}";
        } else {
            pageSize = 5;
            total="${noticePage.total}";

        }
    })

	$("#sysNotice").click(function () {
        pageSize = 10;
        window.location = '<%=path%>/company/1/notice.action?currentPage=1&limit=10';
    });
    $("#newsReport").click(function () {
        pageSize = 5;
        window.location = '<%=path%>/company/6/notice.action?currentPage=1&limit=5';
    });
    var total;
    layui.use(['layer','laypage','element','carousel'], function(){
        var laypage = layui.laypage;

        //执行一个laypage实例
        laypage.render({
            elem: 'noticeMain_pag', //注意，这里的 noticeMain_pag 是 ID，不用加 # 号
            limit: ${noticePage.limit}, //每页显示的条数
            groups:5, //连续出现的页码个数
            first:'首页',
            last:'尾页',
            count: ${noticePage.total} ,//数据总数，从服务端得到
            curr: function(){ //起始页
                var page = location.search.match(/currentPage=(\d+)/);
                return page ? page[1] : 1;
            }(),
            jump:function(obj,first){ //切换分页的回调
                if(!first){
                    var showType = $("#showType").val();

                    if (showType == 1) {
                        pageSize = 10;
                    } else {
                        pageSize = 5;
                    }
                    window.location = '<%=path%>/company/'+showType+'/notice.action?currentPage='+obj.curr+'&limit='+pageSize;
                }
            }
        });
    });
</script>
</body>
</html>