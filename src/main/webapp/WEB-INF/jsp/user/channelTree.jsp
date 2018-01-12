<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/user/main.css">
<!-- ztree -->
<link rel="stylesheet" href="${context}/resources/lib/ZTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${context}/resources/plugin/JQuery.cms.core.js"></script>
<script type="text/javascript">
	var havingTree = ${tree};
</script>
<script type="text/javascript" src="${context}/resources/js/user/channelTree.js"></script>
</head>
<body>
	<div>
		<ul id="channelTree" class="ztree"></ul>
	</div>
</body>
</html>