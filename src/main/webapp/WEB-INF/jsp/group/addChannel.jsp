<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/import.jsp"%>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/user/main.css">
<!-- ztree -->
<link rel="stylesheet" href="${context}/resources/lib/ZTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${context}/resources/plugin/JQuery.cms.core.js"></script>
<script type="text/javascript">
	var zNodes = ${tree};
	var group = ${group};
	var checkedTrees = ${checkedtree};
</script>
<script type="text/javascript" src="${context}/resources/js/group/addChannel.js"></script>
</head>
<body>		
	<div><span>当前组名称：</span>${group.groupName}</div>
	<div id="left">
		<ul id="channelTree" class="ztree"></ul>
	</div>
</body>
</html>