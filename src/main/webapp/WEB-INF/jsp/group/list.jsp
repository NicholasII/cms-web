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
					<td class="head">用户组标识</td>
					<td class="head">用户组名称</td>
					<td colspan="2" class="head">用户组描述</td>
					<td colspan="2" class="head">用户组操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${group.rows}" var="group" varStatus="status">
					<tr>
						<td>${status.index+1}&nbsp;</td>
						<td><a href="${context}/group/numbers?groupid=${group.groupId}">${group.groupId}</a></td>
						<td colspan="2">${group.groupName}&nbsp;</td>
						<td  colspan="2">
							<a href="#" class="a_button">删除</a>
							<a href="#" class="a_button">更新</a>
							<a href="#" class="a_button">查询管理栏目</a>
							<a href="${context}/group/addchannel/page?groupid=${group.groupId}" class="a_button">设置管理栏目</a>
						</td>
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