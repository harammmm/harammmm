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
		
		
		//������ �����ϰ� ���ϰ����� ����Ǿ����� 1, �ƴϸ� 0�� value ������ ��´�.
	    //���ᰴü�� ���� Statement ������ü�� �����ؼ� stmt�� ��´�
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
		//MemberVo ���� ��ü�� ��� ArrayList Ŭ������ ��ü�����Ѵ�.
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		
		ResultSet rs = null;
		//���������� ���ڿ��� ����� ���´�.
		
		String sql = "select * from b_member where delyn='N' order by midx desc";

		try{
			//���ᰴü�� �ִ� prepareStatement �޼ҵ带 �����ؼ� sqp���ڵ��� ��� ������ü�� �����
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		 	System.out.println("rs"+rs);
		while(rs.next()){
			//�ݺ��Ҷ����� ��ü�����Ѵ�
			MemberVo mv = new MemberVo();
			//rs�� ��Ƴ��� �÷������� mv�� �Űܴ�´�
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
		   
		   String sql="select * from b_member where delyn='N' and memberid=? and memberpwd=?"; //���̵�� �н����尡 �Ѿ���� �ش�Ǵ� ���̵�� �н����带 ã�� ����
		   try {
			pstmt = conn.prepareStatement(sql); //���ڿ��� ����ȭ ��Ű��
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
		    rs = pstmt.executeQuery(); //select������ ����Ҷ� ���� ����
		   
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

