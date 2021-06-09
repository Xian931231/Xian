<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
/*
	-page Scope영역-
	
	page 
	request 공유되는 영역
	session 하나의 브라우저에 요청되는 페이지들은 모두 공유
	appication
	
*/

	//각 영역이 어디까지 인지 확인
	pageContext.setAttribute("pageId", "myPageIdVal");
	request.setAttribute("reqId", "myRequestIdVal");
	session.setAttribute("sessionId","mySessionIdVal");
	application.setAttribute("appId", "myApplicationIdVal");

%>
	<h1>index</h1>
	
	pageId: <%=pageContext.getAttribute("pageId") %><br>
	reqId: <%=request.getAttribute("reqId") %><br>
	sessionId: <%=session.getAttribute("sessionId") %><br>
	applicationId: <%=application.getAttribute("appId") %><br>

	<a href="result.jsp">이동(result.jsp)</a><br>
	
	<a href="myservlet.do">이동(controller)</a>



</body>
</html>