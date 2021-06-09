<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("UTF-8"); %>   

<%@ page import="com.login.dto.MyMemberDto" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function updateuser(myno) {
		location.href="logincontroller.jsp?command=updateform&myno="+myno;
	}
	function deleteuser(myno){
		location.href="logincontroller.jsp?command=deleteuser&myno="+myno;
	}
</script>
</head>
<body>
<%
	MyMemberDto dto = (MyMemberDto)request.getAttribute("dto");
%>
	<h1>내 정보 보기</h1>
	<table border="1">
		<tr>
			<th>ID</th>
			<td><%=dto.getMyid() %></td>
		</tr>
		<tr>
			<th>PW</th>
			<td><%=dto.getMypw() %></td>
		</tr>
		<tr>
			<th>NAME</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>ADDR</th>
			<td><%=dto.getMyaddr() %></td>
		</tr>
		<tr>
			<th>PHONE</th>
			<td><%=dto.getMyphone() %></td>
		</tr>
		<tr>
			<th>EMAIL</th>
			<td><%=dto.getMyemail() %></td>
			
		</tr>
		<tr>
			<th>ROLE</th>
			<td><%=dto.getMyrole().equals("USER")?"정회원":"매니저" %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="updateuser(<%=dto.getMyno()%>);">
				<input type="button" value="탈퇴" onclick="deleteuser(<%=dto.getMyno()%>);">
				<input type="button" value="목록" onclick="location.href='usermain.jsp'">
			</td>
		</tr>
			
	</table>
</body>
</html>