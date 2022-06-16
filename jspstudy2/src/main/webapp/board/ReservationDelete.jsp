<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jspstudy.domain.ReservationVo" %>

<% ReservationVo bv = (ReservationVo)request.getAttribute("bv"); %>    
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
<title>게시글 삭제</title>
<script>

function check() {
	var fm = document.frm;
		alert("정말 삭제하시겠습니까?");
		fm.action = "<%=request.getContextPath()%>/board/ReservationDeleteAction.do";
		fm.method = "post";
		fm.submit();
		return;
	}



</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<center><h1>게시판 글 삭제</h1></center><hr>
<form name="frm">
<input type="hidden" name="ridx" value="<%=bv.getRidx()%>">
<table border=1 style="width:800px;">
<tr>
<td style="text-align:center;">삭제하시겠습니까?</td>
</tr>
<tr>
<td colspan=2 style="text-align:center;">
<input type="button" name="btn" value="확인" onclick="check();">
<input type="button" name="list" value="취소" onclick="location.href='<%=request.getContextPath()%>/board/ReservationList.do'">
</td>
</tr>
</table>
 <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>

