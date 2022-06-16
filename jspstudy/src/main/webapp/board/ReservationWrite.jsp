<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
if (session.getAttribute("ridx")==null){
	session.setAttribute("saveUrl",request.getRequestURI());
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
<title>리뷰글 작성</title>
<script>
function check(){
	//alert("테스트입니다.");
	
	 var fm = document.frm;
	
	if(fm.r_subject.value==""){
		alert("제목을 입력하세요");
		fm.r_subject.focus();
		return;
	}else if(fm.r_content.value==""){
		alert("내용을 입력하세요");
		fm.r_content.focus();
		return;
	}else if(fm.r_writer.value==""){
		alert("작성자를 입력하세요");
		fm.r_writer.focus();
		return;
			}
		
	
	alert("전송합니다");
	fm.action = "<%=request.getContextPath()%>/board/ReservationWriteAction.do"
		fm.enctype="multipart/form-data";  	
	  	fm.method = "post";
		fm.submit();
		  	
	return;
}

</script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<center><h1>REVIEW</h1></center><hr>
<table border=1 style="width:800px;">
<form name="frm">
<tr>
<td style="width:100px">제목</td>
<td><input type="text" name="r_subject" size="50"></td>
</tr>
<tr>
<td>내용</td>
<td>
<textarea placeholder="내용을 입력해주세요." name="r_content" cols="100" rows="10">
</textarea>
</td>
</tr>
<tr>
<td>작성자</td>
<td><input type="text" name="r_writer" size="50" value=<%=session.getAttribute("memberName") %> readonly="readonly"></td>
</tr>
<tr>
<td colspan=2 style="text-align:center;">
<input type="button" name="btn" value="확인" onclick="check();">
<input type="reset" name="reset" value="리셋"></td>
</tr>
</table>
 <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>