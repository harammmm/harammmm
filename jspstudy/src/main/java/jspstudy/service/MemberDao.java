package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspstudy.dbconn.Dbconn;
import jspstudy.domain.MemberVo;

public class MemberDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	public MemberDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
		
		
	}
	

	public int insertMember(String memberId, String memberPwd,String memberName,String memberphone, String ip){
		int value=0;	
		
		String sql = "insert into b_member(MEMBERID,MEMBERPWD,MEMBERNAME,MEMBERPHONE,MEMBERIP) " 
				+"values(?,?,?,?,?)";
		
		
		//구문을 실행하고 리턴값으로 실행되었으면 1, 아니면 0을 value 변수에 담는다.
	    //연결객체를 통해 Statement 구문객체를 생성해서 stmt에 담는다
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberId);
			pstmt.setString(2,memberPwd);
			pstmt.setString(3,memberName);
			pstmt.setString(4,memberphone);
			pstmt.setString(5,ip);
			value = pstmt.executeUpdate();
			
	   // Statement stmt =conn.createStatement();
		//value = stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return value;
		
	}


	public ArrayList<MemberVo> memberSelectAll(){
		//MemberVo 여러 객체를 담는 ArrayList 클래스를 객체생성한다.
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		
		ResultSet rs = null;
		//쿼리구문을 문자열로 만들어 놓는다.
		
		String sql = "select * from b_member where delyn='N' order by midx desc";

		try{
			//연결객체에 있는 prepareStatement 메소드를 실행해서 sqp문자들을 담아 구문객체를 만든다
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		 	System.out.println("rs"+rs);
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
		}finally {
		
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("alist"+alist);
		return alist;
	}
	    
	   public MemberVo memberLogin(String memberId, String memberPwd) {
		   MemberVo mv = null;
		   ResultSet rs = null;
		   
		   String sql="select * from b_member where delyn='N' and memberid=? and memberpwd=?"; //아이디와 패스워드가 넘어오면 해당되는 아이디와 패스워드를 찾는 쿼리
		   try {
			pstmt = conn.prepareStatement(sql); //문자열을 쿼리화 시키는
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
		    rs = pstmt.executeQuery(); //select구문을 사용할때 쓰는 쿼리
		   
		   if (rs.next()) {
			   mv = new MemberVo();
			   mv.setMidx(rs.getInt("midx"));
			   mv.setMemberid(rs.getString("memberId"));
			   mv.setMembername(rs.getString("memberName"));

		   }
		   
		   } catch (SQLException e) {
	
			e.printStackTrace();
		}finally {
			try {
				rs.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		   
		   
		   return mv;
	   }
	    
	    

	
}

