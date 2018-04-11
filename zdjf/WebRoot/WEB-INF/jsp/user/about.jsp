<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>关于我们-专业透明的互联网理财平台，注册即送288</title>
	<meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
	<meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
	<meta name="copyright" content="版权所有 © 正道金服">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">  
	<meta name="viewport" content="width=1200"/>
	<link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/comm/css/about.css?t=20160428">
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
</head>
  
<body>
  <jsp:include page="comm/header.jsp"></jsp:include>
  <!-- mid -->
	<div class="mid">
	    <!-- banner -->
	    <div class="banner">
	        <div class="layout about">
	            <div class="title">关于我们</div>
	            <p class="con">投资者的利益高于一切，在正道金服上进行投资理财，绝对安全、透明！</p>
	        </div>
	    </div>
	
	    <!-- intro -->
	    <div class="intro" id="intro">
	        <div class="layout">
	            <div class="title">平台简介</div>
	            <p>　　正道金服是一家专业的第三方债权交易金融信息服务平台，平台由在金融、法律、互联网等领域有丰富经验的资深人士共同研发创立，正道金服网站由杭州云翳网络科技有限公司负责运营。</p>
	            <p>　　我们致力于为广大缺乏投资渠道的人们提供一个最安全、诚信、低风险、回报稳定的理财渠道。公司通过创新思维和高效的运营管理体系搭建了一个专业、透明、安全的服务平台。运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。</p>
	            <div class="icon">
	                <i class="pic01"></i>
	                <i class="pic02"></i>
	                <i class="pic03"></i>
	            </div>
	        </div>
	    </div>
	
	    <div class="next-icon"></div>
	
	    <!-- theory -->
	    <div class="theory">
	        <div class="layout">
	            <div class="title tit-wid">平台项目原理</div>
	            <p>　　本平台产品模式为债权转让模式的一种，即债权收益权转让模式，是指出借人（原始债权人）先行将自有资金借给借款人，形成债权，然后将其拥有债权的债权收益权委托正道金服转让给投资人。转让完成后，借款人根据其相关约定通过原始债权人将一定本金和利息支付给投资人，投资人受让的相应份额的债权收益权即获得实现。</p>
	            <div><img src="${selfSite}/zdjf/res/comm/images/about/flow-pic01.png" alt=""></div>
	            <div class="tip">
	                ① 债权收益权是指基础债权的债权人与债务人一致同意赋予基础债权受让方的优先于基础债权债权人受领基础债权债务人归还借款本金和一定利息款项的权利。<br/>
	                ② 基础债权是指出借人（原始债权人）先行将自有资金借给借款人而形成的债权。
	            </div>
	        </div>
	    </div>
	
	    <!-- aptitude -->
	    <div class="aptitude" id="aptitude">
	        <div class="layout">
	            <div class="title">平台资质</div>
	            <p>　　本平台拥有合法合规的公司营业执照并有顶级国际域名证书，保证投资者的合法利益和稳定收益，让投资者能够真正地享受安心，安稳，安全的理财过程。</p>
	            <div class="table">
	                <table>
	                    <tr>
	                        <td><span class="aptitude-pic01"></span></td>
	                        <td><span class="aptitude-pic02"></span></td>
	                    </tr>
	                    <tr>
	                        <td>域名证书</td>
	                        <td>营业执照</td>
	                    </tr>
	                </table>
	            </div>
	        </div>
	    </div>
	
	    <!-- content -->
	    <div class="content clearfix" id="content">
	        <div class="layout">
	            <div class="left">
	                <div class="tit">联系我们</div>
	                <p>公司地址：浙江省杭州市拱墅区湖州街36-1号814室</p>
	                <p>邮　　编：310000</p>
	                <p>官网域名：www.zdjfu.com</p>
	                <p>客服热线：400-690-9898，每天8:30-22:00</p>
	                <p>客服邮箱：kefu@zdjfu.com</p>
	            </div>
	            <div class="right"><img src="${selfSite}/zdjf/res/comm/images/about/map.png" alt=""></div>
	        </div>
	    </div>
	
	    <!-- friend -->
	    <div class="friend">
	        <div class="layout">
	            <div class="title">合作伙伴</div>
	            <div class="friend-list infoSwitch J_licenseList">
	                <a class="next" href="javascript:void(0)"><i></i></a>
	                <a class="prev" href="javascript:void(0)"><i></i></a>
	                <div class="box">
	                    <ul class="list">
	                        <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/ccb.gif"></li>
	                        <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/abc.gif"></li>
	                        <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/sunshine-ins.gif"></li>
	                        <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/dmls.gif"></li>
	                        <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/icbc.gif"></li>
	                        <li><img src="${selfSite}/zdjf/res/comm/images/friend-logo/cba.gif"></li>
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </div>
	
	</div>
	<jsp:include page="comm/footer.jsp"></jsp:include>
	<jsp:include page="comm/helper.jsp"></jsp:include>
	<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery.cxscroll.min.js"></script>
	<script>
	   /**
	     * 图片滚动
	     **/
	    $(".J_licenseList").cxScroll({
	        step: 1,        //滑动图片张数
	        auto: false     //自动滚动，  更多参数见引用文件
	    })
	</script>
</body>
</html>
