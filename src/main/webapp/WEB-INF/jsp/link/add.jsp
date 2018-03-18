<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<title>添加链接</title>
<!-- jqueryui -->
<link rel="stylesheet" type="text/css" href="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.css">
<script type="text/javascript" src="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<!-- 自定义css -->
<link rel="stylesheet" type="text/css" href="${context}/resources/css/topic/topic.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">
<style type="text/css">
em.error{
	color: red;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#tableform").validate({
		rules : {
			title : {
				required : true,
			},
			url : {
				required : true,
				url:true
			}
		},
		messages : {
			title : {
				required : "请输入超链接标题"
			},
			url:{
				url:"请输入格式正确的超链接"
			}
		},
		errorElement:"em b",
		success:function(label){
			label.text(" ").addClass("error");
		}
	});
	
	$("#select").change(function(){
		var content = $(this).val();
		if("选择其他"==content){
			$("#type").removeAttr("disabled");
		}else{
			$("#type").attr("disabled","disabled");
			$("#type").val("");
		}
		
	});
});

</script>
</head>
<body>
	<div class="admin_link_bar">
			<jsp:include page="inc.jsp"></jsp:include>
	</div>
	<div class="link_bar">
		<span><a href="#" class="a_link">添加超链接</a></span>
	</div>
	<div id="subcontent">
		<form id="tableform" action="${context}/system/link/add" method="post">
			<table>
				<tr>
					<td class="leftCol">超链接标题</td>
					<td class="rightCol"><input id="title" name="title" type="text"></td>
				</tr>
				<tr>
					<td class="leftCol">超链接地址</td>
					<td class="rightCol"><input id="url" name="url" type="url"></td>
				</tr>
				<tr>
					<td class="leftCol">超链接类别</td>
					<td class="rightCol">
						<select id="select" name="type">
							<option>常用网站</option>
							<option>选择其他</option>
						</select>
						<input type="text" name="type" id="type" disabled="disabled" required>
				</tr>
				<tr>
					<td class="leftCol">打开方式</td>
					<td class="rightCol"><input type="radio" value="0" name="newwin" checked="checked">本窗口
						<input type="radio" value="1" name="newwin">新窗口</td>
				</tr>
				<tr>
					<td class="leftCol">链接标签ID</td>
					<td class="rightCol"><input id="urlid" type="text"  name="urlid">
					</td>
				</tr>
				<tr>
					<td class="leftCol">链接标签类别</td>
					<td class="rightCol"><input id="urlclass" type="text"  name="urlclass">
					</td>
				</tr>											
				<tr>
					<td colspan="3"><button id="add">添加超链接</button>&nbsp;<input type="reset" value="重置"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>