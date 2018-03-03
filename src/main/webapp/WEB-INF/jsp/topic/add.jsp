<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<title>添加文章</title>
<!-- ztree -->
<link rel="stylesheet" href="${context}/resources/lib/ZTree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${context}/resources/lib/ZTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery.ztree.core.js"></script>
<!-- jqueryui -->
<link rel="stylesheet" type="text/css" href="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.css">
<script type="text/javascript" src="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<!-- xheditor -->
<script type="text/javascript" src="${context}/resources/lib/xheditor-1.2.2/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="${context}/resources/lib/xheditor-1.2.2/xheditor_lang/zh-cn.js"></script>
<!-- uploadify -->
<link rel="stylesheet" type = "text/css" href="${context}/resources/lib/uploadify/uploadify.css">
<script type="text/javascript" src="${context}/resources/lib/uploadify/jquery.uploadify.min.js"></script>
<!-- 自定义css -->
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/keyword.css">
<%-- <link rel="stylesheet" type="text/css" href="${context}/resources/css/user/main.css"> --%>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/topic/topic.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">
<!-- 自定义js -->
<script type="text/javascript" src="${context}/resources/plugin/JQuery.cms.core.js"></script>
<script type="text/javascript" src="${context}/resources/plugin/JQuery.cms.keyword.js"></script>
<script type="text/javascript" src="${context}/resources/js/topic/topicAdd.js"></script>
<script type="text/javascript">
	var havingTree = ${tree};
	$(function(){
		$("#createdate").datepicker();
	});
</script>
</head>
<body>
	<div class="link_bar">
		<span><a href="#" class="a_link">添加文章功能</a></span>
	</div>
	<div id="subcontent">
		<form id="tableform" action="/cms-web/topic/add" method="post">
			<table>
				<tr>
					<td class="leftCol">文章标题</td>
					<td class="rightCol"><input id="title" name="title" type="text"></td>
				</tr>
				<tr>
					<td class="leftCol">文章栏目</td>
					<td class="rightCol"><input id="channelname" name="channelname" type="text"><a
						id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
					</li></td>
				</tr>
				<tr style="display: none;">
					<td class="leftCol">文章栏目ID</td>
					<td class="rightCol"><input id="channelid" name="channelid" type="text"></td>
				</tr>
				<tr>
					<td class="leftCol">文章状态</td>
					<td class="rightCol"><input id="unpublish" type="radio" value="0" name="status"
						checked="checked">未发布<input id="publish" type="radio" value="1"
						name="status">发布</td>
				</tr>
				<tr>
					<td class="leftCol">是否推荐该文章</td>
					<td class="rightCol"><input id="unrecommend" type="radio" value="0" name="recommend"
						checked="checked">不推荐<input id="recommond" type="radio" value="1"
						name="recommend">推荐</td>
				</tr>
				<tr>
					<td class="leftCol">发布时间</td>
					<td class="rightCol"><input type="text" name="createdate" id="createdate"></td>
				</tr>
				<tr>
					<td class="leftCol">关键字</td>
					<td class="rightCol"><input id="keyword" type="text" value="请输入关键字，通过逗号或者回车确认">
					</td>
				</tr>
				<tr>
					<td class="leftCol">文章附件</td>
					<td class="rightCol">
						<input id="file_upload" name="file_upload" type="file" placeholder="请选择文件">
						<input type="button" id="uploadFile" value="上传文件"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">已传附件</td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: center;">
						<table style="width: 100%; margin: 0px; padding: 0px;">
							<thead style="background: #336d9b; color: white;">
								<tr>
									<th>文件名略缩图</th>
									<th>文件名</th>
									<th>文件大小</th>
									<th>主页图片</th>
									<th>栏目图片</th>
									<th>附件信息</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="attach_info">								
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">文章内容</td>
				</tr>
				<tr>
					<td colspan="3"><textarea rows="10" cols="120" id="content"
							name="content" class="xheditor"></textarea></td>
				</tr>
				<tr>
					<td colspan="3">文章摘要</td>
				</tr>
				<tr>
					<td colspan="3"><textarea rows="5" cols="120" id="summary"
							name="summary"></textarea></td>
				</tr>
				<tr>
					<td colspan="3"><button id="add">添加文章</button><input type="reset" value="重置"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="menuContent" class="menuContent"
		style="display: none; position: absolute;">
		<ul id="channelTree" class="ztree"
			style="margin-top: 0; width: 160px;"></ul>
	</div>
</body>
</html>