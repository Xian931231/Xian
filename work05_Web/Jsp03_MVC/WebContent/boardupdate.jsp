<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    


<%@ page import="com.mvc.dto.MVCBoardDto" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<%
	MVCBoardDto dto = (MVCBoardDto)request.getAttribute("dto");	
%>

	<div id="detailform">
		<h1>게시글 수정</h1>
	<form action="mycontroller.jsp" method="post">
		<input type="hidden" name="command" value="boardupdate">
		<input type="hidden" name="seq" value="<%=dto.getSeq() %>">	
	<table border="1">
		<tr>
			<th>SEQ</th>
			<td><%=dto.getSeq() %></td>
		</tr>
		<tr>
			<th>WRITER:</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>DATE</th>
			<td><%=dto.getRegdate() %></td>
		</tr>
		<tr>
			<th>TITLE:</th>
			<td><input type="text" name ="title" value="<%=dto.getTitle() %>"></td>
		</tr>
		<tr>
			<th>CONTENT:</th>
			<td>
				<textarea rows="10" clos="60" name="content" ><%=dto.getContent() %></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="submit" value="수정">
			<input type="button" value="목록" onclick="location.href='mycontroller.jsp?command=boardlist'">
	</table>
	</form>	
	</div>
</body>
</html>