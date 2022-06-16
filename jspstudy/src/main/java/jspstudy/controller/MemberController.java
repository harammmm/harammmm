package jspstudy.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jspstudy.domain.MemberVo;
import jspstudy.service.MemberDao;

@WebServlet("/MemberController") //.do로 들어오는것들은 멤버컨트롤러에서 처리하게끔 설정 (서버가 jsp만 작업?설정?하고 .do로 들어오는건 모두 받아서 멤버컨트롤러가 작업함)
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
  //  public MemberController() {
 //       super();
       
 //   }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); //한글깨짐 방지
		
		String uri = request.getRequestURI();
		String pj = request.getContextPath();
		String command = uri.substring(pj.length());
		
		//System.out.println("command:"+command);

		if(command.equals("/member/memberJoinAction.do")) {
		
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			String memberName = request.getParameter("memberName");
			//String memberEmail = request.getParameter("memberEmail");
			String memberphone = request.getParameter("memberphone");
			//String memberJumin = request.getParameter("memberJumin");
			//String memberGender = request.getParameter("memberGender");
			//String memberAddr = request.getParameter("memberAddr");
			//String[] memberHobby = request.getParameterValues("memberHobby");
			
			/*
			 * String hobby = ""; //String로 빈값을 선언
			 * 
			 * 
			 * for(int i=0; i<memberHobby.length; i++){ hobby = hobby + "," +
			 * memberHobby[i]; //out.println(memberHobby[i]+"<br>"); }
			 * 
			 * //substring 함수로 문자열 자르는 법 hobby = hobby.substring(1);
			 */
			
			//IP추출
			String ip = InetAddress.getLocalHost().getHostAddress();

			MemberDao md = new MemberDao();
			int value = md.insertMember(memberId,memberPwd,memberName,memberphone,ip);
			
			PrintWriter out = response.getWriter();
			if(value ==1){
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				
				//	out.println("<script>alert('회원가입 성공'); location.href='"+request.getContextPath()+"'</script>");
			//입력이 안되었으면 memberJoin 페이지로 이동하라는 뜻
			}else{
				response.sendRedirect(request.getContextPath()+"/member/memberJoin.do");
			//	out.println("<script>alert('회원가입 실패');location.href='./memberJoin.html'</script>");
			}
			
		}else if(command.equals("/member/memberJoin.do")) {
		//	System.out.println("test");
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberJoin.jsp");
			rd.forward(request, response);
			
			
		}else if(command.equals("/member/memberList.do")) {
			//System.out.println("test2");
			 MemberDao md = new MemberDao();
			 System.out.println(md);
			 ArrayList<MemberVo> alist = md.memberSelectAll();
			 
			
			 request.setAttribute("alist", alist); 
			 
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberList.jsp");
			rd.forward(request, response);
			
		}else if(command.equals("/member/memberLogin.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/member/memberLogin.jsp");
			rd.forward(request, response);
		
		}else if(command.equals("/member/memberLoginAction.do")) {
			
			//1.넘어 온 값을 받는 부분
			String memberId = request.getParameter("memberId");
			String memberPwd = request.getParameter("memberPwd");
			
			//2. 처리하는 부분
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLogin(memberId, memberPwd);
			HttpSession session = request.getSession();
			
			
			//3. 이동하는 부분
			if(mv !=null) { //mv가 값이 있으면
				session.setAttribute("midx",mv.getMidx());
				session.setAttribute("memberId",mv.getMemberid());
				session.setAttribute("memberName",mv.getMembername());
				
			if(session.getAttribute("saveUrl") != null) {
			response.sendRedirect((String)session.getAttribute("saveUrl"));
			}else {
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
		
			}else {
			response.sendRedirect(request.getContextPath()+"/member/memberLogin.do");	
		}
		}else if(command.equals("/member/memberLogout.do")) {
			HttpSession session = request.getSession();
			session.invalidate(); //초기화됨
			response.sendRedirect(request.getContextPath()+"/");
		}
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
