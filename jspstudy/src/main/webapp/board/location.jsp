<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<jsp:include page="../about_nev.jsp"></jsp:include>

	<div><center>
								<h2>Location</h2>
    	
    	<p class="location_add">위치 : 전라북도 전주시 덕진구 금암1동 667-52<br/>
(도로명주소) 전라북도 전주시 덕진구 백제대로 572<br/>
572, Baekje-daero, Deokjin-gu, Jeonju-si, Jeollabuk-do, Republic of Korea<br/><br/>
주차안내 : 룸 당 1대씩 가능

</p>
    	
    	<!-- 구글지도 코드 시작 -->
    	<iframe class="location_map" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3234.3569122813387!2d127.13029047897255!3d35.840250928940314!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35702349c25db98f%3A0x4754f4bcbb956bbd!2z7J207KCg7Lu07ZOo7YSw7JWE7Yq47ISc67mE7Iqk7ZWZ7JuQ!5e0!3m2!1sko!2skr!4v1653529132898!5m2!1sko!2skr" width="800" height="450" frameborder="0" style="border:1px solid #d5d5d5;" allowfullscreen=""></iframe>
    	<!-- 구글지도 코드 끝/-->

							
							<div class="displaynone">
								<p></p>
							</div>
					</div>

		
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>