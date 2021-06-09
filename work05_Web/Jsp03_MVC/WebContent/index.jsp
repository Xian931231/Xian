<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
 <!-- 서버에  한글의 정보를 전달할때 사용 -->   
<% request.setCharacterEncoding("UTF-8"); %>
<!-- 서버가 응답해서 한글의 정보를 받아서 출력할때 사용 -->
<% response.setContentType("text/html; charset=UTF-8"); %>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="mycontroller.jsp?command=boardlist">글 목록</a>
</body>
</html>