<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.multi.dao.MDBoardDao" %>   

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<%
	String[] seq = request.getParameterValues("chk");
	
	if(seq==null || seq.length==0){
%>
	<script type="text/javascript">
		alert("삭제할 글을 1개 이상 선택해주세요");
		location.href="boardlist.jsp";
	</script>

<%
		
	}else{
		MDBoardDao dao = new MDBoardDao();
		int res = dao.multiDelete(seq);
		
		if(res == seq.length){
%>			
		<script type="text/javascript">
			alert("체크된 글들을 삭제 성공하였습니다.");
			location.href="boardlist.jsp";
		</script>
<%		
		}else{
%>			
		<script type="text/javascript">
			alert("체크된 글들을 삭제 실패하였습니다.");
			location.href="boardlist.jsp";
		</script>

<%
		}
	}

%>




</body>
</html>