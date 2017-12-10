<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/common/import.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/user/main.css">
</head>
<body>
	<div id="content">
		<div class="admin_link_bar">
			<jsp:include page="inc.jsp"></jsp:include>
		</div>
		<table>
			<thead>
				<tr>
					<td colspan="3" class="head">用户组列表</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="1">角色名称</td>
					<td colspan="2">${group.groupId}&nbsp;</td>
				</tr>
				<tr>
					<td colspan="1">角色描述</td>
					<td colspan="2">${group.groupName}&nbsp;</td>
				</tr>
				<tr>
					<td colspan="1">用户成员</td>
					<td colspan="2">
						<c:forEach items="${member}" var="user" varStatus="status">
							<a class="a_button">${user.userName}</a>&nbsp;
						</c:forEach> 
						<a class="a_button">修改用户</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>