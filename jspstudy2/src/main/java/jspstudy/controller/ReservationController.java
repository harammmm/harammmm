package jspstudy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import jspstudy.domain.SearchCriteria;
import jspstudy.service.BoardDao;
import jspstudy.service.MemberDao;


@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {
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
			
		}
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
