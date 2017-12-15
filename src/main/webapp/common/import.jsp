<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>    
<meta http-equiv="Expires" content="0">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-control" content="no-cache">
<title>CMS后台系统</title>
<script type="text/javascript" src="${context}/resources/lib/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${context}/resources/lib/jquery.validate.min.js"></script>
<script type="text/javascript" src="${context}/resources/lib/jquery.metadata.js"></script>
<script type="text/javascript" src="${context}/resources/lib/messages_zh.js"></script>
<script type="text/javascript" src="${context}/resources/lib/ZTree/jquery.ztree.all.min.js"></script>
<link rel="stylesheet" href="${context}/resources/lib/ZTree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript">
	var ctx  = "${context}";
</script>