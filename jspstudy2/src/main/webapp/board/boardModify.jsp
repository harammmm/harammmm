<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jspstudy.domain.BoardVo" %>

<% BoardVo bv = (BoardVo)request.getAttribute("bv"); %>    
<%
if (session.getAttribute("midx")==null){
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
	
}

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
<title>게시글 수정</title>
<script>
﻿
function check(){

//alert("테스트입니다.");

	var fm = document.frm;

	if(fm.subject.value==""){
	alert("제목을 입력하세요");
	fm.subject.focus();
	return;

		}else if(fm.content.value==""){
		alert("내용을 입력하세요");
		fm.content.focus();
		return;

		}else if(fm.writer.value==""){
		alert("작성자를 입력하세요");
		fm.writer.focus();
		return;

}
		alert("수정하시겠습니까?");
		fm.action = "<%=request.getContextPath()%>/board/boardModifyAction.do";
		fm.method = "post";
		fm.submit();
		return;

}


﻿

</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<center><h1>게시판 글수정</h1></center><hr>
<table border=1 style="width:800px;">
<form name="frm">
<input type="hidden" name="bidx" value="<%=bv.getBidx()%>">
<tr>
<td style="width:100px">제목</td>
<td><input type="text" name="subject" size="50" value="<%=bv.getSubject()%>"></td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea class="form-control" placeholder="내용을 입력해주세요." name="content" cols="100" rows="10"><%=bv.getContent()%>
</textarea>
</td>
</tr>
<tr>
<td>작성자</td>
<td><input type="text" name="writer" size="50" value="<%=bv.getWriter()%>"></td>
</tr>
<tr>
<td colspan=2 style="text-align:center;">
<input type="button" name="btn" value="확인" onclick="check();">
<input type="reset" name="reset" value="리셋">
<input type="button" name="list" value="목록" onclick="location.href='<%=request.getContextPath()%>/board/boardList.do'">
</td>
</tr>
</table>
 <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>