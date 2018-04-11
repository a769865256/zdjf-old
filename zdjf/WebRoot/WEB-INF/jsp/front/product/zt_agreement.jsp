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
         style='font-size:22.0pt;'>借款合同</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >&nbsp;</span></p>
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;font-weight: 600;'><span
 >合同编号：<u><span style="min-width: 255px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.lender_type == 1 ? lender.name : lender.comp_name}
 </span></u></span
 ></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;font-weight: 600;'><span
 >甲方（借款人）：<u><span style="min-width: 255px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.lender_type == 1 ? lender.name : lender.comp_name}
 </span></u></span
 ></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
 >证件类型：<u><span style="min-width: 255px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.lender_type == 1 ? lender.name : lender.comp_name}
 </span></u></span
 ></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span>
${lender.lender_type == 2 ? "营业执照" : "身份证号"}：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.lender_type == 1 ? lender.idcard : lender.comp_code}

 </span></u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
 >联系地址：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.phone}
 </span></u></span></p>

  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
 >联系方式：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.phone}
 </span></u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;font-weight: 600;'><span
 >乙方（出借人）：<u><span style="min-width: 255px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${user.real_name }
 </span></u></span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
 >证件类型：<u><span style="min-width: 255px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.lender_type == 1 ? lender.name : lender.comp_name}
 </span></u></span
 ></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span>
${lender.lender_type == 2 ? "营业执照" : "身份证号"}：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.lender_type == 1 ? lender.idcard : lender.comp_code}

 </span></u></span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
 >联系地址：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.phone}
 </span></u></span></p>

  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
 >联系方式：<u><span style="min-width: 303px; display: inline-block; text-decoration: none; border-bottom: 1px #000 solid;" lang=EN-US>
  ${lender.phone}
 </span></u></span></p>


 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US ></span><span
 >甲方向乙方申请借款，乙方同意向甲方出借款项，由
  <u><span>某某</span></u>有限公司（以下简称“
  <u><span>某某</span></u>”）及其法定代表人
  <u><span>某某</span></u>为上述借款及违约责任向乙方提供连带责任保证，为本合同项下的借款担保人，且甲方、乙方均为具有完全民事权利能力和完全民事行为能力的自然人或法人，根据中华人民共和国有关法律法规，经甲方、乙方协商一致订立本合同。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
 >平台服务方指杭州云翳网络科技有限公司运营管理的“正道金服”平台（包括网站
 <u><span>https://www.zdjfu.com/及手机客户端/微信端</span></u>
 ，以下简称“正道金服”）。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
 >特别提示：
 <u><span>某某</span></u>
 及其法定代表人
 <u><span>某某</span></u>
 非本借款合同的当事人，
 <u><span>某某</span></u>
 及其法定代表人
 <u><span>某某</span></u>
 对乙方承担担保责任以本借款合同及其项下担保合同中约定的权利义务为准。
 </span></p>

 <p>&nbsp;</p>
 <p class="MsoNormal" align=left style='text-align:left;font-weight: 600;'><span
 >第一部分：借款明细</span></p>
<table class="MsoNormal"Table border="1" cellspacing="0" cellpadding="0" style='border-collapse:collapse;border:none;margin: 0 auto;'>
  <tr>
   <td width=142 valign=top style='width:201.5pt;border:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >借款本金金额：</span></p>
   </td>
   <td width=142 valign=top style='width:201.5pt;border:solid black 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
  人民币（小写）<u><span>某某</span></u>元
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:447.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >借款人：</span></p>
   </td>
   <td width=142 valign=top style='width:447.5pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
  	某某
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:447.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >出借人：</span></p>
   </td>
   <td width=142 valign=top style='width:106.55pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp; 某某</span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:447.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >本金金额大写：</span></p>
   </td>
   <td width=426 colspan="3" valign=top style='width:319.6pt;border-top:none;
  border-left:none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
	某某
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:447.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >借款利率：</span></p>
   </td>
   <td width=426 colspan="3" valign=top style='width:319.6pt;border-top:none;
  border-left:none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
	某某
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:447.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >借款期限：</span></p>
   </td>
   <td width=426 colspan="3" valign=top style='width:319.6pt;border-top:none;
  border-left:none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
	某某
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:447.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >借款用途：</span></p>
   </td>
   <td width=426 colspan="3" valign=top style='width:319.6pt;border-top:none;
  border-left:none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
	某某
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:447.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >还款方式：</span></p>
   </td>
   <td width=426 colspan="3" valign=top style='width:319.6pt;border-top:none;
  border-left:none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
	某某
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:447.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >借款担保人：</span></p>
   </td>
   <td width=426 colspan="3" valign=top style='width:319.6pt;border-top:none;
  border-left:none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
	某某
  </span></p>
   </td>
  </tr>
  <tr>
   <td width=142 valign=top style='width:447.5pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
    <p class="MsoNormal" align=left style='text-align:left;'><span
    >担保方式：</span></p>
   </td>
   <td width=426 colspan="3" valign=top style='width:319.6pt;border-top:none;
  border-left:none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class="MsoNormal" align=left style='text-align:left;'><span
            lang=EN-US >&nbsp;
 最高额担保合同
  </span></p>
   </td>
  </tr>
 </table>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 19em;'><span
         lang=EN-US >注：1</span><span
 >、日利率=年利率÷360，月利率=年利率÷12。
  </span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 21em;'><span
         lang=EN-US >2</span><span
 >、借款期限从本合同约定的借款发放成功之日起算。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;font-weight: 600;'><span
 >第二部分：借款通用条款</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >一</span><span
 >、 释义</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >.《借款合同》（“本合同”）由两部分组成：第一部分为“借款明细”，该明细中所列借款利率等内容会根据第二部分的相关内容做相应的调整；第二部分为“借款通用条款”。</span></p>


 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >2</span><span
 >.甲方是指“借款明细”中列明的借款人，是符合中华人民共和国法律（“中国法律”，仅为本合同之目的，不包括香港特别行政区、澳门特别行政区和台湾省的法律法规）规定的具有完全民事权利能力和民事行为能力，独立行使和承担本合同项下权利义务的法人或其他组织；或者是符合中国法律规定的具有完全民事权利能力和民事行为能力，独立承担本合同项下权利义务的自然人。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >3</span><span
 >.乙方是指“借款明细”中列明的出借人，为正道金服平台的注册用户，是符合中华人民共和国法律规定的具有完全民事权利能力和民事行为能力，独立行使和承担本合同项下权利义务的自然人。在特定情况下，债权转让后的乙方可能是符合中国法律规定的企业法人或其他机构。乙方不可撤销地授权正道金服平台将其信息，包括但不限于姓名/名称、有效证件号码等，为本合同项下之目的提供给与本合同项下借款相关的担保人。</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >4</span><span
 >.<u><span>某某</span></u>是指与正道金服平台有战略合作关系的、具有履约保证能力的公司，为符合中国法律规定的具有完全民事权利能力和民事行为能力，独立行使和承担担保合同项下权利义务的法人。</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >5</span><span
 >.<u><span>某某</span></u>是指与正道金服平台有战略合作关系的、具有履约保证能力的自然人，为符合中国法律规定的具有完全民事权利能力和民事行为能力，独立承担担保合同项下权利义务的自然人。</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >6</span><span
 >.借款金额是指“借款明细”中列明的借款本金金额。借款币种为人民币。</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >7</span><span
 >.借款利率是指“借款明细”中列明的借款利率。</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >8</span><span
 >.借款用途是指“借款明细”中列明的借款用途。</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >9</span><span
 >.借款期限是指自借款放款日起至最终到期日止的期间。</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >10</span><span
 >.借款发放成功是指乙方通过正道金服平台以网络在线点击确认、勾选同意或类似的方式接受本合同后，即不可撤销地授权正道金服平台或正道金服平台委托的第三方将“借款明细”中列明的借款本金金额的资金由乙方的正道金服用户名项下账户（“乙方正道金服账户”）划转至甲方用户名项下正道金服账户之时，即视为借款发放成功。</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >11</span><span
 >.还款方式是指“借款明细”中列明的还款方式。</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >12</span><span
 >.本合同项下借款的还款计算公式如下：</span><br/><span style="text-indent: 3em;display: inline-block;"
 >借款利息=借款本金X借款天数X年利率÷360</span><br/><span style="text-indent: 3em;display: inline-block;"
 >还款总额=借款本金+借款利息</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >13</span><span
 >.工作日是指正道金服平台的正常营业日（不包括中国的法定公休日和节假日）。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >二</span><span
 >、 权利与义务</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >、甲方的权利与义务：</span></p>
 
<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（1）</span><span
 >甲方有权要求乙方按本合同约定发放借款；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（2）</span><span
 >甲方有权要求乙方、担保人对甲方提供的有关其还款能力的资料予以保密，但乙方、担保人为本合同之目的或办理征信相关之目的披露给平台方及其委托的第三方、征信机构或法律、行政法规另有规定的除外；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（3）</span><span
 >甲方应按乙方、担保人要求提供有关包括但不限于资质证明、还款能力等资料，并保证资料的合法性、真实性、完整性和有效性；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（4）</span><span
 >甲方应办理银行卡，以便甲方通过此卡接受借款；乙方委托担保人在借款到期时代表乙方向甲方行使催款权利，同时授权担保人代收甲方还款的借款利息及违约金；乙方直接负责收取借款本金及相应利息。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（5）</span><span
 >乙方按本合同约定发放借款后，甲方应按照本合同约定的期限归还全部借款本息；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（6）</span><span
 >甲方应按本合同约定用途使用借款，未经乙方书面同意甲方不得将借款挪作他用；甲方不得将借款用于有价证券、期货、私募基金、股权等投资或国家明令禁止或限制的经营活动，并自觉接受乙方或其委托的第三方对本合同项下借款使用情况的调查、了解及监督；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（7）</span><span
 >甲方应提供在所有网络借贷信息中介机构未偿还借款信息，不得同时通过多个网络借贷信息中介机构，或者通过变换项目名称、对项目内容进行非实质性变更等方式，就同一借款项目进行重复借款；不得通过故意变换身份、虚构借款、夸大借款项目收益前景等形式的欺诈借款；不得在正道金服以外的公开场所发布同一借款信息；不得从事法律法规和网络借贷有关监管规定禁止从事的其他活动。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（8）</span><span
 >甲方应当积极配合乙方对其经营事项、经济状况的调查、了解及监督，并有义务向乙方提供相关资信资料；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（9）</span><span
 >发生变更住所、通讯地址、经营事项、工作单位或甲方银行账户等事项时，甲方应于有关事项变更后三日内书面通知乙方、担保人；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（10）</span><span
 >在借款全部清偿前，甲方如为他人债务提供担保，可能影响自身债务清偿能力的，甲方应当提前书面通知乙方并征得乙方同意；发生对其履行本合同项下还款义务产生不利影响的其他事件，包括但不限于财务状况恶化、陷入经济纠纷、辞（退）职、开除、除名、解雇、重大违纪事件时，甲方应于事件发生后三日内书面通知乙方、担保人；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（11）</span><span
 >甲方承担本合同项下有关费用的支出，包括但不限于用于公证、鉴定、评估、登记、快递、律师费等事项的费用；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（12）</span><span
 >正道金服平台方有权就本合同所提供的服务向甲方收取服务费，上述费用的收取方式由甲方和正道金服平台另行约定；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（13）</span><span
 >甲方应归还的上述款项统称为“应付款项”。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >2</span><span
 >、乙方的权利与义务：</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（1）</span><span
 >乙方同意本人以网络页面点击确认的方式签订本协议，并不得以任何理由拒绝履行本协议项下的义务。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（2）</span><span
 >乙方有权要求甲方提供与本合同项下借款相关的全部资料；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（3）</span><span
 >乙方应依本合同约定按时足额向甲方发放借款(因甲方原因造成迟延的除外);</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（4）</span><span
 >乙方保证其所用于出借的资金来源合法，乙方是该资金的合法所有人，如果第三人对资金归属、合法性问题发生争议，由乙方负责解决并自行承担责任。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（5）</span><span
 >乙方有权依本合同约定或法律规定，负责收取甲方应偿付的借款本金及相应利息，同时乙方授权担保人代收甲方应偿付的借款利息、罚息、违约金及所有其他应付费用；乙方有权了解、核实有关甲方身份、还款能力等，有权要求甲方提供相关文件资料；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（6）</span><span
 >乙方应对甲方提供的资料予以保密，但乙方为本合同之目的或办理个人征信之目的披露给平台及其委托的第三方、个人征信机构或法律、行政法规另有规定的除外；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（7）</span><span
 >乙方有权自行或委托第三方对借款的使用情况进行监督；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（8）</span><span
 >甲方的资信情况或还款能力出现重大变化（包括但不限于经营状况发生重大变化、拖欠其他债务），足以影响其偿债能力或使其已经不再符合乙方借款条件的，乙方或其委托的第三方有权以书面形式要求借款提前到期；</span></p>
  
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >3</span><span
 >、担保人的权利与义务：</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（1）</span><span
 >担保人有权了解、核实有关甲方的资质、还款能力、财务等状况，有权要求甲方提供相关文件资料；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（2）</span><span
 >担保人应对甲方提供的有关资料予以保密，但担保人为本合同之目的或办理征信之目的披露给乙方、平台及其委托的第三方、征信机构或法律、行政法规另有规定的除外；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（3）</span><span
 >担保人应当依照担保合同的约定及时向乙方履行担保义务，如甲方不能按照本合同的约定及时还款的，则由担保人依照担保合同条款及时进行相应的代偿。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >三</span><span
 >、 逾期</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >、逾期：</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（1）</span><span
 >还款日<u><span>16</span></u>点前，甲方未足额支付应付款项的，则视为逾期。
如借款人在还款日已经向银行及第三方支付系统申请付款且账户资金足额的，但由于银行及第三方支付系统的原因，导致乙方的本金及利息的实际到账时间晚于还款日的，不视为甲方逾期，但是甲方应当按照借款利率补偿乙方相应的利息。
</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（2）</span><span
 >甲方未按照本合同约定归还借款的本金和利息的，从违约之日起按照本金的万分之七每天计算罚息，并计算复利。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（3）</span><span
 >逾期款项中的利息、逾期罚息计算复利。若甲方的还款金额低于债务总额的，按下列清偿顺序清偿：</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >1）</span><span
 >实现债权的费用（包括但不限于律师费、催收费、诉讼费等）；</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >2）</span><span
 >违约金、罚息；</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >3）</span><span
 >利息；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >4）</span><span
 >借款本金。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（4）</span><span
 >甲方不可撤销地授权正道金服平台或正道金服平台委托的第三方在甲方未能按时足额支付应付款项的情况下，在甲方全部到期应付款项的范围内，随时划扣甲方正道金服账户或其指定银行账户中的资金用于归还甲方到期应付款项，该等额划扣无需甲方另行同意。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（5）</span><span
 >提前还款</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >1）</span><span
 >甲方在借款日期开始3天后方能申请提前还款，申请提前还款应当以书面方式向乙方提出，并且在申请的提前还款日付清全部本息。</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >2）</span><span
 >甲方提前还款的，借款利息依照原合同约定用款天数计算，不因提前还款而减少，借款本息按照借款人提前还款当日结算给乙方。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >四</span><span
 >、 借款保险保证条款</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >.保证方式：为确保甲方履行还款义务，保证人同意按照担保合同项下条款的约定，为甲方的上述债务及违约责任承担连带保证责任。</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >2</span><span
 >.保证范围：保证人的保证范围为借款本金、利息及其他所有应付款项。</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >3</span><span
 >.保证期间：见担保合同。</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >4</span><span
 >.若甲方未按本合同约定履行偿付债务本息和相关费用的义务，乙方有权直接向担保人追偿，并委托正道金服平台方向担保人发出代偿通知。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >五</span><span
 >、 担保追偿的委托授权</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >鉴于乙方为分布不同地区的自然人，故乙方在此不可撤销地授权正道金服平台的运营方杭州云翳网络科技有限公司作为其担保追偿的代理人。当担保追偿时由杭州云翳网络科技有限公司向担保人提出担保追偿申请并提交相关资料。同时有权在不损害乙方利益的前提下，与担保人就担保的有关事项代表乙方达成相关协议。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >2</span><span
 >.杭州云翳网络科技有限公司在收到担保人代偿的借款本息及其他所有应付款项后应当及时将所有的款项及时支付给乙方，不得挪用或延迟支付。乙方收到款项后应当视为甲方已经支付了相应款项，担保人支付相应款项后有权向甲方进行追偿，但担保人与甲方的债权债务关系与乙方无关。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >六</span><span
 >、 违约责任</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >本合同各方均应履行本合同所约定的义务。任何一方不履行或不完全履行本合同所约定义务的，应当依法承担违约责任并承担相关费用，包括但不限于诉讼费、财产保全费、交通费、律师费（按《浙江省物价局浙江省司法厅关于完善律师和基层法律服务收费的通知》  浙价服〔2015〕203号的标准收费）、执行费、评估费、拍卖费、保管费、过户费、催收费等费用。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >2</span><span
 >.发生下列任何一项或几项情形的，视为甲方违约：</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（1）</span><span
 >甲方不履行或不完全履行任何本合同所约定的甲方义务的；
</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（2）</span><span
 >甲方违反其在本合同所做的任何承诺和保证的；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（3）</span><span
 >甲方的任何财产遭受没收、征用、查封、扣押、冻结等可能影响其履约能力的不利事件，且不能及时提供有效补救措施的；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（4）</span><span
 >甲方的财务状况出现其他影响其履约能力的不利变化，且不能及时提供有效补救措施的。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >3</span><span
 >.若甲方违约或根据乙方合理判断甲方可能发生违约事件的，乙方（委托正道金服平台）有权采取下列任何一项或几项救济措施：</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（1）</span><span
 >立即暂缓、取消发放全部或部分借款；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（2）</span><span
 >宣布已发放借款全部提前到期，甲方应立即偿还所有应付款项；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（3）</span><span
 >解除本合同；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（4）</span><span
 >采取法律、法规以及本合同约定的其他救济措施。</span></p>
  
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >4</span><span
 >.因甲方违约，造成正道金服平台方暂停借款信息发布的，给乙方造成的所有损失由甲方承担。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >七</span><span
 >、证据和计算</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >.本合同各方确认并同意，委托正道金服平台对本合同项下的任何金额进行计算并通过平台发布或更新具体的还款数据及时间；在无明显错误的情况下，正道金服平台对本合同项下任何金额的任何证明或确定，应作为该金额有关事项的终局证明。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >2</span><span
 >.本合同甲方委托正道金服平台保管所有与本合同有关的书面文件或电子信息；本合同各方确认并同意由正道金服平台提供的与本合同有关的书面文件或电子信息在无明显错误的情况下应作为本合同有关事项的终局证明。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >八</span><span
 >、保密条款</span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >.本合同签署后，除非事先得到其他各方的书面同意，本合同各方均应承担以下保密义务：</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（1）</span><span
 >任何一方不得向非本合同方（正道金服平台除外）披露本合同以及本合同项下的事宜以及与此等事宜有关的任何文件、资料或信息（“保密信息”）；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（2）</span><span
 >任何一方只能将保密信息和其内容用于本合同项下的目的，不得用于任何其他目的。本款的约定不适用于下列保密信息：</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >A</span><span
 >.从披露方获得时，已是公开的；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >B</span><span
 >. 从披露方获得前，接受方已经获知的；</span></p>
  
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >C</span><span
 >.从有正当权限并不受保密义务制约的第三方获得的；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 3em;'><span
         lang=EN-US >D</span><span
 >. 非依靠披露方披露或提供的信息独自开发的。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >2</span><span
 >.本合同各方因下列原因披露保密信息，不受前款的限制：</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（1）</span><span
 >向本合同各方的董事、监事、高级管理人员和雇员及其聘请的会计师、律师、咨询公司披露；</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（2）</span><span
 >因遵循可适用的法律、法规的强制性规定而披露；</span></p>
  
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >（3）</span><span
 >依据其他应遵守的法规向审批机构和/或权力机构进行的披露。</span></p>

 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >3</span><span
 >.本合同任何一方应采取所有其他必要、适当和可以采取的措施，以确保保密信息的保密性。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >4</span><span
 >.本合同各方应促使其向之披露保密信息的人严格遵守本条约定。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >5</span><span
 >.各方在本条项下的权利和义务应在本合同终止或到期后继续有效。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >九</span><span
 >、 合同的变更、解除和终止</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >.本合同生效后，除本合同已有约定外，本合同任何一方均不得擅自变更或提前解除本合同。如确需变更或解除本合同，应经本合同各方协商一致，并达成书面协议。书面协议达成之前，本合同继续有效。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >2</span><span
 >.期限届满，本合同及附件项下未清偿的借款本金、利息、罚息、违约金及所有其他应付款项，继续适用本合同相关条款。 </span></p>
  
 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >十</span><span
 >、 法律适用与争议解决方式</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US ></span><span
 >本合同的签订、履行、终止、解释均适用中国法律。各方同意，因本合同所产生的或与本合同有关的一切争议，各方应协商解决；协商无法达成一致的，各方均有权向杭州市西湖区人民法院提起诉讼。 </span></p>
   
 <p class="MsoNormal" align=left style='text-align:left;'><span
         lang=EN-US >十一</span><span
 >、 其他</span></p>

<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >1</span><span
 >. 甲方、乙方均同意并确认，甲方、乙方通过其正道金服账户和银行账户采取的行为所产生的法律效果和法律责任归属于甲方、乙方本身；甲方、乙方授权和委托正道金服平台和正道金服平台委托的第三方根据本合同所采取的全部行动和措施的法律后果均归属于甲方、乙方本身，与正道金服平台或正道金服平台委托的第三方无关，正道金服平台或正道金服平台委托的第三方也不因此承担任何责任。甲方和乙方同意，正道金服平台有权就其为甲方和乙方提供的平台服务按照约定收取服务费。 </span></p>
 
<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >2</span><span
 >. 本合同中所涉及到的金额均精确到分位（以四舍五入为准）；本合同所涉及到的百分比均采用四舍五入的方式保留小数点后两位。</span></p>
 
<p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >3</span><span
 >.本合同的条款标题仅为了索引方便而设，不影响对本合同条款内容的理解，也不应构成对本合同及其条款内容的定义、限制或扩大。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >4</span><span
 >.甲方、乙方均确认，本合同的签订、生效和履行以不违反法律为前提。如果本协议中的任何一条或多条被司法部门认定为违反所须适用的法律，则该条将被视为无效，但该无效条款并不影响本合同其他条款的效力。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;font-weight: 600;'><span
         lang=EN-US ></span><span
 >甲方、乙方均确认在签署合同前已经充分了解合同条款，并就合同中存在疑问的部分进行了充分的交流及说明，对本合同不存在误解或者其他认识及理解上的障碍。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >5</span><span
 >.本合同项下的借款申请材料与本合同相关的通知、证明、凭证、函电等附件均是本合同不可分割的组成部分，具有同等法律效力，对本合同各方均有法律约束力。</span></p>
 
 <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US >6</span><span
 >.本合同一式四份，甲方执一份，乙方执一份，各担保人均备案一份，具有同等法律效力。</span></p>
 
  <p class="MsoNormal" align=left style='text-align:left;text-indent: 2em;'><span
         lang=EN-US ></span><span
 >（以下无正文，为本协议之签署）</span></p>
 
 

 <style>
  .user .phone{
   width:50%;
   line-height: 110%;
   float: left;
   padding-bottom: 15px;
  }
  .user .comp{
   /* width:50%;
   line-height: 110%; */
   float: right;
   /* padding-bottom: 15px; */
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
   <p>甲方：${lender.lender_type == 1 ? lender.name : lender.comp_name}</p>
   <p>日期：${currDateStr }</p>
  </div>
  <div class="comp">
  <c:if test="${type!=1 }">
   <%--（盖章）--%>
   <img src="<%=path %>/images/front/img/zhang.png" width="180px" height="180px">
  </c:if>
  </span></p>
   <c:if test="${type==1 }">
    <img src="<%=path %>/images/front/img/zhang.png" width="180px" height="180px">
   </c:if>
  </div>
  <div class="phone">
   <p>乙方：${user.real_name }</p>
   <p>日期：${currDateStr }</p>
  </div>
<c:if test="${type!=1 }">
  <div style="font-size: 42px;color: #ccc;letter-spacing: 50px;margin: 10px 300px 0 -350px;width: 260px;height: 50px;line-height: 50px;float: right;">范本</div>
</c:if>
 </div>


<span lang=EN-US style='font-size:12.0pt;line-height:150%;font-family:"Calibri","sans-serif"'><br
        clear=all style='page-break-before:always'>
</span>



</div>
</body>
</html>
