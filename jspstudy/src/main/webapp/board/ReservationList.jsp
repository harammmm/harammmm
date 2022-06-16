<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
 <%@ page import ="jspstudy.domain.*" %>
 <%@ page import ="java.util.ArrayList" %>
  <%@ page import ="jspstudy.domain.*" %>
    <%@ page import ="java.util.ArrayList" %>
 <%
 
	ArrayList<BoardVo> alist =(ArrayList<BoardVo>)request.getAttribute("alist");
 	PageMaker pm = (PageMaker)request.getAttribute("pm");
 	BoardVo bv = (BoardVo)request.getAttribute("bv");
 			
 	
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
<center><h1>REVIEW</h1></center><hr>

<!-- <table border="1" style="width:800px"> -->
<table style="margin-left: auto; margin-right: auto;">

<%-- <% for (int i=0; i<alist.size(); i=i+3) { %> --%>

<% 
int size=alist.size();
outer:
for (int i=0; i<size; i=i+3) {
%>
<tr>	
<%
	int rest = size-i;
	if(rest < 3){
		for (int j = i; j <(i+rest); j++ ){
%>
		<td>
		<a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=alist.get(j).getBidx()%>"><img src="<%=request.getContextPath()%>/img/<%=alist.get(j).getFilename()%>" width="490px" height="655px"></a>
		<%-- <a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=alist.get(j).getBidx()%>"><%=alist.get(j).getSubject() %></a></td>  --%>
		</td>
<%
		}
%>
	</tr>
<%	
		break outer;

	}else{//alist에 남은 이미지 개수가 3보다 크거나 같을 경우
		for (int j = i; j <i+3; j++ ) {
%>
	 	<td>
	 	<a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=alist.get(j).getBidx()%>"><img src="<%=request.getContextPath()%>/img/<%=alist.get(j).getFilename()%>" width="490px" height="655px"></a>
		<%-- <a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=alist.get(j).getBidx()%>"><%=alist.get(j).getSubject() %></a></td>  --%>
		</td>
<%
		} 
	}
%>

</tr>
<% }

%>
<tr>
<td style="width:200px; text-align:right;">
<%

String keyword = pm.getScri().getKeyword();

String searchType = pm.getScri().getSearchType();


if (pm.isPrev() == true){
	out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>");
}
%>
</td>
<td style="width:200px; text-align:center;">
<%

for(int i = pm.getStartPage(); i<=pm.getEndPage(); i++){
	out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+i+"&keyword="+keyword+"&searchType="+searchType+"'>"+i+"</a>");
}

%>
</td>
<td style="width:200px;text-align:left;">
<%
if (pm.isNext()&&pm.getEndPage() >0){
	out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getEndPage()+1)+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
}
%>



</td>
</tr>


</table>
<%--
<table style="width:800px;">
<tbody style="text-align:center;">
 <tr>
<td style="width:200px; text-align:right;">
<%

String keyword = pm.getScri().getKeyword();

String searchType = pm.getScri().getSearchType();


if (pm.isPrev() == true){
	out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>");
}
%>
</td>
<td style="width:800px; text-align:center;">
<%

for(int i = pm.getStartPage(); i<=pm.getEndPage(); i++){
	out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+i+"&keyword="+keyword+"&searchType="+searchType+"'>"+i+"</a>");
}

%>
</td>
<td style="width:200px;text-align:left;">
<%
if (pm.isNext()&&pm.getEndPage() >0){
	out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getEndPage()+1)+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
}
%>



</td>
</tr> 
</tbody>
</table>--%>
<form name="frm" action="<%=request.getContextPath() %>/board/boardList.do" method="post">

<table border=0 style="width:800px; text-align:right">
<tr>
<td style="width:620px;">
	<select name="searchType">
	<option value="subject">제목</option>
	<option value="writer">작성자</option>
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
<input type="button" name="button" value="글쓰기" onClick="location.href='<%=request.getContextPath()%>/board/boardWrite.do'" style="float: right;">
</body>
 <jsp:include page="../footer.jsp"></jsp:include>
</html>



