<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
 <%@ page import = "jspstudy.dbconn.Dbconn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<!-- link 선언 -->

<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/style_index.css">


<!-- script 선언 -->
<script src="https://kit.fontawesome.com/e1bd1cb2a5.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="./js/script.js"></script>


<title>Insert title here. git testddddddd333444 </title>

 <%
Dbconn dbconn = new Dbconn();
System.out.println("dbconn"+dbconn);%>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

     <div class="main_container">
     
        <div class="conA">
            <div class="slide img1"></div>
            <div class="slide img2"></div>
            <div class="slide img3"></div>
        </div>
        </div>


</body>
</html>