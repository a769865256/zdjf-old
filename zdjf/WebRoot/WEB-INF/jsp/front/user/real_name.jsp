<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zdjf.domain.user.User" %>
<%@ page import="com.zdjf.domain.fund.Bank" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getAttribute("user");
String error = (String)request.getAttribute("error");
List bankList = (List)request.getAttribute("bankList");
Object[] province = (Object[])request.getAttribute("province");
String ticket = (String)request.getAttribute("ticket");
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>正道金服</title>
	<!-- reset/iconfont -->
	<link rel="stylesheet" href="<%=path%>/css/front/reset.css">
	<link rel="stylesheet" href="<%=path%>/module/iconfont/iconfont.css">
	<link rel="stylesheet" href="<%=path%>/module/layui/css/layui.css">
	<link rel="stylesheet" href="<%=path%>/css/front/index.css">
	<link rel="stylesheet" href="<%=path%>/css/front/register.css">
</head>
<body>
	<!-- header -->
	<div class="header"><jsp:include page="../common/header.jsp"></jsp:include></div>
	<!-- header end -->

	<!-- content -->
	<div class="realname">
		<div class="realbox">
			<h3>开通上海银行存管账户</h3>
			<div class="statebox">
				<div class="state active"><i class="iconfont">1</i><p>实名认证</p></div>
				<div class="line go_two<%if(user.getReal_name_auth()==1){ %> active<% } %>"></div>
				<div class="state bank<%if(user.getReal_name_auth()==1){ %> active<% } %>"><i class="iconfont">2</i><p>绑定银行卡</p></div>
				<div class="line go_three<%if(user.getStatus()==2){ %> active<% } %>"></div>
				<div class="state paypassword<%if(user.getStatus()==2){ %> active<% } %>"><i class="iconfont">3</i><p>设置支付密码</p></div>
			</div>
			<%if(user.getReal_name_auth()!=1){ %>
			<form action="<%=path %>/audit.action" id="real_name_action" method="post">
				<div class="name_one">
					<ul>
						<li>
							<span>真实姓名:</span><input id="real_name" onchange="checkChinese(this.value)" name="real_name" type="text" value="<%=user.getReal_name()!=null?user.getReal_name():"" %>" placeholder="请输入真实姓名">
							<div class="error" id="real_name_error"><%=error!=null?error:"" %></div>
							<input type="hidden" id="realNameAff" <%if(user.getReal_name()!=null && !"".equals(user.getReal_name())){ %> value="Y" <% } %>>
						</li>
						<li>
							<span>身份证号:</span><input id="id_card" onchange="isCardID(this.value)" name="id_card" type="text" placeholder="请输入身份证号" value="<%=user.getIdcard_no()!=null?user.getIdcard_no():"" %>">
							<div class="error" id="id_card_error"></div>
							<input type="hidden" id="idcardAff" <%if(user.getIdcard_no()!=null && !"".equals(user.getIdcard_no())){ %> value="Y" <% } %>>
						</li>
						<li>
							<a href="javascript:dosubmit();" class="next_one">下一步</a>
							<p>*必须开通上海银行存管账户才能进行投资、充值、提现等操作。</p>
						</li>
					</ul>
				</div>
			</form>
			<%} else if(user.getStatus()!=3 && user.getStatus()!=2){ %>
			<form action="<%=path %>/bindBankCardAdvance.action" id="bindBankCardAdvance" method="post">
				<div class="name_two">
					<ul>
						<li>
							<span>借记卡号:</span><input class="two_bank debit_card" id="bankCard" name="bankCard" type="text" placeholder="请输入借记卡号">
							<div class="addform">
								<div class="layui-form">
									<div class="layui-form-item">
								    	<div class="layui-inline" style="width: 100%;">
								      		<div class="layui-input-inline" style="width: 100%;">
												<select name="bank_accounts" id="bank_accounts"  class="bank_accounts">
													<option value="">请选择开户行</option>
													<%
														if(bankList!=null && bankList.size()>0){
															for(int i=0;i<bankList.size();i++){
															Bank b = (Bank)bankList.get(i);
														%>
														<option value="<%=b.getNum()%>"><%=b.getName()%></option>
														<optgroup label="单笔限额<%=b.getSingle_max()%>万元，单卡单日限额<%=b.getDay_max()%>万元"></optgroup>
														<%}} %>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="add_lyu">
									<select name="province" id="province" onchange="getCity(this.value)" class="province">
										<option value="">省</option>
										<%
										if(province!=null && province.length>0){
											for(int i=0;i<province.length;i++){
											String str = (String)province[i];
										%>
										<option value="<%=str%>"><%=str%></option>
										<%}} %>
									</select>
									<select name="city" id="city" class="city">
										<option value="">市</option>
									</select>
									<i class="sanjiao provincesj"></i>
									<i class="sanjiao citysj"></i>
								</div>
							</div>
							<div class="error" id="bankerror"><%=error!=null?error:"" %></div>
						</li>
						<li>
							<span>预留手机号:</span><input class="two_bank" id="phone" name="phone" type="text" placeholder="请输入银行预留手机号">
							<div class="error" id="phoneerror"></div>
							<input type="hidden" name="ticket" id="ticket" value="<%=ticket!=null?ticket:""%>">
						</li>
						<li class="verification">
							<span>短信验证码:</span><input type="text" id="valid_code" name="valid_code" class="two_message" placeholder="请输入短信验证码"><input type="button" class="verbtn" id="btn3" value="获取验证码" onclick="sendsms()" />
							<div class="error" id="verifierror"></div>
						</li>
						<li>
							<a href="javascript:" class="next_two">下一步</a>
							<p>*必须开通上海银行存管账户才能进行投资、充值、提现等操作。</p>
						</li>
					</ul>
				</div>
			</form>
			<%}else { %>
			<div class="name_three">
				<ul>
					<li><p>设置支付密码后方可进行操作</p></li>
					<li>
						<a href="javascript:" class="next_three">前往设置</a>
						<p class="next_threetxt">*必须开通上海银行存管账户才能进行投资、充值、提现等操作。</p>
					</li>
				</ul>
			</div>
			<%} %>
		</div>
	</div>
	<!-- content end-->

	<!-- footer -->
	<jsp:include page="../common/footer.jsp"></jsp:include>
	<!-- footer end -->
    <script src="<%=path%>/module/jquery/jquery.min.js"></script>
    <script src="<%=path%>/module/sticky-header.js"></script>
    <script src="<%=path%>/module/layui/layui.js"></script>
    <script src="<%=path%>/js/front/realname.js"></script>
    <script type="text/javascript">
    	$('.header').stickMe({
			topOffset:100
		});
		
		var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
		
		function isCardID(sId){
		 var iSum=0 ;
		 var info="" ;
		 if(!/^\d{17}(\d|x)$/i.test(sId)){
		 	$("#idcardAff").val("");
		 	$("#id_card_error").html("你输入的身份证长度或格式错误");
		  	return ;
		 }
		 sId=sId.replace(/x$/i,"a");
		 if(aCity[parseInt(sId.substr(0,2))]==null){
		 $("#idcardAff").val("");
		 	$("#id_card_error").html("你的身份证地区非法");		 
		  	return ;
		 }
		 sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
		 var d=new Date(sBirthday.replace(/-/g,"/")) ;
		 if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
		 $("#idcardAff").val("");
			 $("#id_card_error").html("身份证上的出生日期非法");
			 return ;
		 }
		 
		 for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
		 if(iSum%11!=1){
		 $("#idcardAff").val("");
		 	$("#id_card_error").html("你输入的身份证号非法");
			return ;
		 }
		 $("#id_card_error").html("");
		 $("#idcardAff").val("Y");
		}
		
		function checkChinese(str){
			var myReg = /^[\u4e00-\u9fa5]+$/;
            if (!myReg.test(str)) {
            $("#realNameAff").val("");
                $("#real_name_error").html("名字不正确！");
            }else{
            $("#real_name_error").html("");
            	$("#realNameAff").val("Y")
            }
		}
		function dosubmit(){
			if($("#realNameAff").val()!='Y' || $("#idcardAff").val()!='Y'){
			}else{
				$("#real_name_action").submit();
			}
		}
	
		var path = '<%=path %>';


		function sendsms(){
			var reg = /^(\d{16}|\d{17}|\d{18}|\d{19})$/;   // 以19位数字开头，以19位数字结尾
			var province = $("#province").val();
			var bankCard = $("#bankCard").val();
			var city = $("#city").val();
			var bank_accounts = $("#bank_accounts").val();
			var phone = $("#phone").val();
            if(bankCard==null || bankCard==""){
               $("#bankerror").html("银行卡号错误！");
               return;
            }
            if(bank_accounts==null || bank_accounts==""){
            	$("#bankerror").html("请选择开户行！");
               	return;
            }
            if(province==null || province==""){
            	$("#bankerror").html("请选择省份！");
               	return;
            }
            if(city==null || city==""){
            	$("#bankerror").html("请选择城市！");
               	return;
            }
            var pattern = /^1[34578]\d{9}$/; 
            if(!pattern.test(phone) ){
            	$("#phoneerror").html("手机号码错误！");
               	return;
            }
            $.ajax({
                type: 'POST',
                url: path+'/bindBankCard.json',
                data: {province:province,city:city,bankCard:bankCard,bank_accounts:bank_accounts,phone:phone},
                dataType: 'json',
                success: function(data){
                	if(data.status==100){
                		$("#ticket").val(data.content);
                		sendemail();
                	} else {
                        $("#phoneerror").html(data.content);
					}
                },
                error: function(){
                    alert('请求错误，请重新再次请求！');
                    // 即使加载出错，也得重置
                }
			});
            
		}
		
		
    </script>
</body>
</html>