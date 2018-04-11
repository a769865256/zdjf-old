<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>正道金服与浙江德睦律师事务所全面合作</title>
    <meta name="keywords" content="正道金服">
    <meta name="description" content="正道金服">
    <meta name="copyright" content="版权所有 © 正道金服">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <link rel="stylesheet" type="text/css" href="${selfSite}/zdjf/res/activity/css/cooperation.css">
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/public.js"></script>
</head>

<body>
<jsp:include page="../comm/header.jsp"></jsp:include>

<!-- detail -->
<div class="content">
    <div class="banner01"></div>
    <div class="con">
        <div>
            <p class="title clearfix"><i>&nbsp;&nbsp;◆</i><span>浙江德睦律师事务所概况</span><i>◆</i></p>
            <p class="conter">　　浙江德睦律师事务所是浙江省司法厅核准的合伙制律师事务所，由经验丰富的资深优秀律师按照现代律师事务所标准而设立。德睦律所地处杭州市市中心地段，办公面积200余平方米。秉承“守德、求和”原则，为当事人谋取最大法律利益。各律师凭良好的职业道德、扎实的法律功底、灵活的法律技巧、丰富的执业经验及过硬的协调能力为当事人提供优质的法律服务。律所律师常年为客户提供专业的案件代理与公司法律顾问服务，并在金融行业硕果累累。</p>
            <p class="title clearfix"><i>◆</i><span>浙江德睦律师事务所合作目的</span><i>◆</i></p>
        </div>
        <div class="pic"></div>
    </div>
</div>

<jsp:include page="../comm/footer.jsp"></jsp:include>
<jsp:include page="../comm/helper.jsp"></jsp:include>
</body>
</html>
