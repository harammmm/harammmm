<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	request.setCharacterEncoding("utf-8");

	String ID = request.getParameter("ID");
	String PW = request.getParameter("PW");
	String NAME = request.getParameter("NAME");
	String EMAIL = request.getParameter("EMAIL");

	
	
	out.println(ID+"<br>");
	out.println(PW+"<br>");
	out.println(NAME+"<br>");
	out.println(EMAIL+"<br>");



%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>