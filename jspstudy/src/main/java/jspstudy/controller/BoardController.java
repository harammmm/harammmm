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

		//�����η� �� request�� ������ ó��
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); //�ѱ۱��� ����
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		int sizeLimit = 1024*1024*15;
		
		String uploadPath ="D:\\dev\\workspace2\\jspstudy\\src\\main\\webapp\\";
		String saveFolder ="img";
		String saveFullPath = uploadPath+saveFolder;
		
		if (command.equals("/board/boardWrite.do")) {
			System.out.println("�۾��� ȭ�鿡 ������");
			
			//forward�� ����Ͽ� ���� �������� �̵��ϰԲ� 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(request, response);
			
		}else if (command.equals("/board/boardWriteAction.do")) {
			System.out.println("�۾��� ó�� ȭ������ ������");
		
			MultipartRequest multipartRequest = null;
			multipartRequest = new MultipartRequest(request,saveFullPath,sizeLimit,"UTF-8",new DefaultFileRenamePolicy());
			
			
		String subject =  multipartRequest.getParameter("subject");
		String content =  multipartRequest.getParameter("content");
		String writer =  multipartRequest.getParameter("writer");
		//System.out.println(subject+";"+content+";"+writer);
		
		//�����ڿ� ����� ������ ��� ��ü����
		Enumeration files = multipartRequest.getFileNames();
		//��� ��ü�� ���� �̸��� ��´�
		String file = (String)files.nextElement();
		//�Ѿ���� ��ü�߿� �ش�Ǵ� �����̸����� �Ǿ��ִ� �����̸��� �����Ѵ�(����Ǵ� �����̸�)
		String fileName = multipartRequest.getFilesystemName(file);
		//������ �����̸�
		String OriginalFileName = multipartRequest.getOriginalFileName(file);
		
		
		//ip�� ����
		String ip = InetAddress.getLocalHost().getHostAddress();
		
		//int midx = 2; //���� �α��� ����� ���⶧���� 2���� ȸ���� ��ٰ� ����
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
			System.out.println("�Խ��� ����Ʈ ȭ�� ������");
			
			
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
			
			
			//ó��
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);

	        PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
			
			
			
			request.setAttribute("alist", alist); 
			request.setAttribute("pm", pm); 
			
			//�̵� 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
		
		}else if(command.equals("/board/boardContent.do")) {
			System.out.println("�Խ��� ���뺸��� ������");
			
			//1.�Ѿ�� ���� �޴´�
			String bidx = request.getParameter("bidx");
			//System.out.println(bidx);
			int bidx_ = Integer.parseInt(bidx);

			//2.ó���Ѵ�)
			BoardDao bd = new BoardDao(); //boarddao ��ü����
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv); //���ο� ���� ��ġ���� �ڿ���(bv��) �����Ѵ�.
			
			
			//3.�̵��Ѵ� 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardContent.jsp");
			rd.forward(request, response);
	    
		}else if(command.equals("/board/boardModify.do")) {
			System.out.println("�Խ��� �����ϱ�� ������");
	 
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);

			//2.ó���Ѵ�
			BoardDao bd = new BoardDao(); //boarddao ��ü����
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv); //���ο� ���� ��ġ���� �ڿ���(bv��) �����Ѵ�.
			
			
			//3.�̵��Ѵ� 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");
			rd.forward(request, response);
			
	    }else if (command.equals("/board/boardModifyAction.do")) {
			System.out.println("�۾��� ����ȭ������ ������");
		
		String subject =  request.getParameter("subject");
		String content =  request.getParameter("content");
		String writer =  request.getParameter("writer");
		String bidx =  request.getParameter("bidx");
		//System.out.println(bidx);
		int bidx_ = Integer.parseInt(bidx);
		
//		System.out.println(subject+";"+content+";"+writer);
		
		//ip�� ����
		String ip = InetAddress.getLocalHost().getHostAddress();
		//int midx = 2; //���� �α��� ����� ���⶧���� 2���� ȸ���� ��ٰ� ����
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
			System.out.println("�Խ��� �����ϱ�� ������");
	 
			String bidx = request.getParameter("bidx");
			int bidx_ = Integer.parseInt(bidx);

			//2.ó���Ѵ�
			BoardDao bd = new BoardDao(); //boarddao ��ü����
			BoardVo bv = bd.boardSelectOne(bidx_);
			request.setAttribute("bv", bv); //���ο� ���� ��ġ���� �ڿ���(bv��) �����Ѵ�.
			
			
			//3.�̵��Ѵ� 
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardDelete.jsp");
			rd.forward(request, response);
	
	    }else if (command.equals("/board/boardDeleteAction.do")) {
			System.out.println("�۾��� ����ȭ������ ������");
			
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
	    	System.out.println("�亯�ϱ� ȭ������ ������");
	    	
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
	    	//������ ��ü���
	    	String filePath = saveFullPath+File.separator+filename; //������ ��ü���
	    	Path source = Paths.get(filePath); //���� ��ο� �ִ��� Ȯ��

	    	
	    	String mimeType = Files.probeContentType(source);
	    	//���������� ��������� ��´�
	    	response.setContentType(mimeType); //���������� ��������� ��´�

	    	
	    	String encodingFileName= new String(filename.getBytes("UTF-8"));
	    	//÷���ؼ� �ٿ�ε�Ǵ� ������ ��������� ��´�
	    	response.setHeader("Content-Disposition", "attachment;fileName"+encodingFileName);

	    	
	    	//�ش� ��ġ���� �ִ� ������ �о���δ�.
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
			
			
			//ó��
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);

	        PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
			
			
			
			request.setAttribute("alist", alist); 
			request.setAttribute("pm", pm); 
			
			//�̵� 
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
			
			
			//ó��
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);

	        PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
			
			
			
			request.setAttribute("alist", alist); 
			request.setAttribute("pm", pm); 
			
			//�̵� 
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
			
			
			//ó��
			BoardDao bd = new BoardDao();
			int cnt = bd.boardTotal(scri);

	        PageMaker pm = new PageMaker();
			pm.setScri(scri);
			pm.setTotalCount(cnt);
			
			ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
			
			
			
			request.setAttribute("alist", alist); 
			request.setAttribute("pm", pm); 
			
			//�̵� 
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
		
		
		//ó��
		BoardDao bd = new BoardDao();
		int cnt = bd.boardTotal(scri);

        PageMaker pm = new PageMaker();
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
		
		
		
		request.setAttribute("alist", alist); 
		request.setAttribute("pm", pm); 
		
		//�̵� 
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
		
		
		//ó��
		BoardDao bd = new BoardDao();
		int cnt = bd.boardTotal(scri);

        PageMaker pm = new PageMaker();
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
		
		
		
		request.setAttribute("alist", alist); 
		request.setAttribute("pm", pm); 
		
		//�̵� 
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
		
		
		//ó��
		BoardDao bd = new BoardDao();
		int cnt = bd.boardTotal(scri);

        PageMaker pm = new PageMaker();
		pm.setScri(scri);
		pm.setTotalCount(cnt);
		
		ArrayList<BoardVo> alist = bd.BoardSelectAll(scri);
		
		
		
		request.setAttribute("alist", alist); 
		request.setAttribute("pm", pm); 
		
		//�̵� 
		RequestDispatcher rd = request.getRequestDispatcher("/board/broom.jsp");
		rd.forward(request, response);	
	}
	
	//�����ϱ�ȭ�� ����
	else if(command.equals("/board/reservation.do")) {
		//midx�� null���� ���ǰ��� ������ �α����϶�� �ߴ°� ���⿡ �ϱ�
		//String uri = request.getRequestURI(); //������Ʈ ���� �ޱ�
		
		HttpSession session = request.getSession();
		if (session.getAttribute("midx")==null){
			session.setAttribute("saveUrl",request.getRequestURI());
		}
		//�޼ҵ带 ȣ���ؼ� alist�� ��´�.
		BoardDao bd = new BoardDao();
		ArrayList<ReservationDto> alist = bd.reservationlist();
		System.out.println("alist"+alist);
		
		//���������� �ڿ��� �����ؼ� ���� ���� ��� �޼ҵ�(setAttribute)
		//ȭ������ ��������
		request.setAttribute("alist", alist);
		
		//forward�� �����η� �������� ������� ������η� ����
		RequestDispatcher rd = request.getRequestDispatcher("/board/reservation.jsp");
		rd.forward(request, response);
		//forward������� �ѱ��.
	}

		
		//����ó��ȭ�� ����
	else if(command.equals("/board/reservationAction.do")) {
		System.out.println("���� ó�� ȭ������ ������");
		int sidx = Integer.parseInt(request.getParameter("sidx"));
		
		HttpSession session = request.getSession();
		int midx = (int)session.getAttribute("midx");
		
		//BoardDao���� ���� insert ���� �޼ҵ� ���
		BoardDao bd = new BoardDao(); //������ ȣ��
		
		int value=0;
		value = bd.ReservationBoard(sidx,midx); //��Ƽ� ����
		
		PrintWriter out=response.getWriter(); //�ؿ� alert�� ����Ϸ��� �ʿ�
		
		//value���� 1�̸� ����ȴ�
		
		if (value == 1) {
			out.println("<script>alert('������ �Ϸ� �Ǿ����ϴ�.');location.href='"+request.getContextPath()+"/index.jsp'</script>");
			
		}	else {
				out.println("<script>alert('���࿡ �����Ͽ����ϴ�..');location.href='"+request.getContextPath()+"/board/reservation.do'</script>");
		}
		
	}
	
		//���������� ȭ�� ����
}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
