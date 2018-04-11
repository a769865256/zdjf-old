<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>安全中心-我的财富-专业透明的互联网理财平台，注册即送288</title>
    <meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
    <meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
    <meta name="copyright" content="版权所有 © 正道金服">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=1200"/>
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/center/css/setPass.css">
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
</head>

<body>

<jsp:include page="../comm/header.jsp"></jsp:include>
<div class="body">
    <jsp:include page="center_nav.jsp"></jsp:include>

    <!-- 内容 -->
    <div class="body-right">

        <p class="title">安全中心</p>
        <div class="con">
            <!-- pass -->
            <div class="line">
                <span class="title"><i></i>登录密码</span>
                <span class="tip">登录正道金服账户时需要输入的密码</span>
                <a href="javascript: void(0)" class="alt set">修改</a>
            </div>
            <c:if test="${rsp.userInfo.realNameAuth == 1}">
                <!-- 开通成功 -->
                <div class="line">
                    <span class="title"><i></i>实名认证</span>
                    <span class="tip">汇付天下 ：${chinapnrCustPrefix} ${rsp.userInfo.uid }&nbsp;&nbsp;&nbsp;${rsp.userInfo.realName }， ${rsp.userInfo.idcardNo }</span>
                    <c:if test="${1 == 2}">
                        <a href="${rsp.chinapnrLoginUrl}" target="_blank" class="hide">查看</a>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${rsp.userInfo.realNameAuth == 2}">
                <!-- 处理中 -->
                <div class="line no-pass">
                    <span class="title"><i></i>实名认证</span>
                    <span class="tip">开通汇付天下资金托管账户，保障资金和交易安全</span>
                    <span class="settings">设置中</span>
                </div>
            </c:if>
            <c:if test="${rsp.userInfo.realNameAuth == -1}">
                <!-- 开通失败 -->
                <div class="line no-pass">
                    <span class="title"><i></i>实名认证</span>
                    <span class="tip">开通失败:${rsp.userInfo.remark}</span>
                    <!--未提交审核显示-->
                    <a href="${selfSite}/zdjf/chinapnr/req/register" class="set-up btn-orange" target="_blank">立即设置</a>
                </div>
            </c:if>
            <c:if test="${rsp.userInfo.realNameAuth == null || rsp.userInfo.realNameAuth == -2}">
                <!-- 未开通 -->
                <div class="line no-pass">
                    <span class="title"><i></i>实名认证</span>
                    <span class="tip">开通汇付天下资金托管账户，保障资金和交易安全</span>
                    <a href="${selfSite}/zdjf/chinapnr/req/register" class="set-up btn-orange" target="_blank">立即设置</a>
                </div>
            </c:if>

            <c:if test="${rsp.cashCardNo != null && rsp.cashCardNo != ''}">
                <!-- 已绑定提现银行卡 -->
                <div class="line">
                    <span class="title"><i></i>提现银行卡</span>
                    <span class="tip">${rsp.cashCardNo }</span>
                    <a href="${selfSite}/zdjf/center/banks">管理</a>
                </div>
            </c:if>
            <c:if test="${rsp.cashCardNo == null || rsp.cashCardNo == ''}">
                <!-- 无银行卡状态 -->
                <div class="line no-pass">
                    <span class="title"><i></i>提现银行卡</span>
                    <span class="tip">绑定银行卡后可用于提现</span>
                    <a href="${selfSite}/zdjf/center/banks" class="set-btn btn-orange">管理</a>
                </div>
            </c:if>



        </div>
    </div>
</div>
<!-- 密码设置 -->
<div class="alert change-password hide">
    <div class="alert-darkbg J_alertClose"></div>
    <div class="eject" id="setpass-box">
        <div class="title gray-bg"><span>修改登录密码</span><i class="icon-close J_alertClose">×</i></div>
        <div class="content">
            <div class="setlogin">
                <p><span class="name">当前密码</span><input type="password" placeholder="原始登录密码" /><span class="error-tip text-red J_error-Tip"></span></p>
                <p><span class="name">新密码</span><input type="password" placeholder="新登录密码" /><span class="error-tip text-red J_error-Tip"></span></p>
                <p><span class="name">确认新密码</span><input type="password" placeholder="重输新登录密码" /><span class="error-tip text-red J_error-Tip"></span></p>
            </div>
            <div class="button">
                <input type="button" class="confirm btn-red" value="确定">
            </div>
        </div>
    </div>
</div>
<div id="alert" style="z-index:-1;"></div>

<!--开通托管提示弹窗-->
<div class="alert alert-tip hide">
    <div class="alert-darkbg"></div>
    <div class="eject">
        <div class="title"></div>
        <div class="content">
            <p class="tip-title">账户实名认证</p>
            <p class="con">请在新打开的汇付天下页面完成实名认证</p>
            <p>
                <a href="javascript:void(0);" class="btn-red JRegComplete">已完成</a>
                <a href="javascript:void(0);" class="btn-red-border JRegError">遇到问题未完成</a>
            </p>
        </div>
    </div>
</div>

<jsp:include page="../comm/footer.jsp"></jsp:include>
<jsp:include page="../comm/helper.jsp"></jsp:include>
</body>

<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
<script type="text/javascript" src="${selfSite}/zdjf/res/center/js/safe.js"></script>
</html>
