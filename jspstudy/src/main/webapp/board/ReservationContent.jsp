<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jspstudy.domain.ReservationVo" %>

<% ReservationVo bv = (ReservationVo)request.getAttribute("bv");

%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<center><h1>게시판 내용보기</h1></center><hr>
<table border=1 style="width:800px;">
<form name="frm">
<tr>
<td style="width:250px">제목&nbsp;&nbsp;(날짜 : <%=bv.getR_writeday().substring(0,10)%>)</td>
<td><%=bv.getR_subject() %></td>
</tr>
<tr>
<td>내용</td>
<td style="height:100px;">


<%-- <img src="<%=request.getContextPath()%>/img/<%=bv.getFilename()%>"> --%>
<%-- <a href="<%=request.getContextPath()%>/board/fileDownload.do?filename=<%=bv.getFilename()%>"><%=bv.getFilename()%></a> --%>
<%=bv.getR_content() %>
</td>
</tr>
<tr>
<td>작성자</td>
<td><%=bv.getR_writer() %></td>
</tr>
<tr>
<td colspan=2 style="text-align:center;">
<input type="button" name="modify" value="수정" onclick="location.href='<%=request.getContextPath()%>/board/ReservationModify.do?bidx=<%=bv.getRidx()%>'">
<input type="button" name="delete" value="삭제" onclick="location.href='<%=request.getContextPath()%>/board/ReservationDelete.do?bidx=<%=bv.getRidx()%>'">
<input type="button" name="reply" value="답변" onclick="location.href='<%=request.getContextPath()%>/board/ReservationReply.do?bidx=<%=bv.getRidx()%>&r_originRidx=<%=bv.getR_originbidx()%>&r_depth=<%=bv.getR_depth()%>&R_level_=<%=bv.getR_level_()%>'">
<input type="button" name="list" value="목록" onclick="location.href='<%=request.getContextPath()%>/board/ReservationList.do'">
</tr>
</table>
 <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>




