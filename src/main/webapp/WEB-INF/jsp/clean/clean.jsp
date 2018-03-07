<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<title>系统清理</title>
<!-- jqueryui -->
<link rel="stylesheet" type="text/css" href="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.css">
<script type="text/javascript" src="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/topic/topic.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">
</head>
<body>
	<div id="content">
		<div class=link_bar>
			<jsp:include page="inc.jsp"></jsp:include>
		</div>
		<table>
			<thead>				
				<tr>
					<td>名称</td>
					<td>数量</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>				
					<tr>
						<td>未引用的垃圾附件</td>
						<td>${attach}</td>						
						<td>
							<a href="javascript:deleteInexPicture('1')" class="a_button">清理</a> 	
							<a href="${context}/system/clean/attachPage" class="a_button">查询</a>					
						</td>		
					</tr>
					<tr>
						<td>未引用的首页图片</td>
						<td>${indexpics}</td>						
						<td>
							<a href="javascript:deleteInexPicture('2')" class="a_button">清理</a> 	
							<a href="${context}/system/clean/indexPage" class="a_button">查询</a>					
						</td>		
					</tr>
			</tbody>
		</table>
	</div>
</body>
<div id="dialog-confirm" title="是否进行清理?" style="display: none;">
  	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>点击确定将会清理<b>未引用</b>的附件或图片，是否要清理?</p>
  	
</div>
<script type="text/javascript">
function deleteInexPicture(tag) {
	$("#dialog-confirm").dialog({
		resizable : false,
		height : "auto",
		width : 400,
		modal : true,
		buttons : {
			"删除" : function() {
				var url;
				if (tag=="1") {
					url = ctx + "/system/clean/cleanAttach";
				}else if(tag=="2"){
					url = ctx + "/system/clean/cleanIndexPic";
				}
				formSubmit(url,[]);
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});
	
}
</script>
</html>