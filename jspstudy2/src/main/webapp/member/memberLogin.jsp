<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<HTML>
 <HEAD>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- link 선언 -->

<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../css/style_index.css">


<!-- script 선언 -->
<script src="https://kit.fontawesome.com/e1bd1cb2a5.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="../js/script.js"></script>
  <TITLE> 로그인페이지 </TITLE> 
  <script>
  function check(){
 //alert("테스트입니다.");
	  var fm = document.frm; 
	  
	  if(fm.memberId.value==""){
	    alert("아이디를 입력하세요");
		fm.memberId.focus();
		return;  
	  }else if (fm.memberPwd.value==""){
	   alert("비밀번호를 입력하세요");
		fm.memberPwd.focus();
		return;  
	  
	  	}alert("로그인하시겠습니까?");
	  	//fm.action = "memberJoinOk.jsp";
	  	//가상경로 사용
	  	fm.action = "<%=request.getContextPath()%>/member/memberLoginAction.do"
	  	fm.method = "post";
	  	fm.submit();
	  
	  
 
  return;
}
  </script>
 </HEAD>

 <BODY>
 <jsp:include page="../header.jsp"></jsp:include>
<center><h1>로그인 페이지</h1></center>
<hr></hr>
<form name="frm">
<div><center>
 <table border="1" style="text-align:left;width:800px;height:300px"><center>
<tr>
<td>아이디</td>
<td><input type="text" name="memberId" size="30"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="password" name="memberPwd" size="30"></td>
</tr>
<tr>
<td></td>
<td>
<input type="button" class="btn btn-light"  name ="button"  value="확인" onclick="check();"> 
<input type="reset" class="btn btn-light"  value="다시작성"> 
<input type="button" class="btn btn-light"  name="button" value="회원가입" onClick="location.href='<%=request.getContextPath()%>/member/memberJoin.do'">


</td>
</tr>
 </table>
 </form>
</div>
  <jsp:include page="../footer.jsp"></jsp:include>
 </BODY>
</HTML>
