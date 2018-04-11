<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>正道金服</title>
	<!-- reset/iconfont -->
	<link rel="stylesheet" href="<%=path %>/css/front/reset.css">
	<link rel="stylesheet" href="<%=path %>/module/iconfont/iconfont.css">
	<link rel="stylesheet" href="<%=path %>/module/layui/css/layui.css">
	<link rel="stylesheet" href="<%=path %>/module/svgmap/css/SyntaxHighlighter.css">
	<link rel="stylesheet" href="<%=path %>/css/front/index.css">
	<link rel="stylesheet" href="<%=path %>/css/front/platformData.css">
</head>
<body>
	<!-- header -->
	<div class="header">
		<jsp:include page="../common/header.jsp"></jsp:include>
	</div>
	<!-- header end -->

	<!-- content -->
	<div class="insurance">
		<div class="insuranceBox">
			<div class="insurance_top">
				<p>严选优质项目 四重安全保障</p>
				<p>平台从项目严选、资金存管、信息安全及技术保障四个方面全方位为用户构建安全、透明的投资体验。</p>
			</div>
			
			<div class="insurance_bottom">
				<ul>
					<li class="shenhe active"></li>
					<li class="qianyue"></li>
					<li class="team"></li>
					<li class="anquan"></li>
				</ul>
				<div class="tabBox">
					<div class="tab">
						<p class="tabTlt1">项目严选</p>
						<p class="otlt">多重风控措施审核项目准入标准，严选优质项目</p>
						<p class="activetlt">1、贷前全面信息尽调，交叉验证审核 </p>
						<p class="othertlt">正道金服及专业风控团队对借款人的信息背景、工作情况、银行流水、征信报告等多种材料进行严格审查，剔除不良借款方。对于部分借款人实地考察走访审核，保证全方位覆盖所有风险点。同时每个项目背景信息公开、透明，证据资料真实、有效，用户可轻松了解每个项目的安全细节。</p>
						<p class="activetlt">2、贷中借款用途跟踪，风险实时把控</p>
						<p class="othertlt">项目履约后，将实时跟踪借款方资金流向，对借款人借款用途严格控制，避免借款人将资金挪作他用，降低欺诈风险。
</p>
						<p class="activetlt">3、贷后项目管理，还款能力实时评估</p>
						<p class="othertlt">正道金服及专业风控团队在借款项目还款期间，会通过多种渠道定期、高频监控借款人的资金情况，及时更新借款人的真实还款能力，督促借款人及时还款并对可能出现的风险及时预警。同时形成多种手段针对各种逾期情况的催收体系，督促其尽快还款。</p>
					</div>
					<div class="tab" style="display: none;">
						<p class="tabTlt2">资金存管</p>
						<p class="otlt">上海银行资金存管，用户资金与平台完全隔离</p>
						<p class="activetlt">1、上海银行资金存管上线</p>
						<p class="othertlt">正道金服携手上海银行，正式上线银行存管系统，银行将对用户资金进行独立管理和监督，平台无法直接触碰到用户资金，实现平台资金与用户资金的完全隔离。平台只提供借贷信息撮合服务，用户所有交易均直接通过上海银行完成。</p>
						<p class="activetlt">2、新浪支付资金通道体验</p>
						<p class="othertlt">正道金服与国内领先的第三方支付公司新浪支付达成长期合作，新浪支付作为技术支持方，为正道金服提供合法合规的资金账户管理服务和第三方资金托管支付通道，用户账户安全等级得到再次提升。</p>
					</div>
					<div class="tab" style="display: none;">
						<p class="tabTlt3">技术保障</p>
						<p class="otlt">自有研发团队+第三方技术保障</p>
						<p class="activetlt">1、资深的研发团队</p>
						<p class="othertlt">正道金服研发团队成员拥有多年互联网公司从业经验，对网络数据安全、资金托管和支付业务等核心技术拥有丰富的实践经验。
</p>
						<p class="activetlt">2、第三方技术保障</p>
						<p class="othertlt">正道金服联合新浪支付技术团队、上海银行技术团队，联合保障正道金服平台支付通道、资金账户安全，并携手业界领先的阿里云等技术团队，不断提升平台的安全和性能。</p>
						<p class="activetlt">3、数据传输隐私保护</p>
						<p class="othertlt">正道金服采用https传输加密协议对平台数据进行加密保护，用户在平台的每一步操作的安全性和隐私性都将受到加密保障。同时平台对信息进行多重备份，保障数据传输安全，确保数据零丢失，并有效保障历史数据有据可查。</p>
					</div>
					<div class="tab" style="display: none;">
						<p class="tabTlt4">信息安全</p>
						<p class="otlt">国家信息安全认证 信息数据全程加密</p>
						<p class="activetlt">1、国家信息安全等级三级认证</p>
						<p class="othertlt">正道金服已成功通过中华人民共和国公安部信息安全等级第三级认证，为用户资金信息及个人信息提供强有力的安全保障。</p>
						<p class="activetlt">2、数据全程加密，系统隔离</p>
						<p class="othertlt">正道金服对用户提供的所有信息进行全面保护，均通过多重加密措施进行存储和使用，任何人都无法强行获取任何用户信息，全面保护您的隐私安全。除客户事先同意，或应政
府及上级监管机构的要求而披露之外，不会向任何外部机构披露。</p>
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
    <script src="<%=path %>/module/sticky-header.js"></script>
    <script src="<%=path %>/module/layui/layui.js"></script>
	<script src="<%=path %>/js/front/insurance.js"></script>
	<script type="text/javascript">
		$('.header').stickMe({
			topOffset:100
		});
	</script>
</body>
</html>