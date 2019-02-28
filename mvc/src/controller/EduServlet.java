package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EduServlet")
public class EduServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		int score = Integer.parseInt(request.getParameter("score"));

		if (score >= 90) {
			request.getRequestDispatcher("/jspexam/gradeA.jsp").forward(request, response);
		} else if (score >= 80) {
			request.getRequestDispatcher("/jspexam/gradeB.jsp").forward(request, response);
		} else if (score >= 70) {
			request.getRequestDispatcher("/jspexam/gradeC.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/jspexam/gradeD.jsp").forward(request, response);
		}
	}

}
