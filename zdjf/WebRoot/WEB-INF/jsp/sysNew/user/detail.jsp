<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	String path = request.getContextPath(); 
	// 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）: 
	String basePath = request.getScheme()+"://"+request.getServerName() +":"+request.getServerPort()+path+"/"; 
%> 
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>会员详情</title>
</head>
<body>
<article class="page-container">
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<thead>
				<tr class="text-c">
					<th width="200">投资次数：0</th>
					<th width="200">可用余额：0</th>
					<th width="200">累计投资：0</th>
					<th width="200">累计收益：0</th>
					<th width="200">已收收益：0</th>
					<th width="200">待收收益：0</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c">
					<td>已回本金：0</td>
					<td>待回本金：0</td>
					<td>累计充值：0</td>
					<td>累计提现：0</td>
					<td>冻结金额：0</td>
					<td>优惠抵现：0</td>
				</tr>
				<tr class="text-c">
					<td>投资明细</td>
					<td>资金流水</td>
					<td>红包记录</td>
					<td>加息券记录</td>
					<td>正经值明细</td>
					<td>银行卡</td>
				</tr>
			</tbody>
		</table>
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<caption>基本信息</caption>
			<tbody>
				<tr><td width="40%">用户名：</td><td width="60%">手机号码</td></tr>				
				<tr><td>注册时间：</td><td>手机号码</td></tr>				
				<tr><td>上次登陆时间：</td><td>手机号码</td></tr>				
				<tr><td>注册IP：</td><td>手机号码</td></tr>				
				<tr><td>最近投资时间：</td><td>手机号码</td></tr>				
				<tr><td>最近充值时间：</td><td>手机号码</td></tr>				
				<tr><td>注册渠道：</td><td>手机号码</td></tr>				
				<tr><td>注册来源：</td><td>手机号码</td></tr>			
			</tbody>
		</table>
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<caption>身份信息</caption>
			<tbody>
				<tr><td width="40%">真实姓名：</td><td width="60%">手机号码</td></tr>				
				<tr><td>身份证号码：</td><td>手机号码</td></tr>				
				<tr><td>实名认证时间：</td><td>手机号码</td></tr>			
			</tbody>
		</table>
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<caption>附加信息</caption>
			<thead>
				<tr>
					<th>用户头像</th>
					<th>用户昵称</th>
					<th>用户ID</th>
					<th>用户学历</th>
					<th>用户职业</th>
					<th>婚姻状况</th>
					<th>月收入</th>
					<th>紧急联系人</th>
					<th>居住地址</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>默认公司LOGO</td>
					<td>默认</td>
					<td>默认</td>
					<td>默认</td>
					<td>默认</td>
					<td>默认</td>
					<td>默认</td>
					<td>默认</td>
					<td>默认</td>
				</tr>			
			</tbody>
		</table>
		<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
			<caption>其他信息</caption>
			<tbody>
				<tr>
					<td width="40%">邀请人：</td>
					<td width="60%"></td>
				</tr>				
				<tr>
					<td>状态：</td>
					<td>
						<div class="row cl">
							<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
								<select name="articlecolumn" class="select">
									<option value="0">有效</option>
									<option value="1">新闻资讯</option>
									<option value="11">├行业动态</option>
									<option value="12">├行业资讯</option>
									<option value="13">├行业新闻</option>
								</select>
								</span>
							</div>
						</div>
					</td>
				</tr>				
				<tr>
					<td>用户类型：</td>
					<td>
						<div class="row cl">
							<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
								<select name="articlecolumn" class="select">
									<option value="0">普通户口</option>
									<option value="1">新闻资讯</option>
									<option value="11">├行业动态</option>
									<option value="12">├行业资讯</option>
									<option value="13">├行业新闻</option>
								</select>
								</span>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td>
						<div class="row cl">
							<div class="formControls col-xs-8 col-sm-9">
								<textarea class="textarea"></textarea>
							</div>
						</div>
					</td>
				</tr>			
			</tbody>
		</table>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并提交审核</button>
			</div>
		</div>
	</div>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">


</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>