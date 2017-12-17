<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/import.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/cms-web/resources/css/user/main.css">

</head>
<body style="text-align: center;">
	<div id="content">
	<div class="admin_link_bar">
     <jsp:include page="inc.jsp"></jsp:include>
    </div>
		<form id="adduser" action="/cms-web/channel/add" method="post">
			<table>
				<tr>
					<td colspan="3" class="head" style="line-height: 29px;font-size: 16px;">添加子栏目[${pid}]</td>
				</tr>
				<tr>
					<td colspan="1">栏目名称:</td>
					<td colspan="2"><input type="text" name="name" id="name" required="required"></td>
				</tr>
				<tr>
					<td colspan="1">是否制定链接:</td>
					<td colspan="2">
						<input type="radio" name="customLink" value="0" checked="checked">不指定
						<input type="radio" name="customLink" value="1">指定
					</td>
				</tr>
				<tr>
					<td colspan="1">链接地址:</td>
					<td colspan="2"><input type="text" name="customLinkUrl"></td>
				</tr>
				<tr>
					<td colspan="1">栏目类型:</td>
					<td colspan="2"><select name="channelType" id="channelType">
					<option>导航栏目</option>
					<option>文章列表栏目</option>
					<option>文章内容栏目</option>
					<option>图片列表栏目</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="1">是否留在主页显示:</td>
					<td colspan="2">
						<input type="radio" name="isIndex"  value="0" checked="checked">不是&nbsp; 
						<input type="radio" name="isIndex"  value="1">是&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="1">导航顶部栏目:</td>
					<td colspan="2">
						<input type="radio" name="isTopNav"  value="0" checked="checked">不是&nbsp; 
						<input type="radio" name="isTopNav"  value="1">是&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="1">是否是推荐栏目:</td>
					<td colspan="2">
						<input type="radio" name="recommend"  value="0" checked="checked">不是&nbsp; 
						<input type="radio" name="recommend"  value="1">是&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="1">状态:</td>
					<td colspan="2">
						<input type="radio" name="status" value="1" checked="checked">启用&nbsp; 
						<input type="radio" name="status" value="0">停用&nbsp;
					</td>
				</tr>
				<tr style="text-align: center;">
					<td colspan="3">
						<input type="text" name="orders" value="0" style="display:none">
						<input type="text" name="p_id" value="${pid}" style="display:none">
						<input type="text" name="id" value="0" style="display:none">
						<input type="submit" value="添加栏目">
						<input type="reset">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>