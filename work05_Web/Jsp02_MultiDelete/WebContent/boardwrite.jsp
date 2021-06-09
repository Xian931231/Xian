<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>



    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("form").submit(function(){
			if($("form input:text").val()=="" || $("textarea").val()==""){
				alert("모두 입력해주세요");
				/*입력 취소되게 리턴을 false로 준다*/
				return false;
			}
		});
		
	});
</script>
</head>
<body>
<%@ include file="./form/header.jsp" %>
	<h1>글 작성</h1>
	<form action="boardwrite_res.jsp" method="post">
		<table border="1">
			<tr>
				<th>WRITER</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>TITEL</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>CONTENT</th>
				<td><textarea rows="10" cols="10" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="등록">
					<input type="button" value="취소" onclick="location.href=boardlist.jsp">
			</tr>
		</table>
	</form>
<%@ include file="./form/footer.jsp" %>	
</body>
</html>