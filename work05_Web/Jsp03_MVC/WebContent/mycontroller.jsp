<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.mvc.dao.MVCBoardDao" %>
<%@ page import="com.mvc.dto.MVCBoardDto" %>
<%@ page import="java.util.List" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//view에서 요청한 내욜을 담은 query를 꺼내옴
	String command = request.getParameter("command");
	
	System.out.println("[command : "+command+"]");
	
	MVCBoardDao dao = new MVCBoardDao();
	
	if(command.equals("boardlist")){
		List<MVCBoardDto> list = dao.selectAll();
		
		//requset에 setAttribute를 통해값을 넣어주고 저장한다, request객체는 페이지 마다 각각 존재한다.
		request.setAttribute("allList" ,list);
		
		//forward방식으로 페이지 전환 
		pageContext.forward("boardlist.jsp");
		//forward: requset(req),respons(res) 가 유지되면서 페이지 전환
		//sendRedirect: 새로운 req, res
		
	}else if(command.equals("boarddetail")){
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		MVCBoardDto dto = dao.selectOne(seq);
		request.setAttribute("dto", dto);
		pageContext.forward("boarddetail.jsp");
		
	}else if(command.equals("boardinsertform")){
		response.sendRedirect("boardwrite.jsp");
	}else if(command.equals("boardinsert")){
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCBoardDto dto = new MVCBoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		
		int res = dao.insert(dto);
		
		if(res>0){
%>	
	<script type="text/javascript">
		alert("글 작성 성공");
		location.href="mycontroller.jsp?command=boardlist";
	</script>			
<%			
		}else{
%>
	<script type="text/javascript">
		alert("글 작성 실패");
	</script>
<%			
			response.sendRedirect("mycontroller.jsp?command=boardinsertform");	
		}
	}else if(command.equals("boardupdateform")){
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		MVCBoardDto dto = dao.selectOne(seq);
		request.setAttribute("dto", dto);
		pageContext.forward("boardupdate.jsp");
		
		
	}else if(command.equals("boardupdate")){
		//1.컨트롤러
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCBoardDto dto = new MVCBoardDto();
		dto.setSeq(seq);
		dto.setTitle(title);
		dto.setContent(content);
		
		
		int res = dao.update(dto);
		
		//3.실행 결과를 바탕으로 성공(boarddetail.jsp로 이동) or 실패(boardupdate.jsp로 이동) 처리
		if(res>0){
%>
		<script type="text/javascript">
			alert("글 수정 성공");
			location.href="mycontroller.jsp?command=boarddetail&seq="+<%=seq%>;
		</script>
<%			
		}else{
%>
		<script type="text/javascript">
			alert("글 수정 실패");
			location.href="mycontroller.jsp?command=boardupdateform&seq="+<%=seq%>;
		</script>

<%		
		}


	
	}else if(command.equals("boarddelete")){
		String[] seqArr = new String[1];
		seqArr[0] = request.getParameter("seq");
		
		int res = dao.delete(seqArr);
		
		if(res>0){
%>
		<script type="text/javascript">
			alert("글 삭제 성공");
			location.href="mycontroller.jsp?command=boardlist";
		</script>
<%			
		}else{
%>
		<script type="text/javascript">
			alert("글 삭제 실패");
			location.href="mycontroller.jsp?command=boarddetail&seq="+<%=seqArr[0]%>;
		</script>
<%			
			
			
		}
		
	}else if(command.equals("muldel")){
		//valuse 값들을 배열로 seq에 저장한다.
		String[] seq = request.getParameterValues("chk");
		int res = dao.delete(seq);
		
		if(res==seq.length){
%>
		<script type="text/javascript">
			alert("글 삭제 성공");
			location.href="mycontroller.jsp?command=boardlist";
		</script>
<%			
		}else{
%>
		<script type="text/javascript">
			alert("글 삭제 실패");
			location.href ="mycontroller.jsp?command=boardlist";
		</script>
<%			
		}
	}
%>	
















</body>
</html>