<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>测试页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
<style type="text/css">
<!--
body {
	background-color: #D1D1D1;
}
-->
</style></head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!-- ImageReady Slices (切割登陆.psd) -->
<table width="805" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="139">&nbsp;</td>
  </tr>
</table>
<table width="805" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="images/admin_03.jpg" width="805" height="68" alt=""></td>
  </tr>
</table>
<table width="805" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="18"><img src="images/admin_05.jpg" width="18" height="241" alt=""></td>
    <td valign="top" bgcolor="#FFFFFF" class="hh"><table width="283" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="40">&nbsp;</td>
      </tr>
    </table>
      <table width="283" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td align="right">
          	<form name="form1" method="post" action="">
            <table width="242" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="24">
                	<img src="images/admin_10.jpg" width="24" height="25" alt="">
                </td>
                <td width="60" height="25" align="center">用户名：</td>
                <td>
                	<input name="userName" id="userName" type="text"  class="input1">
                </td>
              </tr>
              <tr>
                <td>
                	<img src="images/admin_12.jpg" width="24" height="22" alt="">
                </td>
                <td height="25" align="center">密&nbsp;&nbsp;码：</td>
                <td>
                	<input name="userPwd" id="userPwd" type="password"  class="input1">
                </td>
              </tr>
            </table>
            <table width="218" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60" height="25" align="center">验证码：                        </td>
                <td>
                	<input name="userYzm" id="userYzm" type="text" class="input2">
                </td>
                <td>
                	<img src="validate.jsp" id="imgObj" width="65" height="22" onclick="changeImg()">
                </td>
              </tr>
            </table>
            <table width="220" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="40" align="center">
                	<img src="images/admin_15.jpg" style="cursor:pointer" onclick="login();">  
                </td>
              </tr>
            </table>
          </form>
          </td>
        </tr>
      </table></td>
    <td width="504"><img src="<%=request.getContextPath() %>/images/admin_07.jpg" width="504" height="241" alt=""></td>
  </tr>
</table>
<table width="805" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    
    <td width="60">&nbsp;</td>
    <td></td>
  </tr>
</table>
<!-- End ImageReady Slices -->
</body>
</html>