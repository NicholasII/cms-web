<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="/common/import.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set> 
<link rel="stylesheet" type="text/css" href="${context}/resources/css/user/main.css">
<!-- ztree -->
<link rel="stylesheet" href="${context}/resources/lib/ZTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript">
	var zTreeObj;
	var setting = {
		check:{
			enable:true,
			chkboxType:{ "Y" : "ps", "N" : "ps" }
		}
		
	}
	var zNodes = ${tree};
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#channelTree"), setting, zNodes);
		zTreeObj.expandAll(true);
	});
</script>
</head>
<body>
	<div id="left">
		<ul id="channelTree" class="ztree"></ul>
	</div>
</body>
</html>