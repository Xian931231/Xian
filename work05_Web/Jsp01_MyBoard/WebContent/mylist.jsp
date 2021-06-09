<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jsp페이지 만들시 적어줘야함  --> 
   
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>  

<%@page import="com.my.dao.MyBoardDao"%>
<%@page import="com.my.dto.MyBoardDto" %> 
<%@page import="java.util.List" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MyBoardDao dao = new MyBoardDao();
	List<MyBoardDto> list = dao.selectAll(); //MyBoardDao에 있는 res 를 List에 저장한다.
%>
	<h1>List PAGE</h1>
	<table border="1">
		<col width="50px">
		<col width="100px">
		<col width="200px">
		<col width="100px">
		<tr>
			<th>NO</th>
			<th>NAME</th>
			<th>TITLE</th>
			<th>DATE</th>
			<th>UPDATE</th>
			<th>DELETE</th>
		</tr>
<!-- for문을 통해 list안에 들어있는 정보들 추가해준다. -->
<%
	for(int i=0;i<list.size();i++){
%>
 <!-- html 코드를 꺽쇠 안에 넣을 수없기때문에 블럭처럼 사용해야한다. -->
 		<tr>
			<td><%=list.get(i).getMyno() %></td>
			<td><%=list.get(i).getMyname() %></td>
			<!-- a태그로 감싸서 클릭하면  게시글로 넘어가게한다.-->
			<td><a href="selectone.jsp?myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle() %></a></td>
			<td><%=list.get(i).getMydate() %></td>
			<td><a href="myupdate.jsp?myno=<%=list.get(i).getMyno()%>">수정</a></td>
			<td><a href="mydelete.jsp?myno=<%=list.get(i).getMyno()%>">삭제</a></td> 		
 		</tr>
<%		
	}
%>		
		<tr>
			<td colspan="6" align="right">
								<!--location.href를 통해 해당 jsp로 이동시킨다. -->
				<button onclick="location.href ='myinsert.jsp'">글쓰기</button>
				<!-- HTTP상태 404 : 페이가 없거나 콘트롤러가 잘못됫을때 발생할 수 있다. -->
		</tr>
	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>