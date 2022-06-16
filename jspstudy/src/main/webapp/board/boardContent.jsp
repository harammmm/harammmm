<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jspstudy.domain.BoardVo" %>

<% BoardVo bv = (BoardVo)request.getAttribute("bv");

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
<center><h1>REVIEW</h1></center><hr>
<div><center><table class="table table-striped" style="text-align:center; width:800px;  border: 0px solid #dddddd">
<form name="frm">
<tr>

<td class="form-control" style="width:250px">제목&nbsp;&nbsp;<%-- (날짜 : <%=bv.getWriteday().substring(0,10)%>) --%></td>
<td><%=bv.getSubject() %></td>
</tr>
<tr>
<td class="form-control">작성자</td>
<td><%=bv.getWriter() %></td>
</tr>
<tr>
<td class="form-control">내용</td>
<td style="height:500px;">


<img src="<%=request.getContextPath()%>/img/<%=bv.getFilename()%>"><br>
<%-- <a href="<%=request.getContextPath()%>/board/fileDownload.do?filename=<%=bv.getFilename()%>"><%=bv.getFilename()%></a> --%>
<%=bv.getContent() %>
</td>
</tr>
<tr>
<td colspan=2 style="text-align:center;">
<input type="button" class="btn btn-light" name="modify" value="수정" onclick="location.href='<%=request.getContextPath()%>/board/boardModify.do?bidx=<%=bv.getBidx()%>'">
<input type="button" class="btn btn-light"  name="delete" value="삭제" onclick="location.href='<%=request.getContextPath()%>/board/boardDelete.do?bidx=<%=bv.getBidx()%>'">
<%-- <input type="button" class="btn btn-primary pull-right" name="reply" value="답변" onclick="location.href='<%=request.getContextPath()%>/board/boardReply.do?bidx=<%=bv.getBidx()%>&originbidx=<%=bv.getOriginbidx()%>&depth=<%=bv.getDepth()%>&level_=<%=bv.getLevel_()%>'"> --%>
<input type="button" class="btn btn-light"  name="list" value="목록" onclick="location.href='<%=request.getContextPath()%>/board/boardList.do'">
</tr>
</table>
</div>
 <jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>




