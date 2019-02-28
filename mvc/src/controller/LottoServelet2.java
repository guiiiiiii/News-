package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/lotto2")
public class LottoServelet2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		int r = (int)(Math.random()*6+1);
		int n = Integer.parseInt(request.getParameter("num"));
		
		System.out.println("전달된 값 : "+n+" , 추출된 값 : "+r);
		
		//String command = request.getParameter("1");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("cnt")==null) {
			session.setAttribute("cnt", new int[1]); 
		}
		int[] session_v = (int[])session.getAttribute("cnt");
		session_v[0] ++;
		if(session_v[0]<=3) {
			if(r==n) {
				session.setAttribute("result","success");
				session_v[0]+=5;
			}
			else {
				session.setAttribute("result","fail");
			}
		}
		else {
			session.setAttribute("result","not");
		}
		

		request.getRequestDispatcher("/jspexam/lottoView.jsp").forward(request, response);
		

	}



}
