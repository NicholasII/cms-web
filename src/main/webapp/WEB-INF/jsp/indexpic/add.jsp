<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<title>添加首页图片</title>
<!-- jqueryui -->
<link rel="stylesheet" type="text/css" href="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.css">
<script type="text/javascript" src="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<!-- uploadify -->
<link rel="stylesheet" type = "text/css" href="${context}/resources/lib/uploadify/uploadify.css">
<script type="text/javascript" src="${context}/resources/lib/uploadify/jquery.uploadify.min.js"></script>
<!-- jcrop -->
<link rel="stylesheet" type="text/css" href="${context}/resources/lib/Jcrop-0.9.12/css/jquery.Jcrop.min.css">
<%-- <script type="text/javascript" src="${context}/resources/lib/Jcrop-0.9.12/js/jquery.min.js"></script> --%>
<script type="text/javascript" src="${context}/resources/lib/Jcrop-0.9.12/js/jquery.Jcrop.min.js"></script>
<!-- 自定义css -->
<link rel="stylesheet" type="text/css" href="${context}/resources/css/topic/topic.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">
<!-- 自定义js -->
<script type="text/javascript" src="${context}/resources/js/indexpic/addPic.js"></script>

</head>
<body>
	<div class="link_bar">
		<span><a href="#" class="a_link">添加首页图片功能</a></span>
	</div>
	<div id="subcontent">
		<form id="tableform" action="${context}/system/indexPic/add" method="post">
			<table>
				<tr>
					<td colspan="3">
						<input id="file_upload" name="file_upload" type="file" placeholder="请选择文件">
						<input type="button" id="uploadFile" value="上传文件"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div id="indexPicView"></div>
					</td>
				</tr>
				<tr>
					<td class="leftCol">首页图片标题</td>
					<td class="rightCol"><input id="title" name="title" type="text"></td>
				</tr>
				<tr>
					<td class="leftCol">首页图片子标题</td>
					<td class="rightCol"><input id="subTitle" name="subTitle" type="text"></td>
				</tr>
				<tr>
					<td class="leftCol">状态</td>
					<td class="rightCol"><input id="publish" type="radio" value="0" name="status"
						checked="checked">未发布<input id="unpublish" type="radio" value="1"
						name="status">发布</td>
				</tr>
				<tr>
					<td class="leftCol">链接类型</td>
					<td class="rightCol"><input type="radio" value="0" name="linkType" checked="checked">站内链接
						<input type="radio" value="1" name="linkType">站外链接</td>
				</tr>
				<tr>
					<td class="leftCol">链接地址</td>
					<td class="rightCol"><input id="linkUrl" type="text"  name="linkUrl">
					</td>
				</tr>											
				<tr>
					<td colspan="3"><button id="add">添加首页图片</button>&nbsp;<input type="reset" value="重置"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>