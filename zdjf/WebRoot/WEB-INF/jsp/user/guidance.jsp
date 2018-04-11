<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
	<base href="${selfSite}/zdjf">
	<meta charset="UTF-8">
	<title>新手指引-专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">  
	<meta name="viewport" content="width=1200"/>
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/guidance.css">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<jsp:include page="comm/header.jsp"></jsp:include>
	<!-- mid -->
	<div class="mid">
		<div class="title"></div>

		<div class="step">
			<!-- step01 -->
			<div class="step01 J_step01">
				<div class="arrow opac"></div>
				<div class="arrow-bot opac"></div>
				<div class="arrow-font opac"></div>
				<a href="${selfSite}/zdjf/user/register" class="btn opac">立即注册</a>
				<div class="font opac">
					1分钟注册成为正道金服会员，<br />轻松开始理财！！
				</div>
			</div>

			<!-- step02 -->
			<div class="step02 J_step02">
				<div class="arrow opac"></div>
				<div class="arrow-bot opac"></div>
				<div class="arrow-font opac"></div>
				<a href="${selfSite}/zdjf/center/safe" class="btn opac">立即开通</a>
				<div class="rocket opac"></div>
				<div class="flying-saucer opac"></div>
			</div>

			<!-- step03 -->
			<div class="step03 J_step03">
				<div class="arrow opac"></div>
				<div class="arrow-bot opac"></div>
				<div class="arrow-font opac"></div>
				<div class="tab-title opac">五大安全保障</div>
				<div class="table">
					<table>
						<tr>
							<td class="tab02 opac"><i class="ic01"></i>专业产品</td>
							<td class="tab03 opac"><i class="ic02"></i>风险保障</td>
						</tr>
						<tr>
							<td class="tab04 opac"><i class="ic03"></i>资金安全</td>
							<td class="tab05 opac"><i class="ic04"></i>公开透明</td>
						</tr>
						<tr>
							<td class="tab06 opac"><i class="ic05"></i>合法合规</td>
							<td></td>
						</tr>
					</table>
				</div>
				<a href="${selfSite}/zdjf/product/list" target="_blank" class="btn opac">挑选理财产品</a>
			</div>
			<!-- step04 -->
			<div class="step04 J_step04">
				<div class="arrow opac"></div>
				<div class="arrow-bot opac"></div>
				<div class="arrow-font opac"></div>
				<div class="flag-dark opac"></div>
				<div class="flag opac"></div>
				<div class="flying-saucer opac"></div>
				<div class="font opac"></div>
			</div>
		</div>
	</div>

	<div class="invest-btn">
		<a href="${selfSite}/zdjf/product/list" target="_blank">立即投资，土豪带你飞</a>
	</div>

	<jsp:include page="comm/footer.jsp"></jsp:include>
	<jsp:include page="comm/helper.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/guidance.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/layer.js"></script>
</html>
