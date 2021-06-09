<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
		<tr>
			<th>EL Expression</th>
			<th>Result</th>
		</tr>	
		</thead>
		<tr>
			
			<td>\${1+2 }</td>
			<td>${1+2 }</td>
		</tr>
		<tr>
		<!-- 실수도 더할 수있다. -->
			<td>\${1.2+2.3 }</td>
			<td>${1.2+2.3 }</td>
		</tr>
		<tr>
			<td>\${4-2 }</td>
			<td>${4-2 }</td>
		</tr>
		<tr>
			<td>\${-4-2}</td>
			<td>${-4-2 }</td>
		</tr>
		<tr>
			<td>\${21*2}</td>
			<td>${21*2 }</td>
		</tr>
		<tr>
			<td>\${4/5}</td>
			<td>${4/5 }</td>
		</tr>
		
		<tr>
		<!--  div로도 나눌 수있다. -->
			<td>\${4 div 5 }</td>
			
		<!-- 	<td>${4 div 5 }</td> -->
		</tr>
		<tr>
		<!-- 0으로 나누면 infinity값이 나온다 -->
			<td>\${4/0 }</td>
			<td>${4/0 }</td>
		</tr>
		<tr>
			<td>\${10%4 }</td>
			<td>${10%4 }</td>
		</tr>
		<tr>
			<!-- mod로 나머지 값을 구할수있다. -->
			<td>\${10 mod 4 }</td>
			<td>${10 mod 4 }</td>
		</tr>
		<tr>
			<!-- 3항 연산자도 사용가능 -->
			<td>\${(1==2)?3:4 }</td>
			<td>${(1==2)?3:4}</td>
		</tr>
		
		
	</table>
</body>
</html>