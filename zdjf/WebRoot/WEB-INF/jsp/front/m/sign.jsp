<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui"/>
    <title>正道金服</title>
    <!-- 公共样式 -->
    <link rel="stylesheet" href="<%=path%>/css/front/m/style.css"/>
    <link rel="stylesheet" href="<%=path%>/css/front/m/index.css"/>
	<!-- 公共样式end -->
	<link rel="stylesheet" href="<%=path%>/css/front/m/sign.css"/>
    <style id="_zoom"></style>
</head>
<body class="zoom">
<div class="sign">
	<div class="s_one" >
		<img src="<%=path%>/images/front/m/sign/sign_01.jpg" alt="">
	</div>
	<div class="s_two">
		<img src="<%=path%>/images/front/m/sign/sign_02.jpg" alt="">
		<c:if test="${''!=phone }">
		<div class="phone hide">${phone }</div>
		</c:if>
		<div class="reg_source hide">${reg_source }</div>
		<div class="twobox">
			<c:if test="${''!=phone }">
				<a href="javascript:" class="toSign"  >点击签到</a><span></span>
			</c:if>	
		</div>
	</div>
	<%-- <div class="s_three">
		<img src="<%=path%>/images/front/m/sign/sign_03.jpg" alt="">
		<div class="threebox">
			<p>-</span></p>
		</div>
	</div> --%>
	<%-- <div class="s_four">
		<img src="<%=path%>/images/front/m/sign/sign_04.jpg" alt="">
		<div class="fourbox">
			<c:if test="${''==phone }">
				<span>您当前暂未登录</span><a href="javascript:" class="tologin">立即登录>></a>
			</c:if>
			<c:if test="${''!=phone }">
				<span>您已获得${totalcoin }点正经值</span><a href="javascript:" class="toinvest">立即投资>></a>
			</c:if>
		</div>
	</div> --%>
	<%-- <div class="s_five">
		<img src="<%=path%>/images/front/m/sign/sign_05.jpg" alt="">
		<div class="fivebox">
			<div class="box">
				<h3>-</h3>
				<p><img src="<%=path%>/images/front/m/sign/five_01.png" alt="">X800点</p>
			</div>
			<div class="box">
				<h3>-</h3>
				<p><img src="<%=path%>/images/front/m/sign/five_02.png" alt="">X800点</p>
			</div>
			<div class="box">
				<h3>-</h3>
				<p><img src="<%=path%>/images/front/m/sign/five_03.png" alt="">X500点</p>
			</div>
		</div>
	</div>
	<div class="s_six">
		<img src="<%=path%>/images/front/m/sign/sign_06.jpg" alt="">
		<div class="sixbox">
			<p>-<span>-点</span></p>
		</div>
	</div>
	<div class="s_seven">
		<img src="<%=path%>/images/front/m/sign/sign_07.jpg" alt="">
		<div class="sevenbox">
			<a href="javascript:"></a>
		</div>
	</div> --%>
</div>
<script type="text/javascript" src="<%=path%>/js/front/m/zoom.js"></script>
<script type="text/javascript" src="<%=path%>/js/front/m/zepto.js"></script>
<script type="text/javascript">
	var path='<%=path%>';
	 $('.toSign').click(function(){
		var phone=$('.phone').text();	 
		var reg_source=$('.reg_source').text();
		var url=path+'/m/sign/create.action';
		$.post(url,{"phone":phone,"reg_source":reg_source},function(data){
			if(data.returnCase){
				alert('签到成功');
				window.location.reload();
			}else{
				alert(data.content);
			}
		});
	});
</script>
</body>
</html>

