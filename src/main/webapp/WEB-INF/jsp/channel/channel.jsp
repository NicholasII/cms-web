<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/common/import.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set> 
<link rel="stylesheet" type="text/css" href="${context}/resources/css/user/main.css">
<!-- ztree -->
<link rel="stylesheet" href="${context}/resources/lib/ZTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${context}/resources/lib/ZTree/js/jquery.ztree.core.js"></script>

<%-- <script type="text/javascript" src="${context}/resources/lib/jquery.js"></script> --%>
<%-- <script type="text/javascript" src="${context}/resources/lib/jquery.simplemodal.js"></script> --%>
<script type="text/javascript">
	var sub_channel_url = ctx + "/channel/page";
	var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		callback : {
			onClick : function(event, treeId, treeNode) {
				var data = [];
				var data1 = {};
				data1.name = "pid";
				data1.value = treeNode.id;
				var data2 = {};
				data2.name = "pname";
				data2.value = treeNode.name;
				data.push(data1);
				data.push(data2);
				formSubmit(sub_channel_url,data);
			}
		}
	}
	
	var zNodes = ${tree};
	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#channelTree"), setting, zNodes);
	});
</script>
</head>
<body>
	<div id="content">
		<div class=link_bar>
			<jsp:include page="topinc.jsp"></jsp:include>
		</div>
		<div id="subcontent">
			<div id="left">
				<ul id="channelTree" class="ztree"></ul>
			</div>
			<div id="right">
				<div class="link_bar">
					<jsp:include page="inc.jsp">
						<jsp:param value="${pname}" name="pname"/>
						<jsp:param value="${pid}" name="pid" />
					</jsp:include>
				</div>
				<table>
					<thead>
						<tr>
							<td class="head">栏目名称</td>
							<td class="head">栏目类型</td>
							<td class="head">是否推荐</td>
							<td class="head">主页栏目</td>
							<td class="head">栏目状态</td>
							<td class="head">栏目顺序</td>
							<td class="head">操作</td>
						</tr>
					</thead>
					<tbody>
							<c:forEach items="${channels.rows}" var="channel">
								<tr>
									<td>${channel.name}</td>
									<td>${channel.channelType}</td>
									<td><c:if test="${channel.recommend==1}">推荐</c:if><c:if test="${channel.recommend==0}"><span style="color: red;">不推荐</span></c:if></td>
									<td><c:if test="${channel.isIndex==1}">是</c:if><c:if test="${channel.isIndex==0}"><span style="color: red;">不是</span></c:if></td>
									<td><c:if test="${channel.status==0}"><span style="color: red;">停用</span></c:if><c:if test="${channel.status==1}"><span>启用</span></c:if></td>
									<td>${channel.orders}</td>
									<td colspan="2">
										<a href="javascript:deleteChannel(${channel.id},${channel.pid})" class="a_button">删除</a>
										<a href="${context}/channel/update/${channel.id}" class="a_button">更新</a> 
									</td>
								</tr>
							</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="7" style="text-align: right; margin-right: 10px;">
								<jsp:include page="/common/pager.jsp">
									<jsp:param value="${total}" name="total" />
									<jsp:param value="page" name="url" />
								</jsp:include>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	<div id="deleteModal"></div>
	<script type="text/javascript" src="${context}/resources/js/channel/channel.js"></script>
</body>
</html>