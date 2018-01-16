<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/login.css">
<script type="text/javascript">
	function getCheckCodeImage(img){
		img.src = ctx + "/admin/checkcode?"+Math.random();
	}
</script>
</head>
<body>
	<div id="contrainer">
		<div id="top">
		
		</div>
		<div id="loginBar">
			<span>欢迎使用昭通市质量监督局后台网站，请登录</span>
		</div>
		<div id="content">
			<div id="loginForm">
				<table id="loginTable" cellpadding="0" cellspacing="0" width="380px">
					<form action="${context}/main" method="post">
						<tr>
							<td><label for="name">登录用户</label></td>
							<td><input type="text" id="name" name="name"
								class="required"></td>
						</tr>
						<tr>
							<td><label for="password">登录密码</label></td>
							<td><input type="password" id="password" name="password"
								class="required"></td>
						</tr>
						<tr>
							<td><label for="checkcode">验证码</label></td>
							<td><input type="text" id="checkcode" name="checkcode" class="required"><span style="color: red;">${error}</span>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<img alt="无法显示图片，请刷新!" src="/cms-web/admin/checkcode" onclick="getCheckCodeImage(this)">
							</td>
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