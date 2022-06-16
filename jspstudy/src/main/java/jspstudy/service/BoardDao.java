package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jspstudy.dbconn.Dbconn;
import jspstudy.domain.BoardVo;
import jspstudy.domain.Criteria;
import jspstudy.domain.MemberVo;
import jspstudy.domain.ReservationDto;
import jspstudy.domain.SearchCriteria;

public class BoardDao {

	private Connection conn;
	private PreparedStatement pstmt;

	public BoardDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();

	}

	public int insertBoard(String subject, String content, String writer, String ip, int midx, String fileName) {
		int value = 0;

		String sql = "INSERT INTO b_board(originbidx,depth,level_,subject,content,writer,ip,midx,filename)"
				+ "select max(bidx)+1,0,0,'제목','내용','작성자','111.222.333.444',1,null from b_board";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip);
			pstmt.setInt(5, midx);
			pstmt.setString(6, fileName);
			value = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return value;

	}

	public ArrayList<BoardVo> BoardSelectAll(SearchCriteria scri) {
		// MemberVo 여러 객체를 담는 ArrayList 클래스를 객체생성한다.
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
		ResultSet rs = null;
		// 쿼리구문을 문자열로 만들어 놓는다.

		String str = "";
		if (scri.getSearchType().equals("subject")) {
			str = "and subject like ?";

		} else {
			str = "and writer like ?";

		}

		String sql = "SELECT * FROM B_MEMBER ORDER BY midx DESC LIMIT 0,15";

		try {
			// 연결객체에 있는 prepareStatement 메소드를 실행해서 sqp문자들을 담아 구문객체를 만든다
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + scri.getKeyword() + "%");
			pstmt.setInt(2, (scri.getPage() - 1) * 15 + 1);
			pstmt.setInt(3, scri.getPage() * 15);
			rs = pstmt.executeQuery();

			// re.next메소드 -> 다음값이 존재하면 true이고 그 행으로 커서 이동하는 메서드
			while (rs.next()) {
				// 반복할때마다 객체생성한다
				BoardVo bv = new BoardVo();
				// rs에 담아놓은 컬럼값들을 mv에 옮겨담는다

				bv.setBidx(rs.getInt("bidx"));
				bv.setSubject(rs.getString("subject"));
				// bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setLevel_(rs.getInt("level_"));
				bv.setFilename(rs.getString("Filename"));
				alist.add(bv);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
//		System.out.println(alist.get(0).getBidx());
		return alist;
	}

	public BoardVo boardSelectOne(int bidx) {
		BoardVo bv = null;
		ResultSet rs = null;

		String sql = "select * from b_board where bidx=?";

		try {
			pstmt = conn.prepareStatement(sql); // 쿼리화 시킴
			pstmt.setInt(1, bidx); // 첫번째 물음표에 bidx값을 담아라
			rs = pstmt.executeQuery();

			if (rs.next()) { // 다음 값이 true(존재하면)이면 커서는 다음행으로 이동한다.
				bv = new BoardVo();

				bv.setBidx(rs.getInt("bidx")); // rs에 담겨져있는 데이터를 bv에 옮겨 담는다
				bv.setOriginbidx(rs.getInt("originbidx"));
				bv.setDepth(rs.getInt("depth"));
				bv.setLevel_(rs.getInt("level_"));

				bv.setSubject(rs.getString("subject"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday")); // 총 8개의 값을 bv에 옮겨담는다
				bv.setFilename(rs.getString("filename"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				pstmt.close();
				conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return bv;
	}

	public int updateBoard(String subject, String content, String writer, int midx, int bidx_) {
		String sql = "update b_board set subject = ?, content = ?, writer = ?, midx=?, writeday=sysdate where bidx = ?";
		int value = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setInt(4, midx);
			pstmt.setInt(5, bidx_);
			value = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public int deleteBoard(int bidx) {
		String sql = "update b_board set delyn='Y', writeday=sysdate where bidx=?";
		int value = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	public int replyBoard(BoardVo bv) {
		int value = 0;

		String sql1 = "update b_board set depth = depth+1 where originbidx=? and depth>?";

		String sql2 = 	"insert into b_board(originbidx,depth,level_,subject,content,writer,ip,midx)"
				+"select max(bidx)+1,0,0,'제목','내용','작성자','111.222.333.444',1 from b_board";
		

		try {
			conn.setAutoCommit(false); // 자바는 오토커밋기능이 있어서 이걸 꺼주는 기능.자동커밋을 끔
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth());
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth() + 1);
			pstmt.setInt(3, bv.getLevel_() + 1);
			pstmt.setString(4, bv.getSubject());
			pstmt.setString(5, bv.getContent());
			pstmt.setString(6, bv.getWriter());
			pstmt.setString(7, bv.getIp());
			pstmt.setInt(8, bv.getMidx());

			value = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback(); // 하나라도 오류가 나면 롤백처리를 해서 원복처리를 한다.
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return value;

	}

	public int boardTotal(SearchCriteria scri) {
		int cnt = 0;
		ResultSet rs = null;

		String str = "";
		if (scri.getSearchType().equals("subject")) {
			str = "and subject like ?";
		} else {
			str = "and writer like ?";
		}

		String sql = "select count(*) as cnt from b_board where delyn='N'" + str + " ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + scri.getKeyword() + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return cnt;
	}

	
	//예약하기 할때
	public int ReservationBoard(int sidx, int midx) {
		int value = 0;
		System.out.println(sidx + "," + midx);

		String sql = "INSERT INTO b_reservation(ridx,sidx,midx,r_writeday) VALUES(ridx_b_seq.NEXTVAL,?,?,SYSDATE)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sidx);
			pstmt.setInt(2, midx);

			value = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}  finally { 
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return value;}
		}
	
	//예약리스트 받을때
	public ArrayList<ReservationDto> reservationlist(){
		//MemberVo 여러 객체를 담는 ArrayList 클래스를 객체생성한다.
		ArrayList<ReservationDto> alist = new ArrayList<ReservationDto>(); 
		ResultSet rs = null;
		
		String sql = "SELECT S_TIME,S_DATE,C.ROIDX as roidx , a.sidx as sidx,c.Ro_name FROM B_SCHEDULE A,B_RESERVATION B,B_ROOM C WHERE A.SIDX = B.SIDX(+) AND A.ROIDX=C.ROIDX AND (R_APP='N' OR R_APP IS NULL) AND S_DELYN='N' ORDER BY A.SIDX";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println(rs); 
			while(rs.next()) {
				ReservationDto rv = new ReservationDto();
				rv.setS_time(rs.getString("S_TIME"));
				rv.setS_date(rs.getString("S_DATE"));
				rv.setRoidx(rs.getInt("Roidx"));
				rv.setSidx(rs.getInt("Sidx"));
				rv.setRo_name(rs.getString("Ro_name"));
				alist.add(rv);
				
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	//	System.out.println("alist는"+alist.get(0).getRidx());
		return alist;
	}

	
}
	//마이페이지에 예약값 보여줄때
	
