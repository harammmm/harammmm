<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!-- <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
 -->
<meta charset="utf-8">

</head>
<body>

	<header>
		<div class="header_container">
			<div class="logo_container">
				<a href="<%=request.getContextPath()%>/index.jsp">MOOD</a>
			</div>
			<div class="nav_container" id="nav_menu">
				<div class="menu_container">
					<ul class="menu">
						<li class="menu_1"><a class="menu_title" href="<%=request.getContextPath()%>/board/about.do">INFO</a>
							<ul class="menu_1_content">
								<li><a class="menu_index" href="<%=request.getContextPath()%>/board/about.do">ABOUT</a></li>
								<li><a class="menu_index" href="<%=request.getContextPath()%>/board/charge.do">이용요금</a></li>
								<li><a class="menu_index" href="<%=request.getContextPath()%>/board/caution.do">주의사항</a></li>
								<li><a class="menu_index" href="<%=request.getContextPath()%>/board/location.do">LOCATION</a></li>
							</ul></li>
						<li class="menu_2"><a class="menu_title" href="<%=request.getContextPath()%>/board/aroom.do">ROOM</a>
							<ul class="menu_2_content">
								<li><a class="menu_index"
									href="<%=request.getContextPath()%>/board/aroom.do">A ROOM</a></li>
								<li><a class="menu_index"
									href="<%=request.getContextPath()%>/board/broom.do">B ROOM</a></li>
							</ul></li>
						<li class="menu_3"><a class="menu_title"
							href="<%=request.getContextPath()%>/board/reservation.do">RESERVATION</a></li>
						<li class="menu_4"><a class="menu_title"
							href="<%=request.getContextPath()%>/board/boardList.do">REVIEW</a></li>
					</ul>
				</div>
				<div class="login_container">
					<ul class="login">
						<li>
						<%
						if (session.getAttribute("midx") != null) {
							out.println(session.getAttribute("memberName") + "님 안녕하세요" + "<br>");
							/* out.println("회원아이디:"+session.getAttribute("memberId")+"<br>");
							out.println("회원이름:"+session.getAttribute("memberName")+"<br>"); */

							 out.println("<a href='"+request.getContextPath()+"/member/memberLogout.do'>로그아웃</a><br>"); 

						}
						else if (session.getAttribute("midx") == null) {
						
						%>	<a class="menu_title" href="<%=request.getContextPath()%>/member/memberLogin.do">로그인</a>
						<%}%>
						</li>
<%-- 						<li class="menu_logout"><a class="menu_title"
							href="<%=request.getContextPath()%>/member/memberLogout.do">로그아웃</a></li> --%>
						<li class="menu_join"><a class="menu_title"
							href="<%=request.getContextPath()%>/member/memberJoin.do">회원가입</a></li>
					<li class="menu_list"><a class="menu_title"
							href="<%=request.getContextPath()%>/member/memberList.do">회원목록</a></li> 
					</ul>
				</div>
			</div>
		</div>
	</header>

 
<!-- <div class="b-example-divider"></div>

  <div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
      <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"/></svg>
      </a>

      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">Features</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">Pricing</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">FAQs</a></li>
        <li><a href="#" class="nav-link px-2 link-dark">About</a></li>
      </ul>

      <div class="col-md-3 text-end">
        <button type="button" class="btn btn-outline-primary me-2">Login</button>
        <button type="button" class="btn btn-primary">Sign-up</button>
      </div>
    </header>
  </div>
 -->


	<%-- <header>
		<a href='<%=request.getContextPath()%>/index.jsp'>홈으로</a><br>
		<!-- <a href="<%=request.getContextPath()%>/member/memberList.do">회원리스트</a>  -->
		<a href="<%=request.getContextPath()%>/member/memberJoin.do">회원가입</a>
		<a href="<%=request.getContextPath()%>/member/memberLogin.do">회원로그인</a>
		<a href="<%=request.getContextPath()%>/board/boardWrite.do">REVIEW</a> 
		<a href="<%=request.getContextPath()%>/board/boardList.do">게시판리스트</a>
	</header> --%>


</body>
</html>