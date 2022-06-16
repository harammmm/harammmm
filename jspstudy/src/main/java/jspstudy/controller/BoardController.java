package jspstudy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jspstudy.domain.BoardVo;
import jspstudy.domain.Criteria;
import jspstudy.domain.MemberVo;
import jspstudy.domain.PageMaker;
import jspstudy.domain.ReservationDto;
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;
import jspstudy.service.MemberDao;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//가상경로로 온 request가 있으면 처리
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); //한글깨짐 방지
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		int sizeLimit = 1024*1024*15;
		
		String uploadPath ="D:\\dev\\workspace2\\jspstudy\\src\\main\\webapp\\";
		String saveFolder ="img";
		String saveFullPath = uploadPath+saveFolder;
		
		if (command.equals("/board/boardWrite.do")) {
			System.out.println("글쓰기 화면에 들어왔음");
			
			//forward를 사용하여 실제 페이지로 이동하게끔 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardWriteAction.do")) {
			System.out.println("글쓰기 처리 화면으로 들어왔음");
		
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,saveFullPath,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
			
			
		String subject =  multipartRequest.getParameter("subject");
		String content =  multipartRequest.getParameter("content");
		String writer =  multipartRequest.getParameter("writer");
		//System.out.println(subject+";"+content+";"+writer);
		
		//열거자에 저장될 파일을 담는 객체생성
		Enumeration files = multipartRequest.getFileNames();
		//담긴 객체의 파일 이름을 얻는다
		String file = (String)files.nextElement();
		//넘어오는 객체중에 해당되는 파일이름으로 되어있는 파일이름을 추출한다(저장되는 파일이름)
		String fileName = multipartRequest.getFilesystemName(file);
		//원본의 파일이름
		String OriginalFileName = multipartRequest.getOriginalFileName(file);
		
		
		//ip값 추출
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		//int midx = 2; //아직 로그인 기능이 없기때문에 2번인 회원이 썼다고 가정
		HttpSession session = request.getSession();
		int midx = (int)session.getAttribute("midx");
		
		BoardDao bd = new BoardDao();
		int value = bd.insertBoard(subject, content, writer, ip, midx,fileName);
		
			if(value==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardWrite.do");
			}
		
		}else if(command.equals("/board/boardList.do")) {
			System.out.println("게시판 리스트 화면 들어왔음");
			
			
			String page = request.getParameter("page");
			if(page==null) page = "1";
			int pagex = Integer.parseInt(page);
			
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword= "";
			String searchType = request.getParameter("searchType");	
			if (searchType == null) searchType= "subject";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setSearchType(searchType);
			scri.setKeyword(keyword);
			
			
			//처리
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);

	        PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
			
			
			
			request.setAttribute("alist", alist); 
			request.setAttribute("pm", pm); 
			
			//이동 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
		
		}else if(command.equals("/board/boardContent.do")) {
			System.out.println("게시판 내용보기로 들어왔음");
			
			//1.넘어온 값을 받는다
			String bidx = request.getParameter("bidx");
			//System.out.println(bidx);
			int bidx_ = Integer.parseInt(bidx);

			//2.처리한다)
			BoardDao bd = new BoardDao(); //boarddao 객체생성
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv); //내부에 같은 위치에서 자원을(bv를) 공유한다.
			
			
			//3.이동한다 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
	    
		}else if(command.equals("/board/boardModify.do")) {
			System.out.println("게시판 수정하기로 들어왔음");
	 
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);

			//2.처리한다
			BoardDao bd = new BoardDao(); //boarddao 객체생성
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv); //내부에 같은 위치에서 자원을(bv를) 공유한다.
			
			
			//3.이동한다 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");
			rd.forward(request, response);
			
	    }else if (command.equals("/board/boardModifyAction.do")) {
			System.out.println("글쓰기 수정화면으로 들어왔음");
		
		String subject =  request.getParameter("subject");
		String content =  request.getParameter("content");
		String writer =  request.getParameter("writer");
		String bidx =  request.getParameter("bidx");
		//System.out.println(bidx);
		int bidx_ = Integer.parseInt(bidx);
		
//		System.out.println(subject+";"+content+";"+writer);
		
		//ip값 추출
		String ip = InetAddress.getLocalHost().getHostAddress();
		//int midx = 2; //아직 로그인 기능이 없기때문에 2번인 회원이 썼다고 가정
		HttpSession session = request.getSession();
		int midx = (int)session.getAttribute("midx");
		
		BoardDao bd = new BoardDao();
		int value = bd.updateBoard(subject, content, writer, midx, bidx_);
		//System.out.println(value);
			if(value==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do?bidx="+bidx);
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardModify.do?bidx="+bidx);
			}
	    }else if(command.equals("/board/boardDelete.do")) {
			System.out.println("게시판 삭제하기로 들어왔음");
	 
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);

			//2.처리한다
			BoardDao bd = new BoardDao(); //boarddao 객체생성
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv); //내부에 같은 위치에서 자원을(bv를) 공유한다.
			
			
			//3.이동한다 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");
			rd.forward(request, response);
	
	    }else if (command.equals("/board/boardDeleteAction.do")) {
			System.out.println("글쓰기 수정화면으로 들어왔음");
			
		String bidx =  request.getParameter("bidx");
		//System.out.println(bidx);
		int bidx_ = Integer.parseInt(bidx);
		
		BoardDao bd = new BoardDao();
		int value = bd.deleteBoard(bidx_);
		//System.out.println(value);
			if(value==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");
				
			}
		}else if (command.equals("/board/boardReply.do")) {
				
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");

			
			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			
			
			request.setAttribute("bv", bv);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardReply.jsp");
			rd.forward(request, response);
			
	    }else if (command.equals("/board/boardReplyAction.do")) {
	    	System.out.println("답변하기 화면으로 들어왔음");
	    	
			String bidx = request.getParameter("bidx");
			String originbidx = request.getParameter("originbidx");
			String depth = request.getParameter("depth");
			String level_ = request.getParameter("level_");
			String subject =  request.getParameter("subject");
			String content =  request.getParameter("content");
			String writer =  request.getParameter("writer");
			String ip = InetAddress.getLocalHost().getHostAddress();
			//int midx = 2; 
			HttpSession session = request.getSession();
			int midx = (int)session.getAttribute("midx");
			
			BoardVo bv = new BoardVo();
			bv.setBidx(Integer.parseInt(bidx));
			bv.setOriginbidx(Integer.parseInt(originbidx));
			bv.setDepth(Integer.parseInt(depth));
			bv.setLevel_(Integer.parseInt(level_));
			bv.setSubject(subject);
			bv.setContent(content);
			bv.setWriter(writer);
			bv.setIp(ip);
			bv.setMidx(midx);
			
			
			BoardDao bd = new BoardDao();
			int value = bd.replyBoard(bv);
			
			if(value==1) {
				response.sendRedirect(request.getContextPath()+"/board/boardList.do");
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardContent.do");
				
			}
	    }else if (command.equals("/board/fileDownload.do")) {
		
	    	String filename = request.getParameter("filename");
	    	//파일의 전체경로
	    	String filePath = saveFullPath+File.separator+filename; //파일의 전체경로
	    	Path source = Paths.get(filePath); //실제 경로에 있는지 확인

	    	
	    	String mimeType = Files.probeContentType(source);
	    	//파일형식을 헤더정보에 담는다
	    	response.setContentType(mimeType); //파일형식을 헤더정보에 담는다

	    	
	    	String encodingFileName= new String(filename.getBytes("UTF-8"));
	    	//첨부해서 다운로드되는 파일을 헤더정보에 담는다
	    	response.setHeader("Content-Disposition", "attachment;fileName"+encodingFileName);

	    	
	    	//해당 위치에서 있는 파일을 읽어들인다.
	    	FileInputStream fileInputStream = new FileInputStream(filePath);
	    	
	    	ServletOutputStream servletOutStream = response.getOutputStream();

	    	
	    	byte[] b = new byte[4096];
	    	
	    	int read = 0;
	    	
	    	while((read = fileInputStream.read(b, 0, b.length))!=-1) {
	    		
	    		servletOutStream.write(b, 0, read);

	    	}
	    	servletOutStream.flush();
	    	servletOutStream.close();
	    	fileInputStream.close();
	    	
	    }
		
	    else if(command.equals("/board/about.do")) {
			System.out.println("about");
			
			
			String page = request.getParameter("page");
			if(page==null) page = "1";
			int pagex = Integer.parseInt(page);
			
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword= "";
			String searchType = request.getParameter("searchType");	
			if (searchType == null) searchType= "subject";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setSearchType(searchType);
			scri.setKeyword(keyword);
			
			
			//처리
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);

	        PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
			
			
			
			request.setAttribute("alist", alist); 
			request.setAttribute("pm", pm); 
			
			//이동 
			RequestDispatcher rd = request.getRequestDispatcher("/board/about.jsp");
			rd.forward(request, response);
	    } else if(command.equals("/board/charge.do")) {
			System.out.println("about");
			
			
			String page = request.getParameter("page");
			if(page==null) page = "1";
			int pagex = Integer.parseInt(page);
			
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword= "";
			String searchType = request.getParameter("searchType");	
			if (searchType == null) searchType= "subject";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setSearchType(searchType);
			scri.setKeyword(keyword);
			
			
			//처리
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);

	        PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
			
			
			
			request.setAttribute("alist", alist); 
			request.setAttribute("pm", pm); 
			
			//이동 
			RequestDispatcher rd = request.getRequestDispatcher("/board/charge.jsp");
			rd.forward(request, response);
	    } else if(command.equals("/board/caution.do")) {
			System.out.println("about");
			
			
			String page = request.getParameter("page");
			if(page==null) page = "1";
			int pagex = Integer.parseInt(page);
			
			
			String keyword = request.getParameter("keyword");
			if (keyword == null) keyword= "";
			String searchType = request.getParameter("searchType");	
			if (searchType == null) searchType= "subject";
			
			SearchCriteria scri = new SearchCriteria();
			scri.setPage(pagex);
			scri.setSearchType(searchType);
			scri.setKeyword(keyword);
			
			
			//처리
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);

	        PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
			
			
			
			request.setAttribute("alist", alist); 
			request.setAttribute("pm", pm); 
			
			//이동 
			RequestDispatcher rd = request.getRequestDispatcher("/board/caution.jsp");
			rd.forward(request, response);
			
	} else if(command.equals("/board/location.do")) {
		System.out.println("about");
		
		
		String page = request.getParameter("page");
		if(page==null) page = "1";
		int pagex = Integer.parseInt(page);
		
		
		String keyword = request.getParameter("keyword");
		if (keyword == null) keyword= "";
		String searchType = request.getParameter("searchType");	
		if (searchType == null) searchType= "subject";
		
		SearchCriteria scri = new SearchCriteria();
		scri.setPage(pagex);
		scri.setSearchType(searchType);
		scri.setKeyword(keyword);
		
		
		//처리
		BoardDao bd = new BoardDao();
		int cnt = bd.boardTotal(scri);

        PageMaker pm = new PageMaker();
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
		
		
		
		request.setAttribute("alist", alist); 
		request.setAttribute("pm", pm); 
		
		//이동 
		RequestDispatcher rd = request.getRequestDispatcher("/board/location.jsp");
		rd.forward(request, response);	
	}
	else if(command.equals("/board/aroom.do")) {
		System.out.println("about");
		
		
		String page = request.getParameter("page");
		if(page==null) page = "1";
		int pagex = Integer.parseInt(page);
		
		
		String keyword = request.getParameter("keyword");
		if (keyword == null) keyword= "";
		String searchType = request.getParameter("searchType");	
		if (searchType == null) searchType= "subject";
		
		SearchCriteria scri = new SearchCriteria();
		scri.setPage(pagex);
		scri.setSearchType(searchType);
		scri.setKeyword(keyword);
		
		
		//처리
		BoardDao bd = new BoardDao();
		int cnt = bd.boardTotal(scri);

        PageMaker pm = new PageMaker();
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
		
		
		
		request.setAttribute("alist", alist); 
		request.setAttribute("pm", pm); 
		
		//이동 
		RequestDispatcher rd = request.getRequestDispatcher("/board/aroom.jsp");
		rd.forward(request, response);	
	}
	else if(command.equals("/board/broom.do")) {
		System.out.println("about");
		
		
		String page = request.getParameter("page");
		if(page==null) page = "1";
		int pagex = Integer.parseInt(page);
		
		
		String keyword = request.getParameter("keyword");
		if (keyword == null) keyword= "";
		String searchType = request.getParameter("searchType");	
		if (searchType == null) searchType= "subject";
		
		SearchCriteria scri = new SearchCriteria();
		scri.setPage(pagex);
		scri.setSearchType(searchType);
		scri.setKeyword(keyword);
		
		
		//처리
		BoardDao bd = new BoardDao();
		int cnt = bd.boardTotal(scri);

        PageMaker pm = new PageMaker();
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
		
		
		
		request.setAttribute("alist", alist); 
		request.setAttribute("pm", pm); 
		
		//이동 
		RequestDispatcher rd = request.getRequestDispatcher("/board/broom.jsp");
		rd.forward(request, response);	
	}
	
	//예약하기화면 시작
	else if(command.equals("/board/reservation.do")) {
		//midx값 null에서 세션값이 없으면 로그인하라고 뜨는걸 여기에 하기
		//String uri = request.getRequestURI(); //리퀘스트 값도 받기
		
		HttpSession session = request.getSession();
		if (session.getAttribute("midx")==null){
			session.setAttribute("saveUrl",request.getRequestURI());
		}
		//메소드를 호출해서 alist에 담는다.
		BoardDao bd = new BoardDao();
		ArrayList<ReservationDto> alist = bd.reservationlist();
		System.out.println("alist"+alist);
		
		//내부적으로 자원을 공유해서 쓸때 값을 담는 메소드(setAttribute)
		//화면으로 가져간다
		request.setAttribute("alist", alist);
		
		//forward는 가상경로로 들어왔으면 포워드로 실제경로로 들어가게
		RequestDispatcher rd = request.getRequestDispatcher("/board/reservation.jsp");
		rd.forward(request, response);
		//forward방식으로 넘긴다.
	}

		
		//예약처리화면 시작
	else if(command.equals("/board/reservationAction.do")) {
		System.out.println("예약 처리 화면으로 들어왔음");
		int sidx = Integer.parseInt(request.getParameter("sidx"));
		
		HttpSession session = request.getSession();
		int midx = (int)session.getAttribute("midx");
		
		//BoardDao에서 만든 insert 구문 메소드 사용
		BoardDao bd = new BoardDao(); //생성자 호출
		
		int value=0;
		value = bd.ReservationBoard(sidx,midx); //담아서 실행
		
		PrintWriter out=response.getWriter(); //밑에 alert문 사용하려면 필요
		
		//value값이 1이면 실행된다
		
		if (value == 1) {
			out.println("<script>alert('예약이 완료 되었습니다.');location.href='"+request.getContextPath()+"/index.jsp'</script>");
			
		}	else {
				out.println("<script>alert('예약에 실패하였습니다..');location.href='"+request.getContextPath()+"/board/reservation.do'</script>");
		}
		
	}
	
		//마이페이지 화면 시작
}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
