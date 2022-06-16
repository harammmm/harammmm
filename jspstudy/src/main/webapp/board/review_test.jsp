<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
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
	
 <section id="content_box">
        <div class="box">
            <h3>REVIEW</h3>
            <p><a href = "/javabucks/boards/list.do">최신 등록일 순</a> | 
               <a href = "/javabucks/boards/hlist.do"> 조회수 순</a>
             </p>
             <form name="frm" action="<%=request.getContextPath() %>/board/boardList.do" method="post">
				<table border = "0" width = "90%">
					<tr>	
						<td width = "750px" align = "right">
							<input type = 'text' name = 'keyword' size = 20><input type = 'submit' name = 'submit' value = '검색' />
						</td>
					</tr>	
				</table>
			</form>
            <div class="clear"></div>

			
			
            <fieldset
                style="width: 100px; display: inline-block; margin: 10px 20px; padding-left: 0px; padding-right: 0px;">
                <ul class="items" style="padding-left: 20px;">
                    <li>
                    	
                    	<img id = "listimg" src="/javabucks/PJS/filefolder/s-Penguins2.jpg">
                    	
                    </li>
                    <li class="a"><a href = "/jspstudy/board/boardContent.do?bidx=159">사진</a></li>
                    <li class="b">작성자 : </li>
                    <li class="c">사진</li>
                    <li class="d">2022-11-24 10:15</li>
                    <li class="e"><i class="fas fa-eye"></i><span>28</span></li>
                </ul>
            </fieldset>
            
			
            <fieldset
                style="width: 100px; display: inline-block; margin: 10px 20px; padding-left: 0px; padding-right: 0px;">
                <ul class="items" style="padding-left: 20px;">
                    <li>
                    	
                    	<img id = "listimg" src="/javabucks/YMU/img/cutedog2.jpg">
                    	
                    </li>
                    <li class="a"><a href = "/javabucks/boards/content.do?bosidx=37">테스트1</a></li>
                    <li class="b">작성자 : 홍길동</li>
                    <li class="c">테스트1</li>
                    <li class="d">2020-11-24 09:05</li>
                    <li class="e"><i class="fas fa-eye"></i><span>26</span></li>
                </ul>`	
            </fieldset>
            
			
            <fieldset
                style="width: 100px; display: inline-block; margin: 10px 20px; padding-left: 0px; padding-right: 0px;">
                <ul class="items" style="padding-left: 20px;">
                    <li>
                    	
                    	<img id = "listimg" src="/javabucks/YMU/img/cutedog2.jpg">
                    	
                    </li>
                    <li class="a"><a href = "/javabucks/boards/content.do?bosidx=36">귀여운댕댕이</a></li>
                    <li class="b">작성자 : 양민욱</li>
                    <li class="c">댕댕댕</li>
                    <li class="d">2020-11-13 10:10</li>
                    <li class="e"><i class="fas fa-eye"></i><span>24</span></li>
                </ul>
            </fieldset>
            
			
            <fieldset
                style="width: 100px; display: inline-block; margin: 10px 20px; padding-left: 0px; padding-right: 0px;">
                <ul class="items" style="padding-left: 20px;">
                    <li>
                    	
                    	<img id = "listimg" src="/javabucks/YMU/img/cutedog2.jpg">
                    	
                    </li>
                    <li class="a"><a href = "/javabucks/boards/content.do?bosidx=35">전라북도에서 ??</a></li>
                    <li class="b">작성자 : 양민욱</li>
                    <li class="c">안녕하셍여</li>
                    <li class="d">2020-11-13 10:10</li>
                    <li class="e"><i class="fas fa-eye"></i><span>28</span></li>
                </ul>
            </fieldset>
            
			
            <fieldset
                style="width: 100px; display: inline-block; margin: 10px 20px; padding-left: 0px; padding-right: 0px;">
                <ul class="items" style="padding-left: 20px;">
                    <li>
                    	
                    	<img id = "listimg" src="/javabucks/YMU/img/cutedog2.jpg">
                    	
                    </li>
                    <li class="a"><a href = "/javabucks/boards/content.do?bosidx=28">테슷트</a></li>
                    <li class="b">작성자 : 양민욱</li>
                    <li class="c">테스테스</li>
                    <li class="d">2020-11-11 13:37</li>
                    <li class="e"><i class="fas fa-eye"></i><span>33</span></li>
                </ul>
            </fieldset>
                               
        </div>
     <table border = "0" style = "margin-left: 45%">
		<tr>
			<td>
				
			</td>
			<td>
				 
				 <a href="/javabucks/boards/list.do?page=1&keyword=">1</a>
				 
			</td>
			<td>
				
			</td>
		</tr>
	</table>

    <div class="clear"></div>
    </section>
    <div class="form">
    		
		    <button type="button" onclick="location.href='boardwrite.do'">글 작성하기</button>
		    
    </div>
</body>
</html>