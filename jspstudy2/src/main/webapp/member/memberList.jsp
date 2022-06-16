<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
 <%@ page import ="jspstudy.domain.*" %>
 <%@ page import ="java.util.*" %>
 <%
 
	ArrayList<MemberVo> alist =(ArrayList<MemberVo>)request.getAttribute("alist");
 
// out.println(alist.get(0).getMembername()+"<br>");
 
 
 
 %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- link 선언 -->

<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/style_index.css">


<!-- script 선언 -->
<script src="https://kit.fontawesome.com/e1bd1cb2a5.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="../js/script.js"></script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<center><h1>회원 목록</h1></center><hr>
<table border="1" style="width:800px">
<tr style="color:green;">
<td>midx번호</td>
<td>이름</td>
<td>전화번호</td>
<td>작성일</td>
</tr>
<tr>
<!-- <a href='<%=request.getContextPath()%>/index.jsp'>홈으로</a><br> -->

<% for (MemberVo mv : alist){ %>
</tr>
<tr>
<td><% out.println(mv.getMidx()); %></td>
<td><% out.println(mv.getMembername()); %></td>
<td><% out.println(mv.getMemberphone()); %></td>
<td><% out.println(mv.getWriteday()); %></td>
</tr>
<tr>
<% } %>
</tr>


</table>
<!-- <input type="button" name ="home" value="홈으로" onclick="location.href='<%=request.getContextPath() %>/index.jsp'">  -->
 <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>