<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="context" value="${pageContext.request.contextPath}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="5;url=${context}/system/indexPic/list" >
<title>Insert title here</title>
<script type="text/javascript" src="${context}/resources/lib/jquery-3.2.1.min.js"></script>
</head>
<body style="width: 100%;height: 100%">
<span id="time" style="width: 100%;margin-top:60px; text-align: center;">5</span>秒后转到首页图片列表！
<span style="width: 100%;margin-top:10px; text-align: center;"><button>立刻转向</button></span>

<script type="text/javascript">
    var count = 5;
	setInterval(() => {
		if(count>0){
			count--;
			$("#time").text(count);
		}else{
			return;
		}
	}, 1000);
</script>
</body>

</html>