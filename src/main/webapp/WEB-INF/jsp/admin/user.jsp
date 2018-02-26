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
	var user = ${user};
	var role = ${role};
	var group = ${group};
</script>
<style type="text/css">
	input{
		border: none;
		background: white;
	}
</style>
</head>
<body style="text-align: center;">
	<div id="content">
	<div class="admin_link_bar">
     <jsp:include page="../user/inc.jsp"></jsp:include>
    </div>		
			<table>
				<tr>
					<td colspan="3" class="head" style="line-height: 29px;font-size: 16px;">用户信息</td>
				</tr>
				<tr>
					<td colspan="1">用户名:</td>
					<td colspan="2"><input type="text" name="id" id="id" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="1">显示名称:</td>
					<td colspan="2"><input type="text" name="name" id="name" disabled="disabled"></td>
				</tr>		
				<tr>
					<td colspan="1">联系电话:</td>
					<td colspan="2"><input type="text" name="tel" id="tel" maxlength="11" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="1">个人手机:</td>
					<td colspan="2"><input type="text" name="mobile" id="mobile" maxlength="11" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="1">状态:</td>
					<td colspan="2"><input type="text" id="status" disabled="disabled"></select></td>
				</tr>
				<tr>
					<td colspan="1">角色:</td>
					<td colspan="2" id="rolerow">
					</td>
				</tr>
				<tr>
					<td colspan="1">用户组:</td>
					<td colspan="2" id="grouprow">
					</td>
				</tr>				
			</table>
	</div>

	<script type="text/javascript">
		$("#id").val(user.userId);
		$("#name").val(user.userName);
		$("#password").val(user.password);
		$("#password_confirm").val(user.password);
		$("#tel").val(user.tel);
		$("#mobile").val(user.mobile);
		if (user.status == 1) {
			$("#status").val("启用");
		} else {
			$("#status").val("停用");
		}
		for (var i = 0; i < role.length; i++) {
			$("#rolerow").append("<span>["+role[i].roleName+"]&nbsp</span>")			
		}
		for (var j = 0; j < group.length; j++) {
			$("#grouprow").append("<span>["+group[i].groupName+"]&nbsp</span>")
		}
	</script>
</body>
</html>