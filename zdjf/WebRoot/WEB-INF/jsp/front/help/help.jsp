<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String showType = (String)request.getAttribute("showType");
    String secMenu = (String)request.getAttribute("secMenu");
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
<input id="showType" type="hidden" value="<%= showType%>"/>
<input id="secMenu"  type="hidden" value="<%= secMenu%>"/>
<div class="help">
    <div class="hcontent">
        <div class="h_left">
            <ul>
                <li>
                    <p <%if(showType!=null && showType.equals("commonQuestion")){ %>class="active"<%} %>>常见问题</p>
                    <div <%if(showType!=null && showType.equals("commonQuestion")){ %>class="he_more"<%} %>
                         <%if(!showType.equals("commonQuestion")){ %>class="he_more hide"<%} %>>
                        <a href="javascript:" class="active">新手指南</a>
                        <a href="javascript:">新手指引</a>
                        <a href="javascript:">平台费用</a>
                    </div>
                </li>
                <li>
                    <p <%if(showType!=null && showType.equals("investGuide")){ %>class="active"<%} %>>投资指南</p>
                    <div <%if(showType!=null && showType.equals("investGuide")){ %>class="he_more"<%} %>
                         <%if(!showType.equals("investGuide")){ %>class="he_more hide"<%} %>>
                        <a href="javascript:" class="active">注册登录</a>
                        <a href="javascript:">充值</a>
                        <a href="javascript:">投资</a>
                        <a href="javascript:">回款</a>
                        <a href="javascript:">提现</a>
                    </div>
                </li>
                <li>
                    <p <%if(showType!=null && showType.equals("userWeal")){ %>class="active"<%} %>>会员福利</p>
                    <div <%if(showType!=null && showType.equals("userWeal")){ %>class="he_more"<%} %>
                         <%if(!showType.equals("userWeal")){ %>class="he_more hide"<%} %>>
                        <a href="javascript:" class="active">优惠券</a>
                        <a href="javascript:">正经值</a>
                        <a href="javascript:">邀请好友</a>
                        <a href="javascript:">每日签到</a>
                    </div>
                </li>
                <li>
                    <p <%if(showType!=null && showType.equals("fundsDeposit")){ %>class="active"<%} %>>资金存管</p>
                    <div <%if(showType!=null && showType.equals("fundsDeposit")){ %>class="he_more"<%} %>
                         <%if(!showType.equals("fundsDeposit")){ %>class="he_more hide"<%} %>>
                        <a href="javascript:" class="active">银行存管优势</a>
                        <a href="javascript:">银行存管影响</a>
                        <a href="javascript:">开户/激活疑问</a>
                    </div>
                </li>
                <li>
                    <p <%if(showType!=null && showType.equals("otherQuestion")){ %>class="active"<%} %>>其他问题</p>
                    <div <%if(showType!=null && showType.equals("otherQuestion")){ %>class="he_more"<%} %>
                         <%if(!showType.equals("otherQuestion")){ %>class="he_more hide"<%} %>>
                        <a href="javascript:" class="active">名词解释</a>
                        <a href="javascript:">正道金服客户端</a>
                        <a href="javascript:">微信公众号</a>
                    </div>
                </li>
            </ul>
        </div>
        <div class="h_right">
            <div class="right_tab newHand">
                <div class="new_guide">
                    <h3>新手指南</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >认识正道</a>
                                <div class="moretxt hide">
                                    <p><a href="<%=path%>/company/profile.action">www.zdjfu.com（公司简介）</a></p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">安全保障</a>
                                <div class="moretxt hide">
                                    <p><a href="<%=path%>/insurance.action">www.zdjfu.com（安全保障）</a></p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>新手指引</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:">新手注册有什么奖励</a>
                                <div class="moretxt hide">
                                    <p>新手注册立即得206元大礼包和一张0.8%加息券，礼包中各含一张20、58、128元现金券。完成相应三个任务，合计可获得688元红包券及0.8%，1.2%加息券各一张。完成新手任务注册、实名认证、绑定银行卡、首次投资等都可获得相应正经值。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">平台使用手册</a>
                                <div class="moretxt hide">
                                    <p><a href="<%= path%>/newHand/guide.action">http://www.zdjfu.com/newHand/guide.action</a></p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>投资费用</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:">平台充值需要收费吗</a>
                                <div class="moretxt hide">
                                    <p>正道金服的用户充值是不收取任何费用的，充值手续费由正道金服承担。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">平台提现需要收费吗</a>
                                <div class="moretxt hide">
                                    <p>为庆祝上海银行存管系统正式上线，平台特此推出“提现免手续费”活动以回馈广大用户。至下一新版本上线之前，正道金服所有理财投资人，申请提现无论金额大小均不收取任何手续费，提现手续费由正道金服垫付。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="right_tab touzi" style="display: none;">
                <div class="new_guide">
                    <h3>注册登录</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >如何注册正道金服？</a>
                                <div class="moretxt hide">
                                    <p>1、进入正道金服官网（www.zdjfu.com）首页，点击首页【注册】按钮；</p>
                                    <p>2、根据提示信息，填写您的手机号码、验证码、并设置密码等信息，点击【立即注册】；</p>
                                    <p>3、手机号一经注册，不可注销或修改。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">为何注册时手机收不到验证码？</a>
                                <div class="moretxt hide">
                                    <p>1、请检查您的手机号输入是否正确；</p>
                                    <p>2、若手机安装了拦截软件请查看拦截记录/垃圾短信，确认是否被拦截； </p>
                                    <p>3、短信运营商通道可能会出现阻塞情况，短信会出现延时或失败的情况，请耐心等候； </p>
                                    <p>4、若没有收到验证码短信，可以点击“语音验证码”接听语音验证电话； </p>
                                    <p>5、若仍然收不到验证码，请联系客服电话400-690-9898；</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">什么是实名认证？</a>
                                <div class="moretxt hide">
                                <p>实名认证是指注册用户进行真实身份验证校验，用户通过绑定银行卡来校对身份证号码、手机号码等信息，平台数据直接对接公安系统，未实名认证用户无法进行充值，也无法进行投资。</p>
                            </div>
                            </li>
                            <li>
                                <a href="javascript:">实名认证成功后能修改吗？可以申请注册几个实名认证的账号？</a>
                                <div class="moretxt hide">
                                    <p>实名认证成功后不能修改。每个人（同一身份证）仅可以实名认证一个账号。为确保用户账户资金的安全，请务必如实填写您的认证信息。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">设置登录密码有什么要求吗？</a>
                                <div class="moretxt hide">
                                <p>密码至少8位数字或英文字母，温馨提示，为保障账户更加安全，请设置大小写字母与数字组合的密码。</p>
                            </div>
                            </li>
                            <li>
                                <a href="javascript:">我忘了登录密码怎么办？</a>
                                <div class="moretxt hide">
                                    <p>打开登录页面，点击“忘记密码”，跳转到密码重置页面，成功验证您的手机号后即可重新设置登录密码；</p>
                                    <p>若仍无法找回，请发送邮件到客服邮箱（service@zdjfu.com），标题格式为“忘记密码+用户名”，邮件内容为“用户名+手机号+姓名+身份证号+手持身份证正面照片+手持身份证反面照片”。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">如何修改注册手机号？</a>
                                <div class="moretxt hide">
                                    <p>请发送邮件至客服邮箱（service@zdjfu.com），标题格式“变更手机号+姓名；邮件内容格式为“姓名+身份证号+注册手机号+手持身份证正面照片+手持身份证反面照片”，客服审核通过后将相关信息回复至您的邮箱。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">为什么会登录失败？</a>
                                <div class="moretxt hide">
                                    <p>1、请确认输入的用户名是否正确；</p>
                                    <p>2、请检查键盘大小写确认输入的密码是否正确；</p>
                                    <p>3、请确认输入的图形验证码是否正确；</p>
                                    <p>4、请检查网络环境是否正常； </p>
                                    <p>5、如您忘记密码，请找回密码 <a href="<%=path%>/toBack.action">https://www.zdjfu.com/user/forgetPass</a></p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>充值</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >如何进行充值？</a>
                                <div class="moretxt hide">
                                    <p>进入【我的财富】，点击【充值】按钮，进入充值页面，输入充值金额，选择对应银行和支付方式，点击【充值】按钮即可；</p>
                                    <p>温馨提醒： 充值前需完成开通银行存管</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">充值需要收费吗？</a>
                                <div class="moretxt hide">
                                    <p>正道金服的用户充值是不收取任何费用的，充值手续费由正道金服承担。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">可以使用信用卡充值吗？</a>
                                <div class="moretxt hide">
                                <p>正道金服严禁信用卡充值、套现等行为，一经发现将予冻结账户、封号并停止服务。</p>
                            </div>
                            </li>
                            <li>
                                <a href="javascript:">为何我的充值有限额？</a>
                                <div class="moretxt hide">
                                    <p>正道金服对充值金额未设置限额，各银行卡充值限额由银行设定，如您遇到金额受限，请具体联系您所使用的银行卡银行查询您的银行卡限额。网银充值目前无限额。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">充值到账时间需要多久？</a>
                                <div class="moretxt hide">
                                    <p>正常情况下，平台充值实时到账，若银行提示扣款但账户金额未增加，可能是同一时间段充值人数过多，请稍后刷新页面，如刷新后仍未到账，请联系客服：400-690-9898。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">为什么银行卡已扣款，但平台账户余额没有增加？</a>
                                <div class="moretxt hide">
                                    <p>因不同银行操作划账时间不同，可能导致实际到账时间有延迟。如果没有到账，建议用户过半个小时再查看余额；如果仍未到账，请致电客服：400-690-9898为您服务，提供订单号或手机号，为您查询对应的支付请求流水号，并为您核实原因。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">平台充值支持哪些银行及银行相应额度？——关于支持的银行卡与每日额度等参考：</a>
                                <div class="moretxt hide">
                                    <p><a href="http://bk.zjtghelp.com/a/yunyingzhuanqu/yinxingqudao/2015/1102/45.html" target="_blank">http://bk.zjtghelp.com/a/yunyingzhuanqu/yinxingqudao/2015/1102/45.html</a></p>
                                    <p>附：快捷类充值支持银行限额表</p>
                                    <table>
                                        <thead>
                                        <tr>
                                            <th>银行(借记卡)</th>
                                            <th>首次绑卡支付（bingding_pay）</th>
                                            <th>已绑卡支付（bingding_pay）</th>
                                            <th>是否支持单独验卡</th>
                                            <th>单笔最低支付限额</th>
                                            <th>备注</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>中国银行</td>
                                            <td>5W</td>
                                            <td>单笔5W/日50W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>农业银行</td>
                                            <td>2K</td>
                                            <td>单笔2K/1W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>建设银行</td>
                                            <td>5W</td>
                                            <td>单笔5W/日20W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>工商银行</td>
                                            <td>5W</td>
                                            <td>单笔5W/日5W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>招商银行</td>
                                            <td>5W</td>
                                            <td>单笔5W/日20W</td>
                                            <td>是</td>
                                            <td>1.00</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>中信银行</td>
                                            <td>5K</td>
                                            <td>单笔5K/日2W/月5W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>民生银行（暂不支持）</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>兴业银行</td>
                                            <td>5W</td>
                                            <td>单笔5W/日5W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>光大银行</td>
                                            <td>5W</td>
                                            <td>单笔5W/日10W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td>需要开通网银</td>
                                        </tr>
                                        <tr>
                                            <td>上海银行</td>
                                            <td>5k</td>
                                            <td>单笔5K/日1W/月2W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td>需要开通网银</td>
                                        </tr>
                                        <tr>
                                            <td>邮储银行</td>
                                            <td>5k</td>
                                            <td>单笔5K/日5K</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td>绑卡支付（或快捷支付）前需要开通银联在线支付：
                                                <a href="https://online.unionpay.com/static/open/" target="_blank">https://online.unionpay.com/static/open/</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>平安银行</td>
                                            <td>5W</td>
                                            <td>单笔5W/日5W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>浦发银行</td>
                                            <td>5W</td>
                                            <td>单笔5W/日5W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>交通银行</td>
                                            <td>9999元</td>
                                            <td>单笔9999元/日50W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>广发银行</td>
                                            <td>2W</td>
                                            <td>单笔5W/日2W/月20W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>华夏银行</td>
                                            <td>5K</td>
                                            <td>单笔5K/日1W</td>
                                            <td>是</td>
                                            <td>0.01</td>
                                            <td></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">正道金服支持哪些支付方式充值？</a>
                                <div class="moretxt hide">
                                <p>正道金服支持快捷支付、网银支付两种支付方式。</p>
                                <p>用户开通快捷支付后，可直接登录上海银行存管账户使用绑定的银行卡输入交易密码完成充值。</p>
                                <p>用户使用网银支付时，需进入到上海银行存管账户，登录到网上银行充值。</p>
                            </div>
                            </li>
                            <li>
                                <a href="javascript:">为什么会充值失败？</a>
                                <div class="moretxt hide">
                                <p>1、银行卡余额不足以支付充值金额：请更换银行卡充值</p>
                                <p>2、交易密码输入有误：请输入正确的交易密码</p>
                                <p>3、充值金额超过银行单日限额：请联系银行查询及更改支付额度</p>
                                <p>4、您的银行卡未开通网上银行：请前往银行柜台或通过银行官网进行开通</p>
                                <p>5、银行卡出现过期、挂失、作废等情况：请更换银行卡或将绑定银行状态恢复正常</p>
                                <p>以上方法如仍未能解决您的问题，请联系客服寻求帮助（服务热线：400-690-9898）</p>
                            </div>
                            </li>
                            <li>
                                <a href="javascript:">什么是绑定银行卡？如何绑定银行卡？</a>
                                <div class="moretxt hide">
                                    <p>绑定银行卡是指在正道金服注册后关联您的银行卡信息，只有绑定银行卡后才能进行充值、投资、支付、提现等操作；</p>
                                    <p>如需绑定银行卡，请进入账户设置-安全中心-银行卡管理，点击“绑定银行卡”，输入银行卡号、开户地址、银行预留手机号（如忘记请与银行客服确认）、获取验证码即可绑定成功。 请注意，所绑卡的持卡人信息（姓名、身份证号码）要与正道金服开户人信息一致，否则将不能进行银行卡绑定。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">如何解除、更换已绑定的银行卡？</a>
                                <div class="moretxt hide">
                                    <p>如您需要解除/更换已绑定的银行卡，请进入“银行卡管理”页面，确保账户可用余额=0，如账户可用余额>0时，请先申请提现，再解绑。</p>
                                    <p>解绑具体操作：进入账户设置-安全中心-银行卡管理-银行卡解绑。填写银行卡预留手机号码，获取验证码，正确填写后即可成功解绑。解绑成功后可重新绑定新银行卡。</p>
                                    <p>若还有其他疑问，请致电客服热线400-690-9898或通过在线客服咨询。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>投资</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >如何投资？</a>
                                <div class="moretxt hide">
                                    <p>点击【首页】项目列表或进入【理财项目】模块，查找您想要投资的项目，点击【立即抢购】，进入项目详情页后，在右侧投资区域输入【投资金额】，进入订单确认页面，如有红包、加息券或正经值请选择使用，点击【确认支付**元】，输入支付密码后，即可成功投资。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">投资前需注意点什么？</a>
                                <div class="moretxt hide">
                                    <p>1、投标前请认真阅读所投资的项目详细信息（包括借款金额，年化利率，借款期限等），以确定您所要投的标符合您的风险承受能力和所要求的投资回报率。</p>
                                    <p>2、 投标前请认真阅读借款协议，了解其中信息，并确知若发生逾期或毁约，自己所获得的保障</p>
                                    <p>3、投标前请确认债权单位（X元/份）和认购份数，确认无误后，再进行投资。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">利息从何时起开始计算？何时取得本息？</a>
                                <div class="moretxt hide">
                                    <p>利息从项目标注的“起息日”开始计算，即项目款项划转给借款人之后开始计息。不同项目类型有不同的还款方式，比如“按天计息，按月付息，到期还本”、“等额本息”、“到期一次性还本付息”的还款方式。项目成立后，投资人将按照“还款计划”收到回款利息和本金。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">投资金额有限制吗？</a>
                                <div class="moretxt hide">
                                <p>每个项目均有不同的起投金额和最大投资金额，请在项目详情页中查看，并选择自己需要投资的金额。</p>
                            </div>
                            </li>
                            <li>
                                <a href="javascript:">投资红包如何使用？</a>
                                <div class="moretxt hide">
                                    <p>红包使用方法如下：使用投资红包需要满足特定的投资金额、投资期限等条件才可使用，具体以红包使用规则为准。红包需要在投资页面手动选择使用。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">投资成功后是否可以取消或提前收回本金？</a>
                                <div class="moretxt hide">
                                    <p>正道金服平台投资后不能取消交易，暂时也不支持提前赎回本金。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">为什么我会投资失败？</a>
                                <div class="moretxt hide">
                                    <p>1、您的账户余额不足；</p>
                                    <p>2、项目太抢手了，在您投资支付之前已满额，导致您未成功投资；</p>
                                    <p>3、如出现其它情况请联系在线客服，或拨打客服热线400-690-9898。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>回款</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >正道金服的还款方式？</a>
                                <div class="moretxt hide">
                                <p>正道金服目前的还款方式步骤为：按日计息，按月付息，到期还本</p>
                            </div>
                            </li>
                            <li>
                                <a href="javascript:">项目到期后何时回款？</a>
                                <div class="moretxt hide">
                                    <p>平台回款是在项目到期日当天下午17：00左右，不区分节假日。具体以上海银行操作为准，不排除有特殊情况导致回款晚于该时间（特殊情况包括但不限于系统延迟、支付操作问题、网络故障、借款人还款时间过晚等）。</p>
                                    <p>   注：按照相关协议规定，只要在还款当天还清，均不属于逾期。如晚于该时间，请各位用户耐心等待。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">投资回款会有短信等通知吗？</a>
                                <div class="moretxt hide">
                                    <p>正道金服会在投资回款操作过程中为您发送短信和站内信提醒。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">借款项目出现逾期怎么办？</a>
                                <div class="moretxt hide">
                                    <p>（1）逾期赎回，债权项目借款人到期未能偿还借款均由该项目出借人先行赎回项目债权，并向投资人垫付债权收益权本息。</p>
                                    <p>（2）逾期代付，如果债权项目借款人到期未能偿还借款，出借人对该项目又未进行赎回的，均会有合作的第三方资产管理公司向投资人代付债权收益权本息，也就是说第三方资产管理公司将会对投资用户的债权进行购买，因此，投资用户的安全保障较高。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>提现</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >如何提现？</a>
                                <div class="moretxt hide">
                                    <p>登录正道金服账户，在“我的财富 - “提现”中输入您要提现的金额，确认提现银行卡，跳转上海银行页面核对所提现信息，输入交易密码即可提现成功。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">提现需要收费吗？</a>
                                <div class="moretxt hide">
                                    <p>1：提现金额≥1000元，均不收取任何手续费；</p>
                                    <p>2：提现金额＜1000元，则按1元/笔收取手续费；</p>
                                    <p>3：用户充值未投资的资金提现将收取其提现金额2‰+1元/笔的手续费；</p>
                                    <p>例：A用户充值10000元未进行投资需要提现，则其手续费为：（10000*2‰）+1元/笔=21元；</p>
                                    <p>注：当用户账户余额≤1元时，账户余额不可提现；您也可以使用10个正经值兑换一张免费提现券。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">申请提现后多久能到账？（银行存管后，提交申请直接提交到上海银行）</a>
                                <div class="moretxt hide">
                                    <p>上海银行在收到您的提现申请后，将于T+1个自然日内将资金转入您本人绑定的银行卡账户中。具体到账时间请参考：</p>
                                    <p>（1）平日的提现申请，银行当天处理， T+1个自然日到账。</p>
                                    <p>（2）法定节假日的提现申请，到账日统一以平台公告为准。</p>
                                    <p>（3）由于银行处理业务的时间不同，具体到账时间请以银行为准，如有疑问，请致电您的提现银行。</p>
                                    <%--<p>具体提现申请及到账时间对应表如下图：</p>
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>申请时间</th>
                                                <th>周一</th>
                                                <th>周二</th>
                                                <th>周三</th>
                                                <th>周四</th>
                                                <th>周五</th>
                                                <th>周六</th>
                                                <th>周日</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>到账时间</td>
                                            <td>周二</td>
                                            <td>周三</td>
                                            <td>周四</td>
                                            <td>周五</td>
                                            <td>周一</td>
                                            <td>周二</td>
                                            <td>周二</td>
                                        </tr>
                                        </tbody>
                                    </table>--%>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">最多能提现多少金额？</a>
                                <div class="moretxt hide">
                                    <p>上海银行存管上线后，个人单笔提现最高限额为5万元，单日最高限额为50万元。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">提现需注意哪些问题？</a>
                                <div class="moretxt hide">
                                    <p>为了保障用户资金的安全性，快捷充值规定每个用户只能绑定一张银行卡，且一旦该用户绑定了快捷卡，则此卡同时自动设置为默认唯一取现卡，原绑定的取现卡将被覆盖。且不能自主进行换绑，解绑，新增取现卡的操作。</p>
                                    <p>请确保您绑定的银行卡的开户人姓名和身份证号与您在平台实名认证的真实姓名保持一致，否则提现无法成功。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">为什么提现会失败？</a>
                                <div class="moretxt hide">
                                    <p>1、提现的银行卡在平台预留的银行信息有误</p>
                                    <p>2、银行卡出现过期、挂失、作废等情况</p>
                                    <p>如果出现其他提现失败的情况，请联系在线客服处理，或拨打客服热线100-690-9898。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">刚申请提现，可以撤销吗？</a>
                                <div class="moretxt hide">
                                    <p>由于提现申请是交给银行处理的，您成功提交提现申请后，是不支持撤销提现申请的。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">提现的银行卡丢失或者注销了，如何提取剩余资金？ </a>
                                <div class="moretxt hide">
                                    <p>如您需要解除/更换已绑定的银行卡，请进入“银行卡管理”页面，确保账户可用余额=0，如账户可用余额>0时，请先申请提现，再解绑。</p>
                                    <p>解绑具体操作：进入账户设置-安全中心-银行卡管理-银行卡解绑。填写银行卡预留手机号码，获取验证码，正确填写后即可成功解绑。解绑成功后可重新绑定新银行卡。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="right_tab vip" style="display: none;">
                <div class="new_guide">
                    <h3>优惠券</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >优惠券包含哪些？</a>
                                <div class="moretxt hide">
                                    <p>正道金服目前所发放的优惠券包含红包券和加息券两种。</p>
                                    <p>红包券在投资时可抵扣现金进行投资，例如投资1000元，使用50元红包券，则只需再投950元现金即可。 红包券只能用于投资，不能提现、转账和找零。</p>
                                    <p>加息券可使您的收益增加。例如，您投资的项目原年化利率为10%，使用0.5%的收益券之后，利率上涨为10.5%。</p>
                                    <p>两类优惠券可同时使用，均在订单确认页面进行选择。（同类优惠券不可叠加使用）</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">如何获得优惠券？</a>
                                <div class="moretxt hide">
                                    <p>红包券、加息券您可以通过参与正道金服推出的各项活动来获得。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">如何使用优惠券？</a>
                                <div class="moretxt hide">
                                    <p>选择您要投资的项目，输入投资金额，在“加息券”“红包券”及“正经值”的框中选择您想要使用的优惠券，点击“确认支付”即可；注意，红包券不得与正经值同时使用。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">优惠券使用规则是什么？</a>
                                <div class="moretxt hide">
                                    <p>每张优惠券都有其规定的起投金额、起投期限，具体使用规则详情可至“我的财富”-“我的优惠”查看</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">为什么我投资确认时没有可选的优惠券</a>
                                <div class="moretxt hide">
                                    <p>1、您没有红包券及加息券；</p>
                                    <p>2、您投资的金额或日期不满足红包券、加息券的使用要求；</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">同一个项目可以同时使用红包券和加息券吗</a>
                                <div class="moretxt hide">
                                    <p>可以，红包券可与加息券同时使用。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">优惠券可以转赠吗</a>
                                <div class="moretxt hide">
                                    <p>正道金服的优惠券暂时不支持转赠。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>正经值</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >什么是正经值</a>
                                <div class="moretxt hide">
                                    <p>正经值属于正道金服优惠积分，用户可通过每日签到、邀请好友投资及参加正道金服官方活动获得。正经值可在投资时抵扣现金，抵扣比例为1%，正经值不得与红包券同时使用。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">如何获得正经值</a>
                                <div class="moretxt hide">
                                    <p>正经值可通过参与正道金服推出的各项活动、邀请好友、每日签到等多种渠道获得。</p>
                                    <p>1、邀请好友：好友注册并投资，您可获得好友投资额2‰的正经值奖励。</p>
                                    <p>2、参与正道金服推出的各项活动，赢取正经值。例如：抢标活动。</p>
                                    <p>3、每日签到领正经值，周末签到、投资签到还可享受翻倍奖励。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">正经值的用途</a>
                                <div class="moretxt hide">
                                    <p>1、正经值可在投资时抵扣现金，抵扣比例为1%，正经值不得与红包券同时使用。</p>
                                    <p>2、正经值可兑换等额红包券，可用作投资抵扣现金。</p>
                                    <p>3、正经值可兑换加息券，可提升用户投资项目的年化收益</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>邀请好友</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >如何邀请好友</a>
                                <div class="moretxt hide">
                                    <p>1、用户可用电脑登录正道金服官网，进入“我的财富“-“邀请有礼”，点击复制推荐人的邀请链接发送给好友或分享至朋友圈等，好友通过该链接注册成功即算邀请成功。</p>
                                    <p>2、新用户在注册时，填写推荐人的手机号码或者邀请码，也可完成邀请。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">邀请好友可获得什么奖励</a>
                                <div class="moretxt hide">
                                    <p>（1）邀请好友注册并投资，您可获得好友投资额2‰的正经值奖励。正经值可用于投资抵现，抵扣比例为1%</p>
                                    <p>（2）好友首次投资时可额外获得一张20元的红包券。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>每日签到</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >每天可签到几次？</a>
                                <div class="moretxt hide">
                                <p>1、用户每日登录即有一次签到机会，签到后成功分享至朋友圈则签到机会+1，通过分享链接邀请好友成功注册则签到机会再+1。</p>
                            </div>
                            </li>
                            <li>
                                <a href="javascript:">签到可获得什么奖励？</a>
                                <div class="moretxt hide">
                                    <p>1、每天签到随机送1-6点正经值奖励。</p>
                                    <p>2、每月签到所获正经值累计排名前三名的用户将分别获得对应奖励，包含红包券，加息券等。</p>
                                    <p>具体签到奖励如下：</p>
                                    <table>
                                        <thead>
                                        <tr>
                                            <th>签到名次</th>
                                            <th>红包券</th>
                                            <th>加息券</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>第一名</td>
                                            <td>128元</td><td>1.5%</td>
                                        </tr>
                                        <tr>
                                            <td>第二名</td>
                                            <td>88元</td>
                                            <td>1.0%</td>
                                        </tr>
                                        <tr>
                                            <td>第三名</td>
                                            <td>58元</td>
                                            <td>0.8%</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">签到所获奖励可以翻倍吗？</a>
                                <div class="moretxt hide">
                                    <p>1、周末签到最高可获得2倍随机翻倍奖励。</p>
                                    <p>2、当日投资≥1000的用户，次日签到最高可获得3倍随机翻倍奖励。</p>
                                    <p>3、若周六周日投资，则次日签到按较高的倍数奖励。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="right_tab zijin" style="display: none;">
                <div class="new_guide">
                    <h3>银行存管优势</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >什么是银行存管</a>
                                <div class="moretxt hide">
                                    <p>银行存管是指按照有关法律、法规的规定，客户交易结算资金统一交由独立于平台的第三方存管机构来存管。第三方存管机构是指具备第三方存管资格的商业银行。存管银行按照法律、法规的要求，负责客户资金的存取与资金交收，平台交易操作保持不变。借款人、投资人和平台通过在银行方开设存管账户，来实现交易和资金的结算。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">为什么要进银行存管</a>
                                <div class="moretxt hide">
                                    <p>根据《关于促进互联网金融健康发展的指导意见》“（十四）客户资金第三方存管制度。除另有规定外，从业机构应当选择符合条件的银行业金融机构作为资金存管机构，对客户资金进行管理和监督，实现客户资金与从业机构自身资金分账管理。” </p>
                                    <p>2016年8月，监管部门发布的《网络借贷信息中介机构业务活动管理暂行办法》中明确要求P2P平台应当选择符合条件的银行业金融机构作为存管机构。为保障您的资金财产安全，实现真正的安全与合规化建设，正道金服与上海银行正式签约银行存管，系统上线工作准备进行中。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">为什么选择上海银行</a>
                                <div class="moretxt hide">
                                    <p>上海银行股份有限公司成立于1995年12月29日，总行位于上海，是上海证券交易所主板上市公司，股票代码601229。上海银行以“精品银行”为战略愿景，以“精诚至上，信义立行”为核心价值观，近年来通过推进专业化经营和精细化管理，着力在中小企业、财富管理和养老金融、金融市场、跨境金融、在线金融等领域培育和塑造经营特色，不断增强可持续发展能力。2016年在英国《银行家》“全球前1000家银行”排名中，按一级资本和总资产计算，上海银行分别位列全球银行业第91位和97位；多次被《亚洲银行家》杂志评为“中国最佳城市零售银行”。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">银行存管有什么优势</a>
                                <div class="moretxt hide">
                                    <p>（1）系统分账监管</p>
                                    <p>   正道金服接入上海银行存管系统后，将由上海银行对您的交易资金及平台自有运营资金进行分账监管，两者完全独立且相互隔离，平台无法触碰您的资金。</p>
                                    <p>（2）用户资金存管</p>
                                    <p>   您进行的充值、绑卡、提现等每一笔与资金有关的操作，均需通过上海银行资金存管账户，由上海银行对您的资金信息进行管理，避免您的资金出现被挪用风险。</p>
                                    <p>（3）用户授权操作</p>
                                    <p>   您需要开通上海银行存管账户，并在上海银行存管页面单独设立交易密码。在进行任何与资金相关操作时，系统会验证密码，在得到您的授权后，由银行根据交易指令进行资金划转。</p>
                                    <p>（4）交易真实有效</p>
                                    <p>   上海银行根据合同约定及您发出的交易指令，对交易流程进行管理并对所有资金流水进行存档记录，确保借贷双方的资金流转和债权关系清晰明确。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>银行存管影响</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >上海银行如何保障您的资金安全？</a>
                                <div class="moretxt hide">
                                    <p>接入存管后，您的交易资金将直接流向上海银行，由银行进行监管，平台无法触碰您的账户资金；您进行的任何充值、绑卡、提现等与资金相关的操作，均在上海银行存管页面进行，由银行对您的资金信息进行管理，资金及操作更加安全无忧。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">存管上线对我提现有什么影响？</a>
                                <div class="moretxt hide">
                                    <p>银行存管后，上海银行将在收到提现申请的当日受理，下一个自然日到账，即T+1。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>激活疑问</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >如何开通银行存管账户？</a>
                                <div class="moretxt hide">
                                    <p>新用户：新用户注册正道金服账户成功后，在进行任何充值、投资、提现操作时都会提示开通上海银行存管账户，存管账户开通需   用户提供真实姓名、身份证号、银行卡号、银行卡预留手机号码，并设置上海银行存管账户支付密码。</p>
                                    <p>老用户：存管上线后，未激活上海银行存管账户的老用户在登录正道金服网站或APP用户端后，进行投资、充值、提款等操作会提示去激活上海银行资金存管账户，按照流程提示依次操作，进行实名/绑卡操作，即可成功激活存管账户。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">为什么我的开户无法通过？</a>
                                <div class="moretxt hide">
                                    <p>开户过程中，由于银行存管与公安系统联网，将需要您提供实名身份信息，如实名身份信息不一致会导致无法通过。可能的原因有：</p>
                                    <p>1、曾经改过名字或名字中带有繁体字；</p>
                                    <p>2、做过户籍变动；</p>
                                    <p>3、军人转业、复员换的身份证；</p>
                                    <p>4、18周岁以下用户；</p>
                                    <p>5、银行卡非支持银行、输入卡号错误、被其他账户绑定；</p>
                                    <p>6、银行卡非您账户同名同证件的卡片；</p>
                                    <p>7、姓名、身份证、银行卡卡号、预留手机号任一不匹配。</p>
                                    <p>如非以上原因，仍开户不成功，请联系在线客服或拨打客服热线400-690-9898咨询客服人员，我们将为您核实。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">可以不开通银行存管账户吗？</a>
                                <div class="moretxt hide">
                                    <p>不能</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">开户成功后信息可以更改吗？</a>
                                <div class="moretxt hide">
                                    <p>为保障投资者账号安全，存管账户开户成功后信息将不能修改，每个人（同一身份证号码）仅可以认证一个正道金服账号。为确保用户账户资金的安全，请务必如实填写您的各项认证信息。如有特殊情况需处理请联系客服热线：400-690-9898。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="right_tab other" style="display: none;">
                <div class="new_guide">
                    <h3>名词解释</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >什么是快捷支付</a>
                                <div class="moretxt hide">
                                    <p>您通过手机或网站进行充值投资时，不需要跳转到银行页面进行支付，直接通过输入卡面信息，即可便捷、快速地完成支付。只需将上海银行存管账户关联您的储蓄卡，每次付款时只需输入汇付天下向您手机发送的动态支付码即可完成。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">支付密码</a>
                                <div class="moretxt hide">
                                    <p>支付密码是不同于登录密码的资金安全保护机制，特别用于保护用户的资产安全。每位用户需设置支付密码后，方可进行投资、充值、提现等操作。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">逾期</a>
                                <div class="moretxt hide">
                                    <p>指当借款人没有在约定还款日归还本息的，此笔借款即为逾期款项。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">年化收益率</a>
                                <div class="moretxt hide">
                                <p>是把当前收益率（日收益率、周收益率、月收益率）换算成年收益率来计算的，是一种理论收益率。</p>
                            </div>
                            </li>
                            <li>
                                <a href="javascript:">红包券</a>
                                <div class="moretxt hide">
                                    <p>红包券在投资时可抵扣现金进行投资，例如投资1000元，使用50元红包券，则只需再投950元现金即可。 红包券只能用于投资，不能提现、转账和找零。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">加息券</a>
                                <div class="moretxt hide">
                                    <p>加息券可使您的收益增加。例如，您投资的项目原年化利率为10%，使用0.5%的收益券之后，利率上涨为10.5%。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">正经值</a>
                                <div class="moretxt hide">
                                    <p>正经值属于正道金服优惠积分，用户可通过每日签到、邀请好友投资及参加正道金服官方活动获得。正经值可在投资时抵扣现金，抵扣比例为1%，正经值不得与优惠券同时使用。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">冻结金额</a>
                                <div class="moretxt hide">
                                    <p>冻结金额分为购买冻结和提现冻结。</p>
                                    <p>购买冻结：指您购买项目成功后，您的购买资金将会被冻结在上海银行存管账户，等到次日00:00时，银行会将这部分资金转给借款人，这部分资金将解除冻结，归入待收金额。</p>
                                    <p>提现冻结：指您提交提现申请后，这部分资金会被冻结，待提现审核通过后，这部分资金将解除冻结，由上海银行划拨至您绑定的银行卡。</p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">待收金额</a>
                                <div class="moretxt hide">
                                    <p>待收金额指投资人在平台投资成功后还未回款的本金和对应产生的利息，主要分为待收本金及待收利息。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>正道金服客户端</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >如何下载正道金服客户端</a>
                                <div class="moretxt hide">
                                    <p>1、点击下方链接可直接下载：</p>
                                    <p><a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.hz.zdjfu.application" target="_blank">   http://a.app.qq.com/o/simple.jsp?pkgname=com.hz.zdjfu.application</a></p>
                                    <p>2、打开应用商店，搜索“正道金服”，点击下载。</p>
                                    <p>3、扫描下方二维码可直接下载。</p>
                                    <p><img id="ewm_img" class="img " src="<%=path %>/images/front/img/footer/code2.png"></p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="new_guide" style="display: none;">
                    <h3>微信公众号</h3>
                    <div class="tent">
                        <ul>
                            <li>
                                <a href="javascript:" >如何关注正道金服微信公众号？</a>
                                <div class="moretxt hide">
                                    <p>1、搜索微信号：zdjfu161208 ，关注正道金服</p>
                                    <p>2、扫描下方二维码直接关注</p>
                                    <p><img id="ewm_img2" class="img " src="<%=path %>/images/front/img/footer/code1.png"></p>
                                </div>
                            </li>
                            <li>
                                <a href="javascript:">关注微信号有什么好处？</a>
                                <div class="moretxt hide">
                                    <p>1、联系微信客服处理相关问题 。</p>
                                    <p>2、查看我的账户信息，例如我的投资项目，优惠券，回款日期等。</p>
                                </div>
                            </li>
                        </ul>
                    </div>
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
</body>
</html>