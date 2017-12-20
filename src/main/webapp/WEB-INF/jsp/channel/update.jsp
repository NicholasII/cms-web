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
	<script type="text/javascript">
		function isneedurl(element) {
			$("#customLinkUrl").attr("required","required")
		}
		function notneedurl(element) {
			$("#customLinkUrl").removeAttr("required");
		}
	</script>
	<div id="content">
	<div class="admin_link_bar">
     <jsp:include page="inc.jsp">
     	<jsp:param value="${pid}" name="pid" />
		<jsp:param value="${pname}" name="pname"/>
     </jsp:include>
    </div>
		<form id="adduser" action="/cms-web/channel/update" method="post">
			<table>
				<tr>
					<td colspan="3" class="head" style="line-height: 29px;font-size: 16px;direction: ltr;">更新子栏目[${pid}]</td>
				</tr>
				<tr>
					<td colspan="1">栏目名称:</td>
					<td colspan="2"><input type="text" name="name" id="name" required="required"></td>
				</tr>
				<tr>
					<td colspan="1">是否制定链接:</td>
					<td colspan="2">
						<input id="uncustomLink" type="radio" name="customLink" value="0" checked="checked" onchange="notneedurl(this)">不指定
						<input id="customLink" type="radio" name="customLink" value="1" onchange="isneedurl(this)">指定
					</td>
				</tr>
				<tr>
					<td colspan="1">链接地址:</td>
					<td colspan="2"><input type="text" name="customLinkUrl" id="customLinkUrl"></td>
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
						<input id="unindex" type="radio" name="isIndex"  value="0" checked="checked">不是&nbsp; 
						<input id="index" type="radio" name="isIndex"  value="1">是&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="1">导航顶部栏目:</td>
					<td colspan="2">
						<input id="untopnav" type="radio" name="isTopNav"  value="0" checked="checked">不是&nbsp; 
						<input id="topnav" type="radio" name="isTopNav"  value="1">是&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="1">是否是推荐栏目:</td>
					<td colspan="2">
						<input id="unrecommend" type="radio" name="recommend"  value="0" checked="checked">不是&nbsp; 
						<input id="recommend" type="radio" name="recommend"  value="1">是&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="1">状态:</td>
					<td colspan="2">
						<input id="use" type="radio" name="status" value="1" checked="checked">启用&nbsp; 
						<input id="unuse" type="radio" name="status" value="0">停用&nbsp;
					</td>
				</tr>
				<tr style="text-align: center;">
					<td colspan="3">
						<input type="text" id="order" name="orders" style="display:none">
						<input type="text" id="pid" name="pid"  style="display:none">
						<input type="text" id="id" name="id" style="display:none">
						<input type="submit" value="更新栏目">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		var channel = ${channel};
		$("#name").val(channel.name);
		$("#channelType").val(channel.channelType);
		if (channel.customLink==1) {
			$("#customLink").attr("checked","checked");
			$("#uncustomLink").removeAttr("checked");
		}else if (channel.customLink==0){
			$("#uncustomLink").attr("checked","checked");
			$("#customLink").removeAttr("checked");
		}		
		if (channel.isIndex==1) {
			$("#index").attr("checked","checked");
			$("#unindex").removeAttr("checked");
		}else if (channel.isIndex==0){
			$("#unindex").attr("checked","checked");
			$("#index").removeAttr("checked");
		}
		if (channel.isTopNav==1) {
			$("#topnav").attr("checked","checked");
			$("#untopnav").removeAttr("checked");
		}else if (channel.isTopNav==0){
			$("#untopnav").attr("checked","checked");
			$("#topnav").removeAttr("checked");
		}
		if (channel.recommend==1) {
			$("#recommend").attr("checked","checked");
			$("#unrecommend").removeAttr("checked");
		}else if (channel.recommend==0){
			$("#unrecommend").attr("checked","checked");
			$("#recommend").removeAttr("checked");
		}
		if (channel.status==1) {
			$("#use").attr("checked","checked");
			$("#unuse").removeAttr("checked");
		}else if (channel.status==0){
			$("#unuse").attr("checked","checked");
			$("#use").removeAttr("checked");
		}
		$("#id").val(channel.id);
		$("#pid").val(channel.pid);
		$("#order").val(channel.orders);
	</script>
</body>
</html>