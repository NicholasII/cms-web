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
					<td class="head">角色标识</td>
					<td class="head">角色名称</td>
					<td colspan="2" class="head">角色描述</td>
					<td class="head">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roles.rows}" var="role" varStatus="status">
					<tr>
						<td>${status.index+1}&nbsp;</td>
						<td>${role.roleId}</td>
						<td colspan="2">${role.roleName}&nbsp;</td>
						<td><a href="${context}/role/numbers?roleid=${role.roleId}" class="a_button">查询</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6" style="text-align: right; margin-right: 10px;">
						<jsp:include page="/common/pager.jsp">
							<jsp:param value="${total}" name="total" />
							<jsp:param value="page" name="url" />
						</jsp:include>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>