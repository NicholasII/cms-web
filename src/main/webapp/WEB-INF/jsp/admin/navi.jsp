<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/import.jsp"%>
<link rel="stylesheet" type="text/css" href="${context}/resources/css/admin/navi.css">
<script type="text/javascript" src="${context}/resources/plugin/JQuery.cms.core.js"></script>
<script type="text/javascript">
$(function(){
	$("#left").commonulli();
})	
</script>
</head>
<body>
	<div id="left">
		<ul class="navMenu">
			<h3 class="navTitle">
				<span class="navTilteTxt">用户管理</span>
			</h3>
			<li><i class="icon"></i><a href="${context}/user/page" target="content">用户信息管理</a></li>
			<li><i class="icon"></i><a href="${context}/group/page" target="content">用户组管理</a></li>
			<li><i class="icon"></i><a href="${context}/role/page" target="content">用户角色管理</a></li>

		</ul>
		<ul class="navMenu">	
			<h3 class="navTitle">
				<span class="navTilteTxt">文章管理</span>
			</h3>
			<li><i class="icon"></i><a href="${context}/channel/page" target="content">栏目信息管理</a></li>
			<li><i class="icon"></i><a href="${context}/topic/list" target="content">文章信息管理</a></li>

		</ul>
		<ul class="navMenu">
			<h3 class="navTitle">
				<span class="navTilteTxt">系统配置</span>
			</h3>
			<li><i class="icon"></i><a href="${context}/user/page" target="content">超级链接管理</a></li>
			<li><i class="icon"></i><a href="${context}/user/page" target="content">网站信息管理</a></li>
			<li><i class="icon"></i><a href="${context}/user/page" target="content">首页图片管理</a></li>
			<li><i class="icon"></i><a href="${context}/user/page" target="content">网站数据管理</a></li>
			<li><i class="icon"></i><a href="${context}/user/page" target="content">系统清理管理</a></li>
		</ul>
	</div>
</body>
</html>