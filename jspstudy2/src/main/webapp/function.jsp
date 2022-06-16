<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="jspstudy.domain.*" %>
    <%!

public int insertMember(Connection conn,String memberId, String memberPwd,String memberName, String memberGender, String memberAddr, String memberJumin,String memberphone, String hobby, String memberEmail, String ip){
	int value=0;	
	PreparedStatement pstmt = null;
	
	String sql = "insert into b_member(MIDX,MEMBERID,MEMBERPWD,MEMBERNAME,MEMBERGENDER,MEMBERADDR,MEMBERJUMIN,MEMBERPHONE,MEMBERHOBBY,MEMBEREMAIL,MEMBERIP) " 
			+"values(midx_b_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
	
	
	//구문을 실행하고 리턴값으로 실행되었으면 1, 아니면 0을 value 변수에 담는다.
    //연결객체를 통해 Statement 구문객체를 생성해서 stmt에 담는다
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,memberId);
		pstmt.setString(2,memberPwd);
		pstmt.setString(3,memberName);
		pstmt.setString(4,memberGender);
		pstmt.setString(5,memberAddr);
		pstmt.setString(6,memberJumin);
		pstmt.setString(7,memberphone);
		pstmt.setString(8,hobby);
		pstmt.setString(9,memberEmail);
		pstmt.setString(10,ip);
		value = pstmt.executeUpdate();
		
    Statement stmt =conn.createStatement();
	value = stmt.executeUpdate(sql);
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return value;
	
}


public ArrayList<MemberVo> memberSelectAll(Connection conn){
	//MemberVo 여러 객체를 담는 ArrayList 클래스를 객체생성한다.
	ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//쿼리구문을 문자열로 만들어 놓는다.
	
	String sql = "select * from b_member where delyn='N' order by midx desc";

	try{
		//연결객체에 있는 prepareStatement 메소드를 실행해서 sqp문자들을 담아 구문객체를 만든다
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
	 	
	while(rs.next()){
		//반복할때마다 객체생성한다
		MemberVo mv = new MemberVo();
		//rs에 담아놓은 컬럼값들을 mv에 옮겨담는다
		mv.setMidx(rs.getInt("midx"));
		mv.setMembername(rs.getString("memberName"));
		mv.setMemberphone(rs.getString("memberphone"));
		mv.setWriteday(rs.getString("writeday"));
		alist.add(mv);
	
	
	}
	
	
	}catch(Exception e){
		e.printStackTrace();
	}
	return alist;
}
    
    
    
    
%>	
	