<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set> 
<script type="text/javascript" src="${context}/resources/lib/jquery-3.2.1.min.js"></script>
<style type="text/css">
*{
		margin:0px;
		padding:0px;
	}
#content{
	width:1035px;
	height:110px;
}
#top{
	height:80px;
	background: url("/cms-web/resources/img/top_bg.jpg") repeat-x;
}
#logo {
	width:433px;
	height:63px;
	display:block;
	background:url("/cms-web/resources/img/top_logo.jpg") no-repeat;
	position:relative;
	left:20px;
	top:7px;
	float:left;
}
#sysoperate {
	font-size:12px;
	position:relative;
	top:15px;
	float:right;
	right:15px;
	color:#fff;
}


#sysoperate a:link,#sysoperate a:visited {
	color:#fff;
	text-decoration:none;
}
#sysoperate a:hover {
	color:#ff0;
	text-decoration:underline;
}
#bar{
	height:28px;
	background:url("/cms-web/resources/img/top_remind_bg.jpg") repeat-x;
	border-top:1px solid #fff;
	border-bottom:1px solid #737373;
}
#bar #showDate{
	font-size:12px;
	color:#233d4e;
	position:relative;
	top:6px;
	margin-left:15px;
	width:500px;
}
</style>
</head>
<body>
<script type="text/javascript">
function exitSystem() {
	window.parent.location.href = "/cms-web/logout";
}
</script>
<div id="content">
	<div id="top">
		<span id="logo"></span>
		<span id="sysname"></span>
		<span id="sysoperate">
			<a href="<%=request.getContextPath()%>/index"  target="_blank">网站首页</a>
			|<a href="<%=request.getContextPath()%>/admin/user/showSelf"  target="content">查询个人信息</a>
			| <a href="<%=request.getContextPath()%>/admin/user/updateSelf"  target="content">修改个人信息</a>
			| <a href="<%=request.getContextPath()%>/admin/user/updatePwd/page"  target="content">修改密码</a>
			| <a href="javascript:exitSystem()">退出系统</a>
		</span>
	</div>
	<div id="bar">
		<span id="showDate">
			现在是<span id="currtime"></span>，欢迎 <span id="user"></span> 光临通用CMS系统后台网站!
		</span>
	</div>
</div>
<script type="text/javascript">
		function getNowFormatDate() {
			var date = new Date();
			var seperator1 = "-";
			var seperator2 = ":";
			var month = date.getMonth() + 1;
			var strDate = date.getDate();
			if (month >= 1 && month <= 9) {
				month = "0" + month;
			}
			if (strDate >= 0 && strDate <= 9) {
				strDate = "0" + strDate;
			}
			var currentdate = date.getFullYear() + seperator1 + month
					+ seperator1 + strDate + " " + date.getHours() + seperator2
					+ date.getMinutes() + seperator2 + date.getSeconds();
			return currentdate;
		}
		var currtime = getNowFormatDate();
		$("#currtime").text(currtime);
		var username = window.parent.username;
		$("#user").text(username);
	</script>
</body>
</html>