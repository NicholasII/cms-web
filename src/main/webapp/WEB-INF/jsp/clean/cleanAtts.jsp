<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/topic/topic.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">
</head>
<body>
<div id="content">
	<div class=link_bar>
		<jsp:include page="inc.jsp"></jsp:include>
	</div>
	<table>
		<thead>
		<tr>
			<td>原始文件名</td>
			<td>新文件名</td>
			<td>大小</td>
			<td>类型</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${attachs.rows}" var="att">
			<tr>
				<td>${att.oldname}</td>
				<td>${att.newname}</td>
				<td>${att.size/1024}K</td>
				<td>${att.type}</td>
			</tr>
		</c:forEach>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="6" style="text-align:right;margin-right:10px;">
			<jsp:include page="/common/pager.jsp">
				<jsp:param value="${attachs.total}" name="total"/>
				<jsp:param value="attachPage" name="url"/>
			</jsp:include>
			</td>
		</tr>
		</tfoot>
	</table>
</div>
</body>
</html>