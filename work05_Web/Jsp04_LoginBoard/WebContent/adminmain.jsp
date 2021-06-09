<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>   

<%@page import="com.login.dto.MyMemberDto"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
						//Attribute는 오브젝트 속성이기 때문에 변환해야한다.
	MyMemberDto dto = (MyMemberDto)session.getAttribute("dto");

	if(dto == null){
		pageContext.forward("index.jsp");
	}
%>

<div>
	<span><%=dto.getMyid() %> 님 환영합니다.(등급: <%=dto.getMyrole() %>)</span>
	<a href="logincontroller.jsp?command=logout">로그아웃</a>
</div>

<div>
	<div>
		<a href="logincontroller.jsp?command=userlistall">회원정보 조회(ALL)</a>
	</div>
	<div>
		<a href="logincontroller.jsp?command=userlistenabled">회원정보 조회(enabled='Y')</a>
	</div>
</div>



</body>
</html>