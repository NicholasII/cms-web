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
					<td class="head">用户标识</td>
					<td class="head">用户名称</td>
					<td class="head">用户昵称</td>
					<td class="head">用户状态</td>
					<td class="head">用户电话</td>
					<td class="head">用户操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users.rows}" var="user" varStatus="status">
					<tr>
						<td>${status.index+1}&nbsp;</td>
						<td>${user.userId}</td>
						<td>${user.userName}&nbsp;</td>
							<c:if test="${user.userId=='admin'}">
								<td><span>启用</span></td>
							</c:if>
							<c:if test="${user.userId!='admin'}">
								<c:if test="${user.status==1}">
									<td><span style="background: green;" class="button">启用</span> <span class="button">停用</span></td>
								</c:if>
								<c:if test="${user.status==0}">
									<td><span class="button">启用</span> <span style="background: green;" class="button">停用</span></td>
								</c:if>
							</c:if>
						<td>${user.tel}</td>
						<td>
							<a href="" class="a_button">删除</a> 
							<a href="${context}/user/update/page?userid=${user.userId}" class="a_button">更新</a> 
							<a href="${context}/user/havingChannel?userid=${user.userId}" class="a_button">管理栏目</a>
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