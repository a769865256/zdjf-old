<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>邀请有礼-我的财富-专业透明的互联网理财平台</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta name="viewport" content="width=1200"/>   
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/extendlink.css">
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/kkpager_gray.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
</head>
  
<body>
   <jsp:include page="../comm/header.jsp"></jsp:include>
	<div class="body">
		<jsp:include page="center_nav.jsp"></jsp:include>

		<!-- 内容 -->
		<div class="body-right">
			<p class="title">邀请有礼
			</p>
			<%--<p class="share-banner"><img src="${selfSite}/zdjf/res/center/images/share/share-pic.png" alt=""></p>--%>
			<%--<p class="invitation-p"><a href="${selfSite}/zdjf/about/notice.html#999" target="_blank" class="help-btn"><i></i>什么是正经值？</a></p>--%>
			<div class="invitation-div">
				<div class="icon-invition">
					<i></i>
					<span>邀请好友注册</span>
				</div>
				<div>
					<p class="p"><span class="span">我的邀请码:</span> <span class="red-bold">${rsp.userInfo.inviteCode }</span></p>
					<p class="p" style="margin-top: 13px"><span class="span letter-spacing-4">邀请链接:</span>
					<input type="text" id="shareUrl" value="${selfSite}/zdjf/user/register?code=${rsp.userInfo.inviteCode }"  /></p>
					<div class="div"><span class="span margin-top5"  style="float: left; margin-right: 15px;">分享给朋友:</span>
						<div style="float: left;">
							<!-- JiaThis Button BEGIN -->
							<div class="jiathis_style_24x24">
								<a class="jiathis_button_weixin" data-way="wexin"></a>
								<a class="jiathis_button_tsina" data-way="qzone"></a>
								<a class="jiathis_button_qzone" data-way="sinawbo"></a>
								<a class="jiathis_button_tqq" data-way="qwbo"></a>
							</div>
							<!-- JiaThis Button END -->
						</div>
						
					</div>
				</div>
			</div>
			<div class="weixin-code">
				<div id="qrcode">
				</div>
				<div class="qrcode-text">
					微信扫一扫，点击右上角分享
				</div>
			</div>
			<div class="inv-info">
				共邀请
				<b>${rsp.inviteCount}</b>
				人注册，被邀请人累计投资
				<b><fmt:formatNumber type="number" pattern="#,##0" value="${rsp.inviteAmount}" /></b>
				元，您获得正经值奖励
				<b><fmt:formatNumber type="number" pattern="#,##0.00" value="${rsp.inviteCoin}" /></b>
				元
				<div class="iconfont">&#xe602;
					<div class="tip-con">
						<span>正经值可在正道金服平台任一投资项目中使用，是平台的“硬通货”。投资人在投资时可用正经值直接抵扣本金。  <a href="${selfSite}/zdjf/active/zj-money.html" target="_blank" class="btn-blue-t">了解更多</a></span>
						<span class="icon-arrow"></span>
						<span class="icon-arrow01"></span>
					</div>
				</div>
			</div>
			<div class="inv-title"><span>◆</span>邀请好友列表</div>
			<table>
				<tr>
					<th class="tjr ">注册时间</th>
					<th class="tzsj">用户名</th>
					<th class="tzxm">是否实名认证</th>
					<th class="tzhb">是否投资</th>
					<th class="tzhb">贡献正经值</th>
				</tr>
				
				<c:forEach var="info" items="${rsp.pageData.datas }">
					<tr>
						<td class="tzsj">
							<fmt:formatDate value="${info.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td class="tjr ">
							${info.phone }
						</td>
						<td class="tzxm">
							<c:if test="${info.isAuth == 1}">
								<i class="icon-hook"></i>
							</c:if>
							<c:if test="${info.isAuth != 1}">
								-
							</c:if>
						</td>
						<td class="tzhb">
							<c:if test="${info.isInvest == 1}">
								<i class="icon-hook"></i>
							</c:if>
							<c:if test="${info.isInvest != 1}">
								-
							</c:if>
						</td>
						<td class="tzhb">
							<fmt:formatNumber type="number" pattern="#,##0.00" value="${info.amount}" />
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<c:if test="${empty rsp.pageData.datas }">
				<p class="invitation-empty">
					<i class="icon-invent"></i>
					<span>暂无邀请记录</span>
				</p>
			</c:if>
			<div class="page" style="position: static">
				<!-- pager -->
				<jsp:include page="../comm/pager.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<jsp:include page="../comm/footer.jsp"></jsp:include>
	<jsp:include page="../comm/helper.jsp"></jsp:include>

   <script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
   <script type="text/javascript" src="${selfSite}/zdjf/res/center/js/qrcode.min.js"></script>
   <script>
	   $('.jiathis_style_24x24 a').on('click', function(){
		   //jiathis分享
		   jiathis_config = {};
		   function setShare(url) {
			   jiathis_config.url = url;
		   }
		   var shareUrl = '${selfSite}/zdjf/h5/user/register.html?code=${rsp.userInfo.inviteCode }&inviteSource=' + $(this).data('way');
		   setShare( shareUrl);
	   })

    UserPhone = getCookie("phone") || ''; //全局变量
    function getCookie(name) {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) {
            return unescape(arr[2]);
        } else {
            return null;
        }
    }
	   //weixin-code
	   var qrcode = new QRCode(document.getElementById("qrcode"), {
		   width : 90,
		   height : 90
	   });
	   qrcode.makeCode('http://m.zdjfu.com/reg2.html?code='+UserPhone+'&qrcode=true&inviteSource=weixin');
   </script>
  </body>
</html>
