<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<title>首页新闻图片列表</title>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/topic/topic.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">

</head>
<body>
	<div id="content">
		<div class="admin_link_bar">
			<jsp:include page="inc.jsp"></jsp:include>
		</div>
		<table>
			<thead>				
				<tr>
					<td>略缩图</td>
					<td>图片标题</td>					
					<td>设置首页图片</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${indexnewspic.rows}" var="indexpic" varStatus="status">
					<tr>
						<td><img alt="图片已删除" src="${context}/resources/upload/thumbnail/${indexpic.newName}"></td>
						<td>${indexpic.title}</td>
						<c:if test="${indexpic.isIndexPic==1}">
							<td><input type="checkbox" checked="checked"></td>
						</c:if>
						<c:if test="${indexpic.isIndexPic==0}">
							<td><input type="checkbox"></td>
						</c:if>								
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6" style="text-align: right; margin-right: 10px;background: white;">
						<jsp:include page="/common/pager.jsp">
							<jsp:param value="${total}" name="total" />
							<jsp:param value="list" name="url" />
						</jsp:include>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>