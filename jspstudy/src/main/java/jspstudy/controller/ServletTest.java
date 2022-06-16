package jspstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ServletTest")
	public class ServletTest extends HttpServlet { //  HttpServlet-> http 통신을 하기위해 자바에서 제공하는 class
	private static final long serialVersionUID = 1L;

    public ServletTest() {  
        super();

    }

    				//doGet 데이터를 노출해서 넘기는 방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); //한글깨짐 방지
		
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head>"
				+ "<title>서블릿</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>안녕하세요</h1>"
				+ "<h2>반갑습니다</h2>"
				+ "</body>"
				+ "</html>");
		
		
		
	}
					//doPost 데이터를 감춰서 넘기는 방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
