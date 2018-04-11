<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
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
<div class="new_realname">
    <input type="hidden" id="path" value="<%=path%>"/>
    <div class="realbox">
        <h3>为保障您的资金安全，请先开通上海银行存管账户</h3>
        <form action="<%=path %>/openBankDeposit.action" id="openBankDeposit" method="post">
	        <div class="name_one">
	            <ul>
	                <li>
	                    <span>真实姓名:</span>
	                    <c:if test="${user.real_name_auth == 1}"><input type="text" class="userRealName" name="real_name" value="${user.real_name}" readonly="readonly"></c:if>
	                    <c:if test="${user.real_name_auth != 1}"><input type="text" class="userRealName" name="real_name" placeholder="请输入真实姓名" onkeyup="cleanMsg('userNameErr')" onblur="checkForm(this.value,'userRealName','userNameErr')"></c:if>
	                    <div class="error" id="userNameErr">${errMsg}</div>
	                </li>
	                <li>
	                    <span>身份证号:</span>
	                    <c:if test="${user.real_name_auth == 1}"><input type="text" class="userIdCard" name="idcard_no" value="${user.idcard_no}" readonly="readonly"></c:if>
	                    <c:if test="${user.real_name_auth != 1}"><input type="text" class="userIdCard" name="idcard_no" placeholder="请输入身份证号" onkeyup="cleanMsg('userIdCardErr')" onblur="checkForm(this.value,'userIdCard','userIdCardErr')"></c:if>
	                    <div class="error" id="userIdCardErr"></div>
	                </li>
	                <li>
	                    <button class="layui-btn noverbtn" lay-submit lay-filter="openBankDepositForm">立即开通</button>
	                    <p class="next_threetxt">温馨提示：请输入本人真实姓名及有效身份证信息，实名信息一旦确认，无法更改。  </p>
	                </li>
	            </ul>
	        </div>
        </form>
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

    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(openBankDepositForm)', function(data){
	        if(!$(".layui-btn").hasClass("noverbtn")){
	        	var userRealName = $('.userRealName').val();
	            var myReg = /^[\u4e00-\u9fa5]+$/;
	            if (userRealName == null || userRealName.length ==0 || !myReg.test(userRealName)) {
	                $("#userNameErr").html("请输入真实姓名！");
	                return false;
	            }else{
	                $("#userNameErr").html("");
	            }
	            var userIdCard = $('.userIdCard').val();
	            var isCardId = isCardID(userIdCard);
	            if(isCardId != 'isCardId') {
	                $("#userIdCardErr").html(isCardId);
	                return false;
	            }else{
	                $("#userIdCardErr").html("");
	            }
	        } else {
	        	return false;
	        }
        });
    });
	//input失去焦点操作
    function checkForm(v,k,t) {
        if(k=='userRealName'){
            var myReg = /^[\u4e00-\u9fa5]+$/;
            if (v == null || v.length ==0 || !myReg.test(v)) {
                $("#"+t).html("请输入真实姓名！");
            }else{
                $("#"+t).html("");
            }
        }
        if(k=='userIdCard'){
            var isCardId = isCardID(v);
            if(isCardId != 'isCardId') {
                $("#"+t).html(isCardId);
            }else{
                $("#"+t).html("");
            }
        }
    }
    //input onkeyup操作
    function cleanMsg(t) {
        $("#"+t).html("");
        var name = $(".userRealName").val();
        var isCardId = isCardID($(".userIdCard").val());
        var myReg = /^[\u4e00-\u9fa5]+$/;
        if(myReg.test(name) && isCardId == 'isCardId'){
        	$(".layui-btn").removeClass("noverbtn");
        }else{
        	$(".layui-btn").addClass("noverbtn");
        }
    }
    var name = $(".userRealName").val();
    var isCardId = isCardID($(".userIdCard").val());
    var myReg = /^[\u4e00-\u9fa5]+$/;
    if(myReg.test(name) && isCardId == 'isCardId'){
    	$(".layui-btn").removeClass("noverbtn");
    }else{
    	$(".layui-btn").addClass("noverbtn");
    }
    /**
     * 身份证号码格式验证
     * @param sId
     */
    function isCardID(sId){
        var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
        var iSum=0 ;
        if(sId == null || sId == ''){
            return "请输入身份证号！";
        }
        if(!/^\d{17}(\d|x)$/i.test(sId)){
            return "身份证号长度或格式错误";
        }
        sId=sId.replace(/x$/i,"a");
        if(aCity[parseInt(sId.substr(0,2))]==null){
            return "身份证号地区非法";
        }
        sBirthday=sId.substr(6,4)+"-"+Number(sId.substr(10,2))+"-"+Number(sId.substr(12,2));
        var d=new Date(sBirthday.replace(/-/g,"/")) ;
        if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())){
            return "身份证号上的出生日期非法";
        }

        for(var i = 17;i>=0;i --) iSum += (Math.pow(2,i) % 11) * parseInt(sId.charAt(17 - i),11) ;
        if(iSum%11!=1){
            return "身份证号非法";
        }
        return "isCardId"
    }
</script>
</body>
</html>