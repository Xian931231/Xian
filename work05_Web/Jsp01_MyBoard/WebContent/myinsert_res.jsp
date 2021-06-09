<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>


<%@ page import="com.my.dto.MyBoardDto" %>
<%@ page import="com.my.dao.MyBoardDao" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//myinsert에 있는 값을 request.getParameter로 받아 변수에 저장한다.
	String myname = request.getParameter("myname");
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	
	MyBoardDto dto = new MyBoardDto(myname, mytitle, mycontent);
	MyBoardDao dao = new MyBoardDao();
	int res = dao.insert(dto);
	
	if(res>0){
%>
	<script type="text/javascript">
		alert("글 등록 성공");
		location.href="mylist.jsp";
	</script>



<%		
	}else{
%>
	<script type="text/javascript">
		alert("글 등록 실패");
		location.href="myinsert.jsp";
	</script>

<%		
	}
%>













</body>
</html>