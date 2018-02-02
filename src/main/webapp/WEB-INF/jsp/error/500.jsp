<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发生错误</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

#content {
	height: 488px;
	width: 870px;
	background: url("/cms-web/resources/img/admin_error_bg.jpg");
}
#info{
	margin-top: 240px;
	width: 870px;
}
#message{
	text-align: center;
}
#upPage{
	text-align: center;
	color: red;
}

</style>
</head>
<body>
	<div id="content">
		<div id="info">
			<div id="message">无访问权限！</div>
			<div id="upPage"><a href="javascript:history.go(-1)">返回上一页</a></div>
		</div>
	</div>
</body>
</html>