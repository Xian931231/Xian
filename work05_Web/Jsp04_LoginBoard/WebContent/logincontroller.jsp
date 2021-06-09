<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="java.util.List" %>
<%@ page import ="com.login.dao.MyMemberDao" %>
<%@ page import ="com.login.dto.MyMemberDto" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("[command: " +command+"]");
	
	MyMemberDao dao = new MyMemberDao();
	
	if(command.equals("login")){
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MyMemberDto dto = dao.login(id,pw);
		
		if(dto.getMyid() != null){
			//request : 페이지에서 각각 존재, 요청과 응답 역영까지 공유된다.
			//session : 외부환경 정보 외장객체 
			//session scope(스코프):세션영역, 브라우저당 1개 
			//(ex.네이버에 로그인해서 뉴스, 메일 등 여러페이지 이동 해도 브라우저를 닫지않는이상 로그인 상태는 유지)
			session.setAttribute("dto", dto);
			
			//session에 담긴 객체가 살아 있는 시간(음수로 처리하면 무제한 유지된다.)
			session.setMaxInactiveInterval(60*60);
			
			
			
			if(dto.getMyrole().equals("ADMIN")){
				response.sendRedirect("adminmain.jsp");
			}else if(dto.getMyrole().equals("USER")){
				response.sendRedirect("usermain.jsp");
			}else if(dto.getMyrole().equals("MANAGER")){
				response.sendRedirect("usermain.jsp");
			}
		}else{
%>
		<script type="text/javascript">
			alert("login 실패!");
			location.href="index.jsp";
		
		</script>
<%
		}
		
	}else if(command.equals("logout")){
		//session에 있는 정보 삭제
		session.invalidate();
		response.sendRedirect("index.jsp");
	}else if(command.equals("userlistall")){
		
		List<MyMemberDto> list = dao.selectAll();
		request.setAttribute("list", list);
		
		pageContext.forward("userlistall.jsp");
		
		
	}else if(command.equals("userlistenabled")){
		List<MyMemberDto> list = dao.selectEnabled();
		request.setAttribute("list", list);
		pageContext.forward("userlistenabled.jsp");
	
	}else if(command.equals("updateroleform")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		MyMemberDto dto = dao.selectUser(myno);
		
		request.setAttribute("selectone", dto);
		pageContext.forward("updateroleform.jsp");
		
	}else if(command.equals("updaterole")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		String myrole = request.getParameter("myrole");
		
		int res = dao.updateRole(myno, myrole);
		
		if(res>0){
%>
		<script type="text/javascript">
			alert("회원등급 조정 성공");
			location.href ="logincontroller.jsp?command=userlistenabled"
		</script>
<%			
		}else{
%>
		<script type="text/javascript">
			alert("회원 등급 조정 실패");
			location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
		</script>
<%			
		}
		
		
	}else if(command.equals("userinfo")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyMemberDto dto = dao.selectUser(myno);
		
		request.setAttribute("dto", dto);
		pageContext.forward("userinfo.jsp");
		
	}else if(command.equals("updateform")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyMemberDto dto = dao.selectUser(myno);
		
		request.setAttribute("dto", dto);
		pageContext.forward("updateuser.jsp");
		
	}else if(command.equals("updateuser")){
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		MyMemberDto dto = new MyMemberDto();
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		dto.setMyno(myno);
		
		boolean res = dao.updateUser(dto);
		
		if(res){
%>
		<script type="text/javascript">
			alert("수정 성공");
			location.href="logincontroller.jsp?command=userinfo&myno=<%=myno%>";
		</script>


<%
		}else{
%>
		<script type="text/javascript">
			alery("수정 실패");
			location.href="logincontroller.jsp?command=userinfo&myno=<%=myno%>";
		</script>
<%			
			
		}
		
	}else if (command.equals("deleteuser")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		
		boolean res = dao.deleteUser(myno);
		
		if(res){
%>
		<script type="text/javascript">
			alert("탈퇴성공");
			location.href="logincontroller.jsp?command=logout";
		</script>
<%			
		}else{
%>
		<script type="text/javascript">
			alert("탈퇴 실패");
			location.href="logincontroller.jsp?command=userinfo&myno=<%=myno%>";
		</script>
<%			
			
		}
		
	} else if(command.equals("registform")){
		response.sendRedirect("registform.jsp");
	}else if(command.equals("idchk")){
		String myid = request.getParameter("id");
		String res = dao.idChk(myid);
		boolean idnotused = true;
		
		if(res != null){
			idnotused = false;
		}
		
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
		
		
	}else if(command.equals("insertuser")){
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		MyMemberDto dto = new MyMemberDto();
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		
		int res = dao.insertUser(dto);
		
		if(res>0){
%>
	<script type="text/javascript">
		alert("회원가입성공");
		location.href="index.jsp";
	</script>			
<%			
		}else{
%>
	<script type="text/javascript">
		alert("회원가입실패");
		location.href="logincontroller.jsp?command=registform";
	</script>

<%

		}
	}
%>


</body>
</html>