<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/user/main.css">
<script type="text/javascript">
</script>
</head>
<body style="text-align: center;">
	<div id="content">
	<div class="admin_link_bar">
     <jsp:include page="../user/inc.jsp"></jsp:include>
    </div>
		<form id="adduser" action="/cms-web/admin/user/updatePwd" method="post">
			<table>
				<tr>
					<td colspan="5" class="head" style="line-height: 29px;font-size: 16px;">修改密码</td>
				</tr>
				<tr>
					<td colspan="1">原始密码:</td>
					<td colspan="4"><input type="password" name="origin_password" id="origin_password"></td>
				</tr>
				<tr>
					<td colspan="1">新密码(6-16位数字英文组合):</td>
					<td colspan="4"><input type="password" name="password" id="password"></td>
				</tr>
				<tr>
					<td colspan="1">确认密码:</td>
					<td colspan="4"><input type="password" name="password_confirm" id="password_confirm"></td>
				</tr>
				
				<tr style="text-align: center;">
					<td colspan="5">
						<input type="submit" value="修改密码">
					</td>
				</tr>
				<tr style="text-align: center;">
					<td colspan="5">
						<span style="color: green;">${response}</span>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript" src="/cms-web/resources/js/user/add.js"></script>
</body>
</html>