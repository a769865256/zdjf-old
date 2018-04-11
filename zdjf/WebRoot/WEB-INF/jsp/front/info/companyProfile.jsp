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
	<div class="companyProfile">
		<div class="companyProfileBox">
			<div class="companyProfile_1">
				<div class="companyProfile_msg">
					<p>杭州云翳网络科技有限公司成立于2016年7月，公司位于中国浙江省杭州市西湖区，实缴注册资本5000万人民币，是一家专注于互联网金融信息科技的研发型企业，公司经营并研发的平台正道金服是一款专业、安全、高效的互联网理财信息科技服务平台。2017年4月，获得华夏九州（北京）证券咨询有限公司数千万A轮战略投资。双方将在多个领域持续展开深入、多元化的共荣发展。并将持之以恒致力于成为国内专业的互联网金融信息科技服务商，旨在为广大用户打造一个安全透明、高效沟通的互联网金融理财信息撮合平台。</p>
					<p>平台的项目均由专业的风控、法律团队严格筛选、层层把关。从项目源头预防风险，为用户甄选优质的各类投资项目。在资金安全方面，平台选择上海银行进行用户资金存管，正道金服的注册用户在平台的交易过程资金均在自己专属的上海银行存管账户中流转，真正实现平台资金与用户资金隔离，分账管理。同时，平台携手国内领先的金融支付专家新浪支付，为用户打造流畅的投资体验。</p>
					<p>公司经营：网络信息技术、计算机软硬件的技术开发、技术咨询、技术服务；网页设计；企业形象策划；市场营销策划； 经济信息咨询；物业管理；接受企业委托从事资产管理、投资管理，投资咨询（除证券、期货）（上述经营范围未经金融等监管部门批准，不得从事向公众融资存款、融资担保、代客理财等金融服务）；电子产品的批发、零售（依法须经批准的项目，经相关部门批准后方可开展经营活动）</p>
					<div class="msg">
						<ul>
							<li><i></i>公司名称：杭州市云翳网络科技有限公司</li>
							<li><i></i>注册资本：五千万元整</li>
							<li><i></i>注册地址：杭州市西湖区西溪国际商务中心5幢909室</li>
						</ul>
						<ul>
							<li><i></i>经营地址：杭州市西湖区西溪国际商务中心5幢909室</li>
							<li><i></i>成立时间：2016/7/11</li>
							<li><i></i>法定代表人：刘学洪</li>
						</ul>
						<ul>
							<li><i></i>联系方式：客服热线：400-690-9898</li>
							<li style="padding-left: 80px;">客服邮箱：service@zdjfu.com</li>
							<li style="padding-left: 80px;">媒体邮箱：media@zdjfu.com</li>
						</ul>
					</div>
						
				</div>
			</div>
			
			<div class="companyProfile_2">
				<div class="companyProfile_msg"></div>
			</div>
			<div class="companyProfile_3">
				<div class="companyProfile_msg"></div>
			</div>
			<div class="companyProfile_4">
				<div class="companyProfile_msg">
					<div class="character">
						<div><span>刘学洪</span>董事长兼总经理</div>
						<p>20年以上国企及投资行业从业经验，曾先后创办多家中型企业，2008年-2011年进入北京市党委党校进行深造，后转型投身于金融行业，现就职于国内某大型国企投资管理公司,并担任董事长一职，具备非常丰富的企业投资咨询及管理从业经验。正道金服获国资入股后，担任董事长兼总经理一职，全面负责公司日常经营管理及平台业务运作。</p>
					</div>
					<div class="character">
						<div><span>柯瑞</span>副董事长</div>
						<p>曾就职于首批获得中国证监会投资咨询资格的国内大型证券投资咨询公司，从事证券交易、投资咨询等工作10年以上，拥有丰富的投资咨询、证券研究等金融行业从业经验，具备专业、独到的行业经验和判断能力，于2016年创立华夏九州（北京）证券咨询有限公司并投资正道金服；</p>
					</div>
					<!-- <div class="character">
						<div><span>张培强</span>监事</div>
						<p>曾任某投资公司副总经理，负责公司日常管理和业务拓展，经营网贷线下业务多年，积累了丰富经验和资源。现创立正道金服，并担任执行董事兼总经理，全面负责公司日常经营管理及平台业务运作。</p>
					</div>
					<div class="character">
						<div><span>许宁宁</span>监事</div>
						<p>多年品牌传播策划、大型品牌活动执行经验，多年互联网金融行业从业经验，具有丰富的线上线下传播推广、品牌塑造、产品运营等实际操盘经验；曾是杭州某互联网金融企业创业团队成员之一，并任职运营中心负责人一职。经历了企业从0-1的发展历程，对互联网金融的业务模式尤其是汽车金融及其产品定位、运营、推广及后期品牌管理有丰富的经验。</p>
					</div> -->
				</div>
			</div>
			<div class="companyProfile_5">
				<div class="companyProfile_msg">
					<ul>
						<li onclick="DataHtml('zhengshu_1.png','<%=path %>/images/front/img/informData/zhengshu_1.png','2.07','1.85')"><img src="<%=path %>/images/front/img/informData/zhengshu_1_07.png" alt="" id="displayimg" /></li>
						<li onclick="DataHtml('zhengshu_2_07.png','<%=path %>/images/front/img/informData/zhengshu_2_07.png','2.03','2')"><img src="<%=path %>/images/front/img/informData/zhengshu_2_07.png" alt="" /></li>
						<li onclick="DataHtml('zhengshu_3.png','<%=path %>/images/front/img/informData/zhengshu_3.png','2.07','1.95')"><img src="<%=path %>/images/front/img/informData/zhengshu_3_07.png" alt="" /></li>
					</ul>
					<ul style="margin-top: 80px;">
						<li onclick="DataHtml('zhengshu_4.png','<%=path %>/images/front/img/informData/zhengshu_4.png','1.2','2.6')"><img src="<%=path %>/images/front/img/informData/zhengshu_4.png" alt="" style="width: 400px;height: 230px;"/></li>
					</ul>
				</div>
			</div>
			<div class="companyProfile_6">
				<div class="companyProfile_msg"></div>
			</div>
			<div class="companyProfile_7">
				<div class="parlist">
					<%-- <div class="list"><img src="<%=path %>/images/front/img/partner/p1.png" alt="" /></div> --%>
					<div class="list"><a href="https://www.aliyun.com/" target="_blank"><img src="<%=path %>/images/front/img/partner/p2.png" alt="" /></a></div>
					<div class="list"><a href="https://pay.sina.com.cn" target="_blank"><img src="<%=path %>/images/front/img/partner/p3.png" alt="" /></a></div>
					<div class="list"><a href="http://www.alidayu.com/" target="_blank"><img src="<%=path %>/images/front/img/partner/p4.png" alt="" /></a></div>
					<div class="list"><a href="javascript:"><img src="<%=path %>/images/front/img/partner/p5.png" alt="" /></a></div>
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
    <script type="text/javascript">
	    $('.header').stickMe({
			topOffset:100
		});
    	/*点击图片放大*/
    	layui.use('layer', function(){
		  var layer = layui.layer;
		}); 
    	function DataHtml(name, url ,heightNum ,widthNum) {
            var height = $("#displayimg").height()*heightNum;
            var width = $("#displayimg").width()*widthNum;
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                area: [width + 'px', height + 'px'], //宽高
                content: "<img alt=" + name + " title=" + name + " src=" + url + " />"
            });
        }
    </script>
</body>
</html>