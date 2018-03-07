<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<title>首页图片列表</title>
<!-- jqueryui -->
<link rel="stylesheet" type="text/css" href="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.css">
<script type="text/javascript" src="${context}/resources/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/topic/topic.css">
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/common.css">
<script type="text/javascript" src="${context}/resources/js/topic/list.js"></script>
</head>
<body>
	<div id="content">
		<div class="admin_link_bar">
			<jsp:include page="inc.jsp"></jsp:include>
		</div>
		<table>
			<thead>				
				<tr>
					<td>略缩图</td>
					<td>图片标题</td>
					<td>状态</td>
					<td>链接类型</td>
					<td>位置</td>
					<td>用户操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${indexpic.rows}" var="indexpic" varStatus="status">
					<tr>
						<td><img alt="图片已删除" src="${context}/resources/upload/indexPic/thumbnail/${indexpic.newName}"></td>
						<td>${indexpic.title}</td>
						<c:if test="${indexpic.status==1}">
							<td>启用<a href="${context}/system/indexPic/update?id=${indexpic.id}&status=0">停用</a></td>
						</c:if>
						<c:if test="${indexpic.status==0}">
							<td>停用<a href="${context}/system/indexPic/update?id=${indexpic.id}&status=1">启用</a></td>
						</c:if>
						<c:if test="${indexpic.linkType==1}">
							<td>站外链接</td>
						</c:if>
						<c:if test="${indexpic.linkType==0}">
							<td>站内链接</td>
						</c:if>
						<td>${indexpic.pos} <a href="javascript:orderIndexPic('${indexpic.id}','${indexpic.pos}')" class="o_button">排序</a><div id="pos${indexpic.id}" style="display: inline;"></div></td>
						<td>
							<a href="javascript:deleteInexPicture('${indexpic.id}')" class="a_button">删除</a> 
							<a href="${context}/system/indexPic/updateIndexPic/${indexpic.id}" class="a_button">更新</a>
						</td>		
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6" style="text-align: right; margin-right: 10px;background: white;">
						<jsp:include page="/common/pager.jsp">
							<jsp:param value="${total}" name="total" />
							<jsp:param value="list" name="url" />
						</jsp:include>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
<div id="dialog-confirm" title="是否删除图片?" style="display: none;">
  	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>点击确定删除图片，是否要删除?</p>
</div>
<script type="text/javascript">
function deleteInexPicture(indexpicid) {
	$("#dialog-confirm").dialog({
		resizable : false,
		height : "auto",
		width : 400,
		modal : true,
		buttons : {
			"删除" : function() {
				$(this).dialog("close");
				var url = ctx + "/system/indexPic/deleteIndexPic/"+indexpicid;
				formSubmit(url,[]);
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});
	
}
function orderIndexPic(id,pos){
	var spinnerid = "spinner"+id;
	var spinner = $("<input id='"+spinnerid+"' name='value'>&nbsp;<a href='javascript:order("+id+","+pos+")' class='o_button'>确定</a>&nbsp;<a href='javascript:clear("+id+")' class='o_button'>取消</a>");
	$("#pos"+id).append(spinner);
	$("#"+spinnerid).spinner({
		spin: function(event, ui) {
	        if (ui.value > ${total}) {
	          $(this ).spinner("value", 1);
	          return false;
	        } else if ( ui.value < 1 ) {
	          $( this ).spinner( "value", ${total});
	          return false;
	        }
	      }
	});	
	$("#"+spinnerid).spinner("value", pos);
}
function order(id,pos){	
	var newpos = $("#spinner"+id).spinner("value");
	var pos = pos;
	var id = id;
	var url = ctx + "/system/indexPic/updatePos/"+id+"/"+pos+"/"+newpos;
	formSubmit(url,[])
}
function clear(id){
	$("#pos"+id).empty();
}
</script>
</html>