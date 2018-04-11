<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${!empty rsp.pageData && rsp.pageData.totalPage > 1}">
<!-- pager -->
<div id="kkpager" class="kp-left"></div>
<script type="text/javascript" src="${selfSite}/zdjf/res/comm/js/kkpager.min.js"></script>
<script>
initPage('${rsp.pageData.totalPage}', '${rsp.pageData.total}', '${rsp.pageData.currPage}');
function initPage(totalPage, totalRecords, pageNo){
	//生成分页
	kkpager.generPageHtml({
		pno : pageNo,
		//总页码
		total : totalPage,
		//总数据条数
		totalRecords : totalRecords,
		//默认值是link，可选link或者click
		mode : 'click',
        click : function(n){
            //手动选中按钮
            var url = window.location.href;
            if(url.indexOf("page=") > 0 ){
                url = url.replace("page=${rsp.pageData.currPage}", "page=" + n);
            } else {
            	if(url.indexOf("?") > 0){
            		url += "&page=" + n;
            	} else {
            		url += "?page=" + n;
            	}
            }
            
            window.location.href = url;
            this.selectPage(n);
            return false;
        }
	});
}
</script>
</c:if>
