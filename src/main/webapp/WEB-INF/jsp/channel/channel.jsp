<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/common/import.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/cms-web/resources/css/user/main.css">
<script type="text/javascript">
	var zTreeObj;
	// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
	var setting = {
		callback : {
			onClick : function(event, treeId, treeNode) {
				alert(treeNode.tId + ", " + treeNode.name);
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
							<td class="head">栏目顺序</td>
							<td class="head">操作</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${channel}" var="c" varStatus="status">
							<tr>
								<td>${c.name}</td>
								<td>${c.channelType}</td>
								<td>${c.recommend}</td>
								<td>${c.isIndex}</td>
								<td>${c.orders}</td>
								<td colspan="2"><a href="#" class="a_button">删除</a> <a
									href="#" class="a_button">更新</a> <a href="#" class="a_button">查询管理栏目</a>
									<a href="#" class="a_button">设置管理栏目</a></td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6" style="text-align: right; margin-right: 10px;">
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
</body>
</html>