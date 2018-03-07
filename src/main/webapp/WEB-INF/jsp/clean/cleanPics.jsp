<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
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
			<td>缩略图</td>
			<td>名称</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${indexPics}" var="pic">
			<tr>
				<td><img src="<%=request.getContextPath()%>/resources/upload/indexPic/thumbnail/${pic}"/></td>
				<td>${pic}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>