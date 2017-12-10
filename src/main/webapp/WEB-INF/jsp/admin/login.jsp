<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${context}/resources/css/admin/login.css">
</head>
<body>
	<div id="contrainer">
		<div id="top"></div>
		<div id="bar">
			<span>欢迎使用昭通市质量监督局后台网站，请登录</span>
		</div>
		<div id="content">
			<div id="form">
				<table id="loginTable">
					<form action="${context}/login" method="post">
						<tr>
							<td><label for="name">登录用户：</label></td>
							<td><input type="text" id="name" name="name"
								class="required"></td>
						</tr>
						<tr>
							<td><label for="password">登录密码：</label></td>
							<td><input type="password" id="password" name="password"
								class="required"></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input type="submit"
								value="登录">&nbsp;&nbsp;&nbsp;<input type="reset"
								value="重置" /></td>
						</tr>
					</form>
				</table>
			</div>
		</div>
	</div>

</body>
</html>