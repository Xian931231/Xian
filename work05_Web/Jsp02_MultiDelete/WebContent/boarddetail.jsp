<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    

<%@ page import="com.multi.dao.MDBoardDao" %>
<%@ page import="com.multi.dto.MDBoardDto" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#updateform{
		display:none;
	
	}
</style>
<script type="text/javascript" src=" https://code.jquery.com/jquery-3.6.0.min.js
"></script>
<script type="text/javascript">
	//수정 버튼 클릭시
	function updateFormView() {
		$("#detailform").hide();
		$("#updateform").show();
		
	}
	
	//수정취소 버튼 클릭시
	function detailFormView() {
		$("#detailform").show();
		$("#updateform").hide();
	}	
	
	
</script>
</head>
<body>
	<%
		int seq = Integer.parseInt(request.getParameter("seq"));
		MDBoardDao dao = new MDBoardDao();
		MDBoardDto dto = dao.selectOne(seq);
	%>
<%@ include file="./form/header.jsp" %>	
	<div id="detailform">
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
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>CONTENT:</th>
			<td>
				<textarea rows="10" clos="60" readonly="readonly"><%=dto.getContent() %></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="button" value="수정" onclick="updateFormView();">
			<input type="button" value="삭제" onclick="location.href='boarddelete.jsp?seq=<%=dto.getSeq()%>'">
			<input type="button" value="목록" onclick="location.href='boardlist.jsp'">
	</table>	
	
	</div>
	
<!-- ------------------------------------------------------------------------ -->	
	<!-- 글 수정할수 있는 form을 아래에 준비 -->
	<div id="updateform">
		<h1>글 수정</h1>
		
		<form action="boardupdate_res.jsp" method="post">
			<!-- 번호를 넘겨줘야 해당 데이터 수정이 이뤄지기 때문에 input을 통해 value 값에 dto.getSeq를 넣어준다. -->
			<input type="hidden" name="seq" value="<%=dto.getSeq() %>">
		<table border="1">
			<tr>
				<th>SEQ</th>
				<td><%=dto.getSeq() %></td>
			</tr>
			<tr>
				<th>WRITER</th>
				<td><%=dto.getWriter() %></td>
			</tr>
			<tr>
				<th>DATE</th>
				<td><%=dto.getRegdate() %></td>
			</tr>
			<tr>
				<th>TITLE</th>
				<td><input type="text" name="title" value="<%=dto.getTitle() %>"></td>
			</tr>
			<tr>
				<th>CONTENT</th>
				<td><textarea rows="10" cols="60" name="content"><%=dto.getContent() %></textarea>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정완료">
					<input type="button" value="취소" onclick="detailFormView()">
			</tr>
		</table>
		
		</form>
	</div>
<%@ include file="./form/footer.jsp" %>	
	
	
	
	
	
	
	
	
	
	
</body>
</html>