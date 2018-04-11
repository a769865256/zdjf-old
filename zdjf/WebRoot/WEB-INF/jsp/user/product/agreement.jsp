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

 <title>正道金服（网）债权收益权转让协议-无律</title>
 <meta name="keywords" content="债权转让,互联网理财,P2P理财,投资理财,金融信息服务,正道金服">
 <meta name="description" content="正道金服是一家专业的第三方债权交易金融信息服务平台，运用成熟、严谨的风险控制评估机制，同互联网的便捷、透明、低成本联系起来，建立起了符合投资者需求的理财平台。">
 <meta name="copyright" content="版权所有 © 正道金服">

 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0">

 <style>
  .Section1{
   width: 940px;
   margin: 0 auto;
   border: 10px solid #dcdcdc;
   padding: 30px;
   border-radius: 10px;
   font-family: 'Microsoft Yahei';
   color: #333;
   line-height: 30px;
  }

  .letter-writer{
    font-size: 42px;
    color: #ccc;
    letter-spacing: 50px;
    margin: 10px 300px 0 -350px;
    width: 260px;
    height: 50px;
    line-height: 50px;
    float: right;
  }
 </style>

</head>

<body>
<div style="width: 940px;margin: 0 auto;border: 10px solid #dcdcdc;padding: 30px;border-radius: 10px;font-family: 'Microsoft Yahei';color: #333;line-height: 30px;">

 <p class="MsoNormal" align=center style='text-align:center;'><span
         style='font-size:22.0pt;'>正道金服（网）债权收益权转让协议</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >&nbsp;</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >甲方（出让方）：<u><span style="min-width: 255px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.lender.lenderType == 1 ? rsp.lender.name : rsp.lender.compName}
 </span></u></span
 ></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span>
${rsp.lender.lenderType == 2 ? "营业执照" : "身份证号"}：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.lender.lenderType == 1 ? rsp.lender.idcard : rsp.lender.compCode}

 </span></u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >电　　话：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.lender.phone}
 </span></u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >乙方（受让方）：<u><span style="min-width: 255px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.user.realName }
 </span></u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >身份证号：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.user.idcardNo }
 </span></u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >电　　话：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.user.phone }
 </span></u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >丙方（第三方服务机构）：<u>杭州云翳网络科技有限公司</u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >法定代表人：<u>柯瑞</u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >网站：<u><span lang=EN-US><a
         href="http://www.zdjfu.com" style="color: #333;">www.zdjfu.com</a> </span>正道金服（网）</u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >电话：<u><span lang=EN-US>400-690-9898</span></u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >地址：<u>杭州市西湖区西溪国际商务中心5幢909室</u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >鉴于：</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >1</span><span
 >、正道金服（网）（域名：<span lang=EN-US>www.zdjfu.com</span>）由丙方运营管理。甲乙双方均系正道金服（网）的注册会员。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >2</span><span
 >、甲方与基础债权债务人通过丙方合作线下平台的借贷居间撮合服务达成了借款合意，并在丙方合作线下平台
  <u><span>法务和风控人员</span></u>的全程监管和见证下，形成了本协议项下的基础债权。丙方合作线下平台
  <u><span>法务和风控人员</span></u>就该基础债权的真实性、合法性、有效性向<u>丙方</u>出具了
  <u><span>法律风控意见（经过律师见证的，出具《法律意见书》）</span></u>。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >根据《中华人民共和国合同法》、《中华人民共和国民法通则》等有关法律规定，甲乙丙三方本着诚实信用的基本原则，经平等友好协商，自愿就甲方通过正道金服（网）平台向乙方转让债权收益权的相关事宜达成如下协议：</span></p>
 <p>&nbsp;</p>
 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第一条 本协议名词释义</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >1</span><span
 >、“基础债权”是指本协议甲方作为出借人，通过丙方合作线下平台的借贷居间撮合服务与借款人达成借款合意，在
  <u><span>丙方法务和风控人员</span></u>的全程监管和见证下形成的并在本协议中转让的原合同债权。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >2</span><span
 >、“原合同”是指本协议甲方作为出借人，通过<u>丙方合作线下投融资服务平台</u>的居间服务与借款人达成借款合意后，就借款、担保等事项约定双方权利与义务的借款合同。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >3</span><span
 >、“债权收益权”是指基础债权的债权人与债务人一致同意赋予基础债权受让方的优先于基础债权债权人受领基础债权债务人归还借款本金和一定利息款项的权利。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >4</span><span
 >、“资金监管账户”是指甲方通过丙方平台在丙方合作的第三方支付机构或银行开立的资金管理账户，该账户由丙方与甲方共同监管。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第二条 基础债权信息</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >1</span><span
 >、甲方自愿将其所拥有的下列基础债权的全部或部分债权收益权对乙方进行转让：</span></p>
 <p>&nbsp;</p>

 <table class="MsoNormal"Table border=1 cellspacing=0 cellpadding=0
        style='border-collapse:collapse;border:none'>
  <tr>
   <td width=142 valign=top style='width:106.5pt;border:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >原合同编号：</span></p>
   </td>
   <td width=142 valign=top style='width:106.5pt;border:solid black 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
  ${rsp.product.debtCode }
  </span></p>
   </td>
   <td width=142 valign=top style='width:106.55pt;border:solid black 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >债权本金：</span></p>
   </td>
   <td width=142 valign=top style='width:106.55pt;border:solid black 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
  ${rsp.money}
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:106.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >债权形成时间：</span></p>
   </td>
   <td width=142 valign=top style='width:106.5pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
  <fmt:formatDate value="${rsp.product.startDate }" pattern="yyyy年MM月dd日"/>
  </span></p>
   </td>
   <td width=142 valign=top style='width:106.55pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >年化利率：</span></p>
   </td>
   <td width=142 valign=top style='width:106.55pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
  -
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:106.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >合同期限：</span></p>
   </td>
   <td width=142 valign=top style='width:210.5pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp; <fmt:formatDate value="${rsp.product.startDate}" pattern="yyyy年MM月dd日"/>至<fmt:formatDate value="${rsp.product.endDate}" pattern="yyyy年MM月dd日"/></span></p>
   </td>
   <td width=142 valign=top style='width:106.55pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >剩余合同期限：</span></p>
   </td>
   <td width=142 valign=top style='width:106.55pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp; ${rsp.endCount } 天</span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:106.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >债权担保物或财产保障信息：</span></p>
   </td>
   <td width=426 colspan=3 valign=top style='width:319.6pt;border-top:none;
  border-left:none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    ${rsp.productAttach.protectMsg }
   </td>
  </tr>
 </table>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >&nbsp;</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >2</span><span
 >、上述债权按日计息，按月付息，<span>
一年按<span lang=EN-US>360</span>天计息</span>。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >3</span><span
 >、基础债权形成的原始凭证在债权收益权获得全部实现之前，由<u><span>丙方法务部门</span></u>负责保管。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第三条 债权收益权的价值和转让价格</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >1</span><span
 >、甲方转让的债权收益权的价值为人民币
<span style="width: 120px; display: inline-block; text-align: center; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
 ${rsp.money+rsp.income  }
</span>元（包含债权收益权本金<u><span  style="width: 120px; text-align: center; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.money}
 </span></u>元和预期利息收益<u><span
         style="width: 120px; display: inline-block; text-align: center; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.income }
 </span></u>元，其中预期利息收益以基础债权合同剩余合同期限为计息天数，按年化<u><span
         style="width: 120px; display: inline-block; text-align: center; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.product.income } %
  </span></u>的利率计算）。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >2</span><span
 >、甲方自愿将上述价值的债权收益权以人民币<u><span
         style="width: 120px; display: inline-block; text-decoration: none; text-align: center; border-bottom: 1px #000 solid;" lang=EN-US>
  ${rsp.money}</span></u>的价格转让给乙方。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >3</span><span
 >、乙方同意按本条第二款约定的价格受让甲方上述转让的债权收益权。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第四条 债权收益权转让款的支付</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >1</span><span
 >、甲乙丙三方均一致同意乙方将债权收益权转让款支付至甲方通过丙方平台在第三方支付机构或银行开立的资金监管账户，该账户由丙方与甲方共同监管。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >2</span><span
 >、乙方按照正道金服（网）的规则将债权转让款支付至甲方通过丙方平台在第三方支付机构或银行开立的资金监管账户中，即视为乙方完成了该笔债权收益权转让的付款行为。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第五条 债权收益权的取得</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >乙方自本协议生效之日即取得本协议项下的债权收益权，有权按照本协议约定的条件受领基础债权的债务人归还的借款本金及相应利息。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第六条 债权收益权的实现</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >1</span><span
 >、基础债权债务人根据其与甲方签订的原合同向指定账户支付借款本息后，甲方应将不少于本协议已转让债权收益权对应的借款本息支付到资金监管账户绑定账号（基础债权债务人已直接到资金监管账户绑定账号的除外），并向乙方支付相应债权收益权本息。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >2</span><span
 >、基础债权债务人提前归还借款本息的，债权收益权利息收益实际计息天数应计算到基础债权债务人提前还款日，甲方应向乙方额外支付相当于五日（债权剩余期限不足五日的，以剩余期限为准）利息的提前还款补偿金，丙方有权以平台活动的形式发放。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >3</span><span
 >、基础债权债务人未按照原合同履行期限按时支付利息的，甲方应当代基础债权债务人向乙方支付相应债权收益权利息。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >4</span><span
 >、丙方对甲方向乙方支付相应债权收益权利息的行为负有监管的权利和义务，并对甲方的上述行为予以配合。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第七条 债权收益权的赎回</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >基础债权清偿期届满之日，基础债权债务人无力归还借款本息，则甲方应自行将不少于本协议已转让债权收益权对应本金和剩余利息的自有资金支付到资金监管账户绑定账号，并向乙方支付相应债权收益权本金和剩余利息，以赎回债权收益权。甲方向乙方支付债权收益权本金和剩余利息后，乙方在本协议项下购买的债权收益权转归甲方所有。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第八条 其他权利义务</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >1</span><span
 >、标的债权收益权的价值可能并非甲方对原债务人享有的全部基础债权价值，乙方对此充分理解和认可。而且乙方已经充分了解基础债权的全部情况，并且同意从甲方处受让债权收益权。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >2</span><span
 >、甲方保证本协议项下转让给乙方的债权收益权真实有效，并为甲方合法拥有，甲方对其拥有完全、有效的处分权。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >3</span><span
 >、乙方声明与保证其所用于受让标的债权收益权的资金来源合法，乙方是该资金的合法所有人，如果第三方对资金归属、合法性问题发生争议，由乙方自行负责解决。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >4</span><span
 >、因战争、动乱、自然灾害等不可抗力或国家法律政策变动、电信网络服务终止、黑客攻击等客观因素出现，导致协议内容延迟履行或不能履行，甲、乙、丙三方互不追究责任。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >5</span><span
 >、甲乙丙三方确认，本协议的签订 、生效和履行以不违反中国的法律法规为前提。如果本协议中的任何一条或多条违反现行的法律法规，则该条将被视为无效，但该无效条款并不影响本协议其他条</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >款的效力。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第九条 协议的变更</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >本协议的任何修改、补充均须以正道金服（网）平台电子文本形式做出。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第十条 争议解决</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >如果甲乙丙三方在本协议履行过程中发生任何争议，应友好协商解决；如协商不成，则须提交丙方所在地人民法院进行诉讼。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第十一条 协议的保管</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >甲乙双方一致同意委托丙方保管所有与本协议有关的书面文件或电子信息。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第十二条 协议的解释</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >本协议中所使用的定义，除在上下文中另有定义外，以正道金服（网）公布的《正道金服（网）网站定义与释义规则》中的定义及内容为准。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >第十三条 本协议的成立与生效</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
 >乙方按照“正道金服（网）”的规则，通过甲方的债权收益权（“标的债权收益权”）转让需求界面，点击
“立即投资”按钮，进入投资理财详细信息界面，填写完成乙方的投资理财信息，点击同意《正道金服（网）债权收益权转让协议范本》，形成相应订单，并在线完成债权收益权转让款支付后本协议即成立并立即生效。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent:23.25pt;
line-height:150%'><span lang=EN-US style='font-size:12.0pt;line-height:150%;
'>&nbsp;</span></p>

 <style>
  .user .phone{
   width:50%;
   line-height: 110%;
   float: left;
   padding-bottom: 15px;
  }
  .user .comp{
   width:50%;
   line-height: 110%;
   float: right;
   padding-bottom: 15px;
   position: relative;
  }
  .user .comp img{
   position: absolute;
   right: 145px;
   top: -30px;;
  }
  .user .comp .seal{
   color: #C4BC96;
   margin-left: 10px;
   display:inline-block;
  }
 </style>

 <div class="user">
  <div class="phone">
   <p>甲方：${rsp.lender.lenderType == 1 ? rsp.lender.name : rsp.lender.compName}</p>
   <p>日期：${rsp.create_time }</p>
  </div>
  <div class="comp">
   <p>第三方服务机构：杭州云翳网络科技有限公司</p>
   <p>日期：${rsp.create_time }<span class="seal">
  <c:if test="${type!=1 }">
   <%--（盖章）--%>
   <img src="${ selfSite}/res/comm/images/public/zhang.png" width="180px" height="180px">
  </c:if>
  </span></p>
   <c:if test="${type==1 }">
    <img src="${ selfSite}/res/comm/images/public/zhang.png" width="180px" height="180px">
   </c:if>
  </div>
  <div class="phone">
   <p>乙方：${rsp.user.realName }</p>
   <p>日期：${rsp.create_time }</p>
  </div>
<c:if test="${type!=1 }">
  <div style="font-size: 42px;color: #ccc;letter-spacing: 50px;margin: 10px 300px 0 -350px;width: 260px;height: 50px;line-height: 50px;float: right;">范本</div>
</c:if>
 </div>


<span lang=EN-US style='font-size:12.0pt;line-height:150%;font-family:"Calibri","sans-serif"'><br
        clear=all style='page-break-before:always'>
</span>

 <p class="MsoNormal" style='line-height:150%'><span style='
line-height:150%;'>附件一</span></p>

 <p class="MsoNormal" style='line-height:150%'><span style='
line-height:150%;'>转让债权收益权付息时间金额表：</span></p>

 <table class="MsoNormal"Table border=1 cellspacing=0 cellpadding=0
        style='border-collapse:collapse;border:none'>
  <tr>
   <td width=140 valign=top style='width:104.65pt;border:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=center style='text-align:center;line-height:150%'><span
            style='line-height:150%;'>付息序次数</span></p>
   </td>
   <td width=210 valign=top style='width:157.8pt;border:solid black 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=center style='text-align:center;line-height:150%'><span
            style='line-height:150%;'>付息时间</span></p>
   </td>
   <td width=218 valign=top style='width:163.65pt;border:solid black 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=center style='text-align:center;line-height:150%'><span
            style='line-height:150%;'>付息金额</span></p>
   </td>
  </tr>

  <c:forEach var="item"  varStatus="i"
             items="${rsp.tempMap }">
   <tr>
    <td width=140 valign=top style='width:104.65pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
     <p class="MsoNormal" align=center style='text-align:center;line-height:150%'><span
             style='line-height:150%;'>
  第${i.index +1}次</span></p>
    </td>
    <td width=210 valign=top style='width:157.8pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
     <p class="MsoNormal" align=center style='text-align:center;line-height:150%'><span
             lang=EN-US style='line-height:150%'>&nbsp;${item.key } </span></p>
    </td>
    <td width=218 valign=top style='width:163.65pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
     <p class="MsoNormal" align=center style='text-align:center;line-height:150%'><span
             lang=EN-US style='line-height:150%'>&nbsp;${item.value }</span></p>
    </td>
   </tr>
  </c:forEach>
 </table>

 <p class="MsoNormal" style='line-height:150%'><span style='
line-height:150%;'>本金包含在最后一次付息金额中，并于最后一次付息时一起支付与乙方。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >&nbsp;</span></p>

</div>
</body>
</html>
