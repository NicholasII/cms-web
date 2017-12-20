<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
#error{
	padding-top: 244px;
	width: 870px;
	text-align: center;
}
span {
	display: block;
}
</style>
</head>
<body>
	<div id="content">
		<div id="error">
			<span>出错了！</span>
			<span>${reasion}</span>
			<span><a href="javascript:back()" style="color: red;">返回上一页</a></span>
		</div>
	</div>
	<script type="text/javascript">
		function back(){
			window.location.replace("/cms-web/channel/page");
		}
	</script>
</body>
</html>