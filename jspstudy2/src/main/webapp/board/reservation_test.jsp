<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
 <%@ page import ="jspstudy.domain.*" %>
 <%@ page import ="java.util.ArrayList" %>
  <%@ page import ="jspstudy.domain.*" %>
    <%@ page import ="java.util.ArrayList" %>
 <%
 
	ArrayList<ReservationVo> alist =(ArrayList<ReservationVo>)request.getAttribute("alist");
 	//System.out.println(alist);
 	PageMaker pm = (PageMaker)request.getAttribute("pm");
 	
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
<center><h1>게시판 리스트</h1></center><hr>
<form name="frm" action="<%=request.getContextPath() %>/boardReservationList.do" method="post">
<table border=0 style="width:800px; text-align:right">
<tr>
<td style="width:620px;">
	<select name="searchType">
	<option value="r_subject">제목</option>
	<option value="r_writer">작성자</option>
	</select>
</td>
<td>
	<input type="text" name="keyword" size="10">
</td>
<td>
	<input type="submit" name="submit" value="검색">
</td>
</tr>
</table>
</form>
<table border="1" style="width:800px">
<tr style="color:skyblue;">
<td style="width:100px;">bidx번호</td>
<td style="width:500px;">제목</td>
<td style="width:100px;">작성자</td>
<td style="width:200px;">작성일</td>
</tr>
<% for (ReservationVo bv : alist) { %>
<tr>
<td><% out.println(bv.getRidx()); %></td>
<td>
<% for (int i =1; i<=bv.getR_level_(); i++) {
	out.println("&nbsp;&nbsp;");
	if(i==bv.getR_level_()){
	out.println("ㄴ");
	}
}
%>
<a href="<%=request.getContextPath()%>/board/ReservationContent.do?bidx=<%=bv.getRidx()%>"><%=bv.getR_subject()%></a></td>

<%-- <% out.println(bv.getSubject()); %></td> --%>
<td><% out.println(bv.getR_writer()); %></td>
<td><% out.println(bv.getR_writeday()); %></td>
</tr>
<% } %>

</table>
<table style="width:800px; text-align:center;">
<tr>
<td style="width:200px; text-align:right;">
<%

String keyword = pm.getScri().getKeyword();

String searchType = pm.getScri().getSearchType();


if (pm.isPrev() == true){
	out.println("<a href='"+request.getContextPath()+"/board/ReservationList.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>");
}
%>
</td>
<td>
<%

for(int i = pm.getStartPage(); i<=pm.getEndPage(); i++){
	out.println("<a href='"+request.getContextPath()+"/board/ReservationList.do?page="+i+"&keyword="+keyword+"&searchType="+searchType+"'>"+i+"</a>");
}

%>
</td>
<td style="width:200px;text-align:left;">
<%
if (pm.isNext()&&pm.getEndPage() >0){
	out.println("<a href='"+request.getContextPath()+"/board/ReservationList.do?page="+(pm.getEndPage()+1)+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
}
%>



</td>
</tr>
</table>
<input type="button" name="button" value="글쓰기" onClick="location.href='<%=request.getContextPath()%>/board/ReservationWrite.do'">
</body>
 <jsp:include page="../footer.jsp"></jsp:include>
</html>
