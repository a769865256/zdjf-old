<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String menu = (String)request.getAttribute("menu");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="../js/sys/favicon.ico" >
<link rel="Shortcut Icon" href="../js/sys/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="../js/sys/lib/html5shiv.js"></script>
<script type="text/javascript" src="../js/sys/lib/respond.min.js"></script>
<![endif]-->

<link rel="stylesheet" type="text/css" href="../js/sys/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="../js/sys/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="../js/sys/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="../js/sys/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="../js/sys/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="../js/sys/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>正道金服admin</title>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl">
			<a class="logo navbar-logo f-l mr-10 hidden-xs" data-href="../js/sys/welcome.html" href="javascript:;">正道金服</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a>
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="article_add('添加资讯','../js/sys/article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
							<li><a href="javascript:;" onclick="picture_add('添加资讯','../js/sys/picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
							<li><a href="javascript:;" onclick="product_add('添加资讯','../js/sys/product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
							<li><a href="javascript:;" onclick="member_add('添加用户','../js/sys/member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
					</ul>
				</li>
			</ul>
		</nav>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>超级管理员</li>
				<li class="dropDown dropDown_hover">
					<a href="#" class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<li><a href="#">切换账户</a></li>
						<li><a href="#">退出</a></li>
				</ul>
			</li>
				<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 首页</dt>
		</dl>
		<%=menu %>
		<!-- <dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 会员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
		</dl>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 财务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="../js/sys/none.html" data-title="购买明细" href="javascript:void(0)">购买明细</a></li>
					<li><a data-href="../js/sys/none.html" data-title="利息营收结算" href="javascript:void(0)">利息营收结算</a></li>
					<li><a data-href="../js/sys/none.html" data-title="项目还本付息管理" href="javascript:void(0)">项目还本付息管理</a></li>
					<li><a data-href="../js/sys/none.html" data-title="资金流水" href="javascript:void(0)">资金流水</a></li>
					<li><a data-href="../js/sys/none.html" data-title="计息记录" href="javascript:void(0)">计息记录</a></li>
					<li><a data-href="../js/sys/none.html" data-title="还款计划管理" href="javascript:void(0)">还款计划管理</a></li>
					<li><a data-href="../js/sys/none.html" data-title="上海银行对账" href="javascript:void(0)">上海银行对账</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 优惠管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="../js/sys/none.html" data-title="加息券管理" href="javascript:void(0)">加息券管理</a></li>
					<li><a data-href="../js/sys/none.html" data-title="加息券领用记录" href="javascript:void(0)">加息券领用记录</a></li>
					<li><a data-href="../js/sys/none.html" data-title="红包领用记录" href="javascript:void(0)">红包领用记录</a></li>
					<li><a data-href="../js/sys/none.html" data-title="正经值管理" href="javascript:void(0)">正经值管理</a></li>
					<li><a data-href="../js/sys/none.html" data-title="正经值明细" href="javascript:void(0)">正经值明细</a></li>
					<li><a data-href="../js/sys/none.html" data-title="兑换管理" href="javascript:void(0)">兑换管理</a></li>
					<li><a data-href="../js/sys/none.html" data-title="用户兑换列表" href="javascript:void(0)">用户兑换列表</a></li>
					<li><a data-href="../js/sys/none.html" data-title="提现券记录" href="javascript:void(0)">提现券记录</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 活动管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="../js/sys/none.html" data-title="抢标活动管理" href="javascript:void(0)">抢标活动管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe620;</i> 配置管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="../js/sys/none.html" data-title="广告位列表" href="javascript:void(0)">广告位列表</a></li>
					<li><a data-href="../js/sys/none.html" data-title="公告列表" href="javascript:void(0)">公告列表</a></li>
					<li><a data-href="../js/sys/none.html" data-title="友情链接列表" href="javascript:void(0)">友情链接列表</a></li>
					<li><a data-href="../js/sys/none.html" data-title="数据字典" href="javascript:void(0)">数据字典</a></li>
					<li><a data-href="../config/source/list.action" data-title="渠道管理" href="javascript:void(0)">渠道管理</a></li>
					<li><a data-href="../js/sys/none.html" data-title="安卓客户端版本" href="javascript:void(0)">安卓客户端版本</a></li>
					<li><a data-href="../js/sys/none.html" data-title="银行卡列表" href="javascript:void(0)">银行卡列表</a></li>
					<li><a data-href="../js/sys/none.html" data-title="提现手续费管理" href="javascript:void(0)">提现手续费管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe622;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="../js/sys/none.html" data-title="管理员列表" href="javascript:;">管理员列表</a></li>
					<li><a data-href="../js/sys/none.html" data-title="角色列表" href="javascript:;">角色列表</a></li>
					<li><a data-href="../js/sys/none.html" data-title="权限列表" href="javascript:;">权限列表</a></li>
					<li><a data-href="../js/sys/none.html" data-title="修改密码" href="javascript:;">修改密码</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 数据统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="../js/sys/none.html" data-title="数据分析" href="javascript:;">数据分析</a></li>
					<li><a data-href="../js/sys/none.html" data-title="数据统计" href="javascript:;">数据统计</a></li>
					<li><a data-href="../js/sys/none.html" data-title="渠道统计" href="javascript:;">渠道统计</a></li>
				</ul>
			</dd>
		</dl> -->
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="首页" data-href="../js/sys/welcome.html">首页</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group">
			<a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a>
			<a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
		</div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="../js/sys/welcome.html"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="../js/sys/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/sys/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="../js/sys/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="../js/sys/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="../js/sys/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script>
</body>
</html>