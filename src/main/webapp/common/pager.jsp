<%@ page import="com.sun.cms.web.dto.SystemContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<pg:pager export="currPage=pageNumber" url="${param.url}"
	items="${param.total}" maxPageItems="<%=SystemContext.getPageSize() %>">
	<span style="float: right; padding: 6px;"> <pg:last>共${pageNumber}页[${param.total}条记录],每页显示<%=SystemContext.getPageSize()%>条记录</pg:last>
		<c:forEach items="${param.params }" var="p">
			<pg:param name="${p}" />
		</c:forEach> <pg:first>
			<a href="${pageUrl}">首页</a>
		</pg:first> <pg:prev>
			<a href="${pageUrl}">上一页</a>
		</pg:prev> <pg:page>
			<c:if test="${currPage eq pageNumber}">
			[${pageNumber}]
		</c:if>
			<c:if test="${currPage != pageNumber}">
				<a href="${pageUrl}">${pageNumber}</a>
			</c:if>
		</pg:page> <pg:next>
			<a href="${pageUrl}">下一页</a>
		</pg:next> <pg:last>
			<a href="${pageUrl}">尾页</a>
		</pg:last>
	</span>
</pg:pager>
