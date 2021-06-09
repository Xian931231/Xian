<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.mvc.dto.MVCBoardDto" %>
<%@ page import="java.util.List" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src=" https://code.jquery.com/jquery-3.6.0.min.js
"></script>
<script type="text/javascript">
	function allChk(bool) {
		//전체선택
		var chks = document.getElementsByName("chk");
		for(var i=0; i<chks.length; i++){
			chks[i].checked = bool;
		}
	}

	//체크를 하나도 안하면 submit 이벤트 취소
	$(function(){
		$("#muldelform").submit(function() {
			if($("#muldelform input:checked").length==0){
				alert("하나 이상 체크해주세요!");
					//false를 통해 submit을 취소한다.
				return false;
			}
				
		});
	});
</script>

</head>
<body>
<%
	List<MVCBoardDto> list = (List<MVCBoardDto>)request.getAttribute("allList"); ;
%>

<!-- include를 통해 header 불러오기 -->

	<h1>글 목록</h1>
	
	<!--form action은 값을 넘겨주는 페이지를 의미 -->
	<form action="mycontroller.jsp" method="post" id="muldelform">
		<!-- input으로 command값도 같이 보내준다 -->
		<input type="hidden" name="command" value="muldel">
		<table border="1">
		<col width ="30px">
		<col width ="50px">
		<col width ="100px">
		<col width ="300px">
		<col width ="100px">
		<tr>
			<th><input type="checkbox" name ="all" onclick="allChk(this.checked)"></th>
			<th>NO</th>
			<th>WRITER</th>
			<th>TITLE</th>
			<th>DATE</th>
		</tr>

<%
		//list의 크기가 0이면 db에서 읽어온 데이터가 없다(게시글이 없을 경우)
		if(list.size() ==0){
%>
		<tr> 
			<td colspan="5">----글이 존재하지 않습니다----</td>
		</tr>

<%			
		}else{
			for(MVCBoardDto dto: list){
			  
%>
		<tr>
			<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>"></td>
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
										<!-- 값을 같이 넘겨줄때 &사용 해서 넘겨줄수있다 -->
			<td><a href="mycontroller.jsp?command=boarddetail&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
			<td><%=dto.getRegdate() %></td>
		</tr>	
<%				
			}
		}
%>		
		<tr>
			<td colspan="5">
				<input type="submit" value="삭제">                        
				<input type="button" value="글쓰기" 
					onclick="location.href='mycontroller.jsp?command=boardinsertform'">
			</td>
		</tr>
		
		</table>
	
	</form>
	
	
</body>
</html>