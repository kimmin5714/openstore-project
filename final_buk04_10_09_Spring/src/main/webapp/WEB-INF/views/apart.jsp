<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
	<%-- <%@ include file="/include/nav.jsp" %> --%>
	<%@ include file="/WEB-INF/views/common/nav.jsp" %>

	<%-- 페이지만의 내용 --%>
	<div class="container p-4">
		<div class="row">
			<%@ include file="/WEB-INF/views/apart/apartmap.jsp" %>
		</div>
	</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
 <%@ include file="/WEB-INF/views/common/login_script.jsp" %>