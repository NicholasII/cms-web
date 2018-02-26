<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<title>文章列表</title>
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
					<td colspan="6">
						<label>搜索文章</label><input type="text" id="topictitle">
						<select id="search">
							<option value="-1">选择栏目</option>
						</select>
						<label>关键字</label><input type="text" id="topickey">
						<input type="button" id="search" value="搜索文章" onclick="searchTopic()"/>
					</td>
				</tr>
				<tr>
					<td>文章标题</td>
					<td>文章作者</td>
					<td>是否推荐</td>
					<td>所属频道</td>
					<td>文章的状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${topic.rows}" var="topic" varStatus="status">
					<tr>
						<td>${topic.title}</td>
						<td>${topic.publisher}</td>
						<c:if test="${topic.recommend==1}">
							<td>推荐</td>
						</c:if>
						<c:if test="${topic.recommend==0}">
							<td>为推荐</td>
						</c:if>
						<td>${topic.channelname}</td>
						<c:if test="${topic.status==1}">
							<td>已发布</td>
						</c:if>
						<c:if test="${topic.status==0}">
							<td>未发布</td>
						</c:if>
						<td>
							<a href="/cms-web/topic/update/${topic.id}" class="a_button">修改</a> 
							<c:if test="${topic.status==1}">
								<a href="/cms-web/topic/updatePublish/${topic.id}/0" class="a_button">取消发布</a> 
							</c:if>
							<c:if test="${topic.status==0}">
								<a href="/cms-web/topic/updatePublish/${topic.id}/1" class="a_button">发布</a> 
							</c:if>
							<a href="javascript:deleteTopic('${topic.id}')" class="a_button">删除</a>
						</td>		
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6" style="text-align: right; margin-right: 10px;">
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
<div id="dialog-confirm" title="是否删除文章?" style="display: none;">
  	<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>点击确定删除文章，是否要删除?</p>
</div>
</html>