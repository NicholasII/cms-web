<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${context}/common/global.js"></script>
<script type="text/javascript">
	username = "${user}";
</script>
<style type="text/css">
frame {
	border: 0;
}
</style>
</head>
<frameset cols="*,1035,*" framespacing="0" border="0" scrolling="false">
	<frame src="${context}/templete/null.html">
	<frameset rows="110,*" framespacing="0" frameborder="0" scrolling="false" noresize="noresize">
		<frame name="top" src="${context}/templete/top.jsp" frameborder="0" noresize="noresize" framespacing="0">
		<frameset cols="164,*" frameborder="0" noresize="noresize">
			<frame name="navi" src="${context}/admin/navi" noresize="noresize">
			<frame name="content" src="${context}/templete/background.html">
		</frameset>
	</frameset>
	<frame src="${context}/templete/null.html">
</frameset>
</html>