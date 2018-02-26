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
	var baseinfo = ${baseinfo};
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
     <jsp:include page="incBaseinfo.jsp"></jsp:include>
    </div>		
			<table>
				<tr>
					<td colspan="3" class="head" style="line-height: 29px;font-size: 16px;">网站基本信息</td>
				</tr>
				<tr>
					<td colspan="1">网站名称:</td>
					<td colspan="2"><input type="text" name="sitename" id="sitename" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="1">网站地址:</td>
					<td colspan="2"><input type="text" name="address" id="address" disabled="disabled"></td>
				</tr>		
				<tr>
					<td colspan="1">邮政编码:</td>
					<td colspan="2"><input type="text" name="zipCode" id="zipCode" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="1">网站备案号:</td>
					<td colspan="2"><input type="text" name="recordCode" id="recordCode" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="1">电话号码:</td>
					<td colspan="2"><input type="text" id="phone" name="phone" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="1">邮箱:</td>
					<td colspan="2"><input type="text" id="email" name="email" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="1">首页图片宽度:</td>
					<td colspan="2"><input type="text" id="indexPicWidth" name="indexPicWidth" disabled="disabled"></td>
				</tr>		
				<tr>
					<td colspan="1">首页图片高度:</td>
					<td colspan="2"><input type="text" id="indexPicHeight" name="indexPicHeight" disabled="disabled"></td>
				</tr>
				<tr>
					<td colspan="1">网站域名:</td>
					<td colspan="2"><input type="text" id="domainName" name="domainName" disabled="disabled"></td>
				</tr>	
				<tr>
					<td colspan="3" style="text-align: center;">
						<a href="${context}/system/baseinfo/updatePage" class="a_button">更新网站信息</a> 
					</td>
				</tr>			
			</table>
	</div>

	<script type="text/javascript">
		$("#sitename").val(baseinfo.sitename);
		$("#address").val(baseinfo.address);
		$("#zipCode").val(baseinfo.zipCode);
		$("#recordCode").val(baseinfo.recordCode);
		$("#phone").val(baseinfo.phone);
		$("#email").val(baseinfo.email);
		$("#address").val(baseinfo.address);
		$("#indexPicWidth").val(baseinfo.indexPicWidth);
		$("#indexPicHeight").val(baseinfo.indexPicHeight);
		$("#domainName").val(baseinfo.domainName);
	</script>
</body>
</html>