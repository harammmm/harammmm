<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="jspstudy.domain.*"%>
<%@ page import="java.util.ArrayList"%>


<%
if (session.getAttribute("midx") == null) {

	out.println(
	"<script>alert('로그인해주세요');location.href='" + request.getContextPath() + "/member/memberLogin.do'</script>");

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
<script>

function reservation(){
	/* alert("테스트입니다"); */
	var fm = document.frm;
	if(fm.sidx.value==""){
		alert("예약이 마감되었습니다.");
		fm.sidx.focus();
		return;
	}
	
	alert("예약하시겠습니까?")
	fm.action = "<%=request.getContextPath()%>/board/reservationAction.do";
		fm.method = "post";
		fm.submit();
		return;

	}
</script>
<title>RESERVATION</title>

</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<center>
		<h1>RESERVATION</h1>
	</center>
	<hr>
<div><center>


								<pre class="about_txt">
<!-- 소개 텍스트 시작 -->
렌탈비용은 2시간 3인기준 6만원 입니다.
이용시간은 촬영시간이 아닌 입장시간과 퇴장시간(정리시간 포함)을 기준으로 합니다.

대관 5~7일전 스케줄 변경 및 취소시는 대관금액의 50%, 대관 2~4일 전 변경 및 취소시에는 60%
전일 변경 및 취소시 80%의 위약금이 발생되는 점 양해부탁드립니다.
대관당일은 취소 불가입니다

세금계산서, 현금영수증 발급 및 카드결제는 부가세 10% 별도 입니다.

파티, 원데이클래스, 영상 및 광고, 공간대여의 경우 별도 문의 바랍니다.
가구 및 소품은 상시 변경될 수 있으며, 촬영이 필요한 소품은 사전문의 바랍니다.

스튜디오의 가구와, 조명, 오브젝트는 고가의 제품들로 조심히 사용부탁드리며
파손하였을 경우 판매가의 100%를 배상하셔야 합니다.
가구 이동시 미리 협의 부탁드리며 절대 바닥에 끌거나 벽에 붙이지 말아주세요

</pre>


						
							<div class="displaynone">
								<p></p>
							</div>
						</div>
	<form name="frm">
	<div><center>
		<select value="" name="sidx" style="width: 350px;">
			<%
			if (request.getAttribute("alist") != null) {
				ArrayList<ReservationDto> alist = (ArrayList<ReservationDto>) request.getAttribute("alist");
				for (ReservationDto rv : alist) {
			%>
			<option value="<%=rv.getSidx()%>">
				<%=rv.getRo_name()%>
				<%=rv.getS_date()%>
				<%=rv.getS_time()%>
			</option>

			<%
			}
			} else {
			out.println("<script>alert('예약이 마감되었습니다.')</script>");
			}
			%>

		
		</select> <br><br> <input type="button" class="btn btn-secondary"  name="button" value="예약하기"
			onclick="reservation()">

	</form>


</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>