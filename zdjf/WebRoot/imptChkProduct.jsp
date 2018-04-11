<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Check Product</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style>
    	body {
    		position:relative;
    	}
    	form {
    		position: absolute;
		    top: 50%;
		    margin-top: -40px;
		    left: 50%;
		    margin-left: -160px;
    	}
    	form .file {
    		width:70px;
    	}
    	form .valfile {
    		width:250px;
    	}
    	form .button {
    		padding-top: 30px;
    	}
    </style>

  </head>
  
  <body>
	<form id="chkForm" action="checkStock.action" method="post" enctype="multipart/form-data">
	<span>库存文件：</span>
	<label>
		<input class="file" type="file" name="chkfile" id="chkxls" onchange="handleFile()">
		<input class="valfile" type="text" name="valfile" id="valfile" readonly>
		<input type="hidden" id="filePath" name="filePath" />
	</label>
<!--		<div>-->
<!--			<ul>-->
<!--				<li><label>库存文件：</label></li>-->
<!--				<li><input type="file" name="chkfile" id="chkxls"></li>-->
<!--				<input type="hidden" id="filePath" name="filePath" />-->
<!--			</ul>-->
<!--		</div>-->
		<div class="button">
			<input type="button" value="提交" onclick="submitForm()">
		</div>
	</form>
  </body>
  <script type="text/javascript">
  		function submitForm(){
  			var filePath = document.getElementById("filePath");
  			filePath.value = document.getElementById("chkxls").value;
  			document.getElementById("chkForm").submit();
  		}
  		
  		var chkxls = document.getElementById("chkxls");
  		var valfile = document.getElementById("valfile");
  		function handleFile(){
  		 	var fileName=chkxls.value.split("\\");
            valfile.value = fileName[fileName.length-1];
        }
  </script>
</html>
