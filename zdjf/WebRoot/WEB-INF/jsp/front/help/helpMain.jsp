<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <link rel="stylesheet" href="<%=path %>/css/front/reset.css">
    <link rel="stylesheet" href="<%=path %>/module/iconfont/iconfont.css">
    <link rel="stylesheet" href="<%=path %>/module/layui/css/layui.css">
    <link rel="stylesheet" href="<%=path %>/css/front/index.css">
    <link rel="stylesheet" href="<%=path %>/css/front/help.css">
</head>
<body>
<!-- header -->
<div class="header">
<jsp:include page="../common/header.jsp"></jsp:include>
</div>
<!-- header end -->

<!-- content -->
<div class="help">
    <div class="vcontent">
        <div class="vcontent_1">
            <h2><a href="javascript:;">热门问题</a></h2>
            <div class="helpMsg">
                <a href="<%=path %>/fundsDeposit/1/help.action"><i class="iconfont one">&#xe602;</i><span>银行存管有什么优势？</span></a>
                <a href="<%=path%>/investGuide/2/help.action"><i class="iconfont tow">&#xe602;</i><span>如何解除，更换已绑定的银行卡？</span></a>
                <a href="<%=path %>/commonQuestion/2/help.action"><i class="iconfont three">&#xe602;</i><span>新手注册有什么奖励？</span></a>
                <a href="<%=path%>/fundsDeposit/3/help.action"><i class="iconfont frou">&#xe602;</i><span>如何开通银行存管账户？</span></a>
            </div>
            <div class="helpMsg">
                <a href="<%=path%>/investGuide/5/help.action"><i class="iconfont five">&#xe602;</i><span>申请提现后多久能到账？</span></a>
                <a href="<%=path%>/investGuide/3/help.action"><i class="iconfont six">&#xe602;</i><span>投资成功后是否可以取消或提前收回本金？</span></a>
                <a href="javascript:"></a>
                <a href="javascript:"></a>
            </div>
        </div>
        <div class="vcontent_2">
            <h2><a href="javascript:;">快捷服务</a></h2>
            <div class="helpMsg">
                <div class="fuwu">
                    <a href="<%=path %>/toRegister.action">
                        <div class="xszc"></div>
                        <p>新手注册</p>
                    </a>
                </div>
                <div class="fuwu">
                    <a href="<%=path %>/toBack.action">
                        <div class="zhmm"></div>
                        <p>找回密码</p>
                    </a>
                </div>
                <div class="fuwu">
                    <a href="<%=path %>/toLogin.action">
                        <div class="xgmm"></div>
                        <p>修改密码</p>
                    </a>
                </div>
                <div class="fuwu">
                    <a href="<%=path%>/pay/charge.action">
                        <div class="cz"></div>
                        <p>充值</p>
                    </a>
                </div>
                <div class="fuwu">
                    <a href="<%=path%>/pay/charge.action?type=1">
                        <div class="tx"></div>
                        <p>提现</p>
                    </a>
                </div>
                <div class="fuwu">
                    <a href="<%=path%>/product/list.action">
                        <div class="tz"></div>
                        <p>投资</p>
                    </a>
                </div>
            </div>
        </div>
        <div class="vcontent_3">
            <h2><a href="<%=path%>/commonQuestion/1/help.action">常见问题</a></h2>
            <div class="wtBox">
                <div class="wt">
                    <p>新手指南</p>
                    <ul>
                        <li><a href="<%=path%>/commonQuestion/1/help.action">认识正道</a></li>
                        <li><a href="<%=path%>/commonQuestion/1/help.action">安全保障</a></li>
                        <li><a href="<%=path%>/commonQuestion/2/help.action">新手指引</a></li>
                        <li><a href="<%=path%>/commonQuestion/3/help.action">平台费用</a></li>
                    </ul>
                </div>
                <div class="wt">
                    <p>投资指南</p>
                    <ul>
                        <li><a href="<%=path%>/investGuide/1/help.action">注册登录</a></li>
                        <li><a href="<%=path%>/investGuide/2/help.action">充值</a></li>
                        <li><a href="<%=path%>/investGuide/3/help.action">投资</a></li>
                        <li><a href="<%=path%>/investGuide/5/help.action">提现</a></li>
                    </ul>
                </div>
                <div class="wt">
                    <p>会员福利</p>
                    <ul>
                        <li><a href="<%=path%>/userWeal/1/help.action">优惠券</a></li>
                        <li><a href="<%=path%>/userWeal/2/help.action">正经值</a></li>
                        <li><a href="<%=path%>/userWeal/3/help.action">邀请好友</a></li>
                        <li><a href="<%=path%>/userWeal/4/help.action">每日签到</a></li>
                    </ul>
                </div>
                <div class="wt">
                    <p>银行存管：新浪支付+上海银行</p>
                    <ul>
                        <li><a href="<%=path%>/fundsDeposit/1/help.action">银行存管优势</a></li>
                        <li><a href="<%=path%>/fundsDeposit/2/help.action">银行存管影响</a></li>
                        <li><a href="<%=path%>/fundsDeposit/3/help.action">开户/激活疑问</a></li>
                    </ul>
                </div>
                <div class="wt">
                    <p>其他问题</p>
                    <ul>
                        <li><a href="<%=path%>/otherQuestion/1/help.action">名词解释</a></li>
                        <li><a href="<%=path%>/otherQuestion/2/help.action">正道金服客户端</a></li>
                        <li><a href="<%=path%>/otherQuestion/3/help.action">微信公众号</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="vcontent_4">
            <h2><a href="<%=path%>/contact.action">联系我们</a></h2>
            <div class="lxMe">
                <div class="lx">
                    <p>电话客服</p>
                    <ul>
                        <li><a href="javascript:">专业、高效解决您的问题</a></li>
                        <li><a href="javascript:">客服热线：400-690-9898</a></li>
                    </ul>
                </div>
                <div class="lx">
                    <p>微信客服</p>
                    <ul>
                        <li><a href="javascript:">扫一扫，立马关注</a></li>
                        <li><a href="javascript:" class="guanzhu">关注 <img src="<%= path%>/images/front/img/footer/code1.png" alt="" class="wxewm"></a></li>
                    </ul>
                </div>
                <div class="lx">
                    <p>建议留言</p>
                    <ul>
                        <li><a href="javascript:">你不满意的，我们都会改</a></li>
                        <li><a href="javascript:">客服邮箱：service@zdjfu.com</a></li>
                    </ul>
                </div>
                <div class="lx">
                    <p>在线客服</p>
                    <ul>
                        <li><a href="javascript:">在线客服为您解决问题</a></li>
                        <li><a href="http://zdjfu.udesk.cn/im_client/?web_plugin_id=24428" target="_blank" class="mslx">马上联系</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- content end-->


<!-- footer -->
<jsp:include page="../common/footer.jsp"></jsp:include>
<!-- footer end -->
<script src="<%=path %>/module/jquery/jquery.min.js"></script>
<script src="<%=path %>/module/sticky-header.js"></script>
<script src="<%=path %>/module/layui/layui.js"></script>
<script src="<%=path %>/js/front/help.js"></script>
<script type="text/javascript">
   	$('.header').stickMe({
		topOffset:100
	});
</script>
</body>
</html>