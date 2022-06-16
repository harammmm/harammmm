package jspstudy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ServletTest")
	public class ServletTest extends HttpServlet { //  HttpServlet-> http ����� �ϱ����� �ڹٿ��� �����ϴ� class
	private static final long serialVersionUID = 1L;

    public ServletTest() {  
        super();

    }

    				//doGet �����͸� �����ؼ� �ѱ�� ���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); //�ѱ۱��� ����
		
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<head>"
				+ "<title>����</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>�ȳ��ϼ���</h1>"
				+ "<h2>�ݰ����ϴ�</h2>"
				+ "</body>"
				+ "</html>");
		
		
		
	}
					//doPost �����͸� ���缭 �ѱ�� ���
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
