<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include  file="/common/import.jsp"%>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/user/main.css">

</head>
<body style="text-align: center;">
	<div id="content">
	<div class="admin_link_bar">
     <jsp:include page="inc.jsp"></jsp:include>
    </div>
		<form id="adduser" action="/cms-web/user/add" method="post">
			<table>
				<tr>
					<td colspan="3" class="head" style="line-height: 29px;font-size: 16px;">添加用户</td>
				</tr>
				<tr>
					<td colspan="1">用户名(必须是英文):</td>
					<td colspan="2"><input type="text" name="id" id="id"></td>
				</tr>
				<tr>
					<td colspan="1">显示名称(可以是中文):</td>
					<td colspan="2"><input type="text" name="name" id="name"></td>
				</tr>
				<tr>
					<td colspan="1">用户密码(6-16位数字英文组合):</td>
					<td colspan="2"><input type="password" name="password"
						id="password"></td>
				</tr>
				<tr>
					<td colspan="1">确认密码:</td>
					<td colspan="2"><input type="password" name="password_confirm"
						id="password_confirm"></td>
				</tr>
				<tr>
					<td colspan="1">联系电话:</td>
					<td colspan="2"><input type="text" name="tel" id="tel"
						maxlength="11"></td>
				</tr>
				<tr>
					<td colspan="1">个人手机:</td>
					<td colspan="2"><input type="text" name="mobile" id="mobile"
						maxlength="11"></td>
				</tr>
				<tr>
					<td colspan="1">状态:</td>
					<td colspan="2"><select name="status"><option>停用</option>
							<option>启用</option></select></td>
				</tr>
				<tr>
					<td colspan="1">角色:</td>
					<td colspan="2">
						<input type="checkbox" name="role"  value="admin">超级管理员&nbsp; 
						<input type="checkbox" name="role"  value="articlechecker">文章审核人员&nbsp;
						<input type="checkbox" name="role"  value="articlepublisher">文章发布人员&nbsp; 
						<input type="checkbox" name="role"  value="user">普通用户
					</td>
				</tr>
				<tr>
					<td colspan="1">用户组:</td>
					<td colspan="2">
						<input type="checkbox" name="group" value="cwc">财务处&nbsp; 
						<input type="checkbox" name="group" value="xcb">宣传部&nbsp;
						<input type="checkbox" name="group" value="wlzx">网络中心
					</td>
				</tr>
				<tr style="text-align: center;">
					<td colspan="3">
						<input type="submit" value="添加用户">
						<input type="reset">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript" src="/cms-web/resources/js/user/add.js"></script>
</body>
</html>