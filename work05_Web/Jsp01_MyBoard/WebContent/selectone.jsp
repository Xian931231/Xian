<%@page import="com.my.dto.MyBoardDto"%>
<%@page import="com.my.dao.MyBoardDao"%>
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
	//parameter로 받으면 Stirng으로 받기 때문에 Integer.parseInt로 int형으로 바꿔준다.
	int myno = Integer.parseInt(request.getParameter("myno"));
	MyBoardDao dao = new MyBoardDao();
	MyBoardDto dto = dao.selectOne(myno);
%>

<table>
	<tr>
		<th>NAME:</th>
		<td><%=dto.getMyname()%></td>
	</tr>
	<tr>
		<th>TITLE:</th>
		<td><%=dto.getMytitle()%></td>
	</tr>
	<tr>
		<th>CONTENT:</th>
		<td>
			<textarea rows="10" clos="60" readonly ="readonly"><%=dto.getMycontent() %></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<button onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno()%>'">수정</button>
			<button>삭제</button>
			<!-- db를 다시 접근해야한다면 이렇게 처리해야함 -->
			<button onclick="location.href='mylist.jsp'">목록</button>
			<!-- db를 다시 접근할 필요가 없으면 뒤로가기로 하는게 나음 -->
			<button onclick="window.histoy.back();'">목록</button> 
	</tr>







</table>	
	
	
	
	
</body>
</html>